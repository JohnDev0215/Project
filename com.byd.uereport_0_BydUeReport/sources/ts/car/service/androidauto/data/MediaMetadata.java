package ts.car.service.androidauto.data;

/* JADX INFO: loaded from: classes2.dex */
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

    public String getAlbum() {
        return this.mAlbum;
    }

    public byte[] getAlbumArt() {
        return this.mAlbumArt;
    }

    public String getArtist() {
        return this.mArtist;
    }

    @Deprecated
    public String getArtworkPath() {
        return this.mArtworkPath;
    }

    @Deprecated
    public String getComposer() {
        return this.mComposer;
    }

    public int getDurationSeconds() {
        return this.mDurationSeconds;
    }

    @Deprecated
    public String getGenrel() {
        return this.mGenrel;
    }

    public String getMediaSource() {
        return this.mMediaSource;
    }

    public String getPlaylist() {
        return this.mPlaylist;
    }

    public int getRatings() {
        return this.mRatings;
    }

    public int getRepeatMode() {
        return this.mRepeatMode;
    }

    public int getShuffleMode() {
        return this.mShuffleMode;
    }

    public String getSong() {
        return this.mSong;
    }

    @Deprecated
    public int getTotalTracks() {
        return this.mTotalTracks;
    }

    @Deprecated
    public int getTrackNumber() {
        return this.mTrackNumber;
    }

    @Deprecated
    public String getYear() {
        return this.mYear;
    }

    public void setAlbum(String str) {
        this.mAlbum = str;
    }

    public void setAlbumArt(byte[] bArr) {
        this.mAlbumArt = bArr;
    }

    public void setArtist(String str) {
        this.mArtist = str;
    }

    @Deprecated
    public void setArtworkPath(String str) {
        this.mArtworkPath = str;
    }

    @Deprecated
    public void setComposer(String str) {
        this.mComposer = str;
    }

    public void setDurationSeconds(int i8) {
        this.mDurationSeconds = i8;
    }

    @Deprecated
    public void setGenrel(String str) {
        this.mGenrel = str;
    }

    public void setMediaSource(String str) {
        this.mMediaSource = str;
    }

    public void setPlaylist(String str) {
        this.mPlaylist = str;
    }

    public void setRatings(int i8) {
        this.mRatings = i8;
    }

    public void setRepeatMode(int i8) {
        this.mRepeatMode = i8;
    }

    public void setShuffleMode(int i8) {
        this.mShuffleMode = i8;
    }

    public void setSong(String str) {
        this.mSong = str;
    }

    @Deprecated
    public void setTotalTracks(int i8) {
        this.mTotalTracks = i8;
    }

    @Deprecated
    public void setTrackNumber(int i8) {
        this.mTrackNumber = i8;
    }

    @Deprecated
    public void setYear(String str) {
        this.mYear = str;
    }

    public String toString() {
        return this.mSong + " " + this.mAlbum + " " + this.mArtist + " " + this.mDurationSeconds + " " + this.mShuffleMode + " " + this.mRepeatMode;
    }
}
