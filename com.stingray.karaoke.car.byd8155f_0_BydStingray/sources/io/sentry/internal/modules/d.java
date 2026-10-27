package io.sentry.internal.modules;

import io.sentry.EnumC1495p1;
import io.sentry.K;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class d implements a {
    public static final Charset c = Charset.forName("UTF-8");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final K f16578a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Map f16579b = null;

    public d(K k10) {
        this.f16578a = k10;
    }

    @Override // io.sentry.internal.modules.a
    public final Map a() {
        Map map = this.f16579b;
        if (map != null) {
            return map;
        }
        Map mapB = b();
        this.f16579b = mapB;
        return mapB;
    }

    public abstract Map b();

    public final TreeMap c(InputStream inputStream) {
        K k10 = this.f16578a;
        TreeMap treeMap = new TreeMap();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, c));
            try {
                for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                    int iLastIndexOf = line.lastIndexOf(58);
                    treeMap.put(line.substring(0, iLastIndexOf), line.substring(iLastIndexOf + 1));
                }
                k10.k(EnumC1495p1.DEBUG, "Extracted %d modules from resources.", Integer.valueOf(treeMap.size()));
                bufferedReader.close();
            } catch (Throwable th) {
                try {
                    bufferedReader.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException e10) {
            k10.s(EnumC1495p1.ERROR, "Error extracting modules.", e10);
        } catch (RuntimeException e11) {
            k10.p(EnumC1495p1.ERROR, e11, "%s file is malformed.", "sentry-external-modules.txt");
        }
        return treeMap;
    }
}
