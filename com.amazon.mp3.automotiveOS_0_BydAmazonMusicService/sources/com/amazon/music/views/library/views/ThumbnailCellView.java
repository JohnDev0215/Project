package com.amazon.music.views.library.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Outline;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewOutlineProvider;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.CornerSizeKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.BitmapEffectUtils;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import com.google.android.material.button.MaterialButton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: ThumbnailCellView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0018\u001a\u00020\u0017\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u0019\u0010\u001aJ\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0004\u001a\u00020\u0002H\u0002J\u000e\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005J\u000e\u0010\n\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\bJ\u000e\u0010\r\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u000bR\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0013\u001a\u00020\u00018\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0015\u001a\u0004\u0018\u00010\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016¨\u0006\u001b"}, d2 = {"Lcom/amazon/music/views/library/views/ThumbnailCellView;", "Lcom/amazon/ui/foundations/views/BaseButton;", "", "initStyle", "initMargins", "", "width", "updateWidth", "", "title", "setTitle", "Landroid/graphics/Bitmap;", "bitmap", "setBackgroundImage", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "cellView", "Lcom/amazon/ui/foundations/views/BaseButton;", "backgroundColor", "Ljava/lang/Integer;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class ThumbnailCellView extends BaseButton {
    private final Integer backgroundColor;
    private BaseButton cellView;
    private final StyleSheet styleSheet;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ThumbnailCellView(Context context, StyleSheet styleSheet) {
        super(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
        this.styleSheet = styleSheet;
        this.backgroundColor = styleSheet == null ? null : styleSheet.getColor(ColorKey.COLOR_SECONDARY, AlphaKey.GLASS_4);
        initStyle();
        setId(R$id.thumbnail_button);
        MaterialButton button = getButton();
        button.setHeight((int) getResources().getDimension(R$dimen.thumbnail_cell_height));
        button.setMaxLines(2);
        button.setEllipsize(TextUtils.TruncateAt.END);
        button.setVisibility(0);
        FontUtil fontUtil = FontUtil.INSTANCE;
        StyleSheet styleSheet2 = getStyleSheet();
        Intrinsics.checkNotNull(styleSheet2);
        fontUtil.applyAutoSize(button, styleSheet2.getTextSizeListInRange(FontStyleKey.LABEL, FontStyleKey.HEADLINE_4));
        this.cellView = this;
    }

    public final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    private final void initStyle() {
        FontStyle fontStyle;
        Integer cornerSize;
        BaseButton.StyleBuilder buttonBuilder;
        BaseButton.StyleBuilder styleBuilderWithWidth;
        initMargins();
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet != null && (buttonBuilder = styleSheet.getButtonBuilder(ButtonSizeKey.LARGE, ButtonStyleKey.GLASS)) != null && (styleBuilderWithWidth = buttonBuilder.withWidth(-1)) != null) {
            styleBuilderWithWidth.applyStyle(this);
        }
        StyleSheet styleSheet2 = this.styleSheet;
        if (styleSheet2 != null && (cornerSize = styleSheet2.getCornerSize(CornerSizeKey.MEDIUM)) != null) {
            final int iIntValue = cornerSize.intValue();
            setCornerRadius(iIntValue);
            setOutlineProvider(new ViewOutlineProvider() { // from class: com.amazon.music.views.library.views.ThumbnailCellView$initStyle$1$1
                @Override // android.view.ViewOutlineProvider
                public void getOutline(View view, Outline outline) {
                    if (view == null) {
                        return;
                    }
                    int i = iIntValue;
                    if (outline == null) {
                        return;
                    }
                    outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), i);
                }
            });
        }
        Integer num = this.backgroundColor;
        if (num != null) {
            getButton().setBackgroundColor(num.intValue());
        }
        StyleSheet styleSheet3 = this.styleSheet;
        if (styleSheet3 == null || (fontStyle = styleSheet3.getFontStyle(FontStyleKey.HEADLINE_4)) == null) {
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
        MaterialButton button = getButton();
        int i = 2;
        if (!StringsKt__StringsKt.contains$default((CharSequence) title, ' ', false, 2, (Object) null) && !StringsKt__StringsKt.contains$default((CharSequence) title, '-', false, 2, (Object) null)) {
            i = 1;
        }
        button.setMaxLines(i);
    }

    public final void setBackgroundImage(final Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        post(new Runnable() { // from class: com.amazon.music.views.library.views.ThumbnailCellView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ThumbnailCellView.m2433setBackgroundImage$lambda6(this.f$0, bitmap);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setBackgroundImage$lambda-6, reason: not valid java name */
    public static final void m2433setBackgroundImage$lambda6(ThumbnailCellView this$0, Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bitmap, "$bitmap");
        if (this$0.cellView.getWidth() <= 0 || this$0.cellView.getHeight() <= 0) {
            return;
        }
        BitmapEffectUtils bitmapEffectUtils = BitmapEffectUtils.INSTANCE;
        Bitmap widthScaledBitmapWithHorizontalCrop = bitmapEffectUtils.getWidthScaledBitmapWithHorizontalCrop(bitmap, this$0.cellView.getWidth(), this$0.cellView.getHeight());
        Integer num = this$0.backgroundColor;
        if (num == null) {
            return;
        }
        Bitmap colorOverlayBitmap = bitmapEffectUtils.getColorOverlayBitmap(widthScaledBitmapWithHorizontalCrop, num.intValue());
        this$0.cellView.setBackground(null);
        this$0.cellView.setBackground(new BitmapDrawable(this$0.getResources(), colorOverlayBitmap));
        this$0.cellView.setClipToOutline(true);
    }
}
