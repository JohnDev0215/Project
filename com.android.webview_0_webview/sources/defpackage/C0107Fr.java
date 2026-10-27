package defpackage;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import java.util.Locale;

/* JADX INFO: renamed from: Fr, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0107Fr {
    public C0035Br a;
    public Activity b;
    public Dialog c;

    public C0107Fr(Activity activity) {
        this.b = activity;
        AlertDialog.Builder builder = new AlertDialog.Builder(this.b);
        AlertDialog.Builder title = builder.setTitle("Wrong WebView DevTools");
        Locale locale = Locale.US;
        Object[] objArr = new Object[1];
        Activity activity2 = this.b;
        ApplicationInfo applicationInfo = activity2.getApplicationInfo();
        PackageManager packageManager = activity2.getPackageManager();
        CharSequence charSequenceLoadLabel = applicationInfo.loadLabel(packageManager);
        try {
            charSequenceLoadLabel = String.format(Locale.US, "%s %s", charSequenceLoadLabel, packageManager.getPackageInfo(activity2.getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException unused) {
        }
        objArr[0] = charSequenceLoadLabel;
        title.setMessage(String.format(locale, "This app (%s) is not the system's selected WebView provider.", objArr));
        builder.setPositiveButton("Open the current WebView provider", new DialogInterface.OnClickListener(this) { // from class: Cr
            public final C0107Fr s;

            {
                this.s = this;
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                final C0107Fr c0107Fr = this.s;
                PackageInfo packageInfoB = AbstractC0232Mr.b(c0107Fr.b);
                if (packageInfoB == null) {
                    Ts.a("WebViewDevTools", "Could not find a valid WebView implementation", new Object[0]);
                    c0107Fr.a().show();
                    return;
                }
                Intent intent = new Intent("com.android.webview.SHOW_DEV_UI");
                intent.setPackage(packageInfoB.packageName);
                if (!c0107Fr.b.getPackageManager().queryIntentActivities(intent, 0).isEmpty()) {
                    intent.addFlags(32768);
                    intent.addFlags(268435456);
                    c0107Fr.b.startActivity(intent);
                    c0107Fr.b.finishAndRemoveTask();
                    return;
                }
                String str = packageInfoB.packageName;
                AlertDialog.Builder builder2 = new AlertDialog.Builder(c0107Fr.b);
                builder2.setTitle("DevTools Not Found").setMessage(String.format(Locale.US, "DevTools are not available in the current WebView provider selected by the system (%s).\n\nPlease update to a newer version or select a different WebView provider.", str));
                if (Build.VERSION.SDK_INT >= 24) {
                    builder2.setPositiveButton("Change WebView provider", new DialogInterface.OnClickListener(c0107Fr) { // from class: Er
                        public final C0107Fr s;

                        {
                            this.s = c0107Fr;
                        }

                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface2, int i2) {
                            this.s.b.startActivity(new Intent("android.settings.WEBVIEW_SETTINGS"));
                        }
                    });
                }
                builder2.create().show();
            }
        });
        if (Build.VERSION.SDK_INT >= 24) {
            builder.setNeutralButton("Change WebView provider", new DialogInterface.OnClickListener(this) { // from class: Dr
                public final C0107Fr s;

                {
                    this.s = this;
                }

                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    this.s.b.startActivity(new Intent("android.settings.WEBVIEW_SETTINGS"));
                }
            });
        }
        this.c = builder.create();
        C0035Br c0035Br = new C0035Br(activity, AbstractC0371Um.r5, EnumC0017Ar.WARNING);
        c0035Br.a.setText("Warning: different WebView provider - Tap for more info.");
        c0035Br.a.setOnClickListener(new ViewOnClickListenerC1897zr(this.c));
        this.a = c0035Br;
    }

    public final Dialog a() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.b);
        builder.setTitle("No Valid WebView").setMessage("Cannot find a valid WebView provider installed. Please install a valid WebView package. Contact android-webview-dev@chromium.org for help.");
        return builder.create();
    }

    public final boolean b() {
        if (AbstractC0232Mr.b(this.b) != null) {
            return !this.b.getPackageName().equals(r0.packageName);
        }
        Ts.a("WebViewDevTools", "Could not find a valid WebView implementation", new Object[0]);
        a().show();
        return true;
    }
}
