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

    public String getSong() {
        return this.mSong;
    }

    public void setSong(String song) {
        this.mSong = song;
    }

    public String getAlbum() {
        return this.mAlbum;
    }

    public void setAlbum(String album) {
        this.mAlbum = album;
    }

    public String getArtist() {
        return this.mArtist;
    }

    public void setArtist(String artist) {
        this.mArtist = artist;
    }

    public int getDurationSeconds() {
        return this.mDurationSeconds;
    }

    public void setDurationSeconds(int durationSeconds) {
        this.mDurationSeconds = durationSeconds;
    }

    public byte[] getAlbumArt() {
        return this.mAlbumArt;
    }

    public void setAlbumArt(byte[] albumArt) {
        this.mAlbumArt = albumArt;
    }

    public String getPlaylist() {
        return this.mPlaylist;
    }

    public void setPlaylist(String playlist) {
        this.mPlaylist = playlist;
    }

    public int getRatings() {
        return this.mRatings;
    }

    public void setRatings(int ratings) {
        this.mRatings = ratings;
    }

    public String getMediaSource() {
        return this.mMediaSource;
    }

    public void setMediaSource(String mediaSource) {
        this.mMediaSource = mediaSource;
    }

    public int getShuffleMode() {
        return this.mShuffleMode;
    }

    public void setShuffleMode(int shuffleMode) {
        this.mShuffleMode = shuffleMode;
    }

    public int getRepeatMode() {
        return this.mRepeatMode;
    }

    public void setRepeatMode(int repeatMode) {
        this.mRepeatMode = repeatMode;
    }

    @Deprecated
    public String getArtworkPath() {
        return this.mArtworkPath;
    }

    @Deprecated
    public void setArtworkPath(String artworkPath) {
        this.mArtworkPath = artworkPath;
    }

    @Deprecated
    public String getComposer() {
        return this.mComposer;
    }

    @Deprecated
    public void setComposer(String composer) {
        this.mComposer = composer;
    }

    @Deprecated
    public String getGenrel() {
        return this.mGenrel;
    }

    @Deprecated
    public void setGenrel(String genrel) {
        this.mGenrel = genrel;
    }

    @Deprecated
    public String getYear() {
        return this.mYear;
    }

    @Deprecated
    public void setYear(String year) {
        this.mYear = year;
    }

    @Deprecated
    public int getTrackNumber() {
        return this.mTrackNumber;
    }

    @Deprecated
    public void setTrackNumber(int trackNumber) {
        this.mTrackNumber = trackNumber;
    }

    @Deprecated
    public int getTotalTracks() {
        return this.mTotalTracks;
    }

    @Deprecated
    public void setTotalTracks(int totalTracks) {
        this.mTotalTracks = totalTracks;
    }

    public String toString() {
        return this.mSong + " " + this.mAlbum + " " + this.mArtist + " " + this.mDurationSeconds + " " + this.mShuffleMode + " " + this.mRepeatMode;
    }
}
