package com.amazon.ion.impl;

import com.amazon.ion.IonBlob;
import com.amazon.ion.IonBool;
import com.amazon.ion.IonClob;
import com.amazon.ion.IonDecimal;
import com.amazon.ion.IonFloat;
import com.amazon.ion.IonInt;
import com.amazon.ion.IonList;
import com.amazon.ion.IonSexp;
import com.amazon.ion.IonString;
import com.amazon.ion.IonStruct;
import com.amazon.ion.IonSymbol;
import com.amazon.ion.IonTimestamp;
import com.amazon.ion.IonType;
import com.amazon.ion.IonValue;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.Timestamp;
import com.amazon.ion.ValueFactory;

/* JADX INFO: loaded from: classes3.dex */
public abstract class _Private_CurriedValueFactory implements ValueFactory {
    private final ValueFactory myFactory;

    protected abstract void handle(IonValue ionValue);

    protected _Private_CurriedValueFactory(ValueFactory valueFactory) {
        this.myFactory = valueFactory;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonBlob newBlob(byte[] bArr, int i, int i2) {
        IonBlob ionBlobNewBlob = this.myFactory.newBlob(bArr, i, i2);
        handle(ionBlobNewBlob);
        return ionBlobNewBlob;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonBool newBool(boolean z) {
        IonBool ionBoolNewBool = this.myFactory.newBool(z);
        handle(ionBoolNewBool);
        return ionBoolNewBool;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonClob newClob(byte[] bArr, int i, int i2) {
        IonClob ionClobNewClob = this.myFactory.newClob(bArr, i, i2);
        handle(ionClobNewClob);
        return ionClobNewClob;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonDecimal newNullDecimal() {
        IonDecimal ionDecimalNewNullDecimal = this.myFactory.newNullDecimal();
        handle(ionDecimalNewNullDecimal);
        return ionDecimalNewNullDecimal;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonFloat newNullFloat() {
        IonFloat ionFloatNewNullFloat = this.myFactory.newNullFloat();
        handle(ionFloatNewNullFloat);
        return ionFloatNewNullFloat;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonFloat newFloat(double d) {
        IonFloat ionFloatNewFloat = this.myFactory.newFloat(d);
        handle(ionFloatNewFloat);
        return ionFloatNewFloat;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonInt newInt(int i) {
        IonInt ionIntNewInt = this.myFactory.newInt(i);
        handle(ionIntNewInt);
        return ionIntNewInt;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonInt newInt(long j) {
        IonInt ionIntNewInt = this.myFactory.newInt(j);
        handle(ionIntNewInt);
        return ionIntNewInt;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonInt newInt(Number number) {
        IonInt ionIntNewInt = this.myFactory.newInt(number);
        handle(ionIntNewInt);
        return ionIntNewInt;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonList newEmptyList() {
        IonList ionListNewEmptyList = this.myFactory.newEmptyList();
        handle(ionListNewEmptyList);
        return ionListNewEmptyList;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonValue newNull(IonType ionType) {
        IonValue ionValueNewNull = this.myFactory.newNull(ionType);
        handle(ionValueNewNull);
        return ionValueNewNull;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonSexp newEmptySexp() {
        IonSexp ionSexpNewEmptySexp = this.myFactory.newEmptySexp();
        handle(ionSexpNewEmptySexp);
        return ionSexpNewEmptySexp;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonString newString(String str) {
        IonString ionStringNewString = this.myFactory.newString(str);
        handle(ionStringNewString);
        return ionStringNewString;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonStruct newEmptyStruct() {
        IonStruct ionStructNewEmptyStruct = this.myFactory.newEmptyStruct();
        handle(ionStructNewEmptyStruct);
        return ionStructNewEmptyStruct;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonSymbol newSymbol(String str) {
        IonSymbol ionSymbolNewSymbol = this.myFactory.newSymbol(str);
        handle(ionSymbolNewSymbol);
        return ionSymbolNewSymbol;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonSymbol newSymbol(SymbolToken symbolToken) {
        IonSymbol ionSymbolNewSymbol = this.myFactory.newSymbol(symbolToken);
        handle(ionSymbolNewSymbol);
        return ionSymbolNewSymbol;
    }

    @Override // com.amazon.ion.ValueFactory
    public IonTimestamp newTimestamp(Timestamp timestamp) {
        IonTimestamp ionTimestampNewTimestamp = this.myFactory.newTimestamp(timestamp);
        handle(ionTimestampNewTimestamp);
        return ionTimestampNewTimestamp;
    }
}
