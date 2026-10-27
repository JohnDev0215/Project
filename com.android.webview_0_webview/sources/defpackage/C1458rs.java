package defpackage;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;

/* JADX INFO: renamed from: rs, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1458rs {
    public static Bundle a(int i) {
        ActivityOptions activityOptionsMakeBasic = ActivityOptions.makeBasic();
        activityOptionsMakeBasic.setLaunchDisplayId(i);
        return activityOptionsMakeBasic.toBundle();
    }

    public static void b(TextView textView) {
        textView.setTextClassifier(TextClassifier.NO_OP);
    }

    public static void c(Intent intent, String str) {
        intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
        intent.putExtra("android.provider.extra.APP_PACKAGE", str);
    }
}
