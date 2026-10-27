package com.amazon.music.views.library.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.R$string;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.GradientStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.music.views.library.styles.keys.LandscapeType;
import com.amazon.music.views.library.styles.keys.ScreenSizeKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.ButtonSize;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.styles.GradientStyle;
import com.amazon.ui.foundations.utils.BitmapEffectUtils;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FeaturePlayView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b(\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0002¿\u0001BE\u0012\b\u0010¼\u0001\u001a\u00030»\u0001\u0012\u0006\u0010G\u001a\u00020F\u0012\n\b\u0002\u0010L\u001a\u0004\u0018\u00010K\u0012\b\b\u0002\u0010P\u001a\u00020\b\u0012\b\b\u0002\u0010T\u001a\u00020\b\u0012\b\b\u0002\u0010V\u001a\u00020\u0002¢\u0006\u0006\b½\u0001\u0010¾\u0001J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0004\u001a\u00020\u0002H\u0002J\b\u0010\u0006\u001a\u00020\u0005H\u0002J\b\u0010\u0007\u001a\u00020\u0005H\u0002J\u0011\u0010\t\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0004\b\t\u0010\nJ\u0011\u0010\u000b\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0004\b\u000b\u0010\nJ\b\u0010\r\u001a\u00020\fH\u0002J\b\u0010\u000e\u001a\u00020\bH\u0002J\b\u0010\u000f\u001a\u00020\bH\u0002J\u0018\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\bH\u0002J\u0018\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\bH\u0002J\b\u0010\u0014\u001a\u00020\u0005H\u0002J\u0018\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\bH\u0002J\u0018\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\bH\u0002J\u0011\u0010\u0017\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0004\b\u0017\u0010\nJ\u0011\u0010\u0018\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0004\b\u0018\u0010\nJ\u0011\u0010\u0019\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0004\b\u0019\u0010\nJ\u001a\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u001a2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001cH\u0002JG\u0010%\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u001f2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0004\b%\u0010&J\u000e\u0010)\u001a\u00020\u00052\u0006\u0010(\u001a\u00020'J\u0010\u0010*\u001a\u00020\u00052\b\u0010\u001d\u001a\u0004\u0018\u00010\u001cJ\u0010\u0010+\u001a\u00020\u00052\b\u0010\u001d\u001a\u0004\u0018\u00010\u001cJ\u0010\u0010,\u001a\u00020\u00052\b\u0010\u001d\u001a\u0004\u0018\u00010\u001cJ\u0010\u0010-\u001a\u00020\u00052\b\u0010\u001d\u001a\u0004\u0018\u00010\u001cJ\u0010\u0010.\u001a\u00020\u00052\b\u0010\u001d\u001a\u0004\u0018\u00010\u001cJ\u0006\u0010/\u001a\u00020\u0005J\u0006\u00100\u001a\u00020\u001fJ\u0019\u00102\u001a\u00020\u00052\n\b\u0002\u00101\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b2\u00103J\u0006\u00104\u001a\u00020\u0005J\u0016\u00105\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\bJ\u000e\u00107\u001a\u00020\u00052\u0006\u00106\u001a\u00020\bJ\u000e\u0010:\u001a\u00020\u00052\u0006\u00109\u001a\u000208J\u000e\u0010;\u001a\u00020\u00052\u0006\u00109\u001a\u000208J\u0017\u0010=\u001a\u00020\u00052\b\u0010<\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b=\u00103J\u0017\u0010>\u001a\u00020\u00052\b\u0010<\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b>\u00103JY\u0010%\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u001f2\b\b\u0002\u0010\u0010\u001a\u00020\b2\b\b\u0002\u0010\u0011\u001a\u00020\b2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b%\u0010?J\u0006\u0010@\u001a\u00020\u0002J\u0006\u0010A\u001a\u00020\fJ\u0006\u0010B\u001a\u00020\u0002J\u0006\u0010C\u001a\u00020\u0002J\u0006\u0010D\u001a\u00020\u0002J\u0006\u0010E\u001a\u00020\u0002R\u0017\u0010G\u001a\u00020F8\u0006¢\u0006\f\n\u0004\bG\u0010H\u001a\u0004\bI\u0010JR\u0019\u0010L\u001a\u0004\u0018\u00010K8\u0006¢\u0006\f\n\u0004\bL\u0010M\u001a\u0004\bN\u0010OR\u0017\u0010P\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\bP\u0010Q\u001a\u0004\bR\u0010SR\u0017\u0010T\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\bT\u0010Q\u001a\u0004\bU\u0010SR\u0014\u0010V\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bV\u0010WR\u001d\u0010]\u001a\u0004\u0018\u00010X8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bY\u0010Z\u001a\u0004\b[\u0010\\R\u001b\u0010`\u001a\u00020X8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b^\u0010Z\u001a\u0004\b_\u0010\\R\u001b\u0010c\u001a\u00020X8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\ba\u0010Z\u001a\u0004\bb\u0010\\R\u001d\u0010f\u001a\u0004\u0018\u00010X8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bd\u0010Z\u001a\u0004\be\u0010\\R\u001d\u0010i\u001a\u0004\u0018\u00010X8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bg\u0010Z\u001a\u0004\bh\u0010\\R\u001d\u0010m\u001a\u0004\u0018\u00010\u00018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bj\u0010Z\u001a\u0004\bk\u0010lR\u001d\u0010p\u001a\u0004\u0018\u00010\u00018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bn\u0010Z\u001a\u0004\bo\u0010lR\u001b\u0010t\u001a\u00020\u001a8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bq\u0010Z\u001a\u0004\br\u0010sR\u001b\u0010w\u001a\u00020\u001a8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bu\u0010Z\u001a\u0004\bv\u0010sR\u001b\u0010z\u001a\u00020\u001a8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bx\u0010Z\u001a\u0004\by\u0010sR\u001b\u0010}\u001a\u00020\u001a8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b{\u0010Z\u001a\u0004\b|\u0010sR\u001c\u0010\u0080\u0001\u001a\u00020\u001a8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b~\u0010Z\u001a\u0004\b\u007f\u0010sR \u0010\u0085\u0001\u001a\u00030\u0081\u00018BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b\u0082\u0001\u0010Z\u001a\u0006\b\u0083\u0001\u0010\u0084\u0001R \u0010\u008a\u0001\u001a\u00030\u0086\u00018BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b\u0087\u0001\u0010Z\u001a\u0006\b\u0088\u0001\u0010\u0089\u0001R \u0010\u008d\u0001\u001a\u00030\u0081\u00018BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b\u008b\u0001\u0010Z\u001a\u0006\b\u008c\u0001\u0010\u0084\u0001R\u001e\u0010\u0090\u0001\u001a\u00020\u001a8BX\u0082\u0084\u0002¢\u0006\u000e\n\u0005\b\u008e\u0001\u0010Z\u001a\u0005\b\u008f\u0001\u0010sR \u0010\u0095\u0001\u001a\u00030\u0091\u00018BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b\u0092\u0001\u0010Z\u001a\u0006\b\u0093\u0001\u0010\u0094\u0001R \u0010\u009a\u0001\u001a\u00030\u0096\u00018BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b\u0097\u0001\u0010Z\u001a\u0006\b\u0098\u0001\u0010\u0099\u0001R\u001e\u0010\u009d\u0001\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\u000e\n\u0005\b\u009b\u0001\u0010Z\u001a\u0005\b\u009c\u0001\u0010SR\u001e\u0010 \u0001\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\u000e\n\u0005\b\u009e\u0001\u0010Z\u001a\u0005\b\u009f\u0001\u0010SR)\u0010¡\u0001\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0016\n\u0006\b¡\u0001\u0010¢\u0001\u001a\u0005\b£\u0001\u0010\n\"\u0005\b¤\u0001\u00103R\u001a\u0010¦\u0001\u001a\u00030¥\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¦\u0001\u0010§\u0001R\u001a\u0010¨\u0001\u001a\u00030¥\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¨\u0001\u0010§\u0001R\u001a\u0010©\u0001\u001a\u00030¥\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b©\u0001\u0010§\u0001R\u0016\u0010ª\u0001\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\bª\u0001\u0010QR\u0019\u0010«\u0001\u001a\u0004\u0018\u00010\b8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b«\u0001\u0010¢\u0001R\u0019\u0010¬\u0001\u001a\u0004\u0018\u00010\b8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¬\u0001\u0010¢\u0001R\u0019\u0010\u00ad\u0001\u001a\u0004\u0018\u00010\b8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u00ad\u0001\u0010¢\u0001R\u0019\u0010®\u0001\u001a\u0004\u0018\u00010\b8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b®\u0001\u0010¢\u0001R\u0019\u0010¯\u0001\u001a\u0004\u0018\u00010\b8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¯\u0001\u0010¢\u0001R\u0019\u0010°\u0001\u001a\u0004\u0018\u00010\b8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b°\u0001\u0010¢\u0001R\u0019\u0010±\u0001\u001a\u0004\u0018\u00010\b8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b±\u0001\u0010¢\u0001R\u0019\u0010²\u0001\u001a\u0004\u0018\u00010\b8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b²\u0001\u0010¢\u0001R\u0019\u0010³\u0001\u001a\u0004\u0018\u00010\b8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b³\u0001\u0010¢\u0001R\u0018\u0010µ\u0001\u001a\u00030´\u00018\u0002X\u0082D¢\u0006\b\n\u0006\bµ\u0001\u0010¶\u0001R\u0018\u0010·\u0001\u001a\u00030´\u00018\u0002X\u0082D¢\u0006\b\n\u0006\b·\u0001\u0010¶\u0001R\u0018\u0010¸\u0001\u001a\u00030´\u00018\u0002X\u0082D¢\u0006\b\n\u0006\b¸\u0001\u0010¶\u0001R\u0018\u0010¹\u0001\u001a\u00030´\u00018\u0002X\u0082D¢\u0006\b\n\u0006\b¹\u0001\u0010¶\u0001R\u0017\u0010(\u001a\u00020'8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b(\u0010º\u0001¨\u0006À\u0001"}, d2 = {"Lcom/amazon/music/views/library/views/FeaturePlayView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "", "isMusicLeftAligned", "isMusicFacetedNavigation", "", "initViewStyling", "initViewMargins", "", "getTopMarginForCenteredView", "()Ljava/lang/Integer;", "getTopMarginForLeftAlignedView", "Lcom/amazon/music/views/library/styles/keys/ScreenSizeKey;", "getScreenSizeKeyForLeftAlignedView", "getTextElementContainerWidth", "getScreenWidth", "width", "height", "setArtistImageContainerSize", "setArtistImageContainerBackground", "setTvImageView", "setVideoBackgroundImageView", "setHeroImageForVideos", "screenSizeAwareTopMarginForPlayButton", "getLeftMarginForLeftAlignedView", "screenSizeAwareBottomMarginForPlayButtonForNewUx", "Landroid/widget/TextView;", "textView", "", "text", "setOrHideText", "Landroid/view/View;", "view", "left", "top", "right", "bottom", "setMargins", "(Landroid/view/View;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "Lcom/amazon/music/views/library/views/FeaturePlayView$ViewType;", "viewType", "initView", "setLabelText", "setTitle", "setPrimaryText", "setSecondaryText", "setTertiaryText", "setPlayButton", "getPlayButtonView", "equalizerBarColor", "setEqualizer", "(Ljava/lang/Integer;)V", "setBackgroundViewsForVideos", "setArtistImageContainer", "color", "setPlayNotificationIconStyle", "Landroid/view/View$OnClickListener;", "onClickListener", "setPlayMusicClickListener", "setBackgroundPlayMusicClickListener", "playState", "setPlayState", "setPodcastPlayState", "(Landroid/view/View;IILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "isWideScreen", "screenWidthSize", "isLandscape", "isTabletLandscape", "isScreenWidthLargePlus", "isScreenLargeOrExtraLarge", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "Landroid/util/AttributeSet;", "attrs", "Landroid/util/AttributeSet;", "getAttrs", "()Landroid/util/AttributeSet;", "defStyleAttr", "I", "getDefStyleAttr", "()I", "defStyleRes", "getDefStyleRes", "isSonicRushEnabled", "Z", "Lcom/amazon/music/views/library/views/TransparentGradientImageView;", "backgroundImageView$delegate", "Lkotlin/Lazy;", "getBackgroundImageView", "()Lcom/amazon/music/views/library/views/TransparentGradientImageView;", "backgroundImageView", "heroImageView$delegate", "getHeroImageView", "heroImageView", "heroImageBackgroundView$delegate", "getHeroImageBackgroundView", "heroImageBackgroundView", "tvBackgroundImageView$delegate", "getTvBackgroundImageView", "tvBackgroundImageView", "tvImageView$delegate", "getTvImageView", "tvImageView", "backgroundImages$delegate", "getBackgroundImages", "()Landroidx/constraintlayout/widget/ConstraintLayout;", "backgroundImages", "artistImageContainer$delegate", "getArtistImageContainer", "artistImageContainer", "mTitleTextView$delegate", "getMTitleTextView", "()Landroid/widget/TextView;", "mTitleTextView", "mPrimaryTextView$delegate", "getMPrimaryTextView", "mPrimaryTextView", "mSecondaryTextView$delegate", "getMSecondaryTextView", "mSecondaryTextView", "mTertiaryTextView$delegate", "getMTertiaryTextView", "mTertiaryTextView", "mLabelTextView$delegate", "getMLabelTextView", "mLabelTextView", "Landroid/widget/LinearLayout;", "textElements$delegate", "getTextElements", "()Landroid/widget/LinearLayout;", "textElements", "Lcom/amazon/music/views/library/views/PlayNotificationIcon;", "mPlayNotificationIcon$delegate", "getMPlayNotificationIcon", "()Lcom/amazon/music/views/library/views/PlayNotificationIcon;", "mPlayNotificationIcon", "equalizerContainerButton$delegate", "getEqualizerContainerButton", "equalizerContainerButton", "equalizerContainerTextView$delegate", "getEqualizerContainerTextView", "equalizerContainerTextView", "Lcom/amazon/ui/foundations/views/BaseButton;", "playButton$delegate", "getPlayButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "playButton", "Landroid/graphics/drawable/Drawable;", "playerButtonIcon$delegate", "getPlayerButtonIcon", "()Landroid/graphics/drawable/Drawable;", "playerButtonIcon", "tvIntrinsicWidth$delegate", "getTvIntrinsicWidth", "tvIntrinsicWidth", "tvIntrinsicHeight$delegate", "getTvIntrinsicHeight", "tvIntrinsicHeight", "mPlayState", "Ljava/lang/Integer;", "getMPlayState", "setMPlayState", "", "buttonActionText", "Ljava/lang/String;", "buttonPlayingText", "buttonPausedText", "gutter", "microSpace", "miniSpace", "smallSpace", "baseSpace", "mediumSpace", "largeSpace", "hugeSpace", "giantSpace", "epicSpace", "", "VIDEO_IMAGE_TOP_MARGIN", "D", "VIDEO_IMAGE_LEFT_MARGIN", "VIDEO_IMAGE_WIDTH_RATIO", "VIDEO_IMAGE_HEIGHT_RATIO", "Lcom/amazon/music/views/library/views/FeaturePlayView$ViewType;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;Landroid/util/AttributeSet;IIZ)V", "ViewType", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class FeaturePlayView extends ConstraintLayout {
    private final double VIDEO_IMAGE_HEIGHT_RATIO;
    private final double VIDEO_IMAGE_LEFT_MARGIN;
    private final double VIDEO_IMAGE_TOP_MARGIN;
    private final double VIDEO_IMAGE_WIDTH_RATIO;

    /* JADX INFO: renamed from: artistImageContainer$delegate, reason: from kotlin metadata */
    private final Lazy artistImageContainer;
    private final AttributeSet attrs;

    /* JADX INFO: renamed from: backgroundImageView$delegate, reason: from kotlin metadata */
    private final Lazy backgroundImageView;

    /* JADX INFO: renamed from: backgroundImages$delegate, reason: from kotlin metadata */
    private final Lazy backgroundImages;
    private final Integer baseSpace;
    private String buttonActionText;
    private String buttonPausedText;
    private String buttonPlayingText;
    private final int defStyleAttr;
    private final int defStyleRes;
    private final Integer epicSpace;

    /* JADX INFO: renamed from: equalizerContainerButton$delegate, reason: from kotlin metadata */
    private final Lazy equalizerContainerButton;

    /* JADX INFO: renamed from: equalizerContainerTextView$delegate, reason: from kotlin metadata */
    private final Lazy equalizerContainerTextView;
    private final Integer giantSpace;
    private final int gutter;

    /* JADX INFO: renamed from: heroImageBackgroundView$delegate, reason: from kotlin metadata */
    private final Lazy heroImageBackgroundView;

    /* JADX INFO: renamed from: heroImageView$delegate, reason: from kotlin metadata */
    private final Lazy heroImageView;
    private final Integer hugeSpace;
    private final boolean isSonicRushEnabled;
    private final Integer largeSpace;

    /* JADX INFO: renamed from: mLabelTextView$delegate, reason: from kotlin metadata */
    private final Lazy mLabelTextView;

    /* JADX INFO: renamed from: mPlayNotificationIcon$delegate, reason: from kotlin metadata */
    private final Lazy mPlayNotificationIcon;
    private Integer mPlayState;

    /* JADX INFO: renamed from: mPrimaryTextView$delegate, reason: from kotlin metadata */
    private final Lazy mPrimaryTextView;

    /* JADX INFO: renamed from: mSecondaryTextView$delegate, reason: from kotlin metadata */
    private final Lazy mSecondaryTextView;

    /* JADX INFO: renamed from: mTertiaryTextView$delegate, reason: from kotlin metadata */
    private final Lazy mTertiaryTextView;

    /* JADX INFO: renamed from: mTitleTextView$delegate, reason: from kotlin metadata */
    private final Lazy mTitleTextView;
    private final Integer mediumSpace;
    private final Integer microSpace;
    private final Integer miniSpace;

    /* JADX INFO: renamed from: playButton$delegate, reason: from kotlin metadata */
    private final Lazy playButton;

    /* JADX INFO: renamed from: playerButtonIcon$delegate, reason: from kotlin metadata */
    private final Lazy playerButtonIcon;
    private final Integer smallSpace;
    private final StyleSheet styleSheet;

    /* JADX INFO: renamed from: textElements$delegate, reason: from kotlin metadata */
    private final Lazy textElements;

    /* JADX INFO: renamed from: tvBackgroundImageView$delegate, reason: from kotlin metadata */
    private final Lazy tvBackgroundImageView;

    /* JADX INFO: renamed from: tvImageView$delegate, reason: from kotlin metadata */
    private final Lazy tvImageView;

    /* JADX INFO: renamed from: tvIntrinsicHeight$delegate, reason: from kotlin metadata */
    private final Lazy tvIntrinsicHeight;

    /* JADX INFO: renamed from: tvIntrinsicWidth$delegate, reason: from kotlin metadata */
    private final Lazy tvIntrinsicWidth;
    private ViewType viewType;

    /* JADX INFO: compiled from: FeaturePlayView.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/views/library/views/FeaturePlayView$ViewType;", "", "(Ljava/lang/String;I)V", "MUSIC_LEFT_ALIGNED", "MUSIC_FACETED_NAVIGATION", "VIDEO_LEFT_ALIGNED", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum ViewType {
        MUSIC_LEFT_ALIGNED,
        MUSIC_FACETED_NAVIGATION,
        VIDEO_LEFT_ALIGNED
    }

    public /* synthetic */ FeaturePlayView(Context context, StyleSheet styleSheet, AttributeSet attributeSet, int i, int i2, boolean z, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, styleSheet, (i3 & 4) != 0 ? null : attributeSet, (i3 & 8) != 0 ? 0 : i, (i3 & 16) != 0 ? 0 : i2, (i3 & 32) != 0 ? false : z);
    }

    public final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    public final AttributeSet getAttrs() {
        return this.attrs;
    }

    public final int getDefStyleAttr() {
        return this.defStyleAttr;
    }

    public final int getDefStyleRes() {
        return this.defStyleRes;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FeaturePlayView(Context context, StyleSheet styleSheet, AttributeSet attributeSet, int i, int i2, boolean z) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.attrs = attributeSet;
        this.defStyleAttr = i;
        this.defStyleRes = i2;
        this.isSonicRushEnabled = z;
        this.backgroundImageView = LazyKt__LazyJVMKt.lazy(new Function0<TransparentGradientImageView>() { // from class: com.amazon.music.views.library.views.FeaturePlayView$backgroundImageView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TransparentGradientImageView invoke() {
                return (TransparentGradientImageView) this.this$0.findViewById(R$id.background_image);
            }
        });
        this.heroImageView = LazyKt__LazyJVMKt.lazy(new Function0<TransparentGradientImageView>() { // from class: com.amazon.music.views.library.views.FeaturePlayView$heroImageView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TransparentGradientImageView invoke() {
                return (TransparentGradientImageView) this.this$0.findViewById(R$id.hero_image);
            }
        });
        this.heroImageBackgroundView = LazyKt__LazyJVMKt.lazy(new Function0<TransparentGradientImageView>() { // from class: com.amazon.music.views.library.views.FeaturePlayView$heroImageBackgroundView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TransparentGradientImageView invoke() {
                return (TransparentGradientImageView) this.this$0.findViewById(R$id.hero_image_background);
            }
        });
        this.tvBackgroundImageView = LazyKt__LazyJVMKt.lazy(new Function0<TransparentGradientImageView>() { // from class: com.amazon.music.views.library.views.FeaturePlayView$tvBackgroundImageView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TransparentGradientImageView invoke() {
                return (TransparentGradientImageView) this.this$0.findViewById(R$id.tv_background_image);
            }
        });
        this.tvImageView = LazyKt__LazyJVMKt.lazy(new Function0<TransparentGradientImageView>() { // from class: com.amazon.music.views.library.views.FeaturePlayView$tvImageView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TransparentGradientImageView invoke() {
                return (TransparentGradientImageView) this.this$0.findViewById(R$id.tv_image);
            }
        });
        this.backgroundImages = LazyKt__LazyJVMKt.lazy(new Function0<ConstraintLayout>() { // from class: com.amazon.music.views.library.views.FeaturePlayView$backgroundImages$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ConstraintLayout invoke() {
                return (ConstraintLayout) this.this$0.findViewById(R$id.background_images);
            }
        });
        this.artistImageContainer = LazyKt__LazyJVMKt.lazy(new Function0<ConstraintLayout>() { // from class: com.amazon.music.views.library.views.FeaturePlayView$artistImageContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ConstraintLayout invoke() {
                return (ConstraintLayout) this.this$0.findViewById(R$id.artistImageContainer);
            }
        });
        this.mTitleTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.FeaturePlayView$mTitleTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.title);
            }
        });
        this.mPrimaryTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.FeaturePlayView$mPrimaryTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.primary_text);
            }
        });
        this.mSecondaryTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.FeaturePlayView$mSecondaryTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.secondary_text);
            }
        });
        this.mTertiaryTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.FeaturePlayView$mTertiaryTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.tertiary_text);
            }
        });
        this.mLabelTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.FeaturePlayView$mLabelTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.label);
            }
        });
        this.textElements = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.music.views.library.views.FeaturePlayView$textElements$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R$id.text_elements);
            }
        });
        this.mPlayNotificationIcon = LazyKt__LazyJVMKt.lazy(new Function0<PlayNotificationIcon>() { // from class: com.amazon.music.views.library.views.FeaturePlayView$mPlayNotificationIcon$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PlayNotificationIcon invoke() {
                return (PlayNotificationIcon) this.this$0.findViewById(R$id.PlaystateIcon);
            }
        });
        this.equalizerContainerButton = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.music.views.library.views.FeaturePlayView$equalizerContainerButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R$id.play_equalizer);
            }
        });
        this.equalizerContainerTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.FeaturePlayView$equalizerContainerTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.internal_storage_state);
            }
        });
        this.playButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.FeaturePlayView$playButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.PlayIcon);
            }
        });
        this.playerButtonIcon = LazyKt__LazyJVMKt.lazy(new Function0<Drawable>() { // from class: com.amazon.music.views.library.views.FeaturePlayView$playerButtonIcon$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Drawable invoke() {
                return this.this$0.getResources().getDrawable(R$drawable.player_btn_play);
            }
        });
        this.tvIntrinsicWidth = LazyKt__LazyJVMKt.lazy(new Function0<Integer>() { // from class: com.amazon.music.views.library.views.FeaturePlayView$tvIntrinsicWidth$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.getResources().getDrawable(R$drawable.t1r_tv).getIntrinsicWidth());
            }
        });
        this.tvIntrinsicHeight = LazyKt__LazyJVMKt.lazy(new Function0<Integer>() { // from class: com.amazon.music.views.library.views.FeaturePlayView$tvIntrinsicHeight$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(this.this$0.getResources().getDrawable(R$drawable.t1r_tv).getIntrinsicHeight());
            }
        });
        String string = getResources().getString(R$string.content_description_play_button);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…_description_play_button)");
        this.buttonActionText = string;
        String string2 = getResources().getString(R$string.content_description_playing);
        Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.st…tent_description_playing)");
        this.buttonPlayingText = string2;
        String string3 = getResources().getString(R$string.content_description_paused);
        Intrinsics.checkNotNullExpressionValue(string3, "resources.getString(R.st…ntent_description_paused)");
        this.buttonPausedText = string3;
        this.gutter = getResources().getDimensionPixelSize(R$dimen.gutter);
        this.microSpace = styleSheet.getSpacerInPixels(SpacerKey.MICRO);
        this.miniSpace = styleSheet.getSpacerInPixels(SpacerKey.MINI);
        this.smallSpace = styleSheet.getSpacerInPixels(SpacerKey.SMALL);
        this.baseSpace = styleSheet.getSpacerInPixels(SpacerKey.BASE);
        this.mediumSpace = styleSheet.getSpacerInPixels(SpacerKey.MEDIUM);
        this.largeSpace = styleSheet.getSpacerInPixels(SpacerKey.LARGE);
        this.hugeSpace = styleSheet.getSpacerInPixels(SpacerKey.HUGE);
        this.giantSpace = styleSheet.getSpacerInPixels(SpacerKey.GIANT);
        this.epicSpace = styleSheet.getSpacerInPixels(SpacerKey.EPIC);
        this.VIDEO_IMAGE_TOP_MARGIN = 0.301d;
        this.VIDEO_IMAGE_LEFT_MARGIN = 0.334d;
        this.VIDEO_IMAGE_WIDTH_RATIO = 0.226d;
        this.VIDEO_IMAGE_HEIGHT_RATIO = 0.344d;
        this.viewType = ViewType.VIDEO_LEFT_ALIGNED;
    }

    public final TransparentGradientImageView getBackgroundImageView() {
        return (TransparentGradientImageView) this.backgroundImageView.getValue();
    }

    public final TransparentGradientImageView getHeroImageView() {
        Object value = this.heroImageView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-heroImageView>(...)");
        return (TransparentGradientImageView) value;
    }

    public final TransparentGradientImageView getHeroImageBackgroundView() {
        Object value = this.heroImageBackgroundView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-heroImageBackgroundView>(...)");
        return (TransparentGradientImageView) value;
    }

    private final TransparentGradientImageView getTvBackgroundImageView() {
        return (TransparentGradientImageView) this.tvBackgroundImageView.getValue();
    }

    private final TransparentGradientImageView getTvImageView() {
        return (TransparentGradientImageView) this.tvImageView.getValue();
    }

    private final ConstraintLayout getBackgroundImages() {
        return (ConstraintLayout) this.backgroundImages.getValue();
    }

    private final ConstraintLayout getArtistImageContainer() {
        return (ConstraintLayout) this.artistImageContainer.getValue();
    }

    private final TextView getMTitleTextView() {
        Object value = this.mTitleTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-mTitleTextView>(...)");
        return (TextView) value;
    }

    private final TextView getMPrimaryTextView() {
        Object value = this.mPrimaryTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-mPrimaryTextView>(...)");
        return (TextView) value;
    }

    private final TextView getMSecondaryTextView() {
        Object value = this.mSecondaryTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-mSecondaryTextView>(...)");
        return (TextView) value;
    }

    private final TextView getMTertiaryTextView() {
        Object value = this.mTertiaryTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-mTertiaryTextView>(...)");
        return (TextView) value;
    }

    private final TextView getMLabelTextView() {
        Object value = this.mLabelTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-mLabelTextView>(...)");
        return (TextView) value;
    }

    private final LinearLayout getTextElements() {
        Object value = this.textElements.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-textElements>(...)");
        return (LinearLayout) value;
    }

    private final PlayNotificationIcon getMPlayNotificationIcon() {
        Object value = this.mPlayNotificationIcon.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-mPlayNotificationIcon>(...)");
        return (PlayNotificationIcon) value;
    }

    private final LinearLayout getEqualizerContainerButton() {
        Object value = this.equalizerContainerButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-equalizerContainerButton>(...)");
        return (LinearLayout) value;
    }

    private final TextView getEqualizerContainerTextView() {
        Object value = this.equalizerContainerTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-equalizerContainerTextView>(...)");
        return (TextView) value;
    }

    private final BaseButton getPlayButton() {
        Object value = this.playButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-playButton>(...)");
        return (BaseButton) value;
    }

    private final Drawable getPlayerButtonIcon() {
        Object value = this.playerButtonIcon.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-playerButtonIcon>(...)");
        return (Drawable) value;
    }

    private final int getTvIntrinsicWidth() {
        return ((Number) this.tvIntrinsicWidth.getValue()).intValue();
    }

    private final int getTvIntrinsicHeight() {
        return ((Number) this.tvIntrinsicHeight.getValue()).intValue();
    }

    public final Integer getMPlayState() {
        return this.mPlayState;
    }

    public final void setMPlayState(Integer num) {
        this.mPlayState = num;
    }

    public final void initView(ViewType viewType) {
        Intrinsics.checkNotNullParameter(viewType, "viewType");
        this.viewType = viewType;
        View.inflate(getContext(), R$layout.dmmviewlibrary_feature_play, this);
        getEqualizerContainerButton().setVisibility(8);
        initViewStyling();
        initViewMargins();
    }

    private final boolean isMusicLeftAligned() {
        return this.viewType == ViewType.MUSIC_LEFT_ALIGNED;
    }

    private final boolean isMusicFacetedNavigation() {
        return this.viewType == ViewType.MUSIC_FACETED_NAVIGATION;
    }

    private final void initViewStyling() {
        FontStyle fontStyle = this.styleSheet.getFontStyle(FontStyleKey.LABEL);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(getMLabelTextView(), fontStyle);
        }
        if (isMusicFacetedNavigation()) {
            FontStyle fontStyle2 = this.styleSheet.getFontStyle(FontStyleKey.HEADLINE_3);
            if (fontStyle2 != null) {
                FontUtil.INSTANCE.applyFontStyle(getMTitleTextView(), fontStyle2);
            }
        } else {
            FontStyle fontStyle3 = this.styleSheet.getFontStyle(FontStyleKey.HEADLINE_2);
            if (fontStyle3 != null) {
                FontUtil.INSTANCE.applyFontStyle(getMTitleTextView(), fontStyle3);
            }
        }
        FontStyle fontStyle4 = this.styleSheet.getFontStyle(FontStyleKey.PRIMARY);
        if (fontStyle4 != null) {
            FontUtil.INSTANCE.applyFontStyle(getMPrimaryTextView(), fontStyle4);
        }
        FontStyle fontStyle5 = this.styleSheet.getFontStyle(FontStyleKey.SECONDARY);
        if (fontStyle5 != null) {
            FontUtil.INSTANCE.applyFontStyle(getMSecondaryTextView(), fontStyle5);
        }
        FontStyle fontStyle6 = this.styleSheet.getFontStyle(FontStyleKey.TERTIARY);
        if (fontStyle6 == null) {
            return;
        }
        FontUtil.INSTANCE.applyFontStyle(getMTertiaryTextView(), fontStyle6);
    }

    private final void initViewMargins() {
        setMargins(getTextElements(), getTextElementContainerWidth(), -2, getLeftMarginForLeftAlignedView(), getTopMarginForLeftAlignedView(), 0, 0);
        if (isMusicLeftAligned() && !isWideScreen()) {
            setMargins(getHeroImageView(), -1, -1, 0, Integer.valueOf((int) getHeroImageView().getContext().getResources().getDimension(R$dimen.feature_play_hero_image_margin_top)), 0, 0);
        } else {
            if (!isMusicFacetedNavigation() || isWideScreen()) {
                return;
            }
            setMargins(getHeroImageView(), -1, -1, 0, this.hugeSpace, 0, 0);
        }
    }

    private final Integer getTopMarginForCenteredView() {
        ScreenSizeKey screenSizeKeyScreenWidthSize = screenWidthSize();
        if (isLandscape()) {
            return this.styleSheet.getFeaturedPlayTopMargin(ScreenSizeKey.XSMALL);
        }
        return this.styleSheet.getFeaturedPlayTopMargin(screenSizeKeyScreenWidthSize);
    }

    private final Integer getTopMarginForLeftAlignedView() {
        return this.styleSheet.getFeaturedPlayLeftAlignedTopMargin(getScreenSizeKeyForLeftAlignedView());
    }

    private final ScreenSizeKey getScreenSizeKeyForLeftAlignedView() {
        if (isTabletLandscape() || isWideScreen()) {
            return ScreenSizeKey.XLARGE4;
        }
        return screenWidthSize();
    }

    private final int getTextElementContainerWidth() {
        if (isWideScreen()) {
            return getScreenWidth() / 2;
        }
        ButtonSize iconSize = this.styleSheet.getIconSize(IconSizeKey.LARGE);
        Intrinsics.checkNotNull(iconSize);
        int screenWidth = getScreenWidth();
        Integer num = this.baseSpace;
        Intrinsics.checkNotNull(num);
        int iIntValue = screenWidth - num.intValue();
        Integer height = iconSize == null ? null : iconSize.getHeight();
        Intrinsics.checkNotNull(height);
        return (((iIntValue - height.intValue()) - this.gutter) - this.baseSpace.intValue()) - this.baseSpace.intValue();
    }

    private final int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public final void setLabelText(CharSequence text) {
        setOrHideText(getMLabelTextView(), text);
    }

    public final void setTitle(CharSequence text) {
        setOrHideText(getMTitleTextView(), text);
    }

    public final void setPrimaryText(CharSequence text) {
        setOrHideText(getMPrimaryTextView(), text);
    }

    public final void setSecondaryText(CharSequence text) {
        setOrHideText(getMSecondaryTextView(), text);
    }

    public final void setTertiaryText(CharSequence text) {
        setOrHideText(getMTertiaryTextView(), text);
    }

    public final void setPlayButton() {
        BaseButton.StyleBuilder styleBuilderWithIcon;
        setMargins(getPlayButton(), getLeftMarginForLeftAlignedView(), screenSizeAwareTopMarginForPlayButton(), this.largeSpace, screenSizeAwareBottomMarginForPlayButtonForNewUx());
        if (isWideScreen()) {
            BaseButton.StyleBuilder buttonBuilder = this.isSonicRushEnabled ? this.styleSheet.getButtonBuilder(ButtonSizeKey.LARGE_WITH_ICON, ButtonStyleKey.SOLID) : this.styleSheet.getButtonBuilder(ButtonSizeKey.LARGE_WITH_ICON, ButtonStyleKey.GLASS);
            if (buttonBuilder == null) {
                return;
            }
            buttonBuilder.withIcon(getPlayerButtonIcon());
            buttonBuilder.withText(this.buttonActionText);
            buttonBuilder.applyStyle(getPlayButton());
            return;
        }
        Drawable drawable = AppCompatResources.getDrawable(getContext(), R$drawable.ic_play_bauhaus);
        BaseButton.StyleBuilder styleBuilder = null;
        if (drawable != null) {
            if (this.isSonicRushEnabled) {
                BaseButton.StyleBuilder iconBuilder = getStyleSheet().getIconBuilder(IconSizeKey.MEDIUM, IconStyleKey.SOLID);
                if (iconBuilder != null) {
                    styleBuilderWithIcon = iconBuilder.withIcon(drawable);
                    styleBuilder = styleBuilderWithIcon;
                }
            } else {
                BaseButton.StyleBuilder iconBuilder2 = getStyleSheet().getIconBuilder(IconSizeKey.MEDIUM, IconStyleKey.GLASS);
                if (iconBuilder2 != null) {
                    styleBuilderWithIcon = iconBuilder2.withIcon(drawable);
                    styleBuilder = styleBuilderWithIcon;
                }
            }
        }
        if (styleBuilder == null) {
            return;
        }
        styleBuilder.withIcon(getPlayerButtonIcon());
        styleBuilder.applyStyle(getPlayButton());
    }

    public final View getPlayButtonView() {
        return getPlayButton();
    }

    public static /* synthetic */ void setEqualizer$default(FeaturePlayView featurePlayView, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        featurePlayView.setEqualizer(num);
    }

    public final void setEqualizer(Integer equalizerBarColor) {
        TextView equalizerContainerTextView = getEqualizerContainerTextView();
        FontStyle fontStyle = getStyleSheet().getFontStyle(FontStyleKey.PRIMARY);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(equalizerContainerTextView, fontStyle);
        }
        setMargins(getEqualizerContainerButton(), getLeftMarginForLeftAlignedView(), screenSizeAwareTopMarginForPlayButton(), this.largeSpace, screenSizeAwareBottomMarginForPlayButtonForNewUx());
        getMPlayNotificationIcon().setStyle(null, equalizerBarColor);
        getMPlayNotificationIcon().setSize(this.largeSpace);
    }

    public final void setBackgroundViewsForVideos() {
        post(new Runnable() { // from class: com.amazon.music.views.library.views.FeaturePlayView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                FeaturePlayView.m2415setBackgroundViewsForVideos$lambda11(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setBackgroundViewsForVideos$lambda-11, reason: not valid java name */
    public static final void m2415setBackgroundViewsForVideos$lambda11(FeaturePlayView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int width = this$0.getWidth();
        int height = this$0.getHeight();
        this$0.setVideoBackgroundImageView(width, height);
        this$0.setArtistImageContainer(width, height);
        this$0.setArtistImageContainerBackground(width, height);
        this$0.setTvImageView();
        this$0.setHeroImageForVideos(width, height);
    }

    public final void setArtistImageContainer(int width, int height) {
        double d;
        getHeroImageView().setAlpha(0.8f);
        setArtistImageContainerSize(width, height);
        Drawable drawable = getContext().getResources().getDrawable(R$drawable.t1r_tv);
        if (isWideScreen()) {
            int intrinsicWidth = (drawable.getIntrinsicWidth() * height) / drawable.getIntrinsicHeight();
            d = (((double) intrinsicWidth) * this.VIDEO_IMAGE_LEFT_MARGIN) + ((double) (width - intrinsicWidth));
        } else {
            int intrinsicWidth2 = (drawable.getIntrinsicWidth() * height) / drawable.getIntrinsicHeight();
            d = (((double) intrinsicWidth2) * this.VIDEO_IMAGE_LEFT_MARGIN) - ((double) ((intrinsicWidth2 - width) / 2));
        }
        int i = (int) (((double) height) * this.VIDEO_IMAGE_TOP_MARGIN);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(getBackgroundImages());
        int i2 = R$id.artistImageContainer;
        constraintSet.connect(i2, 3, 0, 3, i);
        constraintSet.connect(i2, 6, 0, 6, (int) d);
        constraintSet.applyTo(getBackgroundImages());
    }

    private final void setArtistImageContainerSize(int width, int height) {
        int tvIntrinsicWidth = (int) (((double) ((getTvIntrinsicWidth() * height) / getTvIntrinsicHeight())) * this.VIDEO_IMAGE_WIDTH_RATIO);
        int i = (int) (((double) height) * this.VIDEO_IMAGE_HEIGHT_RATIO);
        ConstraintLayout artistImageContainer = getArtistImageContainer();
        ViewGroup.LayoutParams layoutParams = artistImageContainer == null ? null : artistImageContainer.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width = tvIntrinsicWidth;
        }
        ConstraintLayout artistImageContainer2 = getArtistImageContainer();
        ViewGroup.LayoutParams layoutParams2 = artistImageContainer2 != null ? artistImageContainer2.getLayoutParams() : null;
        if (layoutParams2 == null) {
            return;
        }
        layoutParams2.height = i;
    }

    private final void setArtistImageContainerBackground(int width, int height) {
        int tvIntrinsicWidth = (int) (((double) ((getTvIntrinsicWidth() * height) / getTvIntrinsicHeight())) * this.VIDEO_IMAGE_WIDTH_RATIO);
        int i = (int) (((double) height) * this.VIDEO_IMAGE_HEIGHT_RATIO);
        Drawable drawable = getContext().getResources().getDrawable(R$drawable.t1r_tv_screen);
        if (drawable != null) {
            Bitmap t1rTvBackgroundBitmap = ((BitmapDrawable) drawable).getBitmap();
            BitmapEffectUtils bitmapEffectUtils = BitmapEffectUtils.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(t1rTvBackgroundBitmap, "t1rTvBackgroundBitmap");
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapEffectUtils.getScaledBitmap(t1rTvBackgroundBitmap, tvIntrinsicWidth, i), 0, 0, tvIntrinsicWidth, i);
            TransparentGradientImageView tvBackgroundImageView = getTvBackgroundImageView();
            if (tvBackgroundImageView != null) {
                tvBackgroundImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
            TransparentGradientImageView tvBackgroundImageView2 = getTvBackgroundImageView();
            if (tvBackgroundImageView2 == null) {
                return;
            }
            tvBackgroundImageView2.setImageBitmap(bitmapCreateBitmap);
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.graphics.drawable.BitmapDrawable");
    }

    private final void setTvImageView() {
        ViewGroup.LayoutParams layoutParams;
        if (isWideScreen()) {
            TransparentGradientImageView tvImageView = getTvImageView();
            ViewGroup.LayoutParams layoutParams2 = tvImageView == null ? null : tvImageView.getLayoutParams();
            if (layoutParams2 != null) {
                layoutParams2.width = (getHeight() * getTvIntrinsicWidth()) / getTvIntrinsicHeight();
            }
            TransparentGradientImageView tvImageView2 = getTvImageView();
            layoutParams = tvImageView2 != null ? tvImageView2.getLayoutParams() : null;
            if (layoutParams != null) {
                layoutParams.height = getHeight();
            }
        } else {
            TransparentGradientImageView tvImageView3 = getTvImageView();
            ViewGroup.LayoutParams layoutParams3 = tvImageView3 == null ? null : tvImageView3.getLayoutParams();
            if (layoutParams3 != null) {
                layoutParams3.width = getWidth();
            }
            TransparentGradientImageView tvImageView4 = getTvImageView();
            layoutParams = tvImageView4 != null ? tvImageView4.getLayoutParams() : null;
            if (layoutParams != null) {
                layoutParams.height = getHeight();
            }
        }
        TransparentGradientImageView tvImageView5 = getTvImageView();
        if (tvImageView5 != null) {
            tvImageView5.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        TransparentGradientImageView tvImageView6 = getTvImageView();
        if (tvImageView6 == null) {
            return;
        }
        tvImageView6.setImageResource(R$drawable.t1r_tv);
    }

    private final void setVideoBackgroundImageView(int width, int height) {
        List<Float> positions;
        List<Integer> colors;
        Drawable drawable = getContext().getResources().getDrawable(R$drawable.t1r_background);
        if (drawable == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.graphics.drawable.BitmapDrawable");
        }
        Bitmap newBitmap = Bitmap.createScaledBitmap(((BitmapDrawable) drawable).getBitmap(), width, height, true);
        GradientStyle gradientStyle = this.styleSheet.getGradientStyle(GradientStyleKey.FEATURED_PLAY_VIDEO_VERTICAL);
        float[] floatArray = (gradientStyle == null || (positions = gradientStyle.getPositions()) == null) ? null : CollectionsKt___CollectionsKt.toFloatArray(positions);
        int[] intArray = (gradientStyle == null || (colors = gradientStyle.getColors()) == null) ? null : CollectionsKt___CollectionsKt.toIntArray(colors);
        if (floatArray == null || intArray == null) {
            return;
        }
        TransparentGradientImageView backgroundImageView = getBackgroundImageView();
        if (backgroundImageView != null) {
            Intrinsics.checkNotNullExpressionValue(newBitmap, "newBitmap");
            TransparentGradientImageView.applyTransparentGradientToBottom$default(backgroundImageView, newBitmap, floatArray, intArray, width, height, false, 32, null);
        }
        TransparentGradientImageView backgroundImageView2 = getBackgroundImageView();
        if (backgroundImageView2 == null) {
            return;
        }
        backgroundImageView2.invalidate();
    }

    private final void setHeroImageForVideos(int width, int height) {
        getHeroImageView().setAlpha(0.8f);
        if (isWideScreen()) {
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(getArtistImageContainer());
            int i = R$id.hero_image;
            constraintSet.clear(i, 7);
            constraintSet.constrainPercentWidth(i, 1.0f);
            constraintSet.applyTo(getArtistImageContainer());
        }
        TransparentGradientImageView heroImageView = getHeroImageView();
        if (heroImageView != null) {
            heroImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        getHeroImageView().setVisibility(0);
    }

    private final Integer screenSizeAwareTopMarginForPlayButton() {
        if (isWideScreen()) {
            return this.largeSpace;
        }
        return 0;
    }

    private final Integer getLeftMarginForLeftAlignedView() {
        if (isTabletLandscape()) {
            return this.epicSpace;
        }
        if (isWideScreen()) {
            return this.giantSpace;
        }
        return this.largeSpace;
    }

    private final Integer screenSizeAwareBottomMarginForPlayButtonForNewUx() {
        Integer num;
        if (isWideScreen() && (num = this.hugeSpace) != null) {
            num.intValue();
            return Integer.valueOf(this.hugeSpace.intValue() * 2);
        }
        return 0;
    }

    private final void setOrHideText(TextView textView, CharSequence text) {
        textView.setText(text);
        textView.setVisibility(!TextUtils.isEmpty(text) ? 0 : 8);
    }

    public final void setPlayNotificationIconStyle(int color) {
        getMPlayNotificationIcon().setLayoutParams(getPlayButton().getLayoutParams());
        PlayNotificationIcon.setStyle$default(getMPlayNotificationIcon(), null, Integer.valueOf(color), 1, null);
    }

    public final void setPlayMusicClickListener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        getPlayButton().setClickable(true);
        getPlayButton().setOnClickListener(onClickListener);
    }

    public final void setBackgroundPlayMusicClickListener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        getHeroImageView().setClickable(true);
        getHeroImageView().setOnClickListener(onClickListener);
    }

    public final void setPlayState(Integer playState) {
        if (this.mPlayState == null && playState != null && playState.intValue() == 1) {
            return;
        }
        if (playState == null || playState.intValue() == 0) {
            getEqualizerContainerButton().setVisibility(8);
            getPlayButton().setVisibility(0);
            this.mPlayState = playState;
            setEnabled(false);
            return;
        }
        getPlayButton().setVisibility(8);
        getHeroImageView().setClickable(false);
        getMPlayNotificationIcon().setIsPlaying(playState.intValue() == 2);
        getEqualizerContainerButton().setVisibility(0);
        this.mPlayState = playState;
        if (isWideScreen()) {
            getEqualizerContainerTextView().setText(playState.intValue() == 2 ? this.buttonPlayingText : this.buttonPausedText);
        }
        setEnabled(true);
    }

    public final void setPodcastPlayState(Integer playState) {
        if ((playState != null && playState.intValue() == 3) || ((playState != null && playState.intValue() == 4) || (playState != null && playState.intValue() == 5))) {
            getPlayButton().setVisibility(8);
            getMPlayNotificationIcon().setVisibility(0);
            getMPlayNotificationIcon().setIsPlaying(true);
        } else if ((playState != null && playState.intValue() == 2) || (playState != null && playState.intValue() == 6)) {
            getPlayButton().setVisibility(8);
            getMPlayNotificationIcon().setVisibility(0);
            getMPlayNotificationIcon().setIsPlaying(false);
        } else {
            getPlayButton().setVisibility(0);
            getMPlayNotificationIcon().setVisibility(8);
            getMPlayNotificationIcon().setIsPlaying(false);
        }
    }

    private final void setMargins(View view, Integer left, Integer top, Integer right, Integer bottom) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(view, -2, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
    }

    public final void setMargins(View view, int width, int height, Integer left, Integer top, Integer right, Integer bottom) {
        Intrinsics.checkNotNullParameter(view, "view");
        LayoutParamUtils.INSTANCE.setLayoutMargins(view, width, height, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
    }

    public final boolean isWideScreen() {
        return isLandscape() || isScreenWidthLargePlus() || isScreenLargeOrExtraLarge();
    }

    public final ScreenSizeKey screenWidthSize() {
        StyleSheet styleSheet = this.styleSheet;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        return styleSheet.getWidthScreenSizeKey(context);
    }

    public final boolean isLandscape() {
        LandscapeType landscapeType = LandscapeType.MOBILE_LANDSCAPE;
        StyleSheet styleSheet = this.styleSheet;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        return landscapeType == styleSheet.getLandscapeType(context);
    }

    public final boolean isTabletLandscape() {
        LandscapeType landscapeType = LandscapeType.TABLET_LANDSCAPE;
        StyleSheet styleSheet = this.styleSheet;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        return landscapeType == styleSheet.getLandscapeType(context);
    }

    public final boolean isScreenWidthLargePlus() {
        return ScreenSizeKey.LARGE.compareTo(screenWidthSize()) <= 0;
    }

    public final boolean isScreenLargeOrExtraLarge() {
        int i = getContext().getResources().getConfiguration().screenLayout & 15;
        return i == 3 || i == 4;
    }
}
