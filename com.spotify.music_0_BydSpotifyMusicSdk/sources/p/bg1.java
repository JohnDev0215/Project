package p;

import android.graphics.Bitmap;
import android.media.MediaDescription;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.MediaBrowserCompat$MediaItem;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import androidx.versionedparcelable.ParcelImpl;
import java.util.Date;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class bg1 implements Parcelable.Creator {
    public final /* synthetic */ int a;

    public /* synthetic */ bg1(int i) {
        this.a = i;
    }

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        switch (this.a) {
            case 0:
                return new MediaBrowserCompat$MediaItem(parcel);
            case 1:
                Object objCreateFromParcel = MediaDescription.CREATOR.createFromParcel(parcel);
                if (objCreateFromParcel == null) {
                    return null;
                }
                MediaDescription mediaDescription = (MediaDescription) objCreateFromParcel;
                String strG = hh1.g(mediaDescription);
                CharSequence charSequenceI = hh1.i(mediaDescription);
                CharSequence charSequenceH = hh1.h(mediaDescription);
                CharSequence charSequenceC = hh1.c(mediaDescription);
                Bitmap bitmapE = hh1.e(mediaDescription);
                Uri uriF = hh1.f(mediaDescription);
                Bundle bundleD = hh1.d(mediaDescription);
                if (bundleD != null) {
                    bundleD = bi1.f(bundleD);
                }
                Uri uri = bundleD != null ? (Uri) bundleD.getParcelable("android.support.v4.media.description.MEDIA_URI") : null;
                if (uri != null) {
                    if (bundleD.containsKey("android.support.v4.media.description.NULL_BUNDLE_FLAG") && bundleD.size() == 2) {
                        bundleD = null;
                    } else {
                        bundleD.remove("android.support.v4.media.description.MEDIA_URI");
                        bundleD.remove("android.support.v4.media.description.NULL_BUNDLE_FLAG");
                    }
                }
                MediaDescriptionCompat mediaDescriptionCompat = new MediaDescriptionCompat(strG, charSequenceI, charSequenceH, charSequenceC, bitmapE, uriF, bundleD, uri != null ? uri : ih1.a(mediaDescription));
                mediaDescriptionCompat.q = mediaDescription;
                return mediaDescriptionCompat;
            case 2:
                return new MediaMetadataCompat(parcel);
            case 3:
                return new RatingCompat(parcel.readFloat(), parcel.readInt());
            case 4:
                return new b52(parcel);
            case 5:
                return new r4(parcel);
            case 6:
                r9 r9Var = new r9(parcel);
                r9Var.c = parcel.readByte() != 0;
                return r9Var;
            case 7:
                om1 om1Var = new om1(parcel);
                om1Var.c = parcel.readInt();
                return om1Var;
            case 8:
                oa1 oa1Var = new oa1();
                oa1Var.c = parcel.readInt();
                oa1Var.e = parcel.readInt();
                oa1Var.k = parcel.readInt() == 1;
                return oa1Var;
            case 9:
                qf2 qf2Var = new qf2();
                qf2Var.c = parcel.readInt();
                qf2Var.e = parcel.readInt();
                qf2Var.l = parcel.readInt() == 1;
                int i = parcel.readInt();
                if (i > 0) {
                    int[] iArr = new int[i];
                    qf2Var.k = iArr;
                    parcel.readIntArray(iArr);
                }
                return qf2Var;
            case 10:
                rf2 rf2Var = new rf2();
                rf2Var.c = parcel.readInt();
                rf2Var.e = parcel.readInt();
                int i2 = parcel.readInt();
                rf2Var.k = i2;
                if (i2 > 0) {
                    int[] iArr2 = new int[i2];
                    rf2Var.l = iArr2;
                    parcel.readIntArray(iArr2);
                }
                int i3 = parcel.readInt();
                rf2Var.m = i3;
                if (i3 > 0) {
                    int[] iArr3 = new int[i3];
                    rf2Var.n = iArr3;
                    parcel.readIntArray(iArr3);
                }
                rf2Var.f180p = parcel.readInt() == 1;
                rf2Var.q = parcel.readInt() == 1;
                rf2Var.r = parcel.readInt() == 1;
                rf2Var.o = parcel.readArrayList(qf2.class.getClassLoader());
                return rf2Var;
            case 11:
                return new ParcelImpl(parcel);
            case 12:
                String string = parcel.readString();
                v03.h(string);
                int i4 = parcel.readInt();
                LinkedHashMap linkedHashMap = new LinkedHashMap(i4);
                for (int i5 = 0; i5 < i4; i5++) {
                    String string2 = parcel.readString();
                    v03.h(string2);
                    String string3 = parcel.readString();
                    v03.h(string3);
                    linkedHashMap.put(string2, string3);
                }
                return new mi1(string, linkedHashMap);
            case 13:
                return new ip((bl1) parcel.readParcelable(bl1.class.getClassLoader()), (bl1) parcel.readParcelable(bl1.class.getClassLoader()), (hp) parcel.readParcelable(hp.class.getClassLoader()), (bl1) parcel.readParcelable(bl1.class.getClassLoader()));
            case 14:
                return new c60(parcel.readLong());
            case 15:
                return bl1.a(parcel.readInt(), parcel.readInt());
            case 16:
                return new nl2(parcel);
            case 17:
                v03.j(parcel, "parcel");
                return new k2(parcel.readString(), parcel.readString(), (Date) parcel.readSerializable());
            case 18:
                v03.j(parcel, "parcel");
                return new qe(parcel.readString(), parcel.createByteArray());
            case 19:
                v03.j(parcel, "parcel");
                return new bf(parcel.readString(), parcel.readInt() != 0 ? k2.CREATOR.createFromParcel(parcel) : null, (re) parcel.readParcelable(bf.class.getClassLoader()));
            case 20:
                v03.j(parcel, "parcel");
                return new com.spotify.navigation.identifier.a(parcel.readString());
            case 21:
                return new li(parcel.readInt() == 1, parcel.readInt() == 1, parcel.readInt() == 1);
            case 22:
                return new mi(parcel.readInt() == 0 ? Boolean.valueOf(parcel.readInt() == 1) : null, parcel.readInt() == 0 ? Boolean.valueOf(parcel.readInt() == 1) : null, parcel.readInt() == 0 ? Boolean.valueOf(parcel.readInt() == 1) : null);
            case 23:
                return new rt1(parcel);
            case 24:
                return new wt1(parcel);
            case 25:
                return new xt1(parcel);
            case 26:
                return new hu1(parcel);
            case 27:
                return new iu1(parcel);
            default:
                return new nu1(parcel);
        }
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        switch (this.a) {
            case 0:
                return new MediaBrowserCompat$MediaItem[i];
            case 1:
                return new MediaDescriptionCompat[i];
            case 2:
                return new MediaMetadataCompat[i];
            case 3:
                return new RatingCompat[i];
            case 4:
                return new b52[i];
            case 5:
                return new r4[i];
            case 6:
                return new r9[i];
            case 7:
                return new om1[i];
            case 8:
                return new oa1[i];
            case 9:
                return new qf2[i];
            case 10:
                return new rf2[i];
            case 11:
                return new ParcelImpl[i];
            case 12:
                return new mi1[i];
            case 13:
                return new ip[i];
            case 14:
                return new c60[i];
            case 15:
                return new bl1[i];
            case 16:
                return new nl2[i];
            case 17:
                return new k2[i];
            case 18:
                return new qe[i];
            case 19:
                return new bf[i];
            case 20:
                return new com.spotify.navigation.identifier.a[i];
            case 21:
                return new li[i];
            case 22:
                return new mi[i];
            case 23:
                return new rt1[i];
            case 24:
                return new wt1[i];
            case 25:
                return new xt1[i];
            case 26:
                return new hu1[i];
            case 27:
                return new iu1[i];
            default:
                return new nu1[i];
        }
    }
}
