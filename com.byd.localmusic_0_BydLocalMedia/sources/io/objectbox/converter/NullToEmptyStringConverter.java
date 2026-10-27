package io.objectbox.converter;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class NullToEmptyStringConverter implements PropertyConverter<String, String> {
    @Override // io.objectbox.converter.PropertyConverter
    public String convertToDatabaseValue(String str) {
        return str;
    }

    @Override // io.objectbox.converter.PropertyConverter
    public String convertToEntityProperty(@Nullable String str) {
        return str == null ? "" : str;
    }
}
