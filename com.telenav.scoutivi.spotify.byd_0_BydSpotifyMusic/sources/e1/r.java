package e1;

import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: TransitionValues.java */
/* JADX INFO: loaded from: classes.dex */
public class r {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public View f6161b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<String, Object> f6160a = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final ArrayList<l> f6162c = new ArrayList<>();

    @Deprecated
    public r() {
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof r)) {
            return false;
        }
        r rVar = (r) obj;
        return this.f6161b == rVar.f6161b && this.f6160a.equals(rVar.f6160a);
    }

    public int hashCode() {
        return (this.f6161b.hashCode() * 31) + this.f6160a.hashCode();
    }

    public String toString() {
        String str = (("TransitionValues@" + Integer.toHexString(hashCode()) + ":\n") + "    view = " + this.f6161b + "\n") + "    values:";
        for (String str2 : this.f6160a.keySet()) {
            str = str + "    " + str2 + ": " + this.f6160a.get(str2) + "\n";
        }
        return str;
    }

    public r(View view) {
        this.f6161b = view;
    }
}
