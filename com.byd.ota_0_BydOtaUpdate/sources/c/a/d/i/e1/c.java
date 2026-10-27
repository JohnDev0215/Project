package c.a.d.i.e1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.byd.ota.R;

/* JADX INFO: compiled from: FloatingWindow.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static c f2560a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f2561b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public WindowManager f2562c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public WindowManager.LayoutParams f2563d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public View f2564e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public TextView f2565f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public TextView f2566g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Button f2567h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Button f2568i;
    public b j;

    /* JADX INFO: compiled from: FloatingWindow.java */
    public interface b {
        void a();

        void b();
    }

    /* JADX INFO: renamed from: c.a.d.i.e1.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FloatingWindow.java */
    public class ViewOnTouchListenerC0049c implements View.OnTouchListener {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f2569c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f2570d;

        public ViewOnTouchListenerC0049c() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            if (action == 0) {
                this.f2569c = (int) motionEvent.getRawX();
                this.f2570d = (int) motionEvent.getRawY();
                return false;
            }
            if (action != 2) {
                return false;
            }
            int rawX = (int) motionEvent.getRawX();
            int rawY = (int) motionEvent.getRawY();
            int i2 = rawX - this.f2569c;
            int i3 = rawY - this.f2570d;
            this.f2569c = rawX;
            this.f2570d = rawY;
            c.this.f2563d.x += i2;
            c.this.f2563d.y += i3;
            c.this.f2562c.updateViewLayout(view, c.this.f2563d);
            return false;
        }
    }

    public c() {
        throw new UnsupportedOperationException("FloatingWindow: U can't new me");
    }

    public static c d(Context context) {
        if (f2560a == null) {
            synchronized (c.class) {
                if (f2560a == null) {
                    f2560a = new c(context);
                }
            }
        }
        return f2560a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void f(View view) {
        b bVar = this.j;
        if (bVar != null) {
            bVar.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void h(View view) {
        b bVar = this.j;
        if (bVar != null) {
            bVar.b();
        }
    }

    public void c() {
        View view = this.f2564e;
        if (view != null) {
            this.f2562c.removeView(view);
            this.f2564e = null;
        }
    }

    public void i(b bVar) {
        this.j = bVar;
    }

    public void j(String str) {
        this.f2566g.setText(str);
    }

    public void k(String str) {
        this.f2565f.setText(str);
    }

    public void l() {
        if (this.f2564e != null) {
            return;
        }
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.f2563d = layoutParams;
        layoutParams.type = 2045;
        layoutParams.gravity = 51;
        layoutParams.width = 400;
        layoutParams.height = 240;
        layoutParams.format = -3;
        layoutParams.flags = 40;
        View viewInflate = LayoutInflater.from(this.f2561b).inflate(R.layout.view_floating_window, (ViewGroup) null);
        this.f2564e = viewInflate;
        viewInflate.setOnTouchListener(new ViewOnTouchListenerC0049c());
        this.f2565f = (TextView) this.f2564e.findViewById(R.id.dialog_title);
        this.f2566g = (TextView) this.f2564e.findViewById(R.id.dialog_message);
        this.f2567h = (Button) this.f2564e.findViewById(R.id.action_left_btn);
        this.f2568i = (Button) this.f2564e.findViewById(R.id.action_right_btn);
        this.f2567h.setOnClickListener(new View.OnClickListener() { // from class: c.a.d.i.e1.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f2558c.f(view);
            }
        });
        this.f2568i.setOnClickListener(new View.OnClickListener() { // from class: c.a.d.i.e1.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f2559c.h(view);
            }
        });
        this.f2562c.addView(this.f2564e, this.f2563d);
    }

    public c(Context context) {
        this.f2561b = context;
        this.f2562c = (WindowManager) context.getSystemService("window");
    }
}
