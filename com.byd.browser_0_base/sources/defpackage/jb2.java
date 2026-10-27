package defpackage;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.SparseArray;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: RequestThrottler.java */
/* JADX INFO: loaded from: classes.dex */
public final class jb2 {
    public static final AtomicBoolean g = new AtomicBoolean();
    public static SparseArray<jb2> h;
    public long a = -1;
    public long b = 100;
    public final SharedPreferences c;
    public final int d;
    public float e;
    public long f;

    public jb2(int i) {
        SharedPreferences sharedPreferences = xz.a.getSharedPreferences("customtabs_client_bans", 0);
        this.c = sharedPreferences;
        this.d = i;
        this.e = sharedPreferences.getFloat("score_" + i, 10.0f);
        sharedPreferences.getLong("last_request_" + i, 0L);
        this.f = sharedPreferences.getLong("banned_until_" + i, 0L);
    }

    public static jb2 a(int i) {
        String key;
        if (h == null) {
            h = new SparseArray<>();
            SharedPreferences sharedPreferences = xz.a.getSharedPreferences("customtabs_client_bans", 0);
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            long jCurrentTimeMillis = System.currentTimeMillis();
            for (Map.Entry<String, ?> entry : sharedPreferences.getAll().entrySet()) {
                if (entry != null && (key = entry.getKey()) != null && key.startsWith("last_request_")) {
                    try {
                        if (jCurrentTimeMillis - ((Long) entry.getValue()).longValue() >= 1209600000) {
                            String strSubstring = key.substring(13);
                            editorEdit.remove("score_" + strSubstring).remove("last_request_" + strSubstring).remove("banned_until_" + strSubstring);
                        }
                    } catch (NumberFormatException unused) {
                    }
                }
            }
            editorEdit.apply();
        }
        jb2 jb2Var = h.get(i);
        if (jb2Var != null) {
            return jb2Var;
        }
        jb2 jb2Var2 = new jb2(i);
        h.put(i, jb2Var2);
        return jb2Var2;
    }

    @SuppressLint({"CommitPrefEdits"})
    public static void b() {
        if (!g.compareAndSet(false, true)) {
            return;
        }
        PostTask.c(d73.g, new gg3(2));
    }

    public final void c(String str) {
        this.b = 100L;
        this.a = -1L;
        this.e = Math.min(10.0f, this.e + (TextUtils.equals(null, str) ? 2 : 1));
        SharedPreferences.Editor editorEdit = this.c.edit();
        float f = this.e;
        int i = this.d;
        if (f <= 0.0f) {
            this.e = 10.0f;
            this.f = System.currentTimeMillis() + 604800000;
            editorEdit.putLong(gq2.e("banned_until_", i), this.f);
        }
        editorEdit.putFloat(gq2.e("score_", i), this.e);
        editorEdit.apply();
    }
}
