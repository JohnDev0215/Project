package com.byd.auto_emanual.util;

import android.content.Context;
import android.webkit.WebSettings;
import com.byd.auto_emanual.fragment.ManualFragment;

/* JADX INFO: loaded from: classes.dex */
public class WebSettingsUtil {
    public static void updateAccessFromFileURLs(Context context, WebSettings webSettings, String path, String serverFilePath) {
        if (serverFilePath == null || path == null || webSettings == null) {
            return;
        }
        boolean z = path.startsWith(new StringBuilder().append(ManualFragment.LOCAL_FILE_PREFIX).append(FileUtil.sandBoxPath(context)).toString()) || path.startsWith(serverFilePath);
        webSettings.setAllowUniversalAccessFromFileURLs(z);
        webSettings.setAllowFileAccessFromFileURLs(z);
    }
}
