package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.OTAUpdateInfo;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.MapUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
import com.google.android.gms.cast.MediaTrack;

/* JADX INFO: loaded from: classes5.dex */
class OTAUpdateInfoJsonUnmarshaller implements Unmarshaller<OTAUpdateInfo, JsonUnmarshallerContext> {
    private static OTAUpdateInfoJsonUnmarshaller instance;

    OTAUpdateInfoJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public OTAUpdateInfo unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        OTAUpdateInfo oTAUpdateInfo = new OTAUpdateInfo();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("otaUpdateId")) {
                oTAUpdateInfo.setOtaUpdateId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("otaUpdateArn")) {
                oTAUpdateInfo.setOtaUpdateArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("creationDate")) {
                oTAUpdateInfo.setCreationDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("lastModifiedDate")) {
                oTAUpdateInfo.setLastModifiedDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(MediaTrack.ROLE_DESCRIPTION)) {
                oTAUpdateInfo.setDescription(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("targets")) {
                oTAUpdateInfo.setTargets(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("protocols")) {
                oTAUpdateInfo.setProtocols(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("awsJobExecutionsRolloutConfig")) {
                oTAUpdateInfo.setAwsJobExecutionsRolloutConfig(AwsJobExecutionsRolloutConfigJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("awsJobPresignedUrlConfig")) {
                oTAUpdateInfo.setAwsJobPresignedUrlConfig(AwsJobPresignedUrlConfigJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("targetSelection")) {
                oTAUpdateInfo.setTargetSelection(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("otaUpdateFiles")) {
                oTAUpdateInfo.setOtaUpdateFiles(new ListUnmarshaller(OTAUpdateFileJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("otaUpdateStatus")) {
                oTAUpdateInfo.setOtaUpdateStatus(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("awsIotJobId")) {
                oTAUpdateInfo.setAwsIotJobId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("awsIotJobArn")) {
                oTAUpdateInfo.setAwsIotJobArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("errorInfo")) {
                oTAUpdateInfo.setErrorInfo(ErrorInfoJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("additionalParameters")) {
                oTAUpdateInfo.setAdditionalParameters(new MapUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return oTAUpdateInfo;
    }

    public static OTAUpdateInfoJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new OTAUpdateInfoJsonUnmarshaller();
        }
        return instance;
    }
}
