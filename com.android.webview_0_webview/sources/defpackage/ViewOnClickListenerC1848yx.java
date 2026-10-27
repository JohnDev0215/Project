package defpackage;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import org.chromium.components.embedder_support.delegate.ColorPickerSimple;
import org.chromium.components.embedder_support.delegate.ColorSuggestion;

/* JADX INFO: renamed from: yx, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ViewOnClickListenerC1848yx extends BaseAdapter implements View.OnClickListener {
    public Context s;
    public ColorSuggestion[] t;
    public InterfaceC1793xx u;

    public ViewOnClickListenerC1848yx(Context context, ColorSuggestion[] colorSuggestionArr) {
        this.s = context;
        this.t = colorSuggestionArr;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return ((this.t.length + 4) - 1) / 4;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        LinearLayout linearLayout;
        if (view instanceof LinearLayout) {
            linearLayout = (LinearLayout) view;
        } else {
            linearLayout = new LinearLayout(this.s);
            linearLayout.setLayoutParams(new AbsListView.LayoutParams(-1, -2));
            linearLayout.setOrientation(0);
            linearLayout.setBackgroundColor(-1);
            int dimensionPixelOffset = this.s.getResources().getDimensionPixelOffset(AbstractC0299Qm.f1);
            for (int i2 = 0; i2 < 4; i2++) {
                View view2 = new View(this.s);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, dimensionPixelOffset, 1.0f);
                layoutParams.setMarginStart(-1);
                if (i2 == 3) {
                    layoutParams.setMarginEnd(-1);
                }
                view2.setLayoutParams(layoutParams);
                view2.setBackgroundResource(AbstractC0317Rm.M0);
                linearLayout.addView(view2);
            }
        }
        for (int i3 = 0; i3 < 4; i3++) {
            View childAt = linearLayout.getChildAt(i3);
            int i4 = (i * 4) + i3;
            ColorSuggestion[] colorSuggestionArr = this.t;
            if (i4 >= colorSuggestionArr.length) {
                childAt.setTag(null);
                childAt.setContentDescription(null);
                childAt.setVisibility(4);
            } else {
                childAt.setTag(colorSuggestionArr[i4]);
                childAt.setVisibility(0);
                ColorSuggestion colorSuggestion = this.t[i4];
                ((GradientDrawable) ((LayerDrawable) childAt.getBackground()).findDrawableByLayerId(AbstractC0371Um.k0)).setColor(colorSuggestion.a);
                String str = colorSuggestion.b;
                if (TextUtils.isEmpty(str)) {
                    str = String.format("#%06X", Integer.valueOf(colorSuggestion.a & 16777215));
                }
                childAt.setContentDescription(str);
                childAt.setOnClickListener(this);
            }
        }
        return linearLayout;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ColorSuggestion colorSuggestion;
        if (this.u == null || (colorSuggestion = (ColorSuggestion) view.getTag()) == null) {
            return;
        }
        ((ColorPickerSimple) this.u).s.a(colorSuggestion.a);
    }
}
