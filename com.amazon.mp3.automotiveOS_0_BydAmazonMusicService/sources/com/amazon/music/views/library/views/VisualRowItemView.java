package com.amazon.music.views.library.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.MotionEventCompat;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.models.DownloadStateModel;
import com.amazon.music.views.library.recyclerview.DraggableItemView;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.utils.DurationStringUtil;
import com.amazon.music.views.library.views.TableRowItemView;
import com.amazon.music.views.library.views.VisualRowItemRightActionButton;
import com.amazon.ui.foundations.styles.ButtonSize;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VisualRowItemView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010o\u001a\u00020pH\u0016J\b\u0010q\u001a\u00020IH\u0016J\u0006\u0010r\u001a\u00020pJ\u0006\u0010s\u001a\u00020pJ\b\u0010t\u001a\u00020pH\u0016J\u0010\u0010u\u001a\u00020p2\u0006\u0010v\u001a\u00020wH\u0016J\b\u0010x\u001a\u00020pH\u0002J0\u0010y\u001a\u00020p2\b\u0010X\u001a\u0004\u0018\u00010z2\b\u0010^\u001a\u0004\u0018\u00010z2\b\u0010\u001a\u001a\u0004\u0018\u00010z2\b\u0010i\u001a\u0004\u0018\u00010zH\u0002J\u0010\u0010{\u001a\u00020p2\u0006\u0010|\u001a\u00020\u000fH\u0016J0\u0010}\u001a\u00020p2\b\u0010X\u001a\u0004\u0018\u00010z2\b\u0010^\u001a\u0004\u0018\u00010z2\b\u0010\u001a\u001a\u0004\u0018\u00010z2\b\u0010i\u001a\u0004\u0018\u00010zH\u0002J&\u0010~\u001a\u00020p2\b\u0010X\u001a\u0004\u0018\u00010z2\b\u0010^\u001a\u0004\u0018\u00010z2\b\u0010i\u001a\u0004\u0018\u00010zH\u0002J\u001c\u0010\u007f\u001a\u00020p2\b\u0010X\u001a\u0004\u0018\u00010z2\b\u0010^\u001a\u0004\u0018\u00010zH\u0002J1\u0010\u0080\u0001\u001a\u00020p2\b\u0010X\u001a\u0004\u0018\u00010z2\b\u0010^\u001a\u0004\u0018\u00010z2\b\u0010\u001a\u001a\u0004\u0018\u00010z2\b\u0010i\u001a\u0004\u0018\u00010zH\u0002J\t\u0010\u0081\u0001\u001a\u00020pH\u0002J\t\u0010\u0082\u0001\u001a\u00020pH\u0002J\t\u0010\u0083\u0001\u001a\u00020pH\u0002J\t\u0010\u0084\u0001\u001a\u00020\u000fH\u0016J\u001d\u0010\u0085\u0001\u001a\u00020p2\b\u0010X\u001a\u0004\u0018\u00010z2\b\u0010^\u001a\u0004\u0018\u00010zH\u0002J\u0007\u0010\u0086\u0001\u001a\u00020pJ\u0011\u0010\u0087\u0001\u001a\u00020p2\u0006\u0010\u001a\u001a\u00020zH\u0002J\u0011\u0010\u0088\u0001\u001a\u00020p2\u0006\u0010i\u001a\u00020zH\u0002JL\u0010\u0089\u0001\u001a\u00020p2\u0007\u0010\u008a\u0001\u001a\u00020I2\u000b\b\u0002\u0010\u008b\u0001\u001a\u0004\u0018\u00010h2\u000b\b\u0002\u0010\u008c\u0001\u001a\u0004\u0018\u00010h2\u000b\b\u0002\u0010\u008d\u0001\u001a\u0004\u0018\u00010h2\u000b\b\u0002\u0010\u008e\u0001\u001a\u0004\u0018\u00010hH\u0002¢\u0006\u0003\u0010\u008f\u0001J\u0010\u0010\u0090\u0001\u001a\u00020p2\u0007\u0010\u0091\u0001\u001a\u00020hJ\u0012\u0010\u0092\u0001\u001a\u00020p2\u0007\u0010\u0093\u0001\u001a\u00020\u000fH\u0016J\t\u0010\u0094\u0001\u001a\u00020pH\u0002J\u001b\u0010\u0095\u0001\u001a\u00020p2\u0010\u0010\u0096\u0001\u001a\u000b\u0012\u0004\u0012\u00020p\u0018\u00010\u0097\u0001H\u0016J\u0010\u0010\u0098\u0001\u001a\u00020p2\u0007\u0010\u0099\u0001\u001a\u00020hJ\u0013\u0010\u009a\u0001\u001a\u00020p2\b\u0010U\u001a\u0004\u0018\u00010zH\u0002J\u0010\u0010\u009b\u0001\u001a\u00020p2\u0007\u0010\u009c\u0001\u001a\u00020\u000fJ\u0011\u0010\u009d\u0001\u001a\u00020p2\b\u0010\u009e\u0001\u001a\u00030\u009f\u0001JC\u0010 \u0001\u001a\u00020p2\b\u0010U\u001a\u0004\u0018\u00010z2\u0006\u00104\u001a\u00020z2\b\u0010X\u001a\u0004\u0018\u00010z2\b\u0010^\u001a\u0004\u0018\u00010z2\b\u0010\u001a\u001a\u0004\u0018\u00010z2\b\u0010i\u001a\u0004\u0018\u00010zH\u0016J\u0012\u0010¡\u0001\u001a\u00020p2\u0007\u0010¢\u0001\u001a\u00020\u000fH\u0016J\u0007\u0010£\u0001\u001a\u00020pJ\u0012\u0010¤\u0001\u001a\u00020p2\u0007\u0010¥\u0001\u001a\u00020\u000fH\u0002J\u0007\u0010¦\u0001\u001a\u00020pJ\u001c\u0010§\u0001\u001a\u00020p2\b\u0010¨\u0001\u001a\u00030©\u00012\u0007\u0010\u0093\u0001\u001a\u00020\u000fH\u0002R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR$\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001b\u0010\u0015\u001a\u00020\u00168@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\r\u001a\u0004\b\u0017\u0010\u0018R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\r\u001a\u0004\b\u001c\u0010\u001dR\u001b\u0010\u001f\u001a\u00020 8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b#\u0010\r\u001a\u0004\b!\u0010\"R\u001b\u0010$\u001a\u00020%8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b(\u0010\r\u001a\u0004\b&\u0010'R\u001b\u0010)\u001a\u00020%8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b+\u0010\r\u001a\u0004\b*\u0010'R\u001b\u0010,\u001a\u00020%8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b.\u0010\r\u001a\u0004\b-\u0010'R\u0014\u0010/\u001a\u00020%8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b0\u0010'R\u001b\u00101\u001a\u00020%8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b3\u0010\r\u001a\u0004\b2\u0010'R\u001b\u00104\u001a\u00020\u001b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b6\u0010\r\u001a\u0004\b5\u0010\u001dR\u001b\u00107\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b9\u0010\r\u001a\u0004\b8\u0010\u000bR\u001b\u0010:\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b<\u0010\r\u001a\u0004\b;\u0010\u000bR\u001b\u0010=\u001a\u00020 8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b?\u0010\r\u001a\u0004\b>\u0010\"R\u001b\u0010@\u001a\u00020A8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bD\u0010\r\u001a\u0004\bB\u0010CR\u001b\u0010E\u001a\u00020%8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bG\u0010\r\u001a\u0004\bF\u0010'R\u001b\u0010H\u001a\u00020I8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bL\u0010\r\u001a\u0004\bJ\u0010KR\u001b\u0010M\u001a\u00020%8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bO\u0010\r\u001a\u0004\bN\u0010'R\u001b\u0010P\u001a\u00020Q8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bT\u0010\r\u001a\u0004\bR\u0010SR\u001b\u0010U\u001a\u00020\u001b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bW\u0010\r\u001a\u0004\bV\u0010\u001dR\u001b\u0010X\u001a\u00020\u001b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bZ\u0010\r\u001a\u0004\bY\u0010\u001dR\u001b\u0010[\u001a\u00020\u001b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b]\u0010\r\u001a\u0004\b\\\u0010\u001dR\u001b\u0010^\u001a\u00020\u001b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b`\u0010\r\u001a\u0004\b_\u0010\u001dR\u001b\u0010a\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bc\u0010\r\u001a\u0004\bb\u0010\u000bR\u001b\u0010d\u001a\u00020 8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bf\u0010\r\u001a\u0004\be\u0010\"R\u000e\u0010g\u001a\u00020hX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010i\u001a\u00020\u001b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bk\u0010\r\u001a\u0004\bj\u0010\u001dR\u001a\u0010l\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bm\u0010\u0012\"\u0004\bn\u0010\u0014¨\u0006ª\u0001"}, d2 = {"Lcom/amazon/music/views/library/views/VisualRowItemView;", "Lcom/amazon/music/views/library/views/TableRowItemView;", "Lcom/amazon/music/views/library/recyclerview/DraggableItemView;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "badgingContainer", "Landroid/widget/LinearLayout;", "getBadgingContainer", "()Landroid/widget/LinearLayout;", "badgingContainer$delegate", "Lkotlin/Lazy;", "value", "", "canDrag", "getCanDrag", "()Z", "setCanDrag", "(Z)V", "contentView", "Landroidx/constraintlayout/widget/ConstraintLayout;", "getContentView$DMMViewLibrary_release", "()Landroidx/constraintlayout/widget/ConstraintLayout;", "contentView$delegate", "date", "Landroid/widget/TextView;", "getDate", "()Landroid/widget/TextView;", "date$delegate", "downloadButtonContainer", "Landroid/widget/FrameLayout;", "getDownloadButtonContainer", "()Landroid/widget/FrameLayout;", "downloadButtonContainer$delegate", "downloadButtonIcon", "Lcom/amazon/ui/foundations/views/BaseButton;", "getDownloadButtonIcon", "()Lcom/amazon/ui/foundations/views/BaseButton;", "downloadButtonIcon$delegate", "draggableIcon", "getDraggableIcon", "draggableIcon$delegate", "explicitIcon", "getExplicitIcon", "explicitIcon$delegate", "missingSDCardIcon", "getMissingSDCardIcon", "overflowIcon", "getOverflowIcon", "overflowIcon$delegate", ContextMappingConstants.PRIMARY_TEXT, "getPrimaryText", "primaryText$delegate", "primaryTextBadgingContainer", "getPrimaryTextBadgingContainer", "primaryTextBadgingContainer$delegate", "primaryTextContainer", "getPrimaryTextContainer", "primaryTextContainer$delegate", "primaryTextDownloadBadgeContainer", "getPrimaryTextDownloadBadgeContainer", "primaryTextDownloadBadgeContainer$delegate", "rightActionButton", "Lcom/amazon/music/views/library/views/VisualRowItemRightActionButton;", "getRightActionButton", "()Lcom/amazon/music/views/library/views/VisualRowItemRightActionButton;", "rightActionButton$delegate", "rowButtonClickedImageView", "getRowButtonClickedImageView", "rowButtonClickedImageView$delegate", "rowButtonContainerView", "Landroid/view/View;", "getRowButtonContainerView", "()Landroid/view/View;", "rowButtonContainerView$delegate", "rowButtonView", "getRowButtonView", "rowButtonView$delegate", "rowImage", "Lcom/amazon/music/views/library/views/ArtworkFrameView;", "getRowImage", "()Lcom/amazon/music/views/library/views/ArtworkFrameView;", "rowImage$delegate", "rowNumber", "getRowNumber", "rowNumber$delegate", "secondaryText1", "getSecondaryText1", "secondaryText1$delegate", "secondaryText1Xlg", "getSecondaryText1Xlg", "secondaryText1Xlg$delegate", "secondaryText2", "getSecondaryText2", "secondaryText2$delegate", "secondaryTextContainer", "getSecondaryTextContainer", "secondaryTextContainer$delegate", "secondaryTextDownloadBadgeContainer", "getSecondaryTextDownloadBadgeContainer", "secondaryTextDownloadBadgeContainer$delegate", "smallRowNumberTextSize", "", "time", "getTime", "time$delegate", "wasOriginallySuggestionView", "getWasOriginallySuggestionView", "setWasOriginallySuggestionView", "cleanViews", "", "getContentView", "hideForSuggestion", "hideRowNumber", "init", "initDownloadIndicator", "state", "Lcom/amazon/music/views/library/models/DownloadStateModel$DownloadState;", "initDraggableIcon", "initDynamicTextCombination", "", "initExplicitIcon", "isShown", "initTextForLgScreen", "initTextForMedScreen", "initTextForSmallScreen", "initTextForXlgScreen", "initTextStyling", "initViewMargins", "initViewVisibility", "isDraggingEnabled", "mergeAndSetSecondaryText", "resetRowButton", "setAndDisplayDate", "setAndDisplayTime", "setContentMargins", "view", "left", "top", "right", "bottom", "(Landroid/view/View;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "setDateVisibility", "visibility", "setEnabled", "enabled", "setInitialDimens", "setOnDraggedListener", "onDragged", "Lkotlin/Function0;", "setPrimaryTextId", "id", "setRowNumber", "setSecondaryTextContainerVisibility", "isVisible", "setStationOnClickedListener", "onClickListener", "Landroid/view/View$OnClickListener;", "setText", "showSDCardIcon", "show", "showShuffleIcon", "showShuffleOrStationIcon", "isShuffle", "showStationIcon", "updateEnabledStateForViewGroup", "viewGroup", "Landroid/view/ViewGroup;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VisualRowItemView extends TableRowItemView implements DraggableItemView {

    /* JADX INFO: renamed from: badgingContainer$delegate, reason: from kotlin metadata */
    private final Lazy badgingContainer;
    private boolean canDrag;

    /* JADX INFO: renamed from: contentView$delegate, reason: from kotlin metadata */
    private final Lazy contentView;

    /* JADX INFO: renamed from: date$delegate, reason: from kotlin metadata */
    private final Lazy date;

    /* JADX INFO: renamed from: downloadButtonContainer$delegate, reason: from kotlin metadata */
    private final Lazy downloadButtonContainer;

    /* JADX INFO: renamed from: downloadButtonIcon$delegate, reason: from kotlin metadata */
    private final Lazy downloadButtonIcon;

    /* JADX INFO: renamed from: draggableIcon$delegate, reason: from kotlin metadata */
    private final Lazy draggableIcon;

    /* JADX INFO: renamed from: explicitIcon$delegate, reason: from kotlin metadata */
    private final Lazy explicitIcon;

    /* JADX INFO: renamed from: overflowIcon$delegate, reason: from kotlin metadata */
    private final Lazy overflowIcon;

    /* JADX INFO: renamed from: primaryText$delegate, reason: from kotlin metadata */
    private final Lazy primaryText;

    /* JADX INFO: renamed from: primaryTextBadgingContainer$delegate, reason: from kotlin metadata */
    private final Lazy primaryTextBadgingContainer;

    /* JADX INFO: renamed from: primaryTextContainer$delegate, reason: from kotlin metadata */
    private final Lazy primaryTextContainer;

    /* JADX INFO: renamed from: primaryTextDownloadBadgeContainer$delegate, reason: from kotlin metadata */
    private final Lazy primaryTextDownloadBadgeContainer;

    /* JADX INFO: renamed from: rightActionButton$delegate, reason: from kotlin metadata */
    private final Lazy rightActionButton;

    /* JADX INFO: renamed from: rowButtonClickedImageView$delegate, reason: from kotlin metadata */
    private final Lazy rowButtonClickedImageView;

    /* JADX INFO: renamed from: rowButtonContainerView$delegate, reason: from kotlin metadata */
    private final Lazy rowButtonContainerView;

    /* JADX INFO: renamed from: rowButtonView$delegate, reason: from kotlin metadata */
    private final Lazy rowButtonView;

    /* JADX INFO: renamed from: rowImage$delegate, reason: from kotlin metadata */
    private final Lazy rowImage;

    /* JADX INFO: renamed from: rowNumber$delegate, reason: from kotlin metadata */
    private final Lazy rowNumber;

    /* JADX INFO: renamed from: secondaryText1$delegate, reason: from kotlin metadata */
    private final Lazy secondaryText1;

    /* JADX INFO: renamed from: secondaryText1Xlg$delegate, reason: from kotlin metadata */
    private final Lazy secondaryText1Xlg;

    /* JADX INFO: renamed from: secondaryText2$delegate, reason: from kotlin metadata */
    private final Lazy secondaryText2;

    /* JADX INFO: renamed from: secondaryTextContainer$delegate, reason: from kotlin metadata */
    private final Lazy secondaryTextContainer;

    /* JADX INFO: renamed from: secondaryTextDownloadBadgeContainer$delegate, reason: from kotlin metadata */
    private final Lazy secondaryTextDownloadBadgeContainer;
    private final int smallRowNumberTextSize;

    /* JADX INFO: renamed from: time$delegate, reason: from kotlin metadata */
    private final Lazy time;
    private boolean wasOriginallySuggestionView;

    /* JADX INFO: compiled from: VisualRowItemView.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TableRowItemView.SIZE.values().length];
            iArr[TableRowItemView.SIZE.SMALL.ordinal()] = 1;
            iArr[TableRowItemView.SIZE.MEDIUM.ordinal()] = 2;
            iArr[TableRowItemView.SIZE.LARGE.ordinal()] = 3;
            iArr[TableRowItemView.SIZE.XLARGE.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VisualRowItemView(Context context, StyleSheet styleSheet) {
        super(context, styleSheet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.rowNumber = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.VisualRowItemView$rowNumber$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.row_number);
            }
        });
        this.primaryText = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.VisualRowItemView$primaryText$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.TrackTitle);
            }
        });
        this.secondaryText1 = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.VisualRowItemView$secondaryText1$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.secondary_text1);
            }
        });
        this.secondaryText2 = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.VisualRowItemView$secondaryText2$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.secondary_text2);
            }
        });
        this.secondaryText1Xlg = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.VisualRowItemView$secondaryText1Xlg$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.secondary_text1_xlg);
            }
        });
        this.date = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.VisualRowItemView$date$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.date);
            }
        });
        this.time = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.VisualRowItemView$time$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.time);
            }
        });
        this.rowImage = LazyKt__LazyJVMKt.lazy(new Function0<ArtworkFrameView>() { // from class: com.amazon.music.views.library.views.VisualRowItemView$rowImage$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ArtworkFrameView invoke() {
                return (ArtworkFrameView) this.this$0.findViewById(R$id.row_image);
            }
        });
        this.overflowIcon = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.VisualRowItemView$overflowIcon$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.overflow_button_open);
            }
        });
        this.primaryTextContainer = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.music.views.library.views.VisualRowItemView$primaryTextContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R$id.primary_text_container);
            }
        });
        this.badgingContainer = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.music.views.library.views.VisualRowItemView$badgingContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R$id.badge_container);
            }
        });
        this.primaryTextDownloadBadgeContainer = LazyKt__LazyJVMKt.lazy(new Function0<FrameLayout>() { // from class: com.amazon.music.views.library.views.VisualRowItemView$primaryTextDownloadBadgeContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FrameLayout invoke() {
                return (FrameLayout) this.this$0.findViewById(R$id.primary_text_download_badge);
            }
        });
        this.secondaryTextDownloadBadgeContainer = LazyKt__LazyJVMKt.lazy(new Function0<FrameLayout>() { // from class: com.amazon.music.views.library.views.VisualRowItemView$secondaryTextDownloadBadgeContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FrameLayout invoke() {
                return (FrameLayout) this.this$0.findViewById(R$id.secondary_text_download_badge);
            }
        });
        this.draggableIcon = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.VisualRowItemView$draggableIcon$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.draggable_icon);
            }
        });
        this.explicitIcon = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.VisualRowItemView$explicitIcon$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.explicit_icon);
            }
        });
        this.rightActionButton = LazyKt__LazyJVMKt.lazy(new Function0<VisualRowItemRightActionButton>() { // from class: com.amazon.music.views.library.views.VisualRowItemView$rightActionButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final VisualRowItemRightActionButton invoke() {
                return (VisualRowItemRightActionButton) this.this$0.findViewById(R$id.right_action_icon);
            }
        });
        this.primaryTextBadgingContainer = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.music.views.library.views.VisualRowItemView$primaryTextBadgingContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R$id.primary_text_badging_container);
            }
        });
        this.secondaryTextContainer = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.music.views.library.views.VisualRowItemView$secondaryTextContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R$id.secondary_text_container);
            }
        });
        this.contentView = LazyKt__LazyJVMKt.lazy(new Function0<ConstraintLayout>() { // from class: com.amazon.music.views.library.views.VisualRowItemView$contentView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ConstraintLayout invoke() {
                return (ConstraintLayout) this.this$0.findViewById(R$id.content_view);
            }
        });
        this.smallRowNumberTextSize = (int) getResources().getDimension(R$dimen.text_size_10);
        this.downloadButtonIcon = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.VisualRowItemView$downloadButtonIcon$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.download_button_icon);
            }
        });
        this.downloadButtonContainer = LazyKt__LazyJVMKt.lazy(new Function0<FrameLayout>() { // from class: com.amazon.music.views.library.views.VisualRowItemView$downloadButtonContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FrameLayout invoke() {
                View viewFindViewById = this.this$0.findViewById(R$id.download_button_container);
                if (viewFindViewById != null) {
                    return (FrameLayout) viewFindViewById;
                }
                throw new NullPointerException("null cannot be cast to non-null type android.widget.FrameLayout");
            }
        });
        this.rowButtonContainerView = LazyKt__LazyJVMKt.lazy(new Function0<FrameLayout>() { // from class: com.amazon.music.views.library.views.VisualRowItemView$rowButtonContainerView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FrameLayout invoke() {
                View viewFindViewById = this.this$0.findViewById(R$id.add_container);
                if (viewFindViewById != null) {
                    return (FrameLayout) viewFindViewById;
                }
                throw new NullPointerException("null cannot be cast to non-null type android.widget.FrameLayout");
            }
        });
        this.rowButtonView = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.VisualRowItemView$rowButtonView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.add_icon);
            }
        });
        this.rowButtonClickedImageView = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.VisualRowItemView$rowButtonClickedImageView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.add_complete_icon);
            }
        });
        View.inflate(context, R$layout.dmmviewlibrary_visual_row_item, this);
        init();
    }

    private final TextView getRowNumber() {
        Object value = this.rowNumber.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-rowNumber>(...)");
        return (TextView) value;
    }

    private final TextView getPrimaryText() {
        Object value = this.primaryText.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-primaryText>(...)");
        return (TextView) value;
    }

    private final TextView getSecondaryText1() {
        Object value = this.secondaryText1.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-secondaryText1>(...)");
        return (TextView) value;
    }

    private final TextView getSecondaryText2() {
        Object value = this.secondaryText2.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-secondaryText2>(...)");
        return (TextView) value;
    }

    private final TextView getSecondaryText1Xlg() {
        Object value = this.secondaryText1Xlg.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-secondaryText1Xlg>(...)");
        return (TextView) value;
    }

    private final TextView getDate() {
        Object value = this.date.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-date>(...)");
        return (TextView) value;
    }

    private final TextView getTime() {
        Object value = this.time.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-time>(...)");
        return (TextView) value;
    }

    public final ArtworkFrameView getRowImage() {
        Object value = this.rowImage.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-rowImage>(...)");
        return (ArtworkFrameView) value;
    }

    @Override // com.amazon.music.views.library.views.TableRowItemView
    public BaseButton getOverflowIcon() {
        Object value = this.overflowIcon.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-overflowIcon>(...)");
        return (BaseButton) value;
    }

    private final LinearLayout getPrimaryTextContainer() {
        Object value = this.primaryTextContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-primaryTextContainer>(...)");
        return (LinearLayout) value;
    }

    private final LinearLayout getBadgingContainer() {
        Object value = this.badgingContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-badgingContainer>(...)");
        return (LinearLayout) value;
    }

    private final FrameLayout getPrimaryTextDownloadBadgeContainer() {
        Object value = this.primaryTextDownloadBadgeContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-primaryTextDownloadBadgeContainer>(...)");
        return (FrameLayout) value;
    }

    private final FrameLayout getSecondaryTextDownloadBadgeContainer() {
        Object value = this.secondaryTextDownloadBadgeContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-secondaryTextDownloadBadgeContainer>(...)");
        return (FrameLayout) value;
    }

    private final BaseButton getDraggableIcon() {
        Object value = this.draggableIcon.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-draggableIcon>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getExplicitIcon() {
        Object value = this.explicitIcon.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-explicitIcon>(...)");
        return (BaseButton) value;
    }

    private final VisualRowItemRightActionButton getRightActionButton() {
        Object value = this.rightActionButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-rightActionButton>(...)");
        return (VisualRowItemRightActionButton) value;
    }

    private final LinearLayout getPrimaryTextBadgingContainer() {
        Object value = this.primaryTextBadgingContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-primaryTextBadgingContainer>(...)");
        return (LinearLayout) value;
    }

    private final LinearLayout getSecondaryTextContainer() {
        Object value = this.secondaryTextContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-secondaryTextContainer>(...)");
        return (LinearLayout) value;
    }

    public final ConstraintLayout getContentView$DMMViewLibrary_release() {
        Object value = this.contentView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-contentView>(...)");
        return (ConstraintLayout) value;
    }

    public final boolean getWasOriginallySuggestionView() {
        return this.wasOriginallySuggestionView;
    }

    public final void setWasOriginallySuggestionView(boolean z) {
        this.wasOriginallySuggestionView = z;
    }

    @Override // com.amazon.music.views.library.views.TableRowItemView
    public BaseButton getDownloadButtonIcon() {
        Object value = this.downloadButtonIcon.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-downloadButtonIcon>(...)");
        return (BaseButton) value;
    }

    @Override // com.amazon.music.views.library.views.TableRowItemView
    public FrameLayout getDownloadButtonContainer() {
        return (FrameLayout) this.downloadButtonContainer.getValue();
    }

    @Override // com.amazon.music.views.library.views.TableRowItemView, com.amazon.music.views.library.views.LibraryAddableView, com.amazon.music.views.library.views.AddButtonPresenter
    public View getRowButtonContainerView() {
        return (View) this.rowButtonContainerView.getValue();
    }

    @Override // com.amazon.music.views.library.views.TableRowItemView, com.amazon.music.views.library.views.LibraryAddableView, com.amazon.music.views.library.views.AddButtonPresenter
    public BaseButton getRowButtonView() {
        Object value = this.rowButtonView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-rowButtonView>(...)");
        return (BaseButton) value;
    }

    @Override // com.amazon.music.views.library.views.TableRowItemView, com.amazon.music.views.library.views.LibraryAddableView, com.amazon.music.views.library.views.AddButtonPresenter
    public BaseButton getRowButtonClickedImageView() {
        Object value = this.rowButtonClickedImageView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-rowButtonClickedImageView>(...)");
        return (BaseButton) value;
    }

    @Override // com.amazon.music.views.library.views.TableRowItemView
    public BaseButton getMissingSDCardIcon() {
        return getRightActionButton();
    }

    public final boolean getCanDrag() {
        return this.canDrag;
    }

    public final void setCanDrag(boolean z) {
        this.canDrag = z;
        if (z) {
            initDraggableIcon();
        }
        getRowNumber().setVisibility(z ? 8 : 0);
    }

    @Override // com.amazon.music.views.library.views.TableRowItemView
    public void init() {
        Integer iconSize;
        super.init();
        initViewVisibility();
        initTextStyling();
        showOverflowButton(true);
        initRowButton();
        initViewMargins();
        ButtonSize iconSize2 = getStyleSheet().getIconSize(IconSizeKey.SMALL);
        if (iconSize2 == null || (iconSize = iconSize2.getIconSize()) == null) {
            return;
        }
        int iIntValue = iconSize.intValue();
        Integer color = getStyleSheet().getColor(ColorKey.COLOR_ACCENT);
        if (color == null) {
            return;
        }
        getRowImage().setPlayNotificationIconStyle(iIntValue, color.intValue());
    }

    private final void initTextStyling() {
        TableRowItemView.Companion companion = TableRowItemView.INSTANCE;
        TableRowItemView.Companion.applyFontStyle$default(companion, getStyleSheet(), getPrimaryText(), FontStyleKey.PRIMARY, null, 8, null);
        StyleSheet styleSheet = getStyleSheet();
        TextView secondaryText1Xlg = getSecondaryText1Xlg();
        FontStyleKey fontStyleKey = FontStyleKey.SECONDARY;
        TableRowItemView.Companion.applyFontStyle$default(companion, styleSheet, secondaryText1Xlg, fontStyleKey, null, 8, null);
        TableRowItemView.Companion.applyFontStyle$default(companion, getStyleSheet(), getSecondaryText1(), fontStyleKey, null, 8, null);
        TableRowItemView.Companion.applyFontStyle$default(companion, getStyleSheet(), getSecondaryText2(), fontStyleKey, null, 8, null);
        TableRowItemView.Companion.applyFontStyle$default(companion, getStyleSheet(), getDate(), fontStyleKey, null, 8, null);
        TableRowItemView.Companion.applyFontStyle$default(companion, getStyleSheet(), getTime(), fontStyleKey, null, 8, null);
    }

    private final void initViewMargins() {
        setInitialDimens();
        TextView rowNumber = getRowNumber();
        StyleSheet styleSheet = getStyleSheet();
        SpacerKey spacerKey = SpacerKey.MINI;
        setContentMargins$default(this, (View) rowNumber, (Integer) null, (Integer) null, styleSheet.getSpacerInPixels(spacerKey), (Integer) null, 22, (Object) null);
        setContentMargins$default(this, (View) getPrimaryTextContainer(), getStyleSheet().getSpacerInPixels(SpacerKey.BASE), (Integer) null, getStyleSheet().getSpacerInPixels(getSizeClass() == TableRowItemView.SIZE.SMALL ? spacerKey : SpacerKey.LARGE), (Integer) null, 20, (Object) null);
        TextView date = getDate();
        StyleSheet styleSheet2 = getStyleSheet();
        SpacerKey spacerKey2 = SpacerKey.LARGE;
        setContentMargins$default(this, (View) date, (Integer) null, (Integer) null, styleSheet2.getSpacerInPixels(spacerKey2), (Integer) null, 22, (Object) null);
        setContentMargins$default(this, (View) getTime(), (Integer) null, (Integer) null, getStyleSheet().getSpacerInPixels(spacerKey2), (Integer) null, 22, (Object) null);
        setContentMargins$default(this, (View) getSecondaryText2(), (Integer) null, (Integer) null, getStyleSheet().getSpacerInPixels(spacerKey2), (Integer) null, 22, (Object) null);
        setContentMargins$default(this, (View) getSecondaryText1Xlg(), (Integer) null, (Integer) null, getStyleSheet().getSpacerInPixels(spacerKey2), (Integer) null, 22, (Object) null);
        setContentMargins$default(this, (View) getExplicitIcon(), getStyleSheet().getSpacerInPixels(spacerKey), (Integer) null, (Integer) null, (Integer) null, 28, (Object) null);
    }

    public final void setDateVisibility(int visibility) {
        getDate().setVisibility(visibility);
    }

    private final void initViewVisibility() {
        TableRowItemView.SIZE sizeClass = getSizeClass();
        int i = sizeClass == null ? -1 : WhenMappings.$EnumSwitchMapping$0[sizeClass.ordinal()];
        if (i == 1) {
            getDate().setVisibility(8);
            getTime().setVisibility(8);
            getSecondaryText1Xlg().setVisibility(8);
            getSecondaryText2().setVisibility(8);
            return;
        }
        if (i == 2) {
            getDate().setVisibility(8);
            getSecondaryText1Xlg().setVisibility(8);
            getSecondaryText2().setVisibility(8);
        } else {
            if (i == 3) {
                getSecondaryText1Xlg().setVisibility(8);
                getDate().setVisibility(0);
                getTime().setVisibility(0);
                getSecondaryText2().setVisibility(0);
                return;
            }
            if (i != 4) {
                return;
            }
            getSecondaryText1().setVisibility(8);
            getDate().setVisibility(0);
            getTime().setVisibility(0);
            getSecondaryText1Xlg().setVisibility(0);
            getSecondaryText2().setVisibility(0);
        }
    }

    @Override // com.amazon.music.views.library.views.TableRowItemView
    public void initDownloadIndicator(DownloadStateModel.DownloadState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        View downloadIndicatorView = getDownloadIndicatorView();
        if (downloadIndicatorView == null) {
            return;
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        if (getSizeClass() == TableRowItemView.SIZE.XLARGE) {
            layoutParams.leftMargin = (int) getResources().getDimension(R$dimen.spacer_8);
            if (getPrimaryTextDownloadBadgeContainer() != null) {
                getPrimaryTextDownloadBadgeContainer().removeView(downloadIndicatorView);
            }
            getPrimaryTextDownloadBadgeContainer().addView(downloadIndicatorView);
        } else {
            layoutParams.rightMargin = (int) getResources().getDimension(R$dimen.spacer_8);
            if (getSecondaryTextDownloadBadgeContainer() != null) {
                getSecondaryTextDownloadBadgeContainer().removeView(downloadIndicatorView);
            }
            getSecondaryTextDownloadBadgeContainer().addView(downloadIndicatorView);
        }
        downloadIndicatorView.setLayoutParams(layoutParams);
        downloadIndicatorView.setEnabled(isEnabled());
    }

    @Override // com.amazon.music.views.library.views.TableRowItemView
    public void initExplicitIcon(boolean isShown) {
        if (isShown) {
            BaseButton.StyleBuilder explicitBadgeBuilder = getExplicitBadgeBuilder();
            if (explicitBadgeBuilder != null) {
                explicitBadgeBuilder.applyStyle(getExplicitIcon());
            }
            getExplicitIcon().setVisibility(0);
            return;
        }
        getExplicitIcon().setVisibility(8);
    }

    @Override // com.amazon.music.views.library.views.TableRowItemView, android.view.View
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        getPrimaryText().setEnabled(enabled);
        getSecondaryText1().setEnabled(enabled);
        getSecondaryText2().setEnabled(enabled);
        getSecondaryText1Xlg().setEnabled(enabled);
        getDate().setEnabled(enabled);
        getTime().setEnabled(enabled);
        getRowNumber().setEnabled(enabled);
        getDraggableIcon().setEnabled(enabled);
        updateEnabledStateForViewGroup(getBadgingContainer(), enabled);
        updateEnabledStateForViewGroup(getPrimaryTextDownloadBadgeContainer(), enabled);
        updateEnabledStateForViewGroup(getSecondaryTextDownloadBadgeContainer(), enabled);
        getExplicitIcon().setEnabled(enabled);
        Float alpha = getStyleSheet().getAlpha(AlphaKey.GLASS_3);
        if (!enabled && alpha != null) {
            getRowImage().setAlpha(alpha.floatValue());
        } else {
            getRowImage().setAlpha(1.0f);
        }
    }

    public final void showShuffleIcon() {
        showShuffleOrStationIcon(true);
    }

    public final void showStationIcon() {
        showShuffleOrStationIcon(false);
    }

    private final void showShuffleOrStationIcon(boolean isShuffle) {
        VisualRowItemRightActionButton.ActionType actionType;
        if (isShuffle) {
            actionType = VisualRowItemRightActionButton.ActionType.SHUFFLE;
        } else {
            actionType = VisualRowItemRightActionButton.ActionType.STATION;
        }
        BaseButton.StyleBuilder iconBuilder = getStyleSheet().getIconBuilder(getActionIconSizeKey(), IconStyleKey.PRIMARY);
        if (iconBuilder != null) {
            getRightActionButton().show(actionType, iconBuilder);
        }
        LinearLayout primaryTextBadgingContainer = getPrimaryTextBadgingContainer();
        StyleSheet styleSheet = getStyleSheet();
        SpacerKey spacerKey = SpacerKey.LARGE;
        setContentMargins$default(this, (View) primaryTextBadgingContainer, (Integer) null, (Integer) null, styleSheet.getSpacerInPixels(spacerKey), (Integer) null, 22, (Object) null);
        setContentMargins$default(this, (View) getSecondaryTextContainer(), (Integer) null, (Integer) null, getStyleSheet().getSpacerInPixels(spacerKey), (Integer) null, 22, (Object) null);
    }

    private final void updateEnabledStateForViewGroup(ViewGroup viewGroup, boolean enabled) {
        viewGroup.setEnabled(enabled);
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            viewGroup.getChildAt(i).setEnabled(enabled);
        }
    }

    private final void initDraggableIcon() {
        BaseButton.StyleBuilder iconBuilder = getStyleSheet().getIconBuilder(IconSizeKey.TINY, IconStyleKey.PRIMARY);
        if (iconBuilder != null) {
            Drawable drawable = AppCompatResources.getDrawable(getContext(), R$drawable.ic_other_handle);
            if (drawable != null) {
                iconBuilder.withIcon(drawable);
            }
            iconBuilder.applyStyle(getDraggableIcon());
        }
        getDraggableIcon().setVisibility(0);
    }

    public final void hideRowNumber() {
        getRowNumber().setVisibility(8);
    }

    public final void hideForSuggestion() {
        getRowNumber().setVisibility(8);
        getOverflowIcon().setVisibility(8);
    }

    static /* synthetic */ void setContentMargins$default(VisualRowItemView visualRowItemView, View view, Integer num, Integer num2, Integer num3, Integer num4, int i, Object obj) {
        visualRowItemView.setContentMargins(view, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : num2, (i & 8) != 0 ? null : num3, (i & 16) != 0 ? null : num4);
    }

    private final void setContentMargins(View view, Integer left, Integer top, Integer right, Integer bottom) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ConstraintLayout.LayoutParams(-2, -2);
            view.setLayoutParams(layoutParams);
        }
        if (left != null) {
            layoutParams.leftMargin = left.intValue();
        }
        if (right != null) {
            layoutParams.rightMargin = right.intValue();
        }
        if (top != null) {
            layoutParams.topMargin = top.intValue();
        }
        if (bottom == null) {
            return;
        }
        layoutParams.bottomMargin = bottom.intValue();
    }

    private final void setInitialDimens() {
        Integer spacerInPixels = getStyleSheet().getSpacerInPixels(SpacerKey.SMALL);
        if (spacerInPixels == null) {
            return;
        }
        int iIntValue = spacerInPixels.intValue();
        setPadding(getPaddingLeft(), iIntValue, getPaddingRight(), iIntValue);
    }

    @Override // com.amazon.music.views.library.views.TableRowItemView
    public void setText(String rowNumber, String primaryText, String secondaryText1, String secondaryText2, String date, String time) {
        Intrinsics.checkNotNullParameter(primaryText, "primaryText");
        setRowNumber(rowNumber);
        getPrimaryText().setText(primaryText);
        initDynamicTextCombination(secondaryText1, secondaryText2, date, time);
    }

    private final void setRowNumber(String rowNumber) {
        if (rowNumber != null && rowNumber.length() >= 4) {
            TableRowItemView.INSTANCE.applyFontStyle(getStyleSheet(), getRowNumber(), FontStyleKey.INDEX, Integer.valueOf(this.smallRowNumberTextSize));
        } else {
            TableRowItemView.Companion.applyFontStyle$default(TableRowItemView.INSTANCE, getStyleSheet(), getRowNumber(), FontStyleKey.INDEX, null, 8, null);
        }
        getRowNumber().setText(rowNumber);
    }

    private final void initDynamicTextCombination(String secondaryText1, String secondaryText2, String date, String time) {
        TableRowItemView.SIZE sizeClass = getSizeClass();
        int i = sizeClass == null ? -1 : WhenMappings.$EnumSwitchMapping$0[sizeClass.ordinal()];
        if (i == 1) {
            initTextForSmallScreen(secondaryText1, secondaryText2);
            return;
        }
        if (i == 2) {
            initTextForMedScreen(secondaryText1, secondaryText2, time);
        } else if (i == 3) {
            initTextForLgScreen(secondaryText1, secondaryText2, date, time);
        } else {
            if (i != 4) {
                return;
            }
            initTextForXlgScreen(secondaryText1, secondaryText2, date, time);
        }
    }

    private final void initTextForSmallScreen(String secondaryText1, String secondaryText2) {
        mergeAndSetSecondaryText(secondaryText1, secondaryText2);
    }

    private final void initTextForMedScreen(String secondaryText1, String secondaryText2, String time) {
        mergeAndSetSecondaryText(secondaryText1, secondaryText2);
        if (time == null) {
            return;
        }
        setAndDisplayTime(time);
    }

    private final void initTextForLgScreen(String secondaryText1, String secondaryText2, String date, String time) {
        getSecondaryText1().setText(secondaryText1);
        getSecondaryText2().setText(secondaryText2);
        getSecondaryText2().setVisibility(0);
        if (time != null) {
            setAndDisplayTime(time);
        }
        if (date == null) {
            return;
        }
        setAndDisplayDate(date);
    }

    private final void initTextForXlgScreen(String secondaryText1, String secondaryText2, String date, String time) {
        if (time != null) {
            setAndDisplayTime(time);
        }
        if (date != null) {
            setAndDisplayDate(date);
        }
        getSecondaryText1Xlg().setText(secondaryText1);
        getSecondaryText2().setText(secondaryText2);
    }

    private final void mergeAndSetSecondaryText(String secondaryText1, String secondaryText2) {
        if (!TextUtils.isEmpty(secondaryText2)) {
            secondaryText1 = ((Object) secondaryText1) + " · " + ((Object) secondaryText2);
        }
        getSecondaryText1().setText(secondaryText1);
    }

    private final void setAndDisplayDate(String date) {
        getDate().setText(DurationStringUtil.INSTANCE.getFormattedDate(date));
        getDate().setVisibility(0);
        getDate().setWidth((int) getContext().getResources().getDimension(R$dimen.size_100));
    }

    private final void setAndDisplayTime(String time) {
        getTime().setText(DurationStringUtil.INSTANCE.getFormattedDuration(time));
        getTime().setVisibility(0);
        getTime().setWidth((int) getContext().getResources().getDimension(R$dimen.spacer_64));
    }

    public final void setPrimaryTextId(int id) {
        getPrimaryText().setId(id);
    }

    public void cleanViews() {
        getRowImage().setAlpha(1.0f);
        getRowImage().setArtworkImageDrawable(null);
        getPrimaryText().setText("");
        getSecondaryText1().setText("");
        getSecondaryText2().setText("");
        getSecondaryText1Xlg().setText("");
        setRowNumber("");
        getDate().setText("");
        getTime().setText("");
        getBadgingContainer().removeAllViews();
        getPrimaryTextDownloadBadgeContainer().removeAllViews();
        getSecondaryTextDownloadBadgeContainer().removeAllViews();
        getDraggableIcon().setVisibility(8);
        getExplicitIcon().setVisibility(8);
        getRightActionButton().reset();
        initViewVisibility();
    }

    @Override // com.amazon.music.views.library.recyclerview.DraggableItemView
    public boolean isDraggingEnabled() {
        return this.canDrag;
    }

    @Override // com.amazon.music.views.library.recyclerview.DraggableItemView
    public void setOnDraggedListener(final Function0<Unit> onDragged) {
        if (onDragged == null || !this.canDrag) {
            return;
        }
        getDraggableIcon().setOnTouchListener(new View.OnTouchListener() { // from class: com.amazon.music.views.library.views.VisualRowItemView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return VisualRowItemView.m2436setOnDraggedListener$lambda16(onDragged, view, motionEvent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setOnDraggedListener$lambda-16, reason: not valid java name */
    public static final boolean m2436setOnDraggedListener$lambda16(Function0 function0, View view, MotionEvent motionEvent) {
        if (MotionEventCompat.getActionMasked(motionEvent) != 0) {
            return false;
        }
        function0.invoke();
        return false;
    }

    public final void setStationOnClickedListener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        getRightActionButton().setOnClickListener(onClickListener);
    }

    @Override // com.amazon.music.views.library.views.TableRowItemView
    public void showSDCardIcon(boolean show) {
        BaseButton missingSDCardIcon = getMissingSDCardIcon();
        Unit unit = null;
        VisualRowItemRightActionButton visualRowItemRightActionButton = missingSDCardIcon instanceof VisualRowItemRightActionButton ? (VisualRowItemRightActionButton) missingSDCardIcon : null;
        if (visualRowItemRightActionButton != null) {
            if (show) {
                visualRowItemRightActionButton.show(VisualRowItemRightActionButton.ActionType.MISS_SD_CARD, new BaseButton.StyleBuilder());
            } else {
                visualRowItemRightActionButton.hide(VisualRowItemRightActionButton.ActionType.MISS_SD_CARD);
            }
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            initMissingSDCardIcon();
            super.showSDCardIcon(show);
        }
    }

    public final void setSecondaryTextContainerVisibility(boolean isVisible) {
        if (isVisible) {
            LinearLayout secondaryTextContainer = getSecondaryTextContainer();
            if (secondaryTextContainer == null) {
                return;
            }
            secondaryTextContainer.setVisibility(0);
            return;
        }
        LinearLayout secondaryTextContainer2 = getSecondaryTextContainer();
        if (secondaryTextContainer2 == null) {
            return;
        }
        secondaryTextContainer2.setVisibility(8);
    }

    @Override // com.amazon.music.views.library.views.TableRowItemView, com.amazon.music.views.library.providers.NestedContentView
    public View getContentView() {
        return getContentView$DMMViewLibrary_release();
    }

    public final void resetRowButton() {
        resetRowIconForFormerSuggestions();
        this.wasOriginallySuggestionView = false;
    }
}
