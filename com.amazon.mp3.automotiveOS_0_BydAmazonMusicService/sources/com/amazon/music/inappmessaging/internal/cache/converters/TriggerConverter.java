package com.amazon.music.inappmessaging.internal.cache.converters;

import androidx.room.TypeConverter;
import com.amazon.music.inappmessaging.external.model.Trigger;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/* JADX INFO: loaded from: classes4.dex */
public class TriggerConverter {
    private static final Gson gson = new Gson();

    @TypeConverter
    public static Trigger fromString(String value) {
        return (Trigger) gson.fromJson(value, new TypeToken<Trigger>() { // from class: com.amazon.music.inappmessaging.internal.cache.converters.TriggerConverter.1
        }.getType());
    }

    @TypeConverter
    public static String fromTrigger(Trigger trigger) {
        return gson.toJson(trigger);
    }
}
