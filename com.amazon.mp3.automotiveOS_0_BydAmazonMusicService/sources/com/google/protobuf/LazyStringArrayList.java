package com.google.protobuf;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes7.dex */
public class LazyStringArrayList extends AbstractProtobufList<String> implements LazyStringList, RandomAccess {

    @Deprecated
    public static final LazyStringList EMPTY;
    private static final LazyStringArrayList EMPTY_LIST;
    private final List<Object> list;

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public /* bridge */ /* synthetic */ boolean add(Object e) {
        return super.add(e);
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.Collection, java.util.List
    public /* bridge */ /* synthetic */ boolean equals(Object o) {
        return super.equals(o);
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.Collection, java.util.List
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.protobuf.AbstractProtobufList, com.google.protobuf.Internal.ProtobufList
    public /* bridge */ /* synthetic */ boolean isModifiable() {
        return super.isModifiable();
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public /* bridge */ /* synthetic */ boolean remove(Object o) {
        return super.remove(o);
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public /* bridge */ /* synthetic */ boolean removeAll(Collection c) {
        return super.removeAll(c);
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public /* bridge */ /* synthetic */ boolean retainAll(Collection c) {
        return super.retainAll(c);
    }

    static {
        LazyStringArrayList lazyStringArrayList = new LazyStringArrayList();
        EMPTY_LIST = lazyStringArrayList;
        lazyStringArrayList.makeImmutable();
        EMPTY = lazyStringArrayList;
    }

    public static LazyStringArrayList emptyList() {
        return EMPTY_LIST;
    }

    public LazyStringArrayList() {
        this(10);
    }

    public LazyStringArrayList(int initialCapacity) {
        this((ArrayList<Object>) new ArrayList(initialCapacity));
    }

    public LazyStringArrayList(LazyStringList from) {
        this.list = new ArrayList(from.size());
        addAll(from);
    }

    private LazyStringArrayList(ArrayList<Object> list) {
        this.list = list;
    }

    @Override // com.google.protobuf.Internal.ProtobufList, com.google.protobuf.Internal.BooleanList
    /* JADX INFO: renamed from: mutableCopyWithCapacity */
    public LazyStringArrayList mutableCopyWithCapacity2(int capacity) {
        if (capacity < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(capacity);
        arrayList.addAll(this.list);
        return new LazyStringArrayList((ArrayList<Object>) arrayList);
    }

    @Override // java.util.AbstractList, java.util.List
    public String get(int index) {
        Object obj = this.list.get(index);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            String stringUtf8 = byteString.toStringUtf8();
            if (byteString.isValidUtf8()) {
                this.list.set(index, stringUtf8);
            }
            return stringUtf8;
        }
        byte[] bArr = (byte[]) obj;
        String stringUtf82 = Internal.toStringUtf8(bArr);
        if (Internal.isValidUtf8(bArr)) {
            this.list.set(index, stringUtf82);
        }
        return stringUtf82;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.list.size();
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public String set(int index, String s) {
        ensureIsMutable();
        return asString(this.list.set(index, s));
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public void add(int index, String element) {
        ensureIsMutable();
        this.list.add(index, element);
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends String> c) {
        return addAll(size(), c);
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public boolean addAll(int index, Collection<? extends String> c) {
        ensureIsMutable();
        if (c instanceof LazyStringList) {
            c = ((LazyStringList) c).getUnderlyingElements();
        }
        boolean zAddAll = this.list.addAll(index, c);
        ((AbstractList) this).modCount++;
        return zAddAll;
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public String remove(int index) {
        ensureIsMutable();
        Object objRemove = this.list.remove(index);
        ((AbstractList) this).modCount++;
        return asString(objRemove);
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        ensureIsMutable();
        this.list.clear();
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.protobuf.LazyStringList
    public void add(ByteString element) {
        ensureIsMutable();
        this.list.add(element);
        ((AbstractList) this).modCount++;
    }

    @Override // com.google.protobuf.LazyStringList
    public Object getRaw(int index) {
        return this.list.get(index);
    }

    private static String asString(Object o) {
        if (o instanceof String) {
            return (String) o;
        }
        if (o instanceof ByteString) {
            return ((ByteString) o).toStringUtf8();
        }
        return Internal.toStringUtf8((byte[]) o);
    }

    @Override // com.google.protobuf.LazyStringList
    public List<?> getUnderlyingElements() {
        return Collections.unmodifiableList(this.list);
    }

    @Override // com.google.protobuf.LazyStringList
    public LazyStringList getUnmodifiableView() {
        return isModifiable() ? new UnmodifiableLazyStringList(this) : this;
    }
}
