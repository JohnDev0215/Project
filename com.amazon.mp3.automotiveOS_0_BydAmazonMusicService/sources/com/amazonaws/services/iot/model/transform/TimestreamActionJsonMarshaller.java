package com.amazonaws.services.iot.model.transform;

import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.amazonaws.services.iot.model.TimestreamAction;
import com.amazonaws.services.iot.model.TimestreamDimension;
import com.amazonaws.services.iot.model.TimestreamTimestamp;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
class TimestreamActionJsonMarshaller {
    private static TimestreamActionJsonMarshaller instance;

    TimestreamActionJsonMarshaller() {
    }

    public void marshall(TimestreamAction timestreamAction, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (timestreamAction.getRoleArn() != null) {
            String roleArn = timestreamAction.getRoleArn();
            awsJsonWriter.name("roleArn");
            awsJsonWriter.value(roleArn);
        }
        if (timestreamAction.getDatabaseName() != null) {
            String databaseName = timestreamAction.getDatabaseName();
            awsJsonWriter.name("databaseName");
            awsJsonWriter.value(databaseName);
        }
        if (timestreamAction.getTableName() != null) {
            String tableName = timestreamAction.getTableName();
            awsJsonWriter.name("tableName");
            awsJsonWriter.value(tableName);
        }
        if (timestreamAction.getDimensions() != null) {
            List<TimestreamDimension> dimensions = timestreamAction.getDimensions();
            awsJsonWriter.name("dimensions");
            awsJsonWriter.beginArray();
            for (TimestreamDimension timestreamDimension : dimensions) {
                if (timestreamDimension != null) {
                    TimestreamDimensionJsonMarshaller.getInstance().marshall(timestreamDimension, awsJsonWriter);
                }
            }
            awsJsonWriter.endArray();
        }
        if (timestreamAction.getTimestamp() != null) {
            TimestreamTimestamp timestamp = timestreamAction.getTimestamp();
            awsJsonWriter.name(TetheredMessageKey.timestamp);
            TimestreamTimestampJsonMarshaller.getInstance().marshall(timestamp, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }

    public static TimestreamActionJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new TimestreamActionJsonMarshaller();
        }
        return instance;
    }
}
