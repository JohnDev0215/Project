package com.amazon.identity.auth.device.features;

import android.content.Context;
import com.amazon.identity.auth.device.framework.IsolatedModeSwitcher;
import com.amazon.identity.auth.device.qe;
import com.amazon.identity.auth.device.t9;
import com.amazon.identity.auth.device.v5;
import com.amazon.identity.platform.weblab.Weblab;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public enum Feature {
    OverrideDeviceAttributes { // from class: com.amazon.identity.auth.device.features.Feature.1
        @Override // com.amazon.identity.auth.device.features.Feature
        public boolean fetchValue(Context context) {
            return !qe.f(context);
        }
    },
    DSNWhenNotRegistered { // from class: com.amazon.identity.auth.device.features.Feature.2
        @Override // com.amazon.identity.auth.device.features.Feature
        public boolean fetchValue(Context context) {
            return !t9.c(context) || qe.f(context);
        }
    },
    PandaRegistration { // from class: com.amazon.identity.auth.device.features.Feature.3
        @Override // com.amazon.identity.auth.device.features.Feature
        public boolean fetchValue(Context context) {
            if ("panda".equals(new v5().a("com.amazon.map.registration"))) {
                return true;
            }
            return !t9.c(context);
        }
    },
    SplitRegistration { // from class: com.amazon.identity.auth.device.features.Feature.4
        @Override // com.amazon.identity.auth.device.features.Feature
        public boolean fetchValue(Context context) {
            if ("split".equals(new v5().a("com.amazon.map.registration"))) {
                return true;
            }
            return !qe.m(context);
        }
    },
    DirectedIdSupported { // from class: com.amazon.identity.auth.device.features.Feature.5
        @Override // com.amazon.identity.auth.device.features.Feature
        public boolean fetchValue(Context context) {
            return qe.j(context);
        }
    },
    SplitRegistrationWithDirectedId { // from class: com.amazon.identity.auth.device.features.Feature.6
        @Override // com.amazon.identity.auth.device.features.Feature
        public boolean fetchValue(Context context) {
            return !qe.m(context);
        }
    },
    RegistrationViaAuthToken { // from class: com.amazon.identity.auth.device.features.Feature.7
        @Override // com.amazon.identity.auth.device.features.Feature
        public boolean fetchValue(Context context) {
            return qe.m(context);
        }
    },
    UseDeviceLocaleAsLanguagePreference { // from class: com.amazon.identity.auth.device.features.Feature.8
        @Override // com.amazon.identity.auth.device.features.Feature
        public boolean fetchValue(Context context) {
            return qe.f(context);
        }
    },
    IsolateApplication { // from class: com.amazon.identity.auth.device.features.Feature.9
        @Override // com.amazon.identity.auth.device.features.Feature
        public boolean fetchValue(Context context) {
            return IsolatedModeSwitcher.isAppInIsolatedMode(context);
        }
    },
    SecondaryRegistrationUsingPanda { // from class: com.amazon.identity.auth.device.features.Feature.10
        @Override // com.amazon.identity.auth.device.features.Feature
        public boolean fetchValue(Context context) {
            return Weblab.Treatment.T1.equals(Weblab.f715a.a(context));
        }
    };

    public abstract boolean fetchValue(Context context);

    public boolean fetchValueNoCache(Context context) {
        return fetchValue(context);
    }
}
