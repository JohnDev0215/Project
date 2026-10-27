package com.amazon.bolthttp.internal;

import com.amazon.bolthttp.Request;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import okhttp3.logging.HttpLoggingInterceptor;

/* JADX INFO: loaded from: classes2.dex */
public abstract class HttpLogger implements HttpLoggingInterceptor.Logger {
    private final List<String> mLines = new ArrayList();

    protected abstract void logLines(@Nonnull Request request, @Nonnull List<String> list, @Nonnull List<String> list2);

    @Override // okhttp3.logging.HttpLoggingInterceptor.Logger
    public final void log(@Nonnull String str) {
        this.mLines.add((String) Preconditions.checkNotNull(str, "message"));
    }

    public final void flush(@Nonnull Request request) {
        Preconditions.checkNotNull(request, "request");
        ArrayList arrayList = new ArrayList(this.mLines.size());
        ArrayList arrayList2 = new ArrayList(this.mLines.size());
        ArrayList arrayList3 = arrayList;
        for (String str : this.mLines) {
            if (str != null) {
                arrayList3.add(str);
                if (str.startsWith("--> END")) {
                    arrayList3 = arrayList2;
                }
            }
        }
        logLines(request, arrayList, arrayList2);
        this.mLines.clear();
    }
}
