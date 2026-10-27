package com.fasterxml.jackson.annotation;

import java.io.Serializable;
import java.util.Locale;
import java.util.TimeZone;
import p.j71;

/* JADX INFO: loaded from: classes.dex */
public class JsonFormat$Value implements Serializable {
    public static final JsonFormat$Value e = new JsonFormat$Value();
    private static final long serialVersionUID = 1;
    private final j71 _features;
    private final Boolean _lenient;
    private final Locale _locale;
    private final String _pattern;
    private final JsonFormat$Shape _shape;
    private final String _timezoneStr;
    public final transient TimeZone c;

    public JsonFormat$Value() {
        this("", JsonFormat$Shape.c, "", "", j71.c, null);
    }

    public static boolean a(Serializable serializable, Serializable serializable2) {
        if (serializable == null) {
            return serializable2 == null;
        }
        if (serializable2 == null) {
            return false;
        }
        return serializable.equals(serializable2);
    }

    public final Boolean b(JsonFormat$Feature jsonFormat$Feature) {
        j71 j71Var = this._features;
        j71Var.getClass();
        int iOrdinal = 1 << jsonFormat$Feature.ordinal();
        if ((j71Var.b & iOrdinal) != 0) {
            return Boolean.FALSE;
        }
        if ((iOrdinal & j71Var.a) != 0) {
            return Boolean.TRUE;
        }
        return null;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        JsonFormat$Value jsonFormat$Value = (JsonFormat$Value) obj;
        if (this._shape == jsonFormat$Value._shape && this._features.equals(jsonFormat$Value._features)) {
            return a(this._lenient, jsonFormat$Value._lenient) && a(this._timezoneStr, jsonFormat$Value._timezoneStr) && a(this._pattern, jsonFormat$Value._pattern) && a(this.c, jsonFormat$Value.c) && a(this._locale, jsonFormat$Value._locale);
        }
        return false;
    }

    public final int hashCode() {
        String str = this._timezoneStr;
        int iHashCode = str == null ? 1 : str.hashCode();
        String str2 = this._pattern;
        if (str2 != null) {
            iHashCode ^= str2.hashCode();
        }
        int iHashCode2 = this._shape.hashCode() + iHashCode;
        Boolean bool = this._lenient;
        if (bool != null) {
            iHashCode2 ^= bool.hashCode();
        }
        Locale locale = this._locale;
        if (locale != null) {
            iHashCode2 += locale.hashCode();
        }
        return this._features.hashCode() ^ iHashCode2;
    }

    public final String toString() {
        return String.format("JsonFormat.Value(pattern=%s,shape=%s,lenient=%s,locale=%s,timezone=%s,features=%s)", this._pattern, this._shape, this._lenient, this._locale, this._timezoneStr, this._features);
    }

    public JsonFormat$Value(String str, JsonFormat$Shape jsonFormat$Shape, String str2, String str3, j71 j71Var, Boolean bool) {
        this(str, jsonFormat$Shape, (str2 == null || str2.length() == 0 || "##default".equals(str2)) ? null : new Locale(str2), (str3 == null || str3.length() == 0 || "##default".equals(str3)) ? null : str3, null, j71Var, bool);
    }

    public JsonFormat$Value(String str, JsonFormat$Shape jsonFormat$Shape, Locale locale, String str2, TimeZone timeZone, j71 j71Var, Boolean bool) {
        this._pattern = str == null ? "" : str;
        this._shape = jsonFormat$Shape == null ? JsonFormat$Shape.c : jsonFormat$Shape;
        this._locale = locale;
        this.c = timeZone;
        this._timezoneStr = str2;
        this._features = j71Var == null ? j71.c : j71Var;
        this._lenient = bool;
    }
}
