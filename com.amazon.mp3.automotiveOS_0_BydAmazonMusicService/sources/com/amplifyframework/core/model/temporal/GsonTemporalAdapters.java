package com.amplifyframework.core.model.temporal;

import androidx.annotation.NonNull;
import com.amplifyframework.core.model.temporal.Temporal;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public final class GsonTemporalAdapters {
    private GsonTemporalAdapters() {
    }

    public static void register(@NonNull GsonBuilder gsonBuilder) {
        Objects.requireNonNull(gsonBuilder);
        gsonBuilder.registerTypeAdapter(Temporal.Date.class, new DateAdapter());
        gsonBuilder.registerTypeAdapter(Temporal.DateTime.class, new DateTimeAdapter());
        gsonBuilder.registerTypeAdapter(Temporal.Timestamp.class, new TimestampAdapter());
        gsonBuilder.registerTypeAdapter(Temporal.Time.class, new TimeAdapter());
        gsonBuilder.registerTypeAdapter(Date.class, new JavaDateAdapter());
    }

    public static final class DateAdapter implements JsonSerializer<Temporal.Date>, JsonDeserializer<Temporal.Date> {
        @Override // com.google.gson.JsonSerializer
        public JsonElement serialize(Temporal.Date date, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(date.format());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.JsonDeserializer
        public Temporal.Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
            try {
                return new Temporal.Date(jsonElement.getAsString());
            } catch (IllegalArgumentException e) {
                throw new JsonParseException("Failed to deserialize " + jsonElement.getAsString() + " as a Temporal.Date due to " + e);
            }
        }
    }

    public static final class DateTimeAdapter implements JsonSerializer<Temporal.DateTime>, JsonDeserializer<Temporal.DateTime> {
        @Override // com.google.gson.JsonSerializer
        public JsonElement serialize(Temporal.DateTime dateTime, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(dateTime.format());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.JsonDeserializer
        public Temporal.DateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            try {
                return new Temporal.DateTime(jsonElement.getAsString());
            } catch (IllegalArgumentException e) {
                throw new JsonParseException("Failed to deserialize " + jsonElement.getAsString() + " as a Temporal.DateTime due to " + e);
            }
        }
    }

    public static final class TimeAdapter implements JsonSerializer<Temporal.Time>, JsonDeserializer<Temporal.Time> {
        @Override // com.google.gson.JsonSerializer
        public JsonElement serialize(Temporal.Time time, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(time.format());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.JsonDeserializer
        public Temporal.Time deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            try {
                return new Temporal.Time(jsonElement.getAsString());
            } catch (IllegalArgumentException e) {
                throw new JsonParseException("Failed to deserialize " + jsonElement.getAsString() + " as a Temporal.Time due to " + e);
            }
        }
    }

    public static final class TimestampAdapter implements JsonSerializer<Temporal.Timestamp>, JsonDeserializer<Temporal.Timestamp> {
        @Override // com.google.gson.JsonSerializer
        public JsonElement serialize(Temporal.Timestamp timestamp, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(Long.valueOf(timestamp.getSecondsSinceEpoch()));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.JsonDeserializer
        public Temporal.Timestamp deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return new Temporal.Timestamp(jsonElement.getAsLong(), TimeUnit.SECONDS);
        }
    }

    public static final class JavaDateAdapter implements JsonSerializer<Date> {
        @Override // com.google.gson.JsonSerializer
        public JsonElement serialize(Date date, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date));
        }
    }
}
