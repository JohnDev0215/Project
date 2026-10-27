package b.b.g.o;

import android.content.Context;
import android.media.AudioManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: ViewSoundEffectUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f1341a = "byd_idler_wheel";

    public static void a(View view, String str, int i) {
        if (view == null || view.getContext() == null) {
            return;
        }
        Context context = view.getContext();
        if (b(context) && c(context, str)) {
            ((AudioManager) context.getSystemService(AudioManager.class)).playSoundEffect(i);
        }
    }

    public static boolean b(Context context) {
        if (context == null) {
            return false;
        }
        int i = Settings.System.getInt(context.getContentResolver(), "sound_effects_enabled", 0);
        BydLogHelper.logD("isSoundEffectsEnable switchOn :" + i);
        return i == 1;
    }

    public static boolean c(Context context, String str) {
        return (context == null || TextUtils.isEmpty(str) || Settings.System.getInt(context.getContentResolver(), str, 1) == 0) ? false : true;
    }

    public static void d(View view, int i) {
        if (view == null || view.getContext() == null) {
            return;
        }
        ((AudioManager) view.getContext().getSystemService(AudioManager.class)).playSoundEffect(i);
    }

    public static void e(View view) {
        a(view, f1341a, 10);
    }
}
