package com.amazon.mp3.find.view;

import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.animation.AddButtonAnimation;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.library.fragment.AddAndDownloadTask;
import com.amazon.mp3.library.fragment.AddAndDownloadTrackTask;
import com.amazon.mp3.library.fragment.OnItemFinishedAddingListener;
import com.amazon.mp3.library.provider.LibraryStateProvider;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.music.metrics.mts.event.types.LinkType;
import com.amazon.music.metrics.mts.event.types.SelectionSourceType;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.models.HorizontalTileModel;
import com.amazon.music.views.library.models.SimpleHorizontalTileModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.base.LibraryAddableModel;
import com.amazon.music.views.library.models.enums.HorizontalTileType;
import com.amazon.music.views.library.providers.RowButtonOnClickProvider;
import com.amazon.music.views.library.views.AddButtonPresenter;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: SearchAddToLibraryProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\u0010\nJ\u0018\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0018\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\u0018\u0010\u001a\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u001b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u0013\u001a\u00020\u001bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\r\u001a\u001e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000ej\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0010`\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/amazon/mp3/find/view/SearchAddToLibraryProvider;", "Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;", "mActivity", "Landroidx/fragment/app/FragmentActivity;", "mLibraryStateProvider", "Lcom/amazon/mp3/library/provider/LibraryStateProvider;", "mAddToLibraryClickHandler", "Lkotlin/Function1;", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "", "(Landroidx/fragment/app/FragmentActivity;Lcom/amazon/mp3/library/provider/LibraryStateProvider;Lkotlin/jvm/functions/Function1;)V", "mAddAndDownloadTask", "Lcom/amazon/mp3/library/fragment/AddAndDownloadTask;", "mAddingTrackMap", "Ljava/util/HashMap;", "", "Lcom/amazon/mp3/prime/browse/metadata/PrimeTrack;", "Lkotlin/collections/HashMap;", "addTrackFromMetadataToLibrary", "model", "Lcom/amazon/music/views/library/models/base/LibraryAddableModel;", "addButtonPresenter", "Lcom/amazon/music/views/library/views/AddButtonPresenter;", "addTrackToLibrary", "isLikesProvider", "", "onClickAction", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "shouldDisplayRowButton", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SearchAddToLibraryProvider implements RowButtonOnClickProvider {
    private static final Logger logger = LoggerFactory.getLogger(SearchAddToLibraryProvider.class.getName());
    private final FragmentActivity mActivity;
    private AddAndDownloadTask mAddAndDownloadTask;
    private final Function1<ContentMetadata, Unit> mAddToLibraryClickHandler;
    private final HashMap<String, PrimeTrack> mAddingTrackMap;
    private final LibraryStateProvider mLibraryStateProvider;

    @Override // com.amazon.music.views.library.providers.RowButtonOnClickProvider
    public boolean isLikesProvider() {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SearchAddToLibraryProvider(FragmentActivity mActivity, LibraryStateProvider mLibraryStateProvider, Function1<? super ContentMetadata, Unit> mAddToLibraryClickHandler) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        Intrinsics.checkNotNullParameter(mLibraryStateProvider, "mLibraryStateProvider");
        Intrinsics.checkNotNullParameter(mAddToLibraryClickHandler, "mAddToLibraryClickHandler");
        this.mActivity = mActivity;
        this.mLibraryStateProvider = mLibraryStateProvider;
        this.mAddToLibraryClickHandler = mAddToLibraryClickHandler;
        this.mAddingTrackMap = new HashMap<>();
    }

    @Override // com.amazon.music.views.library.providers.RowButtonOnClickProvider
    public boolean shouldDisplayRowButton(BaseViewModel model) {
        Intrinsics.checkNotNullParameter(model, "model");
        if (model instanceof HorizontalTileModel) {
            return !((HorizontalTileModel) model).getIsInLibrary();
        }
        if (!(model instanceof SimpleHorizontalTileModel)) {
            return false;
        }
        SimpleHorizontalTileModel simpleHorizontalTileModel = (SimpleHorizontalTileModel) model;
        return simpleHorizontalTileModel.getHorizontalTileType() == HorizontalTileType.FEATURED && !simpleHorizontalTileModel.getIsInLibrary();
    }

    @Override // com.amazon.music.views.library.providers.RowButtonOnClickProvider
    public void onClickAction(BaseViewModel model, AddButtonPresenter addButtonPresenter) {
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(addButtonPresenter, "addButtonPresenter");
        if (model instanceof HorizontalTileModel) {
            addTrackFromMetadataToLibrary((LibraryAddableModel) model, addButtonPresenter);
        } else if (model instanceof SimpleHorizontalTileModel) {
            addTrackFromMetadataToLibrary((LibraryAddableModel) model, addButtonPresenter);
        }
    }

    private final void addTrackFromMetadataToLibrary(LibraryAddableModel model, AddButtonPresenter addButtonPresenter) {
        if (model.getMetadata() instanceof TrackMetadata) {
            addTrackToLibrary(model, addButtonPresenter);
        }
    }

    private final void addTrackToLibrary(LibraryAddableModel model, AddButtonPresenter addButtonPresenter) {
        PrimeTrack primeTrack = PrimeItemsTransformationUtil.convertToPrimeTrack((TrackMetadata) model.getMetadata());
        LibraryStateProvider libraryStateProvider = this.mLibraryStateProvider;
        Intrinsics.checkNotNullExpressionValue(primeTrack, "primeTrack");
        if (libraryStateProvider.contentIsNotInLibrary(primeTrack) && ContentAccessUtil.canContinueCatalogAction(this.mActivity, primeTrack, ContentAccessUtil.ContentAccessOperation.INTERACT)) {
            AddButtonAnimation.startAnimation(this.mActivity, addButtonPresenter.getRowButtonContainerView(), addButtonPresenter.getRowButtonView(), addButtonPresenter.getRowButtonClickedImageView(), null, false);
            this.mLibraryStateProvider.setAddingToLibrary(primeTrack);
            HashMap<String, PrimeTrack> map = this.mAddingTrackMap;
            String asin = primeTrack.getAsin();
            Intrinsics.checkNotNull(asin);
            map.put(asin, primeTrack);
            AddAndDownloadTrackTask addAndDownloadTrackTask = new AddAndDownloadTrackTask((Context) this.mActivity, primeTrack.getAsin(), false, true, new OnItemFinishedAddingListener() { // from class: com.amazon.mp3.find.view.SearchAddToLibraryProvider$$ExternalSyntheticLambda0
                @Override // com.amazon.mp3.library.fragment.OnItemFinishedAddingListener
                public final void onItemAdded(String str, boolean z) {
                    SearchAddToLibraryProvider.m1040addTrackToLibrary$lambda0(this.f$0, str, z);
                }
            });
            this.mAddAndDownloadTask = addAndDownloadTrackTask;
            addAndDownloadTrackTask.execute(new Void[0]);
            MetricsLogger.clickedAddPrimeContent(LinkType.TRACK, primeTrack.getAsin(), SelectionSourceType.SONGS, null);
            this.mAddToLibraryClickHandler.invoke(model.getMetadata());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: addTrackToLibrary$lambda-0, reason: not valid java name */
    public static final void m1040addTrackToLibrary$lambda0(SearchAddToLibraryProvider this$0, String str, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.mLibraryStateProvider.requestState(this$0.mAddingTrackMap.get(str));
        this$0.mAddingTrackMap.remove(str);
    }
}
