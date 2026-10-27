package com.amazon.mobilepushfrontend.appstate.external.transform;

import com.amazon.CoralAndroidClient.ClientBase.ClientRequest;
import com.amazon.CoralAndroidClient.ClientBase.Marshaller;
import com.amazon.CoralAndroidClient.Model.IntegerValue;
import com.amazon.CoralAndroidClient.Model.NullValue;
import com.amazon.CoralAndroidClient.Model.StringValue;
import com.amazon.CoralAndroidClient.Model.StructureValue;
import com.amazon.KiangService.ApplicationInformation;
import com.amazon.KiangService.DeviceUniqueID;
import com.amazon.KiangService.PushInformation;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.mobilepushfrontendappstateexternal.UpdateApplicationInstallRequest;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class UpdateApplicationInstallRequestMarshaller implements Marshaller<UpdateApplicationInstallRequest> {
    @Override // com.amazon.CoralAndroidClient.ClientBase.Marshaller
    public ClientRequest marshal(UpdateApplicationInstallRequest updateApplicationInstallRequest) {
        StructureValue structureValue = new StructureValue();
        ClientRequest clientRequest = new ClientRequest("com.amazon.mobilepushfrontendappstateexternal.MobilePushFrontendExternalAppStateService.UpdateApplicationInstall", structureValue);
        if (updateApplicationInstallRequest == null) {
            return clientRequest;
        }
        if (updateApplicationInstallRequest.getMarketplaceId() != null) {
            structureValue.put("marketplaceId", new StringValue(updateApplicationInstallRequest.getMarketplaceId()));
        } else {
            structureValue.put("marketplaceId", new NullValue());
        }
        if (updateApplicationInstallRequest.getApplicationInstallId() != null) {
            structureValue.put("applicationInstallId", new StringValue(updateApplicationInstallRequest.getApplicationInstallId()));
        } else {
            structureValue.put("applicationInstallId", new NullValue());
        }
        PushInformation pushInformation = updateApplicationInstallRequest.getPushInformation();
        if (pushInformation != null) {
            StructureValue structureValue2 = new StructureValue();
            structureValue.put("pushInformation", structureValue2);
            if (pushInformation.getProviderKey() != null) {
                structureValue2.put("providerKey", new StringValue(pushInformation.getProviderKey()));
            } else {
                structureValue2.put("providerKey", new NullValue());
            }
            if (pushInformation.getProvider() != null) {
                structureValue2.put("provider", new StringValue(pushInformation.getProvider()));
            } else {
                structureValue2.put("provider", new NullValue());
            }
            if (pushInformation.getSecret() != null) {
                structureValue2.put("secret", new StringValue(pushInformation.getSecret()));
            } else {
                structureValue2.put("secret", new NullValue());
            }
        }
        if (updateApplicationInstallRequest.getLocaleId() != null) {
            structureValue.put("localeId", new StringValue(updateApplicationInstallRequest.getLocaleId()));
        } else {
            structureValue.put("localeId", new NullValue());
        }
        ApplicationInformation applicationInformation = updateApplicationInstallRequest.getApplicationInformation();
        if (applicationInformation != null) {
            StructureValue structureValue3 = new StructureValue();
            structureValue.put("applicationInformation", structureValue3);
            DeviceUniqueID deviceUniqueId = applicationInformation.getDeviceUniqueId();
            if (deviceUniqueId != null) {
                StructureValue structureValue4 = new StructureValue();
                structureValue3.put("deviceUniqueId", structureValue4);
                if (deviceUniqueId.getValue() != null) {
                    structureValue4.put("value", new StringValue(deviceUniqueId.getValue()));
                } else {
                    structureValue4.put("value", new NullValue());
                }
                if (deviceUniqueId.getType() != null) {
                    structureValue4.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, new StringValue(deviceUniqueId.getType()));
                } else {
                    structureValue4.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, new NullValue());
                }
            }
            if (applicationInformation.getApplicationVersion() != null) {
                structureValue3.put("applicationVersion", new StringValue(applicationInformation.getApplicationVersion()));
            } else {
                structureValue3.put("applicationVersion", new NullValue());
            }
            if (applicationInformation.getOsVersion() != null) {
                structureValue3.put("osVersion", new StringValue(applicationInformation.getOsVersion()));
            } else {
                structureValue3.put("osVersion", new NullValue());
            }
            if (applicationInformation.getApplicationIdentifier() != null) {
                structureValue3.put("applicationIdentifier", new StringValue(applicationInformation.getApplicationIdentifier()));
            } else {
                structureValue3.put("applicationIdentifier", new NullValue());
            }
            if (applicationInformation.getUbid() != null) {
                structureValue3.put("ubid", new StringValue(applicationInformation.getUbid()));
            } else {
                structureValue3.put("ubid", new NullValue());
            }
            if (applicationInformation.getOsIdentifier() != null) {
                structureValue3.put("osIdentifier", new StringValue(applicationInformation.getOsIdentifier()));
            } else {
                structureValue3.put("osIdentifier", new NullValue());
            }
            if (applicationInformation.getAssociateTag() != null) {
                structureValue3.put("associateTag", new StringValue(applicationInformation.getAssociateTag()));
            } else {
                structureValue3.put("associateTag", new NullValue());
            }
            if (applicationInformation.getHardwareIdentifier() != null) {
                structureValue3.put("hardwareIdentifier", new StringValue(applicationInformation.getHardwareIdentifier()));
            } else {
                structureValue3.put("hardwareIdentifier", new NullValue());
            }
            Map<String, String> appMetaData = applicationInformation.getAppMetaData();
            if (appMetaData != null) {
                StructureValue structureValue5 = new StructureValue();
                for (Map.Entry<String, String> entry : appMetaData.entrySet()) {
                    structureValue5.put(entry.getKey(), new StringValue(entry.getValue()));
                }
                structureValue3.put("appMetaData", structureValue5);
            } else {
                structureValue3.put("appMetaData", new NullValue());
            }
        }
        if (updateApplicationInstallRequest.getOsNotificationState() != null) {
            structureValue.put("osNotificationState", new StringValue(updateApplicationInstallRequest.getOsNotificationState()));
        } else {
            structureValue.put("osNotificationState", new NullValue());
        }
        if (updateApplicationInstallRequest.getSequenceNumber() != null) {
            structureValue.put("sequenceNumber", new IntegerValue(updateApplicationInstallRequest.getSequenceNumber().intValue()));
        } else {
            structureValue.put("sequenceNumber", new NullValue());
        }
        return clientRequest;
    }
}
