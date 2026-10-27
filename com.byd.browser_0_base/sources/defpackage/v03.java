package defpackage;

import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;

/* JADX INFO: compiled from: TabLayoutPageListener.java */
/* JADX INFO: loaded from: classes2.dex */
public final class v03 extends TabLayout.h {
    public final ArrayList<w72> h;
    public int i;

    public v03(TabLayout tabLayout, ArrayList<w72> arrayList) {
        super(tabLayout);
        this.h = arrayList;
        this.i = 0;
    }

    @Override // com.google.android.material.tabs.TabLayout.h, androidx.viewpager.widget.ViewPager.h
    public final void c(int i) {
        this.i = i;
        super.c(i);
        int i2 = 0;
        while (true) {
            ArrayList<w72> arrayList = this.h;
            if (i2 >= arrayList.size()) {
                return;
            }
            if (this.i == i2) {
                arrayList.get(i2).onResume();
            } else {
                arrayList.get(i2).onPause();
            }
            i2++;
        }
    }
}
