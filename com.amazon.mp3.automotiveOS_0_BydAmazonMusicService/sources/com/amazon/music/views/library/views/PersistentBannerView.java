package com.amazon.music.views.library.views;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.transition.ChangeBounds;
import androidx.transition.TransitionManager;
import androidx.transition.TransitionSet;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.models.PersistentBannerModel;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PersistentBannerView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B%\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010/\u001a\u000200H\u0002J0\u00101\u001a\u0002002\u0006\u00102\u001a\u00020!2\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u00020!2\u0006\u00106\u001a\u00020!2\b\u00107\u001a\u0004\u0018\u000108J)\u00109\u001a\u0002002\u0006\u00109\u001a\u00020!2\n\b\u0002\u00105\u001a\u0004\u0018\u00010!2\b\u00107\u001a\u0004\u0018\u000108¢\u0006\u0002\u0010:J\u000e\u0010;\u001a\u0002002\u0006\u0010+\u001a\u00020,J\u0010\u0010<\u001a\u0002002\u0006\u0010=\u001a\u00020\u0007H\u0002J\b\u0010>\u001a\u000200H\u0002J\u0010\u0010?\u001a\u0002002\b\u0010@\u001a\u0004\u0018\u00010AJ\b\u0010B\u001a\u000200H\u0002J\b\u0010C\u001a\u000200H\u0002J\u0016\u0010D\u001a\u0002002\u0006\u0010-\u001a\u00020.2\u0006\u0010 \u001a\u00020!J\u0010\u0010E\u001a\u0002002\u0006\u00109\u001a\u00020!H\u0002J\u0006\u0010F\u001a\u000200R\u001d\u0010\t\u001a\u0004\u0018\u00010\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u001d\u0010\u000e\u001a\u0004\u0018\u00010\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\r\u001a\u0004\b\u000f\u0010\u000bR\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\r\u001a\u0004\b\u0013\u0010\u0014R\u001d\u0010\u0016\u001a\u0004\u0018\u00010\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\r\u001a\u0004\b\u0017\u0010\u000bR\u000e\u0010\u0019\u001a\u00020\u001aX\u0082D¢\u0006\u0002\n\u0000R\u001d\u0010\u001b\u001a\u0004\u0018\u00010\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010\r\u001a\u0004\b\u001d\u0010\u001eR\u000e\u0010 \u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\"\u001a\u00020#8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b&\u0010\r\u001a\u0004\b$\u0010%R\u001d\u0010'\u001a\u0004\u0018\u00010\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b)\u0010\r\u001a\u0004\b(\u0010\u001eR\u000e\u0010*\u001a\u00020\u001aX\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010+\u001a\u0004\u0018\u00010,X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u0004\u0018\u00010.X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006G"}, d2 = {"Lcom/amazon/music/views/library/views/PersistentBannerView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "activeColor", "getActiveColor", "()Ljava/lang/Integer;", "activeColor$delegate", "Lkotlin/Lazy;", "alertColor", "getAlertColor", "alertColor$delegate", "bannerColorView", "Landroid/view/View;", "getBannerColorView", "()Landroid/view/View;", "bannerColorView$delegate", "expandedHeight", "getExpandedHeight", "expandedHeight$delegate", "heightAnimationDuration", "", "initFontStyle", "Lcom/amazon/ui/foundations/styles/FontStyle;", "getInitFontStyle", "()Lcom/amazon/ui/foundations/styles/FontStyle;", "initFontStyle$delegate", "isRestingState", "", "primaryTextView", "Landroid/widget/TextView;", "getPrimaryTextView", "()Landroid/widget/TextView;", "primaryTextView$delegate", "restingFontStyle", "getRestingFontStyle", "restingFontStyle$delegate", "stateAnimationDuration", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lcom/amazon/music/views/library/models/PersistentBannerModel$PersistentBannerType;", "animateStateTransition", "", "expandCustomHeight", "show", "spacer", "Lcom/amazon/music/views/library/styles/keys/SpacerKey;", "animate", "disableColorBanner", "parentView", "Landroid/view/ViewGroup;", "expandHeight", "(ZLjava/lang/Boolean;Landroid/view/ViewGroup;)V", "initViewStyling", "setBannerColorHeight", "height", "setInitState", "setPrimaryText", "text", "", "setRestingLayout", "setRestingState", "setStyle", "setViewHeight", "transitionToRestingState", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PersistentBannerView extends ConstraintLayout {

    /* JADX INFO: renamed from: activeColor$delegate, reason: from kotlin metadata */
    private final Lazy activeColor;

    /* JADX INFO: renamed from: alertColor$delegate, reason: from kotlin metadata */
    private final Lazy alertColor;

    /* JADX INFO: renamed from: bannerColorView$delegate, reason: from kotlin metadata */
    private final Lazy bannerColorView;

    /* JADX INFO: renamed from: expandedHeight$delegate, reason: from kotlin metadata */
    private final Lazy expandedHeight;
    private final long heightAnimationDuration;

    /* JADX INFO: renamed from: initFontStyle$delegate, reason: from kotlin metadata */
    private final Lazy initFontStyle;
    private boolean isRestingState;

    /* JADX INFO: renamed from: primaryTextView$delegate, reason: from kotlin metadata */
    private final Lazy primaryTextView;

    /* JADX INFO: renamed from: restingFontStyle$delegate, reason: from kotlin metadata */
    private final Lazy restingFontStyle;
    private final long stateAnimationDuration;
    private StyleSheet styleSheet;
    private PersistentBannerModel.PersistentBannerType type;

    /* JADX INFO: compiled from: PersistentBannerView.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PersistentBannerModel.PersistentBannerType.values().length];
            iArr[PersistentBannerModel.PersistentBannerType.ACTIVE.ordinal()] = 1;
            iArr[PersistentBannerModel.PersistentBannerType.ALERT.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public PersistentBannerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public PersistentBannerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.primaryTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.PersistentBannerView$primaryTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.primary_text);
            }
        });
        this.bannerColorView = LazyKt__LazyJVMKt.lazy(new Function0<View>() { // from class: com.amazon.music.views.library.views.PersistentBannerView$bannerColorView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final View invoke() {
                return this.this$0.findViewById(R$id.banner_color);
            }
        });
        this.expandedHeight = LazyKt__LazyJVMKt.lazy(new Function0<Integer>() { // from class: com.amazon.music.views.library.views.PersistentBannerView$expandedHeight$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                StyleSheet styleSheet = this.this$0.styleSheet;
                if (styleSheet == null) {
                    return null;
                }
                return styleSheet.getSpacerInPixels(SpacerKey.EPIC);
            }
        });
        this.activeColor = LazyKt__LazyJVMKt.lazy(new Function0<Integer>() { // from class: com.amazon.music.views.library.views.PersistentBannerView$activeColor$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                StyleSheet styleSheet = this.this$0.styleSheet;
                if (styleSheet == null) {
                    return null;
                }
                return styleSheet.getCombinationColor(ColorKey.COLOR_SECONDARY, ColorKey.COLOR_ACCENT, AlphaKey.GLASS_4);
            }
        });
        this.alertColor = LazyKt__LazyJVMKt.lazy(new Function0<Integer>() { // from class: com.amazon.music.views.library.views.PersistentBannerView$alertColor$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                StyleSheet styleSheet = this.this$0.styleSheet;
                if (styleSheet == null) {
                    return null;
                }
                return styleSheet.getCombinationColor(ColorKey.COLOR_SECONDARY, ColorKey.COLOR_ERROR, AlphaKey.GLASS_4);
            }
        });
        this.initFontStyle = LazyKt__LazyJVMKt.lazy(new Function0<FontStyle>() { // from class: com.amazon.music.views.library.views.PersistentBannerView$initFontStyle$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FontStyle invoke() {
                FontStyle fontStyle;
                StyleSheet styleSheet;
                Integer color;
                StyleSheet styleSheet2 = this.this$0.styleSheet;
                if (styleSheet2 == null || (fontStyle = styleSheet2.getFontStyle(FontStyleKey.LABEL)) == null || (styleSheet = this.this$0.styleSheet) == null || (color = styleSheet.getColor(ColorKey.COLOR_PRIMARY)) == null) {
                    return null;
                }
                return FontStyle.copy$default(fontStyle, null, null, color.intValue(), null, 11, null);
            }
        });
        this.restingFontStyle = LazyKt__LazyJVMKt.lazy(new Function0<FontStyle>() { // from class: com.amazon.music.views.library.views.PersistentBannerView$restingFontStyle$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FontStyle invoke() {
                FontStyle fontStyle;
                StyleSheet styleSheet;
                Integer color;
                StyleSheet styleSheet2 = this.this$0.styleSheet;
                if (styleSheet2 == null || (fontStyle = styleSheet2.getFontStyle(FontStyleKey.LABEL)) == null || (styleSheet = this.this$0.styleSheet) == null || (color = styleSheet.getColor(ColorKey.COLOR_PRIMARY, AlphaKey.GLASS_4)) == null) {
                    return null;
                }
                return FontStyle.copy$default(fontStyle, null, null, color.intValue(), null, 11, null);
            }
        });
        this.stateAnimationDuration = 500L;
        this.heightAnimationDuration = 250L;
        View.inflate(context, R$layout.dmmviewlibrary_persistent_banner_layout, this);
    }

    public /* synthetic */ PersistentBannerView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    private final TextView getPrimaryTextView() {
        Object value = this.primaryTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-primaryTextView>(...)");
        return (TextView) value;
    }

    private final View getBannerColorView() {
        Object value = this.bannerColorView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-bannerColorView>(...)");
        return (View) value;
    }

    private final Integer getExpandedHeight() {
        return (Integer) this.expandedHeight.getValue();
    }

    private final Integer getActiveColor() {
        return (Integer) this.activeColor.getValue();
    }

    private final Integer getAlertColor() {
        return (Integer) this.alertColor.getValue();
    }

    private final FontStyle getInitFontStyle() {
        return (FontStyle) this.initFontStyle.getValue();
    }

    private final FontStyle getRestingFontStyle() {
        return (FontStyle) this.restingFontStyle.getValue();
    }

    public final void initViewStyling(StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        if (this.styleSheet != null) {
            return;
        }
        this.styleSheet = styleSheet;
        Integer color = styleSheet.getColor(ColorKey.COLOR_SECONDARY);
        if (color == null) {
            return;
        }
        setBackgroundColor(color.intValue());
    }

    public final void setPrimaryText(String text) {
        if (TextUtils.isEmpty(text)) {
            getPrimaryTextView().setVisibility(8);
        } else {
            getPrimaryTextView().setText(text);
            getPrimaryTextView().setVisibility(0);
        }
    }

    public final void setStyle(PersistentBannerModel.PersistentBannerType type, boolean isRestingState) {
        Integer alertColor;
        Intrinsics.checkNotNullParameter(type, "type");
        this.type = type;
        this.isRestingState = isRestingState;
        int i = WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
        if (i == 1) {
            Integer activeColor = getActiveColor();
            if (activeColor != null) {
                getBannerColorView().setBackgroundColor(activeColor.intValue());
            }
        } else if (i == 2 && (alertColor = getAlertColor()) != null) {
            getBannerColorView().setBackgroundColor(alertColor.intValue());
        }
        if (isRestingState) {
            setRestingState();
        } else {
            setInitState();
        }
    }

    private final void setInitState() {
        FontStyle initFontStyle = getInitFontStyle();
        if (initFontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(getPrimaryTextView(), initFontStyle);
        }
        setBannerColorHeight(-1);
    }

    private final void setBannerColorHeight(int height) {
        ViewGroup.LayoutParams layoutParams = getBannerColorView().getLayoutParams();
        layoutParams.height = height;
        getBannerColorView().setLayoutParams(layoutParams);
    }

    private final void setRestingState() {
        FontStyle restingFontStyle = getRestingFontStyle();
        if (restingFontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(getPrimaryTextView(), restingFontStyle);
        }
        setRestingLayout();
    }

    private final void setRestingLayout() {
        Integer spacerInPixels;
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet == null || (spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.MICRO)) == null) {
            return;
        }
        setBannerColorHeight(spacerInPixels.intValue());
    }

    public final void expandHeight(boolean expandHeight, Boolean animate, ViewGroup parentView) {
        if (Intrinsics.areEqual(animate, Boolean.TRUE) && parentView != null) {
            TransitionManager.beginDelayedTransition(parentView, new TransitionSet().addTransition(new ChangeBounds()).setDuration(this.heightAnimationDuration));
        }
        setViewHeight(expandHeight);
    }

    public final void expandCustomHeight(boolean show, SpacerKey spacer, boolean animate, boolean disableColorBanner, ViewGroup parentView) {
        Integer spacerInPixels;
        Intrinsics.checkNotNullParameter(spacer, "spacer");
        if (disableColorBanner) {
            getBannerColorView().setVisibility(8);
        }
        if (animate && parentView != null) {
            TransitionManager.beginDelayedTransition(parentView, new TransitionSet().addTransition(new ChangeBounds()).setDuration(this.heightAnimationDuration));
        }
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet == null || (spacerInPixels = styleSheet.getSpacerInPixels(spacer)) == null) {
            return;
        }
        int iIntValue = spacerInPixels.intValue();
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (!show) {
            iIntValue = 0;
        }
        layoutParams.height = iIntValue;
        setLayoutParams(layoutParams);
    }

    private final void setViewHeight(boolean expandHeight) {
        Integer expandedHeight;
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        int iIntValue = 0;
        if (expandHeight && (expandedHeight = getExpandedHeight()) != null) {
            iIntValue = expandedHeight.intValue();
        }
        layoutParams.height = iIntValue;
        setLayoutParams(layoutParams);
    }

    public final void transitionToRestingState() {
        animateStateTransition();
    }

    private final void animateStateTransition() {
        TransitionManager.beginDelayedTransition(this, new TransitionSet().addTransition(new ChangeBounds()).setDuration(this.stateAnimationDuration));
        setRestingLayout();
    }
}
