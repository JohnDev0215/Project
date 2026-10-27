package com.android.providers.downloads;

import android.app.DownloadManager;
import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.security.NetworkSecurityPolicy;
import android.security.net.config.ApplicationConfig;
import java.security.GeneralSecurityException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

/* JADX INFO: loaded from: classes.dex */
class RealSystemFacade implements SystemFacade {
    private Context mContext;

    public RealSystemFacade(Context context) {
        this.mContext = context;
    }

    @Override // com.android.providers.downloads.SystemFacade
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    @Override // com.android.providers.downloads.SystemFacade
    public Network getNetwork(JobParameters jobParameters) {
        return jobParameters.getNetwork();
    }

    @Override // com.android.providers.downloads.SystemFacade
    public NetworkInfo getNetworkInfo(Network network, int i, boolean z) {
        return ((ConnectivityManager) this.mContext.getSystemService(ConnectivityManager.class)).getNetworkInfoForUid(network, i, z);
    }

    @Override // com.android.providers.downloads.SystemFacade
    public NetworkCapabilities getNetworkCapabilities(Network network) {
        return ((ConnectivityManager) this.mContext.getSystemService(ConnectivityManager.class)).getNetworkCapabilities(network);
    }

    @Override // com.android.providers.downloads.SystemFacade
    public long getMaxBytesOverMobile() {
        Long maxBytesOverMobile = DownloadManager.getMaxBytesOverMobile(this.mContext);
        if (maxBytesOverMobile == null) {
            return Long.MAX_VALUE;
        }
        return maxBytesOverMobile.longValue();
    }

    @Override // com.android.providers.downloads.SystemFacade
    public long getRecommendedMaxBytesOverMobile() {
        Long recommendedMaxBytesOverMobile = DownloadManager.getRecommendedMaxBytesOverMobile(this.mContext);
        if (recommendedMaxBytesOverMobile == null) {
            return Long.MAX_VALUE;
        }
        return recommendedMaxBytesOverMobile.longValue();
    }

    @Override // com.android.providers.downloads.SystemFacade
    public void sendBroadcast(Intent intent) {
        this.mContext.sendBroadcast(intent);
    }

    @Override // com.android.providers.downloads.SystemFacade
    public void sendBroadcast(Intent intent, String str, Bundle bundle) {
        this.mContext.sendBroadcast(intent, str, bundle);
    }

    @Override // com.android.providers.downloads.SystemFacade
    public boolean userOwnsPackage(int i, String str) throws PackageManager.NameNotFoundException {
        return this.mContext.getPackageManager().getApplicationInfo(str, 0).uid == i;
    }

    @Override // com.android.providers.downloads.SystemFacade
    public SSLContext getSSLContextForPackage(Context context, String str) throws GeneralSecurityException {
        try {
            ApplicationConfig applicationConfigForPackage = NetworkSecurityPolicy.getApplicationConfigForPackage(context, str);
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(null, new TrustManager[]{applicationConfigForPackage.getTrustManager()}, null);
            return sSLContext;
        } catch (PackageManager.NameNotFoundException unused) {
            return SSLContext.getDefault();
        }
    }

    @Override // com.android.providers.downloads.SystemFacade
    public boolean isCleartextTrafficPermitted(String str, String str2) {
        try {
            return NetworkSecurityPolicy.getApplicationConfigForPackage(this.mContext, str).isCleartextTrafficPermitted(str2);
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }
}
