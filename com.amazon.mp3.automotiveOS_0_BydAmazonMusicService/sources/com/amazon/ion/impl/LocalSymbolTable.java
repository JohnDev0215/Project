package com.amazon.ion.impl;

import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonException;
import com.amazon.ion.IonReader;
import com.amazon.ion.IonType;
import com.amazon.ion.IonWriter;
import com.amazon.ion.ReadOnlyValueException;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.util.IonTextUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes3.dex */
class LocalSymbolTable implements SymbolTable {
    static final Factory DEFAULT_LST_FACTORY = new Factory();
    private boolean isReadOnly;
    final int myFirstLocalSid;
    private final LocalSymbolTableImports myImportsList;
    String[] mySymbolNames;
    int mySymbolsCount;
    private final Map<String, Integer> mySymbolsMap;

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

    static class Factory implements _Private_LocalSymbolTableFactory {
        private Factory() {
        }

        @Override // com.amazon.ion.impl._Private_LocalSymbolTableFactory
        public SymbolTable newLocalSymtab(IonCatalog ionCatalog, IonReader ionReader, boolean z) {
            ArrayList arrayList = new ArrayList();
            SymbolTable symbolTable = ionReader.getSymbolTable();
            LocalSymbolTableImports localSymbolTable = LocalSymbolTable.readLocalSymbolTable(ionReader, ionCatalog, z, arrayList, symbolTable);
            return localSymbolTable == null ? symbolTable : new LocalSymbolTable(localSymbolTable, arrayList);
        }

        @Override // com.amazon.ion.impl._Private_LocalSymbolTableFactory
        public SymbolTable newLocalSymtab(SymbolTable symbolTable, SymbolTable... symbolTableArr) {
            return new LocalSymbolTable(new LocalSymbolTableImports(symbolTable, symbolTableArr), null);
        }
    }

    private void buildSymbolsMap() {
        int i = this.myFirstLocalSid;
        int i2 = 0;
        while (true) {
            String[] strArr = this.mySymbolNames;
            if (i2 >= strArr.length) {
                return;
            }
            String str = strArr[i2];
            if (str != null) {
                putToMapIfNotThere(this.mySymbolsMap, str, i);
            }
            i2++;
            i++;
        }
    }

    protected LocalSymbolTable(LocalSymbolTableImports localSymbolTableImports, List<String> list) {
        if (list == null || list.isEmpty()) {
            this.mySymbolsCount = 0;
            this.mySymbolNames = _Private_Utils.EMPTY_STRING_ARRAY;
        } else {
            int size = list.size();
            this.mySymbolsCount = size;
            this.mySymbolNames = (String[]) list.toArray(new String[size]);
        }
        this.myImportsList = localSymbolTableImports;
        this.myFirstLocalSid = localSymbolTableImports.getMaxId() + 1;
        this.mySymbolsMap = new HashMap((int) Math.ceil(((double) this.mySymbolsCount) / 0.75d));
        buildSymbolsMap();
    }

    protected static LocalSymbolTableImports readLocalSymbolTable(IonReader ionReader, IonCatalog ionCatalog, boolean z, List<String> list, SymbolTable symbolTable) {
        if (!z) {
            ionReader.next();
        }
        ionReader.stepIn();
        ArrayList arrayList = new ArrayList();
        arrayList.add(ionReader.getSymbolTable().getSystemSymbolTable());
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        while (true) {
            IonType next = ionReader.next();
            if (next != null) {
                if (!ionReader.isNullValue()) {
                    int sid = ionReader.getFieldNameSymbol().getSid();
                    if (sid == -1) {
                        sid = _Private_Utils.getSidForSymbolTableField(ionReader.getFieldName());
                    }
                    if (sid != 6) {
                        if (sid != 7) {
                            continue;
                        } else {
                            if (z4) {
                                throw new IonException("Multiple symbol fields found within a single local symbol table.");
                            }
                            if (next == IonType.LIST) {
                                ionReader.stepIn();
                                while (true) {
                                    IonType next2 = ionReader.next();
                                    if (next2 == null) {
                                        break;
                                    }
                                    list.add(next2 == IonType.STRING ? ionReader.stringValue() : null);
                                }
                                ionReader.stepOut();
                            }
                            z4 = true;
                        }
                    } else {
                        if (z3) {
                            throw new IonException("Multiple imports fields found within a single local symbol table.");
                        }
                        if (next == IonType.LIST) {
                            prepImportsList(arrayList, ionReader, ionCatalog);
                        } else if (next == IonType.SYMBOL && "$ion_symbol_table".equals(ionReader.stringValue())) {
                            z2 = true;
                            z3 = true;
                        }
                        z3 = true;
                    }
                }
            } else {
                ionReader.stepOut();
                if (z2 && symbolTable.isLocalTable()) {
                    LocalSymbolTable localSymbolTable = (LocalSymbolTable) symbolTable;
                    Iterator<String> it = list.iterator();
                    while (it.hasNext()) {
                        localSymbolTable.putSymbol(it.next());
                    }
                    return null;
                }
                return new LocalSymbolTableImports(arrayList);
            }
        }
    }

