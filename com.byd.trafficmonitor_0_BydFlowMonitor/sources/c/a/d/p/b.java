package c.a.d.p;

import android.content.Context;
import android.text.TextUtils;
import c.a.d.s.k;
import c.a.d.s.l;
import com.bydux.api.R;

/* JADX INFO: compiled from: FlowServerConfig.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f3616a = "http://test-networkflow-simcard-eu.iov.byd.auto/";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f3617b = "api.base.goodsUsedInfo.query";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f3618c = "/httpsOpenServer/serviceProvide";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f3619d = "POST";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f3620e = "http://%s";

    public String a() {
        return "/httpsOpenServer/serviceProvide";
    }

    public String b(Context context) {
        StringBuffer stringBuffer = new StringBuffer();
        String strC = l.c(context.getString(R.string.flow_app_key_start));
        String strB = l.b(k.b(context, "flow_key_M"));
        String string = context.getString(R.string.flow_app_key_last);
        stringBuffer.append(strC);
        stringBuffer.append(strB);
        stringBuffer.append(string);
        return stringBuffer.toString();
    }

    public String c(Context context) {
        StringBuffer stringBuffer = new StringBuffer();
        String strC = l.c(context.getString(R.string.flow_app_secret_start));
        String strB = l.b(k.b(context, "flow_secret_M"));
        String string = context.getString(R.string.flow_app_secret_last);
        stringBuffer.append(strC);
        stringBuffer.append(strB);
        stringBuffer.append(string);
        return stringBuffer.toString();
    }

    public String d(String str) {
        return String.format("http://%s", str);
    }

    public String e(String str) {
        if (TextUtils.isEmpty(str)) {
            return "networkflow-simcard-global.iov.byd.auto";
        }
        if (str.startsWith("90131")) {
            return "networkflow-simcard-eu.iov.byd.auto";
        }
        if (str.startsWith("52501") || str.startsWith("52004") || str.startsWith("45412")) {
            return "networkflow-simcard-sg.iov.byd.auto";
        }
        if (str.startsWith("44020")) {
            return "networkflow-simcard-jp.iov.byd.auto";
        }
        if (str.startsWith("50501")) {
            return "networkflow-simcard-au.iov.byd.auto";
        }
        if (str.startsWith("72405")) {
            return "networkflow-simcard-br.iov.byd.auto";
        }
        if (str.startsWith("24042")) {
            return "networkflow-simcard-no.iov.byd.auto";
        }
        if (str.startsWith("334020")) {
            return "networkflow-simcard-mx.iov.byd.auto";
        }
        if (str.startsWith("43408")) {
            return "networkflow-simcard-uz.iov.byd.auto";
        }
        if (str.startsWith("405869")) {
            return "networkflow-simcard-in.iov.byd.auto";
        }
        if (str.startsWith("45204")) {
        }
        return "networkflow-simcard-global.iov.byd.auto";
    }

    public String f() {
        return "POST";
    }

    public String g() {
        return "api.base.goodsUsedInfo.query";
    }
}
