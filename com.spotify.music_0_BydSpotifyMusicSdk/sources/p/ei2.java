package p;

import android.util.Log;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.impl.foreground.SystemForegroundService;
import com.google.android.material.datepicker.MaterialCalendar;

/* JADX INFO: loaded from: classes.dex */
public final class ei2 implements Runnable {
    public final /* synthetic */ int c;
    public final /* synthetic */ int e;
    public final /* synthetic */ Object k;

    public /* synthetic */ ei2(int i, int i2, Object obj) {
        this.c = i2;
        this.k = obj;
        this.e = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.c;
        int i2 = this.e;
        Object obj = this.k;
        switch (i) {
            case 0:
                ((SystemForegroundService) obj).m.cancel(i2);
                break;
            default:
                RecyclerView recyclerView = ((MaterialCalendar) obj).i0;
                if (!recyclerView.C) {
                    y12 y12Var = recyclerView.t;
                    if (y12Var != null) {
                        y12Var.s0(recyclerView, i2);
                    } else {
                        Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
                    }
                    break;
                }
                break;
        }
    }
}
