package com.amazon.ion.impl.lite;

import com.amazon.ion.IonList;
import com.amazon.ion.IonType;
import com.amazon.ion.ValueVisitor;
import com.amazon.ion.impl._Private_IonValue;

/* JADX INFO: loaded from: classes3.dex */
final class IonListLite extends IonSequenceLite implements IonList {
    private static final int HASH_SIGNATURE = IonType.LIST.toString().hashCode();

    IonListLite(ContainerlessContext containerlessContext, boolean z) {
        super(containerlessContext, z);
    }

    IonListLite(IonListLite ionListLite, IonContext ionContext) {
        super(ionListLite, ionContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonListLite clone(IonContext ionContext) {
        return new IonListLite(this, ionContext);
    }

    @Override // com.amazon.ion.impl.lite.IonSequenceLite, com.amazon.ion.impl.lite.IonContainerLite, com.amazon.ion.impl.lite.IonValueLite
    /* JADX INFO: renamed from: clone */
    public IonListLite mo103clone() {
        return clone((IonContext) ContainerlessContext.wrap(getSystem()));
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    int hashCode(_Private_IonValue.SymbolTableProvider symbolTableProvider) {
        return sequenceHashCode(HASH_SIGNATURE, symbolTableProvider);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public IonType getType() {
        return IonType.LIST;
    }

    @Override // com.amazon.ion.IonValue
    public void accept(ValueVisitor valueVisitor) throws Exception {
        valueVisitor.visit(this);
    }
}
