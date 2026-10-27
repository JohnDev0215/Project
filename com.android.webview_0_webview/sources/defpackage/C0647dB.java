package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: dB, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0647dB extends ArrayAdapter {
    public List s;
    public boolean t;

    public C0647dB(Context context, int i, List list) {
        super(context, i, list);
        this.s = new ArrayList(list);
        this.t = true;
        for (int i2 = 0; i2 < this.s.size(); i2++) {
            if (((C1204nB) this.s.get(i2)).b != 2) {
                this.t = false;
                return;
            }
        }
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean areAllItemsEnabled() {
        return this.t;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (i < 0 || i >= getCount()) {
            return null;
        }
        View view2 = super.getView(i, view, viewGroup);
        ((TextView) view2).setText(((C1204nB) this.s.get(i)).a);
        if (view2 instanceof CheckedTextView) {
            CheckedTextView checkedTextView = (CheckedTextView) view2;
            if (((C1204nB) this.s.get(i)).b == 0) {
                if (checkedTextView.getCheckMarkDrawable() != null) {
                    checkedTextView.setTag(checkedTextView.getCheckMarkDrawable());
                    checkedTextView.setCheckMarkDrawable((Drawable) null);
                }
            } else if (checkedTextView.getCheckMarkDrawable() == null) {
                checkedTextView.setCheckMarkDrawable((Drawable) checkedTextView.getTag());
            }
        }
        view2.setEnabled(((C1204nB) this.s.get(i)).b != 1);
        return view2;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public boolean isEnabled(int i) {
        return i >= 0 && i < getCount() && ((C1204nB) this.s.get(i)).b == 2;
    }
}
