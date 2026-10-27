package defpackage;

import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: j7, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0977j7 {
    public View b;
    public final Map a = new HashMap();
    public final ArrayList c = new ArrayList();

    public boolean equals(Object obj) {
        if (!(obj instanceof C0977j7)) {
            return false;
        }
        C0977j7 c0977j7 = (C0977j7) obj;
        return this.b == c0977j7.b && this.a.equals(c0977j7.a);
    }

    public int hashCode() {
        return this.a.hashCode() + (this.b.hashCode() * 31);
    }

    public String toString() {
        StringBuilder sbF = Z7.f("TransitionValues@");
        sbF.append(Integer.toHexString(hashCode()));
        sbF.append(":\n");
        String strC = Z7.c(sbF.toString() + "    view = " + this.b + "\n", "    values:");
        for (String str : this.a.keySet()) {
            strC = strC + "    " + str + ": " + this.a.get(str) + "\n";
        }
        return strC;
    }
}
