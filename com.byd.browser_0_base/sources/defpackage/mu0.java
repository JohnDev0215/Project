package defpackage;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import defpackage.om3;
import java.util.ArrayList;
import java.util.WeakHashMap;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$style;

/* JADX INFO: compiled from: HintedDropDownAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class mu0<T> extends be0<T> {
    public final int e;
    public TextView f;

    /* JADX WARN: Multi-variable type inference failed */
    public mu0(Activity activity, int i, int i2, ArrayList arrayList, String str) {
        super(activity, i, i2, arrayList);
        insert(str, 0);
        this.e = i2;
    }

    @Override // android.widget.ArrayAdapter, android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        TextView textView = view == null ? null : (TextView) view.findViewById(this.e);
        this.f = textView;
        if (textView != null) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.e.i(view, om3.e.e(view), 0, om3.e.d(view), 0);
            this.f.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
            c6.f(this.f, R$style.TextAppearance_TextLarge_Primary);
        }
        View dropDownView = super.getDropDownView(i, view, viewGroup);
        if (i == 0) {
            WeakHashMap<View, in3> weakHashMap2 = om3.a;
            om3.e.i(dropDownView, om3.e.e(dropDownView), getContext().getResources().getDimensionPixelSize(R$dimen.editor_dialog_section_small_spacing), om3.e.d(dropDownView), dropDownView.getPaddingBottom());
        }
        return dropDownView;
    }
}
