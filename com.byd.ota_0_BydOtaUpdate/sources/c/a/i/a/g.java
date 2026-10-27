package c.a.i.a;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/* JADX INFO: compiled from: TipsConfirmUpdateDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends c.a.i.a.a {
    public c s;
    public Button x;
    public TextView y;

    /* JADX INFO: compiled from: TipsConfirmUpdateDialog.java */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            g.this.s.a();
        }
    }

    /* JADX INFO: compiled from: TipsConfirmUpdateDialog.java */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            g.this.dismiss();
        }
    }

    /* JADX INFO: compiled from: TipsConfirmUpdateDialog.java */
    public interface c {
        void a();
    }

    public g(Context context) {
        super(context);
        setContentView(c.a.e.d.f2639f);
        this.y = (TextView) findViewById(c.a.e.c.f2633i);
        Button button = (Button) findViewById(c.a.e.c.f2626b);
        this.x = button;
        button.setOnClickListener(new a());
        ((Button) findViewById(c.a.e.c.f2625a)).setOnClickListener(new b());
    }

    public void b(c cVar) {
        this.s = cVar;
    }

    public void c(int i2) {
        if (i2 == 3) {
            this.y.setText(c.a.e.e.m);
        } else if (i2 == 2) {
            this.y.setText(c.a.e.e.l);
        }
    }
}
