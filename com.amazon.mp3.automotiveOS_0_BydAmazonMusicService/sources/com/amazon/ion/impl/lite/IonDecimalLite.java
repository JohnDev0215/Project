package com.amazon.ion.impl.lite;

import com.amazon.ion.Decimal;
import com.amazon.ion.IonDecimal;
import com.amazon.ion.IonType;
import com.amazon.ion.IonWriter;
import com.amazon.ion.NullValueException;
import com.amazon.ion.ValueVisitor;
import com.amazon.ion.impl._Private_IonValue;
import java.io.IOException;
import java.math.BigDecimal;

/* JADX INFO: loaded from: classes3.dex */
final class IonDecimalLite extends IonValueLite implements IonDecimal {
    private static final int HASH_SIGNATURE = IonType.DECIMAL.toString().hashCode();
    private static final int NEGATIVE_ZERO_HASH_SIGNATURE = -1819393101;
    private BigDecimal _decimal_value;

    IonDecimalLite(ContainerlessContext containerlessContext, boolean z) {
        super(containerlessContext, z);
    }

    IonDecimalLite(IonDecimalLite ionDecimalLite, IonContext ionContext) {
        super(ionDecimalLite, ionContext);
        this._decimal_value = ionDecimalLite._decimal_value;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonDecimalLite clone(IonContext ionContext) {
        return new IonDecimalLite(this, ionContext);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    /* JADX INFO: renamed from: clone */
    public IonDecimalLite mo103clone() {
        return clone((IonContext) ContainerlessContext.wrap(getSystem()));
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    int hashCode(_Private_IonValue.SymbolTableProvider symbolTableProvider) {
        int iHashCode = HASH_SIGNATURE;
        if (!isNullValue()) {
            Decimal decimalDecimalValue = decimalValue();
            iHashCode ^= decimalDecimalValue.hashCode();
            if (decimalDecimalValue.isNegativeZero()) {
                iHashCode ^= NEGATIVE_ZERO_HASH_SIGNATURE;
            }
        }
        return hashTypeAnnotations(iHashCode, symbolTableProvider);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public IonType getType() {
        return IonType.DECIMAL;
    }

    @Override // com.amazon.ion.IonDecimal
    public double doubleValue() throws NullValueException {
        if (_isNullValue()) {
            throw new NullValueException();
        }
        return this._decimal_value.doubleValue();
    }

    @Override // com.amazon.ion.IonDecimal
    public BigDecimal bigDecimalValue() throws NullValueException {
        return Decimal.bigDecimalValue(this._decimal_value);
    }

    @Override // com.amazon.ion.IonDecimal
    public Decimal decimalValue() throws NullValueException {
        return Decimal.valueOf(this._decimal_value);
    }

    @Override // com.amazon.ion.IonDecimal
    public void setValue(float f) {
        setValue(Decimal.valueOf(f));
    }

    @Override // com.amazon.ion.IonDecimal
    public void setValue(double d) {
        setValue(Decimal.valueOf(d));
    }

    @Override // com.amazon.ion.IonDecimal
    public void setValue(BigDecimal bigDecimal) {
        checkForLock();
        this._decimal_value = bigDecimal;
        _isNullValue(bigDecimal == null);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    final void writeBodyTo(IonWriter ionWriter, _Private_IonValue.SymbolTableProvider symbolTableProvider) throws IOException {
        ionWriter.writeDecimal(this._decimal_value);
    }

    @Override // com.amazon.ion.IonValue
    public void accept(ValueVisitor valueVisitor) throws Exception {
        valueVisitor.visit(this);
    }
}
