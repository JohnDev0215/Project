package b.n;

import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: TransitionValues.java */
/* JADX INFO: loaded from: classes.dex */
public class s {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public View f2015b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<String, Object> f2014a = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final ArrayList<m> f2016c = new ArrayList<>();

    @Deprecated
    public s() {
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof s)) {
            return false;
        }
        s sVar = (s) obj;
        return this.f2015b == sVar.f2015b && this.f2014a.equals(sVar.f2014a);
    }

    public int hashCode() {
        return (this.f2015b.hashCode() * 31) + this.f2014a.hashCode();
    }

    public String toString() {
        String str = (("TransitionValues@" + Integer.toHexString(hashCode()) + ":\n") + "    view = " + this.f2015b + "\n") + "    values:";
        for (String str2 : this.f2014a.keySet()) {
            str = str + "    " + str2 + ": " + this.f2014a.get(str2) + "\n";
        }
        return str;
    }

    public s(View view) {
        this.f2015b = view;
    }
}
