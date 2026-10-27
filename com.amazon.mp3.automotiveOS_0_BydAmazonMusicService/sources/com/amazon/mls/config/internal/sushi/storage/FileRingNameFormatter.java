package com.amazon.mls.config.internal.sushi.storage;

import com.amazon.mls.config.internal.core.configuration.Region;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class FileRingNameFormatter {
    public String getFileName(long j, Region region, int i) {
        return String.format(Locale.US, "%s_%d_%s_%d", "v2", Long.valueOf(j), region.toString(), Integer.valueOf(i));
    }
}
