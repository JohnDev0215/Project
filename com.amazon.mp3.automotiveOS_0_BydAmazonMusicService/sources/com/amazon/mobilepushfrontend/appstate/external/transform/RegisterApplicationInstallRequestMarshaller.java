package com.amazon.mobilepushfrontend.appstate.external.transform;

import com.amazon.CoralAndroidClient.ClientBase.ClientRequest;
import com.amazon.CoralAndroidClient.ClientBase.Marshaller;
import com.amazon.CoralAndroidClient.Model.NullValue;
import com.amazon.CoralAndroidClient.Model.StringValue;
import com.amazon.CoralAndroidClient.Model.StructureValue;
import com.amazon.KiangService.ApplicationInformation;
import com.amazon.KiangService.DeviceUniqueID;
import com.amazon.KiangService.PushInformation;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.mobilepushfrontendappstateexternal.RegisterApplicationInstallRequest;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class RegisterApplicationInstallRequestMarshaller implements Marshaller<RegisterApplicationInstallRequest> {
    @Override // com.amazon.CoralAndroidClient.ClientBase.Marshaller
    public ClientRequest marshal(RegisterApplicationInstallRequest registerApplicationInstallRequest) {
        StructureValue structureValue = new StructureValue();
        ClientRequest clientRequest = new ClientRequest("com.amazon.mobilepushfrontendappstateexternal.MobilePushFrontendExternalAppStateService.RegisterApplicationInstall", structureValue);
        if (registerApplicationInstallRequest == null) {
            return clientRequest;
        }
        ApplicationInformation applicationInformation = registerApplicationInstallRequest.getApplicationInformation();
        if (applicationInformation != null) {
            StructureValue structureValue2 = new StructureValue();
            structureValue.put("applicationInformation", structureValue2);
            DeviceUniqueID deviceUniqueId = applicationInformation.getDeviceUniqueId();
            if (deviceUniqueId != null) {
                StructureValue structureValue3 = new StructureValue();
                structureValue2.put("deviceUniqueId", structureValue3);
                if (deviceUniqueId.getValue() != null) {
                    structureValue3.put("value", new StringValue(deviceUniqueId.getValue()));
                } else {
                    structureValue3.put("value", new NullValue());
                }
                if (deviceUniqueId.getType() != null) {
                    structureValue3.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, new StringValue(deviceUniqueId.getType()));
                } else {
                    structureValue3.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, new NullValue());
                }
            }
            if (applicationInformation.getApplicationVersion() != null) {
                structureValue2.put("applicationVersion", new StringValue(applicationInformation.getApplicationVersion()));
            } else {
                structureValue2.put("applicationVersion", new NullValue());
            }
            if (applicationInformation.getOsVersion() != null) {
                structureValue2.put("osVersion", new StringValue(applicationInformation.getOsVersion()));
            } else {
                structureValue2.put("osVersion", new NullValue());
            }
            if (applicationInformation.getApplicationIdentifier() != null) {
                structureValue2.put("applicationIdentifier", new StringValue(applicationInformation.getApplicationIdentifier()));
            } else {
                structureValue2.put("applicationIdentifier", new NullValue());
            }
            if (applicationInformation.getUbid() != null) {
                structureValue2.put("ubid", new StringValue(applicationInformation.getUbid()));
            } else {
                structureValue2.put("ubid", new NullValue());
            }
            if (applicationInformation.getOsIdentifier() != null) {
                structureValue2.put("osIdentifier", new StringValue(applicationInformation.getOsIdentifier()));
            } else {
                structureValue2.put("osIdentifier", new NullValue());
            }
            if (applicationInformation.getAssociateTag() != null) {
                structureValue2.put("associateTag", new StringValue(applicationInformation.getAssociateTag()));
            } else {
                structureValue2.put("associateTag", new NullValue());
            }
            if (applicationInformation.getHardwareIdentifier() != null) {
                structureValue2.put("hardwareIdentifier", new StringValue(applicationInformation.getHardwareIdentifier()));
            } else {
                structureValue2.put("hardwareIdentifier", new NullValue());
            }
            Map<String, String> appMetaData = applicationInformation.getAppMetaData();
            if (appMetaData != null) {
                StructureValue structureValue4 = new StructureValue();
                for (Map.Entry<String, String> entry : appMetaData.entrySet()) {
                    structureValue4.put(entry.getKey(), new StringValue(entry.getValue()));
                }
                structureValue2.put("appMetaData", structureValue4);
            } else {
                structureValue2.put("appMetaData", new NullValue());
            }
        }
        PushInformation pushInformation = registerApplicationInstallRequest.getPushInformation();
        if (pushInformation != null) {
            StructureValue structureValue5 = new StructureValue();
            structureValue.put("pushInformation", structureValue5);
            if (pushInformation.getProviderKey() != null) {
                structureValue5.put("providerKey", new StringValue(pushInformation.getProviderKey()));
            } else {
                structureValue5.put("providerKey", new NullValue());
            }
            if (pushInformation.getProvider() != null) {
                structureValue5.put("provider", new StringValue(pushInformation.getProvider()));
            } else {
                structureValue5.put("provider", new NullValue());
            }
            if (pushInformation.getSecret() != null) {
                structureValue5.put("secret", new StringValue(pushInformation.getSecret()));
            } else {
                structureValue5.put("secret", new NullValue());
            }
        }
        return clientRequest;
    }
}
