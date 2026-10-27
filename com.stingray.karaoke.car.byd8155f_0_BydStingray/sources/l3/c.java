package L3;

import A7.m;
import android.content.Context;
import android.util.DisplayMetrics;

/* JADX INFO: loaded from: classes.dex */
public final class c implements h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f4000a;

    public c(Context context) {
        this.f4000a = context;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof c) {
            if (m.a(this.f4000a, ((c) obj).f4000a)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.f4000a.hashCode();
    }

    @Override // L3.h
    public final Object i(z3.f fVar) {
        DisplayMetrics displayMetrics = this.f4000a.getResources().getDisplayMetrics();
        a aVar = new a(Math.max(displayMetrics.widthPixels, displayMetrics.heightPixels));
        return new g(aVar, aVar);
    }
}
