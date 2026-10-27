package b.b.h.e;

import java.util.Objects;

/* JADX INFO: compiled from: KeyValue.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f1858a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f1859b;

    public a(String str, Object obj) {
        this.f1858a = str;
        this.f1859b = obj;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || a.class != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.f1858a, ((a) obj).f1858a);
    }

    public int hashCode() {
        String str = this.f1858a;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "KeyValue{key='" + this.f1858a + "', value=" + this.f1859b + '}';
    }
}
