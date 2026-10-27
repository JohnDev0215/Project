package com.fasterxml.jackson.databind;

import java.io.Serializable;
import java.util.Iterator;
import p.pb2;
import p.vs;

/* JADX INFO: loaded from: classes.dex */
public class PropertyName implements Serializable {
    public static final PropertyName c = new PropertyName("", null);
    public static final PropertyName e = new PropertyName(new String(""), null);
    private static final long serialVersionUID = 1;
    protected pb2 _encodedSimple;
    protected final String _namespace;
    protected final String _simpleName;

    public PropertyName(String str, String str2) {
        Iterator it = vs.a;
        this._simpleName = str == null ? "" : str;
        this._namespace = str2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        PropertyName propertyName = (PropertyName) obj;
        String str = this._simpleName;
        if (str == null) {
            if (propertyName._simpleName != null) {
                return false;
            }
        } else if (!str.equals(propertyName._simpleName)) {
            return false;
        }
        String str2 = this._namespace;
        return str2 == null ? propertyName._namespace == null : str2.equals(propertyName._namespace);
    }

    public final int hashCode() {
        String str = this._namespace;
        return str == null ? this._simpleName.hashCode() : str.hashCode() ^ this._simpleName.hashCode();
    }

    public Object readResolve() {
        String str;
        return (this._namespace == null && ((str = this._simpleName) == null || "".equals(str))) ? c : this;
    }

    public final String toString() {
        if (this._namespace == null) {
            return this._simpleName;
        }
        return "{" + this._namespace + "}" + this._simpleName;
    }
}
