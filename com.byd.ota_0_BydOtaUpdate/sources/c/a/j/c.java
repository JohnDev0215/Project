package c.a.j;

import android.app.Dialog;
import android.content.Context;
import android.widget.Toast;
import c.a.i.a.b;
import c.a.i.a.f;
import c.a.i.a.g;
import c.a.i.a.h;
import c.a.i.a.i;
import c.a.i.a.j;
import com.byd.ui.dialogs.TimesDialog;
import java.util.List;

/* JADX INFO: compiled from: UIManager.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Dialog f2815a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f2816b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public j f2817c;

    public c(Context context) {
        this.f2816b = context;
    }

    public static Toast b(Context context, CharSequence charSequence, int i2) {
        Toast toastMakeText = Toast.makeText(context, charSequence, i2);
        toastMakeText.setGravity(48, 0, 0);
        return toastMakeText;
    }

    public void a() {
        Dialog dialog = this.f2815a;
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public void c(h.c cVar) {
        a();
        Dialog dialogC = c.a.i.a.c.c(this.f2816b, cVar);
        this.f2815a = dialogC;
        dialogC.show();
    }

    public void d() {
        a();
        Dialog dialogA = c.a.i.a.c.a(this.f2816b);
        this.f2815a = dialogA;
        dialogA.show();
    }

    public void e() {
        a();
        Dialog dialogB = c.a.i.a.c.b(this.f2816b);
        this.f2815a = dialogB;
        dialogB.show();
    }

    public void f(b.c cVar) {
        a();
        Dialog dialogD = c.a.i.a.c.d(this.f2816b, cVar);
        this.f2815a = dialogD;
        dialogD.show();
    }

    public void g() {
        a();
        Dialog dialogE = c.a.i.a.c.e(this.f2816b);
        this.f2815a = dialogE;
        dialogE.show();
    }

    public void h() {
        a();
        Dialog dialogF = c.a.i.a.c.f(this.f2816b);
        this.f2815a = dialogF;
        dialogF.show();
    }

    public void i() {
        a();
        Dialog dialogG = c.a.i.a.c.g(this.f2816b);
        this.f2815a = dialogG;
        dialogG.show();
    }

    public void j(int i2) {
        Context context = this.f2816b;
        b(context, context.getString(i2), 1).show();
    }

    public void k() {
        a();
        Dialog dialogH = c.a.i.a.c.h(this.f2816b);
        this.f2815a = dialogH;
        dialogH.show();
    }

    public void l() {
        a();
        Dialog dialogJ = c.a.i.a.c.j(this.f2816b);
        this.f2815a = dialogJ;
        dialogJ.show();
    }

    public void m() {
        a();
        Dialog dialogN = c.a.i.a.c.n(this.f2816b);
        this.f2815a = dialogN;
        dialogN.show();
    }

    public void n() {
        a();
        Dialog dialogO = c.a.i.a.c.o(this.f2816b);
        this.f2815a = dialogO;
        dialogO.show();
    }

    public void o() {
        a();
        Dialog dialogL = c.a.i.a.c.l(this.f2816b);
        this.f2815a = dialogL;
        dialogL.show();
    }

    public void p(f.c cVar) {
        a();
        f fVar = new f(this.f2816b);
        fVar.e(cVar);
        fVar.f();
        this.f2815a = fVar;
        fVar.show();
    }

    public void q(String str, TimesDialog.b bVar) {
        TimesDialog timesDialog = new TimesDialog(this.f2816b);
        timesDialog.setTimesAction(bVar);
        timesDialog.setMessageInfo(str);
        timesDialog.b();
    }

    public void r(g.c cVar, int i2) {
        a();
        Dialog dialogK = c.a.i.a.c.k(this.f2816b, cVar, i2);
        this.f2815a = dialogK;
        dialogK.show();
    }

    public void s(String str, List<i.f> list, i.c cVar) {
        a();
        i iVar = new i(this.f2816b);
        iVar.g(list);
        iVar.f(str);
        iVar.e(cVar);
        iVar.show();
        this.f2815a = iVar;
    }

    public void t() {
        a();
        Dialog dialogM = c.a.i.a.c.m(this.f2816b);
        this.f2815a = dialogM;
        dialogM.show();
    }

    public void u(int i2) {
        a();
        j jVar = new j(this.f2816b);
        this.f2817c = jVar;
        jVar.show();
        this.f2817c.a(i2);
        this.f2815a = this.f2817c;
    }

    public void v(String str) {
        Dialog dialogP = c.a.i.a.c.p(this.f2816b, str);
        this.f2815a = dialogP;
        dialogP.show();
    }

    public void w() {
        a();
        Dialog dialogQ = c.a.i.a.c.q(this.f2816b);
        this.f2815a = dialogQ;
        dialogQ.show();
    }

    public void x(int i2) {
        j jVar = this.f2817c;
        if (jVar != null) {
            jVar.a(i2);
        }
    }
}
