package com.amazon.mp3.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.fragment.navigation.BrushFragmentNavigation;
import com.amazon.mp3.playback.video.VideoUtil;
import com.amazon.mp3.prime.activity.PrimeActivityFactory;
import com.amazon.mp3.prime.browse.PrimeTabFragmentHost;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.util.coroutines.LookupError;
import com.amazon.mp3.video.stories.VideoStoriesUtil;
import com.amazon.music.destination.VideoDestination;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.media.playback.util.AndroidUtils;
import com.amazon.music.router.DestinationHandler;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: VideoDestinationHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 $2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001$B\u001f\b\u0007\u0012\u0014\b\u0002\u0010!\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u00060\u001f¢\u0006\u0004\b\"\u0010#J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0002H\u0002J\u0018\u0010\b\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0002H\u0002J\u001a\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0002H\u0002J\u0018\u0010\f\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0002H\u0016J\u0016\u0010\r\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0002R\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0014\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\"\u0010\u0019\u001a\u00020\u00188\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e¨\u0006%"}, d2 = {"Lcom/amazon/mp3/navigation/VideoDestinationHandler;", "Lcom/amazon/music/router/DestinationHandler;", "Lcom/amazon/music/destination/VideoDestination;", "Landroid/content/Context;", "context", "destination", "", "handleVideoStoryDestination", "startIntentForBrowse", "videoDestination", "Landroid/content/Intent;", "getIntentForVideoDetails", "navigateTo", "getIntentForBrowse", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "coroutineExceptionHandler", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "getCoroutineExceptionHandler", "()Lkotlinx/coroutines/CoroutineExceptionHandler;", "Lkotlinx/coroutines/CoroutineScope;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "getCoroutineScope", "()Lkotlinx/coroutines/CoroutineScope;", "", ParserUtil.TRACK_ASIN_QUERY_PARAM_NAME, "Ljava/lang/String;", "getTrackAsin", "()Ljava/lang/String;", "setTrackAsin", "(Ljava/lang/String;)V", "Lkotlin/Function1;", "Lcom/amazon/mp3/util/coroutines/LookupError;", "onError", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class VideoDestinationHandler implements DestinationHandler<VideoDestination> {
    private static final String TAG = VideoDestinationHandler.class.getSimpleName();
    private final CoroutineExceptionHandler coroutineExceptionHandler;
    private final CoroutineScope coroutineScope;
    private String trackAsin;

    @JvmOverloads
    public VideoDestinationHandler() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @JvmOverloads
    public VideoDestinationHandler(Function1<? super LookupError, Unit> onError) {
        Intrinsics.checkNotNullParameter(onError, "onError");
        VideoDestinationHandler$special$$inlined$CoroutineExceptionHandler$1 videoDestinationHandler$special$$inlined$CoroutineExceptionHandler$1 = new VideoDestinationHandler$special$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.INSTANCE, this, onError);
        this.coroutineExceptionHandler = videoDestinationHandler$special$$inlined$CoroutineExceptionHandler$1;
        this.coroutineScope = CoroutineScopeKt.plus(GlobalScope.INSTANCE, videoDestinationHandler$special$$inlined$CoroutineExceptionHandler$1);
        this.trackAsin = "";
    }

    public /* synthetic */ VideoDestinationHandler(Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new Function1<LookupError, Unit>() { // from class: com.amazon.mp3.navigation.VideoDestinationHandler.1
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LookupError it) {
                Intrinsics.checkNotNullParameter(it, "it");
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(LookupError lookupError) {
                invoke2(lookupError);
                return Unit.INSTANCE;
            }
        } : function1);
    }

    public final String getTrackAsin() {
        return this.trackAsin;
    }

    public final void setTrackAsin(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.trackAsin = str;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.navigation.VideoDestinationHandler$navigateTo$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: VideoDestinationHandler.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.navigation.VideoDestinationHandler$navigateTo$1", f = "VideoDestinationHandler.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03781 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ VideoDestination $destination;
        int label;
        final /* synthetic */ VideoDestinationHandler this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03781(VideoDestination videoDestination, VideoDestinationHandler videoDestinationHandler, Context context, Continuation<? super C03781> continuation) {
            super(2, continuation);
            this.$destination = videoDestination;
            this.this$0 = videoDestinationHandler;
            this.$context = context;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C03781(this.$destination, this.this$0, this.$context, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03781) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            String trackAsin = this.$destination.getTrackAsin();
            if (trackAsin == null || trackAsin.length() == 0) {
                this.$context.startActivity(this.this$0.getIntentForVideoDetails(this.$context, this.$destination));
            } else {
                VideoDestinationHandler videoDestinationHandler = this.this$0;
                String trackAsin2 = this.$destination.getTrackAsin();
                Intrinsics.checkNotNullExpressionValue(trackAsin2, "destination.trackAsin");
                videoDestinationHandler.setTrackAsin(trackAsin2);
                Boolean boolIsVideoStoryDeeplink = this.$destination.isVideoStoryDeeplink();
                Intrinsics.checkNotNullExpressionValue(boolIsVideoStoryDeeplink, "destination.isVideoStoryDeeplink");
                if (boolIsVideoStoryDeeplink.booleanValue()) {
                    this.this$0.handleVideoStoryDestination(this.$context, this.$destination);
                    return Unit.INSTANCE;
                }
                VideoUtil videoUtil = VideoUtil.INSTANCE;
                PrimeTrack primeTrackFetchTrack = videoUtil.fetchTrack(this.$context, this.this$0.getTrackAsin());
                Activity currentActivity = AndroidUtils.getCurrentActivity();
                if (currentActivity == null) {
                    this.this$0.startIntentForBrowse(this.$context, this.$destination);
                } else if (!(currentActivity instanceof DeeplinkHandlerActivity)) {
                    videoUtil.playVideo(primeTrackFetchTrack, this.this$0.getIntentForBrowse(this.$context, this.$destination).getExtras());
                }
            }
            return Unit.INSTANCE;
        }
    }

    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(Context context, VideoDestination destination) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(destination, "destination");
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new C03781(destination, this, context, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleVideoStoryDestination(Context context, VideoDestination destination) {
        Activity currentActivity = AndroidUtils.getCurrentActivity();
        if (currentActivity == null || (currentActivity instanceof DeeplinkHandlerActivity)) {
            startIntentForBrowse(context, destination);
            return;
        }
        if (currentActivity instanceof FragmentActivity) {
            String ref = destination.getRef();
            List<String> listListOf = ref == null ? null : CollectionsKt__CollectionsJVMKt.listOf(ref);
            if (listListOf == null) {
                listListOf = CollectionsKt__CollectionsKt.emptyList();
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            List<String> listListOf2 = CollectionsKt__CollectionsJVMKt.listOf(destination.getTrackAsin());
            String trackAsin = destination.getTrackAsin();
            Intrinsics.checkNotNullExpressionValue(trackAsin, "destination.trackAsin");
            VideoStoriesUtil.INSTANCE.playVideoStories((FragmentActivity) currentActivity, null, listListOf2, trackAsin, listListOf, jCurrentTimeMillis, destination.getRef());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startIntentForBrowse(Context context, VideoDestination destination) {
        context.startActivity(getIntentForBrowse(context, destination));
    }

    public final Intent getIntentForBrowse(Context context, VideoDestination destination) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intent intent = MusicHomeActivity.getStartIntent(context);
        intent.addFlags(268435456);
        intent.setClass(context, MusicHomeActivity.class);
        intent.putExtra("com.amazon.mp3.fragment.extra", PrimeTabFragmentHost.class.getSimpleName());
        intent.putExtra("com.amazon.mp3.library.fragment.EXTRA_DEEPLINK_ACTION", destination.getAction());
        intent.putExtra(DeeplinkIntentKey.KEY_REF_MARKER.toString(), destination.getRef());
        intent.putExtra(DeeplinkIntentKey.KEY_ASSOCIATE_TAG.toString(), destination.getTag());
        Boolean boolIsVideoStoryDeeplink = destination.isVideoStoryDeeplink();
        Intrinsics.checkNotNullExpressionValue(boolIsVideoStoryDeeplink, "destination.isVideoStoryDeeplink");
        if (boolIsVideoStoryDeeplink.booleanValue()) {
            intent.putExtra("com.amazon.mp3.util.videoStoryDeeplink", this.trackAsin);
        } else {
            intent.putExtra("com.amazon.mp3.util.videoDeeplink", this.trackAsin);
        }
        Intrinsics.checkNotNullExpressionValue(intent, "intent");
        return intent;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Intent getIntentForVideoDetails(Context context, VideoDestination videoDestination) {
        Intent intent = PrimeActivityFactory.getHomeIntent(context);
        intent.setFlags(268435456);
        BrushFragmentNavigation.Builder builderWithAssociateTag = new BrushFragmentNavigation.Builder("videos").withTitle("").withAction(videoDestination.getAction()).withRefMarker(videoDestination.getRef()).withAssociateTag(videoDestination.getTag());
        Intrinsics.checkNotNullExpressionValue(intent, "intent");
        builderWithAssociateTag.updateIntent(intent);
        return intent;
    }
}
