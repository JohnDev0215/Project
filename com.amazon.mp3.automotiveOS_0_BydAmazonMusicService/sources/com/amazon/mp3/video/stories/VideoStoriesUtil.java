package com.amazon.mp3.video.stories;

import android.content.Context;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.library.adapter.DefaultTrackStateProvider;
import com.amazon.mp3.library.item.CatalogContent;
import com.amazon.mp3.library.provider.StateProvider;
import com.amazon.mp3.playback.activity.StreamingNetworkPreferenceActivity;
import com.amazon.mp3.playback.service.exception.PlaybackErrorHandler;
import com.amazon.mp3.playback.video.VideoUtil;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.prime.PrimePlaylistTracksTable;
import com.amazon.mp3.prime.browse.metadata.CatalogStatusTiers;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.video.stories.VideoStoriesUtil;
import com.amazon.mp3.video.stories.VideoStoryAutoplayCoordinator;
import com.amazon.mp3.video.stories.VideoStoryFragment;
import com.amazon.music.ContentAccessType;
import com.amazon.music.explore.ExploreEndpointFactory;
import com.amazon.music.featuregating.configuration.Configuration;
import com.amazon.music.media.playback.PlaybackException;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.ConfigurationProvider;
import com.amazon.music.skyfire.ui.fragment.TemplateFragment;
import com.amazon.music.skyfire.ui.skyfire.ServiceStage;
import com.amazon.music.views.library.metadata.VideoStoryMetadata;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.google.gson.Gson;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.serialization.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: VideoStoriesUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001DB\t\b\u0002¢\u0006\u0004\bB\u0010CJP\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\t\u001a\u00020\u00072\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\f\u001a\u00020\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0007H\u0002J\u0010\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\n\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0002J\u0012\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0002J\u0018\u0010\u001b\u001a\u00020\u001a2\u000e\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017H\u0002J\u0018\u0010\u001c\u001a\u00020\u001a2\u000e\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017H\u0002J\u0018\u0010\u001d\u001a\u00020\u001a2\u000e\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017H\u0002J\u001e\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u000e\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0006H\u0002J\u001c\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00130\u0006H\u0002J \u0010\"\u001a\u0004\u0018\u00010\u00132\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00130\u00062\u0006\u0010\t\u001a\u00020\u0007H\u0002J0\u0010$\u001a\u00020\u000e2\u0006\u0010#\u001a\u00020\u00022\u000e\u0010!\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00062\b\u0010\t\u001a\u0004\u0018\u00010\u00072\u0006\u0010\f\u001a\u00020\u000bJP\u0010$\u001a\u00020\u000e2\u0006\u0010#\u001a\u00020\u00022\b\u0010%\u001a\u0004\u0018\u00010\u00152\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\t\u001a\u00020\u00072\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\f\u001a\u00020\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0007J\u0016\u0010'\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010&\u001a\u00020\u001aJ\u0014\u0010)\u001a\u00020\u00072\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006J\u001c\u0010+\u001a\u00020\u00072\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010*\u001a\u00020\u0007J\u000e\u0010,\u001a\u00020\u001a2\u0006\u0010\u0005\u001a\u00020\u0004J\u0016\u0010-\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000f\u0010/\u001a\u0004\u0018\u00010.¢\u0006\u0004\b/\u00100J\u000f\u00101\u001a\u0004\u0018\u00010.¢\u0006\u0004\b1\u00100R\u001c\u00104\u001a\n 3*\u0004\u0018\u000102028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b4\u00105R\u001c\u00106\u001a\n 3*\u0004\u0018\u00010\u00070\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b6\u00107R\u001d\u0010<\u001a\u0004\u0018\u00010\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b8\u00109\u001a\u0004\b:\u0010;R\u0016\u0010=\u001a\u00020\u001a8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b=\u0010>R\u001f\u0010?\u001a\n 3*\u0004\u0018\u00010\u00070\u00078\u0006¢\u0006\f\n\u0004\b?\u00107\u001a\u0004\b@\u0010A¨\u0006E"}, d2 = {"Lcom/amazon/mp3/video/stories/VideoStoriesUtil;", "", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroid/content/Context;", "context", "", "", "storiesList", "selectedAsin", "blockRefsList", "", "storyClickTime", "associateTag", "", "openVideoStoriesPage", "refreshTemplateFragmentIfPresent", "Lcom/amazon/mp3/video/stories/VideoStoriesUtil$VideosCacheConfigMetadata;", "getVideoCacheMetadata", "Lcom/amazon/music/views/library/metadata/VideoStoryMetadata;", "item", "Lcom/amazon/mp3/prime/browse/metadata/PrimeTrack;", "createPrimeTrack", "", "Lcom/amazon/music/ContentAccessType;", "supportedTiers", "", "isFree", "isPrime", "isHawkFire", "metadataList", "getStoriesAsins", "getBlockRefs", "storiesMetadata", "getStoryMetaData", "fragmentActivity", "playVideoStories", "primeTrack", "isSwipeDownAction", "closeVideoStoriesPage", "asins", "getPlaybackCacheKeyFromAsinList", "baseUrl", "getPlaybackCacheKeyFromAsinListAndBaseURL", "shouldBlockPlayingVideoStories", "displayWifiErrorDialog", "", "getMaxCacheSizeForVideoStories", "()Ljava/lang/Integer;", "getTTlForVideoStoriesCacheEntry", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "logger", "Lorg/slf4j/Logger;", "TAG", "Ljava/lang/String;", "videosCacheConfigMetadata$delegate", "Lkotlin/Lazy;", "getVideosCacheConfigMetadata", "()Lcom/amazon/mp3/video/stories/VideoStoriesUtil$VideosCacheConfigMetadata;", "videosCacheConfigMetadata", "storiesFragmentStartedToLoad", "Z", "SKYFIRE_BASE_URL", "getSKYFIRE_BASE_URL", "()Ljava/lang/String;", "<init>", "()V", "VideosCacheConfigMetadata", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class VideoStoriesUtil {
    public static final VideoStoriesUtil INSTANCE;
    private static final String SKYFIRE_BASE_URL;
    private static final String TAG;
    private static final Logger logger;
    private static boolean storiesFragmentStartedToLoad;

    /* JADX INFO: renamed from: videosCacheConfigMetadata$delegate, reason: from kotlin metadata */
    private static final Lazy videosCacheConfigMetadata;

    private VideoStoriesUtil() {
    }

    static {
        VideoStoriesUtil videoStoriesUtil = new VideoStoriesUtil();
        INSTANCE = videoStoriesUtil;
        logger = LoggerFactory.getLogger("VideoStoriesUtil");
        TAG = videoStoriesUtil.getClass().getSimpleName();
        videosCacheConfigMetadata = LazyKt__LazyJVMKt.lazy(new Function0<VideosCacheConfigMetadata>() { // from class: com.amazon.mp3.video.stories.VideoStoriesUtil$videosCacheConfigMetadata$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final VideoStoriesUtil.VideosCacheConfigMetadata invoke() {
                return VideoStoriesUtil.INSTANCE.getVideoCacheMetadata();
            }
        });
        SKYFIRE_BASE_URL = new URL(ExploreEndpointFactory.getExploreEndpoint(ServiceStage.PROD)).getHost();
    }

    private final VideosCacheConfigMetadata getVideosCacheConfigMetadata() {
        return (VideosCacheConfigMetadata) videosCacheConfigMetadata.getValue();
    }

    public final String getSKYFIRE_BASE_URL() {
        return SKYFIRE_BASE_URL;
    }

    public final void playVideoStories(FragmentActivity fragmentActivity, List<VideoStoryMetadata> storiesMetadata, String selectedAsin, long storyClickTime) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "fragmentActivity");
        if (storiesMetadata == null || selectedAsin == null) {
            logger.error("story asin is null");
            return;
        }
        VideoStoryMetadata storyMetaData = getStoryMetaData(storiesMetadata, selectedAsin);
        if (storyMetaData == null) {
            return;
        }
        playVideoStories$default(this, fragmentActivity, createPrimeTrack(storyMetaData), getStoriesAsins(storiesMetadata), selectedAsin, getBlockRefs(storiesMetadata), storyClickTime, null, 64, null);
    }

    public static /* synthetic */ void playVideoStories$default(VideoStoriesUtil videoStoriesUtil, FragmentActivity fragmentActivity, PrimeTrack primeTrack, List list, String str, List list2, long j, String str2, int i, Object obj) {
        videoStoriesUtil.playVideoStories(fragmentActivity, primeTrack, list, str, list2, j, (i & 64) != 0 ? null : str2);
    }

    public final void playVideoStories(final FragmentActivity fragmentActivity, PrimeTrack primeTrack, final List<String> storiesList, final String selectedAsin, final List<String> blockRefsList, final long storyClickTime, final String associateTag) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "fragmentActivity");
        Intrinsics.checkNotNullParameter(storiesList, "storiesList");
        Intrinsics.checkNotNullParameter(selectedAsin, "selectedAsin");
        Intrinsics.checkNotNullParameter(blockRefsList, "blockRefsList");
        if (CastingUtil.isCasting()) {
            CastingUtil.showCastingContentUnavailable(fragmentActivity);
            return;
        }
        final PrimeTrack primeTrackCreatePrimeTrack = primeTrack == null ? createPrimeTrack(null) : primeTrack;
        DefaultTrackStateProvider defaultTrackStateProvider = new DefaultTrackStateProvider(fragmentActivity);
        final ContentAccessUtil.CatalogActionListener catalogActionListener = new ContentAccessUtil.CatalogActionListener() { // from class: com.amazon.mp3.video.stories.VideoStoriesUtil$$ExternalSyntheticLambda0
            @Override // com.amazon.mp3.util.ContentAccessUtil.CatalogActionListener
            public final void continueCatalogAction(CatalogContent catalogContent, boolean z) throws InterruptedException {
                VideoStoriesUtil.m1668playVideoStories$lambda0(fragmentActivity, storiesList, selectedAsin, blockRefsList, storyClickTime, associateTag, (PrimeTrack) catalogContent, z);
            }
        };
        defaultTrackStateProvider.requestState(primeTrackCreatePrimeTrack, new StateProvider.Listener() { // from class: com.amazon.mp3.video.stories.VideoStoriesUtil$$ExternalSyntheticLambda1
            @Override // com.amazon.mp3.library.provider.StateProvider.Listener
            public final void onStateChanged(Object obj, int i, int i2) {
                VideoStoriesUtil.m1669playVideoStories$lambda1(fragmentActivity, primeTrackCreatePrimeTrack, catalogActionListener, (PrimeTrack) obj, i, i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: playVideoStories$lambda-0, reason: not valid java name */
    public static final void m1668playVideoStories$lambda0(FragmentActivity fragmentActivity, List storiesList, String selectedAsin, List blockRefsList, long j, String str, PrimeTrack noName_0, boolean z) throws InterruptedException {
        Intrinsics.checkNotNullParameter(fragmentActivity, "$fragmentActivity");
        Intrinsics.checkNotNullParameter(storiesList, "$storiesList");
        Intrinsics.checkNotNullParameter(selectedAsin, "$selectedAsin");
        Intrinsics.checkNotNullParameter(blockRefsList, "$blockRefsList");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        if (z) {
            VideoStoriesUtil videoStoriesUtil = INSTANCE;
            Context applicationContext = fragmentActivity.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "fragmentActivity.applicationContext");
            videoStoriesUtil.openVideoStoriesPage(fragmentActivity, applicationContext, storiesList, selectedAsin, blockRefsList, j, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: playVideoStories$lambda-1, reason: not valid java name */
    public static final void m1669playVideoStories$lambda1(FragmentActivity fragmentActivity, PrimeTrack track, ContentAccessUtil.CatalogActionListener catalogActionListener, PrimeTrack primeTrack, int i, int i2) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "$fragmentActivity");
        Intrinsics.checkNotNullParameter(track, "$track");
        Intrinsics.checkNotNullParameter(catalogActionListener, "$catalogActionListener");
        ContentAccessUtil.checkCanContinueCatalogAction(fragmentActivity, track, ContentAccessUtil.ContentAccessOperation.STREAM, catalogActionListener);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.video.stories.VideoStoriesUtil$openVideoStoriesPage$1, reason: invalid class name */
    /* JADX INFO: compiled from: VideoStoriesUtil.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.video.stories.VideoStoriesUtil$openVideoStoriesPage$1", f = "VideoStoriesUtil.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ FragmentActivity $activity;
        final /* synthetic */ String $associateTag;
        final /* synthetic */ List<String> $blockRefsList;
        final /* synthetic */ Context $context;
        final /* synthetic */ String $selectedAsin;
        final /* synthetic */ List<String> $storiesList;
        final /* synthetic */ long $storyClickTime;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Context context, FragmentActivity fragmentActivity, List<String> list, List<String> list2, String str, long j, String str2, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$context = context;
            this.$activity = fragmentActivity;
            this.$blockRefsList = list;
            this.$storiesList = list2;
            this.$selectedAsin = str;
            this.$storyClickTime = j;
            this.$associateTag = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$context, this.$activity, this.$blockRefsList, this.$storiesList, this.$selectedAsin, this.$storyClickTime, this.$associateTag, continuation);
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
            VideoStoriesUtil videoStoriesUtil = VideoStoriesUtil.INSTANCE;
            if (!videoStoriesUtil.shouldBlockPlayingVideoStories(this.$context)) {
                if (!VideoStoriesUtil.storiesFragmentStartedToLoad) {
                    VideoStoriesUtil.storiesFragmentStartedToLoad = true;
                    String blockRefs = TextUtils.join(",", this.$blockRefsList);
                    FragmentTransaction fragmentTransactionBeginTransaction = this.$activity.getSupportFragmentManager().beginTransaction();
                    Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "activity.supportFragmentManager.beginTransaction()");
                    VideoStoryFragment.Companion companion = VideoStoryFragment.INSTANCE;
                    List<String> list = this.$storiesList;
                    Intrinsics.checkNotNullExpressionValue(blockRefs, "blockRefs");
                    VideoStoryFragment videoStoryFragmentNewInstance = companion.newInstance(list, blockRefs, this.$selectedAsin, Boxing.boxLong(this.$storyClickTime), this.$associateTag);
                    fragmentTransactionBeginTransaction.setCustomAnimations(R.anim.zoom_in_open, R.anim.blank_anim);
                    fragmentTransactionBeginTransaction.add(R.id.home_menu_drawer, videoStoryFragmentNewInstance, "video_story_fragment").commitAllowingStateLoss();
                }
            } else {
                videoStoriesUtil.displayWifiErrorDialog(this.$context, this.$activity);
            }
            VideoStoryPlaybackProvider.INSTANCE.getVideoStoryAutoPlayCoordinator().pauseAutoPlay(VideoStoryAutoplayCoordinator.AutoplayStateChangeReason.PLAYBACK_STARTED);
            return Unit.INSTANCE;
        }
    }

    private final void openVideoStoriesPage(FragmentActivity activity, Context context, List<String> storiesList, String selectedAsin, List<String> blockRefsList, long storyClickTime, String associateTag) throws InterruptedException {
        BuildersKt__BuildersKt.runBlocking$default(null, new AnonymousClass1(context, activity, blockRefsList, storiesList, selectedAsin, storyClickTime, associateTag, null), 1, null);
    }

    public final void closeVideoStoriesPage(FragmentActivity activity, boolean isSwipeDownAction) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Fragment fragmentFindFragmentByTag = activity.getSupportFragmentManager().findFragmentByTag("video_story_fragment");
        FragmentTransaction fragmentTransactionBeginTransaction = activity.getSupportFragmentManager().beginTransaction();
        Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "activity.supportFragmentManager.beginTransaction()");
        if (fragmentFindFragmentByTag != null) {
            if (!isSwipeDownAction) {
                fragmentTransactionBeginTransaction.setCustomAnimations(R.anim.blank_anim, R.anim.zoom_out_close);
            }
            fragmentTransactionBeginTransaction.remove(fragmentFindFragmentByTag).commitNowAllowingStateLoss();
        }
        refreshTemplateFragmentIfPresent(activity);
        storiesFragmentStartedToLoad = false;
        if (activity.isChangingConfigurations()) {
            return;
        }
        VideoStoryPlaybackProvider.INSTANCE.getVideoStoryAutoPlayCoordinator().resumeAutoPlay(VideoStoryAutoplayCoordinator.AutoplayStateChangeReason.PLAYBACK_ENDED);
    }

    private final void refreshTemplateFragmentIfPresent(FragmentActivity activity) {
        List<Fragment> fragments = activity.getSupportFragmentManager().getFragments();
        Intrinsics.checkNotNullExpressionValue(fragments, "activity\n            .su…er\n            .fragments");
        Fragment fragment = (Fragment) CollectionsKt___CollectionsKt.firstOrNull((List) fragments);
        if (fragment instanceof TemplateFragment) {
            ((TemplateFragment) fragment).refresh();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final VideosCacheConfigMetadata getVideoCacheMetadata() {
        Configuration configuration = (Configuration) Providers.INSTANCE.get(ConfigurationProvider.class);
        Object objFromJson = null;
        JsonObject jsonObject = configuration == null ? null : (JsonObject) Configuration.DefaultImpls.value$default(configuration, "videos_cache_config", Reflection.getOrCreateKotlinClass(JsonObject.class), null, 4, null);
        if (jsonObject != null) {
            try {
                objFromJson = new Gson().fromJson(jsonObject.toString(), (Class<Object>) VideosCacheConfigMetadata.class);
            } catch (Exception e) {
                logger.error("Unable to map Video stories playback cache metadata for cacheConfigData: " + jsonObject + ", exception: " + ExceptionsKt__ExceptionsKt.stackTraceToString(e));
            }
        }
        return (VideosCacheConfigMetadata) objFromJson;
    }

    private final PrimeTrack createPrimeTrack(VideoStoryMetadata item) {
        String title;
        String asin;
        if (item == null || (title = item.getTitle()) == null) {
            title = "";
        }
        if (item == null || (asin = item.getAsin()) == null) {
            asin = "";
        }
        PrimeTrack primeTrack = new PrimeTrack(title, asin, "", "", "", "", "", "", true, ContentOwnershipStatus.UNKNOWN, new CatalogStatusTiers(isPrime(item == null ? null : item.getSupportedTiers()), isHawkFire(item == null ? null : item.getSupportedTiers()), isFree(item != null ? item.getSupportedTiers() : null), false, false, false, false, 112, null));
        primeTrack.setIsExplicit(true);
        primeTrack.setAssetType(PrimePlaylistTracksTable.AssetType.VIDEO);
        return primeTrack;
    }

    private final boolean isFree(Set<? extends ContentAccessType> supportedTiers) {
        if (supportedTiers == null) {
            return false;
        }
        return supportedTiers.contains(ContentAccessType.NIGHTWING);
    }

    private final boolean isPrime(Set<? extends ContentAccessType> supportedTiers) {
        if (supportedTiers == null) {
            return false;
        }
        return supportedTiers.contains(ContentAccessType.PRIME);
    }

    private final boolean isHawkFire(Set<? extends ContentAccessType> supportedTiers) {
        if (supportedTiers == null) {
            return true;
        }
        return supportedTiers.contains(ContentAccessType.HAWKFIRE);
    }

    private final List<String> getStoriesAsins(List<VideoStoryMetadata> metadataList) {
        ArrayList arrayList = new ArrayList();
        if (metadataList != null) {
            Iterator<VideoStoryMetadata> it = metadataList.iterator();
            while (it.hasNext()) {
                String asin = it.next().getAsin();
                if (asin == null) {
                    asin = "";
                }
                arrayList.add(asin);
            }
        }
        return arrayList;
    }

    private final List<String> getBlockRefs(List<VideoStoryMetadata> metadataList) {
        ArrayList arrayList = new ArrayList();
        Iterator<VideoStoryMetadata> it = metadataList.iterator();
        while (it.hasNext()) {
            String blockRef = it.next().getBlockRef();
            if (blockRef == null) {
                blockRef = "";
            }
            arrayList.add(blockRef);
        }
        return arrayList;
    }

    private final VideoStoryMetadata getStoryMetaData(List<VideoStoryMetadata> storiesMetadata, String selectedAsin) {
        Iterator<VideoStoryMetadata> it = storiesMetadata.iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2 = i + 1;
            if (Intrinsics.areEqual(it.next().getAsin(), selectedAsin)) {
                return storiesMetadata.get(i);
            }
            i = i2;
        }
        return null;
    }

    public final String getPlaybackCacheKeyFromAsinList(List<String> asins) {
        Intrinsics.checkNotNullParameter(asins, "asins");
        return SKYFIRE_BASE_URL + '_' + CollectionsKt___CollectionsKt.joinToString$default(asins, Attributes.PREDEFINED_ATTRIBUTE_PREFIX, null, null, 0, null, null, 62, null);
    }

    public final String getPlaybackCacheKeyFromAsinListAndBaseURL(List<String> asins, String baseUrl) {
        Intrinsics.checkNotNullParameter(asins, "asins");
        Intrinsics.checkNotNullParameter(baseUrl, "baseUrl");
        return baseUrl + '_' + CollectionsKt___CollectionsKt.joinToString$default(asins, Attributes.PREDEFINED_ATTRIBUTE_PREFIX, null, null, 0, null, null, 62, null);
    }

    public final boolean shouldBlockPlayingVideoStories(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return VideoUtil.INSTANCE.isConnectivityOtherThanWifi(context) && Intrinsics.areEqual(SettingsUtil.getStreamingNetworkPreference(context, StreamingNetworkPreferenceActivity.Type.STREAMING_NETWORK_PREFERENCE_TYPE_VIDEO), "WIFI_ONLY");
    }

    public final void displayWifiErrorDialog(Context context, FragmentActivity activity) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activity, "activity");
        activity.startActivity(PlaybackErrorHandler.getNotificationIntent(context, PlaybackException.PlaybackError.StreamingVideoRestriction));
    }

    public final Integer getMaxCacheSizeForVideoStories() {
        VideosCacheConfigMetadata videosCacheConfigMetadata2 = getVideosCacheConfigMetadata();
        if (videosCacheConfigMetadata2 == null) {
            return null;
        }
        return Integer.valueOf(videosCacheConfigMetadata2.getMaxCacheSize());
    }

    public final Integer getTTlForVideoStoriesCacheEntry() {
        VideosCacheConfigMetadata videosCacheConfigMetadata2 = getVideosCacheConfigMetadata();
        if (videosCacheConfigMetadata2 == null) {
            return null;
        }
        return Integer.valueOf(videosCacheConfigMetadata2.getTtlSeconds());
    }

    /* JADX INFO: compiled from: VideoStoriesUtil.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\b\u0086\b\u0018\u00002\u00020\u0001J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\"\u0010\t\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\"\u0010\u000f\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000e¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/video/stories/VideoStoriesUtil$VideosCacheConfigMetadata;", "", "", "toString", "", "hashCode", "other", "", "equals", "maxCacheSize", "I", "getMaxCacheSize", "()I", "setMaxCacheSize", "(I)V", "ttlSeconds", "getTtlSeconds", "setTtlSeconds", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final /* data */ class VideosCacheConfigMetadata {
        private int maxCacheSize;
        private int ttlSeconds;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VideosCacheConfigMetadata)) {
                return false;
            }
            VideosCacheConfigMetadata videosCacheConfigMetadata = (VideosCacheConfigMetadata) other;
            return this.maxCacheSize == videosCacheConfigMetadata.maxCacheSize && this.ttlSeconds == videosCacheConfigMetadata.ttlSeconds;
        }

        public int hashCode() {
            return (Integer.hashCode(this.maxCacheSize) * 31) + Integer.hashCode(this.ttlSeconds);
        }

        public String toString() {
            return "VideosCacheConfigMetadata(maxCacheSize=" + this.maxCacheSize + ", ttlSeconds=" + this.ttlSeconds + ')';
        }

        public final int getMaxCacheSize() {
            return this.maxCacheSize;
        }

        public final void setMaxCacheSize(int i) {
            this.maxCacheSize = i;
        }

        public final int getTtlSeconds() {
            return this.ttlSeconds;
        }

        public final void setTtlSeconds(int i) {
            this.ttlSeconds = i;
        }
    }
}
