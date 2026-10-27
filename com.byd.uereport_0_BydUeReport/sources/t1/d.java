package t1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: KeyPath.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<String> f8008a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public e f8009b;

    public d(String... strArr) {
        this.f8008a = Arrays.asList(strArr);
    }

    public d a(String str) {
        d dVar = new d(this);
        dVar.f8008a.add(str);
        return dVar;
    }

    public final boolean b() {
        return this.f8008a.get(r0.size() - 1).equals("**");
    }

    public boolean c(String str, int i8) {
        if (i8 >= this.f8008a.size()) {
            return false;
        }
        boolean z7 = i8 == this.f8008a.size() - 1;
        String str2 = this.f8008a.get(i8);
        if (!str2.equals("**")) {
            return (z7 || (i8 == this.f8008a.size() + (-2) && b())) && (str2.equals(str) || str2.equals("*"));
        }
        if (!z7 && this.f8008a.get(i8 + 1).equals(str)) {
            return i8 == this.f8008a.size() + (-2) || (i8 == this.f8008a.size() + (-3) && b());
        }
        if (z7) {
            return true;
        }
        int i9 = i8 + 1;
        if (i9 < this.f8008a.size() - 1) {
            return false;
        }
        return this.f8008a.get(i9).equals(str);
    }

    public e d() {
        return this.f8009b;
    }

    public int e(String str, int i8) {
        if (f(str)) {
            return 0;
        }
        if (this.f8008a.get(i8).equals("**")) {
            return (i8 != this.f8008a.size() - 1 && this.f8008a.get(i8 + 1).equals(str)) ? 2 : 0;
        }
        return 1;
    }

    public final boolean f(String str) {
        return "__container".equals(str);
    }

    public boolean g(String str, int i8) {
        if (f(str)) {
            return true;
        }
        if (i8 >= this.f8008a.size()) {
            return false;
        }
        return this.f8008a.get(i8).equals(str) || this.f8008a.get(i8).equals("**") || this.f8008a.get(i8).equals("*");
    }

    public boolean h(String str, int i8) {
        return "__container".equals(str) || i8 < this.f8008a.size() - 1 || this.f8008a.get(i8).equals("**");
    }

    public d i(e eVar) {
        d dVar = new d(this);
        dVar.f8009b = eVar;
        return dVar;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("KeyPath{keys=");
        sb.append(this.f8008a);
        sb.append(",resolved=");
        sb.append(this.f8009b != null);
        sb.append('}');
        return sb.toString();
    }

    public d(d dVar) {
        this.f8008a = new ArrayList(dVar.f8008a);
        this.f8009b = dVar.f8009b;
    }
}
