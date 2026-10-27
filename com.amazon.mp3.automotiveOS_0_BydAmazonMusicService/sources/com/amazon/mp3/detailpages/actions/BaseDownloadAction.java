package com.amazon.mp3.detailpages.actions;

import android.net.Uri;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.library.dialog.NetworkErrorDialog;
import com.amazon.mp3.library.fragment.DownloadSettingDialogFragment;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellInformation;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.music.downloads.notification.NotificationInfo;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.skyfire.platform.Log;
import com.amazon.music.views.library.models.DownloadStateModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseDownloadAction.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\b&\u0018\u0000 #2\u00020\u0001:\u0001#B\u001f\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010\u0018\u001a\u00020\u0017\u0012\u0006\u0010\u001d\u001a\u00020\u001c¢\u0006\u0004\b!\u0010\"J\u001a\u0010\u0007\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J<\u0010\u0011\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0004R\u001a\u0010\u0013\u001a\u00020\u00128\u0004X\u0084\u0004¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0018\u001a\u00020\u00178\u0004X\u0084\u0004¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001d\u001a\u00020\u001c8\u0004X\u0084\u0004¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 ¨\u0006$"}, d2 = {"Lcom/amazon/mp3/detailpages/actions/BaseDownloadAction;", "", "", "asin", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "actionType", "", "sendUiClickMetric", "Lcom/amazon/music/views/library/models/DownloadStateModel$DownloadState;", "downloadState", "Lcom/amazon/music/downloads/notification/NotificationInfo;", "notificationInfo", "", "downloadNonAddedPrimeTracks", "shouldBlockDownload", "Lcom/amazon/mp3/prime/upsell/nightwing/model/UpsellInformation;", "upsellInfo", "handleDownloadBtnClick", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "getActivity", "()Landroidx/fragment/app/FragmentActivity;", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "getPageType", "()Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "Landroid/net/Uri;", "contentUri", "Landroid/net/Uri;", "getContentUri", "()Landroid/net/Uri;", "<init>", "(Landroidx/fragment/app/FragmentActivity;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;Landroid/net/Uri;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public abstract class BaseDownloadAction {
    private static final String TAG = BaseDownloadAction.class.getSimpleName();
    private final FragmentActivity activity;
    private final Uri contentUri;
    private final PageType pageType;

    /* JADX INFO: compiled from: BaseDownloadAction.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DownloadStateModel.DownloadState.values().length];
            iArr[DownloadStateModel.DownloadState.AVAILABLE_FOR_DOWNLOAD.ordinal()] = 1;
            iArr[DownloadStateModel.DownloadState.REDOWNLOAD.ordinal()] = 2;
            iArr[DownloadStateModel.DownloadState.DOWNLOADING.ordinal()] = 3;
            iArr[DownloadStateModel.DownloadState.ERROR_DOWNLOADING.ordinal()] = 4;
            iArr[DownloadStateModel.DownloadState.ERROR_DOWNLOAD_RETURN.ordinal()] = 5;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public BaseDownloadAction(FragmentActivity activity, PageType pageType, Uri contentUri) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Intrinsics.checkNotNullParameter(contentUri, "contentUri");
        this.activity = activity;
        this.pageType = pageType;
        this.contentUri = contentUri;
    }

    protected final FragmentActivity getActivity() {
        return this.activity;
    }

    protected final Uri getContentUri() {
        return this.contentUri;
    }

    protected final void handleDownloadBtnClick(final DownloadStateModel.DownloadState downloadState, final NotificationInfo notificationInfo, final boolean downloadNonAddedPrimeTracks, final boolean shouldBlockDownload, final UpsellInformation upsellInfo, final String asin) {
        Intrinsics.checkNotNullParameter(downloadState, "downloadState");
        Intrinsics.checkNotNullParameter(notificationInfo, "notificationInfo");
        final Uri uriAppendTracksIfNeeded = ContentPlaybackUtils.INSTANCE.appendTracksIfNeeded(this.contentUri);
        if (uriAppendTracksIfNeeded == null) {
            Log log = Log.INSTANCE;
            String TAG2 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
            Log.error$default(log, TAG2, Intrinsics.stringPlus("Download task failed because a null tracks uri got with the group uri: ", this.contentUri), null, 4, null);
            return;
        }
        this.activity.runOnUiThread(new Runnable() { // from class: com.amazon.mp3.detailpages.actions.BaseDownloadAction$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                BaseDownloadAction.m797handleDownloadBtnClick$lambda0(this.f$0, downloadState, uriAppendTracksIfNeeded, notificationInfo, downloadNonAddedPrimeTracks, shouldBlockDownload, upsellInfo, asin);
            }
        });
        int i = WhenMappings.$EnumSwitchMapping$0[downloadState.ordinal()];
        if (i == 1 || i == 2) {
            if (shouldBlockDownload) {
                UpsellUtil.showBlockingUpsell(this.activity, upsellInfo);
                return;
            }
            MusicDownloader.getInstance(this.activity).download(uriAppendTracksIfNeeded.toString(), uriAppendTracksIfNeeded, notificationInfo, downloadNonAddedPrimeTracks, true);
            if (MediaProvider.UdoPlaylists.isUdoPlaylist(this.contentUri)) {
                MetricsLogger.userPlaylistDownloaded(MediaProvider.Playlists.getPlaylistLuid(uriAppendTracksIfNeeded));
            }
            sendUiClickMetric(asin, ActionType.DOWNLOAD_ALL);
            return;
        }
        if (i == 3) {
            MusicDownloader.getInstance(this.activity).cancel(uriAppendTracksIfNeeded.toString());
            sendUiClickMetric(asin, ActionType.DOWNLOAD_CANCEL_ALL);
        } else if (i == 4 || i == 5) {
            MusicDownloader.showRetryDialog(this.activity, uriAppendTracksIfNeeded, uriAppendTracksIfNeeded.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handleDownloadBtnClick$lambda-0, reason: not valid java name */
    public static final void m797handleDownloadBtnClick$lambda0(final BaseDownloadAction this$0, final DownloadStateModel.DownloadState downloadState, Uri uri, final NotificationInfo notificationInfo, final boolean z, final boolean z2, final UpsellInformation upsellInformation, final String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(downloadState, "$downloadState");
        Intrinsics.checkNotNullParameter(notificationInfo, "$notificationInfo");
        if (!ConnectivityUtil.hasAnyInternetConnection(this$0.activity.getApplicationContext())) {
            NetworkErrorDialog.create(this$0.activity, new NetworkErrorDialog.INetworkErrorDialogHandler() { // from class: com.amazon.mp3.detailpages.actions.BaseDownloadAction$handleDownloadBtnClick$1$handler$1
                @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
                public void onConnectionErrorDialogCancel() {
                }

                @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
                public void onDialogDismiss() {
                }

                @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
                public void onRetryConnection() {
                    this.this$0.handleDownloadBtnClick(downloadState, notificationInfo, z, z2, upsellInformation, str);
                }
            }).show();
        }
        if (downloadState == DownloadStateModel.DownloadState.AVAILABLE_FOR_DOWNLOAD && DownloadSettingDialogFragment.shouldShowDownloadSettingsDialogFragment(this$0.activity)) {
            DownloadSettingDialogFragment.showSettingsDialog(this$0.activity, uri.toString(), uri, notificationInfo, z);
        }
    }

    private final void sendUiClickMetric(String asin, ActionType actionType) {
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(actionType).withInteractionType(InteractionType.TAP).withPageType(this.pageType).withEventTime(System.currentTimeMillis()).withEntityId(asin).withEntityIdType(EntityIdType.ASIN).build());
    }
}
