package defpackage;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.customtabs.CustomTabBottomBarView;
import org.chromium.chrome.browser.customtabs.CustomTabsShareBroadcastReceiver;

/* JADX INFO: compiled from: CustomButtonParamsImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class o20 implements m20 {
    public final PendingIntent a;
    public final int b;
    public Bitmap c;
    public String d;
    public final boolean e;
    public final boolean f;

    public o20(int i, Bitmap bitmap, String str, PendingIntent pendingIntent, boolean z, boolean z2) {
        this.b = i;
        this.c = bitmap;
        this.d = str;
        this.a = pendingIntent;
        this.e = z;
        this.f = z2;
    }

    public static o20 h(Context context, int i) {
        String string = context.getResources().getString(R$string.share);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, new Intent(context, (Class<?>) CustomTabsShareBroadcastReceiver.class), q11.d(true) | 134217728);
        za3 za3VarA = za3.a(context, R$drawable.ic_share_white_24dp);
        za3VarA.c(bz.b(context, z7.h0(i) ? R$color.default_icon_color_light_tint_list : R$color.default_icon_color_dark_tint_list));
        return new o20(0, za3VarA.getBitmap(), string, broadcast, true, true);
    }

    public static boolean i(Context context, Bitmap bitmap) {
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R$dimen.toolbar_icon_height);
        return bitmap.getHeight() >= dimensionPixelSize && (bitmap.getWidth() / bitmap.getHeight()) * dimensionPixelSize <= dimensionPixelSize * 2;
    }

    public static o20 j(Context context, Bundle bundle, boolean z, boolean z2) {
        boolean z3;
        if (bundle == null) {
            return null;
        }
        if (z2 && !bundle.containsKey("android.support.customtabs.customaction.ID")) {
            return null;
        }
        int iK = q11.k("android.support.customtabs.customaction.ID", 0, bundle);
        Bitmap bitmap = (Bitmap) q11.m(bundle, "android.support.customtabs.customaction.ICON");
        Bitmap bitmap2 = bitmap == null ? null : bitmap;
        if (bitmap2 == null) {
            Log.e("cr_CustomTabs", "Invalid action button: bitmap not present in bundle!");
            return null;
        }
        String strK = k(bundle);
        if (TextUtils.isEmpty(strK)) {
            Log.e("cr_CustomTabs", "Invalid action button: content description not present in bundle!");
            try {
                bundle.remove("android.support.customtabs.customaction.ICON");
            } catch (Throwable unused) {
                Log.e("cr_CustomTabs", "Failed to remove icon extra from the intent");
            }
            bitmap2.recycle();
            return null;
        }
        boolean z4 = iK == 0 || q11.h(bundle, "android.support.customtabs.customaction.SHOW_ON_TOOLBAR", false);
        if (!z4 || i(context, bitmap2)) {
            z3 = z4;
        } else {
            Log.w("cr_CustomTabs", "Button's icon not suitable for toolbar, putting it to bottom bar instead.See: https://developer.android.com/reference/android/support/customtabs/CustomTabsIntent.html#KEY_ICON");
            z3 = false;
        }
        PendingIntent pendingIntent = (PendingIntent) q11.m(bundle, "android.support.customtabs.customaction.PENDING_INTENT");
        if (!z3 || pendingIntent != null) {
            return new o20(iK, bitmap2, strK, pendingIntent, z, z3);
        }
        Log.w("cr_CustomTabs", "Invalid action button on toolbar: pending intent not present in bundle!");
        try {
            bundle.remove("android.support.customtabs.customaction.ICON");
        } catch (Throwable unused2) {
            Log.e("cr_CustomTabs", "Failed to remove icon extra from the intent");
        }
        bitmap2.recycle();
        return null;
    }

    public static String k(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String strP = q11.p(bundle, "android.support.customtabs.customaction.DESCRIPTION");
        if (TextUtils.isEmpty(strP)) {
            return null;
        }
        return strP;
    }

    @Override // defpackage.m20
    public final BitmapDrawable a(Activity activity) {
        return this.e ? new za3(activity, this.c) : new BitmapDrawable(activity.getResources(), this.c);
    }

    @Override // defpackage.m20
    public final void b(Bitmap bitmap, String str) {
        this.c = bitmap;
        this.d = str;
    }

    @Override // defpackage.m20
    public final boolean c() {
        return this.f;
    }

    @Override // defpackage.m20
    public final String d() {
        return this.d;
    }

    @Override // defpackage.m20
    public final PendingIntent e() {
        return this.a;
    }

    @Override // defpackage.m20
    public final ImageButton f(Activity activity, CustomTabBottomBarView customTabBottomBarView, c30 c30Var) {
        ImageButton imageButton = (ImageButton) LayoutInflater.from(activity).inflate(R$layout.custom_tabs_bottombar_item, (ViewGroup) customTabBottomBarView, false);
        imageButton.setId(this.b);
        imageButton.setImageBitmap(this.c);
        imageButton.setContentDescription(this.d);
        if (this.a == null) {
            imageButton.setEnabled(false);
        } else {
            imageButton.setOnClickListener(c30Var);
        }
        imageButton.setOnLongClickListener(new n20());
        return imageButton;
    }

    @Override // defpackage.m20
    public final boolean g(Activity activity) {
        return i(activity, this.c);
    }

    @Override // defpackage.m20
    public final int getId() {
        return this.b;
    }
}
