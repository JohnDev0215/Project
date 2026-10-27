package com.fasterxml.jackson.dataformat.cbor;

/* JADX INFO: loaded from: classes6.dex */
public class CBORSimpleValue {
    protected final int _value;

    public CBORSimpleValue(int i) {
        this._value = i;
    }

    public int getValue() {
        return this._value;
    }

    public int hashCode() {
        return this._value;
    }

    public String toString() {
        return Integer.valueOf(this._value).toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof CBORSimpleValue) && this._value == ((CBORSimpleValue) obj)._value;
    }
}
