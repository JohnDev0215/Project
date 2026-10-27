package com.amazon.ion.impl.bin;

import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonException;
import com.amazon.ion.IonType;
import com.amazon.ion.IonWriter;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.Timestamp;
import com.amazon.ion.UnknownSymbolException;
import com.amazon.ion.impl.bin.AbstractIonWriter;
import com.amazon.ion.impl.bin.IonRawBinaryWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
final class IonManagedBinaryWriter extends AbstractIonWriter implements IonWriter {
    private final ImportedSymbolContext bootstrapImports;
    private final IonCatalog catalog;
    private boolean closed;
    private ImportedSymbolContext imports;
    private boolean isUserLSTAppend;
    private SymbolTable localSymbolTableView;
    private final Map<String, SymbolToken> locals;
    private boolean localsLocked;
    private final boolean lstAppendEnabled;
    private SymbolState symbolState;
    private final IonRawBinaryWriter symbols;
    private final IonRawBinaryWriter user;
    private final ImportDescriptor userCurrentImport;
    private final List<SymbolTable> userImports;
    private UserState userState;
    private long userSymbolTablePosition;
    private final List<String> userSymbols;
    static final ImportedSymbolContext ONLY_SYSTEM_IMPORTS = new ImportedSymbolContext(ImportedSymbolResolverMode.FLAT, Collections.emptyList());
    private static final SymbolTable[] EMPTY_SYMBOL_TABLE_ARRAY = new SymbolTable[0];

    enum ImportedSymbolResolverMode {
        FLAT { // from class: com.amazon.ion.impl.bin.IonManagedBinaryWriter.ImportedSymbolResolverMode.1
            @Override // com.amazon.ion.impl.bin.IonManagedBinaryWriter.ImportedSymbolResolverMode
            SymbolResolverBuilder createBuilder() {
                final HashMap map = new HashMap();
                for (SymbolToken symbolToken : Symbols.systemSymbols()) {
                    map.put(symbolToken.getText(), symbolToken);
                }
                return new SymbolResolverBuilder() { // from class: com.amazon.ion.impl.bin.IonManagedBinaryWriter.ImportedSymbolResolverMode.1.1
                    @Override // com.amazon.ion.impl.bin.IonManagedBinaryWriter.SymbolResolverBuilder
                    public int addSymbolTable(SymbolTable symbolTable, int i) {
                        Iterator<String> itIterateDeclaredSymbolNames = symbolTable.iterateDeclaredSymbolNames();
                        while (itIterateDeclaredSymbolNames.hasNext()) {
                            String next = itIterateDeclaredSymbolNames.next();
                            if (next != null && !map.containsKey(next)) {
                                map.put(next, Symbols.symbol(next, i));
                            }
                            i++;
                        }
                        return i;
                    }

                    @Override // com.amazon.ion.impl.bin.IonManagedBinaryWriter.SymbolResolverBuilder
                    public SymbolResolver build() {
                        return new SymbolResolver() { // from class: com.amazon.ion.impl.bin.IonManagedBinaryWriter.ImportedSymbolResolverMode.1.1.1
                            @Override // com.amazon.ion.impl.bin.IonManagedBinaryWriter.SymbolResolver
                            public SymbolToken get(String str) {
                                return (SymbolToken) map.get(str);
                            }
                        };
                    }
                };
            }
        },
        DELEGATE { // from class: com.amazon.ion.impl.bin.IonManagedBinaryWriter.ImportedSymbolResolverMode.2
            @Override // com.amazon.ion.impl.bin.IonManagedBinaryWriter.ImportedSymbolResolverMode
            SymbolResolverBuilder createBuilder() {
                final ArrayList arrayList = new ArrayList();
                arrayList.add(new ImportTablePosition(Symbols.systemSymbolTable(), 1));
                return new SymbolResolverBuilder() { // from class: com.amazon.ion.impl.bin.IonManagedBinaryWriter.ImportedSymbolResolverMode.2.1
                    @Override // com.amazon.ion.impl.bin.IonManagedBinaryWriter.SymbolResolverBuilder
                    public int addSymbolTable(SymbolTable symbolTable, int i) {
                        arrayList.add(new ImportTablePosition(symbolTable, i));
                        return i + symbolTable.getMaxId();
                    }

                    @Override // com.amazon.ion.impl.bin.IonManagedBinaryWriter.SymbolResolverBuilder
                    public SymbolResolver build() {
                        return new SymbolResolver() { // from class: com.amazon.ion.impl.bin.IonManagedBinaryWriter.ImportedSymbolResolverMode.2.1.1
                            @Override // com.amazon.ion.impl.bin.IonManagedBinaryWriter.SymbolResolver
                            public SymbolToken get(String str) {
                                Iterator it = arrayList.iterator();
                                while (it.hasNext()) {
                                    if (((ImportTablePosition) it.next()).table.find(str) != null) {
                                        return Symbols.symbol(str, (r2.getSid() + r1.startId) - 1);
                                    }
                                }
                                return null;
                            }
                        };
                    }
                };
            }
        };

