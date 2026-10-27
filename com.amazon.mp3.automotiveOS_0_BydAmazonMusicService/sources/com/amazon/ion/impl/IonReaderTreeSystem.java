package com.amazon.ion.impl;

import com.amazon.ion.Decimal;
import com.amazon.ion.IntegerSize;
import com.amazon.ion.IonBool;
import com.amazon.ion.IonContainer;
import com.amazon.ion.IonDatagram;
import com.amazon.ion.IonDecimal;
import com.amazon.ion.IonException;
import com.amazon.ion.IonFloat;
import com.amazon.ion.IonInt;
import com.amazon.ion.IonLob;
import com.amazon.ion.IonNull;
import com.amazon.ion.IonReader;
import com.amazon.ion.IonSymbol;
import com.amazon.ion.IonText;
import com.amazon.ion.IonTimestamp;
import com.amazon.ion.IonType;
import com.amazon.ion.IonValue;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.Timestamp;
import com.amazon.ion.impl._Private_IonValue;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
class IonReaderTreeSystem implements IonReader, _Private_ReaderWriter {
    protected _Private_IonValue _curr;
    protected boolean _eof;
    protected Iterator<IonValue> _iter;
    protected _Private_IonValue _next;
    protected IonValue _parent;
    private Object[] _stack = new Object[10];
    private final _Private_IonValue.SymbolTableProvider _symbolTableAccessor;
    protected final SymbolTable _system_symtab;
    protected int _top;

    @Override // com.amazon.ion.facet.Faceted
    public <T> T asFacet(Class<T> cls) {
        return null;
    }

    @Override // com.amazon.ion.impl._Private_ReaderWriter
    public SymbolTable pop_passed_symbol_table() {
        return null;
    }

    public IonReaderTreeSystem(IonValue ionValue) {
        if (ionValue == null) {
            this._system_symtab = null;
            this._symbolTableAccessor = null;
        } else {
            this._system_symtab = ionValue.getSystem().getSystemSymbolTable();
            re_init(ionValue, false);
            this._symbolTableAccessor = new _Private_IonValue.SymbolTableProvider() { // from class: com.amazon.ion.impl.IonReaderTreeSystem.1
                @Override // com.amazon.ion.impl._Private_IonValue.SymbolTableProvider
                public SymbolTable getSymbolTable() {
                    return IonReaderTreeSystem.this.getSymbolTable();
                }
            };
        }
    }

    void re_init(IonValue ionValue, boolean z) {
        this._curr = null;
        this._eof = false;
        this._top = 0;
        if (ionValue instanceof IonDatagram) {
            IonDatagram ionDatagram = (IonDatagram) ionValue;
            this._parent = ionDatagram;
            this._next = null;
            this._iter = ionDatagram.systemIterator();
            return;
        }
        this._parent = z ? null : ionValue.getContainer();
        this._next = (_Private_IonValue) ionValue;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this._eof = true;
    }

    private void push() {
        Object[] objArr = this._stack;
        int length = objArr.length;
        if (this._top + 1 >= length) {
            Object[] objArr2 = new Object[length * 2];
            System.arraycopy(objArr, 0, objArr2, 0, length);
            this._stack = objArr2;
        }
        Object[] objArr3 = this._stack;
        int i = this._top;
        int i2 = i + 1;
        objArr3[i] = this._parent;
        this._top = i2 + 1;
        objArr3[i2] = this._iter;
    }

    private void pop() {
        int i = this._top - 1;
        this._top = i;
        Object[] objArr = this._stack;
        this._iter = (Iterator) objArr[i];
        objArr[i] = null;
        int i2 = i - 1;
        this._top = i2;
        this._parent = (IonValue) objArr[i2];
        objArr[i2] = null;
        this._eof = false;
    }

    public boolean hasNext() {
        return next_helper_system() != null;
    }

    @Override // com.amazon.ion.IonReader
    public IonType next() {
        if (this._next == null && !hasNext()) {
            this._curr = null;
            return null;
        }
        _Private_IonValue _private_ionvalue = this._next;
        this._curr = _private_ionvalue;
        this._next = null;
        return _private_ionvalue.getType();
    }

