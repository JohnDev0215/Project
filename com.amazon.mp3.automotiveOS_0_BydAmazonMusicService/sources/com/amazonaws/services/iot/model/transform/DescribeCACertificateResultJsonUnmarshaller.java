package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DescribeCACertificateResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeCACertificateResultJsonUnmarshaller implements Unmarshaller<DescribeCACertificateResult, JsonUnmarshallerContext> {
    private static DescribeCACertificateResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public DescribeCACertificateResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DescribeCACertificateResult describeCACertificateResult = new DescribeCACertificateResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("certificateDescription")) {
                describeCACertificateResult.setCertificateDescription(CACertificateDescriptionJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("registrationConfig")) {
                describeCACertificateResult.setRegistrationConfig(RegistrationConfigJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return describeCACertificateResult;
    }

    public static DescribeCACertificateResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeCACertificateResultJsonUnmarshaller();
        }
        return instance;
    }
}
