package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.CreateDomainConfigurationResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class CreateDomainConfigurationResultJsonUnmarshaller implements Unmarshaller<CreateDomainConfigurationResult, JsonUnmarshallerContext> {
    private static CreateDomainConfigurationResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public CreateDomainConfigurationResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        CreateDomainConfigurationResult createDomainConfigurationResult = new CreateDomainConfigurationResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("domainConfigurationName")) {
                createDomainConfigurationResult.setDomainConfigurationName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("domainConfigurationArn")) {
                createDomainConfigurationResult.setDomainConfigurationArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return createDomainConfigurationResult;
    }

    public static CreateDomainConfigurationResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CreateDomainConfigurationResultJsonUnmarshaller();
        }
        return instance;
    }
}
