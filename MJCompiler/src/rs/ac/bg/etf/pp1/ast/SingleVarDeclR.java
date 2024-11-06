// generated with ast extension for cup
// version 0.8
// 4/8/2024 3:38:33


package rs.ac.bg.etf.pp1.ast;

public class SingleVarDeclR extends VarDeclRList {

    private VarDeclRSingle VarDeclRSingle;

    public SingleVarDeclR (VarDeclRSingle VarDeclRSingle) {
        this.VarDeclRSingle=VarDeclRSingle;
        if(VarDeclRSingle!=null) VarDeclRSingle.setParent(this);
    }

    public VarDeclRSingle getVarDeclRSingle() {
        return VarDeclRSingle;
    }

    public void setVarDeclRSingle(VarDeclRSingle VarDeclRSingle) {
        this.VarDeclRSingle=VarDeclRSingle;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclRSingle!=null) VarDeclRSingle.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclRSingle!=null) VarDeclRSingle.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclRSingle!=null) VarDeclRSingle.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleVarDeclR(\n");

        if(VarDeclRSingle!=null)
            buffer.append(VarDeclRSingle.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleVarDeclR]");
        return buffer.toString();
    }
}
