package com.amazon.ion.system;

import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonSystem;
import com.amazon.ion.impl._Private_IonBinaryWriterBuilder;
import com.amazon.ion.impl._Private_Utils;
import com.amazon.ion.impl.lite._Private_LiteDomTrampoline;

/* JADX INFO: loaded from: classes3.dex */
public class IonSystemBuilder {
    private static final IonSystemBuilder STANDARD = new IonSystemBuilder();
    IonCatalog myCatalog;
    boolean myStreamCopyOptimized;
    IonReaderBuilder readerBuilder;

    public static IonSystemBuilder standard() {
        return STANDARD;
    }

    private IonSystemBuilder() {
        this.myStreamCopyOptimized = false;
    }

    private IonSystemBuilder(IonSystemBuilder ionSystemBuilder) {
        this.myStreamCopyOptimized = false;
        this.myCatalog = ionSystemBuilder.myCatalog;
        this.myStreamCopyOptimized = ionSystemBuilder.myStreamCopyOptimized;
        this.readerBuilder = ionSystemBuilder.readerBuilder;
    }

    public final IonSystemBuilder copy() {
        return new Mutable();
    }

    public IonSystemBuilder mutable() {
        return copy();
    }

    void mutationCheck() {
        throw new UnsupportedOperationException("This builder is immutable");
    }

    public final void setCatalog(IonCatalog ionCatalog) {
        mutationCheck();
        this.myCatalog = ionCatalog;
    }

    public final IonSystemBuilder withCatalog(IonCatalog ionCatalog) {
        IonSystemBuilder ionSystemBuilderMutable = mutable();
        ionSystemBuilderMutable.setCatalog(ionCatalog);
        return ionSystemBuilderMutable;
    }

    public final IonSystem build() {
        IonCatalog simpleCatalog = this.myCatalog;
        if (simpleCatalog == null) {
            simpleCatalog = new SimpleCatalog();
        }
        IonTextWriterBuilder ionTextWriterBuilderWithCharsetAscii = IonTextWriterBuilder.standard().withCharsetAscii();
        ionTextWriterBuilderWithCharsetAscii.setCatalog(simpleCatalog);
        _Private_IonBinaryWriterBuilder _private_ionbinarywriterbuilderStandard = _Private_IonBinaryWriterBuilder.standard();
        _private_ionbinarywriterbuilderStandard.setCatalog(simpleCatalog);
        _private_ionbinarywriterbuilderStandard.setStreamCopyOptimized(this.myStreamCopyOptimized);
        _private_ionbinarywriterbuilderStandard.setInitialSymbolTable(_Private_Utils.systemSymtab(1));
        IonReaderBuilder ionReaderBuilderStandard = this.readerBuilder;
        if (ionReaderBuilderStandard == null) {
            ionReaderBuilderStandard = IonReaderBuilder.standard();
        }
        return _Private_LiteDomTrampoline.newLiteSystem(ionTextWriterBuilderWithCharsetAscii, _private_ionbinarywriterbuilderStandard, ionReaderBuilderStandard.withCatalog(simpleCatalog));
    }

    private static final class Mutable extends IonSystemBuilder {
        @Override // com.amazon.ion.system.IonSystemBuilder
        public IonSystemBuilder mutable() {
            return this;
        }

        @Override // com.amazon.ion.system.IonSystemBuilder
        void mutationCheck() {
        }

        private Mutable(IonSystemBuilder ionSystemBuilder) {
            super();
        }
    }
}
