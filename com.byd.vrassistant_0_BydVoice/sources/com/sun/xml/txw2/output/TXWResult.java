package com.sun.xml.txw2.output;

import com.sun.xml.txw2.TypedXmlWriter;
import javax.xml.transform.Result;

/* JADX INFO: loaded from: classes3.dex */
public class TXWResult implements Result {
    private String systemId;
    private TypedXmlWriter writer;

    public TXWResult(TypedXmlWriter typedXmlWriter) {
        this.writer = typedXmlWriter;
    }

    public TypedXmlWriter getWriter() {
        return this.writer;
    }

    public void setWriter(TypedXmlWriter typedXmlWriter) {
        this.writer = typedXmlWriter;
    }

    @Override // javax.xml.transform.Result
    public String getSystemId() {
        return this.systemId;
    }

    @Override // javax.xml.transform.Result
    public void setSystemId(String str) {
        this.systemId = str;
    }
}
