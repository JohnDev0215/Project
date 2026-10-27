package com.fasterxml.jackson.datatype.joda.cfg;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.util.Locale;
import java.util.TimeZone;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/* JADX INFO: loaded from: classes6.dex */
public class JacksonJodaDateFormat extends JacksonJodaFormatBase {
    private static final String JODA_STYLE_CHARS = "SMLF-";
    protected final Boolean _adjustToContextTZOverride;
    protected final boolean _explicitTimezone;
    protected final DateTimeFormatter _formatter;
    protected final DateTimeFormatter _formatterWithOffsetParsed;
    protected final TimeZone _jdkTimezone;
    protected transient DateTimeZone _jodaTimezone;
    protected final Boolean _writeZoneId;

    @Override // com.fasterxml.jackson.datatype.joda.cfg.JacksonJodaFormatBase
    public /* bridge */ /* synthetic */ boolean useTimestamp(SerializerProvider serializerProvider, SerializationFeature serializationFeature) {
        return super.useTimestamp(serializerProvider, serializationFeature);
    }

    public JacksonJodaDateFormat(DateTimeFormatter dateTimeFormatter) {
        this._formatter = dateTimeFormatter;
        this._formatterWithOffsetParsed = dateTimeFormatter.withOffsetParsed();
        DateTimeZone zone = dateTimeFormatter.getZone();
        this._jdkTimezone = zone == null ? null : zone.toTimeZone();
        this._explicitTimezone = false;
        this._adjustToContextTZOverride = null;
        this._writeZoneId = null;
    }

    public JacksonJodaDateFormat(JacksonJodaDateFormat jacksonJodaDateFormat, Boolean bool) {
        super(jacksonJodaDateFormat, bool);
        DateTimeFormatter dateTimeFormatter = jacksonJodaDateFormat._formatter;
        this._formatter = dateTimeFormatter;
        this._formatterWithOffsetParsed = dateTimeFormatter.withOffsetParsed();
        this._jdkTimezone = jacksonJodaDateFormat._jdkTimezone;
        this._explicitTimezone = jacksonJodaDateFormat._explicitTimezone;
        this._adjustToContextTZOverride = jacksonJodaDateFormat._adjustToContextTZOverride;
        this._writeZoneId = jacksonJodaDateFormat._writeZoneId;
    }

    public JacksonJodaDateFormat(JacksonJodaDateFormat jacksonJodaDateFormat, DateTimeFormatter dateTimeFormatter) {
        super(jacksonJodaDateFormat);
        this._formatter = dateTimeFormatter;
        this._formatterWithOffsetParsed = dateTimeFormatter.withOffsetParsed();
        this._jdkTimezone = jacksonJodaDateFormat._jdkTimezone;
        this._explicitTimezone = jacksonJodaDateFormat._explicitTimezone;
        this._adjustToContextTZOverride = jacksonJodaDateFormat._adjustToContextTZOverride;
        this._writeZoneId = jacksonJodaDateFormat._writeZoneId;
    }

    public JacksonJodaDateFormat(JacksonJodaDateFormat jacksonJodaDateFormat, TimeZone timeZone) {
        super(jacksonJodaDateFormat, timeZone);
        DateTimeFormatter dateTimeFormatterWithZone = jacksonJodaDateFormat._formatter.withZone(DateTimeZone.forTimeZone(timeZone));
        this._formatter = dateTimeFormatterWithZone;
        this._formatterWithOffsetParsed = dateTimeFormatterWithZone.withOffsetParsed();
        this._jdkTimezone = timeZone;
        this._explicitTimezone = true;
        this._adjustToContextTZOverride = jacksonJodaDateFormat._adjustToContextTZOverride;
        this._writeZoneId = jacksonJodaDateFormat._writeZoneId;
    }

    public JacksonJodaDateFormat(JacksonJodaDateFormat jacksonJodaDateFormat, Locale locale) {
        super(jacksonJodaDateFormat, locale);
        DateTimeFormatter dateTimeFormatterWithLocale = jacksonJodaDateFormat._formatter.withLocale(locale);
        this._formatter = dateTimeFormatterWithLocale;
        this._formatterWithOffsetParsed = dateTimeFormatterWithLocale.withOffsetParsed();
        this._jdkTimezone = jacksonJodaDateFormat._jdkTimezone;
        this._explicitTimezone = jacksonJodaDateFormat._explicitTimezone;
        this._adjustToContextTZOverride = jacksonJodaDateFormat._adjustToContextTZOverride;
        this._writeZoneId = jacksonJodaDateFormat._writeZoneId;
    }

    protected JacksonJodaDateFormat(JacksonJodaDateFormat jacksonJodaDateFormat, Boolean bool, Boolean bool2) {
        super(jacksonJodaDateFormat);
        DateTimeFormatter dateTimeFormatter = jacksonJodaDateFormat._formatter;
        this._formatter = dateTimeFormatter;
        this._formatterWithOffsetParsed = dateTimeFormatter.withOffsetParsed();
        this._jdkTimezone = jacksonJodaDateFormat._jdkTimezone;
        this._explicitTimezone = jacksonJodaDateFormat._explicitTimezone;
        this._adjustToContextTZOverride = bool;
        this._writeZoneId = bool2;
    }

