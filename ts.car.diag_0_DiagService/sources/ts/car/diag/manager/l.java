package ts.car.diag.manager;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import ts.car.diag.manager.v;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private HashMap<String, e> f1613a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private v.a f1614b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f1615c;

    public l(Context context) {
        new ArrayList();
        this.f1615c = "getprop  |grep  persist.log.tag";
        new HashMap();
        a();
        b();
    }

    private void a() {
        q.e("DIAG-LOG", "init");
    }

    private void b() {
        this.f1614b = v.a(this.f1615c, false);
        q.d("androidLevelResult:" + this.f1614b);
        this.f1613a = new HashMap<>();
        try {
            if (TextUtils.isEmpty(this.f1614b.f1633b)) {
                return;
            }
            String[] strArrSplit = this.f1614b.f1633b.split("\\]\\[");
            for (int i = 0; i < strArrSplit.length; i++) {
                if (i == 0) {
                    String[] strArrSplit2 = strArrSplit[0].split("\\[|\\]: \\[");
                    this.f1613a.put(strArrSplit2[1], new e("Other", false, strArrSplit2[2], strArrSplit2[1]));
                } else if (i == strArrSplit.length - 1) {
                    String[] strArrSplit3 = strArrSplit[strArrSplit.length - 1].split("\\]: \\[|\\]");
                    this.f1613a.put(strArrSplit3[0], new e(strArrSplit3[0].split("persist\\.log\\.tag\\.")[1], false, strArrSplit3[1], strArrSplit3[0]));
                } else {
                    String[] strArrSplit4 = strArrSplit[i].split("\\]: \\[");
                    this.f1613a.put(strArrSplit4[0], new e(strArrSplit4[0].split("persist\\.log\\.tag\\.")[1], false, strArrSplit4[1], strArrSplit4[0]));
                }
            }
        } catch (Exception e) {
            q.b("DIAG-LOG", "initAndroidLevel : " + e);
        }
    }
}
