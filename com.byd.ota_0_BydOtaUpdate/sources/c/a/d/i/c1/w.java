package c.a.d.i.c1;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.byd.ota.R;

/* JADX INFO: compiled from: VersionDetailDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class w extends n {
    public TextView G3;
    public TextView H3;
    public TextView I3;
    public TextView J3;
    public TextView K3;
    public Button L3;
    public a M3;

    /* JADX INFO: compiled from: VersionDetailDialog.java */
    public interface a {
        void a();
    }

    public w(Context context) {
        super(context);
        setContentView(R.layout.dialog_version_detail);
        this.G3 = (TextView) findViewById(R.id.dialog_title);
        this.H3 = (TextView) findViewById(R.id.function_addition);
        this.I3 = (TextView) findViewById(R.id.function_addition_text);
        this.J3 = (TextView) findViewById(R.id.function_optimization);
        this.K3 = (TextView) findViewById(R.id.function_optimization_text);
        Button button = (Button) findViewById(R.id.have_read);
        this.L3 = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: c.a.d.i.c1.m
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f2529c.m(view);
            }
        });
        if (this.f2531d) {
            this.H3.setGravity(8388613);
            this.I3.setGravity(8388613);
            this.J3.setGravity(8388613);
            this.K3.setGravity(8388613);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void m(View view) {
        dismissDialog();
        a aVar = this.M3;
        if (aVar != null) {
            aVar.a();
        }
    }

    public void n(a aVar) {
        this.M3 = aVar;
    }

    public void o(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.I3.setText(str);
        } else {
            this.H3.setVisibility(8);
            this.I3.setVisibility(8);
        }
    }

    public void p(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.K3.setText(str);
        } else {
            this.J3.setVisibility(8);
            this.K3.setVisibility(8);
        }
    }

    public void q(String str) {
        this.G3.setText(str);
    }
}
