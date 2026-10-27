package org.tukaani.xz;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FilterOptions implements Cloneable {
    public abstract int getDecoderMemoryUsage();

    public abstract int getEncoderMemoryUsage();

    abstract FilterEncoder getFilterEncoder();

    public abstract InputStream getInputStream(InputStream inputStream, ArrayCache arrayCache) throws IOException;

    public abstract FinishableOutputStream getOutputStream(FinishableOutputStream finishableOutputStream, ArrayCache arrayCache);

    public static int getEncoderMemoryUsage(FilterOptions[] options) {
        int m = 0;
        for (FilterOptions filterOptions : options) {
            m += filterOptions.getEncoderMemoryUsage();
        }
        return m;
    }

    public static int getDecoderMemoryUsage(FilterOptions[] options) {
        int m = 0;
        for (FilterOptions filterOptions : options) {
            m += filterOptions.getDecoderMemoryUsage();
        }
        return m;
    }

    public FinishableOutputStream getOutputStream(FinishableOutputStream out) {
        return getOutputStream(out, ArrayCache.getDefaultCache());
    }

    public InputStream getInputStream(InputStream in) throws IOException {
        return getInputStream(in, ArrayCache.getDefaultCache());
    }

    FilterOptions() {
    }
}
