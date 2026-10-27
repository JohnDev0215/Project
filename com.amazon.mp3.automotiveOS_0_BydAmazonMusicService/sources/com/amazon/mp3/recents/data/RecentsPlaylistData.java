package com.amazon.mp3.recents.data;

import android.net.Uri;
import com.amazon.mp3.library.util.PlaylistUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RecentsPlaylistData.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0017¢\u0006\u0004\b\u001e\u0010\u001fR\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\"\u0010\n\u001a\u00020\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\"\u0010\u0011\u001a\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R$\u0010\u0018\u001a\u0004\u0018\u00010\u00178\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006 "}, d2 = {"Lcom/amazon/mp3/recents/data/RecentsPlaylistData;", "", "", "playlistId", "J", "getPlaylistId", "()J", "setPlaylistId", "(J)V", "Landroid/net/Uri;", "playlistUri", "Landroid/net/Uri;", "getPlaylistUri", "()Landroid/net/Uri;", "setPlaylistUri", "(Landroid/net/Uri;)V", "Lcom/amazon/mp3/library/util/PlaylistUtil$PlaylistTrackInfo;", "mPlaylistInfo", "Lcom/amazon/mp3/library/util/PlaylistUtil$PlaylistTrackInfo;", "getMPlaylistInfo", "()Lcom/amazon/mp3/library/util/PlaylistUtil$PlaylistTrackInfo;", "setMPlaylistInfo", "(Lcom/amazon/mp3/library/util/PlaylistUtil$PlaylistTrackInfo;)V", "", "imageUrl", "Ljava/lang/String;", "getImageUrl", "()Ljava/lang/String;", "setImageUrl", "(Ljava/lang/String;)V", "<init>", "(JLandroid/net/Uri;Lcom/amazon/mp3/library/util/PlaylistUtil$PlaylistTrackInfo;Ljava/lang/String;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class RecentsPlaylistData {
    private String imageUrl;
    private PlaylistUtil.PlaylistTrackInfo mPlaylistInfo;
    private long playlistId;
    private Uri playlistUri;

    public RecentsPlaylistData(long j, Uri playlistUri, PlaylistUtil.PlaylistTrackInfo mPlaylistInfo, String str) {
        Intrinsics.checkNotNullParameter(playlistUri, "playlistUri");
        Intrinsics.checkNotNullParameter(mPlaylistInfo, "mPlaylistInfo");
        this.playlistId = j;
        this.playlistUri = playlistUri;
        this.mPlaylistInfo = mPlaylistInfo;
        this.imageUrl = str;
    }

    public final long getPlaylistId() {
        return this.playlistId;
    }

    public final Uri getPlaylistUri() {
        return this.playlistUri;
    }

    public final PlaylistUtil.PlaylistTrackInfo getMPlaylistInfo() {
        return this.mPlaylistInfo;
    }
}
