package org.apache.commons.lang3.mutable;

import org.apache.commons.lang3.math.NumberUtils;

/* JADX INFO: loaded from: classes7.dex */
public class MutableInt extends Number implements Comparable<MutableInt> {
    private int value;

    public MutableInt() {
    }

    public MutableInt(int i) {
        this.value = i;
    }

    public void setValue(int i) {
        this.value = i;
    }

    public void increment() {
        this.value++;
    }

    public int getAndIncrement() {
        int i = this.value;
        this.value = i + 1;
        return i;
    }

    public int getAndDecrement() {
        int i = this.value;
        this.value = i - 1;
        return i;
    }

    public int decrementAndGet() {
        int i = this.value - 1;
        this.value = i;
        return i;
    }

    @Override // java.lang.Number
    public int intValue() {
        return this.value;
    }

    @Override // java.lang.Number
    public long longValue() {
        return this.value;
    }

    @Override // java.lang.Number
    public float floatValue() {
        return this.value;
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return this.value;
    }

    public boolean equals(Object obj) {
        return (obj instanceof MutableInt) && this.value == ((MutableInt) obj).intValue();
    }

    public int hashCode() {
        return this.value;
    }

    @Override // java.lang.Comparable
    public int compareTo(MutableInt mutableInt) {
        return NumberUtils.compare(this.value, mutableInt.value);
    }

    public String toString() {
        return String.valueOf(this.value);
    }
}
