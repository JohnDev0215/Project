package c.a.i.a;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/* JADX INFO: compiled from: TwoActionNormalDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends c.a.i.a.a {
    public Button E3;
    public TextView s;
    public TextView x;
    public c y;

    /* JADX INFO: compiled from: TwoActionNormalDialog.java */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            h.this.y.a();
            h.this.dismiss();
        }
    }

    /* JADX INFO: compiled from: TwoActionNormalDialog.java */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            h.this.dismiss();
        }
    }

    /* JADX INFO: compiled from: TwoActionNormalDialog.java */
    public interface c {
        void a();
    }

    public h(Context context) {
        super(context);
        setContentView(c.a.e.d.f2640g);
        this.s = (TextView) findViewById(c.a.e.c.j);
        this.x = (TextView) findViewById(c.a.e.c.f2633i);
        Button button = (Button) findViewById(c.a.e.c.f2626b);
        this.E3 = button;
        button.setOnClickListener(new a());
        ((Button) findViewById(c.a.e.c.f2625a)).setOnClickListener(new b());
    }

    public void b(c cVar) {
        this.y = cVar;
    }

    public void c(int i2) {
        this.E3.setText(i2);
    }

    public void d(int i2) {
        this.x.setText(i2);
    }

    public void e(int i2) {
        this.s.setText(i2);
    }
}
