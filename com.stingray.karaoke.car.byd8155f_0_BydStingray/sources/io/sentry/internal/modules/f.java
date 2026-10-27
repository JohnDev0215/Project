package io.sentry.internal.modules;

import android.content.Context;
import io.sentry.EnumC1495p1;
import io.sentry.K;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public final class f extends d {
    public final /* synthetic */ int d = 1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f16581e;

    public f(List list, K k10) {
        super(k10);
        this.f16581e = list;
    }

    @Override // io.sentry.internal.modules.d
    public final Map b() {
        switch (this.d) {
            case 0:
                K k10 = this.f16578a;
                TreeMap treeMap = new TreeMap();
                try {
                    InputStream resourceAsStream = ((ClassLoader) this.f16581e).getResourceAsStream("sentry-external-modules.txt");
                    try {
                        if (resourceAsStream == null) {
                            k10.k(EnumC1495p1.INFO, "%s file was not found.", "sentry-external-modules.txt");
                            if (resourceAsStream != null) {
                                resourceAsStream.close();
                            }
                        } else {
                            TreeMap treeMapC = c(resourceAsStream);
                            resourceAsStream.close();
                            treeMap = treeMapC;
                        }
                    } catch (Throwable th) {
                        if (resourceAsStream != null) {
                            try {
                                resourceAsStream.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                            break;
                        }
                        throw th;
                    }
                    break;
                } catch (IOException e10) {
                    k10.s(EnumC1495p1.INFO, "Access to resources failed.", e10);
                } catch (SecurityException e11) {
                    k10.s(EnumC1495p1.INFO, "Access to resources denied.", e11);
                }
                return treeMap;
            case 1:
                K k11 = this.f16578a;
                TreeMap treeMap2 = new TreeMap();
                try {
                    InputStream inputStreamOpen = ((Context) this.f16581e).getAssets().open("sentry-external-modules.txt");
                    try {
                        TreeMap treeMapC2 = c(inputStreamOpen);
                        if (inputStreamOpen != null) {
                            inputStreamOpen.close();
                        }
                        return treeMapC2;
                    } catch (Throwable th3) {
                        if (inputStreamOpen != null) {
                            try {
                                inputStreamOpen.close();
                            } catch (Throwable th4) {
                                th3.addSuppressed(th4);
                            }
                            break;
                        }
                        throw th3;
                    }
                } catch (FileNotFoundException unused) {
                    k11.k(EnumC1495p1.INFO, "%s file was not found.", "sentry-external-modules.txt");
                    return treeMap2;
                } catch (IOException e12) {
                    k11.s(EnumC1495p1.ERROR, "Error extracting modules.", e12);
                    return treeMap2;
                }
            default:
                TreeMap treeMap3 = new TreeMap();
                Iterator it = ((List) this.f16581e).iterator();
                while (it.hasNext()) {
                    Map mapA = ((a) it.next()).a();
                    if (mapA != null) {
                        treeMap3.putAll(mapA);
                    }
                }
                return treeMap3;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(K k10) {
        super(k10);
        ClassLoader classLoader = f.class.getClassLoader();
        this.f16581e = classLoader == null ? ClassLoader.getSystemClassLoader() : classLoader;
    }

    public f(Context context, K k10) {
        super(k10);
        Context applicationContext = context.getApplicationContext();
        this.f16581e = applicationContext != null ? applicationContext : context;
    }
}
