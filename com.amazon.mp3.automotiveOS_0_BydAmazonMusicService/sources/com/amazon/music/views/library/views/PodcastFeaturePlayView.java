package com.amazon.music.views.library.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.music.views.library.R$color;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.R$string;
import com.amazon.music.views.library.models.PodcastFeaturePlayModel;
import com.amazon.music.views.library.providers.FeaturedPlayMusicProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.utils.BlurTransformation;
import com.amazon.music.views.library.utils.GradientTransformation;
import com.amazon.music.views.library.utils.HorizontalGradientTransformation;
import com.amazon.music.views.library.utils.RoundedCornersTransformation;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PodcastFeaturePlayView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001BR\u0012\u0006\u0010~\u001a\u00020}\u0012\u0006\u0010%\u001a\u00020$\u0012\n\b\u0002\u0010*\u001a\u0004\u0018\u00010)\u0012\u0006\u0010/\u001a\u00020.\u0012\u0006\u00103\u001a\u000202\u0012\b\b\u0002\u00107\u001a\u00020\u0010\u0012\b\b\u0002\u0010;\u001a\u00020\u0010\u0012\b\b\u0002\u0010=\u001a\u00020.¢\u0006\u0005\b\u007f\u0010\u0080\u0001J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0004\u001a\u00020\u0002H\u0002J\b\u0010\u0005\u001a\u00020\u0002H\u0002J\u0010\u0010\b\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u001c\u0010\r\u001a\u00020\u00022\b\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0002JG\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u000e2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0010H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u0006\u0010\u0017\u001a\u00020\u0002J\u000e\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0006J\u0006\u0010\u0019\u001a\u00020\u000eJ\u0010\u0010\u001a\u001a\u00020\u00022\b\u0010\f\u001a\u0004\u0018\u00010\u000bJ\u0010\u0010\u001b\u001a\u00020\u00022\b\u0010\f\u001a\u0004\u0018\u00010\u000bJ\u0010\u0010\u001c\u001a\u00020\u00022\b\u0010\f\u001a\u0004\u0018\u00010\u000bJ\u0010\u0010\u001d\u001a\u00020\u00022\b\u0010\f\u001a\u0004\u0018\u00010\u000bJ\u0010\u0010\u001e\u001a\u00020\u00022\b\u0010\f\u001a\u0004\u0018\u00010\u000bJ\u0016\u0010\"\u001a\u00020\u00022\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u0010J\u0016\u0010#\u001a\u00020\u00022\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u0010R\u0017\u0010%\u001a\u00020$8\u0006¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(R\u0019\u0010*\u001a\u0004\u0018\u00010)8\u0006¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-R\u0017\u0010/\u001a\u00020.8\u0006¢\u0006\f\n\u0004\b/\u00100\u001a\u0004\b/\u00101R\u0017\u00103\u001a\u0002028\u0006¢\u0006\f\n\u0004\b3\u00104\u001a\u0004\b5\u00106R\u0017\u00107\u001a\u00020\u00108\u0006¢\u0006\f\n\u0004\b7\u00108\u001a\u0004\b9\u0010:R\u0017\u0010;\u001a\u00020\u00108\u0006¢\u0006\f\n\u0004\b;\u00108\u001a\u0004\b<\u0010:R\u0014\u0010=\u001a\u00020.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b=\u00100R\u001b\u0010C\u001a\u00020>8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b?\u0010@\u001a\u0004\bA\u0010BR\u001b\u0010F\u001a\u00020>8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bD\u0010@\u001a\u0004\bE\u0010BR\u001b\u0010J\u001a\u00020\t8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bG\u0010@\u001a\u0004\bH\u0010IR\u001b\u0010M\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bK\u0010@\u001a\u0004\bL\u0010IR\u001b\u0010P\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bN\u0010@\u001a\u0004\bO\u0010IR\u001b\u0010S\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bQ\u0010@\u001a\u0004\bR\u0010IR\u001b\u0010V\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bT\u0010@\u001a\u0004\bU\u0010IR\u001b\u0010[\u001a\u00020W8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bX\u0010@\u001a\u0004\bY\u0010ZR\u001b\u0010`\u001a\u00020\\8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b]\u0010@\u001a\u0004\b^\u0010_R\u001b\u0010c\u001a\u00020\t8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\ba\u0010@\u001a\u0004\bb\u0010IR\u001b\u0010f\u001a\u00020>8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bd\u0010@\u001a\u0004\be\u0010BR\u0018\u0010h\u001a\u0004\u0018\u00010g8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bh\u0010iR\u0018\u0010j\u001a\u0004\u0018\u00010g8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bj\u0010iR\u0018\u0010l\u001a\u0004\u0018\u00010k8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bl\u0010mR\u0018\u0010n\u001a\u0004\u0018\u00010k8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bn\u0010mR\u0018\u0010o\u001a\u0004\u0018\u00010k8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bo\u0010mR\u0018\u0010p\u001a\u0004\u0018\u00010k8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bp\u0010mR\u0016\u0010q\u001a\u00020\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bq\u0010rR\u0016\u0010s\u001a\u00020\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bs\u0010rR\u0016\u0010t\u001a\u00020\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bt\u0010rR\u0016\u0010v\u001a\u00020u8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bv\u0010wR\u0016\u0010x\u001a\u00020u8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bx\u0010wR\u0016\u0010y\u001a\u0004\u0018\u00010\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\by\u0010zR\u0016\u0010{\u001a\u0004\u0018\u00010\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b{\u0010zR\u0016\u0010|\u001a\u0004\u0018\u00010\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b|\u0010z¨\u0006\u0081\u0001"}, d2 = {"Lcom/amazon/music/views/library/views/PodcastFeaturePlayView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "", "initViewStyling", "startEqualizer", "pauseEqualizer", "Lcom/amazon/music/views/library/models/PodcastFeaturePlayModel;", "model", "stopEqualizer", "Landroid/widget/TextView;", "textViewPodcast", "", "text", "setOrHideText", "Landroid/view/View;", "view", "", "left", "top", "right", "bottom", "setMargins", "(Landroid/view/View;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "init", "setPlayState", "getPlayButtonView", "setLabelText", "setTitleText", "setPrimaryText", "setSecondaryText", "setTertiaryText", "", "imageUrl", "backgroundImageViewHeight", "bindImagesForWideScreen", "bindImagesForNormalScreen", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "Landroid/util/AttributeSet;", "attrs", "Landroid/util/AttributeSet;", "getAttrs", "()Landroid/util/AttributeSet;", "", "isScreenSizeGreaterThanMD", "Z", "()Z", "Lcom/amazon/music/views/library/providers/FeaturedPlayMusicProvider;", "playIconOnClickMusicProvider", "Lcom/amazon/music/views/library/providers/FeaturedPlayMusicProvider;", "getPlayIconOnClickMusicProvider", "()Lcom/amazon/music/views/library/providers/FeaturedPlayMusicProvider;", "defStyleAttr", "I", "getDefStyleAttr", "()I", "defStyleRes", "getDefStyleRes", "isSonicRushEnabled", "Landroid/widget/ImageView;", "backgroundImageView$delegate", "Lkotlin/Lazy;", "getBackgroundImageView", "()Landroid/widget/ImageView;", "backgroundImageView", "foregroundImageView$delegate", "getForegroundImageView", "foregroundImageView", "titleTextViewPodcast$delegate", "getTitleTextViewPodcast", "()Landroid/widget/TextView;", "titleTextViewPodcast", "primaryTextViewPodcast$delegate", "getPrimaryTextViewPodcast", "primaryTextViewPodcast", "secondaryTextViewPodcast$delegate", "getSecondaryTextViewPodcast", "secondaryTextViewPodcast", "tertiaryTextViewPodcast$delegate", "getTertiaryTextViewPodcast", "tertiaryTextViewPodcast", "labelTextViewPodcast$delegate", "getLabelTextViewPodcast", "labelTextViewPodcast", "Landroid/widget/ImageButton;", "playButton$delegate", "getPlayButton", "()Landroid/widget/ImageButton;", "playButton", "Landroid/widget/RelativeLayout;", "playButtonWideScreen$delegate", "getPlayButtonWideScreen", "()Landroid/widget/RelativeLayout;", "playButtonWideScreen", "playButtonWideScreenTextView$delegate", "getPlayButtonWideScreenTextView", "playButtonWideScreenTextView", "playButtonWideScreenImageView$delegate", "getPlayButtonWideScreenImageView", "playButtonWideScreenImageView", "Landroid/graphics/drawable/AnimationDrawable;", "playingDrawable", "Landroid/graphics/drawable/AnimationDrawable;", "refreshPlayingDrawable", "Landroid/graphics/drawable/Drawable;", "playDrawable", "Landroid/graphics/drawable/Drawable;", "refreshPlayDrawable", "pausedDrawable", "refreshPausedDrawable", "buttonPlayText", "Ljava/lang/String;", "buttonPlayingText", "buttonPausedText", "", "gradientHeightOffsetForeground", "F", "gradientHeightOffsetBackground", "microSpace", "Ljava/lang/Integer;", "baseSpace", "largeSpace", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;Landroid/util/AttributeSet;ZLcom/amazon/music/views/library/providers/FeaturedPlayMusicProvider;IIZ)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class PodcastFeaturePlayView extends ConstraintLayout {
    private final AttributeSet attrs;

    /* JADX INFO: renamed from: backgroundImageView$delegate, reason: from kotlin metadata */
    private final Lazy backgroundImageView;
    private final Integer baseSpace;
    private String buttonPausedText;
    private String buttonPlayText;
    private String buttonPlayingText;
    private final int defStyleAttr;
    private final int defStyleRes;

    /* JADX INFO: renamed from: foregroundImageView$delegate, reason: from kotlin metadata */
    private final Lazy foregroundImageView;
    private float gradientHeightOffsetBackground;
    private float gradientHeightOffsetForeground;
    private final boolean isScreenSizeGreaterThanMD;
    private final boolean isSonicRushEnabled;

    /* JADX INFO: renamed from: labelTextViewPodcast$delegate, reason: from kotlin metadata */
    private final Lazy labelTextViewPodcast;
    private final Integer largeSpace;
    private final Integer microSpace;
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

    /* JADX INFO: renamed from: primaryTextViewPodcast$delegate, reason: from kotlin metadata */
    private final Lazy primaryTextViewPodcast;
    private Drawable refreshPausedDrawable;
    private Drawable refreshPlayDrawable;
    private AnimationDrawable refreshPlayingDrawable;

    /* JADX INFO: renamed from: secondaryTextViewPodcast$delegate, reason: from kotlin metadata */
    private final Lazy secondaryTextViewPodcast;
    private final StyleSheet styleSheet;

    /* JADX INFO: renamed from: tertiaryTextViewPodcast$delegate, reason: from kotlin metadata */
    private final Lazy tertiaryTextViewPodcast;

    /* JADX INFO: renamed from: titleTextViewPodcast$delegate, reason: from kotlin metadata */
    private final Lazy titleTextViewPodcast;

    public /* synthetic */ PodcastFeaturePlayView(Context context, StyleSheet styleSheet, AttributeSet attributeSet, boolean z, FeaturedPlayMusicProvider featuredPlayMusicProvider, int i, int i2, boolean z2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, styleSheet, (i3 & 4) != 0 ? null : attributeSet, z, featuredPlayMusicProvider, (i3 & 32) != 0 ? 0 : i, (i3 & 64) != 0 ? 0 : i2, (i3 & 128) != 0 ? false : z2);
    }

    public final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    public final AttributeSet getAttrs() {
        return this.attrs;
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
    public PodcastFeaturePlayView(Context context, StyleSheet styleSheet, AttributeSet attributeSet, boolean z, FeaturedPlayMusicProvider playIconOnClickMusicProvider, int i, int i2, boolean z2) {
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
        this.backgroundImageView = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.music.views.library.views.PodcastFeaturePlayView$backgroundImageView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ImageView invoke() {
                return (ImageView) this.this$0.findViewById(R$id.background_gradient_image_view);
            }
        });
        this.foregroundImageView = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.music.views.library.views.PodcastFeaturePlayView$foregroundImageView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ImageView invoke() {
                return (ImageView) this.this$0.findViewById(R$id.foreground_gradient_image_view);
            }
        });
        this.titleTextViewPodcast = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.PodcastFeaturePlayView$titleTextViewPodcast$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.title);
            }
        });
        this.primaryTextViewPodcast = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.PodcastFeaturePlayView$primaryTextViewPodcast$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.primary_text);
            }
        });
        this.secondaryTextViewPodcast = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.PodcastFeaturePlayView$secondaryTextViewPodcast$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.secondary_text);
            }
        });
        this.tertiaryTextViewPodcast = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.PodcastFeaturePlayView$tertiaryTextViewPodcast$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.tertiary_text);
            }
        });
        this.labelTextViewPodcast = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.PodcastFeaturePlayView$labelTextViewPodcast$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.label);
            }
        });
        this.playButton = LazyKt__LazyJVMKt.lazy(new Function0<ImageButton>() { // from class: com.amazon.music.views.library.views.PodcastFeaturePlayView$playButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ImageButton invoke() {
                return (ImageButton) this.this$0.findViewById(R$id.PlayButton);
            }
        });
        this.playButtonWideScreen = LazyKt__LazyJVMKt.lazy(new Function0<RelativeLayout>() { // from class: com.amazon.music.views.library.views.PodcastFeaturePlayView$playButtonWideScreen$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final RelativeLayout invoke() {
                return (RelativeLayout) this.this$0.findViewById(R$id.PlayButtonWideScreen);
            }
        });
        this.playButtonWideScreenTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.PodcastFeaturePlayView$playButtonWideScreenTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.podcast_widescreen_ingress_button_text);
            }
        });
        this.playButtonWideScreenImageView = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.music.views.library.views.PodcastFeaturePlayView$playButtonWideScreenImageView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ImageView invoke() {
                return (ImageView) this.this$0.findViewById(R$id.podcast_widescreen_ingress_button_image);
            }
        });
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
        this.playDrawable = AppCompatResources.getDrawable(context, R$drawable.player_btn_play);
        this.refreshPlayDrawable = AppCompatResources.getDrawable(context, R$drawable.refresh_player_btn_play);
        this.pausedDrawable = AppCompatResources.getDrawable(context, R$drawable.ic_podcast_playback_equalizeroff);
        this.refreshPausedDrawable = AppCompatResources.getDrawable(context, R$drawable.ic_refresh_playback_equalizeroff);
        String string = getResources().getString(R$string.content_description_play_button);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…_description_play_button)");
        this.buttonPlayText = string;
        String string2 = getResources().getString(R$string.content_description_playing);
        Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.st…tent_description_playing)");
        this.buttonPlayingText = string2;
        String string3 = getResources().getString(R$string.content_description_paused);
        Intrinsics.checkNotNullExpressionValue(string3, "resources.getString(R.st…ntent_description_paused)");
        this.buttonPausedText = string3;
        this.gradientHeightOffsetForeground = getResources().getDimension(R$dimen.height_offset_foreground);
        this.gradientHeightOffsetBackground = getResources().getDimension(R$dimen.height_offset_background);
        this.microSpace = styleSheet.getSpacerInPixels(SpacerKey.MICRO);
        this.baseSpace = styleSheet.getSpacerInPixels(SpacerKey.BASE);
        this.largeSpace = styleSheet.getSpacerInPixels(SpacerKey.LARGE);
    }

    public final ImageView getBackgroundImageView() {
        Object value = this.backgroundImageView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-backgroundImageView>(...)");
        return (ImageView) value;
    }

    public final ImageView getForegroundImageView() {
        Object value = this.foregroundImageView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-foregroundImageView>(...)");
        return (ImageView) value;
    }

    public final TextView getTitleTextViewPodcast() {
        Object value = this.titleTextViewPodcast.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-titleTextViewPodcast>(...)");
        return (TextView) value;
    }

    private final TextView getPrimaryTextViewPodcast() {
        Object value = this.primaryTextViewPodcast.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-primaryTextViewPodcast>(...)");
        return (TextView) value;
    }

    private final TextView getSecondaryTextViewPodcast() {
        Object value = this.secondaryTextViewPodcast.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-secondaryTextViewPodcast>(...)");
        return (TextView) value;
    }

    private final TextView getTertiaryTextViewPodcast() {
        Object value = this.tertiaryTextViewPodcast.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-tertiaryTextViewPodcast>(...)");
        return (TextView) value;
    }

    private final TextView getLabelTextViewPodcast() {
        Object value = this.labelTextViewPodcast.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-labelTextViewPodcast>(...)");
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

    public final void init() {
        if (this.isScreenSizeGreaterThanMD) {
            View.inflate(getContext(), R$layout.podcast_feature_play_widescreen, this);
        } else {
            View.inflate(getContext(), R$layout.podcast_feature_play, this);
        }
        initViewStyling();
    }

    private final void initViewStyling() {
        FontStyle fontStyle = this.styleSheet.getFontStyle(FontStyleKey.LABEL);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(getLabelTextViewPodcast(), fontStyle);
        }
        FontStyle fontStyle2 = this.styleSheet.getFontStyle(FontStyleKey.HEADLINE_2);
        if (fontStyle2 != null) {
            FontUtil.INSTANCE.applyFontStyle(getTitleTextViewPodcast(), fontStyle2);
        }
        FontStyle fontStyle3 = this.styleSheet.getFontStyle(FontStyleKey.PRIMARY);
        if (fontStyle3 != null) {
            FontUtil.INSTANCE.applyFontStyle(getPrimaryTextViewPodcast(), fontStyle3);
        }
        FontStyle fontStyle4 = this.styleSheet.getFontStyle(FontStyleKey.SECONDARY);
        if (fontStyle4 != null) {
            FontUtil.INSTANCE.applyFontStyle(getSecondaryTextViewPodcast(), fontStyle4);
        }
        FontStyle fontStyle5 = this.styleSheet.getFontStyle(FontStyleKey.TERTIARY);
        if (fontStyle5 == null) {
            return;
        }
        FontUtil.INSTANCE.applyFontStyle(getTertiaryTextViewPodcast(), fontStyle5);
    }

    public final void setPlayState(PodcastFeaturePlayModel model) {
        Integer playState;
        Intrinsics.checkNotNullParameter(model, "model");
        Integer playState2 = model.getPlayState();
        if (playState2 != null && playState2.intValue() == 3) {
            startEqualizer();
            return;
        }
        Integer playState3 = model.getPlayState();
        if ((playState3 != null && playState3.intValue() == 2) || ((playState = model.getPlayState()) != null && playState.intValue() == 6)) {
            pauseEqualizer();
        } else {
            stopEqualizer(model);
        }
    }

    public final View getPlayButtonView() {
        if (this.isScreenSizeGreaterThanMD) {
            return getPlayButtonWideScreen();
        }
        return getPlayButton();
    }

    private final void startEqualizer() {
        if (this.isScreenSizeGreaterThanMD) {
            if (this.isSonicRushEnabled) {
                getPlayButtonWideScreen().setBackgroundResource(R$drawable.refresh_accent_btn);
                ImageView playButtonWideScreenImageView = getPlayButtonWideScreenImageView();
                Intrinsics.checkNotNull(playButtonWideScreenImageView);
                playButtonWideScreenImageView.setImageDrawable(this.refreshPlayingDrawable);
                TextView playButtonWideScreenTextView = getPlayButtonWideScreenTextView();
                if (playButtonWideScreenTextView != null) {
                    playButtonWideScreenTextView.setTextColor(getResources().getColor(R$color.secondary));
                }
                AnimationDrawable animationDrawable = this.refreshPlayingDrawable;
                Intrinsics.checkNotNull(animationDrawable);
                animationDrawable.start();
            } else {
                getPlayButtonWideScreen().setBackgroundResource(R$drawable.primary_glass_2_rounded_corners);
                ImageView playButtonWideScreenImageView2 = getPlayButtonWideScreenImageView();
                Intrinsics.checkNotNull(playButtonWideScreenImageView2);
                playButtonWideScreenImageView2.setImageDrawable(this.playingDrawable);
                TextView playButtonWideScreenTextView2 = getPlayButtonWideScreenTextView();
                if (playButtonWideScreenTextView2 != null) {
                    playButtonWideScreenTextView2.setTextColor(getResources().getColor(R$color.primary));
                }
                AnimationDrawable animationDrawable2 = this.playingDrawable;
                Intrinsics.checkNotNull(animationDrawable2);
                animationDrawable2.start();
            }
            TextView playButtonWideScreenTextView3 = getPlayButtonWideScreenTextView();
            if (playButtonWideScreenTextView3 != null) {
                playButtonWideScreenTextView3.setText(this.buttonPlayingText);
            }
            RelativeLayout playButtonWideScreen = getPlayButtonWideScreen();
            if (playButtonWideScreen == null) {
                return;
            }
            playButtonWideScreen.setOnClickListener(null);
            return;
        }
        if (this.isSonicRushEnabled) {
            ImageButton playButton = getPlayButton();
            if (playButton != null) {
                playButton.setImageDrawable(this.refreshPlayingDrawable);
            }
            getPlayButton().setBackgroundResource(R$drawable.refresh_accent_btn);
            AnimationDrawable animationDrawable3 = this.refreshPlayingDrawable;
            Intrinsics.checkNotNull(animationDrawable3);
            animationDrawable3.start();
        } else {
            ImageButton playButton2 = getPlayButton();
            if (playButton2 != null) {
                playButton2.setImageDrawable(this.playingDrawable);
            }
            getPlayButton().setBackgroundResource(R$drawable.primary_glass_2_rounded_corners);
            AnimationDrawable animationDrawable4 = this.playingDrawable;
            if (animationDrawable4 != null) {
                animationDrawable4.start();
            }
        }
        ImageButton playButton3 = getPlayButton();
        if (playButton3 == null) {
            return;
        }
        playButton3.setOnClickListener(null);
    }

    private final void pauseEqualizer() {
        if (this.isScreenSizeGreaterThanMD) {
            if (this.isSonicRushEnabled) {
                getPlayButtonWideScreen().setBackgroundResource(R$drawable.refresh_accent_btn);
                ImageView playButtonWideScreenImageView = getPlayButtonWideScreenImageView();
                Intrinsics.checkNotNull(playButtonWideScreenImageView);
                playButtonWideScreenImageView.setImageDrawable(this.refreshPausedDrawable);
                TextView playButtonWideScreenTextView = getPlayButtonWideScreenTextView();
                if (playButtonWideScreenTextView != null) {
                    playButtonWideScreenTextView.setTextColor(getResources().getColor(R$color.secondary));
                }
            } else {
                getPlayButtonWideScreen().setBackgroundResource(R$drawable.primary_glass_2_rounded_corners);
                ImageView playButtonWideScreenImageView2 = getPlayButtonWideScreenImageView();
                Intrinsics.checkNotNull(playButtonWideScreenImageView2);
                playButtonWideScreenImageView2.setImageDrawable(this.pausedDrawable);
                TextView playButtonWideScreenTextView2 = getPlayButtonWideScreenTextView();
                if (playButtonWideScreenTextView2 != null) {
                    playButtonWideScreenTextView2.setTextColor(getResources().getColor(R$color.primary));
                }
            }
            TextView playButtonWideScreenTextView3 = getPlayButtonWideScreenTextView();
            if (playButtonWideScreenTextView3 != null) {
                playButtonWideScreenTextView3.setText(this.buttonPausedText);
            }
            RelativeLayout playButtonWideScreen = getPlayButtonWideScreen();
            if (playButtonWideScreen == null) {
                return;
            }
            playButtonWideScreen.setOnClickListener(null);
            return;
        }
        if (this.isSonicRushEnabled) {
            ImageButton playButton = getPlayButton();
            if (playButton != null) {
                playButton.setImageDrawable(this.refreshPausedDrawable);
            }
            getPlayButton().setBackgroundResource(R$drawable.refresh_accent_btn);
        } else {
            ImageButton playButton2 = getPlayButton();
            if (playButton2 != null) {
                playButton2.setImageDrawable(this.pausedDrawable);
            }
            getPlayButton().setBackgroundResource(R$drawable.primary_glass_2_rounded_corners);
        }
        ImageButton playButton3 = getPlayButton();
        if (playButton3 == null) {
            return;
        }
        playButton3.setOnClickListener(null);
    }

    private final void stopEqualizer(final PodcastFeaturePlayModel model) {
        if (this.isScreenSizeGreaterThanMD) {
            if (this.isSonicRushEnabled) {
                TextView playButtonWideScreenTextView = getPlayButtonWideScreenTextView();
                if (playButtonWideScreenTextView != null) {
                    playButtonWideScreenTextView.setTextColor(getResources().getColor(R$color.secondary));
                }
                getPlayButtonWideScreen().setBackgroundResource(R$drawable.refresh_accent_btn);
                ImageView playButtonWideScreenImageView = getPlayButtonWideScreenImageView();
                Intrinsics.checkNotNull(playButtonWideScreenImageView);
                playButtonWideScreenImageView.setImageDrawable(this.refreshPlayDrawable);
            } else {
                TextView playButtonWideScreenTextView2 = getPlayButtonWideScreenTextView();
                if (playButtonWideScreenTextView2 != null) {
                    playButtonWideScreenTextView2.setTextColor(getResources().getColor(R$color.primary));
                }
                getPlayButtonWideScreen().setBackgroundResource(R$drawable.primary_glass_2_rounded_corners);
                ImageView playButtonWideScreenImageView2 = getPlayButtonWideScreenImageView();
                Intrinsics.checkNotNull(playButtonWideScreenImageView2);
                playButtonWideScreenImageView2.setImageDrawable(this.playDrawable);
            }
            TextView playButtonWideScreenTextView3 = getPlayButtonWideScreenTextView();
            if (playButtonWideScreenTextView3 != null) {
                playButtonWideScreenTextView3.setText(this.buttonPlayText);
            }
            RelativeLayout playButtonWideScreen = getPlayButtonWideScreen();
            if (playButtonWideScreen != null) {
                playButtonWideScreen.setClickable(true);
            }
            RelativeLayout playButtonWideScreen2 = getPlayButtonWideScreen();
            if (playButtonWideScreen2 == null) {
                return;
            }
            playButtonWideScreen2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.views.PodcastFeaturePlayView$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PodcastFeaturePlayView.m2426stopEqualizer$lambda11(this.f$0, model, view);
                }
            });
            return;
        }
        if (this.isSonicRushEnabled) {
            ImageButton playButton = getPlayButton();
            if (playButton != null) {
                playButton.setImageDrawable(this.refreshPlayDrawable);
            }
            getPlayButton().setBackgroundResource(R$drawable.refresh_accent_btn);
        } else {
            ImageButton playButton2 = getPlayButton();
            if (playButton2 != null) {
                playButton2.setImageDrawable(this.playDrawable);
            }
            getPlayButton().setBackgroundResource(R$drawable.primary_glass_2_rounded_corners);
        }
        ImageButton playButton3 = getPlayButton();
        if (playButton3 != null) {
            playButton3.setClickable(true);
        }
        ImageButton playButton4 = getPlayButton();
        if (playButton4 == null) {
            return;
        }
        playButton4.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.views.PodcastFeaturePlayView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PodcastFeaturePlayView.m2427stopEqualizer$lambda12(this.f$0, model, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: stopEqualizer$lambda-11, reason: not valid java name */
    public static final void m2426stopEqualizer$lambda11(PodcastFeaturePlayView this$0, PodcastFeaturePlayModel model, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        FeaturedPlayMusicProvider featuredPlayMusicProvider = this$0.playIconOnClickMusicProvider;
        if (featuredPlayMusicProvider == null) {
            return;
        }
        featuredPlayMusicProvider.startPlayback(model.getMetadata());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: stopEqualizer$lambda-12, reason: not valid java name */
    public static final void m2427stopEqualizer$lambda12(PodcastFeaturePlayView this$0, PodcastFeaturePlayModel model, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        FeaturedPlayMusicProvider featuredPlayMusicProvider = this$0.playIconOnClickMusicProvider;
        if (featuredPlayMusicProvider == null) {
            return;
        }
        featuredPlayMusicProvider.startPlayback(model.getMetadata());
    }

    public final void setLabelText(CharSequence text) {
        setOrHideText(getLabelTextViewPodcast(), text);
        if (TextUtils.isEmpty(text) || this.isScreenSizeGreaterThanMD) {
            return;
        }
        setMargins(getLabelTextViewPodcast(), null, this.baseSpace, null, this.microSpace);
    }

    public final void setTitleText(CharSequence text) {
        setOrHideText(getTitleTextViewPodcast(), text);
        if (TextUtils.isEmpty(text) || this.isScreenSizeGreaterThanMD) {
            return;
        }
        setMargins(getTitleTextViewPodcast(), null, 0, null, this.microSpace);
    }

    public final void setPrimaryText(CharSequence text) {
        setOrHideText(getPrimaryTextViewPodcast(), text);
        if (TextUtils.isEmpty(text) || this.isScreenSizeGreaterThanMD) {
            return;
        }
        setMargins(getPrimaryTextViewPodcast(), null, 0, null, this.largeSpace);
    }

    public final void setSecondaryText(CharSequence text) {
        setOrHideText(getSecondaryTextViewPodcast(), text);
        if (TextUtils.isEmpty(text) || this.isScreenSizeGreaterThanMD) {
            return;
        }
        setMargins(getPrimaryTextViewPodcast(), null, 0, null, this.microSpace);
        setMargins(getSecondaryTextViewPodcast(), null, 0, null, this.largeSpace);
    }

    public final void setTertiaryText(CharSequence text) {
        setOrHideText(getTertiaryTextViewPodcast(), text);
        if (TextUtils.isEmpty(text) || this.isScreenSizeGreaterThanMD) {
            return;
        }
        setMargins(getSecondaryTextViewPodcast(), null, 0, null, this.microSpace);
        setMargins(getTitleTextViewPodcast(), null, 0, null, this.largeSpace);
    }

    private final void setOrHideText(TextView textViewPodcast, CharSequence text) {
        if (textViewPodcast != null) {
            textViewPodcast.setText(text);
        }
        if (TextUtils.isEmpty(text)) {
            if (textViewPodcast == null) {
                return;
            }
            textViewPodcast.setVisibility(8);
        } else {
            if (textViewPodcast == null) {
                return;
            }
            textViewPodcast.setVisibility(0);
        }
    }

    public final void bindImagesForWideScreen(String imageUrl, int backgroundImageViewHeight) {
        Intrinsics.checkNotNullParameter(imageUrl, "imageUrl");
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        Intrinsics.checkNotNullExpressionValue(displayMetrics, "context.resources.displayMetrics");
        RequestCreator requestCreatorCenterCrop = Picasso.get().load(imageUrl).resize(displayMetrics.widthPixels, backgroundImageViewHeight).centerCrop();
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        requestCreatorCenterCrop.transform(new BlurTransformation(context)).transform(new GradientTransformation(this.gradientHeightOffsetBackground)).into(getBackgroundImageView());
        RequestCreator requestCreatorLoad = Picasso.get().load(imageUrl);
        int i = R$drawable.ic_empty_state_image;
        requestCreatorLoad.placeholder(i).error(i).resize(displayMetrics.widthPixels / 2, backgroundImageViewHeight).centerCrop().transform(new GradientTransformation(0.0f)).transform(new HorizontalGradientTransformation()).into(getForegroundImageView());
    }

    public final void bindImagesForNormalScreen(String imageUrl, int backgroundImageViewHeight) {
        Intrinsics.checkNotNullParameter(imageUrl, "imageUrl");
        Resources resources = getResources();
        Intrinsics.checkNotNull(resources);
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Intrinsics.checkNotNullExpressionValue(displayMetrics, "resources!!.displayMetrics");
        RequestCreator requestCreatorCenterCrop = Picasso.get().load(imageUrl).resize(displayMetrics.widthPixels, backgroundImageViewHeight).centerCrop();
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        requestCreatorCenterCrop.transform(new BlurTransformation(context)).transform(new GradientTransformation(0.0f)).into(getBackgroundImageView());
        RequestCreator requestCreatorLoad = Picasso.get().load(imageUrl);
        int i = R$drawable.ic_empty_state_image;
        requestCreatorLoad.placeholder(i).error(i).resize((int) getContext().getResources().getDimension(R$dimen.t1r_foreground_height), (int) getContext().getResources().getDimension(R$dimen.t1r_foreground_width)).centerCrop().transform(new RoundedCornersTransformation(getContext().getResources().getDimension(R$dimen.corner_radius_medium))).transform(new GradientTransformation(this.gradientHeightOffsetForeground)).into(getForegroundImageView());
    }

    private final void setMargins(View view, Integer left, Integer top, Integer right, Integer bottom) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(view, -1, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
    }
}
