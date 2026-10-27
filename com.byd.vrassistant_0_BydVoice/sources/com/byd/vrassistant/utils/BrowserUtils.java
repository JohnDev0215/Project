package com.byd.vrassistant.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import com.byd.vrassistant.ContextHolder;
import com.byd.vrassistant.ui.R;

/* JADX INFO: loaded from: classes2.dex */
public class BrowserUtils {
    public static boolean hasBrowserApp(Context context) {
        return new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com")).resolveActivity(context.getPackageManager()) != null;
    }

    public static void openWebPage(Context context, String str) {
        if (hasBrowserApp(context)) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent.setFlags(268435456);
            context.startActivity(intent);
            return;
        }
        Toast.makeText(context, ContextHolder.getApplicationContext().getText(R.string.toast_web_require), 0).show();
    }
}
