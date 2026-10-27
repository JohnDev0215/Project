package c.a.d.i.c1;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.byd.ota.R;

/* JADX INFO: compiled from: ChooseTipsDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class p extends n {
    public TextView G3;
    public TextView H3;
    public Button I3;
    public Button J3;
    public a K3;

    /* JADX INFO: compiled from: ChooseTipsDialog.java */
    public interface a {
        void a();

        void b();
    }

    public p(Context context) {
        super(context);
        if (c.a.d.h.e.b()) {
            setContentView(R.layout.dialog_choose_tips_rh);
        } else {
            setContentView(R.layout.dialog_choose_tips);
        }
        this.G3 = (TextView) findViewById(R.id.dialog_title);
        this.H3 = (TextView) findViewById(R.id.dialog_message);
        Button button = (Button) findViewById(R.id.action_confirm);
        this.I3 = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: c.a.d.i.c1.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f2520c.m(view);
            }
        });
        Button button2 = (Button) findViewById(R.id.action_cancel);
        this.J3 = button2;
        button2.setOnClickListener(new View.OnClickListener() { // from class: c.a.d.i.c1.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f2521c.o(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void m(View view) {
        dismissDialog();
        a aVar = this.K3;
        if (aVar != null) {
            aVar.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: n, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void o(View view) {
        dismissDialog();
        a aVar = this.K3;
        if (aVar != null) {
            aVar.b();
        }
    }

    public void p(a aVar) {
        this.K3 = aVar;
    }

    public void q(int i2) {
        this.J3.setText(i2);
    }

    public void r(int i2) {
        this.I3.setText(i2);
    }

    public void s(int i2) {
        this.H3.setText(i2);
    }

    public void t(String str) {
        this.H3.setText(str);
    }

    public void u(int i2) {
        this.G3.setText(i2);
    }
}
