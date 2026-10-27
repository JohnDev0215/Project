package a.j.a;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public abstract class a implements Parcelable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Parcelable f892a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f891b = new C0020a();
    public static final Parcelable.Creator<a> CREATOR = new b();

    /* JADX INFO: renamed from: a.j.a.a$a, reason: collision with other inner class name */
    public static class C0020a extends a {
        public C0020a() {
            super((C0020a) null);
        }
    }

    public static class b implements Parcelable.ClassLoaderCreator<a> {
        @Override // android.os.Parcelable.Creator
        public Object createFromParcel(Parcel parcel) {
            if (parcel.readParcelable(null) == null) {
                return a.f891b;
            }
            throw new IllegalStateException("superState must be null");
        }

        @Override // android.os.Parcelable.Creator
        public Object[] newArray(int i) {
            return new a[i];
        }

        @Override // android.os.Parcelable.ClassLoaderCreator
        public a createFromParcel(Parcel parcel, ClassLoader classLoader) {
            if (parcel.readParcelable(classLoader) == null) {
                return a.f891b;
            }
            throw new IllegalStateException("superState must be null");
        }
    }

    public a(C0020a c0020a) {
        this.f892a = null;
    }

    public a(Parcel parcel, ClassLoader classLoader) {
        Parcelable parcelable = parcel.readParcelable(classLoader);
        this.f892a = parcelable == null ? f891b : parcelable;
    }

    public a(Parcelable parcelable) {
        if (parcelable == null) {
            throw new IllegalArgumentException("superState must not be null");
        }
        this.f892a = parcelable == f891b ? null : parcelable;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f892a, i);
    }
}
