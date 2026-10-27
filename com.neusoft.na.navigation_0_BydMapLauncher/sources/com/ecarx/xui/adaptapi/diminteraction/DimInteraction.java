package com.ecarx.xui.adaptapi.diminteraction;

import android.content.Context;
import android.util.Log;
import com.ecarx.xui.adaptapi.sign.SignVerify;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DimInteraction extends AdaptAPI {
    public static final int PARAMS_TYPE_FLT = 1;
    public static final int PARAMS_TYPE_INT = 0;
    public static final int PARAMS_TYPE_STR = 2;

    public abstract IMediaInteraction getMediaInteraction();

    public abstract IPhoneCallInteraction getPhoneCallInteraction();

    public abstract int getSupportedRankingType();

    public abstract IVrInteraction getVrInteraction();

    public abstract void updateAvgFuleRanking(int i, String str);

    public DimInteraction() {
        Log.d("diminteraction", "DimInteraction");
    }

    public static DimInteraction create(Context context) {
        Log.d("diminteraction", "DimInteraction create");
        if (SignVerify.instance().appVerify(context)) {
            return new MainDimInteraction(context);
        }
        return null;
    }
}
