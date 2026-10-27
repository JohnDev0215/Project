package com.micphone;

import android.content.Context;
import android.util.Log;
import com.audio.MicphoneService.R;
import com.micphone.utils.ReflectUtil;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class TsBydEffectFile {
    private static final int BYD_AMP_TYPE_PROP_DANNA = 1;
    private static final int BYD_AMP_TYPE_PROP_DEFAULT = 0;
    private static final int BYD_AMP_TYPE_PROP_DWL = 3;
    private static final int BYD_AMP_TYPE_PROP_HAMAN = 2;
    private static final String BYD_AMP_TYPE_PROP_KEY = "sys.ext.amp.type";
    private static final int BYD_CAR_ID_HAI_BAO_0x8A = 138;
    private static final int BYD_CAR_ID_HAI_BAO_0xA5 = 165;
    private static final int BYD_CAR_ID_HAI_BAO_0xCD = 205;
    private static final int BYD_CAR_ID_HAI_BAO_DMI_0x8C = 140;
    private static final int BYD_CAR_ID_HAI_BAO_DMI_0xCE = 206;
    private static final int BYD_CAR_ID_HAI_OU_0xD7 = 215;
    private static final int BYD_CAR_ID_HAI_TUN_0xBE = 190;
    private static final int BYD_CAR_ID_HAN_0x79 = 121;
    private static final int BYD_CAR_ID_HAN_0x87 = 135;
    private static final int BYD_CAR_ID_HAN_0xD2 = 210;
    private static final int BYD_CAR_ID_QIN_PLUS_0x66 = 102;
    private static final int BYD_CAR_ID_SONG_L_0x9E = 158;
    private static final int BYD_CAR_ID_SONG_PLUS_0x47 = 71;
    private static final int BYD_CAR_ID_SONG_PLUS_0xA2 = 162;
    private static final int BYD_CAR_ID_SONG_PLUS_0xAD = 173;
    private static final int BYD_CAR_ID_TANG_0x88 = 136;
    private static final int BYD_CAR_ID_TANG_0x89 = 137;
    private static final int BYD_CAR_ID_TANG_0xC3 = 195;
    private static final int BYD_CAR_ID_TANG_0xCB = 203;
    private static final int BYD_CAR_ID_TANG_0xD1 = 209;
    private static final int BYD_CAR_ID_TANG_8155_0xC4 = 196;
    private static final int BYD_CAR_ID_YUAN_001 = 128;
    private static final String TAG = "TsBydEffectFile";
    private static volatile TsBydEffectFile mTsBydEffectFile;
    private Context mContext;

    private TsBydEffectFile(Context context) {
        this.mContext = context;
    }

    public static TsBydEffectFile getInstance(Context context) {
        if (mTsBydEffectFile == null) {
            synchronized (TsBydEffectFile.class) {
                if (mTsBydEffectFile == null) {
                    mTsBydEffectFile = new TsBydEffectFile(context);
                }
            }
        }
        return mTsBydEffectFile;
    }

    private InputStream getUfHaiBao(int i) {
        if (i == 0) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_haibao_record_2);
        }
        if (i == 1) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_haibao_ktv_2);
        }
        if (i == 2) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_haibao_music_2);
        }
        if (i != 3) {
            return null;
        }
        return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_haibao_default_2);
    }

    private InputStream getUfHaiBaoDmi(int i) {
        if (i == 0) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_haibao_dmi_record_1);
        }
        if (i == 1) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_haibao_dmi_ktv_1);
        }
        if (i == 2) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_haibao_dmi_music_1);
        }
        if (i != 3) {
            return null;
        }
        return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_haibao_dmi_default_1);
    }

    private InputStream getUfHaiOu(int i) {
        if (i == 0) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_haiou_record_1);
        }
        if (i == 1) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_haiou_ktv_1);
        }
        if (i == 2) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_haiou_music_1);
        }
        if (i != 3) {
            return null;
        }
        return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_haiou_default_1);
    }

    private InputStream getUfHaiTun(int i) {
        if (i == 0) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_haitun_record_2);
        }
        if (i == 1) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_haitun_ktv_2);
        }
        if (i == 2) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_haitun_music_2);
        }
        if (i != 3) {
            return null;
        }
        return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_haitun_default_2);
    }

    private InputStream getUfHan(int i) {
        if (i == 0) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_han_record_2);
        }
        if (i == 1) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_han_ktv_2);
        }
        if (i == 2) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_han_music_2);
        }
        if (i != 3) {
            return null;
        }
        return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_han_default_2);
    }

    private InputStream getUfQinPlus(int i) {
        if (i == 0) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_qinplus_record_1);
        }
        if (i == 1) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_qinplus_ktv_1);
        }
        if (i == 2) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_qinplus_music_1);
        }
        if (i != 3) {
            return null;
        }
        return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_qinplus_default_1);
    }

    private InputStream getUfSongL(int i) {
        if (i == 0) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_songl_record_4);
        }
        if (i == 1) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_songl_ktv_5);
        }
        if (i == 2) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_songl_music_4);
        }
        if (i != 3) {
            return null;
        }
        return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_songl_default_4);
    }

    private InputStream getUfSongPlus(int i) {
        if (i == 0) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_songplus_record_2);
        }
        if (i == 1) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_songplus_ktv_2);
        }
        if (i == 2) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_songplus_music_2);
        }
        if (i != 3) {
            return null;
        }
        return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_songplus_default_2);
    }

    private InputStream getUfTang(int i) {
        if (i == 0) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_tang_record_2);
        }
        if (i == 1) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_tang_ktv_3);
        }
        if (i == 2) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_tang_music_2);
        }
        if (i != 3) {
            return null;
        }
        return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_tang_default_2);
    }

    private InputStream getUfTang8155(int i) {
        if (i == 0) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_tang8155_record_1);
        }
        if (i == 1) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_tang8155_ktv_1);
        }
        if (i == 2) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_tang8155_music_1);
        }
        if (i != 3) {
            return null;
        }
        return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_tang8155_default_1);
    }

    private InputStream getUfYuan(int i) {
        if (i == 0) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_yuan_record_2);
        }
        if (i == 1) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_yuan_ktv_2);
        }
        if (i == 2) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_yuan_music_2);
        }
        if (i != 3) {
            return null;
        }
        return this.mContext.getResources().openRawResource(R.raw.thunder_byd_uf_yuan_default_2);
    }

    public InputStream getHtEffectFile(int i) {
        if (i == 0) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_ht_record_1);
        }
        if (i == 1) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_ht_ktv_1);
        }
        if (i == 2) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_ht_music_1);
        }
        if (i != 3) {
            return null;
        }
        return this.mContext.getResources().openRawResource(R.raw.thunder_byd_ht_default_1);
    }

    public InputStream getSa3EffectFile(int i) {
        if (i == 0) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_sa3_record_4);
        }
        if (i == 1) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_sa3_ktv_5);
        }
        if (i == 2) {
            return this.mContext.getResources().openRawResource(R.raw.thunder_byd_sa3_music_4);
        }
        if (i != 3) {
            return null;
        }
        return this.mContext.getResources().openRawResource(R.raw.thunder_byd_sa3_default_4);
    }

    public InputStream getUfEffectFile(int i, int i2) {
        String property = ReflectUtil.getProperty(BYD_AMP_TYPE_PROP_KEY, "0");
        String str = TAG;
        Log.e(str, "amp_type " + property);
        if (i == 173 || i == 162 || i == 71) {
            return getUfSongPlus(i2);
        }
        if (i == 121 || i == 135 || i == 210) {
            return getUfHan(i2);
        }
        if (i == 137 || i == 136 || i == 203 || i == 195 || i == 209) {
            return getUfTang(i2);
        }
        if (i == 165 || i == 138 || i == 205) {
            return getUfHaiBao(i2);
        }
        if (i == 190) {
            return getUfHaiTun(i2);
        }
        if (i == 158) {
            return getUfSongL(i2);
        }
        if (i == 128) {
            return getUfYuan(i2);
        }
        if (i == 140 || i == 206) {
            return getUfHaiBaoDmi(i2);
        }
        if (i == 215) {
            return getUfHaiOu(i2);
        }
        if (i == 102) {
            return getUfQinPlus(i2);
        }
        if (i == 196) {
            return getUfTang8155(i2);
        }
        Log.e(str, "effect no match return ufTang");
        return getUfTang(i2);
    }
}
