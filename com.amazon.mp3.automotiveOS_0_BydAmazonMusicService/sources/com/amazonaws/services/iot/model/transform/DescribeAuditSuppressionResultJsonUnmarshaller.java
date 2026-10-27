package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DescribeAuditSuppressionResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
import com.google.android.gms.cast.MediaTrack;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeAuditSuppressionResultJsonUnmarshaller implements Unmarshaller<DescribeAuditSuppressionResult, JsonUnmarshallerContext> {
    private static DescribeAuditSuppressionResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public DescribeAuditSuppressionResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DescribeAuditSuppressionResult describeAuditSuppressionResult = new DescribeAuditSuppressionResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("checkName")) {
                describeAuditSuppressionResult.setCheckName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("resourceIdentifier")) {
                describeAuditSuppressionResult.setResourceIdentifier(ResourceIdentifierJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("expirationDate")) {
                describeAuditSuppressionResult.setExpirationDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("suppressIndefinitely")) {
                describeAuditSuppressionResult.setSuppressIndefinitely(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(MediaTrack.ROLE_DESCRIPTION)) {
                describeAuditSuppressionResult.setDescription(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return describeAuditSuppressionResult;
    }

    public static DescribeAuditSuppressionResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeAuditSuppressionResultJsonUnmarshaller();
        }
        return instance;
    }
}
