package c.a.i.a;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

/* JADX INFO: compiled from: RebootCountDownDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends c.a.i.a.a {
    public Button E3;
    public final String s;
    public final b x;
    public c y;

    /* JADX INFO: compiled from: RebootCountDownDialog.java */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            f.this.y.b();
            f.this.x.cancel();
            f.this.dismiss();
        }
    }

    /* JADX INFO: compiled from: RebootCountDownDialog.java */
    public class b extends CountDownTimer {
        public b(long j, long j2) {
            super(j, j2);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            f.this.y.a();
            f.this.x.cancel();
            f.this.dismiss();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            f.this.E3.setText(String.format(f.this.s, Long.valueOf(j / 1000)));
        }
    }

    /* JADX INFO: compiled from: RebootCountDownDialog.java */
    public interface c {
        void a();

        void b();
    }

    public f(Context context) {
        super(context);
        setContentView(c.a.e.d.f2637d);
        Button button = (Button) findViewById(c.a.e.c.q);
        this.E3 = button;
        button.setOnClickListener(new a());
        this.s = context.getResources().getString(c.a.e.e.o);
        this.x = new b(5000L, 1000L);
    }

    public void e(c cVar) {
        this.y = cVar;
    }

    public void f() {
        this.x.start();
    }
}
