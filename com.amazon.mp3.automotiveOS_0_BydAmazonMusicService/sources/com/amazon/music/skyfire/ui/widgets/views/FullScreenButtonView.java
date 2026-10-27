package com.amazon.music.skyfire.ui.widgets.views;

import android.content.Context;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FullScreenButtonView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\fH\u0002J\u000e\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0013R\u000e\u0010\b\u001a\u00020\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/views/FullScreenButtonView;", "Lcom/amazon/ui/foundations/views/BaseButton;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "buttonView", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "initMargins", "", "initStyle", "setTitle", "title", "", "updateWidth", "width", "", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FullScreenButtonView extends BaseButton {
    private BaseButton buttonView;
    private final StyleSheet styleSheet;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FullScreenButtonView(Context context, StyleSheet styleSheet) {
        super(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
        this.styleSheet = styleSheet;
        initStyle();
        setId(R$id.full_screen_button);
        getButton().setVisibility(0);
        this.buttonView = this;
    }

    public final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FullScreenButtonView(Context context) {
        this(context, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    private final void initStyle() {
        FontStyle fontStyle;
        BaseButton.StyleBuilder buttonBuilder;
        BaseButton.StyleBuilder styleBuilderWithWidth;
        initMargins();
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet != null && (buttonBuilder = styleSheet.getButtonBuilder(ButtonSizeKey.LARGE, ButtonStyleKey.GLASS)) != null && (styleBuilderWithWidth = buttonBuilder.withWidth(-1)) != null) {
            styleBuilderWithWidth.applyStyle(this);
        }
        StyleSheet styleSheet2 = this.styleSheet;
        if (styleSheet2 == null || (fontStyle = styleSheet2.getFontStyle(FontStyleKey.PRIMARY)) == null) {
            return;
        }
        FontUtil.INSTANCE.applyFontStyle(getButton(), fontStyle);
    }

    private final void initMargins() {
        LayoutParamUtils.INSTANCE.setLayoutMargins(this, -1, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
    }

    public final void updateWidth(int width) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(this, width, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
    }

    public final void setTitle(String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        setText(title);
    }
}