    public JacksonJodaDateFormat with(JsonFormat.Value value) {
        JacksonJodaDateFormat jacksonJodaDateFormatWithFormat = withLocale(value.getLocale()).withTimeZone(value.getTimeZone()).withFormat(value.getPattern());
        Boolean feature = value.getFeature(JsonFormat.Feature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
        Boolean feature2 = value.getFeature(JsonFormat.Feature.WRITE_DATES_WITH_ZONE_ID);
        return (feature == this._adjustToContextTZOverride && feature2 == this._writeZoneId) ? jacksonJodaDateFormatWithFormat : new JacksonJodaDateFormat(jacksonJodaDateFormatWithFormat, feature, feature2);
    }

    public JacksonJodaDateFormat withUseTimestamp(Boolean bool) {
        Boolean bool2 = this._useTimestamp;
        return (bool2 == null || !bool2.equals(bool)) ? new JacksonJodaDateFormat(this, bool) : this;
    }

    public JacksonJodaDateFormat withFormat(String str) {
        DateTimeFormatter dateTimeFormatterForPattern;
        if (str == null || str.isEmpty()) {
            return this;
        }
        if (_isStyle(str)) {
            dateTimeFormatterForPattern = DateTimeFormat.forStyle(str);
        } else {
            dateTimeFormatterForPattern = DateTimeFormat.forPattern(str);
        }
        Locale locale = this._locale;
        if (locale != null) {
            dateTimeFormatterForPattern = dateTimeFormatterForPattern.withLocale(locale);
        }
        return new JacksonJodaDateFormat(this, dateTimeFormatterForPattern);
    }

    public JacksonJodaDateFormat withTimeZone(TimeZone timeZone) {
        TimeZone timeZone2;
        return (timeZone == null || ((timeZone2 = this._jdkTimezone) != null && timeZone2.equals(timeZone))) ? this : new JacksonJodaDateFormat(this, timeZone);
    }

    public JacksonJodaDateFormat withLocale(Locale locale) {
        Locale locale2;
        return (locale == null || ((locale2 = this._locale) != null && locale2.equals(locale))) ? this : new JacksonJodaDateFormat(this, locale);
    }

    public JacksonJodaDateFormat withAdjustToContextTZOverride(Boolean bool) {
        return bool == this._adjustToContextTZOverride ? this : new JacksonJodaDateFormat(this, bool, this._writeZoneId);
    }

    public JacksonJodaDateFormat withWriteZoneId(Boolean bool) {
        return bool == this._writeZoneId ? this : new JacksonJodaDateFormat(this, this._adjustToContextTZOverride, bool);
    }

    public DateTimeZone getTimeZone() {
        DateTimeZone dateTimeZone = this._jodaTimezone;
        if (dateTimeZone != null) {
            return dateTimeZone;
        }
        TimeZone timeZone = this._jdkTimezone;
        if (timeZone == null) {
            return null;
        }
        DateTimeZone dateTimeZoneForTimeZone = DateTimeZone.forTimeZone(timeZone);
        this._jodaTimezone = dateTimeZoneForTimeZone;
        return dateTimeZoneForTimeZone;
    }

    public Locale getLocale() {
        return this._locale;
    }

    public DateTimeFormatter rawFormatter() {
        return this._formatter;
    }

    public DateTimeFormatter createFormatter(SerializerProvider serializerProvider) {
        TimeZone timeZone;
        DateTimeFormatter dateTimeFormatterCreateFormatterWithLocale = createFormatterWithLocale(serializerProvider);
        return (this._explicitTimezone || (timeZone = serializerProvider.getTimeZone()) == null || timeZone.equals(this._jdkTimezone)) ? dateTimeFormatterCreateFormatterWithLocale : dateTimeFormatterCreateFormatterWithLocale.withZone(DateTimeZone.forTimeZone(timeZone));
    }

    public DateTimeFormatter createFormatterWithLocale(SerializerProvider serializerProvider) {
        Locale locale;
        DateTimeFormatter dateTimeFormatter = this._formatter;
        return (this._explicitLocale || (locale = serializerProvider.getLocale()) == null || locale.equals(this._locale)) ? dateTimeFormatter : dateTimeFormatter.withLocale(locale);
    }

    public DateTimeFormatter createParser(DeserializationContext deserializationContext) {
        Locale locale;
        DateTimeFormatter dateTimeFormatterWithZone = this._formatter;
        if (!this._explicitTimezone) {
            if (shouldAdjustToContextTimeZone(deserializationContext)) {
                TimeZone timeZone = deserializationContext.getTimeZone();
                if (timeZone != null && !timeZone.equals(this._jdkTimezone)) {
                    dateTimeFormatterWithZone = dateTimeFormatterWithZone.withZone(DateTimeZone.forTimeZone(timeZone));
                }
            } else {
                dateTimeFormatterWithZone = this._formatterWithOffsetParsed;
            }
        }
        return (this._explicitLocale || (locale = deserializationContext.getLocale()) == null || locale.equals(this._locale)) ? dateTimeFormatterWithZone : dateTimeFormatterWithZone.withLocale(locale);
    }

    public boolean shouldAdjustToContextTimeZone(DeserializationContext deserializationContext) {
        Boolean bool = this._adjustToContextTZOverride;
        return bool != null ? bool.booleanValue() : deserializationContext.isEnabled(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
    }

    public boolean shouldWriteWithZoneId(SerializerProvider serializerProvider) {
        Boolean bool = this._writeZoneId;
        return bool != null ? bool.booleanValue() : serializerProvider.isEnabled(SerializationFeature.WRITE_DATES_WITH_ZONE_ID);
    }

    public boolean isTimezoneExplicit() {
        return this._explicitTimezone;
    }

    protected static boolean _isStyle(String str) {
        return str.length() == 2 && JODA_STYLE_CHARS.indexOf(str.charAt(0)) >= 0 && JODA_STYLE_CHARS.indexOf(str.charAt(0)) >= 0;
    }

    public String toString() {
        return String.format("[JacksonJodaFormat, explicitTZ? %s, JDK tz = %s, formatter = %s]", Boolean.valueOf(this._explicitTimezone), this._jdkTimezone.getID(), this._formatter);
    }
}
