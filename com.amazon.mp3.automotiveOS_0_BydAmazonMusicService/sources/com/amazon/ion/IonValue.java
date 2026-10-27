package com.amazon.ion;

/* JADX INFO: loaded from: classes3.dex */
public interface IonValue extends Cloneable {
    void accept(ValueVisitor valueVisitor) throws Exception;

    void addTypeAnnotation(String str);

    IonValue clone() throws UnknownSymbolException;

    IonContainer getContainer();

    @Deprecated
    int getFieldId();

    String getFieldName();

    SymbolToken getFieldNameSymbol();

    SymbolTable getSymbolTable();

    IonSystem getSystem();

    IonType getType();

    SymbolToken[] getTypeAnnotationSymbols();

    boolean hasTypeAnnotation(String str);

    int hashCode();

    boolean isNullValue();

    boolean isReadOnly();

    void setTypeAnnotationSymbols(SymbolToken... symbolTokenArr);

    void writeTo(IonWriter ionWriter);
}
