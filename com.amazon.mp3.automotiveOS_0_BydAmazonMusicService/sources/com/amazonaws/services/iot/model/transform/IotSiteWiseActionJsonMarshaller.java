package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.IotSiteWiseAction;
import com.amazonaws.services.iot.model.PutAssetPropertyValueEntry;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
class IotSiteWiseActionJsonMarshaller {
    private static IotSiteWiseActionJsonMarshaller instance;

    IotSiteWiseActionJsonMarshaller() {
    }

    public void marshall(IotSiteWiseAction iotSiteWiseAction, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (iotSiteWiseAction.getPutAssetPropertyValueEntries() != null) {
            List<PutAssetPropertyValueEntry> putAssetPropertyValueEntries = iotSiteWiseAction.getPutAssetPropertyValueEntries();
            awsJsonWriter.name("putAssetPropertyValueEntries");
            awsJsonWriter.beginArray();
            for (PutAssetPropertyValueEntry putAssetPropertyValueEntry : putAssetPropertyValueEntries) {
                if (putAssetPropertyValueEntry != null) {
                    PutAssetPropertyValueEntryJsonMarshaller.getInstance().marshall(putAssetPropertyValueEntry, awsJsonWriter);
                }
            }
            awsJsonWriter.endArray();
        }
        if (iotSiteWiseAction.getRoleArn() != null) {
            String roleArn = iotSiteWiseAction.getRoleArn();
            awsJsonWriter.name("roleArn");
            awsJsonWriter.value(roleArn);
        }
        awsJsonWriter.endObject();
    }

    public static IotSiteWiseActionJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new IotSiteWiseActionJsonMarshaller();
        }
        return instance;
    }
}
