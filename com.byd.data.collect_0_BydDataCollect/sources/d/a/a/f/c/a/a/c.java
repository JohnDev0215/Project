package d.a.a.f.c.a.a;

import android.graphics.drawable.Drawable;
import android.view.View;
import com.bydux.api.common.views.ctrl.BydDigitalClock;

/* JADX INFO: compiled from: AttrBydDigitalClock.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends l {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f4638e = "byd_dgclock_num0";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final String f4639f = "byd_dgclock_num1";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final String f4640g = "byd_dgclock_num2";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final String f4641h = "byd_dgclock_num3";

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final String f4642i = "byd_dgclock_num4";
    private final String j = "byd_dgclock_num5";
    private final String k = "byd_dgclock_num6";
    private final String l = "byd_dgclock_num7";
    private final String m = "byd_dgclock_num8";
    private final String n = "byd_dgclock_num9";
    private final String o = "byd_dgclock_am";
    private final String p = "byd_dgclock_pm";
    private final String q = "byd_dgclock_colon";
    private String r;

    public c(String str) {
        this.r = str;
    }

    @Override // d.a.a.f.c.a.a.l
    public void a(View view) {
        if (view instanceof BydDigitalClock) {
            BydDigitalClock bydDigitalClock = (BydDigitalClock) view;
            if ("drawable".equals(this.f4654d) || "mipmap".equals(this.f4654d)) {
                Drawable drawableL = "drawable".equals(this.f4654d) ? d.a.a.f.c.a.c.c.n().l(this.f4652b) : d.a.a.f.c.a.c.c.n().o(this.f4652b);
                if ("byd_dgclock_num0".equals(this.r)) {
                    bydDigitalClock.setDigitalClockNum0(drawableL);
                    return;
                }
                if ("byd_dgclock_num1".equals(this.r)) {
                    bydDigitalClock.setDigitalClockNum1(drawableL);
                    return;
                }
                if ("byd_dgclock_num2".equals(this.r)) {
                    bydDigitalClock.setDigitalClockNum2(drawableL);
                    return;
                }
                if ("byd_dgclock_num3".equals(this.r)) {
                    bydDigitalClock.setDigitalClockNum3(drawableL);
                    return;
                }
                if ("byd_dgclock_num4".equals(this.r)) {
                    bydDigitalClock.setDigitalClockNum4(drawableL);
                    return;
                }
                if ("byd_dgclock_num5".equals(this.r)) {
                    bydDigitalClock.setDigitalClockNum5(drawableL);
                    return;
                }
                if ("byd_dgclock_num6".equals(this.r)) {
                    bydDigitalClock.setDigitalClockNum6(drawableL);
                    return;
                }
                if ("byd_dgclock_num7".equals(this.r)) {
                    bydDigitalClock.setDigitalClockNum7(drawableL);
                    return;
                }
                if ("byd_dgclock_num8".equals(this.r)) {
                    bydDigitalClock.setDigitalClockNum8(drawableL);
                    return;
                }
                if ("byd_dgclock_num9".equals(this.r)) {
                    bydDigitalClock.setDigitalClockNum9(drawableL);
                    return;
                }
                if ("byd_dgclock_am".equals(this.r)) {
                    bydDigitalClock.setDigitalClockAm(drawableL);
                } else if ("byd_dgclock_pm".equals(this.r)) {
                    bydDigitalClock.setDigitalClockPm(drawableL);
                } else if ("byd_dgclock_colon".equals(this.r)) {
                    bydDigitalClock.setDigitalClockColon(drawableL);
                }
            }
        }
    }
}
