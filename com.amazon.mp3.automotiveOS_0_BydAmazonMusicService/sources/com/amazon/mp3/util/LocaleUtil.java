package com.amazon.mp3.util;

import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public class LocaleUtil {
    public static Locale getLocaleFromCurrencyCode(String currencyCode) {
        currencyCode.hashCode();
        switch (currencyCode) {
            case "AUD":
            case "INR":
            case "MXN":
            case "NZD":
            case "USD":
                return Locale.US;
            case "BRL":
            case "EUR":
                return Locale.GERMANY;
            case "CAD":
                return Locale.CANADA;
            case "GBP":
                return Locale.UK;
            case "JPY":
                return Locale.JAPAN;
            default:
                return Locale.US;
        }
    }
}
