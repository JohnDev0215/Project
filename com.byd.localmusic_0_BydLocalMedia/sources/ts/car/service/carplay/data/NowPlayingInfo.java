package ts.car.service.carplay.data;

/* JADX INFO: loaded from: classes5.dex */
public class NowPlayingInfo {
    private String mMediaItemTitle = null;
    private String mMediaItemArtist = null;
    private long mMediaItemPlaybackDurationMs = -1;
    private int mPlaybackStatus = 0;
    private long mPlaybackElapsedTimeMs = -1;
    private int mPlaybackShuffleMode = -1;
    private int mPlaybackRepeatMode = -1;
    private int mArtworkSize = -1;
    private String mArtworkPath = null;
    private byte[] mArtworkData = null;
    private String mAlbumTitle = null;
    private String mPid = null;

    public static final class PlaybackRepeatMode {
        public static final int PLAYBACK_REPEAT_MODE_ALL = 2;
        public static final int PLAYBACK_REPEAT_MODE_NOT_PRESENT = -1;
        public static final int PLAYBACK_REPEAT_MODE_OFF = 0;
        public static final int PLAYBACK_REPEAT_MODE_ONE = 1;
    }

    public static final class PlaybackShuffleMode {
        public static final int PLAYBACK_SHUFFLE_MODE_ALBUMS = 2;
        public static final int PLAYBACK_SHUFFLE_MODE_NOT_PRESENT = -1;
        public static final int PLAYBACK_SHUFFLE_MODE_OFF = 0;
        public static final int PLAYBACK_SHUFFLE_MODE_SONGS = 1;
    }

    public static final class PlaybackStatus {
        public static final int PLAYBACK_STATE_PAUSED = 2;
        public static final int PLAYBACK_STATE_PLAYING = 1;
        public static final int PLAYBACK_STATE_SEEK_BACKWARD = 4;
        public static final int PLAYBACK_STATE_SEEK_FORWARD = 3;
        public static final int PLAYBACK_STATE_STOPPED = 0;
    }

    public void clear() {
        this.mMediaItemTitle = null;
        this.mMediaItemArtist = null;
        this.mMediaItemPlaybackDurationMs = -1L;
        this.mPlaybackStatus = 0;
        this.mPlaybackElapsedTimeMs = -1L;
        this.mPlaybackShuffleMode = -1;
        this.mPlaybackRepeatMode = -1;
        this.mArtworkSize = -1;
        this.mArtworkPath = null;
        this.mArtworkData = null;
        this.mAlbumTitle = null;
        this.mPid = null;
    }

    public void copy(NowPlayingInfo nowPlayingInfo) {
        if (nowPlayingInfo != null) {
            this.mMediaItemTitle = nowPlayingInfo.getMediaItemTitle();
            this.mMediaItemArtist = nowPlayingInfo.getMediaItemArtist();
            this.mMediaItemPlaybackDurationMs = nowPlayingInfo.getMediaItemPlaybackDurationMs();
            this.mPlaybackStatus = nowPlayingInfo.getPlaybackStatus();
            this.mPlaybackElapsedTimeMs = nowPlayingInfo.getPlaybackElapsedTimeMs();
            this.mPlaybackShuffleMode = nowPlayingInfo.getPlaybackShuffleMode();
            this.mPlaybackRepeatMode = nowPlayingInfo.getPlaybackRepeatMode();
            this.mArtworkSize = nowPlayingInfo.getArtworkSize();
            this.mArtworkPath = nowPlayingInfo.getArtworkPath();
            int i = this.mArtworkSize;
            if (i <= 0) {
                this.mArtworkData = null;
            } else {
                this.mArtworkData = new byte[i];
                if (nowPlayingInfo.getArtworkData() != null) {
                    System.arraycopy(nowPlayingInfo.getArtworkData(), 0, this.mArtworkData, 0, this.mArtworkSize);
                }
            }
            this.mAlbumTitle = nowPlayingInfo.getAlbumTitle();
            this.mPid = nowPlayingInfo.getPid();
        }
    }

