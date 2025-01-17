// generated with ast extension for cup
// version 0.8
// 4/8/2024 3:38:33


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(Mulop Mulop);
    public void visit(VarDeclRList VarDeclRList);
    public void visit(FormParsOpt FormParsOpt);
    public void visit(VarDeclR VarDeclR);
    public void visit(Relop Relop);
    public void visit(TermList TermList);
    public void visit(Before Before);
    public void visit(StatementList StatementList);
    public void visit(Addop Addop);
    public void visit(Factor Factor);
    public void visit(FormParsR FormParsR);
    public void visit(DeclList DeclList);
    public void visit(Designator Designator);
    public void visit(FormParsRList FormParsRList);
    public void visit(ConstDeclList ConstDeclList);
    public void visit(ExprList ExprList);
    public void visit(VarDeclList VarDeclList);
    public void visit(Expr Expr);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(Const Const);
    public void visit(Decl Decl);
    public void visit(Statement Statement);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(FormPars FormPars);
    public void visit(MulopPercent MulopPercent);
    public void visit(MulopDiv MulopDiv);
    public void visit(MulopMul MulopMul);
    public void visit(AddopMinus AddopMinus);
    public void visit(AddopPlus AddopPlus);
    public void visit(RelopLt RelopLt);
    public void visit(RelopLte RelopLte);
    public void visit(RelopGt RelopGt);
    public void visit(RelopGte RelopGte);
    public void visit(RelopNotEqual RelopNotEqual);
    public void visit(RelopEqual RelopEqual);
    public void visit(Assignop Assignop);
    public void visit(Label Label);
    public void visit(ArrayName ArrayName);
    public void visit(DesignatorDoubleBracket DesignatorDoubleBracket);
    public void visit(DesignatorBracket DesignatorBracket);
    public void visit(DesignatorPeriod DesignatorPeriod);
    public void visit(DesignatorIdent DesignatorIdent);
    public void visit(ParenFactor ParenFactor);
    public void visit(NewFactorDoubleBracket NewFactorDoubleBracket);
    public void visit(NewFactorBracket NewFactorBracket);
    public void visit(BoolFactor BoolFactor);
    public void visit(CharFactor CharFactor);
    public void visit(NumFactor NumFactor);
    public void visit(DesignatorFactor DesignatorFactor);
    public void visit(EmptyTermList EmptyTermList);
    public void visit(ListTerm ListTerm);
    public void visit(Term Term);
    public void visit(EmptyExprList EmptyExprList);
    public void visit(ListExpr ListExpr);
    public void visit(ExprTerm ExprTerm);
    public void visit(ExprTermMin ExprTermMin);
    public void visit(DesignatorStatementDec DesignatorStatementDec);
    public void visit(DesignatorStatementInc DesignatorStatementInc);
    public void visit(DesignatorStatementAssign DesignatorStatementAssign);
    public void visit(ErrorStmt ErrorStmt);
    public void visit(PrintStmt PrintStmt);
    public void visit(PrintNumStmt PrintNumStmt);
    public void visit(ReadStmt ReadStmt);
    public void visit(DesignatorStmt DesignatorStmt);
    public void visit(Type Type);
    public void visit(NoBracketFormParsR NoBracketFormParsR);
    public void visit(BracketFormParsR BracketFormParsR);
    public void visit(EmptyFormParsRList EmptyFormParsRList);
    public void visit(ListFormParsR ListFormParsR);
    public void visit(NoBracketFormPars NoBracketFormPars);
    public void visit(BracketFormPars BracketFormPars);
    public void visit(EmptyStatementList EmptyStatementList);
    public void visit(ListStatement ListStatement);
    public void visit(EmptyVarDeclList EmptyVarDeclList);
    public void visit(ListVarDecl ListVarDecl);
    public void visit(NoFormPars NoFormPars);
    public void visit(YesFormPars YesFormPars);
    public void visit(MethodDeclName MethodDeclName);
    public void visit(BeforeVoid BeforeVoid);
    public void visit(BeforeType BeforeType);
    public void visit(MethodDecl MethodDecl);
    public void visit(EmptyMethodDeclList EmptyMethodDeclList);
    public void visit(ListMethodDecl ListMethodDecl);
    public void visit(DoubleBracketVarDeclR DoubleBracketVarDeclR);
    public void visit(NoBracketVarDeclR NoBracketVarDeclR);
    public void visit(BracketVarDeclR BracketVarDeclR);
    public void visit(VarDeclRSingle VarDeclRSingle);
    public void visit(ErrorSingleVarDeclR ErrorSingleVarDeclR);
    public void visit(ErrorListVarDeclR ErrorListVarDeclR);
    public void visit(SingleVarDeclR SingleVarDeclR);
    public void visit(ListVarDeclR ListVarDeclR);
    public void visit(VarDecl VarDecl);
    public void visit(ConstDeclSingle ConstDeclSingle);
    public void visit(ErrorSingleConstDecl ErrorSingleConstDecl);
    public void visit(ErrorListConstDecl ErrorListConstDecl);
    public void visit(SingleConstDecl SingleConstDecl);
    public void visit(ListConstDecl ListConstDecl);
    public void visit(Bool Bool);
    public void visit(Char Char);
    public void visit(Num Num);
    public void visit(ConstDecl ConstDecl);
    public void visit(DeclVar DeclVar);
    public void visit(DeclConst DeclConst);
    public void visit(EmptyDecList EmptyDecList);
    public void visit(ListDecl ListDecl);
    public void visit(ProgName ProgName);
    public void visit(Program Program);

}
