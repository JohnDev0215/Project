package io.objectbox.converter;

/* JADX INFO: loaded from: classes.dex */
public class NullToEmptyStringConverter implements PropertyConverter<String, String> {
    @Override // io.objectbox.converter.PropertyConverter
    public String convertToDatabaseValue(String str) {
        return str;
    }

    @Override // io.objectbox.converter.PropertyConverter
    public String convertToEntityProperty(String str) {
        return str == null ? "" : str;
    }
}
