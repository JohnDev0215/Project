package com.amazonaws.services.iot.model.transform;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazonaws.services.iot.model.DescribeDimensionResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeDimensionResultJsonUnmarshaller implements Unmarshaller<DescribeDimensionResult, JsonUnmarshallerContext> {
    private static DescribeDimensionResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public DescribeDimensionResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DescribeDimensionResult describeDimensionResult = new DescribeDimensionResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("name")) {
                describeDimensionResult.setName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("arn")) {
                describeDimensionResult.setArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE)) {
                describeDimensionResult.setType(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("stringValues")) {
                describeDimensionResult.setStringValues(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("creationDate")) {
                describeDimensionResult.setCreationDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("lastModifiedDate")) {
                describeDimensionResult.setLastModifiedDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return describeDimensionResult;
    }

    public static DescribeDimensionResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeDimensionResultJsonUnmarshaller();
        }
        return instance;
    }
}
