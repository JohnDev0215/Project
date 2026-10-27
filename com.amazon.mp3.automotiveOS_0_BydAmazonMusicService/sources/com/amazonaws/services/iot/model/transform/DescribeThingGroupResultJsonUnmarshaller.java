package com.amazonaws.services.iot.model.transform;

import androidx.core.app.NotificationCompat;
import com.amazonaws.services.iot.model.DescribeThingGroupResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeThingGroupResultJsonUnmarshaller implements Unmarshaller<DescribeThingGroupResult, JsonUnmarshallerContext> {
    private static DescribeThingGroupResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public DescribeThingGroupResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DescribeThingGroupResult describeThingGroupResult = new DescribeThingGroupResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("thingGroupName")) {
                describeThingGroupResult.setThingGroupName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("thingGroupId")) {
                describeThingGroupResult.setThingGroupId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("thingGroupArn")) {
                describeThingGroupResult.setThingGroupArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("version")) {
                describeThingGroupResult.setVersion(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("thingGroupProperties")) {
                describeThingGroupResult.setThingGroupProperties(ThingGroupPropertiesJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("thingGroupMetadata")) {
                describeThingGroupResult.setThingGroupMetadata(ThingGroupMetadataJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("indexName")) {
                describeThingGroupResult.setIndexName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("queryString")) {
                describeThingGroupResult.setQueryString(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("queryVersion")) {
                describeThingGroupResult.setQueryVersion(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(NotificationCompat.CATEGORY_STATUS)) {
                describeThingGroupResult.setStatus(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return describeThingGroupResult;
    }

    public static DescribeThingGroupResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeThingGroupResultJsonUnmarshaller();
        }
        return instance;
    }
}
