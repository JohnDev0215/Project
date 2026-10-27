package com.google.i18n.phonenumbers;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: MultiFileMetadataSourceImpl.java */
/* JADX INFO: loaded from: classes.dex */
final class f implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f4138a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c f4139b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final ConcurrentHashMap<String, Phonemetadata$PhoneMetadata> f4140c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final ConcurrentHashMap<Integer, Phonemetadata$PhoneMetadata> f4141d;

    f(String str, c cVar) {
        this.f4140c = new ConcurrentHashMap<>();
        this.f4141d = new ConcurrentHashMap<>();
        this.f4138a = str;
        this.f4139b = cVar;
    }

    private boolean c(int i) {
        List<String> list = b.a().get(Integer.valueOf(i));
        return list.size() == 1 && "001".equals(list.get(0));
    }

    @Override // com.google.i18n.phonenumbers.e
    public Phonemetadata$PhoneMetadata a(int i) {
        if (c(i)) {
            return d.a(Integer.valueOf(i), this.f4141d, this.f4138a, this.f4139b);
        }
        return null;
    }

    @Override // com.google.i18n.phonenumbers.e
    public Phonemetadata$PhoneMetadata b(String str) {
        return d.a(str, this.f4140c, this.f4138a, this.f4139b);
    }

    f(c cVar) {
        this("/com/google/i18n/phonenumbers/data/PhoneNumberMetadataProto", cVar);
    }
}
