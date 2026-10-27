package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.TransferData;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

/* JADX INFO: loaded from: classes5.dex */
class TransferDataJsonUnmarshaller implements Unmarshaller<TransferData, JsonUnmarshallerContext> {
    private static TransferDataJsonUnmarshaller instance;

    TransferDataJsonUnmarshaller() {
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public TransferData unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        TransferData transferData = new TransferData();
        reader.beginObject();
        while (reader.hasNext()) {
            String strNextName = reader.nextName();
            if (strNextName.equals("transferMessage")) {
                transferData.setTransferMessage(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("rejectReason")) {
                transferData.setRejectReason(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("transferDate")) {
                transferData.setTransferDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("acceptDate")) {
                transferData.setAcceptDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (strNextName.equals("rejectDate")) {
                transferData.setRejectDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return transferData;
    }

    public static TransferDataJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new TransferDataJsonUnmarshaller();
        }
        return instance;
    }
}
