package b.b.a.j.d;

import android.view.View;
import java.math.BigDecimal;

/* JADX INFO: compiled from: NoDoubleClickListener.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a implements View.OnClickListener {
    public static final int MIN_CLICK_DELAY_TIME = 1000;
    private int delayTime;
    private long lastClickTime;

    public a() {
        this.lastClickTime = 0L;
        this.delayTime = 1000;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (this.lastClickTime > jCurrentTimeMillis) {
            this.lastClickTime = 0L;
        }
        long jLongValue = new BigDecimal(jCurrentTimeMillis).subtract(new BigDecimal(this.lastClickTime)).longValue();
        b.b.m.c.a.a("NoDoubleClickListener", "----onClick---delta: " + jLongValue);
        if (jLongValue > this.delayTime) {
            this.lastClickTime = jCurrentTimeMillis;
            onNoDoubleClick(view);
        }
    }

    public abstract void onNoDoubleClick(View view);

    public a(int i) {
        this.lastClickTime = 0L;
        this.delayTime = 1000;
        this.delayTime = i;
    }
}
