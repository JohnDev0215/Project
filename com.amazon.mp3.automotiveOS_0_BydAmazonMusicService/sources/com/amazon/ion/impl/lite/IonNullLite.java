package com.amazon.ion.impl.lite;

import com.amazon.ion.IonNull;
import com.amazon.ion.IonType;
import com.amazon.ion.IonWriter;
import com.amazon.ion.ValueVisitor;
import com.amazon.ion.impl._Private_IonValue;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
final class IonNullLite extends IonValueLite implements IonNull {
    private static final int HASH_SIGNATURE = IonType.NULL.toString().hashCode();

    protected IonNullLite(ContainerlessContext containerlessContext) {
        super(containerlessContext, true);
    }

    IonNullLite(IonNullLite ionNullLite, IonContext ionContext) {
        super(ionNullLite, ionContext);
    }

    @Override // com.amazon.ion.IonValue
    public void accept(ValueVisitor valueVisitor) throws Exception {
        valueVisitor.visit(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonNullLite clone(IonContext ionContext) {
        return new IonNullLite(this, ionContext);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    /* JADX INFO: renamed from: clone */
    public IonNullLite mo103clone() {
        return clone((IonContext) ContainerlessContext.wrap(getSystem()));
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public IonType getType() {
        return IonType.NULL;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    final void writeBodyTo(IonWriter ionWriter, _Private_IonValue.SymbolTableProvider symbolTableProvider) throws IOException {
        ionWriter.writeNull();
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public int hashCode(_Private_IonValue.SymbolTableProvider symbolTableProvider) {
        return hashTypeAnnotations(HASH_SIGNATURE, symbolTableProvider);
    }
}
