package org.chromium.services.media_session;

import android.graphics.Rect;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class MediaImage {
    public String a;
    public String b;
    public List c;

    public MediaImage(String str, String str2, List list) {
        this.c = new ArrayList();
        this.a = str;
        this.b = str2;
        this.c = list;
    }

    public static MediaImage create(String str, String str2, Rect[] rectArr) {
        return new MediaImage(str, str2, Arrays.asList(rectArr));
    }

    public static Rect createRect(int i, int i2) {
        return new Rect(0, 0, i, i2);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MediaImage)) {
            return false;
        }
        MediaImage mediaImage = (MediaImage) obj;
        return TextUtils.equals(this.a, mediaImage.a) && TextUtils.equals(this.b, mediaImage.b) && this.c.equals(mediaImage.c);
    }

    public int hashCode() {
        return this.c.hashCode() + ((this.b.hashCode() + (this.a.hashCode() * 31)) * 31);
    }
}
