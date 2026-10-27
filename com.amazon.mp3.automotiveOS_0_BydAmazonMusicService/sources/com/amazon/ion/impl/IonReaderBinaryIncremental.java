package com.amazon.ion.impl;

import com.amazon.ion.Decimal;
import com.amazon.ion.IntegerSize;
import com.amazon.ion.IonBufferConfiguration;
import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonException;
import com.amazon.ion.IonReader;
import com.amazon.ion.IonStruct;
import com.amazon.ion.IonType;
import com.amazon.ion.IonWriter;
import com.amazon.ion.ReadOnlyValueException;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.Timestamp;
import com.amazon.ion.UnknownSymbolException;
import com.amazon.ion.ValueFactory;
import com.amazon.ion.impl.IonReaderLookaheadBuffer;
import com.amazon.ion.impl._Private_RecyclingStack;
import com.amazon.ion.impl._Private_ScalarConversions;
import com.amazon.ion.impl.bin.utf8.Utf8StringDecoder;
import com.amazon.ion.impl.bin.utf8.Utf8StringDecoderPool;
import com.amazon.ion.system.IonReaderBuilder;
import com.amazon.ion.system.SimpleCatalog;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* JADX INFO: loaded from: classes3.dex */
class IonReaderBinaryIncremental implements IonReader, _Private_ReaderWriter {
    private static final IonCatalog EMPTY_CATALOG;
    private static final Iterator<String> EMPTY_ITERATOR;
    private static final byte[][] SCRATCH_FOR_SIZE;
    private static final List<String> SYSTEM_SYMBOLS_1_0;
    private static final int SYSTEM_SYMBOLS_1_0_SIZE;
    private final AnnotationIterator annotationIterator;
    private final List<Integer> annotationSids;
    private final ResizingPipedInputStream buffer;
    private final IonCatalog catalog;
    private final _Private_RecyclingStack<ContainerInfo> containerStack;
    private int importMaxId;
    private final TreeMap<Integer, SymbolTable> imports;
    private final InputStream inputStream;
    private final boolean isAnnotationIteratorReuseEnabled;
    private final IonReaderLookaheadBuffer lookahead;
    private final _Private_ScalarConversions.ValueVariant scalarConverter;
    private final List<String> symbols;
    private static final IonBufferConfiguration STANDARD_BUFFER_CONFIGURATION = IonBufferConfiguration.Builder.standard().build();
    private static final _Private_RecyclingStack.ElementFactory<ContainerInfo> CONTAINER_INFO_FACTORY = new _Private_RecyclingStack.ElementFactory<ContainerInfo>() { // from class: com.amazon.ion.impl.IonReaderBinaryIncremental.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.ion.impl._Private_RecyclingStack.ElementFactory
        public ContainerInfo newElement() {
            return new ContainerInfo();
        }
    };
    private final Utf8StringDecoder utf8Decoder = (Utf8StringDecoder) Utf8StringDecoderPool.getInstance().getOrCreate();
    private List<SymbolToken> symbolTokensById = null;
    private SymbolTable cachedReadOnlySymbolTable = null;
    private SymbolTable symbolTableLastTransferred = null;
    private int fieldNameSid = -1;
    private int majorVersion = 1;
    private int minorVersion = 0;
    private int lobBytesRead = 0;
    private IonType valueType = null;
    private IonTypeID valueTypeID = null;
    private boolean hasAnnotations = false;
    private boolean completeValueBuffered = false;
    private int valueStartPosition = -1;
    private int valueEndPosition = -1;
    private int annotationStartPosition = -1;
    private int annotationsLength = -1;
    private int peekIndex = -1;

    @Override // com.amazon.ion.facet.Faceted
    public <T> T asFacet(Class<T> cls) {
        return null;
    }

    private static class ContainerInfo {
        private int endPosition;
        private IonType type;

        private ContainerInfo() {
        }
    }

    static {
        List<String> listUnmodifiableList = Collections.unmodifiableList(Arrays.asList(null, "$ion", "$ion_1_0", "$ion_symbol_table", "name", "version", "imports", "symbols", "max_id", "$ion_shared_symbol_table"));
        SYSTEM_SYMBOLS_1_0 = listUnmodifiableList;
        SYSTEM_SYMBOLS_1_0_SIZE = listUnmodifiableList.size();
        EMPTY_CATALOG = new SimpleCatalog();
        SCRATCH_FOR_SIZE = new byte[][]{new byte[0], new byte[1], new byte[2], new byte[3], new byte[4], new byte[5], new byte[6], new byte[7], new byte[8], new byte[9], new byte[10], new byte[11], new byte[12]};
        EMPTY_ITERATOR = new Iterator<String>() { // from class: com.amazon.ion.impl.IonReaderBinaryIncremental.2
            @Override // java.util.Iterator
            public boolean hasNext() {
                return false;
            }

            @Override // java.util.Iterator
            public String next() {
                return null;
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException("Cannot remove from an empty iterator.");
            }
        };
    }

    IonReaderBinaryIncremental(IonReaderBuilder ionReaderBuilder, InputStream inputStream) {
        this.inputStream = inputStream;
        this.catalog = ionReaderBuilder.getCatalog() == null ? EMPTY_CATALOG : ionReaderBuilder.getCatalog();
        if (ionReaderBuilder.isAnnotationIteratorReuseEnabled()) {
            this.isAnnotationIteratorReuseEnabled = true;
            this.annotationIterator = new AnnotationIterator();
        } else {
            this.isAnnotationIteratorReuseEnabled = false;
            this.annotationIterator = null;
        }
        if (ionReaderBuilder.getBufferConfiguration() == null) {
            this.lookahead = new IonReaderLookaheadBuffer(STANDARD_BUFFER_CONFIGURATION, inputStream);
        } else {
            this.lookahead = new IonReaderLookaheadBuffer(ionReaderBuilder.getBufferConfiguration(), inputStream);
        }
        this.buffer = (ResizingPipedInputStream) this.lookahead.getPipe();
        this.containerStack = new _Private_RecyclingStack<>(8, CONTAINER_INFO_FACTORY);
        this.annotationSids = new ArrayList(8);
        ArrayList arrayList = new ArrayList(128);
        this.symbols = arrayList;
        arrayList.addAll(SYSTEM_SYMBOLS_1_0);
        this.imports = new TreeMap<>();
        this.scalarConverter = new _Private_ScalarConversions.ValueVariant();
        resetImports();
    }

