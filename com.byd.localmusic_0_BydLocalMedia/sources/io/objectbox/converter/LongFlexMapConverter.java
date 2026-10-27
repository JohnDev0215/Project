package io.objectbox.converter;

/* JADX INFO: loaded from: classes5.dex */
public class LongFlexMapConverter extends FlexObjectConverter {
    @Override // io.objectbox.converter.FlexObjectConverter
    protected void checkMapKeyType(Object obj) {
        if (!(obj instanceof Long)) {
            throw new IllegalArgumentException("Map keys must be Long");
        }
    }

    @Override // io.objectbox.converter.FlexObjectConverter
    Object convertToKey(String str) {
        return Long.valueOf(str);
    }
}
