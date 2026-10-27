package androidx.lifecycle;

import androidx.lifecycle.d;
import androidx.savedstate.a;
import defpackage.bn3;
import defpackage.ce2;
import defpackage.ge2;
import defpackage.i21;
import defpackage.kd2;
import java.util.Iterator;
import java.util.Map;
import kotlin.jvm.internal.SourceDebugExtension;

/* JADX INFO: compiled from: SavedStateHandleSupport.kt */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nSavedStateHandleSupport.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SavedStateHandleSupport.kt\nandroidx/lifecycle/SavedStateHandleSupport\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 InitializerViewModelFactory.kt\nandroidx/lifecycle/viewmodel/InitializerViewModelFactoryKt\n*L\n1#1,221:1\n1#2:222\n31#3:223\n63#3,2:224\n*S KotlinDebug\n*F\n+ 1 SavedStateHandleSupport.kt\nandroidx/lifecycle/SavedStateHandleSupport\n*L\n109#1:223\n110#1:224,2\n*E\n"})
public final class k {
    public static final b a = new b();
    public static final c b = new c();
    public static final a c = new a();

    /* JADX INFO: compiled from: SavedStateHandleSupport.kt */
    public static final class a {
    }

    /* JADX INFO: compiled from: SavedStateHandleSupport.kt */
    public static final class b {
    }

    /* JADX INFO: compiled from: SavedStateHandleSupport.kt */
    public static final class c {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T extends ge2 & bn3> void a(T t) {
        a.b bVar;
        i21.e(t, "<this>");
        d.b bVar2 = t.j1().c;
        if (!(bVar2 == d.b.INITIALIZED || bVar2 == d.b.CREATED)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        androidx.savedstate.a aVarS0 = t.s0();
        aVarS0.getClass();
        Iterator<Map.Entry<String, a.b>> it = aVarS0.a.iterator();
        while (true) {
            kd2.e eVar = (kd2.e) it;
            if (!eVar.hasNext()) {
                bVar = null;
                break;
            }
            Map.Entry entry = (Map.Entry) eVar.next();
            i21.d(entry, "components");
            String str = (String) entry.getKey();
            bVar = (a.b) entry.getValue();
            if (i21.a(str, "androidx.lifecycle.internal.SavedStateHandlesProvider")) {
                break;
            }
        }
        if (bVar == null) {
            ce2 ce2Var = new ce2(t.s0(), t);
            t.s0().b("androidx.lifecycle.internal.SavedStateHandlesProvider", ce2Var);
            t.j1().a(new SavedStateHandleAttacher(ce2Var));
        }
    }
}
