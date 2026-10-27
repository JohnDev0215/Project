package ts.car.iap.protocol.common;

/* JADX INFO: loaded from: classes.dex */
public class NowPlayingPbMediaItemInfo {
    private boolean mPidValid = false;
    private int mPid = -1;
    private boolean mTitleValid = false;
    private String mTitle = null;
    private boolean mAlbumTitleValid = false;
    private String mAlbum = null;
    private boolean mArtistValid = false;
    private String mArtist = null;
    private boolean mAlbumArtistValid = false;
    private String mAlbumArtist = null;
    private boolean mGenreValid = false;
    private String mGenre = null;
    private boolean mComposerValid = false;
    private String mComposer = null;

    public int getPid() {
        return this.mPid;
    }

    public void setPid(int pid) {
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
        return this.mAlbum;
    }

    public void setAlbumTitle(String album) {
        this.mAlbum = album;
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

    public boolean isartistValid() {
        return this.mArtistValid;
    }

    public String getAlbumArtist() {
        return this.mAlbumArtist;
    }

    public void setAlbumArtist(String albumArtist) {
        this.mAlbumArtist = albumArtist;
        this.mAlbumArtistValid = true;
    }

    public boolean isAlbumArtistValid() {
        return this.mAlbumArtistValid;
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
}
