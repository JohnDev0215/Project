package b.o;

import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: TransitionValues.java */
/* JADX INFO: loaded from: classes.dex */
public class s {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public View f2920b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<String, Object> f2919a = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final ArrayList<m> f2921c = new ArrayList<>();

    @Deprecated
    public s() {
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof s)) {
            return false;
        }
        s sVar = (s) obj;
        return this.f2920b == sVar.f2920b && this.f2919a.equals(sVar.f2919a);
    }

    public int hashCode() {
        return (this.f2920b.hashCode() * 31) + this.f2919a.hashCode();
    }

    public String toString() {
        String str = (("TransitionValues@" + Integer.toHexString(hashCode()) + ":\n") + "    view = " + this.f2920b + "\n") + "    values:";
        for (String str2 : this.f2919a.keySet()) {
            str = str + "    " + str2 + ": " + this.f2919a.get(str2) + "\n";
        }
        return str;
    }

    public s(View view) {
        this.f2920b = view;
    }
}
