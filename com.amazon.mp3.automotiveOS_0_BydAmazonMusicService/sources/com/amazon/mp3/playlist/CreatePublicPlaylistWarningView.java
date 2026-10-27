package com.amazon.mp3.playlist;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import com.google.firebase.perf.util.Constants;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CreatePublicPlaylistWarningView.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\u0018\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eJ\u0018\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020 2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eJ\u000e\u0010!\u001a\u00020\u00192\u0006\u0010\"\u001a\u00020#J\u0018\u0010$\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eJ\u0018\u0010$\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020 2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eJ\u000e\u0010%\u001a\u00020\u00192\u0006\u0010\"\u001a\u00020#J\u000e\u0010&\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001cJ\u0010\u0010&\u001a\u00020\u00192\b\u0010\u001f\u001a\u0004\u0018\u00010 J\u000e\u0010'\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001cJ\u0010\u0010'\u001a\u00020\u00192\b\u0010\u001f\u001a\u0004\u0018\u00010 R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001b\u0010\r\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\f\u001a\u0004\b\u000e\u0010\nR\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\f\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0015\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\f\u001a\u0004\b\u0016\u0010\u0013¨\u0006("}, d2 = {"Lcom/amazon/mp3/playlist/CreatePublicPlaylistWarningView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "negativeButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "getNegativeButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "negativeButton$delegate", "Lkotlin/Lazy;", "positiveButton", "getPositiveButton", "positiveButton$delegate", "subtitleView", "Landroid/widget/TextView;", "getSubtitleView", "()Landroid/widget/TextView;", "subtitleView$delegate", "titleView", "getTitleView", "titleView$delegate", "initStyleSheet", "", "setNegativeButton", "textResId", "", "onClickListener", "Landroid/view/View$OnClickListener;", "text", "", "setNegativeButtonEnabled", Constants.ENABLE_DISABLE, "", "setPositiveButton", "setPositiveButtonEnabled", "setSubtitle", "setTitle", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CreatePublicPlaylistWarningView extends LinearLayout {

    /* JADX INFO: renamed from: negativeButton$delegate, reason: from kotlin metadata */
    private final Lazy negativeButton;

    /* JADX INFO: renamed from: positiveButton$delegate, reason: from kotlin metadata */
    private final Lazy positiveButton;

    /* JADX INFO: renamed from: subtitleView$delegate, reason: from kotlin metadata */
    private final Lazy subtitleView;

    /* JADX INFO: renamed from: titleView$delegate, reason: from kotlin metadata */
    private final Lazy titleView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CreatePublicPlaylistWarningView(Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.titleView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.mp3.playlist.CreatePublicPlaylistWarningView$titleView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R.id.title);
            }
        });
        this.subtitleView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.mp3.playlist.CreatePublicPlaylistWarningView$subtitleView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R.id.subtitle);
            }
        });
        this.positiveButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.playlist.CreatePublicPlaylistWarningView$positiveButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.positive_button);
            }
        });
        this.negativeButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.playlist.CreatePublicPlaylistWarningView$negativeButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.negative_button);
            }
        });
        setOrientation(1);
        LayoutInflater.from(context).inflate(R.layout.create_public_playlist_warning_dialog_layout, this);
        initStyleSheet(styleSheet);
    }

    private final TextView getTitleView() {
        Object value = this.titleView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-titleView>(...)");
        return (TextView) value;
    }

    private final TextView getSubtitleView() {
        Object value = this.subtitleView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-subtitleView>(...)");
        return (TextView) value;
    }

    private final BaseButton getPositiveButton() {
        Object value = this.positiveButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-positiveButton>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getNegativeButton() {
        Object value = this.negativeButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-negativeButton>(...)");
        return (BaseButton) value;
    }

    public final void setTitle(int textResId) {
        getTitleView().setText(textResId);
    }

    public final void setTitle(String text) {
        getTitleView().setText(text);
    }

    public final void setSubtitle(int textResId) {
        getSubtitleView().setText(textResId);
    }

    public final void setSubtitle(String text) {
        getSubtitleView().setText(text);
    }

    public final void setPositiveButton(int textResId, View.OnClickListener onClickListener) {
        String string = getResources().getString(textResId);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(textResId)");
        setPositiveButton(string, onClickListener);
    }

    public final void setPositiveButton(String text, View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(text, "text");
        if (TextUtils.isEmpty(text)) {
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

    public final void setNegativeButton(int textResId, View.OnClickListener onClickListener) {
        String string = getResources().getString(textResId);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(textResId)");
        setNegativeButton(string, onClickListener);
    }

    public final void setNegativeButton(String text, View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(text, "text");
        if (TextUtils.isEmpty(text)) {
            getNegativeButton().setVisibility(8);
            return;
        }
        getNegativeButton().setText(text);
        getNegativeButton().setOnClickListener(onClickListener);
        getNegativeButton().setVisibility(0);
    }

    public final void setNegativeButtonEnabled(boolean isEnabled) {
        getNegativeButton().setEnabled(isEnabled);
    }

    private final void initStyleSheet(StyleSheet styleSheet) {
        FontStyle fontStyle = styleSheet.getFontStyle(FontStyleKey.HEADLINE_4);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(getTitleView(), fontStyle);
        }
        FontStyle fontStyle2 = styleSheet.getFontStyle(FontStyleKey.PRIMARY);
        if (fontStyle2 != null) {
            FontUtil.INSTANCE.applyFontStyle(getSubtitleView(), fontStyle2);
        }
        ButtonSizeKey buttonSizeKey = ButtonSizeKey.MEDIUM;
        BaseButton.StyleBuilder buttonBuilder = styleSheet.getButtonBuilder(buttonSizeKey, ButtonStyleKey.SOLID);
        if (buttonBuilder != null) {
            buttonBuilder.applyStyle(getPositiveButton());
        }
        BaseButton.StyleBuilder buttonBuilder2 = styleSheet.getButtonBuilder(buttonSizeKey, ButtonStyleKey.OUTLINE);
        if (buttonBuilder2 != null) {
            buttonBuilder2.applyStyle(getNegativeButton());
        }
        Integer spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.BASE);
        if (spacerInPixels != null) {
            LayoutParamUtils.INSTANCE.setLayoutMargins(getTitleView(), -2, -2, (240 & 8) != 0 ? Boolean.FALSE : Boolean.FALSE, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : Integer.valueOf(spacerInPixels.intValue()), (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
        }
        Integer spacerInPixels2 = styleSheet.getSpacerInPixels(SpacerKey.MINI);
        if (spacerInPixels2 != null) {
            LayoutParamUtils.INSTANCE.setLayoutMargins(getSubtitleView(), -2, -2, (240 & 8) != 0 ? Boolean.FALSE : Boolean.FALSE, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : Integer.valueOf(spacerInPixels2.intValue()), (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
        }
        Integer spacerInPixels3 = styleSheet.getSpacerInPixels(SpacerKey.SMALL);
        if (spacerInPixels3 != null) {
            int iIntValue = spacerInPixels3.intValue();
            LayoutParamUtils.INSTANCE.setLayoutMargins(getPositiveButton(), -1, -2, (240 & 8) != 0 ? Boolean.FALSE : Boolean.FALSE, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : Integer.valueOf(iIntValue), (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : Integer.valueOf(iIntValue));
        }
        LayoutParamUtils.INSTANCE.setLayoutMargins(getNegativeButton(), -1, -2, (240 & 8) != 0 ? Boolean.FALSE : Boolean.FALSE, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
    }
}
