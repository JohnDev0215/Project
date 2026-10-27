package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DescribeScheduledAuditResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeScheduledAuditResultJsonUnmarshaller implements Unmarshaller<DescribeScheduledAuditResult, JsonUnmarshallerContext> {
    private static DescribeScheduledAuditResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public DescribeScheduledAuditResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DescribeScheduledAuditResult describeScheduledAuditResult = new DescribeScheduledAuditResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("frequency")) {
                describeScheduledAuditResult.setFrequency(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("dayOfMonth")) {
                describeScheduledAuditResult.setDayOfMonth(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("dayOfWeek")) {
                describeScheduledAuditResult.setDayOfWeek(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("targetCheckNames")) {
                describeScheduledAuditResult.setTargetCheckNames(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("scheduledAuditName")) {
                describeScheduledAuditResult.setScheduledAuditName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("scheduledAuditArn")) {
                describeScheduledAuditResult.setScheduledAuditArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return describeScheduledAuditResult;
    }

    public static DescribeScheduledAuditResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeScheduledAuditResultJsonUnmarshaller();
        }
        return instance;
    }
}
