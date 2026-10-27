package org.codehaus.stax2.io;

import javax.xml.transform.Source;

/* JADX INFO: loaded from: classes7.dex */
public abstract class Stax2Source implements Source {
    protected String mSystemId;

    protected Stax2Source() {
    }

    @Override // javax.xml.transform.Source
    public String getSystemId() {
        return this.mSystemId;
    }

    @Override // javax.xml.transform.Source
    public void setSystemId(String str) {
        this.mSystemId = str;
    }
}
