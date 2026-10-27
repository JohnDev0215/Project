package b.b.a.d;

import java.util.Objects;

/* JADX INFO: compiled from: VehicleConfig.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f486a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f487b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f488c;

    public b(int i, int i2, boolean z) {
        this.f486a = -1;
        this.f487b = -1;
        this.f488c = false;
        this.f486a = i;
        this.f487b = i2;
        this.f488c = z;
    }

    public int a() {
        return this.f486a;
    }

    public int b() {
        return this.f487b;
    }

    public boolean c() {
        return this.f488c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b.class != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        return this.f486a == bVar.f486a && this.f487b == bVar.f487b && this.f488c == bVar.f488c;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.f486a), Integer.valueOf(this.f487b), Boolean.valueOf(this.f488c));
    }

    public String toString() {
        return "VehicleConfig{carModelCode=" + this.f486a + ", uiStyle=" + this.f487b + ", rightRudder=" + this.f488c + '}';
    }
}
