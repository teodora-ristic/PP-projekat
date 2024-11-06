// generated with ast extension for cup
// version 0.8
// 4/8/2024 3:38:33


package rs.ac.bg.etf.pp1.ast;

public class Num extends Const {

    private Integer numconst;

    public Num (Integer numconst) {
        this.numconst=numconst;
    }

    public Integer getNumconst() {
        return numconst;
    }

    public void setNumconst(Integer numconst) {
        this.numconst=numconst;
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
        buffer.append("Num(\n");

        buffer.append(" "+tab+numconst);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Num]");
        return buffer.toString();
    }
}
