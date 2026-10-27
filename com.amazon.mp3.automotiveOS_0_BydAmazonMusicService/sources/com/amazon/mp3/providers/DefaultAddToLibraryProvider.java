package com.amazon.mp3.providers;

import android.content.Context;
import android.net.Uri;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.animation.AddButtonAnimation;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.fragment.contextmenu.OnAddLibraryContentListener;
import com.amazon.mp3.library.fragment.AddAndDownloadAlbumTask;
import com.amazon.mp3.library.fragment.AddAndDownloadTask;
import com.amazon.mp3.library.fragment.AddAndDownloadTrackTask;
import com.amazon.mp3.library.fragment.OnItemFinishedAddingListener;
import com.amazon.mp3.library.provider.LibraryStateProvider;
import com.amazon.mp3.prime.browse.metadata.PrimeAlbum;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.LinkType;
import com.amazon.music.metrics.mts.event.types.SelectionSourceType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.views.library.metadata.AlbumMetadata;
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
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DefaultAddToLibraryProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u00002\u00020\u0001B)\b\u0007\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0018\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u001f¢\u0006\u0004\b7\u00108J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0018\u0010\b\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0018\u0010\t\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J$\u0010\u000f\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\fH\u0002J\u0010\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u0010H\u0016J\u0018\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u0014\u001a\u00020\u0011H\u0016R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R$\u0010\u0019\u001a\u0004\u0018\u00010\u00188\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0016\u0010 \u001a\u0004\u0018\u00010\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010!R0\u0010%\u001a\u001e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020#0\"j\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020#`$8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010&R\u0018\u0010(\u001a\u0004\u0018\u00010'8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b(\u0010)R\"\u0010+\u001a\u00020*8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R$\u00101\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b1\u00102\u001a\u0004\b3\u00104\"\u0004\b5\u00106¨\u00069"}, d2 = {"Lcom/amazon/mp3/providers/DefaultAddToLibraryProvider;", "Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;", "Lcom/amazon/music/views/library/models/base/LibraryAddableModel;", "model", "Lcom/amazon/music/views/library/views/AddButtonPresenter;", "addButtonPresenter", "", "addTrackFromMetadataToLibrary", "addTrackToLibrary", "addAlbumTracksToLibrary", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "actionType", "", "asin", "blockRef", "sendMetricEvent", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "", "shouldDisplayRowButton", "onClickAction", "isLikesProvider", "Landroidx/fragment/app/FragmentActivity;", "mActivity", "Landroidx/fragment/app/FragmentActivity;", "Lcom/amazon/mp3/library/provider/LibraryStateProvider;", "libraryStateProvider", "Lcom/amazon/mp3/library/provider/LibraryStateProvider;", "getLibraryStateProvider", "()Lcom/amazon/mp3/library/provider/LibraryStateProvider;", "setLibraryStateProvider", "(Lcom/amazon/mp3/library/provider/LibraryStateProvider;)V", "Lcom/amazon/mp3/fragment/contextmenu/OnAddLibraryContentListener;", "onAddLibraryContentListener", "Lcom/amazon/mp3/fragment/contextmenu/OnAddLibraryContentListener;", "Ljava/util/HashMap;", "Lcom/amazon/mp3/prime/browse/metadata/PrimeTrack;", "Lkotlin/collections/HashMap;", "mAddingTrackMap", "Ljava/util/HashMap;", "Lcom/amazon/mp3/library/fragment/AddAndDownloadTask;", "mAddAndDownloadTask", "Lcom/amazon/mp3/library/fragment/AddAndDownloadTask;", "Lcom/amazon/music/metrics/mts/event/types/SelectionSourceType;", "selectionSourceType", "Lcom/amazon/music/metrics/mts/event/types/SelectionSourceType;", "getSelectionSourceType", "()Lcom/amazon/music/metrics/mts/event/types/SelectionSourceType;", "setSelectionSourceType", "(Lcom/amazon/music/metrics/mts/event/types/SelectionSourceType;)V", "selectionSourceId", "Ljava/lang/String;", "getSelectionSourceId", "()Ljava/lang/String;", "setSelectionSourceId", "(Ljava/lang/String;)V", "<init>", "(Landroidx/fragment/app/FragmentActivity;Lcom/amazon/mp3/library/provider/LibraryStateProvider;Lcom/amazon/mp3/fragment/contextmenu/OnAddLibraryContentListener;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class DefaultAddToLibraryProvider implements RowButtonOnClickProvider {
    private LibraryStateProvider libraryStateProvider;
    private final FragmentActivity mActivity;
    private AddAndDownloadTask mAddAndDownloadTask;
    private final HashMap<String, PrimeTrack> mAddingTrackMap;
    private final OnAddLibraryContentListener onAddLibraryContentListener;
    private String selectionSourceId;
    private SelectionSourceType selectionSourceType;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public DefaultAddToLibraryProvider(FragmentActivity mActivity, LibraryStateProvider libraryStateProvider) {
        this(mActivity, libraryStateProvider, null, 4, null);
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
    }

    @Override // com.amazon.music.views.library.providers.RowButtonOnClickProvider
    public boolean isLikesProvider() {
        return false;
    }

    @JvmOverloads
    public DefaultAddToLibraryProvider(FragmentActivity mActivity, LibraryStateProvider libraryStateProvider, OnAddLibraryContentListener onAddLibraryContentListener) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        this.mActivity = mActivity;
        this.libraryStateProvider = libraryStateProvider;
        this.onAddLibraryContentListener = onAddLibraryContentListener;
        this.mAddingTrackMap = new HashMap<>();
        this.selectionSourceType = SelectionSourceType.SONGS;
    }

    public /* synthetic */ DefaultAddToLibraryProvider(FragmentActivity fragmentActivity, LibraryStateProvider libraryStateProvider, OnAddLibraryContentListener onAddLibraryContentListener, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(fragmentActivity, (i & 2) != 0 ? null : libraryStateProvider, (i & 4) != 0 ? null : onAddLibraryContentListener);
    }

    public final void setLibraryStateProvider(LibraryStateProvider libraryStateProvider) {
        this.libraryStateProvider = libraryStateProvider;
    }

    public final void setSelectionSourceType(SelectionSourceType selectionSourceType) {
        Intrinsics.checkNotNullParameter(selectionSourceType, "<set-?>");
        this.selectionSourceType = selectionSourceType;
    }

    public final void setSelectionSourceId(String str) {
        this.selectionSourceId = str;
    }

    @Override // com.amazon.music.views.library.providers.RowButtonOnClickProvider
    public boolean shouldDisplayRowButton(BaseViewModel model) {
        Intrinsics.checkNotNullParameter(model, "model");
        if (model instanceof LibraryAddableModel) {
            return !((LibraryAddableModel) model).getIsInLibrary() && Feature.should_show_add_to_library_button.isEnabled(true);
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
        if (model instanceof LibraryAddableModel) {
            LibraryAddableModel libraryAddableModel = (LibraryAddableModel) model;
            ContentMetadata metadata = libraryAddableModel.getMetadata();
            if (metadata instanceof TrackMetadata) {
                addTrackToLibrary(libraryAddableModel, addButtonPresenter);
                return;
            } else {
                if (metadata instanceof AlbumMetadata) {
                    addAlbumTracksToLibrary(libraryAddableModel, addButtonPresenter);
                    return;
                }
                return;
            }
        }
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

    private final void addTrackToLibrary(final LibraryAddableModel model, AddButtonPresenter addButtonPresenter) {
        String asin;
        PrimeTrack primeTrack = PrimeItemsTransformationUtil.convertToPrimeTrack((TrackMetadata) model.getMetadata());
        if (ContentAccessUtil.canContinueCatalogAction(this.mActivity, primeTrack, ContentAccessUtil.ContentAccessOperation.INTERACT)) {
            AddButtonAnimation.startAnimation(this.mActivity, addButtonPresenter.getRowButtonContainerView(), addButtonPresenter.getRowButtonView(), addButtonPresenter.getRowButtonClickedImageView(), null, false);
            LibraryStateProvider libraryStateProvider = this.libraryStateProvider;
            if (libraryStateProvider != null) {
                Intrinsics.checkNotNullExpressionValue(primeTrack, "primeTrack");
                libraryStateProvider.setAddingToLibrary(primeTrack);
            }
            if (primeTrack != null && (asin = primeTrack.getAsin()) != null) {
                HashMap<String, PrimeTrack> map = this.mAddingTrackMap;
                Intrinsics.checkNotNullExpressionValue(primeTrack, "primeTrack");
                map.put(asin, primeTrack);
            }
            AddAndDownloadTrackTask addAndDownloadTrackTask = new AddAndDownloadTrackTask((Context) this.mActivity, primeTrack.getAsin(), false, true, new OnItemFinishedAddingListener() { // from class: com.amazon.mp3.providers.DefaultAddToLibraryProvider$$ExternalSyntheticLambda0
                @Override // com.amazon.mp3.library.fragment.OnItemFinishedAddingListener
                public final void onItemAdded(String str, boolean z) {
                    DefaultAddToLibraryProvider.m1599addTrackToLibrary$lambda2(this.f$0, model, str, z);
                }
            });
            this.mAddAndDownloadTask = addAndDownloadTrackTask;
            addAndDownloadTrackTask.execute(new Void[0]);
            MetricsLogger.clickedAddPrimeContent(LinkType.TRACK, primeTrack.getAsin(), this.selectionSourceType, this.selectionSourceId);
            ActionType actionType = ActionType.ADD_TRACK_TO_LIBRARY;
            String asin2 = primeTrack.getAsin();
            Intrinsics.checkNotNull(asin2);
            sendMetricEvent(actionType, asin2, model.getMetadata().getViewId());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: addTrackToLibrary$lambda-2, reason: not valid java name */
    public static final void m1599addTrackToLibrary$lambda2(DefaultAddToLibraryProvider this$0, LibraryAddableModel model, String str, boolean z) {
        Uri uri;
        OnAddLibraryContentListener onAddLibraryContentListener;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        LibraryStateProvider libraryStateProvider = this$0.libraryStateProvider;
        if (libraryStateProvider != null) {
            libraryStateProvider.requestState(this$0.mAddingTrackMap.get(str));
        }
        this$0.mAddingTrackMap.remove(str);
        model.updateLibraryState(true, Boolean.FALSE);
        ContentMetadata metadata = model.getMetadata();
        TrackMetadata trackMetadata = metadata instanceof TrackMetadata ? (TrackMetadata) metadata : null;
        if (trackMetadata == null || (uri = trackMetadata.getUri()) == null || (onAddLibraryContentListener = this$0.onAddLibraryContentListener) == null) {
            return;
        }
        onAddLibraryContentListener.onAddContentComplete(uri);
    }

    private final void addAlbumTracksToLibrary(LibraryAddableModel model, AddButtonPresenter addButtonPresenter) {
        String asin;
        AddButtonAnimation.startAnimation(this.mActivity, addButtonPresenter.getRowButtonContainerView(), addButtonPresenter.getRowButtonView(), addButtonPresenter.getRowButtonClickedImageView(), null, false);
        ContentMetadata metadata = model.getMetadata();
        AlbumMetadata albumMetadata = metadata instanceof AlbumMetadata ? (AlbumMetadata) metadata : null;
        if (albumMetadata == null || (asin = albumMetadata.getAsin()) == null) {
            return;
        }
        new AddAndDownloadAlbumTask(AmazonApplication.getApplication().getApplicationContext(), new PrimeAlbum(null, asin, null, null, null, 0, null, null), false).execute(new Void[0]);
        sendMetricEvent(ActionType.ADD_ALBUM_TO_LIBRARY, asin, model.getMetadata().getViewId());
    }

    private final void sendMetricEvent(ActionType actionType, String asin, String blockRef) {
        if (actionType != null) {
            MetricsHolder.getManager().handleEvent(UiClickEvent.builder(actionType).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis()).withEntityId(asin).withEntityIdType(EntityIdType.ASIN).withBlockRef(blockRef).build());
        }
    }
}
