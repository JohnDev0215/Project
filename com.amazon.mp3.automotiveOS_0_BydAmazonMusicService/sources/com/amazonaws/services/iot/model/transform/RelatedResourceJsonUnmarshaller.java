package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.RelatedResource;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.MapUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class RelatedResourceJsonUnmarshaller implements Unmarshaller<RelatedResource, JsonUnmarshallerContext> {
    private static RelatedResourceJsonUnmarshaller instance;

    RelatedResourceJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public RelatedResource unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        RelatedResource relatedResource = new RelatedResource();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("resourceType")) {
                relatedResource.setResourceType(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("resourceIdentifier")) {
                relatedResource.setResourceIdentifier(ResourceIdentifierJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("additionalInfo")) {
                relatedResource.setAdditionalInfo(new MapUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return relatedResource;
    }

    public static RelatedResourceJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new RelatedResourceJsonUnmarshaller();
        }
        return instance;
    }
}
