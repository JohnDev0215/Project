package defpackage;

import android.os.Bundle;
import java.util.Locale;

/* JADX INFO: renamed from: xy, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1794xy {
    public final long a;
    public final boolean b;
    public final String c;
    public final int d;

    public C1794xy(long j, boolean z) {
        this.a = j;
        this.b = z;
        this.c = null;
        this.d = 0;
    }

    public String toString() {
        return String.format(Locale.US, "LinkerParams(baseLoadAddress:0x%x, waitForSharedRelro:%s, testRunnerClassName:%s, linkerImplementation:%d", Long.valueOf(this.a), Boolean.toString(this.b), this.c, Integer.valueOf(this.d));
    }

    public C1794xy(Bundle bundle) {
        this.a = bundle.getLong("org.chromium.content.common.linker_params.base_load_address", 0L);
        this.b = bundle.getBoolean("org.chromium.content.common.linker_params.wait_for_shared_relro", false);
        this.c = bundle.getString("org.chromium.content.common.linker_params.test_runner_class_name");
        this.d = bundle.getInt("org.chromium.content.common.linker_params.linker_implementation", 0);
    }
}
