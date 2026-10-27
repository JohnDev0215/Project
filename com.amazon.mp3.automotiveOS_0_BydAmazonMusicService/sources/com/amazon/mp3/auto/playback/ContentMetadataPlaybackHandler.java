package com.amazon.mp3.auto.playback;

import android.content.Context;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.auto.VoiceUtil;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifier;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.navigation.playback.TracksPlaybackHelper;
import com.amazon.mp3.playback.PlaybackListener;
import com.amazon.mp3.playback.presets.CarModeQueueManager;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.prime.TrackRequester;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PlaybackHandlerUtil;
import com.amazon.music.metrics.mts.event.types.ExperienceMode;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.PodcastMetadata;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.metadata.UserPlaylistMetadata;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: ContentMetadataPlaybackHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 !2\u00020\u0001:\u0001!BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ$\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u00122\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J.\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u001b\u001a\u00020\u00172\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001dJ>\u0010\u001e\u001a\u00020\u00192\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u00122\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u001b\u001a\u00020\u00172\b\b\u0002\u0010\u001f\u001a\u00020 2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001dR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/amazon/mp3/auto/playback/ContentMetadataPlaybackHandler;", "", "context", "Landroid/content/Context;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "playbackPageType", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "experienceMode", "Lcom/amazon/music/metrics/mts/event/types/ExperienceMode;", "carModeQueueManager", "Lcom/amazon/mp3/playback/presets/CarModeQueueManager;", "trackPlaybackScope", "Lkotlinx/coroutines/CoroutineScope;", "(Landroid/content/Context;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;Lcom/amazon/music/metrics/mts/event/types/ExperienceMode;Lcom/amazon/mp3/playback/presets/CarModeQueueManager;Lkotlinx/coroutines/CoroutineScope;)V", "playbackHelper", "Lcom/amazon/mp3/auto/playback/AutoPlaybackHelper;", "createTrackList", "", "Lcom/amazon/mp3/prime/browse/metadata/PrimeTrack;", "contentMetadataList", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "eligibilityVerified", "", "handle", "", "contentMetadata", "isVoiceInitiated", "playbackListener", "Lcom/amazon/mp3/playback/PlaybackListener;", "handleTrackList", "startingPosition", "", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ContentMetadataPlaybackHandler {
    private static final String TAG = ContentMetadataPlaybackHandler.class.getSimpleName();
    private final CarModeQueueManager carModeQueueManager;
    private final Context context;
    private final PageType pageType;
    private final AutoPlaybackHelper playbackHelper;
    private final PlaybackPageType playbackPageType;
    private final CoroutineScope trackPlaybackScope;

    public ContentMetadataPlaybackHandler(Context context, PageType pageType, PlaybackPageType playbackPageType, ExperienceMode experienceMode, CarModeQueueManager carModeQueueManager, CoroutineScope trackPlaybackScope) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        Intrinsics.checkNotNullParameter(trackPlaybackScope, "trackPlaybackScope");
        this.context = context;
        this.pageType = pageType;
        this.playbackPageType = playbackPageType;
        this.carModeQueueManager = carModeQueueManager;
        this.trackPlaybackScope = trackPlaybackScope;
        this.playbackHelper = new AutoPlaybackHelper(context, pageType, playbackPageType, experienceMode, trackPlaybackScope);
    }

    public /* synthetic */ ContentMetadataPlaybackHandler(Context context, PageType pageType, PlaybackPageType playbackPageType, ExperienceMode experienceMode, CarModeQueueManager carModeQueueManager, CoroutineScope coroutineScope, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? PageType.UNKNOWN : pageType, (i & 4) != 0 ? PlaybackPageType.UNKNOWN : playbackPageType, (i & 8) != 0 ? null : experienceMode, (i & 16) != 0 ? null : carModeQueueManager, (i & 32) != 0 ? CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()) : coroutineScope);
    }

    public static /* synthetic */ void handle$default(ContentMetadataPlaybackHandler contentMetadataPlaybackHandler, ContentMetadata contentMetadata, boolean z, boolean z2, PlaybackListener playbackListener, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        if ((i & 8) != 0) {
            playbackListener = null;
        }
        contentMetadataPlaybackHandler.handle(contentMetadata, z, z2, playbackListener);
    }

    public final void handle(ContentMetadata contentMetadata, boolean eligibilityVerified, boolean isVoiceInitiated, PlaybackListener playbackListener) {
        String currentTrack;
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        if (!(contentMetadata instanceof AlbumMetadata ? true : contentMetadata instanceof PlaylistMetadata)) {
            if (contentMetadata instanceof StationMetadata ? true : contentMetadata instanceof ArtistMetadata ? true : contentMetadata instanceof UserPlaylistMetadata ? true : contentMetadata instanceof TrackMetadata ? true : contentMetadata instanceof PodcastMetadata) {
                CarModeQueueManager carModeQueueManager = this.carModeQueueManager;
                if (carModeQueueManager != null) {
                    carModeQueueManager.setNoCurrentPreset();
                }
                this.playbackHelper.startPlayback(contentMetadata, null, eligibilityVerified, isVoiceInitiated, playbackListener);
                return;
            }
            Log.error(TAG, Intrinsics.stringPlus("Could not start playback for content ", contentMetadata));
            return;
        }
        PlayableEntityIdentifier entityIdentifier = PlaybackHandlerUtil.INSTANCE.getEntityIdentifier(contentMetadata);
        if (entityIdentifier == null) {
            return;
        }
        CarModeQueueManager carModeQueueManager2 = this.carModeQueueManager;
        if (carModeQueueManager2 == null) {
            currentTrack = null;
        } else {
            String identifier = entityIdentifier.getIdentifier();
            Intrinsics.checkNotNullExpressionValue(identifier, "identifier.identifier");
            currentTrack = carModeQueueManager2.getCurrentTrack(identifier);
        }
        String str = currentTrack;
        CarModeQueueManager carModeQueueManager3 = this.carModeQueueManager;
        if (carModeQueueManager3 != null) {
            String identifier2 = entityIdentifier.getIdentifier();
            Intrinsics.checkNotNullExpressionValue(identifier2, "identifier.identifier");
            carModeQueueManager3.setCurrentPreset(identifier2);
        }
        this.playbackHelper.startPlayback(contentMetadata, str, eligibilityVerified, isVoiceInitiated, playbackListener);
    }

    public static /* synthetic */ void handleTrackList$default(ContentMetadataPlaybackHandler contentMetadataPlaybackHandler, List list, boolean z, boolean z2, int i, PlaybackListener playbackListener, int i2, Object obj) {
        boolean z3 = (i2 & 2) != 0 ? false : z;
        boolean z4 = (i2 & 4) != 0 ? false : z2;
        int i3 = (i2 & 8) != 0 ? 0 : i;
        if ((i2 & 16) != 0) {
            playbackListener = null;
        }
        contentMetadataPlaybackHandler.handleTrackList(list, z3, z4, i3, playbackListener);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.auto.playback.ContentMetadataPlaybackHandler$handleTrackList$1, reason: invalid class name */
    /* JADX INFO: compiled from: ContentMetadataPlaybackHandler.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.auto.playback.ContentMetadataPlaybackHandler$handleTrackList$1", f = "ContentMetadataPlaybackHandler.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<ContentMetadata> $contentMetadataList;
        final /* synthetic */ boolean $eligibilityVerified;
        final /* synthetic */ boolean $isVoiceInitiated;
        final /* synthetic */ PlaybackListener $playbackListener;
        final /* synthetic */ int $startingPosition;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(List<? extends ContentMetadata> list, boolean z, boolean z2, PlaybackListener playbackListener, int i, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$contentMetadataList = list;
            this.$eligibilityVerified = z;
            this.$isVoiceInitiated = z2;
            this.$playbackListener = playbackListener;
            this.$startingPosition = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return ContentMetadataPlaybackHandler.this.new AnonymousClass1(this.$contentMetadataList, this.$eligibilityVerified, this.$isVoiceInitiated, this.$playbackListener, this.$startingPosition, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            List listCreateTrackList = ContentMetadataPlaybackHandler.this.createTrackList(this.$contentMetadataList, this.$eligibilityVerified);
            if (listCreateTrackList.isEmpty()) {
                Log.error(ContentMetadataPlaybackHandler.TAG, "No track is available to play");
                return Unit.INSTANCE;
            }
            TracksPlaybackHelper.loadPlayback$default(new TracksPlaybackHelper(ContentMetadataPlaybackHandler.this.context, PrimeCollectionTask.Task.PLAYBACK, ContentMetadataPlaybackHandler.this.playbackPageType, VoiceUtil.INSTANCE.getControlSource(this.$isVoiceInitiated), this.$playbackListener, false, 32, null), listCreateTrackList, this.$startingPosition, null, 4, null);
            return Unit.INSTANCE;
        }
    }

    public final void handleTrackList(List<? extends ContentMetadata> contentMetadataList, boolean eligibilityVerified, boolean isVoiceInitiated, int startingPosition, PlaybackListener playbackListener) {
        Intrinsics.checkNotNullParameter(contentMetadataList, "contentMetadataList");
        BuildersKt__Builders_commonKt.launch$default(this.trackPlaybackScope, null, null, new AnonymousClass1(contentMetadataList, eligibilityVerified, isVoiceInitiated, playbackListener, startingPosition, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<PrimeTrack> createTrackList(List<? extends ContentMetadata> contentMetadataList, boolean eligibilityVerified) {
        ArrayList arrayList = new ArrayList();
        for (ContentMetadata contentMetadata : contentMetadataList) {
            if (arrayList.size() == TracksPlaybackHelper.INSTANCE.getMaxQueueSize()) {
                return arrayList;
            }
            if (contentMetadata instanceof TrackMetadata) {
                TrackMetadata trackMetadata = (TrackMetadata) contentMetadata;
                MusicTrack trackFromAsin = new TrackRequester(this.context).getTrackFromAsin(trackMetadata.getAsin());
                if (trackFromAsin == null) {
                    Log.error(TAG, Intrinsics.stringPlus("Unable to get track from asin ", trackMetadata.getAsin()));
                } else {
                    PrimeTrack trackItem = PrimeItemsTransformationUtil.convertToPrimeTrackByMusicTrack(trackFromAsin);
                    ContentUnavailableReason catalogContentUnavailableReason = ContentAccessUtil.getCatalogContentUnavailableReason(trackItem, ContentAccessUtil.ContentAccessOperation.STREAM);
                    if (eligibilityVerified || catalogContentUnavailableReason == null) {
                        Intrinsics.checkNotNullExpressionValue(trackItem, "trackItem");
                        arrayList.add(trackItem);
                    } else {
                        Log.error(TAG, Intrinsics.stringPlus("User is not eligible to play track ", trackMetadata.getAsin()));
                    }
                }
            } else {
                Log.error(TAG, "Unable to add non track entity");
            }
        }
        return arrayList;
    }
}
