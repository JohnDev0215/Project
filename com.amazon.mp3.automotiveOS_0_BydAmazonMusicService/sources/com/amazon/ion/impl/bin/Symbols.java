package com.amazon.ion.impl.bin;

import com.amazon.ion.IonException;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes3.dex */
class Symbols {
    private static SymbolTable SYSTEM_SYMBOL_TABLE;
    private static final List<SymbolToken> SYSTEM_TOKENS;
    private static final Map<String, SymbolToken> SYSTEM_TOKEN_MAP;

    private Symbols() {
    }

    public static SymbolToken symbol(final String str, final int i) {
        str.getClass();
        if (i <= 0) {
            throw new IllegalArgumentException("Symbol value must be positive: " + i);
        }
        return new SymbolToken() { // from class: com.amazon.ion.impl.bin.Symbols.1
            @Override // com.amazon.ion.SymbolToken
            public String getText() {
                return str;
            }

            @Override // com.amazon.ion.SymbolToken
            public int getSid() {
                return i;
            }

            public String toString() {
                return "(symbol '" + getText() + "' " + getSid() + ")";
            }
        };
    }

    public static Iterator<String> symbolNameIterator(final Iterator<SymbolToken> it) {
        return new Iterator<String>() { // from class: com.amazon.ion.impl.bin.Symbols.2
            @Override // java.util.Iterator
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override // java.util.Iterator
            public String next() {
                return ((SymbolToken) it.next()).getText();
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    static {
        String str = "$ion";
        int i = 1;
        List<SymbolToken> listUnmodifiableList = Collections.unmodifiableList(Arrays.asList(symbol("$ion", 1), symbol("$ion_1_0", 2), symbol("$ion_symbol_table", 3), symbol("name", 4), symbol("version", 5), symbol("imports", 6), symbol("symbols", 7), symbol("max_id", 8), symbol("$ion_shared_symbol_table", 9)));
        SYSTEM_TOKENS = listUnmodifiableList;
        HashMap map = new HashMap();
        for (SymbolToken symbolToken : listUnmodifiableList) {
            map.put(symbolToken.getText(), symbolToken);
        }
        SYSTEM_TOKEN_MAP = Collections.unmodifiableMap(map);
        SYSTEM_SYMBOL_TABLE = new AbstractSymbolTable(str, i) { // from class: com.amazon.ion.impl.bin.Symbols.3
            @Override // com.amazon.ion.impl.bin.AbstractSymbolTable, com.amazon.ion.SymbolTable
            public int getImportedMaxId() {
                return 0;
            }

            @Override // com.amazon.ion.SymbolTable
            public SymbolTable[] getImportedTables() {
                return null;
            }

            @Override // com.amazon.ion.impl.bin.AbstractSymbolTable, com.amazon.ion.SymbolTable
            public int getMaxId() {
                return 9;
            }

            @Override // com.amazon.ion.SymbolTable
            public SymbolTable getSystemSymbolTable() {
                return this;
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

            @Override // com.amazon.ion.SymbolTable
            public boolean isSystemTable() {
                return true;
            }

            @Override // com.amazon.ion.SymbolTable
            public SymbolToken intern(String str2) {
                SymbolToken symbolToken2 = (SymbolToken) Symbols.SYSTEM_TOKEN_MAP.get(str2);
                if (symbolToken2 != null) {
                    return symbolToken2;
                }
                throw new IonException("Cannot intern new symbol into system symbol table");
            }

            @Override // com.amazon.ion.SymbolTable
            public String findKnownSymbol(int i2) {
                if (i2 >= 1) {
                    if (i2 > 9) {
                        return null;
                    }
                    return ((SymbolToken) Symbols.SYSTEM_TOKENS.get(i2 - 1)).getText();
                }
                throw new IllegalArgumentException("SID cannot be less than 1: " + i2);
            }

            @Override // com.amazon.ion.SymbolTable
            public SymbolToken find(String str2) {
                return (SymbolToken) Symbols.SYSTEM_TOKEN_MAP.get(str2);
            }

            @Override // com.amazon.ion.SymbolTable
            public Iterator<String> iterateDeclaredSymbolNames() {
                return Symbols.symbolNameIterator(Symbols.SYSTEM_TOKENS.iterator());
            }
        };
    }

    public static SymbolToken systemSymbol(int i) {
        if (i < 1 || i > 9) {
            throw new IllegalArgumentException("No such system SID: " + i);
        }
        return SYSTEM_TOKENS.get(i - 1);
    }

    public static SymbolTable systemSymbolTable() {
        return SYSTEM_SYMBOL_TABLE;
    }

    public static Collection<SymbolToken> systemSymbols() {
        return SYSTEM_TOKENS;
    }

    public static SymbolTable unknownSharedSymbolTable(final String str, final int i, final int i2) {
        return new AbstractSymbolTable(str, i) { // from class: com.amazon.ion.impl.bin.Symbols.4
            @Override // com.amazon.ion.SymbolTable
            public SymbolToken find(String str2) {
                return null;
            }

            @Override // com.amazon.ion.SymbolTable
            public String findKnownSymbol(int i3) {
                return null;
            }

            @Override // com.amazon.ion.impl.bin.AbstractSymbolTable, com.amazon.ion.SymbolTable
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
                return true;
            }

            @Override // com.amazon.ion.SymbolTable
            public boolean isSystemTable() {
                return false;
            }

            @Override // com.amazon.ion.SymbolTable
            public Iterator<String> iterateDeclaredSymbolNames() {
                return new Iterator<String>() { // from class: com.amazon.ion.impl.bin.Symbols.4.1
                    int id = 1;

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return this.id <= i2;
                    }

                    @Override // java.util.Iterator
                    public String next() {
                        if (!hasNext()) {
                            throw new NoSuchElementException();
                        }
                        this.id++;
                        return null;
                    }

                    @Override // java.util.Iterator
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }

            @Override // com.amazon.ion.SymbolTable
            public SymbolToken intern(String str2) {
                throw new UnsupportedOperationException("Cannot intern into substitute unknown shared symbol table: " + str + " version " + i);
            }

            @Override // com.amazon.ion.SymbolTable
            public SymbolTable getSystemSymbolTable() {
                return Symbols.systemSymbolTable();
            }

            @Override // com.amazon.ion.impl.bin.AbstractSymbolTable, com.amazon.ion.SymbolTable
            public int getMaxId() {
                return i2;
            }
        };
    }
}
