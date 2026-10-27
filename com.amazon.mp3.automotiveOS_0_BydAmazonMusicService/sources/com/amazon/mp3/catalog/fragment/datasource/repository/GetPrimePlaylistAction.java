package com.amazon.mp3.catalog.fragment.datasource.repository;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.playlist.db.PrimePlaylistDatabase;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.mp3.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GetPrimePlaylistAction.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012R\u0016\u0010\u000e\u001a\n \u000f*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/datasource/repository/GetPrimePlaylistAction;", "", "cache", "Lcom/amazon/mp3/playlist/db/PrimePlaylistDatabase;", "playlistId", "", "playlistSource", "", "addToLibrary", "", "isSharedUserPlaylist", "context", "Landroid/content/Context;", "(Lcom/amazon/mp3/playlist/db/PrimePlaylistDatabase;Ljava/lang/String;ILjava/lang/Boolean;ZLandroid/content/Context;)V", "TAG", "kotlin.jvm.PlatformType", "Ljava/lang/Boolean;", "run", "Lcom/amazon/mp3/playlist/CatalogPlaylist;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class GetPrimePlaylistAction {
    private final String TAG;
    private final Boolean addToLibrary;
    private final PrimePlaylistDatabase cache;
    private final Context context;
    private final boolean isSharedUserPlaylist;
    private final String playlistId;
    private final int playlistSource;

    public GetPrimePlaylistAction(PrimePlaylistDatabase cache, String str, int i, Boolean bool, boolean z, Context context) {
        Intrinsics.checkNotNullParameter(cache, "cache");
        Intrinsics.checkNotNullParameter(context, "context");
        this.cache = cache;
        this.playlistId = str;
        this.playlistSource = i;
        this.addToLibrary = bool;
        this.isSharedUserPlaylist = z;
        this.context = context;
        this.TAG = GetPrimePlaylistAction.class.getSimpleName();
    }

    public final CatalogPlaylist run() {
        if (TextUtils.isEmpty(this.playlistId)) {
            Log.error(this.TAG, "Cannot retrieve Playlist without an ID");
        }
        if (Intrinsics.areEqual(this.addToLibrary, Boolean.TRUE)) {
            return PrimePlaylistUtil.followPrimePlaylist(this.context, this.cache, this.playlistSource, this.playlistId);
        }
        if (this.isSharedUserPlaylist) {
            return PrimePlaylistUtil.fetchSharedUserPlaylist(this.cache, this.playlistSource, this.playlistId);
        }
        return PrimePlaylistUtil.fetchPrimePlaylist(this.cache, this.playlistSource, this.playlistId);
    }
}
