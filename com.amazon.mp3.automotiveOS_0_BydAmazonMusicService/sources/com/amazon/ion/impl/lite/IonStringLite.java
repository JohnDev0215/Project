package com.amazon.ion.impl.lite;

import com.amazon.ion.IonString;
import com.amazon.ion.IonType;
import com.amazon.ion.IonWriter;
import com.amazon.ion.ValueVisitor;
import com.amazon.ion.impl._Private_IonValue;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
final class IonStringLite extends IonTextLite implements IonString {
    private static final int HASH_SIGNATURE = IonType.STRING.toString().hashCode();

    IonStringLite(ContainerlessContext containerlessContext, boolean z) {
        super(containerlessContext, z);
    }

    IonStringLite(IonStringLite ionStringLite, IonContext ionContext) {
        super(ionStringLite, ionContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonStringLite clone(IonContext ionContext) {
        return new IonStringLite(this, ionContext);
    }

    @Override // com.amazon.ion.impl.lite.IonTextLite, com.amazon.ion.impl.lite.IonValueLite
    /* JADX INFO: renamed from: clone */
    public IonStringLite mo103clone() {
        return clone((IonContext) ContainerlessContext.wrap(getSystem()));
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    int hashCode(_Private_IonValue.SymbolTableProvider symbolTableProvider) {
        int iHashCode = HASH_SIGNATURE;
        if (!isNullValue()) {
            iHashCode ^= stringValue().hashCode();
        }
        return hashTypeAnnotations(iHashCode, symbolTableProvider);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public IonType getType() {
        return IonType.STRING;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    final void writeBodyTo(IonWriter ionWriter, _Private_IonValue.SymbolTableProvider symbolTableProvider) throws IOException {
        ionWriter.writeString(_get_value());
    }

    @Override // com.amazon.ion.IonValue
    public void accept(ValueVisitor valueVisitor) throws Exception {
        valueVisitor.visit(this);
    }
}
