package com.amazon.ion.util;

import com.amazon.ion.IonBlob;
import com.amazon.ion.IonBool;
import com.amazon.ion.IonClob;
import com.amazon.ion.IonDatagram;
import com.amazon.ion.IonDecimal;
import com.amazon.ion.IonFloat;
import com.amazon.ion.IonInt;
import com.amazon.ion.IonList;
import com.amazon.ion.IonNull;
import com.amazon.ion.IonSexp;
import com.amazon.ion.IonString;
import com.amazon.ion.IonStruct;
import com.amazon.ion.IonSymbol;
import com.amazon.ion.IonTimestamp;
import com.amazon.ion.IonValue;
import com.amazon.ion.ValueVisitor;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AbstractValueVisitor implements ValueVisitor {
    protected void defaultVisit(IonValue ionValue) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.ion.ValueVisitor
    public void visit(IonBlob ionBlob) throws Exception {
        defaultVisit(ionBlob);
    }

    @Override // com.amazon.ion.ValueVisitor
    public void visit(IonBool ionBool) throws Exception {
        defaultVisit(ionBool);
    }

    @Override // com.amazon.ion.ValueVisitor
    public void visit(IonClob ionClob) throws Exception {
        defaultVisit(ionClob);
    }

    @Override // com.amazon.ion.ValueVisitor
    public void visit(IonDatagram ionDatagram) throws Exception {
        defaultVisit(ionDatagram);
    }

    @Override // com.amazon.ion.ValueVisitor
    public void visit(IonDecimal ionDecimal) throws Exception {
        defaultVisit(ionDecimal);
    }

    @Override // com.amazon.ion.ValueVisitor
    public void visit(IonFloat ionFloat) throws Exception {
        defaultVisit(ionFloat);
    }

    @Override // com.amazon.ion.ValueVisitor
    public void visit(IonInt ionInt) throws Exception {
        defaultVisit(ionInt);
    }

    @Override // com.amazon.ion.ValueVisitor
    public void visit(IonList ionList) throws Exception {
        defaultVisit(ionList);
    }

    @Override // com.amazon.ion.ValueVisitor
    public void visit(IonNull ionNull) throws Exception {
        defaultVisit(ionNull);
    }

    @Override // com.amazon.ion.ValueVisitor
    public void visit(IonSexp ionSexp) throws Exception {
        defaultVisit(ionSexp);
    }

    @Override // com.amazon.ion.ValueVisitor
    public void visit(IonString ionString) throws Exception {
        defaultVisit(ionString);
    }

    @Override // com.amazon.ion.ValueVisitor
    public void visit(IonStruct ionStruct) throws Exception {
        defaultVisit(ionStruct);
    }

    @Override // com.amazon.ion.ValueVisitor
    public void visit(IonSymbol ionSymbol) throws Exception {
        defaultVisit(ionSymbol);
    }

    @Override // com.amazon.ion.ValueVisitor
    public void visit(IonTimestamp ionTimestamp) throws Exception {
        defaultVisit(ionTimestamp);
    }
}
