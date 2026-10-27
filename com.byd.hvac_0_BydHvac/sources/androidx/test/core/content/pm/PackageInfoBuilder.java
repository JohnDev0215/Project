package androidx.test.core.content.pm;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import androidx.annotation.Nullable;
import androidx.test.internal.util.Checks;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public final class PackageInfoBuilder {

    @Nullable
    private ApplicationInfo applicationInfo;

    @Nullable
    private String packageName;

    private PackageInfoBuilder() {
    }

    public static PackageInfoBuilder newBuilder() {
        return new PackageInfoBuilder();
    }

    public PackageInfo build() {
        Checks.checkNotNull(this.packageName, "Mandatory field 'packageName' missing.");
        PackageInfo packageInfo = new PackageInfo();
        packageInfo.packageName = this.packageName;
        if (this.applicationInfo == null) {
            this.applicationInfo = ApplicationInfoBuilder.newBuilder().setPackageName(this.packageName).build();
        }
        ApplicationInfo applicationInfo = this.applicationInfo;
        packageInfo.applicationInfo = applicationInfo;
        Checks.checkState(packageInfo.packageName.equals(applicationInfo.packageName), "Field 'packageName' must match field 'applicationInfo.packageName'");
        return packageInfo;
    }

    public PackageInfoBuilder setApplicationInfo(ApplicationInfo applicationInfo) {
        this.applicationInfo = applicationInfo;
        return this;
    }

    public PackageInfoBuilder setPackageName(String str) {
        this.packageName = str;
        return this;
    }
}
