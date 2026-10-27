package com.amazon.music.views.library.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.models.base.HotspotSupportedView;
import com.amazon.music.views.library.models.enums.RankMovementType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.utils.DurationStringUtil;
import com.amazon.music.views.library.views.HotSpotToolTipDialog;
import com.amazon.music.views.library.views.IconPresenter;
import com.amazon.ui.foundations.styles.FontStyle;
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

/* JADX INFO: compiled from: HorizontalTileView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 È\u00012\u00020\u00012\u00020\u00022\u00020\u0003:\u0002È\u0001B\u001b\u0012\b\u0010Å\u0001\u001a\u00030Ä\u0001\u0012\u0006\u0010W\u001a\u00020V¢\u0006\u0006\bÆ\u0001\u0010Ç\u0001J\b\u0010\u0005\u001a\u00020\u0004H\u0002J\b\u0010\u0006\u001a\u00020\u0004H\u0002JG\u0010\u000e\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJG\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00102\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0004\b\u0012\u0010\u0013JG\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00142\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\tH\u0002J\b\u0010\u001a\u001a\u00020\u0004H\u0002J\b\u0010\u001b\u001a\u00020\u0004H\u0002J\u000e\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\tJ\u000e\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\tJ\u000e\u0010\u001f\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\tJ\u0010\u0010\"\u001a\u00020\u00042\b\u0010!\u001a\u0004\u0018\u00010 J\u0014\u0010%\u001a\u00020\u00042\f\u0010$\u001a\b\u0012\u0004\u0012\u00020 0#J\u000e\u0010'\u001a\u00020\u00042\u0006\u0010&\u001a\u00020 J\u000e\u0010(\u001a\u00020\u00042\u0006\u0010&\u001a\u00020 J\u000e\u0010*\u001a\u00020\u00042\u0006\u0010)\u001a\u00020 J\u000e\u0010,\u001a\u00020\u00042\u0006\u0010+\u001a\u00020 J\u000e\u0010.\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\tJ\u000e\u00100\u001a\u00020\u00042\u0006\u0010/\u001a\u00020 J\u001e\u00105\u001a\u00020\u00042\n\b\u0002\u00102\u001a\u0004\u0018\u0001012\n\b\u0002\u00104\u001a\u0004\u0018\u000103J\u0006\u00106\u001a\u00020\u0004J\b\u00107\u001a\u0004\u0018\u000101J\u0010\u00109\u001a\u00020\u00042\b\u00108\u001a\u0004\u0018\u00010 J\u0014\u0010;\u001a\u00020\u00042\f\u0010:\u001a\b\u0012\u0004\u0012\u00020 0#J\u0010\u0010=\u001a\u00020\u00042\b\u0010<\u001a\u0004\u0018\u00010 J\u000e\u0010@\u001a\u00020\u00042\u0006\u0010?\u001a\u00020>J\u000e\u0010B\u001a\u00020\u00042\u0006\u0010A\u001a\u00020\tJ\u000e\u0010D\u001a\u00020\u00042\u0006\u0010C\u001a\u00020>J\u0010\u0010E\u001a\u00020\u00042\u0006\u0010C\u001a\u00020>H\u0016J\u001e\u0010H\u001a\u00020\u00042\n\b\u0002\u0010F\u001a\u0004\u0018\u0001012\n\b\u0002\u0010G\u001a\u0004\u0018\u000103J\u000e\u0010J\u001a\u00020\u00042\u0006\u0010I\u001a\u000203J\u000e\u0010L\u001a\u00020\u00042\u0006\u0010K\u001a\u00020>J\u000e\u0010M\u001a\u00020\u00042\u0006\u0010K\u001a\u00020>J\u000e\u0010N\u001a\u00020\u00042\u0006\u0010C\u001a\u00020>J\u0010\u0010O\u001a\u00020\u00042\u0006\u0010K\u001a\u00020>H\u0016J7\u0010P\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\t2\b\u0010\f\u001a\u0004\u0018\u00010\t2\b\u0010\r\u001a\u0004\u0018\u00010\tH\u0016¢\u0006\u0004\bP\u0010QJ\u000e\u0010S\u001a\u00020\u00042\u0006\u0010R\u001a\u00020>J\b\u0010U\u001a\u00020TH\u0016R\u001a\u0010W\u001a\u00020V8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bW\u0010X\u001a\u0004\bY\u0010ZR\u001b\u0010`\u001a\u00020[8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\\\u0010]\u001a\u0004\b^\u0010_R\u001b\u0010d\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\ba\u0010]\u001a\u0004\bb\u0010cR\u001b\u0010g\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\be\u0010]\u001a\u0004\bf\u0010cR\u001b\u0010j\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bh\u0010]\u001a\u0004\bi\u0010cR\u001b\u0010m\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bk\u0010]\u001a\u0004\bl\u0010cR\u001b\u0010p\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bn\u0010]\u001a\u0004\bo\u0010cR\u001b\u0010s\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bq\u0010]\u001a\u0004\br\u0010cR\u001b\u0010w\u001a\u00020\u00148BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bt\u0010]\u001a\u0004\bu\u0010vR\u001b\u0010z\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bx\u0010]\u001a\u0004\by\u0010cR\u001b\u0010\u007f\u001a\u00020{8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b|\u0010]\u001a\u0004\b}\u0010~R\u001d\u0010F\u001a\u00020\u00148BX\u0082\u0084\u0002¢\u0006\u000e\n\u0005\b\u0080\u0001\u0010]\u001a\u0005\b\u0081\u0001\u0010vR\u001e\u0010\u0084\u0001\u001a\u00020\u00148BX\u0082\u0084\u0002¢\u0006\u000e\n\u0005\b\u0082\u0001\u0010]\u001a\u0005\b\u0083\u0001\u0010vR\u001e\u0010\u0087\u0001\u001a\u00020\u00148BX\u0082\u0084\u0002¢\u0006\u000e\n\u0005\b\u0085\u0001\u0010]\u001a\u0005\b\u0086\u0001\u0010vR\u001e\u0010\u008a\u0001\u001a\u00020\u00148BX\u0082\u0084\u0002¢\u0006\u000e\n\u0005\b\u0088\u0001\u0010]\u001a\u0005\b\u0089\u0001\u0010vR\u001e\u0010\u008d\u0001\u001a\u00020\u00148BX\u0082\u0084\u0002¢\u0006\u000e\n\u0005\b\u008b\u0001\u0010]\u001a\u0005\b\u008c\u0001\u0010vR\u001f\u0010\u0091\u0001\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b\u008e\u0001\u0010]\u001a\u0006\b\u008f\u0001\u0010\u0090\u0001R\u001f\u0010\u0094\u0001\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b\u0092\u0001\u0010]\u001a\u0006\b\u0093\u0001\u0010\u0090\u0001R\u001f\u0010\u0097\u0001\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b\u0095\u0001\u0010]\u001a\u0006\b\u0096\u0001\u0010\u0090\u0001R\u001e\u0010\u009a\u0001\u001a\u00020{8BX\u0082\u0084\u0002¢\u0006\u000e\n\u0005\b\u0098\u0001\u0010]\u001a\u0005\b\u0099\u0001\u0010~R\u001e\u0010\u009d\u0001\u001a\u00020{8BX\u0082\u0084\u0002¢\u0006\u000e\n\u0005\b\u009b\u0001\u0010]\u001a\u0005\b\u009c\u0001\u0010~R\u001c\u0010\u009f\u0001\u001a\u0005\u0018\u00010\u009e\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u009f\u0001\u0010 \u0001R\u001c\u0010¡\u0001\u001a\u0005\u0018\u00010\u009e\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¡\u0001\u0010 \u0001R\u001c\u0010¢\u0001\u001a\u0005\u0018\u00010\u009e\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¢\u0001\u0010 \u0001R\u001c\u0010£\u0001\u001a\u0005\u0018\u00010\u009e\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b£\u0001\u0010 \u0001R\u001c\u0010¤\u0001\u001a\u0005\u0018\u00010\u009e\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¤\u0001\u0010 \u0001R\u001c\u0010¥\u0001\u001a\u0005\u0018\u00010\u009e\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¥\u0001\u0010 \u0001R\u001a\u0010¦\u0001\u001a\u0005\u0018\u00010\u009e\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¦\u0001\u0010 \u0001R\u001d\u00102\u001a\u00020\u00148BX\u0082\u0084\u0002¢\u0006\u000e\n\u0005\b§\u0001\u0010]\u001a\u0005\b¨\u0001\u0010vR\u0018\u0010ª\u0001\u001a\u00030©\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bª\u0001\u0010«\u0001R\u0018\u0010¬\u0001\u001a\u00030©\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¬\u0001\u0010«\u0001R\u0019\u0010\u00ad\u0001\u001a\u0004\u0018\u00010\t8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u00ad\u0001\u0010®\u0001R\u0019\u0010¯\u0001\u001a\u0004\u0018\u00010\t8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¯\u0001\u0010®\u0001R\u0019\u0010°\u0001\u001a\u0004\u0018\u00010\t8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b°\u0001\u0010®\u0001R\u0019\u0010±\u0001\u001a\u0004\u0018\u00010\t8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b±\u0001\u0010®\u0001R\u0019\u0010²\u0001\u001a\u0004\u0018\u00010\t8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b²\u0001\u0010®\u0001R\u0019\u0010³\u0001\u001a\u0004\u0018\u00010\t8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b³\u0001\u0010®\u0001R\u0018\u0010µ\u0001\u001a\u00030´\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bµ\u0001\u0010¶\u0001R \u0010»\u0001\u001a\u00030·\u00018VX\u0096\u0084\u0002¢\u0006\u000f\n\u0005\b¸\u0001\u0010]\u001a\u0006\b¹\u0001\u0010º\u0001R\u0016\u0010½\u0001\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b¼\u0001\u0010vR\u0016\u0010¿\u0001\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b¾\u0001\u0010vR\u0018\u0010Ã\u0001\u001a\u00030À\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\bÁ\u0001\u0010Â\u0001¨\u0006É\u0001"}, d2 = {"Lcom/amazon/music/views/library/views/HorizontalTileView;", "Lcom/amazon/music/views/library/views/LibraryAddableView;", "Lcom/amazon/music/views/library/views/AddButtonPresenter;", "Lcom/amazon/music/views/library/models/base/HotspotSupportedView;", "", "initViewMargins", "initViewStyling", "Landroid/widget/LinearLayout;", "container", "", "left", "top", "right", "bottom", "setLinearLayoutContainer", "(Landroid/widget/LinearLayout;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "Landroid/widget/TextView;", "textView", "setTextMargins", "(Landroid/widget/TextView;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "Lcom/amazon/ui/foundations/views/BaseButton;", "baseButton", "setIconMargins", "(Lcom/amazon/ui/foundations/views/BaseButton;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "number", "updateNumberFontStyle", "topAlignTextContainer", "centerAlignTextContainer", "width", "updateViewWidth", "setNumberText", "setNumber", "", "rankMovement", "setRankMovement", "", "labels", "setLabels", "title", "setLabel", "setTitle", MediaTrack.ROLE_SUBTITLE, "setSubtitle", "detailsText", "setDetailsText", "brandingResource", "setBrandingResource", "durationText", "setDurationText", "Lcom/amazon/music/views/library/views/IconPresenter$IconType;", "leftIcon", "Landroid/view/View$OnClickListener;", "leftIconClickListener", "setLeftIcon", "clearLeftIcon", "getLeftIconType", "audioQualityText", "setAudioQualityText", "languageOfPerformances", "setLanguageOfPerformanceText", "immersiveBadgeText", "setImmersiveBadgeText", "", "shouldDisplay", "setExtraAtmosBadgeText", "upsellBadgeResource", "setUpsellBadgeResource", "show", "showOverflowMenu", "showHotspot", "rightIcon", "rightIconClickListener", "setRightIcon", "onClickListener", "setOverflowOnClickListener", Constants.ENABLE_DISABLE, "setContentViewsAsEnabled", "setControlViewsAsEnabled", "showNavigationIcon", "setEnabled", "setHotspotMargins", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "hasBadging", "setTextContainerConstraints", "Lcom/amazon/music/views/library/views/HotSpotToolTipDialog;", "createTooltipView", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "Lcom/amazon/music/views/library/views/ArtworkFrameView;", "artworkFrameLayout$delegate", "Lkotlin/Lazy;", "getArtworkFrameLayout", "()Lcom/amazon/music/views/library/views/ArtworkFrameView;", "artworkFrameLayout", "numberTextView$delegate", "getNumberTextView", "()Landroid/widget/TextView;", "numberTextView", "labelsTextView$delegate", "getLabelsTextView", "labelsTextView", "labelTextView$delegate", "getLabelTextView", "labelTextView", "titleTextView$delegate", "getTitleTextView", "titleTextView", "subtitleTextView$delegate", "getSubtitleTextView", "subtitleTextView", "detailTextView$delegate", "getDetailTextView", "detailTextView", "brandingIcon$delegate", "getBrandingIcon", "()Lcom/amazon/ui/foundations/views/BaseButton;", "brandingIcon", "durationTextView$delegate", "getDurationTextView", "durationTextView", "Landroid/widget/ImageView;", "upsellBadge$delegate", "getUpsellBadge", "()Landroid/widget/ImageView;", "upsellBadge", "rightIcon$delegate", "getRightIcon", "navigationIcon$delegate", "getNavigationIcon", "navigationIcon", "audioQualityBadgeView$delegate", "getAudioQualityBadgeView", "audioQualityBadgeView", "immersiveContentBadgeView$delegate", "getImmersiveContentBadgeView", "immersiveContentBadgeView", "extraImmersiveContentBadgeView$delegate", "getExtraImmersiveContentBadgeView", "extraImmersiveContentBadgeView", "lopBadgeContainer$delegate", "getLopBadgeContainer", "()Landroid/widget/LinearLayout;", "lopBadgeContainer", "textContainer$delegate", "getTextContainer", "textContainer", "badgeContainer$delegate", "getBadgeContainer", "badgeContainer", "rankMovementUp$delegate", "getRankMovementUp", "rankMovementUp", "rankMovementDown$delegate", "getRankMovementDown", "rankMovementDown", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "overflowIconBuilder", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "navigationIconBuilder", "explicitTagBuilder", "downloadIndicatorBuilder", "audioQualityBadgeBuilder", "immersiveBadgeBuilder", "iconBuilder", "leftIcon$delegate", "getLeftIcon", "Lcom/amazon/music/views/library/views/IconPresenter;", "leftBaseButtonPresenter", "Lcom/amazon/music/views/library/views/IconPresenter;", "rightBaseButtonPresenter", "nanoSpace", "Ljava/lang/Integer;", "microSpace", "miniSpace", "largeSpace", "smallSpace", "baseSpace", "Lcom/amazon/music/views/library/views/BadgeCreationHelper;", "badgeCreationHelper", "Lcom/amazon/music/views/library/views/BadgeCreationHelper;", "Lcom/amazon/music/views/library/views/HotSpotPulse;", "hotSpotPulseView$delegate", "getHotSpotPulseView", "()Lcom/amazon/music/views/library/views/HotSpotPulse;", "hotSpotPulseView", "getRowButtonView", "rowButtonView", "getRowButtonClickedImageView", "rowButtonClickedImageView", "Landroid/view/View;", "getRowButtonContainerView", "()Landroid/view/View;", "rowButtonContainerView", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "Companion", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class HorizontalTileView extends LibraryAddableView implements HotspotSupportedView {

    /* JADX INFO: renamed from: artworkFrameLayout$delegate, reason: from kotlin metadata */
    private final Lazy artworkFrameLayout;
    private BaseButton.StyleBuilder audioQualityBadgeBuilder;

    /* JADX INFO: renamed from: audioQualityBadgeView$delegate, reason: from kotlin metadata */
    private final Lazy audioQualityBadgeView;

    /* JADX INFO: renamed from: badgeContainer$delegate, reason: from kotlin metadata */
    private final Lazy badgeContainer;
    private final BadgeCreationHelper badgeCreationHelper;
    private final Integer baseSpace;

    /* JADX INFO: renamed from: brandingIcon$delegate, reason: from kotlin metadata */
    private final Lazy brandingIcon;

    /* JADX INFO: renamed from: detailTextView$delegate, reason: from kotlin metadata */
    private final Lazy detailTextView;
    private BaseButton.StyleBuilder downloadIndicatorBuilder;

    /* JADX INFO: renamed from: durationTextView$delegate, reason: from kotlin metadata */
    private final Lazy durationTextView;
    private BaseButton.StyleBuilder explicitTagBuilder;

    /* JADX INFO: renamed from: extraImmersiveContentBadgeView$delegate, reason: from kotlin metadata */
    private final Lazy extraImmersiveContentBadgeView;

    /* JADX INFO: renamed from: hotSpotPulseView$delegate, reason: from kotlin metadata */
    private final Lazy hotSpotPulseView;
    private final BaseButton.StyleBuilder iconBuilder;
    private BaseButton.StyleBuilder immersiveBadgeBuilder;

    /* JADX INFO: renamed from: immersiveContentBadgeView$delegate, reason: from kotlin metadata */
    private final Lazy immersiveContentBadgeView;

    /* JADX INFO: renamed from: labelTextView$delegate, reason: from kotlin metadata */
    private final Lazy labelTextView;

    /* JADX INFO: renamed from: labelsTextView$delegate, reason: from kotlin metadata */
    private final Lazy labelsTextView;
    private final Integer largeSpace;
    private final IconPresenter leftBaseButtonPresenter;

    /* JADX INFO: renamed from: leftIcon$delegate, reason: from kotlin metadata */
    private final Lazy leftIcon;

    /* JADX INFO: renamed from: lopBadgeContainer$delegate, reason: from kotlin metadata */
    private final Lazy lopBadgeContainer;
    private final Integer microSpace;
    private final Integer miniSpace;
    private final Integer nanoSpace;

    /* JADX INFO: renamed from: navigationIcon$delegate, reason: from kotlin metadata */
    private final Lazy navigationIcon;
    private BaseButton.StyleBuilder navigationIconBuilder;

    /* JADX INFO: renamed from: numberTextView$delegate, reason: from kotlin metadata */
    private final Lazy numberTextView;
    private BaseButton.StyleBuilder overflowIconBuilder;

    /* JADX INFO: renamed from: rankMovementDown$delegate, reason: from kotlin metadata */
    private final Lazy rankMovementDown;

    /* JADX INFO: renamed from: rankMovementUp$delegate, reason: from kotlin metadata */
    private final Lazy rankMovementUp;
    private final IconPresenter rightBaseButtonPresenter;

    /* JADX INFO: renamed from: rightIcon$delegate, reason: from kotlin metadata */
    private final Lazy rightIcon;
    private final Integer smallSpace;
    private final StyleSheet styleSheet;

    /* JADX INFO: renamed from: subtitleTextView$delegate, reason: from kotlin metadata */
    private final Lazy subtitleTextView;

    /* JADX INFO: renamed from: textContainer$delegate, reason: from kotlin metadata */
    private final Lazy textContainer;

    /* JADX INFO: renamed from: titleTextView$delegate, reason: from kotlin metadata */
    private final Lazy titleTextView;

    /* JADX INFO: renamed from: upsellBadge$delegate, reason: from kotlin metadata */
    private final Lazy upsellBadge;
    private static final float ENABLED_ALPHA_VALUE = 1.0f;
    private static final float DISABLED_ALPHA_VALUE = 0.3f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HorizontalTileView(Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.artworkFrameLayout = LazyKt__LazyJVMKt.lazy(new Function0<ArtworkFrameView>() { // from class: com.amazon.music.views.library.views.HorizontalTileView$artworkFrameLayout$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ArtworkFrameView invoke() {
                return (ArtworkFrameView) this.this$0.findViewById(R$id.artwork_frame_view);
            }
        });
        this.numberTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.HorizontalTileView$numberTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.number_text);
            }
        });
        this.labelsTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.HorizontalTileView$labelsTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.labels);
            }
        });
        this.labelTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.HorizontalTileView$labelTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.label);
            }
        });
        this.titleTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.HorizontalTileView$titleTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.title);
            }
        });
        this.subtitleTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.HorizontalTileView$subtitleTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.subtitle);
            }
        });
        this.detailTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.HorizontalTileView$detailTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.recommendation_text);
            }
        });
        this.brandingIcon = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.HorizontalTileView$brandingIcon$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.branding_icon);
            }
        });
        this.durationTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.HorizontalTileView$durationTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.duration_text);
            }
        });
        this.upsellBadge = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.music.views.library.views.HorizontalTileView$upsellBadge$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ImageView invoke() {
                return (ImageView) this.this$0.findViewById(R$id.upsell_badge);
            }
        });
        this.rightIcon = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.HorizontalTileView$rightIcon$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.right_icon);
            }
        });
        this.navigationIcon = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.HorizontalTileView$navigationIcon$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.navigation_icon);
            }
        });
        this.audioQualityBadgeView = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.HorizontalTileView$audioQualityBadgeView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.audio_quality_badge);
            }
        });
        this.immersiveContentBadgeView = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.HorizontalTileView$immersiveContentBadgeView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.immersive_badge);
            }
        });
        this.extraImmersiveContentBadgeView = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.HorizontalTileView$extraImmersiveContentBadgeView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.immersive_album_extra_badge);
            }
        });
        this.lopBadgeContainer = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.music.views.library.views.HorizontalTileView$lopBadgeContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R$id.lop_badge_container);
            }
        });
        this.textContainer = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.music.views.library.views.HorizontalTileView$textContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R$id.text_container);
            }
        });
        this.badgeContainer = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.music.views.library.views.HorizontalTileView$badgeContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R$id.badge_container);
            }
        });
        this.rankMovementUp = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.music.views.library.views.HorizontalTileView$rankMovementUp$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ImageView invoke() {
                return (ImageView) this.this$0.findViewById(R$id.rankMovementUp);
            }
        });
        this.rankMovementDown = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.music.views.library.views.HorizontalTileView$rankMovementDown$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ImageView invoke() {
                return (ImageView) this.this$0.findViewById(R$id.rankMovementDown);
            }
        });
        BaseButton.StyleBuilder iconBuilder = getStyleSheet().getIconBuilder(IconSizeKey.SMALL, IconStyleKey.PRIMARY);
        this.iconBuilder = iconBuilder;
        this.leftIcon = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.HorizontalTileView$leftIcon$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.left_icon);
            }
        });
        this.nanoSpace = getStyleSheet().getSpacerInPixels(SpacerKey.NANO);
        this.microSpace = getStyleSheet().getSpacerInPixels(SpacerKey.MICRO);
        this.miniSpace = getStyleSheet().getSpacerInPixels(SpacerKey.MINI);
        this.largeSpace = getStyleSheet().getSpacerInPixels(SpacerKey.LARGE);
        this.smallSpace = getStyleSheet().getSpacerInPixels(SpacerKey.SMALL);
        this.baseSpace = getStyleSheet().getSpacerInPixels(SpacerKey.BASE);
        this.hotSpotPulseView = LazyKt__LazyJVMKt.lazy(new Function0<HotSpotPulse>() { // from class: com.amazon.music.views.library.views.HorizontalTileView$hotSpotPulseView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final HotSpotPulse invoke() {
                return (HotSpotPulse) this.this$0.findViewById(R$id.hotspot_pulse);
            }
        });
        View.inflate(context, R$layout.dmmviewlibrary_horizontal_tile_layout, this);
        setId(R$id.horizontal_tile_view);
        initViewStyling();
        initViewMargins();
        this.badgeCreationHelper = new BadgeCreationHelper(getStyleSheet(), context);
        this.leftBaseButtonPresenter = new IconPresenter(getLeftIcon(), iconBuilder == null ? null : iconBuilder.copy(), context);
        this.rightBaseButtonPresenter = new IconPresenter(getRightIcon(), iconBuilder != null ? iconBuilder.copy() : null, context);
    }

    @Override // com.amazon.music.views.library.models.base.HotspotSupportedView
    public HotSpotPulse getHotspotView() {
        return HotspotSupportedView.DefaultImpls.getHotspotView(this);
    }

    public StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    public final ArtworkFrameView getArtworkFrameLayout() {
        Object value = this.artworkFrameLayout.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-artworkFrameLayout>(...)");
        return (ArtworkFrameView) value;
    }

    private final TextView getNumberTextView() {
        Object value = this.numberTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-numberTextView>(...)");
        return (TextView) value;
    }

    private final TextView getLabelsTextView() {
        Object value = this.labelsTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-labelsTextView>(...)");
        return (TextView) value;
    }

    private final TextView getLabelTextView() {
        Object value = this.labelTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-labelTextView>(...)");
        return (TextView) value;
    }

    private final TextView getTitleTextView() {
        Object value = this.titleTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-titleTextView>(...)");
        return (TextView) value;
    }

    private final TextView getSubtitleTextView() {
        Object value = this.subtitleTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-subtitleTextView>(...)");
        return (TextView) value;
    }

    private final TextView getDetailTextView() {
        Object value = this.detailTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-detailTextView>(...)");
        return (TextView) value;
    }

    private final BaseButton getBrandingIcon() {
        Object value = this.brandingIcon.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-brandingIcon>(...)");
        return (BaseButton) value;
    }

    private final TextView getDurationTextView() {
        Object value = this.durationTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-durationTextView>(...)");
        return (TextView) value;
    }

    private final ImageView getUpsellBadge() {
        Object value = this.upsellBadge.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-upsellBadge>(...)");
        return (ImageView) value;
    }

    private final BaseButton getRightIcon() {
        Object value = this.rightIcon.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-rightIcon>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getNavigationIcon() {
        Object value = this.navigationIcon.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-navigationIcon>(...)");
        return (BaseButton) value;
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

    private final LinearLayout getLopBadgeContainer() {
        Object value = this.lopBadgeContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-lopBadgeContainer>(...)");
        return (LinearLayout) value;
    }

    private final LinearLayout getTextContainer() {
        Object value = this.textContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-textContainer>(...)");
        return (LinearLayout) value;
    }

    private final LinearLayout getBadgeContainer() {
        Object value = this.badgeContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-badgeContainer>(...)");
        return (LinearLayout) value;
    }

    private final ImageView getRankMovementUp() {
        Object value = this.rankMovementUp.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-rankMovementUp>(...)");
        return (ImageView) value;
    }

    private final ImageView getRankMovementDown() {
        Object value = this.rankMovementDown.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-rankMovementDown>(...)");
        return (ImageView) value;
    }

    private final BaseButton getLeftIcon() {
        Object value = this.leftIcon.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-leftIcon>(...)");
        return (BaseButton) value;
    }

    @Override // com.amazon.music.views.library.models.base.HotspotSupportedView
    public HotSpotPulse getHotSpotPulseView() {
        Object value = this.hotSpotPulseView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-hotSpotPulseView>(...)");
        return (HotSpotPulse) value;
    }

    @Override // com.amazon.music.views.library.views.LibraryAddableView, com.amazon.music.views.library.views.AddButtonPresenter
    public BaseButton getRowButtonView() {
        View viewFindViewById = findViewById(R$id.add_icon);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById<BaseButton>(R.id.add_icon)");
        return (BaseButton) viewFindViewById;
    }

    @Override // com.amazon.music.views.library.views.LibraryAddableView, com.amazon.music.views.library.views.AddButtonPresenter
    public BaseButton getRowButtonClickedImageView() {
        View viewFindViewById = findViewById(R$id.add_complete_icon);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById<BaseButton>(R.id.add_complete_icon)");
        return (BaseButton) viewFindViewById;
    }

    @Override // com.amazon.music.views.library.views.LibraryAddableView, com.amazon.music.views.library.views.AddButtonPresenter
    public View getRowButtonContainerView() {
        View viewFindViewById = findViewById(R$id.add_container);
        if (viewFindViewById != null) {
            return (FrameLayout) viewFindViewById;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.widget.FrameLayout");
    }

    private final void initViewMargins() {
        updateViewWidth(-1);
        LayoutParamUtils layoutParamUtils = LayoutParamUtils.INSTANCE;
        layoutParamUtils.setLayoutMargins(getTextContainer(), 0, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : this.baseSpace, (240 & 32) != 0 ? null : 0, (240 & 64) != 0 ? null : this.smallSpace, (240 & 128) != 0 ? null : 0);
        setLinearLayoutContainer(getBadgeContainer(), 0, this.microSpace, 0, 0);
        Integer num = this.largeSpace;
        layoutParamUtils.setLayoutMargins(getNumberTextView(), num == null ? -2 : num.intValue(), -2, (240 & 8) != 0 ? Boolean.FALSE : Boolean.FALSE, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
        ViewGroup.LayoutParams layoutParams = getArtworkFrameLayout().getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams = layoutParams instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) layoutParams : null;
        if (marginLayoutParams != null) {
            Integer num2 = this.miniSpace;
            if (num2 == null) {
                num2 = 0;
            }
            marginLayoutParams.leftMargin = num2.intValue();
        }
        setTextMargins(getLabelTextView(), 0, 0, 0, this.nanoSpace);
        setTextMargins(getLabelsTextView(), 0, 0, 0, 0);
        setTextMargins(getDetailTextView(), 0, 0, 0, this.nanoSpace);
        setIconMargins(getAudioQualityBadgeView(), 0, 0, this.microSpace, 0);
        setIconMargins(getImmersiveContentBadgeView(), 0, 0, this.microSpace, 0);
        setIconMargins(getExtraImmersiveContentBadgeView(), 0, 0, this.microSpace, 0);
    }

    public final void updateViewWidth(int width) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(this, width, -2, Boolean.TRUE, 0, 0, 0, 0);
        Integer num = this.smallSpace;
        if (num != null) {
            int iIntValue = num.intValue();
            setPadding(0, iIntValue, 0, iIntValue);
        }
        setForeground(AppCompatResources.getDrawable(getContext(), R$drawable.ripple));
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x0144  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void initViewStyling() {
        /*
            Method dump skipped, instruction units count: 540
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.views.library.views.HorizontalTileView.initViewStyling():void");
    }

    public final void setNumberText(int number) {
        getNumberTextView().setText(String.valueOf(number));
        updateNumberFontStyle(number);
        getNumberTextView().setVisibility(number > 0 ? 0 : 8);
    }

    public final void setNumber(int number) {
        getNumberTextView().setText(String.valueOf(number));
        updateNumberFontStyle(number);
        getNumberTextView().setVisibility(number > 0 ? 0 : 8);
    }

    public final void setRankMovement(String rankMovement) {
        if (Intrinsics.areEqual(rankMovement, RankMovementType.UP.toString())) {
            getRankMovementUp().setVisibility(0);
            getRankMovementDown().setVisibility(4);
        } else if (Intrinsics.areEqual(rankMovement, RankMovementType.DOWN.toString())) {
            getRankMovementUp().setVisibility(4);
            getRankMovementDown().setVisibility(0);
        } else {
            getRankMovementUp().setVisibility(4);
            getRankMovementDown().setVisibility(4);
        }
    }

    public final void setLabels(List<String> labels) {
        Intrinsics.checkNotNullParameter(labels, "labels");
        String strJoinToString$default = CollectionsKt___CollectionsKt.joinToString$default(labels, " • ", null, null, 0, null, null, 62, null);
        getLabelsTextView().setText(strJoinToString$default);
        getLabelsTextView().setVisibility(!TextUtils.isEmpty(strJoinToString$default) ? 0 : 8);
    }

    public final void setLabel(String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        getLabelTextView().setText(title);
        getLabelTextView().setVisibility(!TextUtils.isEmpty(title) ? 0 : 8);
    }

    public final void setTitle(String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        getTitleTextView().setText(title);
        getTitleTextView().setVisibility(!TextUtils.isEmpty(title) ? 0 : 8);
    }

    public final void setSubtitle(String subtitle) {
        Intrinsics.checkNotNullParameter(subtitle, "subtitle");
        getSubtitleTextView().setText(subtitle);
        getSubtitleTextView().setVisibility(!TextUtils.isEmpty(subtitle) ? 0 : 8);
    }

    public final void setDetailsText(String detailsText) {
        Intrinsics.checkNotNullParameter(detailsText, "detailsText");
        getDetailTextView().setText(detailsText);
        getDetailTextView().setVisibility(!TextUtils.isEmpty(detailsText) ? 0 : 8);
    }

    public final void setBrandingResource(int brandingResource) {
        getBrandingIcon().setVisibility(0);
    }

    public final void setDurationText(String durationText) {
        Intrinsics.checkNotNullParameter(durationText, "durationText");
        String formattedDuration = DurationStringUtil.INSTANCE.getFormattedDuration(durationText);
        getDurationTextView().setText(formattedDuration);
        getDurationTextView().setVisibility(!TextUtils.isEmpty(formattedDuration) ? 0 : 8);
    }

    public final void setLeftIcon(IconPresenter.IconType leftIcon, View.OnClickListener leftIconClickListener) {
        if (leftIcon != null) {
            if (leftIcon == IconPresenter.IconType.PLAY_ICON) {
                BaseButton.StyleBuilder playIconBuilder = getPlayIconBuilder();
                if (playIconBuilder != null) {
                    this.leftBaseButtonPresenter.setIcon(leftIcon, playIconBuilder);
                }
            } else {
                BaseButton.StyleBuilder styleBuilder = this.iconBuilder;
                if (styleBuilder != null) {
                    this.leftBaseButtonPresenter.setIcon(leftIcon, styleBuilder);
                }
            }
        }
        if (leftIconClickListener == null) {
            return;
        }
        this.leftBaseButtonPresenter.setOnClickListener(leftIconClickListener);
    }

    public final void clearLeftIcon() {
        this.leftBaseButtonPresenter.reset();
    }

    public final IconPresenter.IconType getLeftIconType() {
        return this.leftBaseButtonPresenter.getCurrentIconType();
    }

    public final void setAudioQualityText(String audioQualityText) {
        this.badgeCreationHelper.setAudioQualityText(audioQualityText, getAudioQualityBadgeView());
    }

    public final void setLanguageOfPerformanceText(List<String> languageOfPerformances) {
        Intrinsics.checkNotNullParameter(languageOfPerformances, "languageOfPerformances");
        this.badgeCreationHelper.setLanguageOfPerformanceText(languageOfPerformances, getLopBadgeContainer());
    }

    public final void setImmersiveBadgeText(String immersiveBadgeText) {
        BadgeCreationHelper.setSpatialBadgeText$default(this.badgeCreationHelper, immersiveBadgeText, getImmersiveContentBadgeView(), null, 4, null);
    }

    public final void setExtraAtmosBadgeText(boolean shouldDisplay) {
        this.badgeCreationHelper.setExtraAtmosBadgeText(shouldDisplay, getExtraImmersiveContentBadgeView());
    }

    public final void setUpsellBadgeResource(int upsellBadgeResource) {
        Drawable drawable = AppCompatResources.getDrawable(getContext(), upsellBadgeResource);
        if (drawable == null) {
            return;
        }
        getUpsellBadge().setImageDrawable(drawable);
        getUpsellBadge().setVisibility(0);
        getRowButtonContainerView().setVisibility(8);
        getRowButtonView().setVisibility(8);
        getRightIcon().setVisibility(8);
    }

    public final void showOverflowMenu(boolean show) {
        IconPresenter.IconType iconType = IconPresenter.IconType.OVERFLOW_ICON;
        BaseButton.StyleBuilder styleBuilder = this.overflowIconBuilder;
        if (styleBuilder != null) {
            this.rightBaseButtonPresenter.setIcon(iconType, styleBuilder);
        }
        getRightIcon().setVisibility(show ? 0 : 8);
    }

    public void showHotspot(boolean show) {
        Integer spacerInPixels;
        getHotSpotPulseView().setVisibility(show ? 0 : 8);
        if (show && (spacerInPixels = getStyleSheet().getSpacerInPixels(SpacerKey.HUGE)) != null) {
            int iIntValue = spacerInPixels.intValue();
            HotspotSupportedView.DefaultImpls.setHotspotMargins$default(this, Integer.valueOf(iIntValue), null, Integer.valueOf(iIntValue * 2), null, 10, null);
        }
    }

    public final void setRightIcon(IconPresenter.IconType rightIcon, View.OnClickListener rightIconClickListener) {
        BaseButton.StyleBuilder playIconBuilderTiny;
        if (rightIcon != null && (playIconBuilderTiny = getPlayIconBuilderTiny()) != null) {
            this.rightBaseButtonPresenter.setIcon(rightIcon, playIconBuilderTiny);
        }
        if (rightIconClickListener != null) {
            this.rightBaseButtonPresenter.setOnClickListener(rightIconClickListener);
        }
        getRightIcon().setVisibility(0);
    }

    public final void setOverflowOnClickListener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        getRightIcon().setOnClickListener(onClickListener);
    }

    public final void setContentViewsAsEnabled(boolean isEnabled) {
        if (isEnabled) {
            getTextContainer().setAlpha(ENABLED_ALPHA_VALUE);
        } else {
            getTextContainer().setAlpha(DISABLED_ALPHA_VALUE);
        }
    }

    public final void setControlViewsAsEnabled(boolean isEnabled) {
        if (isEnabled) {
            getRowButtonContainerView().setAlpha(ENABLED_ALPHA_VALUE);
        } else {
            getRowButtonContainerView().setAlpha(DISABLED_ALPHA_VALUE);
        }
    }

    public final void showNavigationIcon(boolean show) {
        BaseButton.StyleBuilder styleBuilder = this.navigationIconBuilder;
        if (styleBuilder != null) {
            styleBuilder.applyStyle(getNavigationIcon());
        }
        getNavigationIcon().setVisibility(show ? 0 : 8);
        getNavigationIcon().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.views.HorizontalTileView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HorizontalTileView.m2417showNavigationIcon$lambda28(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showNavigationIcon$lambda-28, reason: not valid java name */
    public static final void m2417showNavigationIcon$lambda28(HorizontalTileView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.performClick();
    }

    @Override // android.view.View
    public void setEnabled(boolean isEnabled) {
        setAlpha(isEnabled ? ENABLED_ALPHA_VALUE : DISABLED_ALPHA_VALUE);
    }

    private final void setLinearLayoutContainer(LinearLayout container, Integer left, Integer top, Integer right, Integer bottom) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(container, -2, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
    }

    private final void setTextMargins(TextView textView, Integer left, Integer top, Integer right, Integer bottom) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(textView, -2, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
    }

    private final void setIconMargins(BaseButton baseButton, Integer left, Integer top, Integer right, Integer bottom) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(baseButton, -2, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
    }

    @Override // com.amazon.music.views.library.models.base.HotspotSupportedView
    public void setHotspotMargins(Integer left, Integer top, Integer right, Integer bottom) {
        LayoutParamUtils layoutParamUtils = LayoutParamUtils.INSTANCE;
        HotSpotPulse hotSpotPulseView = getHotSpotPulseView();
        StyleSheet styleSheet = getStyleSheet();
        SpacerKey spacerKey = SpacerKey.HUGE;
        Integer spacerInPixels = styleSheet.getSpacerInPixels(spacerKey);
        int iIntValue = spacerInPixels == null ? 0 : spacerInPixels.intValue();
        Integer spacerInPixels2 = getStyleSheet().getSpacerInPixels(spacerKey);
        layoutParamUtils.setLayoutMargins(hotSpotPulseView, iIntValue, spacerInPixels2 == null ? 0 : spacerInPixels2.intValue(), (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
    }

    private final void updateNumberFontStyle(int number) {
        Integer color = getStyleSheet().getColor(ColorKey.COLOR_PRIMARY);
        int iIntValue = color == null ? -1 : color.intValue();
        FontStyle fontStyle = getStyleSheet().getFontStyle(number < 100 ? FontStyleKey.PRIMARY : FontStyleKey.SECONDARY);
        if (fontStyle == null) {
            return;
        }
        FontUtil.INSTANCE.applyFontStyle(getNumberTextView(), FontStyle.copy$default(fontStyle, null, null, iIntValue, null, 11, null));
    }

    public final void setTextContainerConstraints(boolean hasBadging) {
        if (hasBadging) {
            topAlignTextContainer();
        } else {
            centerAlignTextContainer();
        }
    }

    private final void topAlignTextContainer() {
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R$id.layout_root);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.clear(R$id.text_container, 4);
        constraintSet.applyTo(constraintLayout);
    }

    private final void centerAlignTextContainer() {
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R$id.layout_root);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.connect(R$id.text_container, 4, 0, 4, 0);
        constraintSet.applyTo(constraintLayout);
    }

    @Override // com.amazon.music.views.library.models.base.HotspotSupportedView
    public HotSpotToolTipDialog createTooltipView() {
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        return new HotSpotToolTipDialog(context, getStyleSheet(), HotSpotToolTipDialog.Companion.Position.TOP);
    }
}
