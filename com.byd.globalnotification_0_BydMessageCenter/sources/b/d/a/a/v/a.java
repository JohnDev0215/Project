package b.d.a.a.v;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class a implements Parcelable {
    public static final Parcelable.Creator<a> CREATOR = new C0043a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final o f2065a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final o f2066b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final b f2067c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public o f2068d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f2069e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f2070f;

    /* JADX INFO: renamed from: b.d.a.a.v.a$a, reason: collision with other inner class name */
    public static class C0043a implements Parcelable.Creator<a> {
        @Override // android.os.Parcelable.Creator
        public a createFromParcel(Parcel parcel) {
            return new a((o) parcel.readParcelable(o.class.getClassLoader()), (o) parcel.readParcelable(o.class.getClassLoader()), (b) parcel.readParcelable(b.class.getClassLoader()), (o) parcel.readParcelable(o.class.getClassLoader()), null);
        }

        @Override // android.os.Parcelable.Creator
        public a[] newArray(int i) {
            return new a[i];
        }
    }

    public interface b extends Parcelable {
        boolean g(long j);
    }

    public a(o oVar, o oVar2, b bVar, o oVar3, C0043a c0043a) {
        this.f2065a = oVar;
        this.f2066b = oVar2;
        this.f2068d = oVar3;
        this.f2067c = bVar;
        if (oVar3 != null && oVar.f2104a.compareTo(oVar3.f2104a) > 0) {
            throw new IllegalArgumentException("start Month cannot be after current Month");
        }
        if (oVar3 != null && oVar3.f2104a.compareTo(oVar2.f2104a) > 0) {
            throw new IllegalArgumentException("current Month cannot be after end Month");
        }
        this.f2070f = oVar.j(oVar2) + 1;
        this.f2069e = (oVar2.f2106c - oVar.f2106c) + 1;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.f2065a.equals(aVar.f2065a) && this.f2066b.equals(aVar.f2066b) && Objects.equals(this.f2068d, aVar.f2068d) && this.f2067c.equals(aVar.f2067c);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.f2065a, this.f2066b, this.f2068d, this.f2067c});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f2065a, 0);
        parcel.writeParcelable(this.f2066b, 0);
        parcel.writeParcelable(this.f2068d, 0);
        parcel.writeParcelable(this.f2067c, 0);
    }
}
