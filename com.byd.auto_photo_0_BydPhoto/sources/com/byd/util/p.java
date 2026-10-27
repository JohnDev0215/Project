package com.byd.util;

import android.content.Context;
import android.content.SharedPreferences;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile p f2987a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f2988b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private b f2989c;

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f2990a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f2991b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f2992c;
        private byte d;

        private b(String str, int i, int i2, byte b2) {
            b(str, i, i2, b2);
        }

        public String a() {
            return this.f2990a;
        }

        public void b(String str, int i, int i2, byte b2) {
            this.f2990a = str;
            this.f2991b = i;
            this.f2992c = i2;
            this.d = b2;
        }

        public String toString() {
            return "PlayInfo{url='" + this.f2990a + "', pos=" + this.f2991b + ", duration=" + this.f2992c + ", pauseState=" + ((int) this.d) + '}';
        }
    }

    private p(Context context) {
        this.f2988b = context.getApplicationContext();
    }

    private boolean a(String str) {
        SharedPreferences sharedPreferences = this.f2988b.getSharedPreferences("last_play_info.xml", 0);
        boolean z = sharedPreferences.getBoolean(str, true);
        sharedPreferences.edit().putBoolean(str, false).apply();
        return z;
    }

    public static p d(Context context) {
        if (f2987a == null) {
            synchronized (p.class) {
                if (f2987a == null) {
                    f2987a = new p(context);
                }
            }
        }
        return f2987a;
    }

    public static boolean f() {
        return f2987a != null;
    }

    public int b(String str) {
        SharedPreferences sharedPreferences = this.f2988b.getSharedPreferences("last_play_info.xml", 0);
        String string = sharedPreferences.getString("path", null);
        if (string != null && !"".equals(string) && str.equals(string)) {
            File file = new File(str);
            long j = sharedPreferences.getLong("modified", 0L);
            if (sharedPreferences.getLong("file_length", 0L) == file.length() && j == file.lastModified()) {
                int i = sharedPreferences.getInt("position", 0);
                w.c("checkMediaLastPlayPos==>" + i);
                return i;
            }
        }
        return 0;
    }

    public void c() {
        if (this.f2989c != null) {
            this.f2989c = null;
        }
    }

    public b e() {
        return this.f2989c;
    }

    public boolean g() {
        return a("first");
    }

    public boolean h() {
        return a("first_share_more_dev");
    }

    public void i() {
        this.f2988b = null;
        this.f2989c = null;
        f2987a = null;
    }

    public void j(String str, int i, int i2, byte b2) {
        b bVar = this.f2989c;
        if (bVar == null) {
            this.f2989c = new b(str, i, i2, b2);
        } else {
            bVar.b(str, i, i2, b2);
        }
    }

    public void k(String str, int i) {
        String str2;
        Context context = this.f2988b;
        if (context == null) {
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("last_play_info.xml", 0);
        File file = new File(str);
        if (!file.exists() || "".equals(str)) {
            sharedPreferences.edit().putString("path", "").putLong("modified", 0L).putLong("file_length", 0L).putInt("position", 0).apply();
            str2 = "clear last play info";
        } else {
            sharedPreferences.edit().putString("path", str).putLong("modified", file.lastModified()).putLong("file_length", file.length()).putInt("position", i).apply();
            str2 = "updateLastPlayInfo==>" + str + " pos:" + i;
        }
        w.c(str2);
    }
}
