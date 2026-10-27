package p;

import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes.dex */
public final class le2 {
    public final SharedPreferences.Editor a;
    public boolean b;

    public le2(SharedPreferences.Editor editor, boolean z, oh1 oh1Var) {
        v03.j(oh1Var, "mUtils");
        this.a = editor;
    }

    public final void a() {
        if (!(!this.b)) {
            throw new IllegalStateException("Must not use the same Editor instance twice".toString());
        }
        this.a.apply();
        this.b = true;
    }
}
