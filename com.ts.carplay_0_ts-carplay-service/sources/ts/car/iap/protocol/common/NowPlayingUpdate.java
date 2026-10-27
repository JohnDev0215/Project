package ts.car.iap.protocol.common;

/* JADX INFO: loaded from: classes.dex */
public class NowPlayingUpdate {
    private String mAlbumTitle;
    private String mArtist;
    private String mComposer;
    private String mGenre;
    private String mPid;
    private String mPlaybackAppBundleID;
    private String mPlaybackAppName;
    private String mTitle;
    private boolean mPidValid = false;
    private boolean mTitleValid = false;
    private boolean mAlbumTitleValid = false;
    private boolean mArtistValid = false;
    private boolean mGenreValid = false;
    private boolean mComposerValid = false;
    private boolean mPlaybackAppNameValid = false;
    private boolean mPlaybackAppBundleIDValid = false;
    private long mPlaybackDurationMs = -1;
    private int mChapterCount = -1;
    private int mPlaybackStatus = -1;
    private long mPlaybackElapsedTimeMs = -1;
    private long mPlaybackQueueIndex = -1;
    private long mPlaybackQueueCount = -1;
    private long mPlaybackQueueChapterIndex = -1;
    private int mPlaybackShuffleMode = -1;
    private int mPlaybackRepeatMode = -1;
    private int mIsSetElapsedTimeAvailable = -1;

    public String getPid() {
        return this.mPid;
    }

    public void setPid(String pid) {
        this.mPid = pid;
        this.mPidValid = true;
    }

    public boolean isPidValid() {
        return this.mPidValid;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
        this.mTitleValid = true;
    }

    public boolean isTitleValid() {
        return this.mTitleValid;
    }

    public String getAlbumTitle() {
        return this.mAlbumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.mAlbumTitle = albumTitle;
        this.mAlbumTitleValid = true;
    }

    public boolean isAlbumTitleValid() {
        return this.mAlbumTitleValid;
    }

    public String getArtist() {
        return this.mArtist;
    }

    public void setArtist(String artist) {
        this.mArtist = artist;
        this.mArtistValid = true;
    }

    public boolean isArtistValid() {
        return this.mArtistValid;
    }

    public String getGenre() {
        return this.mGenre;
    }

    public void setGenre(String genre) {
        this.mGenre = genre;
        this.mGenreValid = true;
    }

    public boolean isGenreValid() {
        return this.mGenreValid;
    }

    public String getComposer() {
        return this.mComposer;
    }

    public void setComposer(String composer) {
        this.mComposer = composer;
        this.mComposerValid = true;
    }

    public boolean isComposerValid() {
        return this.mComposerValid;
    }

    public long getPlaybackDurationMs() {
        return this.mPlaybackDurationMs;
    }

    public void setPlaybackDurationMs(long durationMs) {
        this.mPlaybackDurationMs = durationMs;
    }

    public int getChapterCount() {
        return this.mChapterCount;
    }

    public void setChapterCount(int count) {
        this.mChapterCount = count;
    }

    public int getPlaybackStatus() {
        return this.mPlaybackStatus;
    }

    public void setPlaybackStatus(int status) {
        this.mPlaybackStatus = status;
    }

    public long getPlaybackElapsedTimeMs() {
        return this.mPlaybackElapsedTimeMs;
    }

    public void setPlaybackElapsedTimeMs(long timeMs) {
        this.mPlaybackElapsedTimeMs = timeMs;
    }

    public long getPlaybackQueueIndex() {
        return this.mPlaybackQueueIndex;
    }

    public void setPlaybackQueueIndex(long index) {
        this.mPlaybackQueueIndex = index;
    }

    public long getPlaybackQueueCount() {
        return this.mPlaybackQueueCount;
    }

    public void setPlaybackQueueCount(long count) {
        this.mPlaybackQueueCount = count;
    }

    public long getPlaybackQueueChapterIndex() {
        return this.mPlaybackQueueChapterIndex;
    }

    public void setPlaybackQueueChapterIndex(long index) {
        this.mPlaybackQueueChapterIndex = index;
    }

    public int getPlaybackShuffleMode() {
        return this.mPlaybackShuffleMode;
    }

    public void setPlaybackShuffleMode(int mode) {
        this.mPlaybackShuffleMode = mode;
    }

    public int getPlaybackRepeatMode() {
        return this.mPlaybackRepeatMode;
    }

    public void setPlaybackRepeatMode(int mode) {
        this.mPlaybackRepeatMode = mode;
    }

    public String getPlaybackAppName() {
        return this.mPlaybackAppName;
    }

    public void setPlaybackAppName(String playbackAppName) {
        this.mPlaybackAppName = playbackAppName;
        this.mPlaybackAppNameValid = true;
    }

    public boolean isPlaybackAppNameValid() {
        return this.mPlaybackAppNameValid;
    }

    public String getPlaybackAppBundleID() {
        return this.mPlaybackAppBundleID;
    }

    public void setPlaybackAppBundleID(String playbackAppBundleID) {
        this.mPlaybackAppBundleID = playbackAppBundleID;
        this.mPlaybackAppBundleIDValid = true;
    }

    public boolean isPlaybackAppBundleIDValid() {
        return this.mPlaybackAppBundleIDValid;
    }

    public int getIsSetElapsedTimeAvailable() {
        return this.mIsSetElapsedTimeAvailable;
    }

    public void setIsSetElapsedTimeAvailable(int available) {
        this.mIsSetElapsedTimeAvailable = available;
    }
}
