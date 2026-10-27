package com.amazon.mp3.fragment.contextmenu;

import android.app.Activity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.widget.AdapterView;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.library.item.CatalogContent;
import com.amazon.mp3.library.provider.StateProvider;
import com.amazon.mp3.library.provider.source.nowplaying.SequencerHelper;
import com.amazon.mp3.prime.browse.metadata.PrimePlaylist;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.find.model.search.SearchItem;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.share.PlaylistShareProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VideoPlaylistContentMenuProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u0003:\u0001'B\u0017\u0012\u0006\u0010\f\u001a\u00020\u0015\u0012\u0006\u0010#\u001a\u00020\"¢\u0006\u0004\b%\u0010&J\u001a\u0010\b\u001a\u00020\u00072\b\u0010\u0004\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\n\u001a\u00020\tH\u0014J2\u0010\u0014\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\t2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0014J*\u0010\u0019\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00152\u0006\u0010\u0004\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00172\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0016J*\u0010\u0019\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00152\u0006\u0010\u0004\u001a\u00020\u00162\b\u0010\u001a\u001a\u0004\u0018\u00010\u00022\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012J1\u0010 \u001a\u00020\u0007\"\u0010\b\u0000\u0010\u001d*\u0004\u0018\u00010\u001b*\u0004\u0018\u00010\u001c2\u0006\u0010\u001e\u001a\u00028\u00002\u0006\u0010\u001f\u001a\u00020\u0005H\u0016¢\u0006\u0004\b \u0010!R\u0014\u0010#\u001a\u00020\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010$¨\u0006("}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/VideoPlaylistContextMenuProvider;", "Lcom/amazon/mp3/fragment/contextmenu/ContextMenuProvider;", "Lcom/amazon/mp3/prime/browse/metadata/PrimePlaylist;", "Lcom/amazon/mp3/util/ContentAccessUtil$CatalogActionListener;", "item", "", "canContinue", "", "continueCatalogAction", "", "getContextMenuLayoutId", "Landroid/app/Activity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroid/view/ContextMenu;", "menu", "", ParserUtil.TAG_QUERY_PARAM_NAME, "position", "", "sourceID", "updateContextMenuItems", "Landroidx/fragment/app/FragmentActivity;", "Landroid/view/MenuItem;", "Landroid/widget/AdapterView$AdapterContextMenuInfo;", "menuInfo", "onContextMenuItemSelected", "videoPlaylist", "Lcom/amazon/music/find/model/search/SearchItem;", "Lcom/amazon/mp3/library/item/CatalogContent;", "D", Environment.PLAYLIST_PATH, "playNext", "addToPlayQueue", "(Lcom/amazon/music/find/model/search/SearchItem;Z)V", "Lcom/amazon/mp3/fragment/contextmenu/VideoPlaylistContextMenuProvider$VideoPlaylistContextMenuProviderListener;", "contextMenuProviderListener", "Lcom/amazon/mp3/fragment/contextmenu/VideoPlaylistContextMenuProvider$VideoPlaylistContextMenuProviderListener;", "<init>", "(Landroidx/fragment/app/FragmentActivity;Lcom/amazon/mp3/fragment/contextmenu/VideoPlaylistContextMenuProvider$VideoPlaylistContextMenuProviderListener;)V", "VideoPlaylistContextMenuProviderListener", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class VideoPlaylistContextMenuProvider extends ContextMenuProvider<PrimePlaylist> implements ContentAccessUtil.CatalogActionListener<PrimePlaylist> {
    private final VideoPlaylistContextMenuProviderListener contextMenuProviderListener;

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    protected int getContextMenuLayoutId() {
        return R.menu.video_playlist_context;
    }

    public VideoPlaylistContextMenuProvider(FragmentActivity activity, VideoPlaylistContextMenuProviderListener contextMenuProviderListener) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(contextMenuProviderListener, "contextMenuProviderListener");
        this.contextMenuProviderListener = contextMenuProviderListener;
        init(activity);
    }

    /* JADX INFO: compiled from: VideoPlaylistContentMenuProvider.kt */
    @Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u000f\u0010\u0010J \u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u0012\u0010\u000b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\nH&J\u0010\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u0004H&¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/VideoPlaylistContextMenuProvider$VideoPlaylistContextMenuProviderListener;", "", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Lcom/amazon/mp3/prime/browse/metadata/PrimePlaylist;", "videoPlaylist", "Lcom/amazon/mp3/util/ContentAccessUtil$ContentAccessOperation;", "operation", "", "canContinueAction", "Lcom/amazon/mp3/library/provider/StateProvider;", "getPlaylistStateProvider", "videoPlayist", "", "startPlaylistPlayback", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static abstract class VideoPlaylistContextMenuProviderListener {
        public abstract StateProvider<PrimePlaylist> getPlaylistStateProvider();

        public abstract void startPlaylistPlayback(PrimePlaylist videoPlayist);

        public boolean canContinueAction(FragmentActivity activity, PrimePlaylist videoPlaylist, ContentAccessUtil.ContentAccessOperation operation) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(videoPlaylist, "videoPlaylist");
            Intrinsics.checkNotNullParameter(operation, "operation");
            return ContentAccessUtil.canContinueCatalogAction(activity, videoPlaylist, operation);
        }
    }

    @Override // com.amazon.mp3.util.ContentAccessUtil.CatalogActionListener
    public void continueCatalogAction(PrimePlaylist item, boolean canContinue) {
        if (!canContinue || item == null) {
            return;
        }
        this.contextMenuProviderListener.startPlaylistPlayback(item);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    protected void updateContextMenuItems(Activity activity, ContextMenu menu, Object tag, int position, String sourceID) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(tag, "tag");
        setStyledHeaderTitle(((PrimePlaylist) tag).getTitle(), menu);
        if (SequencerHelper.canModifyQueue()) {
            return;
        }
        menu.removeItem(R.id.MenuVideoPlaylistAddToPlayqueue);
        menu.removeItem(R.id.MenuVideoPlaylistPlayNext);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    public boolean onContextMenuItemSelected(FragmentActivity activity, MenuItem item, AdapterView.AdapterContextMenuInfo menuInfo, String sourceID) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(menuInfo, "menuInfo");
        Object tag = menuInfo.targetView.getTag();
        if (tag != null) {
            return onContextMenuItemSelected(activity, item, (PrimePlaylist) tag, sourceID);
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.prime.browse.metadata.PrimePlaylist");
    }

    public final boolean onContextMenuItemSelected(FragmentActivity activity, MenuItem item, PrimePlaylist videoPlaylist, String sourceID) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(item, "item");
        setClickedItemHolder(videoPlaylist);
        int itemId = item.getItemId();
        setClickedEntityIdAndType(videoPlaylist == null ? null : videoPlaylist.getAsin(), EntityIdType.ASIN);
        switch (itemId) {
            case R.id.MenuVideoPlaylistAddToPlayqueue /* 2131427675 */:
                if (videoPlaylist != null) {
                    addToPlayQueue(activity, videoPlaylist, false);
                }
                sendMetricEvent(ActionType.ADD_TO_PLAY_Q_OVERFLOW);
                return true;
            case R.id.MenuVideoPlaylistContextShare /* 2131427676 */:
                if (videoPlaylist != null && this.contextMenuProviderListener.canContinueAction(activity, videoPlaylist, ContentAccessUtil.ContentAccessOperation.BROWSE)) {
                    String musicDomain = EndpointsProvider.get().getMusicDomain();
                    if (musicDomain == null) {
                        musicDomain = "";
                    }
                    new PlaylistShareProvider(activity, musicDomain, videoPlaylist.getAsin(), videoPlaylist.getTitle(), videoPlaylist.getArtworkUri()).startShare();
                }
                return true;
            case R.id.MenuVideoPlaylistPlay /* 2131427677 */:
                ContentAccessUtil.checkCanContinueCatalogAction(activity, videoPlaylist, ContentAccessUtil.ContentAccessOperation.STREAM, this);
                sendMetricEvent(ActionType.PLAY_NOW_OVERFLOW);
                return true;
            case R.id.MenuVideoPlaylistPlayNext /* 2131427678 */:
                if (videoPlaylist != null) {
                    addToPlayQueue(activity, videoPlaylist, true);
                }
                sendMetricEvent(ActionType.PLAY_NEXT_OVERFLOW);
                return true;
            default:
                return false;
        }
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    public <D extends SearchItem & CatalogContent> void addToPlayQueue(D playlist, boolean playNext) {
        PrimePlaylistContextMenuUtil.INSTANCE.addTracksToQueue(this.mActivity, playlist, playNext, this.contextMenuProviderListener.getPlaylistStateProvider());
    }
}
