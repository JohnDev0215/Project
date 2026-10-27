package c.n;

import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: TransitionValues.java */
/* JADX INFO: loaded from: classes.dex */
public class s {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public View f2494b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<String, Object> f2493a = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final ArrayList<m> f2495c = new ArrayList<>();

    @Deprecated
    public s() {
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof s)) {
            return false;
        }
        s sVar = (s) obj;
        return this.f2494b == sVar.f2494b && this.f2493a.equals(sVar.f2493a);
    }

    public int hashCode() {
        return (this.f2494b.hashCode() * 31) + this.f2493a.hashCode();
    }

    public String toString() {
        String str = (("TransitionValues@" + Integer.toHexString(hashCode()) + ":\n") + "    view = " + this.f2494b + "\n") + "    values:";
        for (String str2 : this.f2493a.keySet()) {
            str = str + "    " + str2 + ": " + this.f2493a.get(str2) + "\n";
        }
        return str;
    }

    public s(View view) {
        this.f2494b = view;
    }
}
