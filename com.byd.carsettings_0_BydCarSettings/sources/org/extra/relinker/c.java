package org.extra.relinker;

import android.content.Context;
import java.io.File;

/* JADX INFO: loaded from: classes3.dex */
public class c {

    public interface a {
        void a(Context context, String[] strArr, String str, File file, f fVar);
    }

    public interface b {
        String a(String str);

        String[] a();

        void b(String str);

        String c(String str);

        void d(String str);
    }

    /* JADX INFO: renamed from: org.extra.relinker.c$c, reason: collision with other inner class name */
    public interface InterfaceC0108c {
        void a();

        void a(Throwable th);
    }

    public interface d {
        void a(String str);
    }

    public static void a(Context context, String str) {
        a(context, str, null, null);
    }

    public static void a(Context context, String str, String str2, InterfaceC0108c interfaceC0108c) {
        new f().a(context, str, str2, interfaceC0108c);
    }
}
