package com.amazon.ion.impl.lite;

import com.amazon.ion.ContainedValueException;
import com.amazon.ion.IonCatalog;
import com.amazon.ion.IonDatagram;
import com.amazon.ion.IonException;
import com.amazon.ion.IonSymbol;
import com.amazon.ion.IonSystem;
import com.amazon.ion.IonType;
import com.amazon.ion.IonValue;
import com.amazon.ion.IonWriter;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.ValueVisitor;
import com.amazon.ion.impl._Private_IonDatagram;
import com.amazon.ion.impl._Private_IonValue;
import com.amazon.ion.impl._Private_Utils;
import com.amazon.ion.impl.lite.IonContainerLite;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes3.dex */
final class IonDatagramLite extends IonSequenceLite implements IonDatagram, _Private_IonDatagram {
    private static final int HASH_SIGNATURE = IonType.DATAGRAM.toString().hashCode();
    private final IonCatalog _catalog;
    private IonSymbolLite _ivm;
    private SymbolTable _pending_symbol_table;
    private int _pending_symbol_table_idx;
    private final IonSystemLite _system;

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public IonContainerLite getContainer() {
        return null;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public SymbolToken getFieldNameSymbol() {
        return null;
    }

    IonDatagramLite(IonDatagramLite ionDatagramLite) {
        super(ionDatagramLite, ContainerlessContext.wrap(ionDatagramLite._system));
        this._system = ionDatagramLite._system;
        this._catalog = ionDatagramLite._catalog;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonDatagramLite clone(IonContext ionContext) {
        throw new UnsupportedOperationException("IonDatagram cannot have a parent context (be nested)");
    }

    @Override // com.amazon.ion.impl.lite.IonSequenceLite, com.amazon.ion.impl.lite.IonContainerLite, com.amazon.ion.impl.lite.IonValueLite
    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public IonDatagramLite mo103clone() {
        return new IonDatagramLite(this);
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonValueLite topLevelValue() {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public SymbolToken[] getTypeAnnotationSymbols() {
        return SymbolToken.EMPTY_ARRAY;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public SymbolTable getSymbolTable() {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    public SymbolTable getAssignedSymbolTable() {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.ion.impl._Private_IonDatagram
    public void appendTrailingSymbolTable(SymbolTable symbolTable) {
        this._pending_symbol_table = symbolTable;
        this._pending_symbol_table_idx = get_child_count();
    }

    @Override // com.amazon.ion.impl.lite.IonSequenceLite, java.util.List, java.util.Collection
    public boolean add(IonValue ionValue) throws ContainedValueException, NullPointerException {
        add(this._child_count, ionValue);
        return true;
    }

    @Override // com.amazon.ion.impl.lite.IonSequenceLite, java.util.List
    public void add(int i, IonValue ionValue) throws ContainedValueException, NullPointerException {
        ionValue.getClass();
        if (!(ionValue instanceof IonValueLite)) {
            throw new IllegalArgumentException("IonValue implementation can't be mixed");
        }
        super.add(i, (IonValueLite) ionValue);
        this._pending_symbol_table = null;
        this._pending_symbol_table_idx = -1;
    }

    @Override // com.amazon.ion.impl.lite.IonSequenceLite, java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends IonValue> collection) {
        Iterator<? extends IonValue> it = collection.iterator();
        while (true) {
            boolean z = false;
            while (it.hasNext()) {
                if (add(it.next()) || z) {
                    z = true;
                }
            }
            return z;
        }
    }

    @Override // com.amazon.ion.impl.lite.IonSequenceLite, java.util.List
    public boolean addAll(int i, Collection<? extends IonValue> collection) {
        if (i < 0 || i > size()) {
            throw new IndexOutOfBoundsException();
        }
        boolean z = false;
        Iterator<? extends IonValue> it = collection.iterator();
        while (it.hasNext()) {
            add(i, it.next());
            z = true;
            i++;
        }
        if (z) {
            patch_elements_helper(i);
        }
        return z;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue, java.util.List, java.util.Collection
    public int hashCode() {
        int i = HASH_SIGNATURE;
        if (!isNullValue()) {
            Iterator<IonValue> it = iterator();
            while (it.hasNext()) {
                int iHashCode = (8191 * i) + it.next().hashCode();
                i = ((iHashCode << 29) ^ (iHashCode >> 3)) ^ iHashCode;
            }
        }
        return i;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    int hashCode(_Private_IonValue.SymbolTableProvider symbolTableProvider) {
        throw new UnsupportedOperationException("IonDatagrams do not need a resolved Symbol table use #hashCode()");
    }

    @Override // com.amazon.ion.impl.lite.IonContainerLite, java.util.List
    public ListIterator<IonValue> listIterator(int i) {
        return new IonContainerLite.SequenceContentIterator(i, isReadOnly());
    }

    @Override // com.amazon.ion.impl.lite.IonSequenceLite, java.util.List
    public IonValue set(int i, IonValue ionValue) {
        throw new UnsupportedOperationException();
    }

    @Override // com.amazon.ion.impl.lite.IonContainerLite
    public IonContext getContextForIndex(IonValue ionValue, int i) {
        IonContext ionContext;
        if (i == this._pending_symbol_table_idx) {
            SymbolTable symbolTable = this._pending_symbol_table;
            this._pending_symbol_table = null;
            this._pending_symbol_table_idx = -1;
            return TopLevelContext.wrap(symbolTable, this);
        }
        IonValueLite ionValueLite = i > 0 ? get_child(i - 1) : null;
        return (ionValueLite == null || (ionContext = ionValueLite._context) == this) ? TopLevelContext.wrap(null, this) : ionContext;
    }

    @Override // com.amazon.ion.IonValue
    public void accept(ValueVisitor valueVisitor) throws Exception {
        valueVisitor.visit(this);
    }

    @Override // com.amazon.ion.impl.lite.IonContainerLite, com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public IonSystemLite getSystem() {
        return this._system;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public IonType getType() {
        return IonType.DATAGRAM;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public final void writeTo(IonWriter ionWriter) {
        if (ionWriter.getSymbolTable().isSystemTable()) {
            try {
                ionWriter.writeSymbol("$ion_1_0");
            } catch (IOException e) {
                throw new IonException(e);
            }
        }
        Iterator<IonValue> it = iterator();
        while (it.hasNext()) {
            it.next().writeTo(ionWriter);
        }
    }

    @Override // com.amazon.ion.impl.lite.IonSequenceLite, com.amazon.ion.impl.lite.IonValueLite
    final void writeBodyTo(IonWriter ionWriter, _Private_IonValue.SymbolTableProvider symbolTableProvider) throws IOException {
        throw new UnsupportedOperationException("IonDatagram does not operate with a Symbol Table");
    }

    @Override // com.amazon.ion.IonDatagram
    public ListIterator<IonValue> systemIterator() {
        return new SystemContentIterator(isReadOnly());
    }

    protected synchronized IonSymbolLite get_ivm() {
        if (this._ivm == null) {
            this._ivm = getSystem().newSymbol("$ion_1_0");
        }
        return this._ivm;
    }

    protected final class SystemContentIterator implements ListIterator<IonValue> {
        private IonValueLite __current;
        private SystemIteratorPosition __pos;
        private final boolean __readOnly;
        private SystemIteratorPosition __temp_pos;

        public SystemContentIterator(boolean z) {
            if (IonDatagramLite.this._isLocked() && !z) {
                throw new IllegalStateException("you can't open an updatable iterator on a read only value");
            }
            this.__readOnly = z;
            this.__temp_pos = new SystemIteratorPosition(this);
            SystemIteratorPosition systemIteratorPosition = new SystemIteratorPosition(this);
            this.__pos = systemIteratorPosition;
            systemIteratorPosition.load_initial_position();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public IonSystem getSystem() {
            return IonDatagramLite.this.getSystem();
        }

        protected IonValueLite set_position(SystemIteratorPosition systemIteratorPosition) {
            this.__temp_pos = this.__pos;
            this.__pos = systemIteratorPosition;
            IonValueLite ionValueLiteLoad_position = systemIteratorPosition.load_position();
            this.__current = ionValueLiteLoad_position;
            return ionValueLiteLoad_position;
        }

        private void force_position_sync() {
            IonValueLite ionValueLite;
            SystemIteratorPosition systemIteratorPosition = this.__pos;
            int i = systemIteratorPosition.__user_index;
            if (i >= 0) {
                IonDatagramLite ionDatagramLite = IonDatagramLite.this;
                if (i >= ionDatagramLite._child_count || (ionValueLite = systemIteratorPosition.__current_user_value) == null || ionValueLite == ionDatagramLite._children[i]) {
                    return;
                }
                if (this.__readOnly) {
                    throw new IonException("read only sequence was changed");
                }
                systemIteratorPosition.force_position_sync_helper();
            }
        }

        @Override // java.util.ListIterator
        public void add(IonValue ionValue) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public final boolean hasNext() {
            return this.__pos.has_next();
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public IonValue next() {
            SystemIteratorPosition systemIteratorPositionNext_index_helper = next_index_helper();
            if (systemIteratorPositionNext_index_helper == null) {
                throw new NoSuchElementException();
            }
            return set_position(systemIteratorPositionNext_index_helper);
        }

        @Override // java.util.ListIterator
        public final int nextIndex() {
            SystemIteratorPosition systemIteratorPositionNext_index_helper = next_index_helper();
            if (systemIteratorPositionNext_index_helper == null) {
                return this.__pos.get_external_pos() + 1;
            }
            return systemIteratorPositionNext_index_helper.get_external_pos();
        }

        private final SystemIteratorPosition next_index_helper() {
            force_position_sync();
            if (!this.__pos.has_next()) {
                return null;
            }
            SystemIteratorPosition systemIteratorPosition = this.__temp_pos;
            systemIteratorPosition.copyFrom(this.__pos);
            int i = systemIteratorPosition.__local_index + 1;
            systemIteratorPosition.__local_index = i;
            if (i < systemIteratorPosition.__local_value_count) {
                return systemIteratorPosition;
            }
            systemIteratorPosition.__user_index++;
            systemIteratorPosition.load_updated_position();
            systemIteratorPosition.__local_index = 0;
            return systemIteratorPosition;
        }

        @Override // java.util.ListIterator
        public final boolean hasPrevious() {
            return this.__pos.has_prev();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.ListIterator
        public IonValue previous() {
            SystemIteratorPosition systemIteratorPositionPrevious_index_helper = previous_index_helper();
            if (systemIteratorPositionPrevious_index_helper == null) {
                throw new NoSuchElementException();
            }
            return set_position(systemIteratorPositionPrevious_index_helper);
        }

        @Override // java.util.ListIterator
        public final int previousIndex() {
            SystemIteratorPosition systemIteratorPositionPrevious_index_helper = previous_index_helper();
            if (systemIteratorPositionPrevious_index_helper == null) {
                return -1;
            }
            return systemIteratorPositionPrevious_index_helper.get_external_pos();
        }

        private final SystemIteratorPosition previous_index_helper() {
            force_position_sync();
            if (!this.__pos.has_prev()) {
                return null;
            }
            SystemIteratorPosition systemIteratorPosition = this.__temp_pos;
            systemIteratorPosition.copyFrom(this.__pos);
            int i = systemIteratorPosition.__local_index - 1;
            systemIteratorPosition.__local_index = i;
            if (i >= 0) {
                return systemIteratorPosition;
            }
            systemIteratorPosition.__index_adjustment -= systemIteratorPosition.__local_value_count;
            systemIteratorPosition.__user_index--;
            systemIteratorPosition.load_updated_position();
            systemIteratorPosition.__local_index = systemIteratorPosition.__local_value_count - 1;
            return systemIteratorPosition;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public void remove() {
            SystemIteratorPosition systemIteratorPosition;
            if (this.__readOnly) {
                throw new UnsupportedOperationException();
            }
            force_position_sync();
            if (this.__current == null || (systemIteratorPosition = this.__pos) == null) {
                throw new NoSuchElementException();
            }
            if (systemIteratorPosition.on_system_value()) {
                throw new UnsupportedOperationException();
            }
            int i = this.__pos.__user_index;
            int i_elementid = this.__current._elementid();
            IonDatagramLite.this.remove_child(i);
            IonDatagramLite.this.patch_elements_helper(i_elementid);
            SystemIteratorPosition systemIteratorPosition2 = this.__pos;
            systemIteratorPosition2.__index_adjustment -= systemIteratorPosition2.__local_value_count;
            if (systemIteratorPosition2.__user_index < IonDatagramLite.this.get_child_count() - 1) {
                this.__pos.load_updated_position();
                this.__pos.__local_index = -1;
            }
            this.__current = null;
        }

        @Override // java.util.ListIterator
        public void set(IonValue ionValue) {
            throw new UnsupportedOperationException();
        }

        protected int get_datagram_child_count() {
            return IonDatagramLite.this.get_child_count();
        }

        protected IonValueLite get_datagram_child(int i) {
            return IonDatagramLite.this.get_child(i);
        }

        protected IonSystem get_datagram_system() {
            return IonDatagramLite.this._system;
        }

        protected boolean datagram_contains(IonValueLite ionValueLite) {
            return IonDatagramLite.this.contains(ionValueLite);
        }

        protected IonSymbolLite get_datagram_ivm() {
            return IonDatagramLite.this.get_ivm();
        }
    }

    static class SystemIteratorPosition {
        protected SymbolTable __current_symbols;
        protected int __current_symbols_index;
        protected IonValueLite __current_user_value;
        protected int __index_adjustment;
        protected final SystemContentIterator __iterator;
        protected int __local_index;
        protected int __local_value_count;
        protected IonValueLite[] __local_values = new IonValueLite[3];
        protected int __user_index;

        SystemIteratorPosition(SystemContentIterator systemContentIterator) {
            this.__iterator = systemContentIterator;
        }

        void load_initial_position() {
            this.__user_index = 0;
            this.__local_index = -1;
            this.__current_symbols_index = -1;
            load_updated_position();
        }

        protected int get_external_pos() {
            return ((this.__user_index + this.__index_adjustment) - this.__local_value_count) + this.__local_index;
        }

        protected boolean on_system_value() {
            return this.__current_user_value != this.__local_values[0];
        }

        protected boolean has_next() {
            return this.__local_index + 1 < this.__local_value_count || this.__user_index + 1 < this.__iterator.get_datagram_child_count();
        }

        protected boolean has_prev() {
            return this.__user_index > 0 || this.__local_index > 0;
        }

        protected void copyFrom(SystemIteratorPosition systemIteratorPosition) {
            this.__index_adjustment = systemIteratorPosition.__index_adjustment;
            this.__user_index = systemIteratorPosition.__user_index;
            this.__local_index = systemIteratorPosition.__local_index;
            this.__current_user_value = systemIteratorPosition.__current_user_value;
            this.__current_symbols = systemIteratorPosition.__current_symbols;
            this.__current_symbols_index = systemIteratorPosition.__current_symbols_index;
            int i = systemIteratorPosition.__local_value_count;
            if (i > 0) {
                IonValueLite[] ionValueLiteArr = this.__local_values;
                if (ionValueLiteArr == null || i >= ionValueLiteArr.length) {
                    this.__local_values = new IonValueLite[systemIteratorPosition.__local_values.length];
                }
                System.arraycopy(systemIteratorPosition.__local_values, 0, this.__local_values, 0, i);
            }
            this.__local_value_count = systemIteratorPosition.__local_value_count;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void load_updated_position() {
            IonValueLite ionValueLite = this.__current_user_value;
            int i = this.__user_index;
            if (i < 0 || (i > 0 && i >= this.__iterator.get_datagram_child_count())) {
                throw new IonException("attempt to position iterator past end of values");
            }
            if (this.__user_index < this.__iterator.get_datagram_child_count()) {
                this.__current_user_value = this.__iterator.get_datagram_child(this.__user_index);
            } else {
                this.__current_user_value = null;
            }
            int i2 = this.__local_value_count;
            this.__local_value_count = 0;
            IonValueLite ionValueLite2 = this.__current_user_value;
            if (ionValueLite2 != null) {
                push_system_value(ionValueLite2);
            }
            load_current_symbol_table(ionValueLite);
            for (int i3 = this.__local_value_count; i3 < i2; i3++) {
                this.__local_values[i3] = null;
            }
            this.__index_adjustment += this.__local_value_count - 1;
        }

        void load_current_symbol_table(IonValueLite ionValueLite) {
            SymbolTable symbolTable;
            IonValue ionValueSymtabTree;
            IonValueLite ionValueLite2 = this.__current_user_value;
            int i = this.__user_index;
            SymbolTable assignedSymbolTable = this.__current_symbols;
            int i2 = this.__current_symbols_index;
            this.__current_symbols = null;
            this.__current_symbols_index = i;
            if (ionValueLite2 != null) {
                symbolTable = ionValueLite2.getAssignedSymbolTable();
                this.__current_symbols = symbolTable;
            } else {
                symbolTable = null;
            }
            int i3 = i - 1;
            if (i3 != i2) {
                if (i3 < 0 || i3 >= this.__iterator.get_datagram_child_count()) {
                    assignedSymbolTable = null;
                } else {
                    ionValueLite = this.__iterator.get_datagram_child(i3);
                    assignedSymbolTable = ionValueLite.getAssignedSymbolTable();
                }
            }
            if (symbolTable != assignedSymbolTable) {
                while (symbolTable != null) {
                    boolean zIsSystemTable = symbolTable.isSystemTable();
                    if (zIsSystemTable) {
                        ionValueSymtabTree = this.__iterator.get_datagram_ivm();
                    } else {
                        ionValueSymtabTree = _Private_Utils.symtabTree(symbolTable, this.__iterator.get_datagram_system());
                    }
                    if (ionValueSymtabTree == ionValueLite || (is_ivm(ionValueLite2) && zIsSystemTable)) {
                        int i_elementid = ionValueLite == null ? -1 : ionValueLite._elementid() - 1;
                        if (i_elementid >= 0) {
                            ionValueLite = this.__iterator.get_datagram_child(i_elementid);
                        }
                        symbolTable = ionValueSymtabTree.getSymbolTable();
                        if (symbolTable == null || symbolTable.isSystemTable()) {
                            break;
                        }
                    } else {
                        push_system_value((IonValueLite) ionValueSymtabTree);
                    }
                    ionValueLite = null;
                    symbolTable = ionValueSymtabTree.getSymbolTable();
                    if (symbolTable == null) {
                        break;
                    }
                    break;
                    break;
                }
            }
            if (i != 0 || is_ivm(ionValueLite2)) {
                return;
            }
            push_system_value(this.__iterator.get_datagram_ivm());
        }

        private static final boolean is_ivm(IonValue ionValue) {
            SymbolToken symbolTokenSymbolValue;
            return (ionValue instanceof IonSymbol) && ionValue.getTypeAnnotationSymbols().length == 0 && (symbolTokenSymbolValue = ((IonSymbol) ionValue).symbolValue()) != null && "$ion_1_0".equals(symbolTokenSymbolValue.getText());
        }

        private void push_system_value(IonValueLite ionValueLite) {
            int i = this.__local_value_count;
            IonValueLite[] ionValueLiteArr = this.__local_values;
            if (i >= ionValueLiteArr.length) {
                IonValueLite[] ionValueLiteArr2 = new IonValueLite[ionValueLiteArr != null ? 2 * ionValueLiteArr.length : 2];
                if (i > 0) {
                    System.arraycopy(ionValueLiteArr, 0, ionValueLiteArr2, 0, i);
                }
                this.__local_values = ionValueLiteArr2;
            }
            IonValueLite[] ionValueLiteArr3 = this.__local_values;
            int i2 = this.__local_value_count;
            this.__local_value_count = i2 + 1;
            ionValueLiteArr3[i2] = ionValueLite;
        }

        protected IonValueLite load_position() {
            return this.__local_values[(this.__local_value_count - this.__local_index) - 1];
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void force_position_sync_helper() {
            if (!this.__iterator.datagram_contains(this.__current_user_value)) {
                throw new IonException("current user value removed outside this iterator - position lost");
            }
            int i = this.__user_index;
            int i_elementid = this.__current_user_value._elementid();
            if (i != i_elementid) {
                SymbolTable symbolTable = null;
                int i2 = 0;
                int iCount_system_values = 0;
                while (i2 < i_elementid) {
                    SymbolTable symbolTable2 = this.__iterator.get_datagram_child(i2).getSymbolTable();
                    if (symbolTable2 != symbolTable) {
                        iCount_system_values += count_system_values(this.__iterator.getSystem(), symbolTable, symbolTable2);
                    }
                    i2--;
                    symbolTable = symbolTable2;
                }
                this.__index_adjustment = iCount_system_values + this.__local_value_count;
                this.__user_index = i_elementid;
            }
        }

        private static int count_system_values(IonSystem ionSystem, SymbolTable symbolTable, SymbolTable symbolTable2) {
            int i = 0;
            while (symbolTable2.isLocalTable()) {
                i++;
                symbolTable2 = _Private_Utils.symtabTree(symbolTable2, ionSystem).getSymbolTable();
            }
            return (symbolTable == null || symbolTable.getIonVersionId().equals(symbolTable2.getIonVersionId())) ? i + 1 : i;
        }
    }
}
