package com.fasterxml.jackson.core.io;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Objects;
import p.bs;

/* JADX INFO: loaded from: classes.dex */
public class ContentReference implements Serializable {
    public static final ContentReference e = new ContentReference(null, false);
    private static final long serialVersionUID = 1;
    protected final boolean _isContentTextual;
    public final transient Object c;
    protected final int _offset = -1;
    protected final int _length = -1;

    public ContentReference(Object obj, boolean z) {
        this._isContentTextual = z;
        this.c = obj;
    }

    public static void a(int[] iArr, int i) {
        int i2 = iArr[0];
        if (i2 < 0) {
            i2 = 0;
        } else if (i2 >= i) {
            i2 = i;
        }
        iArr[0] = i2;
        int i3 = iArr[1];
        int i4 = i - i2;
        if (i3 < 0 || i3 > i4) {
            iArr[1] = i4;
        }
    }

    private void readObject(ObjectInputStream objectInputStream) {
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
    }

    public final String b() {
        String str;
        StringBuilder sb = new StringBuilder(200);
        Object obj = this.c;
        if (obj == null) {
            sb.append("UNKNOWN");
        } else {
            Class<?> cls = obj instanceof Class ? (Class) obj : obj.getClass();
            String name = cls.getName();
            if (name.startsWith("java.")) {
                name = cls.getSimpleName();
            } else if (obj instanceof byte[]) {
                name = "byte[]";
            } else if (obj instanceof char[]) {
                name = "char[]";
            }
            sb.append('(');
            sb.append(name);
            sb.append(')');
            if (this._isContentTextual) {
                int[] iArr = {this._offset, this._length};
                String str2 = " chars";
                if (obj instanceof CharSequence) {
                    CharSequence charSequence = (CharSequence) obj;
                    a(iArr, charSequence.length());
                    int i = iArr[0];
                    str = charSequence.subSequence(i, Math.min(iArr[1], 500) + i).toString();
                } else if (obj instanceof char[]) {
                    char[] cArr = (char[]) obj;
                    a(iArr, cArr.length);
                    str = new String(cArr, iArr[0], Math.min(iArr[1], 500));
                } else if (obj instanceof byte[]) {
                    byte[] bArr = (byte[]) obj;
                    a(iArr, bArr.length);
                    str2 = " bytes";
                    str = new String(bArr, iArr[0], Math.min(iArr[1], 500), Charset.forName("UTF-8"));
                } else {
                    str = null;
                }
                if (str != null) {
                    sb.append('\"');
                    int length = str.length();
                    for (int i2 = 0; i2 < length; i2++) {
                        char cCharAt = str.charAt(i2);
                        if (!Character.isISOControl(cCharAt) || cCharAt == '\r' || cCharAt == '\n') {
                            sb.append(cCharAt);
                        } else {
                            sb.append("\\u");
                            char[] cArr2 = bs.a;
                            sb.append(cArr2[(cCharAt >> '\f') & 15]);
                            sb.append(cArr2[(cCharAt >> '\b') & 15]);
                            sb.append(cArr2[(cCharAt >> 4) & 15]);
                            sb.append(cArr2[cCharAt & 15]);
                        }
                    }
                    sb.append('\"');
                    if (iArr[1] > 500) {
                        sb.append("[truncated ");
                        sb.append(iArr[1] - 500);
                        sb.append(str2);
                        sb.append(']');
                    }
                }
            } else if (obj instanceof byte[]) {
                int length2 = this._length;
                if (length2 < 0) {
                    length2 = ((byte[]) obj).length;
                }
                sb.append('[');
                sb.append(length2);
                sb.append(" bytes]");
            }
        }
        return sb.toString();
    }

    public final boolean c() {
        return this._isContentTextual;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof ContentReference)) {
            return false;
        }
        ContentReference contentReference = (ContentReference) obj;
        if (this._offset != contentReference._offset || this._length != contentReference._length) {
            return false;
        }
        Object obj2 = contentReference.c;
        Object obj3 = this.c;
        if (obj3 == null) {
            return obj2 == null;
        }
        if (obj2 == null) {
            return false;
        }
        return ((obj3 instanceof File) || (obj3 instanceof URL) || (obj3 instanceof URI)) ? obj3.equals(obj2) : obj3 == obj2;
    }

    public final int hashCode() {
        return Objects.hashCode(this.c);
    }

    public Object readResolve() {
        return e;
    }
}