        abstract SymbolResolverBuilder createBuilder();
    }

    private interface SymbolResolver {
        SymbolToken get(String str);
    }

    private interface SymbolResolverBuilder {
        int addSymbolTable(SymbolTable symbolTable, int i);

        SymbolResolver build();
    }

    private enum SymbolState {
        SYSTEM_SYMBOLS { // from class: com.amazon.ion.impl.bin.IonManagedBinaryWriter.SymbolState.1
            @Override // com.amazon.ion.impl.bin.IonManagedBinaryWriter.SymbolState
            public void closeTable(IonRawBinaryWriter ionRawBinaryWriter) throws IOException {
                ionRawBinaryWriter.writeIonVersionMarker();
            }
        },
        LOCAL_SYMBOLS_WITH_IMPORTS_ONLY { // from class: com.amazon.ion.impl.bin.IonManagedBinaryWriter.SymbolState.2
            @Override // com.amazon.ion.impl.bin.IonManagedBinaryWriter.SymbolState
            public void closeTable(IonRawBinaryWriter ionRawBinaryWriter) throws IOException {
                ionRawBinaryWriter.stepOut();
            }
        },
        LOCAL_SYMBOLS { // from class: com.amazon.ion.impl.bin.IonManagedBinaryWriter.SymbolState.3
            @Override // com.amazon.ion.impl.bin.IonManagedBinaryWriter.SymbolState
            public void closeTable(IonRawBinaryWriter ionRawBinaryWriter) throws IOException {
                ionRawBinaryWriter.stepOut();
                ionRawBinaryWriter.stepOut();
            }
        },
        LOCAL_SYMBOLS_FLUSHED { // from class: com.amazon.ion.impl.bin.IonManagedBinaryWriter.SymbolState.4
            @Override // com.amazon.ion.impl.bin.IonManagedBinaryWriter.SymbolState
            public void closeTable(IonRawBinaryWriter ionRawBinaryWriter) throws IOException {
            }
        };

        public abstract void closeTable(IonRawBinaryWriter ionRawBinaryWriter) throws IOException;
    }

    private static final class ImportTablePosition {
        public final int startId;
        public final SymbolTable table;

        public ImportTablePosition(SymbolTable symbolTable, int i) {
            this.table = symbolTable;
            this.startId = i;
        }
    }

    static final class ImportedSymbolContext {
        public final SymbolResolver importedSymbols;
        public final int localSidStart;
        public final List<SymbolTable> parents;

        ImportedSymbolContext(ImportedSymbolResolverMode importedSymbolResolverMode, List<SymbolTable> list) {
            ArrayList arrayList = new ArrayList(list.size());
            SymbolResolverBuilder symbolResolverBuilderCreateBuilder = importedSymbolResolverMode.createBuilder();
            int iAddSymbolTable = 10;
            for (SymbolTable symbolTable : list) {
                if (!symbolTable.isSharedTable()) {
                    throw new IonException("Imported symbol table is not shared: " + symbolTable);
                }
                if (!symbolTable.isSystemTable()) {
                    arrayList.add(symbolTable);
                    iAddSymbolTable = symbolResolverBuilderCreateBuilder.addSymbolTable(symbolTable, iAddSymbolTable);
                }
            }
            this.parents = Collections.unmodifiableList(arrayList);
            this.importedSymbols = symbolResolverBuilderCreateBuilder.build();
            this.localSidStart = iAddSymbolTable;
        }
    }

