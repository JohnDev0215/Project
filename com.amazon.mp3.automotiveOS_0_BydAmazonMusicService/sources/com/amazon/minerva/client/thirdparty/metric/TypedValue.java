package com.amazon.minerva.client.thirdparty.metric;

import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class TypedValue<T> {
    private ValueType type;
    private T value;

    TypedValue(ValueType valueType, T t) {
        Objects.requireNonNull(valueType, "type can not be null.");
        Objects.requireNonNull(t, "value can not be null.");
        if (!valueType.getCompatibleClass().isInstance(t)) {
            throw new IllegalArgumentException("value (" + t + ") is not compatible with the type, type:" + valueType + " (" + valueType.getCompatibleClass() + ")");
        }
        this.type = valueType;
        this.value = t;
    }

    public ValueType getType() {
        return this.type;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TypedValue typedValue = (TypedValue) obj;
        return Objects.equals(this.type, typedValue.type) && Objects.equals(this.value, typedValue.value);
    }

    public int hashCode() {
        return Objects.hash(this.type, this.value);
    }

    public String toString() {
        return "<" + this.type + ">" + this.value;
    }

    public String getValueAsString() {
        return this.type.toString(this.value);
    }

    public int getSizeInBytes() {
        return this.type.getSizeInBytes(this.value);
    }
}
