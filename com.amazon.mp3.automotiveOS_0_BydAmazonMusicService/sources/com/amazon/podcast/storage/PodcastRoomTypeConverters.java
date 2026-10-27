package com.amazon.podcast.storage;

import androidx.room.TypeConverter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class PodcastRoomTypeConverters {
    @TypeConverter
    public List<String> stringToStringList(final String data) {
        if (data == null) {
            return Collections.EMPTY_LIST;
        }
        return Arrays.asList(data.split(";"));
    }

    @TypeConverter
    public String stringListToString(final List<String> stringList) {
        return PodcastRoomTypeConverters$$ExternalSyntheticBackport0.m(";", stringList);
    }
}
