package com.amazon.music.views.library.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.models.DisplayState;
import com.amazon.music.views.library.models.DownloadStateModel;
import com.amazon.music.views.library.models.SlotNumber;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.utils.DurationStringUtil;
import com.amazon.music.views.library.views.TableRowItemView;
import com.amazon.ui.foundations.styles.ButtonSize;
import com.amazon.ui.foundations.views.BaseButton;
import com.google.firebase.perf.util.Constants;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DescriptiveRowItemView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0006\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010O\u001a\u00020PH\u0016J\u000e\u0010Q\u001a\u00020P2\u0006\u0010R\u001a\u00020\u0007J\u001c\u0010S\u001a\u00020P2\b\u0010T\u001a\u0004\u0018\u00010;2\b\u0010U\u001a\u0004\u0018\u00010VH\u0002J\b\u0010W\u001a\u000202H\u0016J\b\u0010X\u001a\u00020PH\u0016J\u0010\u0010Y\u001a\u00020P2\u0006\u0010Z\u001a\u00020[H\u0016J\u0010\u0010\\\u001a\u00020P2\u0006\u0010]\u001a\u00020\u0007H\u0016J\b\u0010^\u001a\u00020PH\u0002J\b\u0010_\u001a\u00020PH\u0002J\b\u0010`\u001a\u00020PH\u0002J\b\u0010a\u001a\u00020PH\u0002J\u0010\u0010b\u001a\u00020P2\u0006\u0010c\u001a\u00020dH\u0016J\u0010\u0010e\u001a\u00020P2\u0006\u0010f\u001a\u00020\u0007H\u0016J\u0010\u0010g\u001a\u00020P2\u0006\u0010h\u001a\u00020\u0007H\u0002J\u0018\u0010i\u001a\u00020P2\u0006\u0010j\u001a\u0002022\u0006\u0010k\u001a\u00020lH\u0002J\u001d\u0010m\u001a\u00020P2\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010n\u001a\u0004\u0018\u00010o¢\u0006\u0002\u0010pJ\u000e\u0010q\u001a\u00020P2\u0006\u0010r\u001a\u00020sJ\u000e\u0010t\u001a\u00020P2\u0006\u0010r\u001a\u00020sJ$\u0010u\u001a\u00020P2\u0006\u0010v\u001a\u00020V2\b\u0010w\u001a\u0004\u0018\u00010V2\b\u0010x\u001a\u0004\u0018\u00010VH\u0002JB\u0010u\u001a\u00020P2\b\u0010y\u001a\u0004\u0018\u00010V2\u0006\u0010v\u001a\u00020V2\b\u0010w\u001a\u0004\u0018\u00010V2\b\u0010x\u001a\u0004\u0018\u00010V2\b\u0010z\u001a\u0004\u0018\u00010V2\b\u0010{\u001a\u0004\u0018\u00010VH\u0016J\u001c\u0010u\u001a\u00020P2\b\u0010{\u001a\u0004\u0018\u00010V2\b\u0010z\u001a\u0004\u0018\u00010VH\u0002J\u001f\u0010|\u001a\u00020P2\u0006\u0010j\u001a\u0002022\b\u0010}\u001a\u0004\u0018\u00010oH\u0002¢\u0006\u0002\u0010~J\u000e\u0010\u007f\u001a\u00020P2\u0006\u0010]\u001a\u00020\u0007J\u001a\u0010\u0080\u0001\u001a\u00020P2\t\u0010\u0081\u0001\u001a\u0004\u0018\u00010V2\u0006\u0010]\u001a\u00020\u0007J\t\u0010\u0082\u0001\u001a\u00020PH\u0002J\u0018\u0010\u0083\u0001\u001a\u00020P2\t\u0010B\u001a\u0005\u0018\u00010\u0084\u0001¢\u0006\u0003\u0010\u0085\u0001R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u000f\u001a\u00020\u00108@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u000e\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\u001e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u001b\u0010!\u001a\u00020\"8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b%\u0010\u000e\u001a\u0004\b#\u0010$R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010&\u001a\u00020\u001e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b'\u0010 R\u0014\u0010(\u001a\u00020\u001e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b)\u0010 R\u001b\u0010*\u001a\u00020+8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b.\u0010\u000e\u001a\u0004\b,\u0010-R\u0014\u0010/\u001a\u00020\u001e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b0\u0010 R\u0014\u00101\u001a\u0002028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b3\u00104R\u0014\u00105\u001a\u00020\u001e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b6\u0010 R\u001b\u00107\u001a\u00020\u001e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b9\u0010\u000e\u001a\u0004\b8\u0010 R\u001b\u0010:\u001a\u00020;8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b>\u0010\u000e\u001a\u0004\b<\u0010=R\u001b\u0010?\u001a\u00020;8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bA\u0010\u000e\u001a\u0004\b@\u0010=R$\u0010D\u001a\u00020C2\u0006\u0010B\u001a\u00020C@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR\u001b\u0010I\u001a\u00020\u001e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bK\u0010\u000e\u001a\u0004\bJ\u0010 R\u001b\u0010L\u001a\u00020;8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bN\u0010\u000e\u001a\u0004\bM\u0010=¨\u0006\u0086\u0001"}, d2 = {"Lcom/amazon/music/views/library/views/DescriptiveRowItemView;", "Lcom/amazon/music/views/library/views/TableRowItemView;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "isSonicRushEnabled", "", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;Z)V", "badgingContainer", "Landroid/widget/LinearLayout;", "getBadgingContainer", "()Landroid/widget/LinearLayout;", "badgingContainer$delegate", "Lkotlin/Lazy;", "contentView", "Landroidx/constraintlayout/widget/ConstraintLayout;", "getContentView$DMMViewLibrary_release", "()Landroidx/constraintlayout/widget/ConstraintLayout;", "contentView$delegate", "descriptiveDateContainer", "Lcom/amazon/music/views/library/views/DescriptiveDateContainer;", "getDescriptiveDateContainer", "()Lcom/amazon/music/views/library/views/DescriptiveDateContainer;", "descriptiveDateContainer$delegate", "downloadButtonContainer", "Landroid/widget/FrameLayout;", "getDownloadButtonContainer", "()Landroid/widget/FrameLayout;", "downloadButtonIcon", "Lcom/amazon/ui/foundations/views/BaseButton;", "getDownloadButtonIcon", "()Lcom/amazon/ui/foundations/views/BaseButton;", "headerContainer", "Lcom/amazon/music/views/library/views/DescriptiveHeaderContainer;", "getHeaderContainer", "()Lcom/amazon/music/views/library/views/DescriptiveHeaderContainer;", "headerContainer$delegate", "missingSDCardIcon", "getMissingSDCardIcon", "overflowIcon", "getOverflowIcon", "primaryTextContainer", "Lcom/amazon/music/views/library/views/DescriptivePrimaryTextContainer;", "getPrimaryTextContainer", "()Lcom/amazon/music/views/library/views/DescriptivePrimaryTextContainer;", "primaryTextContainer$delegate", "rowButtonClickedImageView", "getRowButtonClickedImageView", "rowButtonContainerView", "Landroid/view/View;", "getRowButtonContainerView", "()Landroid/view/View;", "rowButtonView", "getRowButtonView", "rowCta", "getRowCta", "rowCta$delegate", "secondaryTextView1", "Landroid/widget/TextView;", "getSecondaryTextView1", "()Landroid/widget/TextView;", "secondaryTextView1$delegate", "secondaryTextView2", "getSecondaryTextView2", "secondaryTextView2$delegate", "value", "Lcom/amazon/music/views/library/models/SlotNumber;", "slotNumber", "getSlotNumber", "()Lcom/amazon/music/views/library/models/SlotNumber;", "setSlotNumber", "(Lcom/amazon/music/views/library/models/SlotNumber;)V", "stationIcon", "getStationIcon", "stationIcon$delegate", "timeTextView", "getTimeTextView", "timeTextView$delegate", "cleanViews", "", "displayStationButton", "show", "displayText", "textView", "text", "", "getContentView", "init", "initDownloadIndicator", "state", "Lcom/amazon/music/views/library/models/DownloadStateModel$DownloadState;", "initExplicitIcon", "isShown", "initSonicRushFlags", "initStationButton", "initViewStyling", "initViewVisibility", "onDisplayStateUpdated", "displayState", "Lcom/amazon/music/views/library/models/DisplayState;", "setEnabled", Constants.ENABLE_DISABLE, "setIsPlaying", "isPlaying", "setLeftMarginInConstraintLayout", "view", "leftMarginSpacerKey", "Lcom/amazon/music/views/library/styles/keys/SpacerKey;", "setRowCta", "showRowCta", "", "(Lcom/amazon/music/views/library/styles/StyleSheet;Ljava/lang/Integer;)V", "setRowCtaOnClickedListener", "onClickListener", "Landroid/view/View$OnClickListener;", "setStationOnClickedListener", "setText", ContextMappingConstants.PRIMARY_TEXT, "secondaryText1", "secondaryText2", "rowNumber", "date", "time", "setViewHeightInConstraintLayout", "height", "(Landroid/view/View;Ljava/lang/Integer;)V", "showBadges", "showCommentary", "commentaryText", "showInstantPlaybackIcon", "showPopularity", "", "(Ljava/lang/Double;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DescriptiveRowItemView extends TableRowItemView {

    /* JADX INFO: renamed from: badgingContainer$delegate, reason: from kotlin metadata */
    private final Lazy badgingContainer;

    /* JADX INFO: renamed from: contentView$delegate, reason: from kotlin metadata */
    private final Lazy contentView;

    /* JADX INFO: renamed from: descriptiveDateContainer$delegate, reason: from kotlin metadata */
    private final Lazy descriptiveDateContainer;

    /* JADX INFO: renamed from: headerContainer$delegate, reason: from kotlin metadata */
    private final Lazy headerContainer;
    private final boolean isSonicRushEnabled;

    /* JADX INFO: renamed from: primaryTextContainer$delegate, reason: from kotlin metadata */
    private final Lazy primaryTextContainer;

    /* JADX INFO: renamed from: rowCta$delegate, reason: from kotlin metadata */
    private final Lazy rowCta;

    /* JADX INFO: renamed from: secondaryTextView1$delegate, reason: from kotlin metadata */
    private final Lazy secondaryTextView1;

    /* JADX INFO: renamed from: secondaryTextView2$delegate, reason: from kotlin metadata */
    private final Lazy secondaryTextView2;
    private SlotNumber slotNumber;

    /* JADX INFO: renamed from: stationIcon$delegate, reason: from kotlin metadata */
    private final Lazy stationIcon;

    /* JADX INFO: renamed from: timeTextView$delegate, reason: from kotlin metadata */
    private final Lazy timeTextView;

    /* JADX INFO: compiled from: DescriptiveRowItemView.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[DisplayState.values().length];
            iArr[DisplayState.PLAYING.ordinal()] = 1;
            iArr[DisplayState.PAUSED.ordinal()] = 2;
            iArr[DisplayState.INSTANT_PLAYBACK.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[TableRowItemView.SIZE.values().length];
            iArr2[TableRowItemView.SIZE.LARGE.ordinal()] = 1;
            iArr2[TableRowItemView.SIZE.XLARGE.ordinal()] = 2;
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DescriptiveRowItemView(Context context, StyleSheet styleSheet, boolean z) {
        super(context, styleSheet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.isSonicRushEnabled = z;
        this.slotNumber = SlotNumber.ONE;
        this.headerContainer = LazyKt__LazyJVMKt.lazy(new Function0<DescriptiveHeaderContainer>() { // from class: com.amazon.music.views.library.views.DescriptiveRowItemView$headerContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DescriptiveHeaderContainer invoke() {
                return (DescriptiveHeaderContainer) this.this$0.findViewById(R$id.header_container);
            }
        });
        this.primaryTextContainer = LazyKt__LazyJVMKt.lazy(new Function0<DescriptivePrimaryTextContainer>() { // from class: com.amazon.music.views.library.views.DescriptiveRowItemView$primaryTextContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DescriptivePrimaryTextContainer invoke() {
                return (DescriptivePrimaryTextContainer) this.this$0.findViewById(R$id.primary_text_container);
            }
        });
        this.secondaryTextView1 = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.DescriptiveRowItemView$secondaryTextView1$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.secondary_text1_lg);
            }
        });
        this.secondaryTextView2 = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.DescriptiveRowItemView$secondaryTextView2$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.secondary_text2_lg);
            }
        });
        this.descriptiveDateContainer = LazyKt__LazyJVMKt.lazy(new Function0<DescriptiveDateContainer>() { // from class: com.amazon.music.views.library.views.DescriptiveRowItemView$descriptiveDateContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DescriptiveDateContainer invoke() {
                return (DescriptiveDateContainer) this.this$0.findViewById(R$id.descriptive_date_container);
            }
        });
        this.timeTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.DescriptiveRowItemView$timeTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.time);
            }
        });
        this.stationIcon = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.DescriptiveRowItemView$stationIcon$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.station_icon);
            }
        });
        this.rowCta = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.DescriptiveRowItemView$rowCta$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.row_cta);
            }
        });
        this.badgingContainer = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.music.views.library.views.DescriptiveRowItemView$badgingContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R$id.badge_container);
            }
        });
        LayoutInflater.from(context).inflate(R$layout.dmmviewlibrary_descriptive_row_item, this);
        init();
        this.contentView = LazyKt__LazyJVMKt.lazy(new Function0<ConstraintLayout>() { // from class: com.amazon.music.views.library.views.DescriptiveRowItemView$contentView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ConstraintLayout invoke() {
                return (ConstraintLayout) this.this$0.findViewById(R$id.content_view);
            }
        });
    }

    public final SlotNumber getSlotNumber() {
        return this.slotNumber;
    }

    public final void setSlotNumber(SlotNumber value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.slotNumber = value;
        initViewVisibility();
    }

    private final DescriptiveHeaderContainer getHeaderContainer() {
        Object value = this.headerContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-headerContainer>(...)");
        return (DescriptiveHeaderContainer) value;
    }

    private final DescriptivePrimaryTextContainer getPrimaryTextContainer() {
        Object value = this.primaryTextContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-primaryTextContainer>(...)");
        return (DescriptivePrimaryTextContainer) value;
    }

    private final TextView getSecondaryTextView1() {
        Object value = this.secondaryTextView1.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-secondaryTextView1>(...)");
        return (TextView) value;
    }

    private final TextView getSecondaryTextView2() {
        Object value = this.secondaryTextView2.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-secondaryTextView2>(...)");
        return (TextView) value;
    }

    private final DescriptiveDateContainer getDescriptiveDateContainer() {
        Object value = this.descriptiveDateContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-descriptiveDateContainer>(...)");
        return (DescriptiveDateContainer) value;
    }

    private final TextView getTimeTextView() {
        Object value = this.timeTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-timeTextView>(...)");
        return (TextView) value;
    }

    private final BaseButton getStationIcon() {
        Object value = this.stationIcon.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-stationIcon>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getRowCta() {
        Object value = this.rowCta.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-rowCta>(...)");
        return (BaseButton) value;
    }

    private final LinearLayout getBadgingContainer() {
        Object value = this.badgingContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-badgingContainer>(...)");
        return (LinearLayout) value;
    }

    @Override // com.amazon.music.views.library.views.TableRowItemView
    public BaseButton getOverflowIcon() {
        View viewFindViewById = findViewById(R$id.overflow_icon);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(R.id.overflow_icon)");
        return (BaseButton) viewFindViewById;
    }

    @Override // com.amazon.music.views.library.views.TableRowItemView
    public BaseButton getDownloadButtonIcon() {
        View viewFindViewById = findViewById(R$id.download_button_icon);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(R.id.download_button_icon)");
        return (BaseButton) viewFindViewById;
    }

    @Override // com.amazon.music.views.library.views.TableRowItemView
    public FrameLayout getDownloadButtonContainer() {
        View viewFindViewById = findViewById(R$id.download_button_container);
        if (viewFindViewById != null) {
            return (FrameLayout) viewFindViewById;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.widget.FrameLayout");
    }

    @Override // com.amazon.music.views.library.views.TableRowItemView
    public BaseButton getMissingSDCardIcon() {
        View viewFindViewById = findViewById(R$id.missing_sd_card);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(R.id.missing_sd_card)");
        return (BaseButton) viewFindViewById;
    }

    @Override // com.amazon.music.views.library.views.TableRowItemView, com.amazon.music.views.library.views.LibraryAddableView, com.amazon.music.views.library.views.AddButtonPresenter
    public View getRowButtonContainerView() {
        View viewFindViewById = findViewById(R$id.add_container);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(R.id.add_container)");
        return viewFindViewById;
    }

    @Override // com.amazon.music.views.library.views.TableRowItemView, com.amazon.music.views.library.views.LibraryAddableView, com.amazon.music.views.library.views.AddButtonPresenter
    public BaseButton getRowButtonView() {
        View viewFindViewById = findViewById(R$id.add_icon);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(R.id.add_icon)");
        return (BaseButton) viewFindViewById;
    }

    @Override // com.amazon.music.views.library.views.TableRowItemView, com.amazon.music.views.library.views.LibraryAddableView, com.amazon.music.views.library.views.AddButtonPresenter
    public BaseButton getRowButtonClickedImageView() {
        View viewFindViewById = findViewById(R$id.add_complete_icon);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(R.id.add_complete_icon)");
        return (BaseButton) viewFindViewById;
    }

    public final ConstraintLayout getContentView$DMMViewLibrary_release() {
        Object value = this.contentView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-contentView>(...)");
        return (ConstraintLayout) value;
    }

    @Override // com.amazon.music.views.library.views.TableRowItemView
    public void setText(String rowNumber, String primaryText, String secondaryText1, String secondaryText2, String date, String time) {
        Intrinsics.checkNotNullParameter(primaryText, "primaryText");
        setText(primaryText, secondaryText1, secondaryText2);
        if (getSizeClass() != TableRowItemView.SIZE.SMALL) {
            setText(time, date);
        }
        if (SetsKt__SetsKt.setOf((Object[]) new DisplayState[]{DisplayState.PLAYING, DisplayState.PAUSED, DisplayState.INSTANT_PLAYBACK}).contains(getDisplayState())) {
            return;
        }
        getHeaderContainer().setRowNumber(rowNumber);
    }

    @Override // com.amazon.music.views.library.views.TableRowItemView
    public void init() {
        super.init();
        initViewStyling();
        showOverflowButton(true);
        initRowButton();
        initStationButton();
        initMissingSDCardIcon();
        initSonicRushFlags();
    }

    public void cleanViews() {
        getPrimaryTextContainer().cleanViews();
        getDescriptiveDateContainer().cleanViews();
        getDescriptiveDateContainer().setVisibility(8);
        getTimeTextView().setVisibility(8);
        getSecondaryTextView1().setVisibility(8);
        getSecondaryTextView2().setVisibility(8);
        getStationIcon().setVisibility(8);
        getRowCta().setVisibility(8);
        getBadgingContainer().removeAllViews();
    }

    @Override // com.amazon.music.views.library.views.TableRowItemView
    public void initDownloadIndicator(DownloadStateModel.DownloadState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        getPrimaryTextContainer().setDownloadIndicator(getDownloadIndicatorView(), state);
    }

    @Override // com.amazon.music.views.library.views.TableRowItemView
    public void initExplicitIcon(boolean isShown) {
        getPrimaryTextContainer().showExplicitIcon(isShown, getExplicitBadgeBuilder());
    }

    private final void initSonicRushFlags() {
        getPrimaryTextContainer().setSonicRushEnabled(this.isSonicRushEnabled);
        getDescriptiveDateContainer().setSonicRushEnabled(this.isSonicRushEnabled);
    }

    @Override // com.amazon.music.views.library.views.TableRowItemView
    public void onDisplayStateUpdated(DisplayState displayState) {
        Intrinsics.checkNotNullParameter(displayState, "displayState");
        super.onDisplayStateUpdated(displayState);
        int i = WhenMappings.$EnumSwitchMapping$0[displayState.ordinal()];
        if (i == 1) {
            setIsPlaying(true);
            return;
        }
        if (i == 2) {
            setIsPlaying(false);
        } else if (i == 3) {
            showInstantPlaybackIcon();
        } else {
            getHeaderContainer().showRowNumber();
            getHeaderContainer().showRowCta();
        }
    }

    public final void showCommentary(String commentaryText, boolean isShown) {
        getPrimaryTextContainer().showCommentary(commentaryText, isShown);
    }

    public final void showBadges(boolean isShown) {
        getBadgingContainer().setVisibility(isShown ? 0 : 8);
    }

    public final void showPopularity(Double value) {
        if (getSizeClass() == TableRowItemView.SIZE.SMALL) {
            getPrimaryTextContainer().showPopularityBar(value);
            getDescriptiveDateContainer().setVisibility(8);
        } else {
            getDescriptiveDateContainer().setVisibility(0);
            getDescriptiveDateContainer().showPopularityBar(value);
        }
    }

    public final void setRowCta(StyleSheet styleSheet, Integer showRowCta) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        if (showRowCta != null && showRowCta.intValue() == 3) {
            getHeaderContainer().setRowCta(styleSheet, R$drawable.ic_playback_shuffle);
        } else if (showRowCta != null && showRowCta.intValue() == 0) {
            getHeaderContainer().setRowCta(styleSheet, R$drawable.ic_playback_play);
        } else {
            getRowCta().setVisibility(8);
        }
    }

    public final void displayStationButton(boolean show) {
        if (show) {
            getStationIcon().setVisibility(0);
        } else {
            getStationIcon().setVisibility(8);
        }
    }

    private final void setIsPlaying(boolean isPlaying) {
        getHeaderContainer().showEqualizer(isPlaying);
    }

    private final void showInstantPlaybackIcon() {
        getHeaderContainer().showInstantPlaybackIcon();
    }

    private final void initViewVisibility() {
        getPrimaryTextContainer().updateView(getSizeClass(), this.slotNumber);
    }

    private final void setText(String primaryText, String secondaryText1, String secondaryText2) {
        getPrimaryTextContainer().setText(primaryText, secondaryText1, secondaryText2);
        TableRowItemView.SIZE sizeClass = getSizeClass();
        int i = sizeClass == null ? -1 : WhenMappings.$EnumSwitchMapping$1[sizeClass.ordinal()];
        if (i == 1) {
            SlotNumber slotNumber = this.slotNumber;
            if (slotNumber == SlotNumber.THREE) {
                displayText(getSecondaryTextView2(), secondaryText2);
                return;
            } else {
                if (slotNumber == SlotNumber.TWO) {
                    displayText(getSecondaryTextView1(), secondaryText1);
                    return;
                }
                return;
            }
        }
        if (i != 2) {
            return;
        }
        SlotNumber slotNumber2 = this.slotNumber;
        if (slotNumber2 == SlotNumber.TWO || slotNumber2 == SlotNumber.THREE) {
            displayText(getSecondaryTextView1(), secondaryText1);
        }
        if (this.slotNumber == SlotNumber.THREE) {
            displayText(getSecondaryTextView2(), secondaryText2);
        }
    }

    private final void setText(String time, String date) {
        Unit unit;
        if (date == null) {
            unit = null;
        } else {
            getDescriptiveDateContainer().setVisibility(0);
            getDescriptiveDateContainer().showDate(date, getStyleSheet());
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            getDescriptiveDateContainer().setVisibility(8);
        }
        getTimeTextView().setText(time != null ? DurationStringUtil.INSTANCE.getFormattedDuration(time) : null);
        getTimeTextView().setVisibility(TextUtils.isEmpty(time) ? 8 : 0);
    }

    private final void displayText(TextView textView, String text) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        if (textView != null) {
            textView.setVisibility(0);
        }
        if (textView == null) {
            return;
        }
        textView.setText(text);
    }

    private final void initViewStyling() {
        getPrimaryTextContainer().setStyleSheet(getStyleSheet());
        getHeaderContainer().initViewStyling(getStyleSheet());
        DescriptivePrimaryTextContainer primaryTextContainer = getPrimaryTextContainer();
        SpacerKey spacerKey = SpacerKey.MINI;
        setLeftMarginInConstraintLayout(primaryTextContainer, spacerKey);
        View rowButtonContainerView = getRowButtonContainerView();
        if (getSizeClass() != TableRowItemView.SIZE.SMALL) {
            spacerKey = SpacerKey.LARGE;
        }
        setLeftMarginInConstraintLayout(rowButtonContainerView, spacerKey);
        DescriptiveDateContainer descriptiveDateContainer = getDescriptiveDateContainer();
        SpacerKey spacerKey2 = SpacerKey.LARGE;
        setLeftMarginInConstraintLayout(descriptiveDateContainer, spacerKey2);
        setLeftMarginInConstraintLayout(getTimeTextView(), spacerKey2);
        setLeftMarginInConstraintLayout(getSecondaryTextView1(), spacerKey2);
        setLeftMarginInConstraintLayout(getSecondaryTextView2(), spacerKey2);
        TableRowItemView.Companion companion = TableRowItemView.INSTANCE;
        StyleSheet styleSheet = getStyleSheet();
        TextView timeTextView = getTimeTextView();
        FontStyleKey fontStyleKey = FontStyleKey.SECONDARY;
        TableRowItemView.Companion.applyFontStyle$default(companion, styleSheet, timeTextView, fontStyleKey, null, 8, null);
        TableRowItemView.Companion.applyFontStyle$default(companion, getStyleSheet(), getSecondaryTextView1(), fontStyleKey, null, 8, null);
        TableRowItemView.Companion.applyFontStyle$default(companion, getStyleSheet(), getSecondaryTextView2(), fontStyleKey, null, 8, null);
        ButtonSize iconSize = getStyleSheet().getIconSize(getActionIconSizeKey());
        if (iconSize != null) {
            setViewHeightInConstraintLayout(getHeaderContainer(), iconSize.getHeight());
            setViewHeightInConstraintLayout(getSecondaryTextView1(), iconSize.getHeight());
            setViewHeightInConstraintLayout(getSecondaryTextView2(), iconSize.getHeight());
            setViewHeightInConstraintLayout(getDescriptiveDateContainer(), iconSize.getHeight());
            setViewHeightInConstraintLayout(getTimeTextView(), iconSize.getHeight());
        }
        Integer spacerInPixels = getStyleSheet().getSpacerInPixels(SpacerKey.SMALL);
        if (spacerInPixels == null) {
            return;
        }
        int iIntValue = spacerInPixels.intValue();
        setPadding(getPaddingLeft(), iIntValue, getPaddingRight(), iIntValue);
    }

    private final void setLeftMarginInConstraintLayout(View view, SpacerKey leftMarginSpacerKey) {
        Integer spacerInPixels = getStyleSheet().getSpacerInPixels(leftMarginSpacerKey);
        if (spacerInPixels == null) {
            return;
        }
        int iIntValue = spacerInPixels.intValue();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        ConstraintLayout.LayoutParams layoutParams2 = layoutParams instanceof ConstraintLayout.LayoutParams ? (ConstraintLayout.LayoutParams) layoutParams : null;
        if (layoutParams2 == null) {
            return;
        }
        ((ViewGroup.MarginLayoutParams) layoutParams2).leftMargin = iIntValue;
    }

    private final void setViewHeightInConstraintLayout(View view, Integer height) {
        if (height == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        ConstraintLayout.LayoutParams layoutParams2 = layoutParams instanceof ConstraintLayout.LayoutParams ? (ConstraintLayout.LayoutParams) layoutParams : null;
        if (layoutParams2 == null) {
            return;
        }
        ((ViewGroup.MarginLayoutParams) layoutParams2).height = height.intValue();
    }

    @Override // com.amazon.music.views.library.views.TableRowItemView, android.view.View
    public void setEnabled(boolean isEnabled) {
        super.setEnabled(isEnabled);
        getPrimaryTextContainer().setEnabled(isEnabled);
        getDescriptiveDateContainer().setEnabled(isEnabled, getStyleSheet());
        getHeaderContainer().setEnabled(isEnabled);
        getTimeTextView().setEnabled(isEnabled);
        getSecondaryTextView1().setEnabled(isEnabled);
        getSecondaryTextView2().setEnabled(isEnabled);
        getRowCta().setEnabled(isEnabled);
    }

    private final void initStationButton() {
        BaseButton.StyleBuilder iconBuilder;
        BaseButton.StyleBuilder styleBuilderWithIcon;
        Drawable drawable = AppCompatResources.getDrawable(getContext(), R$drawable.ic_station_ring);
        if (drawable == null || (iconBuilder = getStyleSheet().getIconBuilder(getActionIconSizeKey(), IconStyleKey.PRIMARY)) == null || (styleBuilderWithIcon = iconBuilder.withIcon(drawable)) == null) {
            return;
        }
        styleBuilderWithIcon.applyStyle(getStationIcon());
    }

    public final void setStationOnClickedListener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        getStationIcon().setOnClickListener(onClickListener);
    }

    public final void setRowCtaOnClickedListener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        getRowCta().setOnClickListener(onClickListener);
    }

    @Override // com.amazon.music.views.library.views.TableRowItemView, com.amazon.music.views.library.providers.NestedContentView
    public View getContentView() {
        return getContentView$DMMViewLibrary_release();
    }
}
