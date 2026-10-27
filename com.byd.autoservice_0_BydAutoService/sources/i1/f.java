package i1;

import android.content.Context;
import android.content.pm.PackageInfo;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.List;

/* JADX INFO: compiled from: ToolsUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static f f3289a = new f();

    public static f a() {
        return f3289a;
    }

    public boolean b(Context context, String str) {
        BydLogHelper.logD("ToolsUtil", "isAppInstalled : " + str);
        List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
        for (int i3 = 0; i3 < installedPackages.size(); i3++) {
            BydLogHelper.logD("ToolsUtil", "Package[" + installedPackages.size() + "]:mPackageInfo.");
            String str2 = installedPackages.get(i3).packageName;
            if (str2 != null && str2.equals(str)) {
                BydLogHelper.logD("ToolsUtil", "Package[" + str + "]:is installed.");
                return true;
            }
        }
        return false;
    }
}
