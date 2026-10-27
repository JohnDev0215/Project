package d.a.a.f.c.a.a;

import android.graphics.drawable.Drawable;
import android.view.View;
import com.bydux.api.common.views.ctrl.BydClock;

/* JADX INFO: compiled from: AttrBydClock.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends l {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f4633e = "byd_clock_background";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final String f4634f = "byd_clock_hour";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final String f4635g = "byd_clock_minute";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final String f4636h = "byd_clock_second";

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private String f4637i;

    public b(String str) {
        this.f4637i = str;
    }

    @Override // d.a.a.f.c.a.a.l
    public void a(View view) {
        if (view instanceof BydClock) {
            BydClock bydClock = (BydClock) view;
            if ("drawable".equals(this.f4654d) || "mipmap".equals(this.f4654d)) {
                Drawable drawableL = "drawable".equals(this.f4654d) ? d.a.a.f.c.a.c.c.n().l(this.f4652b) : d.a.a.f.c.a.c.c.n().o(this.f4652b);
                if ("byd_clock_background".equals(this.f4637i)) {
                    bydClock.setClockBackground(drawableL);
                    return;
                }
                if ("byd_clock_hour".equals(this.f4637i)) {
                    bydClock.setClockHour(drawableL);
                } else if ("byd_clock_minute".equals(this.f4637i)) {
                    bydClock.setClockMinute(drawableL);
                } else if ("byd_clock_second".equals(this.f4637i)) {
                    bydClock.setClockSecond(drawableL);
                }
            }
        }
    }
}
