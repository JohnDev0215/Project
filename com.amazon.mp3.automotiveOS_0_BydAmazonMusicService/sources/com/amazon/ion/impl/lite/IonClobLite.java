package com.amazon.ion.impl.lite;

import com.amazon.ion.IonClob;
import com.amazon.ion.IonType;
import com.amazon.ion.IonWriter;
import com.amazon.ion.ValueVisitor;
import com.amazon.ion.impl._Private_IonValue;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
final class IonClobLite extends IonLobLite implements IonClob {
    private static final int HASH_SIGNATURE = IonType.CLOB.toString().hashCode();

    IonClobLite(ContainerlessContext containerlessContext, boolean z) {
        super(containerlessContext, z);
    }

    IonClobLite(IonClobLite ionClobLite, IonContext ionContext) {
        super(ionClobLite, ionContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonClobLite clone(IonContext ionContext) {
        return new IonClobLite(this, ionContext);
    }

    @Override // com.amazon.ion.impl.lite.IonLobLite, com.amazon.ion.impl.lite.IonValueLite
    /* JADX INFO: renamed from: clone */
    public IonClobLite mo103clone() {
        return clone((IonContext) ContainerlessContext.wrap(getSystem()));
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    int hashCode(_Private_IonValue.SymbolTableProvider symbolTableProvider) {
        return lobHashCode(HASH_SIGNATURE, symbolTableProvider);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public IonType getType() {
        return IonType.CLOB;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    final void writeBodyTo(IonWriter ionWriter, _Private_IonValue.SymbolTableProvider symbolTableProvider) throws IOException {
        ionWriter.writeClob(getBytesNoCopy());
    }

    @Override // com.amazon.ion.IonValue
    public void accept(ValueVisitor valueVisitor) throws Exception {
        valueVisitor.visit(this);
    }
}
