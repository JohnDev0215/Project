package com.amazon.mp3.providers;

import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.brush.FollowStateUseCase;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.catalog.fragment.datasource.LikesAndFollowsDBOperations;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.prime.browse.PrimeArtistPlaybackHandler;
import com.amazon.mp3.prime.browse.metadata.PrimeArtist;
import com.amazon.mp3.social.MusicRelationshipProvider;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.share.ArtistShareProvider;
import com.amazon.music.views.library.deeplinks.ContentMetadataOnClickListener;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.ArtistDetailHeaderModel;
import com.amazon.music.views.library.providers.ArtistClickListenerProvider;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: ArtistClickHandler.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B/\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\u001d\u0012\b\u0010!\u001a\u0004\u0018\u00010 \u0012\b\u0010$\u001a\u0004\u0018\u00010#\u0012\b\u0010'\u001a\u0004\u0018\u00010&¢\u0006\u0004\b2\u00103J&\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0002J\u0018\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH\u0002J\u0010\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016J,\u0010\u0011\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00122\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\b0\u0014H\u0016J\u0010\u0010\u0016\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\fH\u0016J(\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\f2\u000e\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017H\u0016J(\u0010\u001b\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\f2\u000e\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017H\u0016J\u0018\u0010\u001c\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\fH\u0016R\u0016\u0010\u001e\u001a\u0004\u0018\u00010\u001d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0016\u0010!\u001a\u0004\u0018\u00010 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\"R\u0016\u0010$\u001a\u0004\u0018\u00010#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%R\u0016\u0010'\u001a\u0004\u0018\u00010&8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b'\u0010(R\u001c\u0010*\u001a\n )*\u0004\u0018\u00010\u00040\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010+R\u001b\u00101\u001a\u00020,8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b-\u0010.\u001a\u0004\b/\u00100¨\u00064"}, d2 = {"Lcom/amazon/mp3/providers/ArtistClickHandler;", "Lcom/amazon/music/views/library/providers/ArtistClickListenerProvider;", "Landroid/content/Context;", "context", "", "asin", "Lcom/amazon/music/media/playback/MediaCollectionType;", "collectionType", "", "startArtistSFA", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "actionType", "Lcom/amazon/music/views/library/metadata/ArtistMetadata;", "metadata", "emitUiClickMetric", "Lcom/amazon/music/views/library/models/ArtistDetailHeaderModel;", "model", "onFollowClick", "", "isFollowing", "Lkotlin/Function1;", "callback", "onShareClick", "", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadataList", "onPlayActionIconClick", "onShuffleActionIconClick", "onStationActionIconClick", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "Lcom/amazon/mp3/social/MusicRelationshipProvider;", "musicRelationshipProvider", "Lcom/amazon/mp3/social/MusicRelationshipProvider;", "Lcom/amazon/mp3/brush/FollowStateUseCase;", "followStateUseCase", "Lcom/amazon/mp3/brush/FollowStateUseCase;", "Lcom/amazon/music/views/library/deeplinks/ContentMetadataOnClickListener;", "contentMetadataOnClickListener", "Lcom/amazon/music/views/library/deeplinks/ContentMetadataOnClickListener;", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "Lcom/amazon/mp3/prime/browse/PrimeArtistPlaybackHandler;", "artistPlaybackHandler$delegate", "Lkotlin/Lazy;", "getArtistPlaybackHandler", "()Lcom/amazon/mp3/prime/browse/PrimeArtistPlaybackHandler;", "artistPlaybackHandler", "<init>", "(Landroidx/fragment/app/FragmentActivity;Lcom/amazon/mp3/social/MusicRelationshipProvider;Lcom/amazon/mp3/brush/FollowStateUseCase;Lcom/amazon/music/views/library/deeplinks/ContentMetadataOnClickListener;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class ArtistClickHandler implements ArtistClickListenerProvider {
    private final FragmentActivity activity;
    private final ContentMetadataOnClickListener contentMetadataOnClickListener;
    private final FollowStateUseCase followStateUseCase;
    private final MusicRelationshipProvider musicRelationshipProvider;
    private final String TAG = ArtistClickHandler.class.getSimpleName();

    /* JADX INFO: renamed from: artistPlaybackHandler$delegate, reason: from kotlin metadata */
    private final Lazy artistPlaybackHandler = LazyKt__LazyJVMKt.lazy(new Function0<PrimeArtistPlaybackHandler>() { // from class: com.amazon.mp3.providers.ArtistClickHandler$artistPlaybackHandler$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final PrimeArtistPlaybackHandler invoke() {
            return new PrimeArtistPlaybackHandler(this.this$0.activity);
        }
    });

    public ArtistClickHandler(FragmentActivity fragmentActivity, MusicRelationshipProvider musicRelationshipProvider, FollowStateUseCase followStateUseCase, ContentMetadataOnClickListener contentMetadataOnClickListener) {
        this.activity = fragmentActivity;
        this.musicRelationshipProvider = musicRelationshipProvider;
        this.followStateUseCase = followStateUseCase;
        this.contentMetadataOnClickListener = contentMetadataOnClickListener;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.providers.ArtistClickHandler$onFollowClick$1, reason: invalid class name */
    /* JADX INFO: compiled from: ArtistClickHandler.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Boolean, Unit> {
        AnonymousClass1(Object obj) {
            super(1, obj, ArtistDetailHeaderModel.class, "updateFollowState", "updateFollowState(Z)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
            invoke(bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void invoke(boolean z) {
            ((ArtistDetailHeaderModel) this.receiver).updateFollowState(z);
        }
    }

    @Override // com.amazon.music.views.library.providers.ArtistClickListenerProvider
    public void onFollowClick(ArtistDetailHeaderModel model) {
        Intrinsics.checkNotNullParameter(model, "model");
        onFollowClick(model.getMetadata(), model.getIsFollowing(), new AnonymousClass1(model));
    }

    @Override // com.amazon.music.views.library.providers.ArtistClickListenerProvider
    public void onFollowClick(final ArtistMetadata metadata, final boolean isFollowing, final Function1<? super Boolean, Unit> callback) {
        MusicRelationshipProvider musicRelationshipProvider;
        Observable<Void> observableSubscribeOn;
        Observable<Void> observableObserveOn;
        MusicRelationshipProvider musicRelationshipProvider2;
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        Intrinsics.checkNotNullParameter(callback, "callback");
        Observable<Void> observableFollowArtist = null;
        if (isFollowing) {
            String asin = metadata.getAsin();
            if (asin != null && (musicRelationshipProvider2 = this.musicRelationshipProvider) != null) {
                observableFollowArtist = musicRelationshipProvider2.unFollowArtist(asin);
            }
        } else {
            String asin2 = metadata.getAsin();
            if (asin2 != null && (musicRelationshipProvider = this.musicRelationshipProvider) != null) {
                ChildUserUtil childUserUtil = ChildUserUtil.INSTANCE;
                Context applicationContext = AmazonApplication.getApplication().getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplication().applicationContext");
                observableFollowArtist = musicRelationshipProvider.followArtist(asin2, childUserUtil.isChildUser(applicationContext));
            }
        }
        if (observableFollowArtist == null || (observableSubscribeOn = observableFollowArtist.subscribeOn(Schedulers.io())) == null || (observableObserveOn = observableSubscribeOn.observeOn(AndroidSchedulers.mainThread())) == null) {
            return;
        }
        observableObserveOn.subscribe(new Observer<Object>() { // from class: com.amazon.mp3.providers.ArtistClickHandler.onFollowClick.2
            @Override // rx.Observer
            public void onNext(Object p0) {
            }

            @Override // rx.Observer
            public void onCompleted() {
                FollowStateUseCase followStateUseCase;
                BauhausToastUtils.showTextToast(this.activity, isFollowing ? R.string.dmusic_unfollow_artist_request_success_text : R.string.dmusic_follow_artist_request_success_text, 1);
                if (!isFollowing && (followStateUseCase = this.followStateUseCase) != null) {
                    followStateUseCase.artistFollowedUpdate();
                }
                if (AmazonApplication.getCapabilities().isTrueLikesAndFollowsEnabled()) {
                    LikesAndFollowsDBOperations.INSTANCE.updateArtistFollowStatusInLocalDB(!isFollowing, metadata);
                }
                callback.invoke(Boolean.valueOf(!isFollowing));
                this.emitUiClickMetric(!isFollowing ? ActionType.FOLLOW_ARTIST : ActionType.UNFOLLOW_ARTIST, metadata);
            }

            @Override // rx.Observer
            public void onError(Throwable throwable) {
                Intrinsics.checkNotNullParameter(throwable, "throwable");
                Log.error(this.TAG, Intrinsics.stringPlus("Cannot update artist follow status ", throwable.getLocalizedMessage()));
                BauhausToastUtils.showTextToast(this.activity, R.string.dmusic_follow_artist_request_failed_text, 0);
            }
        });
    }

    @Override // com.amazon.music.views.library.providers.ArtistClickListenerProvider
    public void onShareClick(ArtistMetadata metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        new ArtistShareProvider(this.activity, EndpointsProvider.get().getMusicDomain(), metadata.getAsin(), metadata.getTitle(), metadata.getImageUrl()).startShare();
        emitUiClickMetric(ActionType.SHARE, metadata);
    }

    @Override // com.amazon.music.views.library.providers.ArtistClickListenerProvider
    public void onPlayActionIconClick(Context context, ArtistMetadata metadata, List<? extends ContentMetadata> metadataList) {
        ContentMetadataOnClickListener contentMetadataOnClickListener;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        if (metadataList != null && (contentMetadataOnClickListener = this.contentMetadataOnClickListener) != null) {
            ContentMetadataOnClickListener.DefaultImpls.onContentListClicked$default(contentMetadataOnClickListener, metadataList, 0, null, 4, null);
        }
        emitUiClickMetric(ActionType.PLAY_ARTIST, metadata);
    }

    @Override // com.amazon.music.views.library.providers.ArtistClickListenerProvider
    public void onShuffleActionIconClick(Context context, ArtistMetadata metadata, List<? extends ContentMetadata> metadataList) {
        ContentMetadataOnClickListener contentMetadataOnClickListener;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        if (UserSubscriptionUtil.isNightwingOnly()) {
            startArtistSFA(context, metadata.getAsin(), null);
        } else if (metadataList != null && (contentMetadataOnClickListener = this.contentMetadataOnClickListener) != null) {
            ContentMetadataOnClickListener.DefaultImpls.onContentListClicked$default(contentMetadataOnClickListener, metadataList, RangesKt___RangesKt.random(new IntRange(0, metadataList.size()), Random.INSTANCE), null, 4, null);
        }
        emitUiClickMetric(ActionType.PLAY_SHUFFLE_ALL, metadata);
    }

    @Override // com.amazon.music.views.library.providers.ArtistClickListenerProvider
    public void onStationActionIconClick(Context context, ArtistMetadata metadata) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        PrimeArtist primeArtistConvertToPrimeArtist = PrimeItemsTransformationUtil.convertToPrimeArtist(metadata);
        Intrinsics.checkNotNullExpressionValue(primeArtistConvertToPrimeArtist, "convertToPrimeArtist(metadata)");
        if (ContentAccessUtil.canContinueCatalogAction(this.activity, primeArtistConvertToPrimeArtist, ContentAccessUtil.ContentAccessOperation.STREAM)) {
            StationUtils.startSFA(this.activity, metadata.getAsin(), "ARTIST_SEED", PlaybackPageType.ARTIST_DETAIL, ControlSource.APP_UI);
        }
        StationUtils.sendStartSFAUiClickMetrics(metadata.getAsin(), EntityIdType.SFA_ARTIST_SEED);
    }

    private final void startArtistSFA(Context context, String asin, MediaCollectionType collectionType) {
        StationUtils.startSFA(context, asin, "ARTIST_SEED", PlaybackPageType.ARTIST_DETAIL, ControlSource.APP_UI);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void emitUiClickMetric(ActionType actionType, ArtistMetadata metadata) {
        UiClickEvent uiClickEventBuild = UiClickEvent.builder(actionType).withInteractionType(InteractionType.TAP).withEntityType(EntityType.ARTIST).withEntityIdType(EntityIdType.ASIN).withEntityId(metadata.getAsin()).withEventTime(System.currentTimeMillis()).withBlockRef(metadata.getViewId()).build();
        Intrinsics.checkNotNullExpressionValue(uiClickEventBuild, "builder(actionType)\n    …\n                .build()");
        MetricsHolder.getManager().handleEvent(uiClickEventBuild);
    }
}
