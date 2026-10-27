package com.android.textclassifier.common.intent;

import android.R;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import androidx.core.app.RemoteActionCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.IconCompat;
import com.android.textclassifier.common.base.TcLog;
import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes.dex */
public final class LabeledIntent {
    private static final TitleChooser DEFAULT_TITLE_CHOOSER = new TitleChooser() { // from class: com.android.textclassifier.common.intent.-$$Lambda$LabeledIntent$Ef5QLBfEhrkX4H2Of8KdcPhmr-g
        @Override // com.android.textclassifier.common.intent.LabeledIntent.TitleChooser
        public final CharSequence chooseTitle(LabeledIntent labeledIntent, ResolveInfo resolveInfo) {
            return LabeledIntent.lambda$static$0(labeledIntent, resolveInfo);
        }
    };
    public final String description;
    public final String descriptionWithAppName;
    public final Intent intent;
    public final int requestCode;
    public final String titleWithEntity;
    public final String titleWithoutEntity;

    public interface TitleChooser {
        CharSequence chooseTitle(LabeledIntent labeledIntent, ResolveInfo resolveInfo);
    }

    static /* synthetic */ CharSequence lambda$static$0(LabeledIntent labeledIntent, ResolveInfo resolveInfo) {
        if (!TextUtils.isEmpty(labeledIntent.titleWithEntity)) {
            return labeledIntent.titleWithEntity;
        }
        return labeledIntent.titleWithoutEntity;
    }

    public LabeledIntent(String str, String str2, String str3, String str4, Intent intent, int i) {
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("titleWithEntity and titleWithoutEntity should not be both null");
        }
        this.titleWithoutEntity = str;
        this.titleWithEntity = str2;
        Preconditions.checkNotNull(str3);
        this.description = str3;
        this.descriptionWithAppName = str4;
        Preconditions.checkNotNull(intent);
        this.intent = intent;
        this.requestCode = i;
    }

    public Result resolve(Context context, TitleChooser titleChooser) {
        ActivityInfo activityInfo;
        PackageManager packageManager = context.getPackageManager();
        boolean z = false;
        ResolveInfo resolveInfoResolveActivity = packageManager.resolveActivity(this.intent, 0);
        IconCompat iconCompatCreateWithResource = null;
        if (resolveInfoResolveActivity == null || (activityInfo = resolveInfoResolveActivity.activityInfo) == null) {
            TcLog.w("LabeledIntent", "resolveInfo or activityInfo is null");
            return null;
        }
        if (!hasPermission(context, activityInfo)) {
            TcLog.d("LabeledIntent", "No permission to access: " + resolveInfoResolveActivity.activityInfo);
            return null;
        }
        ActivityInfo activityInfo2 = resolveInfoResolveActivity.activityInfo;
        String str = activityInfo2.packageName;
        String str2 = activityInfo2.name;
        if (str == null || str2 == null) {
            TcLog.w("LabeledIntent", "packageName or className is null");
            return null;
        }
        Intent intent = new Intent(this.intent);
        if (!"android".equals(str)) {
            intent.setComponent(new ComponentName(str, str2));
            if (resolveInfoResolveActivity.activityInfo.getIconResource() != 0) {
                iconCompatCreateWithResource = createIconFromPackage(context, str, resolveInfoResolveActivity.activityInfo.getIconResource());
                z = true;
            }
        }
        if (iconCompatCreateWithResource == null) {
            iconCompatCreateWithResource = IconCompat.createWithResource(context, R.drawable.ic_menu_more);
        }
        PendingIntent pendingIntentCreatePendingIntent = createPendingIntent(context, intent, this.requestCode);
        if (titleChooser == null) {
            titleChooser = DEFAULT_TITLE_CHOOSER;
        }
        CharSequence charSequenceChooseTitle = titleChooser.chooseTitle(this, resolveInfoResolveActivity);
        if (TextUtils.isEmpty(charSequenceChooseTitle)) {
            TcLog.w("LabeledIntent", "Custom titleChooser return null, fallback to the default titleChooser");
            charSequenceChooseTitle = DEFAULT_TITLE_CHOOSER.chooseTitle(this, resolveInfoResolveActivity);
        }
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat(iconCompatCreateWithResource, charSequenceChooseTitle, resolveDescription(resolveInfoResolveActivity, packageManager), pendingIntentCreatePendingIntent);
        remoteActionCompat.setShouldShowIcon(z);
        return new Result(intent, remoteActionCompat);
    }

    private String resolveDescription(ResolveInfo resolveInfo, PackageManager packageManager) {
        if (!TextUtils.isEmpty(this.descriptionWithAppName)) {
            String applicationName = getApplicationName(resolveInfo, packageManager);
            if (!TextUtils.isEmpty(applicationName)) {
                return String.format(this.descriptionWithAppName, applicationName);
            }
        }
        return this.description;
    }

    private static IconCompat createIconFromPackage(Context context, String str, int i) {
        try {
            return IconCompat.createWithResource(context.createPackageContext(str, 0), i);
        } catch (PackageManager.NameNotFoundException e) {
            TcLog.e("LabeledIntent", "createIconFromPackage: failed to create package context", e);
            return null;
        }
    }

    private static PendingIntent createPendingIntent(Context context, Intent intent, int i) {
        return PendingIntent.getActivity(context, i, intent, 134217728);
    }

    private static String getApplicationName(ResolveInfo resolveInfo, PackageManager packageManager) {
        ApplicationInfo applicationInfo;
        ActivityInfo activityInfo = resolveInfo.activityInfo;
        if (activityInfo == null || "android".equals(activityInfo.packageName) || (applicationInfo = resolveInfo.activityInfo.applicationInfo) == null) {
            return null;
        }
        return packageManager.getApplicationLabel(applicationInfo).toString();
    }

    private static boolean hasPermission(Context context, ActivityInfo activityInfo) {
        if (!activityInfo.exported) {
            return false;
        }
        String str = activityInfo.permission;
        return str == null || ContextCompat.checkSelfPermission(context, str) == 0;
    }

    public static final class Result {
        public final RemoteActionCompat remoteAction;
        public final Intent resolvedIntent;

        public Result(Intent intent, RemoteActionCompat remoteActionCompat) {
            Preconditions.checkNotNull(intent);
            this.resolvedIntent = intent;
            Preconditions.checkNotNull(remoteActionCompat);
            this.remoteAction = remoteActionCompat;
        }
    }
}
