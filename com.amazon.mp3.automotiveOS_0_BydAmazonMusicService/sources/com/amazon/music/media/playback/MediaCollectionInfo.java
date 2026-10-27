package com.amazon.music.media.playback;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.access.MediaAccessInfo;
import com.amazon.music.media.playback.util.StringUtils;
import java.util.EnumMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class MediaCollectionInfo {
    private final MediaAccessInfo accessInfo;
    private final Map<MediaCollectionId.Type, MediaCollectionId> ids = new EnumMap(MediaCollectionId.Type.class);
    private final String name;
    private final MediaCollectionId.Type primaryIdType;
    private final String subtitle;
    private final MediaCollectionType type;

    public static String getMediaCollectionIdString(MediaCollectionInfo mediaCollectionInfo, MediaCollectionId.Type type, @Nullable String str) {
        MediaCollectionId id;
        if (mediaCollectionInfo == null || (id = mediaCollectionInfo.getId(type)) == null) {
            return str;
        }
        String id2 = id.getId();
        return StringUtils.isEmpty(id2) ? str : id2;
    }

    public MediaCollectionInfo(String str, String str2, MediaCollectionId mediaCollectionId, MediaCollectionType mediaCollectionType, MediaAccessInfo mediaAccessInfo, MediaCollectionId... mediaCollectionIdArr) {
        this.name = StringUtils.nullToEmpty(str);
        this.subtitle = StringUtils.nullToEmpty(str2);
        this.type = mediaCollectionType;
        this.accessInfo = mediaAccessInfo;
        if (mediaCollectionIdArr != null) {
            for (MediaCollectionId mediaCollectionId2 : mediaCollectionIdArr) {
                if (mediaCollectionId2 != null) {
                    this.ids.put(mediaCollectionId2.getType(), mediaCollectionId2);
                }
            }
        }
        if (mediaCollectionId != null) {
            this.ids.put(mediaCollectionId.getType(), mediaCollectionId);
            this.primaryIdType = mediaCollectionId.getType();
        } else {
            this.primaryIdType = null;
        }
    }

    public void addId(MediaCollectionId mediaCollectionId) {
        this.ids.put(mediaCollectionId.getType(), mediaCollectionId);
    }

    @NonNull
    public String getName() {
        return this.name;
    }

    @NonNull
    public String getSubtitle() {
        return this.subtitle;
    }

    public MediaCollectionId getId() {
        return this.ids.get(this.primaryIdType);
    }

    public MediaCollectionId getId(MediaCollectionId.Type type) {
        return this.ids.get(type);
    }

    public Map<MediaCollectionId.Type, MediaCollectionId> getIds() {
        return this.ids;
    }

    public MediaCollectionType getType() {
        return this.type;
    }

    public MediaCollectionId.Type getPrimaryType() {
        return this.primaryIdType;
    }

    public MediaAccessInfo getMediaAccessInfo() {
        return this.accessInfo;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MediaCollectionInfo mediaCollectionInfo = (MediaCollectionInfo) obj;
        if (getId() != null) {
            return getId().equals(mediaCollectionInfo.getId());
        }
        if (mediaCollectionInfo.getId() != null || !this.name.equals(mediaCollectionInfo.name) || !this.subtitle.equals(mediaCollectionInfo.subtitle)) {
            return false;
        }
        MediaCollectionType mediaCollectionType = this.type;
        MediaCollectionType mediaCollectionType2 = mediaCollectionInfo.type;
        return mediaCollectionType != null ? mediaCollectionType.equals(mediaCollectionType2) : mediaCollectionType2 == null;
    }

    public int hashCode() {
        if (getId() != null) {
            return getId().hashCode() + 1;
        }
        int iHashCode = ((this.name.hashCode() * 31) + this.subtitle.hashCode()) * 31;
        MediaCollectionType mediaCollectionType = this.type;
        return iHashCode + (mediaCollectionType != null ? mediaCollectionType.hashCode() : 0);
    }

    public String toString() {
        return "MediaCollectionInfo{name='" + this.name + "', subtitle='" + this.subtitle + "', id=" + getId() + ", type=" + this.type + ", accessInfo=" + this.accessInfo + ", allIds=" + StringUtils.toString(this.ids) + '}';
    }
}
