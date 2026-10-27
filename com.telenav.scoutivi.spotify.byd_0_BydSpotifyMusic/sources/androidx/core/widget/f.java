package androidx.core.widget;

import android.widget.ListView;

/* JADX INFO: compiled from: ListViewAutoScrollHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends a {

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private final ListView f2019x;

    public f(ListView listView) {
        super(listView);
        this.f2019x = listView;
    }

    @Override // androidx.core.widget.a
    public boolean a(int i7) {
        return false;
    }

    @Override // androidx.core.widget.a
    public boolean b(int i7) {
        ListView listView = this.f2019x;
        int count = listView.getCount();
        if (count == 0) {
            return false;
        }
        int childCount = listView.getChildCount();
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        int i8 = firstVisiblePosition + childCount;
        if (i7 > 0) {
            if (i8 >= count && listView.getChildAt(childCount - 1).getBottom() <= listView.getHeight()) {
                return false;
            }
        } else {
            if (i7 >= 0) {
                return false;
            }
            if (firstVisiblePosition <= 0 && listView.getChildAt(0).getTop() >= 0) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.core.widget.a
    public void j(int i7, int i8) {
        g.a(this.f2019x, i8);
    }
}
