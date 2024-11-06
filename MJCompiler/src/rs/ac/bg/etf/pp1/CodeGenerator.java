package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.AddopPlus;
import rs.ac.bg.etf.pp1.ast.BoolFactor;
import rs.ac.bg.etf.pp1.ast.CharFactor;
import rs.ac.bg.etf.pp1.ast.Designator;
import rs.ac.bg.etf.pp1.ast.DesignatorBracket;
import rs.ac.bg.etf.pp1.ast.DesignatorDoubleBracket;
import rs.ac.bg.etf.pp1.ast.DesignatorFactor;
import rs.ac.bg.etf.pp1.ast.DesignatorIdent;
import rs.ac.bg.etf.pp1.ast.DesignatorStatementAssign;
import rs.ac.bg.etf.pp1.ast.DesignatorStatementDec;
import rs.ac.bg.etf.pp1.ast.DesignatorStatementInc;
import rs.ac.bg.etf.pp1.ast.Expr;
import rs.ac.bg.etf.pp1.ast.ExprTermMin;
import rs.ac.bg.etf.pp1.ast.ListExpr;
import rs.ac.bg.etf.pp1.ast.ListTerm;
import rs.ac.bg.etf.pp1.ast.MethodDecl;
import rs.ac.bg.etf.pp1.ast.MethodDeclName;
import rs.ac.bg.etf.pp1.ast.MulopDiv;
import rs.ac.bg.etf.pp1.ast.MulopMul;
import rs.ac.bg.etf.pp1.ast.NewFactorBracket;
import rs.ac.bg.etf.pp1.ast.NewFactorDoubleBracket;
import rs.ac.bg.etf.pp1.ast.NumFactor;
import rs.ac.bg.etf.pp1.ast.PrintNumStmt;
import rs.ac.bg.etf.pp1.ast.PrintStmt;
import rs.ac.bg.etf.pp1.ast.ProgName;
import rs.ac.bg.etf.pp1.ast.ReadStmt;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.ast.Term;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;

public class CodeGenerator extends VisitorAdaptor {
	
	private int mainPc;
	
	public int getMainPc(){
		return mainPc;
	}
	
	public void visit(ProgName node) {
		Obj chr = Tab.find("chr");
		Obj ord = Tab.find("ord");
		Obj len = Tab.find("len");
		// chr() 
		chr.setAdr(Code.pc);
		Code.put(Code.return_);
		// ord() 
		ord.setAdr(Code.pc);
		Code.put(Code.return_);
		// len() 
		len.setAdr(Code.pc);
		Code.put(Code.arraylength);
		Code.put(Code.return_);
	}
	
