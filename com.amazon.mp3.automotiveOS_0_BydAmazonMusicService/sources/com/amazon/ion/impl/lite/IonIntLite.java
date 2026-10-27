package com.amazon.ion.impl.lite;

import com.amazon.ion.IntegerSize;
import com.amazon.ion.IonInt;
import com.amazon.ion.IonType;
import com.amazon.ion.IonWriter;
import com.amazon.ion.NullValueException;
import com.amazon.ion.ValueVisitor;
import com.amazon.ion.impl._Private_IonValue;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes3.dex */
final class IonIntLite extends IonValueLite implements IonInt {
    private BigInteger _big_int_value;
    private long _long_value;
    private static final BigInteger LONG_MIN_VALUE = BigInteger.valueOf(Long.MIN_VALUE);
    private static final BigInteger LONG_MAX_VALUE = BigInteger.valueOf(Long.MAX_VALUE);
    private static final int HASH_SIGNATURE = IonType.INT.toString().hashCode();
    private static final IntegerSize[] SIZES = IntegerSize.values();

    IonIntLite(ContainerlessContext containerlessContext, boolean z) {
        super(containerlessContext, z);
    }

    IonIntLite(IonIntLite ionIntLite, IonContext ionContext) {
        super(ionIntLite, ionContext);
        this._long_value = ionIntLite._long_value;
        this._big_int_value = ionIntLite._big_int_value;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonIntLite clone(IonContext ionContext) {
        return new IonIntLite(this, ionContext);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    /* JADX INFO: renamed from: clone */
    public IonIntLite mo103clone() {
        return clone((IonContext) ContainerlessContext.wrap(getSystem()));
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    int hashCode(_Private_IonValue.SymbolTableProvider symbolTableProvider) {
        int iHashCode = HASH_SIGNATURE;
        if (!isNullValue()) {
            BigInteger bigInteger = this._big_int_value;
            if (bigInteger == null) {
                long jLongValue = longValue();
                iHashCode ^= (int) jLongValue;
                int i = (int) (jLongValue >>> 32);
                if (i != 0 && i != -1) {
                    iHashCode ^= i;
                }
            } else {
                iHashCode = bigInteger.hashCode();
            }
        }
        return hashTypeAnnotations(iHashCode, symbolTableProvider);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public IonType getType() {
        return IonType.INT;
    }

    @Override // com.amazon.ion.IonInt
    public int intValue() throws NullValueException {
        validateThisNotNull();
        BigInteger bigInteger = this._big_int_value;
        if (bigInteger == null) {
            return (int) this._long_value;
        }
        return bigInteger.intValue();
    }

    @Override // com.amazon.ion.IonInt
    public long longValue() throws NullValueException {
        validateThisNotNull();
        BigInteger bigInteger = this._big_int_value;
        if (bigInteger == null) {
            return this._long_value;
        }
        return bigInteger.longValue();
    }

    @Override // com.amazon.ion.IonInt
    public BigInteger bigIntegerValue() throws NullValueException {
        if (isNullValue()) {
            return null;
        }
        BigInteger bigInteger = this._big_int_value;
        return bigInteger == null ? BigInteger.valueOf(this._long_value) : bigInteger;
    }

    @Override // com.amazon.ion.IonInt
    public void setValue(int i) {
        setValue(i);
    }

    public void setValue(long j) {
        checkForLock();
        doSetValue(j, false);
    }

    public void setValue(Number number) {
        checkForLock();
        if (number == null) {
            doSetValue(0L, true);
            return;
        }
        if (number instanceof BigInteger) {
            doSetValue((BigInteger) number);
        } else if (number instanceof BigDecimal) {
            doSetValue(((BigDecimal) number).toBigInteger());
        } else {
            doSetValue(number.longValue(), false);
        }
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    final void writeBodyTo(IonWriter ionWriter, _Private_IonValue.SymbolTableProvider symbolTableProvider) throws IOException {
        if (isNullValue()) {
            ionWriter.writeNull(IonType.INT);
            return;
        }
        BigInteger bigInteger = this._big_int_value;
        if (bigInteger != null) {
            ionWriter.writeInt(bigInteger);
        } else {
            ionWriter.writeInt(this._long_value);
        }
    }

    private void doSetValue(long j, boolean z) {
        this._long_value = j;
        this._big_int_value = null;
        _isNullValue(z);
        if (z) {
            return;
        }
        if (j < -2147483648L || j > 2147483647L) {
            setSize(IntegerSize.LONG);
        } else {
            setSize(IntegerSize.INT);
        }
    }

    private void doSetValue(BigInteger bigInteger) {
        if (bigInteger.compareTo(LONG_MIN_VALUE) < 0 || bigInteger.compareTo(LONG_MAX_VALUE) > 0) {
            setSize(IntegerSize.BIG_INTEGER);
            this._long_value = 0L;
            this._big_int_value = bigInteger;
            _isNullValue(false);
            return;
        }
        doSetValue(bigInteger.longValue(), false);
    }

    private void setSize(IntegerSize integerSize) {
        _setMetadata(integerSize.ordinal(), 24, 3);
    }

    @Override // com.amazon.ion.IonValue
    public void accept(ValueVisitor valueVisitor) throws Exception {
        valueVisitor.visit(this);
    }

    @Override // com.amazon.ion.IonInt
    public IntegerSize getIntegerSize() {
        if (isNullValue()) {
            return null;
        }
        return SIZES[_getMetadata(24, 3)];
    }
}
