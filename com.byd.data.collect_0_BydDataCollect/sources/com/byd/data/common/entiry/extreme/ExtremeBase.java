package com.byd.data.common.entiry.extreme;

import java.io.Serializable;
import java.lang.Number;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ExtremeBase<E extends Number> implements Serializable {
    protected byte index;
    protected byte serialId;
    public E value;

    public ExtremeBase(byte b2, byte b3, E e2) {
        this.serialId = b2;
        this.index = b3;
        this.value = e2;
    }

    public byte a() {
        return this.index;
    }

    public byte b() {
        return this.serialId;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.value == ((ExtremeBase) obj).value;
    }

    public int hashCode() {
        return Objects.hash(this.value);
    }
}
