package com.amazon.ion.system;

import com.amazon.ion.IonCatalog;
import com.amazon.ion.impl._Private_IonBinaryWriterBuilder;

/* JADX INFO: loaded from: classes3.dex */
public abstract class IonBinaryWriterBuilder extends IonWriterBuilderBase<IonBinaryWriterBuilder> {
    private boolean myStreamCopyOptimized;

    @Override // com.amazon.ion.system.IonWriterBuilderBase
    protected /* bridge */ /* synthetic */ void mutationCheck() {
        super.mutationCheck();
    }

    @Override // com.amazon.ion.system.IonWriterBuilderBase
    public /* bridge */ /* synthetic */ void setCatalog(IonCatalog ionCatalog) {
        super.setCatalog(ionCatalog);
    }

    protected IonBinaryWriterBuilder() {
    }

    protected IonBinaryWriterBuilder(IonBinaryWriterBuilder ionBinaryWriterBuilder) {
        super(ionBinaryWriterBuilder);
        this.myStreamCopyOptimized = ionBinaryWriterBuilder.myStreamCopyOptimized;
    }

    public static IonBinaryWriterBuilder standard() {
        return _Private_IonBinaryWriterBuilder.standard();
    }

    public void setStreamCopyOptimized(boolean z) {
        mutationCheck();
        this.myStreamCopyOptimized = z;
    }
}
