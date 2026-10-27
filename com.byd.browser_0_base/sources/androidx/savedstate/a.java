package androidx.savedstate;

import android.annotation.SuppressLint;
import android.os.Bundle;
import defpackage.ge2;
import defpackage.i21;
import defpackage.kd2;
import kotlin.jvm.internal.SourceDebugExtension;

/* JADX INFO: compiled from: SavedStateRegistry.kt */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedApi"})
@SourceDebugExtension({"SMAP\nSavedStateRegistry.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SavedStateRegistry.kt\nandroidx/savedstate/SavedStateRegistry\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,272:1\n1#2:273\n*E\n"})
public final class a {
    public final kd2<String, b> a = new kd2<>();
    public boolean b;
    public Bundle c;
    public boolean d;

    /* JADX INFO: renamed from: androidx.savedstate.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SavedStateRegistry.kt */
    public interface InterfaceC0027a {
        void a(ge2 ge2Var);
    }

    /* JADX INFO: compiled from: SavedStateRegistry.kt */
    public interface b {
        Bundle a();
    }

    public final Bundle a(String str) {
        if (!this.d) {
            throw new IllegalStateException("You can consumeRestoredStateForKey only after super.onCreate of corresponding component".toString());
        }
        Bundle bundle = this.c;
        if (bundle == null) {
            return null;
        }
        Bundle bundle2 = bundle.getBundle(str);
        Bundle bundle3 = this.c;
        if (bundle3 != null) {
            bundle3.remove(str);
        }
        Bundle bundle4 = this.c;
        if (!((bundle4 == null || bundle4.isEmpty()) ? false : true)) {
            this.c = null;
        }
        return bundle2;
    }

    public final void b(String str, b bVar) {
        b bVar2;
        i21.e(bVar, "provider");
        kd2<String, b> kd2Var = this.a;
        kd2.c<String, b> cVarA = kd2Var.a(str);
        if (cVarA != null) {
            bVar2 = cVarA.f;
        } else {
            kd2.c<K, V> cVar = new kd2.c<>(str, bVar);
            kd2Var.h++;
            kd2.c cVar2 = kd2Var.f;
            if (cVar2 == null) {
                kd2Var.e = cVar;
                kd2Var.f = cVar;
            } else {
                cVar2.g = cVar;
                cVar.h = cVar2;
                kd2Var.f = cVar;
            }
            bVar2 = null;
        }
        if (!(bVar2 == null)) {
            throw new IllegalArgumentException("SavedStateProvider with the given key is already registered".toString());
        }
    }
}
