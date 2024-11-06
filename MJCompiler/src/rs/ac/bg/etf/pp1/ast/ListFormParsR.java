// generated with ast extension for cup
// version 0.8
// 4/8/2024 3:38:33


package rs.ac.bg.etf.pp1.ast;

public class ListFormParsR extends FormParsRList {

    private FormParsRList FormParsRList;
    private FormParsR FormParsR;

    public ListFormParsR (FormParsRList FormParsRList, FormParsR FormParsR) {
        this.FormParsRList=FormParsRList;
        if(FormParsRList!=null) FormParsRList.setParent(this);
        this.FormParsR=FormParsR;
        if(FormParsR!=null) FormParsR.setParent(this);
    }

    public FormParsRList getFormParsRList() {
        return FormParsRList;
    }

    public void setFormParsRList(FormParsRList FormParsRList) {
        this.FormParsRList=FormParsRList;
    }

    public FormParsR getFormParsR() {
        return FormParsR;
    }

    public void setFormParsR(FormParsR FormParsR) {
        this.FormParsR=FormParsR;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FormParsRList!=null) FormParsRList.accept(visitor);
        if(FormParsR!=null) FormParsR.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormParsRList!=null) FormParsRList.traverseTopDown(visitor);
        if(FormParsR!=null) FormParsR.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormParsRList!=null) FormParsRList.traverseBottomUp(visitor);
        if(FormParsR!=null) FormParsR.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ListFormParsR(\n");

        if(FormParsRList!=null)
            buffer.append(FormParsRList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormParsR!=null)
            buffer.append(FormParsR.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ListFormParsR]");
        return buffer.toString();
    }
}
