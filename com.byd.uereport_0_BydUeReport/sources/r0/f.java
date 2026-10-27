package r0;

import android.widget.ListView;

/* JADX INFO: compiled from: ListViewAutoScrollHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends a {

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final ListView f7726v;

    public f(ListView listView) {
        super(listView);
        this.f7726v = listView;
    }

    @Override // r0.a
    public boolean a(int i8) {
        return false;
    }

    @Override // r0.a
    public boolean b(int i8) {
        ListView listView = this.f7726v;
        int count = listView.getCount();
        if (count == 0) {
            return false;
        }
        int childCount = listView.getChildCount();
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        int i9 = firstVisiblePosition + childCount;
        if (i8 > 0) {
            if (i9 >= count && listView.getChildAt(childCount - 1).getBottom() <= listView.getHeight()) {
                return false;
            }
        } else {
            if (i8 >= 0) {
                return false;
            }
            if (firstVisiblePosition <= 0 && listView.getChildAt(0).getTop() >= 0) {
                return false;
            }
        }
        return true;
    }

    @Override // r0.a
    public void j(int i8, int i9) {
        g.b(this.f7726v, i9);
    }
}
