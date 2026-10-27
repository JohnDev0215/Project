package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DescribeManagedJobTemplateResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
import com.google.android.gms.cast.MediaTrack;

/* JADX INFO: loaded from: classes5.dex */
public class DescribeManagedJobTemplateResultJsonUnmarshaller implements Unmarshaller<DescribeManagedJobTemplateResult, JsonUnmarshallerContext> {
    private static DescribeManagedJobTemplateResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public DescribeManagedJobTemplateResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        DescribeManagedJobTemplateResult describeManagedJobTemplateResult = new DescribeManagedJobTemplateResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("templateName")) {
                describeManagedJobTemplateResult.setTemplateName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("templateArn")) {
                describeManagedJobTemplateResult.setTemplateArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(MediaTrack.ROLE_DESCRIPTION)) {
                describeManagedJobTemplateResult.setDescription(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("templateVersion")) {
                describeManagedJobTemplateResult.setTemplateVersion(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("environments")) {
                describeManagedJobTemplateResult.setEnvironments(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("documentParameters")) {
                describeManagedJobTemplateResult.setDocumentParameters(new ListUnmarshaller(DocumentParameterJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("document")) {
                describeManagedJobTemplateResult.setDocument(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return describeManagedJobTemplateResult;
    }

    public static DescribeManagedJobTemplateResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DescribeManagedJobTemplateResultJsonUnmarshaller();
        }
        return instance;
    }
}
