package com.amazon.mp3.playlist;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.ColorUtil;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import com.google.firebase.perf.util.Constants;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CreatePlaylistView.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010'\u001a\u00020(2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\u0010\u0010)\u001a\u00020(2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\u0010\u0010*\u001a\u00020(2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\u000e\u0010+\u001a\u00020(2\u0006\u0010,\u001a\u00020#J\u0010\u0010+\u001a\u00020(2\b\u0010-\u001a\u0004\u0018\u00010.J\u0018\u0010/\u001a\u00020(2\u0006\u0010,\u001a\u00020#2\b\u00100\u001a\u0004\u0018\u000101J\u001a\u0010/\u001a\u00020(2\b\u00102\u001a\u0004\u0018\u00010.2\b\u00100\u001a\u0004\u0018\u000101J\u000e\u00103\u001a\u00020(2\u0006\u00104\u001a\u000205J\u000e\u00106\u001a\u00020(2\u0006\u0010,\u001a\u00020#J\u0010\u00106\u001a\u00020(2\b\u00102\u001a\u0004\u0018\u00010.J\u000e\u00107\u001a\u00020(2\u0006\u0010,\u001a\u00020#J\u0010\u00107\u001a\u00020(2\b\u00102\u001a\u0004\u0018\u00010.J\u000e\u00108\u001a\u00020(2\u0006\u0010,\u001a\u00020#J\u0010\u00108\u001a\u00020(2\b\u00102\u001a\u0004\u0018\u00010.R\u001b\u0010\u0007\u001a\u00020\b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\f\u001a\u0004\b\u0014\u0010\u0015R\u001b\u0010\u0017\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\f\u001a\u0004\b\u0018\u0010\u0010R\u001b\u0010\u001a\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\f\u001a\u0004\b\u001b\u0010\u0010R\u001b\u0010\u001d\u001a\u00020\u001e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\f\u001a\u0004\b\u001f\u0010 R\u000e\u0010\"\u001a\u00020#X\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010$\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b&\u0010\f\u001a\u0004\b%\u0010\u0010¨\u00069"}, d2 = {"Lcom/amazon/mp3/playlist/CreatePlaylistView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "editText", "Landroid/widget/EditText;", "getEditText", "()Landroid/widget/EditText;", "editText$delegate", "Lkotlin/Lazy;", "labelView", "Landroid/widget/TextView;", "getLabelView", "()Landroid/widget/TextView;", "labelView$delegate", "positiveButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "getPositiveButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "positiveButton$delegate", "publicTogglePrimaryText", "getPublicTogglePrimaryText", "publicTogglePrimaryText$delegate", "publicToggleSecondaryText", "getPublicToggleSecondaryText", "publicToggleSecondaryText$delegate", "publicToggleSwitch", "Landroid/widget/ToggleButton;", "getPublicToggleSwitch", "()Landroid/widget/ToggleButton;", "publicToggleSwitch$delegate", "saveButtonWidth", "", "titleView", "getTitleView", "titleView$delegate", "initEditText", "", "initStyleSheet", "setEditTextUnderlineColors", "setLabel", "textResId", "labelText", "", "setPositiveButton", "onClickListener", "Landroid/view/View$OnClickListener;", "text", "setPositiveButtonEnabled", Constants.ENABLE_DISABLE, "", "setPublicToggleDescription", "setPublicToggleTitle", "setTitle", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CreatePlaylistView extends LinearLayout {

    /* JADX INFO: renamed from: editText$delegate, reason: from kotlin metadata */
    private final Lazy editText;

    /* JADX INFO: renamed from: labelView$delegate, reason: from kotlin metadata */
    private final Lazy labelView;

    /* JADX INFO: renamed from: positiveButton$delegate, reason: from kotlin metadata */
    private final Lazy positiveButton;

    /* JADX INFO: renamed from: publicTogglePrimaryText$delegate, reason: from kotlin metadata */
    private final Lazy publicTogglePrimaryText;

    /* JADX INFO: renamed from: publicToggleSecondaryText$delegate, reason: from kotlin metadata */
    private final Lazy publicToggleSecondaryText;

    /* JADX INFO: renamed from: publicToggleSwitch$delegate, reason: from kotlin metadata */
    private final Lazy publicToggleSwitch;
    private final int saveButtonWidth;

    /* JADX INFO: renamed from: titleView$delegate, reason: from kotlin metadata */
    private final Lazy titleView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CreatePlaylistView(Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.titleView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.mp3.playlist.CreatePlaylistView$titleView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R.id.title);
            }
        });
        this.labelView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.mp3.playlist.CreatePlaylistView$labelView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R.id.label);
            }
        });
        this.editText = LazyKt__LazyJVMKt.lazy(new Function0<EditText>() { // from class: com.amazon.mp3.playlist.CreatePlaylistView$editText$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final EditText invoke() {
                return (EditText) this.this$0.findViewById(R.id.edit_text);
            }
        });
        this.positiveButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.playlist.CreatePlaylistView$positiveButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.positive_button);
            }
        });
        this.publicTogglePrimaryText = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.mp3.playlist.CreatePlaylistView$publicTogglePrimaryText$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R.id.public_toggle_primary);
            }
        });
        this.publicToggleSecondaryText = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.mp3.playlist.CreatePlaylistView$publicToggleSecondaryText$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R.id.public_toggle_secondary);
            }
        });
        this.publicToggleSwitch = LazyKt__LazyJVMKt.lazy(new Function0<ToggleButton>() { // from class: com.amazon.mp3.playlist.CreatePlaylistView$publicToggleSwitch$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ToggleButton invoke() {
                return (ToggleButton) this.this$0.findViewById(R.id.public_toggle);
            }
        });
        this.saveButtonWidth = 135;
        setOrientation(1);
        LayoutInflater.from(context).inflate(R.layout.create_playlist_dialog_layout, this);
        initStyleSheet(styleSheet);
    }

    private final TextView getTitleView() {
        Object value = this.titleView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-titleView>(...)");
        return (TextView) value;
    }

    private final TextView getLabelView() {
        Object value = this.labelView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-labelView>(...)");
        return (TextView) value;
    }

    public final EditText getEditText() {
        Object value = this.editText.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-editText>(...)");
        return (EditText) value;
    }

    private final BaseButton getPositiveButton() {
        Object value = this.positiveButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-positiveButton>(...)");
        return (BaseButton) value;
    }

    private final TextView getPublicTogglePrimaryText() {
        Object value = this.publicTogglePrimaryText.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-publicTogglePrimaryText>(...)");
        return (TextView) value;
    }

    private final TextView getPublicToggleSecondaryText() {
        Object value = this.publicToggleSecondaryText.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-publicToggleSecondaryText>(...)");
        return (TextView) value;
    }

    public final ToggleButton getPublicToggleSwitch() {
        Object value = this.publicToggleSwitch.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-publicToggleSwitch>(...)");
        return (ToggleButton) value;
    }

    public final void setTitle(int textResId) {
        getTitleView().setText(textResId);
    }

    public final void setTitle(String text) {
        getTitleView().setText(text);
    }

    public final void setLabel(int textResId) {
        setLabel(getResources().getString(textResId));
    }

    public final void setLabel(String labelText) {
        if (TextUtils.isEmpty(labelText)) {
            getLabelView().setVisibility(8);
        } else {
            getLabelView().setVisibility(0);
        }
    }

    public final void setPositiveButton(int textResId, View.OnClickListener onClickListener) {
        setPositiveButton(getResources().getString(textResId), onClickListener);
    }

    public final void setPositiveButton(String text, View.OnClickListener onClickListener) {
        if (text == null || TextUtils.isEmpty(text)) {
            getPositiveButton().setVisibility(8);
            return;
        }
        getPositiveButton().setText(text);
        getPositiveButton().setOnClickListener(onClickListener);
        getPositiveButton().setVisibility(0);
    }

    public final void setPositiveButtonEnabled(boolean isEnabled) {
        getPositiveButton().setEnabled(isEnabled);
    }

    public final void setPublicToggleTitle(int textResId) {
        getPublicTogglePrimaryText().setText(textResId);
    }

    public final void setPublicToggleTitle(String text) {
        getPublicTogglePrimaryText().setText(text);
    }

    public final void setPublicToggleDescription(int textResId) {
        getPublicToggleSecondaryText().setText(textResId);
    }

    public final void setPublicToggleDescription(String text) {
        getPublicToggleSecondaryText().setText(text);
    }

    private final void initStyleSheet(StyleSheet styleSheet) {
        FontStyle fontStyleCopy$default;
        FontStyle fontStyle = styleSheet.getFontStyle(FontStyleKey.HEADLINE_4);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(getTitleView(), fontStyle);
        }
        BaseButton.StyleBuilder buttonBuilder = styleSheet.getButtonBuilder(ButtonSizeKey.MEDIUM, ButtonStyleKey.SOLID);
        if (buttonBuilder != null) {
            buttonBuilder.applyStyle(getPositiveButton());
        }
        Integer color = styleSheet.getColor(ColorKey.COLOR_PRIMARY);
        int iIntValue = color == null ? -1 : color.intValue();
        FontStyle fontStyle2 = styleSheet.getFontStyle(FontStyleKey.LABEL);
        if (fontStyle2 != null && (fontStyleCopy$default = FontStyle.copy$default(fontStyle2, null, null, iIntValue, null, 11, null)) != null) {
            FontUtil.INSTANCE.applyFontStyle(getLabelView(), fontStyleCopy$default);
        }
        Integer spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.BASE);
        if (spacerInPixels != null) {
            int iIntValue2 = spacerInPixels.intValue();
            LayoutParamUtils.INSTANCE.setLayoutMargins(getTitleView(), -2, -2, (240 & 8) != 0 ? Boolean.FALSE : Boolean.FALSE, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : Integer.valueOf(iIntValue2), (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : Integer.valueOf(iIntValue2));
        }
        Integer spacerInPixels2 = styleSheet.getSpacerInPixels(SpacerKey.HUGE);
        if (spacerInPixels2 != null) {
            LayoutParamUtils.INSTANCE.setLayoutMargins(getPositiveButton(), -1, -2, (240 & 8) != 0 ? Boolean.FALSE : Boolean.FALSE, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : Integer.valueOf(spacerInPixels2.intValue()), (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
        }
        if (AmazonApplication.getCapabilities().isPlaylistCreationPublicToggleEnabled()) {
            FontStyle fontStyle3 = styleSheet.getFontStyle(FontStyleKey.PRIMARY);
            if (fontStyle3 != null) {
                FontUtil.INSTANCE.applyFontStyle(getPublicTogglePrimaryText(), fontStyle3);
                getPublicTogglePrimaryText().setTypeface(null, 1);
            }
            FontStyle fontStyle4 = styleSheet.getFontStyle(FontStyleKey.SECONDARY);
            if (fontStyle4 != null) {
                FontUtil.INSTANCE.applyFontStyle(getPublicToggleSecondaryText(), fontStyle4);
            }
            Integer spacerInPixels3 = styleSheet.getSpacerInPixels(SpacerKey.MINI);
            if (spacerInPixels3 != null) {
                LayoutParamUtils.INSTANCE.setLayoutMargins(getPublicToggleSecondaryText(), 0, -2, (240 & 8) != 0 ? Boolean.FALSE : Boolean.FALSE, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : Integer.valueOf(spacerInPixels3.intValue()), (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
            }
            Integer spacerInPixels4 = styleSheet.getSpacerInPixels(SpacerKey.SMALL);
            if (spacerInPixels4 != null) {
                LayoutParamUtils.INSTANCE.setLayoutMargins(getPublicTogglePrimaryText(), -2, -2, (240 & 8) != 0 ? Boolean.FALSE : Boolean.FALSE, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : Integer.valueOf(spacerInPixels4.intValue()), (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
            }
            LayoutParamUtils.INSTANCE.setLayoutMargins(getPositiveButton(), (int) ((this.saveButtonWidth * getContext().getResources().getDisplayMetrics().density) + 0.5f), -2, (240 & 8) != 0 ? Boolean.FALSE : Boolean.FALSE, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
        } else {
            getPublicTogglePrimaryText().setVisibility(8);
            getPublicToggleSecondaryText().setVisibility(8);
            getPublicToggleSwitch().setVisibility(8);
        }
        initEditText(styleSheet);
    }

    private final void initEditText(StyleSheet styleSheet) {
        FontStyle fontStyle = styleSheet.getFontStyle(FontStyleKey.PRIMARY);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(getEditText(), fontStyle);
        }
        Integer color = styleSheet.getColor(ColorKey.COLOR_PRIMARY, AlphaKey.GLASS_4);
        if (color != null) {
            getEditText().setHintTextColor(color.intValue());
        }
        setEditTextUnderlineColors(styleSheet);
        getEditText().requestFocus();
    }

    private final void setEditTextUnderlineColors(StyleSheet styleSheet) {
        ColorKey colorKey = ColorKey.COLOR_PRIMARY;
        Integer color = styleSheet.getColor(colorKey, AlphaKey.GLASS_2);
        Integer color2 = styleSheet.getColor(colorKey);
        if (color == null || color2 == null) {
            return;
        }
        getEditText().setBackgroundTintList(ColorUtil.getColorStateList$default(ColorUtil.INSTANCE, color.intValue(), color2, color2, null, 8, null));
    }
}
