package com.amazon.mp3.video.stories.storyqueue;

import CoreInterface.v1_0.Element;
import CoreInterface.v1_0.Method;
import ExternalActionInterface.v1_0.PlayableContent;
import ExternalActionInterface.v1_0.VideoStoryContent;
import Touch.PlaybackTemplateInterface.v1_0.PlaybackTemplate;
import Touch.SwipeablePagesTemplateInterface.v1_0.ThumbnailElement;
import Touch.WidgetsInterface.v1_0.ButtonElement;
import Touch.WidgetsInterface.v1_0.LabeledIconButtonElement;
import Touch.WidgetsInterface.v1_0.TextButtonElement;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.video.stories.Mappers;
import com.amazon.mp3.video.stories.VideoChangeReason;
import com.amazon.mp3.video.stories.VideoStoryArtistMapProvider;
import com.amazon.mp3.video.stories.VideoStoryBaseButton;
import com.amazon.mp3.video.stories.VideoStoryFragment;
import com.amazon.mp3.video.stories.VideoStoryPlaybackDataRepository;
import com.amazon.mp3.video.stories.playback.PlaybackRequest;
import com.amazon.mp3.video.stories.videostoryplayback.VideoStoryPlayback;
import com.amazon.music.explore.ExploreResources;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.skyfire.ui.widgets.views.IconButtonView;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.utils.RoundedCornersTransformation;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import com.google.android.gms.cast.MediaTrack;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import java.io.IOException;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VideoStoryItemFragment.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 \u0084\u00012\u00020\u0001:\u0002\u0084\u0001B\t¢\u0006\u0006\b\u0082\u0001\u0010\u0083\u0001J \u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002JO\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u00042\b\u0010\u000e\u001a\u0004\u0018\u00010\u0004H\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\n\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0002J\b\u0010\u0013\u001a\u00020\u0007H\u0002J\b\u0010\u0014\u001a\u00020\u0007H\u0002J\b\u0010\u0015\u001a\u00020\u0007H\u0002J\b\u0010\u0016\u001a\u00020\u0007H\u0002J\b\u0010\u0017\u001a\u00020\u0007H\u0002J\b\u0010\u0019\u001a\u00020\u0018H\u0002J\u0010\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001b\u001a\u00020\u001aH\u0002J\u0010\u0010!\u001a\u00020 2\u0006\u0010\u001f\u001a\u00020\u001eH\u0002J\u0010\u0010#\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u0011H\u0002J\b\u0010$\u001a\u00020\u0007H\u0002J\b\u0010%\u001a\u00020\u0007H\u0002J\u0010\u0010(\u001a\u00020\u00072\u0006\u0010'\u001a\u00020&H\u0002J\b\u0010)\u001a\u00020\u0007H\u0002J\b\u0010*\u001a\u00020\u0007H\u0002J(\u0010+\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0002J&\u00102\u001a\u0004\u0018\u00010\u00022\u0006\u0010-\u001a\u00020,2\b\u0010/\u001a\u0004\u0018\u00010.2\b\u00101\u001a\u0004\u0018\u000100H\u0016J\u001a\u00103\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\b\u00101\u001a\u0004\u0018\u000100H\u0017J\u000e\u00106\u001a\u00020\u00072\u0006\u00105\u001a\u000204J%\u00109\u001a\u00020\u00072\n\b\u0002\u00107\u001a\u0004\u0018\u0001042\n\b\u0002\u00108\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b9\u0010:J\u000e\u0010<\u001a\u00020\u00072\u0006\u0010;\u001a\u000204J\u0006\u0010=\u001a\u00020\u0007J\u0006\u0010>\u001a\u00020\u0007R\u0018\u0010@\u001a\u0004\u0018\u00010?8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b@\u0010AR\u0016\u0010B\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bB\u0010CR\u001e\u0010F\u001a\n\u0012\u0004\u0012\u00020E\u0018\u00010D8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bF\u0010GR\u0018\u0010I\u001a\u0004\u0018\u00010H8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bI\u0010JR\u0016\u0010K\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bK\u0010LR$\u0010N\u001a\u0004\u0018\u00010M8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bN\u0010O\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010SR\u0016\u0010U\u001a\u00020T8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bU\u0010VR\u0016\u0010W\u001a\u00020\u00188\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bW\u0010XR\u0016\u0010Z\u001a\u00020Y8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bZ\u0010[R\u0016\u0010\\\u001a\u00020Y8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\\\u0010[R\u0016\u0010]\u001a\u00020Y8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b]\u0010[R\u0016\u0010^\u001a\u00020\u001c8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b^\u0010_R\u0016\u00105\u001a\u0002048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b5\u0010`R\u0016\u0010a\u001a\u00020\u001c8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\ba\u0010_R\u0014\u0010c\u001a\u00020b8\u0002X\u0082D¢\u0006\u0006\n\u0004\bc\u0010dR\u0014\u0010f\u001a\u00020b8\u0002X\u0082D¢\u0006\u0006\n\u0004\be\u0010dR\u0014\u0010g\u001a\u00020\u00118\u0002X\u0082D¢\u0006\u0006\n\u0004\bg\u0010CR\u0014\u0010h\u001a\u00020\u00118\u0002X\u0082D¢\u0006\u0006\n\u0004\bh\u0010CR\u0014\u0010i\u001a\u00020\u00048\u0002X\u0082D¢\u0006\u0006\n\u0004\bi\u0010LR\u0014\u0010j\u001a\u00020\u00048\u0002X\u0082D¢\u0006\u0006\n\u0004\bj\u0010LR\u0014\u0010k\u001a\u00020\u00048\u0002X\u0082D¢\u0006\u0006\n\u0004\bk\u0010LR\u0016\u0010l\u001a\u00020\u00188\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bl\u0010XR\u0016\u0010m\u001a\u00020\u00188\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bm\u0010XR\u0016\u0010n\u001a\u00020b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bn\u0010dR\u0016\u0010o\u001a\u00020b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bo\u0010dR\u001b\u0010t\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bp\u0010q\u001a\u0004\br\u0010sR\u001b\u0010w\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bu\u0010q\u001a\u0004\bv\u0010sR\"\u0010y\u001a\u00020x8\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\by\u0010z\u001a\u0004\b{\u0010|\"\u0004\b}\u0010~R\u0018\u0010\u007f\u001a\u0004\u0018\u00010\u001c8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u007f\u0010_R\u001b\u0010\u0080\u0001\u001a\u0004\u0018\u00010 8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0080\u0001\u0010\u0081\u0001¨\u0006\u0085\u0001"}, d2 = {"Lcom/amazon/mp3/video/stories/storyqueue/VideoStoryItemFragment;", "Landroidx/fragment/app/Fragment;", "Landroid/view/View;", "view", "", "side", "padding", "", "setPlayPauseButtonSize", "width", "height", "left", "right", "top", "bottom", "setMargins", "(Landroid/view/View;IILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "", "getScreenSize", "animatePlayPauseButton", "bind", "setFollowContainerMargins", "bindJumpOffButton", "bindFollowButton", "Landroid/widget/LinearLayout;", "getLinearContainerLayoutForJumpOffButton", "LTouch/SwipeablePagesTemplateInterface/v1_0/ThumbnailElement;", "buttonElement", "Landroid/widget/ImageView;", "initializeArtistImageAttributes", "LExternalActionInterface/v1_0/PlayableContent;", "playableContent", "Lcom/amazon/ui/foundations/views/BaseButton;", "initializeFollowButtonAttributes", "jumpOffText", "initializeJumpOffButton", "bindPreviewImage", "bindActions", "Lcom/amazon/music/skyfire/ui/widgets/views/IconButtonView;", "iconButton", "setIconButtonLabelStyle", "bindText", "setFontFamily", "setWindowInsets", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "Landroid/os/Bundle;", "savedInstanceState", "onCreateView", "onViewCreated", "", "playWhenReady", "setPlayWhenReady", "isFollowing", "artistAsin", "updateFollowButtonText", "(Ljava/lang/Boolean;Ljava/lang/String;)V", "condition", "updateText", "showPreviewImage", "hidePreviewImage", "LTouch/PlaybackTemplateInterface/v1_0/PlaybackTemplate;", "videoQueueItem", "LTouch/PlaybackTemplateInterface/v1_0/PlaybackTemplate;", "ownerId", "Ljava/lang/String;", "Lcom/amazon/music/skyfire/platform/MethodsDispatcher;", "LCoreInterface/v1_0/Method;", "methodsDispatcher", "Lcom/amazon/music/skyfire/platform/MethodsDispatcher;", "Lcom/amazon/mp3/video/stories/videostoryplayback/VideoStoryPlayback;", "videoStoryPlayback", "Lcom/amazon/mp3/video/stories/videostoryplayback/VideoStoryPlayback;", "queuePosition", "I", "Landroid/view/TextureView;", "textureView", "Landroid/view/TextureView;", "getTextureView", "()Landroid/view/TextureView;", "setTextureView", "(Landroid/view/TextureView;)V", "Landroid/widget/RelativeLayout;", "interfaceContainer", "Landroid/widget/RelativeLayout;", "actionsContainer", "Landroid/widget/LinearLayout;", "Landroid/widget/TextView;", "title", "Landroid/widget/TextView;", MediaTrack.ROLE_SUBTITLE, "artistName", "previewImage", "Landroid/widget/ImageView;", "Z", "playPauseButton", "", "THRESHOLD_WIDTH", "F", "CLICK_ACTION_THRESHOLD$1", "CLICK_ACTION_THRESHOLD", "XS_SCREEN", "SMALL_SCREEN", "SWIPE_MIN_DISTANCE", "ACTION_MAX_TIME", "TAP_MAX_DISTANCE", "jumpOffLayout", "followLayout", "initialActionDownYCoordinate", "actionMoveYCoordinateDifference", "screenHeight$delegate", "Lkotlin/Lazy;", "getScreenHeight", "()I", "screenHeight", "thresholdFlingDistanceToClose$delegate", "getThresholdFlingDistanceToClose", "thresholdFlingDistanceToClose", "Lcom/amazon/mp3/video/stories/VideoStoryFragment;", "parentFragment", "Lcom/amazon/mp3/video/stories/VideoStoryFragment;", "getParentFragment", "()Lcom/amazon/mp3/video/stories/VideoStoryFragment;", "setParentFragment", "(Lcom/amazon/mp3/video/stories/VideoStoryFragment;)V", "artistView", "followButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class VideoStoryItemFragment extends Fragment {
    private float actionMoveYCoordinateDifference;
    private LinearLayout actionsContainer;
    private TextView artistName;
    private ImageView artistView;
    private BaseButton followButton;
    private LinearLayout followLayout;
    private float initialActionDownYCoordinate;
    private RelativeLayout interfaceContainer;
    private LinearLayout jumpOffLayout;
    private MethodsDispatcher<Method> methodsDispatcher;
    public VideoStoryFragment parentFragment;
    private ImageView playPauseButton;
    private boolean playWhenReady;
    private ImageView previewImage;
    private int queuePosition;
    private TextView subtitle;
    private TextureView textureView;
    private TextView title;
    private PlaybackTemplate videoQueueItem;
    private VideoStoryPlayback videoStoryPlayback;
    private String ownerId = "";
    private final float THRESHOLD_WIDTH = 359.0f;

    /* JADX INFO: renamed from: CLICK_ACTION_THRESHOLD$1, reason: from kotlin metadata */
    private final float CLICK_ACTION_THRESHOLD = 200.0f;
    private final String XS_SCREEN = "XS_SCREEN";
    private final String SMALL_SCREEN = "SMALL_SCREEN";
    private final int SWIPE_MIN_DISTANCE = 200;
    private final int ACTION_MAX_TIME = 200;
    private final int TAP_MAX_DISTANCE = 10;

    /* JADX INFO: renamed from: screenHeight$delegate, reason: from kotlin metadata */
    private final Lazy screenHeight = LazyKt__LazyJVMKt.lazy(new Function0<Integer>() { // from class: com.amazon.mp3.video.stories.storyqueue.VideoStoryItemFragment$screenHeight$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Integer invoke() {
            return Integer.valueOf(Resources.getSystem().getDisplayMetrics().heightPixels);
        }
    });

    /* JADX INFO: renamed from: thresholdFlingDistanceToClose$delegate, reason: from kotlin metadata */
    private final Lazy thresholdFlingDistanceToClose = LazyKt__LazyJVMKt.lazy(new Function0<Integer>() { // from class: com.amazon.mp3.video.stories.storyqueue.VideoStoryItemFragment$thresholdFlingDistanceToClose$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Integer invoke() {
            return Integer.valueOf(Resources.getSystem().getDisplayMetrics().heightPixels / 4);
        }
    });

    public final TextureView getTextureView() {
        return this.textureView;
    }

    private final int getScreenHeight() {
        return ((Number) this.screenHeight.getValue()).intValue();
    }

    private final int getThresholdFlingDistanceToClose() {
        return ((Number) this.thresholdFlingDistanceToClose.getValue()).intValue();
    }

    @Override // androidx.fragment.app.Fragment
    public final VideoStoryFragment getParentFragment() {
        VideoStoryFragment videoStoryFragment = this.parentFragment;
        if (videoStoryFragment != null) {
            return videoStoryFragment;
        }
        Intrinsics.throwUninitializedPropertyAccessException("parentFragment");
        return null;
    }

    public final void setParentFragment(VideoStoryFragment videoStoryFragment) {
        Intrinsics.checkNotNullParameter(videoStoryFragment, "<set-?>");
        this.parentFragment = videoStoryFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.video_story_view, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    @SuppressLint({"ClickableViewAccessibility"})
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Resources resources;
        Resources resources2;
        TextView textView;
        Resources resources3;
        Resources resources4;
        Resources resources5;
        Resources resources6;
        RelativeLayout relativeLayout;
        Resources resources7;
        Resources resources8;
        TextView textView2;
        Resources resources9;
        Resources resources10;
        TextView textView3;
        Resources resources11;
        Resources resources12;
        Resources resources13;
        Resources resources14;
        RelativeLayout relativeLayout2;
        Resources resources15;
        Resources resources16;
        VideoStoryPlayback videoStoryPlayback;
        Intrinsics.checkNotNullParameter(view, "view");
        Fragment parentFragment = getParentFragment();
        if (parentFragment == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.video.stories.VideoStoryFragment");
        }
        setParentFragment((VideoStoryFragment) parentFragment);
        this.methodsDispatcher = getParentFragment().getMethodsDispatcher();
        this.ownerId = getParentFragment().getOwnerId();
        this.videoStoryPlayback = getParentFragment().getVideoStoryPlayback();
        View viewFindViewById = view.findViewById(R.id.play_pause_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "view.findViewById(R.id.play_pause_button)");
        ImageView imageView = (ImageView) viewFindViewById;
        this.playPauseButton = imageView;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playPauseButton");
            imageView = null;
        }
        imageView.setVisibility(4);
        View viewFindViewById2 = view.findViewById(R.id.jump_off_container);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "view.findViewById(R.id.jump_off_container)");
        this.jumpOffLayout = (LinearLayout) viewFindViewById2;
        View viewFindViewById3 = view.findViewById(R.id.follow_container);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "view.findViewById(R.id.follow_container)");
        this.followLayout = (LinearLayout) viewFindViewById3;
        View viewFindViewById4 = view.findViewById(R.id.video_story_interface_container);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "view.findViewById(R.id.v…tory_interface_container)");
        this.interfaceContainer = (RelativeLayout) viewFindViewById4;
        View viewFindViewById5 = view.findViewById(R.id.video_story_actions_container);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "view.findViewById(R.id.v…_story_actions_container)");
        this.actionsContainer = (LinearLayout) viewFindViewById5;
        this.textureView = (TextureView) view.findViewById(R.id.video_story_texture_view);
        View viewFindViewById6 = view.findViewById(R.id.video_story_preview_image);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "view.findViewById(R.id.video_story_preview_image)");
        this.previewImage = (ImageView) viewFindViewById6;
        View viewFindViewById7 = view.findViewById(R.id.video_story_primary_text);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById7, "view.findViewById(R.id.video_story_primary_text)");
        this.title = (TextView) viewFindViewById7;
        View viewFindViewById8 = view.findViewById(R.id.video_story_secondary_text);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById8, "view.findViewById(R.id.video_story_secondary_text)");
        this.subtitle = (TextView) viewFindViewById8;
        View viewFindViewById9 = view.findViewById(R.id.video_story_tertiary_text);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById9, "view.findViewById(R.id.video_story_tertiary_text)");
        this.artistName = (TextView) viewFindViewById9;
        Bundle arguments = getArguments();
        if (arguments != null) {
            try {
                this.videoQueueItem = (PlaybackTemplate) Mappers.INSTANCE.mapper().readValue(arguments.getString("VIDEO_STORY_ITEM_ELEMENT"), PlaybackTemplate.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.queuePosition = arguments.getInt("VIDEO_STORY_ITEM_POSITION");
            setWindowInsets(arguments.getInt("INSET_LEFT"), arguments.getInt("INSET_TOP"), arguments.getInt("INSET_RIGHT"), arguments.getInt("INSET_BOTTOM"));
            if (this.playWhenReady) {
                this.playWhenReady = false;
                if (getParentFragment().isActivityInForeground() && (videoStoryPlayback = this.videoStoryPlayback) != null) {
                    videoStoryPlayback.playQueuePosition(this.queuePosition, VideoChangeReason.NONE);
                }
            }
        }
        ImageView imageView2 = this.playPauseButton;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playPauseButton");
            imageView2 = null;
        }
        imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.video.stories.storyqueue.VideoStoryItemFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                VideoStoryItemFragment.m1686onViewCreated$lambda0(this.f$0, view2);
            }
        });
        TextureView textureView = this.textureView;
        if (textureView == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.TextureView");
        }
        textureView.setOnTouchListener(new View.OnTouchListener() { // from class: com.amazon.mp3.video.stories.storyqueue.VideoStoryItemFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view2, MotionEvent motionEvent) {
                return VideoStoryItemFragment.m1687onViewCreated$lambda2(this.f$0, view2, motionEvent);
            }
        });
        bind();
        if (Intrinsics.areEqual(getScreenSize(), this.XS_SCREEN)) {
            TextView textView4 = this.title;
            if (textView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("title");
                textView2 = null;
            } else {
                textView2 = textView4;
            }
            Context context = getContext();
            Integer numValueOf = (context == null || (resources9 = context.getResources()) == null) ? null : Integer.valueOf((int) resources9.getDimension(R.dimen.video_player_base_margin));
            Context context2 = getContext();
            setMargins(textView2, -2, -2, numValueOf, (context2 == null || (resources10 = context2.getResources()) == null) ? null : Integer.valueOf((int) resources10.getDimension(R.dimen.video_player_base_margin)), 0, 0);
            TextView textView5 = this.subtitle;
            if (textView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(MediaTrack.ROLE_SUBTITLE);
                textView3 = null;
            } else {
                textView3 = textView5;
            }
            Context context3 = getContext();
            Integer numValueOf2 = (context3 == null || (resources11 = context3.getResources()) == null) ? null : Integer.valueOf((int) resources11.getDimension(R.dimen.video_player_base_margin));
            Context context4 = getContext();
            Integer numValueOf3 = (context4 == null || (resources12 = context4.getResources()) == null) ? null : Integer.valueOf((int) resources12.getDimension(R.dimen.video_player_base_margin));
            Context context5 = getContext();
            Integer numValueOf4 = (context5 == null || (resources13 = context5.getResources()) == null) ? null : Integer.valueOf((int) resources13.getDimension(R.dimen.video_player_mini_margin));
            Context context6 = getContext();
            setMargins(textView3, -2, -2, numValueOf2, numValueOf3, numValueOf4, (context6 == null || (resources14 = context6.getResources()) == null) ? null : Integer.valueOf((int) resources14.getDimension(R.dimen.spacer_base)));
            Context context7 = getContext();
            if (context7 != null && (resources16 = context7.getResources()) != null) {
                float dimension = resources16.getDimension(R.dimen.spacer_40);
                ImageView imageView3 = this.playPauseButton;
                if (imageView3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("playPauseButton");
                    imageView3 = null;
                }
                int i = (int) dimension;
                setPlayPauseButtonSize(imageView3, i, i / 4);
            }
            RelativeLayout relativeLayout3 = this.interfaceContainer;
            if (relativeLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("interfaceContainer");
                relativeLayout2 = null;
            } else {
                relativeLayout2 = relativeLayout3;
            }
            Context context8 = getContext();
            setMargins(relativeLayout2, -1, -2, 0, 0, 0, (context8 == null || (resources15 = context8.getResources()) == null) ? null : Integer.valueOf((int) resources15.getDimension(R.dimen.spacer_mini)));
            return;
        }
        TextView textView6 = this.title;
        if (textView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("title");
            textView6 = null;
        }
        Context context9 = getContext();
        Integer numValueOf5 = (context9 == null || (resources = context9.getResources()) == null) ? null : Integer.valueOf((int) resources.getDimension(R.dimen.large_padding));
        Context context10 = getContext();
        setMargins(textView6, -2, -2, numValueOf5, (context10 == null || (resources2 = context10.getResources()) == null) ? null : Integer.valueOf((int) resources2.getDimension(R.dimen.large_padding)), 0, 0);
        TextView textView7 = this.subtitle;
        if (textView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(MediaTrack.ROLE_SUBTITLE);
            textView = null;
        } else {
            textView = textView7;
        }
        Context context11 = getContext();
        Integer numValueOf6 = (context11 == null || (resources3 = context11.getResources()) == null) ? null : Integer.valueOf((int) resources3.getDimension(R.dimen.large_padding));
        Context context12 = getContext();
        Integer numValueOf7 = (context12 == null || (resources4 = context12.getResources()) == null) ? null : Integer.valueOf((int) resources4.getDimension(R.dimen.large_padding));
        Context context13 = getContext();
        Integer numValueOf8 = (context13 == null || (resources5 = context13.getResources()) == null) ? null : Integer.valueOf((int) resources5.getDimension(R.dimen.video_player_mini_margin));
        Context context14 = getContext();
        setMargins(textView, -2, -2, numValueOf6, numValueOf7, numValueOf8, (context14 == null || (resources6 = context14.getResources()) == null) ? null : Integer.valueOf((int) resources6.getDimension(R.dimen.spacer_base)));
        Context context15 = getContext();
        if (context15 != null && (resources8 = context15.getResources()) != null) {
            float dimension2 = resources8.getDimension(R.dimen.bottom_bar_height);
            ImageView imageView4 = this.playPauseButton;
            if (imageView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("playPauseButton");
                imageView4 = null;
            }
            int i2 = (int) dimension2;
            setPlayPauseButtonSize(imageView4, i2, i2 / 4);
        }
        RelativeLayout relativeLayout4 = this.interfaceContainer;
        if (relativeLayout4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("interfaceContainer");
            relativeLayout = null;
        } else {
            relativeLayout = relativeLayout4;
        }
        Context context16 = getContext();
        setMargins(relativeLayout, -1, -2, 0, 0, 0, (context16 == null || (resources7 = context16.getResources()) == null) ? null : Integer.valueOf((int) resources7.getDimension(R.dimen.spacer_mini)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-0, reason: not valid java name */
    public static final void m1686onViewCreated$lambda0(VideoStoryItemFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        VideoStoryPlayback videoStoryPlayback = this$0.videoStoryPlayback;
        if (videoStoryPlayback != null) {
            videoStoryPlayback.togglePlaying(VideoChangeReason.PLAY_PAUSE);
        }
        this$0.animatePlayPauseButton();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-2, reason: not valid java name */
    public static final boolean m1687onViewCreated$lambda2(final VideoStoryItemFragment this$0, View noName_0, MotionEvent event) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(event, "event");
        int action = event.getAction();
        if (action == 0) {
            this$0.initialActionDownYCoordinate = event.getRawY();
            return true;
        }
        boolean z = false;
        if (action != 1) {
            if (action != 2) {
                return false;
            }
            this$0.actionMoveYCoordinateDifference = event.getRawY() - this$0.initialActionDownYCoordinate;
            if (event.getRawY() <= this$0.initialActionDownYCoordinate || this$0.actionMoveYCoordinateDifference < 10.0f) {
                return true;
            }
            this$0.getParentFragment().setPageSwipeForVideoPager(false);
            this$0.getParentFragment().getRootView().animate().translationY(this$0.actionMoveYCoordinateDifference).setDuration(0L).start();
            return false;
        }
        this$0.getParentFragment().setPageSwipeForVideoPager(true);
        float rawY = event.getRawY() - this$0.initialActionDownYCoordinate;
        this$0.actionMoveYCoordinateDifference = rawY;
        double d = this$0.TAP_MAX_DISTANCE;
        double d2 = rawY;
        if (0.0d <= d2 && d2 <= d) {
            z = true;
        }
        if (z) {
            VideoStoryPlayback videoStoryPlayback = this$0.videoStoryPlayback;
            if (videoStoryPlayback != null) {
                videoStoryPlayback.togglePlaying(VideoChangeReason.PLAY_PAUSE);
            }
            this$0.animatePlayPauseButton();
        } else if (rawY >= this$0.getThresholdFlingDistanceToClose()) {
            this$0.getParentFragment().getRootView().animate().translationY(this$0.getScreenHeight()).setDuration(250L).withEndAction(new Runnable() { // from class: com.amazon.mp3.video.stories.storyqueue.VideoStoryItemFragment$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    VideoStoryItemFragment.m1688onViewCreated$lambda2$lambda1(this.f$0);
                }
            }).start();
        } else {
            this$0.getParentFragment().getRootView().animate().translationY(0.0f).setDuration(250L).start();
            this$0.initialActionDownYCoordinate = 0.0f;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onViewCreated$lambda-2$lambda-1, reason: not valid java name */
    public static final void m1688onViewCreated$lambda2$lambda1(VideoStoryItemFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getParentFragment().setSwipeDownClose(true);
        VideoStoryPlayback.sendUIClickEvent$default(this$0.getParentFragment().getVideoStoryPlayback(), ActionType.CLOSE_VIDEO_STORY_SCREEN, InteractionType.SWIPE, null, 4, null);
        PlaybackRequest currentRequest = this$0.getParentFragment().getVideoStoryPlayback().getCurrentRequest();
        if (currentRequest != null) {
            currentRequest.setTerminationReason(TerminationReason.CLOSE_PLAYER);
        }
        this$0.getParentFragment().close();
    }

    private final void setPlayPauseButtonSize(View view, int side, int padding) {
        view.getLayoutParams().height = side;
        view.getLayoutParams().width = side;
        view.setPadding(padding, padding, padding, padding);
    }

    private final void setMargins(View view, int width, int height, Integer left, Integer right, Integer top, Integer bottom) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(view, width, height, Boolean.FALSE, left, top, right, bottom);
    }

    private final String getScreenSize() {
        if (r0.widthPixels / getResources().getDisplayMetrics().density <= this.THRESHOLD_WIDTH) {
            return this.XS_SCREEN;
        }
        return this.SMALL_SCREEN;
    }

    private final void animatePlayPauseButton() {
        VideoStoryPlayback videoStoryPlayback = this.videoStoryPlayback;
        boolean z = false;
        if (videoStoryPlayback != null && videoStoryPlayback.isPlaying()) {
            z = true;
        }
        ImageView imageView = null;
        if (z) {
            ImageView imageView2 = this.playPauseButton;
            if (imageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("playPauseButton");
                imageView2 = null;
            }
            imageView2.setImageResource(R.drawable.ic_playback_play);
        } else {
            ImageView imageView3 = this.playPauseButton;
            if (imageView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("playPauseButton");
                imageView3 = null;
            }
            imageView3.setImageResource(R.drawable.ic_playback_pause);
        }
        Animation animationLoadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.play_pause_fade_in);
        Intrinsics.checkNotNullExpressionValue(animationLoadAnimation, "loadAnimation(context, R.anim.play_pause_fade_in)");
        final Animation animationLoadAnimation2 = AnimationUtils.loadAnimation(getContext(), R.anim.play_pause_fade_out_zoom_in);
        Intrinsics.checkNotNullExpressionValue(animationLoadAnimation2, "loadAnimation(context, R…y_pause_fade_out_zoom_in)");
        final Animation animationLoadAnimation3 = AnimationUtils.loadAnimation(getContext(), R.anim.scale_down);
        Intrinsics.checkNotNullExpressionValue(animationLoadAnimation3, "loadAnimation(context, R.anim.scale_down)");
        animationLoadAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.amazon.mp3.video.stories.storyqueue.VideoStoryItemFragment.animatePlayPauseButton.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                ImageView imageView4 = VideoStoryItemFragment.this.playPauseButton;
                if (imageView4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("playPauseButton");
                    imageView4 = null;
                }
                imageView4.startAnimation(animationLoadAnimation2);
            }
        });
        ImageView imageView4 = this.playPauseButton;
        if (imageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playPauseButton");
        } else {
            imageView = imageView4;
        }
        imageView.startAnimation(animationLoadAnimation);
        animationLoadAnimation2.setAnimationListener(new Animation.AnimationListener() { // from class: com.amazon.mp3.video.stories.storyqueue.VideoStoryItemFragment.animatePlayPauseButton.2
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                ImageView imageView5 = VideoStoryItemFragment.this.playPauseButton;
                if (imageView5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("playPauseButton");
                    imageView5 = null;
                }
                imageView5.startAnimation(animationLoadAnimation3);
            }
        });
    }

    public final void setPlayWhenReady(boolean playWhenReady) {
        this.playWhenReady = playWhenReady;
    }

    private final void bind() {
        bindPreviewImage();
        bindActions();
        bindText();
        if (AmazonApplication.getCapabilities().isArtistFollowForVideoStoriesSupported()) {
            PlaybackTemplate playbackTemplate = this.videoQueueItem;
            List<Element> listSecondaryButtons = playbackTemplate == null ? null : playbackTemplate.secondaryButtons();
            if (!(listSecondaryButtons == null || listSecondaryButtons.isEmpty())) {
                setFollowContainerMargins();
                bindFollowButton();
            }
        }
        bindJumpOffButton();
    }

    private final void setFollowContainerMargins() {
        Resources resources;
        Resources resources2;
        Resources resources3;
        LayoutParamUtils layoutParamUtils = LayoutParamUtils.INSTANCE;
        LinearLayout linearLayout = this.followLayout;
        Integer numValueOf = null;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("followLayout");
            linearLayout = null;
        }
        Boolean bool = Boolean.FALSE;
        Context context = getContext();
        Integer numValueOf2 = (context == null || (resources = context.getResources()) == null) ? null : Integer.valueOf((int) resources.getDimension(R.dimen.spacer_large));
        Context context2 = getContext();
        Integer numValueOf3 = (context2 == null || (resources2 = context2.getResources()) == null) ? null : Integer.valueOf((int) resources2.getDimension(R.dimen.spacer_small));
        Context context3 = getContext();
        if (context3 != null && (resources3 = context3.getResources()) != null) {
            numValueOf = Integer.valueOf((int) resources3.getDimension(R.dimen.spacer_mini));
        }
        layoutParamUtils.setLayoutMargins(linearLayout, -2, -2, bool, numValueOf2, 0, numValueOf3, numValueOf);
    }

    private final void bindJumpOffButton() {
        PlaybackTemplate playbackTemplate = this.videoQueueItem;
        List<ButtonElement> listActionButtons = playbackTemplate == null ? null : playbackTemplate.actionButtons();
        if (listActionButtons != null) {
            for (final ButtonElement buttonElement : listActionButtons) {
                if (buttonElement instanceof LabeledIconButtonElement) {
                    String strLabel = ((LabeledIconButtonElement) buttonElement).label();
                    Intrinsics.checkNotNullExpressionValue(strLabel, "action.label()");
                    LinearLayout linearLayoutInitializeJumpOffButton = initializeJumpOffButton(strLabel);
                    LinearLayout linearLayout = this.jumpOffLayout;
                    if (linearLayout == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("jumpOffLayout");
                        linearLayout = null;
                    }
                    linearLayout.addView(linearLayoutInitializeJumpOffButton);
                    linearLayoutInitializeJumpOffButton.getChildAt(0).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.video.stories.storyqueue.VideoStoryItemFragment$$ExternalSyntheticLambda5
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            VideoStoryItemFragment.m1685bindJumpOffButton$lambda5(this.f$0, buttonElement, view);
                        }
                    });
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bindJumpOffButton$lambda-5, reason: not valid java name */
    public static final void m1685bindJumpOffButton$lambda5(VideoStoryItemFragment this$0, ButtonElement buttonElement, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (ConnectivityUtil.hasAnyInternetConnection(this$0.getContext())) {
            this$0.getParentFragment().getVideoStoryPlayback().updateTerminationReason(TerminationReason.ACTION_BUTTON_CLICKED);
            MethodsDispatcher<Method> methodsDispatcher = this$0.methodsDispatcher;
            if (methodsDispatcher == null) {
                return;
            }
            String str = this$0.ownerId;
            List<Method> listOnItemSelected = buttonElement.onItemSelected();
            Intrinsics.checkNotNullExpressionValue(listOnItemSelected, "action.onItemSelected()");
            methodsDispatcher.dispatchMethods(str, listOnItemSelected);
            return;
        }
        ConnectivityUtil.checkConnectivityAndShowDialog(this$0.getContext());
        this$0.getParentFragment().close();
    }

    private final void bindFollowButton() {
        View childAt;
        PlayableContent playableContent;
        PlaybackTemplate playbackTemplate = this.videoQueueItem;
        List<Element> listSecondaryButtons = playbackTemplate == null ? null : playbackTemplate.secondaryButtons();
        if (listSecondaryButtons == null) {
            return;
        }
        for (final Element element : listSecondaryButtons) {
            if (element instanceof ThumbnailElement) {
                ImageView imageViewInitializeArtistImageAttributes = initializeArtistImageAttributes((ThumbnailElement) element);
                this.artistView = imageViewInitializeArtistImageAttributes;
                if (imageViewInitializeArtistImageAttributes != null) {
                    imageViewInitializeArtistImageAttributes.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.video.stories.storyqueue.VideoStoryItemFragment$$ExternalSyntheticLambda3
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            VideoStoryItemFragment.m1683bindFollowButton$lambda9$lambda6(this.f$0, element, view);
                        }
                    });
                }
            } else if (element instanceof TextButtonElement) {
                PlaybackTemplate playbackTemplate2 = this.videoQueueItem;
                if (playbackTemplate2 != null && (playableContent = playbackTemplate2.playableContent()) != null) {
                    this.followButton = initializeFollowButtonAttributes(playableContent);
                    updateFollowButtonText$default(this, null, null, 3, null);
                }
                BaseButton baseButton = this.followButton;
                if (baseButton != null && (childAt = baseButton.getChildAt(0)) != null) {
                    childAt.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.video.stories.storyqueue.VideoStoryItemFragment$$ExternalSyntheticLambda4
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            VideoStoryItemFragment.m1684bindFollowButton$lambda9$lambda8(this.f$0, element, view);
                        }
                    });
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bindFollowButton$lambda-9$lambda-6, reason: not valid java name */
    public static final void m1683bindFollowButton$lambda9$lambda6(VideoStoryItemFragment this$0, Element element, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getParentFragment().getVideoStoryPlayback().updateTerminationReason(TerminationReason.ARTIST_IMAGE_CLICKED);
        MethodsDispatcher<Method> methodsDispatcher = this$0.methodsDispatcher;
        if (methodsDispatcher == null) {
            return;
        }
        String str = this$0.ownerId;
        List<Method> listOnItemSelected = ((ThumbnailElement) element).onItemSelected();
        Intrinsics.checkNotNullExpressionValue(listOnItemSelected, "action.onItemSelected()");
        methodsDispatcher.dispatchMethods(str, listOnItemSelected);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bindFollowButton$lambda-9$lambda-8, reason: not valid java name */
    public static final void m1684bindFollowButton$lambda9$lambda8(VideoStoryItemFragment this$0, Element element, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        VideoStoryPlaybackDataRepository.Companion companion = VideoStoryPlaybackDataRepository.INSTANCE;
        VideoStoryPlaybackDataRepository videoStoryPlaybackDataRepository = companion.get();
        PlaybackTemplate playbackTemplate = this$0.videoQueueItem;
        VideoStoryContent videoStoryContent = (VideoStoryContent) (playbackTemplate == null ? null : playbackTemplate.playableContent());
        videoStoryPlaybackDataRepository.setAsinToBeUpdated(videoStoryContent != null ? videoStoryContent.asin() : null);
        companion.get().setToggleButtonMethodHandled(false);
        MethodsDispatcher<Method> methodsDispatcher = this$0.methodsDispatcher;
        if (methodsDispatcher == null) {
            return;
        }
        String str = this$0.ownerId;
        List<Method> listOnItemSelected = ((TextButtonElement) element).onItemSelected();
        Intrinsics.checkNotNullExpressionValue(listOnItemSelected, "action.onItemSelected()");
        methodsDispatcher.dispatchMethods(str, listOnItemSelected);
    }

    private final LinearLayout getLinearContainerLayoutForJumpOffButton() {
        LinearLayout linearLayout = new LinearLayout(requireContext());
        linearLayout.setOrientation(0);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2, 1.0f);
        layoutParams.setMargins((int) getResources().getDimension(R.dimen.spacer_8), 0, (int) getResources().getDimension(R.dimen.spacer_8), 0);
        linearLayout.setLayoutParams(layoutParams);
        return linearLayout;
    }

    private final ImageView initializeArtistImageAttributes(ThumbnailElement buttonElement) {
        ImageView imageView = new ImageView(requireContext());
        imageView.setId(R.id.video_story_artist_image);
        float dimension = getResources().getDimension(R.dimen.video_story_artist_view_size);
        RoundedCornersTransformation roundedCornersTransformation = new RoundedCornersTransformation(dimension / 2);
        int i = (int) dimension;
        Picasso.get().load(buttonElement.thumbnailImage()).resize(i, i).transform(roundedCornersTransformation).centerCrop().into(imageView);
        LinearLayout linearLayout = this.followLayout;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("followLayout");
            linearLayout = null;
        }
        linearLayout.addView(imageView);
        return imageView;
    }

    private final BaseButton initializeFollowButtonAttributes(PlayableContent playableContent) {
        StyleSheet mStyleSheet = getParentFragment().getMStyleSheet();
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        VideoStoryBaseButton videoStoryBaseButton = new VideoStoryBaseButton(contextRequireContext, R.id.video_story_follow_button);
        BaseButton.StyleBuilder buttonBuilder = mStyleSheet.getButtonBuilder(ButtonSizeKey.SMALL, ButtonStyleKey.GLASS_OUTLINE);
        if (buttonBuilder != null) {
            buttonBuilder.applyStyle(videoStoryBaseButton);
        }
        SpacerKey spacerKey = SpacerKey.MINI;
        Integer spacerInPixels = mStyleSheet.getSpacerInPixels(spacerKey);
        if (spacerInPixels != null) {
            videoStoryBaseButton.setPadding(spacerInPixels.intValue(), 0, 0, 0);
        }
        float dimension = getResources().getDimension(R.dimen.video_story_artist_view_size);
        RoundedCornersTransformation roundedCornersTransformation = new RoundedCornersTransformation(dimension / 2);
        VideoStoryContent videoStoryContent = (VideoStoryContent) playableContent;
        int i = (int) dimension;
        Picasso.get().load(videoStoryContent.artistArtworkUrl()).resize(i, i).transform(roundedCornersTransformation).centerCrop().into(this.artistView);
        TextView textView = new TextView(requireContext());
        textView.setId(R.id.video_story_artist_name);
        textView.setText(videoStoryContent.artistName());
        textView.setTextColor(-1);
        textView.setHeight((int) getResources().getDimension(R.dimen.video_story_artist_name_height));
        Integer spacerInPixels2 = mStyleSheet.getSpacerInPixels(spacerKey);
        if (spacerInPixels2 != null) {
            textView.setPadding(spacerInPixels2.intValue(), 0, 0, 0);
        }
        FontStyle fontStyle = mStyleSheet.getFontStyle(FontStyleKey.PRIMARY);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(textView, fontStyle);
        }
        LinearLayout linearLayout = this.followLayout;
        LinearLayout linearLayout2 = null;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("followLayout");
            linearLayout = null;
        }
        linearLayout.addView(textView);
        LinearLayout linearLayout3 = this.followLayout;
        if (linearLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("followLayout");
        } else {
            linearLayout2 = linearLayout3;
        }
        linearLayout2.addView(videoStoryBaseButton);
        return videoStoryBaseButton;
    }

    public static /* synthetic */ void updateFollowButtonText$default(VideoStoryItemFragment videoStoryItemFragment, Boolean bool, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = null;
        }
        if ((i & 2) != 0) {
            str = null;
        }
        videoStoryItemFragment.updateFollowButtonText(bool, str);
    }

    public final void updateFollowButtonText(Boolean isFollowing, String artistAsin) {
        String strArtistAsin;
        List<Element> listSecondaryButtons;
        Element element = null;
        if (artistAsin != null) {
            PlaybackTemplate playbackTemplate = this.videoQueueItem;
            VideoStoryContent videoStoryContent = (VideoStoryContent) (playbackTemplate == null ? null : playbackTemplate.playableContent());
            if (!Intrinsics.areEqual(artistAsin, videoStoryContent == null ? null : videoStoryContent.artistAsin())) {
                return;
            }
        }
        if (isFollowing != null) {
            updateText(isFollowing.booleanValue());
            return;
        }
        PlaybackTemplate playbackTemplate2 = this.videoQueueItem;
        VideoStoryContent videoStoryContent2 = (VideoStoryContent) (playbackTemplate2 == null ? null : playbackTemplate2.playableContent());
        if (videoStoryContent2 == null || (strArtistAsin = videoStoryContent2.artistAsin()) == null) {
            return;
        }
        VideoStoryArtistMapProvider videoStoryArtistMapProvider = VideoStoryArtistMapProvider.INSTANCE;
        if (videoStoryArtistMapProvider.isArtistPresent(strArtistAsin)) {
            updateText(videoStoryArtistMapProvider.isArtistBeingFollowed(strArtistAsin));
            return;
        }
        PlaybackTemplate playbackTemplate3 = this.videoQueueItem;
        List<Element> listSecondaryButtons2 = playbackTemplate3 == null ? null : playbackTemplate3.secondaryButtons();
        if (listSecondaryButtons2 == null || listSecondaryButtons2.isEmpty()) {
            return;
        }
        PlaybackTemplate playbackTemplate4 = this.videoQueueItem;
        if (playbackTemplate4 != null && (listSecondaryButtons = playbackTemplate4.secondaryButtons()) != null) {
            element = listSecondaryButtons.get(1);
        }
        if (element == null) {
            throw new NullPointerException("null cannot be cast to non-null type Touch.WidgetsInterface.v1_0.TextButtonElement");
        }
        updateText(((TextButtonElement) element).isActive());
    }

    public final void updateText(boolean condition) {
        BaseButton baseButton = this.followButton;
        if (baseButton == null) {
            return;
        }
        String string = getResources().getString(condition ? R.string.dmusic_following_text : R.string.dmusic_follow_text);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(if (…tring.dmusic_follow_text)");
        baseButton.setText(string);
    }

    private final LinearLayout initializeJumpOffButton(String jumpOffText) {
        StyleSheet mStyleSheet = getParentFragment().getMStyleSheet();
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        VideoStoryBaseButton videoStoryBaseButton = new VideoStoryBaseButton(contextRequireContext, R.id.video_story_jump_off_button);
        BaseButton.StyleBuilder buttonBuilder = mStyleSheet.getButtonBuilder(ButtonSizeKey.MEDIUM, ButtonStyleKey.GLASS);
        if (buttonBuilder != null) {
            buttonBuilder.applyStyle(videoStoryBaseButton);
        }
        Integer spacerInPixels = mStyleSheet.getSpacerInPixels(SpacerKey.GIANT);
        if (spacerInPixels != null) {
            LayoutParamUtils.INSTANCE.setLayoutMargins(videoStoryBaseButton.getButton(), -2, spacerInPixels.intValue(), (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
        }
        Integer spacerInPixels2 = mStyleSheet.getSpacerInPixels(SpacerKey.MICRO);
        if (spacerInPixels2 != null) {
            int iIntValue = spacerInPixels2.intValue();
            videoStoryBaseButton.setPadding(iIntValue, 0, iIntValue, 0);
        }
        videoStoryBaseButton.setText(jumpOffText);
        videoStoryBaseButton.setEllipsize(TextUtils.TruncateAt.END);
        videoStoryBaseButton.setMaxLines(1);
        LinearLayout linearContainerLayoutForJumpOffButton = getLinearContainerLayoutForJumpOffButton();
        linearContainerLayoutForJumpOffButton.addView(videoStoryBaseButton);
        return linearContainerLayoutForJumpOffButton;
    }

    private final void bindPreviewImage() {
        PlaybackTemplate playbackTemplate = this.videoQueueItem;
        if (playbackTemplate == null) {
            return;
        }
        Picasso picasso = Picasso.get();
        PlayableContent playableContent = playbackTemplate.playableContent();
        if (playableContent == null) {
            throw new NullPointerException("null cannot be cast to non-null type ExternalActionInterface.v1_0.VideoStoryContent");
        }
        RequestCreator requestCreatorPlaceholder = picasso.load(((VideoStoryContent) playableContent).artworkUrl()).fit().centerCrop().placeholder(new ColorDrawable(ViewCompat.MEASURED_STATE_MASK));
        ImageView imageView = this.previewImage;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("previewImage");
            imageView = null;
        }
        requestCreatorPlaceholder.into(imageView);
    }

    private final void bindActions() {
        LinearLayout linearLayout = this.actionsContainer;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("actionsContainer");
            linearLayout = null;
        }
        if (linearLayout.getChildCount() > 0) {
            return;
        }
        PlaybackTemplate playbackTemplate = this.videoQueueItem;
        List<ButtonElement> listActionButtons = playbackTemplate == null ? null : playbackTemplate.actionButtons();
        if (listActionButtons == null) {
            return;
        }
        int i = 0;
        int size = listActionButtons.size();
        while (i < size) {
            int i2 = i + 1;
            if (listActionButtons.get(i) instanceof LabeledIconButtonElement) {
                IconButtonView iconButtonView = new IconButtonView(getContext(), getResources().getDimensionPixelSize(R.dimen.playback_icon_button_size));
                ButtonElement buttonElement = listActionButtons.get(i);
                if (buttonElement == null) {
                    throw new NullPointerException("null cannot be cast to non-null type Touch.WidgetsInterface.v1_0.LabeledIconButtonElement");
                }
                iconButtonView.bind((LabeledIconButtonElement) buttonElement, this.ownerId, this.methodsDispatcher);
                iconButtonView.setButtonContentDescription(Intrinsics.stringPlus(ExploreResources.PLAYBACK_ACTION_BUTTON_PREFIX, Integer.valueOf(i)));
                setIconButtonLabelStyle(iconButtonView);
                LinearLayout linearLayout2 = this.actionsContainer;
                if (linearLayout2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("actionsContainer");
                    linearLayout2 = null;
                }
                linearLayout2.addView(iconButtonView);
                if (i < listActionButtons.size() - 1) {
                    Space space = new Space(getContext());
                    space.setLayoutParams(new LinearLayout.LayoutParams(-2, -2, 1.0f));
                    LinearLayout linearLayout3 = this.actionsContainer;
                    if (linearLayout3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("actionsContainer");
                        linearLayout3 = null;
                    }
                    linearLayout3.addView(space);
                }
            }
            i = i2;
        }
    }

    private final void setIconButtonLabelStyle(IconButtonView iconButton) {
        TextView label = iconButton.getLabel();
        label.setTypeface(Typeface.createFromAsset(getResources().getAssets(), getResources().getString(R.string.font_amazon_ember_regular)));
        label.setTextSize(0, getResources().getDimensionPixelSize(R.dimen.playback_icon_button_label_size));
        label.setLineSpacing(getResources().getDimensionPixelSize(R.dimen.playback_icon_button_line_spacing), 1.0f);
        if (getResources().getConfiguration().orientation == 2) {
            ViewGroup.LayoutParams layoutParams = label.getLayoutParams();
            if (layoutParams == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
            }
            ((LinearLayout.LayoutParams) layoutParams).topMargin = getResources().getDimensionPixelSize(R.dimen.spacer_mini);
        }
    }

    private final void bindText() {
        String strTertiaryText;
        String strPrimaryText;
        String strSecondaryText;
        TextView textView = this.title;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("title");
            textView = null;
        }
        PlaybackTemplate playbackTemplate = this.videoQueueItem;
        String str = "";
        if (playbackTemplate == null || (strTertiaryText = playbackTemplate.tertiaryText()) == null) {
            strTertiaryText = "";
        }
        textView.setText(strTertiaryText);
        TextView textView3 = this.subtitle;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(MediaTrack.ROLE_SUBTITLE);
            textView3 = null;
        }
        PlaybackTemplate playbackTemplate2 = this.videoQueueItem;
        if (playbackTemplate2 == null || (strPrimaryText = playbackTemplate2.primaryText()) == null) {
            strPrimaryText = "";
        }
        textView3.setText(strPrimaryText);
        TextView textView4 = this.artistName;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("artistName");
        } else {
            textView2 = textView4;
        }
        PlaybackTemplate playbackTemplate3 = this.videoQueueItem;
        if (playbackTemplate3 != null && (strSecondaryText = playbackTemplate3.secondaryText()) != null) {
            str = strSecondaryText;
        }
        textView2.setText(str);
        setFontFamily();
    }

    private final void setFontFamily() {
        Typeface typefaceCreateFromAsset = Typeface.createFromAsset(getResources().getAssets(), getResources().getString(R.string.font_amazon_ember_bold));
        TextView textView = this.title;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("title");
            textView = null;
        }
        textView.setTypeface(typefaceCreateFromAsset);
        TextView textView3 = this.subtitle;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(MediaTrack.ROLE_SUBTITLE);
            textView3 = null;
        }
        textView3.setTypeface(typefaceCreateFromAsset);
        TextView textView4 = this.artistName;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("artistName");
        } else {
            textView2 = textView4;
        }
        textView2.setTypeface(typefaceCreateFromAsset);
    }

    private final void setWindowInsets(int left, int top, int right, int bottom) {
        RelativeLayout relativeLayout = this.interfaceContainer;
        if (relativeLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("interfaceContainer");
            relativeLayout = null;
        }
        ViewGroup.LayoutParams layoutParams = relativeLayout.getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        }
        ((ViewGroup.MarginLayoutParams) layoutParams).setMargins(left, top, right, bottom);
    }

    public final void showPreviewImage() {
        ImageView imageView = this.previewImage;
        if (imageView != null) {
            if (imageView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("previewImage");
                imageView = null;
            }
            imageView.setVisibility(0);
        }
    }

    public final void hidePreviewImage() {
        ImageView imageView = this.previewImage;
        if (imageView != null) {
            if (imageView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("previewImage");
                imageView = null;
            }
            imageView.setVisibility(8);
        }
    }
}
