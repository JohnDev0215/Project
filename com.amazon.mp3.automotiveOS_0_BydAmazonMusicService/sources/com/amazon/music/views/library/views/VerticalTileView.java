package com.amazon.music.views.library.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.R$string;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.GradientStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.styles.keys.TagSizeKey;
import com.amazon.music.views.library.styles.keys.TagStyleKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.styles.GradientStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import com.google.android.gms.cast.MediaTrack;
import com.google.firebase.perf.util.Constants;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VerticalTileView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u001a\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b)\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u0000 \u008f\u00012\u00020\u0001:\u0002\u008f\u0001B\u001b\u0012\b\u0010\u008c\u0001\u001a\u00030\u008b\u0001\u0012\u0006\u0010=\u001a\u00020<¢\u0006\u0006\b\u008d\u0001\u0010\u008e\u0001J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0004\u001a\u00020\u0002H\u0002JG\u0010\f\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0007H\u0002¢\u0006\u0004\b\f\u0010\rJ\u0018\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010H\u0002J\u0006\u0010\u0014\u001a\u00020\u0013J\u0006\u0010\u0015\u001a\u00020\u0002J\u0012\u0010\u0018\u001a\u00020\u00022\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0016J\u0010\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u0016H\u0016J\u0010\u0010\u001c\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0016H\u0016J\u000e\u0010\u001e\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u0016J\u0010\u0010 \u001a\u00020\u00022\b\u0010\u001f\u001a\u0004\u0018\u00010\u0016J\u0010\u0010\"\u001a\u00020\u00022\b\u0010!\u001a\u0004\u0018\u00010\u0016J\u000e\u0010$\u001a\u00020\u00022\u0006\u0010#\u001a\u00020\u0010J\u000e\u0010&\u001a\u00020\u00022\u0006\u0010%\u001a\u00020\u0010J\u000e\u0010'\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0010J\u000e\u0010(\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0010J\u000e\u0010*\u001a\u00020\u00022\u0006\u0010)\u001a\u00020\u0016J\u0016\u0010-\u001a\u00020\u00022\u0006\u0010+\u001a\u00020\u00102\u0006\u0010,\u001a\u00020\u0007J\u0006\u0010.\u001a\u00020\u0013J\u000e\u00100\u001a\u00020\u00022\u0006\u0010/\u001a\u00020\u0007J#\u00104\u001a\u00020\u00022\b\u00102\u001a\u0004\u0018\u0001012\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b4\u00105J\u0010\u00107\u001a\u00020\u00022\b\u00106\u001a\u0004\u0018\u00010\u0016J\u0010\u00108\u001a\u00020\u00022\u0006\u0010%\u001a\u00020\u0010H\u0016J\b\u0010%\u001a\u00020\u0010H\u0016JG\u0010:\u001a\u00020\u00022\u0006\u00109\u001a\u00020\u000e2\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0007H\u0004¢\u0006\u0004\b:\u0010;R\u0017\u0010=\u001a\u00020<8\u0006¢\u0006\f\n\u0004\b=\u0010>\u001a\u0004\b?\u0010@R\u001b\u0010F\u001a\u00020A8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bB\u0010C\u001a\u0004\bD\u0010ER\u001b\u0010J\u001a\u00020\u000e8DX\u0084\u0084\u0002¢\u0006\f\n\u0004\bG\u0010C\u001a\u0004\bH\u0010IR\u001b\u0010M\u001a\u00020\u000e8DX\u0084\u0084\u0002¢\u0006\f\n\u0004\bK\u0010C\u001a\u0004\bL\u0010IR\u001b\u0010P\u001a\u00020\u000e8DX\u0084\u0084\u0002¢\u0006\f\n\u0004\bN\u0010C\u001a\u0004\bO\u0010IR\u001b\u0010S\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bQ\u0010C\u001a\u0004\bR\u0010IR\u001b\u0010W\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bT\u0010C\u001a\u0004\bU\u0010VR\u001b\u0010Z\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bX\u0010C\u001a\u0004\bY\u0010VR\u001b\u0010]\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b[\u0010C\u001a\u0004\b\\\u0010VR\u001b\u0010a\u001a\u00020\u00138BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b^\u0010C\u001a\u0004\b_\u0010`R\u001b\u0010d\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bb\u0010C\u001a\u0004\bc\u0010VR\u001b\u0010g\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\be\u0010C\u001a\u0004\bf\u0010VR\u001b\u0010j\u001a\u00020\u00138BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bh\u0010C\u001a\u0004\bi\u0010`R\u001b\u0010o\u001a\u00020k8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bl\u0010C\u001a\u0004\bm\u0010nR\u001b\u0010t\u001a\u00020p8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bq\u0010C\u001a\u0004\br\u0010sR\u001b\u0010w\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bu\u0010C\u001a\u0004\bv\u0010IR\u0018\u0010y\u001a\u0004\u0018\u00010x8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\by\u0010zR\u0018\u0010{\u001a\u0004\u0018\u00010x8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b{\u0010zR\u0016\u0010|\u001a\u0004\u0018\u00010\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b|\u0010}R\u0016\u0010~\u001a\u0004\u0018\u00010\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b~\u0010}R\u0017\u0010\u0080\u0001\u001a\u00020\u007f8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0080\u0001\u0010\u0081\u0001R\u001f\u0010\u0082\u0001\u001a\u00020\u00108\u0014X\u0094D¢\u0006\u0010\n\u0006\b\u0082\u0001\u0010\u0083\u0001\u001a\u0006\b\u0084\u0001\u0010\u0085\u0001R \u0010\u0086\u0001\u001a\u0004\u0018\u00010\u00078\u0004X\u0084\u0004¢\u0006\u000f\n\u0005\b\u0086\u0001\u0010}\u001a\u0006\b\u0087\u0001\u0010\u0088\u0001R \u0010\u0089\u0001\u001a\u0004\u0018\u00010\u00078\u0004X\u0084\u0004¢\u0006\u000f\n\u0005\b\u0089\u0001\u0010}\u001a\u0006\b\u008a\u0001\u0010\u0088\u0001¨\u0006\u0090\u0001"}, d2 = {"Lcom/amazon/music/views/library/views/VerticalTileView;", "Lcom/amazon/music/views/library/views/ScopedConstraintLayout;", "", "initViewMargins", "initViewStyling", "Lcom/amazon/ui/foundations/views/BaseButton;", "baseButton", "", "left", "top", "right", "bottom", "setIconMargins", "(Lcom/amazon/ui/foundations/views/BaseButton;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "Landroid/widget/TextView;", "view", "", "shouldCenter", "centerText", "Landroid/widget/ImageView;", "getSourceLogoImageView", "setSourceLogoMargins", "", ContextMappingConstants.LABEL, "setLabel", "title", "setTitle", MediaTrack.ROLE_SUBTITLE, "setSubtitle", "detailsText", "setDetailsText", "audioQualityText", "setAudioQualityText", "immersiveBadgeText", "setImmersiveBadgeText", "shouldDisplay", "setExtraAtmosBadgeText", Constants.ENABLE_DISABLE, "setContentViewsAsEnabled", "centerTitle", "centerSubtitle", "viewerCount", "setViewerCount", "isLive", "width", "setLiveComponents", "getArtistArtworkImageView", "size", "setArtistArtworkMargins", "", "rating", "ratingCount", "setStarRating", "(Ljava/lang/Float;Ljava/lang/Integer;)V", "labelText", "showAllAccessLabel", "setEnabled", "textView", "setTextMargins", "(Landroid/widget/TextView;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "Lcom/amazon/music/views/library/views/ArtworkFrameView;", "artworkFrameView$delegate", "Lkotlin/Lazy;", "getArtworkFrameView", "()Lcom/amazon/music/views/library/views/ArtworkFrameView;", "artworkFrameView", "labelTextView$delegate", "getLabelTextView", "()Landroid/widget/TextView;", "labelTextView", "titleTextView$delegate", "getTitleTextView", "titleTextView", "subtitleTextView$delegate", "getSubtitleTextView", "subtitleTextView", "detailTextView$delegate", "getDetailTextView", "detailTextView", "audioQualityBadgeView$delegate", "getAudioQualityBadgeView", "()Lcom/amazon/ui/foundations/views/BaseButton;", "audioQualityBadgeView", "immersiveContentBadgeView$delegate", "getImmersiveContentBadgeView", "immersiveContentBadgeView", "extraImmersiveContentBadgeView$delegate", "getExtraImmersiveContentBadgeView", "extraImmersiveContentBadgeView", "artistArtwork$delegate", "getArtistArtwork", "()Landroid/widget/ImageView;", "artistArtwork", "liveBadgeButton$delegate", "getLiveBadgeButton", "liveBadgeButton", "viewerCountButton$delegate", "getViewerCountButton", "viewerCountButton", "sourceLogo$delegate", "getSourceLogo", "sourceLogo", "Lcom/amazon/music/views/library/views/TransparentGradientImageView;", "transparentGradientImageView$delegate", "getTransparentGradientImageView", "()Lcom/amazon/music/views/library/views/TransparentGradientImageView;", "transparentGradientImageView", "Lcom/amazon/music/views/library/views/StarRatingView;", "starRating$delegate", "getStarRating", "()Lcom/amazon/music/views/library/views/StarRatingView;", "starRating", "allAccessLabel$delegate", "getAllAccessLabel", "allAccessLabel", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "audioQualityBadgeBuilder", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "immersiveBadgeBuilder", "microSpace", "Ljava/lang/Integer;", "hugeSpace", "Lcom/amazon/music/views/library/views/BadgeCreationHelper;", "badgeCreationHelper", "Lcom/amazon/music/views/library/views/BadgeCreationHelper;", "mIsContentEnabled", "Z", "getMIsContentEnabled", "()Z", "nanoSpace", "getNanoSpace", "()Ljava/lang/Integer;", "smallSpace", "getSmallSpace", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "Companion", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public class VerticalTileView extends ScopedConstraintLayout {

    /* JADX INFO: renamed from: allAccessLabel$delegate, reason: from kotlin metadata */
    private final Lazy allAccessLabel;

    /* JADX INFO: renamed from: artistArtwork$delegate, reason: from kotlin metadata */
    private final Lazy artistArtwork;

    /* JADX INFO: renamed from: artworkFrameView$delegate, reason: from kotlin metadata */
    private final Lazy artworkFrameView;
    private BaseButton.StyleBuilder audioQualityBadgeBuilder;

    /* JADX INFO: renamed from: audioQualityBadgeView$delegate, reason: from kotlin metadata */
    private final Lazy audioQualityBadgeView;
    private final BadgeCreationHelper badgeCreationHelper;

    /* JADX INFO: renamed from: detailTextView$delegate, reason: from kotlin metadata */
    private final Lazy detailTextView;

    /* JADX INFO: renamed from: extraImmersiveContentBadgeView$delegate, reason: from kotlin metadata */
    private final Lazy extraImmersiveContentBadgeView;
    private final Integer hugeSpace;
    private BaseButton.StyleBuilder immersiveBadgeBuilder;

    /* JADX INFO: renamed from: immersiveContentBadgeView$delegate, reason: from kotlin metadata */
    private final Lazy immersiveContentBadgeView;

    /* JADX INFO: renamed from: labelTextView$delegate, reason: from kotlin metadata */
    private final Lazy labelTextView;

    /* JADX INFO: renamed from: liveBadgeButton$delegate, reason: from kotlin metadata */
    private final Lazy liveBadgeButton;
    private final boolean mIsContentEnabled;
    private final Integer microSpace;
    private final Integer nanoSpace;
    private final Integer smallSpace;

    /* JADX INFO: renamed from: sourceLogo$delegate, reason: from kotlin metadata */
    private final Lazy sourceLogo;

    /* JADX INFO: renamed from: starRating$delegate, reason: from kotlin metadata */
    private final Lazy starRating;
    private final StyleSheet styleSheet;

    /* JADX INFO: renamed from: subtitleTextView$delegate, reason: from kotlin metadata */
    private final Lazy subtitleTextView;

    /* JADX INFO: renamed from: titleTextView$delegate, reason: from kotlin metadata */
    private final Lazy titleTextView;

    /* JADX INFO: renamed from: transparentGradientImageView$delegate, reason: from kotlin metadata */
    private final Lazy transparentGradientImageView;

    /* JADX INFO: renamed from: viewerCountButton$delegate, reason: from kotlin metadata */
    private final Lazy viewerCountButton;
    private static final float ENABLED_ALPHA_VALUE = 1.0f;
    private static final float DISABLED_ALPHA_VALUE = 0.3f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VerticalTileView(Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.artworkFrameView = LazyKt__LazyJVMKt.lazy(new Function0<ArtworkFrameView>() { // from class: com.amazon.music.views.library.views.VerticalTileView$artworkFrameView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ArtworkFrameView invoke() {
                View viewFindViewById = this.this$0.findViewById(R$id.artwork_frame);
                if (viewFindViewById != null) {
                    return (ArtworkFrameView) viewFindViewById;
                }
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.views.ArtworkFrameView");
            }
        });
        this.labelTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.VerticalTileView$labelTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.label);
            }
        });
        this.titleTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.VerticalTileView$titleTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.title);
            }
        });
        this.subtitleTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.VerticalTileView$subtitleTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.subtitle);
            }
        });
        this.detailTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.VerticalTileView$detailTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.recommendation_text);
            }
        });
        this.audioQualityBadgeView = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.VerticalTileView$audioQualityBadgeView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.audio_quality_badge);
            }
        });
        this.immersiveContentBadgeView = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.VerticalTileView$immersiveContentBadgeView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.immersive_badge);
            }
        });
        this.extraImmersiveContentBadgeView = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.VerticalTileView$extraImmersiveContentBadgeView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.immersive_album_extra_badge);
            }
        });
        this.artistArtwork = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.music.views.library.views.VerticalTileView$artistArtwork$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ImageView invoke() {
                return (ImageView) this.this$0.findViewById(R$id.artist_artwork);
            }
        });
        this.liveBadgeButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.VerticalTileView$liveBadgeButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.live_badge);
            }
        });
        this.viewerCountButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.VerticalTileView$viewerCountButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.viewer_count);
            }
        });
        this.sourceLogo = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.music.views.library.views.VerticalTileView$sourceLogo$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ImageView invoke() {
                return (ImageView) this.this$0.findViewById(R$id.source_logo);
            }
        });
        this.transparentGradientImageView = LazyKt__LazyJVMKt.lazy(new Function0<TransparentGradientImageView>() { // from class: com.amazon.music.views.library.views.VerticalTileView$transparentGradientImageView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TransparentGradientImageView invoke() {
                return (TransparentGradientImageView) this.this$0.findViewById(R$id.transparent_gradient_image_view);
            }
        });
        this.starRating = LazyKt__LazyJVMKt.lazy(new Function0<StarRatingView>() { // from class: com.amazon.music.views.library.views.VerticalTileView$starRating$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final StarRatingView invoke() {
                return (StarRatingView) this.this$0.findViewById(R$id.star_rating);
            }
        });
        this.allAccessLabel = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.VerticalTileView$allAccessLabel$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.all_access_label);
            }
        });
        this.microSpace = styleSheet.getSpacerInPixels(SpacerKey.MICRO);
        this.hugeSpace = styleSheet.getSpacerInPixels(SpacerKey.HUGE);
        this.mIsContentEnabled = true;
        this.nanoSpace = styleSheet.getSpacerInPixels(SpacerKey.NANO);
        this.smallSpace = styleSheet.getSpacerInPixels(SpacerKey.SMALL);
        View.inflate(context, R$layout.dmmviewlibrary_vertical_tile_layout, this);
        initViewStyling();
        initViewMargins();
        this.badgeCreationHelper = new BadgeCreationHelper(styleSheet, context);
    }

    public final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    public final ArtworkFrameView getArtworkFrameView() {
        return (ArtworkFrameView) this.artworkFrameView.getValue();
    }

    protected final TextView getLabelTextView() {
        Object value = this.labelTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-labelTextView>(...)");
        return (TextView) value;
    }

    protected final TextView getTitleTextView() {
        Object value = this.titleTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-titleTextView>(...)");
        return (TextView) value;
    }

    protected final TextView getSubtitleTextView() {
        Object value = this.subtitleTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-subtitleTextView>(...)");
        return (TextView) value;
    }

    private final TextView getDetailTextView() {
        Object value = this.detailTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-detailTextView>(...)");
        return (TextView) value;
    }

    private final BaseButton getAudioQualityBadgeView() {
        Object value = this.audioQualityBadgeView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-audioQualityBadgeView>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getImmersiveContentBadgeView() {
        Object value = this.immersiveContentBadgeView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-immersiveContentBadgeView>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getExtraImmersiveContentBadgeView() {
        Object value = this.extraImmersiveContentBadgeView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-extraImmersiveContentBadgeView>(...)");
        return (BaseButton) value;
    }

    private final ImageView getArtistArtwork() {
        Object value = this.artistArtwork.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-artistArtwork>(...)");
        return (ImageView) value;
    }

    private final BaseButton getLiveBadgeButton() {
        Object value = this.liveBadgeButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-liveBadgeButton>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getViewerCountButton() {
        Object value = this.viewerCountButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-viewerCountButton>(...)");
        return (BaseButton) value;
    }

    private final ImageView getSourceLogo() {
        Object value = this.sourceLogo.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-sourceLogo>(...)");
        return (ImageView) value;
    }

    private final TransparentGradientImageView getTransparentGradientImageView() {
        Object value = this.transparentGradientImageView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-transparentGradientImageView>(...)");
        return (TransparentGradientImageView) value;
    }

    private final StarRatingView getStarRating() {
        Object value = this.starRating.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-starRating>(...)");
        return (StarRatingView) value;
    }

    private final TextView getAllAccessLabel() {
        Object value = this.allAccessLabel.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-allAccessLabel>(...)");
        return (TextView) value;
    }

    protected boolean getMIsContentEnabled() {
        return this.mIsContentEnabled;
    }

    protected final Integer getNanoSpace() {
        return this.nanoSpace;
    }

    protected final Integer getSmallSpace() {
        return this.smallSpace;
    }

    private final void initViewMargins() {
        setTextMargins(getTitleTextView(), 0, this.smallSpace, 0, this.nanoSpace);
        setTextMargins(getSubtitleTextView(), 0, 0, 0, this.nanoSpace);
        setTextMargins(getDetailTextView(), 0, 0, 0, this.nanoSpace);
        setIconMargins(getAudioQualityBadgeView(), 0, this.microSpace, 0, 0);
        BaseButton immersiveContentBadgeView = getImmersiveContentBadgeView();
        Integer num = this.microSpace;
        setIconMargins(immersiveContentBadgeView, num, num, 0, 0);
        BaseButton extraImmersiveContentBadgeView = getExtraImmersiveContentBadgeView();
        Integer num2 = this.microSpace;
        setIconMargins(extraImmersiveContentBadgeView, num2, num2, 0, 0);
    }

    private final void initViewStyling() {
        FontStyle fontStyle = this.styleSheet.getFontStyle(FontStyleKey.LABEL);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(getLabelTextView(), fontStyle);
        }
        FontStyle fontStyle2 = this.styleSheet.getFontStyle(FontStyleKey.PRIMARY);
        if (fontStyle2 != null) {
            FontUtil.INSTANCE.applyFontStyle(getTitleTextView(), fontStyle2);
        }
        FontStyle fontStyle3 = this.styleSheet.getFontStyle(FontStyleKey.SECONDARY);
        if (fontStyle3 != null) {
            FontUtil.INSTANCE.applyFontStyle(getSubtitleTextView(), fontStyle3);
        }
        FontStyle fontStyle4 = this.styleSheet.getFontStyle(FontStyleKey.TERTIARY);
        if (fontStyle4 != null) {
            FontUtil fontUtil = FontUtil.INSTANCE;
            fontUtil.applyFontStyle(getDetailTextView(), fontStyle4);
            fontUtil.applyFontStyle(getStarRating().getCountView(), fontStyle4);
        }
        StyleSheet styleSheet = this.styleSheet;
        TagSizeKey tagSizeKey = TagSizeKey.SMALL;
        TagStyleKey tagStyleKey = TagStyleKey.GLASS_PRIMARY;
        this.audioQualityBadgeBuilder = styleSheet.getTagBuilder(tagSizeKey, tagStyleKey);
        this.immersiveBadgeBuilder = this.styleSheet.getTagBuilder(tagSizeKey, tagStyleKey);
    }

    public final ImageView getSourceLogoImageView() {
        return getSourceLogo();
    }

    public final void setSourceLogoMargins() {
        Float alpha = this.styleSheet.getAlpha(AlphaKey.GLASS_5);
        if (alpha != null) {
            getSourceLogo().setAlpha(alpha.floatValue());
        }
        Integer num = this.nanoSpace;
        if (num != null) {
            int iIntValue = num.intValue();
            getSourceLogo().setPadding(0, 0, iIntValue, iIntValue);
        }
        LayoutParamUtils layoutParamUtils = LayoutParamUtils.INSTANCE;
        ImageView sourceLogo = getSourceLogo();
        Integer num2 = this.smallSpace;
        layoutParamUtils.setLayoutMargins(sourceLogo, -2, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : 0, (240 & 32) != 0 ? null : 0, (240 & 64) != 0 ? null : num2, (240 & 128) != 0 ? null : num2);
    }

    public void setLabel(String label) {
        getLabelTextView().setText(label);
        if (label == null || label.length() == 0) {
            getLabelTextView().setVisibility(8);
            return;
        }
        setTextMargins(getLabelTextView(), 0, this.smallSpace, 0, this.nanoSpace);
        setTextMargins(getTitleTextView(), 0, 0, 0, this.nanoSpace);
        getLabelTextView().setVisibility(0);
    }

    public void setTitle(String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        getTitleTextView().setText(title);
        getTitleTextView().setVisibility(!TextUtils.isEmpty(title) ? 0 : 8);
    }

    public void setSubtitle(String subtitle) {
        Intrinsics.checkNotNullParameter(subtitle, "subtitle");
        getSubtitleTextView().setText(subtitle);
        getSubtitleTextView().setVisibility(!TextUtils.isEmpty(subtitle) ? 0 : 8);
    }

    public final void setDetailsText(String detailsText) {
        Intrinsics.checkNotNullParameter(detailsText, "detailsText");
        getDetailTextView().setText(detailsText);
        getDetailTextView().setVisibility(!TextUtils.isEmpty(detailsText) ? 0 : 8);
    }

    public final void setAudioQualityText(String audioQualityText) {
        this.badgeCreationHelper.setAudioQualityText(audioQualityText, getAudioQualityBadgeView());
    }

    public final void setImmersiveBadgeText(String immersiveBadgeText) {
        BadgeCreationHelper.setSpatialBadgeText$default(this.badgeCreationHelper, immersiveBadgeText, getImmersiveContentBadgeView(), null, 4, null);
    }

    public final void setExtraAtmosBadgeText(boolean shouldDisplay) {
        this.badgeCreationHelper.setExtraAtmosBadgeText(shouldDisplay, getExtraImmersiveContentBadgeView());
    }

    public final void setContentViewsAsEnabled(boolean isEnabled) {
        if (isEnabled) {
            ArtworkFrameView artworkFrameView = getArtworkFrameView();
            float f = ENABLED_ALPHA_VALUE;
            artworkFrameView.setAlpha(f);
            getTitleTextView().setAlpha(f);
            getSubtitleTextView().setAlpha(f);
            getDetailTextView().setAlpha(f);
            return;
        }
        ArtworkFrameView artworkFrameView2 = getArtworkFrameView();
        float f2 = DISABLED_ALPHA_VALUE;
        artworkFrameView2.setAlpha(f2);
        getTitleTextView().setAlpha(f2);
        getSubtitleTextView().setAlpha(f2);
        getDetailTextView().setAlpha(f2);
    }

    public final void centerTitle(boolean shouldCenter) {
        if (shouldCenter) {
            LayoutParamUtils.INSTANCE.setLayoutMargins(getTitleTextView(), -2, -2, Boolean.TRUE, 0, this.smallSpace, 0, this.nanoSpace);
        }
        centerText(getTitleTextView(), shouldCenter);
    }

    public final void centerSubtitle(boolean shouldCenter) {
        centerText(getSubtitleTextView(), shouldCenter);
    }

    public final void setViewerCount(String viewerCount) {
        Intrinsics.checkNotNullParameter(viewerCount, "viewerCount");
        getViewerCountButton().setText(viewerCount);
        getViewerCountButton().setVisibility(!TextUtils.isEmpty(viewerCount) ? 0 : 8);
    }

    public final void setLiveComponents(boolean isLive, int width) {
        List<Float> positions;
        List<Integer> colors;
        List listReversed;
        Integer num = this.hugeSpace;
        Intrinsics.checkNotNull(num);
        int iIntValue = num.intValue();
        if (isLive) {
            FontStyle fontStyle = this.styleSheet.getFontStyle(FontStyleKey.SECONDARY);
            if (fontStyle != null) {
                FontUtil.INSTANCE.applyFontStyle(getDetailTextView(), fontStyle);
            }
            getDetailTextView().setTypeface(getDetailTextView().getTypeface(), 2);
            StyleSheet styleSheet = this.styleSheet;
            TagSizeKey tagSizeKey = TagSizeKey.MEDIUM;
            BaseButton.StyleBuilder tagBuilder = styleSheet.getTagBuilder(tagSizeKey, TagStyleKey.LIVE);
            if (tagBuilder != null) {
                String string = getContext().getResources().getString(R$string.dmusic_live);
                Intrinsics.checkNotNullExpressionValue(string, "context.resources.getString(R.string.dmusic_live)");
                tagBuilder.withText(string);
                tagBuilder.applyStyle(getLiveBadgeButton());
            }
            BaseButton.StyleBuilder tagBuilder2 = this.styleSheet.getTagBuilder(tagSizeKey, TagStyleKey.LIVE_VIEWER);
            if (tagBuilder2 != null) {
                tagBuilder2.applyStyle(getViewerCountButton());
            }
            getLiveBadgeButton().setVisibility(0);
            getTransparentGradientImageView().setVisibility(0);
            GradientStyle gradientStyle = this.styleSheet.getGradientStyle(GradientStyleKey.TRANSPARENT_GRADIENT);
            int[] intArray = null;
            float[] floatArray = (gradientStyle == null || (positions = gradientStyle.getPositions()) == null) ? null : CollectionsKt___CollectionsKt.toFloatArray(positions);
            if (gradientStyle != null && (colors = gradientStyle.getColors()) != null && (listReversed = CollectionsKt___CollectionsKt.reversed(colors)) != null) {
                intArray = CollectionsKt___CollectionsKt.toIntArray(listReversed);
            }
            int[] iArr = intArray;
            if (floatArray == null || iArr == null) {
                return;
            }
            Bitmap bitmap = Bitmap.createBitmap(width, iIntValue, Bitmap.Config.ARGB_8888);
            Integer color = this.styleSheet.getColor(ColorKey.COLOR_SECONDARY, AlphaKey.GLASS_3);
            Intrinsics.checkNotNull(color);
            bitmap.eraseColor(color.intValue());
            TransparentGradientImageView transparentGradientImageView = getTransparentGradientImageView();
            Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
            TransparentGradientImageView.applyTransparentGradientToBottom$default(transparentGradientImageView, bitmap, floatArray, iArr, width, iIntValue, false, 32, null);
            getTransparentGradientImageView().invalidate();
            return;
        }
        getLiveBadgeButton().setVisibility(8);
        getViewerCountButton().setVisibility(8);
        getTransparentGradientImageView().setVisibility(8);
    }

    public final ImageView getArtistArtworkImageView() {
        return getArtistArtwork();
    }

    public final void setArtistArtworkMargins(int size) {
        LayoutParamUtils layoutParamUtils = LayoutParamUtils.INSTANCE;
        ImageView artistArtwork = getArtistArtwork();
        Integer num = this.smallSpace;
        layoutParamUtils.setLayoutMargins(artistArtwork, size, size, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : 0, (240 & 32) != 0 ? null : num, (240 & 64) != 0 ? null : num, (240 & 128) != 0 ? null : 0);
    }

    public static /* synthetic */ void setStarRating$default(VerticalTileView verticalTileView, Float f, Integer num, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setStarRating");
        }
        if ((i & 2) != 0) {
            num = null;
        }
        verticalTileView.setStarRating(f, num);
    }

    public final void setStarRating(Float rating, Integer ratingCount) {
        if (rating != null || ratingCount != null) {
            getStarRating().setRating(rating);
            getStarRating().setRatingCount(ratingCount);
            getStarRating().setVisibility(0);
            return;
        }
        getStarRating().setVisibility(8);
    }

    public final void showAllAccessLabel(String labelText) {
        getAllAccessLabel().setVisibility(labelText != null ? 0 : 8);
        if (labelText == null) {
            return;
        }
        getAllAccessLabel().setText(labelText);
        FontStyle fontStyle = this.styleSheet.getFontStyle(FontStyleKey.LABEL);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(getAllAccessLabel(), fontStyle);
        }
        getAllAccessLabel().setBackgroundResource(R$drawable.label_badge_background);
    }

    @Override // android.view.View
    public void setEnabled(boolean isEnabled) {
        if (isEnabled) {
            setAlpha(ENABLED_ALPHA_VALUE);
        } else {
            setAlpha(DISABLED_ALPHA_VALUE);
            getArtworkFrameView().setPlayState(null);
        }
    }

    @Override // android.view.View
    public boolean isEnabled() {
        return getMIsContentEnabled();
    }

    protected final void setTextMargins(TextView textView, Integer left, Integer top, Integer right, Integer bottom) {
        Intrinsics.checkNotNullParameter(textView, "textView");
        LayoutParamUtils.INSTANCE.setLayoutMargins(textView, 0, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
    }

    private final void setIconMargins(BaseButton baseButton, Integer left, Integer top, Integer right, Integer bottom) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(baseButton, -2, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
    }

    private final void centerText(TextView view, boolean shouldCenter) {
        view.setGravity(shouldCenter ? 1 : 8388611);
    }
}
