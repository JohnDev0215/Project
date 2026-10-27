package n0;

import android.widget.ListView;

/* JADX INFO: compiled from: ListViewAutoScrollHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends a {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final ListView f4073t;

    public f(ListView listView) {
        super(listView);
        this.f4073t = listView;
    }

    @Override // n0.a
    public boolean a(int i3) {
        return false;
    }

    @Override // n0.a
    public boolean b(int i3) {
        ListView listView = this.f4073t;
        int count = listView.getCount();
        if (count == 0) {
            return false;
        }
        int childCount = listView.getChildCount();
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        int i4 = firstVisiblePosition + childCount;
        if (i3 > 0) {
            if (i4 >= count && listView.getChildAt(childCount - 1).getBottom() <= listView.getHeight()) {
                return false;
            }
        } else {
            if (i3 >= 0) {
                return false;
            }
            if (firstVisiblePosition <= 0 && listView.getChildAt(0).getTop() >= 0) {
                return false;
            }
        }
        return true;
    }

    @Override // n0.a
    public void j(int i3, int i4) {
        g.a(this.f4073t, i4);
    }
}
