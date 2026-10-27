package com.amazon.ion.impl;

import com.amazon.ion.IonList;
import com.amazon.ion.IonStruct;
import com.amazon.ion.IonSystem;
import com.amazon.ion.IonType;
import com.amazon.ion.IonValue;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.ValueFactory;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
class SymbolTableStructCache {
    private final int firstLocalSid;
    private IonStruct image;
    private final SymbolTable[] importedTables;
    private final SymbolTable symbolTable;

    SymbolTableStructCache(SymbolTable symbolTable, SymbolTable[] symbolTableArr, IonStruct ionStruct) {
        this.symbolTable = symbolTable;
        this.importedTables = symbolTableArr;
        this.firstLocalSid = symbolTable.getImportedMaxId() + 1;
        this.image = ionStruct;
    }

    public IonStruct getIonRepresentation(ValueFactory valueFactory) {
        IonStruct ionStruct;
        synchronized (this) {
            if (this.image == null) {
                makeIonRepresentation(valueFactory);
            }
            ionStruct = this.image;
        }
        return ionStruct;
    }

    public boolean hasStruct() {
        return this.image != null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10, types: [int] */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v9, types: [boolean] */
    private void makeIonRepresentation(ValueFactory valueFactory) {
        ?? IsSystemTable;
        IonStruct ionStructNewEmptyStruct = valueFactory.newEmptyStruct();
        this.image = ionStructNewEmptyStruct;
        ionStructNewEmptyStruct.addTypeAnnotation("$ion_symbol_table");
        SymbolTable[] symbolTableArr = this.importedTables;
        if (symbolTableArr.length > 0 && (IsSystemTable = symbolTableArr[0].isSystemTable()) < this.importedTables.length) {
            IonList ionListNewEmptyList = valueFactory.newEmptyList();
            ?? r0 = IsSystemTable;
            while (true) {
                SymbolTable[] symbolTableArr2 = this.importedTables;
                if (r0 >= symbolTableArr2.length) {
                    break;
                }
                SymbolTable symbolTable = symbolTableArr2[r0];
                IonStruct ionStructNewEmptyStruct2 = valueFactory.newEmptyStruct();
                ionStructNewEmptyStruct2.add("name", valueFactory.newString(symbolTable.getName()));
                ionStructNewEmptyStruct2.add("version", valueFactory.newInt(symbolTable.getVersion()));
                ionStructNewEmptyStruct2.add("max_id", valueFactory.newInt(symbolTable.getMaxId()));
                ionListNewEmptyList.add((IonValue) ionStructNewEmptyStruct2);
                r0++;
            }
            this.image.add("imports", ionListNewEmptyList);
        }
        if (this.symbolTable.getMaxId() <= this.symbolTable.getImportedMaxId()) {
            return;
        }
        Iterator<String> itIterateDeclaredSymbolNames = this.symbolTable.iterateDeclaredSymbolNames();
        int importedMaxId = this.symbolTable.getImportedMaxId();
        while (true) {
            importedMaxId++;
            if (!itIterateDeclaredSymbolNames.hasNext()) {
                return;
            } else {
                addSymbol(itIterateDeclaredSymbolNames.next(), importedMaxId);
            }
        }
    }

    void addSymbol(String str, int i) {
        IonSystem system = this.image.getSystem();
        IonValue ionValueNewEmptyList = this.image.get("symbols");
        while (ionValueNewEmptyList != null && ionValueNewEmptyList.getType() != IonType.LIST) {
            this.image.remove(ionValueNewEmptyList);
            ionValueNewEmptyList = this.image.get("symbols");
        }
        if (ionValueNewEmptyList == null) {
            ionValueNewEmptyList = system.newEmptyList();
            this.image.put("symbols", ionValueNewEmptyList);
        }
        ((IonList) ionValueNewEmptyList).add(i - this.firstLocalSid, (IonValue) system.newString(str));
    }
}
