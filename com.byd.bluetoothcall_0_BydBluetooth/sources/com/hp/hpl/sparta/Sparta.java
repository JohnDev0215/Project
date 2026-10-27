package com.hp.hpl.sparta;

import java.util.Hashtable;

/* JADX INFO: loaded from: classes.dex */
public class Sparta {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static c f4162a = new n();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static b f4163b = new o();

    /* JADX INFO: Access modifiers changed from: private */
    static class HashtableCache extends Hashtable implements a {
        private HashtableCache() {
        }

        /* synthetic */ HashtableCache(n nVar) {
            this();
        }
    }

    public interface a {
    }

    public interface b {
        a a();
    }

    public interface c {
        String a(String str);
    }

    public static String a(String str) {
        return f4162a.a(str);
    }

    static a b() {
        return f4163b.a();
    }
}
