package s2;

import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public final class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f22168a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f22169b;
    public final boolean c;

    public r(String str, boolean z6, boolean z10) {
        this.f22168a = str;
        this.f22169b = z6;
        this.c = z10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != r.class) {
            return false;
        }
        r rVar = (r) obj;
        return TextUtils.equals(this.f22168a, rVar.f22168a) && this.f22169b == rVar.f22169b && this.c == rVar.c;
    }

    public final int hashCode() {
        return ((M5.b.c(this.f22168a, 31, 31) + (this.f22169b ? 1231 : 1237)) * 31) + (this.c ? 1231 : 1237);
    }
}
