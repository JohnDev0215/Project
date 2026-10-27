package com.amazon.weblab.mobile;

import android.content.Context;
import com.amazon.weblab.mobile.experimental.PlatformWeblabs;
import com.amazon.weblab.mobile.settings.MobileWeblabClientAttributes;
import com.amazon.weblab.mobile.settings.MobileWeblabRuntimeConfiguration;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/* JADX INFO: loaded from: classes5.dex */
public class MobileWeblabClientFactory {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static HashMap<String, WeakReference<IMobileWeblabClient>> sClients = new HashMap<>();

    public static synchronized IMobileWeblabClient createMobileWeblabClient(MobileWeblabClientAttributes mobileWeblabClientAttributes, MobileWeblabRuntimeConfiguration mobileWeblabRuntimeConfiguration, String str, String str2, String str3, Context context) throws IllegalArgumentException {
        WeblabClientWithNameValidation weblabClientWithNameValidation;
        try {
            if (mobileWeblabClientAttributes == null) {
                throw new IllegalArgumentException("clientAttr has no registered weblabs");
            }
            if (sClients.containsKey(mobileWeblabClientAttributes.getIdentifier()) && sClients.get(mobileWeblabClientAttributes.getIdentifier()).get() != null) {
                throw new IllegalArgumentException("attr must have a unique identifier");
            }
            if (context == null) {
                throw new IllegalArgumentException("application context cannot be null");
            }
            weblabClientWithNameValidation = new WeblabClientWithNameValidation(new MobileWeblabClientAttributes(mobileWeblabClientAttributes, PlatformWeblabs.values()), new MobileWeblabRuntimeConfiguration(mobileWeblabRuntimeConfiguration), str, str2, str3, context);
            sClients.put(mobileWeblabClientAttributes.getIdentifier(), new WeakReference<>(weblabClientWithNameValidation));
        } finally {
        }
        return weblabClientWithNameValidation;
    }
}
