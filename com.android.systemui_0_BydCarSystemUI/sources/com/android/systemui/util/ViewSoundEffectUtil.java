package com.android.systemui.util;

import android.content.Context;
import android.media.AudioManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public final class ViewSoundEffectUtil {
    public static String BYD_IDLER_WHEEL = "byd_idler_wheel";
    public static final String DTMF_BT = "byd_bluetooth_dial";
    public static final int STATE_OFF = 0;
    public static final int STATE_ON = 1;

    public static boolean isSwitchOn(Context ctx, String key) {
        return (ctx == null || TextUtils.isEmpty(key) || Settings.System.getInt(ctx.getContentResolver(), key, 1) == 0) ? false : true;
    }

    public static void checkAndPlay(View view, String key, int soundConstant) {
        if (view == null || view.getContext() == null) {
            return;
        }
        Context context = view.getContext();
        boolean zIsSoundEffectsEnable = isSoundEffectsEnable(context);
        BydLogHelper.logD("checkAndPlay : " + zIsSoundEffectsEnable);
        if (zIsSoundEffectsEnable) {
            ((AudioManager) context.getSystemService(AudioManager.class)).playSoundEffect(soundConstant);
        }
    }

    public static void playWheelSoundEffect(View view) {
        checkAndPlay(view, BYD_IDLER_WHEEL, 10);
    }

    public static void setSoundEffectsEnable(Context context, boolean z) {
        if (context == null) {
            return;
        }
        Settings.System.putInt(context.getContentResolver(), "sound_effects_enabled", z ? 1 : 0);
    }

    public static boolean isSoundEffectsEnable(Context ctx) {
        return (ctx == null || Settings.System.getInt(ctx.getContentResolver(), "sound_effects_enabled", 0) == 0) ? false : true;
    }

    public static void playViewSound(View view, int soundConstant) {
        if (view == null || view.getContext() == null) {
            return;
        }
        ((AudioManager) view.getContext().getSystemService(AudioManager.class)).playSoundEffect(soundConstant);
    }
}
