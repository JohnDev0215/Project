package l3;

import android.media.MediaMetadata;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import u.C2465e;

/* JADX INFO: renamed from: l3.F, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1741F implements Parcelable {
    public static final Parcelable.Creator<C1741F> CREATOR;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C2465e f18520n;
    public static final String[] o;
    public final Bundle l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public MediaMetadata f18521m;

    static {
        C2465e c2465e = new C2465e(0);
        f18520n = c2465e;
        android.hardware.bydauto.ac.tsimpl.a.n(1, c2465e, "android.media.metadata.TITLE", 1, "android.media.metadata.ARTIST");
        android.hardware.bydauto.ac.tsimpl.a.n(0, c2465e, "android.media.metadata.DURATION", 1, "android.media.metadata.ALBUM");
        android.hardware.bydauto.ac.tsimpl.a.n(1, c2465e, "android.media.metadata.AUTHOR", 1, "android.media.metadata.WRITER");
        android.hardware.bydauto.ac.tsimpl.a.n(1, c2465e, "android.media.metadata.COMPOSER", 1, "android.media.metadata.COMPILATION");
        android.hardware.bydauto.ac.tsimpl.a.n(1, c2465e, "android.media.metadata.DATE", 0, "android.media.metadata.YEAR");
        android.hardware.bydauto.ac.tsimpl.a.n(1, c2465e, "android.media.metadata.GENRE", 0, "android.media.metadata.TRACK_NUMBER");
        android.hardware.bydauto.ac.tsimpl.a.n(0, c2465e, "android.media.metadata.NUM_TRACKS", 0, "android.media.metadata.DISC_NUMBER");
        android.hardware.bydauto.ac.tsimpl.a.n(1, c2465e, "android.media.metadata.ALBUM_ARTIST", 2, "android.media.metadata.ART");
        android.hardware.bydauto.ac.tsimpl.a.n(1, c2465e, "android.media.metadata.ART_URI", 2, "android.media.metadata.ALBUM_ART");
        android.hardware.bydauto.ac.tsimpl.a.n(1, c2465e, "android.media.metadata.ALBUM_ART_URI", 3, "android.media.metadata.USER_RATING");
        android.hardware.bydauto.ac.tsimpl.a.n(3, c2465e, "android.media.metadata.RATING", 1, "android.media.metadata.DISPLAY_TITLE");
        android.hardware.bydauto.ac.tsimpl.a.n(1, c2465e, "android.media.metadata.DISPLAY_SUBTITLE", 1, "android.media.metadata.DISPLAY_DESCRIPTION");
        android.hardware.bydauto.ac.tsimpl.a.n(2, c2465e, "android.media.metadata.DISPLAY_ICON", 1, "android.media.metadata.DISPLAY_ICON_URI");
        android.hardware.bydauto.ac.tsimpl.a.n(1, c2465e, "android.media.metadata.MEDIA_ID", 0, "android.media.metadata.BT_FOLDER_TYPE");
        android.hardware.bydauto.ac.tsimpl.a.n(1, c2465e, "android.media.metadata.MEDIA_URI", 0, "android.media.metadata.ADVERTISEMENT");
        c2465e.put("android.media.metadata.DOWNLOAD_STATUS", 0);
        o = new String[]{"android.media.metadata.TITLE", "android.media.metadata.ARTIST", "android.media.metadata.ALBUM", "android.media.metadata.ALBUM_ARTIST", "android.media.metadata.WRITER", "android.media.metadata.AUTHOR", "android.media.metadata.COMPOSER"};
        CREATOR = new android.support.v4.media.a(16);
    }

    public C1741F(Bundle bundle) {
        Bundle bundle2 = new Bundle(bundle);
        this.l = bundle2;
        P.o(bundle2);
    }

    public final long a(String str) {
        return this.l.getLong(str, 0L);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i7) {
        parcel.writeBundle(this.l);
    }

    public C1741F(Parcel parcel) {
        Bundle bundle = parcel.readBundle(P.class.getClassLoader());
        bundle.getClass();
        this.l = bundle;
    }
}
