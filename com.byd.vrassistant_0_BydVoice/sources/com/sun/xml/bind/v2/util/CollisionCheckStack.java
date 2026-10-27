package com.sun.xml.bind.v2.util;

import java.util.AbstractList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public final class CollisionCheckStack<E> extends AbstractList<E> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private int size = 0;
    private boolean latestPushResult = false;
    private boolean useIdentity = true;
    private final int[] initialHash = new int[17];
    private Object[] data = new Object[16];
    private int[] next = new int[16];

    public void setUseIdentity(boolean z) {
        this.useIdentity = z;
    }

    public boolean getUseIdentity() {
        return this.useIdentity;
    }

    public boolean getLatestPushResult() {
        return this.latestPushResult;
    }

    public boolean push(E e) {
        if (this.data.length == this.size) {
            expandCapacity();
        }
        this.data[this.size] = e;
        int iHash = hash(e);
        boolean zFindDuplicate = findDuplicate(e, iHash);
        int[] iArr = this.next;
        int i = this.size;
        int[] iArr2 = this.initialHash;
        iArr[i] = iArr2[iHash];
        iArr2[iHash] = i + 1;
        this.size = i + 1;
        this.latestPushResult = zFindDuplicate;
        return zFindDuplicate;
    }

    public void pushNocheck(E e) {
        if (this.data.length == this.size) {
            expandCapacity();
        }
        Object[] objArr = this.data;
        int i = this.size;
        objArr[i] = e;
        this.next[i] = -1;
        this.size = i + 1;
    }

    public boolean findDuplicate(E e) {
        return findDuplicate(e, hash(e));
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int i) {
        return (E) this.data[i];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.size;
    }

    private int hash(Object obj) {
        return ((this.useIdentity ? System.identityHashCode(obj) : obj.hashCode()) & Integer.MAX_VALUE) % this.initialHash.length;
    }

    public E pop() {
        int i = this.size - 1;
        this.size = i;
        Object[] objArr = this.data;
        E e = (E) objArr[i];
        objArr[i] = null;
        int i2 = this.next[i];
        if (i2 >= 0) {
            this.initialHash[hash(e)] = i2;
        }
        return e;
    }

    public E peek() {
        return (E) this.data[this.size - 1];
    }

    private boolean findDuplicate(E e, int i) {
        int i2 = this.initialHash[i];
        while (i2 != 0) {
            int i3 = i2 - 1;
            Object obj = this.data[i3];
            if (this.useIdentity) {
                if (obj == e) {
                    return true;
                }
            } else if (e.equals(obj)) {
                return true;
            }
            i2 = this.next[i3];
        }
        return false;
    }

    private void expandCapacity() {
        Object[] objArr = this.data;
        int length = objArr.length;
        int i = length * 2;
        Object[] objArr2 = new Object[i];
        int[] iArr = new int[i];
        System.arraycopy(objArr, 0, objArr2, 0, length);
        System.arraycopy(this.next, 0, iArr, 0, length);
        this.data = objArr2;
        this.next = iArr;
    }

    public void reset() {
        if (this.size > 0) {
            this.size = 0;
            Arrays.fill(this.initialHash, 0);
        }
    }

    public String getCycleString() {
        E e;
        StringBuilder sb = new StringBuilder();
        int size = size() - 1;
        E e2 = get(size);
        sb.append(e2);
        do {
            sb.append(" -> ");
            size--;
            e = get(size);
            sb.append(e);
        } while (e2 != e);
        return sb.toString();
    }
}
