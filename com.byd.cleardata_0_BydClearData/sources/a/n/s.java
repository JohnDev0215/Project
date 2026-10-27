package a.n;

import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class s {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public View f591b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<String, Object> f590a = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final ArrayList<m> f592c = new ArrayList<>();

    @Deprecated
    public s() {
    }

    public s(View view) {
        this.f591b = view;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof s)) {
            return false;
        }
        s sVar = (s) obj;
        return this.f591b == sVar.f591b && this.f590a.equals(sVar.f590a);
    }

    public int hashCode() {
        return (this.f591b.hashCode() * 31) + this.f590a.hashCode();
    }

    public String toString() {
        String str = (("TransitionValues@" + Integer.toHexString(hashCode()) + ":\n") + "    view = " + this.f591b + "\n") + "    values:";
        for (String str2 : this.f590a.keySet()) {
            str = str + "    " + str2 + ": " + this.f590a.get(str2) + "\n";
        }
        return str;
    }
}
