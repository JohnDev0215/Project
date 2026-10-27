package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.Nulls;
import java.io.Serializable;
import p.k52;

/* JADX INFO: loaded from: classes.dex */
public class PropertyMetadata implements Serializable {
    public static final PropertyMetadata e = new PropertyMetadata(Boolean.TRUE, null, null, null, null, null, null);
    public static final PropertyMetadata k = new PropertyMetadata(Boolean.FALSE, null, null, null, null, null, null);
    public static final PropertyMetadata l = new PropertyMetadata(null, null, null, null, null, null, null);
    private static final long serialVersionUID = -1;
    protected Nulls _contentNulls;
    protected final String _defaultValue;
    protected final String _description;
    protected final Integer _index;
    protected final Boolean _required;
    protected Nulls _valueNulls;
    public final transient k52 c;

    public PropertyMetadata(Boolean bool, String str, Integer num, String str2, k52 k52Var, Nulls nulls, Nulls nulls2) {
        this._required = bool;
        this._description = str;
        this._index = num;
        this._defaultValue = (str2 == null || str2.isEmpty()) ? null : str2;
        this.c = k52Var;
        this._valueNulls = nulls;
        this._contentNulls = nulls2;
    }

    public final Nulls a() {
        return this._contentNulls;
    }

    public final Nulls b() {
        return this._valueNulls;
    }

    public Object readResolve() {
        if (this._description != null || this._index != null || this._defaultValue != null || this.c != null || this._valueNulls != null || this._contentNulls != null) {
            return this;
        }
        Boolean bool = this._required;
        return bool == null ? l : bool.booleanValue() ? e : k;
    }
}
