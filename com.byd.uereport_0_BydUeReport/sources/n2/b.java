package n2;

import java.util.Objects;

/* JADX INFO: compiled from: VehicleConfig.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f6513a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f6514b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f6515c;

    public b(int i8, int i9, boolean z7) {
        this.f6513a = -1;
        this.f6514b = -1;
        this.f6515c = false;
        this.f6513a = i8;
        this.f6514b = i9;
        this.f6515c = z7;
    }

    public int a() {
        return this.f6513a;
    }

    public int b() {
        return this.f6514b;
    }

    public boolean c() {
        return this.f6515c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b.class != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        return this.f6513a == bVar.f6513a && this.f6514b == bVar.f6514b && this.f6515c == bVar.f6515c;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.f6513a), Integer.valueOf(this.f6514b), Boolean.valueOf(this.f6515c));
    }

    public String toString() {
        return "VehicleConfig{carModelCode=" + this.f6513a + ", uiStyle=" + this.f6514b + ", rightRudder=" + this.f6515c + '}';
    }
}
