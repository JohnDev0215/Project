package com.amazon.ion.impl.bin;

import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonWriter;
import com.amazon.ion.SubstituteSymbolTableException;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.impl.bin.AbstractIonWriter;
import com.amazon.ion.impl.bin.IonManagedBinaryWriter;
import com.amazon.ion.impl.bin.IonRawBinaryWriter;
import com.amazon.ion.system.SimpleCatalog;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes3.dex */
public final class _Private_IonManagedBinaryWriterBuilder {
    volatile IonCatalog catalog;
    volatile IonManagedBinaryWriter.ImportedSymbolContext imports;
    volatile SymbolTable initialSymbolTable;
    volatile boolean isFloatBinary32Enabled;
    volatile boolean isLocalSymbolTableAppendEnabled;
    volatile AbstractIonWriter.WriteValueOptimization optimization;
    volatile IonRawBinaryWriter.PreallocationMode preallocationMode;
    final BlockAllocatorProvider provider;
    volatile int symbolsBlockSize;
    volatile int userBlockSize;

    /* JADX INFO: renamed from: com.amazon.ion.impl.bin._Private_IonManagedBinaryWriterBuilder$1, reason: invalid class name */
    class AnonymousClass1 {
    }

    public enum AllocatorMode {
        POOLED { // from class: com.amazon.ion.impl.bin._Private_IonManagedBinaryWriterBuilder.AllocatorMode.1
            @Override // com.amazon.ion.impl.bin._Private_IonManagedBinaryWriterBuilder.AllocatorMode
            BlockAllocatorProvider createAllocatorProvider() {
                return PooledBlockAllocatorProvider.getInstance();
            }
        },
        BASIC { // from class: com.amazon.ion.impl.bin._Private_IonManagedBinaryWriterBuilder.AllocatorMode.2
            @Override // com.amazon.ion.impl.bin._Private_IonManagedBinaryWriterBuilder.AllocatorMode
            BlockAllocatorProvider createAllocatorProvider() {
                return BlockAllocatorProviders.basicProvider();
            }
        };

        abstract BlockAllocatorProvider createAllocatorProvider();

        /* synthetic */ AllocatorMode(AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    private _Private_IonManagedBinaryWriterBuilder(BlockAllocatorProvider blockAllocatorProvider) {
        this.provider = blockAllocatorProvider;
        this.symbolsBlockSize = 32768;
        this.userBlockSize = 32768;
        this.imports = IonManagedBinaryWriter.ONLY_SYSTEM_IMPORTS;
        this.preallocationMode = IonRawBinaryWriter.PreallocationMode.PREALLOCATE_2;
        this.catalog = new SimpleCatalog();
        this.optimization = AbstractIonWriter.WriteValueOptimization.NONE;
        this.isLocalSymbolTableAppendEnabled = false;
        this.isFloatBinary32Enabled = false;
    }

    private _Private_IonManagedBinaryWriterBuilder(_Private_IonManagedBinaryWriterBuilder _private_ionmanagedbinarywriterbuilder) {
        this.provider = _private_ionmanagedbinarywriterbuilder.provider;
        this.symbolsBlockSize = _private_ionmanagedbinarywriterbuilder.symbolsBlockSize;
        this.userBlockSize = _private_ionmanagedbinarywriterbuilder.userBlockSize;
        this.preallocationMode = _private_ionmanagedbinarywriterbuilder.preallocationMode;
        this.imports = _private_ionmanagedbinarywriterbuilder.imports;
        this.catalog = _private_ionmanagedbinarywriterbuilder.catalog;
        this.optimization = _private_ionmanagedbinarywriterbuilder.optimization;
        this.initialSymbolTable = _private_ionmanagedbinarywriterbuilder.initialSymbolTable;
        this.isLocalSymbolTableAppendEnabled = _private_ionmanagedbinarywriterbuilder.isLocalSymbolTableAppendEnabled;
        this.isFloatBinary32Enabled = _private_ionmanagedbinarywriterbuilder.isFloatBinary32Enabled;
    }

    public _Private_IonManagedBinaryWriterBuilder copy() {
        return new _Private_IonManagedBinaryWriterBuilder(this);
    }

    public _Private_IonManagedBinaryWriterBuilder withPaddedLengthPreallocation(int i) {
        this.preallocationMode = IonRawBinaryWriter.PreallocationMode.withPadSize(i);
        return this;
    }

    public _Private_IonManagedBinaryWriterBuilder withCatalog(IonCatalog ionCatalog) {
        this.catalog = ionCatalog;
        return this;
    }

    public _Private_IonManagedBinaryWriterBuilder withStreamCopyOptimization(boolean z) {
        this.optimization = z ? AbstractIonWriter.WriteValueOptimization.COPY_OPTIMIZED : AbstractIonWriter.WriteValueOptimization.NONE;
        return this;
    }

    public _Private_IonManagedBinaryWriterBuilder withInitialSymbolTable(SymbolTable symbolTable) {
        if (symbolTable != null) {
            if (!symbolTable.isLocalTable() && !symbolTable.isSystemTable()) {
                throw new IllegalArgumentException("Initial symbol table must be local or system");
            }
            if (symbolTable.isSystemTable()) {
                if (symbolTable.getMaxId() != 9) {
                    throw new IllegalArgumentException("Unsupported system symbol table");
                }
                symbolTable = null;
            } else {
                for (SymbolTable symbolTable2 : symbolTable.getImportedTables()) {
                    if (symbolTable2.isSubstitute()) {
                        throw new SubstituteSymbolTableException("Cannot use initial symbol table with imported substitutes");
                    }
                }
            }
        }
        this.initialSymbolTable = symbolTable;
        return this;
    }

    public IonWriter newWriter(OutputStream outputStream) throws IOException {
        return new IonManagedBinaryWriter(this, outputStream);
    }

    public static _Private_IonManagedBinaryWriterBuilder create(AllocatorMode allocatorMode) {
        return new _Private_IonManagedBinaryWriterBuilder(allocatorMode.createAllocatorProvider());
    }
}
