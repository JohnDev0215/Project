package com.fasterxml.jackson.datatype.joda.cfg;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes6.dex */
abstract class JacksonJodaFormatBase {
    protected static final Locale DEFAULT_LOCALE = Locale.getDefault();
    protected final boolean _explicitLocale;
    protected final Locale _locale;
    protected final Boolean _useTimestamp;

    protected JacksonJodaFormatBase(Boolean bool, Locale locale, boolean z) {
        this._useTimestamp = bool;
        this._locale = locale;
        this._explicitLocale = z;
    }

    protected JacksonJodaFormatBase() {
        this._useTimestamp = null;
        this._locale = DEFAULT_LOCALE;
        this._explicitLocale = false;
    }

    protected JacksonJodaFormatBase(JacksonJodaFormatBase jacksonJodaFormatBase) {
        this._useTimestamp = jacksonJodaFormatBase._useTimestamp;
        this._locale = jacksonJodaFormatBase._locale;
        this._explicitLocale = jacksonJodaFormatBase._explicitLocale;
    }

    protected JacksonJodaFormatBase(JacksonJodaFormatBase jacksonJodaFormatBase, Boolean bool) {
        this._useTimestamp = bool;
        this._locale = jacksonJodaFormatBase._locale;
        this._explicitLocale = jacksonJodaFormatBase._explicitLocale;
    }

    protected JacksonJodaFormatBase(JacksonJodaFormatBase jacksonJodaFormatBase, TimeZone timeZone) {
        this._useTimestamp = jacksonJodaFormatBase._useTimestamp;
        this._locale = jacksonJodaFormatBase._locale;
        this._explicitLocale = jacksonJodaFormatBase._explicitLocale;
    }

    protected JacksonJodaFormatBase(JacksonJodaFormatBase jacksonJodaFormatBase, Locale locale) {
        this._useTimestamp = jacksonJodaFormatBase._useTimestamp;
        if (locale == null) {
            this._locale = DEFAULT_LOCALE;
            this._explicitLocale = false;
        } else {
            this._locale = locale;
            this._explicitLocale = true;
        }
    }

    public boolean useTimestamp(SerializerProvider serializerProvider, SerializationFeature serializationFeature) {
        Boolean bool = this._useTimestamp;
        if (bool != null) {
            return bool.booleanValue();
        }
        return serializerProvider.isEnabled(serializationFeature);
    }
}
