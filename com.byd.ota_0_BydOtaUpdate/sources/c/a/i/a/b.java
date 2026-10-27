package c.a.i.a;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

/* JADX INFO: compiled from: CheckedUsbUpdateDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends c.a.i.a.a {
    public c s;
    public Button x;

    /* JADX INFO: compiled from: CheckedUsbUpdateDialog.java */
    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ RadioButton f2788c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ RadioButton f2789d;

        public a(RadioButton radioButton, RadioButton radioButton2) {
            this.f2788c = radioButton;
            this.f2789d = radioButton2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v3, types: [c.a.i.a.b$c] */
        /* JADX WARN: Type inference failed for: r2v3, types: [int] */
        /* JADX WARN: Type inference failed for: r2v5 */
        /* JADX WARN: Type inference failed for: r2v6 */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ?? IsChecked = this.f2788c.isChecked();
            if (this.f2789d.isChecked()) {
                IsChecked = 2;
            }
            b.this.s.a(IsChecked);
            if (IsChecked != 0) {
                b.this.dismiss();
            }
        }
    }

    /* JADX INFO: renamed from: c.a.i.a.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CheckedUsbUpdateDialog.java */
    public class ViewOnClickListenerC0064b implements View.OnClickListener {
        public ViewOnClickListenerC0064b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            b.this.dismiss();
        }
    }

    /* JADX INFO: compiled from: CheckedUsbUpdateDialog.java */
    public interface c {
        void a(int i2);
    }

    public b(Context context) {
        super(context);
        setContentView(c.a.e.d.f2634a);
        RadioButton radioButton = (RadioButton) findViewById(c.a.e.c.p);
        RadioButton radioButton2 = (RadioButton) findViewById(c.a.e.c.w);
        Button button = (Button) findViewById(c.a.e.c.f2626b);
        this.x = button;
        button.setOnClickListener(new a(radioButton, radioButton2));
        ((Button) findViewById(c.a.e.c.f2625a)).setOnClickListener(new ViewOnClickListenerC0064b());
    }

    public void b(c cVar) {
        this.s = cVar;
    }
}
