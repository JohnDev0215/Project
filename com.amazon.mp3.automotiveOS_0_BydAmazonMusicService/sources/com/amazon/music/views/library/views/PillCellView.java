package com.amazon.music.views.library.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.FrameLayout;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PillCellView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\u000e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014JK\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0017¢\u0006\u0002\u0010\u001dJ\u000e\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020 J\u0018\u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0002R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006&"}, d2 = {"Lcom/amazon/music/views/library/views/PillCellView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "baseButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "getBaseButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "baseButton$delegate", "Lkotlin/Lazy;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "initStyle", "", "setIcon", ContextMappingConstants.IMAGE, "Landroid/graphics/drawable/Drawable;", "setMargins", "height", "", "width", "left", "top", "right", "bottom", "(IILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "setText", "title", "", "updateStyle", "buttonSize", "Lcom/amazon/music/views/library/styles/keys/ButtonSizeKey;", "buttonStyle", "Lcom/amazon/music/views/library/styles/keys/ButtonStyleKey;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PillCellView extends FrameLayout {

    /* JADX INFO: renamed from: baseButton$delegate, reason: from kotlin metadata */
    private final Lazy baseButton;
    private final StyleSheet styleSheet;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PillCellView(Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.styleSheet = styleSheet;
        this.baseButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.PillCellView$baseButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.pill_button);
            }
        });
        View.inflate(context, R$layout.dmmviewlibrary_pill_navigator_layout, this);
        setId(R$id.nav_button);
        initStyle();
    }

    public final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    private final BaseButton getBaseButton() {
        Object value = this.baseButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-baseButton>(...)");
        return (BaseButton) value;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public PillCellView(Context context) {
        this(context, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public final void setMargins(int height, int width, Integer left, Integer top, Integer right, Integer bottom) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(this, width, height, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : left, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : right, (240 & 128) != 0 ? null : bottom);
    }

    public final void setText(String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        getBaseButton().setText(title);
    }

    public final void setIcon(Drawable image) {
        Intrinsics.checkNotNullParameter(image, "image");
        getBaseButton().setIcon(image);
        updateStyle(ButtonSizeKey.LARGE_WITH_ICON, ButtonStyleKey.GLASS);
    }

    private final void initStyle() {
        BaseButton.StyleBuilder buttonBuilder;
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet != null && (buttonBuilder = styleSheet.getButtonBuilder(ButtonSizeKey.LARGE, ButtonStyleKey.GLASS)) != null) {
            buttonBuilder.applyStyle(getBaseButton());
        }
        getBaseButton().getButton().setMaxLines(1);
        getBaseButton().getButton().setVisibility(0);
        getBaseButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.views.library.views.PillCellView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PillCellView.m2424initStyle$lambda0(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initStyle$lambda-0, reason: not valid java name */
    public static final void m2424initStyle$lambda0(PillCellView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.performClick();
    }

    private final void updateStyle(ButtonSizeKey buttonSize, ButtonStyleKey buttonStyle) {
        BaseButton.StyleBuilder buttonBuilder;
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet == null || (buttonBuilder = styleSheet.getButtonBuilder(buttonSize, buttonStyle)) == null) {
            return;
        }
        buttonBuilder.applyStyle(getBaseButton());
    }
}
