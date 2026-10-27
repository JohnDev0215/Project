package org.chromium.services.media_session;

import android.text.TextUtils;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public final class MediaMetadata {
    public final String a;
    public final String b;
    public final String c;

    public MediaMetadata(String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    @CalledByNative
    public static MediaMetadata create(String str, String str2, String str3) {
        return new MediaMetadata(str, str2, str3);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MediaMetadata)) {
            return false;
        }
        MediaMetadata mediaMetadata = (MediaMetadata) obj;
        return TextUtils.equals(this.a, mediaMetadata.a) && TextUtils.equals(this.b, mediaMetadata.b) && TextUtils.equals(this.c, mediaMetadata.c);
    }

    public final int hashCode() {
        return this.c.hashCode() + ((this.b.hashCode() + (this.a.hashCode() * 31)) * 31);
    }
}
