package defpackage;

import android.text.TextUtils;

/* JADX INFO: compiled from: ImageTile.java */
/* JADX INFO: loaded from: classes2.dex */
public class gx0 {
    public final String a;
    public final String b;
    public final String c;

    public gx0(String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof gx0)) {
            return false;
        }
        gx0 gx0Var = (gx0) obj;
        return TextUtils.equals(this.a, gx0Var.a) && TextUtils.equals(this.b, gx0Var.b) && TextUtils.equals(this.c, gx0Var.c);
    }
}