    private class AnnotationIterator implements Iterator<String> {
        protected List<Integer> annotationSids;
        protected int index;

        private AnnotationIterator() {
            this.annotationSids = Collections.emptyList();
            this.index = 0;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.index < this.annotationSids.size();
        }

        @Override // java.util.Iterator
        public String next() {
            int iIntValue = this.annotationSids.get(this.index).intValue();
            String symbol = IonReaderBinaryIncremental.this.getSymbol(iIntValue);
            if (symbol == null) {
                throw new UnknownSymbolException(iIntValue);
            }
            this.index++;
            return symbol;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("This iterator does not support element removal.");
        }
    }

    static class ImportLocation {
        final String name;
        final int sid;

        ImportLocation(String str, int i) {
            this.name = str;
            this.sid = i;
        }

        public String getName() {
            return this.name;
        }

        public int getSid() {
            return this.sid;
        }

        public String toString() {
            return String.format("ImportLocation::{name: %s, sid: %d}", this.name, Integer.valueOf(this.sid));
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ImportLocation)) {
                return false;
            }
            ImportLocation importLocation = (ImportLocation) obj;
            return getName().equals(importLocation.getName()) && getSid() == importLocation.getSid();
        }

        public int hashCode() {
            return (getName().hashCode() * 31) + 17 + (getSid() * 31);
        }
    }

    static class SymbolTokenImpl implements _Private_SymbolToken {
        private final ImportLocation importLocation;
        private final int sid;
        private final String text;

        SymbolTokenImpl(String str, int i, ImportLocation importLocation) {
            this.text = str;
            this.sid = i;
            this.importLocation = importLocation;
        }

        @Override // com.amazon.ion.SymbolToken
        public String getText() {
            return this.text;
        }

        @Override // com.amazon.ion.impl._Private_SymbolToken, com.amazon.ion.SymbolToken
        public int getSid() {
            return this.sid;
        }

        public String toString() {
            return String.format("SymbolToken::{text: %s, sid: %d, importLocation: %s}", this.text, Integer.valueOf(this.sid), this.importLocation);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SymbolToken)) {
                return false;
            }
            SymbolToken symbolToken = (SymbolToken) obj;
            if (getText() == null || symbolToken.getText() == null) {
                return getText() == symbolToken.getText();
            }
            return getText().equals(symbolToken.getText());
        }

        public int hashCode() {
            if (getText() != null) {
                return getText().hashCode();
            }
            return 0;
        }
    }

    private class LocalSymbolTableSnapshot implements SymbolTable, SymbolTableAsStruct {
        private final SymbolTable[] importedTables;
        private final int importedTablesMaxId;
        final List<String> listView;
        final Map<String, Integer> mapView;
        private final int maxId;
        private SymbolTableStructCache structCache = null;
        private final SymbolTable system;

        @Override // com.amazon.ion.SymbolTable
        public String getName() {
            return null;
        }

        @Override // com.amazon.ion.SymbolTable
        public int getVersion() {
            return 0;
        }

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

        LocalSymbolTableSnapshot() {
            this.system = SharedSymbolTable.getSystemSymbolTable(IonReaderBinaryIncremental.this.majorVersion);
            int size = IonReaderBinaryIncremental.this.symbols.size();
            this.maxId = size - 1;
            this.importedTablesMaxId = IonReaderBinaryIncremental.this.importMaxId;
            int i = 0;
            this.listView = new ArrayList(IonReaderBinaryIncremental.this.symbols.subList(0, size));
            this.mapView = new HashMap((int) Math.ceil(((double) size) / 0.75d), 0.75f);
            for (int i2 = 0; i2 < size; i2++) {
                String str = this.listView.get(i2);
                if (str != null) {
                    this.mapView.put(str, Integer.valueOf(i2));
                }
            }
            this.importedTables = new SymbolTable[IonReaderBinaryIncremental.this.imports.size()];
            Iterator it = IonReaderBinaryIncremental.this.imports.values().iterator();
            while (it.hasNext()) {
                this.importedTables[i] = (SymbolTable) it.next();
                i++;
            }
        }

        @Override // com.amazon.ion.SymbolTable
        public SymbolTable getSystemSymbolTable() {
            return this.system;
        }

        @Override // com.amazon.ion.SymbolTable
        public String getIonVersionId() {
            return this.system.getIonVersionId();
        }

        @Override // com.amazon.ion.SymbolTable
        public SymbolTable[] getImportedTables() {
            return this.importedTables;
        }

        @Override // com.amazon.ion.SymbolTable
        public int getImportedMaxId() {
            return this.importedTablesMaxId;
        }

        @Override // com.amazon.ion.SymbolTable
        public SymbolToken find(String str) {
            Integer num = this.mapView.get(str);
            if (num == null) {
                return null;
            }
            return new SymbolTokenImpl(str, num.intValue(), null);
        }

        @Override // com.amazon.ion.SymbolTable
        public int findSymbol(String str) {
            Integer num = this.mapView.get(str);
            if (num == null) {
                return -1;
            }
            return num.intValue();
        }

        @Override // com.amazon.ion.SymbolTable
        public String findKnownSymbol(int i) {
            if (i >= 0) {
                if (i >= IonReaderBinaryIncremental.this.symbols.size()) {
                    return null;
                }
                return this.listView.get(i);
            }
            throw new IllegalArgumentException("Symbol IDs must be at least 0.");
        }

        @Override // com.amazon.ion.SymbolTable
        public Iterator<String> iterateDeclaredSymbolNames() {
            return new Iterator<String>() { // from class: com.amazon.ion.impl.IonReaderBinaryIncremental.LocalSymbolTableSnapshot.1
                private int index;

                {
                    this.index = LocalSymbolTableSnapshot.this.getImportedMaxId() + 1;
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return this.index <= LocalSymbolTableSnapshot.this.getMaxId();
                }

                @Override // java.util.Iterator
                public String next() {
                    String str = LocalSymbolTableSnapshot.this.listView.get(this.index);
                    this.index++;
                    return str;
                }

                @Override // java.util.Iterator
                public void remove() {
                    throw new UnsupportedOperationException("This iterator does not support element removal.");
                }
            };
        }

        @Override // com.amazon.ion.SymbolTable
        public SymbolToken intern(String str) {
            SymbolToken symbolTokenFind = find(str);
            if (symbolTokenFind != null) {
                return symbolTokenFind;
            }
            throw new ReadOnlyValueException();
        }

        @Override // com.amazon.ion.SymbolTable
        public int getMaxId() {
            return this.maxId;
        }

        @Override // com.amazon.ion.SymbolTable
        public void writeTo(IonWriter ionWriter) throws IOException {
            ionWriter.writeValues(new SymbolTableReader(this));
        }

        public String toString() {
            return "(LocalSymbolTable max_id:" + getMaxId() + ')';
        }

        @Override // com.amazon.ion.impl.SymbolTableAsStruct
        public IonStruct getIonRepresentation(ValueFactory valueFactory) {
            if (this.structCache == null) {
                this.structCache = new SymbolTableStructCache(this, getImportedTables(), null);
            }
            return this.structCache.getIonRepresentation(valueFactory);
        }
    }

    private void requireSupportedIonVersion() {
        if (this.majorVersion != 1 || this.minorVersion != 0) {
            throw new IonException(String.format("Unsupported Ion version: %d.%d", Integer.valueOf(this.majorVersion), Integer.valueOf(this.minorVersion)));
        }
    }

    private void resetSymbolTable() {
        this.symbols.clear();
        this.symbols.addAll(SYSTEM_SYMBOLS_1_0);
        this.cachedReadOnlySymbolTable = null;
        List<SymbolToken> list = this.symbolTokensById;
        if (list != null) {
            list.clear();
        }
    }

    private void resetImports() {
        this.imports.clear();
        this.importMaxId = SharedSymbolTable.getSystemSymbolTable(this.majorVersion).getMaxId();
    }

    private void addImport(String str, int i, int i2) {
        SymbolTable table = this.catalog.getTable(str, i);
        int i3 = this.importMaxId + i2;
        this.importMaxId = i3;
        if (table == null) {
            this.imports.put(Integer.valueOf(i3), new SubstituteSymbolTable(str, i, i2));
        } else if (table.getMaxId() != i2 || table.getVersion() != i) {
            this.imports.put(Integer.valueOf(this.importMaxId), new SubstituteSymbolTable(table, i, i2));
        } else {
            this.imports.put(Integer.valueOf(this.importMaxId), table);
        }
    }

    private Integer lowerKey(int i) {
        SortedMap<Integer, SymbolTable> sortedMapHeadMap = this.imports.headMap(Integer.valueOf(i));
        if (sortedMapHeadMap.isEmpty()) {
            return null;
        }
        return sortedMapHeadMap.lastKey();
    }

    private ImportLocation getImportLocation(int i) {
        int maxId = SharedSymbolTable.getSystemSymbolTable(this.majorVersion).getMaxId();
        int i2 = i - maxId;
        Map.Entry<Integer, SymbolTable> next = this.imports.tailMap(Integer.valueOf(i2)).entrySet().iterator().next();
        Integer numLowerKey = lowerKey(i2);
        if (numLowerKey == null) {
            numLowerKey = Integer.valueOf(maxId);
        }
        return new ImportLocation(next.getValue().getName(), i - numLowerKey.intValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getSymbol(int i) {
        if (i >= this.symbols.size()) {
            throw new IonException("Symbol ID exceeds the max ID of the symbol table.");
        }
        return this.symbols.get(i);
    }

    private SymbolToken getSymbolToken(int i) {
        if (this.symbolTokensById == null) {
            this.symbolTokensById = new ArrayList(this.symbols.size());
        }
        ImportLocation importLocation = null;
        if (this.symbolTokensById.size() < this.symbols.size()) {
            for (int size = this.symbolTokensById.size(); size < this.symbols.size(); size++) {
                this.symbolTokensById.add(null);
            }
        }
        if (i >= this.symbols.size()) {
            throw new IonException("Symbol ID exceeds the max ID of the symbol table.");
        }
        SymbolToken symbolToken = this.symbolTokensById.get(i);
        if (symbolToken != null) {
            return symbolToken;
        }
        String str = this.symbols.get(i);
        if (str == null) {
            if (i <= 0 || i > this.importMaxId) {
                i = 0;
            } else {
                importLocation = getImportLocation(i);
            }
        }
        SymbolTokenImpl symbolTokenImpl = new SymbolTokenImpl(str, i, importLocation);
        this.symbolTokensById.set(i, symbolTokenImpl);
        return symbolTokenImpl;
    }

    private void addSymbolsFromImport(SymbolTable symbolTable, int i) {
        int maxId = SharedSymbolTable.getSystemSymbolTable(this.majorVersion).getMaxId();
        Integer numLowerKey = lowerKey(i);
        if (numLowerKey == null) {
            numLowerKey = Integer.valueOf(maxId);
        }
        int iIntValue = i - numLowerKey.intValue();
        Iterator<String> itIterateDeclaredSymbolNames = symbolTable.iterateDeclaredSymbolNames();
        for (int i2 = 1; itIterateDeclaredSymbolNames.hasNext() && i2 <= iIntValue; i2++) {
            this.symbols.add(itIterateDeclaredSymbolNames.next());
        }
    }

    private void addSymbolsFromImports() {
        for (Map.Entry<Integer, SymbolTable> entry : this.imports.entrySet()) {
            addSymbolsFromImport(entry.getValue(), entry.getKey().intValue());
        }
    }

    private void readSymbolTable(IonReaderLookaheadBuffer.SymbolTableMarker symbolTableMarker) {
        int iIntValue;
        int iIntValue2;
        String strStringValue;
        this.peekIndex = symbolTableMarker.startIndex;
        int i = -1;
        int i2 = -1;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        while (this.peekIndex < symbolTableMarker.endIndex) {
            this.fieldNameSid = readVarUInt();
            IonTypeID typeId = readTypeId();
            calculateEndPosition(typeId);
            int i3 = this.valueEndPosition;
            int i4 = this.fieldNameSid;
            if (i4 == 6) {
                if (z) {
                    throw new IonException("Symbol table contained multiple imports fields.");
                }
                IonType ionType = typeId.type;
                if (ionType == IonType.SYMBOL) {
                    boolean z4 = readUInt(this.peekIndex, i3) == 3;
                    this.peekIndex = i3;
                    z3 = z4;
                } else if (ionType == IonType.LIST) {
                    resetImports();
                    stepIn();
                    IonType next = next();
                    while (next != null) {
                        if (next == IonType.STRUCT) {
                            stepIn();
                            IonType next2 = next();
                            iIntValue = -1;
                            iIntValue2 = -1;
                            strStringValue = null;
                            while (next2 != null) {
                                int fieldId = getFieldId();
                                if (fieldId == 4) {
                                    if (next2 == IonType.STRING) {
                                        strStringValue = stringValue();
                                    }
                                } else if (fieldId == 5) {
                                    if (next2 == IonType.INT) {
                                        iIntValue = intValue();
                                    }
                                } else if (fieldId == 8 && next2 == IonType.INT) {
                                    iIntValue2 = intValue();
                                }
                                next2 = next();
                            }
                            stepOut();
                        } else {
                            iIntValue = -1;
                            iIntValue2 = -1;
                            strStringValue = null;
                        }
                        addImport(strStringValue, iIntValue, iIntValue2);
                        next = next();
                    }
                    stepOut();
                }
                if (!z3) {
                    resetSymbolTable();
                    addSymbolsFromImports();
                }
                z = true;
            } else if (i4 != 7) {
                continue;
            } else {
                if (z2) {
                    throw new IonException("Symbol table contained multiple symbols fields.");
                }
                if (typeId.type == IonType.LIST) {
                    i = this.peekIndex;
                    i2 = i3;
                }
                z2 = true;
            }
            this.peekIndex = i3;
        }
        if (!z) {
            resetSymbolTable();
            resetImports();
        }
        if (i > -1) {
            this.peekIndex = i;
            this.valueType = IonType.LIST;
            this.valueEndPosition = i2;
            stepIn();
            while (next() != null) {
                if (this.valueType != IonType.STRING) {
                    this.symbols.add(null);
                } else {
                    this.symbols.add(stringValue());
                }
            }
            stepOut();
            this.peekIndex = this.valueEndPosition;
        }
    }

    private void nextAtTopLevel() {
        if (this.completeValueBuffered) {
            this.buffer.seekTo(this.valueEndPosition);
            this.completeValueBuffered = false;
        }
        try {
            this.lookahead.fillInput();
            if (this.lookahead.moreDataRequired()) {
                this.valueType = null;
                this.valueTypeID = null;
                return;
            }
            this.completeValueBuffered = true;
            if (this.lookahead.getIvmIndex() > -1) {
                int ivmIndex = this.lookahead.getIvmIndex();
                ResizingPipedInputStream resizingPipedInputStream = this.buffer;
                this.peekIndex = ivmIndex + 1;
                this.majorVersion = resizingPipedInputStream.peek(ivmIndex);
                ResizingPipedInputStream resizingPipedInputStream2 = this.buffer;
                int i = this.peekIndex;
                this.peekIndex = i + 1;
                this.minorVersion = resizingPipedInputStream2.peek(i);
                ResizingPipedInputStream resizingPipedInputStream3 = this.buffer;
                int i2 = this.peekIndex;
                this.peekIndex = i2 + 1;
                if (resizingPipedInputStream3.peek(i2) != 234) {
                    throw new IonException("Invalid Ion version marker.");
                }
                requireSupportedIonVersion();
                resetSymbolTable();
                resetImports();
                this.lookahead.resetIvmIndex();
            } else if (this.peekIndex < 0) {
                throw new IonException("Binary Ion must start with an Ion version marker.");
            }
            List<IonReaderLookaheadBuffer.SymbolTableMarker> symbolTableMarkers = this.lookahead.getSymbolTableMarkers();
            if (!symbolTableMarkers.isEmpty()) {
                this.cachedReadOnlySymbolTable = null;
                Iterator<IonReaderLookaheadBuffer.SymbolTableMarker> it = symbolTableMarkers.iterator();
                while (it.hasNext()) {
                    readSymbolTable(it.next());
                }
                this.lookahead.resetSymbolTableMarkers();
            }
            this.peekIndex = this.lookahead.getValueStart();
            if (this.lookahead.getAnnotationSids().isEmpty()) {
                IonTypeID valueTid = this.lookahead.getValueTid();
                this.valueTypeID = valueTid;
                this.valueType = valueTid.type;
            } else {
                this.hasAnnotations = true;
                IonTypeID[] ionTypeIDArr = IonTypeID.TYPE_IDS;
                ResizingPipedInputStream resizingPipedInputStream4 = this.buffer;
                int i3 = this.peekIndex;
                this.peekIndex = i3 + 1;
                IonTypeID ionTypeID = ionTypeIDArr[resizingPipedInputStream4.peek(i3)];
                this.valueTypeID = ionTypeID;
                int varUInt = ionTypeID.length;
                if (ionTypeID.variableLength) {
                    varUInt = readVarUInt();
                }
                IonType ionType = this.valueTypeID.type;
                this.valueType = ionType;
                if (ionType == IonTypeID.ION_TYPE_ANNOTATION_WRAPPER) {
                    throw new IonException("Nested annotations are invalid.");
                }
                if (this.peekIndex + varUInt != this.lookahead.getValueEnd()) {
                    throw new IonException("Mismatched annotation wrapper length.");
                }
            }
            this.valueStartPosition = this.peekIndex;
            this.valueEndPosition = this.lookahead.getValueEnd();
            this.lookahead.resetNopPadIndex();
        } catch (Exception e) {
            throw new IonException(e);
        }
    }

    private IonTypeID readTypeId() {
        IonTypeID[] ionTypeIDArr = IonTypeID.TYPE_IDS;
        ResizingPipedInputStream resizingPipedInputStream = this.buffer;
        int i = this.peekIndex;
        this.peekIndex = i + 1;
        IonTypeID ionTypeID = ionTypeIDArr[resizingPipedInputStream.peek(i)];
        this.valueTypeID = ionTypeID;
        this.valueType = ionTypeID.type;
        return ionTypeID;
    }

    private void calculateEndPosition(IonTypeID ionTypeID) {
        if (ionTypeID.variableLength) {
            this.valueEndPosition = readVarUInt() + this.peekIndex;
        } else {
            this.valueEndPosition = ionTypeID.length + this.peekIndex;
        }
    }

    private void endContainer() {
        this.valueType = null;
        this.valueTypeID = null;
        this.annotationStartPosition = -1;
        this.annotationsLength = -1;
        this.hasAnnotations = false;
    }

    private void nextBelowTopLevel() {
        int i = this.peekIndex;
        int i2 = this.valueEndPosition;
        if (i < i2) {
            this.peekIndex = i2;
        }
        if (this.peekIndex < this.containerStack.peek().endPosition) {
            if (this.containerStack.peek().type == IonType.STRUCT) {
                this.fieldNameSid = readVarUInt();
            }
            IonTypeID typeId = readTypeId();
            while (typeId.isNopPad) {
                calculateEndPosition(typeId);
                int i3 = this.valueEndPosition;
                this.peekIndex = i3;
                if (i3 < this.containerStack.peek().endPosition) {
                    if (this.containerStack.peek().type == IonType.STRUCT) {
                        this.fieldNameSid = readVarUInt();
                    }
                    typeId = readTypeId();
                } else {
                    endContainer();
                    return;
                }
            }
            calculateEndPosition(typeId);
            IonType ionType = this.valueType;
            IonType ionType2 = IonTypeID.ION_TYPE_ANNOTATION_WRAPPER;
            if (ionType == ionType2) {
                this.hasAnnotations = true;
                this.annotationSids.clear();
                int varUInt = readVarUInt();
                this.annotationsLength = varUInt;
                int i4 = this.peekIndex;
                this.annotationStartPosition = i4;
                this.peekIndex = i4 + varUInt;
                IonTypeID typeId2 = readTypeId();
                if (typeId2.isNopPad) {
                    throw new IonException("Invalid annotation wrapper: NOP pad may not occur inside an annotation wrapper.");
                }
                if (this.valueType == ionType2) {
                    throw new IonException("Nested annotations are invalid.");
                }
                long j = this.valueEndPosition;
                calculateEndPosition(typeId2);
                if (j != this.valueEndPosition) {
                    throw new IonException("Invalid annotation wrapper: end of the wrapper did not match end of the value.");
                }
            } else {
                this.annotationStartPosition = -1;
                this.annotationsLength = -1;
                this.hasAnnotations = false;
                if (this.valueEndPosition > this.containerStack.peek().endPosition) {
                    throw new IonException("Value overflowed its container.");
                }
            }
            if (!this.valueTypeID.isValid) {
                throw new IonException("Invalid type ID.");
            }
            this.valueStartPosition = this.peekIndex;
            return;
        }
        endContainer();
    }

    @Override // com.amazon.ion.IonReader
    public IonType next() {
        this.fieldNameSid = -1;
        this.lobBytesRead = 0;
        this.valueStartPosition = -1;
        this.hasAnnotations = false;
        if (this.containerStack.isEmpty()) {
            nextAtTopLevel();
        } else {
            nextBelowTopLevel();
        }
        IonType ionType = this.valueType;
        if (ionType == IonType.STRUCT && this.valueTypeID.lowerNibble == 1 && this.valueStartPosition == this.valueEndPosition) {
            throw new IonException("Ordered struct must not be empty.");
        }
        return ionType;
    }

    @Override // com.amazon.ion.IonReader
    public void stepIn() {
        if (!IonType.isContainer(this.valueType)) {
            throw new IonException("Must be positioned on a container to step in.");
        }
        ContainerInfo containerInfoPush = this.containerStack.push();
        containerInfoPush.type = this.valueType;
        containerInfoPush.endPosition = this.valueEndPosition;
        this.valueType = null;
        this.valueTypeID = null;
        this.valueEndPosition = -1;
        this.fieldNameSid = -1;
        this.valueStartPosition = -1;
    }

    @Override // com.amazon.ion.IonReader
    public void stepOut() {
        if (!this.containerStack.isEmpty()) {
            this.valueEndPosition = this.containerStack.pop().endPosition;
            this.valueType = null;
            this.valueTypeID = null;
            this.fieldNameSid = -1;
            this.valueStartPosition = -1;
            return;
        }
        throw new IllegalStateException("Cannot step out at top level.");
    }

    @Override // com.amazon.ion.IonReader
    public int getDepth() {
        return this.containerStack.size();
    }

    @Override // com.amazon.ion.IonReader
    public SymbolTable getSymbolTable() {
        if (this.cachedReadOnlySymbolTable == null) {
            if (this.symbols.size() == SYSTEM_SYMBOLS_1_0_SIZE) {
                this.cachedReadOnlySymbolTable = SharedSymbolTable.getSystemSymbolTable(this.majorVersion);
            } else {
                this.cachedReadOnlySymbolTable = new LocalSymbolTableSnapshot();
            }
        }
        return this.cachedReadOnlySymbolTable;
    }

    @Override // com.amazon.ion.impl._Private_ReaderWriter
    public SymbolTable pop_passed_symbol_table() {
        SymbolTable symbolTable = getSymbolTable();
        if (symbolTable == this.symbolTableLastTransferred) {
            return null;
        }
        this.symbolTableLastTransferred = symbolTable;
        return symbolTable;
    }

    @Override // com.amazon.ion.IonReader
    public IonType getType() {
        return this.valueType;
    }

    @Override // com.amazon.ion.IonReader
    public IntegerSize getIntegerSize() {
        if (this.valueType != IonType.INT || isNullValue()) {
            return null;
        }
        IonTypeID ionTypeID = this.valueTypeID;
        byte b = ionTypeID.length;
        if (b < 4) {
            return IntegerSize.INT;
        }
        if (b < 8) {
            return IntegerSize.LONG;
        }
        if (b == 8) {
            if (ionTypeID.isNegativeInt) {
                int iPeek = this.buffer.peek(this.valueStartPosition);
                if (iPeek < 128) {
                    return IntegerSize.LONG;
                }
                if (iPeek > 128) {
                    return IntegerSize.BIG_INTEGER;
                }
                int i = this.valueStartPosition;
                do {
                    i++;
                    if (i < this.valueEndPosition) {
                    }
                } while (this.buffer.peek(i) == 0);
                return IntegerSize.BIG_INTEGER;
            }
            if (this.buffer.peek(this.valueStartPosition) > 127) {
                return IntegerSize.BIG_INTEGER;
            }
            return IntegerSize.LONG;
        }
        return IntegerSize.BIG_INTEGER;
    }

    private void requireType(IonType ionType) {
        if (ionType != this.valueType) {
            throw new IllegalStateException(String.format("Invalid type. Required %s but found %s.", ionType, this.valueType));
        }
    }

    private int readVarUInt() {
        int iPeek = 0;
        int i = 0;
        while ((iPeek & 128) == 0) {
            ResizingPipedInputStream resizingPipedInputStream = this.buffer;
            int i2 = this.peekIndex;
            this.peekIndex = i2 + 1;
            iPeek = resizingPipedInputStream.peek(i2);
            i = (i << 7) | (iPeek & WorkQueueKt.MASK);
        }
        return i;
    }

    private long readUInt(int i, int i2) {
        long jPeek = 0;
        while (i < i2) {
            jPeek = (jPeek << 8) | ((long) this.buffer.peek(i));
            i++;
        }
        return jPeek;
    }

    private long readUInt() {
        return readUInt(this.valueStartPosition, this.valueEndPosition);
    }

    private int readVarInt(int i) {
        int i2 = (i & 64) == 0 ? 1 : -1;
        int i3 = i & 63;
        while ((i & 128) == 0) {
            ResizingPipedInputStream resizingPipedInputStream = this.buffer;
            int i4 = this.peekIndex;
            this.peekIndex = i4 + 1;
            i = resizingPipedInputStream.peek(i4);
            i3 = (i3 << 7) | (i & WorkQueueKt.MASK);
        }
        return i3 * i2;
    }

    private int readVarInt() {
        ResizingPipedInputStream resizingPipedInputStream = this.buffer;
        int i = this.peekIndex;
        this.peekIndex = i + 1;
        return readVarInt(resizingPipedInputStream.peek(i));
    }

    private byte[] copyBytesToScratch(int i, int i2) {
        byte[][] bArr = SCRATCH_FOR_SIZE;
        byte[] bArr2 = i2 < bArr.length ? bArr[i2] : null;
        if (bArr2 == null) {
            bArr2 = new byte[i2];
        }
        this.buffer.copyBytes(i, bArr2, 0, bArr2.length);
        return bArr2;
    }

    private BigInteger readUIntAsBigInteger(boolean z) {
        int i = this.valueEndPosition;
        int i2 = this.valueStartPosition;
        return new BigInteger(z ? -1 : 1, copyBytesToScratch(i2, i - i2));
    }

    private int getAndClearSignBit(byte[] bArr) {
        byte b = bArr[0];
        boolean z = (b & ByteCompanionObject.MIN_VALUE) != 0;
        int i = z ? -1 : 1;
        if (z) {
            bArr[0] = (byte) (b & 127);
        }
        return i;
    }

    private BigInteger readIntAsBigInteger(int i) {
        int i2 = this.peekIndex;
        int i3 = i - i2;
        if (i3 > 0) {
            byte[] bArrCopyBytesToScratch = copyBytesToScratch(i2, i3);
            return new BigInteger(getAndClearSignBit(bArrCopyBytesToScratch), bArrCopyBytesToScratch);
        }
        return BigInteger.ZERO;
    }

    @Override // com.amazon.ion.IonReader
    public long longValue() {
        IonType ionType = this.valueType;
        if (ionType == IonType.INT) {
            if (this.valueTypeID.length == 0) {
                return 0L;
            }
            long uInt = readUInt();
            if (!this.valueTypeID.isNegativeInt) {
                return uInt;
            }
            if (uInt != 0) {
                return uInt * (-1);
            }
            throw new IonException("Int zero may not be negative.");
        }
        if (ionType == IonType.FLOAT) {
            this.scalarConverter.addValue(doubleValue());
            this.scalarConverter.setAuthoritativeType(7);
            _Private_ScalarConversions.ValueVariant valueVariant = this.scalarConverter;
            valueVariant.cast(valueVariant.get_conversion_fnid(4));
            long j = this.scalarConverter.getLong();
            this.scalarConverter.clear();
            return j;
        }
        if (ionType == IonType.DECIMAL) {
            this.scalarConverter.addValue(decimalValue());
            this.scalarConverter.setAuthoritativeType(6);
            _Private_ScalarConversions.ValueVariant valueVariant2 = this.scalarConverter;
            valueVariant2.cast(valueVariant2.get_conversion_fnid(4));
            long j2 = this.scalarConverter.getLong();
            this.scalarConverter.clear();
            return j2;
        }
        throw new IllegalStateException("longValue() may only be called on values of type int, float, or decimal.");
    }

    @Override // com.amazon.ion.IonReader
    public BigInteger bigIntegerValue() {
        IonType ionType = this.valueType;
        if (ionType == IonType.INT) {
            if (isNullValue()) {
                return null;
            }
            IonTypeID ionTypeID = this.valueTypeID;
            if (ionTypeID.length == 0) {
                return BigInteger.ZERO;
            }
            BigInteger uIntAsBigInteger = readUIntAsBigInteger(ionTypeID.isNegativeInt);
            if (this.valueTypeID.isNegativeInt && uIntAsBigInteger.signum() == 0) {
                throw new IonException("Int zero may not be negative.");
            }
            return uIntAsBigInteger;
        }
        if (ionType == IonType.FLOAT) {
            if (isNullValue()) {
                return null;
            }
            this.scalarConverter.addValue(doubleValue());
            this.scalarConverter.setAuthoritativeType(7);
            _Private_ScalarConversions.ValueVariant valueVariant = this.scalarConverter;
            valueVariant.cast(valueVariant.get_conversion_fnid(5));
            BigInteger bigInteger = this.scalarConverter.getBigInteger();
            this.scalarConverter.clear();
            return bigInteger;
        }
        if (ionType == IonType.DECIMAL) {
            if (isNullValue()) {
                return null;
            }
            this.scalarConverter.addValue(decimalValue());
            this.scalarConverter.setAuthoritativeType(6);
            _Private_ScalarConversions.ValueVariant valueVariant2 = this.scalarConverter;
            valueVariant2.cast(valueVariant2.get_conversion_fnid(5));
            BigInteger bigInteger2 = this.scalarConverter.getBigInteger();
            this.scalarConverter.clear();
            return bigInteger2;
        }
        throw new IllegalStateException("longValue() may only be called on values of type int, float, or decimal.");
    }

    @Override // com.amazon.ion.IonReader
    public int intValue() {
        return (int) longValue();
    }

    @Override // com.amazon.ion.IonReader
    public double doubleValue() {
        IonType ionType = this.valueType;
        if (ionType == IonType.FLOAT) {
            int i = this.valueEndPosition;
            int i2 = this.valueStartPosition;
            int i3 = i - i2;
            if (i3 == 0) {
                return 0.0d;
            }
            ByteBuffer byteBuffer = this.buffer.getByteBuffer(i2, i);
            if (i3 == 4) {
                return byteBuffer.getFloat();
            }
            return byteBuffer.getDouble();
        }
        if (ionType == IonType.DECIMAL) {
            this.scalarConverter.addValue(decimalValue());
            this.scalarConverter.setAuthoritativeType(6);
            _Private_ScalarConversions.ValueVariant valueVariant = this.scalarConverter;
            valueVariant.cast(valueVariant.get_conversion_fnid(7));
            double d = this.scalarConverter.getDouble();
            this.scalarConverter.clear();
            return d;
        }
        throw new IllegalStateException("doubleValue() may only be called on values of type float or decimal.");
    }

    private String readString(int i, int i2) {
        return this.utf8Decoder.decode(this.buffer.getByteBuffer(i, i2), i2 - i);
    }

    @Override // com.amazon.ion.IonReader
    public String stringValue() {
        IonType ionType = this.valueType;
        if (ionType == IonType.STRING) {
            if (isNullValue()) {
                return null;
            }
            return readString(this.valueStartPosition, this.valueEndPosition);
        }
        if (ionType == IonType.SYMBOL) {
            if (isNullValue()) {
                return null;
            }
            int uInt = (int) readUInt();
            String symbol = getSymbol(uInt);
            if (symbol != null) {
                return symbol;
            }
            throw new UnknownSymbolException(uInt);
        }
        throw new IllegalStateException("Invalid type requested.");
    }

    @Override // com.amazon.ion.IonReader
    public SymbolToken symbolValue() {
        requireType(IonType.SYMBOL);
        if (isNullValue()) {
            return null;
        }
        return getSymbolToken((int) readUInt());
    }

    public int byteSize() {
        if (!IonType.isLob(this.valueType) && !isNullValue()) {
            throw new IonException("Reader must be positioned on a blob or clob.");
        }
        return this.valueEndPosition - this.valueStartPosition;
    }

    @Override // com.amazon.ion.IonReader
    public byte[] newBytes() {
        int iByteSize = byteSize();
        byte[] bArr = new byte[iByteSize];
        this.buffer.copyBytes(this.valueStartPosition, bArr, 0, iByteSize);
        return bArr;
    }

    private BigDecimal readBigDecimal() {
        int i = this.valueEndPosition - this.peekIndex;
        if (i == 0) {
            return BigDecimal.ZERO;
        }
        int i2 = -readVarInt();
        if (i < 8) {
            long jPeek = 0;
            int i3 = this.peekIndex;
            if (i3 < this.valueEndPosition) {
                ResizingPipedInputStream resizingPipedInputStream = this.buffer;
                this.peekIndex = i3 + 1;
                int iPeek = resizingPipedInputStream.peek(i3);
                i = (iPeek & 128) != 0 ? -1 : 1;
                jPeek = iPeek & WorkQueueKt.MASK;
            }
            while (true) {
                int i4 = this.peekIndex;
                if (i4 < this.valueEndPosition) {
                    ResizingPipedInputStream resizingPipedInputStream2 = this.buffer;
                    this.peekIndex = i4 + 1;
                    jPeek = (jPeek << 8) | ((long) resizingPipedInputStream2.peek(i4));
                } else {
                    return BigDecimal.valueOf(jPeek * ((long) i), i2);
                }
            }
        } else {
            return new BigDecimal(readIntAsBigInteger(this.valueEndPosition), i2);
        }
    }

    private Decimal readDecimal() {
        BigInteger bigInteger;
        if (this.valueEndPosition - this.peekIndex == 0) {
            return Decimal.ZERO;
        }
        int i = -readVarInt();
        int i2 = this.valueEndPosition;
        int i3 = this.peekIndex;
        int i4 = i2 - i3;
        if (i4 > 0) {
            byte[] bArrCopyBytesToScratch = copyBytesToScratch(i3, i4);
            int andClearSignBit = getAndClearSignBit(bArrCopyBytesToScratch);
            bigInteger = new BigInteger(andClearSignBit, bArrCopyBytesToScratch);
            if (bigInteger.signum() == 0 && andClearSignBit < 0) {
                return Decimal.negativeZero(i);
            }
        } else {
            bigInteger = BigInteger.ZERO;
        }
        return Decimal.valueOf(bigInteger, i);
    }

    @Override // com.amazon.ion.IonReader
    public Decimal decimalValue() {
        requireType(IonType.DECIMAL);
        if (isNullValue()) {
            return null;
        }
        this.peekIndex = this.valueStartPosition;
        return readDecimal();
    }

    @Override // com.amazon.ion.IonReader
    public Timestamp timestampValue() {
        Timestamp.Precision precision;
        BigDecimal bigDecimal;
        int i;
        int i2;
        int i3;
        int i4;
        requireType(IonType.TIMESTAMP);
        BigDecimal bigDecimal2 = null;
        if (isNullValue()) {
            return null;
        }
        int i5 = this.valueStartPosition;
        ResizingPipedInputStream resizingPipedInputStream = this.buffer;
        this.peekIndex = i5 + 1;
        int iPeek = resizingPipedInputStream.peek(i5);
        Integer numValueOf = iPeek != 192 ? Integer.valueOf(readVarInt(iPeek)) : null;
        int varUInt = readVarUInt();
        Timestamp.Precision precision2 = Timestamp.Precision.YEAR;
        int varUInt2 = 0;
        try {
            if (this.peekIndex < this.valueEndPosition) {
                int varUInt3 = readVarUInt();
                precision = Timestamp.Precision.MONTH;
                if (this.peekIndex < this.valueEndPosition) {
                    int varUInt4 = readVarUInt();
                    Timestamp.Precision precision3 = Timestamp.Precision.DAY;
                    if (this.peekIndex < this.valueEndPosition) {
                        int varUInt5 = readVarUInt();
                        if (this.peekIndex >= this.valueEndPosition) {
                            throw new IonException("Timestamps may not specify hour without specifying minute.");
                        }
                        int varUInt6 = readVarUInt();
                        Timestamp.Precision precision4 = Timestamp.Precision.MINUTE;
                        if (this.peekIndex < this.valueEndPosition) {
                            varUInt2 = readVarUInt();
                            precision4 = Timestamp.Precision.SECOND;
                            if (this.peekIndex < this.valueEndPosition) {
                                bigDecimal2 = readBigDecimal();
                                if (bigDecimal2.signum() < 0 || bigDecimal2.compareTo(BigDecimal.ONE) >= 0) {
                                    throw new IonException("The fractional seconds value in a timestamp must be greaterthan or equal to zero and less than one.");
                                }
                            }
                        }
                        bigDecimal = bigDecimal2;
                        i4 = varUInt2;
                        varUInt2 = varUInt4;
                        precision = precision4;
                        i3 = varUInt6;
                        i2 = varUInt5;
                    } else {
                        bigDecimal = null;
                        i2 = 0;
                        i3 = 0;
                        i4 = 0;
                        varUInt2 = varUInt4;
                        precision = precision3;
                    }
                    i = varUInt3;
                    return Timestamp.createFromUtcFields(precision, varUInt, i, varUInt2, i2, i3, i4, bigDecimal, numValueOf);
                }
                i = varUInt3;
                bigDecimal = null;
                i2 = 0;
            } else {
                precision = precision2;
                bigDecimal = null;
                i = 0;
                i2 = 0;
            }
            return Timestamp.createFromUtcFields(precision, varUInt, i, varUInt2, i2, i3, i4, bigDecimal, numValueOf);
        } catch (IllegalArgumentException e) {
            throw new IonException("Illegal timestamp encoding. ", e);
        }
        i3 = i2;
        i4 = i3;
    }

    private List<Integer> getAnnotationSids() {
        if (this.containerStack.isEmpty()) {
            return this.lookahead.getAnnotationSids();
        }
        if (this.annotationSids.isEmpty()) {
            int i = this.peekIndex;
            int i2 = this.annotationStartPosition;
            this.peekIndex = i2;
            long j = i2 + this.annotationsLength;
            while (this.peekIndex < j) {
                this.annotationSids.add(Integer.valueOf(readVarUInt()));
            }
            this.peekIndex = i;
        }
        return this.annotationSids;
    }

    @Override // com.amazon.ion.IonReader
    public SymbolToken[] getTypeAnnotationSymbols() {
        if (this.hasAnnotations) {
            List<Integer> annotationSids = getAnnotationSids();
            int size = annotationSids.size();
            SymbolToken[] symbolTokenArr = new SymbolToken[size];
            for (int i = 0; i < size; i++) {
                symbolTokenArr[i] = getSymbolToken(annotationSids.get(i).intValue());
            }
            return symbolTokenArr;
        }
        return SymbolToken.EMPTY_ARRAY;
    }

    @Override // com.amazon.ion.IonReader
    public int getFieldId() {
        return this.fieldNameSid;
    }

    @Override // com.amazon.ion.IonReader
    public String getFieldName() {
        int i = this.fieldNameSid;
        if (i < 0) {
            return null;
        }
        String symbol = getSymbol(i);
        if (symbol != null) {
            return symbol;
        }
        throw new UnknownSymbolException(this.fieldNameSid);
    }

    @Override // com.amazon.ion.IonReader
    public SymbolToken getFieldNameSymbol() {
        int i = this.fieldNameSid;
        if (i < 0) {
            return null;
        }
        return getSymbolToken(i);
    }

    @Override // com.amazon.ion.IonReader
    public boolean isNullValue() {
        IonTypeID ionTypeID = this.valueTypeID;
        return ionTypeID != null && ionTypeID.isNull;
    }

    @Override // com.amazon.ion.IonReader
    public boolean booleanValue() {
        requireType(IonType.BOOL);
        return this.valueTypeID.lowerNibble == 1;
    }

    public void requireCompleteValue() {
        if (this.lookahead.isSkippingCurrentValue()) {
            throw new IonException("Unexpected EOF.");
        }
        if (this.lookahead.available() <= 0 || !this.lookahead.moreDataRequired()) {
            return;
        }
        if (this.lookahead.getIvmIndex() < 0 || this.lookahead.available() != _Private_IonConstants.BINARY_VERSION_MARKER_SIZE) {
            throw new IonException("Unexpected EOF.");
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        requireCompleteValue();
        this.inputStream.close();
        this.utf8Decoder.close();
    }
}
