package com.amazon.ion.impl.lite;

import com.amazon.ion.IonStruct;
import com.amazon.ion.IonType;
import com.amazon.ion.IonValue;
import com.amazon.ion.IonWriter;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.UnknownSymbolException;
import com.amazon.ion.ValueFactory;
import com.amazon.ion.ValueVisitor;
import com.amazon.ion.impl._Private_CurriedValueFactory;
import com.amazon.ion.impl._Private_IonValue;
import com.amazon.ion.impl.lite.IonContainerLite;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* JADX INFO: loaded from: classes3.dex */
final class IonStructLite extends IonContainerLite implements IonStruct {
    private static final int HASH_SIGNATURE = IonType.STRUCT.toString().hashCode();
    private Map<String, Integer> _field_map;
    public int _field_map_duplicate_count;
    private boolean hasNullFieldName;

    IonStructLite(ContainerlessContext containerlessContext, boolean z) {
        super(containerlessContext, z);
        this.hasNullFieldName = false;
    }

    private IonStructLite(IonStructLite ionStructLite, IonContext ionContext) {
        super(ionStructLite, ionContext, true);
        this.hasNullFieldName = false;
        this._field_map = ionStructLite._field_map == null ? null : new HashMap(ionStructLite._field_map);
        this._field_map_duplicate_count = ionStructLite._field_map_duplicate_count;
        this.hasNullFieldName = ionStructLite.hasNullFieldName;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.ion.impl.lite.IonValueLite
    public IonStructLite clone(IonContext ionContext) {
        return new IonStructLite(this, ionContext);
    }

    @Override // com.amazon.ion.impl.lite.IonContainerLite, com.amazon.ion.impl.lite.IonValueLite
    /* JADX INFO: renamed from: clone */
    public IonStructLite mo103clone() {
        return clone((IonContext) ContainerlessContext.wrap(getSystem()));
    }

    @Override // com.amazon.ion.impl.lite.IonContainerLite
    protected void transitionToLargeSize(int i) {
        if (this._field_map != null) {
            return;
        }
        build_field_map();
    }

    protected void build_field_map() {
        IonValueLite[] ionValueLiteArr = this._children;
        this._field_map = new HashMap(ionValueLiteArr == null ? 0 : ionValueLiteArr.length);
        this._field_map_duplicate_count = 0;
        int i = get_child_count();
        for (int i2 = 0; i2 < i; i2++) {
            String text = get_child(i2).getFieldNameSymbol().getText();
            if (this._field_map.get(text) != null) {
                this._field_map_duplicate_count++;
            }
            this._field_map.put(text, Integer.valueOf(i2));
        }
    }

    private void add_field(String str, int i) {
        Integer num = this._field_map.get(str);
        if (num != null) {
            this._field_map_duplicate_count++;
            if (num.intValue() > i) {
                i = num.intValue();
            }
        }
        this._field_map.put(str, Integer.valueOf(i));
    }

    private void remove_field(String str, int i, int i2) {
        Map<String, Integer> map = this._field_map;
        if (map == null) {
            return;
        }
        map.get(str);
        this._field_map.remove(str);
        this._field_map_duplicate_count -= i2 - 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void remove_field_from_field_map(String str, int i) {
        if (this._field_map.get(str).intValue() != i) {
            this._field_map_duplicate_count--;
            return;
        }
        if (this._field_map_duplicate_count > 0) {
            int iFind_last_duplicate = find_last_duplicate(str, i);
            if (iFind_last_duplicate == -1) {
                this._field_map.remove(str);
                return;
            } else {
                this._field_map.put(str, Integer.valueOf(iFind_last_duplicate));
                this._field_map_duplicate_count--;
                return;
            }
        }
        this._field_map.remove(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void patch_map_elements_helper(int i) {
        if (this._field_map != null && i < get_child_count()) {
            while (i < get_child_count()) {
                String fieldName = get_child(i).getFieldName();
                if (this._field_map.get(fieldName).intValue() != i) {
                    this._field_map.put(fieldName, Integer.valueOf(i));
                }
                i++;
            }
        }
    }

    private int find_last_duplicate(String str, int i) {
        while (i > 0) {
            i--;
            if (str.equals(get_child(i).getFieldName())) {
                return i;
            }
        }
        return -1;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    int hashCode(_Private_IonValue.SymbolTableProvider symbolTableProvider) {
        int iHashCode;
        int i = HASH_SIGNATURE;
        if (!isNullValue()) {
            Iterator<IonValue> it = iterator();
            while (it.hasNext()) {
                IonValueLite ionValueLite = (IonValueLite) it.next();
                SymbolToken fieldNameSymbol = ionValueLite.getFieldNameSymbol(symbolTableProvider);
                String text = fieldNameSymbol.getText();
                if (text == null) {
                    iHashCode = fieldNameSymbol.getSid() * WorkQueueKt.MASK;
                } else {
                    iHashCode = text.hashCode() * 31;
                }
                int iHashCode2 = (((HASH_SIGNATURE * 8191) + ionValueLite.hashCode(symbolTableProvider)) * 16777619) + (iHashCode ^ ((iHashCode << 17) ^ (iHashCode >> 15)));
                i += ((iHashCode2 << 19) ^ (iHashCode2 >> 13)) ^ iHashCode2;
            }
        }
        return hashTypeAnnotations(i, symbolTableProvider);
    }

    @Override // com.amazon.ion.IonStruct
    public IonStruct cloneAndRemove(String... strArr) {
        return doClone(false, strArr);
    }

    private IonStruct doClone(boolean z, String... strArr) {
        IonStructLite ionStructLiteNewNullStruct;
        if (isNullValue()) {
            ionStructLiteNewNullStruct = getSystem().newNullStruct();
        } else {
            HashSet hashSet = new HashSet(Arrays.asList(strArr));
            if (z && hashSet.contains(null)) {
                throw new NullPointerException("Can't retain an unknown field name");
            }
            IonStructLite ionStructLiteNewEmptyStruct = getSystem().newEmptyStruct();
            for (IonValue ionValue : this) {
                if (hashSet.contains(ionValue.getFieldNameSymbol().getText()) == z) {
                    ionStructLiteNewEmptyStruct.add(ionValue.getFieldName(), ionValue.mo103clone());
                }
            }
            ionStructLiteNewNullStruct = ionStructLiteNewEmptyStruct;
        }
        ionStructLiteNewNullStruct.setTypeAnnotationSymbols(getTypeAnnotationSymbols());
        return ionStructLiteNewNullStruct;
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite, com.amazon.ion.IonValue
    public IonType getType() {
        return IonType.STRUCT;
    }

    @Override // com.amazon.ion.IonStruct
    public boolean containsKey(Object obj) {
        return get((String) obj) != null;
    }

    @Override // com.amazon.ion.IonStruct
    public IonValue get(String str) {
        int iFind_field_helper = find_field_helper(str);
        if (iFind_field_helper < 0) {
            if (this.hasNullFieldName) {
                throw new UnknownSymbolException("Unable to determine whether the field exists because the struct contains field names with unknown text.");
            }
            return null;
        }
        return get_child(iFind_field_helper);
    }

    private int find_field_helper(String str) {
        validateFieldName(str);
        if (isNullValue()) {
            return -1;
        }
        Map<String, Integer> map = this._field_map;
        if (map != null) {
            Integer num = map.get(str);
            if (num != null) {
                return num.intValue();
            }
            return -1;
        }
        int i = get_child_count();
        for (int i2 = 0; i2 < i; i2++) {
            if (str.equals(get_child(i2).getFieldName())) {
                return i2;
            }
        }
        return -1;
    }

    @Override // com.amazon.ion.IonStruct
    public ValueFactory add(final String str) {
        return new _Private_CurriedValueFactory(getSystem()) { // from class: com.amazon.ion.impl.lite.IonStructLite.1
            @Override // com.amazon.ion.impl._Private_CurriedValueFactory
            protected void handle(IonValue ionValue) {
                IonStructLite.this.add(str, ionValue);
            }
        };
    }

    private void _add(String str, IonValueLite ionValueLite) {
        this.hasNullFieldName |= str == null;
        add(get_child_count(), ionValueLite);
        if (this._field_map != null) {
            add_field(str, ionValueLite._elementid());
        }
    }

    @Override // com.amazon.ion.IonStruct
    public void add(String str, IonValue ionValue) {
        checkForLock();
        validateNewChild(ionValue);
        validateFieldName(str);
        IonValueLite ionValueLite = (IonValueLite) ionValue;
        _add(str, ionValueLite);
        ionValueLite.setFieldName(str);
    }

    @Override // com.amazon.ion.IonStruct
    public void add(SymbolToken symbolToken, IonValue ionValue) {
        String text = symbolToken.getText();
        if (text != null) {
            add(text, ionValue);
            return;
        }
        if (symbolToken.getSid() < 0) {
            throw new IllegalArgumentException("fieldName has no text or ID");
        }
        checkForLock();
        validateNewChild(ionValue);
        IonValueLite ionValueLite = (IonValueLite) ionValue;
        ionValueLite.setFieldNameSymbol(symbolToken);
        _add(text, ionValueLite);
    }

    @Override // com.amazon.ion.IonStruct
    public void put(String str, IonValue ionValue) {
        checkForLock();
        validateFieldName(str);
        if (ionValue != null) {
            validateNewChild(ionValue);
        }
        int iIntValue = get_child_count();
        Map<String, Integer> map = this._field_map;
        boolean z = false;
        if (map != null && this._field_map_duplicate_count == 0) {
            Integer num = map.get(str);
            if (num != null) {
                iIntValue = num.intValue();
                remove_field_from_field_map(str, iIntValue);
                remove_child(iIntValue);
                z = true;
            }
        } else {
            int i = get_child_count();
            int i2 = 0;
            while (i > 0) {
                i--;
                if (str.equals(get_child(i).getFieldNameSymbol().getText())) {
                    remove_child(i);
                    i2++;
                    iIntValue = i;
                    z = true;
                }
            }
            if (z) {
                remove_field(str, iIntValue, i2);
            }
        }
        if (z) {
            patch_map_elements_helper(iIntValue);
            patch_elements_helper(iIntValue);
        }
        if (ionValue != null) {
            add(str, ionValue);
        }
    }

    @Override // com.amazon.ion.impl.lite.IonContainerLite, java.util.List
    public ListIterator<IonValue> listIterator(int i) {
        return new IonContainerLite.SequenceContentIterator(i, isReadOnly()) { // from class: com.amazon.ion.impl.lite.IonStructLite.3
            @Override // com.amazon.ion.impl.lite.IonContainerLite.SequenceContentIterator, java.util.ListIterator, java.util.Iterator
            public void remove() {
                if (this.__readOnly) {
                    throw new UnsupportedOperationException();
                }
                force_position_sync();
                int i2 = this.__pos;
                if (!this.__lastMoveWasPrevious) {
                    i2--;
                }
                if (i2 < 0) {
                    throw new ArrayIndexOutOfBoundsException();
                }
                IonValueLite ionValueLite = this.__current;
                ionValueLite._elementid();
                if (IonStructLite.this._field_map != null) {
                    IonStructLite.this.remove_field_from_field_map(ionValueLite.getFieldName(), i2);
                }
                super.remove();
                if (IonStructLite.this._field_map != null) {
                    IonStructLite.this.patch_map_elements_helper(i2);
                }
            }
        };
    }

    @Override // com.amazon.ion.impl.lite.IonContainerLite, com.amazon.ion.IonContainer
    public boolean remove(IonValue ionValue) {
        ionValue.getClass();
        checkForLock();
        if (ionValue.getContainer() != this) {
            return false;
        }
        IonValueLite ionValueLite = (IonValueLite) ionValue;
        int i_elementid = ionValueLite._elementid();
        if (this._field_map != null) {
            remove_field_from_field_map(ionValueLite.getFieldName(), i_elementid);
        }
        super.remove(ionValueLite);
        if (this._field_map == null) {
            return true;
        }
        patch_map_elements_helper(i_elementid);
        return true;
    }

    private static void validateFieldName(String str) {
        if (str == null) {
            throw new NullPointerException("fieldName is null");
        }
    }

    @Override // com.amazon.ion.impl.lite.IonValueLite
    final void writeBodyTo(IonWriter ionWriter, _Private_IonValue.SymbolTableProvider symbolTableProvider) throws IOException {
        if (isNullValue()) {
            ionWriter.writeNull(IonType.STRUCT);
            return;
        }
        ionWriter.stepIn(IonType.STRUCT);
        writeChildren(ionWriter, this, symbolTableProvider);
        ionWriter.stepOut();
    }

    @Override // com.amazon.ion.IonValue
    public void accept(ValueVisitor valueVisitor) throws Exception {
        valueVisitor.visit(this);
    }
}
