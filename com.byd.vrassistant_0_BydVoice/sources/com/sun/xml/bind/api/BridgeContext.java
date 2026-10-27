package com.sun.xml.bind.api;

import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.attachment.AttachmentMarshaller;
import javax.xml.bind.attachment.AttachmentUnmarshaller;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BridgeContext {
    public abstract AttachmentMarshaller getAttachmentMarshaller();

    public abstract AttachmentUnmarshaller getAttachmentUnmarshaller();

    public abstract void setAttachmentMarshaller(AttachmentMarshaller attachmentMarshaller);

    public abstract void setAttachmentUnmarshaller(AttachmentUnmarshaller attachmentUnmarshaller);

    public abstract void setErrorHandler(ValidationEventHandler validationEventHandler);

    protected BridgeContext() {
    }
}
