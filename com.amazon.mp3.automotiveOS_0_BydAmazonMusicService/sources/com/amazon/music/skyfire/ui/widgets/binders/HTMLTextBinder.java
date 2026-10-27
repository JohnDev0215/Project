package com.amazon.music.skyfire.ui.widgets.binders;

import Touch.WidgetsInterface.v1_0.HTMLTextElement;
import android.content.Context;
import android.text.Html;
import android.view.ViewGroup;
import android.widget.TextView;
import com.amazon.music.skyfire.ui.ktx.ContextKt;
import com.amazon.music.skyfire.ui.ktx.ViewKt;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.ScreenSizeKey;
import com.amazon.ui.foundations.styles.GridSize;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HTMLTextBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0003H\u0016J\u0012\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/binders/HTMLTextBinder;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "LTouch/WidgetsInterface/v1_0/HTMLTextElement;", "Landroid/widget/TextView;", "()V", "bind", "", "element", "view", "createView", "parent", "Landroid/view/ViewGroup;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HTMLTextBinder extends ElementBinder<HTMLTextElement, TextView> {
    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public TextView createView(ViewGroup parent) {
        return new TextView(getContext());
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public void bind(HTMLTextElement element, TextView view) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        StyleSheet styleSheet = getStyleSheet();
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        GridSize gridSize = styleSheet.getGridSize(context);
        if (gridSize != null) {
            int marginSpace = gridSize.getMarginSpace() + gridSize.getColumnSpace();
            Context context2 = view.getContext();
            Intrinsics.checkNotNullExpressionValue(context2, "context");
            int screenWidth = ContextKt.getScreenWidth(context2) - (marginSpace * 2);
            StyleSheet styleSheet2 = getStyleSheet();
            Context context3 = view.getContext();
            Intrinsics.checkNotNullExpressionValue(context3, "context");
            if (styleSheet2.getWidthScreenSizeKey(context3).compareTo(ScreenSizeKey.XLARGE) >= 0) {
                view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), screenWidth / 3, view.getPaddingBottom());
            }
        }
        ViewKt.applyFontStyle(view, getStyleSheet(), FontStyleKey.PRIMARY);
        Integer color = getStyleSheet().getColor(ColorKey.COLOR_PRIMARY, AlphaKey.GLASS_4);
        Intrinsics.checkNotNull(color);
        view.setTextColor(color.intValue());
        view.setText(Html.fromHtml(element.text(), 63));
    }
}
