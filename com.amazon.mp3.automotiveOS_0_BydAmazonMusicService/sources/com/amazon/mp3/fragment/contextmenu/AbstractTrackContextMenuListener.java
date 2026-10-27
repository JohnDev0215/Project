package com.amazon.mp3.fragment.contextmenu;

import android.content.Context;
import android.net.Uri;
import android.view.ContextMenu;
import android.view.Menu;
import androidx.core.content.ContextCompat;
import com.amazon.mp3.activity.NetworkErrorDialogActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.fragment.contextmenu.TrackContextMenuProvider;
import com.amazon.mp3.library.fragment.AddAndDownloadTrackTask;
import com.amazon.mp3.library.fragment.OnItemFinishedAddingListener;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.util.ConnectivityUtil;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AbstractTrackContextMenuListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\b&\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J$\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u001c\u0010\u000f\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0016J2\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0002J\u001a\u0010\u0017\u001a\u00020\u00152\u0006\u0010\t\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0010\u0010\u0018\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\u0019\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\fH\u0016J\b\u0010\u001b\u001a\u00020\bH\u0016J\u0018\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\u0015H\u0014J\u0012\u0010\u001f\u001a\u00020\b2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u0010\u0010\"\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\fH\u0014J\u0012\u0010#\u001a\u00020\b2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u0010\u0010$\u001a\u00020\b2\u0006\u0010 \u001a\u00020%H\u0014J\u0010\u0010&\u001a\u00020\b2\u0006\u0010 \u001a\u00020!H\u0014J\u0018\u0010'\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\u0015H\u0016J\u0012\u0010(\u001a\u00020\b2\b\u0010\u001a\u001a\u0004\u0018\u00010\fH\u0016J\b\u0010)\u001a\u00020\u0015H\u0016J\b\u0010*\u001a\u00020\u0015H\u0014J\b\u0010+\u001a\u00020\u0015H\u0016J\b\u0010,\u001a\u00020\u0015H\u0014J\b\u0010-\u001a\u00020\u0015H\u0014J\b\u0010.\u001a\u00020\u0015H\u0016J\u0010\u0010/\u001a\u00020\u00152\u0006\u00100\u001a\u00020\u0015H\u0014J\u0010\u00101\u001a\u00020\u00152\u0006\u00100\u001a\u00020\u0015H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/AbstractTrackContextMenuListener;", "Lcom/amazon/mp3/fragment/contextmenu/TrackContextMenuProvider$TrackContextMenuProviderListener;", "context", "Landroid/content/Context;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/amazon/mp3/library/fragment/OnItemFinishedAddingListener;", "(Landroid/content/Context;Lcom/amazon/mp3/library/fragment/OnItemFinishedAddingListener;)V", "addAndDownloadPrimeTrack", "", "position", "", "uri", "Landroid/net/Uri;", "item", "Lcom/amazon/mp3/library/item/AbstractItem;", "addPrimeTrack", "asin", "", "luid", "addToLibrary", "showToast", "", "download", "isNotAddedPrime", "onAddContentComplete", "onRemoveContentComplete", "trackUri", "onRemoveContentConfirm", "removeFromPlayQueue", "trackPos", "isShuffled", "removeFromPlayQueueMenuItem", "menu", "Landroid/view/ContextMenu;", "removeFromPlaylist", "removeFromPlaylistMenuItem", "removeItemRemoveFromPlayQueue", "Landroid/view/Menu;", "removeItemRemoveFromPlaylist", "removeTrackFromPlayQueue", "removeTrackFromPlaylist", "shouldRemoveAddToPlaylist", "shouldRemoveAddToPlaylistButton", "shouldRemoveAlbumDetailItem", "shouldRemoveShowAlbumDetail", "shouldRemoveTrackDownload", "shouldRemoveTrackDownloadItem", "shouldRemoveVideoPlay", "isVideoTrack", "shouldRemoveVideoPlayItem", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class AbstractTrackContextMenuListener implements TrackContextMenuProvider.TrackContextMenuProviderListener {
    private final Context context;
    private final OnItemFinishedAddingListener listener;

    public void onAddContentComplete(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
    }

    @Override // com.amazon.mp3.fragment.contextmenu.RemoveLibraryContentProvider.OnRemoveLibraryContentListener
    public void onRemoveContentComplete(Uri trackUri) {
        Intrinsics.checkNotNullParameter(trackUri, "trackUri");
    }

    @Override // com.amazon.mp3.fragment.contextmenu.RemoveLibraryContentProvider.OnRemoveLibraryContentListener
    public void onRemoveContentConfirm() {
    }

    protected void removeFromPlayQueue(int trackPos, boolean isShuffled) {
    }

    protected void removeFromPlaylist(Uri trackUri) {
        Intrinsics.checkNotNullParameter(trackUri, "trackUri");
    }

    protected boolean shouldRemoveAddToPlaylistButton() {
        return false;
    }

    protected boolean shouldRemoveShowAlbumDetail() {
        return false;
    }

    protected boolean shouldRemoveTrackDownload() {
        return false;
    }

    protected boolean shouldRemoveVideoPlay(boolean isVideoTrack) {
        return true;
    }

    public AbstractTrackContextMenuListener(Context context, OnItemFinishedAddingListener onItemFinishedAddingListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.listener = onItemFinishedAddingListener;
    }

    @Override // com.amazon.mp3.fragment.contextmenu.TrackContextMenuProvider.TrackContextMenuProviderListener
    public /* bridge */ /* synthetic */ boolean shouldRemoveVideoPlayItem(Boolean bool) {
        return shouldRemoveVideoPlayItem(bool.booleanValue());
    }

    @Override // com.amazon.mp3.fragment.contextmenu.TrackContextMenuProvider.TrackContextMenuProviderListener
    public void removeFromPlaylistMenuItem(ContextMenu menu) {
        if (menu == null) {
            return;
        }
        removeItemRemoveFromPlaylist(menu);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.TrackContextMenuProvider.TrackContextMenuProviderListener
    public void removeFromPlayQueueMenuItem(ContextMenu menu) {
        if (menu == null) {
            return;
        }
        removeItemRemoveFromPlayQueue(menu);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.TrackContextMenuProvider.TrackContextMenuProviderListener
    public boolean shouldRemoveAlbumDetailItem() {
        return shouldRemoveShowAlbumDetail();
    }

    @Override // com.amazon.mp3.fragment.contextmenu.TrackContextMenuProvider.TrackContextMenuProviderListener
    public boolean shouldRemoveTrackDownloadItem() {
        return shouldRemoveTrackDownload();
    }

    public boolean shouldRemoveVideoPlayItem(boolean isVideoTrack) {
        return shouldRemoveVideoPlay(isVideoTrack);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.TrackContextMenuProvider.TrackContextMenuProviderListener
    public boolean shouldRemoveAddToPlaylist() {
        return shouldRemoveAddToPlaylistButton();
    }

    @Override // com.amazon.mp3.fragment.contextmenu.TrackContextMenuProvider.TrackContextMenuProviderListener
    public void removeTrackFromPlaylist(Uri trackUri) {
        if (trackUri == null) {
            return;
        }
        removeFromPlaylist(trackUri);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.TrackContextMenuProvider.TrackContextMenuProviderListener
    public void removeTrackFromPlayQueue(int trackPos, boolean isShuffled) {
        removeFromPlayQueue(trackPos, isShuffled);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.TrackContextMenuProvider.TrackContextMenuProviderListener
    public boolean isNotAddedPrime(int position, AbstractItem item) {
        MusicTrack musicTrack = item instanceof MusicTrack ? (MusicTrack) item : null;
        return (musicTrack == null || !musicTrack.getContentCatalogStatus().isHawkfire() || musicTrack.getOwnershipStatus().isInLibrary()) ? false : true;
    }

    @Override // com.amazon.mp3.fragment.contextmenu.TrackContextMenuProvider.TrackContextMenuProviderListener
    public void addPrimeTrack(String asin, String luid) {
        if (asin == null) {
            return;
        }
        addToLibrary(this.context, asin, luid, true, false);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.TrackContextMenuProvider.TrackContextMenuProviderListener
    public void addAndDownloadPrimeTrack(int position, Uri uri, AbstractItem item) {
        if (!ConnectivityUtil.hasAnyInternetConnection(this.context)) {
            Context context = this.context;
            ContextCompat.startActivity(context, NetworkErrorDialogActivity.getStartIntent(context), null);
            return;
        }
        MusicTrack musicTrack = item instanceof MusicTrack ? (MusicTrack) item : null;
        if (musicTrack == null) {
            return;
        }
        Context context2 = this.context;
        String asin = musicTrack.getAsin();
        Intrinsics.checkNotNullExpressionValue(asin, "track.asin");
        addToLibrary(context2, asin, musicTrack.getLuid(), true, true);
    }

    protected void removeItemRemoveFromPlaylist(ContextMenu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        menu.removeItem(R.id.MenuTrackContextDeleteFromPlaylist);
    }

    protected void removeItemRemoveFromPlayQueue(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        menu.removeItem(R.id.MenuTrackRemoveFromPlayQueue);
    }

    private final void addToLibrary(Context context, String asin, String luid, boolean showToast, boolean download) {
        new AddAndDownloadTrackTask(context, asin, luid, download, showToast, this.listener).execute(new Void[0]);
    }
}
