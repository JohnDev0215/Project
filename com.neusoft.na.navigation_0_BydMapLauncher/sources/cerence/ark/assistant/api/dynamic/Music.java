package cerence.ark.assistant.api.dynamic;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class Music implements Parcelable {
    public static final Parcelable.Creator<Music> CREATOR = new Parcelable.Creator<Music>() { // from class: cerence.ark.assistant.api.dynamic.Music.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Music createFromParcel(Parcel parcel) {
            return new Music(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Music[] newArray(int i) {
            return new Music[i];
        }
    };
    private String mAlbum;
    private long mAlbumId;
    private String mArtist;
    private long mArtistId;
    private String mGenre;
    private long mGenreId;
    private long mId;
    private String mTitle;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Music() {
    }

    protected Music(Parcel parcel) {
        this.mId = parcel.readLong();
        this.mTitle = parcel.readString();
        this.mAlbum = parcel.readString();
        this.mAlbumId = parcel.readLong();
        this.mArtist = parcel.readString();
        this.mArtistId = parcel.readLong();
        this.mGenre = parcel.readString();
        this.mGenreId = parcel.readLong();
    }

    public long getId() {
        return this.mId;
    }

    public void setId(long j) {
        this.mId = j;
    }

    public String getTitle() {
        String str = this.mTitle;
        return str == null ? "" : str;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public String getAlbum() {
        String str = this.mAlbum;
        return str == null ? "" : str;
    }

    public void setAlbum(String str) {
        this.mAlbum = str;
    }

    public String getArtist() {
        String str = this.mArtist;
        return str == null ? "" : str;
    }

    public void setArtist(String str) {
        this.mArtist = str;
    }

    public String getGenre() {
        String str = this.mGenre;
        return str == null ? "" : str;
    }

    public void setGenre(String str) {
        this.mGenre = str;
    }

    public long getAlbumId() {
        return this.mAlbumId;
    }

    public void setAlbumId(long j) {
        this.mAlbumId = j;
    }

    public long getArtistId() {
        return this.mArtistId;
    }

    public void setArtistId(long j) {
        this.mArtistId = j;
    }

    public long getGenreId() {
        return this.mGenreId;
    }

    public void setGenreId(long j) {
        this.mGenreId = j;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.mId);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mAlbum);
        parcel.writeLong(this.mAlbumId);
        parcel.writeString(this.mArtist);
        parcel.writeLong(this.mArtistId);
        parcel.writeString(this.mGenre);
        parcel.writeLong(this.mGenreId);
    }

    public String toString() {
        return "Music{mId=" + this.mId + ", mTitle='" + this.mTitle + "', mAlbum='" + this.mAlbum + "', mAlbumId=" + this.mAlbumId + ", mArtist='" + this.mArtist + "', mArtistId=" + this.mArtistId + ", mGenre='" + this.mGenre + "', mGenreId=" + this.mGenreId + '}';
    }
}
