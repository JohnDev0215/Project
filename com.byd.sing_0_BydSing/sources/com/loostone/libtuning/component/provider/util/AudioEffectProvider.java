package com.loostone.libtuning.component.provider.util;

import OooO00o.OooO00o.OooO0O0.b;
import OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0OO.OooO0O0;
import com.loostone.libtuning.PuremicTuning;
import com.loostone.libtuning.inf.extern.IVolumeAdjustment;
import kotlin.a;
import kotlin.d;

/* JADX INFO: loaded from: classes.dex */
public final class AudioEffectProvider {
    private static final int GET_AEF1_REVERB_ENABLE = 201;
    private static final int GET_AEF1_REVERB_OUT_GAIN = 203;
    private static final int GET_AEF2_ECHO_ENABLE = 243;
    private static final int GET_AEF2_ECHO_FEEDBACK = 251;
    private static final int GET_AEF2_ECHO_GAIN = 245;
    private static final int GET_AEF2_ECHO_TIME = 249;
    private static final int GET_AEF2_ECHO_TYPE = 247;
    private static final int GET_AEF2_EQ1 = 215;
    private static final int GET_AEF2_EQ1_FREQUENCY = 233;
    private static final int GET_AEF2_EQ2 = 216;
    private static final int GET_AEF2_EQ2_FREQUENCY = 234;
    private static final int GET_AEF2_EQ3 = 217;
    private static final int GET_AEF2_EQ3_FREQUENCY = 235;
    private static final int GET_AEF2_EQ4 = 218;
    private static final int GET_AEF2_EQ4_FREQUENCY = 236;
    private static final int GET_AEF2_EQ5 = 219;
    private static final int GET_AEF2_EQ5_FREQUENCY = 237;
    private static final int GET_AEF2_EQ6 = 220;
    private static final int GET_AEF2_EQ6_FREQUENCY = 238;
    private static final int GET_AEF2_EQ7 = 221;
    private static final int GET_AEF2_EQ7_FREQUENCY = 239;
    private static final int GET_AEF2_EQ8 = 222;
    private static final int GET_AEF2_EQ8_FREQUENCY = 240;
    private static final int GET_AEF2_EQ9 = 223;
    private static final int GET_AEF2_EQ9_FREQUENCY = 241;
    private static final int GET_AEF2_EQ_ENABLE = 205;
    private static final int GET_AEF2_REVERB_ENABLE = 253;
    private static final int GET_AEF2_REVERB_MODE = 255;
    private static final int GET_AEF2_REVERB_TIME = 259;
    private static final int GET_AEF2_REVERB_VOLUME = 257;
    private static final int GET_AEF2_VOICE_MODE = 261;
    private static final int GET_ENVIRONMENTAL_SOUND = 112;
    private static final int GET_MAX_AEF1_REVERB_VOLUME = 273;
    private static final int GET_MAX_AEF2_REVERB_VOLUME = 274;
    private static final int GET_MAX_MIC_VOLUME = 272;
    private static final int GET_MIC_VOLUME = 101;
    private static final int GET_MIC_VOLUME_ENABLE = 103;
    private static final int GET_MUSIC_VOLUME = 107;
    private static final int GET_SUPPORT_ADVANCE_EFFECT = 262;
    private static final int GET_SUPPORT_PHONE_TUNING = 1002;
    private static final int GET_SYSTEM_VOLUME = 105;
    private static final int GET_TUNING_SWITCH = 1001;
    private static final int SET_AEF1_REVERB_ENABLE = 200;
    private static final int SET_AEF1_REVERB_OUT_GAIN = 202;
    private static final int SET_AEF2_ECHO_ENABLE = 242;
    private static final int SET_AEF2_ECHO_FEEDBACK = 250;
    private static final int SET_AEF2_ECHO_GAIN = 244;
    private static final int SET_AEF2_ECHO_TIME = 248;
    private static final int SET_AEF2_ECHO_TYPE = 246;
    private static final int SET_AEF2_EQ1 = 206;
    private static final int SET_AEF2_EQ1_FREQUENCY = 224;
    private static final int SET_AEF2_EQ2 = 207;
    private static final int SET_AEF2_EQ2_FREQUENCY = 225;
    private static final int SET_AEF2_EQ3 = 208;
    private static final int SET_AEF2_EQ3_FREQUENCY = 226;
    private static final int SET_AEF2_EQ4 = 209;
    private static final int SET_AEF2_EQ4_FREQUENCY = 227;
    private static final int SET_AEF2_EQ5 = 210;
    private static final int SET_AEF2_EQ5_FREQUENCY = 228;
    private static final int SET_AEF2_EQ6 = 211;
    private static final int SET_AEF2_EQ6_FREQUENCY = 229;
    private static final int SET_AEF2_EQ7 = 212;
    private static final int SET_AEF2_EQ7_FREQUENCY = 230;
    private static final int SET_AEF2_EQ8 = 213;
    private static final int SET_AEF2_EQ8_FREQUENCY = 231;
    private static final int SET_AEF2_EQ9 = 214;
    private static final int SET_AEF2_EQ9_FREQUENCY = 232;
    private static final int SET_AEF2_EQ_ENABLE = 204;
    private static final int SET_AEF2_REVERB_ENABLE = 252;
    private static final int SET_AEF2_REVERB_MODE = 254;
    private static final int SET_AEF2_REVERB_TIME = 258;
    private static final int SET_AEF2_REVERB_VOLUME = 256;
    private static final int SET_AEF2_VOICE_MODE = 260;
    private static final int SET_ENVIRONMENTAL_SOUND = 111;
    private static final int SET_MIC_VOLUME = 100;
    private static final int SET_MIC_VOLUME_ENABLE = 102;
    private static final int SET_MUSIC_VOLUME = 106;
    private static final int SET_SYSTEM_VOLUME = 104;
    private static final int SET_TUNING_SWITCH = 1000;
    private static final String TAG = "AudioEffectProvider";
    private static final int TYPE_GET_ECHO = 6;
    private static final int TYPE_GET_ECHO_MAX = 4;
    private static final int TYPE_GET_MIC = 5;
    private static final int TYPE_GET_MIC_MAX = 3;
    private static final int TYPE_GET_REVERB_MODE = 8;
    private static final int TYPE_GET_VOICE_MODE = 10;
    private static final int TYPE_SET_ECHO = 2;
    private static final int TYPE_SET_MIC = 1;
    private static final int TYPE_SET_REVERB_MODE = 7;
    private static final int TYPE_SET_VOICE_MODE = 9;
    private static final int TYPE_SUPPORT_ADVANCE = 11;
    private static final int VOLUME_DOWN = 271;
    private static final int VOLUME_UP = 270;
    public static final AudioEffectProvider INSTANCE = new AudioEffectProvider();
    private static final a volumeAdjustment$delegate = d.b(new kotlin.jvm.b.a<IVolumeAdjustment>() { // from class: com.loostone.libtuning.component.provider.util.AudioEffectProvider$volumeAdjustment$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.b.a
        public final IVolumeAdjustment invoke() {
            return PuremicTuning.Companion.getInstance().getVolumeAdjustment();
        }
    });

