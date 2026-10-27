package org.chromium.chrome.browser.site_settings;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.NotificationChannel;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import defpackage.ku2;
import defpackage.n3;
import defpackage.oj2;
import defpackage.q11;
import defpackage.so1;
import defpackage.sp;
import defpackage.uv;
import defpackage.xn2;
import defpackage.xz;
import defpackage.yn2;
import defpackage.zf2;
import java.util.Collection;
import java.util.Iterator;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.about_settings.AboutChromeSettings;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.settings.SettingsActivity;
import org.chromium.components.browser_ui.site_settings.AllSiteSettings;
import org.chromium.components.browser_ui.site_settings.f;
import org.chromium.components.browser_ui.site_settings.g;

/* JADX INFO: loaded from: classes2.dex */
public class ManageSpaceActivity extends AppCompatActivity implements View.OnClickListener {
    public static boolean K;
    public TextView D;
    public TextView E;
    public Button F;
    public Button G;
    public Button H;
    public AlertDialog I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public boolean f77J;

    public class a extends n3 {
        public a() {
        }

        @Override // defpackage.n3, defpackage.nj
        public final void K() {
            ManageSpaceActivity manageSpaceActivity = ManageSpaceActivity.this;
            manageSpaceActivity.f77J = true;
            manageSpaceActivity.G.setEnabled(true);
            manageSpaceActivity.F.setEnabled(true);
            n3.X0("Android.ManageSpace");
            Profile profileD = Profile.d();
            new g(profileD, false).a(yn2.c(profileD, 22), manageSpaceActivity.new d());
        }

        @Override // defpackage.n3, defpackage.nj
        public final void q(Exception exc) {
            ManageSpaceActivity manageSpaceActivity = ManageSpaceActivity.this;
            TextView textView = manageSpaceActivity.E;
            int i = R$string.storage_management_startup_failure;
            textView.setText(i);
            manageSpaceActivity.D.setText(i);
        }
    }

    public class b implements DialogInterface.OnClickListener {
        public b() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public final void onClick(DialogInterface dialogInterface, int i) {
            ManageSpaceActivity manageSpaceActivity = ManageSpaceActivity.this;
            manageSpaceActivity.I = null;
            oj2.U(0, 3, "Android.ManageSpace.ActionTaken");
            TextView textView = manageSpaceActivity.E;
            int i2 = R$string.storage_management_computing_size;
            textView.setText(i2);
            manageSpaceActivity.D.setText(i2);
            manageSpaceActivity.F.setEnabled(false);
            manageSpaceActivity.G.setEnabled(false);
            e eVar = manageSpaceActivity.new e();
            Profile profileD = Profile.d();
            eVar.f = SystemClock.elapsedRealtime();
            new g(profileD, true).a(yn2.c(profileD, 22), eVar);
        }
    }

    public class c implements DialogInterface.OnClickListener {
        public final /* synthetic */ ActivityManager e;

        public c(ActivityManager activityManager) {
            this.e = activityManager;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public final void onClick(DialogInterface dialogInterface, int i) {
            if (ManageSpaceActivity.this.f77J) {
                oj2.U(2, 3, "Android.ManageSpace.ActionTaken");
            }
            SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
            sharedPreferencesManager.removeKey("org.chromium.chrome.browser.searchwidget.SEARCH_ENGINE_SHORTNAME");
            sharedPreferencesManager.removeKey("org.chromium.chrome.browser.searchwidget.SEARCH_ENGINE_URL");
            sharedPreferencesManager.removeKey("org.chromium.chrome.browser.searchwidget.IS_VOICE_SEARCH_AVAILABLE");
            sharedPreferencesManager.removeKey("org.chromium.chrome.browser.searchwidget.IS_GOOGLE_LENS_AVAILABLE");
            sharedPreferencesManager.removeKey("org.chromium.chrome.browser.searchwidget.IS_INCOGNITO_AVAILABLE");
            zf2.b();
            so1 so1Var = xn2.a.a.a;
            Iterator<NotificationChannel> it = so1Var.b().iterator();
            while (it.hasNext()) {
                String id = it.next().getId();
                if (xn2.e(id)) {
                    so1Var.a(id);
                }
            }
            this.e.clearApplicationUserData();
        }
    }

    public class d implements g.c {
        public d() {
        }

        @Override // org.chromium.components.browser_ui.site_settings.g.c
        public final void a(Collection<f> collection) {
            long j = 0;
            long j2 = 0;
            for (f fVar : collection) {
                j += fVar.j();
                org.chromium.components.browser_ui.site_settings.b bVar = fVar.i;
                if (bVar != null && bVar.g) {
                    j2 = fVar.j() + j2;
                }
            }
            ManageSpaceActivity.D1(ManageSpaceActivity.this, j, j - j2);
        }
    }

    public class e implements g.c, f.a {
        public int e;
        public long f;

        public e() {
        }

        @Override // org.chromium.components.browser_ui.site_settings.g.c
        public final void a(Collection<f> collection) {
            long j = 0;
            for (f fVar : collection) {
                org.chromium.components.browser_ui.site_settings.b bVar = fVar.i;
                if (bVar == null || !bVar.g) {
                    this.e++;
                    fVar.a(Profile.d(), this);
                } else {
                    j += fVar.j();
                }
            }
            if (this.e == 0) {
                l();
            }
            ManageSpaceActivity.D1(ManageSpaceActivity.this, j, 0L);
        }

