package com.amazon.music.views.library.views;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.styles.GridSize;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseHeaderView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\"\u001a\u00020#J\b\u0010$\u001a\u00020#H\u0002J\b\u0010%\u001a\u00020#H\u0002JC\u0010&\u001a\u00020#2\u0006\u0010'\u001a\u00020(2\n\b\u0002\u0010)\u001a\u0004\u0018\u00010*2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010*2\n\b\u0002\u0010,\u001a\u0004\u0018\u00010*2\n\b\u0002\u0010-\u001a\u0004\u0018\u00010*¢\u0006\u0002\u0010.R$\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001b\u0010\u000e\u001a\u00020\u000f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011R$\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0015\u0010\u000b\"\u0004\b\u0016\u0010\rR\u001b\u0010\u0017\u001a\u00020\u000f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u0013\u001a\u0004\b\u0018\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR$\u0010\u001c\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001d\u0010\u000b\"\u0004\b\u001e\u0010\rR\u001b\u0010\u001f\u001a\u00020\u000f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\u0013\u001a\u0004\b \u0010\u0011¨\u0006/"}, d2 = {"Lcom/amazon/music/views/library/views/BaseHeaderView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "title", "", "headerText", "getHeaderText", "()Ljava/lang/String;", "setHeaderText", "(Ljava/lang/String;)V", "headerTextView", "Landroid/widget/TextView;", "getHeaderTextView", "()Landroid/widget/TextView;", "headerTextView$delegate", "Lkotlin/Lazy;", "secondaryHeaderText", "getSecondaryHeaderText", "setSecondaryHeaderText", "secondaryHeaderTextView", "getSecondaryHeaderTextView", "secondaryHeaderTextView$delegate", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "tertiaryHeaderText", "getTertiaryHeaderText", "setTertiaryHeaderText", "tertiaryHeaderTextView", "getTertiaryHeaderTextView", "tertiaryHeaderTextView$delegate", "applyGridListHeaderLayout", "", "initMargins", "initViewStyling", "setViewLayout", "view", "Landroid/view/View;", "top", "", "left", "bottom", "right", "(Landroid/view/View;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BaseHeaderView extends ConstraintLayout {

    /* JADX INFO: renamed from: headerTextView$delegate, reason: from kotlin metadata */
    private final Lazy headerTextView;

    /* JADX INFO: renamed from: secondaryHeaderTextView$delegate, reason: from kotlin metadata */
    private final Lazy secondaryHeaderTextView;
    private final StyleSheet styleSheet;

    /* JADX INFO: renamed from: tertiaryHeaderTextView$delegate, reason: from kotlin metadata */
    private final Lazy tertiaryHeaderTextView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseHeaderView(Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        this.headerTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.BaseHeaderView$headerTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                View viewFindViewById = this.this$0.findViewById(R$id.header_text);
                if (viewFindViewById != null) {
                    return (TextView) viewFindViewById;
                }
                throw new NullPointerException("null cannot be cast to non-null type android.widget.TextView");
            }
        });
        this.secondaryHeaderTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.BaseHeaderView$secondaryHeaderTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                View viewFindViewById = this.this$0.findViewById(R$id.secondary_header_text);
                if (viewFindViewById != null) {
                    return (TextView) viewFindViewById;
                }
                throw new NullPointerException("null cannot be cast to non-null type android.widget.TextView");
            }
        });
        this.tertiaryHeaderTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.music.views.library.views.BaseHeaderView$tertiaryHeaderTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                View viewFindViewById = this.this$0.findViewById(R$id.tertiary_header_text);
                if (viewFindViewById != null) {
                    return (TextView) viewFindViewById;
                }
                throw new NullPointerException("null cannot be cast to non-null type android.widget.TextView");
            }
        });
        View.inflate(context, R$layout.dmmviewlibrary_base_detail_header_layout, this);
        initViewStyling();
        initMargins();
    }

    public final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    public final TextView getHeaderTextView() {
        return (TextView) this.headerTextView.getValue();
    }

    public final TextView getSecondaryHeaderTextView() {
        return (TextView) this.secondaryHeaderTextView.getValue();
    }

    public final TextView getTertiaryHeaderTextView() {
        return (TextView) this.tertiaryHeaderTextView.getValue();
    }

    public final String getHeaderText() {
        return getHeaderTextView().getText().toString();
    }

    public final void setHeaderText(String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        getHeaderTextView().setText(title);
        getHeaderTextView().setVisibility(!TextUtils.isEmpty(title) ? 0 : 8);
    }

    public final String getSecondaryHeaderText() {
        return getSecondaryHeaderTextView().getText().toString();
    }

    public final void setSecondaryHeaderText(String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        getSecondaryHeaderTextView().setText(title);
        getSecondaryHeaderTextView().setVisibility(!TextUtils.isEmpty(title) ? 0 : 8);
    }

    public final String getTertiaryHeaderText() {
        return getTertiaryHeaderTextView().getText().toString();
    }

    public final void setTertiaryHeaderText(String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        getTertiaryHeaderTextView().setText(title);
        getTertiaryHeaderTextView().setVisibility(!TextUtils.isEmpty(title) ? 0 : 8);
    }

    private final void initViewStyling() {
        Integer spacerInPixels = this.styleSheet.getSpacerInPixels(SpacerKey.MICRO);
        TextView headerTextView = getHeaderTextView();
        FontStyle fontStyle = getStyleSheet().getFontStyle(FontStyleKey.HEADLINE_2);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(headerTextView, fontStyle);
        }
        setViewLayout$default(this, headerTextView, spacerInPixels, null, 0, null, 20, null);
        TextView secondaryHeaderTextView = getSecondaryHeaderTextView();
        FontStyle fontStyle2 = getStyleSheet().getFontStyle(FontStyleKey.PRIMARY);
        if (fontStyle2 != null) {
            FontUtil.INSTANCE.applyFontStyle(secondaryHeaderTextView, fontStyle2);
        }
        setViewLayout$default(this, secondaryHeaderTextView, spacerInPixels, null, 0, null, 20, null);
        TextView tertiaryHeaderTextView = getTertiaryHeaderTextView();
        FontStyle fontStyle3 = getStyleSheet().getFontStyle(FontStyleKey.SECONDARY);
        if (fontStyle3 != null) {
            FontUtil.INSTANCE.applyFontStyle(tertiaryHeaderTextView, fontStyle3);
        }
        setViewLayout$default(this, tertiaryHeaderTextView, spacerInPixels, null, 0, null, 20, null);
    }

    private final void initMargins() {
        StyleSheet styleSheet = this.styleSheet;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        GridSize gridSize = styleSheet.getGridSize(context);
        int marginSpace = gridSize == null ? 0 : gridSize.getMarginSpace();
        StyleSheet styleSheet2 = this.styleSheet;
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "context");
        GridSize gridSize2 = styleSheet2.getGridSize(context2);
        int columnSpace = marginSpace + (gridSize2 == null ? 0 : gridSize2.getColumnSpace());
        Integer spacerInPixels = this.styleSheet.getSpacerInPixels(SpacerKey.MEDIUM);
        int iIntValue = spacerInPixels == null ? 0 : spacerInPixels.intValue();
        Integer spacerInPixels2 = this.styleSheet.getSpacerInPixels(SpacerKey.SMALL);
        setViewLayout$default(this, this, null, Integer.valueOf(columnSpace), Integer.valueOf(spacerInPixels2 == null ? 0 : spacerInPixels2.intValue()), Integer.valueOf(columnSpace), 2, null);
        setPadding(0, iIntValue, 0, 0);
    }

    public final void applyGridListHeaderLayout() {
        TextView headerTextView = getHeaderTextView();
        FontStyle fontStyle = getStyleSheet().getFontStyle(FontStyleKey.HEADLINE_3);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(headerTextView, fontStyle);
        }
        Integer spacerInPixels = this.styleSheet.getSpacerInPixels(SpacerKey.MEDIUM);
        int iIntValue = spacerInPixels == null ? 0 : spacerInPixels.intValue();
        Integer spacerInPixels2 = this.styleSheet.getSpacerInPixels(SpacerKey.SMALL);
        setViewLayout$default(this, this, 0, null, Integer.valueOf(spacerInPixels2 == null ? 0 : spacerInPixels2.intValue()), null, 20, null);
        setPadding(0, iIntValue, 0, 0);
        requestLayout();
    }

    public static /* synthetic */ void setViewLayout$default(BaseHeaderView baseHeaderView, View view, Integer num, Integer num2, Integer num3, Integer num4, int i, Object obj) {
        baseHeaderView.setViewLayout(view, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : num2, (i & 8) != 0 ? null : num3, (i & 16) != 0 ? null : num4);
    }

    public final void setViewLayout(View view, Integer top, Integer left, Integer bottom, Integer right) {
        Intrinsics.checkNotNullParameter(view, "view");
        LayoutParamUtils.INSTANCE.setLayoutMargins(view, -1, -2, Boolean.TRUE, left, top, right, bottom);
    }
}
