package b.h.n;

import android.widget.ListView;

/* JADX INFO: compiled from: ListViewAutoScrollHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends a {
    public final ListView t;

    public f(ListView listView) {
        super(listView);
        this.t = listView;
    }

    @Override // b.h.n.a
    public boolean a(int i2) {
        return false;
    }

    @Override // b.h.n.a
    public boolean b(int i2) {
        ListView listView = this.t;
        int count = listView.getCount();
        if (count == 0) {
            return false;
        }
        int childCount = listView.getChildCount();
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        int i3 = firstVisiblePosition + childCount;
        if (i2 > 0) {
            if (i3 >= count && listView.getChildAt(childCount - 1).getBottom() <= listView.getHeight()) {
                return false;
            }
        } else {
            if (i2 >= 0) {
                return false;
            }
            if (firstVisiblePosition <= 0 && listView.getChildAt(0).getTop() >= 0) {
                return false;
            }
        }
        return true;
    }

    @Override // b.h.n.a
    public void j(int i2, int i3) {
        g.b(this.t, i3);
    }
}
