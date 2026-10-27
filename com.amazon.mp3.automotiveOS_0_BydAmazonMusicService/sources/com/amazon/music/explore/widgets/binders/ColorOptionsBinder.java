package com.amazon.music.explore.widgets.binders;

import Touch.WidgetsInterface.v1_0.ColorOptionsElement;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import com.amazon.music.explore.R;
import com.amazon.music.explore.widgets.views.ColorOptionsView;
import com.amazon.music.skyfire.ui.ktx.ViewGroupKt;
import com.amazon.music.skyfire.ui.widgets.binders.ElementBinder;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.ui.foundations.ktx.TextViewKt;
import com.amazon.ui.foundations.ktx.ViewKt;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ColorOptionsBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0003H\u0016J\u001e\u0010\t\u001a\u0004\u0018\u00010\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\fH\u0002J\u0012\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/explore/widgets/binders/ColorOptionsBinder;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "LTouch/WidgetsInterface/v1_0/ColorOptionsElement;", "Lcom/amazon/music/explore/widgets/views/ColorOptionsView;", "()V", "bind", "", "element", "view", "createAndBindColorView", "Landroid/view/View;", "colorOption", "", "", "createView", "parent", "Landroid/view/ViewGroup;", "DMMExplore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ColorOptionsBinder extends ElementBinder<ColorOptionsElement, ColorOptionsView> {
    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public ColorOptionsView createView(ViewGroup parent) {
        return new ColorOptionsView(getContext());
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public void bind(ColorOptionsElement element, ColorOptionsView view) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        int dimension = (int) getContext().getResources().getDimension(R.dimen.spacer_8);
        view.getColorContainer().removeAllViews();
        LinearLayout colorContainer = view.getColorContainer();
        List<Map<String, String>> listColors = element.colors();
        Intrinsics.checkNotNullExpressionValue(listColors, "element\n                    .colors()");
        ArrayList arrayList = new ArrayList();
        for (Map<String, String> it : listColors) {
            Intrinsics.checkNotNullExpressionValue(it, "it");
            View viewCreateAndBindColorView = createAndBindColorView(it);
            if (viewCreateAndBindColorView != null) {
                ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(-2, -2);
                marginLayoutParams.setMargins(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, dimension, marginLayoutParams.bottomMargin);
                viewCreateAndBindColorView.setLayoutParams(marginLayoutParams);
            }
            if (viewCreateAndBindColorView != null) {
                arrayList.add(viewCreateAndBindColorView);
            }
        }
        ViewGroupKt.plusAssign(colorContainer, arrayList);
        ViewKt.collapseIf(view.getColorContainer(), element.colors().isEmpty());
        com.amazon.music.skyfire.ui.ktx.ViewKt.applyFontStyle(view.getText(), getStyleSheet(), FontStyleKey.SECONDARY);
        view.getText().setText(element.text());
        TextViewKt.collapseIfEmpty(view.getText());
    }

    private final View createAndBindColorView(Map<String, String> colorOption) {
        Integer numValueOf;
        try {
            numValueOf = Integer.valueOf(Color.parseColor((String) CollectionsKt___CollectionsKt.first(colorOption.values())));
        } catch (IllegalArgumentException unused) {
            numValueOf = null;
        }
        if (numValueOf == null) {
            return null;
        }
        int iIntValue = numValueOf.intValue();
        Drawable drawable = getContext().getDrawable(R.drawable.color_dot);
        if (drawable == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
        }
        GradientDrawable gradientDrawable = (GradientDrawable) drawable;
        gradientDrawable.setColor(iIntValue);
        int color = Color.parseColor("#FFFFFF");
        int dimension = (int) getContext().getResources().getDimension(R.dimen.spacer_2);
        if (ColorUtils.calculateContrast(iIntValue, ColorUtils.setAlphaComponent(ContextCompat.getColor(getContext(), R.color.background_color), 255)) < 4.5d) {
            iIntValue = color;
        }
        gradientDrawable.setStroke(dimension, iIntValue);
        ImageView imageView = new ImageView(getContext());
        imageView.setBackground(gradientDrawable);
        return imageView;
    }
}