    IonType next_helper_system() {
        if (this._eof) {
            return null;
        }
        _Private_IonValue _private_ionvalue = this._next;
        if (_private_ionvalue != null) {
            return _private_ionvalue.getType();
        }
        Iterator<IonValue> it = this._iter;
        if (it != null && it.hasNext()) {
            this._next = (_Private_IonValue) this._iter.next();
        }
        _Private_IonValue _private_ionvalue2 = this._next;
        boolean z = _private_ionvalue2 == null;
        this._eof = z;
        if (z) {
            return null;
        }
        return _private_ionvalue2.getType();
    }

    @Override // com.amazon.ion.IonReader
    public final void stepIn() {
        if (!(this._curr instanceof IonContainer)) {
            throw new IllegalStateException("current value must be a container");
        }
        push();
        _Private_IonValue _private_ionvalue = this._curr;
        this._parent = _private_ionvalue;
        this._iter = new Children((IonContainer) _private_ionvalue);
        this._curr = null;
    }

    @Override // com.amazon.ion.IonReader
    public final void stepOut() {
        if (this._top < 1) {
            throw new IllegalStateException("Cannot stepOut any further, already at top level.");
        }
        pop();
        this._curr = null;
    }

    @Override // com.amazon.ion.IonReader
    public final int getDepth() {
        return this._top / 2;
    }

    @Override // com.amazon.ion.IonReader
    public SymbolTable getSymbolTable() {
        return this._system_symtab;
    }

