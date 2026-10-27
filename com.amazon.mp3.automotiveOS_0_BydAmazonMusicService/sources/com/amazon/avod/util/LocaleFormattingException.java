package com.amazon.avod.util;

import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class LocaleFormattingException extends Exception {
    public LocaleFormattingException(@Nonnull String str) {
        super(String.format(Locale.US, "String %s could not be parsed from Amazon Locale format", str));
    }
}
