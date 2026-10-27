package n3;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.lifecycle.EnumC0981t;

/* JADX INFO: renamed from: n3.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1946k implements Parcelable {
    public static final Parcelable.Creator<C1946k> CREATOR = new android.support.v4.media.a(24);
    public final String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f19875m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Bundle f19876n;
    public final Bundle o;

    public C1946k(C1945j c1945j) {
        A7.m.f(c1945j, "entry");
        this.l = c1945j.f19868q;
        this.f19875m = c1945j.f19865m.f19917q;
        this.f19876n = c1945j.c();
        Bundle bundle = new Bundle();
        this.o = bundle;
        c1945j.f19871t.m(bundle);
    }

    public final C1945j a(Context context, y yVar, EnumC0981t enumC0981t, r rVar) {
        A7.m.f(context, "context");
        A7.m.f(enumC0981t, "hostLifecycleState");
        Bundle bundle = this.f19876n;
        if (bundle != null) {
            bundle.setClassLoader(context.getClassLoader());
        } else {
            bundle = null;
        }
        Bundle bundle2 = bundle;
        String str = this.l;
        A7.m.f(str, "id");
        return new C1945j(context, yVar, bundle2, enumC0981t, rVar, str, this.o);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        A7.m.f(parcel, "parcel");
        parcel.writeString(this.l);
        parcel.writeInt(this.f19875m);
        parcel.writeBundle(this.f19876n);
        parcel.writeBundle(this.o);
    }

    public C1946k(Parcel parcel) {
        A7.m.f(parcel, "inParcel");
        String string = parcel.readString();
        A7.m.c(string);
        this.l = string;
        this.f19875m = parcel.readInt();
        this.f19876n = parcel.readBundle(C1946k.class.getClassLoader());
        Bundle bundle = parcel.readBundle(C1946k.class.getClassLoader());
        A7.m.c(bundle);
        this.o = bundle;
    }
}
