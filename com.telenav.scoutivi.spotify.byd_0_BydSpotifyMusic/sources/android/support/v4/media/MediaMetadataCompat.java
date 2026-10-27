package android.support.v4.media;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.media.MediaMetadata;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class MediaMetadataCompat implements Parcelable {
    public static final Parcelable.Creator<MediaMetadataCompat> CREATOR;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    static final n.a<String, Integer> f219i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private static final String[] f220j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private static final String[] f221k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private static final String[] f222l;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final Bundle f223e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private MediaMetadata f224g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private MediaDescriptionCompat f225h;

    class a implements Parcelable.Creator<MediaMetadataCompat> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public MediaMetadataCompat createFromParcel(Parcel parcel) {
            return new MediaMetadataCompat(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public MediaMetadataCompat[] newArray(int i7) {
            return new MediaMetadataCompat[i7];
        }
    }

    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Bundle f226a = new Bundle();

        public MediaMetadataCompat a() {
            return new MediaMetadataCompat(this.f226a);
        }

        public b b(String str, String str2) {
            n.a<String, Integer> aVar = MediaMetadataCompat.f219i;
            if (!aVar.containsKey(str) || aVar.get(str).intValue() == 1) {
                this.f226a.putCharSequence(str, str2);
                return this;
            }
            throw new IllegalArgumentException("The " + str + " key cannot be used to put a String");
        }
    }

    static {
        n.a<String, Integer> aVar = new n.a<>();
        f219i = aVar;
        aVar.put("android.media.metadata.TITLE", 1);
        aVar.put("android.media.metadata.ARTIST", 1);
        aVar.put("android.media.metadata.DURATION", 0);
        aVar.put("android.media.metadata.ALBUM", 1);
        aVar.put("android.media.metadata.AUTHOR", 1);
        aVar.put("android.media.metadata.WRITER", 1);
        aVar.put("android.media.metadata.COMPOSER", 1);
        aVar.put("android.media.metadata.COMPILATION", 1);
        aVar.put("android.media.metadata.DATE", 1);
        aVar.put("android.media.metadata.YEAR", 0);
        aVar.put("android.media.metadata.GENRE", 1);
        aVar.put("android.media.metadata.TRACK_NUMBER", 0);
        aVar.put("android.media.metadata.NUM_TRACKS", 0);
        aVar.put("android.media.metadata.DISC_NUMBER", 0);
        aVar.put("android.media.metadata.ALBUM_ARTIST", 1);
        aVar.put("android.media.metadata.ART", 2);
        aVar.put("android.media.metadata.ART_URI", 1);
        aVar.put("android.media.metadata.ALBUM_ART", 2);
        aVar.put("android.media.metadata.ALBUM_ART_URI", 1);
        aVar.put("android.media.metadata.USER_RATING", 3);
        aVar.put("android.media.metadata.RATING", 3);
        aVar.put("android.media.metadata.DISPLAY_TITLE", 1);
        aVar.put("android.media.metadata.DISPLAY_SUBTITLE", 1);
        aVar.put("android.media.metadata.DISPLAY_DESCRIPTION", 1);
        aVar.put("android.media.metadata.DISPLAY_ICON", 2);
        aVar.put("android.media.metadata.DISPLAY_ICON_URI", 1);
        aVar.put("android.media.metadata.MEDIA_ID", 1);
        aVar.put("android.media.metadata.BT_FOLDER_TYPE", 0);
        aVar.put("android.media.metadata.MEDIA_URI", 1);
        aVar.put("android.media.metadata.ADVERTISEMENT", 0);
        aVar.put("android.media.metadata.DOWNLOAD_STATUS", 0);
        f220j = new String[]{"android.media.metadata.TITLE", "android.media.metadata.ARTIST", "android.media.metadata.ALBUM", "android.media.metadata.ALBUM_ARTIST", "android.media.metadata.WRITER", "android.media.metadata.AUTHOR", "android.media.metadata.COMPOSER"};
        f221k = new String[]{"android.media.metadata.DISPLAY_ICON", "android.media.metadata.ART", "android.media.metadata.ALBUM_ART"};
        f222l = new String[]{"android.media.metadata.DISPLAY_ICON_URI", "android.media.metadata.ART_URI", "android.media.metadata.ALBUM_ART_URI"};
        CREATOR = new a();
    }

    MediaMetadataCompat(Bundle bundle) {
        Bundle bundle2 = new Bundle(bundle);
        this.f223e = bundle2;
        MediaSessionCompat.a(bundle2);
    }

    public static MediaMetadataCompat b(Object obj) {
        if (obj == null) {
            return null;
        }
        Parcel parcelObtain = Parcel.obtain();
        MediaMetadata mediaMetadata = (MediaMetadata) obj;
        mediaMetadata.writeToParcel(parcelObtain, 0);
        parcelObtain.setDataPosition(0);
        MediaMetadataCompat mediaMetadataCompatCreateFromParcel = CREATOR.createFromParcel(parcelObtain);
        parcelObtain.recycle();
        mediaMetadataCompatCreateFromParcel.f224g = mediaMetadata;
        return mediaMetadataCompatCreateFromParcel;
    }

    public Bitmap c(String str) {
        try {
            return (Bitmap) this.f223e.getParcelable(str);
        } catch (Exception e7) {
            Log.w("MediaMetadata", "Failed to retrieve a key as Bitmap.", e7);
            return null;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Bundle h() {
        return new Bundle(this.f223e);
    }

    public MediaDescriptionCompat j() {
        Bitmap bitmapC;
        Uri uri;
        MediaDescriptionCompat mediaDescriptionCompat = this.f225h;
        if (mediaDescriptionCompat != null) {
            return mediaDescriptionCompat;
        }
        String strM = m("android.media.metadata.MEDIA_ID");
        CharSequence[] charSequenceArr = new CharSequence[3];
        CharSequence charSequenceP = p("android.media.metadata.DISPLAY_TITLE");
        if (TextUtils.isEmpty(charSequenceP)) {
            int i7 = 0;
            int i8 = 0;
            while (i7 < 3) {
                String[] strArr = f220j;
                if (i8 >= strArr.length) {
                    break;
                }
                int i9 = i8 + 1;
                CharSequence charSequenceP2 = p(strArr[i8]);
                if (!TextUtils.isEmpty(charSequenceP2)) {
                    charSequenceArr[i7] = charSequenceP2;
                    i7++;
                }
                i8 = i9;
            }
        } else {
            charSequenceArr[0] = charSequenceP;
            charSequenceArr[1] = p("android.media.metadata.DISPLAY_SUBTITLE");
            charSequenceArr[2] = p("android.media.metadata.DISPLAY_DESCRIPTION");
        }
        int i10 = 0;
        while (true) {
            String[] strArr2 = f221k;
            if (i10 >= strArr2.length) {
                bitmapC = null;
                break;
            }
            bitmapC = c(strArr2[i10]);
            if (bitmapC != null) {
                break;
            }
            i10++;
        }
        int i11 = 0;
        while (true) {
            String[] strArr3 = f222l;
            if (i11 >= strArr3.length) {
                uri = null;
                break;
            }
            String strM2 = m(strArr3[i11]);
            if (!TextUtils.isEmpty(strM2)) {
                uri = Uri.parse(strM2);
                break;
            }
            i11++;
        }
        String strM3 = m("android.media.metadata.MEDIA_URI");
        Uri uri2 = TextUtils.isEmpty(strM3) ? null : Uri.parse(strM3);
        MediaDescriptionCompat.b bVar = new MediaDescriptionCompat.b();
        bVar.f(strM);
        bVar.i(charSequenceArr[0]);
        bVar.h(charSequenceArr[1]);
        bVar.b(charSequenceArr[2]);
        bVar.d(bitmapC);
        bVar.e(uri);
        bVar.g(uri2);
        Bundle bundle = new Bundle();
        if (this.f223e.containsKey("android.media.metadata.BT_FOLDER_TYPE")) {
            bundle.putLong("android.media.extra.BT_FOLDER_TYPE", k("android.media.metadata.BT_FOLDER_TYPE"));
        }
        if (this.f223e.containsKey("android.media.metadata.DOWNLOAD_STATUS")) {
            bundle.putLong("android.media.extra.DOWNLOAD_STATUS", k("android.media.metadata.DOWNLOAD_STATUS"));
        }
        if (!bundle.isEmpty()) {
            bVar.c(bundle);
        }
        MediaDescriptionCompat mediaDescriptionCompatA = bVar.a();
        this.f225h = mediaDescriptionCompatA;
        return mediaDescriptionCompatA;
    }

    public long k(String str) {
        return this.f223e.getLong(str, 0L);
    }

    public String m(String str) {
        CharSequence charSequence = this.f223e.getCharSequence(str);
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }

    public CharSequence p(String str) {
        return this.f223e.getCharSequence(str);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i7) {
        parcel.writeBundle(this.f223e);
    }

    MediaMetadataCompat(Parcel parcel) {
        this.f223e = parcel.readBundle(MediaSessionCompat.class.getClassLoader());
    }
}
