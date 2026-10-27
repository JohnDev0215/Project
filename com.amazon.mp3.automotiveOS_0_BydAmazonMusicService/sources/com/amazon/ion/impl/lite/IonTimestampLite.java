package com.amazon.ion.impl.lite;

import com.amazon.ion.IonTimestamp;
import com.amazon.ion.IonType;
import com.amazon.ion.IonWriter;
import com.amazon.ion.NullValueException;
import com.amazon.ion.Timestamp;
import com.amazon.ion.ValueVisitor;
import com.amazon.ion.impl._Private_IonValue;
import java.io.IOException;
import java.math.BigDecimal;

/* JADX INFO: loaded from: classes3.dex */
final class IonTimestampLite extends IonValueLite implements IonTimestamp {
    private Timestamp _timestamp_value;
    public static final Integer UTC_OFFSET = Timestamp.UTC_OFFSET;
    private static final int HASH_SIGNATURE = IonType.TIMESTAMP.toString().hashCode();

    IonTimestampLite(ContainerlessContext containerlessContext, boolean z) {
        super(containerlessContext, z);
    }

    IonTimestampLite(IonTimestampLite ionTimestampLite, IonContext ionContext) {
        super(ionTimestampLite, ionContext);
        this._timestamp_value = ionTimestampLite._timestamp_value;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonTimestampLite clone(IonContext ionContext) {
        return new IonTimestampLite(this, ionContext);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    /* JADX INFO: renamed from: clone */
    public IonTimestampLite mo103clone() {
        return clone((IonContext) ContainerlessContext.wrap(getSystem()));
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    int hashCode(_Private_IonValue.SymbolTableProvider symbolTableProvider) {
        int iHashCode = HASH_SIGNATURE;
        if (!isNullValue()) {
            iHashCode ^= timestampValue().hashCode();
        }
        return hashTypeAnnotations(iHashCode, symbolTableProvider);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public IonType getType() {
        return IonType.TIMESTAMP;
    }

    @Override // com.amazon.ion.IonTimestamp
    public Timestamp timestampValue() {
        if (isNullValue()) {
            return null;
        }
        return this._timestamp_value;
    }

    public void setValue(Timestamp timestamp) {
        checkForLock();
        this._timestamp_value = timestamp;
        _isNullValue(timestamp == null);
    }

    public void setValue(BigDecimal bigDecimal, Integer num) {
        setValue(new Timestamp(bigDecimal, num));
    }

    @Override // com.amazon.ion.IonTimestamp
    public long getMillis() {
        if (_isNullValue()) {
            throw new NullValueException();
        }
        return this._timestamp_value.getMillis();
    }

    @Override // com.amazon.ion.IonTimestamp
    public void setLocalOffset(int i) throws NullValueException {
        setLocalOffset(new Integer(i));
    }

    public void setLocalOffset(Integer num) throws NullValueException {
        validateThisNotNull();
        setValue(this._timestamp_value.getDecimalMillis(), num);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    final void writeBodyTo(IonWriter ionWriter, _Private_IonValue.SymbolTableProvider symbolTableProvider) throws IOException {
        ionWriter.writeTimestamp(this._timestamp_value);
    }

    @Override // com.amazon.ion.IonValue
    public void accept(ValueVisitor valueVisitor) throws Exception {
        valueVisitor.visit(this);
    }
}
