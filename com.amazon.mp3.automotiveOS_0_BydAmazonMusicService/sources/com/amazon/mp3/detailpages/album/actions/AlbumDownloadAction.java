package com.amazon.mp3.detailpages.album.actions;

import android.net.Uri;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.detailpages.actions.BaseDownloadAction;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.music.downloads.notification.NotificationInfo;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.models.DownloadStateModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AlbumDownloadAction.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u001e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/detailpages/album/actions/AlbumDownloadAction;", "Lcom/amazon/mp3/detailpages/actions/BaseDownloadAction;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "contentUri", "Landroid/net/Uri;", "(Landroidx/fragment/app/FragmentActivity;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;Landroid/net/Uri;)V", "downloadAlbum", "", "metadata", "Lcom/amazon/music/views/library/metadata/AlbumMetadata;", "downloadState", "Lcom/amazon/music/views/library/models/DownloadStateModel$DownloadState;", "shouldBlockDownload", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AlbumDownloadAction extends BaseDownloadAction {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlbumDownloadAction(FragmentActivity activity, PageType pageType, Uri contentUri) {
        super(activity, pageType, contentUri);
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Intrinsics.checkNotNullParameter(contentUri, "contentUri");
    }

    public final void downloadAlbum(AlbumMetadata metadata, DownloadStateModel.DownloadState downloadState, boolean shouldBlockDownload) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        Intrinsics.checkNotNullParameter(downloadState, "downloadState");
        Integer trackCount = metadata.getTrackCount();
        int iIntValue = trackCount == null ? 0 : trackCount.intValue();
        String quantityString = getActivity().getResources().getQuantityString(R.plurals.dmusic_download_str_to_download, iIntValue, Integer.valueOf(iIntValue));
        Intrinsics.checkNotNullExpressionValue(quantityString, "activity.resources.getQu…  trackCount, trackCount)");
        handleDownloadBtnClick(downloadState, new NotificationInfo(metadata.getTitle(), quantityString, metadata.getImageUrl()), true, shouldBlockDownload, shouldBlockDownload ? UpsellUtil.getUpsellInformation(metadata.getAsin(), UpsellReason.DOWNLOAD_CONTENT, UpsellSourceEntity.ALBUM) : null, metadata.getAsin());
    }
}
