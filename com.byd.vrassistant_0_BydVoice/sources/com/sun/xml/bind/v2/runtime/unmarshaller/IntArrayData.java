package com.sun.xml.bind.v2.runtime.unmarshaller;

import com.sun.xml.bind.v2.runtime.output.Pcdata;
import com.sun.xml.bind.v2.runtime.output.UTF8XmlOutput;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public final class IntArrayData extends Pcdata {
    private int[] data;
    private int len;
    private StringBuilder literal;
    private int start;

    public IntArrayData(int[] iArr, int i, int i2) {
        set(iArr, i, i2);
    }

    public IntArrayData() {
    }

    public void set(int[] iArr, int i, int i2) {
        this.data = iArr;
        this.start = i;
        this.len = i2;
        this.literal = null;
    }

    @Override // java.lang.CharSequence
    public int length() {
        return getLiteral().length();
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        return getLiteral().charAt(i);
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        return getLiteral().subSequence(i, i2);
    }

    private StringBuilder getLiteral() {
        StringBuilder sb = this.literal;
        if (sb != null) {
            return sb;
        }
        this.literal = new StringBuilder();
        int i = this.start;
        int i2 = this.len;
        while (i2 > 0) {
            if (this.literal.length() > 0) {
                this.literal.append(' ');
            }
            this.literal.append(this.data[i]);
            i2--;
            i++;
        }
        return this.literal;
    }

    @Override // com.sun.xml.bind.v2.runtime.output.Pcdata, java.lang.CharSequence
    public String toString() {
        return this.literal.toString();
    }

    @Override // com.sun.xml.bind.v2.runtime.output.Pcdata
    public void writeTo(UTF8XmlOutput uTF8XmlOutput) throws IOException {
        int i = this.start;
        int i2 = this.len;
        while (i2 > 0) {
            if (i2 != this.len) {
                uTF8XmlOutput.write(32);
            }
            uTF8XmlOutput.text(this.data[i]);
            i2--;
            i++;
        }
    }
}
