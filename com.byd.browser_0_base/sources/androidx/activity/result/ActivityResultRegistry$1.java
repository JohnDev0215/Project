package androidx.activity.result;

import androidx.lifecycle.d;
import androidx.lifecycle.f;
import defpackage.b71;

/* JADX INFO: loaded from: classes.dex */
class ActivityResultRegistry$1 implements f {
    @Override // androidx.lifecycle.f
    public final void a(b71 b71Var, d.a aVar) {
        if (d.a.ON_START.equals(aVar) || d.a.ON_STOP.equals(aVar) || d.a.ON_DESTROY.equals(aVar)) {
            throw null;
        }
    }
}
