package defpackage;

import android.content.SharedPreferences;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: compiled from: CachedFlagsSafeMode.java */
/* JADX INFO: loaded from: classes.dex */
public final class uk {
    public Boolean a;
    public final AtomicInteger b = new AtomicInteger(0);
    public final AtomicBoolean c = new AtomicBoolean(false);
    public final AtomicBoolean d = new AtomicBoolean(false);

    public static void a(uk ukVar, wk3 wk3Var) {
        ukVar.getClass();
        TraceEvent.q("writeSafeValues", null);
        SharedPreferences.Editor editorEdit = b().edit();
        editorEdit.clear();
        synchronized (wk3Var.a) {
            for (Map.Entry entry : wk3Var.a.entrySet()) {
                editorEdit.putBoolean((String) entry.getKey(), ((Boolean) entry.getValue()).booleanValue());
            }
        }
        synchronized (wk3Var.c) {
            for (Map.Entry entry2 : wk3Var.c.entrySet()) {
                editorEdit.putInt((String) entry2.getKey(), ((Integer) entry2.getValue()).intValue());
            }
        }
        synchronized (wk3Var.d) {
            for (Map.Entry entry3 : wk3Var.d.entrySet()) {
                editorEdit.putLong((String) entry3.getKey(), Double.doubleToRawLongBits(((Double) entry3.getValue()).doubleValue()));
            }
        }
        synchronized (wk3Var.b) {
            for (Map.Entry entry4 : wk3Var.b.entrySet()) {
                editorEdit.putString((String) entry4.getKey(), (String) entry4.getValue());
            }
        }
        editorEdit.putString("Chrome.Flags.SafeValuesVersion", "4.1.1.0");
        editorEdit.apply();
        TraceEvent.C("writeSafeValues");
    }

    public static SharedPreferences b() {
        return xz.a.getSharedPreferences("org.chromium.chrome.browser.flags.SafeModeValues", 0);
    }

    public static boolean e() {
        int iD = SharedPreferencesManager.getInstance().d(0, "Chrome.Flags.SafeModeRunsLeft");
        if (iD > 0) {
            SharedPreferencesManager.getInstance().m(iD - 1, "Chrome.Flags.SafeModeRunsLeft");
            fa1.b("Flags", "Enter Safe Mode for CachedFlags, %d runs left.", Integer.valueOf(iD));
            return true;
        }
        int iD2 = SharedPreferencesManager.getInstance().d(0, "Chrome.Flags.CrashStreakBeforeCache");
        oj2.U(iD2, 50, "Variations.SafeModeCachedFlags.Streak.Crashes");
        if (iD2 < 2) {
            return false;
        }
        SharedPreferencesManager.getInstance().m(1, "Chrome.Flags.SafeModeRunsLeft");
        fa1.b("Flags", "Enter Safe Mode for CachedFlags, crash streak is %d.", Integer.valueOf(iD2));
        return true;
    }

    public final boolean c() {
        if (this.a == null) {
            this.a = Boolean.valueOf(SharedPreferencesManager.getInstance().readBoolean("Chrome.Flags.SafeModeEnabled", false));
        }
        return this.a.booleanValue();
    }

    public final void d() {
        synchronized (this.b) {
            if (this.b.get() != 0) {
                return;
            }
            if (e()) {
                String string = b().getString("Chrome.Flags.SafeValuesVersion", "");
                int i = string.isEmpty() ? 4 : !string.equals("4.1.1.0") ? 3 : 2;
                this.b.set(i);
                oj2.U(i, 5, "Variations.SafeModeCachedFlags.Engaged");
            } else {
                this.b.set(1);
                oj2.U(1, 5, "Variations.SafeModeCachedFlags.Engaged");
            }
        }
    }
}
