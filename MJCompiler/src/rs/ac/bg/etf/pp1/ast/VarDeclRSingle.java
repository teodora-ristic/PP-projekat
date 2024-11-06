// generated with ast extension for cup
// version 0.8
// 4/8/2024 3:38:33


package rs.ac.bg.etf.pp1.ast;

public class VarDeclRSingle implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String name;
    private VarDeclR VarDeclR;

    public VarDeclRSingle (String name, VarDeclR VarDeclR) {
        this.name=name;
        this.VarDeclR=VarDeclR;
        if(VarDeclR!=null) VarDeclR.setParent(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public VarDeclR getVarDeclR() {
        return VarDeclR;
    }

    public void setVarDeclR(VarDeclR VarDeclR) {
        this.VarDeclR=VarDeclR;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclR!=null) VarDeclR.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclR!=null) VarDeclR.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclR!=null) VarDeclR.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclRSingle(\n");

        buffer.append(" "+tab+name);
        buffer.append("\n");

        if(VarDeclR!=null)
            buffer.append(VarDeclR.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclRSingle]");
        return buffer.toString();
    }
}
