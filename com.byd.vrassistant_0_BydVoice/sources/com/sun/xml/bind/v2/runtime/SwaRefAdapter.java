package com.sun.xml.bind.v2.runtime;

import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallingContext;
import javax.activation.DataHandler;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/* JADX INFO: loaded from: classes3.dex */
public final class SwaRefAdapter extends XmlAdapter<String, DataHandler> {
    @Override // javax.xml.bind.annotation.adapters.XmlAdapter
    public DataHandler unmarshal(String str) {
        return UnmarshallingContext.getInstance().parent.getAttachmentUnmarshaller().getAttachmentAsDataHandler(str);
    }

    @Override // javax.xml.bind.annotation.adapters.XmlAdapter
    public String marshal(DataHandler dataHandler) {
        if (dataHandler == null) {
            return null;
        }
        return XMLSerializer.getInstance().attachmentMarshaller.addSwaRefAttachment(dataHandler);
    }
}
