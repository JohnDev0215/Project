package androidx.lifecycle;

import androidx.lifecycle.d;
import defpackage.b71;
import kotlin.jvm.internal.SourceDebugExtension;

/* JADX INFO: compiled from: SavedStateHandleController.kt */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nSavedStateHandleController.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SavedStateHandleController.kt\nandroidx/lifecycle/SavedStateHandleController\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,41:1\n1#2:42\n*E\n"})
public final class SavedStateHandleController implements f {
    @Override // androidx.lifecycle.f
    public final void a(b71 b71Var, d.a aVar) {
        if (aVar == d.a.ON_DESTROY) {
            b71Var.j1().b(this);
        }
    }
}
