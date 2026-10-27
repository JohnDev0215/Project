package org.chromium.chrome.browser.preferences;

import android.content.SharedPreferences;
import defpackage.a8;
import defpackage.ku2;
import defpackage.xz;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class SharedPreferencesManager {
    public final a8 a;
    public final HashMap b = new HashMap();

    public static class a {
        public static final SharedPreferencesManager a = new SharedPreferencesManager();
    }

    public interface b {
        void i(String str);
    }

    public SharedPreferencesManager() {
        if (this.a == null) {
            this.a = new a8();
        }
    }

    @CalledByNative
    public static SharedPreferencesManager getInstance() {
        return a.a;
    }

    public final void a(final b bVar) {
        SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() { // from class: ql2
            @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
            public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
                bVar.i(str);
            }
        };
        this.b.put(bVar, onSharedPreferenceChangeListener);
        xz.a.a.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    public final void b(String str, String str2) {
        this.a.getClass();
        SharedPreferences sharedPreferences = xz.a.a;
        HashSet hashSet = new HashSet(sharedPreferences.getStringSet(str, Collections.emptySet()));
        hashSet.add(str2);
        sharedPreferences.edit().putStringSet(str, hashSet).apply();
    }

    public final void c(String str) {
        this.a.getClass();
        SharedPreferences sharedPreferences = xz.a.a;
        int i = sharedPreferences.getInt(str, 0) + 1;
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putInt(str, i);
        editorEdit.apply();
    }

    @CalledByNative
    public boolean contains(String str) {
        this.a.getClass();
        return xz.a.a.contains(str);
    }

    public final int d(int i, String str) {
        this.a.getClass();
        ku2 ku2VarT = ku2.t();
        try {
            int i2 = xz.a.a.getInt(str, i);
            ku2VarT.close();
            return i2;
        } catch (Throwable th) {
            try {
                ku2VarT.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    public final long e(long j, String str) {
        this.a.getClass();
        ku2 ku2VarT = ku2.t();
        try {
            long j2 = xz.a.a.getLong(str, j);
            ku2VarT.close();
            return j2;
        } catch (Throwable th) {
            try {
                ku2VarT.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    public final long f(String str) {
        return e(0L, str);
    }

    public final String g(String str, String str2) {
        this.a.getClass();
        ku2 ku2VarT = ku2.t();
        try {
            String string = xz.a.a.getString(str, str2);
            ku2VarT.close();
            return string;
        } catch (Throwable th) {
            try {
                ku2VarT.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    public final Set<String> h(String str) {
        return i(str, Collections.emptySet());
    }

    public final Set<String> i(String str, Set<String> set) {
        this.a.getClass();
        Set<String> stringSet = xz.a.a.getStringSet(str, set);
        if (stringSet != null) {
            return Collections.unmodifiableSet(stringSet);
        }
        return null;
    }

    public final void j(String str, String str2) {
        this.a.getClass();
        SharedPreferences sharedPreferences = xz.a.a;
        HashSet hashSet = new HashSet(sharedPreferences.getStringSet(str, Collections.emptySet()));
        if (hashSet.remove(str2)) {
            sharedPreferences.edit().putStringSet(str, hashSet).apply();
        }
    }

    public final void k(b bVar) {
        SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = (SharedPreferences.OnSharedPreferenceChangeListener) this.b.get(bVar);
        if (onSharedPreferenceChangeListener == null) {
            return;
        }
        xz.a.a.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    public final void l(String str, boolean z) {
        this.a.getClass();
        SharedPreferences.Editor editorEdit = xz.a.a.edit();
        editorEdit.putBoolean(str, z);
        editorEdit.apply();
    }

    public final void m(int i, String str) {
        this.a.getClass();
        SharedPreferences.Editor editorEdit = xz.a.a.edit();
        editorEdit.putInt(str, i);
        editorEdit.apply();
    }

    public final void n(long j, String str) {
        this.a.getClass();
        SharedPreferences.Editor editorEdit = xz.a.a.edit();
        editorEdit.putLong(str, j);
        editorEdit.apply();
    }

    public final void o(String str, String str2) {
        this.a.getClass();
        SharedPreferences.Editor editorEdit = xz.a.a.edit();
        editorEdit.putString(str, str2);
        editorEdit.apply();
    }

    public final void p(String str, Set<String> set) {
        this.a.getClass();
        xz.a.a.edit().putStringSet(str, set).apply();
    }

    @CalledByNative
    public boolean readBoolean(String str, boolean z) {
        this.a.getClass();
        ku2 ku2VarT = ku2.t();
        try {
            boolean z2 = xz.a.a.getBoolean(str, z);
            ku2VarT.close();
            return z2;
        } catch (Throwable th) {
            try {
                ku2VarT.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    @CalledByNative
    public void removeKey(String str) {
        this.a.getClass();
        SharedPreferences.Editor editorEdit = xz.a.a.edit();
        editorEdit.remove(str);
        editorEdit.apply();
    }
}
