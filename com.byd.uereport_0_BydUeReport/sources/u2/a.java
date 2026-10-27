package u2;

import android.content.Context;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.text.TextUtils;
import b5.i;
import com.bydux.api.common.tools.log.BydLogHelper;
import g5.c;
import g5.f;

/* JADX INFO: compiled from: BindHandler.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile a f8144a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Context f8146c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public BYDAutoBodyworkDevice f8147d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f8145b = "BindHandler";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f8148e = 44;

    public a(Context context) {
        this.f8146c = context.getApplicationContext();
        c();
    }

    public static a b(Context context) {
        if (f8144a == null) {
            synchronized (a.class) {
                if (f8144a == null) {
                    f8144a = new a(context);
                }
            }
        }
        return f8144a;
    }

    public void a() {
        BydLogHelper.logD("-----------checkVin------------");
        int iA = c.a();
        BydLogHelper.logD("countryCode:" + iA);
        if (iA == -1 || iA != 44) {
            return;
        }
        String realAutoVIN = this.f8147d.getRealAutoVIN();
        String strA = f.a("/vehicleconfig/bind_vin_code.data");
        BydLogHelper.logD("realAutoVIN:" + realAutoVIN + ";saveVinCode:" + strA);
        if (TextUtils.isEmpty(realAutoVIN)) {
            return;
        }
        if (TextUtils.isEmpty(strA)) {
            f.b("/vehicleconfig/bind_vin_code.data", realAutoVIN, false);
        } else {
            if (strA == null || strA.equals(realAutoVIN)) {
                return;
            }
            i.f(this.f8146c).j(realAutoVIN).l();
        }
    }

    public final void c() {
        this.f8147d = BYDAutoBodyworkDevice.getInstance(this.f8146c);
    }

    public void d() {
        a();
    }

    public void e() {
    }
}
