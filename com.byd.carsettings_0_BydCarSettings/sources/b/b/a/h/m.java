package b.b.a.h;

import android.content.Context;
import android.content.Intent;
import android.widget.ScrollView;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: ViewUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class m {
    public static void a(Context context, boolean z) {
        Intent intent = new Intent("com.byd.carsettings.not.mainactivity.change");
        intent.putExtra("show", z);
        context.sendBroadcast(intent);
    }

    public static void b(RecyclerView recyclerView) {
        d.a.a.a.a.h.b(recyclerView, 0);
        new d.a.a.a.a.i(new d.a.a.a.a.j.b(recyclerView), 1.0f, 1.0f, -2.0f);
    }

    public static void c(ScrollView scrollView) {
        d.a.a.a.a.h.a(scrollView);
        new d.a.a.a.a.i(new d.a.a.a.a.j.c(scrollView), 1.0f, 1.0f, -2.0f);
    }
}
