package defpackage;

import android.os.Bundle;
import androidx.lifecycle.c;
import androidx.lifecycle.l;
import androidx.savedstate.a;
import defpackage.g10;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import kotlin.jvm.internal.SourceDebugExtension;

/* JADX INFO: compiled from: SavedStateHandleSupport.kt */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nSavedStateHandleSupport.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SavedStateHandleSupport.kt\nandroidx/lifecycle/SavedStateHandlesProvider\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,221:1\n215#2,2:222\n*S KotlinDebug\n*F\n+ 1 SavedStateHandleSupport.kt\nandroidx/lifecycle/SavedStateHandlesProvider\n*L\n146#1:222,2\n*E\n"})
public final class ce2 implements a.b {
    public final androidx.savedstate.a a;
    public boolean b;
    public Bundle c;
    public final yx2 d;

    /* JADX INFO: compiled from: SavedStateHandleSupport.kt */
    public static final class a extends z41 implements yq0<de2> {
        public final /* synthetic */ bn3 f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(bn3 bn3Var) {
            super(0);
            this.f = bn3Var;
        }

        @Override // defpackage.yq0
        public final de2 a() {
            bn3 bn3Var = this.f;
            i21.e(bn3Var, "<this>");
            ArrayList arrayList = new ArrayList();
            fa2.a.getClass();
            Class<?> clsA = new nt(de2.class).a();
            i21.c(clsA, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-java>>");
            arrayList.add(new zm3(clsA));
            zm3[] zm3VarArr = (zm3[]) arrayList.toArray(new zm3[0]);
            return (de2) new l(bn3Var.h0(), new b01((zm3[]) Arrays.copyOf(zm3VarArr, zm3VarArr.length)), bn3Var instanceof c ? ((c) bn3Var).S() : g10.a.b).b(de2.class, "androidx.lifecycle.internal.SavedStateHandlesVM");
        }
    }

    public ce2(androidx.savedstate.a aVar, bn3 bn3Var) {
        i21.e(aVar, "savedStateRegistry");
        i21.e(bn3Var, "viewModelStoreOwner");
        this.a = aVar;
        this.d = new yx2(new a(bn3Var));
    }

    @Override // androidx.savedstate.a.b
    public final Bundle a() {
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.c;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
        for (Map.Entry entry : ((de2) this.d.a()).c.entrySet()) {
            String str = (String) entry.getKey();
            Bundle bundleA = ((ae2) entry.getValue()).e.a();
            if (!i21.a(bundleA, Bundle.EMPTY)) {
                bundle.putBundle(str, bundleA);
            }
        }
        this.b = false;
        return bundle;
    }
}
