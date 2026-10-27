package com.amazon.mp3.service.metrics.mts;

import android.content.Context;
import com.amazon.hermes.AuthMethod;
import com.amazon.hermes.NoOpAuthMethod;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailStorage;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.net.RequestAuthorizer;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.metrics.mts.MTSEventUploader;
import com.amazon.music.metrics.mts.MTSEventUploaderFactory;
import com.amazon.music.metrics.mts.android.MTSReportClientActionsV3Uploader;
import java.net.MalformedURLException;
import java.net.URL;

/* JADX INFO: loaded from: classes4.dex */
public class MusicUploadFactory implements MTSEventUploaderFactory {
    private static final String TAG = "MusicUploadFactory";
    private final Context context;

    public MusicUploadFactory(final Context context) {
        this.context = context;
    }

    @Override // com.amazon.music.metrics.mts.MTSEventUploaderFactory
    public MTSEventUploader createUploader() {
        return new MTSReportClientActionsV3Uploader(new MusicUploadConfig(this.context));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static URL getCirrusV3URL() throws MalformedURLException {
        return new URL(Environment.CIRRUS_V3.get().toURL());
    }

    public static class MusicUploadConfig implements MTSReportClientActionsV3Uploader.MTSEventUploadConfig {
        private final Context context;

        MusicUploadConfig(final Context context) {
            this.context = context;
        }

        @Override // com.amazon.music.metrics.mts.android.MTSReportClientActionsV3Uploader.MTSEventUploadConfig
        public String getDeviceType() {
            return AccountCredentialStorage.get().getDeviceType();
        }

        @Override // com.amazon.music.metrics.mts.android.MTSReportClientActionsV3Uploader.MTSEventUploadConfig
        public String getDeviceId() {
            return AccountCredentialStorage.get().getDeviceId();
        }

        @Override // com.amazon.music.metrics.mts.android.MTSReportClientActionsV3Uploader.MTSEventUploadConfig
        public String getMusicRequestIdentityContextToken() {
            return ChildUserUtil.INSTANCE.getMusicRequestIdentityContextToken(this.context);
        }

        @Override // com.amazon.music.metrics.mts.android.MTSReportClientActionsV3Uploader.MTSEventUploadConfig
        public MTSReportClientActionsV3Uploader.AuthProvider getAuthProvider() {
            return new MusicAuthProvider(this.context);
        }

        @Override // com.amazon.music.metrics.mts.android.MTSReportClientActionsV3Uploader.MTSEventUploadConfig
        public URL getURL() {
            try {
                return MusicUploadFactory.getCirrusV3URL();
            } catch (MalformedURLException e) {
                throw new RuntimeException("Unable to send metrics request due to malformed url", e);
            }
        }

        @Override // com.amazon.music.metrics.mts.android.MTSReportClientActionsV3Uploader.MTSEventUploadConfig
        public boolean doesNetworkSupportUpload() {
            return ConnectivityUtil.hasAnyInternetConnection();
        }

        @Override // com.amazon.music.metrics.mts.android.MTSReportClientActionsV3Uploader.MTSEventUploadConfig
        public boolean doesCustomerHaveCloudPlayerAccount() {
            return AccountDetailStorage.get().getCloudPlayerAccountExist();
        }

        @Override // com.amazon.music.metrics.mts.android.MTSReportClientActionsV3Uploader.MTSEventUploadConfig
        public boolean isCompressedCoralRequestsSupported() {
            return AmazonApplication.getCapabilities().isCompressedCoralRequestsSupported();
        }
    }

    public static final class MusicAuthProvider implements MTSReportClientActionsV3Uploader.AuthProvider {
        private final Context context;

        MusicAuthProvider(final Context context) {
            this.context = context;
        }

        @Override // com.amazon.music.metrics.mts.android.MTSReportClientActionsV3Uploader.AuthProvider
        public AuthMethod provideAuth(Object request) {
            try {
                return new RequestAuthorizer().urlAuthorizerMethod(MusicUploadFactory.getCirrusV3URL(), request);
            } catch (IllegalArgumentException | NullPointerException unused) {
                Log.info(MusicUploadFactory.TAG, "Customer signed out. Metrics upload is about to fail.");
                return new NoOpAuthMethod();
            } catch (MalformedURLException e) {
                throw new RuntimeException("Unable to auth metrics request due to malformed url", e);
            }
        }
    }
}
