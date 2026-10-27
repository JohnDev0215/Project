package p;

import android.content.Context;
import com.google.android.datatransport.cct.CctBackendFactory;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class bk1 {
    public final ww1 a;
    public final n40 b;
    public final HashMap c;

    public bk1(Context context, n40 n40Var) {
        ww1 ww1Var = new ww1(context, 5);
        this.c = new HashMap();
        this.a = ww1Var;
        this.b = n40Var;
    }

    public final synchronized eo2 a(String str) {
        if (this.c.containsKey(str)) {
            return (eo2) this.c.get(str);
        }
        CctBackendFactory cctBackendFactoryU = this.a.u(str);
        if (cctBackendFactoryU == null) {
            return null;
        }
        n40 n40Var = this.b;
        eo2 eo2VarCreate = cctBackendFactoryU.create(new zh(n40Var.a, n40Var.b, n40Var.c, str));
        this.c.put(str, eo2VarCreate);
        return eo2VarCreate;
    }
}
