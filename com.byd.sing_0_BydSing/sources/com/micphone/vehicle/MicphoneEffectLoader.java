package com.micphone.vehicle;

import android.content.Context;
import android.util.Log;
import com.micphone.service.IEffectLoader;

/* JADX INFO: loaded from: classes.dex */
public class MicphoneEffectLoader implements IVehicleEffectLoader {
    private static final String TAG = "MicphoneEffectLoader";
    private static MicphoneEffectLoader instance;
    private Context mContext;
    private IEffectLoader mLoader;

    MicphoneEffectLoader(Context context, IEffectLoader iEffectLoader) {
        this.mLoader = iEffectLoader;
        this.mContext = context;
        Log.e(TAG, "MicphoneEffectLoader: byd");
    }

    public static MicphoneEffectLoader getInstance(Context context, IEffectLoader iEffectLoader) {
        if (instance == null) {
            instance = new MicphoneEffectLoader(context, iEffectLoader);
        }
        return instance;
    }

    @Override // com.micphone.vehicle.IVehicleEffectLoader
    public void loadEffect() {
    }
}
