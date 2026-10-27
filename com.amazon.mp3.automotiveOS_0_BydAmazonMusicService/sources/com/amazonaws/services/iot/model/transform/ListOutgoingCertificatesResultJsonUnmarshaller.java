package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListOutgoingCertificatesResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListOutgoingCertificatesResultJsonUnmarshaller implements Unmarshaller<ListOutgoingCertificatesResult, JsonUnmarshallerContext> {
    private static ListOutgoingCertificatesResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListOutgoingCertificatesResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListOutgoingCertificatesResult listOutgoingCertificatesResult = new ListOutgoingCertificatesResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("outgoingCertificates")) {
                listOutgoingCertificatesResult.setOutgoingCertificates(new ListUnmarshaller(OutgoingCertificateJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextMarker")) {
                listOutgoingCertificatesResult.setNextMarker(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listOutgoingCertificatesResult;
    }

    public static ListOutgoingCertificatesResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListOutgoingCertificatesResultJsonUnmarshaller();
        }
        return instance;
    }
}
