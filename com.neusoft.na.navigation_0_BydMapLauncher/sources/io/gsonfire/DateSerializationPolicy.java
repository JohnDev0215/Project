package io.gsonfire;

import com.google.gson.TypeAdapter;
import io.gsonfire.gson.DateRFC3339TypeAdapter;
import io.gsonfire.gson.DateUnixtimeMillisTypeAdapter;
import io.gsonfire.gson.DateUnixtimeSecondsTypeAdapter;
import io.gsonfire.gson.NullableTypeAdapter;
import java.util.Date;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public enum DateSerializationPolicy {
    unixTimeMillis { // from class: io.gsonfire.DateSerializationPolicy.1
        @Override // io.gsonfire.DateSerializationPolicy
        TypeAdapter<Date> createTypeAdapter(TimeZone timeZone) {
            return new NullableTypeAdapter(new DateUnixtimeMillisTypeAdapter(true));
        }
    },
    unixTimeSeconds { // from class: io.gsonfire.DateSerializationPolicy.2
        @Override // io.gsonfire.DateSerializationPolicy
        TypeAdapter<Date> createTypeAdapter(TimeZone timeZone) {
            return new NullableTypeAdapter(new DateUnixtimeSecondsTypeAdapter(true));
        }
    },
    unixTimePositiveMillis { // from class: io.gsonfire.DateSerializationPolicy.3
        @Override // io.gsonfire.DateSerializationPolicy
        TypeAdapter<Date> createTypeAdapter(TimeZone timeZone) {
            return new NullableTypeAdapter(new DateUnixtimeMillisTypeAdapter(false));
        }
    },
    unixTimePositiveSeconds { // from class: io.gsonfire.DateSerializationPolicy.4
        @Override // io.gsonfire.DateSerializationPolicy
        TypeAdapter<Date> createTypeAdapter(TimeZone timeZone) {
            return new NullableTypeAdapter(new DateUnixtimeSecondsTypeAdapter(false));
        }
    },
    rfc3339 { // from class: io.gsonfire.DateSerializationPolicy.5
        @Override // io.gsonfire.DateSerializationPolicy
        TypeAdapter<Date> createTypeAdapter(TimeZone timeZone) {
            return new NullableTypeAdapter(new DateRFC3339TypeAdapter(timeZone, true));
        }
    },
    rfc3339Date { // from class: io.gsonfire.DateSerializationPolicy.6
        @Override // io.gsonfire.DateSerializationPolicy
        TypeAdapter<Date> createTypeAdapter(TimeZone timeZone) {
            return new NullableTypeAdapter(new DateRFC3339TypeAdapter(timeZone, false));
        }
    };

    abstract TypeAdapter<Date> createTypeAdapter(TimeZone timeZone);
}
