package org.joda.time.convert;

import org.joda.time.ReadablePartial;

/* JADX INFO: loaded from: classes6.dex */
class ReadablePartialConverter extends AbstractConverter {
    static final ReadablePartialConverter INSTANCE = new ReadablePartialConverter();

    protected ReadablePartialConverter() {
    }

    @Override // org.joda.time.convert.Converter
    public Class<?> getSupportedType() {
        return ReadablePartial.class;
    }
}
