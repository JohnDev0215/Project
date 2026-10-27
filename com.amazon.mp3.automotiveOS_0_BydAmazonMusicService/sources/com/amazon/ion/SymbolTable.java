package com.amazon.ion;

import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public interface SymbolTable {
    SymbolToken find(String str);

    String findKnownSymbol(int i);

    int findSymbol(String str);

    int getImportedMaxId();

    SymbolTable[] getImportedTables();

    String getIonVersionId();

    int getMaxId();

    String getName();

    SymbolTable getSystemSymbolTable();

    int getVersion();

    SymbolToken intern(String str);

    boolean isLocalTable();

    boolean isSharedTable();

    boolean isSubstitute();

    boolean isSystemTable();

    Iterator<String> iterateDeclaredSymbolNames();

    void writeTo(IonWriter ionWriter) throws IOException;
}
