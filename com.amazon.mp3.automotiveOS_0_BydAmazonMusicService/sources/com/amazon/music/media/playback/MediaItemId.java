package com.amazon.music.media.playback;

import android.net.Uri;
import android.text.TextUtils;
import com.amazon.music.media.playback.util.StringUtils;
import java.io.File;
import java.util.Objects;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public final class MediaItemId {
    private final String id;
    private final Type type;

    public enum Type {
        LOCAL_URI("file"),
        ASIN("asin"),
        OWNED("coid"),
        LUID("luid"),
        LIVE("livemcid"),
        ONDEMAND("staticmcid"),
        MPQS(""),
        INTERLUDE("interlude"),
        URL(""),
        CONTENT_URI("contentUri"),
        PID("pid");

        private final String scheme;

        Type(String str) {
            this.scheme = str;
        }

        public String getScheme() {
            return this.scheme;
        }

        public static Type forScheme(String str) {
            for (Type type : values()) {
                if (!type.scheme.isEmpty() && type.scheme.equals(str)) {
                    return type;
                }
            }
            return URL;
        }
    }

    public MediaItemId(Type type, String str) {
        this.type = (Type) Validate.notNull(type);
        this.id = (String) Validate.notEmpty(str);
    }

    public MediaItemId(Uri uri) {
        Type typeForScheme = Type.forScheme(uri.getScheme());
        this.type = typeForScheme;
        if (StringUtils.isEmpty(typeForScheme.getScheme())) {
            this.id = uri.toString();
        } else {
            this.id = uri.toString().substring(typeForScheme.getScheme().length() + 3);
        }
    }

    public String getId() {
        return this.id;
    }

    public Type getType() {
        return this.type;
    }

    public Uri getUri() {
        return Uri.parse(toString());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(this.type.scheme) && !this.id.startsWith(this.type.scheme)) {
            if (!"file".equals(this.type.scheme)) {
                sb.append(this.type.scheme);
                sb.append("://");
            } else {
                return Uri.fromFile(new File(this.id)).toString();
            }
        }
        sb.append(this.id);
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != MediaItemId.class) {
            return false;
        }
        MediaItemId mediaItemId = (MediaItemId) obj;
        return Objects.equals(this.id, mediaItemId.id) && Objects.equals(this.type, mediaItemId.type);
    }

    public int hashCode() {
        return Objects.hash(this.id, this.type);
    }
}