    @Override // com.amazon.ion.IonReader
    public IonType getType() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (_private_ionvalue == null) {
            return null;
        }
        return _private_ionvalue.getType();
    }

    @Override // com.amazon.ion.IonReader
    public final SymbolToken[] getTypeAnnotationSymbols() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (_private_ionvalue == null) {
            throw new IllegalStateException();
        }
        return _private_ionvalue.getTypeAnnotationSymbols(this._symbolTableAccessor);
    }

    @Override // com.amazon.ion.IonReader
    public boolean isNullValue() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (_private_ionvalue instanceof IonNull) {
            return true;
        }
        if (_private_ionvalue == null) {
            throw new IllegalStateException("must call next() before isNullValue()");
        }
        return _private_ionvalue.isNullValue();
    }

    @Override // com.amazon.ion.IonReader
    public int getFieldId() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (_private_ionvalue == null || this._top == 0) {
            return -1;
        }
        return _private_ionvalue.getFieldId();
    }

    @Override // com.amazon.ion.IonReader
    public String getFieldName() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (_private_ionvalue == null || this._top == 0) {
            return null;
        }
        return _private_ionvalue.getFieldName();
    }

    @Override // com.amazon.ion.IonReader
    public final SymbolToken getFieldNameSymbol() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (_private_ionvalue == null || this._top == 0) {
            return null;
        }
        return _private_ionvalue.getFieldNameSymbol(this._symbolTableAccessor);
    }

    @Override // com.amazon.ion.IonReader
    public boolean booleanValue() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (_private_ionvalue instanceof IonBool) {
            return ((IonBool) _private_ionvalue).booleanValue();
        }
        throw new IllegalStateException("current value is not a boolean");
    }

    @Override // com.amazon.ion.IonReader
    public int intValue() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (_private_ionvalue instanceof IonInt) {
            return ((IonInt) _private_ionvalue).intValue();
        }
        if (_private_ionvalue instanceof IonFloat) {
            return (int) ((IonFloat) _private_ionvalue).doubleValue();
        }
        if (_private_ionvalue instanceof IonDecimal) {
            return (int) ((IonDecimal) _private_ionvalue).doubleValue();
        }
        throw new IllegalStateException("current value is not an ion int, float, or decimal");
    }

    @Override // com.amazon.ion.IonReader
    public long longValue() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (_private_ionvalue instanceof IonInt) {
            return ((IonInt) _private_ionvalue).longValue();
        }
        if (_private_ionvalue instanceof IonFloat) {
            return (long) ((IonFloat) _private_ionvalue).doubleValue();
        }
        if (_private_ionvalue instanceof IonDecimal) {
            return (long) ((IonDecimal) _private_ionvalue).doubleValue();
        }
        throw new IllegalStateException("current value is not an ion int, float, or decimal");
    }

    @Override // com.amazon.ion.IonReader
    public BigInteger bigIntegerValue() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (_private_ionvalue instanceof IonInt) {
            return ((IonInt) _private_ionvalue).bigIntegerValue();
        }
        if (_private_ionvalue instanceof IonFloat) {
            BigDecimal bigDecimalBigDecimalValue = ((IonFloat) _private_ionvalue).bigDecimalValue();
            if (bigDecimalBigDecimalValue == null) {
                return null;
            }
            return bigDecimalBigDecimalValue.toBigInteger();
        }
        if (_private_ionvalue instanceof IonDecimal) {
            BigDecimal bigDecimalBigDecimalValue2 = ((IonDecimal) _private_ionvalue).bigDecimalValue();
            if (bigDecimalBigDecimalValue2 == null) {
                return null;
            }
            return bigDecimalBigDecimalValue2.toBigInteger();
        }
        throw new IllegalStateException("current value is not an ion int, float, or decimal");
    }

    @Override // com.amazon.ion.IonReader
    public double doubleValue() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (_private_ionvalue instanceof IonFloat) {
            return ((IonFloat) _private_ionvalue).doubleValue();
        }
        if (_private_ionvalue instanceof IonDecimal) {
            return ((IonDecimal) _private_ionvalue).doubleValue();
        }
        throw new IllegalStateException("current value is not an ion float or decimal");
    }

    @Override // com.amazon.ion.IonReader
    public Decimal decimalValue() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (_private_ionvalue instanceof IonDecimal) {
            return ((IonDecimal) _private_ionvalue).decimalValue();
        }
        throw new IllegalStateException("current value is not an ion decimal");
    }

    @Override // com.amazon.ion.IonReader
    public Timestamp timestampValue() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (_private_ionvalue instanceof IonTimestamp) {
            return ((IonTimestamp) _private_ionvalue).timestampValue();
        }
        throw new IllegalStateException("current value is not a timestamp");
    }

    @Override // com.amazon.ion.IonReader
    public String stringValue() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (_private_ionvalue instanceof IonText) {
            return ((IonText) _private_ionvalue).stringValue();
        }
        throw new IllegalStateException("current value is not a symbol or string");
    }

    @Override // com.amazon.ion.IonReader
    public SymbolToken symbolValue() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (!(_private_ionvalue instanceof IonSymbol)) {
            throw new IllegalStateException();
        }
        if (_private_ionvalue.isNullValue()) {
            return null;
        }
        return ((IonSymbol) this._curr).symbolValue();
    }

    @Override // com.amazon.ion.IonReader
    public byte[] newBytes() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (_private_ionvalue instanceof IonLob) {
            IonLob ionLob = (IonLob) _private_ionvalue;
            int iByteSize = ionLob.byteSize();
            byte[] bArr = new byte[iByteSize];
            InputStream inputStreamNewInputStream = ionLob.newInputStream();
            try {
                _Private_Utils.readFully(inputStreamNewInputStream, bArr, 0, iByteSize);
                inputStreamNewInputStream.close();
                return bArr;
            } catch (IOException e) {
                throw new IonException(e);
            }
        }
        throw new IllegalStateException("current value is not an ion blob or clob");
    }

    private static final class Children implements Iterator<IonValue> {
        IonValue _curr;
        boolean _eof;
        int _next_idx;
        _Private_IonContainer _parent;

        Children(IonContainer ionContainer) {
            if (ionContainer instanceof _Private_IonContainer) {
                _Private_IonContainer _private_ioncontainer = (_Private_IonContainer) ionContainer;
                this._parent = _private_ioncontainer;
                this._next_idx = 0;
                this._curr = null;
                if (_private_ioncontainer.isNullValue()) {
                    this._eof = true;
                    return;
                }
                return;
            }
            throw new UnsupportedOperationException("this only supports IonContainerImpl instances");
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this._eof) {
                return false;
            }
            int i = this._parent.get_child_count();
            int i2 = this._next_idx;
            if (i2 > 0) {
                int i3 = i2 - 1;
                this._next_idx = i;
                while (true) {
                    if (i3 >= i) {
                        break;
                    }
                    if (this._curr == this._parent.get_child(i3)) {
                        this._next_idx = i3 + 1;
                        break;
                    }
                    i3++;
                }
            }
            if (this._next_idx >= this._parent.get_child_count()) {
                this._eof = true;
            }
            return !this._eof;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public IonValue next() {
            if (!hasNext()) {
                this._curr = null;
            } else {
                this._curr = this._parent.get_child(this._next_idx);
                this._next_idx++;
            }
            return this._curr;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override // com.amazon.ion.IonReader
    public IntegerSize getIntegerSize() {
        _Private_IonValue _private_ionvalue = this._curr;
        if (_private_ionvalue instanceof IonInt) {
            return ((IonInt) _private_ionvalue).getIntegerSize();
        }
        return null;
    }
}
