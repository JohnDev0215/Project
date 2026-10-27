package com.android.car.developeroptions.enterprise;

import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public interface EnterprisePrivacyFeatureProvider {
    CharSequence getDeviceOwnerDisclosure();

    String getDeviceOwnerOrganizationName();

    String getImeLabelIfOwnerSet();

    Date getLastBugReportRequestTime();

    Date getLastNetworkLogRetrievalTime();

    Date getLastSecurityLogRetrievalTime();

    int getMaximumFailedPasswordsBeforeWipeInCurrentUser();

    int getMaximumFailedPasswordsBeforeWipeInManagedProfile();

    int getNumberOfActiveDeviceAdminsForCurrentUserAndManagedProfile();

    int getNumberOfOwnerInstalledCaCertsForCurrentUser();

    int getNumberOfOwnerInstalledCaCertsForManagedProfile();

    boolean hasDeviceOwner();

    boolean isAlwaysOnVpnSetInCurrentUser();

    boolean isAlwaysOnVpnSetInManagedProfile();

    boolean isGlobalHttpProxySet();

    boolean isInCompMode();

    boolean isNetworkLoggingEnabled();

    boolean isSecurityLoggingEnabled();
}
