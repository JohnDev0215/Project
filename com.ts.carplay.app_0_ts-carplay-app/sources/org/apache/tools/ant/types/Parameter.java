package org.apache.tools.ant.types;

/* JADX INFO: loaded from: classes.dex */
public final class Parameter {
    private String name = null;
    private String type = null;
    private String value = null;

    public final void setName(String name) {
        this.name = name;
    }

    public final void setType(String type) {
        this.type = type;
    }

    public final void setValue(String value) {
        this.value = value;
    }

    public final String getName() {
        return this.name;
    }

    public final String getType() {
        return this.type;
    }

    public final String getValue() {
        return this.value;
    }
}
