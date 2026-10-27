package com.google.firebase.abt.component;

import android.content.Context;
import androidx.annotation.GuardedBy;
import androidx.annotation.VisibleForTesting;
import com.google.firebase.abt.FirebaseABTesting;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.inject.Provider;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes6.dex */
public class AbtComponent {

    @GuardedBy("this")
    private final Map<String, FirebaseABTesting> abtOriginInstances = new HashMap();
    private final Provider<AnalyticsConnector> analyticsConnector;
    private final Context appContext;

    @VisibleForTesting(otherwise = 3)
    protected AbtComponent(Context context, Provider<AnalyticsConnector> provider) {
        this.appContext = context;
        this.analyticsConnector = provider;
    }

    public synchronized FirebaseABTesting get(String str) {
        if (!this.abtOriginInstances.containsKey(str)) {
            this.abtOriginInstances.put(str, createAbtInstance(str));
        }
        return this.abtOriginInstances.get(str);
    }

    @VisibleForTesting
    protected FirebaseABTesting createAbtInstance(String str) {
        return new FirebaseABTesting(this.appContext, this.analyticsConnector, str);
    }
}
