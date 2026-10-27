package n3;

import java.util.Objects;
import l6.c;

/* JADX INFO: compiled from: SimpleDigitalKey.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @c("isOwnerKeyFlag")
    public int f6516a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @c("keyNo")
    public String f6517b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @c("keyType")
    public byte f6518c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @c("userPro")
    public byte f6519d;

    public boolean a() {
        return this.f6519d == 2 && this.f6518c == 9;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || a.class != obj.getClass()) {
            return false;
        }
        a aVar = (a) obj;
        return this.f6516a == aVar.f6516a && Objects.equals(this.f6517b, aVar.f6517b);
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.f6516a), this.f6517b);
    }

    public String toString() {
        return "SimpleDigitalKey{isOwnerKey=" + this.f6516a + ", keyNo='" + this.f6517b + "'}";
    }
}
