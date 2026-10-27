package com.jakewharton.threetenabp;

import android.app.Application;
import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;
import org.threeten.bp.zone.TzdbZoneRulesProvider;
import org.threeten.bp.zone.ZoneRulesProvider;

/* JADX INFO: loaded from: classes2.dex */
public final class AndroidThreeTen {
    private static final AtomicBoolean initialized = new AtomicBoolean();

    public static void init(Application application) {
        init((Context) application);
    }

    public static void init(Context context) {
        if (initialized.getAndSet(true)) {
            return;
        }
        InputStream inputStreamOpen = null;
        try {
            try {
                inputStreamOpen = context.getAssets().open("org/threeten/bp/TZDB.dat");
                TzdbZoneRulesProvider tzdbZoneRulesProvider = new TzdbZoneRulesProvider(inputStreamOpen);
                if (inputStreamOpen != null) {
                    try {
                        inputStreamOpen.close();
                    } catch (IOException unused) {
                    }
                }
                ZoneRulesProvider.registerProvider(tzdbZoneRulesProvider);
            } catch (IOException e) {
                throw new IllegalStateException("TZDB.dat missing from assets.", e);
            }
        } catch (Throwable th) {
            if (inputStreamOpen != null) {
                try {
                    inputStreamOpen.close();
                } catch (IOException unused2) {
                }
            }
            throw th;
        }
    }

    private AndroidThreeTen() {
        throw new AssertionError("No instances.");
    }
}
