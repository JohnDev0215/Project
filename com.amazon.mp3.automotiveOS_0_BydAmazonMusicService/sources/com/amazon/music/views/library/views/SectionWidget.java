package com.amazon.music.views.library.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.R$string;
import com.amazon.music.views.library.models.base.ClearableContainer;
import com.amazon.music.views.library.models.base.HotspotSupportedView;
import com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.views.HotSpotToolTipDialog;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.styles.GridSize;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import com.google.android.gms.cast.MediaTrack;
import com.google.firebase.perf.util.Constants;
import java.util.Arrays;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: SectionWidget.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0015\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010?\u001a\u00020@H\u0016J\b\u0010A\u001a\u00020BH\u0016J\b\u0010C\u001a\u0004\u0018\u00010DJ\u0016\u0010E\u001a\u00020@2\u000e\u0010F\u001a\n\u0012\u0004\u0012\u00020H\u0018\u00010GJ\b\u0010I\u001a\u00020@H\u0002J\b\u0010J\u001a\u00020@H\u0002J\u0006\u0010K\u001a\u00020@J\u0010\u0010L\u001a\u00020@2\b\u0010M\u001a\u0004\u0018\u00010DJ;\u0010N\u001a\u00020@2\n\b\u0002\u0010O\u001a\u0004\u0018\u00010P2\n\b\u0002\u0010Q\u001a\u0004\u0018\u00010P2\n\b\u0002\u0010R\u001a\u0004\u0018\u00010P2\n\b\u0002\u0010S\u001a\u0004\u0018\u00010P¢\u0006\u0002\u0010TJ\u000e\u0010U\u001a\u00020@2\u0006\u0010\u001f\u001a\u00020 J\u0010\u0010V\u001a\u00020@2\u0006\u0010W\u001a\u00020XH\u0016J5\u0010Y\u001a\u00020@2\b\u0010O\u001a\u0004\u0018\u00010P2\b\u0010Q\u001a\u0004\u0018\u00010P2\b\u0010R\u001a\u0004\u0018\u00010P2\b\u0010S\u001a\u0004\u0018\u00010PH\u0016¢\u0006\u0002\u0010TJ\u0006\u0010Z\u001a\u00020@JE\u0010[\u001a\u00020@2\n\b\u0002\u0010O\u001a\u0004\u0018\u00010P2\n\b\u0002\u0010Q\u001a\u0004\u0018\u00010P2\n\b\u0002\u0010R\u001a\u0004\u0018\u00010P2\n\b\u0002\u0010S\u001a\u0004\u0018\u00010P2\b\b\u0002\u0010\\\u001a\u00020]¢\u0006\u0002\u0010^J;\u0010_\u001a\u00020@2\n\b\u0002\u0010O\u001a\u0004\u0018\u00010P2\n\b\u0002\u0010Q\u001a\u0004\u0018\u00010P2\n\b\u0002\u0010R\u001a\u0004\u0018\u00010P2\n\b\u0002\u0010S\u001a\u0004\u0018\u00010P¢\u0006\u0002\u0010TJ\u0010\u0010`\u001a\u00020@2\b\u0010a\u001a\u0004\u0018\u00010bJ\u001c\u0010c\u001a\u00020@2\b\u0010d\u001a\u0004\u0018\u00010H2\b\b\u0002\u0010e\u001a\u00020XH\u0007J;\u0010f\u001a\u00020@2\n\b\u0002\u0010O\u001a\u0004\u0018\u00010P2\n\b\u0002\u0010Q\u001a\u0004\u0018\u00010P2\n\b\u0002\u0010R\u001a\u0004\u0018\u00010P2\n\b\u0002\u0010S\u001a\u0004\u0018\u00010P¢\u0006\u0002\u0010TJ;\u0010g\u001a\u00020@2\n\b\u0002\u0010O\u001a\u0004\u0018\u00010P2\n\b\u0002\u0010Q\u001a\u0004\u0018\u00010P2\n\b\u0002\u0010R\u001a\u0004\u0018\u00010P2\n\b\u0002\u0010S\u001a\u0004\u0018\u00010P¢\u0006\u0002\u0010TJ;\u0010h\u001a\u00020@2\n\b\u0002\u0010O\u001a\u0004\u0018\u00010P2\n\b\u0002\u0010Q\u001a\u0004\u0018\u00010P2\n\b\u0002\u0010R\u001a\u0004\u0018\u00010P2\n\b\u0002\u0010S\u001a\u0004\u0018\u00010P¢\u0006\u0002\u0010TJ\u000e\u0010i\u001a\u00020@2\u0006\u0010a\u001a\u00020bJ\u000e\u0010j\u001a\u00020@2\u0006\u0010k\u001a\u00020HJ;\u0010l\u001a\u00020@2\n\b\u0002\u0010O\u001a\u0004\u0018\u00010P2\n\b\u0002\u0010Q\u001a\u0004\u0018\u00010P2\n\b\u0002\u0010R\u001a\u0004\u0018\u00010P2\n\b\u0002\u0010S\u001a\u0004\u0018\u00010P¢\u0006\u0002\u0010TJG\u0010m\u001a\u00020@2\u0006\u0010\\\u001a\u00020]2\n\b\u0002\u0010O\u001a\u0004\u0018\u00010P2\n\b\u0002\u0010Q\u001a\u0004\u0018\u00010P2\n\b\u0002\u0010R\u001a\u0004\u0018\u00010P2\n\b\u0002\u0010S\u001a\u0004\u0018\u00010PH\u0000¢\u0006\u0004\bn\u0010oJ\u000e\u0010p\u001a\u00020@2\u0006\u0010q\u001a\u00020HJ\u000e\u0010r\u001a\u00020@2\u0006\u0010s\u001a\u00020XJ\u0010\u0010t\u001a\u00020@2\u0006\u0010u\u001a\u00020XH\u0016J\u000e\u0010v\u001a\u00020@2\u0006\u0010u\u001a\u00020XR\u001b\u0010\t\u001a\u00020\n8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0014\u001a\u00020\u00018FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u000e\u001a\u0004\b\u0015\u0010\u0016R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u001a\u001a\u00020\u001b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u000e\u001a\u0004\b\u001c\u0010\u001dR\u001c\u0010\u001f\u001a\u0004\u0018\u00010 X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001b\u0010%\u001a\u00020\u00018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b'\u0010\u000e\u001a\u0004\b&\u0010\u0016R\u0010\u0010(\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010)\u001a\u00020*8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b-\u0010\u000e\u001a\u0004\b+\u0010,R\u0010\u0010.\u001a\u0004\u0018\u00010/X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u001b\u00102\u001a\u0002038FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b6\u0010\u000e\u001a\u0004\b4\u00105R\u001b\u00107\u001a\u0002088BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b;\u0010\u000e\u001a\u0004\b9\u0010:R\u001b\u0010<\u001a\u0002038FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b>\u0010\u000e\u001a\u0004\b=\u00105¨\u0006w"}, d2 = {"Lcom/amazon/music/views/library/views/SectionWidget;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "Lcom/amazon/music/views/library/models/base/ClearableContainer;", "Lcom/amazon/music/views/library/models/base/HotspotSupportedView;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "artworkFrameLayout", "Lcom/amazon/music/views/library/views/TransparentGradientImageView;", "getArtworkFrameLayout", "()Lcom/amazon/music/views/library/views/TransparentGradientImageView;", "artworkFrameLayout$delegate", "Lkotlin/Lazy;", "contentContainer", "Landroid/widget/FrameLayout;", "getContentContainer", "()Landroid/widget/FrameLayout;", "contentContainer$delegate", "headerContainer", "getHeaderContainer", "()Landroidx/constraintlayout/widget/ConstraintLayout;", "headerContainer$delegate", "headerDrawable", "Landroid/graphics/drawable/Drawable;", "hotSpotPulseView", "Lcom/amazon/music/views/library/views/HotSpotPulse;", "getHotSpotPulseView", "()Lcom/amazon/music/views/library/views/HotSpotPulse;", "hotSpotPulseView$delegate", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "getRecyclerView", "()Landroidx/recyclerview/widget/RecyclerView;", "setRecyclerView", "(Landroidx/recyclerview/widget/RecyclerView;)V", "sectionContentContainer", "getSectionContentContainer", "sectionContentContainer$delegate", "sectionDrawable", "seeMoreButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "getSeeMoreButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "seeMoreButton$delegate", "seeMoreButtonBuilder", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "subtitleTextView", "Landroid/widget/TextView;", "getSubtitleTextView", "()Landroid/widget/TextView;", "subtitleTextView$delegate", "textViewContainer", "Landroid/widget/LinearLayout;", "getTextViewContainer", "()Landroid/widget/LinearLayout;", "textViewContainer$delegate", "titleTextView", "getTitleTextView", "titleTextView$delegate", "clearModels", "", "createTooltipView", "Lcom/amazon/music/views/library/views/HotSpotToolTipDialog;", "getAdapter", "Lcom/amazon/music/views/library/recyclerview/adapters/UniversalAdapter;", "highlightTitle", "stringsToHighlight", "", "", "initMargins", "initViewMargins", "initViewStyling", "setAdapter", "adapter", "setContentMargins", "left", "", "top", "right", "bottom", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "setContentView", "setEnabled", Constants.ENABLE_DISABLE, "", "setHotspotMargins", "setInitialMargins", "setMargins", "view", "Landroid/view/View;", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Landroid/view/View;)V", "setMoreButtonMargins", "setMoreButtonOnClickListener", "onClickListener", "Landroid/view/View$OnClickListener;", "setMoreButtonText", "moreButtonText", "shouldDisplayEmptyTextButton", "setPlaybackContainerBodyMargins", "setPlaybackContainerContentMargins", "setPlaybackContainerHeaderMargins", "setPlaybackContainerHeaderOnClickListener", "setSubtitle", MediaTrack.ROLE_SUBTITLE, "setTextMargins", "setTextViewMargins", "setTextViewMargins$DMMViewLibrary_release", "(Landroid/view/View;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "setTitle", "title", "showBackground", "shouldShowBackground", "showHotspot", "show", "showImage", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SectionWidget extends ConstraintLayout implements ClearableContainer, HotspotSupportedView {

    /* JADX INFO: renamed from: artworkFrameLayout$delegate, reason: from kotlin metadata */
    private final Lazy artworkFrameLayout;

    /* JADX INFO: renamed from: contentContainer$delegate, reason: from kotlin metadata */
    private final Lazy contentContainer;

    /* JADX INFO: renamed from: headerContainer$delegate, reason: from kotlin metadata */
    private final Lazy headerContainer;
    private Drawable headerDrawable;

    /* JADX INFO: renamed from: hotSpotPulseView$delegate, reason: from kotlin metadata */
    private final Lazy hotSpotPulseView;
    private RecyclerView recyclerView;

    /* JADX INFO: renamed from: sectionContentContainer$delegate, reason: from kotlin metadata */
    private final Lazy sectionContentContainer;
    private Drawable sectionDrawable;

    /* JADX INFO: renamed from: seeMoreButton$delegate, reason: from kotlin metadata */
    private final Lazy seeMoreButton;
    private BaseButton.StyleBuilder seeMoreButtonBuilder;
    private final StyleSheet styleSheet;

    /* JADX INFO: renamed from: subtitleTextView$delegate, reason: from kotlin metadata */
    private final Lazy subtitleTextView;

    /* JADX INFO: renamed from: textViewContainer$delegate, reason: from kotlin metadata */
    private final Lazy textViewContainer;

    /* JADX INFO: renamed from: titleTextView$delegate, reason: from kotlin metadata */
    private final Lazy titleTextView;

    @JvmOverloads
    public final void setMoreButtonText(String str) {
        setMoreButtonText$default(this, str, false, 2, null);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SectionWidget(Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.contentContainer = LazyKt__LazyJVMKt.lazy(new Function0<FrameLayout>() { // from class: com.amazon.music.views.library.views.SectionWidget$contentContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FrameLayout invoke() {
                return (FrameLayout) this.this$0.findViewById(R$id.section_content);
            }
        });
        this.textViewContainer = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.music.views.library.views.SectionWidget$textViewContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R$id.section_text_view);
            }
        });
        this.titleTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.SectionWidget$titleTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.section_title);
            }
        });
        this.subtitleTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.SectionWidget$subtitleTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.section_subtitle);
            }
        });
        this.seeMoreButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.SectionWidget$seeMoreButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.section_more_button);
            }
        });
        this.hotSpotPulseView = LazyKt__LazyJVMKt.lazy(new Function0<HotSpotPulse>() { // from class: com.amazon.music.views.library.views.SectionWidget$hotSpotPulseView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final HotSpotPulse invoke() {
                return (HotSpotPulse) this.this$0.findViewById(R$id.hotspot_pulse);
            }
        });
        this.artworkFrameLayout = LazyKt__LazyJVMKt.lazy(new Function0<TransparentGradientImageView>() { // from class: com.amazon.music.views.library.views.SectionWidget$artworkFrameLayout$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TransparentGradientImageView invoke() {
                return (TransparentGradientImageView) this.this$0.findViewById(R$id.artwork_frame_view);
            }
        });
        this.headerContainer = LazyKt__LazyJVMKt.lazy(new Function0<ConstraintLayout>() { // from class: com.amazon.music.views.library.views.SectionWidget$headerContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ConstraintLayout invoke() {
                return (ConstraintLayout) this.this$0.findViewById(R$id.header_container);
            }
        });
        this.sectionContentContainer = LazyKt__LazyJVMKt.lazy(new Function0<ConstraintLayout>() { // from class: com.amazon.music.views.library.views.SectionWidget$sectionContentContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ConstraintLayout invoke() {
                return (ConstraintLayout) this.this$0.findViewById(R$id.section_content_container);
            }
        });
        this.headerDrawable = AppCompatResources.getDrawable(context, R$drawable.playback_container_header_rounded_corners);
        this.sectionDrawable = AppCompatResources.getDrawable(context, R$drawable.playback_container_section_rounded_corners);
        View.inflate(context, R$layout.dmmviewlibrary_section_layout, this);
        initViewStyling();
        setInitialMargins();
    }

    @Override // com.amazon.music.views.library.models.base.HotspotSupportedView
    public HotSpotPulse getHotspotView() {
        return HotspotSupportedView.DefaultImpls.getHotspotView(this);
    }

    public StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    private final FrameLayout getContentContainer() {
        Object value = this.contentContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-contentContainer>(...)");
        return (FrameLayout) value;
    }

    private final LinearLayout getTextViewContainer() {
        Object value = this.textViewContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-textViewContainer>(...)");
        return (LinearLayout) value;
    }

    public final TextView getTitleTextView() {
        Object value = this.titleTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-titleTextView>(...)");
        return (TextView) value;
    }

    public final TextView getSubtitleTextView() {
        Object value = this.subtitleTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-subtitleTextView>(...)");
        return (TextView) value;
    }

    public final BaseButton getSeeMoreButton() {
        Object value = this.seeMoreButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-seeMoreButton>(...)");
        return (BaseButton) value;
    }

    @Override // com.amazon.music.views.library.models.base.HotspotSupportedView
    public HotSpotPulse getHotSpotPulseView() {
        Object value = this.hotSpotPulseView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-hotSpotPulseView>(...)");
        return (HotSpotPulse) value;
    }

    public final TransparentGradientImageView getArtworkFrameLayout() {
        Object value = this.artworkFrameLayout.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-artworkFrameLayout>(...)");
        return (TransparentGradientImageView) value;
    }

    public final ConstraintLayout getHeaderContainer() {
        Object value = this.headerContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-headerContainer>(...)");
        return (ConstraintLayout) value;
    }

    private final ConstraintLayout getSectionContentContainer() {
        Object value = this.sectionContentContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-sectionContentContainer>(...)");
        return (ConstraintLayout) value;
    }

    public final RecyclerView getRecyclerView() {
        return this.recyclerView;
    }

    public final void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public final void initViewStyling() {
        FontStyle fontStyle = getStyleSheet().getFontStyle(FontStyleKey.HEADLINE_4);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(getTitleTextView(), fontStyle);
        }
        FontStyle fontStyle2 = getStyleSheet().getFontStyle(FontStyleKey.SECONDARY);
        if (fontStyle2 != null) {
            FontUtil.INSTANCE.applyFontStyle(getSubtitleTextView(), fontStyle2);
        }
        this.seeMoreButtonBuilder = getStyleSheet().getButtonBuilder(ButtonSizeKey.SMALL, ButtonStyleKey.GLASS);
    }

    private final void initMargins() {
        Integer spacerInPixels = getStyleSheet().getSpacerInPixels(SpacerKey.LARGE);
        int iIntValue = spacerInPixels == null ? 0 : spacerInPixels.intValue();
        Integer spacerInPixels2 = getStyleSheet().getSpacerInPixels(SpacerKey.SMALL);
        int iIntValue2 = spacerInPixels2 == null ? 0 : spacerInPixels2.intValue();
        StyleSheet styleSheet = getStyleSheet();
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        GridSize gridSize = styleSheet.getGridSize(context);
        int marginSpace = gridSize != null ? gridSize.getMarginSpace() : 0;
        setMargins$default(this, Integer.valueOf(marginSpace), Integer.valueOf(iIntValue), Integer.valueOf(marginSpace), Integer.valueOf(iIntValue2), null, 16, null);
    }

    private final void initViewMargins() {
        StyleSheet styleSheet = getStyleSheet();
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        GridSize gridSize = styleSheet.getGridSize(context);
        if (gridSize == null) {
            return;
        }
        setContentMargins(Integer.valueOf(gridSize.getColumnSpace() / 2), 0, Integer.valueOf(gridSize.getColumnSpace() / 2), 0);
        setTextMargins(Integer.valueOf(gridSize.getColumnSpace()), 0, Integer.valueOf(gridSize.getColumnSpace() + gridSize.getMarginSpace()), 0);
        setMoreButtonMargins(0, 0, Integer.valueOf(gridSize.getColumnSpace() + gridSize.getMarginSpace()), 0);
    }

    public final void setInitialMargins() {
        initMargins();
        initViewMargins();
    }

    public final void setAdapter(UniversalAdapter adapter) {
        RecyclerView recyclerView = this.recyclerView;
        if (recyclerView != null) {
            recyclerView.setAdapter(adapter);
        }
        getContentContainer().setVisibility(adapter != null ? 0 : 8);
    }

    public final UniversalAdapter getAdapter() {
        RecyclerView recyclerView = this.recyclerView;
        if ((recyclerView == null ? null : recyclerView.getAdapter()) == null) {
            return null;
        }
        RecyclerView recyclerView2 = this.recyclerView;
        Intrinsics.checkNotNull(recyclerView2);
        RecyclerView.Adapter adapter = recyclerView2.getAdapter();
        if (adapter != null) {
            return (UniversalAdapter) adapter;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter");
    }

    @Override // com.amazon.music.views.library.models.base.ClearableContainer
    public void clearModels() {
        setAdapter(null);
        getTitleTextView().setVisibility(8);
        getSubtitleTextView().setVisibility(8);
        getSeeMoreButton().setVisibility(8);
    }

    public final void setContentView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        getContentContainer().addView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override // android.view.View
    public void setEnabled(boolean isEnabled) {
        if (isEnabled) {
            getTitleTextView().setAlpha(1.0f);
            return;
        }
        Float alpha = getStyleSheet().getAlpha(AlphaKey.GLASS_3);
        if (alpha != null) {
            getTitleTextView().setAlpha(alpha.floatValue());
        }
        getSeeMoreButton().setVisibility(8);
    }

    public final void setTitle(String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        getTitleTextView().setText(title);
        getTitleTextView().setMaxLines(2);
        getTitleTextView().setVisibility(!TextUtils.isEmpty(title) ? 0 : 8);
    }

    public final void highlightTitle(List<String> stringsToHighlight) {
        if (stringsToHighlight == null) {
            Integer color = getStyleSheet().getColor(ColorKey.COLOR_PRIMARY);
            if (color == null) {
                return;
            }
            getTitleTextView().setTextColor(color.intValue());
            return;
        }
        SpannableString spannableString = new SpannableString(getTitleTextView().getText());
        Integer color2 = getStyleSheet().getColor(ColorKey.COLOR_ACCENT);
        for (String str : stringsToHighlight) {
            int iIndexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) getTitleTextView().getText().toString(), StringsKt__StringsKt.trim((CharSequence) str).toString(), 0, false, 6, (Object) null);
            int iMin = Math.min(str.length() + iIndexOf$default, getTitleTextView().getText().length());
            if (iIndexOf$default >= 0 && color2 != null) {
                color2.intValue();
                spannableString.setSpan(new ForegroundColorSpan(color2.intValue()), iIndexOf$default, iMin, 33);
            }
        }
        getTitleTextView().setText(spannableString);
        getTitleTextView().setMaxLines(2);
        getTitleTextView().setVisibility(!TextUtils.isEmpty(spannableString) ? 0 : 8);
    }

    public final void setSubtitle(String subtitle) {
        Intrinsics.checkNotNullParameter(subtitle, "subtitle");
        getSubtitleTextView().setText(subtitle);
        getTitleTextView().setMaxLines(1);
        getSubtitleTextView().setVisibility(!TextUtils.isEmpty(subtitle) ? 0 : 8);
        Integer spacerInPixels = getStyleSheet().getSpacerInPixels(SpacerKey.MICRO);
        if (spacerInPixels == null) {
            return;
        }
        setTextViewMargins$DMMViewLibrary_release$default(this, getSubtitleTextView(), null, Integer.valueOf(spacerInPixels.intValue()), null, null, 26, null);
    }

    public static /* synthetic */ void setMoreButtonText$default(SectionWidget sectionWidget, String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        sectionWidget.setMoreButtonText(str, z);
    }

    @JvmOverloads
    public final void setMoreButtonText(String moreButtonText, boolean shouldDisplayEmptyTextButton) {
        if (shouldDisplayEmptyTextButton) {
            return;
        }
        if (TextUtils.isEmpty(moreButtonText)) {
            getSeeMoreButton().setVisibility(8);
            getSeeMoreButton().setContentDescription(null);
            return;
        }
        getSeeMoreButton().setVisibility(0);
        BaseButton seeMoreButton = getSeeMoreButton();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = getResources().getString(R$string.content_description_link_suffix);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…_description_link_suffix)");
        String str = String.format(string, Arrays.copyOf(new Object[]{moreButtonText}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        seeMoreButton.setContentDescription(str);
        BaseButton.StyleBuilder styleBuilder = this.seeMoreButtonBuilder;
        if (styleBuilder != null) {
            Intrinsics.checkNotNull(moreButtonText);
            styleBuilder.withText(moreButtonText);
            styleBuilder.applyStyle(getSeeMoreButton());
        }
        Integer spacerInPixels = getStyleSheet().getSpacerInPixels(SpacerKey.BASE);
        if (spacerInPixels == null) {
            return;
        }
        int iIntValue = spacerInPixels.intValue();
        setMoreButtonMargins$default(this, Integer.valueOf(iIntValue), null, null, null, 14, null);
        setTextMargins$default(this, null, null, Integer.valueOf(iIntValue), null, 11, null);
    }

    public final void setMoreButtonOnClickListener(View.OnClickListener onClickListener) {
        getSeeMoreButton().setOnClickListener(onClickListener);
    }

    public static /* synthetic */ void setMargins$default(SectionWidget sectionWidget, Integer num, Integer num2, Integer num3, Integer num4, View view, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        if ((i & 2) != 0) {
            num2 = null;
        }
        if ((i & 4) != 0) {
            num3 = null;
        }
        if ((i & 8) != 0) {
            num4 = null;
        }
        View view2 = view;
        if ((i & 16) != 0) {
            view2 = sectionWidget;
        }
        sectionWidget.setMargins(num, num2, num3, num4, view2);
    }

    public final void setMargins(Integer left, Integer top, Integer right, Integer bottom, View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        LayoutParamUtils.INSTANCE.setLayoutMargins(view, -1, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
    }

    public static /* synthetic */ void setPlaybackContainerHeaderMargins$default(SectionWidget sectionWidget, Integer num, Integer num2, Integer num3, Integer num4, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        if ((i & 2) != 0) {
            num2 = null;
        }
        if ((i & 4) != 0) {
            num3 = null;
        }
        if ((i & 8) != 0) {
            num4 = null;
        }
        sectionWidget.setPlaybackContainerHeaderMargins(num, num2, num3, num4);
    }

    public final void setPlaybackContainerHeaderMargins(Integer left, Integer top, Integer right, Integer bottom) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(getHeaderContainer(), -1, -1, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
    }

    public static /* synthetic */ void setPlaybackContainerBodyMargins$default(SectionWidget sectionWidget, Integer num, Integer num2, Integer num3, Integer num4, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        if ((i & 2) != 0) {
            num2 = null;
        }
        if ((i & 4) != 0) {
            num3 = null;
        }
        if ((i & 8) != 0) {
            num4 = null;
        }
        sectionWidget.setPlaybackContainerBodyMargins(num, num2, num3, num4);
    }

    public final void setPlaybackContainerBodyMargins(Integer left, Integer top, Integer right, Integer bottom) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(getSectionContentContainer(), -1, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
    }

    public static /* synthetic */ void setPlaybackContainerContentMargins$default(SectionWidget sectionWidget, Integer num, Integer num2, Integer num3, Integer num4, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        if ((i & 2) != 0) {
            num2 = null;
        }
        if ((i & 4) != 0) {
            num3 = null;
        }
        if ((i & 8) != 0) {
            num4 = null;
        }
        sectionWidget.setPlaybackContainerContentMargins(num, num2, num3, num4);
    }

    public final void setPlaybackContainerContentMargins(Integer left, Integer top, Integer right, Integer bottom) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(getContentContainer(), -1, -1, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
    }

    public static /* synthetic */ void setContentMargins$default(SectionWidget sectionWidget, Integer num, Integer num2, Integer num3, Integer num4, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        if ((i & 2) != 0) {
            num2 = null;
        }
        if ((i & 4) != 0) {
            num3 = null;
        }
        if ((i & 8) != 0) {
            num4 = null;
        }
        sectionWidget.setContentMargins(num, num2, num3, num4);
    }

    public final void setContentMargins(Integer left, Integer top, Integer right, Integer bottom) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(getContentContainer(), 0, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
    }

    public static /* synthetic */ void setTextMargins$default(SectionWidget sectionWidget, Integer num, Integer num2, Integer num3, Integer num4, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        if ((i & 2) != 0) {
            num2 = null;
        }
        if ((i & 4) != 0) {
            num3 = null;
        }
        if ((i & 8) != 0) {
            num4 = null;
        }
        sectionWidget.setTextMargins(num, num2, num3, num4);
    }

    public final void setTextMargins(Integer left, Integer top, Integer right, Integer bottom) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(getTextViewContainer(), 0, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
    }

    public static /* synthetic */ void setTextViewMargins$DMMViewLibrary_release$default(SectionWidget sectionWidget, View view, Integer num, Integer num2, Integer num3, Integer num4, int i, Object obj) {
        sectionWidget.setTextViewMargins$DMMViewLibrary_release(view, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : num2, (i & 8) != 0 ? null : num3, (i & 16) != 0 ? null : num4);
    }

    public final void setTextViewMargins$DMMViewLibrary_release(View view, Integer left, Integer top, Integer right, Integer bottom) {
        Intrinsics.checkNotNullParameter(view, "view");
        LayoutParamUtils.INSTANCE.setLayoutMargins(view, -2, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
    }

    public static /* synthetic */ void setMoreButtonMargins$default(SectionWidget sectionWidget, Integer num, Integer num2, Integer num3, Integer num4, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        if ((i & 2) != 0) {
            num2 = null;
        }
        if ((i & 4) != 0) {
            num3 = null;
        }
        if ((i & 8) != 0) {
            num4 = null;
        }
        sectionWidget.setMoreButtonMargins(num, num2, num3, num4);
    }

    public final void setMoreButtonMargins(Integer left, Integer top, Integer right, Integer bottom) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(getSeeMoreButton(), -2, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
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

    public void showHotspot(boolean show) {
        getHotSpotPulseView().setVisibility(show ? 0 : 8);
        if (show) {
            if (getSeeMoreButton().getVisibility() == 8) {
                Integer spacerInPixels = getStyleSheet().getSpacerInPixels(SpacerKey.HUGE);
                if (spacerInPixels == null) {
                    return;
                }
                int iIntValue = spacerInPixels.intValue();
                HotspotSupportedView.DefaultImpls.setHotspotMargins$default(this, Integer.valueOf(iIntValue), null, Integer.valueOf(iIntValue * 4), null, 10, null);
                return;
            }
            Integer spacerInPixels2 = getStyleSheet().getSpacerInPixels(SpacerKey.BASE);
            if (spacerInPixels2 == null) {
                return;
            }
            int iIntValue2 = spacerInPixels2.intValue();
            HotspotSupportedView.DefaultImpls.setHotspotMargins$default(this, Integer.valueOf(iIntValue2), null, Integer.valueOf(iIntValue2), null, 10, null);
        }
    }

    public final void showImage(boolean show) {
        getArtworkFrameLayout().setVisibility(show ? 0 : 8);
    }

    public final void showBackground(boolean shouldShowBackground) {
        if (shouldShowBackground) {
            Integer spacerInPixels = getStyleSheet().getSpacerInPixels(SpacerKey.MEDIUM);
            int iIntValue = spacerInPixels == null ? 0 : spacerInPixels.intValue();
            getHeaderContainer().setPadding(iIntValue, iIntValue, 0, iIntValue);
            getHeaderContainer().setBackground(this.headerDrawable);
            getContentContainer().setBackground(this.sectionDrawable);
            getContentContainer().setPadding(0, iIntValue, 0, iIntValue);
            setPlaybackContainerContentMargins$default(this, -1, null, -1, null, 10, null);
            return;
        }
        getHeaderContainer().setPadding(0, 0, 0, 0);
        getHeaderContainer().setBackground(null);
        getContentContainer().setBackground(null);
        getContentContainer().setPadding(0, 0, 0, 0);
        setPlaybackContainerContentMargins$default(this, null, null, null, null, 15, null);
    }

    public final void setPlaybackContainerHeaderOnClickListener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        getHeaderContainer().setOnClickListener(onClickListener);
    }

    @Override // com.amazon.music.views.library.models.base.HotspotSupportedView
    public HotSpotToolTipDialog createTooltipView() {
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        return new HotSpotToolTipDialog(context, getStyleSheet(), HotSpotToolTipDialog.Companion.Position.TOP);
    }
}
