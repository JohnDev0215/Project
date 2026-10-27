package com.amazon.music.connect.contacts;

import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.BuildInfoProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.amazon.music.platform.providers.MetricsProvider;
import dagger.MembersInjector;

/* JADX INFO: loaded from: classes4.dex */
public final class ContactImportWebViewFragment_MembersInjector implements MembersInjector<ContactImportWebViewFragment> {
    public static void injectAuthenticationProvider(ContactImportWebViewFragment contactImportWebViewFragment, AuthenticationProvider authenticationProvider) {
        contactImportWebViewFragment.authenticationProvider = authenticationProvider;
    }

    public static void injectDeviceInfoProvider(ContactImportWebViewFragment contactImportWebViewFragment, DeviceInfoProvider deviceInfoProvider) {
        contactImportWebViewFragment.deviceInfoProvider = deviceInfoProvider;
    }

    public static void injectBuildInfoProvider(ContactImportWebViewFragment contactImportWebViewFragment, BuildInfoProvider buildInfoProvider) {
        contactImportWebViewFragment.buildInfoProvider = buildInfoProvider;
    }

    public static void injectMetricsProvider(ContactImportWebViewFragment contactImportWebViewFragment, MetricsProvider metricsProvider) {
        contactImportWebViewFragment.metricsProvider = metricsProvider;
    }

    public static void injectContactImportAppInterface(ContactImportWebViewFragment contactImportWebViewFragment, ContactImportProvider contactImportProvider) {
        contactImportWebViewFragment.contactImportAppInterface = contactImportProvider;
    }
}