    public boolean isDefaultValue() {
        return this.mMediaItemTitle == null && this.mMediaItemArtist == null && this.mMediaItemPlaybackDurationMs <= 0 && this.mPlaybackStatus == 0 && this.mPid == null && this.mPlaybackElapsedTimeMs <= 0 && this.mPlaybackShuffleMode <= 0 && this.mPlaybackRepeatMode <= 0 && this.mArtworkSize <= 0 && this.mArtworkPath == null && this.mArtworkData == null && this.mAlbumTitle == null;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("NowPlayingInfo{");
        stringBuffer.append("mMediaItemTitle = ");
        stringBuffer.append(this.mMediaItemTitle);
        stringBuffer.append(" ");
        stringBuffer.append("mMediaItemArtist = ");
        stringBuffer.append(this.mMediaItemArtist);
        stringBuffer.append(" ");
        stringBuffer.append("mMediaItemPlaybackDurationMs = ");
        stringBuffer.append(this.mMediaItemPlaybackDurationMs);
        stringBuffer.append(" ");
        stringBuffer.append("mPlaybackStatus = ");
        stringBuffer.append(this.mPlaybackStatus);
        stringBuffer.append(" ");
        stringBuffer.append("mPlaybackElapsedTimeMs = ");
        stringBuffer.append(this.mPlaybackElapsedTimeMs);
        stringBuffer.append(" ");
        stringBuffer.append("mPlaybackShuffleMode = ");
        stringBuffer.append(this.mPlaybackShuffleMode);
        stringBuffer.append(" ");
        stringBuffer.append("mPlaybackRepeatMode = ");
        stringBuffer.append(this.mPlaybackRepeatMode);
        stringBuffer.append(" ");
        stringBuffer.append("mArtworkSize = ");
        stringBuffer.append(this.mArtworkSize);
        stringBuffer.append(" ");
        stringBuffer.append("mArtworkPath = ");
        stringBuffer.append(this.mArtworkPath);
        stringBuffer.append(" ");
        stringBuffer.append("mAlbumTitle = ");
        stringBuffer.append(this.mAlbumTitle);
        stringBuffer.append(" ");
        stringBuffer.append("mPid = ");
        stringBuffer.append(this.mPid);
        stringBuffer.append(" ");
        stringBuffer.append("}");
        return stringBuffer.toString();
    }

    public String getMediaItemTitle() {
        return this.mMediaItemTitle;
    }

    public void setMediaItemTitle(String str) {
        this.mMediaItemTitle = str;
    }

    public String getMediaItemArtist() {
        return this.mMediaItemArtist;
    }

    public void setMediaItemArtist(String str) {
        this.mMediaItemArtist = str;
    }

    public long getMediaItemPlaybackDurationMs() {
        return this.mMediaItemPlaybackDurationMs;
    }

    public void setMediaItemPlaybackDurationMs(long j) {
        this.mMediaItemPlaybackDurationMs = j;
    }

    public int getPlaybackStatus() {
        return this.mPlaybackStatus;
    }

    public void setPlaybackStatus(int i) {
        this.mPlaybackStatus = i;
    }

    public long getPlaybackElapsedTimeMs() {
        return this.mPlaybackElapsedTimeMs;
    }

    public void setPlaybackElapsedTimeMs(long j) {
        this.mPlaybackElapsedTimeMs = j;
    }

    public int getPlaybackShuffleMode() {
        return this.mPlaybackShuffleMode;
    }

    public void setPlaybackShuffleMode(int i) {
        this.mPlaybackShuffleMode = i;
    }

    public int getPlaybackRepeatMode() {
        return this.mPlaybackRepeatMode;
    }

    public void setPlaybackRepeatMode(int i) {
        this.mPlaybackRepeatMode = i;
    }

    public int getArtworkSize() {
        return this.mArtworkSize;
    }

    public void setArtworkSize(int i) {
        this.mArtworkSize = i;
    }

    public String getArtworkPath() {
        return this.mArtworkPath;
    }

    public void setArtworkPath(String str) {
        this.mArtworkPath = str;
    }

    public byte[] getArtworkData() {
        return this.mArtworkData;
    }

    public void setArtworkData(byte[] bArr) {
        if (getArtworkSize() > 0) {
            byte[] bArr2 = new byte[getArtworkSize()];
            this.mArtworkData = bArr2;
            System.arraycopy(bArr, 0, bArr2, 0, getArtworkSize());
            return;
        }
        this.mArtworkData = null;
    }

    public String getAlbumTitle() {
        return this.mAlbumTitle;
    }

    public void setAlbumTitle(String str) {
        this.mAlbumTitle = str;
    }

    public String getPid() {
        return this.mPid;
    }

    public void setPid(String str) {
        this.mPid = str;
    }
}
