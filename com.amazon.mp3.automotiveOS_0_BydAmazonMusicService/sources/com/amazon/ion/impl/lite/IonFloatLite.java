package com.amazon.ion.impl.lite;

import com.amazon.ion.Decimal;
import com.amazon.ion.IonFloat;
import com.amazon.ion.IonType;
import com.amazon.ion.IonWriter;
import com.amazon.ion.NullValueException;
import com.amazon.ion.ValueVisitor;
import com.amazon.ion.impl._Private_IonValue;
import java.io.IOException;
import java.math.BigDecimal;

/* JADX INFO: loaded from: classes3.dex */
final class IonFloatLite extends IonValueLite implements IonFloat {
    private static final int HASH_SIGNATURE = IonType.FLOAT.toString().hashCode();
    private Double _float_value;

    IonFloatLite(ContainerlessContext containerlessContext, boolean z) {
        super(containerlessContext, z);
    }

    IonFloatLite(IonFloatLite ionFloatLite, IonContext ionContext) {
        super(ionFloatLite, ionContext);
        this._float_value = ionFloatLite._float_value;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonFloatLite clone(IonContext ionContext) {
        return new IonFloatLite(this, ionContext);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    /* JADX INFO: renamed from: clone */
    public IonFloatLite mo103clone() {
        return clone((IonContext) ContainerlessContext.wrap(getSystem()));
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    int hashCode(_Private_IonValue.SymbolTableProvider symbolTableProvider) {
        int i = HASH_SIGNATURE;
        if (!isNullValue()) {
            long jDoubleToLongBits = Double.doubleToLongBits(doubleValue());
            i ^= (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
        }
        return hashTypeAnnotations(i, symbolTableProvider);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public IonType getType() {
        return IonType.FLOAT;
    }

    @Override // com.amazon.ion.IonFloat
    public double doubleValue() throws NullValueException {
        validateThisNotNull();
        return this._float_value.doubleValue();
    }

    @Override // com.amazon.ion.IonFloat
    public BigDecimal bigDecimalValue() throws NullValueException {
        if (isNullValue()) {
            return null;
        }
        return Decimal.valueOf(this._float_value.doubleValue());
    }

    @Override // com.amazon.ion.IonFloat
    public void setValue(float f) {
        setValue(new Double(f));
    }

    @Override // com.amazon.ion.IonFloat
    public void setValue(double d) {
        setValue(new Double(d));
    }

    public void setValue(Double d) {
        checkForLock();
        this._float_value = d;
        _isNullValue(d == null);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    final void writeBodyTo(IonWriter ionWriter, _Private_IonValue.SymbolTableProvider symbolTableProvider) throws IOException {
        if (isNullValue()) {
            ionWriter.writeNull(IonType.FLOAT);
        } else {
            ionWriter.writeFloat(this._float_value.doubleValue());
        }
    }

    @Override // com.amazon.ion.IonValue
    public void accept(ValueVisitor valueVisitor) throws Exception {
        valueVisitor.visit(this);
    }
}
