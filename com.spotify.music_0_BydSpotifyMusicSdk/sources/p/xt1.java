package p;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.common.collect.ImmutableMap;
import com.spotify.player.legacyplayer.PlayerTrack;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class xt1 implements Parcelable {
    public static final Parcelable.Creator<xt1> CREATOR = new bg1(25);
    public final String c;
    public final String e;
    public final PlayerTrack[] k;
    public final ImmutableMap l;

    public xt1(String str, String str2, PlayerTrack[] playerTrackArr, Map map) {
        this.c = str;
        this.e = str2;
        this.k = playerTrackArr;
        if (map == null || map.isEmpty()) {
            this.l = ImmutableMap.d();
        } else {
            this.l = ImmutableMap.b(map);
        }
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof xt1)) {
            return false;
        }
        xt1 xt1Var = (xt1) obj;
        String str = this.c;
        if (str == null ? xt1Var.c != null : !str.equals(xt1Var.c)) {
            return false;
        }
        String str2 = this.e;
        if (str2 == null ? xt1Var.e != null : !str2.equals(xt1Var.e)) {
            return false;
        }
        if (Arrays.equals(this.k, xt1Var.k)) {
            return this.l.equals(xt1Var.l);
        }
        return false;
    }

    public final int hashCode() {
        String str = this.c;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.e;
        return this.l.hashCode() + ((((iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + Arrays.hashCode(this.k)) * 31);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        ux2.C(parcel, this.l);
        parcel.writeString(this.c);
        parcel.writeString(this.e);
        parcel.writeTypedArray(this.k, i);
    }

    public xt1(Parcel parcel) {
        int i = parcel.readInt();
        com.google.common.collect.b bVarA = ImmutableMap.a();
        while (true) {
            int i2 = i - 1;
            if (i > 0) {
                bVarA.b(parcel.readString(), parcel.readString());
                i = i2;
            } else {
                this.l = bVarA.a();
                this.c = parcel.readString();
                this.e = parcel.readString();
                this.k = (PlayerTrack[]) parcel.createTypedArray(PlayerTrack.CREATOR);
                return;
            }
        }
    }
}
