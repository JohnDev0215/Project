package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.StartSigningJobParameter;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class StartSigningJobParameterJsonUnmarshaller implements Unmarshaller<StartSigningJobParameter, JsonUnmarshallerContext> {
    private static StartSigningJobParameterJsonUnmarshaller instance;

    StartSigningJobParameterJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public StartSigningJobParameter unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        StartSigningJobParameter startSigningJobParameter = new StartSigningJobParameter();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("signingProfileParameter")) {
                startSigningJobParameter.setSigningProfileParameter(SigningProfileParameterJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("signingProfileName")) {
                startSigningJobParameter.setSigningProfileName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("destination")) {
                startSigningJobParameter.setDestination(DestinationJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return startSigningJobParameter;
    }

    public static StartSigningJobParameterJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new StartSigningJobParameterJsonUnmarshaller();
        }
        return instance;
    }
}
