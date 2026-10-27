package org.apache.tools.ant.types;

/* JADX INFO: loaded from: classes.dex */
public class FlexInteger {
    private Integer value;

    public FlexInteger(String value) {
        this.value = Integer.decode(value);
    }

    public int intValue() {
        return this.value.intValue();
    }

    public String toString() {
        return this.value.toString();
    }
}
