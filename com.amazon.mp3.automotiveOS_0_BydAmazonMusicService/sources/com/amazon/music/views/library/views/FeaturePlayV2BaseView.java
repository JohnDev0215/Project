package com.amazon.music.views.library.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.CoroutineLiveDataKt;
import com.amazon.music.views.library.R$color;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.R$string;
import com.amazon.music.views.library.models.FeaturePlayModel;
import com.amazon.music.views.library.models.PodcastFeaturePlayModel;
import com.amazon.music.views.library.providers.FeaturedPlayMusicProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.utils.AnimationUtil;
import com.amazon.music.views.library.utils.AverageColorTransformation;
import com.amazon.music.views.library.utils.BlurTransformation;
import com.amazon.music.views.library.utils.GradientOpacityTransformation;
import com.amazon.music.views.library.utils.GradientTransformation;
import com.amazon.music.views.library.utils.HorizontalGradientTransformation;
import com.amazon.music.views.library.utils.RoundedCornersTransformation;
import com.amazon.music.views.library.views.FeaturePlayV2BaseView;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.facebook.react.modules.appstate.AppStateModule;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FeaturePlayV2BaseView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000Â\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\r\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b4\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0013\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001BU\u0012\b\u0010¾\u0001\u001a\u00030½\u0001\u0012\u0006\u0010,\u001a\u00020+\u0012\n\b\u0002\u00101\u001a\u0004\u0018\u000100\u0012\u0006\u00106\u001a\u000205\u0012\u0006\u0010:\u001a\u000209\u0012\b\b\u0002\u0010>\u001a\u00020\u0017\u0012\b\b\u0002\u0010B\u001a\u00020\u0017\u0012\b\b\u0002\u0010D\u001a\u000205¢\u0006\u0006\b¿\u0001\u0010À\u0001J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\u0006\u0010\u0004\u001a\u00020\u0002J\u0010\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\b\u001a\u00020\u0002H\u0016J\b\u0010\t\u001a\u00020\u0002H\u0016J\u0010\u0010\n\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0012\u0010\r\u001a\u00020\u00022\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0016J\u0012\u0010\u000e\u001a\u00020\u00022\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0016J\u0012\u0010\u000f\u001a\u00020\u00022\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0016J\u0012\u0010\u0010\u001a\u00020\u00022\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0016J\u0012\u0010\u0011\u001a\u00020\u00022\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0016J\u001c\u0010\u0014\u001a\u00020\u00022\b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0016J\u0018\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0017H\u0016J0\u0010\u001e\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u0017H\u0016JF\u0010(\u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020\u00172\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00150 2\u0006\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u00172\u0006\u0010$\u001a\u00020\u001a2\u0006\u0010%\u001a\u00020\u001a2\u0006\u0010'\u001a\u00020&H\u0004J.\u0010)\u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020\u00172\u000e\u0010!\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010 2\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u0017J\u0010\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020*H\u0016R\u0017\u0010,\u001a\u00020+8\u0006¢\u0006\f\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/R\u0019\u00101\u001a\u0004\u0018\u0001008\u0006¢\u0006\f\n\u0004\b1\u00102\u001a\u0004\b3\u00104R\u0017\u00106\u001a\u0002058\u0006¢\u0006\f\n\u0004\b6\u00107\u001a\u0004\b6\u00108R\u0017\u0010:\u001a\u0002098\u0006¢\u0006\f\n\u0004\b:\u0010;\u001a\u0004\b<\u0010=R\u0017\u0010>\u001a\u00020\u00178\u0006¢\u0006\f\n\u0004\b>\u0010?\u001a\u0004\b@\u0010AR\u0017\u0010B\u001a\u00020\u00178\u0006¢\u0006\f\n\u0004\bB\u0010?\u001a\u0004\bC\u0010AR\u0017\u0010D\u001a\u0002058\u0006¢\u0006\f\n\u0004\bD\u00107\u001a\u0004\bD\u00108R\u001b\u0010I\u001a\u00020\u00018FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bE\u0010F\u001a\u0004\bG\u0010HR\u001b\u0010M\u001a\u00020\u001a8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bJ\u0010F\u001a\u0004\bK\u0010LR\u001b\u0010P\u001a\u00020\u001a8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bN\u0010F\u001a\u0004\bO\u0010LR\u001b\u0010S\u001a\u00020\u001a8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bQ\u0010F\u001a\u0004\bR\u0010LR\u001b\u0010V\u001a\u00020\u001a8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bT\u0010F\u001a\u0004\bU\u0010LR\u001b\u0010Z\u001a\u00020&8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bW\u0010F\u001a\u0004\bX\u0010YR\u001b\u0010]\u001a\u00020&8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b[\u0010F\u001a\u0004\b\\\u0010YR\u001b\u0010a\u001a\u00020\u00128FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b^\u0010F\u001a\u0004\b_\u0010`R\u001b\u0010d\u001a\u00020\u00128FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bb\u0010F\u001a\u0004\bc\u0010`R\u001b\u0010g\u001a\u00020\u00128FX\u0086\u0084\u0002¢\u0006\f\n\u0004\be\u0010F\u001a\u0004\bf\u0010`R\u001b\u0010j\u001a\u00020\u00128FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bh\u0010F\u001a\u0004\bi\u0010`R\u001b\u0010m\u001a\u00020\u00128FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bk\u0010F\u001a\u0004\bl\u0010`R\u001b\u0010r\u001a\u00020n8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bo\u0010F\u001a\u0004\bp\u0010qR\u001b\u0010w\u001a\u00020s8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bt\u0010F\u001a\u0004\bu\u0010vR\u001b\u0010z\u001a\u00020\u00128FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bx\u0010F\u001a\u0004\by\u0010`R\u001b\u0010}\u001a\u00020\u001a8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b{\u0010F\u001a\u0004\b|\u0010LR\u0016\u0010~\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b~\u0010\u007fR\u0018\u0010\u0080\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u0080\u0001\u0010\u007fR\u001c\u0010\u0082\u0001\u001a\u0005\u0018\u00010\u0081\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0082\u0001\u0010\u0083\u0001R\u001c\u0010\u0084\u0001\u001a\u0005\u0018\u00010\u0081\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0084\u0001\u0010\u0083\u0001R\u001c\u0010\u0086\u0001\u001a\u0005\u0018\u00010\u0085\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0086\u0001\u0010\u0087\u0001R\u001c\u0010\u0088\u0001\u001a\u0005\u0018\u00010\u0085\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0088\u0001\u0010\u0087\u0001R\u0018\u0010\u008a\u0001\u001a\u00030\u0089\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u008a\u0001\u0010\u008b\u0001R\u0018\u0010\u008d\u0001\u001a\u00030\u008c\u00018\u0002X\u0082D¢\u0006\b\n\u0006\b\u008d\u0001\u0010\u008e\u0001R'\u0010\u008f\u0001\u001a\u00020\u00178\u0014@\u0014X\u0094\u000e¢\u0006\u0016\n\u0005\b\u008f\u0001\u0010?\u001a\u0005\b\u0090\u0001\u0010A\"\u0006\b\u0091\u0001\u0010\u0092\u0001R,\u0010\u0093\u0001\u001a\u0005\u0018\u00010\u0081\u00018\u0004@\u0004X\u0084\u000e¢\u0006\u0018\n\u0006\b\u0093\u0001\u0010\u0083\u0001\u001a\u0006\b\u0094\u0001\u0010\u0095\u0001\"\u0006\b\u0096\u0001\u0010\u0097\u0001R,\u0010\u0098\u0001\u001a\u0005\u0018\u00010\u0081\u00018\u0004@\u0004X\u0084\u000e¢\u0006\u0018\n\u0006\b\u0098\u0001\u0010\u0083\u0001\u001a\u0006\b\u0099\u0001\u0010\u0095\u0001\"\u0006\b\u009a\u0001\u0010\u0097\u0001R(\u0010\u009b\u0001\u001a\u00020\u00158\u0004@\u0004X\u0084\u000e¢\u0006\u0017\n\u0005\b\u009b\u0001\u0010\u007f\u001a\u0006\b\u009c\u0001\u0010\u009d\u0001\"\u0006\b\u009e\u0001\u0010\u009f\u0001R*\u0010¡\u0001\u001a\u00030 \u00018\u0004@\u0004X\u0084\u000e¢\u0006\u0018\n\u0006\b¡\u0001\u0010¢\u0001\u001a\u0006\b£\u0001\u0010¤\u0001\"\u0006\b¥\u0001\u0010¦\u0001R \u0010«\u0001\u001a\u00030§\u00018DX\u0084\u0084\u0002¢\u0006\u000f\n\u0005\b¨\u0001\u0010F\u001a\u0006\b©\u0001\u0010ª\u0001R \u0010®\u0001\u001a\u00030§\u00018DX\u0084\u0084\u0002¢\u0006\u000f\n\u0005\b¬\u0001\u0010F\u001a\u0006\b\u00ad\u0001\u0010ª\u0001R \u0010±\u0001\u001a\u00030§\u00018DX\u0084\u0084\u0002¢\u0006\u000f\n\u0005\b¯\u0001\u0010F\u001a\u0006\b°\u0001\u0010ª\u0001R \u0010¶\u0001\u001a\u00030²\u00018DX\u0084\u0084\u0002¢\u0006\u000f\n\u0005\b³\u0001\u0010F\u001a\u0006\b´\u0001\u0010µ\u0001R \u0010¹\u0001\u001a\u00030²\u00018DX\u0084\u0084\u0002¢\u0006\u000f\n\u0005\b·\u0001\u0010F\u001a\u0006\b¸\u0001\u0010µ\u0001R \u0010¼\u0001\u001a\u00030²\u00018DX\u0084\u0084\u0002¢\u0006\u000f\n\u0005\bº\u0001\u0010F\u001a\u0006\b»\u0001\u0010µ\u0001¨\u0006Á\u0001"}, d2 = {"Lcom/amazon/music/views/library/views/FeaturePlayV2BaseView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "", "initViewStyling", "init", "Lcom/amazon/music/views/library/models/FeaturePlayModel;", "model", "setPlayState", "startEqualizer", "pauseEqualizer", "stopEqualizer", "", "text", "setLabelText", "setTitleText", "setPrimaryText", "setSecondaryText", "setTertiaryText", "Landroid/widget/TextView;", "textView", "setText", "", "imageUrl", "", "backgroundImageViewHeight", "bindImagesForWideScreen", "Landroid/widget/ImageView;", "backgroundImage", "foreGroundImage", "backgroundImageViewWidth", "bindImagesForNormalScreen", "pos", "", "imageUrls", "backgroundHeight", "backgroundWidth", "foreground", AppStateModule.APP_STATE_BACKGROUND, "Landroid/widget/FrameLayout;", "foreGroundLayout", "startCrossFadeAnimation", "executeAnimationSequence", "Lcom/amazon/music/views/library/models/PodcastFeaturePlayModel;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "Landroid/util/AttributeSet;", "attrs", "Landroid/util/AttributeSet;", "getAttrs", "()Landroid/util/AttributeSet;", "", "isScreenSizeGreaterThanMD", "Z", "()Z", "Lcom/amazon/music/views/library/providers/FeaturedPlayMusicProvider;", "playIconOnClickMusicProvider", "Lcom/amazon/music/views/library/providers/FeaturedPlayMusicProvider;", "getPlayIconOnClickMusicProvider", "()Lcom/amazon/music/views/library/providers/FeaturedPlayMusicProvider;", "defStyleAttr", "I", "getDefStyleAttr", "()I", "defStyleRes", "getDefStyleRes", "isSonicRushEnabled", "feature_play_container$delegate", "Lkotlin/Lazy;", "getFeature_play_container", "()Landroidx/constraintlayout/widget/ConstraintLayout;", "feature_play_container", "backgroundImageView$delegate", "getBackgroundImageView", "()Landroid/widget/ImageView;", "backgroundImageView", "backgroundImageViewSecond$delegate", "getBackgroundImageViewSecond", "backgroundImageViewSecond", "foregroundImageView$delegate", "getForegroundImageView", "foregroundImageView", "foregroundImageViewSecond$delegate", "getForegroundImageViewSecond", "foregroundImageViewSecond", "foregroundLayout$delegate", "getForegroundLayout", "()Landroid/widget/FrameLayout;", "foregroundLayout", "foregroundLayoutSecond$delegate", "getForegroundLayoutSecond", "foregroundLayoutSecond", "titleTextView$delegate", "getTitleTextView", "()Landroid/widget/TextView;", "titleTextView", "primaryTextView$delegate", "getPrimaryTextView", "primaryTextView", "labelTextView$delegate", "getLabelTextView", "labelTextView", "secondaryTextView$delegate", "getSecondaryTextView", "secondaryTextView", "tertiaryTextView$delegate", "getTertiaryTextView", "tertiaryTextView", "Landroid/widget/ImageButton;", "playButton$delegate", "getPlayButton", "()Landroid/widget/ImageButton;", "playButton", "Landroid/widget/RelativeLayout;", "playButtonWideScreen$delegate", "getPlayButtonWideScreen", "()Landroid/widget/RelativeLayout;", "playButtonWideScreen", "playButtonWideScreenTextView$delegate", "getPlayButtonWideScreenTextView", "playButtonWideScreenTextView", "playButtonWideScreenImageView$delegate", "getPlayButtonWideScreenImageView", "playButtonWideScreenImageView", "buttonPlayingText", "Ljava/lang/String;", "buttonPausedText", "Landroid/graphics/drawable/Drawable;", "pausedDrawable", "Landroid/graphics/drawable/Drawable;", "refreshPausedDrawable", "Landroid/graphics/drawable/AnimationDrawable;", "playingDrawable", "Landroid/graphics/drawable/AnimationDrawable;", "refreshPlayingDrawable", "Ljava/util/concurrent/ScheduledExecutorService;", "animationScheduler", "Ljava/util/concurrent/ScheduledExecutorService;", "", "ANIMATION_DELAY_MS", "J", "errorStateImage", "getErrorStateImage", "setErrorStateImage", "(I)V", "playDrawable", "getPlayDrawable", "()Landroid/graphics/drawable/Drawable;", "setPlayDrawable", "(Landroid/graphics/drawable/Drawable;)V", "refreshPlayDrawable", "getRefreshPlayDrawable", "setRefreshPlayDrawable", "buttonPlayText", "getButtonPlayText", "()Ljava/lang/String;", "setButtonPlayText", "(Ljava/lang/String;)V", "", "gradientHeightOffsetBackground", "F", "getGradientHeightOffsetBackground", "()F", "setGradientHeightOffsetBackground", "(F)V", "Landroid/view/animation/Animation;", "animTranslateTextY$delegate", "getAnimTranslateTextY", "()Landroid/view/animation/Animation;", "animTranslateTextY", "animationFadeInFromZeroPercent$delegate", "getAnimationFadeInFromZeroPercent", "animationFadeInFromZeroPercent", "animationFadeInFromThirtyPercent$delegate", "getAnimationFadeInFromThirtyPercent", "animationFadeInFromThirtyPercent", "Landroid/view/animation/AnimationSet;", "animationSetForegroundImage$delegate", "getAnimationSetForegroundImage", "()Landroid/view/animation/AnimationSet;", "animationSetForegroundImage", "animationSetPrimaryText$delegate", "getAnimationSetPrimaryText", "animationSetPrimaryText", "animationSetHeadlineText$delegate", "getAnimationSetHeadlineText", "animationSetHeadlineText", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;Landroid/util/AttributeSet;ZLcom/amazon/music/views/library/providers/FeaturedPlayMusicProvider;IIZ)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public class FeaturePlayV2BaseView extends ConstraintLayout {
    private final long ANIMATION_DELAY_MS;

    /* JADX INFO: renamed from: animTranslateTextY$delegate, reason: from kotlin metadata */
    private final Lazy animTranslateTextY;

    /* JADX INFO: renamed from: animationFadeInFromThirtyPercent$delegate, reason: from kotlin metadata */
    private final Lazy animationFadeInFromThirtyPercent;

    /* JADX INFO: renamed from: animationFadeInFromZeroPercent$delegate, reason: from kotlin metadata */
    private final Lazy animationFadeInFromZeroPercent;
    private final ScheduledExecutorService animationScheduler;

    /* JADX INFO: renamed from: animationSetForegroundImage$delegate, reason: from kotlin metadata */
    private final Lazy animationSetForegroundImage;

    /* JADX INFO: renamed from: animationSetHeadlineText$delegate, reason: from kotlin metadata */
    private final Lazy animationSetHeadlineText;

    /* JADX INFO: renamed from: animationSetPrimaryText$delegate, reason: from kotlin metadata */
    private final Lazy animationSetPrimaryText;
    private final AttributeSet attrs;

    /* JADX INFO: renamed from: backgroundImageView$delegate, reason: from kotlin metadata */
    private final Lazy backgroundImageView;

    /* JADX INFO: renamed from: backgroundImageViewSecond$delegate, reason: from kotlin metadata */
    private final Lazy backgroundImageViewSecond;
    private String buttonPausedText;
    private String buttonPlayText;
    private String buttonPlayingText;
    private final int defStyleAttr;
    private final int defStyleRes;
    private int errorStateImage;

    /* JADX INFO: renamed from: feature_play_container$delegate, reason: from kotlin metadata */
    private final Lazy feature_play_container;

    /* JADX INFO: renamed from: foregroundImageView$delegate, reason: from kotlin metadata */
    private final Lazy foregroundImageView;

    /* JADX INFO: renamed from: foregroundImageViewSecond$delegate, reason: from kotlin metadata */
    private final Lazy foregroundImageViewSecond;

    /* JADX INFO: renamed from: foregroundLayout$delegate, reason: from kotlin metadata */
    private final Lazy foregroundLayout;

    /* JADX INFO: renamed from: foregroundLayoutSecond$delegate, reason: from kotlin metadata */
    private final Lazy foregroundLayoutSecond;
    private float gradientHeightOffsetBackground;
    private final boolean isScreenSizeGreaterThanMD;
    private final boolean isSonicRushEnabled;

    /* JADX INFO: renamed from: labelTextView$delegate, reason: from kotlin metadata */
    private final Lazy labelTextView;
    private Drawable pausedDrawable;

    /* JADX INFO: renamed from: playButton$delegate, reason: from kotlin metadata */
    private final Lazy playButton;

    /* JADX INFO: renamed from: playButtonWideScreen$delegate, reason: from kotlin metadata */
    private final Lazy playButtonWideScreen;

    /* JADX INFO: renamed from: playButtonWideScreenImageView$delegate, reason: from kotlin metadata */
    private final Lazy playButtonWideScreenImageView;

    /* JADX INFO: renamed from: playButtonWideScreenTextView$delegate, reason: from kotlin metadata */
    private final Lazy playButtonWideScreenTextView;
    private Drawable playDrawable;
    private final FeaturedPlayMusicProvider playIconOnClickMusicProvider;
    private AnimationDrawable playingDrawable;

    /* JADX INFO: renamed from: primaryTextView$delegate, reason: from kotlin metadata */
    private final Lazy primaryTextView;
    private Drawable refreshPausedDrawable;
    private Drawable refreshPlayDrawable;
    private AnimationDrawable refreshPlayingDrawable;

    /* JADX INFO: renamed from: secondaryTextView$delegate, reason: from kotlin metadata */
    private final Lazy secondaryTextView;
    private final StyleSheet styleSheet;

    /* JADX INFO: renamed from: tertiaryTextView$delegate, reason: from kotlin metadata */
    private final Lazy tertiaryTextView;

    /* JADX INFO: renamed from: titleTextView$delegate, reason: from kotlin metadata */
    private final Lazy titleTextView;

    public void setPlayState(PodcastFeaturePlayModel model) {
        Intrinsics.checkNotNullParameter(model, "model");
    }

    public /* synthetic */ FeaturePlayV2BaseView(Context context, StyleSheet styleSheet, AttributeSet attributeSet, boolean z, FeaturedPlayMusicProvider featuredPlayMusicProvider, int i, int i2, boolean z2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, styleSheet, (i3 & 4) != 0 ? null : attributeSet, z, featuredPlayMusicProvider, (i3 & 32) != 0 ? 0 : i, (i3 & 64) != 0 ? 0 : i2, (i3 & 128) != 0 ? false : z2);
    }

    public final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    public final AttributeSet getAttrs() {
        return this.attrs;
    }

    /* JADX INFO: renamed from: isScreenSizeGreaterThanMD, reason: from getter */
    public final boolean getIsScreenSizeGreaterThanMD() {
        return this.isScreenSizeGreaterThanMD;
    }

    public final FeaturedPlayMusicProvider getPlayIconOnClickMusicProvider() {
        return this.playIconOnClickMusicProvider;
    }

    public final int getDefStyleAttr() {
        return this.defStyleAttr;
    }

    public final int getDefStyleRes() {
        return this.defStyleRes;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FeaturePlayV2BaseView(Context context, StyleSheet styleSheet, AttributeSet attributeSet, boolean z, FeaturedPlayMusicProvider playIconOnClickMusicProvider, int i, int i2, boolean z2) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(playIconOnClickMusicProvider, "playIconOnClickMusicProvider");
        this.styleSheet = styleSheet;
        this.attrs = attributeSet;
        this.isScreenSizeGreaterThanMD = z;
        this.playIconOnClickMusicProvider = playIconOnClickMusicProvider;
        this.defStyleAttr = i;
        this.defStyleRes = i2;
        this.isSonicRushEnabled = z2;
        this.feature_play_container = LazyKt__LazyJVMKt.lazy(new Function0<ConstraintLayout>() { // from class: com.amazon.music.views.library.views.FeaturePlayV2BaseView$feature_play_container$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ConstraintLayout invoke() {
                return (ConstraintLayout) this.this$0.findViewById(R$id.feature_play);
            }
        });
        this.backgroundImageView = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.music.views.library.views.FeaturePlayV2BaseView$backgroundImageView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ImageView invoke() {
                return (ImageView) this.this$0.findViewById(R$id.background_gradient_image_view);
            }
        });
        this.backgroundImageViewSecond = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.music.views.library.views.FeaturePlayV2BaseView$backgroundImageViewSecond$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ImageView invoke() {
                return (ImageView) this.this$0.findViewById(R$id.background_gradient_image_view_second);
            }
        });
        this.foregroundImageView = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.music.views.library.views.FeaturePlayV2BaseView$foregroundImageView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ImageView invoke() {
                return (ImageView) this.this$0.findViewById(R$id.foreground_gradient_image_view);
            }
        });
        this.foregroundImageViewSecond = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.music.views.library.views.FeaturePlayV2BaseView$foregroundImageViewSecond$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ImageView invoke() {
                return (ImageView) this.this$0.findViewById(R$id.foreground_gradient_image_view_second);
            }
        });
        this.foregroundLayout = LazyKt__LazyJVMKt.lazy(new Function0<FrameLayout>() { // from class: com.amazon.music.views.library.views.FeaturePlayV2BaseView$foregroundLayout$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FrameLayout invoke() {
                return (FrameLayout) this.this$0.findViewById(R$id.foreground_gradient_image_view_relative_layout);
            }
        });
        this.foregroundLayoutSecond = LazyKt__LazyJVMKt.lazy(new Function0<FrameLayout>() { // from class: com.amazon.music.views.library.views.FeaturePlayV2BaseView$foregroundLayoutSecond$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FrameLayout invoke() {
                return (FrameLayout) this.this$0.findViewById(R$id.foreground_gradient_image_view_relative_layout_second);
            }
        });
        this.titleTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.FeaturePlayV2BaseView$titleTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.title);
            }
        });
        this.primaryTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.FeaturePlayV2BaseView$primaryTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.primary_text);
            }
        });
        this.labelTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.FeaturePlayV2BaseView$labelTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.label);
            }
        });
        this.secondaryTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.FeaturePlayV2BaseView$secondaryTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.secondary_text);
            }
        });
        this.tertiaryTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.FeaturePlayV2BaseView$tertiaryTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.tertiary_text);
            }
        });
        this.playButton = LazyKt__LazyJVMKt.lazy(new Function0<ImageButton>() { // from class: com.amazon.music.views.library.views.FeaturePlayV2BaseView$playButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ImageButton invoke() {
                return (ImageButton) this.this$0.findViewById(R$id.PlayButton);
            }
        });
        this.playButtonWideScreen = LazyKt__LazyJVMKt.lazy(new Function0<RelativeLayout>() { // from class: com.amazon.music.views.library.views.FeaturePlayV2BaseView$playButtonWideScreen$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final RelativeLayout invoke() {
                return (RelativeLayout) this.this$0.findViewById(R$id.PlayButtonWideScreen);
            }
        });
        this.playButtonWideScreenTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.FeaturePlayV2BaseView$playButtonWideScreenTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.widescreen_ingress_button_text);
            }
        });
        this.playButtonWideScreenImageView = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.music.views.library.views.FeaturePlayV2BaseView$playButtonWideScreenImageView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ImageView invoke() {
                return (ImageView) this.this$0.findViewById(R$id.widescreen_ingress_button_image);
            }
        });
        String string = getResources().getString(R$string.content_description_playing);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…tent_description_playing)");
        this.buttonPlayingText = string;
        String string2 = getResources().getString(R$string.content_description_paused);
        Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.st…ntent_description_paused)");
        this.buttonPausedText = string2;
        this.pausedDrawable = AppCompatResources.getDrawable(context, R$drawable.ic_playback_equalizeroff);
        this.refreshPausedDrawable = AppCompatResources.getDrawable(context, R$drawable.ic_refresh_playback_equalizeroff);
        Drawable drawable = AppCompatResources.getDrawable(context, R$drawable.equalizer_primary_feature_play);
        if (drawable == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.graphics.drawable.AnimationDrawable");
        }
        this.playingDrawable = (AnimationDrawable) drawable;
        Drawable drawable2 = AppCompatResources.getDrawable(context, R$drawable.equalizer_secondary_feature_play);
        if (drawable2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.graphics.drawable.AnimationDrawable");
        }
        this.refreshPlayingDrawable = (AnimationDrawable) drawable2;
        ScheduledExecutorService scheduledExecutorServiceNewSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        Intrinsics.checkNotNullExpressionValue(scheduledExecutorServiceNewSingleThreadScheduledExecutor, "newSingleThreadScheduledExecutor()");
        this.animationScheduler = scheduledExecutorServiceNewSingleThreadScheduledExecutor;
        this.ANIMATION_DELAY_MS = 8000L;
        this.errorStateImage = R$drawable.feature_play_base_empty;
        this.playDrawable = AppCompatResources.getDrawable(context, R$drawable.player_btn_play);
        this.refreshPlayDrawable = AppCompatResources.getDrawable(context, R$drawable.refresh_player_btn_play);
        String string3 = getResources().getString(R$string.content_description_play_button);
        Intrinsics.checkNotNullExpressionValue(string3, "resources.getString(R.st…_description_play_button)");
        this.buttonPlayText = string3;
        this.gradientHeightOffsetBackground = getResources().getDimension(R$dimen.height_offset_background);
        this.animTranslateTextY = LazyKt__LazyJVMKt.lazy(new Function0<Animation>() { // from class: com.amazon.music.views.library.views.FeaturePlayV2BaseView$animTranslateTextY$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Animation invoke() {
                return AnimationUtil.INSTANCE.createTranslateAnimation(0.0f, 0.0f, 40.0f, 0.0f, true, 350L);
            }
        });
        this.animationFadeInFromZeroPercent = LazyKt__LazyJVMKt.lazy(new Function0<Animation>() { // from class: com.amazon.music.views.library.views.FeaturePlayV2BaseView$animationFadeInFromZeroPercent$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Animation invoke() {
                return AnimationUtil.INSTANCE.createFadeAnimation(0.0f, 1.0f, true, 350L);
            }
        });
        this.animationFadeInFromThirtyPercent = LazyKt__LazyJVMKt.lazy(new Function0<Animation>() { // from class: com.amazon.music.views.library.views.FeaturePlayV2BaseView$animationFadeInFromThirtyPercent$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Animation invoke() {
                return AnimationUtil.INSTANCE.createFadeAnimation(0.3f, 1.0f, true, 350L);
            }
        });
        this.animationSetForegroundImage = LazyKt__LazyJVMKt.lazy(new Function0<AnimationSet>() { // from class: com.amazon.music.views.library.views.FeaturePlayV2BaseView$animationSetForegroundImage$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final AnimationSet invoke() {
                return AnimationUtil.INSTANCE.createAnimationSet(true, CollectionsKt__CollectionsJVMKt.listOf(this.this$0.getAnimationFadeInFromZeroPercent()));
            }
        });
        this.animationSetPrimaryText = LazyKt__LazyJVMKt.lazy(new Function0<AnimationSet>() { // from class: com.amazon.music.views.library.views.FeaturePlayV2BaseView$animationSetPrimaryText$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final AnimationSet invoke() {
                return AnimationUtil.INSTANCE.createAnimationSet(true, CollectionsKt__CollectionsKt.listOf((Object[]) new Animation[]{this.this$0.getAnimTranslateTextY(), this.this$0.getAnimationFadeInFromZeroPercent()}));
            }
        });
        this.animationSetHeadlineText = LazyKt__LazyJVMKt.lazy(new Function0<AnimationSet>() { // from class: com.amazon.music.views.library.views.FeaturePlayV2BaseView$animationSetHeadlineText$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final AnimationSet invoke() {
                return AnimationUtil.INSTANCE.createAnimationSet(true, CollectionsKt__CollectionsKt.listOf((Object[]) new Animation[]{this.this$0.getAnimTranslateTextY(), this.this$0.getAnimationFadeInFromThirtyPercent()}));
            }
        });
    }

    /* JADX INFO: renamed from: isSonicRushEnabled, reason: from getter */
    public final boolean getIsSonicRushEnabled() {
        return this.isSonicRushEnabled;
    }

    public final ConstraintLayout getFeature_play_container() {
        Object value = this.feature_play_container.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-feature_play_container>(...)");
        return (ConstraintLayout) value;
    }

    public final ImageView getBackgroundImageView() {
        Object value = this.backgroundImageView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-backgroundImageView>(...)");
        return (ImageView) value;
    }

    public final ImageView getBackgroundImageViewSecond() {
        Object value = this.backgroundImageViewSecond.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-backgroundImageViewSecond>(...)");
        return (ImageView) value;
    }

    public final ImageView getForegroundImageView() {
        Object value = this.foregroundImageView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-foregroundImageView>(...)");
        return (ImageView) value;
    }

    public final ImageView getForegroundImageViewSecond() {
        Object value = this.foregroundImageViewSecond.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-foregroundImageViewSecond>(...)");
        return (ImageView) value;
    }

    public final FrameLayout getForegroundLayout() {
        Object value = this.foregroundLayout.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-foregroundLayout>(...)");
        return (FrameLayout) value;
    }

    public final FrameLayout getForegroundLayoutSecond() {
        Object value = this.foregroundLayoutSecond.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-foregroundLayoutSecond>(...)");
        return (FrameLayout) value;
    }

    public final TextView getTitleTextView() {
        Object value = this.titleTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-titleTextView>(...)");
        return (TextView) value;
    }

    public final TextView getPrimaryTextView() {
        Object value = this.primaryTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-primaryTextView>(...)");
        return (TextView) value;
    }

    public final TextView getLabelTextView() {
        Object value = this.labelTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-labelTextView>(...)");
        return (TextView) value;
    }

    public final TextView getSecondaryTextView() {
        Object value = this.secondaryTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-secondaryTextView>(...)");
        return (TextView) value;
    }

    public final TextView getTertiaryTextView() {
        Object value = this.tertiaryTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-tertiaryTextView>(...)");
        return (TextView) value;
    }

    public final ImageButton getPlayButton() {
        Object value = this.playButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-playButton>(...)");
        return (ImageButton) value;
    }

    public final RelativeLayout getPlayButtonWideScreen() {
        Object value = this.playButtonWideScreen.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-playButtonWideScreen>(...)");
        return (RelativeLayout) value;
    }

    public final TextView getPlayButtonWideScreenTextView() {
        Object value = this.playButtonWideScreenTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-playButtonWideScreenTextView>(...)");
        return (TextView) value;
    }

    public final ImageView getPlayButtonWideScreenImageView() {
        Object value = this.playButtonWideScreenImageView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-playButtonWideScreenImageView>(...)");
        return (ImageView) value;
    }

    protected int getErrorStateImage() {
        return this.errorStateImage;
    }

    protected void setErrorStateImage(int i) {
        this.errorStateImage = i;
    }

    protected final Drawable getPlayDrawable() {
        return this.playDrawable;
    }

    protected final void setPlayDrawable(Drawable drawable) {
        this.playDrawable = drawable;
    }

    protected final Drawable getRefreshPlayDrawable() {
        return this.refreshPlayDrawable;
    }

    protected final void setRefreshPlayDrawable(Drawable drawable) {
        this.refreshPlayDrawable = drawable;
    }

    protected final String getButtonPlayText() {
        return this.buttonPlayText;
    }

    protected final void setButtonPlayText(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.buttonPlayText = str;
    }

    protected final float getGradientHeightOffsetBackground() {
        return this.gradientHeightOffsetBackground;
    }

    protected final void setGradientHeightOffsetBackground(float f) {
        this.gradientHeightOffsetBackground = f;
    }

    protected final Animation getAnimTranslateTextY() {
        return (Animation) this.animTranslateTextY.getValue();
    }

    protected final Animation getAnimationFadeInFromZeroPercent() {
        return (Animation) this.animationFadeInFromZeroPercent.getValue();
    }

    protected final Animation getAnimationFadeInFromThirtyPercent() {
        return (Animation) this.animationFadeInFromThirtyPercent.getValue();
    }

    protected final AnimationSet getAnimationSetForegroundImage() {
        return (AnimationSet) this.animationSetForegroundImage.getValue();
    }

    protected final AnimationSet getAnimationSetPrimaryText() {
        return (AnimationSet) this.animationSetPrimaryText.getValue();
    }

    protected final AnimationSet getAnimationSetHeadlineText() {
        return (AnimationSet) this.animationSetHeadlineText.getValue();
    }

    public final void init() {
        if (this.isScreenSizeGreaterThanMD) {
            View.inflate(getContext(), R$layout.feature_play_base_widescreen, this);
        } else {
            View.inflate(getContext(), R$layout.feature_play_base, this);
        }
        initViewStyling();
    }

    private final void initViewStyling() {
        FontStyle fontStyle = this.styleSheet.getFontStyle(FontStyleKey.LABEL);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(getLabelTextView(), fontStyle);
        }
        FontStyle fontStyle2 = this.styleSheet.getFontStyle(FontStyleKey.HEADLINE_3);
        if (fontStyle2 != null) {
            FontUtil.INSTANCE.applyFontStyle(getTitleTextView(), fontStyle2);
        }
        FontStyle fontStyle3 = this.styleSheet.getFontStyle(FontStyleKey.PRIMARY);
        if (fontStyle3 == null) {
            return;
        }
        FontUtil.INSTANCE.applyFontStyle(getPrimaryTextView(), fontStyle3);
    }

    public void setPlayState(FeaturePlayModel model) {
        Intrinsics.checkNotNullParameter(model, "model");
        Integer playState = model.getPlayState();
        if (playState != null && playState.intValue() == 2) {
            startEqualizer();
        } else if (playState != null && playState.intValue() == 1) {
            pauseEqualizer();
        } else {
            stopEqualizer(model);
        }
    }

    public void startEqualizer() {
        if (this.isScreenSizeGreaterThanMD) {
            if (this.isSonicRushEnabled) {
                getPlayButtonWideScreen().setBackgroundResource(R$drawable.refresh_accent_btn);
                getPlayButtonWideScreenImageView().setImageDrawable(this.refreshPlayingDrawable);
                getPlayButtonWideScreenTextView().setTextColor(getResources().getColor(R$color.secondary));
                AnimationDrawable animationDrawable = this.refreshPlayingDrawable;
                Intrinsics.checkNotNull(animationDrawable);
                animationDrawable.start();
            } else {
                getPlayButtonWideScreen().setBackgroundResource(R$drawable.primary_glass_2_rounded_corners);
                getPlayButtonWideScreenImageView().setImageDrawable(this.playingDrawable);
                getPlayButtonWideScreenTextView().setTextColor(getResources().getColor(R$color.primary));
                AnimationDrawable animationDrawable2 = this.playingDrawable;
                Intrinsics.checkNotNull(animationDrawable2);
                animationDrawable2.start();
            }
            getPlayButtonWideScreenTextView().setText(this.buttonPlayingText);
            getPlayButtonWideScreen().setOnClickListener(null);
            return;
        }
        if (this.isSonicRushEnabled) {
            getPlayButton().setImageDrawable(this.refreshPlayingDrawable);
            getPlayButton().setBackgroundResource(R$drawable.refresh_accent_btn);
            AnimationDrawable animationDrawable3 = this.refreshPlayingDrawable;
            Intrinsics.checkNotNull(animationDrawable3);
            animationDrawable3.start();
        } else {
            getPlayButton().setImageDrawable(this.playingDrawable);
            getPlayButton().setBackgroundResource(R$drawable.primary_glass_2_rounded_corners);
            AnimationDrawable animationDrawable4 = this.playingDrawable;
            if (animationDrawable4 != null) {
                animationDrawable4.start();
            }
        }
        getPlayButton().setOnClickListener(null);
    }

    public void pauseEqualizer() {
        if (this.isScreenSizeGreaterThanMD) {
            if (this.isSonicRushEnabled) {
                getPlayButtonWideScreen().setBackgroundResource(R$drawable.refresh_accent_btn);
                getPlayButtonWideScreenImageView().setImageDrawable(this.refreshPausedDrawable);
                getPlayButtonWideScreenTextView().setTextColor(getResources().getColor(R$color.secondary));
            } else {
                getPlayButtonWideScreen().setBackgroundResource(R$drawable.primary_glass_2_rounded_corners);
                getPlayButtonWideScreenImageView().setImageDrawable(this.pausedDrawable);
                getPlayButtonWideScreenTextView().setTextColor(getResources().getColor(R$color.primary));
            }
            getPlayButtonWideScreenTextView().setText(this.buttonPausedText);
            getPlayButtonWideScreen().setOnClickListener(null);
            return;
        }
        if (this.isSonicRushEnabled) {
            getPlayButton().setImageDrawable(this.refreshPausedDrawable);
            getPlayButton().setBackgroundResource(R$drawable.refresh_accent_btn);
        } else {
            getPlayButton().setImageDrawable(this.pausedDrawable);
            getPlayButton().setBackgroundResource(R$drawable.primary_glass_2_rounded_corners);
        }
        getPlayButton().setOnClickListener(null);
    }

    public void stopEqualizer(final FeaturePlayModel model) {
        Intrinsics.checkNotNullParameter(model, "model");
        if (this.isScreenSizeGreaterThanMD) {
            if (this.isSonicRushEnabled) {
                getPlayButtonWideScreenTextView().setTextColor(getResources().getColor(R$color.secondary));
                getPlayButtonWideScreen().setBackgroundResource(R$drawable.refresh_accent_btn);
                getPlayButtonWideScreenImageView().setImageDrawable(this.refreshPlayDrawable);
            } else {
                getPlayButtonWideScreenTextView().setTextColor(getResources().getColor(R$color.primary));
                getPlayButtonWideScreen().setBackgroundResource(R$drawable.primary_glass_2_rounded_corners);
                getPlayButtonWideScreenImageView().setImageDrawable(this.playDrawable);
            }
            getPlayButtonWideScreenTextView().setText(this.buttonPlayText);
            getPlayButtonWideScreen().setClickable(true);
            getPlayButtonWideScreen().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.views.FeaturePlayV2BaseView$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FeaturePlayV2BaseView.m2403stopEqualizer$lambda9(this.f$0, model, view);
                }
            });
            return;
        }
        if (this.isSonicRushEnabled) {
            getPlayButton().setImageDrawable(this.refreshPlayDrawable);
            getPlayButton().setBackgroundResource(R$drawable.refresh_accent_btn);
        } else {
            getPlayButton().setImageDrawable(this.playDrawable);
            getPlayButton().setBackgroundResource(R$drawable.primary_glass_2_rounded_corners);
        }
        getPlayButton().setClickable(true);
        getPlayButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.views.FeaturePlayV2BaseView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FeaturePlayV2BaseView.m2402stopEqualizer$lambda10(this.f$0, model, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: stopEqualizer$lambda-9, reason: not valid java name */
    public static final void m2403stopEqualizer$lambda9(FeaturePlayV2BaseView this$0, FeaturePlayModel model, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        this$0.playIconOnClickMusicProvider.startPlayback(model.getMetadata());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: stopEqualizer$lambda-10, reason: not valid java name */
    public static final void m2402stopEqualizer$lambda10(FeaturePlayV2BaseView this$0, FeaturePlayModel model, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        this$0.playIconOnClickMusicProvider.startPlayback(model.getMetadata());
    }

    public void setLabelText(CharSequence text) {
        setText(getLabelTextView(), text);
    }

    public void setTitleText(CharSequence text) {
        setText(getTitleTextView(), text);
        if (TextUtils.isEmpty(text)) {
            return;
        }
        TextView titleTextView = getTitleTextView();
        FontStyle fontStyle = getStyleSheet().getFontStyle(FontStyleKey.HEADLINE_3);
        if (fontStyle == null) {
            return;
        }
        FontUtil.INSTANCE.applyFontStyle(titleTextView, fontStyle);
    }

    public void setPrimaryText(CharSequence text) {
        setText(getPrimaryTextView(), text);
    }

    public void setSecondaryText(CharSequence text) {
        setText(getSecondaryTextView(), text);
    }

    public void setTertiaryText(CharSequence text) {
        setText(getTertiaryTextView(), text);
    }

    public void setText(TextView textView, CharSequence text) {
        if (textView != null) {
            textView.setText(text);
        }
        if (!TextUtils.isEmpty(text) || textView == null) {
            return;
        }
        textView.setVisibility(8);
    }

    public void bindImagesForWideScreen(String imageUrl, int backgroundImageViewHeight) {
        Intrinsics.checkNotNullParameter(imageUrl, "imageUrl");
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        Intrinsics.checkNotNullExpressionValue(displayMetrics, "context.resources.displayMetrics");
        RequestCreator requestCreatorCenterCrop = Picasso.get().load(imageUrl).resize(displayMetrics.widthPixels, backgroundImageViewHeight).centerCrop();
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        requestCreatorCenterCrop.transform(new BlurTransformation(context)).transform(new GradientTransformation(this.gradientHeightOffsetBackground)).into(getBackgroundImageView());
        Picasso.get().load(imageUrl).error(getErrorStateImage()).resize(displayMetrics.widthPixels / 2, backgroundImageViewHeight).centerCrop().transform(new GradientTransformation(0.0f)).transform(new HorizontalGradientTransformation()).into(getForegroundImageView());
    }

    public void bindImagesForNormalScreen(ImageView backgroundImage, ImageView foreGroundImage, String imageUrl, int backgroundImageViewHeight, int backgroundImageViewWidth) {
        Intrinsics.checkNotNullParameter(backgroundImage, "backgroundImage");
        Intrinsics.checkNotNullParameter(foreGroundImage, "foreGroundImage");
        Intrinsics.checkNotNullParameter(imageUrl, "imageUrl");
        RequestCreator requestCreatorTransform = Picasso.get().load(imageUrl).resize(backgroundImageViewWidth, backgroundImageViewHeight).centerCrop().transform(new AverageColorTransformation()).transform(new GradientOpacityTransformation());
        Resources resources = getContext().getResources();
        int i = R$dimen.corner_radius_medium;
        requestCreatorTransform.transform(new RoundedCornersTransformation(resources.getDimension(i))).into(backgroundImage);
        Picasso.get().load(imageUrl).error(getErrorStateImage()).resize((int) getContext().getResources().getDimension(R$dimen.t1r_foreground_small_width), backgroundImageViewHeight).centerCrop().transform(new RoundedCornersTransformation(getContext().getResources().getDimension(i))).transform(new HorizontalGradientTransformation()).into(foreGroundImage);
    }

    protected final void startCrossFadeAnimation(int pos, List<String> imageUrls, int backgroundHeight, int backgroundWidth, ImageView foreground, ImageView background, FrameLayout foreGroundLayout) {
        Intrinsics.checkNotNullParameter(imageUrls, "imageUrls");
        Intrinsics.checkNotNullParameter(foreground, "foreground");
        Intrinsics.checkNotNullParameter(background, "background");
        Intrinsics.checkNotNullParameter(foreGroundLayout, "foreGroundLayout");
        this.animationScheduler.scheduleWithFixedDelay(new FeaturePlayV2BaseView$startCrossFadeAnimation$animationTask$1(pos, foreground, this, background, imageUrls, backgroundHeight, backgroundWidth, foreGroundLayout), 0L, this.ANIMATION_DELAY_MS, TimeUnit.MILLISECONDS);
    }

    public final void executeAnimationSequence(int pos, List<String> imageUrls, int backgroundImageViewHeight, int backgroundImageViewWidth) {
        if (this.isScreenSizeGreaterThanMD) {
            return;
        }
        getForegroundLayout().setVisibility(0);
        getForegroundLayoutSecond().setVisibility(0);
        getLabelTextView().setVisibility(0);
        getPrimaryTextView().setVisibility(0);
        getTitleTextView().setVisibility(0);
        getLabelTextView().startAnimation(getAnimationSetHeadlineText());
        getTitleTextView().startAnimation(getAnimationSetHeadlineText());
        getPrimaryTextView().startAnimation(getAnimationSetPrimaryText());
        getForegroundLayout().startAnimation(getAnimationSetForegroundImage());
        if (imageUrls == null || imageUrls.size() <= 2) {
            return;
        }
        getAnimationSetHeadlineText().setAnimationListener(new AnonymousClass1(pos, imageUrls, this, backgroundImageViewHeight, backgroundImageViewWidth));
    }

    /* JADX INFO: renamed from: com.amazon.music.views.library.views.FeaturePlayV2BaseView$executeAnimationSequence$1, reason: invalid class name */
    /* JADX INFO: compiled from: FeaturePlayV2BaseView.kt */
    @Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\b"}, d2 = {"com/amazon/music/views/library/views/FeaturePlayV2BaseView$executeAnimationSequence$1", "Landroid/view/animation/Animation$AnimationListener;", "onAnimationEnd", "", "animation", "Landroid/view/animation/Animation;", "onAnimationRepeat", "onAnimationStart", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class AnonymousClass1 implements Animation.AnimationListener {
        final /* synthetic */ int $backgroundImageViewHeight;
        final /* synthetic */ int $backgroundImageViewWidth;
        final /* synthetic */ List<String> $imageUrls;
        final /* synthetic */ int $pos;
        final /* synthetic */ FeaturePlayV2BaseView this$0;

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
            Intrinsics.checkNotNullParameter(animation, "animation");
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            Intrinsics.checkNotNullParameter(animation, "animation");
        }

        AnonymousClass1(int i, List<String> list, FeaturePlayV2BaseView featurePlayV2BaseView, int i2, int i3) {
            this.$pos = i;
            this.$imageUrls = list;
            this.this$0 = featurePlayV2BaseView;
            this.$backgroundImageViewHeight = i2;
            this.$backgroundImageViewWidth = i3;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            Intrinsics.checkNotNullParameter(animation, "animation");
            final int size = (this.$pos + 1) % this.$imageUrls.size();
            final int size2 = (this.$pos + 2) % this.$imageUrls.size();
            AnimationUtil.INSTANCE.panInAnimation(this.this$0.getForegroundLayout(), CoroutineLiveDataKt.DEFAULT_TIMEOUT);
            ScheduledExecutorService scheduledExecutorService = this.this$0.animationScheduler;
            final FeaturePlayV2BaseView featurePlayV2BaseView = this.this$0;
            final List<String> list = this.$imageUrls;
            final int i = this.$backgroundImageViewHeight;
            final int i2 = this.$backgroundImageViewWidth;
            Runnable runnable = new Runnable() { // from class: com.amazon.music.views.library.views.FeaturePlayV2BaseView$executeAnimationSequence$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    FeaturePlayV2BaseView.AnonymousClass1.m2406onAnimationEnd$lambda0(featurePlayV2BaseView, size, list, i, i2);
                }
            };
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            scheduledExecutorService.schedule(runnable, 4000L, timeUnit);
            ScheduledExecutorService scheduledExecutorService2 = this.this$0.animationScheduler;
            final FeaturePlayV2BaseView featurePlayV2BaseView2 = this.this$0;
            final List<String> list2 = this.$imageUrls;
            final int i3 = this.$backgroundImageViewHeight;
            final int i4 = this.$backgroundImageViewWidth;
            scheduledExecutorService2.schedule(new Runnable() { // from class: com.amazon.music.views.library.views.FeaturePlayV2BaseView$executeAnimationSequence$1$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    FeaturePlayV2BaseView.AnonymousClass1.m2407onAnimationEnd$lambda1(featurePlayV2BaseView2, size2, list2, i3, i4);
                }
            }, 8000L, timeUnit);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: onAnimationEnd$lambda-0, reason: not valid java name */
        public static final void m2406onAnimationEnd$lambda0(FeaturePlayV2BaseView this$0, int i, List list, int i2, int i3) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            AnimationUtil animationUtil = AnimationUtil.INSTANCE;
            AnimationUtil.fadeOutImage$default(animationUtil, this$0.getForegroundImageView(), 1000L, null, 4, null);
            AnimationUtil.fadeOutImage$default(animationUtil, this$0.getBackgroundImageView(), 1000L, null, 4, null);
            this$0.startCrossFadeAnimation(i, list, i2, i3, this$0.getForegroundImageViewSecond(), this$0.getBackgroundImageViewSecond(), this$0.getForegroundLayoutSecond());
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: onAnimationEnd$lambda-1, reason: not valid java name */
        public static final void m2407onAnimationEnd$lambda1(FeaturePlayV2BaseView this$0, int i, List list, int i2, int i3) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.startCrossFadeAnimation(i, list, i2, i3, this$0.getForegroundImageView(), this$0.getBackgroundImageView(), this$0.getForegroundLayout());
        }
    }
}
