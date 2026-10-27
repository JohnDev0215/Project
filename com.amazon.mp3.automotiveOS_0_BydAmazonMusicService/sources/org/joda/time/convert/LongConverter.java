package org.joda.time.convert;

import org.joda.time.Chronology;

/* JADX INFO: loaded from: classes6.dex */
class LongConverter extends AbstractConverter implements InstantConverter {
    static final LongConverter INSTANCE = new LongConverter();

    protected LongConverter() {
    }

    @Override // org.joda.time.convert.AbstractConverter, org.joda.time.convert.InstantConverter
    public long getInstantMillis(Object obj, Chronology chronology) {
        return ((Long) obj).longValue();
    }

    @Override // org.joda.time.convert.Converter
    public Class<?> getSupportedType() {
        return Long.class;
    }
}