    private static class ImportDescriptor {
        public int maxId;
        public String name;
        public int version;

        public ImportDescriptor() {
            reset();
        }

        public void reset() {
            this.name = null;
            this.version = -1;
            this.maxId = -1;
        }

        public boolean isDefined() {
            return this.name != null && this.version >= 1;
        }

        public boolean isUndefined() {
            return this.name == null && this.version == -1 && this.maxId == -1;
        }

        public boolean isMalformed() {
            return (isDefined() || isUndefined()) ? false : true;
        }

        public String toString() {
            return "{name: \"" + this.name + "\", version: " + this.version + ", max_id: " + this.maxId + "}";
        }
    }

    private enum UserState {
        NORMAL { // from class: com.amazon.ion.impl.bin.IonManagedBinaryWriter.UserState.1
            @Override // com.amazon.ion.impl.bin.IonManagedBinaryWriter.UserState
            public void afterStepOut(IonManagedBinaryWriter ionManagedBinaryWriter) {
            }

            @Override // com.amazon.ion.impl.bin.IonManagedBinaryWriter.UserState
            public void writeInt(IonManagedBinaryWriter ionManagedBinaryWriter, BigInteger bigInteger) {
            }

            @Override // com.amazon.ion.impl.bin.IonManagedBinaryWriter.UserState
            public void beforeStepIn(IonManagedBinaryWriter ionManagedBinaryWriter, IonType ionType) {
                if (ionManagedBinaryWriter.user.hasTopLevelSymbolTableAnnotation() && ionType == IonType.STRUCT) {
                    ionManagedBinaryWriter.userState = UserState.LOCALS_AT_TOP;
                    ionManagedBinaryWriter.userSymbolTablePosition = ionManagedBinaryWriter.user.position();
                }
            }
        },
        LOCALS_AT_TOP { // from class: com.amazon.ion.impl.bin.IonManagedBinaryWriter.UserState.2
            @Override // com.amazon.ion.impl.bin.IonManagedBinaryWriter.UserState
            public void beforeStepIn(IonManagedBinaryWriter ionManagedBinaryWriter, IonType ionType) {
                if (ionManagedBinaryWriter.user.getDepth() == 1) {
                    int fieldId = ionManagedBinaryWriter.user.getFieldId();
                    if (fieldId == 6) {
                        if (ionType == IonType.LIST) {
                            ionManagedBinaryWriter.userState = UserState.LOCALS_AT_IMPORTS;
                            return;
                        }
                        throw new IllegalArgumentException("Cannot step into Local Symbol Table 'symbols' field as non-list: " + ionType);
                    }
                    if (fieldId != 7) {
                        return;
                    }
                    if (ionType == IonType.LIST) {
                        ionManagedBinaryWriter.userState = UserState.LOCALS_AT_SYMBOLS;
                        return;
                    }
                    throw new IllegalArgumentException("Cannot step into Local Symbol Table 'symbols' field as non-list: " + ionType);
                }
            }

            @Override // com.amazon.ion.impl.bin.IonManagedBinaryWriter.UserState
            public void afterStepOut(IonManagedBinaryWriter ionManagedBinaryWriter) throws IOException {
                if (ionManagedBinaryWriter.user.getDepth() == 0) {
                    ionManagedBinaryWriter.user.truncate(ionManagedBinaryWriter.userSymbolTablePosition);
                    if (ionManagedBinaryWriter.isUserLSTAppend) {
                        ionManagedBinaryWriter.flush();
                    } else {
                        ionManagedBinaryWriter.finish();
                        ionManagedBinaryWriter.imports = new ImportedSymbolContext(ImportedSymbolResolverMode.DELEGATE, ionManagedBinaryWriter.userImports);
                    }
                    ionManagedBinaryWriter.startLocalSymbolTableIfNeeded(false);
                    Iterator it = ionManagedBinaryWriter.userSymbols.iterator();
                    while (it.hasNext()) {
                        ionManagedBinaryWriter.intern((String) it.next());
                    }
                    ionManagedBinaryWriter.userSymbolTablePosition = 0L;
                    ionManagedBinaryWriter.userCurrentImport.reset();
                    ionManagedBinaryWriter.userImports.clear();
                    ionManagedBinaryWriter.userSymbols.clear();
                    ionManagedBinaryWriter.isUserLSTAppend = false;
                    ionManagedBinaryWriter.userState = UserState.NORMAL;
                }
            }

            @Override // com.amazon.ion.impl.bin.IonManagedBinaryWriter.UserState
            public void writeSymbolToken(IonManagedBinaryWriter ionManagedBinaryWriter, SymbolToken symbolToken) {
                if (ionManagedBinaryWriter.user.getDepth() == 1 && ionManagedBinaryWriter.user.getFieldId() == 6 && symbolToken.getSid() == 3) {
                    ionManagedBinaryWriter.isUserLSTAppend = true;
                    ionManagedBinaryWriter.userState = UserState.LOCALS_AT_TOP;
                }
            }
        },
        LOCALS_AT_IMPORTS { // from class: com.amazon.ion.impl.bin.IonManagedBinaryWriter.UserState.3
            @Override // com.amazon.ion.impl.bin.IonManagedBinaryWriter.UserState
            public void beforeStepIn(IonManagedBinaryWriter ionManagedBinaryWriter, IonType ionType) {
                if (ionType == IonType.STRUCT) {
                    return;
                }
                throw new IllegalArgumentException("Cannot step into non-struct in Local Symbol Table import list: " + ionType);
            }

            @Override // com.amazon.ion.impl.bin.IonManagedBinaryWriter.UserState
            public void afterStepOut(IonManagedBinaryWriter ionManagedBinaryWriter) {
                int depth = ionManagedBinaryWriter.user.getDepth();
                if (depth == 1) {
                    ionManagedBinaryWriter.userState = UserState.LOCALS_AT_TOP;
                    return;
                }
                if (depth != 2) {
                    return;
                }
                ImportDescriptor importDescriptor = ionManagedBinaryWriter.userCurrentImport;
                if (importDescriptor.isMalformed()) {
                    throw new IllegalArgumentException("Invalid import: " + importDescriptor);
                }
                if (importDescriptor.isDefined()) {
                    SymbolTable table = ionManagedBinaryWriter.catalog.getTable(importDescriptor.name, importDescriptor.version);
                    if (table == null) {
                        int i = importDescriptor.maxId;
                        if (i == -1) {
                            throw new IllegalArgumentException("Import is not in catalog and no max ID provided: " + importDescriptor);
                        }
                        table = Symbols.unknownSharedSymbolTable(importDescriptor.name, importDescriptor.version, i);
                    }
                    int i2 = importDescriptor.maxId;
                    if (i2 == -1 || i2 == table.getMaxId()) {
                        ionManagedBinaryWriter.userImports.add(table);
                        return;
                    }
                    throw new IllegalArgumentException("Import doesn't match Max ID: " + importDescriptor);
                }
            }

            @Override // com.amazon.ion.impl.bin.IonManagedBinaryWriter.UserState
            public void writeString(IonManagedBinaryWriter ionManagedBinaryWriter, String str) {
                if (ionManagedBinaryWriter.user.getDepth() == 3 && ionManagedBinaryWriter.user.getFieldId() == 4) {
                    if (str != null) {
                        ionManagedBinaryWriter.userCurrentImport.name = str;
                        return;
                    }
                    throw new NullPointerException("Cannot have null import name");
                }
            }

            @Override // com.amazon.ion.impl.bin.IonManagedBinaryWriter.UserState
            public void writeInt(IonManagedBinaryWriter ionManagedBinaryWriter, long j) {
                if (ionManagedBinaryWriter.user.getDepth() == 3) {
                    if (j <= 2147483647L && j >= 1) {
                        int fieldId = ionManagedBinaryWriter.user.getFieldId();
                        if (fieldId == 5) {
                            ionManagedBinaryWriter.userCurrentImport.version = (int) j;
                            return;
                        } else {
                            if (fieldId != 8) {
                                return;
                            }
                            ionManagedBinaryWriter.userCurrentImport.maxId = (int) j;
                            return;
                        }
                    }
                    throw new IllegalArgumentException("Invalid integer value in import: " + j);
                }
            }
        },
        LOCALS_AT_SYMBOLS { // from class: com.amazon.ion.impl.bin.IonManagedBinaryWriter.UserState.4
            @Override // com.amazon.ion.impl.bin.IonManagedBinaryWriter.UserState
            public void beforeStepIn(IonManagedBinaryWriter ionManagedBinaryWriter, IonType ionType) {
            }

            @Override // com.amazon.ion.impl.bin.IonManagedBinaryWriter.UserState
            public void afterStepOut(IonManagedBinaryWriter ionManagedBinaryWriter) {
                if (ionManagedBinaryWriter.user.getDepth() == 1) {
                    ionManagedBinaryWriter.userState = UserState.LOCALS_AT_TOP;
                }
            }

            @Override // com.amazon.ion.impl.bin.IonManagedBinaryWriter.UserState
            public void writeString(IonManagedBinaryWriter ionManagedBinaryWriter, String str) {
                if (ionManagedBinaryWriter.user.getDepth() == 2) {
                    ionManagedBinaryWriter.userSymbols.add(str);
                }
            }
        };

