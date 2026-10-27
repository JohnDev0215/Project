package defpackage;

import java.io.Serializable;

/* JADX INFO: compiled from: ContentSettingException.java */
/* JADX INFO: loaded from: classes2.dex */
public final class wy implements Serializable {
    public final int e;
    public final String f;
    public final String g;
    public final Integer h;
    public final String i;
    public final boolean j;

    public wy(int i, String str, String str2, Integer num, String str3, boolean z) {
        this.e = i;
        this.f = str;
        this.g = str2;
        this.h = num;
        this.i = str3;
        this.j = z;
    }

    public wy(int i, String str, Integer num) {
        this(i, str, "*", num, "", false);
    }
}