    @Override // com.amazon.ion.SymbolTable
    public int getImportedMaxId() {
        return this.myImportsList.getMaxId();
    }

    @Override // com.amazon.ion.SymbolTable
    public synchronized int getMaxId() {
        return this.mySymbolsCount + this.myImportsList.getMaxId();
    }

    @Override // com.amazon.ion.SymbolTable
    public String getIonVersionId() {
        return this.myImportsList.getSystemSymbolTable().getIonVersionId();
    }

    @Override // com.amazon.ion.SymbolTable
    public synchronized Iterator<String> iterateDeclaredSymbolNames() {
        return new SymbolIterator(this.mySymbolNames, this.mySymbolsCount);
    }

    @Override // com.amazon.ion.SymbolTable
    public String findKnownSymbol(int i) {
        String[] strArr;
        if (i < 0) {
            throw new IllegalArgumentException("symbol IDs must be >= 0");
        }
        int i2 = this.myFirstLocalSid;
        if (i < i2) {
            return this.myImportsList.findKnownSymbol(i);
        }
        int i3 = i - i2;
        synchronized (this) {
            strArr = this.mySymbolNames;
        }
        if (i3 < strArr.length) {
            return strArr[i3];
        }
        return null;
    }

    @Override // com.amazon.ion.SymbolTable
    public int findSymbol(String str) {
        int iFindSymbol = this.myImportsList.findSymbol(str);
        return iFindSymbol == -1 ? findLocalSymbol(str) : iFindSymbol;
    }

    private int findLocalSymbol(String str) {
        Integer num;
        synchronized (this) {
            num = this.mySymbolsMap.get(str);
        }
        if (num != null) {
            return num.intValue();
        }
        return -1;
    }

    @Override // com.amazon.ion.SymbolTable
    public synchronized SymbolToken intern(String str) {
        SymbolToken symbolTokenFind;
        symbolTokenFind = find(str);
        if (symbolTokenFind == null) {
            validateSymbol(str);
            symbolTokenFind = new SymbolTokenImpl(str, putSymbol(str));
        }
        return symbolTokenFind;
    }

    @Override // com.amazon.ion.SymbolTable
    public SymbolToken find(String str) {
        Integer num;
        String[] strArr;
        str.getClass();
        SymbolToken symbolTokenFind = this.myImportsList.find(str);
        if (symbolTokenFind != null) {
            return symbolTokenFind;
        }
        synchronized (this) {
            num = this.mySymbolsMap.get(str);
            strArr = this.mySymbolNames;
        }
        return num != null ? new SymbolTokenImpl(strArr[num.intValue() - this.myFirstLocalSid], num.intValue()) : symbolTokenFind;
    }

    private static final void validateSymbol(String str) {
        if (str == null) {
            throw new IllegalArgumentException("symbols must not be null");
        }
        int i = 0;
        while (i < str.length()) {
            char cCharAt = str.charAt(i);
            if (cCharAt >= 55296 && cCharAt <= 57343) {
                if (cCharAt >= 56320) {
                    throw new IllegalArgumentException("unpaired trailing surrogate in symbol name at position " + i);
                }
                i++;
                if (i == str.length()) {
                    throw new IllegalArgumentException("unmatched leading surrogate in symbol name at position " + i);
                }
                char cCharAt2 = str.charAt(i);
                if (cCharAt2 < 56320 || cCharAt2 > 57343) {
                    throw new IllegalArgumentException("unmatched leading surrogate in symbol name at position " + i);
                }
            }
            i++;
        }
    }

    int putSymbol(String str) {
        if (this.isReadOnly) {
            throw new ReadOnlyValueException(SymbolTable.class);
        }
        int i = this.mySymbolsCount;
        String[] strArr = this.mySymbolNames;
        if (i == strArr.length) {
            int i2 = i * 2;
            if (i2 < 16) {
                i2 = 16;
            }
            String[] strArr2 = new String[i2];
            System.arraycopy(strArr, 0, strArr2, 0, i);
            this.mySymbolNames = strArr2;
        }
        int i3 = -1;
        if (str != null) {
            i3 = this.mySymbolsCount + this.myFirstLocalSid;
            putToMapIfNotThere(this.mySymbolsMap, str, i3);
        }
        String[] strArr3 = this.mySymbolNames;
        int i4 = this.mySymbolsCount;
        strArr3[i4] = str;
        this.mySymbolsCount = i4 + 1;
        return i3;
    }

