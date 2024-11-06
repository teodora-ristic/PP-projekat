// generated with ast extension for cup
// version 0.8
// 4/8/2024 3:38:33


package rs.ac.bg.etf.pp1.ast;

public class ErrorListVarDeclR extends VarDeclRList {

    private VarDeclRList VarDeclRList;

    public ErrorListVarDeclR (VarDeclRList VarDeclRList) {
        this.VarDeclRList=VarDeclRList;
        if(VarDeclRList!=null) VarDeclRList.setParent(this);
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
        if(VarDeclRList!=null) VarDeclRList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclRList!=null) VarDeclRList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclRList!=null) VarDeclRList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ErrorListVarDeclR(\n");

        if(VarDeclRList!=null)
            buffer.append(VarDeclRList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ErrorListVarDeclR]");
        return buffer.toString();
    }
}
