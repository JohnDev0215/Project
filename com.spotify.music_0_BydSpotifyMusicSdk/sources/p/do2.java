package p;

import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class do2 {
    public final View b;
    public final HashMap a = new HashMap();
    public final ArrayList c = new ArrayList();

    public do2(View view) {
        this.b = view;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof do2)) {
            return false;
        }
        do2 do2Var = (do2) obj;
        return this.b == do2Var.b && this.a.equals(do2Var.a);
    }

    public final int hashCode() {
        return this.a.hashCode() + (this.b.hashCode() * 31);
    }

    public final String toString() {
        StringBuilder sbF = hf2.f("TransitionValues@" + Integer.toHexString(hashCode()) + ":\n", "    view = ");
        sbF.append(this.b);
        sbF.append("\n");
        String strH = u4.h(sbF.toString(), "    values:");
        HashMap map = this.a;
        for (String str : map.keySet()) {
            strH = strH + "    " + str + ": " + map.get(str) + "\n";
        }
        return strH;
    }
}
