package com.amazon.music.views.library.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseImage;
import com.facebook.react.modules.appstate.AppStateModule;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CompactHeaderView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u001b\u001a\u00020\bJ\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020 J\u000e\u0010!\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020\u000eJ\u0010\u0010#\u001a\u00020\u001d2\b\u0010$\u001a\u0004\u0018\u00010%J\u0010\u0010&\u001a\u00020\u001d2\b\u0010$\u001a\u0004\u0018\u00010%J\b\u0010'\u001a\u00020\u001dH\u0002R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u0012\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000fR\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\f\u001a\u0004\b\u0012\u0010\u0013R\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u000eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000fR\u001b\u0010\u0016\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\f\u001a\u0004\b\u0017\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006("}, d2 = {"Lcom/amazon/music/views/library/views/CompactHeaderView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "baseImage", "Lcom/amazon/ui/foundations/views/BaseImage;", "getBaseImage", "()Lcom/amazon/ui/foundations/views/BaseImage;", "baseImage$delegate", "Lkotlin/Lazy;", "baseSpace", "", "Ljava/lang/Integer;", ContextMappingConstants.LABEL, "Landroid/widget/TextView;", "getLabel", "()Landroid/widget/TextView;", "label$delegate", "mediumSpace", ContextMappingConstants.PRIMARY_TEXT, "getPrimaryText", "primaryText$delegate", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "getImage", "initViewMargins", "", "setHeaderBackground", AppStateModule.APP_STATE_BACKGROUND, "Landroid/graphics/drawable/Drawable;", "setHeaderBackgroundColor", "color", "setLabel", "text", "", "setPrimaryText", "setStyle", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CompactHeaderView extends ConstraintLayout {

    /* JADX INFO: renamed from: baseImage$delegate, reason: from kotlin metadata */
    private final Lazy baseImage;
    private final Integer baseSpace;

    /* JADX INFO: renamed from: label$delegate, reason: from kotlin metadata */
    private final Lazy label;
    private final Integer mediumSpace;

    /* JADX INFO: renamed from: primaryText$delegate, reason: from kotlin metadata */
    private final Lazy primaryText;
    private final StyleSheet styleSheet;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CompactHeaderView(Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.baseImage = LazyKt__LazyJVMKt.lazy(new Function0<BaseImage>() { // from class: com.amazon.music.views.library.views.CompactHeaderView$baseImage$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseImage invoke() {
                return (BaseImage) this.this$0.findViewById(R$id.image);
            }
        });
        this.label = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.CompactHeaderView$label$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.label_text);
            }
        });
        this.primaryText = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.CompactHeaderView$primaryText$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R$id.primary_text);
            }
        });
        this.baseSpace = styleSheet.getSpacerInPixels(SpacerKey.BASE);
        this.mediumSpace = styleSheet.getSpacerInPixels(SpacerKey.MEDIUM);
        View.inflate(context, R$layout.dmmviewlibrary_compact_header, this);
        setStyle();
    }

    public final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    private final BaseImage getBaseImage() {
        Object value = this.baseImage.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-baseImage>(...)");
        return (BaseImage) value;
    }

    private final TextView getLabel() {
        Object value = this.label.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-label>(...)");
        return (TextView) value;
    }

    private final TextView getPrimaryText() {
        Object value = this.primaryText.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-primaryText>(...)");
        return (TextView) value;
    }

    public final BaseImage getImage() {
        return getBaseImage();
    }

    public final void setLabel(String text) {
        getLabel().setText(text);
    }

    public final void setPrimaryText(String text) {
        getPrimaryText().setText(text);
    }

    public final void setHeaderBackground(Drawable background) {
        Intrinsics.checkNotNullParameter(background, "background");
        setBackground(background);
    }

    public final void setHeaderBackgroundColor(int color) {
        setBackgroundColor(color);
    }

    public final void initViewMargins() {
        LayoutParamUtils layoutParamUtils = LayoutParamUtils.INSTANCE;
        TextView primaryText = getPrimaryText();
        Integer num = this.baseSpace;
        Integer num2 = this.mediumSpace;
        Boolean bool = Boolean.FALSE;
        layoutParamUtils.setLayoutMargins(primaryText, 0, -2, bool, num, 0, num2, 0);
        BaseImage baseImage = getBaseImage();
        Integer num3 = this.baseSpace;
        layoutParamUtils.setLayoutMargins(baseImage, -2, -2, bool, this.mediumSpace, num3, 0, num3);
    }

    private final void setStyle() {
        StyleSheet styleSheet = this.styleSheet;
        FontStyle fontStyle = styleSheet.getFontStyle(FontStyleKey.HEADLINE_4);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(getPrimaryText(), fontStyle);
        }
        FontStyle fontStyle2 = styleSheet.getFontStyle(FontStyleKey.LABEL);
        if (fontStyle2 == null) {
            return;
        }
        FontUtil.INSTANCE.applyFontStyle(getLabel(), fontStyle2);
    }
}
