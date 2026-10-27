package com.amazon.ion.impl.lite;

import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonLoader;
import com.amazon.ion.system.IonReaderBuilder;

/* JADX INFO: loaded from: classes3.dex */
final class IonLoaderLite implements IonLoader {
    private final IonCatalog _catalog;
    private final IonReaderBuilder _readerBuilder;
    private final IonSystemLite _system;

    public IonLoaderLite(IonSystemLite ionSystemLite, IonCatalog ionCatalog) {
        this._system = ionSystemLite;
        this._catalog = ionCatalog;
        if (ionCatalog == ionSystemLite.getCatalog()) {
            this._readerBuilder = ionSystemLite.getReaderBuilder();
        } else {
            this._readerBuilder = ionSystemLite.getReaderBuilder().withCatalog(ionCatalog).immutable();
        }
    }
}
