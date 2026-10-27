package com.fasterxml.jackson.dataformat.xml.util;

/* JADX INFO: loaded from: classes6.dex */
public class XmlInfo {
    protected final boolean _isAttribute;
    protected final boolean _isCData;
    protected final boolean _isText;
    protected final String _namespace;

    public XmlInfo(Boolean bool, String str, Boolean bool2, Boolean bool3) {
        this._isAttribute = bool == null ? false : bool.booleanValue();
        this._namespace = str == null ? "" : str;
        this._isText = bool2 == null ? false : bool2.booleanValue();
        this._isCData = bool3 != null ? bool3.booleanValue() : false;
    }

    public String getNamespace() {
        return this._namespace;
    }

    public boolean isAttribute() {
        return this._isAttribute;
    }

    public boolean isText() {
        return this._isText;
    }

    public boolean isCData() {
        return this._isCData;
    }
}
