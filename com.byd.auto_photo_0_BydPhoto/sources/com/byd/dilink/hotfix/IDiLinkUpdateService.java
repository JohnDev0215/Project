package com.byd.dilink.hotfix;

import android.os.ParcelFileDescriptor;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkUpdateService {
    int disableElfFix();

    int dpkInstall(String str, ParcelFileDescriptor parcelFileDescriptor);

    List<String> dpkPayload(String str);

    int dpkStatus(String str);

    int dpkUninstall(String str);

    int dpkUninstallWithReason(String str);

    int enableElfFix();

    int hotfixInstall(String str, String str2, ParcelFileDescriptor parcelFileDescriptor);

    int hotfixUninstall(String str);

    int isElfFeatureEnabled();

    int syncSettingsFromFileSystem();
}
