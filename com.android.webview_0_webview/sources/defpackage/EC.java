package defpackage;

import android.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class EC extends AlertDialog implements DialogInterface.OnClickListener, BC {
    public final CC s;
    public final DC t;

    public EC(Context context, int i, DC dc, int i2, int i3, double d, double d2) {
        super(context, i);
        this.t = dc;
        setButton(-1, context.getText(AbstractC0566bn.m1), this);
        setButton(-2, context.getText(R.string.cancel), (DialogInterface.OnClickListener) null);
        setIcon(0);
        CC ccA = a(context, d, d2);
        this.s = ccA;
        setView(ccA);
        CC cc = this.s;
        cc.i(i2, i3);
        cc.j();
        cc.u = this;
    }

    public abstract CC a(Context context, double d, double d2);

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        if (this.t != null) {
            this.s.clearFocus();
            DC dc = this.t;
            int iG = this.s.g();
            int iF = this.s.f();
            C1479sC c1479sC = (C1479sC) dc;
            int i2 = c1479sC.a;
            if (i2 == 11) {
                c1479sC.b.b(i2, iG, iF, 0, 0, 0, 0, 0, 0);
            } else {
                c1479sC.b.b(i2, iG, 0, 0, 0, 0, 0, 0, iF);
            }
        }
    }
}
