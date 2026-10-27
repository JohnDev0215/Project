package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import java.util.ArrayList;

/* JADX INFO: compiled from: SelectPopupAdapter.java */
/* JADX INFO: loaded from: classes3.dex */
public final class rh2 extends ArrayAdapter<zh2> {
    public final ArrayList e;
    public final boolean f;

    public rh2(int i, Context context, ArrayList arrayList) {
        super(context, i, arrayList);
        this.e = new ArrayList(arrayList);
        this.f = true;
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            if (((zh2) this.e.get(i2)).b != 2) {
                this.f = false;
                return;
            }
        }
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public final boolean areAllItemsEnabled() {
        return this.f;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        if (i < 0 || i >= getCount()) {
            return null;
        }
        View view2 = super.getView(i, view, viewGroup);
        ArrayList arrayList = this.e;
        ((TextView) view2).setText(((zh2) arrayList.get(i)).a);
        if (view2 instanceof CheckedTextView) {
            CheckedTextView checkedTextView = (CheckedTextView) view2;
            if (((zh2) arrayList.get(i)).b == 0) {
                if (checkedTextView.getCheckMarkDrawable() != null) {
                    checkedTextView.setTag(checkedTextView.getCheckMarkDrawable());
                    checkedTextView.setCheckMarkDrawable((Drawable) null);
                }
            } else if (checkedTextView.getCheckMarkDrawable() == null) {
                checkedTextView.setCheckMarkDrawable((Drawable) checkedTextView.getTag());
            }
        }
        view2.setEnabled(((zh2) arrayList.get(i)).b != 1);
        return view2;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public final boolean isEnabled(int i) {
        return i >= 0 && i < getCount() && ((zh2) this.e.get(i)).b == 2;
    }
}
