package defpackage;

import J.N;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Ds extends Es {
    public Ds(String[] strArr) {
        super(null);
        N.MDkrKi31(strArr);
    }

    @Override // defpackage.Es
    public void a(String str) {
        N.M5K_ewhl(str);
    }

    @Override // defpackage.Es
    public void b(String str, String str2) {
        N.MUoYiNbY(str, str2);
    }

    @Override // defpackage.Es
    public void c() {
        throw new IllegalStateException("Can't destroy native command line after startup");
    }

    @Override // defpackage.Es
    public String[] d() {
        return null;
    }

    @Override // defpackage.Es
    public String f(String str) {
        return N.MZJ2lrZY(str);
    }

    @Override // defpackage.Es
    public Map g() {
        HashMap map = new HashMap();
        String[] strArrMHzche6O = N.MHzche6O();
        for (int i = 0; i < strArrMHzche6O.length; i += 2) {
            map.put(strArrMHzche6O[i], strArrMHzche6O[i + 1]);
        }
        return map;
    }

    @Override // defpackage.Es
    public boolean h(String str) {
        return N.MsCvypjU(str);
    }

    @Override // defpackage.Es
    public void k(String str) {
        N.M1cMYXGO(str);
    }
}
