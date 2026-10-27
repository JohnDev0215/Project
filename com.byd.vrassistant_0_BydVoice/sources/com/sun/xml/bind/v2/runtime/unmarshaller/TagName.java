package com.sun.xml.bind.v2.runtime.unmarshaller;

import com.sun.xml.bind.v2.runtime.Name;
import javax.xml.namespace.QName;
import org.xml.sax.Attributes;

/* JADX INFO: loaded from: classes3.dex */
public abstract class TagName {
    public Attributes atts;
    public String local;
    public String uri;

    public abstract String getQname();

    public final boolean matches(String str, String str2) {
        return this.uri == str && this.local == str2;
    }

    public final boolean matches(Name name) {
        return this.local == name.localName && this.uri == name.nsUri;
    }

    public String toString() {
        return "{" + this.uri + '}' + this.local;
    }

    public String getPrefix() {
        String qname = getQname();
        int iIndexOf = qname.indexOf(58);
        return iIndexOf < 0 ? "" : qname.substring(0, iIndexOf);
    }

    public QName createQName() {
        return new QName(this.uri, this.local, getPrefix());
    }
}
