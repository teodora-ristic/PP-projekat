// generated with ast extension for cup
// version 0.8
// 4/8/2024 3:38:33


package rs.ac.bg.etf.pp1.ast;

public class DoubleBracketVarDeclR extends VarDeclR {

    public DoubleBracketVarDeclR () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DoubleBracketVarDeclR(\n");

        buffer.append(tab);
        buffer.append(") [DoubleBracketVarDeclR]");
        return buffer.toString();
    }
}
