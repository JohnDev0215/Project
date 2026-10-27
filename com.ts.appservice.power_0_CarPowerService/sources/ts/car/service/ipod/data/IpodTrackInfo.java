package ts.car.service.ipod.data;

/* JADX INFO: loaded from: classes.dex */
public class IpodTrackInfo {
    private String mAlbum;
    private String mAppBundleid;
    private String mAppName;
    private String mArtist;
    private boolean[] mBarry;
    private String mComposer;
    private String mGenre;
    private boolean mIsChangePositionAvaible;
    private int mMediaType;
    private String mPid;
    private String mTitle;

    public IpodTrackInfo() {
        this.mBarry = new boolean[1];
    }

    public IpodTrackInfo(String title) {
        this.mBarry = new boolean[1];
        this.mTitle = title;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
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

    public String getGenre() {
        return this.mGenre;
    }

    public void setGenre(String genre) {
        this.mGenre = genre;
    }

    public String getComposer() {
        return this.mComposer;
    }

    public void setComposer(String composer) {
        this.mComposer = composer;
    }

    public boolean getIsChangePositionAvaible() {
        return this.mIsChangePositionAvaible;
    }

    public void setIsChangePositionAvaible(boolean isChangePositionAvaible) {
        this.mIsChangePositionAvaible = isChangePositionAvaible;
    }

    public String getPid() {
        return this.mPid;
    }

    public void setPid(String pid) {
        this.mPid = pid;
    }

    public int getMediaType() {
        return this.mMediaType;
    }

    public void setMediaType(int mediaType) {
        this.mMediaType = mediaType;
    }

    public String getAppName() {
        return this.mAppName;
    }

    public void setAppName(String appName) {
        this.mAppName = appName;
    }

    public String getAppBundleid() {
        return this.mAppBundleid;
    }

    public void setAppBundleid(String appBundleid) {
        this.mAppBundleid = appBundleid;
    }

    public String toString() {
        return "IpodTrackInfo{Title='" + this.mTitle + "', Album='" + this.mAlbum + "', Artist='" + this.mArtist + "', Genre='" + this.mGenre + "', Composer='" + this.mComposer + "', IsChangePositionAvaible='" + this.mIsChangePositionAvaible + "', Pid='" + this.mPid + "', MediaType=" + this.mMediaType + "}";
    }
}
