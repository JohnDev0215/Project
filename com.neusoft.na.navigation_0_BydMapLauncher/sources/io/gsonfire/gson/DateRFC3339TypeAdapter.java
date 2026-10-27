package io.gsonfire.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.gsonfire.util.RFC3339DateFormat;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public final class DateRFC3339TypeAdapter extends TypeAdapter<Date> {
    private final ThreadLocal<DateFormat> dateFormatThreadLocal;
    private final TimeZone serializationTimezone;
    private final boolean serializeTime;

    public DateRFC3339TypeAdapter(boolean z) {
        this(TimeZone.getDefault(), z);
    }

    public DateRFC3339TypeAdapter(TimeZone timeZone, boolean z) {
        this.dateFormatThreadLocal = new ThreadLocal<>();
        this.serializationTimezone = timeZone;
        this.serializeTime = z;
    }

    private DateFormat getDateFormat() {
        DateFormat dateFormat = this.dateFormatThreadLocal.get();
        if (dateFormat != null) {
            return dateFormat;
        }
        RFC3339DateFormat rFC3339DateFormat = new RFC3339DateFormat(this.serializationTimezone, this.serializeTime);
        this.dateFormatThreadLocal.set(rFC3339DateFormat);
        return rFC3339DateFormat;
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, Date date) throws IOException {
        jsonWriter.value(getDateFormat().format(date));
    }

    @Override // com.google.gson.TypeAdapter
    public Date read(JsonReader jsonReader) throws IOException {
        String strNextString = jsonReader.nextString();
        try {
            return getDateFormat().parse(strNextString);
        } catch (ParseException e) {
            throw new IOException("Could not parse date " + strNextString, e);
        }
    }
}
