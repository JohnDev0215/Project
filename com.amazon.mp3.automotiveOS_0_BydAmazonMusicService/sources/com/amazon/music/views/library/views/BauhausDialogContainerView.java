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
import androidx.constraintlayout.widget.ConstraintSet;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.music.views.library.styles.keys.ScreenSizeKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BauhausDialogContainerView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u00104\u001a\u0002052\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\b\u00106\u001a\u000207H\u0002J\b\u00108\u001a\u000207H\u0002J\u000e\u00109\u001a\u0002072\u0006\u0010:\u001a\u00020;J\u0010\u0010<\u001a\u0002072\b\u0010=\u001a\u0004\u0018\u00010>J\b\u0010?\u001a\u000207H\u0002J\u0010\u0010@\u001a\u0002072\b\u0010A\u001a\u0004\u0018\u00010BJ0\u0010C\u001a\u0002072\u0006\u0010D\u001a\u00020>2\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010E\u001a\u0004\u0018\u00010F2\n\b\u0002\u0010G\u001a\u0004\u0018\u00010FH\u0002J\u0010\u0010H\u001a\u0002072\b\u0010I\u001a\u0004\u0018\u00010JJ\u0010\u0010K\u001a\u0002072\b\u0010L\u001a\u0004\u0018\u00010MJ\u0018\u0010N\u001a\u0002072\b\u0010I\u001a\u0004\u0018\u00010J2\u0006\u0010O\u001a\u00020MJ\u0018\u0010P\u001a\u0002072\b\u0010I\u001a\u0004\u0018\u00010J2\u0006\u0010O\u001a\u00020MJ\u0018\u0010Q\u001a\u0002072\b\u0010I\u001a\u0004\u0018\u00010J2\u0006\u0010R\u001a\u00020\u001fR\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0012\u001a\u00020\u00018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\f\u001a\u0004\b\u0013\u0010\u0014R\u001b\u0010\u0016\u001a\u00020\u00178BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\f\u001a\u0004\b\u0018\u0010\u0019R\u001b\u0010\u001b\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\f\u001a\u0004\b\u001c\u0010\u0010R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010!\u001a\u00020\"8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b%\u0010\f\u001a\u0004\b#\u0010$R\u000e\u0010&\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010'\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b)\u0010\f\u001a\u0004\b(\u0010\u0010R\u001b\u0010*\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b,\u0010\f\u001a\u0004\b+\u0010\u0010R\u000e\u0010-\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010.\u001a\u00020\u00178BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b0\u0010\f\u001a\u0004\b/\u0010\u0019R\u001b\u00101\u001a\u00020\"8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b3\u0010\f\u001a\u0004\b2\u0010$¨\u0006S"}, d2 = {"Lcom/amazon/music/views/library/views/BauhausDialogContainerView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "bottomButtonContainer", "Landroidx/constraintlayout/widget/ConstraintLayout;", "getBottomButtonContainer", "()Landroidx/constraintlayout/widget/ConstraintLayout;", "bottomButtonContainer$delegate", "Lkotlin/Lazy;", "closeButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "getCloseButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "closeButton$delegate", "contentContainer", "getContentContainer", "()Landroid/widget/FrameLayout;", "contentContainer$delegate", "dialogContainer", "Landroid/widget/LinearLayout;", "getDialogContainer", "()Landroid/widget/LinearLayout;", "dialogContainer$delegate", "iconView", "getIconView", "iconView$delegate", "largeWidth", "", "mediumWidth", "messageView", "Landroid/widget/TextView;", "getMessageView", "()Landroid/widget/TextView;", "messageView$delegate", "minButtonWidth", "primaryButton", "getPrimaryButton", "primaryButton$delegate", "secondaryButton", "getSecondaryButton", "secondaryButton$delegate", "smallWidth", "titleContainer", "getTitleContainer", "titleContainer$delegate", "titleView", "getTitleView", "titleView$delegate", "getScreenSizeKey", "Lcom/amazon/music/views/library/styles/keys/ScreenSizeKey;", "initBackground", "", "initStyleSheet", "setButtonOrientation", "orientation", "Lcom/amazon/music/views/library/views/Orientation;", "setContent", "content", "Landroid/view/View;", "setDialogWidth", "setIcon", "drawable", "Landroid/graphics/drawable/Drawable;", "setMargins", "view", "rightMarginSpacerKey", "Lcom/amazon/music/views/library/styles/keys/SpacerKey;", "topMarginSpacerKey", "setMessage", "text", "", "setOnCloseButtonClickListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Landroid/view/View$OnClickListener;", "setPrimaryButton", "onClickListener", "setSecondaryButton", "setTitle", "alignment", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BauhausDialogContainerView extends FrameLayout {

    /* JADX INFO: renamed from: bottomButtonContainer$delegate, reason: from kotlin metadata */
    private final Lazy bottomButtonContainer;

    /* JADX INFO: renamed from: closeButton$delegate, reason: from kotlin metadata */
    private final Lazy closeButton;

    /* JADX INFO: renamed from: contentContainer$delegate, reason: from kotlin metadata */
    private final Lazy contentContainer;

    /* JADX INFO: renamed from: dialogContainer$delegate, reason: from kotlin metadata */
    private final Lazy dialogContainer;

    /* JADX INFO: renamed from: iconView$delegate, reason: from kotlin metadata */
    private final Lazy iconView;
    private final int largeWidth;
    private final int mediumWidth;

    /* JADX INFO: renamed from: messageView$delegate, reason: from kotlin metadata */
    private final Lazy messageView;
    private final int minButtonWidth;

    /* JADX INFO: renamed from: primaryButton$delegate, reason: from kotlin metadata */
    private final Lazy primaryButton;

    /* JADX INFO: renamed from: secondaryButton$delegate, reason: from kotlin metadata */
    private final Lazy secondaryButton;
    private final int smallWidth;
    private final StyleSheet styleSheet;

    /* JADX INFO: renamed from: titleContainer$delegate, reason: from kotlin metadata */
    private final Lazy titleContainer;

    /* JADX INFO: renamed from: titleView$delegate, reason: from kotlin metadata */
    private final Lazy titleView;

    /* JADX INFO: compiled from: BauhausDialogContainerView.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ScreenSizeKey.values().length];
            iArr[ScreenSizeKey.XSMALL.ordinal()] = 1;
            iArr[ScreenSizeKey.SMALL.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BauhausDialogContainerView(Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.dialogContainer = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.music.views.library.views.BauhausDialogContainerView$dialogContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R$id.dialog_container);
            }
        });
        this.closeButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.BauhausDialogContainerView$closeButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.close_button);
            }
        });
        this.titleContainer = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.music.views.library.views.BauhausDialogContainerView$titleContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.findViewById(R$id.title_container);
            }
        });
        this.titleView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.BauhausDialogContainerView$titleView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.title);
            }
        });
        this.iconView = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.BauhausDialogContainerView$iconView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.icon);
            }
        });
        this.messageView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.BauhausDialogContainerView$messageView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.message);
            }
        });
        this.contentContainer = LazyKt__LazyJVMKt.lazy(new Function0<FrameLayout>() { // from class: com.amazon.music.views.library.views.BauhausDialogContainerView$contentContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FrameLayout invoke() {
                return (FrameLayout) this.this$0.findViewById(R$id.content_container);
            }
        });
        this.bottomButtonContainer = LazyKt__LazyJVMKt.lazy(new Function0<ConstraintLayout>() { // from class: com.amazon.music.views.library.views.BauhausDialogContainerView$bottomButtonContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ConstraintLayout invoke() {
                return (ConstraintLayout) this.this$0.findViewById(R$id.bottom_button_container);
            }
        });
        this.primaryButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.BauhausDialogContainerView$primaryButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.button1);
            }
        });
        this.secondaryButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.BauhausDialogContainerView$secondaryButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.secondary_button);
            }
        });
        this.smallWidth = getResources().getDimensionPixelSize(R$dimen.dialog_small_width);
        this.mediumWidth = getResources().getDimensionPixelSize(R$dimen.dialog_medium_width);
        this.largeWidth = getResources().getDimensionPixelSize(R$dimen.dialog_large_width);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.spacer_96);
        this.minButtonWidth = dimensionPixelSize;
        LayoutInflater.from(context).inflate(R$layout.dmmviewlibrary_dialog_container_layout, this);
        getPrimaryButton().getButton().setMinWidth(dimensionPixelSize);
        getSecondaryButton().getButton().setMinWidth(dimensionPixelSize);
        setDialogWidth();
        initStyleSheet();
        initBackground();
    }

    private final LinearLayout getDialogContainer() {
        Object value = this.dialogContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-dialogContainer>(...)");
        return (LinearLayout) value;
    }

    private final BaseButton getCloseButton() {
        Object value = this.closeButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-closeButton>(...)");
        return (BaseButton) value;
    }

    private final LinearLayout getTitleContainer() {
        Object value = this.titleContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-titleContainer>(...)");
        return (LinearLayout) value;
    }

    private final TextView getTitleView() {
        Object value = this.titleView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-titleView>(...)");
        return (TextView) value;
    }

    private final BaseButton getIconView() {
        Object value = this.iconView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-iconView>(...)");
        return (BaseButton) value;
    }

    private final TextView getMessageView() {
        Object value = this.messageView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-messageView>(...)");
        return (TextView) value;
    }

    private final FrameLayout getContentContainer() {
        Object value = this.contentContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-contentContainer>(...)");
        return (FrameLayout) value;
    }

    private final ConstraintLayout getBottomButtonContainer() {
        Object value = this.bottomButtonContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-bottomButtonContainer>(...)");
        return (ConstraintLayout) value;
    }

    private final BaseButton getPrimaryButton() {
        Object value = this.primaryButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-primaryButton>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getSecondaryButton() {
        Object value = this.secondaryButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-secondaryButton>(...)");
        return (BaseButton) value;
    }

    public final void setContent(View content) {
        if (content != null) {
            getContentContainer().removeAllViews();
            getContentContainer().addView(content);
        }
    }

    public final void setButtonOrientation(Orientation orientation) {
        Intrinsics.checkNotNullParameter(orientation, "orientation");
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(getBottomButtonContainer());
        Integer spacerInPixels = (getSecondaryButton().getVisibility() == 0 && getPrimaryButton().getVisibility() == 0) ? this.styleSheet.getSpacerInPixels(SpacerKey.SMALL) : null;
        int i = 0;
        if (orientation != Orientation.HORIZONTAL) {
            constraintSet.connect(getPrimaryButton().getId(), 3, getBottomButtonContainer().getId(), 3);
            constraintSet.connect(getPrimaryButton().getId(), 2, getBottomButtonContainer().getId(), 2);
            constraintSet.connect(getPrimaryButton().getId(), 1, getBottomButtonContainer().getId(), 1);
            constraintSet.connect(getSecondaryButton().getId(), 4, getBottomButtonContainer().getId(), 4);
            constraintSet.connect(getSecondaryButton().getId(), 2, getBottomButtonContainer().getId(), 2);
            constraintSet.connect(getSecondaryButton().getId(), 1, getBottomButtonContainer().getId(), 1);
            constraintSet.connect(getPrimaryButton().getId(), 4, getSecondaryButton().getId(), 3, spacerInPixels == null ? 0 : spacerInPixels.intValue());
        } else {
            BaseButton[] baseButtonArr = {getSecondaryButton(), getPrimaryButton()};
            ArrayList arrayList = new ArrayList(2);
            int i2 = 0;
            while (i2 < 2) {
                BaseButton baseButton = baseButtonArr[i2];
                i2++;
                arrayList.add(Float.valueOf(baseButton.getVisibility() == 0 ? 1.0f : 0.0f));
            }
            float[] floatArray = CollectionsKt___CollectionsKt.toFloatArray(arrayList);
            ArrayList arrayList2 = new ArrayList(2);
            while (i < 2) {
                BaseButton baseButton2 = baseButtonArr[i];
                i++;
                arrayList2.add(Integer.valueOf(baseButton2.getId()));
            }
            constraintSet.createHorizontalChain(0, 1, 0, 2, CollectionsKt___CollectionsKt.toIntArray(arrayList2), floatArray, 2);
            if (spacerInPixels != null) {
                constraintSet.setMargin(getSecondaryButton().getId(), 2, spacerInPixels.intValue());
            }
        }
        constraintSet.applyTo(getBottomButtonContainer());
    }

    public final void setOnCloseButtonClickListener(View.OnClickListener listener) {
        getCloseButton().setOnClickListener(listener);
        getCloseButton().setVisibility(listener != null ? 0 : 4);
    }

    public final void setTitle(CharSequence text, int alignment) {
        getTitleView().setText(text);
        getTitleView().setTextAlignment(alignment);
        getTitleView().setVisibility(TextUtils.isEmpty(text) ? 8 : 0);
    }

    public final void setIcon(Drawable drawable) {
        if (drawable != null) {
            getIconView().setIcon(drawable);
            getIconView().getButton().setClickable(false);
            getIconView().setVisibility(0);
            return;
        }
        getIconView().setVisibility(8);
    }

    public final void setMessage(CharSequence text) {
        getMessageView().setText(text);
        getMessageView().setVisibility(TextUtils.isEmpty(text) ? 8 : 0);
    }

    public final void setPrimaryButton(CharSequence text, View.OnClickListener onClickListener) {
        String string;
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        BaseButton primaryButton = getPrimaryButton();
        String str = "";
        if (text != null && (string = text.toString()) != null) {
            str = string;
        }
        primaryButton.setText(str);
        getPrimaryButton().setOnClickListener(onClickListener);
        getPrimaryButton().setVisibility(0);
    }

    public final void setSecondaryButton(CharSequence text, View.OnClickListener onClickListener) {
        String string;
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        BaseButton secondaryButton = getSecondaryButton();
        String str = "";
        if (text != null && (string = text.toString()) != null) {
            str = string;
        }
        secondaryButton.setText(str);
        getSecondaryButton().setOnClickListener(onClickListener);
        getSecondaryButton().setVisibility(0);
    }

    private final void initStyleSheet() {
        BaseButton.StyleBuilder styleBuilderWithIcon;
        FontStyle fontStyle = this.styleSheet.getFontStyle(FontStyleKey.HEADLINE_4);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(getTitleView(), fontStyle);
        }
        LinearLayout titleContainer = getTitleContainer();
        StyleSheet styleSheet = this.styleSheet;
        SpacerKey spacerKey = SpacerKey.BASE;
        setMargins$default(this, titleContainer, styleSheet, null, spacerKey, 4, null);
        FontStyle fontStyle2 = this.styleSheet.getFontStyle(FontStyleKey.PRIMARY);
        if (fontStyle2 != null) {
            FontUtil.INSTANCE.applyFontStyle(getMessageView(), fontStyle2);
        }
        setMargins$default(this, getMessageView(), this.styleSheet, null, spacerKey, 4, null);
        setMargins$default(this, getContentContainer(), this.styleSheet, null, spacerKey, 4, null);
        setMargins$default(this, getBottomButtonContainer(), this.styleSheet, null, spacerKey, 4, null);
        StyleSheet styleSheet2 = this.styleSheet;
        ButtonSizeKey buttonSizeKey = ButtonSizeKey.MEDIUM;
        BaseButton.StyleBuilder buttonBuilder = styleSheet2.getButtonBuilder(buttonSizeKey, ButtonStyleKey.SOLID);
        if (buttonBuilder != null) {
            buttonBuilder.applyStyle(getPrimaryButton());
        }
        BaseButton.StyleBuilder buttonBuilder2 = this.styleSheet.getButtonBuilder(buttonSizeKey, ButtonStyleKey.OUTLINE);
        if (buttonBuilder2 != null) {
            buttonBuilder2.applyStyle(getSecondaryButton());
        }
        Drawable drawable = AppCompatResources.getDrawable(getContext(), R$drawable.ic_action_cancel);
        if (drawable == null) {
            return;
        }
        BaseButton closeButton = getCloseButton();
        BaseButton.StyleBuilder iconBuilder = this.styleSheet.getIconBuilder(IconSizeKey.SMALL, IconStyleKey.GLASS);
        if (iconBuilder == null || (styleBuilderWithIcon = iconBuilder.withIcon(drawable)) == null) {
            return;
        }
        styleBuilderWithIcon.applyStyle(closeButton);
    }

    private final void setDialogWidth() {
        int i;
        int i2 = WhenMappings.$EnumSwitchMapping$0[getScreenSizeKey(this.styleSheet).ordinal()];
        if (i2 == 1) {
            i = this.smallWidth;
        } else if (i2 == 2) {
            i = this.mediumWidth;
        } else {
            i = this.largeWidth;
        }
        LayoutParamUtils.INSTANCE.setLayoutMargins(getDialogContainer(), i, -2, (240 & 8) != 0 ? Boolean.FALSE : Boolean.FALSE, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
    }

    private final ScreenSizeKey getScreenSizeKey(StyleSheet styleSheet) {
        ScreenSizeKey heightScreenSizeKey = styleSheet.getHeightScreenSizeKey();
        ScreenSizeKey widthScreenSizeKey = styleSheet.getWidthScreenSizeKey();
        return heightScreenSizeKey.ordinal() < widthScreenSizeKey.ordinal() ? heightScreenSizeKey : widthScreenSizeKey;
    }

    private final void initBackground() {
        Integer color = this.styleSheet.getColor(ColorKey.COLOR_SECONDARY, AlphaKey.GLASS_6);
        setBackgroundColor(color == null ? 0 : color.intValue());
        getContentContainer().setBackgroundColor(0);
    }

    static /* synthetic */ void setMargins$default(BauhausDialogContainerView bauhausDialogContainerView, View view, StyleSheet styleSheet, SpacerKey spacerKey, SpacerKey spacerKey2, int i, Object obj) {
        if ((i & 4) != 0) {
            spacerKey = null;
        }
        if ((i & 8) != 0) {
            spacerKey2 = null;
        }
        bauhausDialogContainerView.setMargins(view, styleSheet, spacerKey, spacerKey2);
    }

    private final void setMargins(View view, StyleSheet styleSheet, SpacerKey rightMarginSpacerKey, SpacerKey topMarginSpacerKey) {
        Integer spacerInPixels;
        Integer spacerInPixels2;
        int iIntValue = (topMarginSpacerKey == null || (spacerInPixels = styleSheet.getSpacerInPixels(topMarginSpacerKey)) == null) ? 0 : spacerInPixels.intValue();
        int iIntValue2 = (rightMarginSpacerKey == null || (spacerInPixels2 = styleSheet.getSpacerInPixels(rightMarginSpacerKey)) == null) ? 0 : spacerInPixels2.intValue();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        marginLayoutParams.setMargins(0, iIntValue, iIntValue2, 0);
        marginLayoutParams.setMarginStart(0);
        marginLayoutParams.setMarginEnd(iIntValue2);
        view.requestLayout();
    }
}
