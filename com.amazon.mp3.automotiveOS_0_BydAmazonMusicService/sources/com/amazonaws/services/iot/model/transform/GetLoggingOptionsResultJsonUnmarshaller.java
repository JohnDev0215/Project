package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.GetLoggingOptionsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class GetLoggingOptionsResultJsonUnmarshaller implements Unmarshaller<GetLoggingOptionsResult, JsonUnmarshallerContext> {
    private static GetLoggingOptionsResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public GetLoggingOptionsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        GetLoggingOptionsResult getLoggingOptionsResult = new GetLoggingOptionsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("roleArn")) {
                getLoggingOptionsResult.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("logLevel")) {
                getLoggingOptionsResult.setLogLevel(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return getLoggingOptionsResult;
    }

    public static GetLoggingOptionsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new GetLoggingOptionsResultJsonUnmarshaller();
        }
        return instance;
    }
}
