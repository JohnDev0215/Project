package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.Objects;
import org.chromium.components.javascript_dialogs.JavascriptDialogCustomView;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class Lx implements InterfaceC1656vO {
    public final String a;
    public final String b;
    public final int c;
    public final int d;
    public final String e;
    public final boolean f;
    public C1546tO g;
    public JO h;
    public JavascriptDialogCustomView i;

    public Lx(String str, String str2, String str3, boolean z, int i, int i2) {
        this.a = str;
        this.b = str2;
        this.c = i;
        this.d = i2;
        this.e = str3;
        this.f = z;
    }

    public abstract void a(String str, boolean z);

    @Override // defpackage.InterfaceC1656vO
    public void b(JO jo, int i) {
        JavascriptDialogCustomView javascriptDialogCustomView = this.i;
        if (javascriptDialogCustomView == null) {
            return;
        }
        if (i == 1) {
            a(javascriptDialogCustomView.s.getText().toString(), this.i.a());
        } else if (i == 2) {
            c(true, javascriptDialogCustomView.a());
        } else if (i != 4) {
            c(false, javascriptDialogCustomView.a());
        }
        this.h = null;
        this.i = null;
        this.g = null;
    }

    public abstract void c(boolean z, boolean z2);

    @Override // defpackage.InterfaceC1656vO
    public void d(JO jo, int i) {
        C1546tO c1546tO = this.g;
        if (c1546tO == null) {
            return;
        }
        if (i == 0) {
            c1546tO.b(jo, 1);
        } else if (i != 1) {
            Ts.a("JSModalDialog", Z7.a("Unexpected button pressed in dialog: ", i), new Object[0]);
        } else {
            c1546tO.b(jo, 2);
        }
    }

    public void e(Context context, C1546tO c1546tO, int i) {
        C0683dt c0683dtX = C0683dt.x();
        try {
            this.i = (JavascriptDialogCustomView) LayoutInflater.from(context).inflate(AbstractC0425Xm.n0, (ViewGroup) null);
            c0683dtX.close();
            JavascriptDialogCustomView javascriptDialogCustomView = this.i;
            String str = this.e;
            Objects.requireNonNull(javascriptDialogCustomView);
            if (str != null) {
                javascriptDialogCustomView.s.setVisibility(0);
                if (str.length() > 0) {
                    javascriptDialogCustomView.s.setText(str);
                    javascriptDialogCustomView.s.selectAll();
                }
            }
            this.i.t.setVisibility(this.f ? 0 : 8);
            Resources resources = context.getResources();
            BO bo = new BO(AbstractC1711wO.q);
            bo.d(AbstractC1711wO.a, this);
            bo.d(AbstractC1711wO.c, this.a);
            bo.d(AbstractC1711wO.e, this.b);
            bo.d(AbstractC1711wO.f, this.i);
            bo.c(AbstractC1711wO.g, resources, this.c);
            bo.c(AbstractC1711wO.j, resources, this.d);
            bo.b(AbstractC1711wO.o, true);
            JO joA = bo.a();
            this.h = joA;
            this.g = c1546tO;
            c1546tO.e(joA, i, false);
        } catch (Throwable th) {
            try {
                c0683dtX.close();
            } catch (Throwable th2) {
                AbstractC0279Pk.a.a(th, th2);
            }
            throw th;
        }
    }
}
