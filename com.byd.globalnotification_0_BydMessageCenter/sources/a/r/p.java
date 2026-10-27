package a.r;

import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public View f1349b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<String, Object> f1348a = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList<i> f1350c = new ArrayList<>();

    @Deprecated
    public p() {
    }

    public p(View view) {
        this.f1349b = view;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof p)) {
            return false;
        }
        p pVar = (p) obj;
        return this.f1349b == pVar.f1349b && this.f1348a.equals(pVar.f1348a);
    }

    public int hashCode() {
        return this.f1348a.hashCode() + (this.f1349b.hashCode() * 31);
    }

    public String toString() {
        StringBuilder sbD = b.a.a.a.a.d("TransitionValues@");
        sbD.append(Integer.toHexString(hashCode()));
        sbD.append(":\n");
        String strC = b.a.a.a.a.c(sbD.toString() + "    view = " + this.f1349b + "\n", "    values:");
        for (String str : this.f1348a.keySet()) {
            strC = strC + "    " + str + ": " + this.f1348a.get(str) + "\n";
        }
        return strC;
    }
}
