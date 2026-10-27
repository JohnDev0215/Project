package com.amazon.mp3.providers;

import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.capability.FeatureFlagsProvider;
import com.amazon.mp3.library.fragment.AddAndDownloadTrackTask;
import com.amazon.mp3.library.fragment.OnItemFinishedAddingListener;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.models.DownloadStateModel;
import com.amazon.music.views.library.models.HorizontalTileModel;
import com.amazon.music.views.library.models.TableRowItemModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.base.LibraryAddableModel;
import com.amazon.music.views.library.providers.TrackDownloadButtonProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: DefaultTrackDownloadButtonProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/providers/DefaultTrackDownloadButtonProvider;", "Lcom/amazon/music/views/library/providers/TrackDownloadButtonProvider;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "(Landroidx/fragment/app/FragmentActivity;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;)V", "getDownloadStateModel", "Lcom/amazon/music/views/library/models/DownloadStateModel;", "model", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "getTrackMetadata", "Lcom/amazon/music/views/library/metadata/TrackMetadata;", "isFeatureEnabled", "", "onDownload", "", "shouldShowDownloadIcon", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DefaultTrackDownloadButtonProvider implements TrackDownloadButtonProvider {
    private static final Logger logger;
    private final FragmentActivity activity;
    private final PageType pageType;

    public DefaultTrackDownloadButtonProvider(FragmentActivity activity, PageType pageType) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.activity = activity;
        this.pageType = pageType;
    }

    static {
        Logger logger2 = LoggerFactory.getLogger((Class<?>) DefaultTrackDownloadButtonProvider.class);
        Intrinsics.checkNotNullExpressionValue(logger2, "getLogger(DefaultTrackDo…ttonProvider::class.java)");
        logger = logger2;
    }

    @Override // com.amazon.music.views.library.providers.TrackDownloadButtonProvider
    public void onDownload(BaseViewModel model) {
        String asin;
        Intrinsics.checkNotNullParameter(model, "model");
        if (!(model instanceof LibraryAddableModel)) {
            logger.warn("NoOp - Unsupported BaseViewModel.");
            return;
        }
        TrackMetadata trackMetadata = getTrackMetadata(model);
        if (trackMetadata == null || (asin = trackMetadata.getAsin()) == null) {
            return;
        }
        Context applicationContext = AmazonApplication.getApplication().getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplication().applicationContext");
        LibraryAddableModel libraryAddableModel = (LibraryAddableModel) model;
        if (ContentAccessUtil.canContinueCatalogAction(this.activity, PrimeItemsTransformationUtil.convertToPrimeTrack((TrackMetadata) libraryAddableModel.getMetadata()), ContentAccessUtil.ContentAccessOperation.DOWNLOAD)) {
            new AddAndDownloadTrackTask(applicationContext, asin, true, true, (OnItemFinishedAddingListener) null).execute(new Void[0]);
            UiClickEvent.Builder builderWithBlockRef = UiClickEvent.builder(ActionType.DOWNLOAD_TRACK_BUTTON).withInteractionType(InteractionType.TAP).withEntityId(asin).withEntityIdType(EntityIdType.ASIN).withEventTime(System.currentTimeMillis()).withBlockRef(libraryAddableModel.getViewId());
            PageType pageType = PageType.DETAIL_ARTIST;
            PageType pageType2 = this.pageType;
            if (pageType == pageType2) {
                pageType2 = PageType.CLOUD_LIBRARY_ARTISTS;
            }
            MetricsHolder.getManager().handleEvent(builderWithBlockRef.withPageType(pageType2).build());
        }
    }

    @Override // com.amazon.music.views.library.providers.TrackDownloadButtonProvider
    public boolean shouldShowDownloadIcon(BaseViewModel model) {
        Intrinsics.checkNotNullParameter(model, "model");
        DownloadStateModel downloadStateModel = getDownloadStateModel(model);
        if (downloadStateModel == null) {
            return false;
        }
        DownloadStateModel.DownloadState downloadState = downloadStateModel.getDownloadState();
        return (!isFeatureEnabled(model) || downloadState == DownloadStateModel.DownloadState.QUEUING || downloadState == DownloadStateModel.DownloadState.DOWNLOADED || downloadState == DownloadStateModel.DownloadState.DOWNLOADING) ? false : true;
    }

    @Override // com.amazon.music.views.library.providers.TrackDownloadButtonProvider
    public boolean isFeatureEnabled(BaseViewModel model) {
        Intrinsics.checkNotNullParameter(model, "model");
        if (model instanceof TableRowItemModel) {
            return FeatureFlagsProvider.getFeatureFlagProvider().isDownloadTrackButtonEnabled();
        }
        if (model instanceof HorizontalTileModel) {
            return AmazonApplication.getCapabilities().isTrackDownloadFeatureAvailable();
        }
        return false;
    }

    private final DownloadStateModel getDownloadStateModel(BaseViewModel model) {
        if (model instanceof TableRowItemModel) {
            return ((TableRowItemModel) model).getDownloadStateModel();
        }
        if (model instanceof HorizontalTileModel) {
            return ((HorizontalTileModel) model).getDownloadStateModel();
        }
        return null;
    }

    private final TrackMetadata getTrackMetadata(BaseViewModel model) {
        if (model instanceof TableRowItemModel) {
            ContentMetadata metadata = ((TableRowItemModel) model).getMetadata();
            if (metadata instanceof TrackMetadata) {
                return (TrackMetadata) metadata;
            }
            return null;
        }
        if (!(model instanceof HorizontalTileModel)) {
            return null;
        }
        ContentMetadata metadata2 = ((HorizontalTileModel) model).getMetadata();
        if (metadata2 instanceof TrackMetadata) {
            return (TrackMetadata) metadata2;
        }
        return null;
    }
}
