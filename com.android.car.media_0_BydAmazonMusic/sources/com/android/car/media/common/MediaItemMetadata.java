package com.android.car.media.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.media.utils.MediaConstants;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import spi.c;
import spi.h2;
import spi.v4;

/* JADX INFO: loaded from: classes.dex */
public class MediaItemMetadata implements Parcelable {

    @NonNull
    public final MediaDescriptionCompat a;

    @Nullable
    public final Long b;
    public final boolean c;
    public final boolean d;
    public final String e;
    public final String f;
    public final b g;
    public static final int h = Color.argb(200, 255, 0, 0);
    public static Map<v4.b, List<Drawable>> i = new HashMap();
    public static final Parcelable.Creator<MediaItemMetadata> CREATOR = new a();

    public class a implements Parcelable.Creator<MediaItemMetadata> {
        @Override // android.os.Parcelable.Creator
        public final MediaItemMetadata createFromParcel(Parcel parcel) {
            return new MediaItemMetadata(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final MediaItemMetadata[] newArray(int i) {
            return new MediaItemMetadata[i];
        }
    }

    public class b implements v4.a {
        public b() {
        }

        @Nullable
        public final Bitmap a(Context context) {
            MediaDescriptionCompat mediaDescriptionCompat;
            if (!h2.a(context).b() || (mediaDescriptionCompat = MediaItemMetadata.this.a) == null) {
                return null;
            }
            return mediaDescriptionCompat.getIconBitmap();
        }

        @Nullable
        public final Uri b() {
            MediaItemMetadata mediaItemMetadata = MediaItemMetadata.this;
            int i = MediaItemMetadata.h;
            return mediaItemMetadata.c();
        }

        public final int c() {
            if (MediaItemMetadata.this.f() != null) {
                return MediaItemMetadata.this.f().hashCode();
            }
            return 0;
        }

        public final String toString() {
            StringBuilder sbK = c.k("title: ");
            sbK.append((Object) MediaItemMetadata.this.f());
            sbK.append(" uri: ");
            sbK.append(MediaItemMetadata.this.c());
            return sbK.toString();
        }
    }

    public MediaItemMetadata(@NonNull MediaMetadataCompat mediaMetadataCompat) {
        this(mediaMetadataCompat.getDescription(), null, false, false, mediaMetadataCompat.getString(MediaMetadataCompat.METADATA_KEY_ALBUM), mediaMetadataCompat.getString(MediaMetadataCompat.METADATA_KEY_ARTIST));
    }

    public final int a() {
        Bundle extras = this.a.getExtras();
        if (extras != null) {
            if (extras.containsKey(MediaConstants.DESCRIPTION_EXTRAS_KEY_CONTENT_STYLE_BROWSABLE)) {
                return extras.getInt(MediaConstants.DESCRIPTION_EXTRAS_KEY_CONTENT_STYLE_BROWSABLE, 0);
            }
            if (extras.containsKey("android.auto.media.CONTENT_STYLE_BROWSABLE_HINT")) {
                return extras.getInt("android.auto.media.CONTENT_STYLE_BROWSABLE_HINT", 0);
            }
        }
        return 0;
    }

    @Nullable
    public final String b() {
        return this.a.getMediaId();
    }

    @Nullable
    public final Uri c() {
        Uri iconUri = this.a.getIconUri();
        if (iconUri == null || TextUtils.isEmpty(iconUri.toString())) {
            return null;
        }
        return iconUri;
    }

    public final int d() {
        Bundle extras = this.a.getExtras();
        if (extras != null) {
            if (extras.containsKey(MediaConstants.DESCRIPTION_EXTRAS_KEY_CONTENT_STYLE_PLAYABLE)) {
                return extras.getInt(MediaConstants.DESCRIPTION_EXTRAS_KEY_CONTENT_STYLE_PLAYABLE, 0);
            }
            if (extras.containsKey("android.auto.media.CONTENT_STYLE_PLAYABLE_HINT")) {
                return extras.getInt("android.auto.media.CONTENT_STYLE_PLAYABLE_HINT", 0);
            }
        }
        return 0;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Nullable
    public final CharSequence e() {
        return this.a.getSubtitle();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MediaItemMetadata mediaItemMetadata = (MediaItemMetadata) obj;
        return this.c == mediaItemMetadata.c && this.d == mediaItemMetadata.d && Objects.equals(b(), mediaItemMetadata.b()) && Objects.equals(f(), mediaItemMetadata.f()) && Objects.equals(e(), mediaItemMetadata.e()) && Objects.equals(this.e, mediaItemMetadata.e) && Objects.equals(this.f, mediaItemMetadata.f) && Objects.equals(c(), mediaItemMetadata.c()) && Objects.equals(this.b, mediaItemMetadata.b);
    }

    @Nullable
    public final CharSequence f() {
        return this.a.getTitle();
    }

    public final String g() {
        Bundle extras = this.a.getExtras();
        if (extras != null) {
            if (extras.containsKey(MediaConstants.DESCRIPTION_EXTRAS_KEY_CONTENT_STYLE_GROUP_TITLE)) {
                return extras.getString(MediaConstants.DESCRIPTION_EXTRAS_KEY_CONTENT_STYLE_GROUP_TITLE, null);
            }
            if (extras.containsKey("android.auto.media.CONTENT_STYLE_GROUP_TITLE_HINT")) {
                return extras.getString("android.auto.media.CONTENT_STYLE_GROUP_TITLE_HINT", null);
            }
        }
        return null;
    }

    public final int hashCode() {
        return Objects.hash(this.a.getMediaId(), this.b, Boolean.valueOf(this.c), Boolean.valueOf(this.d));
    }

    public final String toString() {
        StringBuilder sbK = c.k("[Id: ");
        MediaDescriptionCompat mediaDescriptionCompat = this.a;
        sbK.append(mediaDescriptionCompat != null ? mediaDescriptionCompat.getMediaId() : "-");
        sbK.append(", Queue Id: ");
        Object obj = this.b;
        if (obj == null) {
            obj = "-";
        }
        sbK.append(obj);
        sbK.append(", title: ");
        sbK.append(this.a);
        if (sbK.toString() != null) {
            return this.a.getTitle().toString();
        }
        StringBuilder sbK2 = c.k("-, subtitle: ");
        sbK2.append(this.a);
        if (sbK2.toString() != null) {
            return this.a.getSubtitle().toString();
        }
        StringBuilder sbK3 = c.k("-, album title: ");
        sbK3.append(this.e);
        if (sbK3.toString() != null) {
            return this.e;
        }
        StringBuilder sbK4 = c.k("-, artist: ");
        sbK4.append(this.f);
        if (sbK4.toString() != null) {
            return this.f;
        }
        StringBuilder sbK5 = c.k("-, album art URI: ");
        MediaDescriptionCompat mediaDescriptionCompat2 = this.a;
        sbK5.append(mediaDescriptionCompat2 != null ? mediaDescriptionCompat2.getIconUri() : "-");
        sbK5.append("]");
        return sbK5.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeValue(this.a);
        if (this.b == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(this.b.longValue());
        }
        parcel.writeByte(this.c ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.d ? (byte) 1 : (byte) 0);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
    }

    public MediaItemMetadata(@NonNull MediaSessionCompat.QueueItem queueItem) {
        this(queueItem.getDescription(), Long.valueOf(queueItem.getQueueId()), false, true, null, null);
    }

    public MediaItemMetadata(@NonNull MediaBrowserCompat.MediaItem mediaItem) {
        this(mediaItem.getDescription(), null, mediaItem.isBrowsable(), mediaItem.isPlayable(), null, null);
    }

    public MediaItemMetadata(@NonNull Parcel parcel) {
        this.g = new b();
        this.a = (MediaDescriptionCompat) parcel.readValue(MediaDescriptionCompat.class.getClassLoader());
        this.b = parcel.readByte() == 0 ? null : Long.valueOf(parcel.readLong());
        this.c = parcel.readByte() != 0;
        this.d = parcel.readByte() != 0;
        this.e = parcel.readString();
        this.f = parcel.readString();
    }

    @VisibleForTesting
    public MediaItemMetadata(MediaDescriptionCompat mediaDescriptionCompat, Long l, boolean z, boolean z2, String str, String str2) {
        this.g = new b();
        this.a = mediaDescriptionCompat;
        this.b = l;
        this.d = z2;
        this.c = z;
        this.e = str;
        this.f = str2;
    }
}
