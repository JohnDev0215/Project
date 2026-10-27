package com.spotify.localfiles.localfiles;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.parcelize.Parcelize;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
@Parcelize
public final class SortOrder implements Parcelable {
    public static final Parcelable.Creator<SortOrder> CREATOR = new Creator();
    private final String key;
    private final boolean reversed;
    private final SortOrder secondary;

    public static final class Creator implements Parcelable.Creator<SortOrder> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final SortOrder createFromParcel(Parcel parcel) {
            v03.j(parcel, "parcel");
            return new SortOrder(parcel.readString(), parcel.readInt() != 0, parcel.readInt() == 0 ? null : SortOrder.CREATOR.createFromParcel(parcel));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final SortOrder[] newArray(int i) {
            return new SortOrder[i];
        }
    }

    public SortOrder(String str, boolean z, SortOrder sortOrder) {
        v03.j(str, "key");
        this.key = str;
        this.reversed = z;
        this.secondary = sortOrder;
    }

    public static /* synthetic */ SortOrder copy$default(SortOrder sortOrder, String str, boolean z, SortOrder sortOrder2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = sortOrder.key;
        }
        if ((i & 2) != 0) {
            z = sortOrder.reversed;
        }
        if ((i & 4) != 0) {
            sortOrder2 = sortOrder.secondary;
        }
        return sortOrder.copy(str, z, sortOrder2);
    }

    public final SortOrder buildReversed(boolean z) {
        SortOrder sortOrder = this.secondary;
        return copy$default(this, null, z, sortOrder != null ? sortOrder.buildReversed(z) : null, 1, null);
    }

    public final String component1() {
        return this.key;
    }

    public final boolean component2() {
        return this.reversed;
    }

    public final SortOrder component3() {
        return this.secondary;
    }

    public final SortOrder copy(String str, boolean z, SortOrder sortOrder) {
        v03.j(str, "key");
        return new SortOrder(str, z, sortOrder);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SortOrder)) {
            return false;
        }
        SortOrder sortOrder = (SortOrder) obj;
        return v03.b(this.key, sortOrder.key) && this.reversed == sortOrder.reversed && v03.b(this.secondary, sortOrder.secondary);
    }

    public final String getKey() {
        return this.key;
    }

    public final boolean getReversed() {
        return this.reversed;
    }

    public final SortOrder getSecondary() {
        return this.secondary;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    public int hashCode() {
        int iHashCode = this.key.hashCode() * 31;
        boolean z = this.reversed;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        int i = (iHashCode + r1) * 31;
        SortOrder sortOrder = this.secondary;
        return i + (sortOrder == null ? 0 : sortOrder.hashCode());
    }

    public String toString() {
        return "SortOrder(key=" + this.key + ", reversed=" + this.reversed + ", secondary=" + this.secondary + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        v03.j(parcel, "out");
        parcel.writeString(this.key);
        parcel.writeInt(this.reversed ? 1 : 0);
        SortOrder sortOrder = this.secondary;
        if (sortOrder == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            sortOrder.writeToParcel(parcel, i);
        }
    }

    public /* synthetic */ SortOrder(String str, boolean z, SortOrder sortOrder, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? false : z, (i & 4) != 0 ? null : sortOrder);
    }
}
