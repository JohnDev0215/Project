package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListDomainConfigurationsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
public class ListDomainConfigurationsResultJsonUnmarshaller implements Unmarshaller<ListDomainConfigurationsResult, JsonUnmarshallerContext> {
    private static ListDomainConfigurationsResultJsonUnmarshaller instance;

    @Override // com.amazonaws.transform.Unmarshaller
    public ListDomainConfigurationsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListDomainConfigurationsResult listDomainConfigurationsResult = new ListDomainConfigurationsResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("domainConfigurations")) {
                listDomainConfigurationsResult.setDomainConfigurations(new ListUnmarshaller(DomainConfigurationSummaryJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("nextMarker")) {
                listDomainConfigurationsResult.setNextMarker(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listDomainConfigurationsResult;
    }

    public static ListDomainConfigurationsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListDomainConfigurationsResultJsonUnmarshaller();
        }
        return instance;
    }
}
