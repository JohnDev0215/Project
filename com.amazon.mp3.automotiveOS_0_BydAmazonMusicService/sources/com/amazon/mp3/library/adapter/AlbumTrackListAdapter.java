package com.amazon.mp3.library.adapter;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.library.cache.image.IArtCache;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AlbumTrackListAdapter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\u0006\u0010\u0015\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0016\u0010\u0017J\b\u0010\u0003\u001a\u00020\u0002H\u0016R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0016\u0010\u000b\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\"\u0010\r\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\r\u0010\u0006\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\n¨\u0006\u0018"}, d2 = {"Lcom/amazon/mp3/library/adapter/AlbumTrackListAdapter;", "Lcom/amazon/mp3/library/adapter/TrackListAdapter;", "", "getCount", "", "showCompressedView", "Z", "getShowCompressedView", "()Z", "setShowCompressedView", "(Z)V", "defaultNumOfTracks", "I", "isOpenedFromBrowseHome", "setOpenedFromBrowseHome", "Landroid/content/Context;", "context", "Landroid/net/Uri;", "contentUri", "Lcom/amazon/mp3/library/cache/image/IArtCache;", "artCache", "shouldShowPrimeBadging", "<init>", "(Landroid/content/Context;Landroid/net/Uri;Lcom/amazon/mp3/library/cache/image/IArtCache;ZZ)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class AlbumTrackListAdapter extends TrackListAdapter {
    private int defaultNumOfTracks;
    private boolean isOpenedFromBrowseHome;
    private boolean showCompressedView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlbumTrackListAdapter(Context context, Uri contentUri, IArtCache artCache, boolean z, boolean z2) {
        super(context, contentUri, artCache, z);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(contentUri, "contentUri");
        Intrinsics.checkNotNullParameter(artCache, "artCache");
        this.showCompressedView = z2;
        this.defaultNumOfTracks = 3;
    }

    public final void setOpenedFromBrowseHome(boolean z) {
        this.isOpenedFromBrowseHome = z;
    }

    @Override // com.amazon.mp3.library.adapter.TrackListAdapter, android.widget.CursorAdapter, android.widget.Adapter
    public int getCount() {
        return super.getCount();
    }
}
