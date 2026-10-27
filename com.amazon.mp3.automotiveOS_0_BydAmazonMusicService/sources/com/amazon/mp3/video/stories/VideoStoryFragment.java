package com.amazon.mp3.video.stories;

import CoreInterface.v1_0.Element;
import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Template;
import ExternalActionInterface.v1_0.ImmutableTrack;
import ExternalActionInterface.v1_0.PlayableContent;
import ExternalActionInterface.v1_0.ToggleButtonMethod;
import ExternalActionInterface.v1_0.Track;
import ExternalActionInterface.v1_0.TriggerExternalActionMethod;
import ExternalActionInterface.v1_0.TriggerExternalGenericPlaybackMethod;
import ExternalActionInterface.v1_0.VideoStoryContent;
import Touch.PlaybackTemplateInterface.v1_0.PlaybackTemplate;
import Touch.VideoQueueTemplateInterface.v1_0.VideoQueueTemplate;
import Touch.WidgetsInterface.v1_0.TextButtonElement;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.os.BundleKt;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.BounceBackViewPager;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.cache.videostory.CachedData;
import com.amazon.mp3.library.cache.videostory.StoryPlaybackCachedData;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.VideoStoryPrefetcher;
import com.amazon.mp3.video.stories.VideoStoryPlaybackDataRepository;
import com.amazon.mp3.video.stories.metrics.UIPageViewEvents;
import com.amazon.mp3.video.stories.playback.PlaybackRequest;
import com.amazon.mp3.video.stories.storyqueue.StackingPageTransformer;
import com.amazon.mp3.video.stories.storyqueue.VideoStoryItemAdapter;
import com.amazon.mp3.video.stories.storyqueue.VideoStoryItemFragment;
import com.amazon.mp3.video.stories.videostoryplayback.VideoStoryPlayback;
import com.amazon.music.events.types.EntityIdType;
import com.amazon.music.events.types.EntityType;
import com.amazon.music.explore.widgets.views.ErrorView;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.OnPlaybackEventListener;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.metrics.mts.event.types.ResumeReason;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.skyfire.ui.skyfire.Contexts;
import com.amazon.music.skyfire.ui.widgets.views.IconButtonView;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.BauhausLoadingView;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.media.playback.Playback;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: VideoStoryFragment.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u009c\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\r\n\u0002\u0010!\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0002Æ\u0001\u0018\u0000 Ñ\u00012\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006:\u0002Ñ\u0001B\t¢\u0006\u0006\bÏ\u0001\u0010Ð\u0001J\u0016\u0010\u000b\u001a\u00020\n2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0002J\b\u0010\f\u001a\u00020\nH\u0002J\u0016\u0010\u000e\u001a\u00020\n2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0002J\u0016\u0010\u0011\u001a\u00020\n2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0007H\u0002J\b\u0010\u0012\u001a\u00020\nH\u0002J\b\u0010\u0013\u001a\u00020\nH\u0002J@\u0010\u001d\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u0016H\u0002J\b\u0010\u001e\u001a\u00020\u000fH\u0002J\b\u0010\u001f\u001a\u00020\nH\u0002J\u0010\u0010!\u001a\u00020\n2\u0006\u0010 \u001a\u00020\u0016H\u0002J\b\u0010\"\u001a\u00020\u0016H\u0002J\b\u0010#\u001a\u00020\nH\u0002J\b\u0010$\u001a\u00020\nH\u0002J\b\u0010%\u001a\u00020\nH\u0002J\u001c\u0010'\u001a\b\u0012\u0004\u0012\u00020&0\u00072\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0002J\b\u0010(\u001a\u00020\nH\u0002J\u0010\u0010*\u001a\u00020&2\u0006\u0010)\u001a\u00020\u000fH\u0002J\u0018\u0010-\u001a\u00020&2\u0006\u0010+\u001a\u00020\b2\u0006\u0010,\u001a\u00020\u0016H\u0002J\b\u0010.\u001a\u00020\u0016H\u0002J\u0010\u00101\u001a\u0002002\u0006\u0010/\u001a\u00020\u0016H\u0002J\b\u00102\u001a\u00020\nH\u0002J\b\u00103\u001a\u00020\nH\u0002J\u0010\u00106\u001a\u00020\n2\u0006\u00105\u001a\u000204H\u0002J\u0010\u00108\u001a\u00020\n2\u0006\u00105\u001a\u000207H\u0002J\u0010\u0010:\u001a\u00020\n2\u0006\u00105\u001a\u000209H\u0002J\u0010\u0010=\u001a\u00020;2\u0006\u0010<\u001a\u00020;H\u0002J\u0019\u0010>\u001a\u00020\u00162\b\u0010 \u001a\u0004\u0018\u00010\u0016H\u0002¢\u0006\u0004\b>\u0010?J\u0012\u0010B\u001a\u00020\n2\b\u0010A\u001a\u0004\u0018\u00010@H\u0016J\u0014\u0010E\u001a\u00020\n2\f\u0010D\u001a\b\u0012\u0004\u0012\u00020C0\u0007J\u000e\u0010G\u001a\u00020\n2\u0006\u0010F\u001a\u000200J\u0006\u0010H\u001a\u00020\nJ\u0018\u0010K\u001a\u00020\n2\u0006\u0010I\u001a\u00020\u000f2\u0006\u0010J\u001a\u00020\u0002H\u0014J\u0018\u0010L\u001a\u00020\n2\u0006\u0010I\u001a\u00020\u000f2\u0006\u0010J\u001a\u00020\u0002H\u0014J$\u0010Q\u001a\u00020\u00142\u0006\u0010N\u001a\u00020M2\b\u0010P\u001a\u0004\u0018\u00010O2\b\u0010A\u001a\u0004\u0018\u00010@H\u0016J\u001a\u0010R\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u00142\b\u0010A\u001a\u0004\u0018\u00010@H\u0016J\u000e\u0010T\u001a\u0002002\u0006\u0010S\u001a\u00020\u0016J\u0006\u0010U\u001a\u000200J\b\u0010V\u001a\u00020\nH\u0016J\u0018\u0010Z\u001a\u00020\n2\u0006\u0010W\u001a\u00020\u00162\u0006\u0010Y\u001a\u00020XH\u0016J\b\u0010[\u001a\u00020\nH\u0016J\u0012\u0010]\u001a\u0004\u0018\u00010\\2\u0006\u0010W\u001a\u00020\u0016H\u0016J\b\u0010^\u001a\u00020\nH\u0016J\b\u0010_\u001a\u00020\nH\u0016J\b\u0010`\u001a\u00020\nH\u0016J\u0010\u0010b\u001a\u00020\n2\u0006\u0010a\u001a\u00020@H\u0016J\b\u0010c\u001a\u00020\nH\u0016J\b\u0010d\u001a\u00020\nH\u0016J\b\u0010e\u001a\u0004\u0018\u00010&J\b\u0010f\u001a\u00020\nH\u0016J\u0006\u0010g\u001a\u00020\nJ\u0006\u0010h\u001a\u00020\nJ\u0010\u0010i\u001a\u00020\n2\u0006\u00105\u001a\u000204H\u0016J\u001c\u0010j\u001a\u00020\n2\b\u0010I\u001a\u0004\u0018\u00010\u000f2\b\u00105\u001a\u0004\u0018\u00010CH\u0014R\u0016\u0010k\u001a\u00020\u00148\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bk\u0010lR\"\u0010n\u001a\u00020m8\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\bn\u0010o\u001a\u0004\bp\u0010q\"\u0004\br\u0010sR\"\u0010u\u001a\u00020t8\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\bu\u0010v\u001a\u0004\bw\u0010x\"\u0004\by\u0010zR\u0016\u0010|\u001a\u00020{8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b|\u0010}R\u0017\u0010\u007f\u001a\u00020~8\u0002@\u0002X\u0082.¢\u0006\u0007\n\u0005\b\u007f\u0010\u0080\u0001R\u001a\u0010\u0082\u0001\u001a\u00030\u0081\u00018\u0002@\u0002X\u0082.¢\u0006\b\n\u0006\b\u0082\u0001\u0010\u0083\u0001R\u001a\u0010\u0085\u0001\u001a\u00030\u0084\u00018\u0002@\u0002X\u0082.¢\u0006\b\n\u0006\b\u0085\u0001\u0010\u0086\u0001R!\u0010\u008c\u0001\u001a\u00030\u0087\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b\u0088\u0001\u0010\u0089\u0001\u001a\u0006\b\u008a\u0001\u0010\u008b\u0001R!\u0010\u0091\u0001\u001a\u00030\u008d\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b\u008e\u0001\u0010\u0089\u0001\u001a\u0006\b\u008f\u0001\u0010\u0090\u0001R!\u0010\u0092\u0001\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00078\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0092\u0001\u0010\u0093\u0001R!\u0010\u0094\u0001\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u00078\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0094\u0001\u0010\u0093\u0001R\u0019\u0010\u0095\u0001\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0095\u0001\u0010\u0096\u0001R\u001a\u0010\u0098\u0001\u001a\u00030\u0097\u00018\u0002@\u0002X\u0082.¢\u0006\b\n\u0006\b\u0098\u0001\u0010\u0099\u0001R)\u0010\u009a\u0001\u001a\u00020X8\u0006@\u0006X\u0086\u000e¢\u0006\u0018\n\u0006\b\u009a\u0001\u0010\u009b\u0001\u001a\u0006\b\u009c\u0001\u0010\u009d\u0001\"\u0006\b\u009e\u0001\u0010\u009f\u0001R,\u0010¢\u0001\u001a\u00030 \u00012\b\u0010¡\u0001\u001a\u00030 \u00018\u0006@BX\u0086.¢\u0006\u0010\n\u0006\b¢\u0001\u0010£\u0001\u001a\u0006\b¤\u0001\u0010¥\u0001R)\u0010¦\u0001\u001a\u0002008\u0006@\u0006X\u0086\u000e¢\u0006\u0018\n\u0006\b¦\u0001\u0010§\u0001\u001a\u0006\b¨\u0001\u0010©\u0001\"\u0006\bª\u0001\u0010«\u0001R*\u0010\u00ad\u0001\u001a\u00030¬\u00018\u0006@\u0006X\u0086.¢\u0006\u0018\n\u0006\b\u00ad\u0001\u0010®\u0001\u001a\u0006\b¯\u0001\u0010°\u0001\"\u0006\b±\u0001\u0010²\u0001R)\u0010³\u0001\u001a\u0002008\u0006@\u0006X\u0086\u000e¢\u0006\u0018\n\u0006\b³\u0001\u0010§\u0001\u001a\u0006\b´\u0001\u0010©\u0001\"\u0006\bµ\u0001\u0010«\u0001R\u0018\u0010·\u0001\u001a\u00030¶\u00018\u0002X\u0082D¢\u0006\b\n\u0006\b·\u0001\u0010¸\u0001R\u0017\u0010¹\u0001\u001a\u00020\u000f8\u0002X\u0082D¢\u0006\b\n\u0006\b¹\u0001\u0010º\u0001R\u0017\u0010»\u0001\u001a\u00020\u000f8\u0002X\u0082D¢\u0006\b\n\u0006\b»\u0001\u0010º\u0001R\u0017\u0010¼\u0001\u001a\u00020\u00168\u0002X\u0082D¢\u0006\b\n\u0006\b¼\u0001\u0010\u0096\u0001R\u0017\u0010½\u0001\u001a\u00020\u000f8\u0002X\u0082D¢\u0006\b\n\u0006\b½\u0001\u0010º\u0001R\u0017\u0010¾\u0001\u001a\u00020\u000f8\u0002X\u0082D¢\u0006\b\n\u0006\b¾\u0001\u0010º\u0001R\u001f\u0010¿\u0001\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00078\u0002@\u0002X\u0082.¢\u0006\b\n\u0006\b¿\u0001\u0010\u0093\u0001R\u0019\u0010À\u0001\u001a\u00020\u000f8\u0002@\u0002X\u0082.¢\u0006\b\n\u0006\bÀ\u0001\u0010º\u0001R\u0019\u0010Á\u0001\u001a\u00020\u000f8\u0002@\u0002X\u0082.¢\u0006\b\n\u0006\bÁ\u0001\u0010º\u0001R\u001b\u0010Â\u0001\u001a\u0004\u0018\u00010\u000f8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÂ\u0001\u0010º\u0001R\u0019\u0010Ã\u0001\u001a\u00020X8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÃ\u0001\u0010\u009b\u0001R \u0010Å\u0001\u001a\t\u0012\u0004\u0012\u00020&0Ä\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÅ\u0001\u0010\u0093\u0001R\u0018\u0010Ç\u0001\u001a\u00030Æ\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÇ\u0001\u0010È\u0001R\u0018\u0010Ê\u0001\u001a\u00030É\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÊ\u0001\u0010Ë\u0001R\u0018\u0010Í\u0001\u001a\u00030Ì\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÍ\u0001\u0010Î\u0001¨\u0006Ò\u0001"}, d2 = {"Lcom/amazon/mp3/video/stories/VideoStoryFragment;", "Lcom/amazon/mp3/video/stories/BaseVideoStoryFragment;", "LTouch/VideoQueueTemplateInterface/v1_0/VideoQueueTemplate;", "Lcom/amazon/mp3/video/stories/videostoryplayback/VideoStoryPlayback$PositionCallback;", "Lcom/amazon/mp3/video/stories/videostoryplayback/VideoStoryPlayback$VideoScreenCallback;", "Lcom/amazon/mp3/video/stories/videostoryplayback/VideoStoryPlayback$ContentStateCallback;", "", "", "LTouch/PlaybackTemplateInterface/v1_0/PlaybackTemplate;", "videoItems", "", "mapArtistFollowStates", "updateAPIDelay", "playbackTemplates", "setPlayerQueueAndViewPageAdapter", "", "asinsList", "populateQueue", "setVideoQueueTemplateFromCache", "setVideoQueueTemplateObserver", "Landroid/view/View;", "view", "", "width", "height", "left", "right", "top", "bottom", "setMargins", "getScreenSize", "initProgressBar", "duration", "bindProgressBar", "getProgressBarDuration", "handleNotch", "scheduleShowSpinner", "hideSpinner", "Lcom/amazon/mp3/video/stories/playback/PlaybackRequest;", "createVideoQueue", "bindCloseButton", "playbackAsin", "createPlaybackRequestWithoutContent", "playbackTemplate", "index", "createPlaybackRequest", "getSelectedAsinIndex", "position", "", "isFirstPlayback", "checkWifiPlaying", "registerNetworkManager", "LExternalActionInterface/v1_0/TriggerExternalActionMethod;", "method", "displayErrorScreen", "LExternalActionInterface/v1_0/ToggleButtonMethod;", "toggleArtistFollowState", "LExternalActionInterface/v1_0/TriggerExternalGenericPlaybackMethod;", "handleGenericPlaybackMethod", "LExternalActionInterface/v1_0/Track;", "track", "updateTrackDurationToMilliSeconds", "toMilliSeconds", "(Ljava/lang/Integer;)I", "Landroid/os/Bundle;", "savedInstanceState", "onCreate", "LCoreInterface/v1_0/Method;", "initialMethods", "setInitialMethodsAndRun", "isSwipeable", "setPageSwipeForVideoPager", "checkConnectionAndResumePlayback", "ownerId", "template", "onCreateAndBindTemplate", "onBindTemplate", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "onCreateView", "onViewCreated", "currIndex", "shouldPrefetchStories", "isActivityInForeground", "onNewPlaybackStarted", "queuePosition", "", "playbackPosition", "onPlaybackPositionChanged", "onPlaybackFinished", "Landroid/view/TextureView;", "getTextureView", "onContentLoading", "onContentReady", "onResume", "outState", "onSaveInstanceState", "onDestroy", "onPause", "getCurrentPlaybackRequest", "close", "registerListeners", "unRegisterListeners", "onHandleExternalAction", "onHandleTemplateMethod", "storyView", "Landroid/view/View;", "Lcom/amazon/mp3/video/stories/videostoryplayback/VideoStoryPlayback;", "videoStoryPlayback", "Lcom/amazon/mp3/video/stories/videostoryplayback/VideoStoryPlayback;", "getVideoStoryPlayback", "()Lcom/amazon/mp3/video/stories/videostoryplayback/VideoStoryPlayback;", "setVideoStoryPlayback", "(Lcom/amazon/mp3/video/stories/videostoryplayback/VideoStoryPlayback;)V", "Lcom/amazon/mp3/BounceBackViewPager;", "videoPager", "Lcom/amazon/mp3/BounceBackViewPager;", "getVideoPager", "()Lcom/amazon/mp3/BounceBackViewPager;", "setVideoPager", "(Lcom/amazon/mp3/BounceBackViewPager;)V", "Lcom/amazon/mp3/video/stories/storyqueue/VideoStoryItemAdapter;", "videoStoryItemAdapter", "Lcom/amazon/mp3/video/stories/storyqueue/VideoStoryItemAdapter;", "Landroid/widget/LinearLayout;", "progressBarContainer", "Landroid/widget/LinearLayout;", "Lcom/amazon/music/views/library/views/BauhausLoadingView;", "spinner", "Lcom/amazon/music/views/library/views/BauhausLoadingView;", "Lcom/amazon/music/skyfire/ui/widgets/views/IconButtonView;", "closeButton", "Lcom/amazon/music/skyfire/ui/widgets/views/IconButtonView;", "Lcom/amazon/music/media/playback/PlaybackController;", "playbackController$delegate", "Lkotlin/Lazy;", "getPlaybackController", "()Lcom/amazon/music/media/playback/PlaybackController;", "playbackController", "Lcom/amazon/podcast/media/playback/Playback;", "playback$delegate", "getPlayback", "()Lcom/amazon/podcast/media/playback/Playback;", "playback", "videoQueue", "Ljava/util/List;", "blockRefs", "currentPosition", "I", "Lcom/amazon/mp3/util/VideoStoryPrefetcher;", "videoStoryPrefetcher", "Lcom/amazon/mp3/util/VideoStoryPrefetcher;", "apiDelay", "J", "getApiDelay", "()J", "setApiDelay", "(J)V", "Landroidx/coordinatorlayout/widget/CoordinatorLayout;", "<set-?>", "rootView", "Landroidx/coordinatorlayout/widget/CoordinatorLayout;", "getRootView", "()Landroidx/coordinatorlayout/widget/CoordinatorLayout;", "swipeDownClose", "Z", "getSwipeDownClose", "()Z", "setSwipeDownClose", "(Z)V", "Lcom/amazon/music/views/library/styles/StyleSheet;", "mStyleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getMStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "setMStyleSheet", "(Lcom/amazon/music/views/library/styles/StyleSheet;)V", "changeOrientationBackToUnspecified", "getChangeOrientationBackToUnspecified", "setChangeOrientationBackToUnspecified", "", "THRESHOLD_WIDTH", "F", "XS_SCREEN", "Ljava/lang/String;", "SMALL_SCREEN", "THRESHOLD_DIFFERENCE_TO_QUEUE_END", "CONNECTIVITY_ACTION", "closeButtonIconSrc", "storiesAsins", "blockRefStr", "selectedAsin", "associateTag", "storyClickTime", "", "playbackRequestsList", "com/amazon/mp3/video/stories/VideoStoryFragment$networkManager$1", "networkManager", "Lcom/amazon/mp3/video/stories/VideoStoryFragment$networkManager$1;", "Lcom/amazon/music/media/playback/OnPlaybackEventListener;", "mPlaybackEventListener", "Lcom/amazon/music/media/playback/OnPlaybackEventListener;", "Lcom/amazon/podcast/media/playback/Playback$StateCallback;", "podcastPlaybackStateCallback", "Lcom/amazon/podcast/media/playback/Playback$StateCallback;", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class VideoStoryFragment extends BaseVideoStoryFragment<VideoQueueTemplate> implements VideoStoryPlayback.PositionCallback, VideoStoryPlayback.VideoScreenCallback, VideoStoryPlayback.ContentStateCallback {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private long apiDelay;
    private String associateTag;
    private String blockRefStr;
    private List<String> blockRefs;
    private IconButtonView closeButton;
    private final OnPlaybackEventListener mPlaybackEventListener;
    public StyleSheet mStyleSheet;
    private final VideoStoryFragment$networkManager$1 networkManager;
    private final Playback.StateCallback podcastPlaybackStateCallback;
    private LinearLayout progressBarContainer;
    private CoordinatorLayout rootView;
    private String selectedAsin;
    private BauhausLoadingView spinner;
    private List<String> storiesAsins;
    private long storyClickTime;
    private View storyView;
    private boolean swipeDownClose;
    public BounceBackViewPager videoPager;
    private List<? extends PlaybackTemplate> videoQueue;
    private VideoStoryItemAdapter videoStoryItemAdapter;
    public VideoStoryPlayback videoStoryPlayback;
    private VideoStoryPrefetcher videoStoryPrefetcher;

    /* JADX INFO: renamed from: playbackController$delegate, reason: from kotlin metadata */
    private final Lazy playbackController = LazyKt__LazyJVMKt.lazy(new Function0<ActionValidatedPlaybackController>() { // from class: com.amazon.mp3.video.stories.VideoStoryFragment$playbackController$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ActionValidatedPlaybackController invoke() {
            return PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI);
        }
    });

    /* JADX INFO: renamed from: playback$delegate, reason: from kotlin metadata */
    private final Lazy playback = LazyKt__LazyJVMKt.lazy(new Function0<Playback>() { // from class: com.amazon.mp3.video.stories.VideoStoryFragment$playback$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Playback invoke() {
            return Podcast.getPlayback();
        }
    });
    private int currentPosition = -1;
    private boolean changeOrientationBackToUnspecified = true;
    private final float THRESHOLD_WIDTH = 359.0f;
    private final String XS_SCREEN = "XS_SCREEN";
    private final String SMALL_SCREEN = "SMALL_SCREEN";
    private final int THRESHOLD_DIFFERENCE_TO_QUEUE_END = 5;
    private final String CONNECTIVITY_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    private final String closeButtonIconSrc = "https://m.media-amazon.com/images/G/01/music-explore/ic_action_cancel.png";
    private List<PlaybackRequest> playbackRequestsList = new ArrayList();

    @Override // com.amazon.mp3.video.stories.videostoryplayback.VideoStoryPlayback.PositionCallback
    public void onNewPlaybackStarted() {
    }

    /* JADX WARN: Type inference failed for: r0v15, types: [com.amazon.mp3.video.stories.VideoStoryFragment$networkManager$1] */
    public VideoStoryFragment() {
        StyleSheetProvider.getStyleSheetObservable().observeOn(Schedulers.io()).subscribe(new Action1() { // from class: com.amazon.mp3.video.stories.VideoStoryFragment$$ExternalSyntheticLambda3
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                VideoStoryFragment.m1673_init_$lambda0(this.f$0, (StyleSheet) obj);
            }
        });
        this.networkManager = new BroadcastReceiver() { // from class: com.amazon.mp3.video.stories.VideoStoryFragment$networkManager$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context cxt, Intent intent) {
                Intrinsics.checkNotNullParameter(cxt, "cxt");
                Intrinsics.checkNotNullParameter(intent, "intent");
                this.this$0.checkConnectionAndResumePlayback();
            }
        };
        this.mPlaybackEventListener = new OnPlaybackEventListener() { // from class: com.amazon.mp3.video.stories.VideoStoryFragment$$ExternalSyntheticLambda4
            @Override // com.amazon.music.media.playback.OnPlaybackEventListener
            public final void onPlaybackEvent(int i, ControlSource controlSource) {
                VideoStoryFragment.m1676mPlaybackEventListener$lambda8(this.f$0, i, controlSource);
            }
        };
        this.podcastPlaybackStateCallback = new Playback.StateCallback() { // from class: com.amazon.mp3.video.stories.VideoStoryFragment$$ExternalSyntheticLambda5
            @Override // com.amazon.podcast.media.playback.Playback.StateCallback
            public final void onPlaybackStateChange(int i) {
                VideoStoryFragment.m1678podcastPlaybackStateCallback$lambda9(this.f$0, i);
            }
        };
    }

    public final VideoStoryPlayback getVideoStoryPlayback() {
        VideoStoryPlayback videoStoryPlayback = this.videoStoryPlayback;
        if (videoStoryPlayback != null) {
            return videoStoryPlayback;
        }
        Intrinsics.throwUninitializedPropertyAccessException("videoStoryPlayback");
        return null;
    }

    public final void setVideoStoryPlayback(VideoStoryPlayback videoStoryPlayback) {
        Intrinsics.checkNotNullParameter(videoStoryPlayback, "<set-?>");
        this.videoStoryPlayback = videoStoryPlayback;
    }

    public final BounceBackViewPager getVideoPager() {
        BounceBackViewPager bounceBackViewPager = this.videoPager;
        if (bounceBackViewPager != null) {
            return bounceBackViewPager;
        }
        Intrinsics.throwUninitializedPropertyAccessException("videoPager");
        return null;
    }

    public final void setVideoPager(BounceBackViewPager bounceBackViewPager) {
        Intrinsics.checkNotNullParameter(bounceBackViewPager, "<set-?>");
        this.videoPager = bounceBackViewPager;
    }

    private final PlaybackController getPlaybackController() {
        return (PlaybackController) this.playbackController.getValue();
    }

    private final Playback getPlayback() {
        Object value = this.playback.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-playback>(...)");
        return (Playback) value;
    }

    public final long getApiDelay() {
        return this.apiDelay;
    }

    public final void setApiDelay(long j) {
        this.apiDelay = j;
    }

    public final CoordinatorLayout getRootView() {
        CoordinatorLayout coordinatorLayout = this.rootView;
        if (coordinatorLayout != null) {
            return coordinatorLayout;
        }
        Intrinsics.throwUninitializedPropertyAccessException("rootView");
        return null;
    }

    public final void setSwipeDownClose(boolean z) {
        this.swipeDownClose = z;
    }

    public final StyleSheet getMStyleSheet() {
        StyleSheet styleSheet = this.mStyleSheet;
        if (styleSheet != null) {
            return styleSheet;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mStyleSheet");
        return null;
    }

    public final void setMStyleSheet(StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(styleSheet, "<set-?>");
        this.mStyleSheet = styleSheet;
    }

    /* JADX INFO: compiled from: VideoStoryFragment.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J=\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u000f2\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00042\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0015R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/video/stories/VideoStoryFragment$Companion;", "", "()V", "ASSOCIATE_TAG", "", "BLOCK_REFS_KEY", "SELECTED_ASIN_KEY", "SPINNER_ANIM_DELAY_MILLIS", "", "SPINNER_ANIM_DURATION_MILLIS", "STORIES_ASIN_KEY", "STORY_CLICK_TIME", "newInstance", "Lcom/amazon/mp3/video/stories/VideoStoryFragment;", "storiesAsins", "", "blockRefStr", "selectedAsin", "storyClickTime", "", "associateTag", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;)Lcom/amazon/mp3/video/stories/VideoStoryFragment;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final VideoStoryFragment newInstance(List<String> storiesAsins, String blockRefStr, String selectedAsin, Long storyClickTime, String associateTag) {
            Intrinsics.checkNotNullParameter(storiesAsins, "storiesAsins");
            Intrinsics.checkNotNullParameter(blockRefStr, "blockRefStr");
            Intrinsics.checkNotNullParameter(selectedAsin, "selectedAsin");
            VideoStoryFragment videoStoryFragment = new VideoStoryFragment();
            Pair[] pairArr = new Pair[5];
            Object[] array = storiesAsins.toArray(new String[0]);
            if (array == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            }
            pairArr[0] = TuplesKt.to("storiesAsin", array);
            pairArr[1] = TuplesKt.to("blockRefs", blockRefStr);
            pairArr[2] = TuplesKt.to("selectedAsin", selectedAsin);
            pairArr[3] = TuplesKt.to("storyClickTime", storyClickTime);
            pairArr[4] = TuplesKt.to("associateTag", associateTag);
            videoStoryFragment.setArguments(BundleKt.bundleOf(pairArr));
            return videoStoryFragment;
        }
    }

    @Override // com.amazon.mp3.video.stories.BaseVideoStoryFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        String[] stringArray;
        String string;
        String string2;
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            savedInstanceState = getArguments();
        }
        List<String> list = (savedInstanceState == null || (stringArray = savedInstanceState.getStringArray("storiesAsin")) == null) ? null : ArraysKt___ArraysKt.toList(stringArray);
        if (list == null) {
            list = CollectionsKt__CollectionsKt.emptyList();
        }
        this.storiesAsins = list;
        String str = "";
        if (savedInstanceState == null || (string = savedInstanceState.getString("blockRefs")) == null) {
            string = "";
        }
        this.blockRefStr = string;
        if (savedInstanceState != null && (string2 = savedInstanceState.getString("selectedAsin")) != null) {
            str = string2;
        }
        this.selectedAsin = str;
        this.storyClickTime = savedInstanceState == null ? 0L : savedInstanceState.getLong("storyClickTime");
        this.associateTag = savedInstanceState != null ? savedInstanceState.getString("associateTag") : null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m1673_init_$lambda0(VideoStoryFragment this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(styleSheet, "styleSheet");
        this$0.setMStyleSheet(styleSheet);
    }

    public final void setInitialMethodsAndRun(List<? extends Method> initialMethods) {
        Intrinsics.checkNotNullParameter(initialMethods, "initialMethods");
        setInitialMethods(initialMethods);
        getMethodsDispatcher().dispatchMethods(getOwnerId(), initialMethods);
    }

    public final void setPageSwipeForVideoPager(boolean isSwipeable) {
        getVideoPager().setPageSwipeEnabled(isSwipeable);
    }

    public final void checkConnectionAndResumePlayback() {
        try {
            if (ConnectivityUtil.hasAnyInternetConnection(getContext())) {
                checkWifiPlaying();
                getVideoStoryPlayback().resumePlaybackAfterConnectionRestored();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.mp3.video.stories.BaseVideoStoryFragment, com.amazon.music.explore.fragment.StandaloneContainerFragment
    public void onCreateAndBindTemplate(String ownerId, VideoQueueTemplate template) {
        Intrinsics.checkNotNullParameter(ownerId, "ownerId");
        Intrinsics.checkNotNullParameter(template, "template");
        onBindTemplate(ownerId, template);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.mp3.video.stories.BaseVideoStoryFragment, com.amazon.music.explore.fragment.StandaloneContainerFragment
    public void onBindTemplate(String ownerId, VideoQueueTemplate template) {
        Intrinsics.checkNotNullParameter(ownerId, "ownerId");
        Intrinsics.checkNotNullParameter(template, "template");
        super.onBindTemplate(ownerId, template);
        List<PlaybackTemplate> listVideoItems = template.videoQueue().videoItems();
        Intrinsics.checkNotNullExpressionValue(listVideoItems, "template.videoQueue().videoItems()");
        setPlayerQueueAndViewPageAdapter(listVideoItems);
    }

    private final void mapArtistFollowStates(List<? extends PlaybackTemplate> videoItems) {
        VideoStoryContent videoStoryContent;
        String strArtistName;
        for (PlaybackTemplate playbackTemplate : videoItems) {
            for (Element element : playbackTemplate.secondaryButtons()) {
                if ((element instanceof TextButtonElement) && (videoStoryContent = (VideoStoryContent) playbackTemplate.playableContent()) != null && (strArtistName = videoStoryContent.artistName()) != null) {
                    VideoStoryArtistMapProvider.INSTANCE.setArtistFollowState(strArtistName, ((TextButtonElement) element).isActive());
                }
            }
        }
    }

    private final void updateAPIDelay() {
        if (this.videoQueue == null) {
            this.apiDelay = System.currentTimeMillis() - this.storyClickTime;
        }
    }

    private final void setPlayerQueueAndViewPageAdapter(List<? extends PlaybackTemplate> playbackTemplates) {
        getVideoStoryPlayback().setVideoQueue(createVideoQueue(playbackTemplates), false);
        VideoStoryItemAdapter videoStoryItemAdapter = this.videoStoryItemAdapter;
        if (videoStoryItemAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("videoStoryItemAdapter");
            videoStoryItemAdapter = null;
        }
        videoStoryItemAdapter.setVideoQueue(playbackTemplates);
        mapArtistFollowStates(playbackTemplates);
        getVideoPager().setCurrentItem(getSelectedAsinIndex(), false);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String str;
        LinearLayout linearLayout;
        IconButtonView iconButtonView;
        OnBackPressedDispatcher onBackPressedDispatcher;
        LinearLayout linearLayout2;
        IconButtonView iconButtonView2;
        String str2;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.setRequestedOrientation(1);
        }
        View viewInflate = inflater.inflate(R.layout.video_story_template_view, container, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater.inflate(R.layou…e_view, container, false)");
        this.storyView = viewInflate;
        if (viewInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storyView");
            viewInflate = null;
        }
        View viewFindViewById = viewInflate.findViewById(R.id.video_playback_loading_spinner);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "storyView.findViewById(R…playback_loading_spinner)");
        this.spinner = (BauhausLoadingView) viewFindViewById;
        View view = this.storyView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storyView");
            view = null;
        }
        View viewFindViewById2 = view.findViewById(R.id.video_story_pager);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "storyView.findViewById(R.id.video_story_pager)");
        setVideoPager((BounceBackViewPager) viewFindViewById2);
        View view2 = this.storyView;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storyView");
            view2 = null;
        }
        View viewFindViewById3 = view2.findViewById(R.id.video_story_layout_view);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "storyView.findViewById(R….video_story_layout_view)");
        this.rootView = (CoordinatorLayout) viewFindViewById3;
        setVideoStoryPlayback(new VideoStoryPlayback(this));
        FragmentManager childFragmentManager = getChildFragmentManager();
        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "childFragmentManager");
        this.videoStoryItemAdapter = new VideoStoryItemAdapter(childFragmentManager, this);
        String str3 = this.blockRefStr;
        if (str3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("blockRefStr");
            str = null;
        } else {
            str = str3;
        }
        List<String> listSplit$default = StringsKt__StringsKt.split$default((CharSequence) str, new String[]{","}, false, 0, 6, (Object) null);
        if (listSplit$default == null) {
            listSplit$default = CollectionsKt__CollectionsKt.emptyList();
        }
        this.blockRefs = listSplit$default;
        getPlaybackController().pause();
        if (getPlayback().isPodcastLastPlayed()) {
            getPlayback().pause();
        }
        getVideoPager().setClickable(true);
        getVideoPager().setCurrentItem(getSelectedAsinIndex(), false);
        getVideoPager().setPageTransformer(false, new StackingPageTransformer());
        getVideoPager().addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.amazon.mp3.video.stories.VideoStoryFragment.onCreateView.1
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int position, float positionOffset, int arg) {
                if (AmazonApplication.getCapabilities().isArtistFollowForVideoStoriesSupported()) {
                    VideoStoryItemAdapter videoStoryItemAdapter = VideoStoryFragment.this.videoStoryItemAdapter;
                    if (videoStoryItemAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("videoStoryItemAdapter");
                        videoStoryItemAdapter = null;
                    }
                    VideoStoryItemFragment fragment = videoStoryItemAdapter.getFragment(VideoStoryFragment.this.getVideoPager().getCurrentItem());
                    if (fragment == null) {
                        return;
                    }
                    VideoStoryItemFragment.updateFollowButtonText$default(fragment, null, null, 3, null);
                }
            }

            /* JADX WARN: Removed duplicated region for block: B:19:0x006e  */
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onPageSelected(int r9) {
                /*
                    Method dump skipped, instruction units count: 229
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.video.stories.VideoStoryFragment.AnonymousClass1.onPageSelected(int):void");
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(getVideoPager(), new OnApplyWindowInsetsListener() { // from class: com.amazon.mp3.video.stories.VideoStoryFragment$$ExternalSyntheticLambda1
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view3, WindowInsetsCompat windowInsetsCompat) {
                return VideoStoryFragment.m1677onCreateView$lambda2(view3, windowInsetsCompat);
            }
        });
        VideoStoryItemAdapter videoStoryItemAdapter = this.videoStoryItemAdapter;
        if (videoStoryItemAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("videoStoryItemAdapter");
            videoStoryItemAdapter = null;
        }
        videoStoryItemAdapter.setContainer(getVideoPager());
        BounceBackViewPager videoPager = getVideoPager();
        VideoStoryItemAdapter videoStoryItemAdapter2 = this.videoStoryItemAdapter;
        if (videoStoryItemAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("videoStoryItemAdapter");
            videoStoryItemAdapter2 = null;
        }
        videoPager.setAdapter(videoStoryItemAdapter2);
        View view3 = this.storyView;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storyView");
            view3 = null;
        }
        View viewFindViewById4 = view3.findViewById(R.id.video_story_close_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "storyView.findViewById(R…video_story_close_button)");
        this.closeButton = (IconButtonView) viewFindViewById4;
        View view4 = this.storyView;
        if (view4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storyView");
            view4 = null;
        }
        View viewFindViewById5 = view4.findViewById(R.id.video_story_progress_bar_container);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "storyView.findViewById(R…y_progress_bar_container)");
        this.progressBarContainer = (LinearLayout) viewFindViewById5;
        if (savedInstanceState == null) {
            UIPageViewEvents uIPageViewEvents = UIPageViewEvents.INSTANCE;
            List<String> list = this.blockRefs;
            if (list == null || (str2 = (String) CollectionsKt___CollectionsKt.getOrNull(list, getSelectedAsinIndex())) == null) {
                str2 = "";
            }
            uIPageViewEvents.sendPageViewMetrics(str2, PageType.NOW_PLAYING_VIDEO_STORY.name(), null, null, this.associateTag);
        }
        handleNotch();
        bindCloseButton();
        if (Intrinsics.areEqual(getScreenSize(), this.XS_SCREEN)) {
            LinearLayout linearLayout3 = this.progressBarContainer;
            if (linearLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressBarContainer");
                linearLayout2 = null;
            } else {
                linearLayout2 = linearLayout3;
            }
            setMargins(linearLayout2, -1, (int) getResources().getDimension(R.dimen.videoQueueProgressBarContainerHeight), (int) getResources().getDimension(R.dimen.video_player_base_margin), (int) getResources().getDimension(R.dimen.video_player_base_margin), (int) getResources().getDimension(R.dimen.large_padding), (int) getResources().getDimension(R.dimen.medium_padding));
            IconButtonView iconButtonView3 = this.closeButton;
            if (iconButtonView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("closeButton");
                iconButtonView2 = null;
            } else {
                iconButtonView2 = iconButtonView3;
            }
            setMargins(iconButtonView2, -2, -2, (int) getResources().getDimension(R.dimen.video_player_base_margin), (int) getResources().getDimension(R.dimen.video_player_base_margin), (int) getResources().getDimension(R.dimen.spacer_base), (int) getResources().getDimension(R.dimen.spacer_base));
        } else {
            LinearLayout linearLayout4 = this.progressBarContainer;
            if (linearLayout4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressBarContainer");
                linearLayout = null;
            } else {
                linearLayout = linearLayout4;
            }
            setMargins(linearLayout, -1, (int) getResources().getDimension(R.dimen.videoQueueProgressBarContainerHeight), (int) getResources().getDimension(R.dimen.large_padding), (int) getResources().getDimension(R.dimen.large_padding), (int) getResources().getDimension(R.dimen.spacer_huge), (int) getResources().getDimension(R.dimen.medium_padding));
            IconButtonView iconButtonView4 = this.closeButton;
            if (iconButtonView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("closeButton");
                iconButtonView = null;
            } else {
                iconButtonView = iconButtonView4;
            }
            setMargins(iconButtonView, -2, -2, (int) getResources().getDimension(R.dimen.large_padding), (int) getResources().getDimension(R.dimen.large_padding), (int) getResources().getDimension(R.dimen.spacer_base), (int) getResources().getDimension(R.dimen.spacer_base));
        }
        List<String> list2 = this.storiesAsins;
        if (list2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storiesAsins");
            list2 = null;
        }
        populateQueue(list2);
        this.videoStoryPrefetcher = new VideoStoryPrefetcher(this);
        FragmentActivity activity2 = getActivity();
        if (activity2 != null && (onBackPressedDispatcher = activity2.getOnBackPressedDispatcher()) != null) {
            onBackPressedDispatcher.addCallback(getViewLifecycleOwner(), new OnBackPressedCallback() { // from class: com.amazon.mp3.video.stories.VideoStoryFragment.onCreateView.3
                {
                    super(true);
                }

                @Override // androidx.activity.OnBackPressedCallback
                public void handleOnBackPressed() {
                    VideoStoryPlayback.sendUIClickEvent$default(VideoStoryFragment.this.getVideoStoryPlayback(), ActionType.GO_BACK, InteractionType.SWIPE, null, 4, null);
                    VideoStoryFragment.this.getVideoStoryPlayback().updateTerminationReason(TerminationReason.CLOSE_PLAYER);
                    VideoStoryFragment.this.close();
                }
            });
        }
        View view5 = this.storyView;
        if (view5 != null) {
            return view5;
        }
        Intrinsics.throwUninitializedPropertyAccessException("storyView");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-2, reason: not valid java name */
    public static final WindowInsetsCompat m1677onCreateView$lambda2(View view, WindowInsetsCompat windowInsetsCompat) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(windowInsetsCompat, "windowInsetsCompat");
        PagerAdapter adapter = ((ViewPager) view).getAdapter();
        if (adapter == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.video.stories.storyqueue.VideoStoryItemAdapter");
        }
        ((VideoStoryItemAdapter) adapter).setWindowInsets(windowInsetsCompat);
        return windowInsetsCompat.consumeSystemWindowInsets();
    }

    private final void populateQueue(List<String> asinsList) {
        Iterator<String> it = asinsList.iterator();
        while (it.hasNext()) {
            this.playbackRequestsList.add(createPlaybackRequestWithoutContent(it.next()));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x003a  */
    @Override // androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onViewCreated(android.view.View r14, android.os.Bundle r15) {
        /*
            r13 = this;
            java.lang.String r0 = "view"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
            super.onViewCreated(r14, r15)
            r13.registerListeners()
            com.amazon.mp3.video.stories.VideoStoryPlaybackDataRepository$Companion r14 = com.amazon.mp3.video.stories.VideoStoryPlaybackDataRepository.INSTANCE
            com.amazon.mp3.video.stories.VideoStoryPlaybackDataRepository r15 = r14.get()
            com.amazon.mp3.video.stories.VideoStoriesUtil r0 = com.amazon.mp3.video.stories.VideoStoriesUtil.INSTANCE
            java.util.List<java.lang.String> r1 = r13.storiesAsins
            java.lang.String r2 = "storiesAsins"
            r3 = 0
            if (r1 != 0) goto L20
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r1 = r3
        L20:
            java.lang.String r0 = r0.getPlaybackCacheKeyFromAsinList(r1)
            com.amazon.mp3.library.cache.videostory.CachedData r15 = r15.getFromPlaybackCache(r0)
            if (r15 == 0) goto L2e
            r13.setVideoQueueTemplateFromCache()
            goto L7a
        L2e:
            com.amazon.mp3.video.stories.VideoStoryPlaybackDataRepository r14 = r14.get()
            java.lang.String r14 = r14.getRequestedStoryIds()
            r15 = 1
            r0 = 0
            if (r14 != 0) goto L3c
        L3a:
            r15 = r0
            goto L59
        L3c:
            java.util.List<java.lang.String> r1 = r13.storiesAsins
            if (r1 != 0) goto L45
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r4 = r3
            goto L46
        L45:
            r4 = r1
        L46:
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 63
            r12 = 0
            java.lang.String r1 = kotlin.collections.CollectionsKt.joinToString$default(r4, r5, r6, r7, r8, r9, r10, r11, r12)
            boolean r14 = r14.equals(r1)
            if (r14 != r15) goto L3a
        L59:
            if (r15 == 0) goto L5f
            r13.setVideoQueueTemplateObserver()
            goto L7a
        L5f:
            com.amazon.mp3.util.VideoStoryPrefetcher r14 = r13.videoStoryPrefetcher
            if (r14 != 0) goto L6a
            java.lang.String r14 = "videoStoryPrefetcher"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r14)
            r14 = r3
        L6a:
            java.util.List<java.lang.String> r15 = r13.storiesAsins
            if (r15 != 0) goto L72
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            goto L73
        L72:
            r3 = r15
        L73:
            java.util.List r14 = r14.getSerializedMethods(r3)
            r13.setInitialMethodsAndRun(r14)
        L7a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.video.stories.VideoStoryFragment.onViewCreated(android.view.View, android.os.Bundle):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void setVideoQueueTemplateFromCache() {
        VideoStoryPlaybackDataRepository videoStoryPlaybackDataRepository = VideoStoryPlaybackDataRepository.INSTANCE.get();
        VideoStoriesUtil videoStoriesUtil = VideoStoriesUtil.INSTANCE;
        List<String> list = this.storiesAsins;
        List<String> list2 = null;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storiesAsins");
            list = null;
        }
        CachedData fromPlaybackCache = videoStoryPlaybackDataRepository.getFromPlaybackCache(videoStoriesUtil.getPlaybackCacheKeyFromAsinList(list));
        if (fromPlaybackCache != null) {
            StoryPlaybackCachedData storyPlaybackCachedData = fromPlaybackCache instanceof StoryPlaybackCachedData ? (StoryPlaybackCachedData) fromPlaybackCache : null;
            Template template = storyPlaybackCachedData != null ? storyPlaybackCachedData.getTemplate() : null;
            if (template == null) {
                throw new NullPointerException("null cannot be cast to non-null type Touch.VideoQueueTemplateInterface.v1_0.VideoQueueTemplate");
            }
            List<PlaybackTemplate> listVideoItems = ((VideoQueueTemplate) template).videoQueue().videoItems();
            this.videoQueue = listVideoItems;
            if (listVideoItems == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.List<Touch.PlaybackTemplateInterface.v1_0.PlaybackTemplate>");
            }
            setPlayerQueueAndViewPageAdapter(listVideoItems);
            return;
        }
        VideoStoryPrefetcher videoStoryPrefetcher = this.videoStoryPrefetcher;
        if (videoStoryPrefetcher == null) {
            Intrinsics.throwUninitializedPropertyAccessException("videoStoryPrefetcher");
            videoStoryPrefetcher = null;
        }
        List<String> list3 = this.storiesAsins;
        if (list3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storiesAsins");
        } else {
            list2 = list3;
        }
        setInitialMethodsAndRun(videoStoryPrefetcher.getSerializedMethods(list2));
    }

    private final void setVideoQueueTemplateObserver() {
        VideoStoryPlaybackDataRepository.INSTANCE.get().getPlaybackTemplates().observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.mp3.video.stories.VideoStoryFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                VideoStoryFragment.m1679setVideoQueueTemplateObserver$lambda3(this.f$0, (List) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setVideoQueueTemplateObserver$lambda-3, reason: not valid java name */
    public static final void m1679setVideoQueueTemplateObserver$lambda3(VideoStoryFragment this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list == null || list.isEmpty()) {
            return;
        }
        this$0.updateAPIDelay();
        this$0.videoQueue = list;
        if (list == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.List<Touch.PlaybackTemplateInterface.v1_0.PlaybackTemplate>");
        }
        this$0.setPlayerQueueAndViewPageAdapter(list);
    }

    public final boolean shouldPrefetchStories(int currIndex) {
        return Math.abs(getVideoStoryPlayback().getVideoQueueSize() - currIndex) <= this.THRESHOLD_DIFFERENCE_TO_QUEUE_END;
    }

    private final void setMargins(View view, int width, int height, int left, int right, int top, int bottom) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(view, width, height, Boolean.FALSE, Integer.valueOf(left), Integer.valueOf(top), Integer.valueOf(right), Integer.valueOf(bottom));
    }

    private final String getScreenSize() {
        if (r0.widthPixels / getResources().getDisplayMetrics().density <= this.THRESHOLD_WIDTH) {
            return this.XS_SCREEN;
        }
        return this.SMALL_SCREEN;
    }

    private final void initProgressBar() {
        LinearLayout linearLayout = this.progressBarContainer;
        LinearLayout linearLayout2 = null;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressBarContainer");
            linearLayout = null;
        }
        if (linearLayout.getChildCount() > 0) {
            return;
        }
        ProgressBar progressBar = new ProgressBar(getContext(), null, 0, R.style.VideoQueueProgressBar);
        LinearLayout linearLayout3 = this.progressBarContainer;
        if (linearLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressBarContainer");
            linearLayout3 = null;
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(linearLayout3.getWidth(), -1);
        LinearLayout linearLayout4 = this.progressBarContainer;
        if (linearLayout4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressBarContainer");
        } else {
            linearLayout2 = linearLayout4;
        }
        linearLayout2.addView(progressBar, layoutParams);
    }

    private final void bindProgressBar(int duration) {
        LinearLayout linearLayout = this.progressBarContainer;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressBarContainer");
            linearLayout = null;
        }
        ProgressBar progressBar = (ProgressBar) linearLayout.getChildAt(0);
        if (progressBar != null) {
            progressBar.setMax(duration);
        }
        if (progressBar == null) {
            return;
        }
        progressBar.setProgress(0);
    }

    private final int getProgressBarDuration() {
        LinearLayout linearLayout = this.progressBarContainer;
        LinearLayout linearLayout2 = null;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressBarContainer");
            linearLayout = null;
        }
        if (linearLayout.getChildCount() == 0) {
            return 0;
        }
        LinearLayout linearLayout3 = this.progressBarContainer;
        if (linearLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressBarContainer");
        } else {
            linearLayout2 = linearLayout3;
        }
        ProgressBar progressBar = (ProgressBar) linearLayout2.getChildAt(0);
        if (progressBar == null) {
            return 0;
        }
        return progressBar.getMax();
    }

    private final void handleNotch() {
        Window window;
        if (Build.VERSION.SDK_INT < 28) {
            return;
        }
        FragmentActivity activity = getActivity();
        WindowManager.LayoutParams attributes = null;
        if (activity != null && (window = activity.getWindow()) != null) {
            attributes = window.getAttributes();
        }
        if (attributes == null) {
            return;
        }
        attributes.layoutInDisplayCutoutMode = 1;
    }

    private final void scheduleShowSpinner() {
        BauhausLoadingView bauhausLoadingView = this.spinner;
        if (bauhausLoadingView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("spinner");
            bauhausLoadingView = null;
        }
        bauhausLoadingView.animate().alpha(1.0f).setDuration(100L).start();
    }

    private final void hideSpinner() {
        BauhausLoadingView bauhausLoadingView = this.spinner;
        if (bauhausLoadingView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("spinner");
            bauhausLoadingView = null;
        }
        bauhausLoadingView.animate().alpha(0.0f).setStartDelay(130L).setDuration(100L).start();
    }

    private final List<PlaybackRequest> createVideoQueue(List<? extends PlaybackTemplate> playbackTemplates) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Object obj : playbackTemplates) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            arrayList.add(createPlaybackRequest((PlaybackTemplate) obj, i));
            i = i2;
        }
        return arrayList;
    }

    private final void bindCloseButton() {
        IconButtonView iconButtonView = this.closeButton;
        IconButtonView iconButtonView2 = null;
        if (iconButtonView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("closeButton");
            iconButtonView = null;
        }
        iconButtonView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.video.stories.VideoStoryFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VideoStoryFragment.m1674bindCloseButton$lambda5(this.f$0, view);
            }
        });
        IconButtonView iconButtonView3 = this.closeButton;
        if (iconButtonView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("closeButton");
            iconButtonView3 = null;
        }
        iconButtonView3.setVisibility(0);
        IconButtonView iconButtonView4 = this.closeButton;
        if (iconButtonView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("closeButton");
        } else {
            iconButtonView2 = iconButtonView4;
        }
        iconButtonView2.setImage(this.closeButtonIconSrc);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bindCloseButton$lambda-5, reason: not valid java name */
    public static final void m1674bindCloseButton$lambda5(VideoStoryFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        VideoStoryPlayback videoStoryPlayback = this$0.getVideoStoryPlayback();
        ActionType actionType = ActionType.CLOSE_VIDEO_STORY_SCREEN;
        InteractionType interactionType = InteractionType.TAP;
        String str = this$0.selectedAsin;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectedAsin");
            str = null;
        }
        videoStoryPlayback.sendUIClickEvent(actionType, interactionType, str);
        this$0.getVideoStoryPlayback().updateTerminationReason(TerminationReason.CLOSE_PLAYER);
        this$0.close();
    }

    private final PlaybackRequest createPlaybackRequestWithoutContent(String playbackAsin) {
        String str;
        String str2;
        int selectedAsinIndex = getSelectedAsinIndex();
        int selectedAsinIndex2 = getSelectedAsinIndex();
        List<String> list = this.blockRefs;
        String str3 = list == null ? null : (String) CollectionsKt___CollectionsKt.getOrNull(list, selectedAsinIndex);
        String str4 = this.selectedAsin;
        if (str4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectedAsin");
            str = playbackAsin;
            str4 = null;
        } else {
            str = playbackAsin;
        }
        boolean zAreEqual = Intrinsics.areEqual(str, str4);
        VideoResourceType videoResourceType = VideoResourceType.BROWSE;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        String str5 = this.selectedAsin;
        if (str5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectedAsin");
            str2 = null;
        } else {
            str2 = str5;
        }
        return new PlaybackRequest(null, null, selectedAsinIndex2, str3, zAreEqual, false, false, videoResourceType, linkedHashMap, playbackAsin, str2, playbackAsin, EntityIdType.ASIN.getMetricValue(), EntityType.VIDEO.getMetricValue(), VideoType.VIDEO_STORY, null, null, VideoPlaybackState.PLAYING);
    }

    private final PlaybackRequest createPlaybackRequest(PlaybackTemplate playbackTemplate, int index) {
        String str;
        PlayableContent playableContent = playbackTemplate.playableContent();
        if (playableContent == null) {
            throw new NullPointerException("null cannot be cast to non-null type ExternalActionInterface.v1_0.VideoStoryContent");
        }
        VideoStoryContent videoStoryContent = (VideoStoryContent) playableContent;
        int selectedAsinIndex = getSelectedAsinIndex();
        List<String> list = this.blockRefs;
        String str2 = list == null ? null : (String) CollectionsKt___CollectionsKt.getOrNull(list, index);
        String strAsin = videoStoryContent.asin();
        String str3 = this.selectedAsin;
        if (str3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectedAsin");
            str3 = null;
        }
        boolean zAreEqual = Intrinsics.areEqual(strAsin, str3);
        VideoResourceType videoResourceType = VideoResourceType.BROWSE;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        String strAsin2 = videoStoryContent.asin();
        String str4 = this.selectedAsin;
        if (str4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectedAsin");
            str = null;
        } else {
            str = str4;
        }
        return new PlaybackRequest(playbackTemplate, videoStoryContent, selectedAsinIndex, str2, zAreEqual, false, false, videoResourceType, linkedHashMap, strAsin2, str, videoStoryContent.asin(), EntityIdType.ASIN.getMetricValue(), EntityType.VIDEO.getMetricValue(), VideoType.VIDEO_STORY, null, null, VideoPlaybackState.PLAYING);
    }

    private final int getSelectedAsinIndex() {
        List<? extends PlaybackTemplate> list = this.videoQueue;
        if (list != null) {
            if (list != null) {
                int size = list.size();
                int i = 0;
                while (i < size) {
                    int i2 = i + 1;
                    PlayableContent playableContent = list.get(i).playableContent();
                    if (playableContent == null) {
                        throw new NullPointerException("null cannot be cast to non-null type ExternalActionInterface.v1_0.VideoStoryContent");
                    }
                    String strAsin = ((VideoStoryContent) playableContent).asin();
                    String str = this.selectedAsin;
                    if (str == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("selectedAsin");
                        str = null;
                    }
                    if (Intrinsics.areEqual(strAsin, str)) {
                        return i;
                    }
                    i = i2;
                }
            }
            return 0;
        }
        List<String> list2 = this.storiesAsins;
        if (list2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storiesAsins");
            list2 = null;
        }
        int size2 = list2.size();
        int i3 = 0;
        while (i3 < size2) {
            int i4 = i3 + 1;
            List<String> list3 = this.storiesAsins;
            if (list3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("storiesAsins");
                list3 = null;
            }
            String str2 = list3.get(i3);
            String str3 = this.selectedAsin;
            if (str3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("selectedAsin");
                str3 = null;
            }
            if (Intrinsics.areEqual(str2, str3)) {
                return i3;
            }
            i3 = i4;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isFirstPlayback(int position) {
        return getSelectedAsinIndex() == position && this.currentPosition == -1;
    }

    public final boolean isActivityInForeground() {
        return getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: mPlaybackEventListener$lambda-8, reason: not valid java name */
    public static final void m1676mPlaybackEventListener$lambda8(VideoStoryFragment this$0, int i, ControlSource controlSource) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == 14) {
            this$0.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: podcastPlaybackStateCallback$lambda-9, reason: not valid java name */
    public static final void m1678podcastPlaybackStateCallback$lambda9(VideoStoryFragment this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (i == 3 || i == 6) {
            this$0.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkWifiPlaying() {
        Context context = getContext();
        boolean z = false;
        if (context != null && VideoStoriesUtil.INSTANCE.shouldBlockPlayingVideoStories(context)) {
            z = true;
        }
        if (z) {
            close();
            FragmentActivity activity = getActivity();
            if (activity == null) {
                return;
            }
            VideoStoriesUtil videoStoriesUtil = VideoStoriesUtil.INSTANCE;
            Context contextRequireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
            videoStoriesUtil.displayWifiErrorDialog(contextRequireContext, activity);
        }
    }

    @Override // com.amazon.mp3.video.stories.videostoryplayback.VideoStoryPlayback.PositionCallback
    public void onPlaybackPositionChanged(int queuePosition, long playbackPosition) {
        if (getVideoPager().getCurrentItem() == queuePosition) {
            LinearLayout linearLayout = this.progressBarContainer;
            if (linearLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("progressBarContainer");
                linearLayout = null;
            }
            View childAt = linearLayout.getChildAt(0);
            if (childAt != null && (childAt instanceof ProgressBar)) {
                ((ProgressBar) childAt).setProgress((int) playbackPosition);
            }
            getVideoStoryPlayback().setProgress((int) playbackPosition);
        }
    }

    @Override // com.amazon.mp3.video.stories.videostoryplayback.VideoStoryPlayback.PositionCallback
    public void onPlaybackFinished() {
        if (getVideoPager().getCurrentItem() < getVideoStoryPlayback().getVideoQueueSize() - 1) {
            getVideoStoryPlayback().sendVideoPlaybackTerminatedMetrics(TerminationReason.TRACK_FINISHED, "");
            getVideoStoryPlayback().sendVideoStreamedMetrics();
            getVideoPager().arrowScroll(66);
        } else {
            getVideoStoryPlayback().updateTerminationReason(TerminationReason.TRACK_FINISHED);
            close();
        }
    }

    @Override // com.amazon.mp3.video.stories.videostoryplayback.VideoStoryPlayback.VideoScreenCallback
    public TextureView getTextureView(int queuePosition) {
        VideoStoryItemAdapter videoStoryItemAdapter = this.videoStoryItemAdapter;
        if (videoStoryItemAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("videoStoryItemAdapter");
            videoStoryItemAdapter = null;
        }
        return videoStoryItemAdapter.getTextureView(queuePosition);
    }

    @Override // com.amazon.mp3.video.stories.videostoryplayback.VideoStoryPlayback.ContentStateCallback
    public void onContentLoading() {
        scheduleShowSpinner();
    }

    @Override // com.amazon.mp3.video.stories.videostoryplayback.VideoStoryPlayback.ContentStateCallback
    public void onContentReady() {
        hideSpinner();
        initProgressBar();
        getVideoStoryPlayback().setFirstPlaybackStarted(true);
        VideoStoryItemAdapter videoStoryItemAdapter = this.videoStoryItemAdapter;
        if (videoStoryItemAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("videoStoryItemAdapter");
            videoStoryItemAdapter = null;
        }
        VideoStoryItemFragment fragment = videoStoryItemAdapter.getFragment(getVideoPager().getCurrentItem());
        if (fragment != null) {
            fragment.hidePreviewImage();
        }
        PlaybackRequest currentRequest = getVideoStoryPlayback().getCurrentRequest();
        if ((currentRequest != null ? currentRequest.getPlaybackStatus() : null) != VideoPlaybackState.PAUSED && isActivityInForeground()) {
            getVideoStoryPlayback().toggleMute(false);
            getVideoStoryPlayback().play();
        }
        if (getProgressBarDuration() != getVideoStoryPlayback().getDuration()) {
            bindProgressBar(getVideoStoryPlayback().getDuration());
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.changeOrientationBackToUnspecified = true;
        PlaybackRequest currentRequest = getVideoStoryPlayback().getCurrentRequest();
        if ((currentRequest == null ? null : currentRequest.getPlaybackStatus()) != VideoPlaybackState.PAUSED) {
            if (this.videoQueue != null && getVideoStoryPlayback().getCurrentRequest() == null) {
                getVideoStoryPlayback().playQueuePosition(getSelectedAsinIndex(), VideoChangeReason.NONE);
                return;
            }
            PlaybackRequest currentRequest2 = getVideoStoryPlayback().getCurrentRequest();
            if (currentRequest2 != null) {
                currentRequest2.setResumeReason(ResumeReason.USER_START);
            }
            checkConnectionAndResumePlayback();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        this.changeOrientationBackToUnspecified = false;
        List<String> list = this.storiesAsins;
        String str = null;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("storiesAsins");
            list = null;
        }
        Object[] array = list.toArray(new String[0]);
        if (array == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
        outState.putStringArray("storiesAsin", (String[]) array);
        String str2 = this.blockRefStr;
        if (str2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("blockRefStr");
            str2 = null;
        }
        outState.putString("blockRefs", str2);
        String str3 = this.selectedAsin;
        if (str3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectedAsin");
        } else {
            str = str3;
        }
        outState.putString("selectedAsin", str);
        outState.putLong("storyClickTime", this.storyClickTime);
        outState.putString("associateTag", this.associateTag);
    }

    @Override // com.amazon.mp3.video.stories.BaseVideoStoryFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        FragmentActivity activity;
        super.onDestroy();
        if (isAdded()) {
            close();
        }
        if (this.changeOrientationBackToUnspecified && (activity = getActivity()) != null) {
            activity.setRequestedOrientation(-1);
        }
        VideoStoryPlaybackDataRepository.Companion companion = VideoStoryPlaybackDataRepository.INSTANCE;
        if (companion.get().getToggleButtonMethodHandled()) {
            return;
        }
        companion.get().fetchAndUpdateFollowState();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        getVideoStoryPlayback().pause(VideoChangeReason.ENTER_BACKGROUND);
    }

    public final PlaybackRequest getCurrentPlaybackRequest() {
        return (PlaybackRequest) CollectionsKt___CollectionsKt.getOrNull(this.playbackRequestsList, getSelectedAsinIndex());
    }

    public void close() {
        if (getVideoStoryPlayback().isPlaying()) {
            PlaybackRequest currentRequest = getVideoStoryPlayback().getCurrentRequest();
            TerminationReason terminationReason = currentRequest == null ? null : currentRequest.getTerminationReason();
            if (terminationReason == null) {
                terminationReason = TerminationReason.USER_STOP;
            }
            VideoStoryPlayback.sendVideoPlaybackTerminatedMetrics$default(getVideoStoryPlayback(), terminationReason, null, 2, null);
        }
        if (!getVideoStoryPlayback().getFirstPlaybackStarted()) {
            VideoStoryPlayback.sendVideoPlaybackTerminatedMetrics$default(getVideoStoryPlayback(), TerminationReason.CLOSE_PLAYER, null, 2, null);
        }
        if (getVideoStoryPlayback().isRebuffering()) {
            getVideoStoryPlayback().sendVideoStreamingRebufferingEvent(getVideoStoryPlayback().getStoryBufferingTime());
        }
        unRegisterListeners();
        getVideoStoryPlayback().sendVideoStreamedMetrics();
        if (!requireActivity().isChangingConfigurations()) {
            getVideoStoryPlayback().shutdown();
        }
        VideoStoriesUtil videoStoriesUtil = VideoStoriesUtil.INSTANCE;
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
        videoStoriesUtil.closeVideoStoriesPage(fragmentActivityRequireActivity, this.swipeDownClose);
    }

    public final void registerListeners() {
        getVideoStoryPlayback().addPositionCallback(this);
        getVideoStoryPlayback().addContentStateCallback(this);
        getVideoStoryPlayback().setVideoScreenCallback(this);
        getPlaybackController().addOnPlaybackEventListener(this.mPlaybackEventListener);
        getPlayback().addStateCallback(this.podcastPlaybackStateCallback);
        registerNetworkManager();
    }

    public final void unRegisterListeners() {
        getVideoStoryPlayback().removePositionCallback(this);
        getVideoStoryPlayback().removeContentStateCallback(this);
        getVideoStoryPlayback().removeVideoScreenCallback();
        getPlaybackController().removeOnPlaybackEventListener(this.mPlaybackEventListener);
        getPlayback().removeStateCallback(this.podcastPlaybackStateCallback);
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.unregisterReceiver(this.networkManager);
    }

    private final void registerNetworkManager() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(this.CONNECTIVITY_ACTION);
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.registerReceiver(this.networkManager, intentFilter);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0044  */
    @Override // com.amazon.mp3.video.stories.BaseVideoStoryFragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onHandleExternalAction(ExternalActionInterface.v1_0.TriggerExternalActionMethod r3) {
        /*
            r2 = this;
            java.lang.String r0 = "method"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = r3.name()
            if (r0 == 0) goto L6a
            int r1 = r0.hashCode()
            switch(r1) {
                case -2127894679: goto L3b;
                case -1540952042: goto L32;
                case -1472565484: goto L29;
                case 358634570: goto L20;
                case 730021664: goto L13;
                default: goto L12;
            }
        L12:
            goto L6a
        L13:
            java.lang.String r1 = "DISPLAY_ERROR_SCREEN"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L1c
            goto L6a
        L1c:
            r2.displayErrorScreen(r3)
            goto L6a
        L20:
            java.lang.String r1 = "OPEN_PODCAST_LINK"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L44
            goto L6a
        L29:
            java.lang.String r1 = "OPEN_ARTIST_DETAIL"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L44
            goto L6a
        L32:
            java.lang.String r1 = "OPEN_ALBUM_DETAIL"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L44
            goto L6a
        L3b:
            java.lang.String r1 = "OPEN_PLAYLIST_DETAIL"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L44
            goto L6a
        L44:
            android.content.Context r0 = r2.getContext()
            boolean r0 = com.amazon.mp3.util.ConnectivityUtil.checkConnectivityAndShowDialog(r0)
            if (r0 == 0) goto L69
            com.amazon.mp3.video.stories.videostoryplayback.VideoStoryPlayback r0 = r2.getVideoStoryPlayback()
            com.amazon.music.metrics.mts.event.types.TerminationReason r0 = r0.getTerminationReasonIfAvailable()
            if (r0 == 0) goto L5c
            com.amazon.music.metrics.mts.event.types.TerminationReason r1 = com.amazon.music.metrics.mts.event.types.TerminationReason.ARTIST_IMAGE_CLICKED
            if (r0 == r1) goto L65
        L5c:
            com.amazon.mp3.video.stories.videostoryplayback.VideoStoryPlayback r0 = r2.getVideoStoryPlayback()
            com.amazon.music.metrics.mts.event.types.TerminationReason r1 = com.amazon.music.metrics.mts.event.types.TerminationReason.ACTION_BUTTON_CLICKED
            r0.updateTerminationReason(r1)
        L65:
            r2.close()
            goto L6a
        L69:
            return
        L6a:
            super.onHandleExternalAction(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.video.stories.VideoStoryFragment.onHandleExternalAction(ExternalActionInterface.v1_0.TriggerExternalActionMethod):void");
    }

    private final void displayErrorScreen(TriggerExternalActionMethod method) {
        ErrorView errorView = new ErrorView(getContext());
        errorView.setPrimaryText(method.context().get(Contexts.DisplayErrorScreen.primaryText));
        errorView.setSecondaryText(method.context().get(Contexts.DisplayErrorScreen.secondaryText));
        errorView.setButtonText(method.context().get(Contexts.DisplayErrorScreen.buttonText));
        errorView.setButtonClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.video.stories.VideoStoryFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VideoStoryFragment.m1675displayErrorScreen$lambda12(this.f$0, view);
            }
        });
        getRootView().addView(errorView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: displayErrorScreen$lambda-12, reason: not valid java name */
    public static final void m1675displayErrorScreen$lambda12(VideoStoryFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.close();
    }

    @Override // com.amazon.mp3.video.stories.BaseVideoStoryFragment, com.amazon.music.explore.fragment.StandaloneContainerFragment
    protected void onHandleTemplateMethod(String ownerId, Method method) {
        if (method instanceof TriggerExternalGenericPlaybackMethod) {
            handleGenericPlaybackMethod((TriggerExternalGenericPlaybackMethod) method);
        } else {
            if (method instanceof TriggerExternalActionMethod) {
                onHandleExternalAction((TriggerExternalActionMethod) method);
                return;
            }
            if (method instanceof ToggleButtonMethod) {
                toggleArtistFollowState((ToggleButtonMethod) method);
            }
            super.onHandleTemplateMethod(ownerId, method);
        }
    }

    private final void toggleArtistFollowState(ToggleButtonMethod method) {
        String strWidgetId;
        Boolean boolIsActive = method.isActive();
        if (boolIsActive != null && (strWidgetId = method.widgetId()) != null) {
            VideoStoryArtistMapProvider videoStoryArtistMapProvider = VideoStoryArtistMapProvider.INSTANCE;
            String asinFromWidgetId = videoStoryArtistMapProvider.getAsinFromWidgetId(strWidgetId);
            videoStoryArtistMapProvider.setArtistFollowState(asinFromWidgetId, boolIsActive.booleanValue());
            VideoStoryItemAdapter videoStoryItemAdapter = this.videoStoryItemAdapter;
            if (videoStoryItemAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("videoStoryItemAdapter");
                videoStoryItemAdapter = null;
            }
            VideoStoryItemFragment fragment = videoStoryItemAdapter.getFragment(getVideoPager().getCurrentItem());
            if (fragment != null) {
                fragment.updateFollowButtonText(boolIsActive, asinFromWidgetId);
            }
        }
        VideoStoryPlaybackDataRepository.INSTANCE.get().setToggleButtonMethodHandled(true);
    }

    private final void handleGenericPlaybackMethod(TriggerExternalGenericPlaybackMethod method) {
        List<PlayableContent> listContent = method.content();
        PlayableContent playableContentUpdateTrackDurationToMilliSeconds = listContent == null ? null : listContent.get(0);
        if (playableContentUpdateTrackDurationToMilliSeconds instanceof Track) {
            playableContentUpdateTrackDurationToMilliSeconds = updateTrackDurationToMilliSeconds((Track) playableContentUpdateTrackDurationToMilliSeconds);
        }
        if (playableContentUpdateTrackDurationToMilliSeconds != null) {
            getPlaybackProvider().play(playableContentUpdateTrackDurationToMilliSeconds, getContext());
            getPlaybackProvider().seekTo(toMilliSeconds(0));
        }
        getVideoStoryPlayback().updateTerminationReason(TerminationReason.ACTION_BUTTON_CLICKED);
        close();
    }

    private final Track updateTrackDurationToMilliSeconds(Track track) {
        ImmutableTrack immutableTrackBuild = ImmutableTrack.builder().from(track).duration(Integer.valueOf(toMilliSeconds(track.duration()))).build();
        Intrinsics.checkNotNullExpressionValue(immutableTrackBuild, "builder().from(track).du…rack.duration())).build()");
        return immutableTrackBuild;
    }

    private final int toMilliSeconds(Integer duration) {
        return (int) TimeUnit.MILLISECONDS.convert(duration == null ? 0L : duration.intValue(), TimeUnit.SECONDS);
    }
}
