package defpackage;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.RestrictionsManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import defpackage.g50;
import java.util.ArrayList;
import java.util.Locale;
import org.chromium.base.SysUtils;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.document.ChromeLauncherActivity;

/* JADX INFO: compiled from: MediaViewerUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class oe1 {
    public static Intent a(Uri uri, String str, String str2, String str3) {
        Intent intent = new Intent("android.intent.action.VIEW");
        String strNormalizeMimeType = Intent.normalizeMimeType(str);
        if (TextUtils.isEmpty(strNormalizeMimeType)) {
            intent.setData(uri);
        } else {
            intent.setDataAndType(uri, strNormalizeMimeType);
        }
        intent.addFlags(1);
        intent.addFlags(2);
        intent.addFlags(268435456);
        if (str2 != null) {
            intent.putExtra("android.intent.extra.ORIGINATING_URI", Uri.parse(str2));
        }
        if (str3 != null) {
            intent.putExtra("android.intent.extra.REFERRER", Uri.parse(str3));
        }
        return intent;
    }

    public static Intent b(Uri uri, Uri uri2, String str, boolean z, Context context) {
        Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(context.getResources(), R$drawable.ic_arrow_back_white_24dp);
        g50.a aVar = new g50.a();
        aVar.b.a = -16777216;
        aVar.a.putExtra("android.support.customtabs.extra.CLOSE_BUTTON_ICON", bitmapDecodeResource);
        aVar.d();
        aVar.b(z7.I(context) ? 2 : 1);
        boolean zEquals = false;
        if (z && !uri2.getScheme().equals("file")) {
            Intent intentCreateChooser = Intent.createChooser(a(uri2, str, null, null), null);
            intentCreateChooser.addFlags(268435456);
            String string = context.getString(R$string.download_manager_open_with);
            PendingIntent activity = PendingIntent.getActivity(context, 0, intentCreateChooser, q11.d(true) | 268435456);
            if (aVar.c == null) {
                aVar.c = new ArrayList<>();
            }
            Bundle bundle = new Bundle();
            bundle.putString("android.support.customtabs.customaction.MENU_ITEM_TITLE", string);
            bundle.putParcelable("android.support.customtabs.customaction.PENDING_INTENT", activity);
            aVar.c.add(bundle);
        }
        if (!TextUtils.isEmpty(str)) {
            String[] strArrSplit = str.toLowerCase(Locale.getDefault()).split("/");
            if (strArrSplit.length == 2) {
                zEquals = "image".equals(strArrSplit[0]);
            }
        }
        int color = context.getColor(zEquals ? R$color.image_viewer_bg : R$color.media_viewer_bg);
        g50 g50VarA = aVar.a();
        String packageName = context.getPackageName();
        Intent intent = g50VarA.a;
        intent.setPackage(packageName);
        intent.setData(uri2);
        intent.putExtra("org.chromium.chrome.browser.customtabs.EXTRA_UI_TYPE", 1);
        intent.putExtra("org.chromium.chrome.browser.customtabs.MEDIA_VIEWER_URL", uri.toString());
        intent.putExtra("org.chromium.chrome.browser.customtabs.EXTRA_ENABLE_EMBEDDED_MEDIA_EXPERIENCE", true);
        intent.putExtra("org.chromium.chrome.browser.customtabs.EXTRA_INITIAL_BACKGROUND_COLOR", color);
        intent.putExtra("android.support.customtabs.extra.TOOLBAR_COLOR", color);
        intent.putExtra("com.android.browser.application_id", context.getPackageName());
        q11.a(intent);
        intent.addFlags(268435456);
        intent.setClass(context, ChromeLauncherActivity.class);
        return intent;
    }

    public static boolean c() {
        if (SysUtils.isLowEndDevice()) {
            return true;
        }
        RestrictionsManager restrictionsManager = (RestrictionsManager) xz.a.getSystemService("restrictions");
        return restrictionsManager.hasRestrictionsProvider() || !restrictionsManager.getApplicationRestrictions().isEmpty();
    }
}
