package org.chromium.components.embedder_support.delegate;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import defpackage.bs1;
import defpackage.fv;
import org.chromium.components.embedder_support.R$dimen;
import org.chromium.components.embedder_support.R$drawable;
import org.chromium.components.embedder_support.R$id;

/* JADX INFO: compiled from: ColorSuggestionListAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a extends BaseAdapter implements View.OnClickListener {
    public final Context e;
    public final ColorSuggestion[] f;
    public InterfaceC0131a g;
    public int h;

    /* JADX INFO: renamed from: org.chromium.components.embedder_support.delegate.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ColorSuggestionListAdapter.java */
    public interface InterfaceC0131a {
    }

    public a(Context context, ColorSuggestion[] colorSuggestionArr) {
        this.e = context;
        this.f = colorSuggestionArr;
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        return ((this.f.length + 4) - 1) / 4;
    }

    @Override // android.widget.Adapter
    public final Object getItem(int i) {
        return null;
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        LinearLayout linearLayout;
        if (view instanceof LinearLayout) {
            linearLayout = (LinearLayout) view;
        } else {
            Context context = this.e;
            linearLayout = new LinearLayout(context);
            linearLayout.setLayoutParams(new AbsListView.LayoutParams(-1, -2));
            linearLayout.setOrientation(0);
            linearLayout.setBackgroundColor(-1);
            int dimensionPixelOffset = context.getResources().getDimensionPixelOffset(R$dimen.color_button_height);
            for (int i2 = 0; i2 < 4; i2++) {
                View view2 = new View(context);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, dimensionPixelOffset, 1.0f);
                layoutParams.setMarginStart(-1);
                if (i2 == 3) {
                    layoutParams.setMarginEnd(-1);
                }
                view2.setLayoutParams(layoutParams);
                view2.setBackgroundResource(R$drawable.color_button_background);
                linearLayout.addView(view2);
            }
        }
        for (int i3 = 0; i3 < 4; i3++) {
            View childAt = linearLayout.getChildAt(i3);
            int i4 = (i * 4) + i3;
            ColorSuggestion[] colorSuggestionArr = this.f;
            if (i4 >= colorSuggestionArr.length) {
                childAt.setTag(null);
                childAt.setContentDescription(null);
                childAt.setVisibility(4);
            } else {
                childAt.setTag(colorSuggestionArr[i4]);
                childAt.setVisibility(0);
                ColorSuggestion colorSuggestion = colorSuggestionArr[i4];
                ((GradientDrawable) ((LayerDrawable) childAt.getBackground()).findDrawableByLayerId(R$id.color_button_swatch)).setColor(colorSuggestion.a);
                String str = colorSuggestion.b;
                if (TextUtils.isEmpty(str)) {
                    str = String.format("#%06X", Integer.valueOf(16777215 & colorSuggestion.a));
                }
                childAt.setContentDescription(str);
                childAt.setOnClickListener(this);
                childAt.setAccessibilityDelegate(new fv(this, i4, colorSuggestion));
            }
        }
        return linearLayout;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        ColorSuggestion colorSuggestion;
        if (this.g == null || (colorSuggestion = (ColorSuggestion) view.getTag()) == null) {
            return;
        }
        ColorPickerSimple colorPickerSimple = (ColorPickerSimple) this.g;
        bs1 bs1Var = colorPickerSimple.e;
        int i = colorSuggestion.a;
        bs1Var.a(i);
        colorPickerSimple.f.h = i;
    }

    public void setOnColorSuggestionClickListener(InterfaceC0131a interfaceC0131a) {
        this.g = interfaceC0131a;
    }
}
