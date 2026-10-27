package defpackage;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.util.ArrayList;

/* JADX INFO: compiled from: DropdownFieldAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class be0<T> extends ArrayAdapter<T> {
    public be0(Activity activity, int i, int i2, ArrayList arrayList) {
        super(activity, i, i2, new ArrayList(arrayList));
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        View view2 = super.getView(i, view, viewGroup);
        Rect rect = new Rect();
        viewGroup.getBackground().getPadding(rect);
        view2.setPadding(view2.getPaddingLeft() + rect.left, view2.getPaddingTop(), view2.getPaddingRight() + rect.right, view2.getPaddingBottom());
        return view2;
    }

    public be0(int i, Activity activity, ArrayList arrayList) {
        super(activity, i, new ArrayList(arrayList));
    }
}
