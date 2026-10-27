package g5;

import android.content.Context;
import android.media.AudioManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;

/* JADX INFO: compiled from: ViewSoundEffectUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public final class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f5088a = "byd_idler_wheel";

    public static void a(View view, String str, int i8) {
        if (view == null || view.getContext() == null) {
            return;
        }
        Context context = view.getContext();
        if (b(context, str)) {
            ((AudioManager) context.getSystemService(AudioManager.class)).playSoundEffect(i8);
        }
    }

    public static boolean b(Context context, String str) {
        return (context == null || TextUtils.isEmpty(str) || Settings.System.getInt(context.getContentResolver(), str, 1) == 0) ? false : true;
    }

    public static void c(View view) {
        a(view, f5088a, 10);
    }
}
