package spi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import com.android.car.ui.R$integer;

/* JADX INFO: compiled from: OnContinuousScrollListener.java */
/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"ClickableViewAccessibility"})
public final class e8 implements View.OnTouchListener {
    public final int b;
    public final int c;
    public final View.OnClickListener d;
    public View e;
    public boolean f;
    public final Handler a = new Handler(Looper.getMainLooper());
    public final a g = new a();

    /* JADX INFO: compiled from: OnContinuousScrollListener.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (!e8.this.e.isEnabled()) {
                e8.this.f = false;
                return;
            }
            e8.this.a.postDelayed(this, r0.c);
            e8 e8Var = e8.this;
            e8Var.d.onClick(e8Var.e);
            e8.this.f = true;
        }
    }

    public e8(@NonNull Context context, @NonNull View.OnClickListener onClickListener) {
        int integer = context.getResources().getInteger(R$integer.car_ui_scrollbar_longpress_initial_delay);
        this.b = integer;
        int integer2 = context.getResources().getInteger(R$integer.car_ui_scrollbar_longpress_repeat_interval);
        this.c = integer2;
        if (integer < 0 || integer2 < 0) {
            throw new IllegalArgumentException("negative intervals are not allowed");
        }
        this.d = onClickListener;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        this.e = view;
        int action = motionEvent.getAction();
        if (action == 0) {
            this.a.removeCallbacks(this.g);
            this.a.postDelayed(this.g, this.b);
            this.e.setPressed(true);
            return true;
        }
        if (action != 1 && action != 3) {
            return false;
        }
        if (!this.f) {
            this.d.onClick(view);
        }
        this.a.removeCallbacks(this.g);
        this.e.setPressed(false);
        this.f = false;
        return true;
    }
}