        @Override // org.chromium.components.browser_ui.site_settings.f.a
        public final void l() {
            int i = this.e - 1;
            this.e = i;
            if (i <= 0) {
                oj2.b0(SystemClock.elapsedRealtime() - this.f, "Android.ManageSpace.ClearUnimportantTime");
                ManageSpaceActivity manageSpaceActivity = ManageSpaceActivity.this;
                manageSpaceActivity.F.setEnabled(true);
                manageSpaceActivity.G.setEnabled(true);
            }
        }
    }

    public static void D1(ManageSpaceActivity manageSpaceActivity, long j, long j2) {
        manageSpaceActivity.getClass();
        oj2.R((int) (j / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED), "Android.ManageSpace.TotalDiskUsageMB");
        oj2.R((int) (j2 / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED), "Android.ManageSpace.UnimportantDiskUsageMB");
        manageSpaceActivity.E.setText(Formatter.formatFileSize(manageSpaceActivity, j));
        manageSpaceActivity.D.setText(Formatter.formatFileSize(manageSpaceActivity, j2));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.F) {
            if (this.I == null) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.f(R$string.ok, new b());
                builder.e(R$string.cancel, null);
                builder.i(R$string.storage_clear_site_storage_title);
                builder.b(R$string.storage_management_clear_unimportant_dialog_text);
                this.I = builder.a();
            }
            this.I.show();
            return;
        }
        if (view != this.G) {
            if (view == this.H) {
                ActivityManager activityManager = (ActivityManager) getSystemService("activity");
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.f(R$string.ok, new c(activityManager));
                builder2.e(R$string.cancel, null);
                builder2.i(R$string.storage_management_reset_app_dialog_title);
                builder2.b(R$string.storage_management_reset_app_dialog_text);
                builder2.a().show();
                return;
            }
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("category", yn2.m(22));
        bundle.putString("title", getString(R$string.website_settings_storage));
        oj2.U(1, 3, "Android.ManageSpace.ActionTaken");
        String name = AllSiteSettings.class.getName();
        Intent intent = new Intent();
        intent.setClass(this, SettingsActivity.class);
        intent.putExtra("show_fragment", name);
        intent.putExtra("show_fragment_args", bundle);
        ComponentName componentName = q11.a;
        try {
            startActivity(intent, null);
        } catch (ActivityNotFoundException unused) {
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    @SuppressLint({"ApplySharedPref", "CommitPrefEdits"})
    public final void onCreate(Bundle bundle) {
        if (!K) {
            K = true;
            try {
                if (getPackageManager().getActivityInfo(getComponentName(), 0).exported) {
                    throw new IllegalStateException("ManageSpaceActivity must not be exported.");
                }
            } catch (PackageManager.NameNotFoundException e2) {
                throw new uv(e2);
            }
        }
        setContentView(R$layout.manage_space_activity);
        Resources resources = getResources();
        setTitle(String.format(resources.getString(R$string.storage_management_activity_label), resources.getString(R$string.app_name)));
        TextView textView = (TextView) findViewById(R$id.site_data_storage_size_text);
        this.E = textView;
        int i = R$string.storage_management_computing_size;
        textView.setText(i);
        TextView textView2 = (TextView) findViewById(R$id.unimportant_site_data_storage_size_text);
        this.D = textView2;
        textView2.setText(i);
        this.G = (Button) findViewById(R$id.manage_site_data_storage);
        this.F = (Button) findViewById(R$id.clear_unimportant_site_data_storage);
        this.G.setEnabled(false);
        this.F.setEnabled(false);
        this.G.setOnClickListener(this);
        this.F.setOnClickListener(this);
        Button button = (Button) findViewById(R$id.clear_all_data);
        this.H = button;
        button.setOnClickListener(this);
        super.onCreate(bundle);
        a aVar = new a();
        int i2 = AboutChromeSettings.h0;
        if (TextUtils.equals(SharedPreferencesManager.getInstance().g("ManagedSpace.FailedBuildVersion", null), "4.1.1.0")) {
            aVar.q(null);
            return;
        }
        SharedPreferencesManager.getInstance().a.getClass();
        SharedPreferences.Editor editorEdit = xz.a.a.edit();
        editorEdit.putString("ManagedSpace.FailedBuildVersion", "4.1.1.0");
        ku2 ku2VarC = ku2.C();
        try {
            editorEdit.commit();
            ku2VarC.close();
            try {
                sp.a().c(aVar);
                sp.a().b(true, aVar);
            } catch (Exception e3) {
                Log.e("cr_ManageSpaceActivity", "Unable to load native library.", e3);
                TextView textView3 = this.E;
                int i3 = R$string.storage_management_startup_failure;
                textView3.setText(i3);
                this.D.setText(i3);
            }
        } catch (Throwable th) {
            try {
                ku2VarC.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onResume() {
        super.onResume();
        if (this.f77J) {
            Profile profileD = Profile.d();
            new g(profileD, false).a(yn2.c(profileD, 22), new d());
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onStop() {
        super.onStop();
        SharedPreferencesManager.getInstance().o("ManagedSpace.FailedBuildVersion", null);
    }
}
