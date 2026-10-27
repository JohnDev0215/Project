package io.branch.referral;

import android.content.Context;
import android.os.RemoteException;
import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;

/* JADX INFO: loaded from: classes7.dex */
class InstallReferrerClientWrapper {
    private Context context_;
    private Object mReferrerClient;

    interface InstallReferrerWrapperListener {
        void onReferrerClientError();

        void onReferrerClientFinished(Context context, String str, long j, long j2);
    }

    InstallReferrerClientWrapper(Context context) {
        this.context_ = context;
    }

    boolean getReferrerUsingReferrerClient(final InstallReferrerWrapperListener installReferrerWrapperListener) {
        try {
            InstallReferrerClient installReferrerClientBuild = InstallReferrerClient.newBuilder(this.context_).build();
            this.mReferrerClient = installReferrerClientBuild;
            installReferrerClientBuild.startConnection(new InstallReferrerStateListener() { // from class: io.branch.referral.InstallReferrerClientWrapper.1
                @Override // com.android.installreferrer.api.InstallReferrerStateListener
                public void onInstallReferrerSetupFinished(int i) {
                    long j;
                    long installBeginTimestampSeconds;
                    if (i != 0) {
                        if (i == 1) {
                            installReferrerWrapperListener.onReferrerClientError();
                            return;
                        } else if (i == 2) {
                            installReferrerWrapperListener.onReferrerClientError();
                            return;
                        } else {
                            if (i != 3) {
                                return;
                            }
                            installReferrerWrapperListener.onReferrerClientError();
                            return;
                        }
                    }
                    try {
                        if (InstallReferrerClientWrapper.this.mReferrerClient != null) {
                            ReferrerDetails installReferrer = ((InstallReferrerClient) InstallReferrerClientWrapper.this.mReferrerClient).getInstallReferrer();
                            String installReferrer2 = null;
                            if (installReferrer != null) {
                                installReferrer2 = installReferrer.getInstallReferrer();
                                long referrerClickTimestampSeconds = installReferrer.getReferrerClickTimestampSeconds();
                                installBeginTimestampSeconds = installReferrer.getInstallBeginTimestampSeconds();
                                j = referrerClickTimestampSeconds;
                            } else {
                                j = 0;
                                installBeginTimestampSeconds = 0;
                            }
                            installReferrerWrapperListener.onReferrerClientFinished(InstallReferrerClientWrapper.this.context_, installReferrer2, j, installBeginTimestampSeconds);
                        }
                    } catch (RemoteException e) {
                        PrefHelper.Debug("onInstallReferrerSetupFinished() Exception: " + e.getMessage());
                        installReferrerWrapperListener.onReferrerClientError();
                    }
                }

                @Override // com.android.installreferrer.api.InstallReferrerStateListener
                public void onInstallReferrerServiceDisconnected() {
                    installReferrerWrapperListener.onReferrerClientError();
                }
            });
            return true;
        } catch (Throwable th) {
            PrefHelper.Debug("ReferrerClientWrapper Exception: " + th.getMessage());
            return false;
        }
    }
}
