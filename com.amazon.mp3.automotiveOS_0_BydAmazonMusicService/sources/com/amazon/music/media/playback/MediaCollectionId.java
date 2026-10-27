package com.amazon.music.media.playback;

import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class MediaCollectionId {
    private final String id;
    private final Type type;

    public enum Type {
        ASIN("asin"),
        STATION_KEY(""),
        STATION_INSTANCE_ID(""),
        STATION_URI(""),
        AUTO_GENERATED_PLAYLIST(""),
        PROGRAM_ID(""),
        ALBUM_ART_ID(""),
        SELECTION_SOURCE_ID(""),
        LIBRARY_COLLECTION(""),
        MPQS(""),
        ADVERTISEMENT(""),
        CUSTOM(""),
        CLOUD_QUEUE_IMAGE_URL(""),
        PLAYBACK_SESSION_ID(""),
        ALEXA_PLAYBACK_SESSION_ID("");

        private final String scheme;

        Type(String str) {
            this.scheme = str;
        }

        public String getScheme() {
            return this.scheme;
        }

        public static Type forScheme(String str) {
            for (Type type : values()) {
                if (type.scheme.equals(str)) {
                    return type;
                }
            }
            return null;
        }

        public String getMetricsName() {
            return name();
        }
    }

    public MediaCollectionId(Type type, String str) {
        this.type = (Type) Validate.notNull(type);
        if (str.startsWith(type.getScheme() + "://")) {
            this.id = str.substring(type.getScheme().length() + 3);
        } else {
            this.id = str;
        }
    }

    public Type getType() {
        return this.type;
    }

    public String getId() {
        return this.id;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MediaCollectionId mediaCollectionId = (MediaCollectionId) obj;
        if (this.type != mediaCollectionId.type) {
            return false;
        }
        String str = this.id;
        String str2 = mediaCollectionId.id;
        return str != null ? str.equals(str2) : str2 == null;
    }

    public int hashCode() {
        Type type = this.type;
        int iHashCode = (type != null ? type.hashCode() : 0) * 31;
        String str = this.id;
        return iHashCode + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "MediaCollectionId{type=" + this.type + ", id='" + this.id + "'}";
    }
}