    private static void putToMapIfNotThere(Map<String, Integer> map, String str, int i) {
        Integer numPut = map.put(str, Integer.valueOf(i));
        if (numPut != null) {
            map.put(str, numPut);
        }
    }

    @Override // com.amazon.ion.SymbolTable
    public SymbolTable getSystemSymbolTable() {
        return this.myImportsList.getSystemSymbolTable();
    }

    @Override // com.amazon.ion.SymbolTable
    public SymbolTable[] getImportedTables() {
        return this.myImportsList.getImportedTables();
    }

    SymbolTable[] getImportedTablesNoCopy() {
        return this.myImportsList.getImportedTablesNoCopy();
    }

    @Override // com.amazon.ion.SymbolTable
    public void writeTo(IonWriter ionWriter) throws IOException {
        ionWriter.writeValues(new SymbolTableReader(this));
    }

    private static void prepImportsList(List<SymbolTable> list, IonReader ionReader, IonCatalog ionCatalog) {
        SymbolTable oneImport;
        ionReader.stepIn();
        while (true) {
            IonType next = ionReader.next();
            if (next != null) {
                if (!ionReader.isNullValue() && next == IonType.STRUCT && (oneImport = readOneImport(ionReader, ionCatalog)) != null) {
                    list.add(oneImport);
                }
            } else {
                ionReader.stepOut();
                return;
            }
        }
    }

    private static SymbolTable readOneImport(IonReader ionReader, IonCatalog ionCatalog) {
        ionReader.stepIn();
        int iIntValue = -1;
        int maxId = -1;
        String strStringValue = null;
        while (true) {
            IonType next = ionReader.next();
            if (next == null) {
                break;
            }
            if (!ionReader.isNullValue()) {
                int sid = ionReader.getFieldNameSymbol().getSid();
                if (sid == -1) {
                    sid = _Private_Utils.getSidForSymbolTableField(ionReader.getFieldName());
                }
                if (sid != 4) {
                    if (sid == 5) {
                        if (next == IonType.INT) {
                            iIntValue = ionReader.intValue();
                        }
                    } else if (sid == 8 && next == IonType.INT) {
                        maxId = ionReader.intValue();
                    }
                } else if (next == IonType.STRING) {
                    strStringValue = ionReader.stringValue();
                }
            }
        }
        ionReader.stepOut();
        if (strStringValue == null || strStringValue.length() == 0 || strStringValue.equals("$ion")) {
            return null;
        }
        if (iIntValue < 1) {
            iIntValue = 1;
        }
        SymbolTable table = ionCatalog != null ? ionCatalog.getTable(strStringValue, iIntValue) : null;
        if (maxId < 0) {
            if (table == null || iIntValue != table.getVersion()) {
                String str = "Import of shared table " + IonTextUtils.printString(strStringValue) + " lacks a valid max_id field, but an exact match was not found in the catalog";
                if (table != null) {
                    str = str + " (found version " + table.getVersion() + ")";
                }
                throw new IonException(str);
            }
            maxId = table.getMaxId();
        }
        if (table == null) {
            return new SubstituteSymbolTable(strStringValue, iIntValue, maxId);
        }
        return (table.getVersion() == iIntValue && table.getMaxId() == maxId) ? table : new SubstituteSymbolTable(table, iIntValue, maxId);
    }

    public String toString() {
        return "(LocalSymbolTable max_id:" + getMaxId() + ')';
    }

    private static final class SymbolIterator implements Iterator<String> {
        private int _idx = 0;
        private final String[] mySymbolNames;
        private final int mySymbolsCount;

        SymbolIterator(String[] strArr, int i) {
            this.mySymbolNames = strArr;
            this.mySymbolsCount = i;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this._idx < this.mySymbolsCount;
        }

        @Override // java.util.Iterator
        public String next() {
            int i = this._idx;
            if (i < this.mySymbolsCount) {
                String[] strArr = this.mySymbolNames;
                this._idx = i + 1;
                return strArr[i];
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    boolean symtabExtends(SymbolTable symbolTable) {
        LocalSymbolTable localSymbolTable = (LocalSymbolTable) symbolTable;
        if (getMaxId() < localSymbolTable.getMaxId() || !this.myImportsList.equalImports(localSymbolTable.myImportsList)) {
            return false;
        }
        int i = localSymbolTable.mySymbolsCount;
        if (i == 0) {
            return true;
        }
        if (this.mySymbolsCount < i) {
            return false;
        }
        String[] strArr = localSymbolTable.mySymbolNames;
        int i2 = i - 1;
        if (!_Private_Utils.safeEquals(this.mySymbolNames[i2], strArr[i2])) {
            return false;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (!_Private_Utils.safeEquals(this.mySymbolNames[i3], strArr[i3])) {
                return false;
            }
        }
        return true;
    }
}