        public abstract void afterStepOut(IonManagedBinaryWriter ionManagedBinaryWriter) throws IOException;

        public abstract void beforeStepIn(IonManagedBinaryWriter ionManagedBinaryWriter, IonType ionType) throws IOException;

        public void writeInt(IonManagedBinaryWriter ionManagedBinaryWriter, long j) throws IOException {
        }

        public void writeString(IonManagedBinaryWriter ionManagedBinaryWriter, String str) throws IOException {
        }

        public void writeSymbolToken(IonManagedBinaryWriter ionManagedBinaryWriter, SymbolToken symbolToken) {
        }

        public void writeInt(IonManagedBinaryWriter ionManagedBinaryWriter, BigInteger bigInteger) throws IOException {
            writeInt(ionManagedBinaryWriter, bigInteger.longValue());
        }
    }

    private class LocalSymbolTableView extends AbstractSymbolTable {
        @Override // com.amazon.ion.SymbolTable
        public boolean isLocalTable() {
            return true;
        }

        @Override // com.amazon.ion.SymbolTable
        public boolean isSharedTable() {
            return false;
        }

        @Override // com.amazon.ion.SymbolTable
        public boolean isSubstitute() {
            return false;
        }

        @Override // com.amazon.ion.SymbolTable
        public boolean isSystemTable() {
            return false;
        }

