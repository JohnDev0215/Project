package b.a.a.i.h;

import java.util.Objects;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class ta {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f780a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f781b;

    public ta(String str) {
        this.f780a = str;
    }

    public String a() {
        return this.f781b;
    }

    public String b() {
        return this.f780a;
    }

    public void c(String str) {
        this.f781b = str;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return this == obj || ((ta) obj).b().equals(b());
    }

    public int hashCode() {
        return Objects.hash(this.f780a);
    }

    public String toString() {
        return "CommonModel{title='" + this.f780a + "'data='" + this.f781b + "'}";
    }
}
