package com.amazon.music.views.library.views;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.R$color;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$string;
import com.amazon.music.views.library.models.PageHeaderActionIconModel;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.music.views.library.styles.keys.ScreenSizeKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.styles.keys.TagSizeKey;
import com.amazon.music.views.library.styles.keys.TagStyleKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.styles.GridSize;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import com.google.firebase.perf.util.Constants;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PageHeaderView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0019\b&\u0018\u0000 Ä\u00012\u00020\u0001:\u0002Ä\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0014\u0010u\u001a\u00020v2\f\u0010w\u001a\b\u0012\u0004\u0012\u00020y0xJ[\u0010z\u001a\u00020v2\u0006\u0010{\u001a\u00020|2\b\b\u0002\u0010}\u001a\u00020=2\b\b\u0002\u0010~\u001a\u00020=2\b\b\u0002\u0010\u007f\u001a\u00020=2\u000b\b\u0002\u0010\u0080\u0001\u001a\u0004\u0018\u00010y2\f\b\u0002\u0010\u0081\u0001\u001a\u0005\u0018\u00010\u0082\u00012\f\b\u0002\u0010\u0083\u0001\u001a\u0005\u0018\u00010\u0084\u0001¢\u0006\u0003\u0010\u0085\u0001J\u001f\u0010\u0086\u0001\u001a\u00020v2\t\u0010\u0087\u0001\u001a\u0004\u0018\u00010)2\t\u0010\u0088\u0001\u001a\u0004\u0018\u00010\u0011H\u0002J\u001d\u0010\u0089\u0001\u001a\u00020v2\t\u0010\u008a\u0001\u001a\u0004\u0018\u00010B2\u0007\u0010\u008b\u0001\u001a\u000202H\u0002J\u0012\u0010\u008c\u0001\u001a\u00020v2\u0007\u0010\u008d\u0001\u001a\u00020)H\u0002J\t\u0010\u008e\u0001\u001a\u00020vH\u0016J\u0007\u0010\u008f\u0001\u001a\u00020vJ-\u0010\u0090\u0001\u001a\u0004\u0018\u00010)2\t\u0010\u0091\u0001\u001a\u0004\u0018\u00010\u00192\b\b\u0002\u0010\u007f\u001a\u00020=2\u000b\b\u0002\u0010\u0080\u0001\u001a\u0004\u0018\u00010yH\u0002J\u0016\u0010\u0092\u0001\u001a\u0004\u0018\u00010)2\t\u0010\u0093\u0001\u001a\u0004\u0018\u00010yH\u0002J\u0016\u0010\u0094\u0001\u001a\u0004\u0018\u00010\b2\t\u0010\u0093\u0001\u001a\u0004\u0018\u00010yH\u0002J\u0016\u0010\u0095\u0001\u001a\u0004\u0018\u00010)2\t\u0010\u0091\u0001\u001a\u0004\u0018\u00010\u0019H\u0002J\u0016\u0010\u0096\u0001\u001a\u0004\u0018\u00010)2\t\u0010\u0091\u0001\u001a\u0004\u0018\u00010\u0019H\u0002J\u001e\u0010\u0097\u0001\u001a\u0004\u0018\u00010)2\t\u0010\u0091\u0001\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u007f\u001a\u00020=H\u0002J+\u0010\u0098\u0001\u001a\u0004\u0018\u00010)2\t\u0010\u0091\u0001\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u007f\u001a\u00020=2\u000b\b\u0002\u0010\u0080\u0001\u001a\u0004\u0018\u00010yH\u0002J\t\u0010\u0099\u0001\u001a\u00020vH\u0016J\t\u0010\u009a\u0001\u001a\u00020vH\u0014J\t\u0010\u009b\u0001\u001a\u00020vH\u0004J\t\u0010\u009c\u0001\u001a\u00020=H\u0004J\u0015\u0010\u009d\u0001\u001a\u00020v2\n\u0010\u009e\u0001\u001a\u0005\u0018\u00010\u009f\u0001H\u0014J\u001b\u0010 \u0001\u001a\u00020v2\t\u0010¡\u0001\u001a\u0004\u0018\u00010y2\u0007\u0010¢\u0001\u001a\u00020=J\u0019\u0010£\u0001\u001a\u00020v2\n\u0010¤\u0001\u001a\u0005\u0018\u00010\u0082\u0001¢\u0006\u0003\u0010¥\u0001J/\u0010¦\u0001\u001a\u00020v2\t\u0010¡\u0001\u001a\u0004\u0018\u00010y2\t\u0010§\u0001\u001a\u0004\u0018\u0001022\n\u0010¨\u0001\u001a\u0005\u0018\u00010\u0082\u0001¢\u0006\u0003\u0010©\u0001J\u0012\u0010ª\u0001\u001a\u00020v2\t\u0010¡\u0001\u001a\u0004\u0018\u00010yJk\u0010«\u0001\u001a\u00020v2\n\u0010\u008a\u0001\u001a\u0005\u0018\u00010¬\u00012\n\b\u0002\u0010\u00ad\u0001\u001a\u00030\u0082\u00012\n\b\u0002\u0010®\u0001\u001a\u00030\u0082\u00012\f\b\u0002\u0010¯\u0001\u001a\u0005\u0018\u00010\u0082\u00012\f\b\u0002\u0010°\u0001\u001a\u0005\u0018\u00010\u0082\u00012\f\b\u0002\u0010±\u0001\u001a\u0005\u0018\u00010\u0082\u00012\f\b\u0002\u0010²\u0001\u001a\u0005\u0018\u00010\u0082\u0001H\u0004¢\u0006\u0003\u0010³\u0001J\u0012\u0010´\u0001\u001a\u00020v2\t\u0010¡\u0001\u001a\u0004\u0018\u00010yJ\u0019\u0010µ\u0001\u001a\u00020v2\n\u0010¤\u0001\u001a\u0005\u0018\u00010\u0082\u0001¢\u0006\u0003\u0010¥\u0001J\u0012\u0010¶\u0001\u001a\u00020v2\t\u0010¡\u0001\u001a\u0004\u0018\u00010yJ\u0019\u0010·\u0001\u001a\u00020v2\n\u0010¤\u0001\u001a\u0005\u0018\u00010\u0082\u0001¢\u0006\u0003\u0010¥\u0001J\u0012\u0010¸\u0001\u001a\u00020v2\t\u0010¡\u0001\u001a\u0004\u0018\u00010yJ\u001f\u0010¹\u0001\u001a\u00020v2\t\u0010\u008a\u0001\u001a\u0004\u0018\u00010B2\t\u0010¡\u0001\u001a\u0004\u0018\u00010yH\u0002J\u0012\u0010º\u0001\u001a\u00020v2\t\u0010¡\u0001\u001a\u0004\u0018\u00010yJ%\u0010»\u0001\u001a\u00020v2\n\u0010\u008a\u0001\u001a\u0005\u0018\u00010¬\u00012\n\u0010¼\u0001\u001a\u0005\u0018\u00010\u0082\u0001¢\u0006\u0003\u0010½\u0001J\u0014\u0010¾\u0001\u001a\u00020v2\t\u0010\u0093\u0001\u001a\u0004\u0018\u00010yH\u0002J\u0014\u0010¿\u0001\u001a\u00020v2\t\u0010\u0093\u0001\u001a\u0004\u0018\u00010yH\u0002J\u0018\u0010À\u0001\u001a\u00020v2\t\u0010Á\u0001\u001a\u0004\u0018\u00010=¢\u0006\u0003\u0010Â\u0001J\u0018\u0010Ã\u0001\u001a\u00020v2\t\u0010Á\u0001\u001a\u0004\u0018\u00010=¢\u0006\u0003\u0010Â\u0001R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u0004\u0018\u00010\bX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\fR\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001d\u0010\u0018\u001a\u0004\u0018\u00010\u00198BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001a\u0010\u001bR\u0016\u0010\u001e\u001a\u0004\u0018\u00010\u0011X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0013R\u001b\u0010 \u001a\u00020!8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b$\u0010\u001d\u001a\u0004\b\"\u0010#R\u001d\u0010%\u001a\u0004\u0018\u00010\u00198BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b'\u0010\u001d\u001a\u0004\b&\u0010\u001bR\u0010\u0010(\u001a\u0004\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010*\u001a\u0004\u0018\u00010\u00198BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b,\u0010\u001d\u001a\u0004\b+\u0010\u001bR\u001d\u0010-\u001a\u0004\u0018\u00010\u00198BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b/\u0010\u001d\u001a\u0004\b.\u0010\u001bR\u0010\u00100\u001a\u0004\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u00101\u001a\u000202¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u0016\u00105\u001a\u0004\u0018\u000106X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b7\u00108R\u001c\u00109\u001a\u0004\u0018\u00010\bX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\n\"\u0004\b;\u0010\fR\u001a\u0010<\u001a\u00020=X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010>\"\u0004\b?\u0010@R\u0016\u0010A\u001a\u0004\u0018\u00010BX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bC\u0010DR\u0016\u0010E\u001a\u0004\u0018\u00010BX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bF\u0010DR\u001c\u0010G\u001a\u0004\u0018\u00010\bX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010\n\"\u0004\bI\u0010\fR\u0014\u0010J\u001a\u000202X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bK\u00104R\u0014\u0010L\u001a\u000202X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bM\u00104R\u001d\u0010N\u001a\u0004\u0018\u00010\u00198DX\u0084\u0084\u0002¢\u0006\f\n\u0004\bP\u0010\u001d\u001a\u0004\bO\u0010\u001bR\u001d\u0010Q\u001a\u0004\u0018\u00010\u00198DX\u0084\u0084\u0002¢\u0006\f\n\u0004\bS\u0010\u001d\u001a\u0004\bR\u0010\u001bR\u001c\u0010T\u001a\u0004\u0018\u00010UX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010W\"\u0004\bX\u0010YR\u001d\u0010Z\u001a\u0004\u0018\u00010\u00198DX\u0084\u0084\u0002¢\u0006\f\n\u0004\b\\\u0010\u001d\u001a\u0004\b[\u0010\u001bR\u0016\u0010]\u001a\u0004\u0018\u00010BX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b^\u0010DR\u0016\u0010_\u001a\u0004\u0018\u00010\u0011X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b`\u0010\u0013R\u0016\u0010a\u001a\u0004\u0018\u00010BX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bb\u0010DR\u001d\u0010c\u001a\u0004\u0018\u00010\u00198DX\u0084\u0084\u0002¢\u0006\f\n\u0004\be\u0010\u001d\u001a\u0004\bd\u0010\u001bR\u001a\u0010f\u001a\u00020=X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bg\u0010>\"\u0004\bh\u0010@R\u001d\u0010i\u001a\u0004\u0018\u00010\u00198DX\u0084\u0084\u0002¢\u0006\f\n\u0004\bk\u0010\u001d\u001a\u0004\bj\u0010\u001bR\u001d\u0010l\u001a\u0004\u0018\u00010\u00198DX\u0084\u0084\u0002¢\u0006\f\n\u0004\bn\u0010\u001d\u001a\u0004\bm\u0010\u001bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bo\u0010pR\u0016\u0010q\u001a\u0004\u0018\u00010BX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\br\u0010DR\u0016\u0010s\u001a\u0004\u0018\u00010BX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bt\u0010D¨\u0006Å\u0001"}, d2 = {"Lcom/amazon/music/views/library/views/PageHeaderView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "accentButtonBuilder", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "getAccentButtonBuilder", "()Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "setAccentButtonBuilder", "(Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;)V", "accentIconButtonBuilder", "getAccentIconButtonBuilder", "setAccentIconButtonBuilder", "actionIconContainer", "Landroid/widget/LinearLayout;", "getActionIconContainer", "()Landroid/widget/LinearLayout;", "actionIcons", "Landroid/widget/RelativeLayout;", "getActionIcons", "()Landroid/widget/RelativeLayout;", "addDrawable", "Landroid/graphics/drawable/Drawable;", "getAddDrawable", "()Landroid/graphics/drawable/Drawable;", "addDrawable$delegate", "Lkotlin/Lazy;", "badgeIconContainer", "getBadgeIconContainer", "downloadActionButton", "Lcom/amazon/music/views/library/views/DownloadHeaderActionView;", "getDownloadActionButton", "()Lcom/amazon/music/views/library/views/DownloadHeaderActionView;", "downloadActionButton$delegate", "editDrawable", "getEditDrawable", "editDrawable$delegate", "favoriteButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "favoriteDrawable", "getFavoriteDrawable", "favoriteDrawable$delegate", "favoriteOnDrawable", "getFavoriteOnDrawable", "favoriteOnDrawable$delegate", "followButton", "headlineFontStyleKeySR", "Lcom/amazon/music/views/library/styles/keys/FontStyleKey;", "getHeadlineFontStyleKeySR", "()Lcom/amazon/music/views/library/styles/keys/FontStyleKey;", "headlineTextView", "Lcom/amazon/music/views/library/views/PageHeaderHeadlineTextView;", "getHeadlineTextView", "()Lcom/amazon/music/views/library/views/PageHeaderHeadlineTextView;", "iconButtonBuilder", "getIconButtonBuilder", "setIconButtonBuilder", "isSonicRushEnabled", "", "()Z", "setSonicRushEnabled", "(Z)V", "labelBadgeView", "Landroid/widget/TextView;", "getLabelBadgeView", "()Landroid/widget/TextView;", "labelTextView", "getLabelTextView", "largeButtonBuilder", "getLargeButtonBuilder", "setLargeButtonBuilder", "maximumHeadlineFontStyleKey", "getMaximumHeadlineFontStyleKey", "minimumHeadlineFontStyleKey", "getMinimumHeadlineFontStyleKey", "overflowDrawable", "getOverflowDrawable", "overflowDrawable$delegate", "overflowDrawableVertical", "getOverflowDrawableVertical", "overflowDrawableVertical$delegate", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "getPageType", "()Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "setPageType", "(Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;)V", "playDrawable", "getPlayDrawable", "playDrawable$delegate", "primaryTextView", "getPrimaryTextView", "rightActionIconContainer", "getRightActionIconContainer", "secondaryTextView", "getSecondaryTextView", "shareDrawable", "getShareDrawable", "shareDrawable$delegate", "shouldShowDownloadTextButton", "getShouldShowDownloadTextButton", "setShouldShowDownloadTextButton", "shuffleDrawable", "getShuffleDrawable", "shuffleDrawable$delegate", "stationDrawable", "getStationDrawable", "stationDrawable$delegate", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "tertiaryTextView", "getTertiaryTextView", "textView4", "getTextView4", "addBadgeIcons", "", "encodings", "", "", "addButton", "actionIconType", "Lcom/amazon/music/views/library/models/PageHeaderActionIconModel$ActionType;", Constants.ENABLE_DISABLE, "canClickWhenDisabled", "asLargeButton", "largeButtonText", "buttonViewId", "", "onClickListener", "Landroid/view/View$OnClickListener;", "(Lcom/amazon/music/views/library/models/PageHeaderActionIconModel$ActionType;ZZZLjava/lang/String;Ljava/lang/Integer;Landroid/view/View$OnClickListener;)V", "addToContainer", "button", "iconContainer", "applyFontStyle", "view", "fontStyleKey", "buildBadge", "badge", "clear", "clearActionIconContainer", "getAddButton", "drawable", "getBadge", "badgeText", "getBadgeBuilder", "getButtonWithDrawable", "getPlayButton", "getShuffleButton", "getStationButton", "init", "initMargins", "initViewStyling", "isTabletDevice", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "setHeadlineText", "text", "overrideTitleCaps", "setHeadlineTitleId", "id", "(Ljava/lang/Integer;)V", "setLabelBadgeText", "fontStyle", "badgeBackground", "(Ljava/lang/String;Lcom/amazon/music/views/library/styles/keys/FontStyleKey;Ljava/lang/Integer;)V", "setLabelText", "setMargins", "Landroid/view/View;", "width", "height", "left", "top", "right", "bottom", "(Landroid/view/View;IILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "setPrimaryText", "setPrimaryTextViewId", "setSecondaryText", "setSecondaryTextViewId", "setTertiaryText", "setText", "setText4", "setViewId", "viewId", "(Landroid/view/View;Ljava/lang/Integer;)V", "setupSpatialAudioQualityBadge", "setupStereoAudioQualityBadge", "updateFavoriteState", "isFollowed", "(Ljava/lang/Boolean;)V", "updateFollowState", "Companion", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class PageHeaderView extends ConstraintLayout {
    private BaseButton.StyleBuilder accentButtonBuilder;
    private BaseButton.StyleBuilder accentIconButtonBuilder;
    private final LinearLayout actionIconContainer;
    private final RelativeLayout actionIcons;

    /* JADX INFO: renamed from: addDrawable$delegate, reason: from kotlin metadata */
    private final Lazy addDrawable;
    private final LinearLayout badgeIconContainer;

    /* JADX INFO: renamed from: downloadActionButton$delegate, reason: from kotlin metadata */
    private final Lazy downloadActionButton;

    /* JADX INFO: renamed from: editDrawable$delegate, reason: from kotlin metadata */
    private final Lazy editDrawable;
    private BaseButton favoriteButton;

    /* JADX INFO: renamed from: favoriteDrawable$delegate, reason: from kotlin metadata */
    private final Lazy favoriteDrawable;

    /* JADX INFO: renamed from: favoriteOnDrawable$delegate, reason: from kotlin metadata */
    private final Lazy favoriteOnDrawable;
    private BaseButton followButton;
    private final FontStyleKey headlineFontStyleKeySR;
    private final PageHeaderHeadlineTextView headlineTextView;
    private BaseButton.StyleBuilder iconButtonBuilder;
    private boolean isSonicRushEnabled;
    private final TextView labelBadgeView;
    private final TextView labelTextView;
    private BaseButton.StyleBuilder largeButtonBuilder;
    private final FontStyleKey maximumHeadlineFontStyleKey;
    private final FontStyleKey minimumHeadlineFontStyleKey;

    /* JADX INFO: renamed from: overflowDrawable$delegate, reason: from kotlin metadata */
    private final Lazy overflowDrawable;

    /* JADX INFO: renamed from: overflowDrawableVertical$delegate, reason: from kotlin metadata */
    private final Lazy overflowDrawableVertical;
    private PageType pageType;

    /* JADX INFO: renamed from: playDrawable$delegate, reason: from kotlin metadata */
    private final Lazy playDrawable;
    private final TextView primaryTextView;
    private final LinearLayout rightActionIconContainer;
    private final TextView secondaryTextView;

    /* JADX INFO: renamed from: shareDrawable$delegate, reason: from kotlin metadata */
    private final Lazy shareDrawable;
    private boolean shouldShowDownloadTextButton;

    /* JADX INFO: renamed from: shuffleDrawable$delegate, reason: from kotlin metadata */
    private final Lazy shuffleDrawable;

    /* JADX INFO: renamed from: stationDrawable$delegate, reason: from kotlin metadata */
    private final Lazy stationDrawable;
    private final StyleSheet styleSheet;
    private final TextView tertiaryTextView;
    private final TextView textView4;

    /* JADX INFO: compiled from: PageHeaderView.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            int[] iArr = new int[PageType.values().length];
            iArr[PageType.OFFLINE_LIBRARY_PLAYLISTS.ordinal()] = 1;
            iArr[PageType.CLOUD_LIBRARY_PLAYLISTS.ordinal()] = 2;
            iArr[PageType.OFFLINE_LIBRARY_SONGS.ordinal()] = 3;
            iArr[PageType.CLOUD_LIBRARY_SONGS.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[PageHeaderActionIconModel.ActionType.values().length];
            iArr2[PageHeaderActionIconModel.ActionType.PLAY.ordinal()] = 1;
            iArr2[PageHeaderActionIconModel.ActionType.SHUFFLE.ordinal()] = 2;
            iArr2[PageHeaderActionIconModel.ActionType.STATION.ordinal()] = 3;
            iArr2[PageHeaderActionIconModel.ActionType.SHARE.ordinal()] = 4;
            iArr2[PageHeaderActionIconModel.ActionType.ADD.ordinal()] = 5;
            iArr2[PageHeaderActionIconModel.ActionType.OVERFLOW.ordinal()] = 6;
            iArr2[PageHeaderActionIconModel.ActionType.DOWNLOAD.ordinal()] = 7;
            iArr2[PageHeaderActionIconModel.ActionType.FOLLOW.ordinal()] = 8;
            iArr2[PageHeaderActionIconModel.ActionType.FAVORITE.ordinal()] = 9;
            iArr2[PageHeaderActionIconModel.ActionType.EDIT.ordinal()] = 10;
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[ScreenSizeKey.values().length];
            iArr3[ScreenSizeKey.XSMALL.ordinal()] = 1;
            iArr3[ScreenSizeKey.SMALL.ordinal()] = 2;
            iArr3[ScreenSizeKey.MEDIUM.ordinal()] = 3;
            $EnumSwitchMapping$2 = iArr3;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PageHeaderView(final Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.maximumHeadlineFontStyleKey = FontStyleKey.HEADLINE_2;
        FontStyleKey fontStyleKey = FontStyleKey.HEADLINE_3;
        this.minimumHeadlineFontStyleKey = fontStyleKey;
        this.headlineFontStyleKeySR = fontStyleKey;
        this.playDrawable = LazyKt__LazyJVMKt.lazy(new Function0<Drawable>() { // from class: com.amazon.music.views.library.views.PageHeaderView$playDrawable$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Drawable invoke() {
                return AppCompatResources.getDrawable(context, R$drawable.ic_playback_play);
            }
        });
        this.shuffleDrawable = LazyKt__LazyJVMKt.lazy(new Function0<Drawable>() { // from class: com.amazon.music.views.library.views.PageHeaderView$shuffleDrawable$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Drawable invoke() {
                return AppCompatResources.getDrawable(context, R$drawable.ic_playback_shuffle);
            }
        });
        this.stationDrawable = LazyKt__LazyJVMKt.lazy(new Function0<Drawable>() { // from class: com.amazon.music.views.library.views.PageHeaderView$stationDrawable$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Drawable invoke() {
                return AppCompatResources.getDrawable(context, R$drawable.ic_playback_station_svg);
            }
        });
        this.overflowDrawable = LazyKt__LazyJVMKt.lazy(new Function0<Drawable>() { // from class: com.amazon.music.views.library.views.PageHeaderView$overflowDrawable$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Drawable invoke() {
                return AppCompatResources.getDrawable(context, R$drawable.ic_action_more);
            }
        });
        this.overflowDrawableVertical = LazyKt__LazyJVMKt.lazy(new Function0<Drawable>() { // from class: com.amazon.music.views.library.views.PageHeaderView$overflowDrawableVertical$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Drawable invoke() {
                return AppCompatResources.getDrawable(context, R$drawable.overflow_normal);
            }
        });
        this.shareDrawable = LazyKt__LazyJVMKt.lazy(new Function0<Drawable>() { // from class: com.amazon.music.views.library.views.PageHeaderView$shareDrawable$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Drawable invoke() {
                return AppCompatResources.getDrawable(context, R$drawable.ic_action_share);
            }
        });
        this.addDrawable = LazyKt__LazyJVMKt.lazy(new Function0<Drawable>() { // from class: com.amazon.music.views.library.views.PageHeaderView$addDrawable$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Drawable invoke() {
                return AppCompatResources.getDrawable(context, R$drawable.ic_action_add);
            }
        });
        this.favoriteDrawable = LazyKt__LazyJVMKt.lazy(new Function0<Drawable>() { // from class: com.amazon.music.views.library.views.PageHeaderView$favoriteDrawable$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Drawable invoke() {
                return AppCompatResources.getDrawable(context, R$drawable.ic_action_favorite_svg);
            }
        });
        this.favoriteOnDrawable = LazyKt__LazyJVMKt.lazy(new Function0<Drawable>() { // from class: com.amazon.music.views.library.views.PageHeaderView$favoriteOnDrawable$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Drawable invoke() {
                return AppCompatResources.getDrawable(context, R$drawable.ic_action_favoriteon_svg);
            }
        });
        this.editDrawable = LazyKt__LazyJVMKt.lazy(new Function0<Drawable>() { // from class: com.amazon.music.views.library.views.PageHeaderView$editDrawable$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Drawable invoke() {
                return AppCompatResources.getDrawable(context, R$drawable.ic_action_edit);
            }
        });
        this.downloadActionButton = LazyKt__LazyJVMKt.lazy(new Function0<DownloadHeaderActionView>() { // from class: com.amazon.music.views.library.views.PageHeaderView$downloadActionButton$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DownloadHeaderActionView invoke() {
                return new DownloadHeaderActionView(context, this.getStyleSheet(), this.getShouldShowDownloadTextButton());
            }
        });
    }

    public final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    public TextView getLabelBadgeView() {
        return this.labelBadgeView;
    }

    public TextView getLabelTextView() {
        return this.labelTextView;
    }

    public PageHeaderHeadlineTextView getHeadlineTextView() {
        return this.headlineTextView;
    }

    public TextView getPrimaryTextView() {
        return this.primaryTextView;
    }

    public TextView getSecondaryTextView() {
        return this.secondaryTextView;
    }

    public TextView getTertiaryTextView() {
        return this.tertiaryTextView;
    }

    public TextView getTextView4() {
        return this.textView4;
    }

    public LinearLayout getBadgeIconContainer() {
        return this.badgeIconContainer;
    }

    public RelativeLayout getActionIcons() {
        return this.actionIcons;
    }

    public LinearLayout getActionIconContainer() {
        return this.actionIconContainer;
    }

    public LinearLayout getRightActionIconContainer() {
        return this.rightActionIconContainer;
    }

    public FontStyleKey getMaximumHeadlineFontStyleKey() {
        return this.maximumHeadlineFontStyleKey;
    }

    public FontStyleKey getMinimumHeadlineFontStyleKey() {
        return this.minimumHeadlineFontStyleKey;
    }

    public final FontStyleKey getHeadlineFontStyleKeySR() {
        return this.headlineFontStyleKeySR;
    }

    protected final BaseButton.StyleBuilder getIconButtonBuilder() {
        return this.iconButtonBuilder;
    }

    protected final void setIconButtonBuilder(BaseButton.StyleBuilder styleBuilder) {
        this.iconButtonBuilder = styleBuilder;
    }

    protected final BaseButton.StyleBuilder getAccentIconButtonBuilder() {
        return this.accentIconButtonBuilder;
    }

    protected final void setAccentIconButtonBuilder(BaseButton.StyleBuilder styleBuilder) {
        this.accentIconButtonBuilder = styleBuilder;
    }

    protected final BaseButton.StyleBuilder getLargeButtonBuilder() {
        return this.largeButtonBuilder;
    }

    protected final void setLargeButtonBuilder(BaseButton.StyleBuilder styleBuilder) {
        this.largeButtonBuilder = styleBuilder;
    }

    protected final BaseButton.StyleBuilder getAccentButtonBuilder() {
        return this.accentButtonBuilder;
    }

    protected final void setAccentButtonBuilder(BaseButton.StyleBuilder styleBuilder) {
        this.accentButtonBuilder = styleBuilder;
    }

    protected final Drawable getPlayDrawable() {
        return (Drawable) this.playDrawable.getValue();
    }

    protected final Drawable getShuffleDrawable() {
        return (Drawable) this.shuffleDrawable.getValue();
    }

    protected final Drawable getStationDrawable() {
        return (Drawable) this.stationDrawable.getValue();
    }

    protected final Drawable getOverflowDrawable() {
        return (Drawable) this.overflowDrawable.getValue();
    }

    protected final Drawable getOverflowDrawableVertical() {
        return (Drawable) this.overflowDrawableVertical.getValue();
    }

    protected final Drawable getShareDrawable() {
        return (Drawable) this.shareDrawable.getValue();
    }

    private final Drawable getAddDrawable() {
        return (Drawable) this.addDrawable.getValue();
    }

    private final Drawable getFavoriteDrawable() {
        return (Drawable) this.favoriteDrawable.getValue();
    }

    private final Drawable getFavoriteOnDrawable() {
        return (Drawable) this.favoriteOnDrawable.getValue();
    }

    private final Drawable getEditDrawable() {
        return (Drawable) this.editDrawable.getValue();
    }

    public boolean getShouldShowDownloadTextButton() {
        return this.shouldShowDownloadTextButton;
    }

    public void setShouldShowDownloadTextButton(boolean z) {
        this.shouldShowDownloadTextButton = z;
    }

    public final DownloadHeaderActionView getDownloadActionButton() {
        return (DownloadHeaderActionView) this.downloadActionButton.getValue();
    }

    /* JADX INFO: renamed from: isSonicRushEnabled, reason: from getter */
    public boolean getIsSonicRushEnabled() {
        return this.isSonicRushEnabled;
    }

    public void setSonicRushEnabled(boolean z) {
        this.isSonicRushEnabled = z;
    }

    public PageType getPageType() {
        return this.pageType;
    }

    public void setPageType(PageType pageType) {
        this.pageType = pageType;
    }

    public void init() {
        int spacerInPixels;
        BaseButton.StyleBuilder styleBuilderWithIcon;
        BaseButton.StyleBuilder styleBuilderWithIcon2;
        BaseButton baseButtonBuild;
        PageHeaderHeadlineTextView headlineTextView = getHeadlineTextView();
        if (headlineTextView != null) {
            headlineTextView.initStyleSheet(this.styleSheet, getMinimumHeadlineFontStyleKey(), getMaximumHeadlineFontStyleKey());
        }
        initViewStyling();
        initMargins();
        if (getPageType() == null) {
            Integer spacerInPixels2 = this.styleSheet.getSpacerInPixels(getIsSonicRushEnabled() ? SpacerKey.MINI : SpacerKey.LARGE);
            if (spacerInPixels2 != null) {
                setMargins$default(this, getActionIcons(), 0, 0, null, Integer.valueOf(spacerInPixels2.intValue()), null, null, 110, null);
            }
        } else {
            PageType pageType = getPageType();
            int i = pageType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[pageType.ordinal()];
            if (i == 1 || i == 2) {
                spacerInPixels = this.styleSheet.getSpacerInPixels(SpacerKey.LARGE);
            } else if (i == 3 || i == 4) {
                spacerInPixels = this.styleSheet.getSpacerInPixels(SpacerKey.SMALL);
            } else {
                spacerInPixels = 0;
            }
            setMargins$default(this, getActionIcons(), 0, 0, null, spacerInPixels, null, null, 110, null);
        }
        Drawable addDrawable = getAddDrawable();
        BaseButton baseButtonBuild2 = null;
        if (addDrawable != null) {
            BaseButton.StyleBuilder iconButtonBuilder = getIconButtonBuilder();
            if (iconButtonBuilder == null || (styleBuilderWithIcon2 = iconButtonBuilder.withIcon(addDrawable)) == null) {
                baseButtonBuild = null;
            } else {
                Context context = getContext();
                Intrinsics.checkNotNullExpressionValue(context, "context");
                baseButtonBuild = styleBuilderWithIcon2.build(context);
            }
            this.followButton = baseButtonBuild;
        }
        Drawable favoriteDrawable = getFavoriteDrawable();
        if (favoriteDrawable == null) {
            return;
        }
        BaseButton.StyleBuilder iconButtonBuilder2 = getIconButtonBuilder();
        if (iconButtonBuilder2 != null && (styleBuilderWithIcon = iconButtonBuilder2.withIcon(favoriteDrawable)) != null) {
            Context context2 = getContext();
            Intrinsics.checkNotNullExpressionValue(context2, "context");
            baseButtonBuild2 = styleBuilderWithIcon.build(context2);
        }
        this.favoriteButton = baseButtonBuild2;
    }

    protected final void initViewStyling() {
        StyleSheet styleSheet;
        ButtonSizeKey buttonSizeKey;
        ButtonStyleKey buttonStyleKey;
        applyFontStyle(getLabelTextView(), FontStyleKey.LABEL);
        applyFontStyle(getHeadlineTextView(), getMaximumHeadlineFontStyleKey());
        applyFontStyle(getPrimaryTextView(), FontStyleKey.PRIMARY);
        applyFontStyle(getSecondaryTextView(), FontStyleKey.SECONDARY);
        applyFontStyle(getTertiaryTextView(), FontStyleKey.DP_TERTIARY);
        applyFontStyle(getTextView4(), FontStyleKey.TERTIARY);
        StyleSheet styleSheet2 = this.styleSheet;
        IconSizeKey iconSizeKey = IconSizeKey.MEDIUM;
        this.iconButtonBuilder = styleSheet2.getIconBuilder(iconSizeKey, getIsSonicRushEnabled() ? IconStyleKey.PRIMARY : IconStyleKey.GLASS);
        this.accentIconButtonBuilder = this.styleSheet.getIconBuilder(iconSizeKey, IconStyleKey.SOLID);
        if (getIsSonicRushEnabled()) {
            styleSheet = this.styleSheet;
            buttonSizeKey = ButtonSizeKey.MEDIUM_WITH_ICON;
            buttonStyleKey = ButtonStyleKey.SOLID;
        } else {
            styleSheet = this.styleSheet;
            buttonSizeKey = ButtonSizeKey.LARGE_WITH_ICON;
            buttonStyleKey = ButtonStyleKey.GLASS;
        }
        this.largeButtonBuilder = styleSheet.getButtonBuilder(buttonSizeKey, buttonStyleKey);
        this.accentButtonBuilder = this.styleSheet.getButtonBuilder(ButtonSizeKey.LARGE_WITH_ICON, ButtonStyleKey.SOLID);
    }

    protected void initMargins() {
        GridSize gridSize = this.styleSheet.getGridSize();
        int marginSpace = gridSize == null ? 0 : gridSize.getMarginSpace();
        GridSize gridSize2 = this.styleSheet.getGridSize();
        int columnSpace = marginSpace + (gridSize2 == null ? 0 : gridSize2.getColumnSpace());
        Integer spacerInPixels = this.styleSheet.getSpacerInPixels(SpacerKey.MICRO);
        int iIntValue = spacerInPixels == null ? 0 : spacerInPixels.intValue();
        Integer spacerInPixels2 = this.styleSheet.getSpacerInPixels(SpacerKey.MINI);
        int iIntValue2 = spacerInPixels2 == null ? 0 : spacerInPixels2.intValue();
        Integer spacerInPixels3 = this.styleSheet.getSpacerInPixels(SpacerKey.SMALL);
        int iIntValue3 = spacerInPixels3 == null ? 0 : spacerInPixels3.intValue();
        Integer spacerInPixels4 = this.styleSheet.getSpacerInPixels(SpacerKey.LARGE);
        int iIntValue4 = spacerInPixels4 == null ? 0 : spacerInPixels4.intValue();
        int i = iIntValue3;
        int i2 = iIntValue4;
        LayoutParamUtils.INSTANCE.setLayoutMargins(this, -1, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : Integer.valueOf(columnSpace), (240 & 32) != 0 ? null : 0, (240 & 64) != 0 ? null : Integer.valueOf(columnSpace), (240 & 128) != 0 ? null : Integer.valueOf(iIntValue4));
        setPadding(0, i2, 0, 0);
        if (getIsSonicRushEnabled() && getPageType() == null) {
            setMargins$default(this, getHeadlineTextView(), 0, 0, null, Integer.valueOf(i2), null, null, 110, null);
        }
        setMargins$default(this, getPrimaryTextView(), 0, 0, null, Integer.valueOf(iIntValue), null, null, 110, null);
        setMargins$default(this, getSecondaryTextView(), 0, 0, null, Integer.valueOf(iIntValue2), null, null, 110, null);
        setMargins$default(this, getTertiaryTextView(), 0, 0, null, Integer.valueOf(iIntValue2), null, null, 110, null);
        setMargins$default(this, getTextView4(), 0, 0, null, Integer.valueOf(iIntValue2), null, null, 110, null);
        setMargins$default(this, getBadgeIconContainer(), 0, 0, null, Integer.valueOf(i), null, null, 110, null);
    }

    public final void setLabelText(String text) {
        if (!getIsSonicRushEnabled() || getPageType() != null) {
            setText(getLabelTextView(), text);
        } else {
            setText(getLabelTextView(), null);
        }
    }

    public final void setLabelBadgeText(String text, FontStyleKey fontStyle, Integer badgeBackground) {
        FontStyle fontStyle2;
        Integer spacerInPixels = this.styleSheet.getSpacerInPixels(SpacerKey.NANO);
        int iIntValue = spacerInPixels == null ? 0 : spacerInPixels.intValue();
        Integer spacerInPixels2 = this.styleSheet.getSpacerInPixels(SpacerKey.MICRO);
        int iIntValue2 = spacerInPixels2 == null ? 0 : spacerInPixels2.intValue();
        Integer spacerInPixels3 = this.styleSheet.getSpacerInPixels(SpacerKey.LARGE);
        int iIntValue3 = spacerInPixels3 == null ? 0 : spacerInPixels3.intValue();
        setText(getLabelBadgeView(), text);
        setMargins$default(this, getLabelBadgeView(), 0, 0, null, Integer.valueOf(iIntValue3), null, Integer.valueOf(iIntValue2), 46, null);
        setMargins$default(this, getHeadlineTextView(), 0, 0, null, 0, null, null, 110, null);
        TextView labelBadgeView = getLabelBadgeView();
        if (labelBadgeView != null && fontStyle != null && (fontStyle2 = getStyleSheet().getFontStyle(fontStyle)) != null) {
            FontUtil.INSTANCE.applyFontSize(labelBadgeView, fontStyle2.getFontSize());
            labelBadgeView.setTypeface(fontStyle2.getTypeface());
            labelBadgeView.setTextColor(fontStyle2.getColor());
        }
        if (badgeBackground != null) {
            int iIntValue4 = badgeBackground.intValue();
            TextView labelBadgeView2 = getLabelBadgeView();
            if (labelBadgeView2 != null) {
                labelBadgeView2.setBackgroundResource(iIntValue4);
            }
        }
        TextView labelBadgeView3 = getLabelBadgeView();
        if (labelBadgeView3 != null) {
            labelBadgeView3.setPadding(iIntValue2, iIntValue, iIntValue2, iIntValue);
        }
        TextView labelBadgeView4 = getLabelBadgeView();
        if (labelBadgeView4 == null) {
            return;
        }
        labelBadgeView4.setVisibility(0);
    }

    public final void setHeadlineText(String text, boolean overrideTitleCaps) {
        setText(getHeadlineTextView(), text);
        if (TextUtils.isEmpty(text)) {
            return;
        }
        FontStyle fontStyle = getIsSonicRushEnabled() ? this.styleSheet.getFontStyle(this.headlineFontStyleKeySR) : this.styleSheet.getFontStyle(getMaximumHeadlineFontStyleKey());
        if (overrideTitleCaps || getIsSonicRushEnabled()) {
            fontStyle = fontStyle == null ? null : FontStyle.copy$default(fontStyle, null, null, 0, Boolean.FALSE, 7, null);
        }
        PageHeaderHeadlineTextView headlineTextView = getHeadlineTextView();
        if (headlineTextView == null || fontStyle == null) {
            return;
        }
        FontUtil.INSTANCE.applyFontStyle(headlineTextView, fontStyle);
    }

    public final void setHeadlineTitleId(Integer id) {
        setViewId(getHeadlineTextView(), id);
    }

    public final void setPrimaryText(String text) {
        setText(getPrimaryTextView(), text);
    }

    public final void setPrimaryTextViewId(Integer id) {
        setViewId(getPrimaryTextView(), id);
    }

    public final void setSecondaryText(String text) {
        setText(getSecondaryTextView(), text);
    }

    public final void setSecondaryTextViewId(Integer id) {
        setViewId(getSecondaryTextView(), id);
    }

    public final void setTertiaryText(String text) {
        setText(getTertiaryTextView(), text);
    }

    public final void setText4(String text) {
        setText(getTextView4(), text);
    }

    public final void setViewId(View view, Integer viewId) {
        if (view == null || viewId == null) {
            return;
        }
        view.setId(viewId.intValue());
    }

    public static /* synthetic */ void addButton$default(PageHeaderView pageHeaderView, PageHeaderActionIconModel.ActionType actionType, boolean z, boolean z2, boolean z3, String str, Integer num, View.OnClickListener onClickListener, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addButton");
        }
        pageHeaderView.addButton(actionType, (i & 2) != 0 ? true : z, (i & 4) != 0 ? false : z2, (i & 8) == 0 ? z3 : false, (i & 16) != 0 ? null : str, (i & 32) != 0 ? null : num, (i & 64) == 0 ? onClickListener : null);
    }

    public final void addButton(PageHeaderActionIconModel.ActionType actionIconType, boolean isEnabled, boolean canClickWhenDisabled, boolean asLargeButton, String largeButtonText, Integer buttonViewId, View.OnClickListener onClickListener) {
        BaseButton buttonWithDrawable;
        Integer numValueOf;
        Integer spacerInPixels;
        Intrinsics.checkNotNullParameter(actionIconType, "actionIconType");
        int[] iArr = WhenMappings.$EnumSwitchMapping$1;
        switch (iArr[actionIconType.ordinal()]) {
            case 1:
                buttonWithDrawable = !getIsSonicRushEnabled() ? getButtonWithDrawable(getPlayDrawable()) : getPlayButton(getPlayDrawable());
                break;
            case 2:
                buttonWithDrawable = getShuffleButton(getShuffleDrawable(), asLargeButton);
                break;
            case 3:
                buttonWithDrawable = getStationButton(getStationDrawable(), asLargeButton, largeButtonText);
                break;
            case 4:
                buttonWithDrawable = getButtonWithDrawable(getShareDrawable());
                break;
            case 5:
                buttonWithDrawable = getAddButton(getAddDrawable(), asLargeButton, largeButtonText);
                break;
            case 6:
                buttonWithDrawable = getButtonWithDrawable(getIsSonicRushEnabled() ? getOverflowDrawable() : getOverflowDrawableVertical());
                break;
            case 7:
                buttonWithDrawable = getDownloadActionButton();
                break;
            case 8:
                buttonWithDrawable = this.followButton;
                break;
            case 9:
                buttonWithDrawable = this.favoriteButton;
                break;
            case 10:
                buttonWithDrawable = getButtonWithDrawable(getEditDrawable());
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        BaseButton baseButton = buttonWithDrawable;
        switch (iArr[actionIconType.ordinal()]) {
            case 1:
                numValueOf = Integer.valueOf(buttonViewId == null ? R$id.PlayButton : buttonViewId.intValue());
                break;
            case 2:
                numValueOf = Integer.valueOf(buttonViewId == null ? R$id.ShuffleButton : buttonViewId.intValue());
                break;
            case 3:
                numValueOf = Integer.valueOf(buttonViewId == null ? R$id.StationFromAnythingButton : buttonViewId.intValue());
                break;
            case 4:
                numValueOf = Integer.valueOf(buttonViewId == null ? R$id.ShareButton : buttonViewId.intValue());
                break;
            case 5:
                numValueOf = Integer.valueOf(buttonViewId == null ? R$id.AddOrFollowButton : buttonViewId.intValue());
                break;
            case 6:
                numValueOf = Integer.valueOf(buttonViewId == null ? R$id.OverflowButton : buttonViewId.intValue());
                break;
            case 7:
                numValueOf = buttonViewId;
                break;
            case 8:
                numValueOf = Integer.valueOf(buttonViewId == null ? R$id.AddOrFollowButton : buttonViewId.intValue());
                break;
            case 9:
                numValueOf = Integer.valueOf(buttonViewId == null ? R$id.FavoriteButton : buttonViewId.intValue());
                break;
            case 10:
                numValueOf = Integer.valueOf(buttonViewId == null ? R$id.EditButton : buttonViewId.intValue());
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        if (canClickWhenDisabled && !isEnabled) {
            if (baseButton != null) {
                baseButton.setLayerType(2, null);
            }
            if (baseButton != null) {
                baseButton.setAlpha(0.3f);
            }
        } else if (baseButton != null) {
            baseButton.setEnabled(isEnabled);
        }
        if (baseButton != null) {
            baseButton.setOnClickListener(onClickListener);
        }
        if (baseButton != null) {
            baseButton.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        }
        if (numValueOf != null) {
            int iIntValue = numValueOf.intValue();
            if (baseButton != null) {
                baseButton.setId(iIntValue);
            }
        }
        if (baseButton != null) {
            if (getIsSonicRushEnabled()) {
                int i = iArr[actionIconType.ordinal()];
                if (i == 1) {
                    setMargins$default(this, this, -1, 0, null, null, null, 0, 60, null);
                    if (getPageType() != null || isTabletDevice()) {
                        setMargins$default(this, baseButton, 0, 0, null, null, this.styleSheet.getSpacerInPixels(SpacerKey.SMALL), null, 94, null);
                        addToContainer(baseButton, getActionIconContainer());
                        return;
                    } else {
                        addToContainer(baseButton, getRightActionIconContainer());
                        return;
                    }
                }
                if (i != 2) {
                    if (i == 7) {
                        if (asLargeButton && (spacerInPixels = this.styleSheet.getSpacerInPixels(SpacerKey.SMALL)) != null) {
                            setMargins$default(this, baseButton, 0, 0, null, Integer.valueOf(spacerInPixels.intValue() / 2), null, null, 110, null);
                        }
                        addToContainer(baseButton, getActionIconContainer());
                        return;
                    }
                    addToContainer(baseButton, getActionIconContainer());
                    return;
                }
                if (asLargeButton) {
                    StyleSheet styleSheet = this.styleSheet;
                    SpacerKey spacerKey = SpacerKey.SMALL;
                    Integer spacerInPixels2 = styleSheet.getSpacerInPixels(spacerKey);
                    Integer spacerInPixels3 = this.styleSheet.getSpacerInPixels(SpacerKey.LARGE);
                    Integer spacerInPixels4 = this.styleSheet.getSpacerInPixels(spacerKey);
                    if (spacerInPixels2 != null) {
                        setMargins$default(this, baseButton, 0, 0, null, Integer.valueOf(spacerInPixels2.intValue() / 2), null, null, 110, null);
                    }
                    if (spacerInPixels3 != null) {
                        setMargins$default(this, this, -1, 0, null, null, null, spacerInPixels3, 60, null);
                    }
                    if (spacerInPixels4 != null && isTabletDevice()) {
                        setMargins$default(this, baseButton, 0, 0, null, null, spacerInPixels4, null, 94, null);
                    }
                    addToContainer(baseButton, !isTabletDevice() ? getRightActionIconContainer() : getActionIconContainer());
                    return;
                }
                addToContainer(baseButton, getActionIconContainer());
                return;
            }
            LinearLayout actionIconContainer = getActionIconContainer();
            if (!(actionIconContainer != null && actionIconContainer.getChildCount() == 0)) {
                setMargins$default(this, baseButton, 0, 0, this.styleSheet.getSpacerInPixels(SpacerKey.MINI), null, null, null, 118, null);
            }
            addToContainer(baseButton, getActionIconContainer());
        }
    }

    private final void addToContainer(BaseButton button, LinearLayout iconContainer) {
        if (button != null && button.getParent() != null) {
            ViewParent parent = button.getParent();
            ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
            if (viewGroup != null) {
                viewGroup.removeView(button);
            }
        }
        if (iconContainer != null) {
            iconContainer.addView(button);
        }
        if (iconContainer == null) {
            return;
        }
        iconContainer.setVisibility(iconContainer.getChildCount() == 0 ? 8 : 0);
    }

    public final void addBadgeIcons(List<String> encodings) {
        String string;
        Intrinsics.checkNotNullParameter(encodings, "encodings");
        if (encodings.contains("uhdAvailable")) {
            string = getContext().getResources().getString(R$string.katana_uhd_audio_badge);
        } else {
            string = encodings.contains("hdAvailable") ? getContext().getResources().getString(R$string.katana_hd_audio_badge) : "";
        }
        Intrinsics.checkNotNullExpressionValue(string, "when {\n            encod…     else -> \"\"\n        }");
        setupStereoAudioQualityBadge(string);
        if (encodings.contains("atmosAvailable")) {
            setupSpatialAudioQualityBadge(getContext().getResources().getString(R$string.ivy_atmos_badge));
        }
        if (encodings.contains("ra360Available")) {
            setupSpatialAudioQualityBadge(getContext().getResources().getString(R$string.ivy_ra360_badge));
        }
    }

    private final void setupStereoAudioQualityBadge(String badgeText) {
        BaseButton badge = getBadge(badgeText);
        if (badge != null) {
            badge.setId(R$id.KatanaBadge);
        }
        if (badge == null) {
            return;
        }
        buildBadge(badge);
    }

    private final void setupSpatialAudioQualityBadge(String badgeText) {
        BaseButton badge = getBadge(badgeText);
        if (badge != null) {
            badge.setId(R$id.ImmersiveBadge);
        }
        if (badge == null) {
            return;
        }
        StyleSheet styleSheet = getStyleSheet();
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        new BadgeCreationHelper(styleSheet, context).setSpatialBadgeText(badgeText, badge, getBadgeBuilder(badgeText));
        buildBadge(badge);
    }

    private final BaseButton getBadge(String badgeText) {
        BaseButton.StyleBuilder badgeBuilder = getBadgeBuilder(badgeText);
        if (badgeBuilder == null) {
            return null;
        }
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        return badgeBuilder.build(context);
    }

    private final BaseButton.StyleBuilder getBadgeBuilder(String badgeText) {
        BaseButton.StyleBuilder tagBuilder;
        if (badgeText == null || (tagBuilder = this.styleSheet.getTagBuilder(TagSizeKey.MEDIUM, TagStyleKey.GLASS_PRIMARY)) == null) {
            return null;
        }
        return tagBuilder.withText(badgeText);
    }

    private final void buildBadge(BaseButton badge) {
        Integer spacerInPixels;
        LinearLayout badgeIconContainer = getBadgeIconContainer();
        if (badgeIconContainer != null) {
            if (badgeIconContainer.getChildCount() > 0 && (spacerInPixels = getStyleSheet().getSpacerInPixels(SpacerKey.SMALL)) != null) {
                int iIntValue = spacerInPixels.intValue();
                ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(-2, -2);
                ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = iIntValue;
                badge.setLayoutParams(layoutParams);
            }
            badgeIconContainer.addView(badge);
            badge.setEnabled(isEnabled());
        }
        LinearLayout badgeIconContainer2 = getBadgeIconContainer();
        if (badgeIconContainer2 == null) {
            return;
        }
        LinearLayout badgeIconContainer3 = getBadgeIconContainer();
        badgeIconContainer2.setVisibility(badgeIconContainer3 != null && badgeIconContainer3.getChildCount() == 0 ? 8 : 0);
    }

    public void clear() {
        LinearLayout actionIconContainer = getActionIconContainer();
        if (actionIconContainer != null) {
            actionIconContainer.setVisibility(8);
        }
        LinearLayout actionIconContainer2 = getActionIconContainer();
        if (actionIconContainer2 != null) {
            actionIconContainer2.removeAllViews();
        }
        LinearLayout rightActionIconContainer = getRightActionIconContainer();
        if (rightActionIconContainer != null) {
            rightActionIconContainer.setVisibility(8);
        }
        LinearLayout rightActionIconContainer2 = getRightActionIconContainer();
        if (rightActionIconContainer2 != null) {
            rightActionIconContainer2.removeAllViews();
        }
        LinearLayout badgeIconContainer = getBadgeIconContainer();
        if (badgeIconContainer != null) {
            badgeIconContainer.setVisibility(8);
        }
        LinearLayout badgeIconContainer2 = getBadgeIconContainer();
        if (badgeIconContainer2 != null) {
            badgeIconContainer2.removeAllViews();
        }
        setLabelText("");
        setHeadlineText("", false);
        setPrimaryText("");
        setSecondaryText("");
        setTertiaryText("");
    }

    public final void clearActionIconContainer() {
        LinearLayout actionIconContainer = getActionIconContainer();
        if (actionIconContainer != null) {
            actionIconContainer.removeAllViewsInLayout();
        }
        LinearLayout rightActionIconContainer = getRightActionIconContainer();
        if (rightActionIconContainer == null) {
            return;
        }
        rightActionIconContainer.removeAllViewsInLayout();
    }

    public final void updateFollowState(Boolean isFollowed) {
        if (isFollowed == null) {
            return;
        }
        isFollowed.booleanValue();
        BaseButton baseButton = this.followButton;
        if (baseButton == null) {
            return;
        }
        baseButton.setVisibility(isFollowed.booleanValue() ? 8 : 0);
    }

    public final void updateFavoriteState(Boolean isFollowed) {
        if (isFollowed == null) {
            return;
        }
        isFollowed.booleanValue();
        if (isFollowed.booleanValue()) {
            Drawable favoriteOnDrawable = getFavoriteOnDrawable();
            if (favoriteOnDrawable != null) {
                favoriteOnDrawable.setColorFilter(getResources().getColor(R$color.cyan_accent), PorterDuff.Mode.MULTIPLY);
            }
            BaseButton baseButton = this.favoriteButton;
            if (baseButton == null) {
                return;
            }
            baseButton.setIcon(getFavoriteOnDrawable());
            return;
        }
        BaseButton baseButton2 = this.favoriteButton;
        if (baseButton2 == null) {
            return;
        }
        baseButton2.setIcon(getFavoriteDrawable());
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        clear();
        init();
    }

    public static /* synthetic */ void setMargins$default(PageHeaderView pageHeaderView, View view, int i, int i2, Integer num, Integer num2, Integer num3, Integer num4, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setMargins");
        }
        pageHeaderView.setMargins(view, (i3 & 2) != 0 ? -2 : i, (i3 & 4) == 0 ? i2 : -2, (i3 & 8) != 0 ? null : num, (i3 & 16) != 0 ? null : num2, (i3 & 32) != 0 ? null : num3, (i3 & 64) == 0 ? num4 : null);
    }

    protected final void setMargins(View view, int width, int height, Integer left, Integer top, Integer right, Integer bottom) {
        if (view == null) {
            return;
        }
        LayoutParamUtils.INSTANCE.setLayoutMargins(view, width, height, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
    }

    private final void setText(TextView view, String text) {
        if (TextUtils.isEmpty(text)) {
            if (view == null) {
                return;
            }
            view.setVisibility(8);
        } else {
            if (view != null) {
                view.setText(text);
            }
            if (view == null) {
                return;
            }
            view.setVisibility(0);
        }
    }

    private final void applyFontStyle(TextView view, FontStyleKey fontStyleKey) {
        FontStyle fontStyle;
        if (view == null || (fontStyle = this.styleSheet.getFontStyle(fontStyleKey)) == null) {
            return;
        }
        FontUtil.INSTANCE.applyFontStyle(view, fontStyle);
    }

    private final BaseButton getButtonWithDrawable(Drawable drawable) {
        BaseButton.StyleBuilder iconButtonBuilder;
        BaseButton.StyleBuilder styleBuilderWithIcon;
        if (drawable == null || (iconButtonBuilder = getIconButtonBuilder()) == null || (styleBuilderWithIcon = iconButtonBuilder.withIcon(drawable)) == null) {
            return null;
        }
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        return styleBuilderWithIcon.build(context);
    }

    private final BaseButton getAddButton(Drawable drawable, boolean asLargeButton, String largeButtonText) {
        BaseButton.StyleBuilder styleBuilderWithIcon;
        BaseButton.StyleBuilder accentButtonBuilder;
        BaseButton.StyleBuilder styleBuilderWithIcon2;
        BaseButton.StyleBuilder styleBuilderWithText;
        if (drawable == null) {
            return null;
        }
        if (!asLargeButton) {
            BaseButton.StyleBuilder iconButtonBuilder = getIconButtonBuilder();
            if (iconButtonBuilder == null || (styleBuilderWithIcon = iconButtonBuilder.withIcon(drawable)) == null) {
                return null;
            }
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "context");
            return styleBuilderWithIcon.build(context);
        }
        if (largeButtonText == null || (accentButtonBuilder = getAccentButtonBuilder()) == null || (styleBuilderWithIcon2 = accentButtonBuilder.withIcon(drawable)) == null || (styleBuilderWithText = styleBuilderWithIcon2.withText(largeButtonText)) == null) {
            return null;
        }
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "context");
        return styleBuilderWithText.build(context2);
    }

    private final BaseButton getPlayButton(Drawable drawable) {
        BaseButton.StyleBuilder accentIconButtonBuilder;
        BaseButton.StyleBuilder styleBuilderWithIcon;
        if (drawable == null || (accentIconButtonBuilder = getAccentIconButtonBuilder()) == null || (styleBuilderWithIcon = accentIconButtonBuilder.withIcon(drawable)) == null) {
            return null;
        }
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        return styleBuilderWithIcon.build(context);
    }

    private final BaseButton getStationButton(Drawable drawable, boolean asLargeButton, String largeButtonText) {
        BaseButton.StyleBuilder styleBuilderWithIcon;
        BaseButton.StyleBuilder styleBuilderWithIcon2;
        BaseButton.StyleBuilder styleBuilderWithText;
        if (largeButtonText == null) {
            largeButtonText = getResources().getString(R$string.dmusic_detail_page_start_station);
            Intrinsics.checkNotNullExpressionValue(largeButtonText, "resources.getString(R.st…etail_page_start_station)");
        }
        if (drawable == null) {
            return null;
        }
        if (asLargeButton) {
            BaseButton.StyleBuilder largeButtonBuilder = getLargeButtonBuilder();
            if (largeButtonBuilder == null || (styleBuilderWithIcon2 = largeButtonBuilder.withIcon(drawable)) == null || (styleBuilderWithText = styleBuilderWithIcon2.withText(largeButtonText)) == null) {
                return null;
            }
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "context");
            return styleBuilderWithText.build(context);
        }
        BaseButton.StyleBuilder iconButtonBuilder = getIconButtonBuilder();
        if (iconButtonBuilder == null || (styleBuilderWithIcon = iconButtonBuilder.withIcon(drawable)) == null) {
            return null;
        }
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "context");
        return styleBuilderWithIcon.build(context2);
    }

    private final BaseButton getShuffleButton(Drawable drawable, boolean asLargeButton) {
        BaseButton.StyleBuilder styleBuilderWithIcon;
        BaseButton baseButtonBuild;
        BaseButton.StyleBuilder styleBuilderWithIcon2;
        if (drawable == null) {
            return null;
        }
        if (asLargeButton) {
            BaseButton.StyleBuilder largeButtonBuilder = getLargeButtonBuilder();
            if (largeButtonBuilder == null || (styleBuilderWithIcon2 = largeButtonBuilder.withIcon(drawable)) == null) {
                return null;
            }
            String string = getResources().getString(R$string.content_description_shuffle_button);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…scription_shuffle_button)");
            BaseButton.StyleBuilder styleBuilderWithText = styleBuilderWithIcon2.withText(string);
            if (styleBuilderWithText == null) {
                return null;
            }
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "context");
            baseButtonBuild = styleBuilderWithText.build(context);
        } else {
            BaseButton.StyleBuilder iconButtonBuilder = getIconButtonBuilder();
            if (iconButtonBuilder == null || (styleBuilderWithIcon = iconButtonBuilder.withIcon(drawable)) == null) {
                return null;
            }
            Context context2 = getContext();
            Intrinsics.checkNotNullExpressionValue(context2, "context");
            baseButtonBuild = styleBuilderWithIcon.build(context2);
        }
        return baseButtonBuild;
    }

    protected final boolean isTabletDevice() {
        int i = WhenMappings.$EnumSwitchMapping$2[this.styleSheet.getWidthScreenSizeKey().ordinal()];
        return (i == 1 || i == 2 || i == 3) ? false : true;
    }
}
