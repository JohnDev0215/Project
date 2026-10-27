package com.amazon.mp3.curate.provider;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.ImageCacheUri;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.fragment.contextmenu.ContextMenuDownloadComponent;
import com.amazon.mp3.fragment.navigation.BrushFragmentNavigation;
import com.amazon.mp3.library.activity.AddToPlaylistPopupActivity;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.fragment.AddAndDownloadTrackTask;
import com.amazon.mp3.library.fragment.AlbumDetailFragment;
import com.amazon.mp3.library.fragment.BasePrimeFragment;
import com.amazon.mp3.library.fragment.LibraryBaseFragment;
import com.amazon.mp3.library.fragment.OnItemFinishedAddingListener;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingQueueUtil;
import com.amazon.mp3.library.util.TrackUtil;
import com.amazon.mp3.prime.browse.SimilarRecommendationsFragment;
import com.amazon.mp3.prime.browse.pager.PaginatedDataType;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.store.activity.HTMLStoreActivityFactory;
import com.amazon.mp3.styles.BauhausTextStyler;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.curate.data.PlaylistIntentReceiverService;
import com.amazon.music.curate.model.Playlist;
import com.amazon.music.curate.model.Track;
import com.amazon.music.curate.model.station;
import com.amazon.music.curate.provider.OverflowMenuProvider;
import com.amazon.music.downloads.DownloadState;
import com.amazon.music.downloads.Group;
import com.amazon.music.downloads.Item;
import com.amazon.music.downloads.notification.DownloadListener;
import com.amazon.music.downloads.notification.NotificationInfo;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.StoreLinkType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.share.PlaylistShareProvider;
import com.amazon.music.share.StationShareProvider;
import com.amazon.music.share.TrackShareProvider;
import com.amazon.music.station.StationException;
import com.amazon.music.station.StationNotFoundException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: CurateOverflowMenuProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 *2\u00020\u0001:\u0002*+B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u001a\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0018\u0010\u001a\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0018\u0010\u001d\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0002J\u0018\u0010\u001d\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010 \u001a\u00020!H\u0002J \u0010\"\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J \u0010\"\u001a\u00020\r2\u0006\u0010#\u001a\u00020$2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J8\u0010%\u001a\u00020\r2\u0006\u0010&\u001a\u00020\u00192\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010'\u001a\u00020\n2\u0006\u0010(\u001a\u00020)2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcom/amazon/mp3/curate/provider/CurateOverflowMenuProvider;", "Lcom/amazon/music/curate/provider/OverflowMenuProvider;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "addToMenu", "Landroid/view/MenuItem;", "menu", "Landroid/view/ContextMenu;", "id", "", "stringRes", "changeScreenFragment", "", "manager", "Landroidx/fragment/app/FragmentManager;", "fragment", "Landroidx/fragment/app/Fragment;", "getAlbumDetailFragment", "Lcom/amazon/mp3/library/fragment/LibraryBaseFragment;", "track", "Lcom/amazon/music/curate/model/Track;", "getContentUriFromAsin", "Landroid/net/Uri;", "asin", "", "goToSimilarRecommendationsFragment", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "sendMetricEvent", Environment.PLAYLIST_PATH, "Lcom/amazon/music/curate/model/Playlist;", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "showOverflowMenu", "station", "Lcom/amazon/music/curate/model/Station;", "showTrackOverflowMenu", "userPlaylistId", "index", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "Companion", "CurateDownloadListener", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CurateOverflowMenuProvider implements OverflowMenuProvider {
    private static final Logger LOG;
    private final Context context;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showTrackOverflowMenu$lambda-8$lambda-7, reason: not valid java name */
    public static final void m763showTrackOverflowMenu$lambda8$lambda7(String str, boolean z) {
    }

    public CurateOverflowMenuProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    static {
        Logger logger = LoggerFactory.getLogger(CurateOverflowMenuProvider.class.getSimpleName());
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(CurateOverflow…r::class.java.simpleName)");
        LOG = logger;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x003b  */
    @Override // com.amazon.music.curate.provider.OverflowMenuProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void showOverflowMenu(final com.amazon.music.curate.model.Playlist r9, android.view.ContextMenu r10, final androidx.fragment.app.FragmentActivity r11) {
        /*
            Method dump skipped, instruction units count: 208
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.curate.provider.CurateOverflowMenuProvider.showOverflowMenu(com.amazon.music.curate.model.Playlist, android.view.ContextMenu, androidx.fragment.app.FragmentActivity):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showOverflowMenu$lambda-0, reason: not valid java name */
    public static final boolean m748showOverflowMenu$lambda0(CurateOverflowMenuProvider this$0, Playlist playlist, FragmentActivity activity, boolean z, MenuItem menuItem) {
        Uri contentUri;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(playlist, "$playlist");
        Intrinsics.checkNotNullParameter(activity, "$activity");
        this$0.sendMetricEvent(playlist, ActionType.ADD_TO_PLAY_Q_OVERFLOW);
        if (UserSubscriptionUtil.isNightwingOnly()) {
            UpsellUtil.showBlockingUpsell(activity, playlist.getId(), UpsellReason.ADD_TO_PLAY_QUEUE, UpsellSourceEntity.PLAYLIST);
        }
        if (z) {
            contentUri = MediaProvider.UdoPlaylistTracks.getContentUriFromLuid(this$0.context, "cirrus", playlist.getId());
        } else {
            contentUri = MediaProvider.PrimePlaylists.Tracks.getContentUri("cirrus", playlist.getId());
        }
        NowPlayingQueueUtil.getInstance().addToPlayQueue(contentUri, false);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showOverflowMenu$lambda-1, reason: not valid java name */
    public static final boolean m749showOverflowMenu$lambda1(CurateOverflowMenuProvider this$0, Playlist playlist, FragmentActivity activity, boolean z, MenuItem menuItem) {
        Uri contentUri;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(playlist, "$playlist");
        Intrinsics.checkNotNullParameter(activity, "$activity");
        this$0.sendMetricEvent(playlist, ActionType.PLAY_NEXT_OVERFLOW);
        if (UserSubscriptionUtil.isNightwingOnly()) {
            UpsellUtil.showBlockingUpsell(activity, playlist.getId(), UpsellReason.ADD_TO_PLAY_QUEUE, UpsellSourceEntity.PLAYLIST);
        }
        if (z) {
            contentUri = MediaProvider.UdoPlaylistTracks.getContentUriFromLuid(this$0.context, "cirrus", playlist.getId());
        } else {
            contentUri = MediaProvider.PrimePlaylists.Tracks.getContentUri("cirrus", playlist.getId());
        }
        NowPlayingQueueUtil.getInstance().addToPlayQueue(contentUri, true);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showOverflowMenu$lambda-2, reason: not valid java name */
    public static final boolean m750showOverflowMenu$lambda2(CurateOverflowMenuProvider this$0, Playlist playlist, FragmentActivity activity, boolean z, boolean z2, MenuItem menuItem) {
        Uri uriGenerateUri;
        Uri contentUri;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(playlist, "$playlist");
        Intrinsics.checkNotNullParameter(activity, "$activity");
        this$0.sendMetricEvent(playlist, ActionType.DOWNLOAD_OVERFLOW);
        boolean zIsNightwingOnly = UserSubscriptionUtil.isNightwingOnly();
        if (zIsNightwingOnly) {
            UpsellUtil.showBlockingUpsell(activity, playlist.getId(), UpsellReason.DOWNLOAD_CONTENT, UpsellSourceEntity.PLAYLIST);
        }
        if (zIsNightwingOnly && z) {
            return true;
        }
        if (z2) {
            uriGenerateUri = ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.PLAYLIST_UDO_THUMBNAIL.intValue(), playlist.getId());
            Intrinsics.checkNotNullExpressionValue(uriGenerateUri, "generateUri(ImageLoaderF….intValue(), playlist.id)");
            contentUri = MediaProvider.UdoPlaylistTracks.getContentUriFromLuid(this$0.context, "cirrus", playlist.getId());
            Intrinsics.checkNotNullExpressionValue(contentUri, "getContentUriFromLuid(co…e.ID_CIRRUS, playlist.id)");
        } else {
            uriGenerateUri = ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.PRIME_PLAYLIST_THUMBNAIL.intValue(), playlist.getId());
            Intrinsics.checkNotNullExpressionValue(uriGenerateUri, "generateUri(ImageLoaderF….intValue(), playlist.id)");
            contentUri = MediaProvider.PrimePlaylists.Tracks.getContentUri("cirrus", playlist.getId());
            Intrinsics.checkNotNullExpressionValue(contentUri, "getContentUri(CirrusMedi…e.ID_CIRRUS, playlist.id)");
        }
        ContextMenuDownloadComponent.startDownload(activity, contentUri.toString(), contentUri, new NotificationInfo(playlist.getTitle(), null, uriGenerateUri.toString()));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showOverflowMenu$lambda-3, reason: not valid java name */
    public static final boolean m751showOverflowMenu$lambda3(CurateOverflowMenuProvider this$0, Playlist playlist, FragmentActivity activity, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(playlist, "$playlist");
        Intrinsics.checkNotNullParameter(activity, "$activity");
        this$0.sendMetricEvent(playlist, ActionType.SHARE);
        new PlaylistShareProvider(activity, EndpointsProvider.get().getMusicDomain(), playlist.getId(), playlist.getTitle(), playlist.getArtworkUrl()).startShare();
        return true;
    }

    @Override // com.amazon.music.curate.provider.OverflowMenuProvider
    public void showOverflowMenu(final station station, ContextMenu menu, final FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(station, "station");
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (AmazonApplication.getCapabilities().isSharingSupported()) {
            menu.setHeaderTitle(new BauhausTextStyler().styleText(station.getTitle()));
            addToMenu(menu, R.id.MenuStationShare, R.string.dmusic_context_share_station).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: com.amazon.mp3.curate.provider.CurateOverflowMenuProvider$$ExternalSyntheticLambda0
                @Override // android.view.MenuItem.OnMenuItemClickListener
                public final boolean onMenuItemClick(MenuItem menuItem) {
                    return CurateOverflowMenuProvider.m752showOverflowMenu$lambda4(activity, station, menuItem);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showOverflowMenu$lambda-4, reason: not valid java name */
    public static final boolean m752showOverflowMenu$lambda4(FragmentActivity activity, station station, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(activity, "$activity");
        Intrinsics.checkNotNullParameter(station, "$station");
        try {
            new StationShareProvider(activity, EndpointsProvider.get().getMusicDomain(), station.getStationKey(), station.getTitle(), station.getImageUrl()).startShare();
            MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.SHARE).withInteractionType(InteractionType.TAP).withEntityId(station.getStationKey()).withEntityIdType(EntityIdType.STATION_KEY).withEventTime(System.currentTimeMillis()).withPageType(PageType.MY_MUSIC_CLOUD_FRONT_PAGE).build());
            return true;
        } catch (StationException e) {
            LOG.error("Failed to get station item to share", (Throwable) e);
            return true;
        } catch (StationNotFoundException e2) {
            LOG.error("Failed to get station item to share", (Throwable) e2);
            return true;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12, types: [T, com.amazon.mp3.library.item.AbstractItem, com.amazon.mp3.library.item.MusicTrack] */
    @Override // com.amazon.music.curate.provider.OverflowMenuProvider
    public void showTrackOverflowMenu(final String userPlaylistId, final Track track, final int index, final PageType pageType, ContextMenu menu, final FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(userPlaylistId, "userPlaylistId");
        Intrinsics.checkNotNullParameter(track, "track");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(activity, "activity");
        menu.setHeaderTitle(new BauhausTextStyler().styleText(track.getTitle()));
        addToMenu(menu, R.id.MenuTrackAddToPlayQueue, R.string.dmusic_context_add_to_play_queue).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: com.amazon.mp3.curate.provider.CurateOverflowMenuProvider$$ExternalSyntheticLambda1
            @Override // android.view.MenuItem.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return CurateOverflowMenuProvider.m760showTrackOverflowMenu$lambda5(this.f$0, track, menuItem);
            }
        });
        addToMenu(menu, R.id.MenuTrackPlayNext, R.string.dmusic_context_play_next).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: com.amazon.mp3.curate.provider.CurateOverflowMenuProvider$$ExternalSyntheticLambda3
            @Override // android.view.MenuItem.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return CurateOverflowMenuProvider.m761showTrackOverflowMenu$lambda6(this.f$0, track, menuItem);
            }
        });
        CirrusSourceLibraryItemFactory cirrusSourceLibraryItemFactory = new CirrusSourceLibraryItemFactory(this.context);
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ?? trackForAsin = cirrusSourceLibraryItemFactory.getTrackForAsin(track.getAsin());
        objectRef.element = trackForAsin;
        if (trackForAsin != 0) {
            if (5 == trackForAsin.getDownloadState() || 7 == ((MusicTrack) objectRef.element).getDownloadState()) {
                MusicDownloader.getInstance(this.context).registerDownloadListener(new CurateDownloadListener(this.context, userPlaylistId));
                addToMenu(menu, R.id.MenuTrackContextDownload, R.string.dmusic_context_download).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: com.amazon.mp3.curate.provider.CurateOverflowMenuProvider$$ExternalSyntheticLambda4
                    @Override // android.view.MenuItem.OnMenuItemClickListener
                    public final boolean onMenuItemClick(MenuItem menuItem) {
                        return CurateOverflowMenuProvider.m762showTrackOverflowMenu$lambda8(this.f$0, track, objectRef, activity, menuItem);
                    }
                });
            }
        }
        addToMenu(menu, R.id.MenuTrackContextAddToPlaylist, R.string.dmusic_context_add_to_playlist).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: com.amazon.mp3.curate.provider.CurateOverflowMenuProvider$$ExternalSyntheticLambda5
            @Override // android.view.MenuItem.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return CurateOverflowMenuProvider.m764showTrackOverflowMenu$lambda9(this.f$0, track, activity, pageType, menuItem);
            }
        });
        addToMenu(menu, R.id.MenuTrackShowAlbumDetail, R.string.dmusic_context_album_detail).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: com.amazon.mp3.curate.provider.CurateOverflowMenuProvider$$ExternalSyntheticLambda6
            @Override // android.view.MenuItem.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return CurateOverflowMenuProvider.m753showTrackOverflowMenu$lambda10(this.f$0, track, activity, menuItem);
            }
        });
        if (AmazonApplication.getCapabilities().isStoreSupported() && !ChildUserUtil.INSTANCE.isChildUser(this.context)) {
            menu.add(0, R.id.MenuTrackExploreArtist, 0, activity.getResources().getString(R.string.dmusic_context_shop_for, track.getArtistName())).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: com.amazon.mp3.curate.provider.CurateOverflowMenuProvider$$ExternalSyntheticLambda7
                @Override // android.view.MenuItem.OnMenuItemClickListener
                public final boolean onMenuItemClick(MenuItem menuItem) {
                    return CurateOverflowMenuProvider.m754showTrackOverflowMenu$lambda11(this.f$0, track, activity, menuItem);
                }
            });
        }
        addToMenu(menu, R.id.MenuTrackContextDeleteFromPlaylist, R.string.dmusic_track_context_delete_from_playlist).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: com.amazon.mp3.curate.provider.CurateOverflowMenuProvider$$ExternalSyntheticLambda8
            @Override // android.view.MenuItem.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return CurateOverflowMenuProvider.m755showTrackOverflowMenu$lambda12(this.f$0, userPlaylistId, track, index, menuItem);
            }
        });
        if (track.isInLibrary()) {
            addToMenu(menu, R.id.MenuTrackContextDeleteFromCirrus, R.string.dmusic_context_remove_from_library).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: com.amazon.mp3.curate.provider.CurateOverflowMenuProvider$$ExternalSyntheticLambda9
                @Override // android.view.MenuItem.OnMenuItemClickListener
                public final boolean onMenuItemClick(MenuItem menuItem) {
                    return CurateOverflowMenuProvider.m756showTrackOverflowMenu$lambda13(this.f$0, track, menuItem);
                }
            });
        } else if (!AmazonApplication.getCapabilities().isLikesEverywhereEnabled()) {
            addToMenu(menu, R.id.MenuTrackContextAddToCirrus, R.string.dmusic_context_add_to_library);
        }
        addToMenu(menu, R.id.MenuTrackContextGoToArtist, R.string.dmusic_context_artist_detail).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: com.amazon.mp3.curate.provider.CurateOverflowMenuProvider$$ExternalSyntheticLambda10
            @Override // android.view.MenuItem.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return CurateOverflowMenuProvider.m757showTrackOverflowMenu$lambda14(this.f$0, track, activity, menuItem);
            }
        });
        addToMenu(menu, R.id.MenuTrackSimilarRecommend, R.string.dmusic_context_similar_recommend).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: com.amazon.mp3.curate.provider.CurateOverflowMenuProvider$$ExternalSyntheticLambda11
            @Override // android.view.MenuItem.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return CurateOverflowMenuProvider.m758showTrackOverflowMenu$lambda15(this.f$0, track, activity, menuItem);
            }
        });
        addToMenu(menu, R.id.MenuTrackContextShare, R.string.dmusic_context_share_track).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: com.amazon.mp3.curate.provider.CurateOverflowMenuProvider$$ExternalSyntheticLambda2
            @Override // android.view.MenuItem.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return CurateOverflowMenuProvider.m759showTrackOverflowMenu$lambda16(activity, track, menuItem);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showTrackOverflowMenu$lambda-5, reason: not valid java name */
    public static final boolean m760showTrackOverflowMenu$lambda5(CurateOverflowMenuProvider this$0, Track track, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(track, "$track");
        this$0.sendMetricEvent(track, ActionType.ADD_TO_PLAY_Q_OVERFLOW);
        NowPlayingQueueUtil.getInstance().addToPlayQueue(this$0.getContentUriFromAsin(this$0.context, track.getAsin()), false);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showTrackOverflowMenu$lambda-6, reason: not valid java name */
    public static final boolean m761showTrackOverflowMenu$lambda6(CurateOverflowMenuProvider this$0, Track track, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(track, "$track");
        this$0.sendMetricEvent(track, ActionType.PLAY_NEXT_OVERFLOW);
        NowPlayingQueueUtil.getInstance().addToPlayQueue(this$0.getContentUriFromAsin(this$0.context, track.getAsin()), true);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: showTrackOverflowMenu$lambda-8, reason: not valid java name */
    public static final boolean m762showTrackOverflowMenu$lambda8(CurateOverflowMenuProvider this$0, Track track, Ref.ObjectRef libraryTrack, FragmentActivity activity, MenuItem menuItem) {
        String trackLuidByLocalFileLocation;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(track, "$track");
        Intrinsics.checkNotNullParameter(libraryTrack, "$libraryTrack");
        Intrinsics.checkNotNullParameter(activity, "$activity");
        this$0.sendMetricEvent(track, ActionType.DOWNLOAD_OVERFLOW);
        try {
            if (!((MusicTrack) libraryTrack.element).isInLibrary() && ((MusicTrack) libraryTrack.element).isAnyCatalog()) {
                new AddAndDownloadTrackTask(this$0.context, track.getAsin(), true, true, new OnItemFinishedAddingListener() { // from class: com.amazon.mp3.curate.provider.CurateOverflowMenuProvider$$ExternalSyntheticLambda16
                    @Override // com.amazon.mp3.library.fragment.OnItemFinishedAddingListener
                    public final void onItemAdded(String str, boolean z) {
                        CurateOverflowMenuProvider.m763showTrackOverflowMenu$lambda8$lambda7(str, z);
                    }
                }).execute(new Void[0]);
                return true;
            }
            String title = track.getTitle();
            String artistName = track.getArtistName();
            String string = ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.ALBUM.intValue(), track.getAlbumAsin()).toString();
            Intrinsics.checkNotNullExpressionValue(string, "generateUri(ImageLoaderF…ack.albumAsin).toString()");
            NotificationInfo notificationInfo = new NotificationInfo(title, artistName, string);
            Uri contentUri = ((MusicTrack) libraryTrack.element).getContentUri();
            if (!((MusicTrack) libraryTrack.element).isRemote() && (trackLuidByLocalFileLocation = TrackUtil.getTrackLuidByLocalFileLocation(CirrusDatabase.getWritableDatabase(activity), ((MusicTrack) libraryTrack.element).getLocalUri(), 0)) != null) {
                contentUri = MediaProvider.Tracks.getContentUri("cirrus", trackLuidByLocalFileLocation);
            }
            ContextMenuDownloadComponent.startDownload(activity, track.getAsin(), contentUri, notificationInfo);
            return true;
        } catch (IllegalStateException e) {
            LOG.error("Unable to download a track", (Throwable) e);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showTrackOverflowMenu$lambda-9, reason: not valid java name */
    public static final boolean m764showTrackOverflowMenu$lambda9(CurateOverflowMenuProvider this$0, Track track, FragmentActivity activity, PageType pageType, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(track, "$track");
        Intrinsics.checkNotNullParameter(activity, "$activity");
        Intrinsics.checkNotNullParameter(pageType, "$pageType");
        this$0.sendMetricEvent(track, ActionType.ADD_TO_PLAYLIST_OVERFLOW);
        Intent startIntent = AddToPlaylistPopupActivity.getStartIntent(activity, this$0.getContentUriFromAsin(activity, track.getAsin()), pageType);
        startIntent.putExtra("com.amazon.mp3.library.EXTRA_SAVE_SOURCE_ON_RESUME_ID", false);
        activity.startActivity(startIntent);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showTrackOverflowMenu$lambda-10, reason: not valid java name */
    public static final boolean m753showTrackOverflowMenu$lambda10(CurateOverflowMenuProvider this$0, Track track, FragmentActivity activity, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(track, "$track");
        Intrinsics.checkNotNullParameter(activity, "$activity");
        this$0.sendMetricEvent(track, ActionType.VIEW_ALBUM_OVERFLOW);
        LibraryBaseFragment albumDetailFragment = this$0.getAlbumDetailFragment(activity, track);
        FragmentManager supportFragmentManager = activity.getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "activity.getSupportFragmentManager()");
        this$0.changeScreenFragment(supportFragmentManager, albumDetailFragment);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showTrackOverflowMenu$lambda-11, reason: not valid java name */
    public static final boolean m754showTrackOverflowMenu$lambda11(CurateOverflowMenuProvider this$0, Track track, FragmentActivity activity, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(track, "$track");
        Intrinsics.checkNotNullParameter(activity, "$activity");
        this$0.sendMetricEvent(track, ActionType.SHOP_FOR_ARTIST_OVERFLOW);
        activity.startActivity(HTMLStoreActivityFactory.intentForSearch(activity, HTMLStoreActivityFactory.bundleForSearchString(track.getArtistName(), 1, "field-author")));
        MetricsLogger.clickedOnStoreLink(StoreLinkType.ARTIST);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showTrackOverflowMenu$lambda-12, reason: not valid java name */
    public static final boolean m755showTrackOverflowMenu$lambda12(CurateOverflowMenuProvider this$0, String userPlaylistId, Track track, int i, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(userPlaylistId, "$userPlaylistId");
        Intrinsics.checkNotNullParameter(track, "$track");
        PlaylistIntentReceiverService.INSTANCE.processTrackRemoval(this$0.context, userPlaylistId, track.getAsin(), i);
        this$0.sendMetricEvent(track, ActionType.REMOVE_FROM_PLAYLIST_OVERFLOW);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showTrackOverflowMenu$lambda-13, reason: not valid java name */
    public static final boolean m756showTrackOverflowMenu$lambda13(CurateOverflowMenuProvider this$0, Track track, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(track, "$track");
        this$0.sendMetricEvent(track, ActionType.REMOVE_FROM_CLOUD_OVERFLOW);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showTrackOverflowMenu$lambda-14, reason: not valid java name */
    public static final boolean m757showTrackOverflowMenu$lambda14(CurateOverflowMenuProvider this$0, Track track, FragmentActivity activity, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(track, "$track");
        Intrinsics.checkNotNullParameter(activity, "$activity");
        this$0.sendMetricEvent(track, ActionType.VIEW_ARTIST_OVERFLOW);
        BrushFragmentNavigation.navigateToArtistDetails((MusicHomeActivity) activity, track.getArtistName(), track.getArtistAsin());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showTrackOverflowMenu$lambda-15, reason: not valid java name */
    public static final boolean m758showTrackOverflowMenu$lambda15(CurateOverflowMenuProvider this$0, Track track, FragmentActivity activity, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(track, "$track");
        Intrinsics.checkNotNullParameter(activity, "$activity");
        this$0.sendMetricEvent(track, ActionType.CUSTOMERS_ALSO_LISTENED_TO_OVERFLOW);
        this$0.goToSimilarRecommendationsFragment(track, activity);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showTrackOverflowMenu$lambda-16, reason: not valid java name */
    public static final boolean m759showTrackOverflowMenu$lambda16(FragmentActivity activity, Track track, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(activity, "$activity");
        Intrinsics.checkNotNullParameter(track, "$track");
        new TrackShareProvider(activity, EndpointsProvider.get().getMusicDomain(), track.getAlbumAsin(), track.getAsin(), track.getTitle(), track.getArtistName(), track.getAlbumImageUrl()).startShare();
        return true;
    }

    private final MenuItem addToMenu(ContextMenu menu, int id, int stringRes) {
        MenuItem menuItemAdd = menu.add(0, id, 0, stringRes);
        Intrinsics.checkNotNullExpressionValue(menuItemAdd, "menu.add(Menu.NONE, id, Menu.NONE, stringRes)");
        return menuItemAdd;
    }

    private final void sendMetricEvent(Track track, ActionType actionType) {
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(actionType).withInteractionType(InteractionType.TAP).withEntityId(track.getAsin()).withEntityIdType(EntityIdType.ASIN).withEventTime(System.currentTimeMillis()).build());
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void sendMetricEvent(com.amazon.music.curate.model.Playlist r3, com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType r4) {
        /*
            r2 = this;
            java.lang.Boolean r0 = r3.getIsPersonalized()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L1c
            java.lang.Boolean r0 = r3.getIsFollowed()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            boolean r0 = r0.booleanValue()
            if (r0 != 0) goto L1c
            r0 = 1
            goto L1d
        L1c:
            r0 = 0
        L1d:
            com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent$Builder r4 = com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent.builder(r4)
            com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType r1 = com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType.TAP
            com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent$Builder r4 = r4.withInteractionType(r1)
            java.lang.String r3 = r3.getId()
            com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent$Builder r3 = r4.withEntityId(r3)
            if (r0 == 0) goto L34
            com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType r4 = com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType.PLAYLIST_ID
            goto L36
        L34:
            com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType r4 = com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType.ASIN
        L36:
            com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent$Builder r3 = r3.withEntityIdType(r4)
            long r0 = java.lang.System.currentTimeMillis()
            com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent$Builder r3 = r3.withEventTime(r0)
            com.amazon.music.metrics.mts.event.types.uiinteraction.PageType r4 = com.amazon.music.metrics.mts.event.types.uiinteraction.PageType.MY_MUSIC_CLOUD_FRONT_PAGE
            com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent$Builder r3 = r3.withPageType(r4)
            com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent r3 = r3.build()
            com.amazon.music.metrics.MetricsManager r4 = com.amazon.music.metrics.MetricsHolder.getManager()
            r4.handleEvent(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.curate.provider.CurateOverflowMenuProvider.sendMetricEvent(com.amazon.music.curate.model.Playlist, com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType):void");
    }

    private final Uri getContentUriFromAsin(Context context, String asin) {
        Cursor cursorQuery = CirrusDatabase.getReadOnlyDatabase(context).query("Track", new String[]{"luid"}, "asin=?", new String[]{asin}, null, null, null, null);
        try {
            if (cursorQuery.moveToFirst()) {
                Uri contentUri = MediaProvider.Tracks.getContentUri("cirrus", cursorQuery.getString(cursorQuery.getColumnIndex("luid")));
                CloseableKt.closeFinally(cursorQuery, null);
                return contentUri;
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursorQuery, null);
            return null;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursorQuery, th);
                throw th2;
            }
        }
    }

    private final LibraryBaseFragment getAlbumDetailFragment(Context context, Track track) {
        Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(context, MediaProvider.Albums.getContentUri("cirrus", IdGenerator.generateAlbumId(track.getAlbumAsin(), track.getAlbumName())));
        intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_SHOW_HEADER", true);
        intentForContentUri.putExtra("com.amazon.mp3.library.activity.EXTRA_ALBUM_ASIN", track.getAlbumAsin());
        AlbumDetailFragment albumDetailFragment = new AlbumDetailFragment();
        albumDetailFragment.setArguments(intentForContentUri.getExtras());
        return albumDetailFragment;
    }

    private final void goToSimilarRecommendationsFragment(Track track, FragmentActivity activity) {
        BasePrimeFragment fragment = new SimilarRecommendationsFragment.Builder(new Bundle()).setDataType(PaginatedDataType.TRACK).setAsin(track.getAsin()).build();
        FragmentManager supportFragmentManager = activity.getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "activity.getSupportFragmentManager()");
        Intrinsics.checkNotNullExpressionValue(fragment, "fragment");
        changeScreenFragment(supportFragmentManager, fragment);
    }

    private final void changeScreenFragment(FragmentManager manager, Fragment fragment) {
        manager.beginTransaction().setCustomAnimations(R.anim.zoom_in, R.anim.fragment_fade_out).replace(R.id.music_screen_container, fragment).addToBackStack(null).commitAllowingStateLoss();
    }

    /* JADX INFO: compiled from: CurateOverflowMenuProvider.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J$\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J$\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\f\u001a\u00020\rH\u0016J&\u0010\u0010\u001a\u00020\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\t\u001a\u0004\u0018\u00010\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J&\u0010\u0010\u001a\u00020\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\t\u001a\u0004\u0018\u00010\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/curate/provider/CurateOverflowMenuProvider$CurateDownloadListener;", "Lcom/amazon/music/downloads/notification/DownloadListener;", "context", "Landroid/content/Context;", "playlistId", "", "(Landroid/content/Context;Ljava/lang/String;)V", "onProgressUpdate", "", "id", "group", "Lcom/amazon/music/downloads/Group;", "percentageDownloaded", "", "item", "Lcom/amazon/music/downloads/Item;", "onStatusUpdate", "state", "Lcom/amazon/music/downloads/DownloadState;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class CurateDownloadListener extends DownloadListener {
        private final Context context;
        private final String playlistId;

        @Override // com.amazon.music.downloads.notification.DownloadListener
        public void onProgressUpdate(String id, Group group, float percentageDownloaded) {
        }

        @Override // com.amazon.music.downloads.notification.DownloadListener
        public void onProgressUpdate(String id, Item item, float percentageDownloaded) {
        }

        @Override // com.amazon.music.downloads.notification.DownloadListener
        public void onStatusUpdate(DownloadState state, String id, Group group) {
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CurateDownloadListener(Context context, String playlistId) {
            super(true);
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(playlistId, "playlistId");
            this.context = context;
            this.playlistId = playlistId;
        }

        @Override // com.amazon.music.downloads.notification.DownloadListener
        public void onStatusUpdate(DownloadState state, String id, Item item) {
            if (DownloadState.DOWNLOADED.equals(state)) {
                PlaylistIntentReceiverService.INSTANCE.processUpdatePlaylist(this.context, this.playlistId);
                MusicDownloader.getInstance(this.context).unregisterDownloadListener(this);
            }
        }
    }
}
