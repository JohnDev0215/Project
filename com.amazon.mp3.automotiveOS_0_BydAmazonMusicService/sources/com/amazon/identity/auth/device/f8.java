package com.amazon.identity.auth.device;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class f8 {
    public static final String c = "com.amazon.identity.auth.device.f8";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SharedPreferences f313a;
    public final String b;

    @Deprecated
    public f8(Context context, String str) {
        this(context, str, 0);
    }

    public Boolean a(String str, boolean z) {
        return Boolean.valueOf(this.f313a.getBoolean(str, z));
    }

    public String b(String str) {
        return this.f313a.getString(str, null);
    }

    public boolean c(String str) {
        boolean zCommit = this.f313a.edit().remove(str).commit();
        if (!zCommit) {
            Log.e(ga.a(c), String.format("Failed to remove key: %s from value store %s", str, this.b));
        }
        return zCommit;
    }

    @Deprecated
    public f8(Context context, String str, int i) {
        this.f313a = context.getSharedPreferences(str, i);
        this.b = str;
    }

    public long a(String str) {
        return this.f313a.getLong(str, 0L);
    }

    @SuppressLint({"CommitPrefEdits"})
    public boolean a(String str, String str2) {
        boolean zCommit = this.f313a.edit().putString(str, str2).commit();
        if (!zCommit) {
            Log.e(ga.a(c), String.format("Failed to set key %s in the local key value store %s", str, this.b));
        }
        return zCommit;
    }

    public boolean a(String str, Boolean bool, int i, int i2) {
        SharedPreferences.Editor editorPutBoolean = this.f313a.edit().putBoolean(str, bool.booleanValue());
        for (int i3 = 0; i3 <= i; i3++) {
            if (editorPutBoolean.commit()) {
                return true;
            }
            Log.e(ga.a(c), "Commit failed retrying");
            try {
                Thread.sleep(i2);
            } catch (InterruptedException e) {
                Log.e(ga.a(c), "Retry sleep interrupted", e);
            }
        }
        return false;
    }

    @SuppressLint({"CommitPrefEdits"})
    public boolean a(String str, long j) {
        boolean zCommit = this.f313a.edit().putLong(str, j).commit();
        if (!zCommit) {
            Log.e(ga.a(c), String.format("Failed to set key %s in the local key value store %s", str, this.b));
        }
        return zCommit;
    }

    @SuppressLint({"CommitPrefEdits"})
    public boolean a(String str, Boolean bool) {
        boolean zCommit = this.f313a.edit().putBoolean(str, bool.booleanValue()).commit();
        if (!zCommit) {
            Log.e(ga.a(c), String.format("Failed to set key %s in the local key value store %s", str, this.b));
        }
        return zCommit;
    }

    public boolean a(String str, int i) {
        boolean zCommit = this.f313a.edit().putInt(str, i).commit();
        if (!zCommit) {
            Log.e(ga.a(c), String.format("Failed to set key %s in the local key value store %s", str, this.b));
        }
        return zCommit;
    }

    @TargetApi(26)
    public static f8 a(Context context, String str) {
        if (qe.g(context)) {
            Log.i(ga.a(c), "Create DE shared preference, OS supports direct boot.");
            return new f8(context.createDeviceProtectedStorageContext(), str, 0);
        }
        return new f8(context, str, 0);
    }

    @SuppressLint({"CommitPrefEdits"})
    public boolean a() {
        boolean zCommit = this.f313a.edit().clear().commit();
        if (!zCommit) {
            Log.e(ga.a(c), String.format("Failed to clear the local key value store %s", this.b));
        }
        return zCommit;
    }
}
