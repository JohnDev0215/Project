package com.fasterxml.jackson.dataformat.xml.deser;

/* JADX INFO: loaded from: classes6.dex */
class ElementWrapper {
    protected final ElementWrapper _parent;
    protected final String _wrapperName;
    protected final String _wrapperNamespace;

    private ElementWrapper(ElementWrapper elementWrapper) {
        this._parent = elementWrapper;
        this._wrapperName = null;
        this._wrapperNamespace = "";
    }

    private ElementWrapper(ElementWrapper elementWrapper, String str, String str2) {
        this._parent = elementWrapper;
        this._wrapperName = str;
        this._wrapperNamespace = str2 == null ? "" : str2;
    }

    public static ElementWrapper matchingWrapper(ElementWrapper elementWrapper, String str, String str2) {
        return new ElementWrapper(elementWrapper, str, str2);
    }

    public ElementWrapper intermediateWrapper() {
        return new ElementWrapper(this, null, null);
    }

    public boolean isMatching() {
        return this._wrapperName != null;
    }

    public String getWrapperLocalName() {
        return this._wrapperName;
    }

    public String getWrapperNamespace() {
        return this._wrapperNamespace;
    }

    public ElementWrapper getParent() {
        return this._parent;
    }

    public boolean matchesWrapper(String str, String str2) {
        String str3 = this._wrapperName;
        if (str3 == null) {
            return true;
        }
        if (str2 == null) {
            str2 = "";
        }
        return str3.equals(str) && this._wrapperNamespace.equals(str2);
    }

    public String toString() {
        if (this._parent == null) {
            return "Wrapper: ROOT, matching: " + this._wrapperName;
        }
        if (this._wrapperName == null) {
            return "Wrapper: empty";
        }
        return "Wrapper: branch, matching: " + this._wrapperName;
    }
}
