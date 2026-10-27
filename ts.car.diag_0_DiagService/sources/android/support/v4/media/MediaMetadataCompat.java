package android.support.v4.media;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.media.MediaMetadata;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import android.util.Log;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"BanParcelableUsage"})
public final class MediaMetadataCompat implements Parcelable {
    public static final Parcelable.Creator<MediaMetadataCompat> CREATOR;
    static final b.a.a<String, Integer> f;
    private static final String[] g;
    private static final String[] h;
    private static final String[] i;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final Bundle f36c;
    private MediaMetadata d;
    private MediaDescriptionCompat e;

    /* JADX INFO: compiled from: caradapter-bug */
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
        public MediaMetadataCompat[] newArray(int i) {
            return new MediaMetadataCompat[i];
        }
    }

    static {
        b.a.a<String, Integer> aVar = new b.a.a<>();
        f = aVar;
        aVar.put("android.media.metadata.TITLE", 1);
        f.put("android.media.metadata.ARTIST", 1);
        f.put("android.media.metadata.DURATION", 0);
        f.put("android.media.metadata.ALBUM", 1);
        f.put("android.media.metadata.AUTHOR", 1);
        f.put("android.media.metadata.WRITER", 1);
        f.put("android.media.metadata.COMPOSER", 1);
        f.put("android.media.metadata.COMPILATION", 1);
        f.put("android.media.metadata.DATE", 1);
        f.put("android.media.metadata.YEAR", 0);
        f.put("android.media.metadata.GENRE", 1);
        f.put("android.media.metadata.TRACK_NUMBER", 0);
        f.put("android.media.metadata.NUM_TRACKS", 0);
        f.put("android.media.metadata.DISC_NUMBER", 0);
        f.put("android.media.metadata.ALBUM_ARTIST", 1);
        f.put("android.media.metadata.ART", 2);
        f.put("android.media.metadata.ART_URI", 1);
        f.put("android.media.metadata.ALBUM_ART", 2);
        f.put("android.media.metadata.ALBUM_ART_URI", 1);
        f.put("android.media.metadata.USER_RATING", 3);
        f.put("android.media.metadata.RATING", 3);
        f.put("android.media.metadata.DISPLAY_TITLE", 1);
        f.put("android.media.metadata.DISPLAY_SUBTITLE", 1);
        f.put("android.media.metadata.DISPLAY_DESCRIPTION", 1);
        f.put("android.media.metadata.DISPLAY_ICON", 2);
        f.put("android.media.metadata.DISPLAY_ICON_URI", 1);
        f.put("android.media.metadata.MEDIA_ID", 1);
        f.put("android.media.metadata.BT_FOLDER_TYPE", 0);
        f.put("android.media.metadata.MEDIA_URI", 1);
        f.put("android.media.metadata.ADVERTISEMENT", 0);
        f.put("android.media.metadata.DOWNLOAD_STATUS", 0);
        g = new String[]{"android.media.metadata.TITLE", "android.media.metadata.ARTIST", "android.media.metadata.ALBUM", "android.media.metadata.ALBUM_ARTIST", "android.media.metadata.WRITER", "android.media.metadata.AUTHOR", "android.media.metadata.COMPOSER"};
        h = new String[]{"android.media.metadata.DISPLAY_ICON", "android.media.metadata.ART", "android.media.metadata.ALBUM_ART"};
        i = new String[]{"android.media.metadata.DISPLAY_ICON_URI", "android.media.metadata.ART_URI", "android.media.metadata.ALBUM_ART_URI"};
        CREATOR = new a();
    }

    MediaMetadataCompat(Parcel parcel) {
        this.f36c = parcel.readBundle(MediaSessionCompat.class.getClassLoader());
    }

    public static MediaMetadataCompat a(Object obj) {
        if (obj == null || Build.VERSION.SDK_INT < 21) {
            return null;
        }
        Parcel parcelObtain = Parcel.obtain();
        MediaMetadata mediaMetadata = (MediaMetadata) obj;
        mediaMetadata.writeToParcel(parcelObtain, 0);
        parcelObtain.setDataPosition(0);
        MediaMetadataCompat mediaMetadataCompatCreateFromParcel = CREATOR.createFromParcel(parcelObtain);
        parcelObtain.recycle();
        mediaMetadataCompatCreateFromParcel.d = mediaMetadata;
        return mediaMetadataCompatCreateFromParcel;
    }

    public Bitmap b(String str) {
        try {
            return (Bitmap) this.f36c.getParcelable(str);
        } catch (Exception e) {
            Log.w("MediaMetadata", "Failed to retrieve a key as Bitmap.", e);
            return null;
        }
    }

    public MediaDescriptionCompat c() {
        Bitmap bitmapB;
        Uri uri;
        MediaDescriptionCompat mediaDescriptionCompat = this.e;
        if (mediaDescriptionCompat != null) {
            return mediaDescriptionCompat;
        }
        String strE = e("android.media.metadata.MEDIA_ID");
        CharSequence[] charSequenceArr = new CharSequence[3];
        CharSequence charSequenceF = f("android.media.metadata.DISPLAY_TITLE");
        if (TextUtils.isEmpty(charSequenceF)) {
            int i2 = 0;
            int i3 = 0;
            while (i2 < 3) {
                String[] strArr = g;
                if (i3 >= strArr.length) {
                    break;
                }
                int i4 = i3 + 1;
                CharSequence charSequenceF2 = f(strArr[i3]);
                if (!TextUtils.isEmpty(charSequenceF2)) {
                    charSequenceArr[i2] = charSequenceF2;
                    i2++;
                }
                i3 = i4;
            }
        } else {
            charSequenceArr[0] = charSequenceF;
            charSequenceArr[1] = f("android.media.metadata.DISPLAY_SUBTITLE");
            charSequenceArr[2] = f("android.media.metadata.DISPLAY_DESCRIPTION");
        }
        int i5 = 0;
        while (true) {
            String[] strArr2 = h;
            if (i5 >= strArr2.length) {
                bitmapB = null;
                break;
            }
            bitmapB = b(strArr2[i5]);
            if (bitmapB != null) {
                break;
            }
            i5++;
        }
        int i6 = 0;
        while (true) {
            String[] strArr3 = i;
            if (i6 >= strArr3.length) {
                uri = null;
                break;
            }
            String strE2 = e(strArr3[i6]);
            if (!TextUtils.isEmpty(strE2)) {
                uri = Uri.parse(strE2);
                break;
            }
            i6++;
        }
        String strE3 = e("android.media.metadata.MEDIA_URI");
        Uri uri2 = TextUtils.isEmpty(strE3) ? null : Uri.parse(strE3);
        MediaDescriptionCompat.b bVar = new MediaDescriptionCompat.b();
        bVar.f(strE);
        bVar.i(charSequenceArr[0]);
        bVar.h(charSequenceArr[1]);
        bVar.b(charSequenceArr[2]);
        bVar.d(bitmapB);
        bVar.e(uri);
        bVar.g(uri2);
        Bundle bundle = new Bundle();
        if (this.f36c.containsKey("android.media.metadata.BT_FOLDER_TYPE")) {
            bundle.putLong("android.media.extra.BT_FOLDER_TYPE", d("android.media.metadata.BT_FOLDER_TYPE"));
        }
        if (this.f36c.containsKey("android.media.metadata.DOWNLOAD_STATUS")) {
            bundle.putLong("android.media.extra.DOWNLOAD_STATUS", d("android.media.metadata.DOWNLOAD_STATUS"));
        }
        if (!bundle.isEmpty()) {
            bVar.c(bundle);
        }
        MediaDescriptionCompat mediaDescriptionCompatA = bVar.a();
        this.e = mediaDescriptionCompatA;
        return mediaDescriptionCompatA;
    }

    public long d(String str) {
        return this.f36c.getLong(str, 0L);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String e(String str) {
        CharSequence charSequence = this.f36c.getCharSequence(str);
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }

    public CharSequence f(String str) {
        return this.f36c.getCharSequence(str);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeBundle(this.f36c);
    }
}
