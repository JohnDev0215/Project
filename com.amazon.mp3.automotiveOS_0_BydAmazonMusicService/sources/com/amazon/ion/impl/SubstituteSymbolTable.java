package com.amazon.ion.impl;

import com.amazon.ion.IonWriter;
import com.amazon.ion.ReadOnlyValueException;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
final class SubstituteSymbolTable implements SymbolTable {
    private final int myMaxId;
    private final String myName;
    private final SymbolTable myOriginalSymTab;
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
    public String getIonVersionId() {
        return null;
    }

    @Override // com.amazon.ion.SymbolTable
    public SymbolTable getSystemSymbolTable() {
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

    SubstituteSymbolTable(String str, int i, int i2) {
        this.myOriginalSymTab = null;
        this.myName = str;
        this.myVersion = i;
        this.myMaxId = i2;
    }

    SubstituteSymbolTable(SymbolTable symbolTable, int i, int i2) {
        this.myOriginalSymTab = symbolTable;
        this.myName = symbolTable.getName();
        this.myVersion = i;
        this.myMaxId = i2;
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
    public int getMaxId() {
        return this.myMaxId;
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
        SymbolTable symbolTable = this.myOriginalSymTab;
        if (symbolTable == null) {
            return null;
        }
        SymbolToken symbolTokenFind = symbolTable.find(str);
        if (symbolTokenFind == null || symbolTokenFind.getSid() <= this.myMaxId) {
            return symbolTokenFind;
        }
        return null;
    }

    @Override // com.amazon.ion.SymbolTable
    public int findSymbol(String str) {
        int iFindSymbol;
        SymbolTable symbolTable = this.myOriginalSymTab;
        if (symbolTable == null || (iFindSymbol = symbolTable.findSymbol(str)) > this.myMaxId) {
            return -1;
        }
        return iFindSymbol;
    }

    @Override // com.amazon.ion.SymbolTable
    public String findKnownSymbol(int i) {
        SymbolTable symbolTable;
        if (i > this.myMaxId || (symbolTable = this.myOriginalSymTab) == null) {
            return null;
        }
        return symbolTable.findKnownSymbol(i);
    }

    @Override // com.amazon.ion.SymbolTable
    public Iterator<String> iterateDeclaredSymbolNames() {
        Iterator<String> it;
        SymbolTable symbolTable = this.myOriginalSymTab;
        if (symbolTable != null) {
            it = symbolTable.iterateDeclaredSymbolNames();
        } else {
            it = Collections.EMPTY_LIST.iterator();
        }
        return new SymbolIterator(it);
    }

    @Override // com.amazon.ion.SymbolTable
    public void writeTo(IonWriter ionWriter) throws IOException {
        ionWriter.writeValues(new SymbolTableReader(this));
    }

    private final class SymbolIterator implements Iterator<String> {
        private int myIndex = 0;
        private Iterator<String> myOriginalIterator;

        SymbolIterator(Iterator<String> it) {
            this.myOriginalIterator = it;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.myIndex < SubstituteSymbolTable.this.myMaxId;
        }

        @Override // java.util.Iterator
        public String next() {
            if (this.myIndex < SubstituteSymbolTable.this.myMaxId) {
                next = this.myOriginalIterator.hasNext() ? this.myOriginalIterator.next() : null;
                this.myIndex++;
            }
            return next;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
