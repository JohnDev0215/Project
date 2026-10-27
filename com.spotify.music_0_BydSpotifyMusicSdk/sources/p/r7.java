package p;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class r7 implements u62 {
    public final /* synthetic */ int a = 0;
    public final Object b;

    public r7(v62 v62Var) {
        v03.j(v62Var, "registry");
        this.b = new LinkedHashSet();
        v62Var.b("androidx.savedstate.Restarter", this);
    }

    @Override // p.u62
    public final Bundle a() {
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                Bundle bundle = new Bundle();
                ((t7) obj).u().getClass();
                return bundle;
            default:
                Bundle bundle2 = new Bundle();
                bundle2.putStringArrayList("classes_to_restore", new ArrayList<>((Set) obj));
                return bundle2;
        }
    }

    public r7(t7 t7Var) {
        this.b = t7Var;
    }
}
