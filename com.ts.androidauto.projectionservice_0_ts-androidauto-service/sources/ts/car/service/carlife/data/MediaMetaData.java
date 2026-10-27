package ts.car.service.carlife.data;

/* JADX INFO: loaded from: classes2.dex */
public class MediaMetaData {
    public String mSource = null;
    public String mSongName = null;
    public String mArtist = null;
    public String mAlbumName = null;
    public byte[] mAlbumArt = null;
    public String mSongId = null;
    public int mPlayListNum = -1;
    public int mDuration = -1;
    public int mMode = -1;

    public void clear() {
        this.mSource = null;
        this.mSongName = null;
        this.mArtist = null;
        this.mAlbumName = null;
        this.mAlbumArt = null;
        this.mSongId = null;
        this.mPlayListNum = -1;
        this.mDuration = -1;
        this.mMode = -1;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("MediaMetaData{");
        sb.append("mSource = ");
        sb.append(this.mSource);
        sb.append(" ");
        sb.append("mSongName = ");
        sb.append(this.mSongName);
        sb.append(" ");
        sb.append("mArtist = ");
        sb.append(this.mArtist);
        sb.append(" ");
        sb.append("mAlbumName = ");
        sb.append(this.mAlbumName);
        sb.append(" ");
        sb.append("mSongId = ");
        sb.append(this.mSongId);
        sb.append(" ");
        sb.append("mPlayListNum = ");
        sb.append(this.mPlayListNum);
        sb.append(" ");
        sb.append("mDuration = ");
        sb.append(this.mDuration);
        sb.append(" ");
        sb.append("mMode = ");
        sb.append(this.mMode);
        sb.append(" ");
        sb.append("mAlbumArt = ");
        byte[] bArr = this.mAlbumArt;
        if (bArr == null) {
            sb.append("null");
        } else {
            sb.append(bArr.length);
        }
        sb.append(" ");
        sb.append("}");
        return sb.toString();
    }
}
