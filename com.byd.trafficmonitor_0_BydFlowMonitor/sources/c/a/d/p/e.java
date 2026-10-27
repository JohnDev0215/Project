package c.a.d.p;

import android.content.Context;
import android.text.TextUtils;
import c.a.d.s.k;
import c.a.d.s.l;
import com.bydux.api.R;

/* JADX INFO: compiled from: SimUploadServerConfig.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f3634a = "overseasPad";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f3635b = "/dcm/uploadSimInfo";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f3636c = "https://%s";

    public String a() {
        return "/dcm/uploadSimInfo";
    }

    public String b(String str) {
        return String.format("https://%s", str);
    }

    public final String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.startsWith("90131")) {
            return "eu-simcard-iccid.byd.auto";
        }
        if (str.startsWith("52501") || str.startsWith("45412") || str.startsWith("52004")) {
            return "sg-simcard-iccid.byd.auto";
        }
        if (str.startsWith("44020")) {
            return "jp-simcard-iccid.byd.auto";
        }
        if (str.startsWith("50501")) {
            return "au-simcard-iccid.byd.auto";
        }
        if (str.startsWith("72405")) {
            return "br-simcard-iccid.byd.auto";
        }
        if (str.startsWith("24042")) {
            return "no-simcard-iccid.byd.auto";
        }
        if (str.startsWith("334020")) {
            return "mx-simcard-iccid.byd.auto";
        }
        if (str.startsWith("43408")) {
            return "uz-simcard-iccid.byd.auto";
        }
        if (str.startsWith("405869")) {
            return "in-simcard-iccid.byd.auto";
        }
        if (str.startsWith("51010")) {
            return "id-simcard-iccid.byd.auto";
        }
        if (str.startsWith("28602")) {
            return "tr-simcard-iccid.byd.auto";
        }
        if (str.startsWith("45204")) {
            return "vn-simcard-iccid.byd.auto";
        }
        return null;
    }

    public final String d(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.startsWith("90131")) {
            return "eu-simcard-iccid.denza.auto";
        }
        if (str.startsWith("52501") || str.startsWith("45412") || str.startsWith("52004")) {
            return "sg-simcard-iccid.denza.auto";
        }
        if (str.startsWith("44020")) {
            return "jp-simcard-iccid.denza.auto";
        }
        if (str.startsWith("50501")) {
            return "au-simcard-iccid.denza.auto";
        }
        if (str.startsWith("72405")) {
            return "br-simcard-iccid.denza.auto";
        }
        if (str.startsWith("24042")) {
            return "no-simcard-iccid.denza.auto";
        }
        if (str.startsWith("334020")) {
            return "mx-simcard-iccid.denza.auto";
        }
        if (str.startsWith("43408")) {
            return "uz-simcard-iccid.denza.auto";
        }
        if (str.startsWith("405869")) {
            return "in-simcard-iccid.denza.auto";
        }
        if (str.startsWith("51010")) {
            return "id-simcard-iccid.denza.auto";
        }
        if (str.startsWith("28602")) {
            return "tr-simcard-iccid.denza.auto";
        }
        return null;
    }

    public String e(String str, Context context) {
        return c.a.d.s.c.a(context) == 1 ? d(str) : c(str);
    }

    public String f() {
        return "overseasPad";
    }

    public String g(Context context) {
        StringBuffer stringBuffer = new StringBuffer();
        String strC = l.c(context.getString(R.string.sim_pwd_start));
        String strB = l.b(k.b(context, "flow_pwd_M"));
        String string = context.getString(R.string.sim_pwd_last);
        stringBuffer.append(strC);
        stringBuffer.append(strB);
        stringBuffer.append(string);
        return stringBuffer.toString();
    }

    public String h(Context context) {
        StringBuffer stringBuffer = new StringBuffer();
        String strC = l.c(context.getString(R.string.sim_token_start));
        String strB = l.b(k.b(context, "flow_token_M"));
        String string = context.getString(R.string.sim_token_last);
        stringBuffer.append(strC);
        stringBuffer.append(strB);
        stringBuffer.append(string);
        return stringBuffer.toString();
    }
}
