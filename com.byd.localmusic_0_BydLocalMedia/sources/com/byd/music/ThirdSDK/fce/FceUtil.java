package com.byd.music.ThirdSDK.fce;

import com.blankj.utilcode.util.Utils;
import com.byd.music.utils.LogUtil;

/* JADX INFO: loaded from: classes5.dex */
public class FceUtil {
    public static final String AudialsAudioFocusPKG = "com.audials";
    public static final String AudialsPKG = "com.fce.onlinemedia.audials";
    public static final String AudibleAudioFocusPKG = "com.audible.application";
    public static final String AudiblePKG = "com.fce.onlinemedia.audible";
    public static final String BloomBergAudioFocusPKG = "com.bloomberg.android.plus";
    public static final String BloomBergPKG = "com.fce.onlinemedia.bloomberg";
    public static final String DeezerAudioFocusPKG = "deezer.android.app";
    public static final String DeezerPKG = "com.fce.onlinemedia.deezer";
    public static final String FLOAudioFocusPKG = "skplanet.musicmate";
    public static final String FLOPKG = "com.fce.onlinemedia.musicmate";
    public static final String IheartradioAudioFocusPKG = "com.clearchannel.iheartradio.controller";
    public static final String IheartradioPKG = "com.fce.onlinemedia.iheartradio";
    public static final String LibbyAudioFocusPKG = "com.overdrive.mobile.android.automotive.libby";
    public static final String LibbyPKG = "com.fce.onlinemedia.libby";
    public static final String MelonAudioFocusPKG = "com.iloen.melon";
    public static final String MelonPKG = "com.fce.onlinemedia.melon";
    public static final String PocketcastsAudioFocusPKG = "au.com.shiftyjelly.pocketcasts";
    public static final String PocketcastsPKG = "com.fce.onlinemedia.pocketcasts";
    public static final String RadiolineAudioFocusPKG = "com.radioline.android.radioline.auto";
    public static final String RadiolinePKG = "com.fce.onlinemedia.radioline";
    public static final String TidalAudioFocusPKG = "com.aspiro.tidal";
    public static final String TidalPKG = "com.fce.onlinemedia.tidal";
    public static final String TuneinAudioFocusPKG = "tunein.player";
    public static final String TuneinPKG = "com.fce.onlinemedia.tunein";

    public static boolean isPKGInstall(String str) {
        boolean z = Utils.getApp().getPackageManager().getLaunchIntentForPackage(str) != null;
        LogUtil.e("OnlineMusic", "isPKGInstall check for " + str + ", isInstall = " + z);
        return z;
    }
}
