package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.UpdateSecurityProfileResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.MapUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateSecurityProfileResultJsonUnmarshaller implements Unmarshaller<UpdateSecurityProfileResult, JsonUnmarshallerContext> {
    private static UpdateSecurityProfileResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public UpdateSecurityProfileResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        UpdateSecurityProfileResult updateSecurityProfileResult = new UpdateSecurityProfileResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("securityProfileName")) {
                updateSecurityProfileResult.setSecurityProfileName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("securityProfileArn")) {
                updateSecurityProfileResult.setSecurityProfileArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("securityProfileDescription")) {
                updateSecurityProfileResult.setSecurityProfileDescription(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("behaviors")) {
                updateSecurityProfileResult.setBehaviors(new ListUnmarshaller(BehaviorJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("alertTargets")) {
                updateSecurityProfileResult.setAlertTargets(new MapUnmarshaller(AlertTargetJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("additionalMetricsToRetain")) {
                updateSecurityProfileResult.setAdditionalMetricsToRetain(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("additionalMetricsToRetainV2")) {
                updateSecurityProfileResult.setAdditionalMetricsToRetainV2(new ListUnmarshaller(MetricToRetainJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("version")) {
                updateSecurityProfileResult.setVersion(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("creationDate")) {
                updateSecurityProfileResult.setCreationDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("lastModifiedDate")) {
                updateSecurityProfileResult.setLastModifiedDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return updateSecurityProfileResult;
    }

    public static UpdateSecurityProfileResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new UpdateSecurityProfileResultJsonUnmarshaller();
        }
        return instance;
    }
}
