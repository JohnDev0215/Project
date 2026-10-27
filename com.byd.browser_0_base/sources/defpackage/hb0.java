package defpackage;

import android.R;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/* JADX INFO: compiled from: DistilledPagePrefsView.java */
/* JADX INFO: loaded from: classes.dex */
public final class hb0 extends ArrayAdapter<CharSequence> {
    public hb0(Context context, CharSequence[] charSequenceArr) {
        super(context, R.layout.simple_spinner_item, charSequenceArr);
    }

    public static void a(View view, int i) {
        z7.n0(i);
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            if (i == 2) {
                textView.setTypeface(Typeface.MONOSPACE);
            } else if (i == 0) {
                textView.setTypeface(Typeface.SANS_SERIF);
            } else if (i == 1) {
                textView.setTypeface(Typeface.SERIF);
            }
        }
    }

    @Override // android.widget.ArrayAdapter, android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public final View getDropDownView(int i, View view, ViewGroup viewGroup) {
        View dropDownView = super.getDropDownView(i, view, viewGroup);
        a(dropDownView, i);
        return dropDownView;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        View view2 = super.getView(i, view, viewGroup);
        a(view2, i);
        return view2;
    }
}