        public LocalSymbolTableView() {
            super(null, 0);
        }

        @Override // com.amazon.ion.SymbolTable
        public Iterator<String> iterateDeclaredSymbolNames() {
            return IonManagedBinaryWriter.this.locals.keySet().iterator();
        }

        @Override // com.amazon.ion.impl.bin.AbstractSymbolTable, com.amazon.ion.SymbolTable
        public int getMaxId() {
            return getImportedMaxId() + IonManagedBinaryWriter.this.locals.size();
        }

        @Override // com.amazon.ion.SymbolTable
        public SymbolTable[] getImportedTables() {
            return (SymbolTable[]) IonManagedBinaryWriter.this.imports.parents.toArray(IonManagedBinaryWriter.EMPTY_SYMBOL_TABLE_ARRAY);
        }

        @Override // com.amazon.ion.impl.bin.AbstractSymbolTable, com.amazon.ion.SymbolTable
        public int getImportedMaxId() {
            return IonManagedBinaryWriter.this.imports.localSidStart - 1;
        }

        @Override // com.amazon.ion.SymbolTable
        public SymbolTable getSystemSymbolTable() {
            return Symbols.systemSymbolTable();
        }

        @Override // com.amazon.ion.SymbolTable
        public SymbolToken intern(String str) {
            SymbolToken symbolTokenFind = find(str);
            if (symbolTokenFind != null) {
                return symbolTokenFind;
            }
            if (!IonManagedBinaryWriter.this.localsLocked) {
                return IonManagedBinaryWriter.this.intern(str);
            }
            throw new IonException("Cannot intern into locked (read-only) local symbol table");
        }

        @Override // com.amazon.ion.SymbolTable
        public String findKnownSymbol(int i) {
            Iterator<SymbolTable> it = IonManagedBinaryWriter.this.imports.parents.iterator();
            while (it.hasNext()) {
                String strFindKnownSymbol = it.next().findKnownSymbol(i);
                if (strFindKnownSymbol != null) {
                    return strFindKnownSymbol;
                }
            }
            for (SymbolToken symbolToken : IonManagedBinaryWriter.this.locals.values()) {
                if (symbolToken.getSid() == i) {
                    return symbolToken.getText();
                }
            }
            return null;
        }

