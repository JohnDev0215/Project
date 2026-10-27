package com.amazon.mp3.fragment.contextmenu;

import android.app.Activity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.widget.AdapterView;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.external.api.uri.QuerySongByAsin;
import com.amazon.mp3.library.item.CatalogContent;
import com.amazon.mp3.library.provider.source.nowplaying.SequencerHelper;
import com.amazon.mp3.playback.service.metrics.PlaybackMetricInformation;
import com.amazon.mp3.prime.PrimePlaylistTracksTable;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.mp3.prime.cta.PrimeTrackTask;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.find.model.search.SearchItem;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.visualon.OSMPUtils.voMimeTypes;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VideoContextMenuProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001$B\u0017\u0012\u0006\u0010\u0006\u001a\u00020\u0010\u0012\u0006\u0010 \u001a\u00020\u001f¢\u0006\u0004\b\"\u0010#J\b\u0010\u0004\u001a\u00020\u0003H\u0014J2\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u00032\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0014J*\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0006\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00132\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0016J*\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0006\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\t2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0016J.\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0006\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00112\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00020\u00172\b\u0010\r\u001a\u0004\u0018\u00010\fJ1\u0010\u001d\u001a\u00020\u000e\"\u0010\b\u0000\u0010\u001b*\u0004\u0018\u00010\u0019*\u0004\u0018\u00010\u001a2\u0006\u0010\u0012\u001a\u00028\u00002\u0006\u0010\u001c\u001a\u00020\u0015H\u0016¢\u0006\u0004\b\u001d\u0010\u001eR\u0014\u0010 \u001a\u00020\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010!¨\u0006%"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/VideoContextMenuProvider;", "Lcom/amazon/mp3/fragment/contextmenu/ContextMenuProvider;", "Lcom/amazon/mp3/prime/browse/metadata/PrimeTrack;", "", "getContextMenuLayoutId", "Landroid/app/Activity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroid/view/ContextMenu;", "menu", "", ParserUtil.TAG_QUERY_PARAM_NAME, "position", "", "sourceID", "", "updateContextMenuItems", "Landroidx/fragment/app/FragmentActivity;", "Landroid/view/MenuItem;", "item", "Landroid/widget/AdapterView$AdapterContextMenuInfo;", "menuInfo", "", "onContextMenuItemSelected", "", "videos", "Lcom/amazon/music/find/model/search/SearchItem;", "Lcom/amazon/mp3/library/item/CatalogContent;", "D", "playNext", "addToPlayQueue", "(Lcom/amazon/music/find/model/search/SearchItem;Z)V", "Lcom/amazon/mp3/fragment/contextmenu/VideoContextMenuProvider$VideoContextMenuProviderListener;", "providerListener", "Lcom/amazon/mp3/fragment/contextmenu/VideoContextMenuProvider$VideoContextMenuProviderListener;", "<init>", "(Landroidx/fragment/app/FragmentActivity;Lcom/amazon/mp3/fragment/contextmenu/VideoContextMenuProvider$VideoContextMenuProviderListener;)V", "VideoContextMenuProviderListener", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class VideoContextMenuProvider extends ContextMenuProvider<PrimeTrack> {
    private final VideoContextMenuProviderListener providerListener;

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    protected int getContextMenuLayoutId() {
        return R.menu.video_context;
    }

    public VideoContextMenuProvider(FragmentActivity activity, VideoContextMenuProviderListener providerListener) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(providerListener, "providerListener");
        this.providerListener = providerListener;
        init(activity);
    }

    /* JADX INFO: compiled from: VideoContextMenuProvider.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/VideoContextMenuProvider$VideoContextMenuProviderListener;", "", "()V", "canContinueAction", "", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", voMimeTypes.VOBASE_TYPE_VIDEO, "Lcom/amazon/mp3/prime/browse/metadata/PrimeTrack;", "operation", "Lcom/amazon/mp3/util/ContentAccessUtil$ContentAccessOperation;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static class VideoContextMenuProviderListener {
        public boolean canContinueAction(FragmentActivity activity, PrimeTrack video, ContentAccessUtil.ContentAccessOperation operation) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(operation, "operation");
            return ContentAccessUtil.canContinueCatalogAction(activity, video, operation);
        }
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    protected void updateContextMenuItems(Activity activity, ContextMenu menu, Object tag, int position, String sourceID) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(tag, "tag");
        PrimeTrack primeTrack = tag instanceof PrimeTrack ? (PrimeTrack) tag : null;
        setStyledHeaderTitle(primeTrack != null ? primeTrack.getTitle() : null, menu);
        if (SequencerHelper.canModifyQueue()) {
            return;
        }
        menu.removeItem(R.id.MenuVideoAddToPlayqueue);
        menu.removeItem(R.id.MenuVideoPlayNext);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    public boolean onContextMenuItemSelected(FragmentActivity activity, MenuItem item, AdapterView.AdapterContextMenuInfo menuInfo, String sourceID) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(menuInfo, "menuInfo");
        Object tag = menuInfo.targetView.getTag();
        if (tag != null) {
            return onContextMenuItemSelected(activity, item, CollectionsKt__CollectionsJVMKt.listOf((PrimeTrack) tag), sourceID);
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.prime.browse.metadata.PrimeTrack");
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    public boolean onContextMenuItemSelected(FragmentActivity activity, MenuItem item, Object tag, String sourceID) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(tag, "tag");
        return onContextMenuItemSelected(activity, item, CollectionsKt__CollectionsJVMKt.listOf((PrimeTrack) tag), sourceID);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0078  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onContextMenuItemSelected(androidx.fragment.app.FragmentActivity r4, android.view.MenuItem r5, java.util.List<? extends com.amazon.mp3.prime.browse.metadata.PrimeTrack> r6, java.lang.String r7) {
        /*
            r3 = this;
            java.lang.String r7 = "activity"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r7)
            java.lang.String r7 = "item"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r7)
            java.lang.String r7 = "videos"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r7)
            boolean r7 = r6.isEmpty()
            r0 = 0
            if (r7 == 0) goto L18
            return r0
        L18:
            java.lang.Object r7 = r6.get(r0)
            com.amazon.mp3.prime.browse.metadata.PrimeTrack r7 = (com.amazon.mp3.prime.browse.metadata.PrimeTrack) r7
            r3.setClickedItemHolder(r7)
            int r5 = r5.getItemId()
            java.lang.String r1 = r7.getAsin()
            com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType r2 = com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType.ASIN
            r3.setClickedEntityIdAndType(r1, r2)
            r1 = 2131427659(0x7f0b014b, float:1.847694E38)
            if (r5 == r1) goto L78
            r1 = 2131427666(0x7f0b0152, float:1.8476955E38)
            if (r5 == r1) goto L78
            switch(r5) {
                case 2131427671: goto L65;
                case 2131427672: goto L78;
                case 2131427673: goto L50;
                case 2131427674: goto L3c;
                default: goto L3b;
            }
        L3b:
            goto L9b
        L3c:
            com.amazon.mp3.fragment.contextmenu.VideoContextMenuProvider$VideoContextMenuProviderListener r5 = r3.providerListener
            com.amazon.mp3.util.ContentAccessUtil$ContentAccessOperation r6 = com.amazon.mp3.util.ContentAccessUtil.ContentAccessOperation.STREAM
            boolean r4 = r5.canContinueAction(r4, r7, r6)
            if (r4 == 0) goto L4a
            r4 = 1
            r3.addToPlayQueue(r7, r4)
        L4a:
            com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType r4 = com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType.PLAY_NEXT_OVERFLOW
            r3.sendMetricEvent(r4)
            goto L9b
        L50:
            com.amazon.mp3.fragment.contextmenu.VideoContextMenuProvider$VideoContextMenuProviderListener r5 = r3.providerListener
            com.amazon.mp3.util.ContentAccessUtil$ContentAccessOperation r1 = com.amazon.mp3.util.ContentAccessUtil.ContentAccessOperation.BROWSE
            boolean r5 = r5.canContinueAction(r4, r7, r1)
            if (r5 == 0) goto L5f
            com.amazon.mp3.playback.video.VideoUtil r5 = com.amazon.mp3.playback.video.VideoUtil.INSTANCE
            r5.playVideo(r4, r6, r0)
        L5f:
            com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType r4 = com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType.PLAY_VIDEO
            r3.sendMetricEvent(r4)
            goto L9b
        L65:
            com.amazon.mp3.fragment.contextmenu.VideoContextMenuProvider$VideoContextMenuProviderListener r5 = r3.providerListener
            com.amazon.mp3.util.ContentAccessUtil$ContentAccessOperation r6 = com.amazon.mp3.util.ContentAccessUtil.ContentAccessOperation.STREAM
            boolean r4 = r5.canContinueAction(r4, r7, r6)
            if (r4 == 0) goto L72
            r3.addToPlayQueue(r7, r0)
        L72:
            com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType r4 = com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType.ADD_TO_PLAY_Q_OVERFLOW
            r3.sendMetricEvent(r4)
            goto L9b
        L78:
            com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType r4 = com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType.VIEW_ARTIST_OVERFLOW
            r3.sendMetricEvent(r4)
            androidx.fragment.app.FragmentActivity r4 = r3.mActivity
            boolean r5 = r4 instanceof com.amazon.mp3.activity.MusicHomeActivity
            if (r5 == 0) goto L9b
            if (r4 == 0) goto L93
            com.amazon.mp3.activity.MusicHomeActivity r4 = (com.amazon.mp3.activity.MusicHomeActivity) r4
            java.lang.String r5 = r7.getArtist()
            java.lang.String r6 = r7.getArtistAsin()
            com.amazon.mp3.fragment.navigation.BrushFragmentNavigation.navigateToArtistDetails(r4, r5, r6)
            goto L9b
        L93:
            java.lang.NullPointerException r4 = new java.lang.NullPointerException
            java.lang.String r5 = "null cannot be cast to non-null type com.amazon.mp3.activity.MusicHomeActivity"
            r4.<init>(r5)
            throw r4
        L9b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.fragment.contextmenu.VideoContextMenuProvider.onContextMenuItemSelected(androidx.fragment.app.FragmentActivity, android.view.MenuItem, java.util.List, java.lang.String):boolean");
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    public <D extends SearchItem & CatalogContent> void addToPlayQueue(D item, boolean playNext) {
        if (item != null) {
            PrimeTrack primeTrack = (PrimeTrack) item;
            PrimeTrackTask primeTrackTaskCreatePlaybackTask = PrimeTrackTask.createPlaybackTask(this.mActivity, primeTrack.getAlbumAsin(), primeTrack.getAsin(), (PlaybackMetricInformation) null, (PlaybackPageType) null, playNext ? PrimeCollectionTask.Task.PLAY_NEXT : PrimeCollectionTask.Task.ADD_TO_PLAYQUEUE, new QuerySongByAsin(this.mActivity), (PrimeCollectionTask.OnUriReadyCallback) null);
            primeTrackTaskCreatePlaybackTask.setIsVideo(PrimePlaylistTracksTable.AssetType.VIDEO == primeTrack.getAssetType());
            primeTrackTaskCreatePlaybackTask.execute();
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.prime.browse.metadata.PrimeTrack");
    }
}
