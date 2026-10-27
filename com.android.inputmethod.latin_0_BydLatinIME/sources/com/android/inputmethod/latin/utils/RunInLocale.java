package com.android.inputmethod.latin.utils;

import android.content.res.Configuration;
import android.content.res.Resources;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public abstract class RunInLocale<T> {
    private static final Object sLockForRunInLocale = new Object();

    protected abstract T job(final Resources res);

    public T runInLocale(final Resources res, final Locale newLocale) {
        synchronized (sLockForRunInLocale) {
            Configuration configuration = res.getConfiguration();
            if (newLocale != null && !newLocale.equals(configuration.locale)) {
                Locale locale = configuration.locale;
                try {
                    configuration.locale = newLocale;
                    res.updateConfiguration(configuration, null);
                    return job(res);
                } finally {
                    configuration.locale = locale;
                    res.updateConfiguration(configuration, null);
                }
            }
            return job(res);
        }
    }
}