        @Override // com.amazon.ion.SymbolTable
        public SymbolToken find(String str) {
            SymbolToken symbolToken = IonManagedBinaryWriter.this.imports.importedSymbols.get(str);
            return symbolToken != null ? symbolToken : (SymbolToken) IonManagedBinaryWriter.this.locals.get(str);
        }
    }

    IonManagedBinaryWriter(_Private_IonManagedBinaryWriterBuilder _private_ionmanagedbinarywriterbuilder, OutputStream outputStream) throws IOException {
        super(_private_ionmanagedbinarywriterbuilder.optimization);
        BlockAllocatorProvider blockAllocatorProvider = _private_ionmanagedbinarywriterbuilder.provider;
        int i = _private_ionmanagedbinarywriterbuilder.symbolsBlockSize;
        AbstractIonWriter.WriteValueOptimization writeValueOptimization = AbstractIonWriter.WriteValueOptimization.NONE;
        this.symbols = new IonRawBinaryWriter(blockAllocatorProvider, i, outputStream, writeValueOptimization, IonRawBinaryWriter.StreamCloseMode.NO_CLOSE, IonRawBinaryWriter.StreamFlushMode.NO_FLUSH, _private_ionmanagedbinarywriterbuilder.preallocationMode, _private_ionmanagedbinarywriterbuilder.isFloatBinary32Enabled);
        this.user = new IonRawBinaryWriter(_private_ionmanagedbinarywriterbuilder.provider, _private_ionmanagedbinarywriterbuilder.userBlockSize, outputStream, writeValueOptimization, IonRawBinaryWriter.StreamCloseMode.CLOSE, IonRawBinaryWriter.StreamFlushMode.FLUSH, _private_ionmanagedbinarywriterbuilder.preallocationMode, _private_ionmanagedbinarywriterbuilder.isFloatBinary32Enabled);
        this.catalog = _private_ionmanagedbinarywriterbuilder.catalog;
        this.bootstrapImports = _private_ionmanagedbinarywriterbuilder.imports;
        this.locals = new LinkedHashMap();
        this.localsLocked = false;
        this.localSymbolTableView = new LocalSymbolTableView();
        this.symbolState = SymbolState.SYSTEM_SYMBOLS;
        this.closed = false;
        this.userState = UserState.NORMAL;
        this.userSymbolTablePosition = 0L;
        this.userImports = new ArrayList();
        this.userSymbols = new ArrayList();
        this.userCurrentImport = new ImportDescriptor();
        this.lstAppendEnabled = _private_ionmanagedbinarywriterbuilder.isLocalSymbolTableAppendEnabled;
        this.isUserLSTAppend = false;
        SymbolTable symbolTable = _private_ionmanagedbinarywriterbuilder.initialSymbolTable;
        if (symbolTable != null) {
            this.imports = new ImportedSymbolContext(ImportedSymbolResolverMode.DELEGATE, Arrays.asList(symbolTable.getImportedTables()));
            Iterator<String> itIterateDeclaredSymbolNames = symbolTable.iterateDeclaredSymbolNames();
            while (itIterateDeclaredSymbolNames.hasNext()) {
                intern(itIterateDeclaredSymbolNames.next());
            }
            startLocalSymbolTableIfNeeded(true);
            return;
        }
        this.imports = _private_ionmanagedbinarywriterbuilder.imports;
    }

    @Override // com.amazon.ion.impl._Private_IonWriter
    public boolean isFieldNameSet() {
        return this.user.isFieldNameSet();
    }

