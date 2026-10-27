package com.amazon.mp3.providers;

import android.content.Intent;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.animation.AddButtonAnimation;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.catalog.fragment.datasource.LikesAndFollowsDBOperations;
import com.amazon.mp3.dialog.fragment.NetworkErrorDialogHandler;
import com.amazon.mp3.fragment.contextmenu.OnAddLibraryContentListener;
import com.amazon.mp3.library.cache.likes.LikesStateCacheManager;
import com.amazon.mp3.library.fragment.AddAndDownloadAlbumTask;
import com.amazon.mp3.library.mylibrary.likesandfollows.LikesAndFollowsFireflyCallsKt;
import com.amazon.mp3.library.provider.LibraryStateProvider;
import com.amazon.mp3.library.service.sync.MyLikesPlaylistDBUpdater;
import com.amazon.mp3.likes.LikesServiceProxy;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.prime.browse.metadata.PrimeAlbum;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.ContentEnabilityDialogUtil;
import com.amazon.mp3.util.extensions.ActivityKt;
import com.amazon.music.connect.firefly.LikesService;
import com.amazon.music.media.playback.ratings.LikeState;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.SelectionSourceType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.models.HorizontalTileModel;
import com.amazon.music.views.library.models.SimpleHorizontalTileModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.base.LibraryAddableModel;
import com.amazon.music.views.library.providers.RowButtonOnClickProvider;
import com.amazon.music.views.library.views.AddButtonPresenter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DefaultLikesProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 C2\u00020\u0001:\u0001CB?\b\u0007\u0012\u0006\u0010\u001c\u001a\u00020\u001b\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u001e\u0012\n\b\u0002\u0010&\u001a\u0004\u0018\u00010%\u0012\b\b\u0002\u0010(\u001a\u00020\u0017\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010*¢\u0006\u0004\bA\u0010BJ\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0018\u0010\b\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0018\u0010\n\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0004H\u0002J\u0018\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0004H\u0002J\u0018\u0010\f\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J$\u0010\u0012\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u000fH\u0002J\u001a\u0010\u0015\u001a\u00020\u00062\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0014\u001a\u00020\u0013H\u0002J\u0010\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0003\u001a\u00020\u0016H\u0016J\u0018\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00162\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u001a\u001a\u00020\u0017H\u0016R\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR$\u0010\u001f\u001a\u0004\u0018\u00010\u001e8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u0016\u0010&\u001a\u0004\u0018\u00010%8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b&\u0010'R\u0014\u0010(\u001a\u00020\u00178\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b(\u0010)R\u0018\u0010+\u001a\u0004\u0018\u00010*8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b+\u0010,R\"\u0010.\u001a\u00020-8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b.\u0010/\u001a\u0004\b0\u00101\"\u0004\b2\u00103R$\u00104\u001a\u0004\u0018\u00010\u000f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b4\u00105\u001a\u0004\b6\u00107\"\u0004\b8\u00109R0\u0010;\u001a\u0010\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u0006\u0018\u00010:8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b;\u0010<\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@¨\u0006D"}, d2 = {"Lcom/amazon/mp3/providers/DefaultLikesProvider;", "Lcom/amazon/music/views/library/providers/RowButtonOnClickProvider;", "Lcom/amazon/music/views/library/models/base/LibraryAddableModel;", "model", "Lcom/amazon/music/views/library/views/AddButtonPresenter;", "addButtonPresenter", "", "animateAndLikeTrackFromMetadata", "animateAndLikeTrack", "addOrLikeButtonPresenter", "animateAndLikeArtist", "changeArtistFollowStatus", "addAlbumTracksToLibrary", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "actionType", "", "asin", "blockRef", "sendMetricEvent", "Lcom/amazon/music/media/playback/ratings/LikeState;", "likeState", "notifyTrackLikeStateUpdate", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "", "shouldDisplayRowButton", "onClickAction", "isLikesProvider", "Landroidx/fragment/app/FragmentActivity;", "mActivity", "Landroidx/fragment/app/FragmentActivity;", "Lcom/amazon/mp3/library/provider/LibraryStateProvider;", "libraryStateProvider", "Lcom/amazon/mp3/library/provider/LibraryStateProvider;", "getLibraryStateProvider", "()Lcom/amazon/mp3/library/provider/LibraryStateProvider;", "setLibraryStateProvider", "(Lcom/amazon/mp3/library/provider/LibraryStateProvider;)V", "Lcom/amazon/mp3/fragment/contextmenu/OnAddLibraryContentListener;", "onAddLibraryContentListener", "Lcom/amazon/mp3/fragment/contextmenu/OnAddLibraryContentListener;", "canLikeOrFollow", "Z", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "Lcom/amazon/music/metrics/mts/event/types/SelectionSourceType;", "selectionSourceType", "Lcom/amazon/music/metrics/mts/event/types/SelectionSourceType;", "getSelectionSourceType", "()Lcom/amazon/music/metrics/mts/event/types/SelectionSourceType;", "setSelectionSourceType", "(Lcom/amazon/music/metrics/mts/event/types/SelectionSourceType;)V", "selectionSourceId", "Ljava/lang/String;", "getSelectionSourceId", "()Ljava/lang/String;", "setSelectionSourceId", "(Ljava/lang/String;)V", "Lkotlin/Function1;", "followStateCallback", "Lkotlin/jvm/functions/Function1;", "getFollowStateCallback", "()Lkotlin/jvm/functions/Function1;", "setFollowStateCallback", "(Lkotlin/jvm/functions/Function1;)V", "<init>", "(Landroidx/fragment/app/FragmentActivity;Lcom/amazon/mp3/library/provider/LibraryStateProvider;Lcom/amazon/mp3/fragment/contextmenu/OnAddLibraryContentListener;ZLcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class DefaultLikesProvider implements RowButtonOnClickProvider {
    private final boolean canLikeOrFollow;
    private Function1<? super Boolean, Unit> followStateCallback;
    private LibraryStateProvider libraryStateProvider;
    private final FragmentActivity mActivity;
    private final OnAddLibraryContentListener onAddLibraryContentListener;
    private PageType pageType;
    private String selectionSourceId;
    private SelectionSourceType selectionSourceType;

    @Override // com.amazon.music.views.library.providers.RowButtonOnClickProvider
    public boolean isLikesProvider() {
        return true;
    }

    @JvmOverloads
    public DefaultLikesProvider(FragmentActivity mActivity, LibraryStateProvider libraryStateProvider, OnAddLibraryContentListener onAddLibraryContentListener, boolean z, PageType pageType) {
        Intrinsics.checkNotNullParameter(mActivity, "mActivity");
        this.mActivity = mActivity;
        this.libraryStateProvider = libraryStateProvider;
        this.onAddLibraryContentListener = onAddLibraryContentListener;
        this.canLikeOrFollow = z;
        this.pageType = pageType;
        this.selectionSourceType = SelectionSourceType.SONGS;
    }

    public /* synthetic */ DefaultLikesProvider(FragmentActivity fragmentActivity, LibraryStateProvider libraryStateProvider, OnAddLibraryContentListener onAddLibraryContentListener, boolean z, PageType pageType, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(fragmentActivity, (i & 2) != 0 ? null : libraryStateProvider, (i & 4) != 0 ? null : onAddLibraryContentListener, (i & 8) != 0 ? true : z, (i & 16) != 0 ? null : pageType);
    }

    public final LibraryStateProvider getLibraryStateProvider() {
        return this.libraryStateProvider;
    }

    public final void setSelectionSourceType(SelectionSourceType selectionSourceType) {
        Intrinsics.checkNotNullParameter(selectionSourceType, "<set-?>");
        this.selectionSourceType = selectionSourceType;
    }

    public final void setSelectionSourceId(String str) {
        this.selectionSourceId = str;
    }

    public final Function1<Boolean, Unit> getFollowStateCallback() {
        return this.followStateCallback;
    }

    public final void setFollowStateCallback(Function1<? super Boolean, Unit> function1) {
        this.followStateCallback = function1;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0046  */
    @Override // com.amazon.music.views.library.providers.RowButtonOnClickProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean shouldDisplayRowButton(com.amazon.music.views.library.models.base.BaseViewModel r4) {
        /*
            r3 = this;
            java.lang.String r0 = "model"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            com.amazon.music.views.library.models.base.LibraryAddableModel r4 = (com.amazon.music.views.library.models.base.LibraryAddableModel) r4
            com.amazon.music.views.library.metadata.ContentMetadata r4 = r4.getMetadata()
            boolean r0 = r4 instanceof com.amazon.music.views.library.metadata.TrackMetadata
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L30
            com.amazon.mp3.capability.Capabilities r0 = com.amazon.mp3.AmazonApplication.getCapabilities()
            boolean r0 = r0.isLikesEverywhereEnabled()
            if (r0 == 0) goto L48
            com.amazon.music.views.library.metadata.TrackMetadata r4 = (com.amazon.music.views.library.metadata.TrackMetadata) r4
            java.lang.String r0 = r4.getAsin()
            if (r0 == 0) goto L48
            java.lang.String r4 = r4.getAssetType()
            java.lang.String r0 = "AUDIO"
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r0)
            if (r4 == 0) goto L48
            goto L46
        L30:
            boolean r0 = r4 instanceof com.amazon.music.views.library.metadata.ArtistMetadata
            if (r0 == 0) goto L48
            com.amazon.mp3.capability.Capabilities r0 = com.amazon.mp3.AmazonApplication.getCapabilities()
            boolean r0 = r0.isTrueLikesAndFollowsEnabled()
            if (r0 == 0) goto L48
            com.amazon.music.views.library.metadata.ArtistMetadata r4 = (com.amazon.music.views.library.metadata.ArtistMetadata) r4
            java.lang.String r4 = r4.getAsin()
            if (r4 == 0) goto L48
        L46:
            r4 = r1
            goto L49
        L48:
            r4 = r2
        L49:
            boolean r0 = r3.canLikeOrFollow
            if (r0 == 0) goto L50
            if (r4 == 0) goto L50
            goto L51
        L50:
            r1 = r2
        L51:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.providers.DefaultLikesProvider.shouldDisplayRowButton(com.amazon.music.views.library.models.base.BaseViewModel):boolean");
    }

    @Override // com.amazon.music.views.library.providers.RowButtonOnClickProvider
    public void onClickAction(BaseViewModel model, AddButtonPresenter addButtonPresenter) {
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(addButtonPresenter, "addButtonPresenter");
        if (!ConnectivityUtil.hasAnyInternetConnection(this.mActivity)) {
            new NetworkErrorDialogHandler(this.mActivity).showNetworkErrorDialog();
            return;
        }
        if (model instanceof LibraryAddableModel) {
            LibraryAddableModel libraryAddableModel = (LibraryAddableModel) model;
            ContentMetadata metadata = libraryAddableModel.getMetadata();
            if (metadata instanceof TrackMetadata) {
                animateAndLikeTrack(libraryAddableModel, addButtonPresenter);
                return;
            } else if (metadata instanceof AlbumMetadata) {
                addAlbumTracksToLibrary(libraryAddableModel, addButtonPresenter);
                return;
            } else {
                if (metadata instanceof ArtistMetadata) {
                    animateAndLikeArtist(libraryAddableModel, addButtonPresenter);
                    return;
                }
                return;
            }
        }
        if (model instanceof HorizontalTileModel) {
            animateAndLikeTrackFromMetadata((LibraryAddableModel) model, addButtonPresenter);
        } else if (model instanceof SimpleHorizontalTileModel) {
            animateAndLikeTrackFromMetadata((LibraryAddableModel) model, addButtonPresenter);
        }
    }

    private final void animateAndLikeTrackFromMetadata(LibraryAddableModel model, AddButtonPresenter addButtonPresenter) {
        if (model.getMetadata() instanceof TrackMetadata) {
            animateAndLikeTrack(model, addButtonPresenter);
        }
    }

    private final void animateAndLikeTrack(final LibraryAddableModel model, final AddButtonPresenter addButtonPresenter) {
        final PrimeTrack primeTrack = PrimeItemsTransformationUtil.convertToPrimeTrack((TrackMetadata) model.getMetadata());
        if (AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled()) {
            if (primeTrack == null ? false : primeTrack.getIsExplicit()) {
                DialogFragment dialogForDisabledReason = ContentEnabilityDialogUtil.getDialogForDisabledReason(this.mActivity, ContentUnavailableReason.EXPLICIT_CONTENT_FILTERED, null, primeTrack == null ? null : primeTrack.getTitle());
                FragmentActivity fragmentActivity = this.mActivity;
                if (fragmentActivity == null) {
                    return;
                }
                ActivityKt.showDialogFragment(fragmentActivity, dialogForDisabledReason);
                return;
            }
        }
        if (primeTrack != null) {
            MyLikesPlaylistDBUpdater.Companion companion = MyLikesPlaylistDBUpdater.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(primeTrack, "primeTrack");
            companion.addTrackToDB(primeTrack);
        }
        final boolean z = !model.getIsLiked();
        BauhausToastUtils.showTextToast(this.mActivity, z ? R.string.dmusic_like_action_toast : R.string.dmusic_un_like_action_toast, 0);
        AddButtonAnimation.startAnimation(this.mActivity, addButtonPresenter.getRowButtonContainerView(), addButtonPresenter.getRowButtonView(), addButtonPresenter.getRowButtonClickedImageView(), new AddButtonAnimation.AnimationFinishedListener() { // from class: com.amazon.mp3.providers.DefaultLikesProvider.animateAndLikeTrack.2
            @Override // com.amazon.mp3.animation.AddButtonAnimation.AnimationFinishedListener
            public void onAnimationCanceled() {
            }

            @Override // com.amazon.mp3.animation.AddButtonAnimation.AnimationFinishedListener
            public void onAnimationFinished() {
                String asin;
                addButtonPresenter.restyleLikeIcons(z);
                boolean z2 = z;
                LikeState likeState = z2 ? LikeState.LIKE : LikeState.NEUTRAL;
                ActionType actionType = z2 ? ActionType.THUMBS_UP : ActionType.THUMBS_UP_UNDO;
                PrimeTrack primeTrack2 = primeTrack;
                if (primeTrack2 != null && (asin = primeTrack2.getAsin()) != null) {
                    DefaultLikesProvider defaultLikesProvider = this;
                    boolean z3 = z;
                    LibraryAddableModel libraryAddableModel = model;
                    LikesService likesService = new LikesServiceProxy(defaultLikesProvider.mActivity).get();
                    if (likesService != null) {
                        likesService.setLikeState(asin, likeState);
                    }
                    LikesStateCacheManager instance$default = LikesStateCacheManager.Companion.getInstance$default(LikesStateCacheManager.INSTANCE, null, 1, null);
                    if (instance$default != null) {
                        instance$default.add(asin, z3);
                    }
                    defaultLikesProvider.sendMetricEvent(actionType, asin, libraryAddableModel.getMetadata().getViewId());
                }
                model.setLiked(z);
                DefaultLikesProvider defaultLikesProvider2 = this;
                PrimeTrack primeTrack3 = primeTrack;
                defaultLikesProvider2.notifyTrackLikeStateUpdate(primeTrack3 != null ? primeTrack3.getAsin() : null, likeState);
                MyLikesPlaylistDBUpdater.INSTANCE.updateMyLikesPlaylistInDB(this.mActivity, primeTrack, likeState);
                LibraryStateProvider libraryStateProvider = this.getLibraryStateProvider();
                if (libraryStateProvider == null) {
                    return;
                }
                libraryStateProvider.requestState(primeTrack);
            }
        }, false, true, R.animator.add_button_animation, R.animator.add_successful_animation_retain);
    }

    private final void animateAndLikeArtist(final LibraryAddableModel model, final AddButtonPresenter addOrLikeButtonPresenter) {
        AddButtonAnimation.startAnimation(this.mActivity, addOrLikeButtonPresenter.getRowButtonContainerView(), addOrLikeButtonPresenter.getRowButtonView(), addOrLikeButtonPresenter.getRowButtonClickedImageView(), new AddButtonAnimation.AnimationFinishedListener() { // from class: com.amazon.mp3.providers.DefaultLikesProvider.animateAndLikeArtist.1
            @Override // com.amazon.mp3.animation.AddButtonAnimation.AnimationFinishedListener
            public void onAnimationCanceled() {
            }

            @Override // com.amazon.mp3.animation.AddButtonAnimation.AnimationFinishedListener
            public void onAnimationFinished() {
                DefaultLikesProvider.this.changeArtistFollowStatus(model, addOrLikeButtonPresenter);
            }
        }, false, true, R.animator.add_button_animation, R.animator.add_successful_animation_retain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void changeArtistFollowStatus(final LibraryAddableModel model, AddButtonPresenter addOrLikeButtonPresenter) {
        addOrLikeButtonPresenter.restyleLikeIcons(!model.getIsLiked());
        if (model.getIsLiked()) {
            final ArtistMetadata artistMetadata = (ArtistMetadata) model.getMetadata();
            LikesAndFollowsDBOperations.INSTANCE.updateArtistFollowStatusInLocalDB(false, artistMetadata);
            Function1<? super Boolean, Unit> function1 = this.followStateCallback;
            if (function1 != null) {
                function1.invoke(Boolean.FALSE);
            }
            FragmentActivity fragmentActivity = this.mActivity;
            BauhausToastUtils.showTextToast(fragmentActivity, fragmentActivity.getString(R.string.dmusic_unfollow_artist_request_success_text), 1);
            BauhausToastUtils bauhausToastUtils = BauhausToastUtils.INSTANCE;
            bauhausToastUtils.setOnContentClickListener(new Function0<Unit>() { // from class: com.amazon.mp3.providers.DefaultLikesProvider.changeArtistFollowStatus.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    BauhausToastUtils.INSTANCE.setOnDismissListener(null);
                    LikesAndFollowsDBOperations.INSTANCE.updateArtistFollowStatusInLocalDB(true, artistMetadata);
                    Function1<Boolean, Unit> followStateCallback = this.getFollowStateCallback();
                    if (followStateCallback == null) {
                        return;
                    }
                    followStateCallback.invoke(Boolean.TRUE);
                }
            });
            bauhausToastUtils.setOnDismissListener(new Function0<Unit>() { // from class: com.amazon.mp3.providers.DefaultLikesProvider.changeArtistFollowStatus.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    LikesAndFollowsFireflyCallsKt.updateFireflyArtistFollowStatus$default(artistMetadata, model.getIsLiked(), null, 4, null);
                }
            });
        }
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

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendMetricEvent(ActionType actionType, String asin, String blockRef) {
        if (actionType != null) {
            MetricsHolder.getManager().handleEvent(UiClickEvent.builder(actionType).withPageType(this.pageType).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis()).withEntityId(asin).withEntityIdType(EntityIdType.ASIN).withBlockRef(blockRef).build());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyTrackLikeStateUpdate(String asin, LikeState likeState) {
        if (this.mActivity == null || asin == null) {
            return;
        }
        Intent intent = new Intent("LIKE_TRACK_BROADCAST");
        intent.putExtra("LIKE_TRACK_ASIN", asin);
        intent.putExtra("TRACK_LIKE_STATE", likeState.toString());
        LocalBroadcastManager.getInstance(this.mActivity).sendBroadcast(intent);
    }
}
