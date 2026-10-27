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
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("PlaybackMediaItemInfo{").append("mPid = ").append(this.mPid).append(" ").append("mTitle = ").append(this.mTitle).append(" ").append("mAlbum = ").append(this.mAlbum).append(" ").append("mAlbumArtist = ").append(this.mAlbumArtist).append(" ").append("mGenre = ").append(this.mGenre).append(" ").append("mComposer = ").append(this.mComposer).append(" ");
        stringBuffer.append("}");
        return stringBuffer.toString();
    }
}
