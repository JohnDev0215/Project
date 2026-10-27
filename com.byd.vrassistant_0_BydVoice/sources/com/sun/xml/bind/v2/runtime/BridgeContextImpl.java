package com.sun.xml.bind.v2.runtime;

import com.sun.xml.bind.api.BridgeContext;
import com.sun.xml.bind.v2.runtime.unmarshaller.UnmarshallerImpl;
import javax.xml.bind.JAXBException;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.attachment.AttachmentMarshaller;
import javax.xml.bind.attachment.AttachmentUnmarshaller;

/* JADX INFO: loaded from: classes3.dex */
public final class BridgeContextImpl extends BridgeContext {
    public final MarshallerImpl marshaller;
    public final UnmarshallerImpl unmarshaller;

    BridgeContextImpl(JAXBContextImpl jAXBContextImpl) {
        this.unmarshaller = jAXBContextImpl.createUnmarshaller();
        this.marshaller = jAXBContextImpl.createMarshaller();
    }

    @Override // com.sun.xml.bind.api.BridgeContext
    public void setErrorHandler(ValidationEventHandler validationEventHandler) {
        try {
            this.unmarshaller.setEventHandler(validationEventHandler);
            this.marshaller.setEventHandler(validationEventHandler);
        } catch (JAXBException e) {
            throw new Error(e);
        }
    }

    @Override // com.sun.xml.bind.api.BridgeContext
    public void setAttachmentMarshaller(AttachmentMarshaller attachmentMarshaller) {
        this.marshaller.setAttachmentMarshaller(attachmentMarshaller);
    }

    @Override // com.sun.xml.bind.api.BridgeContext
    public void setAttachmentUnmarshaller(AttachmentUnmarshaller attachmentUnmarshaller) {
        this.unmarshaller.setAttachmentUnmarshaller(attachmentUnmarshaller);
    }

    @Override // com.sun.xml.bind.api.BridgeContext
    public AttachmentMarshaller getAttachmentMarshaller() {
        return this.marshaller.getAttachmentMarshaller();
    }

    @Override // com.sun.xml.bind.api.BridgeContext
    public AttachmentUnmarshaller getAttachmentUnmarshaller() {
        return this.unmarshaller.getAttachmentUnmarshaller();
    }
}
