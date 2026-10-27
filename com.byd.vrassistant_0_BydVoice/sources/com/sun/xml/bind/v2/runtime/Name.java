package com.sun.xml.bind.v2.runtime;

import javax.xml.namespace.QName;

/* JADX INFO: loaded from: classes3.dex */
public final class Name implements Comparable<Name> {
    public final boolean isAttribute;
    public final String localName;
    public final short localNameIndex;
    public final String nsUri;
    public final short nsUriIndex;
    public final short qNameIndex;

    Name(int i, int i2, String str, int i3, String str2, boolean z) {
        this.qNameIndex = (short) i;
        this.nsUri = str;
        this.localName = str2;
        this.nsUriIndex = (short) i2;
        this.localNameIndex = (short) i3;
        this.isAttribute = z;
    }

    public String toString() {
        return "{" + this.nsUri + '}' + this.localName;
    }

    public QName toQName() {
        return new QName(this.nsUri, this.localName);
    }

    public boolean equals(String str, String str2) {
        return str2.equals(this.localName) && str.equals(this.nsUri);
    }

    @Override // java.lang.Comparable
    public int compareTo(Name name) {
        int iCompareTo = this.nsUri.compareTo(name.nsUri);
        return iCompareTo != 0 ? iCompareTo : this.localName.compareTo(name.localName);
    }
}
