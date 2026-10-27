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
        stringBuffer.append("PlaybackMediaItemInfo{");
        stringBuffer.append("mPid = ");
        stringBuffer.append(this.mPid);
        stringBuffer.append(" ");
        stringBuffer.append("mTitle = ");
        stringBuffer.append(this.mTitle);
        stringBuffer.append(" ");
        stringBuffer.append("mAlbum = ");
        stringBuffer.append(this.mAlbum);
        stringBuffer.append(" ");
        stringBuffer.append("mAlbumArtist = ");
        stringBuffer.append(this.mAlbumArtist);
        stringBuffer.append(" ");
        stringBuffer.append("mGenre = ");
        stringBuffer.append(this.mGenre);
        stringBuffer.append(" ");
        stringBuffer.append("mComposer = ");
        stringBuffer.append(this.mComposer);
        stringBuffer.append(" ");
        stringBuffer.append("}");
        return stringBuffer.toString();
    }
}
