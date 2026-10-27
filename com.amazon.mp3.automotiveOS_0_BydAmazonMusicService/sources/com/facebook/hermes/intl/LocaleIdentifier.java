package com.facebook.hermes.intl;

/* JADX INFO: loaded from: classes5.dex */
public class LocaleIdentifier {
    static String canonicalizeLocaleId(String str) throws JSRangeErrorException {
        return LocaleObject.createFromLocaleId(str).toCanonicalTag();
    }
}
