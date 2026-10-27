package p;

import android.util.Log;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class fx0 implements s4 {
    public final /* synthetic */ int c;
    public final /* synthetic */ ox0 e;

    public /* synthetic */ fx0(ox0 ox0Var, int i) {
        this.c = i;
        this.e = ox0Var;
    }

    @Override // p.s4
    public final void b(Object obj) {
        int i = this.c;
        ox0 ox0Var = this.e;
        switch (i) {
            case 0:
                Map map = (Map) obj;
                ArrayList arrayList = new ArrayList(map.values());
                int[] iArr = new int[arrayList.size()];
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    iArr[i2] = ((Boolean) arrayList.get(i2)).booleanValue() ? 0 : -1;
                }
                lx0 lx0Var = (lx0) ox0Var.C.pollFirst();
                if (lx0Var != null) {
                    ox0Var.c.l(lx0Var.c);
                    break;
                }
                break;
            default:
                r4 r4Var = (r4) obj;
                lx0 lx0Var2 = (lx0) ox0Var.C.pollFirst();
                if (lx0Var2 != null) {
                    ww0 ww0VarL = ox0Var.c.l(lx0Var2.c);
                    if (ww0VarL != null) {
                        int i3 = r4Var.c;
                        if (Log.isLoggable("FragmentManager", 2)) {
                            ww0VarL.toString();
                            Objects.toString(r4Var.e);
                        }
                        break;
                    }
                }
                break;
        }
    }
}
