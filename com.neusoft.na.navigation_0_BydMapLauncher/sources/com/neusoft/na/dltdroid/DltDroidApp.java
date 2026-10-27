package com.neusoft.na.dltdroid;

import android.app.Application;
import android.content.pm.PackageManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DltDroidApp.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\b\u001a\u00020\tH\u0016R\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/neusoft/na/dltdroid/DltDroidApp;", "Landroid/app/Application;", "()V", "<set-?>", "", "version", "getVersion", "()Ljava/lang/String;", "onCreate", "", "dltdroid-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class DltDroidApp extends Application {
    private String version = "";

    public final String getVersion() {
        return this.version;
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        try {
            String str = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            Intrinsics.checkNotNullExpressionValue(str, "info.versionName");
            this.version = str;
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }
}
