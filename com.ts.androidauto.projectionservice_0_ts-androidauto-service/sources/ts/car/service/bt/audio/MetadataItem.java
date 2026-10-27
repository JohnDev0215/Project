package ts.car.service.bt.audio;

/* JADX INFO: loaded from: classes2.dex */
public class MetadataItem {
    public static final int ITEM_TYPE_ALBUM = 2;
    public static final int ITEM_TYPE_ARTIST = 3;
    public static final int ITEM_TYPE_GENRES = 4;
    public static final int ITEM_TYPE_PLAYLIST = 5;
    public static final int ITEM_TYPE_TITLES = 1;
    public static final int ITEM_TYPE_TRACK = 0;
    public static final int ITEM_TYPE_YEARS = 6;
    public String albumCoverArtUri;
    public String albumName;
    public String artistName;
    public String genre;
    public String playTime;
    public String title;
    public String totalTrackNumber;
    public String trackNumber;
    public int type;
    public String uid;

    public String toString() {
        String str = "[type=" + this.type + ", UID=" + this.uid + ", title=" + this.title + ", artistName=" + this.artistName + ", albumName=" + this.albumName + ", genre=" + this.genre + ", trackNumber=" + this.trackNumber + ", totalTrackNumber=" + this.totalTrackNumber + ", playTime=" + this.playTime + ", albumCoverArtUri=" + this.albumCoverArtUri + "]";
        return str;
    }
}
