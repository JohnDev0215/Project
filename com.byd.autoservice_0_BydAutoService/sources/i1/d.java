package i1;

import android.content.Context;
import android.provider.Settings;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: GuideUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3287a = "d";

    public static boolean a(Context context) {
        int i3 = Settings.Global.getInt(context.getContentResolver(), "device_provisioned", 0);
        BydLogHelper.logD(f3287a, "Provision status:" + i3);
        return i3 == 1;
    }

    public static boolean b(Context context) {
        int i3 = Settings.Secure.getInt(context.getContentResolver(), "user_setup_complete", 0);
        BydLogHelper.logD(f3287a, "Setup status:" + i3);
        return i3 == 1;
    }
}
