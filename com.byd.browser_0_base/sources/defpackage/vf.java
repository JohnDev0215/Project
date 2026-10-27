package defpackage;

import android.view.View;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import defpackage.gg;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.bookmark_history.BookmarkHistoryActivity;

/* JADX INFO: compiled from: BookmarkHistoryActivity.java */
/* JADX INFO: loaded from: classes.dex */
public final class vf implements TabLayout.d {
    public final /* synthetic */ BookmarkHistoryActivity e;

    public vf(BookmarkHistoryActivity bookmarkHistoryActivity) {
        this.e = bookmarkHistoryActivity;
    }

    @Override // com.google.android.material.tabs.TabLayout.c
    public final void h(TabLayout.g gVar) {
        View view = gVar.f;
        if (view != null) {
            TextView textView = (TextView) view.findViewById(R$id.tab_item_name);
            int i = BookmarkHistoryActivity.O;
            BookmarkHistoryActivity bookmarkHistoryActivity = this.e;
            bookmarkHistoryActivity.K1(textView, true);
            bookmarkHistoryActivity.f62J.g.setSelect(gVar.e == 0);
            bookmarkHistoryActivity.K.k.setSelect(gVar.e == 1);
            if (gVar.e == 1) {
                eg egVar = bookmarkHistoryActivity.f62J;
                gg ggVar = egVar.i;
                gg.b bVar = ggVar.b;
                if (!bVar.isEmpty() && ggVar.l.hasFocus()) {
                    bVar.pop();
                }
                egVar.h.a.x();
                egVar.g.k();
            } else {
                bookmarkHistoryActivity.K.n.x();
            }
            ViewPager viewPager = bookmarkHistoryActivity.I;
            int i2 = gVar.e;
            viewPager.x = false;
            viewPager.v(i2, 0, false, false);
        }
    }

    @Override // com.google.android.material.tabs.TabLayout.c
    public final void l(TabLayout.g gVar) {
        View view = gVar.f;
        if (view != null) {
            TextView textView = (TextView) view.findViewById(R$id.tab_item_name);
            int i = BookmarkHistoryActivity.O;
            this.e.K1(textView, false);
        }
    }

    @Override // com.google.android.material.tabs.TabLayout.c
    public final void a(TabLayout.g gVar) {
    }
}
