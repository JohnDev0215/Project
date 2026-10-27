package com.amazon.ion.impl;

import com.amazon.ion.IonReader;
import com.amazon.ion.system.IonReaderBuilder;
import com.amazon.ion.util.IonStreamUtils;
import java.io.ByteArrayInputStream;

/* JADX INFO: loaded from: classes3.dex */
public class _Private_IonReaderBuilder extends IonReaderBuilder {
    private _Private_LocalSymbolTableFactory lstFactory;

    private _Private_IonReaderBuilder() {
        this.lstFactory = LocalSymbolTable.DEFAULT_LST_FACTORY;
    }

    private _Private_IonReaderBuilder(_Private_IonReaderBuilder _private_ionreaderbuilder) {
        super(_private_ionreaderbuilder);
        this.lstFactory = _private_ionreaderbuilder.lstFactory;
    }

    public IonReaderBuilder withLstFactory(_Private_LocalSymbolTableFactory _private_localsymboltablefactory) {
        _Private_IonReaderBuilder _private_ionreaderbuilder = (_Private_IonReaderBuilder) mutable();
        _private_ionreaderbuilder.setLstFactory(_private_localsymboltablefactory);
        return _private_ionreaderbuilder;
    }

    public void setLstFactory(_Private_LocalSymbolTableFactory _private_localsymboltablefactory) {
        mutationCheck();
        if (_private_localsymboltablefactory == null) {
            this.lstFactory = LocalSymbolTable.DEFAULT_LST_FACTORY;
        } else {
            this.lstFactory = _private_localsymboltablefactory;
        }
    }

    public static class Mutable extends _Private_IonReaderBuilder {
        @Override // com.amazon.ion.system.IonReaderBuilder
        public IonReaderBuilder mutable() {
            return this;
        }

        @Override // com.amazon.ion.system.IonReaderBuilder
        protected void mutationCheck() {
        }

        public Mutable() {
            super();
        }

        public Mutable(IonReaderBuilder ionReaderBuilder) {
            super();
        }

        @Override // com.amazon.ion.system.IonReaderBuilder
        public IonReaderBuilder immutable() {
            return new _Private_IonReaderBuilder();
        }
    }

    @Override // com.amazon.ion.system.IonReaderBuilder
    public IonReader build(byte[] bArr, int i, int i2) {
        if (isIncrementalReadingEnabled()) {
            if (IonStreamUtils.isGzip(bArr, i, i2)) {
                throw new IllegalArgumentException("Automatic GZIP detection is not supported with incrementalsupport enabled. Wrap the bytes with a GZIPInputStream and call build(InputStream).");
            }
            if (IonStreamUtils.isIonBinary(bArr, i, i2)) {
                return _Private_IonReaderFactory.makeIncrementalReader(this, new ByteArrayInputStream(bArr, i, i2));
            }
        }
        return _Private_IonReaderFactory.makeReader(validateCatalog(), bArr, i, i2, this.lstFactory);
    }
}
