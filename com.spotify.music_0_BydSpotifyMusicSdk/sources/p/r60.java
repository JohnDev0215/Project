package p;

import android.app.Application;
import android.content.Context;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class r60 implements m10, l10 {
    public final Context a;

    public r60(Application application) {
        this.a = application;
    }

    public final File a() {
        return zb0.i(this.a.getFilesDir(), "core-cache");
    }

    public final File b() {
        return zb0.i(this.a.getFilesDir(), "core-settings");
    }
}
