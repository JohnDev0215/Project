package com.android.settingslib.drawer;

import android.content.Intent;
import android.content.pm.ComponentInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.UserHandle;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import java.util.ArrayList;
import java.util.Comparator;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public abstract class Tile implements Parcelable {
    public static final Parcelable.Creator<Tile> CREATOR = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ArrayList<UserHandle> f1352c = new ArrayList<>();
    private final String d;
    private final String e;
    protected ComponentInfo f;
    private Bundle g;
    private String h;

    @VisibleForTesting
    long mLastUpdateTime;

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements Parcelable.Creator<Tile> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Tile createFromParcel(Parcel parcel) {
            boolean z = parcel.readBoolean();
            parcel.setDataPosition(0);
            return z ? new ProviderTile(parcel) : new ActivityTile(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Tile[] newArray(int i) {
            return new Tile[i];
        }
    }

    static {
        b bVar = new Comparator() { // from class: com.android.settingslib.drawer.b
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return Tile.e((Tile) obj, (Tile) obj2);
            }
        };
    }

    public Tile(ComponentInfo componentInfo, String str) {
        this.f = componentInfo;
        this.d = componentInfo.packageName;
        this.e = componentInfo.name;
        this.h = str;
        new Intent().setClassName(this.d, this.e);
    }

    static boolean d(Bundle bundle) {
        String string = bundle != null ? bundle.getString("com.android.settings.profile") : "all_profiles";
        return TextUtils.equals(string != null ? string : "all_profiles", "primary_profile_only");
    }

    static /* synthetic */ int e(Tile tile, Tile tile2) {
        return tile2.b() - tile.b();
    }

    public Bundle a() {
        return this.g;
    }

    public int b() {
        if (c()) {
            return this.g.getInt("com.android.settings.order");
        }
        return 0;
    }

    public boolean c() {
        return this.g.containsKey("com.android.settings.order") && (this.g.get("com.android.settings.order") instanceof Integer);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void f(Bundle bundle) {
        this.g = bundle;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBoolean(this instanceof ProviderTile);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        int size = this.f1352c.size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            this.f1352c.get(i2).writeToParcel(parcel, i);
        }
        parcel.writeString(this.h);
        parcel.writeBundle(this.g);
    }

    Tile(Parcel parcel) {
        parcel.readBoolean();
        this.d = parcel.readString();
        this.e = parcel.readString();
        new Intent().setClassName(this.d, this.e);
        int i = parcel.readInt();
        for (int i2 = 0; i2 < i; i2++) {
            this.f1352c.add((UserHandle) UserHandle.CREATOR.createFromParcel(parcel));
        }
        this.h = parcel.readString();
        this.g = parcel.readBundle();
    }
}
