package com.amazon.mp3.detailpages.playlist.actions;

import PlaybackInterface.v1_0.PlaybackEvent;
import android.net.Uri;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.detailpages.actions.BaseDownloadAction;
import com.amazon.mp3.detailpages.playlist.actions.PlaylistFollowAction;
import com.amazon.mp3.download.ImageCacheUri;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.dialog.NetworkErrorDialog;
import com.amazon.mp3.library.fragment.DownloadSettingDialogFragment;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellInformation;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PrimeContentUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.downloads.notification.NotificationInfo;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.CapabilityActionProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.models.DownloadStateModel;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: PlaylistDownloadAction.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 /2\u00020\u0001:\u0001/B'\u0012\u0006\u0010(\u001a\u00020'\u0012\u0006\u0010*\u001a\u00020)\u0012\u0006\u0010,\u001a\u00020+\u0012\u0006\u0010\u001b\u001a\u00020\f¢\u0006\u0004\b-\u0010.J\u0012\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002H\u0002J\"\u0010\u000e\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH\u0002J\u0018\u0010\u000f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0002J4\u0010\u000f\u001a\u00020\u00042\b\u0010\u0010\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0011\u001a\u00020\f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH\u0002J\"\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0002J \u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH\u0002J\u000e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00140\u0017H\u0002J,\u0010\u0016\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\n2\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0019R\u0014\u0010\u001b\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u001d8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0018\u0010!\u001a\u0004\u0018\u00010 8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b!\u0010\"R\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u00198\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010#R\u0016\u0010%\u001a\u0004\u0018\u00010$8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010&¨\u00060"}, d2 = {"Lcom/amazon/mp3/detailpages/playlist/actions/PlaylistDownloadAction;", "Lcom/amazon/mp3/detailpages/actions/BaseDownloadAction;", "", "message", "", "onFollowPlaylistFailed", "luid", "onFollowPlaylistFinished", "Lcom/amazon/music/views/library/metadata/PlaylistMetadata;", "metadata", "Lcom/amazon/music/views/library/models/DownloadStateModel$DownloadState;", "downloadState", "", "shouldBlockDownload", "handleDownloadBtnClickForCatalogPlaylist", "handleDownloadBtnClickForLibraryPlaylist", TetheredMessageKey.requestId, "downloadNonAddedPrimeTracks", "Lcom/amazon/music/downloads/notification/NotificationInfo;", "notificationInfo", "Lcom/amazon/mp3/playlist/CatalogPlaylist;", Environment.PLAYLIST_PATH, "handleDownloadBtnClick", "Lio/reactivex/Observable;", "getPlaylist", "Lcom/amazon/mp3/detailpages/playlist/actions/PlaylistFollowAction$Callback;", "followActionCallback", "isCatalog", "Z", "Lio/reactivex/disposables/Disposable;", "disposable", "Lio/reactivex/disposables/Disposable;", "Lcom/amazon/mp3/detailpages/playlist/actions/PlaylistFollowAction;", "pendingFollowingAction", "Lcom/amazon/mp3/detailpages/playlist/actions/PlaylistFollowAction;", "Lcom/amazon/mp3/detailpages/playlist/actions/PlaylistFollowAction$Callback;", "Lcom/amazon/music/platform/providers/CapabilityActionProvider;", "capabilityActionProvider", "Lcom/amazon/music/platform/providers/CapabilityActionProvider;", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroid/net/Uri;", "contentUri", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "pageType", "<init>", "(Landroidx/fragment/app/FragmentActivity;Landroid/net/Uri;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;Z)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class PlaylistDownloadAction extends BaseDownloadAction {
    private static final String TAG = Reflection.getOrCreateKotlinClass(PlaylistDownloadAction.class).getSimpleName();
    private final CapabilityActionProvider capabilityActionProvider;
    private Disposable disposable;
    private PlaylistFollowAction.Callback followActionCallback;
    private final boolean isCatalog;
    private PlaylistFollowAction pendingFollowingAction;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlaylistDownloadAction(FragmentActivity activity, Uri contentUri, PageType pageType, boolean z) {
        super(activity, pageType, contentUri);
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(contentUri, "contentUri");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        this.isCatalog = z;
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(CapabilityActionProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        this.capabilityActionProvider = (CapabilityActionProvider) (interfaceProvider instanceof CapabilityActionProvider ? interfaceProvider : null);
    }

    public final void handleDownloadBtnClick(PlaylistMetadata metadata, boolean shouldBlockDownload, DownloadStateModel.DownloadState downloadState, PlaylistFollowAction.Callback followActionCallback) {
        Intrinsics.checkNotNullParameter(downloadState, "downloadState");
        this.followActionCallback = followActionCallback;
        if (this.isCatalog) {
            handleDownloadBtnClickForCatalogPlaylist(metadata, downloadState, shouldBlockDownload);
        } else {
            handleDownloadBtnClickForLibraryPlaylist(shouldBlockDownload, downloadState);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onFollowPlaylistFailed(String message) {
        PlaylistFollowAction.Callback callback = this.followActionCallback;
        if (callback != null) {
            callback.onFollowPlaylistFailed(message);
        }
        this.pendingFollowingAction = null;
        this.followActionCallback = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onFollowPlaylistFinished(String luid) {
        PlaylistFollowAction.Callback callback = this.followActionCallback;
        if (callback != null) {
            callback.onFollowPlaylistFinished(luid);
        }
        this.pendingFollowingAction = null;
        this.followActionCallback = null;
    }

    private final void handleDownloadBtnClickForCatalogPlaylist(PlaylistMetadata metadata, DownloadStateModel.DownloadState downloadState, boolean shouldBlockDownload) {
        if (UserSubscriptionUtil.isNightwingOnly()) {
            UpsellInformation upsellInformation = UpsellUtil.getUpsellInformation(metadata == null ? null : metadata.getAsin(), UpsellReason.DOWNLOAD_CONTENT, UpsellSourceEntity.PLAYLIST);
            Intrinsics.checkNotNullExpressionValue(upsellInformation, "getUpsellInformation(\n  …ty.PLAYLIST\n            )");
            UpsellUtil.showBlockingUpsell(getActivity(), upsellInformation);
            return;
        }
        handleDownloadBtnClickForCatalogPlaylist(downloadState, shouldBlockDownload, metadata);
    }

    private final void handleDownloadBtnClickForLibraryPlaylist(boolean shouldBlockDownload, DownloadStateModel.DownloadState downloadState) {
        if (shouldBlockDownload) {
            UpsellUtil.showBlockingUpsell(getActivity(), String.valueOf(PlaylistUtil.getPlaylistId(getContentUri())), UpsellReason.DOWNLOAD_CONTENT, UpsellSourceEntity.PLAYLIST);
        } else {
            handleDownloadBtnClickForLibraryPlaylist(String.valueOf(ContentPlaybackUtils.INSTANCE.appendTracksIfNeeded(getContentUri())), false, new NotificationInfo(PlaylistUtil.getPlaylistName(getActivity(), getContentUri()), null, ImageCacheUri.generateUri((MediaProvider.SmartPlaylists.isSmartPlaylist(getContentUri()) ? ImageLoaderFactory.ItemType.PLAYLIST_SMART : ImageLoaderFactory.ItemType.PLAYLIST_UDO).intValue(), MediaProvider.Playlists.getPlaylistId(getContentUri())).toString()), downloadState, shouldBlockDownload);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleDownloadBtnClickForLibraryPlaylist(final String requestId, final boolean downloadNonAddedPrimeTracks, final NotificationInfo notificationInfo, final DownloadStateModel.DownloadState downloadState, final boolean shouldBlockDownload) {
        Uri uriAppendTracksIfNeeded = ContentPlaybackUtils.INSTANCE.appendTracksIfNeeded(getContentUri());
        if (!ConnectivityUtil.hasAnyInternetConnection(getActivity().getApplicationContext())) {
            NetworkErrorDialog.create(getActivity(), new NetworkErrorDialog.INetworkErrorDialogHandler() { // from class: com.amazon.mp3.detailpages.playlist.actions.PlaylistDownloadAction$handleDownloadBtnClickForLibraryPlaylist$handler$1
                @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
                public void onConnectionErrorDialogCancel() {
                }

                @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
                public void onDialogDismiss() {
                }

                @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
                public void onRetryConnection() {
                    this.this$0.handleDownloadBtnClickForLibraryPlaylist(requestId, downloadNonAddedPrimeTracks, notificationInfo, downloadState, shouldBlockDownload);
                }
            }).show();
        } else if (DownloadSettingDialogFragment.shouldShowDownloadSettingsDialogFragment(getActivity())) {
            DownloadSettingDialogFragment.showSettingsDialog(getActivity(), requestId, uriAppendTracksIfNeeded, notificationInfo, downloadNonAddedPrimeTracks);
        } else if (notificationInfo != null) {
            handleDownloadBtnClick(downloadState, notificationInfo, downloadNonAddedPrimeTracks, shouldBlockDownload, null, null);
        }
    }

    private final void handleDownloadBtnClickForCatalogPlaylist(final DownloadStateModel.DownloadState downloadState, final boolean shouldBlockDownload, final PlaylistMetadata metadata) {
        this.disposable = getPlaylist().subscribe(new Consumer() { // from class: com.amazon.mp3.detailpages.playlist.actions.PlaylistDownloadAction$$ExternalSyntheticLambda0
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                PlaylistDownloadAction.m870handleDownloadBtnClickForCatalogPlaylist$lambda1(this.f$0, metadata, downloadState, shouldBlockDownload, (CatalogPlaylist) obj);
            }
        }, new Consumer() { // from class: com.amazon.mp3.detailpages.playlist.actions.PlaylistDownloadAction$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                PlaylistDownloadAction.m872handleDownloadBtnClickForCatalogPlaylist$lambda2(this.f$0, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handleDownloadBtnClickForCatalogPlaylist$lambda-1, reason: not valid java name */
    public static final void m870handleDownloadBtnClickForCatalogPlaylist$lambda1(final PlaylistDownloadAction this$0, final PlaylistMetadata playlistMetadata, final DownloadStateModel.DownloadState downloadState, final boolean z, final CatalogPlaylist catalogPlaylist) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(downloadState, "$downloadState");
        PrimeContentUtil.checkOfflineAccess(catalogPlaylist, new PrimeContentUtil.ContentAvailabilityStateListener() { // from class: com.amazon.mp3.detailpages.playlist.actions.PlaylistDownloadAction$$ExternalSyntheticLambda3
            @Override // com.amazon.mp3.util.PrimeContentUtil.ContentAvailabilityStateListener
            public final void resultReceived(boolean z2, ContentUnavailableReason contentUnavailableReason) {
                PlaylistDownloadAction.m871handleDownloadBtnClickForCatalogPlaylist$lambda1$lambda0(this.f$0, catalogPlaylist, playlistMetadata, downloadState, z, z2, contentUnavailableReason);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handleDownloadBtnClickForCatalogPlaylist$lambda-1$lambda-0, reason: not valid java name */
    public static final void m871handleDownloadBtnClickForCatalogPlaylist$lambda1$lambda0(final PlaylistDownloadAction this$0, final CatalogPlaylist playlist, PlaylistMetadata playlistMetadata, final DownloadStateModel.DownloadState downloadState, final boolean z, boolean z2, ContentUnavailableReason contentUnavailableReason) {
        Outcome outcomeCanPerform;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(downloadState, "$downloadState");
        CapabilityActionProvider capabilityActionProvider = this$0.capabilityActionProvider;
        if (capabilityActionProvider == null) {
            outcomeCanPerform = null;
        } else {
            Intrinsics.checkNotNullExpressionValue(playlist, "playlist");
            outcomeCanPerform = capabilityActionProvider.canPerform(PlaybackEvent.UPSELL, playlist);
        }
        if (!Intrinsics.areEqual(outcomeCanPerform, new Outcome.Success(com.amazon.music.platform.playback.capabilities.UpsellReason.NotDownloadable))) {
            PlaylistFollowAction playlistFollowAction = new PlaylistFollowAction(this$0.getActivity(), playlistMetadata);
            if (playlistFollowAction.canFollowPlaylist()) {
                playlistFollowAction.setListener(new PlaylistFollowAction.Callback() { // from class: com.amazon.mp3.detailpages.playlist.actions.PlaylistDownloadAction$handleDownloadBtnClickForCatalogPlaylist$1$1$1
                    @Override // com.amazon.mp3.detailpages.playlist.actions.PlaylistFollowAction.Callback
                    public void onFollowPlaylistFailed(String message) {
                        this.this$0.onFollowPlaylistFailed(message);
                    }

                    @Override // com.amazon.mp3.detailpages.playlist.actions.PlaylistFollowAction.Callback
                    public void onFollowPlaylistFinished(String luid) {
                        this.this$0.onFollowPlaylistFinished(luid);
                        PlaylistDownloadAction playlistDownloadAction = this.this$0;
                        CatalogPlaylist playlist2 = playlist;
                        Intrinsics.checkNotNullExpressionValue(playlist2, "playlist");
                        playlistDownloadAction.handleDownloadBtnClick(playlist2, downloadState, z);
                    }
                });
                playlistFollowAction.followPlaylist(playlist.getAsin());
                this$0.pendingFollowingAction = playlistFollowAction;
            } else {
                Intrinsics.checkNotNullExpressionValue(playlist, "playlist");
                this$0.handleDownloadBtnClick(playlist, downloadState, z);
            }
        } else {
            UpsellUtil.showBlockingUpsell(this$0.getActivity(), playlistMetadata == null ? null : playlistMetadata.getAsin(), UpsellReason.DOWNLOAD_CONTENT, UpsellSourceEntity.PLAYLIST);
        }
        this$0.disposable = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handleDownloadBtnClickForCatalogPlaylist$lambda-2, reason: not valid java name */
    public static final void m872handleDownloadBtnClickForCatalogPlaylist$lambda2(PlaylistDownloadAction this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.error(TAG, Intrinsics.stringPlus("An error was happened when downloading a catalog playlist: ", th.getMessage()));
        this$0.disposable = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleDownloadBtnClick(CatalogPlaylist playlist, DownloadStateModel.DownloadState downloadState, boolean shouldBlockDownload) {
        Uri uriGenerateUri = ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.PRIME_PLAYLIST_THUMBNAIL.intValue(), playlist.getAsin());
        Intrinsics.checkNotNullExpressionValue(uriGenerateUri, "generateUri(\n           …ntValue(), playlist.asin)");
        handleDownloadBtnClick(downloadState, new NotificationInfo(playlist.getTitle(), null, uriGenerateUri.toString()), false, shouldBlockDownload, null, playlist.getAsin());
    }

    private final Observable<CatalogPlaylist> getPlaylist() {
        Observable<CatalogPlaylist> observableSubscribeOn = Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.mp3.detailpages.playlist.actions.PlaylistDownloadAction$$ExternalSyntheticLambda2
            @Override // io.reactivex.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                PlaylistDownloadAction.m869getPlaylist$lambda3(this.f$0, observableEmitter);
            }
        }).subscribeOn(Schedulers.io());
        Intrinsics.checkNotNullExpressionValue(observableSubscribeOn, "create<CatalogPlaylist> …scribeOn(Schedulers.io())");
        return observableSubscribeOn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getPlaylist$lambda-3, reason: not valid java name */
    public static final void m869getPlaylist$lambda3(PlaylistDownloadAction this$0, ObservableEmitter it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        it.onNext(AmazonApplication.getLibraryItemFactory().getCatalogPlaylist(this$0.getContentUri()));
        it.onComplete();
    }
}
