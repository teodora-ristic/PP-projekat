// generated with ast extension for cup
// version 0.8
// 4/8/2024 3:38:33


package rs.ac.bg.etf.pp1.ast;

public class BracketFormPars extends FormPars {

    private Type Type;
    private String I2;
    private FormParsRList FormParsRList;

    public BracketFormPars (Type Type, String I2, FormParsRList FormParsRList) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.I2=I2;
        this.FormParsRList=FormParsRList;
        if(FormParsRList!=null) FormParsRList.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getI2() {
        return I2;
    }

    public void setI2(String I2) {
        this.I2=I2;
    }

    public FormParsRList getFormParsRList() {
        return FormParsRList;
    }

    public void setFormParsRList(FormParsRList FormParsRList) {
        this.FormParsRList=FormParsRList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(FormParsRList!=null) FormParsRList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(FormParsRList!=null) FormParsRList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(FormParsRList!=null) FormParsRList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("BracketFormPars(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+I2);
        buffer.append("\n");

        if(FormParsRList!=null)
            buffer.append(FormParsRList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BracketFormPars]");
        return buffer.toString();
    }
}
