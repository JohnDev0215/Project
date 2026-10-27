package g5;

import android.content.Context;
import android.os.SystemProperties;
import android.provider.Settings;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: BydSystemSettings.java */
/* JADX INFO: loaded from: classes2.dex */
public class b {
    public static boolean a(Context context) {
        int i8 = SystemProperties.getInt("persist.byd.privacy.policy", 0);
        BydLogHelper.logD("BydSystemSettings", "boot privacy anInt:" + i8);
        return b(context) && (i8 == 1);
    }

    public static boolean b(Context context) {
        int i8 = Settings.System.getInt(context.getContentResolver(), "sys.byd.safety.warn", 0);
        BydLogHelper.logD("BydSystemSettings", "SafetyWarn anInt:" + i8);
        return i8 == 1;
    }
}
