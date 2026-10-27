package com.google.i18n.phonenumbers;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: compiled from: MetadataManager.java */
/* JADX INFO: loaded from: classes.dex */
final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final c f4133a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Logger f4134b = Logger.getLogger(d.class.getName());

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final ConcurrentHashMap<Integer, Phonemetadata$PhoneMetadata> f4135c = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final ConcurrentHashMap<String, Phonemetadata$PhoneMetadata> f4136d = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final Set<Integer> f4137e = com.google.i18n.phonenumbers.a.a();
    private static final Set<String> f = g.a();

    /* JADX INFO: compiled from: MetadataManager.java */
    static class a implements c {
        a() {
        }

        @Override // com.google.i18n.phonenumbers.c
        public InputStream a(String str) {
            return d.class.getResourceAsStream(str);
        }
    }

    private d() {
    }

    static <T> Phonemetadata$PhoneMetadata a(T t, ConcurrentHashMap<T, Phonemetadata$PhoneMetadata> concurrentHashMap, String str, c cVar) {
        Phonemetadata$PhoneMetadata phonemetadata$PhoneMetadata = concurrentHashMap.get(t);
        if (phonemetadata$PhoneMetadata != null) {
            return phonemetadata$PhoneMetadata;
        }
        String str2 = str + "_" + t;
        List<Phonemetadata$PhoneMetadata> listB = b(str2, cVar);
        if (listB.size() > 1) {
            f4134b.log(Level.WARNING, "more than one metadata in file " + str2);
        }
        Phonemetadata$PhoneMetadata phonemetadata$PhoneMetadata2 = listB.get(0);
        Phonemetadata$PhoneMetadata phonemetadata$PhoneMetadataPutIfAbsent = concurrentHashMap.putIfAbsent(t, phonemetadata$PhoneMetadata2);
        return phonemetadata$PhoneMetadataPutIfAbsent != null ? phonemetadata$PhoneMetadataPutIfAbsent : phonemetadata$PhoneMetadata2;
    }

    private static List<Phonemetadata$PhoneMetadata> b(String str, c cVar) {
        InputStream inputStreamA = cVar.a(str);
        if (inputStreamA == null) {
            throw new IllegalStateException("missing metadata: " + str);
        }
        List<Phonemetadata$PhoneMetadata> metadataList = c(inputStreamA).getMetadataList();
        if (metadataList.size() != 0) {
            return metadataList;
        }
        throw new IllegalStateException("empty metadata: " + str);
    }

    private static Phonemetadata$PhoneMetadataCollection c(InputStream inputStream) throws Throwable {
        ObjectInputStream objectInputStream;
        ObjectInputStream objectInputStream2 = null;
        try {
            try {
                objectInputStream = new ObjectInputStream(inputStream);
            } catch (IOException e2) {
                throw new RuntimeException("cannot load/parse metadata", e2);
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            Phonemetadata$PhoneMetadataCollection phonemetadata$PhoneMetadataCollection = new Phonemetadata$PhoneMetadataCollection();
            try {
                phonemetadata$PhoneMetadataCollection.readExternal(objectInputStream);
                try {
                    objectInputStream.close();
                } catch (IOException e3) {
                    f4134b.log(Level.WARNING, "error closing input stream (ignored)", (Throwable) e3);
                }
                return phonemetadata$PhoneMetadataCollection;
            } catch (IOException e4) {
                throw new RuntimeException("cannot load/parse metadata", e4);
            }
        } catch (Throwable th2) {
            th = th2;
            objectInputStream2 = objectInputStream;
            try {
                if (objectInputStream2 != null) {
                    objectInputStream2.close();
                } else {
                    inputStream.close();
                }
            } catch (IOException e5) {
                f4134b.log(Level.WARNING, "error closing input stream (ignored)", (Throwable) e5);
            }
            throw th;
        }
    }
}
