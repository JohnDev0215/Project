package com.fasterxml.jackson.datatype.joda.cfg;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.Locale;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;

/* JADX INFO: loaded from: classes6.dex */
public class JacksonJodaPeriodFormat extends JacksonJodaFormatBase {
    protected final PeriodFormatter _formatter;

    public JacksonJodaPeriodFormat withFormat(String str) {
        return this;
    }

    @Override // com.fasterxml.jackson.datatype.joda.cfg.JacksonJodaFormatBase
    public /* bridge */ /* synthetic */ boolean useTimestamp(SerializerProvider serializerProvider, SerializationFeature serializationFeature) {
        return super.useTimestamp(serializerProvider, serializationFeature);
    }

    public JacksonJodaPeriodFormat(PeriodFormatter periodFormatter) {
        this._formatter = periodFormatter;
    }

    public JacksonJodaPeriodFormat(JacksonJodaPeriodFormat jacksonJodaPeriodFormat, Locale locale) {
        super(jacksonJodaPeriodFormat, locale);
        PeriodFormatter periodFormatter = jacksonJodaPeriodFormat._formatter;
        this._formatter = locale != null ? periodFormatter.withLocale(locale) : periodFormatter;
    }

    public JacksonJodaPeriodFormat(JacksonJodaPeriodFormat jacksonJodaPeriodFormat, Boolean bool) {
        super(jacksonJodaPeriodFormat, bool);
        this._formatter = jacksonJodaPeriodFormat._formatter;
    }

    public PeriodFormatter nativeFormatter() {
        return this._formatter;
    }

    public JacksonJodaPeriodFormat withUseTimestamp(Boolean bool) {
        Boolean bool2 = this._useTimestamp;
        return (bool2 == null || !bool2.equals(bool)) ? new JacksonJodaPeriodFormat(this, bool) : this;
    }

    public JacksonJodaPeriodFormat withLocale(Locale locale) {
        Locale locale2;
        return (locale == null || ((locale2 = this._locale) != null && locale2.equals(locale))) ? this : new JacksonJodaPeriodFormat(this, locale);
    }

    public PeriodFormatter createFormatter(SerializerProvider serializerProvider) {
        Locale locale;
        PeriodFormatter periodFormatter = this._formatter;
        return (this._explicitLocale || (locale = serializerProvider.getLocale()) == null || locale.equals(this._locale)) ? periodFormatter : periodFormatter.withLocale(locale);
    }

    public Period parsePeriod(DeserializationContext deserializationContext, String str) throws IOException {
        return this._formatter.parsePeriod(str);
    }
}
