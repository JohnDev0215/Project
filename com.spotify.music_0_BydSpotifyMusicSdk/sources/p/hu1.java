package p;

import android.os.Parcel;
import android.os.Parcelable;
import com.spotify.player.legacyplayer.PlayerTrack;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class hu1 implements Parcelable {
    public static final Parcelable.Creator<hu1> CREATOR = new bg1(26);
    public final PlayerTrack c;
    public final PlayerTrack[] e;
    public final PlayerTrack[] k;
    public final String l;

    public hu1(String str, PlayerTrack playerTrack, PlayerTrack[] playerTrackArr, PlayerTrack[] playerTrackArr2) {
        this.l = str;
        this.c = playerTrack;
        this.e = playerTrackArr;
        this.k = playerTrackArr2;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof hu1)) {
            return false;
        }
        hu1 hu1Var = (hu1) obj;
        if (!this.l.equals(hu1Var.l)) {
            return false;
        }
        PlayerTrack playerTrack = hu1Var.c;
        PlayerTrack playerTrack2 = this.c;
        if (playerTrack2 == null ? playerTrack != null : !playerTrack2.equals(playerTrack)) {
            return false;
        }
        if (Arrays.equals(this.e, hu1Var.e)) {
            return Arrays.equals(this.k, hu1Var.k);
        }
        return false;
    }

    public final int hashCode() {
        return (((Arrays.hashCode(new Object[]{this.l, this.c}) * 31) + Arrays.hashCode(this.e)) * 31) + Arrays.hashCode(this.k);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.l);
        parcel.writeTypedObject(this.c, 0);
        parcel.writeTypedArray(this.e, 0);
        parcel.writeTypedArray(this.k, 0);
    }

    public hu1(Parcel parcel) {
        this.l = parcel.readString();
        Parcelable.Creator<PlayerTrack> creator = PlayerTrack.CREATOR;
        this.c = (PlayerTrack) ((Parcelable) parcel.readTypedObject(creator));
        this.e = (PlayerTrack[]) parcel.createTypedArray(creator);
        this.k = (PlayerTrack[]) parcel.createTypedArray(creator);
    }
}
