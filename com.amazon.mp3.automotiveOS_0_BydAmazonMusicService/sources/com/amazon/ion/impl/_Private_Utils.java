package com.amazon.ion.impl;

import com.amazon.ion.IonStruct;
import com.amazon.ion.IonType;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.ValueFactory;
import com.amazon.ion.impl.Base64Encoder;
import com.amazon.ion.impl.LocalSymbolTableAsStruct;
import com.amazon.music.destination.parser.ParserUtil;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes3.dex */
public final class _Private_Utils {
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    public static final int[] EMPTY_INT_ARRAY = new int[0];
    public static final Charset ASCII_CHARSET = Charset.forName("US-ASCII");
    public static final Charset UTF8_CHARSET = Charset.forName(ParserUtil.UTF_8);
    public static final TimeZone UTC = TimeZone.getTimeZone("UTC");
    public static final ListIterator<?> EMPTY_ITERATOR = new ListIterator() { // from class: com.amazon.ion.impl._Private_Utils.1
        @Override // java.util.ListIterator, java.util.Iterator
        public boolean hasNext() {
            return false;
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return false;
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return 0;
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return -1;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public Object next() {
            throw new NoSuchElementException();
        }

        @Override // java.util.ListIterator
        public Object previous() {
            throw new NoSuchElementException();
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public void remove() {
            throw new IllegalStateException();
        }

        @Override // java.util.ListIterator
        public void add(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.ListIterator
        public void set(Object obj) {
            throw new UnsupportedOperationException();
        }
    };

    public static final <T> ListIterator<T> emptyIterator() {
        return (ListIterator<T>) EMPTY_ITERATOR;
    }

    public static boolean safeEquals(Object obj, Object obj2) {
        if (obj != null) {
            return obj.equals(obj2);
        }
        return obj2 == null;
    }

    public static SymbolTokenImpl newSymbolToken(String str, int i) {
        return new SymbolTokenImpl(str, i);
    }

    public static SymbolToken localize(SymbolTable symbolTable, SymbolToken symbolToken) {
        String text = symbolToken.getText();
        int sid = symbolToken.getSid();
        if (symbolTable == null) {
            return (text == null || sid < 0) ? symbolToken : new SymbolTokenImpl(text, -1);
        }
        if (text == null) {
            String strFindKnownSymbol = symbolTable.findKnownSymbol(sid);
            return strFindKnownSymbol != null ? new SymbolTokenImpl(strFindKnownSymbol, sid) : symbolToken;
        }
        SymbolToken symbolTokenFind = symbolTable.find(text);
        if (symbolTokenFind == null) {
            if (sid < 0) {
                return symbolToken;
            }
            symbolTokenFind = new SymbolTokenImpl(text, -1);
        }
        return symbolTokenFind;
    }

    public static int readFully(InputStream inputStream, byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        while (i3 < i2) {
            int i4 = inputStream.read(bArr, i, i2 - i3);
            if (i4 < 0) {
                return i3;
            }
            i3 += i4;
            i += i4;
        }
        return i3;
    }

    public static boolean valueIsLocalSymbolTable(_Private_IonValue _private_ionvalue) {
        return (_private_ionvalue instanceof IonStruct) && _private_ionvalue.findTypeAnnotation("$ion_symbol_table") == 0;
    }

    public static final boolean symtabIsSharedNotSystem(SymbolTable symbolTable) {
        return (symbolTable == null || !symbolTable.isSharedTable() || symbolTable.isSystemTable()) ? false : true;
    }

    public static SymbolTable systemSymtab(int i) {
        return SharedSymbolTable.getSystemSymbolTable(i);
    }

    @Deprecated
    public static _Private_LocalSymbolTableFactory newLocalSymbolTableAsStructFactory(ValueFactory valueFactory) {
        return new LocalSymbolTableAsStruct.Factory(valueFactory);
    }

    public static SymbolTable initialSymtab(_Private_LocalSymbolTableFactory _private_localsymboltablefactory, SymbolTable symbolTable, SymbolTable... symbolTableArr) {
        if (symbolTableArr == null || symbolTableArr.length == 0) {
            return symbolTable;
        }
        if (symbolTableArr.length == 1 && symbolTableArr[0].isSystemTable()) {
            return symbolTableArr[0];
        }
        return _private_localsymboltablefactory.newLocalSymtab(symbolTable, symbolTableArr);
    }

    public static IonStruct symtabTree(SymbolTable symbolTable, ValueFactory valueFactory) {
        SymbolTableAsStruct symbolTableAsStruct;
        if (symbolTable instanceof SymbolTableAsStruct) {
            symbolTableAsStruct = (SymbolTableAsStruct) symbolTable;
        } else {
            LocalSymbolTableAsStruct localSymbolTableAsStruct = (LocalSymbolTableAsStruct) new LocalSymbolTableAsStruct.Factory(valueFactory).newLocalSymtab(symbolTable.getSystemSymbolTable(), symbolTable.getImportedTables());
            Iterator<String> itIterateDeclaredSymbolNames = symbolTable.iterateDeclaredSymbolNames();
            while (itIterateDeclaredSymbolNames.hasNext()) {
                String next = itIterateDeclaredSymbolNames.next();
                if (next != null) {
                    localSymbolTableAsStruct.intern(next);
                }
            }
            symbolTableAsStruct = localSymbolTableAsStruct;
        }
        return symbolTableAsStruct.getIonRepresentation(valueFactory);
    }

    private static boolean localSymtabExtends(SymbolTable symbolTable, SymbolTable symbolTable2) {
        if (symbolTable2.getMaxId() > symbolTable.getMaxId()) {
            return false;
        }
        SymbolTable[] importedTables = symbolTable.getImportedTables();
        SymbolTable[] importedTables2 = symbolTable2.getImportedTables();
        if (importedTables.length != importedTables2.length) {
            return false;
        }
        for (int i = 0; i < importedTables.length; i++) {
            SymbolTable symbolTable3 = importedTables[i];
            SymbolTable symbolTable4 = importedTables2[i];
            if (!symbolTable3.getName().equals(symbolTable4.getName()) || symbolTable3.getVersion() != symbolTable4.getVersion()) {
                return false;
            }
        }
        Iterator<String> itIterateDeclaredSymbolNames = symbolTable.iterateDeclaredSymbolNames();
        Iterator<String> itIterateDeclaredSymbolNames2 = symbolTable2.iterateDeclaredSymbolNames();
        while (itIterateDeclaredSymbolNames2.hasNext()) {
            if (!itIterateDeclaredSymbolNames2.next().equals(itIterateDeclaredSymbolNames.next())) {
                return false;
            }
        }
        return true;
    }

    public static boolean symtabExtends(SymbolTable symbolTable, SymbolTable symbolTable2) {
        if (symbolTable == symbolTable2 || symbolTable2.isSystemTable()) {
            return true;
        }
        if (!symbolTable.isLocalTable()) {
            return symbolTable2.getMaxId() == symbolTable.getMaxId();
        }
        if ((symbolTable instanceof LocalSymbolTable) && (symbolTable2 instanceof LocalSymbolTable)) {
            return ((LocalSymbolTable) symbolTable).symtabExtends(symbolTable2);
        }
        return localSymtabExtends(symbolTable, symbolTable2);
    }

    public static boolean isNonSymbolScalar(IonType ionType) {
        return (IonType.isContainer(ionType) || ionType.equals(IonType.SYMBOL)) ? false : true;
    }

    public static final int getSidForSymbolTableField(String str) {
        if (str == null || str.length() < 4) {
            return -1;
        }
        char cCharAt = str.charAt(0);
        return cCharAt != 'i' ? cCharAt != 's' ? cCharAt != 'v' ? cCharAt != 'm' ? (cCharAt == 'n' && "name".equals(str)) ? 4 : -1 : "max_id".equals(str) ? 8 : -1 : "version".equals(str) ? 5 : -1 : "symbols".equals(str) ? 7 : -1 : "imports".equals(str) ? 6 : -1;
    }

    public static void writeAsBase64(InputStream inputStream, Appendable appendable) throws IOException {
        Base64Encoder.TextStream textStream = new Base64Encoder.TextStream(inputStream);
        while (true) {
            int i = textStream.read();
            if (i == -1) {
                return;
            } else {
                appendable.append((char) i);
            }
        }
    }
}
