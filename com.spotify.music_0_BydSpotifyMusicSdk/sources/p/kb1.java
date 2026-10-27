package p;

import android.widget.ListView;

/* JADX INFO: loaded from: classes.dex */
public abstract class kb1 {
    public static boolean a(ListView listView, int i) {
        return listView.canScrollList(i);
    }

    public static void b(ListView listView, int i) {
        listView.scrollListBy(i);
    }
}
