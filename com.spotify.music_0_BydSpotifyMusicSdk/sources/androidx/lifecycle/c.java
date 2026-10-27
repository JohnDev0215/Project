package androidx.lifecycle;

import android.os.Bundle;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import p.hk0;
import p.ju2;
import p.mu2;
import p.o41;
import p.py0;
import p.q40;
import p.r22;
import p.r62;
import p.ry0;
import p.u4;
import p.u62;
import p.us;
import p.v03;
import p.v4;
import p.v62;
import p.v81;
import p.zu;

/* JADX INFO: loaded from: classes.dex */
public final class c implements u62 {
    public final v62 a;
    public boolean b;
    public Bundle c;
    public final v81 d;

    public c(v62 v62Var, final mu2 mu2Var) {
        v03.j(v62Var, "savedStateRegistry");
        v03.j(mu2Var, "viewModelStoreOwner");
        this.a = v62Var;
        this.d = kotlin.a.c(new py0() { // from class: androidx.lifecycle.SavedStateHandlesProvider$viewModel$2
            {
                super(0);
            }

            @Override // p.py0
            public final Object invoke() {
                mu2 mu2Var2 = mu2Var;
                v03.j(mu2Var2, "<this>");
                hk0 hk0Var = new hk0(13);
                us usVarA = r22.a(r62.class);
                SavedStateHandleSupport$savedStateHandlesVM$1$1 savedStateHandleSupport$savedStateHandlesVM$1$1 = new ry0() { // from class: androidx.lifecycle.SavedStateHandleSupport$savedStateHandlesVM$1$1
                    @Override // p.ry0
                    public final Object invoke(Object obj) {
                        v03.j((q40) obj, "$this$initializer");
                        return new r62();
                    }
                };
                v03.j(savedStateHandleSupport$savedStateHandlesVM$1$1, "initializer");
                ((List) hk0Var.e).add(new ju2(zu.f0(usVarA), savedStateHandleSupport$savedStateHandlesVM$1$1));
                ju2[] ju2VarArr = (ju2[]) ((List) hk0Var.e).toArray(new ju2[0]);
                return (r62) new v4(mu2Var2, new o41((ju2[]) Arrays.copyOf(ju2VarArr, ju2VarArr.length))).m(r62.class, "androidx.lifecycle.internal.SavedStateHandlesVM");
            }
        });
    }

    @Override // p.u62
    public final Bundle a() {
        Bundle bundle = new Bundle();
        Bundle bundle2 = this.c;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
        Iterator it = ((r62) this.d.getValue()).c.entrySet().iterator();
        if (!it.hasNext()) {
            this.b = false;
            return bundle;
        }
        Map.Entry entry = (Map.Entry) it.next();
        u4.z(entry.getValue());
        throw null;
    }
}
