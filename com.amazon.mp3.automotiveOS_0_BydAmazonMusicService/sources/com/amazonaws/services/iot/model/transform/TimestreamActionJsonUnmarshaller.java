package com.amazonaws.services.iot.model.transform;

import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.amazonaws.services.iot.model.TimestreamAction;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class TimestreamActionJsonUnmarshaller implements Unmarshaller<TimestreamAction, JsonUnmarshallerContext> {
    private static TimestreamActionJsonUnmarshaller instance;

    TimestreamActionJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public TimestreamAction unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        TimestreamAction timestreamAction = new TimestreamAction();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("roleArn")) {
                timestreamAction.setRoleArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("databaseName")) {
                timestreamAction.setDatabaseName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("tableName")) {
                timestreamAction.setTableName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("dimensions")) {
                timestreamAction.setDimensions(new ListUnmarshaller(TimestreamDimensionJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(TetheredMessageKey.timestamp)) {
                timestreamAction.setTimestamp(TimestreamTimestampJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return timestreamAction;
    }

    public static TimestreamActionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new TimestreamActionJsonUnmarshaller();
        }
        return instance;
    }
}
