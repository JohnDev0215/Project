package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

/* JADX INFO: renamed from: br, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0570br {
    public final Bundle a;

    public C0570br(Bundle bundle) {
        this.a = bundle;
    }

    public static C0570br a(Context context) {
        Bundle bundle = new Bundle();
        SharedPreferences sharedPreferences = context.getSharedPreferences("variations_prefs", 0);
        if (sharedPreferences.contains("job_interval")) {
            bundle.putLong("job_interval", sharedPreferences.getLong("job_interval", 0L));
        }
        if (sharedPreferences.contains("job_queue_time")) {
            bundle.putLong("job_queue_time", sharedPreferences.getLong("job_queue_time", 0L));
        }
        if (sharedPreferences.contains("last_enqueue_time")) {
            bundle.putLong("last_enqueue_time", sharedPreferences.getLong("last_enqueue_time", 0L));
        }
        if (sharedPreferences.contains("last_job_start_time")) {
            bundle.putLong("last_job_start_time", sharedPreferences.getLong("last_job_start_time", 0L));
        }
        if (sharedPreferences.contains("seed_fetch_result")) {
            bundle.putInt("seed_fetch_result", sharedPreferences.getInt("seed_fetch_result", 0));
        }
        if (sharedPreferences.contains("seed_fetch_time")) {
            bundle.putLong("seed_fetch_time", sharedPreferences.getLong("seed_fetch_time", 0L));
        }
        return new C0570br(bundle);
    }

    public boolean b(Context context) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("variations_prefs", 0).edit();
        editorEdit.clear();
        if (this.a.containsKey("job_interval")) {
            editorEdit.putLong("job_interval", this.a.getLong("job_interval"));
        }
        if (this.a.containsKey("job_queue_time")) {
            editorEdit.putLong("job_queue_time", this.a.getLong("job_queue_time"));
        }
        if (this.a.containsKey("last_enqueue_time")) {
            editorEdit.putLong("last_enqueue_time", this.a.getLong("last_enqueue_time"));
        }
        if (this.a.containsKey("last_job_start_time")) {
            editorEdit.putLong("last_job_start_time", this.a.getLong("last_job_start_time"));
        }
        if (this.a.containsKey("seed_fetch_result")) {
            editorEdit.putInt("seed_fetch_result", this.a.getInt("seed_fetch_result"));
        }
        if (this.a.containsKey("seed_fetch_time")) {
            editorEdit.putLong("seed_fetch_time", this.a.getLong("seed_fetch_time"));
        }
        return editorEdit.commit();
    }
}
