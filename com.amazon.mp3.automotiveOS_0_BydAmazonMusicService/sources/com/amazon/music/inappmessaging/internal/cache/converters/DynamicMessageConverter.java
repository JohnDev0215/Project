package com.amazon.music.inappmessaging.internal.cache.converters;

import androidx.room.TypeConverter;
import com.amazon.music.inappmessaging.internal.model.DynamicMessage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class DynamicMessageConverter {
    private static final Gson gson = new Gson();

    @TypeConverter
    public static List<DynamicMessage> fromString(String value) {
        return (List) gson.fromJson(value, new TypeToken<List<DynamicMessage>>() { // from class: com.amazon.music.inappmessaging.internal.cache.converters.DynamicMessageConverter.1
        }.getType());
    }

    @TypeConverter
    public static String fromDynamicMessages(List<DynamicMessage> dynamicMessages) {
        return gson.toJson(dynamicMessages);
    }
}
