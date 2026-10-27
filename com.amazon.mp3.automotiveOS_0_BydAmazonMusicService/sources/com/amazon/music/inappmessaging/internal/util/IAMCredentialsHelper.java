package com.amazon.music.inappmessaging.internal.util;

import com.amazon.music.inappmessaging.external.DeviceInformation;
import com.amazon.music.inappmessaging.external.DeviceInformationVendor;
import com.amazon.music.inappmessaging.external.IAMCredentials;
import com.amazon.music.inappmessaging.external.IAMCredentialsVendor;
import com.amazon.music.inappmessaging.external.events.DynamicMessageEventSender;
import com.amazon.music.metrics.mts.event.types.DynamicMessagingErrorCode;
import com.amazon.music.metrics.mts.event.types.DynamicMessagingInteractionType;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes4.dex */
public class IAMCredentialsHelper {
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) IAMCredentialsHelper.class);

    public static Observable<IAMCredentials> getAndValidateIAMCredentials(final IAMCredentialsVendor credentialsVendor) {
        return Observable.fromCallable(new Callable<IAMCredentials>() { // from class: com.amazon.music.inappmessaging.internal.util.IAMCredentialsHelper.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public IAMCredentials call() {
                IAMCredentials iamCredentials = credentialsVendor.getIamCredentials();
                List<String> listFindMissingIAMCredentials = iamCredentials.findMissingIAMCredentials();
                if (listFindMissingIAMCredentials.isEmpty()) {
                    return iamCredentials;
                }
                String str = "Missing IAM credentials: " + Arrays.toString(listFindMissingIAMCredentials.toArray());
                DynamicMessageEventSender.withInteractionType(DynamicMessagingInteractionType.DYNAMIC_MESSAGING_ERROR).withErrorCode(DynamicMessagingErrorCode.AUTHENTICATION_ERROR).withErrorMessage(str).withTrigger("N/A").send();
                throw new IllegalArgumentException(str);
            }
        }).cache().subscribeOn(Schedulers.io());
    }

    public static Observable<DeviceInformation> getDeviceInformation(final DeviceInformationVendor deviceInfoVendor) {
        return Observable.fromCallable(new Callable<DeviceInformation>() { // from class: com.amazon.music.inappmessaging.internal.util.IAMCredentialsHelper.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public DeviceInformation call() {
                DeviceInformation deviceInformation = deviceInfoVendor.getDeviceInformation();
                List<String> listFindMissingDeviceInfo = deviceInformation.findMissingDeviceInfo();
                if (!listFindMissingDeviceInfo.isEmpty()) {
                    IAMCredentialsHelper.LOG.debug("Missing Device info: " + Arrays.toString(listFindMissingDeviceInfo.toArray()));
                }
                return deviceInformation;
            }
        }).cache().subscribeOn(Schedulers.io());
    }
}
