package ts.car.service.carplay.data;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackMediaItemInfo {
    public int mPid = -1;
    public String mTitle = null;
    public String mAlbum = null;
    public String mArtist = null;
    public String mAlbumArtist = null;
    public String mGenre = null;
    public String mComposer = null;

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("PlaybackMediaItemInfo{");
        sb.append("mPid = ");
        sb.append(this.mPid);
        sb.append(" ");
        sb.append("mTitle = ");
        sb.append(this.mTitle);
        sb.append(" ");
        sb.append("mAlbum = ");
        sb.append(this.mAlbum);
        sb.append(" ");
        sb.append("mAlbumArtist = ");
        sb.append(this.mAlbumArtist);
        sb.append(" ");
        sb.append("mGenre = ");
        sb.append(this.mGenre);
        sb.append(" ");
        sb.append("mComposer = ");
        sb.append(this.mComposer);
        sb.append(" ");
        sb.append("}");
        return sb.toString();
    }
}
