package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ManagedJobTemplateSummary;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
import com.google.android.gms.cast.MediaTrack;

/* JADX INFO: loaded from: classes5.dex */
class ManagedJobTemplateSummaryJsonUnmarshaller implements Unmarshaller<ManagedJobTemplateSummary, JsonUnmarshallerContext> {
    private static ManagedJobTemplateSummaryJsonUnmarshaller instance;

    ManagedJobTemplateSummaryJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ManagedJobTemplateSummary unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ManagedJobTemplateSummary managedJobTemplateSummary = new ManagedJobTemplateSummary();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("templateArn")) {
                managedJobTemplateSummary.setTemplateArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("templateName")) {
                managedJobTemplateSummary.setTemplateName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals(MediaTrack.ROLE_DESCRIPTION)) {
                managedJobTemplateSummary.setDescription(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("environments")) {
                managedJobTemplateSummary.setEnvironments(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("templateVersion")) {
                managedJobTemplateSummary.setTemplateVersion(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return managedJobTemplateSummary;
    }

    public static ManagedJobTemplateSummaryJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ManagedJobTemplateSummaryJsonUnmarshaller();
        }
        return instance;
    }
}
