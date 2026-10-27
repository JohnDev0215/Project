package io.objectbox.converter;

/* JADX INFO: loaded from: classes5.dex */
public interface PropertyConverter<P, D> {
    D convertToDatabaseValue(P p);

    P convertToEntityProperty(D d);
}
