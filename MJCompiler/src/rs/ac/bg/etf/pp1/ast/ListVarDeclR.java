// generated with ast extension for cup
// version 0.8
// 4/8/2024 3:38:33


package rs.ac.bg.etf.pp1.ast;

public class ListVarDeclR extends VarDeclRList {

    private VarDeclRSingle VarDeclRSingle;
    private VarDeclRList VarDeclRList;

    public ListVarDeclR (VarDeclRSingle VarDeclRSingle, VarDeclRList VarDeclRList) {
        this.VarDeclRSingle=VarDeclRSingle;
        if(VarDeclRSingle!=null) VarDeclRSingle.setParent(this);
        this.VarDeclRList=VarDeclRList;
        if(VarDeclRList!=null) VarDeclRList.setParent(this);
    }

    public VarDeclRSingle getVarDeclRSingle() {
        return VarDeclRSingle;
    }

    public void setVarDeclRSingle(VarDeclRSingle VarDeclRSingle) {
        this.VarDeclRSingle=VarDeclRSingle;
    }

    public VarDeclRList getVarDeclRList() {
        return VarDeclRList;
    }

    public void setVarDeclRList(VarDeclRList VarDeclRList) {
        this.VarDeclRList=VarDeclRList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclRSingle!=null) VarDeclRSingle.accept(visitor);
        if(VarDeclRList!=null) VarDeclRList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclRSingle!=null) VarDeclRSingle.traverseTopDown(visitor);
        if(VarDeclRList!=null) VarDeclRList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclRSingle!=null) VarDeclRSingle.traverseBottomUp(visitor);
        if(VarDeclRList!=null) VarDeclRList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ListVarDeclR(\n");

        if(VarDeclRSingle!=null)
            buffer.append(VarDeclRSingle.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclRList!=null)
            buffer.append(VarDeclRList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ListVarDeclR]");
        return buffer.toString();
    }
}