    private AudioEffectProvider() {
    }

    private final IVolumeAdjustment getVolumeAdjustment() {
        return (IVolumeAdjustment) volumeAdjustment$delegate.getValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:70:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01cb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.database.Cursor getValue(int r10) {
        /*
            Method dump skipped, instruction units count: 642
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loostone.libtuning.component.provider.util.AudioEffectProvider.getValue(int):android.database.Cursor");
    }

    public final int oldSetGetValue(int i, int i2) {
        b.f41a.e(TAG, "update typeIndex: " + i + " value: " + i2);
        switch (i) {
            case 1:
                getVolumeAdjustment().setMicVolume(i2);
                break;
            case 2:
                getVolumeAdjustment().setAef1ReverbOutGain(i2);
                break;
            case 3:
            case 4:
                return 100;
            case 5:
                return getVolumeAdjustment().getMicVolume();
            case 6:
                return getVolumeAdjustment().getAef1ReverbOutGain();
            case 7:
                getVolumeAdjustment().setAef2ReverbMode(i2);
                break;
            case 8:
                return getVolumeAdjustment().getAef2ReverbMode();
            case 9:
                getVolumeAdjustment().setAef2VoiceMode(i2);
                break;
            case 10:
                return getVolumeAdjustment().getAef2VoiceMode();
            case 11:
                if (OooO0O0.f531a.e() < 4) {
                    return 0;
                }
                break;
        }
        return 1;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01df  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01e7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int setValue(int r9, int r10) {
        /*
            Method dump skipped, instruction units count: 626
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loostone.libtuning.component.provider.util.AudioEffectProvider.setValue(int, int):int");
    }
}
