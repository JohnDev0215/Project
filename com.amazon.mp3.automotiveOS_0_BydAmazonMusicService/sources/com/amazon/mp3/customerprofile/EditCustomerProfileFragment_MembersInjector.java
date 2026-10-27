package com.amazon.mp3.customerprofile;

import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.music.platform.providers.MetricsProvider;
import dagger.MembersInjector;

/* JADX INFO: loaded from: classes3.dex */
public final class EditCustomerProfileFragment_MembersInjector implements MembersInjector<EditCustomerProfileFragment> {
    public static void injectAuthenticationProvider(EditCustomerProfileFragment instance, AuthenticationProvider authenticationProvider) {
        instance.authenticationProvider = authenticationProvider;
    }

    public static void injectMetricsProvider(EditCustomerProfileFragment instance, MetricsProvider metricsProvider) {
        instance.metricsProvider = metricsProvider;
    }

    public static void injectFeatureGateProvider(EditCustomerProfileFragment instance, FeatureGateProvider featureGateProvider) {
        instance.featureGateProvider = featureGateProvider;
    }
}
