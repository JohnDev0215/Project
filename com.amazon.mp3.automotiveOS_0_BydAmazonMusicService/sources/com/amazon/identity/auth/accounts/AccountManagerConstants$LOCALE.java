package com.amazon.identity.auth.accounts;

import java.util.Locale;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public enum AccountManagerConstants$LOCALE {
    US,
    DE,
    UK,
    JP,
    FR,
    CA,
    ES,
    CN;

    private static final String LOCALE_SEPERATOR = "-";

    public String toUrlString() {
        if (US.equals(this)) {
            return "";
        }
        return name().toLowerCase(Locale.US) + LOCALE_SEPERATOR;
    }
}
