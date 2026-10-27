package org.apache.commons.compress.harmony.pack200;

import com.android.dx.cf.attrib.AttSynthetic;
import org.objectweb.asm.ClassReader;

/* JADX INFO: loaded from: classes4.dex */
public class Pack200ClassReader extends ClassReader {
    private boolean anySyntheticAttributes;
    private String fileName;
    private boolean lastConstantHadWideIndex;
    private int lastUnsignedShort;

    public Pack200ClassReader(byte[] bArr) {
        super(bArr);
    }

    public int readUnsignedShort(int i) {
        int unsignedShort = super.readUnsignedShort(i);
        if (this.b[i - 1] == 19) {
            this.lastUnsignedShort = unsignedShort;
        } else {
            this.lastUnsignedShort = -32768;
        }
        return unsignedShort;
    }

    public Object readConst(int i, char[] cArr) {
        this.lastConstantHadWideIndex = i == this.lastUnsignedShort;
        return super.readConst(i, cArr);
    }

    public String readUTF8(int i, char[] cArr) {
        String utf8 = super.readUTF8(i, cArr);
        if (!this.anySyntheticAttributes && AttSynthetic.ATTRIBUTE_NAME.equals(utf8)) {
            this.anySyntheticAttributes = true;
        }
        return utf8;
    }

    public boolean lastConstantHadWideIndex() {
        return this.lastConstantHadWideIndex;
    }

    public boolean hasSyntheticAttributes() {
        return this.anySyntheticAttributes;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public String getFileName() {
        return this.fileName;
    }
}
