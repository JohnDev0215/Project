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

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final UUID f2608e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final int f2609g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final Bundle f2610h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final Bundle f2611i;

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
        public NavBackStackEntryState[] newArray(int i7) {
            return new NavBackStackEntryState[i7];
        }
    }

    NavBackStackEntryState(e eVar) {
        this.f2608e = eVar.f2650k;
        this.f2609g = eVar.e().k();
        this.f2610h = eVar.b();
        Bundle bundle = new Bundle();
        this.f2611i = bundle;
        eVar.l(bundle);
    }

    Bundle b() {
        return this.f2610h;
    }

    int c() {
        return this.f2609g;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    Bundle h() {
        return this.f2611i;
    }

    UUID j() {
        return this.f2608e;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i7) {
        parcel.writeString(this.f2608e.toString());
        parcel.writeInt(this.f2609g);
        parcel.writeBundle(this.f2610h);
        parcel.writeBundle(this.f2611i);
    }

    NavBackStackEntryState(Parcel parcel) {
        this.f2608e = UUID.fromString(parcel.readString());
        this.f2609g = parcel.readInt();
        this.f2610h = parcel.readBundle(NavBackStackEntryState.class.getClassLoader());
        this.f2611i = parcel.readBundle(NavBackStackEntryState.class.getClassLoader());
    }
}
