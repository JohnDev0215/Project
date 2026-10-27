package c.a.d.i.c1;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.ViewTreeObserver;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.byd.ota.R;

/* JADX INFO: compiled from: UpdateTipsFullscreenDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class v extends o {
    public ProgressBar E3;
    public TextView F3;
    public ValueAnimator G3;
    public int x;
    public String y;

    /* JADX INFO: compiled from: UpdateTipsFullscreenDialog.java */
    public class a implements ViewTreeObserver.OnGlobalLayoutListener {
        public a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            v.this.e();
            v.this.E3.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
    }

    public v(Context context) {
        super(context);
        setContentView(R.layout.fullscreen_dialog_update_tips);
        this.x = c.a.b.c.a.k().g() / 60;
        this.y = context.getResources().getString(R.string.updating_waiting_with_time);
        this.E3 = (ProgressBar) findViewById(R.id.install_progress_bar);
        this.F3 = (TextView) findViewById(R.id.update_tips_tv);
        this.E3.getViewTreeObserver().addOnGlobalLayoutListener(new a());
        if (this.f2533d) {
            this.E3.setLayoutDirection(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void c(ValueAnimator valueAnimator) {
        this.E3.setProgress(((Integer) valueAnimator.getAnimatedValue()).intValue());
    }

    public void d(String str) {
        this.F3.setText(str);
    }

    public void e() {
        if (this.G3 == null) {
            this.G3 = ValueAnimator.ofInt(0, 100);
        }
        this.G3.setDuration(5000L);
        this.G3.setRepeatCount(-1);
        this.G3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: c.a.d.i.c1.l
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f2528a.c(valueAnimator);
            }
        });
        this.G3.start();
    }

    public void f() {
        ValueAnimator valueAnimator = this.G3;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.G3 = null;
        }
    }

    public void g(int i2, int i3) {
        f();
        this.F3.setText(String.format(this.y, Integer.valueOf(i2), Integer.valueOf(i3)));
        this.E3.setProgress(i2);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        f();
    }
}
