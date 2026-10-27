package org.apache.tools.ant.types;

import org.apache.tools.ant.BuildException;

/* JADX INFO: loaded from: classes.dex */
public abstract class EnumeratedAttribute {
    private int index = -1;
    protected String value;

    public abstract String[] getValues();

    protected EnumeratedAttribute() {
    }

    public final void setValue(String value) throws BuildException {
        int index = indexOfValue(value);
        if (index == -1) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(value);
            stringBuffer.append(" is not a legal value for this attribute");
            throw new BuildException(stringBuffer.toString());
        }
        this.index = index;
        this.value = value;
    }

    public final boolean containsValue(String value) {
        return indexOfValue(value) != -1;
    }

    public final int indexOfValue(String value) {
        String[] values = getValues();
        if (values == null || value == null) {
            return -1;
        }
        for (int i = 0; i < values.length; i++) {
            if (value.equals(values[i])) {
                return i;
            }
        }
        return -1;
    }

    public final String getValue() {
        return this.value;
    }

    public final int getIndex() {
        return this.index;
    }

    public String toString() {
        return getValue();
    }
}
