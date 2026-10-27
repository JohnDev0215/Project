package com.amazon.music.media.auto.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: InterceptorHeaderUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002R\u001c\u0010\b\u001a\n \u0007*\u0004\u0018\u00010\u00040\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"Lcom/amazon/music/media/auto/util/InterceptorHeaderUtil;", "", "Landroid/content/Context;", "context", "", "getUserAgent", "getApplicationVersion", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "<init>", "()V", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
public final class InterceptorHeaderUtil {
    public static final InterceptorHeaderUtil INSTANCE = new InterceptorHeaderUtil();
    private static final String TAG = InterceptorHeaderUtil.class.getSimpleName();

    private InterceptorHeaderUtil() {
    }

    public final String getUserAgent(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format("Music%s/%s", Arrays.copyOf(new Object[]{"Android", getApplicationVersion(context)}, 2));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        return str;
    }

    public final String getApplicationVersion(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        PackageManager packageManager = context.getPackageManager();
        Intrinsics.checkNotNullExpressionValue(packageManager, "context.packageManager");
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            Intrinsics.checkNotNullExpressionValue(packageInfo, "packageManager.getPackag…o(context.packageName, 0)");
            String str = packageInfo.versionName;
            Intrinsics.checkNotNullExpressionValue(str, "{\n            val info: …nfo.versionName\n        }");
            return str;
        } catch (Exception e) {
            AutoLog autoLog = AutoLog.INSTANCE;
            String TAG2 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
            autoLog.error(TAG2, "Fail to get application version", e);
            return new String();
        }
    }
}
