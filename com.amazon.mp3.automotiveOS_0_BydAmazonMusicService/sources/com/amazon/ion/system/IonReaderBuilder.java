package com.amazon.ion.system;

import com.amazon.ion.IonBufferConfiguration;
import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonReader;
import com.amazon.ion.impl._Private_IonReaderBuilder;

/* JADX INFO: loaded from: classes3.dex */
public abstract class IonReaderBuilder {
    private IonBufferConfiguration bufferConfiguration;
    private IonCatalog catalog;
    private boolean isAnnotationIteratorReuseEnabled;
    private boolean isIncrementalReadingEnabled;

    public abstract IonReader build(byte[] bArr, int i, int i2);

    public IonReaderBuilder immutable() {
        return this;
    }

    protected IonReaderBuilder() {
        this.catalog = null;
        this.isIncrementalReadingEnabled = false;
        this.bufferConfiguration = null;
        this.isAnnotationIteratorReuseEnabled = true;
    }

    protected IonReaderBuilder(IonReaderBuilder ionReaderBuilder) {
        this.catalog = null;
        this.isIncrementalReadingEnabled = false;
        this.bufferConfiguration = null;
        this.isAnnotationIteratorReuseEnabled = true;
        this.catalog = ionReaderBuilder.catalog;
        this.isIncrementalReadingEnabled = ionReaderBuilder.isIncrementalReadingEnabled;
        this.bufferConfiguration = ionReaderBuilder.bufferConfiguration;
        this.isAnnotationIteratorReuseEnabled = ionReaderBuilder.isAnnotationIteratorReuseEnabled;
    }

    public static IonReaderBuilder standard() {
        return new _Private_IonReaderBuilder.Mutable();
    }

    public IonReaderBuilder copy() {
        return new _Private_IonReaderBuilder.Mutable(this);
    }

    public IonReaderBuilder mutable() {
        return copy();
    }

    protected void mutationCheck() {
        throw new UnsupportedOperationException("This builder is immutable");
    }

    public IonReaderBuilder withCatalog(IonCatalog ionCatalog) {
        IonReaderBuilder ionReaderBuilderMutable = mutable();
        ionReaderBuilderMutable.setCatalog(ionCatalog);
        return ionReaderBuilderMutable;
    }

    public void setCatalog(IonCatalog ionCatalog) {
        mutationCheck();
        this.catalog = ionCatalog;
    }

    public IonCatalog getCatalog() {
        return this.catalog;
    }

    protected IonCatalog validateCatalog() {
        IonCatalog ionCatalog = this.catalog;
        return ionCatalog != null ? ionCatalog : new SimpleCatalog();
    }

    public boolean isIncrementalReadingEnabled() {
        return this.isIncrementalReadingEnabled;
    }

    public IonBufferConfiguration getBufferConfiguration() {
        return this.bufferConfiguration;
    }

    public boolean isAnnotationIteratorReuseEnabled() {
        return this.isAnnotationIteratorReuseEnabled;
    }

    public IonReader build(byte[] bArr) {
        return build(bArr, 0, bArr.length);
    }
}
