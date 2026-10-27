package c.a.i.a;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/* JADX INFO: compiled from: NormalDoingDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends c.a.i.a.a {
    public TextView s;
    public TextView x;
    public Button y;

    /* JADX INFO: compiled from: NormalDoingDialog.java */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            d.this.dismiss();
        }
    }

    public d(Context context) {
        super(context);
        setContentView(c.a.e.d.f2635b);
        TextView textView = (TextView) findViewById(c.a.e.c.j);
        this.s = textView;
        textView.setText(c.a.e.e.k);
        this.x = (TextView) findViewById(c.a.e.c.f2633i);
        Button button = (Button) findViewById(c.a.e.c.o);
        this.y = button;
        button.setOnClickListener(new a());
    }

    public void a(int i2) {
        this.x.setText(i2);
    }

    public void b(int i2) {
        this.s.setText(i2);
    }
}
