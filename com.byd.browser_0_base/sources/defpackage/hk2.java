package defpackage;

import J.N;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.share.R$string;
import org.chromium.components.browser_ui.share.ShareHelper;
import org.chromium.components.browser_ui.share.a;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: ShareHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class hk2 extends ShareHelper {

    /* JADX INFO: compiled from: ShareHelper.java */
    public static class a extends ShareHelper.TargetChosenReceiver {
        public final List<Parcelable> e;

        public a(a.b bVar, ArrayList arrayList) {
            super(bVar);
            this.e = arrayList;
        }

        public final Intent a(WindowAndroid windowAndroid, Intent intent, String str) {
            Context context = xz.a;
            Intent intent2 = new Intent(str);
            intent2.setPackage(context.getPackageName());
            q11.a(intent2);
            Activity activity = (Activity) windowAndroid.k().get();
            Intent intentCreateChooser = Intent.createChooser(intent, activity.getString(R$string.share_link_chooser_title), PendingIntent.getBroadcast(activity, 0, intent2, q11.d(true) | 1342177280).getIntentSender());
            List<Parcelable> list = this.e;
            if (list != null) {
                intentCreateChooser.putExtra("android.intent.extra.CHOOSER_CUSTOM_ACTIONS", (Parcelable[]) list.toArray(new Parcelable[0]));
            }
            return intentCreateChooser;
        }
    }

    /* JADX INFO: compiled from: ShareHelper.java */
    public static class b implements a.b {
        public final a.b a;
        public final Profile b;

        public b(Profile profile, a.b bVar) {
            this.a = bVar;
            this.b = profile;
        }

        @Override // org.chromium.components.browser_ui.share.a.b
        public final void a(ComponentName componentName) {
            SharedPreferencesManager.getInstance().o("Chrome.Sharing.LastSharedComponentName", componentName.flattenToString());
            Profile profile = this.b;
            if (profile != null) {
                N.MtTgEuiL(profile, componentName.flattenToString());
            }
            a.b bVar = this.a;
            if (bVar != null) {
                bVar.a(componentName);
            }
        }

        @Override // org.chromium.components.browser_ui.share.a.b
        public final void onCancel() {
            a.b bVar = this.a;
            if (bVar != null) {
                bVar.onCancel();
            }
        }
    }

    public static ComponentName d() {
        String strG = SharedPreferencesManager.getInstance().g("Chrome.Sharing.LastSharedComponentName", null);
        if (strG == null) {
            return null;
        }
        return ComponentName.unflattenFromString(strG);
    }

    public static Intent e() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.addFlags(524288);
        intent.putExtra("android.intent.extra.SUBJECT", "");
        intent.putExtra("android.intent.extra.TEXT", "");
        intent.setType("text/plain");
        return intent;
    }

    public static void f(WindowAndroid windowAndroid, Intent intent, a.b bVar, ArrayList arrayList) {
        a aVar = new a(bVar, arrayList);
        Object obj = ThreadUtils.a;
        Context context = xz.a;
        String packageName = context.getPackageName();
        synchronized (ShareHelper.TargetChosenReceiver.b) {
            try {
                if (ShareHelper.TargetChosenReceiver.c == null) {
                    ShareHelper.TargetChosenReceiver.c = packageName + "/" + ShareHelper.TargetChosenReceiver.class.getName() + "_ACTION";
                }
                ShareHelper.TargetChosenReceiver targetChosenReceiver = ShareHelper.TargetChosenReceiver.d;
                if (targetChosenReceiver != null) {
                    context.unregisterReceiver(targetChosenReceiver);
                    ShareHelper.TargetChosenReceiver targetChosenReceiver2 = ShareHelper.TargetChosenReceiver.d;
                    a.b bVar2 = targetChosenReceiver2.a;
                    if (bVar2 != null) {
                        bVar2.onCancel();
                        targetChosenReceiver2.a = null;
                    }
                }
                ShareHelper.TargetChosenReceiver.d = aVar;
                xz.f(context, aVar, new IntentFilter(ShareHelper.TargetChosenReceiver.c));
            } catch (Throwable th) {
                throw th;
            }
        }
        ShareHelper.a(windowAndroid, aVar.a(windowAndroid, intent, ShareHelper.TargetChosenReceiver.c), ShareHelper.TargetChosenReceiver.d);
    }

    public static void g(org.chromium.components.browser_ui.share.a aVar, ComponentName componentName, Profile profile, boolean z) {
        if (z) {
            SharedPreferencesManager.getInstance().o("Chrome.Sharing.LastSharedComponentName", componentName.flattenToString());
            if (profile != null) {
                N.MtTgEuiL(profile, componentName.flattenToString());
            }
        }
        Intent intentB = ShareHelper.b(aVar);
        intentB.addFlags(50331648);
        sk skVar = bq.a;
        if (N.M09VlOh_("ChromeSharingHubLaunchAdjacent")) {
            intentB.addFlags(4096);
        }
        intentB.setComponent(componentName);
        ShareHelper.a(aVar.a, intentB, null);
    }

    public static void h(WindowAndroid windowAndroid, Profile profile, ComponentName componentName, Uri uri, String str) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.addFlags(524288);
        intent.setType("image/jpeg");
        if (uri != null) {
            intent.putExtra("android.intent.extra.STREAM", uri);
            intent.addFlags(1);
            ContentResolver contentResolver = xz.a.getContentResolver();
            intent.setType(contentResolver.getType(uri));
            intent.setClipData(ClipData.newUri(contentResolver, null, uri));
            if (!TextUtils.isEmpty(str)) {
                intent.putExtra("android.intent.extra.TEXT", str);
            }
        }
        if (componentName == null) {
            ShareHelper.c(0);
            f(windowAndroid, intent, new b(profile, null), null);
        } else {
            intent.setComponent(componentName);
            ShareHelper.a(windowAndroid, intent, null);
        }
    }
}
