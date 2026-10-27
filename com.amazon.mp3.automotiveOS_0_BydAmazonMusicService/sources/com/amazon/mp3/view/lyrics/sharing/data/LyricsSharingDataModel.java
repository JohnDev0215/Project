package com.amazon.mp3.view.lyrics.sharing.data;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import androidx.core.app.FrameMetricsAggregator;
import com.amazon.mp3.lyrics.item.Lyrics;
import com.amazon.mp3.view.stage.util.StageLyricsUtil;
import com.amazon.music.media.playback.MediaItem;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LyricsSharingDataModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b+\b\u0086\b\u0018\u00002\u00020\u0001Bm\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\b\b\u0002\u00101\u001a\u00020\u0011\u0012\b\b\u0002\u00107\u001a\u00020\u0011\u0012\b\b\u0002\u0010:\u001a\u00020\u0011¢\u0006\u0004\b=\u0010>JB\u0010\u000e\u001a\u00020\r2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u00072\b\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\f\u001a\u0004\u0018\u00010\u000bJ\u0006\u0010\u000f\u001a\u00020\rJ\t\u0010\u0010\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0011HÖ\u0001J\u0013\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003R$\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR$\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR$\u0010\u0006\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0006\u0010\u001b\u001a\u0004\b \u0010\u001d\"\u0004\b!\u0010\u001fR$\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\b\u0010\"\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R$\u0010\n\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010'\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R$\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\f\u0010,\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\"\u00101\u001a\u00020\u00118\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b1\u00102\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\"\u00107\u001a\u00020\u00118\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b7\u00102\u001a\u0004\b8\u00104\"\u0004\b9\u00106R\"\u0010:\u001a\u00020\u00118\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b:\u00102\u001a\u0004\b;\u00104\"\u0004\b<\u00106¨\u0006?"}, d2 = {"Lcom/amazon/mp3/view/lyrics/sharing/data/LyricsSharingDataModel;", "", "Lcom/amazon/music/media/playback/MediaItem;", "mediaItem", "", "title", "artistName", "Landroid/graphics/drawable/Drawable;", "albumArt", "Landroid/graphics/Bitmap;", "blurAlbumArt", "Lcom/amazon/mp3/lyrics/item/Lyrics;", "lyrics", "", "setLyricsSharingDataModelForCurrentTrack", "reset", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/music/media/playback/MediaItem;", "getMediaItem", "()Lcom/amazon/music/media/playback/MediaItem;", "setMediaItem", "(Lcom/amazon/music/media/playback/MediaItem;)V", "Ljava/lang/String;", "getTitle", "()Ljava/lang/String;", "setTitle", "(Ljava/lang/String;)V", "getArtistName", "setArtistName", "Landroid/graphics/drawable/Drawable;", "getAlbumArt", "()Landroid/graphics/drawable/Drawable;", "setAlbumArt", "(Landroid/graphics/drawable/Drawable;)V", "Landroid/graphics/Bitmap;", "getBlurAlbumArt", "()Landroid/graphics/Bitmap;", "setBlurAlbumArt", "(Landroid/graphics/Bitmap;)V", "Lcom/amazon/mp3/lyrics/item/Lyrics;", "getLyrics", "()Lcom/amazon/mp3/lyrics/item/Lyrics;", "setLyrics", "(Lcom/amazon/mp3/lyrics/item/Lyrics;)V", "lastLineNumberInFocus", "I", "getLastLineNumberInFocus", "()I", "setLastLineNumberInFocus", "(I)V", "startIndexOfLyricsSelected", "getStartIndexOfLyricsSelected", "setStartIndexOfLyricsSelected", "endIndexOfLyricsSelected", "getEndIndexOfLyricsSelected", "setEndIndexOfLyricsSelected", "<init>", "(Lcom/amazon/music/media/playback/MediaItem;Ljava/lang/String;Ljava/lang/String;Landroid/graphics/drawable/Drawable;Landroid/graphics/Bitmap;Lcom/amazon/mp3/lyrics/item/Lyrics;III)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class LyricsSharingDataModel {
    private Drawable albumArt;
    private String artistName;
    private Bitmap blurAlbumArt;
    private int endIndexOfLyricsSelected;
    private int lastLineNumberInFocus;
    private Lyrics lyrics;
    private MediaItem mediaItem;
    private int startIndexOfLyricsSelected;
    private String title;

    public LyricsSharingDataModel() {
        this(null, null, null, null, null, null, 0, 0, 0, FrameMetricsAggregator.EVERY_DURATION, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LyricsSharingDataModel)) {
            return false;
        }
        LyricsSharingDataModel lyricsSharingDataModel = (LyricsSharingDataModel) other;
        return Intrinsics.areEqual(this.mediaItem, lyricsSharingDataModel.mediaItem) && Intrinsics.areEqual(this.title, lyricsSharingDataModel.title) && Intrinsics.areEqual(this.artistName, lyricsSharingDataModel.artistName) && Intrinsics.areEqual(this.albumArt, lyricsSharingDataModel.albumArt) && Intrinsics.areEqual(this.blurAlbumArt, lyricsSharingDataModel.blurAlbumArt) && Intrinsics.areEqual(this.lyrics, lyricsSharingDataModel.lyrics) && this.lastLineNumberInFocus == lyricsSharingDataModel.lastLineNumberInFocus && this.startIndexOfLyricsSelected == lyricsSharingDataModel.startIndexOfLyricsSelected && this.endIndexOfLyricsSelected == lyricsSharingDataModel.endIndexOfLyricsSelected;
    }

    public int hashCode() {
        MediaItem mediaItem = this.mediaItem;
        int iHashCode = (mediaItem == null ? 0 : mediaItem.hashCode()) * 31;
        String str = this.title;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.artistName;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Drawable drawable = this.albumArt;
        int iHashCode4 = (iHashCode3 + (drawable == null ? 0 : drawable.hashCode())) * 31;
        Bitmap bitmap = this.blurAlbumArt;
        int iHashCode5 = (iHashCode4 + (bitmap == null ? 0 : bitmap.hashCode())) * 31;
        Lyrics lyrics = this.lyrics;
        return ((((((iHashCode5 + (lyrics != null ? lyrics.hashCode() : 0)) * 31) + Integer.hashCode(this.lastLineNumberInFocus)) * 31) + Integer.hashCode(this.startIndexOfLyricsSelected)) * 31) + Integer.hashCode(this.endIndexOfLyricsSelected);
    }

    public String toString() {
        return "LyricsSharingDataModel(mediaItem=" + this.mediaItem + ", title=" + ((Object) this.title) + ", artistName=" + ((Object) this.artistName) + ", albumArt=" + this.albumArt + ", blurAlbumArt=" + this.blurAlbumArt + ", lyrics=" + this.lyrics + ", lastLineNumberInFocus=" + this.lastLineNumberInFocus + ", startIndexOfLyricsSelected=" + this.startIndexOfLyricsSelected + ", endIndexOfLyricsSelected=" + this.endIndexOfLyricsSelected + ')';
    }

    public LyricsSharingDataModel(MediaItem mediaItem, String str, String str2, Drawable drawable, Bitmap bitmap, Lyrics lyrics, int i, int i2, int i3) {
        this.mediaItem = mediaItem;
        this.title = str;
        this.artistName = str2;
        this.albumArt = drawable;
        this.blurAlbumArt = bitmap;
        this.lyrics = lyrics;
        this.lastLineNumberInFocus = i;
        this.startIndexOfLyricsSelected = i2;
        this.endIndexOfLyricsSelected = i3;
    }

    public /* synthetic */ LyricsSharingDataModel(MediaItem mediaItem, String str, String str2, Drawable drawable, Bitmap bitmap, Lyrics lyrics, int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? null : mediaItem, (i4 & 2) != 0 ? null : str, (i4 & 4) != 0 ? null : str2, (i4 & 8) != 0 ? null : drawable, (i4 & 16) != 0 ? null : bitmap, (i4 & 32) == 0 ? lyrics : null, (i4 & 64) != 0 ? 0 : i, (i4 & 128) != 0 ? -1 : i2, (i4 & 256) == 0 ? i3 : -1);
    }

    public final MediaItem getMediaItem() {
        return this.mediaItem;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getArtistName() {
        return this.artistName;
    }

    public final Drawable getAlbumArt() {
        return this.albumArt;
    }

    public final Bitmap getBlurAlbumArt() {
        return this.blurAlbumArt;
    }

    public final Lyrics getLyrics() {
        return this.lyrics;
    }

    public final int getLastLineNumberInFocus() {
        return this.lastLineNumberInFocus;
    }

    public final void setLastLineNumberInFocus(int i) {
        this.lastLineNumberInFocus = i;
    }

    public final int getStartIndexOfLyricsSelected() {
        return this.startIndexOfLyricsSelected;
    }

    public final void setStartIndexOfLyricsSelected(int i) {
        this.startIndexOfLyricsSelected = i;
    }

    public final int getEndIndexOfLyricsSelected() {
        return this.endIndexOfLyricsSelected;
    }

    public final void setEndIndexOfLyricsSelected(int i) {
        this.endIndexOfLyricsSelected = i;
    }

    public final void setLyricsSharingDataModelForCurrentTrack(MediaItem mediaItem, String title, String artistName, Drawable albumArt, Bitmap blurAlbumArt, Lyrics lyrics) {
        this.mediaItem = mediaItem;
        this.title = title;
        this.artistName = artistName;
        this.albumArt = albumArt;
        this.blurAlbumArt = blurAlbumArt;
        this.lyrics = lyrics;
    }

    public final void reset() {
        this.mediaItem = null;
        this.title = null;
        this.artistName = null;
        this.albumArt = null;
        this.blurAlbumArt = null;
        StageLyricsUtil.INSTANCE.resetLyricsSelection(this.lyrics, this.startIndexOfLyricsSelected, this.endIndexOfLyricsSelected);
        this.startIndexOfLyricsSelected = -1;
        this.endIndexOfLyricsSelected = -1;
    }
}
