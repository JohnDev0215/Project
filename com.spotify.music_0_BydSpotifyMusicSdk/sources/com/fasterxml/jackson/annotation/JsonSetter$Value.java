package com.fasterxml.jackson.annotation;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class JsonSetter$Value implements Serializable {
    public static final JsonSetter$Value c;
    private static final long serialVersionUID = 1;
    private final Nulls _contentNulls;
    private final Nulls _nulls;

    static {
        Nulls nulls = Nulls.l;
        c = new JsonSetter$Value(nulls, nulls);
    }

    public JsonSetter$Value(Nulls nulls, Nulls nulls2) {
        this._nulls = nulls;
        this._contentNulls = nulls2;
    }

    public final Nulls a() {
        return this._contentNulls;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        JsonSetter$Value jsonSetter$Value = (JsonSetter$Value) obj;
        return jsonSetter$Value._nulls == this._nulls && jsonSetter$Value._contentNulls == this._contentNulls;
    }

    public final int hashCode() {
        return this._nulls.ordinal() + (this._contentNulls.ordinal() << 2);
    }

    public Object readResolve() {
        Nulls nulls = this._nulls;
        Nulls nulls2 = this._contentNulls;
        Nulls nulls3 = Nulls.l;
        return (nulls == nulls3 && nulls2 == nulls3) ? c : this;
    }

    public final String toString() {
        return String.format("JsonSetter.Value(valueNulls=%s,contentNulls=%s)", this._nulls, this._contentNulls);
    }
}
