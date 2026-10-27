package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.UpdateDomainConfigurationResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class UpdateDomainConfigurationResultJsonUnmarshaller implements Unmarshaller<UpdateDomainConfigurationResult, JsonUnmarshallerContext> {
    private static UpdateDomainConfigurationResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public UpdateDomainConfigurationResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        UpdateDomainConfigurationResult updateDomainConfigurationResult = new UpdateDomainConfigurationResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("domainConfigurationName")) {
                updateDomainConfigurationResult.setDomainConfigurationName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("domainConfigurationArn")) {
                updateDomainConfigurationResult.setDomainConfigurationArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return updateDomainConfigurationResult;
    }

    public static UpdateDomainConfigurationResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new UpdateDomainConfigurationResultJsonUnmarshaller();
        }
        return instance;
    }
}