    @Override // com.amazon.ion.impl.bin.AbstractIonWriter, com.amazon.ion.impl._Private_IonWriter
    public int getDepth() {
        return this.user.getDepth();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startLocalSymbolTableIfNeeded(boolean z) throws IOException {
        SymbolState symbolState = this.symbolState;
        boolean z2 = symbolState == SymbolState.LOCAL_SYMBOLS_FLUSHED && this.lstAppendEnabled;
        if (symbolState == SymbolState.SYSTEM_SYMBOLS || z2) {
            if (z && !z2) {
                this.symbols.writeIonVersionMarker();
            }
            this.symbols.addTypeAnnotationSymbol(Symbols.systemSymbol(3));
            this.symbols.stepIn(IonType.STRUCT);
            if (z2) {
                this.symbols.setFieldNameSymbol(Symbols.systemSymbol(6));
                this.symbols.writeSymbolToken(Symbols.systemSymbol(3));
            } else if (this.imports.parents.size() > 0) {
                this.symbols.setFieldNameSymbol(Symbols.systemSymbol(6));
                this.symbols.stepIn(IonType.LIST);
                for (SymbolTable symbolTable : this.imports.parents) {
                    this.symbols.stepIn(IonType.STRUCT);
                    this.symbols.setFieldNameSymbol(Symbols.systemSymbol(4));
                    this.symbols.writeString(symbolTable.getName());
                    this.symbols.setFieldNameSymbol(Symbols.systemSymbol(5));
                    this.symbols.writeInt(symbolTable.getVersion());
                    this.symbols.setFieldNameSymbol(Symbols.systemSymbol(8));
                    this.symbols.writeInt(symbolTable.getMaxId());
                    this.symbols.stepOut();
                }
                this.symbols.stepOut();
            }
            this.symbolState = SymbolState.LOCAL_SYMBOLS_WITH_IMPORTS_ONLY;
        }
    }

    private void startLocalSymbolTableSymbolListIfNeeded() throws IOException {
        if (this.symbolState == SymbolState.LOCAL_SYMBOLS_WITH_IMPORTS_ONLY) {
            this.symbols.setFieldNameSymbol(Symbols.systemSymbol(7));
            this.symbols.stepIn(IonType.LIST);
            this.symbolState = SymbolState.LOCAL_SYMBOLS;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SymbolToken intern(String str) {
        if (str == null) {
            return null;
        }
        try {
            SymbolToken symbolToken = this.imports.importedSymbols.get(str);
            if (symbolToken != null) {
                if (symbolToken.getSid() > 9) {
                    startLocalSymbolTableIfNeeded(true);
                }
                return symbolToken;
            }
            SymbolToken symbolToken2 = this.locals.get(str);
            if (symbolToken2 != null) {
                return symbolToken2;
            }
            if (this.localsLocked) {
                throw new IonException("Local symbol table was locked (made read-only)");
            }
            startLocalSymbolTableIfNeeded(true);
            startLocalSymbolTableSymbolListIfNeeded();
            SymbolToken symbolTokenSymbol = Symbols.symbol(str, this.imports.localSidStart + this.locals.size());
            this.locals.put(str, symbolTokenSymbol);
            this.symbols.writeString(str);
            return symbolTokenSymbol;
        } catch (IOException e) {
            throw new IonException("Error synthesizing symbols", e);
        }
    }

    private SymbolToken intern(SymbolToken symbolToken) {
        if (symbolToken == null) {
            return null;
        }
        String text = symbolToken.getText();
        if (text != null) {
            return intern(text);
        }
        int sid = symbolToken.getSid();
        if (sid <= getSymbolTable().getMaxId()) {
            return symbolToken;
        }
        throw new UnknownSymbolException(sid);
    }

    @Override // com.amazon.ion.IonWriter
    public SymbolTable getSymbolTable() {
        if (this.symbolState == SymbolState.SYSTEM_SYMBOLS && this.imports.parents.isEmpty()) {
            return Symbols.systemSymbolTable();
        }
        return this.localSymbolTableView;
    }

    @Override // com.amazon.ion.IonWriter
    public void setFieldNameSymbol(SymbolToken symbolToken) {
        this.user.setFieldNameSymbol(intern(symbolToken));
    }

    @Override // com.amazon.ion.IonWriter
    public void setTypeAnnotationSymbols(SymbolToken... symbolTokenArr) {
        if (symbolTokenArr == null) {
            this.user.setTypeAnnotationSymbols(null);
            return;
        }
        for (int i = 0; i < symbolTokenArr.length; i++) {
            symbolTokenArr[i] = intern(symbolTokenArr[i]);
        }
        this.user.setTypeAnnotationSymbols(symbolTokenArr);
    }

    @Override // com.amazon.ion.IonWriter
    public void stepIn(IonType ionType) throws IOException {
        this.userState.beforeStepIn(this, ionType);
        this.user.stepIn(ionType);
    }

    @Override // com.amazon.ion.IonWriter
    public void stepOut() throws IOException {
        this.user.stepOut();
        this.userState.afterStepOut(this);
    }

    @Override // com.amazon.ion.IonWriter
    public boolean isInStruct() {
        return this.user.isInStruct();
    }

    @Override // com.amazon.ion.IonWriter
    public void writeNull() throws IOException {
        this.user.writeNull();
    }

    @Override // com.amazon.ion.IonWriter
    public void writeNull(IonType ionType) throws IOException {
        this.user.writeNull(ionType);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeBool(boolean z) throws IOException {
        this.user.writeBool(z);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeInt(long j) throws IOException {
        this.userState.writeInt(this, j);
        this.user.writeInt(j);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeInt(BigInteger bigInteger) throws IOException {
        this.userState.writeInt(this, bigInteger);
        this.user.writeInt(bigInteger);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeFloat(double d) throws IOException {
        this.user.writeFloat(d);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeDecimal(BigDecimal bigDecimal) throws IOException {
        this.user.writeDecimal(bigDecimal);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeTimestamp(Timestamp timestamp) throws IOException {
        this.user.writeTimestamp(timestamp);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeSymbol(String str) throws IOException {
        writeSymbolToken(intern(str));
    }

    private boolean handleIVM(int i) throws IOException {
        if (!this.user.isIVM(i)) {
            return false;
        }
        if (!this.user.hasWrittenValuesSinceFinished()) {
            return true;
        }
        finish();
        return true;
    }

    @Override // com.amazon.ion.IonWriter
    public void writeSymbolToken(SymbolToken symbolToken) throws IOException {
        if (symbolToken == null || !handleIVM(symbolToken.getSid())) {
            SymbolToken symbolTokenIntern = intern(symbolToken);
            this.userState.writeSymbolToken(this, symbolTokenIntern);
            this.user.writeSymbolToken(symbolTokenIntern);
        }
    }

    @Override // com.amazon.ion.IonWriter
    public void writeString(String str) throws IOException {
        this.userState.writeString(this, str);
        this.user.writeString(str);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeClob(byte[] bArr) throws IOException {
        this.user.writeClob(bArr);
    }

    @Override // com.amazon.ion.IonWriter
    public void writeBlob(byte[] bArr) throws IOException {
        this.user.writeBlob(bArr);
    }

    @Override // com.amazon.ion.impl._Private_ByteTransferSink
    public void writeBytes(byte[] bArr, int i, int i2) throws IOException {
        startLocalSymbolTableIfNeeded(true);
        this.user.writeBytes(bArr, i, i2);
    }

    @Override // com.amazon.ion.IonWriter, java.io.Flushable
    public void flush() throws IOException {
        if (getDepth() != 0 || this.user.hasAnnotations()) {
            return;
        }
        if (this.localsLocked || this.lstAppendEnabled) {
            unsafeFlush();
        }
    }

    private void unsafeFlush() throws IOException {
        if (this.user.hasWrittenValuesSinceFinished()) {
            this.symbolState.closeTable(this.symbols);
            this.symbolState = SymbolState.LOCAL_SYMBOLS_FLUSHED;
        }
        this.symbols.finish();
        this.user.finish();
    }

    public void finish() throws IOException {
        if (getDepth() != 0) {
            throw new IllegalStateException("IonWriter.finish() can only be called at top-level.");
        }
        unsafeFlush();
        this.locals.clear();
        this.localsLocked = false;
        this.symbolState = SymbolState.SYSTEM_SYMBOLS;
        this.imports = this.bootstrapImports;
    }

    @Override // com.amazon.ion.IonWriter, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        this.closed = true;
        try {
            try {
                finish();
            } catch (IllegalStateException unused) {
                this.symbols.close();
            } catch (Throwable th) {
                try {
                    this.symbols.close();
                    throw th;
                } finally {
                }
            }
            try {
                this.symbols.close();
            } finally {
            }
        } finally {
        }
    }
}
