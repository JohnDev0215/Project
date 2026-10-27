package com.sun.xml.bind.util;

import com.sun.xml.bind.ValidationEventLocatorEx;
import javax.xml.bind.helpers.ValidationEventLocatorImpl;

/* JADX INFO: loaded from: classes3.dex */
public class ValidationEventLocatorExImpl extends ValidationEventLocatorImpl implements ValidationEventLocatorEx {
    private final String fieldName;

    public ValidationEventLocatorExImpl(Object obj, String str) {
        super(obj);
        this.fieldName = str;
    }

    @Override // com.sun.xml.bind.ValidationEventLocatorEx
    public String getFieldName() {
        return this.fieldName;
    }

    @Override // javax.xml.bind.helpers.ValidationEventLocatorImpl
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("[url=");
        stringBuffer.append(getURL());
        stringBuffer.append(",line=");
        stringBuffer.append(getLineNumber());
        stringBuffer.append(",column=");
        stringBuffer.append(getColumnNumber());
        stringBuffer.append(",node=");
        stringBuffer.append(getNode());
        stringBuffer.append(",object=");
        stringBuffer.append(getObject());
        stringBuffer.append(",field=");
        stringBuffer.append(getFieldName());
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
