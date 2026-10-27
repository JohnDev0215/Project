package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DescribeThingTypeResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeThingTypeResultJsonUnmarshaller implements Unmarshaller<DescribeThingTypeResult, JsonUnmarshallerContext> {
    private static DescribeThingTypeResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public DescribeThingTypeResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DescribeThingTypeResult describeThingTypeResult = new DescribeThingTypeResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("thingTypeName")) {
                describeThingTypeResult.setThingTypeName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("thingTypeId")) {
                describeThingTypeResult.setThingTypeId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("thingTypeArn")) {
                describeThingTypeResult.setThingTypeArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("thingTypeProperties")) {
                describeThingTypeResult.setThingTypeProperties(ThingTypePropertiesJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("thingTypeMetadata")) {
                describeThingTypeResult.setThingTypeMetadata(ThingTypeMetadataJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return describeThingTypeResult;
    }

    public static DescribeThingTypeResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeThingTypeResultJsonUnmarshaller();
        }
        return instance;
    }
}
