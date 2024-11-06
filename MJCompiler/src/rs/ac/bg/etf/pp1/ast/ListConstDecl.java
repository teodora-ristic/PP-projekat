// generated with ast extension for cup
// version 0.8
// 4/8/2024 3:38:33


package rs.ac.bg.etf.pp1.ast;

public class ListConstDecl extends ConstDeclList {

    private ConstDeclSingle ConstDeclSingle;
    private ConstDeclList ConstDeclList;

    public ListConstDecl (ConstDeclSingle ConstDeclSingle, ConstDeclList ConstDeclList) {
        this.ConstDeclSingle=ConstDeclSingle;
        if(ConstDeclSingle!=null) ConstDeclSingle.setParent(this);
        this.ConstDeclList=ConstDeclList;
        if(ConstDeclList!=null) ConstDeclList.setParent(this);
    }

    public ConstDeclSingle getConstDeclSingle() {
        return ConstDeclSingle;
    }

    public void setConstDeclSingle(ConstDeclSingle ConstDeclSingle) {
        this.ConstDeclSingle=ConstDeclSingle;
    }

    public ConstDeclList getConstDeclList() {
        return ConstDeclList;
    }

    public void setConstDeclList(ConstDeclList ConstDeclList) {
        this.ConstDeclList=ConstDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstDeclSingle!=null) ConstDeclSingle.accept(visitor);
        if(ConstDeclList!=null) ConstDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDeclSingle!=null) ConstDeclSingle.traverseTopDown(visitor);
        if(ConstDeclList!=null) ConstDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDeclSingle!=null) ConstDeclSingle.traverseBottomUp(visitor);
        if(ConstDeclList!=null) ConstDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ListConstDecl(\n");

        if(ConstDeclSingle!=null)
            buffer.append(ConstDeclSingle.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDeclList!=null)
            buffer.append(ConstDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ListConstDecl]");
        return buffer.toString();
    }
}
