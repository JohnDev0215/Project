package c.a.d.i.c1;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.byd.ota.R;

/* JADX INFO: compiled from: UpdateCountDownDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class s extends n {
    public final String G3;
    public TextView H3;
    public Button I3;
    public Button J3;
    public c K3;
    public d L3;

    /* JADX INFO: compiled from: UpdateCountDownDialog.java */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            s.this.dismissDialog();
            s.this.q();
            if (s.this.L3 != null) {
                s.this.L3.b();
            }
        }
    }

    /* JADX INFO: compiled from: UpdateCountDownDialog.java */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            s.this.dismissDialog();
            s.this.q();
            if (s.this.L3 != null) {
                s.this.L3.c();
            }
        }
    }

    /* JADX INFO: compiled from: UpdateCountDownDialog.java */
    public class c extends CountDownTimer {
        public c(long j, long j2) {
            super(j, j2);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            s.this.dismissDialog();
            s.this.q();
            if (s.this.L3 != null) {
                s.this.L3.a();
            }
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            s.this.H3.setText(String.format(s.this.G3, Long.valueOf(j / 1000)));
        }
    }

    /* JADX INFO: compiled from: UpdateCountDownDialog.java */
    public interface d {
        void a();

        void b();

        void c();
    }

    public s(Context context) {
        super(context);
        if (c.a.d.h.e.b()) {
            setContentView(R.layout.dialog_update_countdown_rh);
        } else {
            setContentView(R.layout.dialog_update_countdown);
        }
        this.H3 = (TextView) findViewById(R.id.dialog_time);
        Button button = (Button) findViewById(R.id.upgrade_immediately);
        this.I3 = button;
        button.setOnClickListener(new a());
        Button button2 = (Button) findViewById(R.id.cancel_update);
        this.J3 = button2;
        button2.setOnClickListener(new b());
        this.G3 = context.getResources().getString(R.string.update_deadline_time);
        this.K3 = new c(30000L, 1000L);
    }

    public void o(d dVar) {
        this.L3 = dVar;
    }

    public void p() {
        c cVar = this.K3;
        if (cVar != null) {
            cVar.start();
        }
    }

    public void q() {
        c cVar = this.K3;
        if (cVar != null) {
            cVar.cancel();
            this.K3 = null;
        }
    }
}
