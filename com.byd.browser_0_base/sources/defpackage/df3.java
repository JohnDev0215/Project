package defpackage;

import android.annotation.SuppressLint;
import android.view.View;
import androidx.transition.Transition;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: compiled from: TransitionValues.java */
/* JADX INFO: loaded from: classes.dex */
public final class df3 {

    @SuppressLint({"UnknownNullness"})
    public final View b;
    public final HashMap a = new HashMap();
    public final ArrayList<Transition> c = new ArrayList<>();

    @Deprecated
    public df3() {
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof df3)) {
            return false;
        }
        df3 df3Var = (df3) obj;
        return this.b == df3Var.b && this.a.equals(df3Var.a);
    }

    public final int hashCode() {
        return this.a.hashCode() + (this.b.hashCode() * 31);
    }

    public final String toString() {
        StringBuilder sbK = C0173a.k("TransitionValues@" + Integer.toHexString(hashCode()) + ":\n", "    view = ");
        sbK.append(this.b);
        sbK.append("\n");
        String strF = gq2.f(sbK.toString(), "    values:");
        HashMap map = this.a;
        for (String str : map.keySet()) {
            strF = strF + "    " + str + ": " + map.get(str) + "\n";
        }
        return strF;
    }

    public df3(View view) {
        this.b = view;
    }
}