	public void visit(MethodDeclName methodDeclName){
		
		if("main".equalsIgnoreCase(methodDeclName.getName())){
			mainPc = Code.pc;
		}
		methodDeclName.obj.setAdr(Code.pc);
		// Collect arguments and local variables
		SyntaxNode methodNode = methodDeclName.getParent();
	
		VarCounter varCnt = new VarCounter();
		methodNode.traverseTopDown(varCnt);
		
		FormParamCounter fpCnt = new FormParamCounter();
		methodNode.traverseTopDown(fpCnt);
		
		// Generate the entry
		Code.put(Code.enter);
		Code.put(fpCnt.getCount());
		Code.put(fpCnt.getCount() + varCnt.getCount());
	
	}
	
	
	public void visit(MethodDecl methodDecl){
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(PrintStmt printStmt){
		if(printStmt.getExpr().struct == Tab.intType){
			Code.loadConst(5);
			Code.put(Code.print);
		} else if(printStmt.getExpr().struct == Tab.charType){
			Code.loadConst(1);
			Code.put(Code.bprint);
		} else if(printStmt.getExpr().struct == SemanticAnalizer.boolType){
			Code.loadConst(5);
			Code.put(Code.print);
		}
		
	}
	
	public void visit(ReadStmt readStmt){
		if(readStmt.getDesignator().obj.getType() == Tab.intType){
			Code.put(Code.read);
		} else if(readStmt.getDesignator().obj.getType() == Tab.charType){
			Code.put(Code.bread);
		} else if(readStmt.getDesignator().obj.getType() == SemanticAnalizer.boolType){
			Code.put(Code.read);
		}
		Code.store(readStmt.getDesignator().obj);
	}
	
	
	
	public void visit(PrintNumStmt printNumStmt){ 
		if(printNumStmt.getExpr().struct == Tab.intType){
			Code.loadConst(printNumStmt.getN2());
			Code.put(Code.print);
		} else if(printNumStmt.getExpr().struct == Tab.charType){
			Code.loadConst(printNumStmt.getN2());
			Code.put(Code.bprint);
		} else if(printNumStmt.getExpr().struct == SemanticAnalizer.boolType){
			Code.loadConst(printNumStmt.getN2());
			Code.put(Code.print);
		}
		
	}
	
	
	
	
	
	public void visit(NumFactor num){
		Obj con = Tab.insert(Obj.Con, "$", num.struct);
		con.setLevel(0);
		con.setAdr(num.getN1()); //vrednost
		
		Code.load(con);
	}
	
	public void visit(CharFactor ch){
		Obj con = Tab.insert(Obj.Con, "$", ch.struct);
		con.setLevel(0);
		con.setAdr(ch.getC1()); //vrednost
		
		Code.load(con);
	}

	public void visit(BoolFactor bl){
		Obj con = Tab.insert(Obj.Con, "$", bl.struct);
		con.setLevel(0);
		if (bl.getB1()) {
			con.setAdr(1); //vrednost
		} else {
			con.setAdr(0); //vrednost
		}
		
		Code.load(con);
	}
	
	
	public void visit(DesignatorFactor designatorFactor){
		Code.load(designatorFactor.getDesignator().obj);
	}
	
	public void visit(DesignatorStatementAssign designatorStatementAssign){
		Code.store(designatorStatementAssign.getDesignator().obj);
		

	}
	
	public void visit(DesignatorStatementInc designatorStatementInc){
		Code.load(designatorStatementInc.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(designatorStatementInc.getDesignator().obj);
	}
	
	public void visit(DesignatorStatementDec designatorStatementDec){
		Code.load(designatorStatementDec.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.store(designatorStatementDec.getDesignator().obj);

	}
	
	public void visit(ListExpr listExpr){
		if (listExpr.getAddop().getClass() == AddopPlus.class) {
			Code.put(Code.add);
		} else {
			Code.put(Code.sub);
		}
		
	}
	
	public void visit(ListTerm listTerm){
		if (listTerm.getMulop().getClass() == MulopMul.class) {
			Code.put(Code.mul);
		} else if (listTerm.getMulop().getClass() == MulopDiv.class) {
			Code.put(Code.div);
		} else {
			Code.put(Code.rem);
		}
	}
	
	public void visit(Term term){
		if (term.getParent().getClass() == ExprTermMin.class) {
			Code.put(Code.neg);
		}
	}
	
	public void visit(NewFactorBracket newFactorBracket) {
		
		if(newFactorBracket.getType().struct == Tab.intType){
			Code.put(Code.newarray);
			Code.put(1);
		} else if(newFactorBracket.getType().struct == Tab.charType){
			Code.put(Code.newarray);
			Code.put(0);
		} else if(newFactorBracket.getType().struct == SemanticAnalizer.boolType){
			Code.put(Code.newarray);
			Code.put(1);
		}
		
	}
	
	public void visit(NewFactorDoubleBracket newFactorDoubleBracket) {
		
		Code.put(Code.dup_x1);
		Code.put(Code.mul);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.loadConst(0);
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
		Code.put(Code.newarray);
		Code.put(1);
		Code.put(Code.dup_x2);
		Code.put(Code.dup_x2);
		Code.put(Code.pop);
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
		Code.put(Code.astore);
		
	}
	
	
	public void visit(DesignatorBracket designatorBracket){
		Code.load(designatorBracket.getArrayName().obj);
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
	}
	
	public void visit(DesignatorDoubleBracket designatorDoubleBracket){
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
		Code.load(designatorDoubleBracket.getArrayName().obj);
		Code.loadConst(0);
		Code.put(Code.aload);
		Code.put(Code.mul);
		Code.put(Code.add);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.load(designatorDoubleBracket.getArrayName().obj);
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
	}
	
	
	
	
	
	
	

}
