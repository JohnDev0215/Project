package com.amazon.ion.impl;

import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonReader;
import com.amazon.ion.IonStruct;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.ValueFactory;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
class LocalSymbolTableAsStruct extends LocalSymbolTable implements SymbolTableAsStruct {
    private final SymbolTableStructCache structCache;

    static class Factory implements _Private_LocalSymbolTableFactory {
        private final ValueFactory imageFactory;

        public Factory(ValueFactory valueFactory) {
            this.imageFactory = valueFactory;
        }

        @Override // com.amazon.ion.impl._Private_LocalSymbolTableFactory
        public SymbolTable newLocalSymtab(IonCatalog ionCatalog, IonReader ionReader, boolean z) {
            ArrayList arrayList = new ArrayList();
            SymbolTable symbolTable = ionReader.getSymbolTable();
            LocalSymbolTableImports localSymbolTable = LocalSymbolTable.readLocalSymbolTable(ionReader, ionCatalog, z, arrayList, symbolTable);
            if (localSymbolTable == null) {
                return symbolTable;
            }
            return new LocalSymbolTableAsStruct(localSymbolTable, arrayList, null);
        }

        @Override // com.amazon.ion.impl._Private_LocalSymbolTableFactory
        public SymbolTable newLocalSymtab(SymbolTable symbolTable, SymbolTable... symbolTableArr) {
            return new LocalSymbolTableAsStruct(new LocalSymbolTableImports(symbolTable, symbolTableArr), null, 0 == true ? 1 : 0);
        }

        public SymbolTable newLocalSymtab(IonCatalog ionCatalog, IonStruct ionStruct) {
            IonReaderTreeSystem ionReaderTreeSystem = new IonReaderTreeSystem(ionStruct);
            ArrayList arrayList = new ArrayList();
            return new LocalSymbolTableAsStruct(LocalSymbolTable.readLocalSymbolTable(ionReaderTreeSystem, ionCatalog, false, arrayList, ionStruct.getSymbolTable()), arrayList, ionStruct);
        }
    }

    private LocalSymbolTableAsStruct(LocalSymbolTableImports localSymbolTableImports, List<String> list, IonStruct ionStruct) {
        super(localSymbolTableImports, list);
        this.structCache = new SymbolTableStructCache(this, localSymbolTableImports.getImportedTablesNoCopy(), ionStruct);
    }

    @Override // com.amazon.ion.impl.LocalSymbolTable
    int putSymbol(String str) {
        int iPutSymbol = super.putSymbol(str);
        if (this.structCache.hasStruct()) {
            this.structCache.addSymbol(str, iPutSymbol);
        }
        return iPutSymbol;
    }

    @Override // com.amazon.ion.impl.SymbolTableAsStruct
    public IonStruct getIonRepresentation(ValueFactory valueFactory) {
        return this.structCache.getIonRepresentation(valueFactory);
    }
}
