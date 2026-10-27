package com.byd.auto_photo;

import android.content.res.Configuration;
import android.database.ContentObserver;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyCallback;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.core.view.ViewCompat;
import com.byd.util.e;
import com.byd.util.w;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public class VideoPlayActivity extends VideoPlayBaseActivity {
    private View B0;
    private TextView C0;
    private ImageView D0;
    private OTAReceiver E0;
    private TelephonyManager F0;
    private TelephonyCallback H0;
    private PhoneStateListener G0 = new a();
    private ContentObserver I0 = new b(new Handler());

    class a extends PhoneStateListener {
        a() {
        }

        @Override // android.telephony.PhoneStateListener
        public void onCallStateChanged(int i, String str) {
            super.onCallStateChanged(i, str);
            w.c("onCallStateChanged " + i + " " + str);
            VideoPlayActivity.this.u1(i);
        }
    }

    class b extends ContentObserver {
        b(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            super.onChange(z);
            try {
                int i = Settings.System.getInt(VideoPlayActivity.this.getContentResolver(), "navibar_mode_on_screensaver");
                w.c("VideoPlayActivity screen state : " + i);
                if (i == 0) {
                    VideoPlayActivity.this.y0();
                } else if (i == 1) {
                    VideoPlayActivity.this.z0();
                }
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                w.c("navibar_mode_on_screensaver Exception : " + e.getMessage());
            }
        }
    }

    @RequiresApi(api = 31)
    private class c extends TelephonyCallback implements TelephonyCallback.CallStateListener {
        private c() {
        }

        /* synthetic */ c(VideoPlayActivity videoPlayActivity, a aVar) {
            this();
        }

        @Override // android.telephony.TelephonyCallback.CallStateListener
        public void onCallStateChanged(int i) {
            VideoPlayActivity.this.u1(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u1(int i) {
        w.c("phone state change : " + i);
        if (i == 0) {
            z0();
        } else {
            y0();
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private void v1() {
        if (!this.b0) {
            int iB = this.e0.b();
            this.N = iB;
            this.P.j(this.O, iB, this.D, !this.e0.h() ? (byte) 1 : (byte) 0);
            setResult(-1);
        }
        p1();
    }

    private void w1() {
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.seek_player_horizontal_margin);
        ((ViewGroup) findViewById(R.id.layout_tools)).setPadding(dimensionPixelSize, 0, dimensionPixelSize, 0);
    }

    private void x1() {
        int i = getResources().getConfiguration().orientation == 2 ? R.dimen.dp_81 : R.dimen.dp_360;
        com.byd.util.c cVar = this.Y;
        if (cVar == null) {
            return;
        }
        cVar.j(getResources().getDimensionPixelSize(i));
    }

    private void y1() {
        WindowInsetsController windowInsetsController;
        if (Build.VERSION.SDK_INT >= 30 && (windowInsetsController = getWindow().getDecorView().getWindowInsetsController()) != null) {
            windowInsetsController.show(WindowInsets.Type.statusBars());
            windowInsetsController.show(WindowInsets.Type.navigationBars());
            windowInsetsController.hide(WindowInsets.Type.statusBars());
            windowInsetsController.hide(WindowInsets.Type.navigationBars());
        }
    }

    @Override // com.byd.auto_photo.VideoPlayBaseActivity
    protected void F0() {
        super.F0();
        y1();
    }

    @Override // com.byd.theme.ThemeActivity
    protected int H() {
        return 0;
    }

    @Override // com.byd.theme.ThemeActivity
    protected int J() {
        return 0;
    }

    @Override // com.byd.theme.ThemeActivity
    protected int K(boolean z) {
        return b.a.a.a(this) != 1 ? R.style.AppThemeNight : R.style.AppThemeTS;
    }

    @Override // com.byd.auto_photo.VideoPlayBaseActivity
    protected void L0() {
        if (this.Z && this.Y == null) {
            this.Y = new com.byd.util.b(this);
        }
    }

    @Override // com.byd.auto_photo.VideoPlayBaseActivity, com.byd.theme.ThemeActivity
    protected void N() {
        SeekBar seekBar;
        Drawable drawable;
        super.N();
        this.f.setBackground(new ColorDrawable(ViewCompat.MEASURED_STATE_MASK));
        this.B0 = findViewById(R.id.full_title_container);
        this.C0 = (TextView) findViewById(R.id.tv_full_title);
        ImageView imageView = (ImageView) findViewById(R.id.iv_full_close);
        this.D0 = imageView;
        imageView.setOnClickListener(this);
        this.B0.setVisibility(0);
        this.H.setVisibility(8);
        this.J.setProgressDrawable(e.p(this, R.attr.progress_drawable));
        if (b.a.a.a(this) == 1) {
            seekBar = this.J;
            drawable = getDrawable(R.drawable.seek_thumb_drawable_nomal_ui3);
        } else {
            seekBar = this.J;
            drawable = getResources().getDrawable(R.drawable.seek_thumb_drawable_preview_ui3);
        }
        seekBar.setThumb(drawable);
        w1();
    }

    @Override // com.byd.auto_photo.VideoPlayBaseActivity
    protected boolean P0() {
        TelephonyManager telephonyManager = this.F0;
        return (telephonyManager == null || telephonyManager.getCallState() == 0) ? false : true;
    }

    @Override // com.byd.auto_photo.VideoPlayBaseActivity, com.byd.theme.ThemeActivity
    protected void R() {
        S();
        w.e(this, R.string.permission_denied_and_finish);
    }

    @Override // com.byd.auto_photo.VideoPlayBaseActivity, com.byd.theme.ThemeActivity
    protected void S() {
        super.S();
        com.byd.util.c cVar = this.Y;
        if (cVar != null) {
            cVar.i(49);
            x1();
        }
        try {
            if (Build.VERSION.SDK_INT < 31) {
                this.F0.listen(this.G0, 32);
            } else {
                this.H0 = new c(this, null);
                this.F0.registerTelephonyCallback(Executors.newCachedThreadPool(), this.H0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.byd.theme.ThemeActivity
    protected void V(boolean z) {
    }

    @Override // com.byd.auto_photo.VideoPlayBaseActivity, com.byd.theme.ThemeActivity
    protected String[] W() {
        String[] strArrW = super.W();
        String[] strArr = new String[strArrW.length + 1];
        System.arraycopy(strArrW, 0, strArr, 0, strArrW.length);
        strArr[strArrW.length] = "android.permission.READ_PHONE_STATE";
        return strArr;
    }

    @Override // com.byd.auto_photo.VideoPlayBaseActivity
    protected void b1() {
        getContentResolver().registerContentObserver(Settings.System.getUriFor("navibar_mode_on_screensaver"), true, this.I0);
    }

    @Override // com.byd.auto_photo.VideoPlayBaseActivity
    protected void d1() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT < 21) {
            window.addFlags(67108864);
            window.addFlags(134217728);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(this.T);
            window.setStatusBarColor(0);
            window.setNavigationBarColor(0);
        }
    }

    @Override // com.byd.auto_photo.VideoPlayBaseActivity
    protected void e1() {
        super.e1();
        if (this.B0.getVisibility() != 0) {
            this.B0.setVisibility(0);
        }
    }

    @Override // com.byd.auto_photo.VideoPlayBaseActivity
    protected void l1(boolean z) {
        View view;
        int i;
        if (z) {
            view = this.B0;
            i = 0;
        } else {
            view = this.B0;
            i = 4;
        }
        view.setVisibility(i);
    }

    @Override // com.byd.auto_photo.VideoPlayBaseActivity
    protected void n1(String str) {
        super.n1(str);
        this.C0.setText(str);
    }

    @Override // com.byd.auto_photo.VideoPlayBaseActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        v1();
    }

    @Override // com.byd.auto_photo.VideoPlayBaseActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_pre || id == R.id.iv_full_close) {
            v1();
        } else {
            super.onClick(view);
        }
    }

    @Override // com.byd.theme.ThemeActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.t == null) {
            return;
        }
        w1();
        x1();
    }

    @Override // com.byd.auto_photo.VideoPlayBaseActivity, com.byd.auto_photo.SubBaseTaskActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        this.T = 5895;
        OTAReceiver oTAReceiver = new OTAReceiver(this);
        this.E0 = oTAReceiver;
        oTAReceiver.a();
        this.F0 = (TelephonyManager) getSystemService("phone");
        super.onCreate(bundle);
        b.a.i.c cVar = this.e0;
        if (cVar != null) {
            cVar.r(3);
        }
    }

    @Override // com.byd.auto_photo.VideoPlayBaseActivity, com.byd.auto_photo.SubBaseTaskActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        this.E0.b();
        super.onDestroy();
        TelephonyManager telephonyManager = this.F0;
        if (telephonyManager != null) {
            try {
                if (Build.VERSION.SDK_INT >= 31) {
                    telephonyManager.unregisterTelephonyCallback(this.H0);
                } else {
                    telephonyManager.listen(this.G0, 0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.byd.auto_photo.VideoPlayBaseActivity
    protected void s1() {
        getContentResolver().unregisterContentObserver(this.I0);
    }
}
