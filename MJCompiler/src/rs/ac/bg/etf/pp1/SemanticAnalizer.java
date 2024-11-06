package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticAnalizer extends VisitorAdaptor {

	
	Obj currentMethod = null;
	boolean returnFound = false;
	boolean errorDetected = false;
	int nVars;
	private Obj currentType = null;
	private Type currentMethodType = null;
	public static final Struct boolType = new Struct(Struct.Bool);
	
	Logger log = Logger.getLogger(getClass());

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}

	
	public void visit(ProgName progName) {
		progName.obj = Tab.insert(Obj.Prog, progName.getName(), Tab.noType);
		Tab.openScope();
	}
	
	public void visit(Program program) {
		if (Tab.find("main") == Tab.noObj) {
			report_error("Ne postoji main", program);
		}
		nVars = Tab.currentScope.getnVars();
		Tab.chainLocalSymbols(program.getProgName().obj);
		Tab.closeScope();
	}
	
	public void visit(VarDeclRSingle varDeclRSingle) {		
		if (Tab.find(varDeclRSingle.getName()) != Tab.noObj) {
			report_error("Vec postoji promenljiva sa zadatim imenom "  + varDeclRSingle.getName(), varDeclRSingle);
			return;
		}
		report_info("Deklarisana promenljiva "+ varDeclRSingle.getName(), varDeclRSingle);
		Tab.insert(Obj.Var, varDeclRSingle.getName(), varDeclRSingle.getVarDeclR().struct);
	}
	
	@Override
	public void visit(BracketVarDeclR bracketVarDeclR) {
		bracketVarDeclR.struct = new Struct(Struct.Array, currentType.getType());
	}

	@Override
	public void visit(NoBracketVarDeclR noBracketVarDeclR) {
		noBracketVarDeclR.struct = currentType.getType();
	}
	
	public void visit(DoubleBracketVarDeclR doubleBracketVarDeclR) {
		Struct mat = new Struct(Struct.Array, currentType.getType());
		doubleBracketVarDeclR.struct = new Struct(Struct.Array, mat);
	}
	
	public void visit(Type type){
    	Obj typeNode = Tab.find(type.getName());
    	if(typeNode == Tab.noObj){
    		report_error("Nije pronadjen tip " + type.getName() + " u tabeli simbola! ", null);
    		type.struct = Tab.noType;
    	}else{
    		if(Obj.Type == typeNode.getKind()){
    			type.struct = typeNode.getType();
    		}else{
    			report_error("Greska: Ime " + type.getName() + " ne predstavlja tip!", type);
    			type.struct = Tab.noType;
    		}
    	}
    	currentType = typeNode;
    }
	
	public void visit(ConstDeclSingle constDeclSingle) {
		if (currentType == null) {
			return;
		}
		if (!constDeclSingle.getConst().obj.getType().equals(currentType.getType())) {
			report_error("Nekompatibilni tiplovi u deklaraciji promenljive " + constDeclSingle.getName(), constDeclSingle);
			return;
		}
		if (Tab.find(constDeclSingle.getName()) != Tab.noObj) {
			report_error("Vec postoji konstanta sa zadatim imenom "  + constDeclSingle.getName(), constDeclSingle);
			return;
		}
		report_info("Deklarisana konstanta "+ constDeclSingle.getName(), constDeclSingle);
		Obj cnst = Tab.insert(Obj.Con, constDeclSingle.getName(), constDeclSingle.getConst().obj.getType());
		cnst.setAdr(constDeclSingle.getConst().obj.getAdr());
	}
	
	@Override
	public void visit(Num num) {
		num.obj = new Obj(Obj.Con, "$const", Tab.intType, num.getNumconst(), 0);
	}

	@Override
	public void visit(Char ch) {
		ch.obj = new Obj(Obj.Con, "$const", Tab.charType, (int) ch.getCharconst(), 0);
	}

	@Override
	public void visit(Bool bool) {
		bool.obj = new Obj(Obj.Con, "$const", boolType, bool.getBoolconst() ? 1 : 0, 0);
	}

	public void visit(BeforeType beforeType) {
		currentMethodType = beforeType.getType();
	}
	public void visit(BeforeVoid beforeVoid) {
		currentMethodType = null;
	}
	
	public void visit(MethodDeclName methodDeclName) {
		if (Tab.find(methodDeclName.getName()) != Tab.noObj) {
			report_error("Vec postoji metoda sa zadatim imenom "  + methodDeclName.getName(), methodDeclName);
			return;
		}
		if (currentMethodType != null && methodDeclName.getName().equals("main")) {
			report_error("Main mora biti void tipa ", methodDeclName);
			return;
		} 
		if (currentMethodType != null )
			currentMethod = Tab.insert(Obj.Meth, methodDeclName.getName(), currentMethodType.struct); 
		else 
			currentMethod = Tab.insert(Obj.Meth, methodDeclName.getName(), Tab.noType);
		Tab.openScope();
		methodDeclName.obj = currentMethod;
		report_info("Obradjuje se funkcija " + methodDeclName.getName(), methodDeclName);

	}
	
	public void visit(MethodDecl methodDecl) {
		if (currentMethod!=null) {
			Tab.chainLocalSymbols(currentMethod); 
			Tab.closeScope();
			currentMethod = null;
		}
	}
	
	
    public void visit(DesignatorIdent designator){
    	Obj obj = Tab.find(designator.getName());
    	if(obj == Tab.noObj){
    		report_error("Greska na liniji " + designator.getLine()+ " : ime "+designator.getName()+" nije deklarisano! ", null);
    	}
    	designator.obj = obj;
    }
    
    public void visit(ArrayName arrayName){
    	Obj obj = Tab.find(arrayName.getName());
    	if(obj == Tab.noObj){
    		report_error("Greska na liniji " + arrayName.getLine()+ " : ime "+arrayName.getName()+" nije deklarisano! ", null);
    	}
    	arrayName.obj = obj;
    }
    
    
    
    
    public void visit(DesignatorStatementAssign designatorStatementAssign){
    	if(designatorStatementAssign.getDesignator().obj.getKind() != Obj.Var 
    		&& designatorStatementAssign.getDesignator().obj.getKind() != Obj.Fld
    		&& designatorStatementAssign.getDesignator().obj.getKind() != Obj.Elem) {
	    		report_error("Greska na liniji " + designatorStatementAssign.getLine() + " : " + "designator nije  promenljiva, element niza, element matrice ili polje unutar objekta! ", null);
				return;
    	}
    	Struct te = designatorStatementAssign.getDesignator().obj.getType(); 
		Struct t = designatorStatementAssign.getExpr().struct;
		//report_info("des " + te, designatorStatementAssign);
		//report_info("expr " + t, designatorStatementAssign);
		if (te != null && t != null) {
			if(!te.equals(t))
				report_error("Nekompatibilni tiplovi u operaciji dodele " + designatorStatementAssign.getDesignator().obj.getName(), designatorStatementAssign);			
		}
    }
    
    public void visit(DesignatorStatementInc designatorStatementInc){
    	if(designatorStatementInc.getDesignator().obj.getKind() != Obj.Var 
        		&& designatorStatementInc.getDesignator().obj.getKind() != Obj.Fld
        		&& designatorStatementInc.getDesignator().obj.getKind() != Obj.Elem) {
    	    		report_error("Greska na liniji " + designatorStatementInc.getLine() + " : " + "designator nije  promenljiva, element niza, element matrice ili polje unutar objekta! ", null);
    				return;
        	}
    	
    	if (designatorStatementInc.getDesignator().obj.getType() != Tab.intType) {
    		report_error("Greska na liniji " + designatorStatementInc.getLine() + " : " + "designator nije ceo broj! ", null);
			return;
		}
    }
    
    public void visit(DesignatorStatementDec designatorStatementDec){
    	if(designatorStatementDec.getDesignator().obj.getKind() != Obj.Var 
        		&& designatorStatementDec.getDesignator().obj.getKind() != Obj.Fld
        		&& designatorStatementDec.getDesignator().obj.getKind() != Obj.Elem) {
    	    		report_error("Greska na liniji " + designatorStatementDec.getLine() + " : " + "designator nije  promenljiva, element niza, element matrice ili polje unutar objekta! ", null);
    				return;
        	}
    	
    	if (designatorStatementDec.getDesignator().obj.getType() != Tab.intType) {
    		report_error("Greska na liniji " + designatorStatementDec.getLine() + " : " + "designator nije ceo broj! ", null);
			return;
		}
    }
    
    public void visit(ReadStmt readStmt){
    	if(readStmt.getDesignator().obj.getKind() != Obj.Var 
        		&& readStmt.getDesignator().obj.getKind() != Obj.Fld
        		&& readStmt.getDesignator().obj.getKind() != Obj.Elem) {
    	    		report_error("Greska na liniji " + readStmt.getLine() + " : " + "designator nije  promenljiva, element niza, element matrice ili polje unutar objekta! ", null);
    				return;
        	}
    	
    	if (readStmt.getDesignator().obj.getType() != Tab.intType
    		&& readStmt.getDesignator().obj.getType() != Tab.charType
    		&& readStmt.getDesignator().obj.getType() != boolType) {
    		report_error("Greska na liniji " + readStmt.getLine() + " : " + "designator nije int, char ili bool! ", null);
			return;
		}
    }
    
    public void visit(PrintNumStmt printNumStmt){
    	if (printNumStmt.getExpr().struct != Tab.intType
    		&& printNumStmt.getExpr().struct != Tab.charType
    		&& printNumStmt.getExpr().struct != boolType) {
    		report_error("Greska na liniji " + printNumStmt.getLine() + " : " + "expr nije int, char ili bool! ", null);
			return;
		}
    }
    
    public void visit(PrintStmt printStmt){
    	if (printStmt.getExpr().struct != Tab.intType
    		&& printStmt.getExpr().struct != Tab.charType
    		&& printStmt.getExpr().struct != boolType) {
    		report_error("Greska na liniji " + printStmt.getLine() + " : " + "expr nije int, char ili bool! ", null);
			return;
		}
    }
    
    
    
    public void visit(DesignatorFactor designatorFactor) {
    	if(designatorFactor.getDesignator().obj != null) {
    		designatorFactor.struct = designatorFactor.getDesignator().obj.getType();
    	}
    	
	}

	public void visit(NumFactor numFactor) {
		numFactor.struct = Tab.intType;
	}
	
	public void visit(CharFactor charFactor) {
		charFactor.struct = Tab.charType;
	}
	
	public void visit(BoolFactor boolFactor) {
		boolFactor.struct = boolType;
	}
    
    public void visit(ExprTermMin exprTermMin) {
    	if (exprTermMin.getTerm().struct != Tab.intType) {
    		report_error("Greska na liniji " + exprTermMin.getLine() + " : " + "term nije int! ", null);
    		return;
    	}
    	
    	
    	Struct te = exprTermMin.getTerm().struct; 
		Struct t = exprTermMin.getExprList().struct;
		if (te != null && t != null) {
			if(te.equals(Tab.intType) && te.equals(t))
				exprTermMin.struct = te;
			else 
				report_error("Greska na liniji "+ exprTermMin.getLine()+": nekompatibilni tipovi u izrazu!", null);	
		}
		else exprTermMin.struct = t;
		
    	
		
    }
    
    public void visit(ExprTerm exprTerm) {
		Struct te = exprTerm.getTerm().struct; 
		Struct t = exprTerm.getExprList().struct;
		
		if (te != null && t != null) {
			if(te.equals(Tab.intType) && te.equals(t))
				exprTerm.struct = te;
			else 
				report_error("Greska na liniji "+ exprTerm.getLine()+": nekompatibilni tipovi u izrazu!", null);	
		}
		else exprTerm.struct = te;
    	
		
	}
    
    public void visit(Term term) {
    	
		Struct te = term.getTermList().struct; 
		Struct t = term.getFactor().struct;
		if (te != null && t != null) {
			if(te.equals(t))
				term.struct = t;
			else 
				report_error("Greska na liniji "+ term.getLine()+": nekompatibilni tipovi u izrazu!", null);	
		}
		else term.struct = t;
		
		
	}
    
    
    public void visit(ListExpr listExpr) {
    	if (listExpr.getExprList().struct == null) {
			listExpr.struct = listExpr.getTerm().struct;
			
		} else {
			if (listExpr.getExprList().struct == Tab.intType && listExpr.getTerm().struct == Tab.intType) {
				listExpr.struct = listExpr.getTerm().struct;
			}
			else {
				report_error("Greska na liniji " + listExpr.getLine() + " : " + "expr nije int! ", null);
	    		return;
			}
				
		}
    	
    	
    	
    }
    

    
    public void visit(ListTerm listTerm) {
    	if (listTerm.getTermList().struct == null) {
			listTerm.struct = listTerm.getFactor().struct;
			
		} else {
			if (listTerm.getTermList().struct == Tab.intType && listTerm.getFactor().struct == Tab.intType) {
				listTerm.struct = listTerm.getFactor().struct;
			}
			else {
				report_error("Greska na liniji " + listTerm.getLine() + " : " + "expr nije int! ", null);
	    		return;
			}
				
		}
    }
    
    
    public void visit(NewFactorBracket newFactorBracket) {
    	if (newFactorBracket.getExpr().struct != Tab.intType) {
    		report_error("Greska na liniji " + newFactorBracket.getLine() + " : " + "expr nije int! ", null);
    		return;
		}
    	newFactorBracket.struct = new Struct(Struct.Array, currentType.getType());
    	
    }
    
    public void visit(NewFactorDoubleBracket newFactorDoubleBracket) {
    	if (newFactorDoubleBracket.getExpr().struct != Tab.intType || newFactorDoubleBracket.getExpr1().struct != Tab.intType) {
    		report_error("Greska na liniji " + newFactorDoubleBracket.getLine() + " : " + "expr nije int! ", null);
    		return;
		}
    	Struct mat = new Struct(Struct.Array, currentType.getType());
    	newFactorDoubleBracket.struct = new Struct(Struct.Array, mat);
    }
    
    public void visit(DesignatorBracket designatorBracket) {
    	
    	if (designatorBracket.getExpr().struct != Tab.intType) {
    		report_error("Greska na liniji " + designatorBracket.getLine() + " : " + "expr nije int! ", null);
    		
		} 
    	if (designatorBracket.getArrayName().obj.getType().getKind() != Struct.Array) {
    		report_error("Greska na liniji " + designatorBracket.getLine() + " : " + "designator nije niz! ", null);
		}
		designatorBracket.obj = new Obj(Obj.Elem, "$arrayelem", designatorBracket.getArrayName().obj.getType().getElemType());
		
    }
    
    public void visit(DesignatorDoubleBracket designatorDoubleBracket) {
    	if (designatorDoubleBracket.getExpr().struct != Tab.intType || designatorDoubleBracket.getExpr1().struct != Tab.intType) {
    		report_error("Greska na liniji " + designatorDoubleBracket.getLine() + " : " + "expr nije int! ", null);
    		
		} 
    	if (designatorDoubleBracket.getArrayName().obj.getType().getElemType().getKind() != Struct.Array) {
    		report_error("Greska na liniji " + designatorDoubleBracket.getLine() + " : " + "designator nije matrica! ", null);
		}
    	designatorDoubleBracket.obj = new Obj(Obj.Elem, "$matrixelem", designatorDoubleBracket.getArrayName().obj.getType().getElemType().getElemType());
    }
    
    
	
	
    public boolean passed(){
    	return !errorDetected;
    }



	
	

	
	
}
