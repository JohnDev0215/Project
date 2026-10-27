package com.amazon.music.media.playback;

import PlaybackInterface.v1_0.AudioStreamFormat;
import UpsellInterface.v1_0.PlaybackMode;
import android.net.Uri;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import com.amazon.music.media.playback.config.BitRateSelection;
import com.amazon.music.media.playback.util.StringUtils;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class MediaLink {
    private final BitRateSelection bitRateSelection;
    private final long expirationTime;
    private final Type type;
    private final Uri uri;

    public MediaLink(@NonNull Type type, @NonNull BitRateSelection bitRateSelection, @NonNull Uri uri, long j) {
        this.type = (Type) Validate.notNull(type);
        this.bitRateSelection = bitRateSelection;
        this.uri = (Uri) Validate.notNull(uri);
        this.expirationTime = j;
    }

    public Type getType() {
        return this.type;
    }

    public BitRateSelection getBitRateSelection() {
        return this.bitRateSelection;
    }

    public Uri getUri() {
        return this.uri;
    }

    public long timeToExpire() {
        long j = this.expirationTime;
        if (j <= 0 || j == Long.MAX_VALUE) {
            return Long.MAX_VALUE;
        }
        return j - SystemClock.elapsedRealtime();
    }

    public boolean isExpired() {
        long j = this.expirationTime;
        return j > 0 && j <= SystemClock.elapsedRealtime();
    }

    public String toString() {
        String str;
        long jTimeToExpire = timeToExpire();
        long j = this.expirationTime;
        if (j <= 0 || j == Long.MAX_VALUE) {
            str = "Does Not Expire";
        } else if (jTimeToExpire > 0) {
            str = "Expires in " + StringUtils.getDurationString(jTimeToExpire);
        } else {
            str = "Expired " + StringUtils.getDurationString(jTimeToExpire) + " ago";
        }
        return "MediaLink{type=" + this.type.getMetricsName() + ", uri=" + this.uri + ", expirationTime=" + this.expirationTime + ", " + str + '}';
    }

    public static class Type {
        private final String name;
        private static final Map<String, Type> types = new HashMap();
        public static final Type HLS = new Type(AudioStreamFormat.HLS);
        public static final Type BUY_DRM = new Type("BUYDRM");
        public static final Type LOCAL = new Type(PlaybackMode.NONE);
        public static final Type PROGRESSIVE = new Type(AudioStreamFormat.PROGRESSIVE);
        public static final Type DASH = new Type("DASH");
        public static final Type UNKNOWN = new Type("UNKNOWN");

        public static Type getType(@NonNull String str) {
            Type type;
            Map<String, Type> map = types;
            synchronized (map) {
                type = map.get(str);
                if (type == null) {
                    type = new Type(str);
                }
            }
            return type;
        }

        private Type(String str) {
            this.name = (String) Validate.notEmpty(str, "Type name cannot be empty", new Object[0]);
            types.put(str, this);
        }

        public String getMetricsName() {
            return this.name;
        }

        public String toString() {
            return "MediaLink.Type{name='" + this.name + "'}";
        }
    }
}
