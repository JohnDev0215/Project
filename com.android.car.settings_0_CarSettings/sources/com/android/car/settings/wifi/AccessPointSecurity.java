package com.android.car.settings.wifi;

import android.content.Context;
import com.android.car.settings.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class AccessPointSecurity {
    public static final int SECURITY_NONE_POSITION = 0;
    private static final List<Integer> SECURITY_TYPES = Arrays.asList(0, 1, 2, 3, 5, 4, 6);
    private final Context mContext;
    private final int mSecurityType;

    public static List<AccessPointSecurity> getSecurityTypes(Context context) {
        List<AccessPointSecurity> securities = new ArrayList<>();
        Iterator<Integer> it = SECURITY_TYPES.iterator();
        while (it.hasNext()) {
            int security = it.next().intValue();
            securities.add(new AccessPointSecurity(context, security));
        }
        return securities;
    }

    private AccessPointSecurity(Context context, int securityType) {
        this.mContext = context;
        this.mSecurityType = securityType;
    }

    public int getSecurityType() {
        return this.mSecurityType;
    }

    public String toString() {
        switch (this.mSecurityType) {
            case 1:
                return this.mContext.getString(R.string.wifi_security_wep);
            case 2:
                return this.mContext.getString(R.string.wifi_security_psk_generic);
            case 3:
                return this.mContext.getString(R.string.wifi_security_eap);
            case 4:
                return this.mContext.getString(R.string.wifi_security_owe);
            case 5:
                return this.mContext.getString(R.string.wifi_security_sae);
            case 6:
                return this.mContext.getString(R.string.wifi_security_eap_suiteb);
            default:
                return this.mContext.getString(R.string.wifi_security_none);
        }
    }
}
