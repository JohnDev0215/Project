package com.amazon.ion;

/* JADX INFO: loaded from: classes3.dex */
public interface IonStruct extends IonContainer {
    ValueFactory add(String str);

    void add(SymbolToken symbolToken, IonValue ionValue) throws ContainedValueException;

    void add(String str, IonValue ionValue) throws ContainedValueException;

    IonStruct cloneAndRemove(String... strArr) throws UnknownSymbolException;

    boolean containsKey(Object obj);

    IonValue get(String str);

    @Override // com.amazon.ion.IonContainer, com.amazon.ion.IonValue
    @Deprecated
    /* synthetic */ int getFieldId();

    void put(String str, IonValue ionValue) throws ContainedValueException;

    @Override // com.amazon.ion.IonContainer
    int size() throws NullValueException;
}
