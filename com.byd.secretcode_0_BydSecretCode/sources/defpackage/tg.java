package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.provider.Settings;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.List;

/* JADX INFO: compiled from: ToolsUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class tg {
    public static tg a;

    public static tg b() {
        tg tgVar = a;
        if (tgVar != null) {
            return tgVar;
        }
        tg tgVar2 = new tg();
        a = tgVar2;
        return tgVar2;
    }

    public static String e(String str) {
        char[] charArray = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : charArray) {
            if (c < 'a' || c > 'z') {
                sb.append(c);
            } else {
                sb.append((char) (c - ' '));
            }
        }
        BydLogHelper.logD("builder.toString() = " + sb.toString());
        return sb.toString();
    }

    public boolean a(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "airplane_mode_on", 0) == 1;
    }

    public boolean c(Context context, String str) {
        ng.a("ToolsUtil", "isAppInstalled : " + str);
        List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
        if (installedPackages == null) {
            return false;
        }
        for (int i = 0; i < installedPackages.size(); i++) {
            ng.a("ToolsUtil", "Package[" + installedPackages.size() + "]:mPackageInfo.");
            String str2 = installedPackages.get(i).packageName;
            if (str2 != null && str2.equals(str)) {
                ng.a("ToolsUtil", "Package[" + str + "]:is installed.");
                return true;
            }
        }
        return false;
    }

    public void d(Context context, boolean z) {
        Settings.Global.putInt(context.getContentResolver(), "airplane_mode_on", z ? 1 : 0);
        Intent intent = new Intent("android.intent.action.AIRPLANE_MODE");
        intent.putExtra("state", z);
        context.sendBroadcast(intent);
    }
}
