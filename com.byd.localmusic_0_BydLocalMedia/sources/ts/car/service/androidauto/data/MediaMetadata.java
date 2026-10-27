package ts.car.service.androidauto.data;

/* JADX INFO: loaded from: classes5.dex */
public class MediaMetadata {
    private String mAlbum;
    private byte[] mAlbumArt;
    private String mArtist;
    private String mArtworkPath;
    private String mComposer;
    private int mDurationSeconds;
    private String mGenrel;
    private String mMediaSource;
    private String mPlaylist;
    private int mRatings;
    private int mRepeatMode;
    private int mShuffleMode;
    private String mSong;
    private int mTotalTracks;
    private int mTrackNumber;
    private String mYear;

    public String getSong() {
        return this.mSong;
    }

    public void setSong(String str) {
        this.mSong = str;
    }

    public String getAlbum() {
        return this.mAlbum;
    }

    public void setAlbum(String str) {
        this.mAlbum = str;
    }

    public String getArtist() {
        return this.mArtist;
    }

    public void setArtist(String str) {
        this.mArtist = str;
    }

    public int getDurationSeconds() {
        return this.mDurationSeconds;
    }

    public void setDurationSeconds(int i) {
        this.mDurationSeconds = i;
    }

    public byte[] getAlbumArt() {
        return this.mAlbumArt;
    }

    public void setAlbumArt(byte[] bArr) {
        this.mAlbumArt = bArr;
    }

    public String getPlaylist() {
        return this.mPlaylist;
    }

    public void setPlaylist(String str) {
        this.mPlaylist = str;
    }

    public int getRatings() {
        return this.mRatings;
    }

    public void setRatings(int i) {
        this.mRatings = i;
    }

    public String getMediaSource() {
        return this.mMediaSource;
    }

    public void setMediaSource(String str) {
        this.mMediaSource = str;
    }

    public int getShuffleMode() {
        return this.mShuffleMode;
    }

    public void setShuffleMode(int i) {
        this.mShuffleMode = i;
    }

    public int getRepeatMode() {
        return this.mRepeatMode;
    }

    public void setRepeatMode(int i) {
        this.mRepeatMode = i;
    }

    @Deprecated
    public String getArtworkPath() {
        return this.mArtworkPath;
    }

    @Deprecated
    public void setArtworkPath(String str) {
        this.mArtworkPath = str;
    }

    @Deprecated
    public String getComposer() {
        return this.mComposer;
    }

    @Deprecated
    public void setComposer(String str) {
        this.mComposer = str;
    }

    @Deprecated
    public String getGenrel() {
        return this.mGenrel;
    }

    @Deprecated
    public void setGenrel(String str) {
        this.mGenrel = str;
    }

    @Deprecated
    public String getYear() {
        return this.mYear;
    }

    @Deprecated
    public void setYear(String str) {
        this.mYear = str;
    }

    @Deprecated
    public int getTrackNumber() {
        return this.mTrackNumber;
    }

    @Deprecated
    public void setTrackNumber(int i) {
        this.mTrackNumber = i;
    }

    @Deprecated
    public int getTotalTracks() {
        return this.mTotalTracks;
    }

    @Deprecated
    public void setTotalTracks(int i) {
        this.mTotalTracks = i;
    }

    public String toString() {
        return this.mSong + " " + this.mAlbum + " " + this.mArtist + " " + this.mDurationSeconds + " " + this.mShuffleMode + " " + this.mRepeatMode;
    }
}
