package ts.car.iap.protocol.common;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MediaLibraryUpdate {
    public static final int PROGRESS_DONE = 100;
    private List<String> mMediaItemDeletePids;
    private List<MediaItem> mMediaItems;
    private String mMediaLibName;
    private String mMediaLibRevision;
    private String mMediaLibUid;
    private List<String> mMediaPlaylistDeletePids;
    private List<MediaPlaylist> mMediaPlaylists;
    private int mMediaLibType = -1;
    private int mMediaLibUpdateProgress = -1;
    private int mMediaLibReset = -1;

    public String getMediaLibName() {
        return this.mMediaLibName;
    }

    public void setMediaLibName(String name) {
        this.mMediaLibName = name;
    }

    public String getMediaLibUid() {
        return this.mMediaLibUid;
    }

    public void setMediaLibUid(String uid) {
        this.mMediaLibUid = uid;
    }

    public int getMediaLibType() {
        return this.mMediaLibType;
    }

    public void setMediaLibType(int type) {
        if (type != 0 && type != 2) {
            return;
        }
        this.mMediaLibType = type;
    }

    public String getMediaLibRevision() {
        return this.mMediaLibRevision;
    }

    public void setMediaLibRevision(String revision) {
        this.mMediaLibRevision = revision;
    }

    public int getMediaLibUpdateProgress() {
        return this.mMediaLibUpdateProgress;
    }

    public void setMediaLibUpdateProgress(int progress) {
        if (progress < 0 || progress > 100) {
            return;
        }
        this.mMediaLibUpdateProgress = progress;
    }

    public int getMediaLibReset() {
        return this.mMediaLibReset;
    }

    public void setMediaLibReset(int reset) {
        this.mMediaLibReset = reset;
    }

    public List<MediaItem> getMediaItems() {
        return this.mMediaItems;
    }

    public void addMediaItem(MediaItem item) {
        if (item == null) {
            return;
        }
        if (this.mMediaItems == null) {
            this.mMediaItems = new ArrayList();
        }
        this.mMediaItems.add(item);
    }

    public List<MediaPlaylist> getMediaPlaylists() {
        return this.mMediaPlaylists;
    }

    public void addMediaPlaylist(MediaPlaylist playlist) {
        if (playlist == null) {
            return;
        }
        if (this.mMediaPlaylists == null) {
            this.mMediaPlaylists = new ArrayList();
        }
        this.mMediaPlaylists.add(playlist);
    }

    public List<String> getMediaItemDeletPids() {
        return this.mMediaItemDeletePids;
    }

    public void addMediaItemDeletePid(String pid) {
        if (pid == null) {
            return;
        }
        if (this.mMediaItemDeletePids == null) {
            this.mMediaItemDeletePids = new ArrayList();
        }
        this.mMediaItemDeletePids.add(pid);
    }

    public List<String> getMediaPlaylistDeletPids() {
        return this.mMediaPlaylistDeletePids;
    }

    public void addMediaPlaylistDeletePid(String pid) {
        if (pid == null) {
            return;
        }
        if (this.mMediaPlaylistDeletePids == null) {
            this.mMediaPlaylistDeletePids = new ArrayList();
        }
        this.mMediaPlaylistDeletePids.add(pid);
    }

    public String toString() {
        return "MediaLibraryUpdate{mMediaLibName='" + this.mMediaLibName + "', mMediaLibUid='" + this.mMediaLibUid + "', mMediaLibType=" + this.mMediaLibType + ", mMediaLibRevision='" + this.mMediaLibRevision + "', mMediaLibUpdateProgress=" + this.mMediaLibUpdateProgress + ", mMediaLibReset=" + this.mMediaLibReset + ", mMediaItems=" + this.mMediaItems + ", mMediaPlaylists=" + this.mMediaPlaylists + ", mMediaItemDeletePids=" + this.mMediaItemDeletePids + ", mMediaPlaylistDeletePids=" + this.mMediaPlaylistDeletePids + '}';
    }

    public static final class MediaLibType {
        public static final int INVALID = -1;
        public static final int LOCAL_LIB = 0;
        public static final int RADIO_LIB = 2;

        public static boolean checkValidity(int type) {
            return type == 0 || type == 2;
        }
    }
}
