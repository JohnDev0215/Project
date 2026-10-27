package com.amazon.ion.impl.lite;

import com.amazon.ion.IonSexp;
import com.amazon.ion.IonType;
import com.amazon.ion.ValueVisitor;
import com.amazon.ion.impl._Private_IonValue;

/* JADX INFO: loaded from: classes3.dex */
final class IonSexpLite extends IonSequenceLite implements IonSexp {
    private static final int HASH_SIGNATURE = IonType.SEXP.toString().hashCode();

    IonSexpLite(ContainerlessContext containerlessContext, boolean z) {
        super(containerlessContext, z);
    }

    IonSexpLite(IonSexpLite ionSexpLite, IonContext ionContext) {
        super(ionSexpLite, ionContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonSexpLite clone(IonContext ionContext) {
        return new IonSexpLite(this, ionContext);
    }

    @Override // com.amazon.ion.impl.lite.IonSequenceLite, com.amazon.ion.impl.lite.IonContainerLite, com.amazon.ion.impl.lite.IonValueLite
    /* JADX INFO: renamed from: clone */
    public IonSexpLite mo103clone() {
        return clone((IonContext) ContainerlessContext.wrap(getSystem()));
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public int hashCode(_Private_IonValue.SymbolTableProvider symbolTableProvider) {
        return sequenceHashCode(HASH_SIGNATURE, symbolTableProvider);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public IonType getType() {
        return IonType.SEXP;
    }

    @Override // com.amazon.ion.IonValue
    public void accept(ValueVisitor valueVisitor) throws Exception {
        valueVisitor.visit(this);
    }
}
