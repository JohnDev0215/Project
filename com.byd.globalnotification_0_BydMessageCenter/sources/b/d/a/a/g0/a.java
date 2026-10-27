package b.d.a.a.g0;

import a.e.h;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class a extends a.j.a.a {
    public static final Parcelable.Creator<a> CREATOR = new C0037a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final h<String, Bundle> f1894c;

    /* JADX INFO: renamed from: b.d.a.a.g0.a$a, reason: collision with other inner class name */
    public static class C0037a implements Parcelable.ClassLoaderCreator<a> {
        @Override // android.os.Parcelable.Creator
        public Object createFromParcel(Parcel parcel) {
            return new a(parcel, null, null);
        }

        @Override // android.os.Parcelable.Creator
        public Object[] newArray(int i) {
            return new a[i];
        }

        @Override // android.os.Parcelable.ClassLoaderCreator
        public a createFromParcel(Parcel parcel, ClassLoader classLoader) {
            return new a(parcel, classLoader, null);
        }
    }

    public a(Parcel parcel, ClassLoader classLoader, C0037a c0037a) {
        super(parcel, classLoader);
        int i = parcel.readInt();
        String[] strArr = new String[i];
        parcel.readStringArray(strArr);
        Bundle[] bundleArr = new Bundle[i];
        parcel.readTypedArray(bundleArr, Bundle.CREATOR);
        this.f1894c = new h<>(i);
        for (int i2 = 0; i2 < i; i2++) {
            this.f1894c.put(strArr[i2], bundleArr[i2]);
        }
    }

    public String toString() {
        StringBuilder sbD = b.a.a.a.a.d("ExtendableSavedState{");
        sbD.append(Integer.toHexString(System.identityHashCode(this)));
        sbD.append(" states=");
        sbD.append(this.f1894c);
        sbD.append("}");
        return sbD.toString();
    }

    @Override // a.j.a.a, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f892a, i);
        int i2 = this.f1894c.f490c;
        parcel.writeInt(i2);
        String[] strArr = new String[i2];
        Bundle[] bundleArr = new Bundle[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            strArr[i3] = this.f1894c.h(i3);
            bundleArr[i3] = this.f1894c.k(i3);
        }
        parcel.writeStringArray(strArr);
        parcel.writeTypedArray(bundleArr, 0);
    }
}
