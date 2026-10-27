package com.amazon.mp3.video.stories;

import CoreInterface.v1_0.Element;
import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Template;
import ExternalActionInterface.v1_0.VideoStoryContent;
import Touch.LiveStreamPlayerTemplateInterface.v1_0.ImmutableDisplayGenericErrorMethod;
import Touch.PlaybackTemplateInterface.v1_0.PlaybackTemplate;
import Touch.VideoQueueTemplateInterface.v1_0.VideoQueueTemplate;
import Touch.WidgetsInterface.v1_0.TextButtonElement;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.MainThread;
import androidx.autofill.HintConstants;
import androidx.lifecycle.MutableLiveData;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.alexa.mobilytics.util.Log;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.cache.videostory.CachedData;
import com.amazon.mp3.library.cache.videostory.StoryPlaybackCachedData;
import com.amazon.mp3.library.cache.videostory.VideoPlaybackCache;
import com.amazon.mp3.prime.browse.BrowseFactory;
import com.amazon.mp3.video.stories.VideoStoryPlaybackDataRemoteDataSource;
import com.amazon.music.explore.skyfire.SkyFireUtils;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.skyfire.ui.Constants;
import com.amazon.music.views.library.providers.VideoStoryPlaybackDataProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

/* JADX INFO: compiled from: VideoStoryPlaybackDataRepository.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0018\u0018\u0000 M2\u00020\u0001:\u0001MB\u0007¢\u0006\u0004\bK\u0010LJ\u001e\u0010\u0007\u001a\u00020\u00062\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0003H\u0002J\u001c\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u00022\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0002J\u0016\u0010\f\u001a\u00020\u00032\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0002J\u0016\u0010\u000f\u001a\u00020\u00062\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u0002H\u0002J\u0018\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0002J\u0018\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0017H\u0002J\u0016\u0010\u001a\u001a\u00020\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016J\u001c\u0010\u001a\u001a\u00020\u00062\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0003J\u0006\u0010\u001b\u001a\u00020\u0006J\u0006\u0010\u001c\u001a\u00020\u0006J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001d\u001a\u00020\u0003R\u0014\u0010!\u001a\u00020 8\u0002X\u0082D¢\u0006\u0006\n\u0004\b!\u0010\"R\u0014\u0010$\u001a\u00020#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%R\u001c\u0010(\u001a\n '*\u0004\u0018\u00010&0&8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b(\u0010)R\u0018\u0010*\u001a\u0004\u0018\u00010\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b*\u0010+R\u0016\u0010-\u001a\u00020,8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b-\u0010.R\u0016\u0010/\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b/\u00100R#\u00102\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u0002018\u0006¢\u0006\f\n\u0004\b2\u00103\u001a\u0004\b4\u00105R$\u00107\u001a\u0004\u0018\u0001068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b7\u00108\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R$\u0010=\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b=\u0010>\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\"\u0010C\u001a\u00020,8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bC\u0010.\u001a\u0004\bD\u0010E\"\u0004\bF\u0010GR$\u0010H\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bH\u0010>\u001a\u0004\bI\u0010@\"\u0004\bJ\u0010B¨\u0006N"}, d2 = {"Lcom/amazon/mp3/video/stories/VideoStoryPlaybackDataRepository;", "Lcom/amazon/music/views/library/providers/VideoStoryPlaybackDataProvider;", "", "", "storyAsins", "baseUrl", "", "requestVideoStoriesDataAndUpdatePlaybackTemplate", "storyIds", "LCoreInterface/v1_0/Method;", "getSerializedMethods", "metadataList", "getStoryAsinListAsString", "LTouch/PlaybackTemplateInterface/v1_0/PlaybackTemplate;", "videoItems", "mapArtistFollowStates", "Landroid/content/Intent;", MAPAccountManager.KEY_INTENT, "Landroid/os/Bundle;", "extras", "handleToggleArtist", "Landroid/content/BroadcastReceiver;", "receiver", "Landroid/content/IntentFilter;", "intentFilter", "registerLocalBroadcastActivityReceiver", "fetchAndStoreVideoStoriesTemplate", "unregisterToggleArtistForStoriesReceiver", "fetchAndUpdateFollowState", "key", "Lcom/amazon/mp3/library/cache/videostory/CachedData;", "getFromPlaybackCache", "", "DEFAULT_PLAYBACK_CACHE_SIZE", "I", "Lcom/amazon/mp3/library/cache/videostory/VideoPlaybackCache;", "videoPlaybackCache", "Lcom/amazon/mp3/library/cache/videostory/VideoPlaybackCache;", "Landroid/content/Context;", "kotlin.jvm.PlatformType", "context", "Landroid/content/Context;", "toggleArtistFromADPExtras", "Landroid/os/Bundle;", "", "isToggleArtistFromADPReceiverUnregistered", "Z", "toggleArtistFromADPReceiver", "Landroid/content/BroadcastReceiver;", "Landroidx/lifecycle/MutableLiveData;", "playbackTemplates", "Landroidx/lifecycle/MutableLiveData;", "getPlaybackTemplates", "()Landroidx/lifecycle/MutableLiveData;", "Lcom/amazon/mp3/video/stories/VideoStoryPlaybackDataRemoteDataSource;", "videoStoryPlaybackDataRemoteDataSource", "Lcom/amazon/mp3/video/stories/VideoStoryPlaybackDataRemoteDataSource;", "getVideoStoryPlaybackDataRemoteDataSource", "()Lcom/amazon/mp3/video/stories/VideoStoryPlaybackDataRemoteDataSource;", "setVideoStoryPlaybackDataRemoteDataSource", "(Lcom/amazon/mp3/video/stories/VideoStoryPlaybackDataRemoteDataSource;)V", "requestedStoryIds", "Ljava/lang/String;", "getRequestedStoryIds", "()Ljava/lang/String;", "setRequestedStoryIds", "(Ljava/lang/String;)V", "toggleButtonMethodHandled", "getToggleButtonMethodHandled", "()Z", "setToggleButtonMethodHandled", "(Z)V", "asinToBeUpdated", "getAsinToBeUpdated", "setAsinToBeUpdated", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class VideoStoryPlaybackDataRepository implements VideoStoryPlaybackDataProvider {
    private static VideoStoryPlaybackDataRepository sInstance;
    private final int DEFAULT_PLAYBACK_CACHE_SIZE = 20;
    private String asinToBeUpdated;
    private final Context context;
    private boolean isToggleArtistFromADPReceiverUnregistered;
    private final MutableLiveData<List<PlaybackTemplate>> playbackTemplates;
    private String requestedStoryIds;
    private Bundle toggleArtistFromADPExtras;
    private BroadcastReceiver toggleArtistFromADPReceiver;
    private boolean toggleButtonMethodHandled;
    private final VideoPlaybackCache videoPlaybackCache;
    private VideoStoryPlaybackDataRemoteDataSource videoStoryPlaybackDataRemoteDataSource;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = VideoStoryPlaybackDataRepository.class.getSimpleName();
    private static final CoroutineScope videoCacheIOScope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null));

    public VideoStoryPlaybackDataRepository() {
        Integer maxCacheSizeForVideoStories = VideoStoriesUtil.INSTANCE.getMaxCacheSizeForVideoStories();
        this.videoPlaybackCache = new VideoPlaybackCache(maxCacheSizeForVideoStories != null ? maxCacheSizeForVideoStories.intValue() : 20);
        this.context = AmazonApplication.getApplication().getApplicationContext();
        this.isToggleArtistFromADPReceiverUnregistered = true;
        this.toggleArtistFromADPReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.video.stories.VideoStoryPlaybackDataRepository$toggleArtistFromADPReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(intent, "intent");
                this.this$0.toggleArtistFromADPExtras = intent.getExtras();
                Bundle bundle = this.this$0.toggleArtistFromADPExtras;
                if (bundle == null) {
                    return;
                }
                this.this$0.handleToggleArtist(intent, bundle);
            }
        };
        this.playbackTemplates = new MutableLiveData<>();
        this.toggleButtonMethodHandled = true;
    }

    public final MutableLiveData<List<PlaybackTemplate>> getPlaybackTemplates() {
        return this.playbackTemplates;
    }

    public final VideoStoryPlaybackDataRemoteDataSource getVideoStoryPlaybackDataRemoteDataSource() {
        return this.videoStoryPlaybackDataRemoteDataSource;
    }

    public final void setVideoStoryPlaybackDataRemoteDataSource(VideoStoryPlaybackDataRemoteDataSource videoStoryPlaybackDataRemoteDataSource) {
        this.videoStoryPlaybackDataRemoteDataSource = videoStoryPlaybackDataRemoteDataSource;
    }

    public final String getRequestedStoryIds() {
        return this.requestedStoryIds;
    }

    public final boolean getToggleButtonMethodHandled() {
        return this.toggleButtonMethodHandled;
    }

    public final void setToggleButtonMethodHandled(boolean z) {
        this.toggleButtonMethodHandled = z;
    }

    public final void setAsinToBeUpdated(String str) {
        this.asinToBeUpdated = str;
    }

    /* JADX INFO: compiled from: VideoStoryPlaybackDataRepository.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\f\u001a\u00020\u0007H\u0007R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/video/stories/VideoStoryPlaybackDataRepository$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "sInstance", "Lcom/amazon/mp3/video/stories/VideoStoryPlaybackDataRepository;", "videoCacheIOScope", "Lkotlinx/coroutines/CoroutineScope;", "getVideoCacheIOScope", "()Lkotlinx/coroutines/CoroutineScope;", "get", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final CoroutineScope getVideoCacheIOScope() {
            return VideoStoryPlaybackDataRepository.videoCacheIOScope;
        }

        @MainThread
        public final VideoStoryPlaybackDataRepository get() {
            VideoStoryPlaybackDataRepository videoStoryPlaybackDataRepository;
            if (VideoStoryPlaybackDataRepository.sInstance != null) {
                videoStoryPlaybackDataRepository = VideoStoryPlaybackDataRepository.sInstance;
                if (videoStoryPlaybackDataRepository == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sInstance");
                    videoStoryPlaybackDataRepository = null;
                }
            } else {
                videoStoryPlaybackDataRepository = new VideoStoryPlaybackDataRepository();
            }
            VideoStoryPlaybackDataRepository.sInstance = videoStoryPlaybackDataRepository;
            VideoStoryPlaybackDataRepository videoStoryPlaybackDataRepository2 = VideoStoryPlaybackDataRepository.sInstance;
            if (videoStoryPlaybackDataRepository2 != null) {
                return videoStoryPlaybackDataRepository2;
            }
            Intrinsics.throwUninitializedPropertyAccessException("sInstance");
            return null;
        }
    }

    @Override // com.amazon.music.views.library.providers.VideoStoryPlaybackDataProvider
    public void fetchAndStoreVideoStoriesTemplate(List<String> storyIds) {
        Intrinsics.checkNotNullParameter(storyIds, "storyIds");
        String skyfire_base_url = VideoStoriesUtil.INSTANCE.getSKYFIRE_BASE_URL();
        Intrinsics.checkNotNullExpressionValue(skyfire_base_url, "VideoStoriesUtil.SKYFIRE_BASE_URL");
        fetchAndStoreVideoStoriesTemplate(storyIds, skyfire_base_url);
    }

    public final void fetchAndStoreVideoStoriesTemplate(List<String> storyAsins, String baseUrl) {
        Intrinsics.checkNotNullParameter(storyAsins, "storyAsins");
        Intrinsics.checkNotNullParameter(baseUrl, "baseUrl");
        registerLocalBroadcastActivityReceiver(this.toggleArtistFromADPReceiver, new IntentFilter(Constants.INSTANCE.getTOGGLE_ARTIST_INTENT()));
        if (StringsKt__StringsJVMKt.equals$default(this.requestedStoryIds, CollectionsKt___CollectionsKt.joinToString$default(storyAsins, null, null, null, 0, null, null, 63, null), false, 2, null)) {
            return;
        }
        VideoStoryPlaybackDataRemoteDataSource videoStoryPlaybackDataRemoteDataSource = this.videoStoryPlaybackDataRemoteDataSource;
        if (videoStoryPlaybackDataRemoteDataSource != null) {
            if (videoStoryPlaybackDataRemoteDataSource != null) {
                videoStoryPlaybackDataRemoteDataSource.removeCallback();
            }
            this.videoStoryPlaybackDataRemoteDataSource = null;
        }
        this.requestedStoryIds = CollectionsKt___CollectionsKt.joinToString$default(storyAsins, null, null, null, 0, null, null, 63, null);
        BuildersKt__Builders_commonKt.launch$default(videoCacheIOScope, Dispatchers.getIO(), null, new AnonymousClass1(storyAsins, baseUrl, null), 2, null);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.video.stories.VideoStoryPlaybackDataRepository$fetchAndStoreVideoStoriesTemplate$1, reason: invalid class name */
    /* JADX INFO: compiled from: VideoStoryPlaybackDataRepository.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.video.stories.VideoStoryPlaybackDataRepository$fetchAndStoreVideoStoriesTemplate$1", f = "VideoStoryPlaybackDataRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $baseUrl;
        final /* synthetic */ List<String> $storyAsins;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(List<String> list, String str, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$storyAsins = list;
            this.$baseUrl = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return VideoStoryPlaybackDataRepository.this.new AnonymousClass1(this.$storyAsins, this.$baseUrl, continuation);
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
            VideoStoryPlaybackDataRepository.this.videoPlaybackCache.removeExpiredKeys();
            if (VideoStoryPlaybackDataRepository.this.videoPlaybackCache.get(VideoStoriesUtil.INSTANCE.getPlaybackCacheKeyFromAsinListAndBaseURL(this.$storyAsins, this.$baseUrl)) == null) {
                VideoStoryPlaybackDataRepository.this.requestVideoStoriesDataAndUpdatePlaybackTemplate(this.$storyAsins, this.$baseUrl);
                return Unit.INSTANCE;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void requestVideoStoriesDataAndUpdatePlaybackTemplate(final List<String> storyAsins, final String baseUrl) {
        Log.d(TAG, "Making API call to prefetch stories data, and cache them");
        Context applicationContext = AmazonApplication.getApplication().getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplication().applicationContext");
        VideoStoryPlaybackDataRemoteDataSource videoStoryPlaybackDataRemoteDataSource = new VideoStoryPlaybackDataRemoteDataSource(applicationContext);
        this.videoStoryPlaybackDataRemoteDataSource = videoStoryPlaybackDataRemoteDataSource;
        videoStoryPlaybackDataRemoteDataSource.setCallback(new VideoStoryPlaybackDataRemoteDataSource.Callback() { // from class: com.amazon.mp3.video.stories.VideoStoryPlaybackDataRepository.requestVideoStoriesDataAndUpdatePlaybackTemplate.2
            @Override // com.amazon.mp3.video.stories.VideoStoryPlaybackDataRemoteDataSource.Callback
            public void onDataLoaded(Template template) {
                Intrinsics.checkNotNullParameter(template, "template");
                StoryPlaybackCachedData storyPlaybackCachedData = new StoryPlaybackCachedData();
                VideoQueueTemplate videoQueueTemplate = (VideoQueueTemplate) template;
                storyPlaybackCachedData.setTemplate(videoQueueTemplate);
                VideoStoryPlaybackDataRepository.this.getPlaybackTemplates().setValue(videoQueueTemplate.videoQueue().videoItems());
                BuildersKt__Builders_commonKt.launch$default(VideoStoryPlaybackDataRepository.INSTANCE.getVideoCacheIOScope(), Dispatchers.getIO(), null, new VideoStoryPlaybackDataRepository$requestVideoStoriesDataAndUpdatePlaybackTemplate$2$onDataLoaded$1(VideoStoryPlaybackDataRepository.this, storyPlaybackCachedData, storyAsins, baseUrl, null), 2, null);
                Template template2 = storyPlaybackCachedData.getTemplate();
                if (template2 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type Touch.VideoQueueTemplateInterface.v1_0.VideoQueueTemplate");
                }
                List<PlaybackTemplate> listVideoItems = ((VideoQueueTemplate) template2).videoQueue().videoItems();
                if (listVideoItems != null) {
                    VideoStoryPlaybackDataRepository.this.mapArtistFollowStates(listVideoItems);
                }
                VideoStoryPlaybackDataRemoteDataSource videoStoryPlaybackDataRemoteDataSource2 = VideoStoryPlaybackDataRepository.this.getVideoStoryPlaybackDataRemoteDataSource();
                if (videoStoryPlaybackDataRemoteDataSource2 != null) {
                    videoStoryPlaybackDataRemoteDataSource2.removeCallback();
                }
                VideoStoryPlaybackDataRepository.this.setVideoStoryPlaybackDataRemoteDataSource(null);
            }
        });
        VideoStoryPlaybackDataRemoteDataSource videoStoryPlaybackDataRemoteDataSource2 = this.videoStoryPlaybackDataRemoteDataSource;
        if (videoStoryPlaybackDataRemoteDataSource2 == null) {
            return;
        }
        videoStoryPlaybackDataRemoteDataSource2.dispatchMethods(getSerializedMethods(storyAsins));
    }

    private final List<Method> getSerializedMethods(List<String> storyIds) {
        List<Method> list;
        Object value;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format("/showVideoStory", Arrays.copyOf(new Object[0], 0));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        String str2 = BrowseFactory.isTabletDevice() ? "tablet" : HintConstants.AUTOFILL_HINT_PHONE;
        HashMap map = new HashMap();
        map.put("ids", getStoryAsinListAsString(storyIds));
        map.put(Splash.PARAMS_DEVICE_TYPE, str2);
        ArrayList arrayList = new ArrayList();
        ImmutableDisplayGenericErrorMethod immutableDisplayGenericErrorMethodBuild = ImmutableDisplayGenericErrorMethod.builder().queue(SkyFireUtils.stHttpQueue()).build();
        Intrinsics.checkNotNullExpressionValue(immutableDisplayGenericErrorMethodBuild, "builder()\n              …                 .build()");
        arrayList.add(immutableDisplayGenericErrorMethodBuild);
        ArrayList arrayList2 = new ArrayList();
        Method invokeHttpSkillMethod = SkyFireUtils.getInvokeHttpSkillMethod(str, map, arrayList);
        Intrinsics.checkNotNullExpressionValue(invokeHttpSkillMethod, "getInvokeHttpSkillMethod…th, queryParams, onError)");
        arrayList2.add(invokeHttpSkillMethod);
        try {
            value = com.amazon.music.explore.Mappers.mapper().readValue(SkyFireUtils.getSerializedMethods(arrayList2), com.amazon.music.explore.Mappers.mapper().getTypeFactory().constructCollectionType(List.class, Method.class));
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        }
        if (value == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.List<*>");
        }
        list = (List) value;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException("initialMethods");
        return null;
    }

    private final String getStoryAsinListAsString(List<String> metadataList) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(metadataList);
        String strJoin = TextUtils.join(",", arrayList);
        Intrinsics.checkNotNullExpressionValue(strJoin, "join(VideoStoryPlaybackD….SEPARATOR, storiesAsins)");
        return strJoin;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void mapArtistFollowStates(List<? extends PlaybackTemplate> videoItems) {
        VideoStoryContent videoStoryContent;
        String strArtistAsin;
        for (PlaybackTemplate playbackTemplate : videoItems) {
            for (Element element : playbackTemplate.secondaryButtons()) {
                if ((element instanceof TextButtonElement) && (videoStoryContent = (VideoStoryContent) playbackTemplate.playableContent()) != null && (strArtistAsin = videoStoryContent.artistAsin()) != null) {
                    VideoStoryArtistMapProvider.INSTANCE.setArtistFollowState(strArtistAsin, ((TextButtonElement) element).isActive());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleToggleArtist(Intent intent, Bundle extras) {
        String stringExtra;
        Constants constants = Constants.INSTANCE;
        if (extras.containsKey(constants.getWIDGET_ID_KEY()) && extras.containsKey(constants.getIS_ACTIVE_KEY()) && (stringExtra = intent.getStringExtra(constants.getWIDGET_ID_KEY())) != null) {
            VideoStoryArtistMapProvider videoStoryArtistMapProvider = VideoStoryArtistMapProvider.INSTANCE;
            videoStoryArtistMapProvider.setArtistFollowState(videoStoryArtistMapProvider.getAsinFromWidgetId(stringExtra), intent.getBooleanExtra(constants.getIS_ACTIVE_KEY(), false));
        }
    }

    private final void registerLocalBroadcastActivityReceiver(BroadcastReceiver receiver, IntentFilter intentFilter) {
        if (this.isToggleArtistFromADPReceiverUnregistered) {
            LocalBroadcastManager.getInstance(this.context).registerReceiver(receiver, intentFilter);
            this.isToggleArtistFromADPReceiverUnregistered = false;
        }
    }

    public final void unregisterToggleArtistForStoriesReceiver() {
        if (this.isToggleArtistFromADPReceiverUnregistered) {
            return;
        }
        LocalBroadcastManager.getInstance(this.context).unregisterReceiver(this.toggleArtistFromADPReceiver);
        this.isToggleArtistFromADPReceiverUnregistered = true;
    }

    public final void fetchAndUpdateFollowState() {
        VideoStoryPlaybackDataRemoteDataSource videoStoryPlaybackDataRemoteDataSource;
        Context applicationContext = AmazonApplication.getApplication().getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplication().applicationContext");
        VideoStoryPlaybackDataRemoteDataSource videoStoryPlaybackDataRemoteDataSource2 = new VideoStoryPlaybackDataRemoteDataSource(applicationContext);
        this.videoStoryPlaybackDataRemoteDataSource = videoStoryPlaybackDataRemoteDataSource2;
        videoStoryPlaybackDataRemoteDataSource2.setCallback(new VideoStoryPlaybackDataRemoteDataSource.Callback() { // from class: com.amazon.mp3.video.stories.VideoStoryPlaybackDataRepository.fetchAndUpdateFollowState.1
            @Override // com.amazon.mp3.video.stories.VideoStoryPlaybackDataRemoteDataSource.Callback
            public void onDataLoaded(Template template) {
                Intrinsics.checkNotNullParameter(template, "template");
                List<PlaybackTemplate> newPlaybackTemplate = ((VideoQueueTemplate) template).videoQueue().videoItems();
                VideoStoryPlaybackDataRemoteDataSource videoStoryPlaybackDataRemoteDataSource3 = VideoStoryPlaybackDataRepository.this.getVideoStoryPlaybackDataRemoteDataSource();
                if (videoStoryPlaybackDataRemoteDataSource3 != null) {
                    videoStoryPlaybackDataRemoteDataSource3.removeCallback();
                }
                VideoStoryPlaybackDataRepository.this.setVideoStoryPlaybackDataRemoteDataSource(null);
                VideoStoryPlaybackDataRepository videoStoryPlaybackDataRepository = VideoStoryPlaybackDataRepository.this;
                Intrinsics.checkNotNullExpressionValue(newPlaybackTemplate, "newPlaybackTemplate");
                videoStoryPlaybackDataRepository.mapArtistFollowStates(newPlaybackTemplate);
                VideoStoryPlaybackDataRepository.this.setToggleButtonMethodHandled(true);
            }
        });
        String str = this.asinToBeUpdated;
        if (str == null || (videoStoryPlaybackDataRemoteDataSource = getVideoStoryPlaybackDataRemoteDataSource()) == null) {
            return;
        }
        videoStoryPlaybackDataRemoteDataSource.dispatchMethods(getSerializedMethods(CollectionsKt__CollectionsJVMKt.listOf(str)));
    }

    public final CachedData getFromPlaybackCache(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.videoPlaybackCache.get(key);
    }
}
