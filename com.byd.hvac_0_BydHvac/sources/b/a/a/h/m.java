package b.a.a.h;

import android.content.Context;
import android.media.AudioManager;
import android.provider.Settings;
import android.view.View;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public final class m {
    public static boolean a(Context context) {
        if (context == null) {
            return false;
        }
        int i = Settings.System.getInt(context.getContentResolver(), "sound_effects_enabled", 0);
        BydLogHelper.logD("isSoundEffectsEnable switchOn :" + i);
        return i == 1;
    }

    public static void b(View view) {
        if (view == null || view.getContext() == null) {
            BydLogHelper.logE("playWheelSoundEffect view == null || view.getContext() == null:");
            return;
        }
        Context context = view.getContext();
        if (!a(context)) {
            BydLogHelper.logE("playWheelSoundEffect system sound switch off:");
        } else {
            ((AudioManager) context.getSystemService(AudioManager.class)).playSoundEffect(10);
            BydLogHelper.logD("playWheelSoundEffect playSoundEffect:");
        }
    }
}
