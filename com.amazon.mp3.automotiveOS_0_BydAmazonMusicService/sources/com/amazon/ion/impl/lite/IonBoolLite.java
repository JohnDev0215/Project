package com.amazon.ion.impl.lite;

import com.amazon.ion.IonBool;
import com.amazon.ion.IonType;
import com.amazon.ion.IonWriter;
import com.amazon.ion.NullValueException;
import com.amazon.ion.ValueVisitor;
import com.amazon.ion.impl._Private_IonValue;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
final class IonBoolLite extends IonValueLite implements IonBool {
    protected static final int FALSE_HASH;
    private static final int HASH_SIGNATURE;
    protected static final int TRUE_HASH;

    static {
        int iHashCode = IonType.BOOL.toString().hashCode();
        HASH_SIGNATURE = iHashCode;
        TRUE_HASH = (Boolean.TRUE.hashCode() * 16777619) ^ iHashCode;
        FALSE_HASH = iHashCode ^ (Boolean.FALSE.hashCode() * 16777619);
    }

    IonBoolLite(ContainerlessContext containerlessContext, boolean z) {
        super(containerlessContext, z);
    }

    IonBoolLite(IonBoolLite ionBoolLite, IonContext ionContext) {
        super(ionBoolLite, ionContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonBoolLite clone(IonContext ionContext) {
        return new IonBoolLite(this, ionContext);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    /* JADX INFO: renamed from: clone */
    public IonBoolLite mo103clone() {
        return clone((IonContext) ContainerlessContext.wrap(getSystem()));
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public IonType getType() {
        return IonType.BOOL;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    int hashCode(_Private_IonValue.SymbolTableProvider symbolTableProvider) {
        int i = HASH_SIGNATURE;
        if (!isNullValue()) {
            i = booleanValue() ? TRUE_HASH : FALSE_HASH;
        }
        return hashTypeAnnotations(i, symbolTableProvider);
    }

    @Override // com.amazon.ion.IonBool
    public boolean booleanValue() throws NullValueException {
        validateThisNotNull();
        return _isBoolTrue();
    }

    public void setValue(boolean z) {
        setValue(Boolean.valueOf(z));
    }

    public void setValue(Boolean bool) {
        checkForLock();
        if (bool == null) {
            _isBoolTrue(false);
            _isNullValue(true);
        } else {
            _isBoolTrue(bool.booleanValue());
            _isNullValue(false);
        }
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    final void writeBodyTo(IonWriter ionWriter, _Private_IonValue.SymbolTableProvider symbolTableProvider) throws IOException {
        if (isNullValue()) {
            ionWriter.writeNull(IonType.BOOL);
        } else {
            ionWriter.writeBool(_isBoolTrue());
        }
    }

    @Override // com.amazon.ion.IonValue
    public void accept(ValueVisitor valueVisitor) throws Exception {
        valueVisitor.visit(this);
    }
}
