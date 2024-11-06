// generated with ast extension for cup
// version 0.8
// 4/8/2024 3:38:33


package rs.ac.bg.etf.pp1.ast;

public class Bool extends Const {

    private Boolean boolconst;

    public Bool (Boolean boolconst) {
        this.boolconst=boolconst;
    }

    public Boolean getBoolconst() {
        return boolconst;
    }

    public void setBoolconst(Boolean boolconst) {
        this.boolconst=boolconst;
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
        buffer.append("Bool(\n");

        buffer.append(" "+tab+boolconst);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Bool]");
        return buffer.toString();
    }
}
