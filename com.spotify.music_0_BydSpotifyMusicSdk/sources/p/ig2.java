package p;

import android.os.Parcel;
import android.os.Parcelable;
import com.spotify.player.legacyplayer.PlayerTrack;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class ig2 implements Parcelable.Creator {
    public final /* synthetic */ int a;

    public /* synthetic */ ig2(int i) {
        this.a = i;
    }

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        LinkedHashMap linkedHashMap;
        switch (this.a) {
            case 0:
                return parcel.readString();
            default:
                v03.j(parcel, "parcel");
                String string = parcel.readString();
                String string2 = parcel.readString();
                String string3 = parcel.readString();
                String string4 = parcel.readString();
                String string5 = parcel.readString();
                if (parcel.readInt() == 0) {
                    linkedHashMap = null;
                } else {
                    int i = parcel.readInt();
                    LinkedHashMap linkedHashMap2 = new LinkedHashMap(i);
                    for (int i2 = 0; i2 != i; i2++) {
                        linkedHashMap2.put(parcel.readString(), parcel.readString());
                    }
                    linkedHashMap = linkedHashMap2;
                }
                return new PlayerTrack(string, string2, string3, string4, string5, linkedHashMap);
        }
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        switch (this.a) {
            case 0:
                return new String[i];
            default:
                return new PlayerTrack[i];
        }
    }
}
