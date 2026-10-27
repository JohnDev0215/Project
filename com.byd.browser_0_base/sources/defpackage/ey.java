package defpackage;

import android.text.TextUtils;
import java.util.HashSet;

/* JADX INFO: compiled from: ContactEditor.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ey extends mf0<ra> {
    public final boolean c;
    public final boolean d;
    public final boolean e;
    public final boolean f;
    public final HashSet g = new HashSet();
    public final HashSet h = new HashSet();
    public final HashSet i = new HashSet();
    public jy1 j;
    public cy k;
    public dy l;

    public ey(boolean z, boolean z2, boolean z3, boolean z4) {
        this.c = z;
        this.d = z2;
        this.e = z3;
        this.f = z4;
    }

    public final int a(String str, String str2, String str3) {
        int i = (this.c && TextUtils.isEmpty(str)) ? 1 : 0;
        if (this.d) {
            if (this.k == null) {
                this.k = new cy();
            }
            if (!this.k.isValid(str2)) {
                i |= 2;
            }
        }
        if (!this.e) {
            return i;
        }
        if (this.l == null) {
            this.l = new dy();
        }
        return !this.l.isValid(str3) ? i | 4 : i;
    }
}
