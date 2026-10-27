package io.objectbox.converter;

/* JADX INFO: loaded from: classes5.dex */
public class IntegerFlexMapConverter extends FlexObjectConverter {
    @Override // io.objectbox.converter.FlexObjectConverter
    protected void checkMapKeyType(Object obj) {
        if (!(obj instanceof Integer)) {
            throw new IllegalArgumentException("Map keys must be Integer");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.objectbox.converter.FlexObjectConverter
    public Integer convertToKey(String str) {
        return Integer.valueOf(str);
    }
}
