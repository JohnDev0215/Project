package androidx.navigation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
final class NavBackStackEntryState implements Parcelable {
    public static final Parcelable.Creator<NavBackStackEntryState> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final UUID f1628b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f1629c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Bundle f1630d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Bundle f1631e;

    class a implements Parcelable.Creator<NavBackStackEntryState> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public NavBackStackEntryState createFromParcel(Parcel parcel) {
            return new NavBackStackEntryState(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public NavBackStackEntryState[] newArray(int i2) {
            return new NavBackStackEntryState[i2];
        }
    }

    NavBackStackEntryState(e eVar) {
        this.f1628b = eVar.f1663g;
        this.f1629c = eVar.c().i();
        this.f1630d = eVar.b();
        Bundle bundle = new Bundle();
        this.f1631e = bundle;
        eVar.k(bundle);
    }

    Bundle a() {
        return this.f1630d;
    }

    int c() {
        return this.f1629c;
    }

    Bundle d() {
        return this.f1631e;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    UUID f() {
        return this.f1628b;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f1628b.toString());
        parcel.writeInt(this.f1629c);
        parcel.writeBundle(this.f1630d);
        parcel.writeBundle(this.f1631e);
    }

    NavBackStackEntryState(Parcel parcel) {
        this.f1628b = UUID.fromString(parcel.readString());
        this.f1629c = parcel.readInt();
        this.f1630d = parcel.readBundle(NavBackStackEntryState.class.getClassLoader());
        this.f1631e = parcel.readBundle(NavBackStackEntryState.class.getClassLoader());
    }
}
