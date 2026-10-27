package defpackage;

import android.widget.ListView;

/* JADX INFO: compiled from: ListViewAutoScrollHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class na extends ia {
    public final ListView t;

    public na(ListView listView) {
        super(listView);
        this.t = listView;
    }

    @Override // defpackage.ia
    public boolean a(int i) {
        return false;
    }

    @Override // defpackage.ia
    public boolean b(int i) {
        ListView listView = this.t;
        int count = listView.getCount();
        if (count == 0) {
            return false;
        }
        int childCount = listView.getChildCount();
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        int i2 = firstVisiblePosition + childCount;
        if (i > 0) {
            if (i2 >= count && listView.getChildAt(childCount - 1).getBottom() <= listView.getHeight()) {
                return false;
            }
        } else {
            if (i >= 0) {
                return false;
            }
            if (firstVisiblePosition <= 0 && listView.getChildAt(0).getTop() >= 0) {
                return false;
            }
        }
        return true;
    }

    @Override // defpackage.ia
    public void j(int i, int i2) {
        oa.a(this.t, i2);
    }
}
