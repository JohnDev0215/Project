package com.amazon.avod.media.drm;

import android.media.DeniedByServerException;
import android.media.MediaDrm;
import com.amazon.avod.media.framework.error.DrmLicensingException;
import com.amazon.avod.media.framework.error.LicenseError;
import com.amazon.avod.media.playback.MediaDefaultConfiguration;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.util.DLog;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.common.base.Preconditions;
import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class WidevineFieldProvisioner {
    public static void handleNotProvisionedException(MediaDrm mediaDrm) throws Throwable {
        Preconditions.checkNotNull(mediaDrm, "mediaDrm");
        if (!MediaDefaultConfiguration.getInstance().isWidevineFieldProvisioningEnabled()) {
            DLog.warnf("Device is not provisioned for Widevine and field provisioning is disabled!");
            throw new DrmLicensingException(LicenseError.FIELD_PROVISIONING_FAILURE, "Device is not provisioned for Widevine and field provisioning is disabled!");
        }
        DLog.warnf("Device Widevine DRM is not provisioned, attempting field provisioning!");
        MediaDrm.ProvisionRequest provisionRequest = mediaDrm.getProvisionRequest();
        String str = provisionRequest.getDefaultUrl() + "&signedRequest=" + new String(provisionRequest.getData());
        try {
            DLog.logf("Executing provision request to Url: %s", str);
            byte[] bArrExecutePost = executePost(str);
            DLog.logf("Providing provision response to MediaDrm : %s", new String(bArrExecutePost));
            mediaDrm.provideProvisionResponse(bArrExecutePost);
            DLog.logf("Successfully provisioned device");
        } catch (DeniedByServerException e) {
            throw new DrmLicensingException(LicenseError.FIELD_PROVISIONING_FAILURE, "Provisioning response rejected by MediaDrm", e, DrmScheme.WIDEVINE);
        } catch (IOException e2) {
            throw new DrmLicensingException(LicenseError.FIELD_PROVISIONING_FAILURE, "Failed to execute provisioning request", e2, DrmScheme.WIDEVINE);
        }
    }

    private static byte[] executePost(@Nonnull String str) throws Throwable {
        HttpURLConnection httpURLConnection;
        Preconditions.checkNotNull(str, ImagesContract.URL);
        InputStream inputStream = null;
        try {
            httpURLConnection = (HttpURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(new URL(str).openConnection()));
        } catch (Throwable th) {
            th = th;
            httpURLConnection = null;
        }
        try {
            httpURLConnection.setRequestMethod(FirebasePerformance.HttpMethod.POST);
            httpURLConnection.setDoInput(true);
            inputStream = httpURLConnection.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int i = inputStream.read(bArr);
                if (i != -1) {
                    byteArrayOutputStream.write(bArr, 0, i);
                } else {
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return byteArray;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (inputStream != null) {
                inputStream.close();
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }
}
