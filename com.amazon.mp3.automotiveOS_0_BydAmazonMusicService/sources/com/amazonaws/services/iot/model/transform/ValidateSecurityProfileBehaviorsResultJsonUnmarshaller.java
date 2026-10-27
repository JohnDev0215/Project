package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ValidateSecurityProfileBehaviorsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ValidateSecurityProfileBehaviorsResultJsonUnmarshaller implements Unmarshaller<ValidateSecurityProfileBehaviorsResult, JsonUnmarshallerContext> {
    private static ValidateSecurityProfileBehaviorsResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ValidateSecurityProfileBehaviorsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ValidateSecurityProfileBehaviorsResult validateSecurityProfileBehaviorsResult = new ValidateSecurityProfileBehaviorsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("valid")) {
                validateSecurityProfileBehaviorsResult.setValid(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("validationErrors")) {
                validateSecurityProfileBehaviorsResult.setValidationErrors(new ListUnmarshaller(ValidationErrorJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return validateSecurityProfileBehaviorsResult;
    }

    public static ValidateSecurityProfileBehaviorsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ValidateSecurityProfileBehaviorsResultJsonUnmarshaller();
        }
        return instance;
    }
}
