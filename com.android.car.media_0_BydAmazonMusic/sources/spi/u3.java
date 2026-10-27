package spi;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.ui.R$id;

/* JADX INFO: compiled from: FastScroller.java */
/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"ClickableViewAccessibility"})
public final class u3 implements View.OnTouchListener {
    public float a = -1.0f;

    @NonNull
    public final View b;

    @NonNull
    public final View c;

    @NonNull
    public final RecyclerView d;
    public final int e;

    public u3(@NonNull RecyclerView recyclerView, @NonNull View view, @NonNull View view2) {
        this.d = recyclerView;
        this.b = view;
        this.c = b2.n(view2, R$id.car_ui_scrollbar_thumb);
        this.e = ViewConfiguration.get(recyclerView.getContext()).getScaledTouchSlop();
    }

    public final void a(float f) {
        int[] iArr = {(this.c.getHeight() / 2) + ((int) this.b.getY()), (this.b.getHeight() + ((int) this.b.getY())) - (this.c.getHeight() / 2)};
        int i = iArr[1] - iArr[0];
        int height = i == 0 ? 0 : (int) (((f - ((this.c.getHeight() / 2.0f) + this.c.getY())) / i) * (this.d.computeVerticalScrollRange() - this.d.getHeight()));
        if (height != 0) {
            this.d.scrollBy(0, height);
        }
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.a = motionEvent.getY();
        } else {
            if (action == 2) {
                if (this.b.getY() + motionEvent.getY() < this.c.getY() + this.c.getHeight()) {
                    if (this.b.getY() + motionEvent.getY() > this.c.getY()) {
                        a((motionEvent.getY() - this.a) + (this.c.getHeight() / 2.0f) + this.c.getY());
                        this.a = motionEvent.getY();
                    }
                }
                return true;
            }
            if (Math.abs(this.a - motionEvent.getY()) < ((float) this.e)) {
                a(this.b.getY() + motionEvent.getY());
            }
            this.a = -1.0f;
        }
        return true;
    }
}
