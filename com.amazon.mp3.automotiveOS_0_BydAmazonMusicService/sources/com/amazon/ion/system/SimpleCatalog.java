package com.amazon.ion.system;

import com.amazon.ion.IonCatalog;
import com.amazon.ion.SymbolTable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes3.dex */
public class SimpleCatalog implements IonCatalog, Iterable<SymbolTable> {
    private Map<String, TreeMap<Integer, SymbolTable>> myTablesByName = new HashMap();

    @Override // com.amazon.ion.IonCatalog
    public SymbolTable getTable(String str, int i) {
        TreeMap<Integer, SymbolTable> treeMap;
        SymbolTable symbolTable;
        if (str == null) {
            throw new IllegalArgumentException("name is null");
        }
        if (str.length() == 0) {
            throw new IllegalArgumentException("name is empty");
        }
        if (i < 1) {
            throw new IllegalArgumentException("version is < 1");
        }
        synchronized (this.myTablesByName) {
            treeMap = this.myTablesByName.get(str);
        }
        if (treeMap == null) {
            return null;
        }
        synchronized (treeMap) {
            symbolTable = treeMap.get(Integer.valueOf(i));
            if (symbolTable == null) {
                symbolTable = treeMap.get(bestMatch(i, treeMap.keySet()));
            }
        }
        return symbolTable;
    }

    static Integer bestMatch(int i, Iterable<Integer> iterable) {
        Integer num = null;
        int i2 = i;
        for (Integer num2 : iterable) {
            int iIntValue = num2.intValue();
            if (i < i2) {
                if (i < iIntValue && iIntValue < i2) {
                    num = num2;
                    i2 = iIntValue;
                }
            } else if (i2 >= i || i2 < iIntValue) {
                num = num2;
                i2 = iIntValue;
            }
        }
        return num;
    }

    @Override // java.lang.Iterable
    public Iterator<SymbolTable> iterator() {
        ArrayList arrayList;
        synchronized (this.myTablesByName) {
            arrayList = new ArrayList(this.myTablesByName.size());
            for (TreeMap<Integer, SymbolTable> treeMap : this.myTablesByName.values()) {
                synchronized (treeMap) {
                    arrayList.addAll(treeMap.values());
                }
            }
        }
        return arrayList.iterator();
    }
}
