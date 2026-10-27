package com.amazon.ion.impl;

import com.amazon.ion.IonException;
import com.amazon.ion.IonWriter;
import com.amazon.ion.ReadOnlyValueException;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
final class SharedSymbolTable implements SymbolTable {
    private static final SymbolTable ION_1_0_SYSTEM_SYMTAB;
    private static final String[] SYSTEM_SYMBOLS = {"$ion", "$ion_1_0", "$ion_symbol_table", "name", "version", "imports", "symbols", "max_id", "$ion_shared_symbol_table"};
    private final String myName;
    private final String[] mySymbolNames;
    private final Map<String, Integer> mySymbolsMap;
    private final int myVersion;

    @Override // com.amazon.ion.SymbolTable
    public int getImportedMaxId() {
        return 0;
    }

    @Override // com.amazon.ion.SymbolTable
    public SymbolTable[] getImportedTables() {
        return null;
    }

    @Override // com.amazon.ion.SymbolTable
    public boolean isLocalTable() {
        return false;
    }

    @Override // com.amazon.ion.SymbolTable
    public boolean isSharedTable() {
        return true;
    }

    @Override // com.amazon.ion.SymbolTable
    public boolean isSubstitute() {
        return false;
    }

    static {
        HashMap map = new HashMap();
        int i = 0;
        while (true) {
            String[] strArr = SYSTEM_SYMBOLS;
            if (i < strArr.length) {
                String str = strArr[i];
                i++;
                map.put(str, Integer.valueOf(i));
            } else {
                ION_1_0_SYSTEM_SYMTAB = new SharedSymbolTable("$ion", 1, strArr, map);
                return;
            }
        }
    }

    private SharedSymbolTable(String str, int i, String[] strArr, Map<String, Integer> map) {
        this.myName = str;
        this.myVersion = i;
        this.mySymbolsMap = map;
        this.mySymbolNames = strArr;
    }

    static SymbolTable getSystemSymbolTable(int i) {
        if (i != 1) {
            throw new IllegalArgumentException("only Ion 1.0 system symbols are supported");
        }
        return ION_1_0_SYSTEM_SYMTAB;
    }

    @Override // com.amazon.ion.SymbolTable
    public String getName() {
        return this.myName;
    }

    @Override // com.amazon.ion.SymbolTable
    public int getVersion() {
        return this.myVersion;
    }

    @Override // com.amazon.ion.SymbolTable
    public boolean isSystemTable() {
        return "$ion".equals(this.myName);
    }

    @Override // com.amazon.ion.SymbolTable
    public SymbolTable getSystemSymbolTable() {
        if (isSystemTable()) {
            return this;
        }
        return null;
    }

    @Override // com.amazon.ion.SymbolTable
    public String getIonVersionId() {
        if (!isSystemTable()) {
            return null;
        }
        int version = getVersion();
        if (version == 1) {
            return "$ion_1_0";
        }
        throw new IonException("unrecognized system version encountered: " + version);
    }

    @Override // com.amazon.ion.SymbolTable
    public int getMaxId() {
        return this.mySymbolNames.length;
    }

    @Override // com.amazon.ion.SymbolTable
    public SymbolToken intern(String str) {
        SymbolToken symbolTokenFind = find(str);
        if (symbolTokenFind != null) {
            return symbolTokenFind;
        }
        throw new ReadOnlyValueException(SymbolTable.class);
    }

    @Override // com.amazon.ion.SymbolTable
    public SymbolToken find(String str) {
        str.getClass();
        Integer num = this.mySymbolsMap.get(str);
        if (num == null) {
            return null;
        }
        return new SymbolTokenImpl(this.mySymbolNames[num.intValue() - 1], num.intValue());
    }

    @Override // com.amazon.ion.SymbolTable
    public int findSymbol(String str) {
        Integer num = this.mySymbolsMap.get(str);
        if (num != null) {
            return num.intValue();
        }
        return -1;
    }

    @Override // com.amazon.ion.SymbolTable
    public String findKnownSymbol(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("symbol IDs must be >= 0");
        }
        int i2 = i - 1;
        if (i == 0) {
            return null;
        }
        String[] strArr = this.mySymbolNames;
        if (i2 < strArr.length) {
            return strArr[i2];
        }
        return null;
    }

    @Override // com.amazon.ion.SymbolTable
    public Iterator<String> iterateDeclaredSymbolNames() {
        return Collections.unmodifiableList(Arrays.asList(this.mySymbolNames)).iterator();
    }

    @Override // com.amazon.ion.SymbolTable
    public void writeTo(IonWriter ionWriter) throws IOException {
        ionWriter.writeValues(new SymbolTableReader(this));
    }
}
