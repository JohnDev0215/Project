package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.MitigationActionParams;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class MitigationActionParamsJsonUnmarshaller implements Unmarshaller<MitigationActionParams, JsonUnmarshallerContext> {
    private static MitigationActionParamsJsonUnmarshaller instance;

    MitigationActionParamsJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public MitigationActionParams unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        MitigationActionParams mitigationActionParams = new MitigationActionParams();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("updateDeviceCertificateParams")) {
                mitigationActionParams.setUpdateDeviceCertificateParams(UpdateDeviceCertificateParamsJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("updateCACertificateParams")) {
                mitigationActionParams.setUpdateCACertificateParams(UpdateCACertificateParamsJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("addThingsToThingGroupParams")) {
                mitigationActionParams.setAddThingsToThingGroupParams(AddThingsToThingGroupParamsJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("replaceDefaultPolicyVersionParams")) {
                mitigationActionParams.setReplaceDefaultPolicyVersionParams(ReplaceDefaultPolicyVersionParamsJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("enableIoTLoggingParams")) {
                mitigationActionParams.setEnableIoTLoggingParams(EnableIoTLoggingParamsJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("publishFindingToSnsParams")) {
                mitigationActionParams.setPublishFindingToSnsParams(PublishFindingToSnsParamsJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return mitigationActionParams;
    }

    public static MitigationActionParamsJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new MitigationActionParamsJsonUnmarshaller();
        }
        return instance;
    }
}
