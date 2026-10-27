package com.amazon.ion.impl.lite;

import com.amazon.ion.IonBlob;
import com.amazon.ion.IonType;
import com.amazon.ion.IonWriter;
import com.amazon.ion.ValueVisitor;
import com.amazon.ion.impl._Private_IonValue;
import com.amazon.ion.impl._Private_Utils;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes3.dex */
final class IonBlobLite extends IonLobLite implements IonBlob {
    private static final int HASH_SIGNATURE = IonType.BLOB.toString().hashCode();

    IonBlobLite(ContainerlessContext containerlessContext, boolean z) {
        super(containerlessContext, z);
    }

    IonBlobLite(IonBlobLite ionBlobLite, IonContext ionContext) {
        super(ionBlobLite, ionContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonBlobLite clone(IonContext ionContext) {
        return new IonBlobLite(this, ionContext);
    }

    @Override // com.amazon.ion.impl.lite.IonLobLite, com.amazon.ion.impl.lite.IonValueLite
    /* JADX INFO: renamed from: clone */
    public IonBlobLite mo103clone() {
        return clone((IonContext) ContainerlessContext.wrap(getSystem()));
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    int hashCode(_Private_IonValue.SymbolTableProvider symbolTableProvider) {
        return lobHashCode(HASH_SIGNATURE, symbolTableProvider);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public IonType getType() {
        return IonType.BLOB;
    }

    @Override // com.amazon.ion.IonBlob
    public void printBase64(Appendable appendable) throws IOException {
        validateThisNotNull();
        InputStream inputStreamNewInputStream = newInputStream();
        try {
            _Private_Utils.writeAsBase64(inputStreamNewInputStream, appendable);
        } finally {
            inputStreamNewInputStream.close();
        }
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    final void writeBodyTo(IonWriter ionWriter, _Private_IonValue.SymbolTableProvider symbolTableProvider) throws IOException {
        ionWriter.writeBlob(getBytesNoCopy());
    }

    @Override // com.amazon.ion.IonValue
    public void accept(ValueVisitor valueVisitor) throws Exception {
        valueVisitor.visit(this);
    }
}
