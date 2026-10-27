package com.google.android.setupdesign.util;

import android.content.Context;
import android.widget.FrameLayout;
import com.google.android.setupcompat.partnerconfig.PartnerConfig;
import com.google.android.setupcompat.partnerconfig.PartnerConfigHelper;
import com.google.android.setupdesign.GlifLayout;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class PartnerStyleHelper {
    public static int getLayoutGravity(Context context) {
        String string = PartnerConfigHelper.get(context).getString(context, PartnerConfig.CONFIG_LAYOUT_GRAVITY);
        if (string == null) {
            return 0;
        }
        String lowerCase = string.toLowerCase(Locale.ROOT);
        byte b = -1;
        int iHashCode = lowerCase.hashCode();
        if (iHashCode != -1364013995) {
            if (iHashCode == 109757538 && lowerCase.equals("start")) {
                b = 1;
            }
        } else if (lowerCase.equals("center")) {
            b = 0;
        }
        if (b != 0) {
            return b != 1 ? 0 : 8388611;
        }
        return 17;
    }

    public static boolean isPartnerHeavyThemeLayout(FrameLayout frameLayout) {
        return (frameLayout instanceof GlifLayout) && ((GlifLayout) frameLayout).shouldApplyPartnerHeavyThemeResource();
    }
}
