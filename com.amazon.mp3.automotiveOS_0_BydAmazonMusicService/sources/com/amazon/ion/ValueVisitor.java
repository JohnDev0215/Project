package com.amazon.ion;

/* JADX INFO: loaded from: classes3.dex */
public interface ValueVisitor {
    void visit(IonBlob ionBlob) throws Exception;

    void visit(IonBool ionBool) throws Exception;

    void visit(IonClob ionClob) throws Exception;

    void visit(IonDatagram ionDatagram) throws Exception;

    void visit(IonDecimal ionDecimal) throws Exception;

    void visit(IonFloat ionFloat) throws Exception;

    void visit(IonInt ionInt) throws Exception;

    void visit(IonList ionList) throws Exception;

    void visit(IonNull ionNull) throws Exception;

    void visit(IonSexp ionSexp) throws Exception;

    void visit(IonString ionString) throws Exception;

    void visit(IonStruct ionStruct) throws Exception;

    void visit(IonSymbol ionSymbol) throws Exception;

    void visit(IonTimestamp ionTimestamp) throws Exception;
}
