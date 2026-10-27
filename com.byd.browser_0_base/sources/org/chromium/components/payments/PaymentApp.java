package org.chromium.components.payments;

import android.graphics.drawable.Drawable;
import defpackage.gq2;
import defpackage.kz1;
import defpackage.lf0;
import defpackage.lz1;
import defpackage.nz1;
import defpackage.rh3;
import defpackage.rz1;
import defpackage.ty1;
import defpackage.vz1;
import defpackage.wz1;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.chromium.base.task.PostTask;

/* JADX INFO: loaded from: classes3.dex */
public abstract class PaymentApp extends lf0 {

    public interface a {
    }

    public interface b {
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public PaymentApp(Drawable drawable, String str, String str2, String str3) {
        String strReplaceAll = str2.replaceAll("[\n\f\r\u0085\u2028\u2029]+", "");
        super(drawable, str, strReplaceAll.length() > 64 ? gq2.f(strReplaceAll.substring(0, 64), "…") : strReplaceAll, str3);
    }

    public void e(e eVar) {
        PostTask.c(rh3.a, new c(this, eVar));
    }

    public boolean f() {
        return true;
    }

    public abstract void g();

    public String h() {
        return null;
    }

    public Set<String> i() {
        return null;
    }

    public abstract Set<String> j();

    public int k() {
        return 0;
    }

    public long l() {
        return 0L;
    }

    public boolean m() {
        return false;
    }

    public boolean n() {
        return false;
    }

    public boolean o() {
        return false;
    }

    public boolean p() {
        return false;
    }

    public boolean q() {
        return true;
    }

    public boolean s() {
        return false;
    }

    public boolean t(String str, lz1 lz1Var) {
        return j().contains(str);
    }

    public boolean u() {
        return false;
    }

    public void r(String str, String str2, String str3, String str4, byte[][] bArr, Map<String, lz1> map, kz1 kz1Var, List<kz1> list, Map<String, ty1> map2, nz1 nz1Var, List<wz1> list2, b bVar) {
    }

    public void v() {
    }

    public vz1 w(vz1 vz1Var) {
        return vz1Var;
    }

    public void x(PaymentHandlerHost paymentHandlerHost) {
    }

    public void y(rz1 rz1Var) {
    }
}
