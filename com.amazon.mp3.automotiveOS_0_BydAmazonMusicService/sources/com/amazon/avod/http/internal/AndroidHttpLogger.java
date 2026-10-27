package com.amazon.avod.http.internal;

import com.amazon.avod.http.MetricEventListener;
import com.amazon.avod.util.DLog;
import com.amazon.bolthttp.Request;
import com.amazon.bolthttp.internal.HttpLogger;
import com.amazonaws.http.HttpHeader;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AndroidHttpLogger extends HttpLogger {
    protected final MetricEventListener.ServiceNameProvider mServiceNameProvider;

    public AndroidHttpLogger(@Nonnull MetricEventListener.ServiceNameProvider serviceNameProvider) {
        this.mServiceNameProvider = (MetricEventListener.ServiceNameProvider) Preconditions.checkNotNull(serviceNameProvider, "serviceNameProvider");
    }

    @Override // com.amazon.bolthttp.internal.HttpLogger
    protected void logLines(@Nonnull Request request, @Nonnull List<String> list, @Nonnull List<String> list2) {
        String apiShortName = this.mServiceNameProvider.getApiShortName(request);
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            Iterator<String> it2 = splitLineToFit(it.next()).iterator();
            while (it2.hasNext()) {
                DLog.logf("HttpLogger.Request(%s): %s", apiShortName, transformRequestLine(it2.next()));
            }
        }
        Iterator<String> it3 = list2.iterator();
        while (it3.hasNext()) {
            Iterator<String> it4 = splitLineToFit(it3.next()).iterator();
            while (it4.hasNext()) {
                DLog.logf("HttpLogger.Response(%s): %s", apiShortName, it4.next());
            }
        }
    }

    @Nonnull
    private List<String> splitLineToFit(@Nonnull String str) {
        ArrayList arrayListNewArrayListWithExpectedSize = Lists.newArrayListWithExpectedSize((str.length() / 2048) + 1);
        int i = 0;
        while (i < str.length()) {
            int i2 = i + 2048;
            arrayListNewArrayListWithExpectedSize.add(str.substring(i, Math.min(str.length(), i2)));
            i = i2;
        }
        return arrayListNewArrayListWithExpectedSize;
    }

    @Nonnull
    protected String transformRequestLine(@Nonnull String str) {
        return !str.startsWith(HttpHeader.AUTHORIZATION) ? str : String.format(Locale.US, "%s: <REMOVED>", HttpHeader.AUTHORIZATION);
    }
}
