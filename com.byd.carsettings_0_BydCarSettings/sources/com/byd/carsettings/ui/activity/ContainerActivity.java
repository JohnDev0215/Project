package com.byd.carsettings.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import b.b.a.h.g;
import b.b.g.n.k.c;
import com.byd.carsettings.R;
import com.byd.mvp.mvp.DialogActivity;
import com.byd.systemsettings.BydSettingsApi;
import com.byd.systemsettings.system.LanguageDialog;

/* JADX INFO: loaded from: classes2.dex */
public class ContainerActivity extends DialogActivity<c> {
    public LanguageDialog u;
    public int v = -1;

    public class a implements LanguageDialog.OnDismissListener {
        public a() {
        }

        @Override // com.byd.systemsettings.system.LanguageDialog.OnDismissListener
        public void onDismiss() {
            ContainerActivity.this.finish();
        }
    }

    public class b implements LanguageDialog.OnDismissListener {
        public b() {
        }

        @Override // com.byd.systemsettings.system.LanguageDialog.OnDismissListener
        public void onDismiss() {
            ContainerActivity.this.finish();
        }
    }

    @Override // com.byd.mvp.mvp.DialogActivity
    public int J(Context context) {
        return (int) getResources().getDimension(R.dimen.dp_544);
    }

    @Override // com.byd.mvp.mvp.DialogActivity
    public int K() {
        return R.layout.activity_container;
    }

    @Override // com.byd.mvp.mvp.DialogActivity
    public int M(Context context) {
        return (int) getResources().getDimension(R.dimen.dp_560);
    }

    @Override // com.byd.mvp.mvp.DialogActivity
    public void N(Context context) {
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: Q, reason: merged with bridge method [inline-methods] */
    public c newP() {
        return new c();
    }

    @Override // com.bydux.api.common.views.window.BydActivity, android.app.Activity
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_byd_activity_close_enter, R.anim.anim_byd_activity_close_exit);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.byd.mvp.mvp.XActivity
    public void k(Context context) {
        String stringExtra = getIntent().getStringExtra("action");
        b.b.m.c.a.a("ContainerActivity", "----initData----action: " + stringExtra);
        if (TextUtils.isEmpty(stringExtra)) {
            return;
        }
        g.k(getSupportFragmentManager());
        stringExtra.hashCode();
        if (stringExtra.equals(BydSettingsApi.SYS_LANGUAGE_SETTINGS)) {
            int iC = ((c) mo7getP()).c();
            int i = this.v;
            if (i >= 0) {
                iC = i;
            }
            LanguageDialog languageDialogNewLanguageDialog = LanguageDialog.newLanguageDialog(iC, true);
            this.u = languageDialogNewLanguageDialog;
            languageDialogNewLanguageDialog.show(getSupportFragmentManager(), LanguageDialog.TAG);
            this.u.setOnDismissListener(new a());
            return;
        }
        if (stringExtra.equals("sys_language_settings_limit")) {
            int iD = ((c) mo7getP()).d();
            int i2 = this.v;
            if (i2 >= 0) {
                iD = i2;
            }
            LanguageDialog languageDialogNewLanguageDialog2 = LanguageDialog.newLanguageDialog(iD);
            this.u = languageDialogNewLanguageDialog2;
            languageDialogNewLanguageDialog2.show(getSupportFragmentManager(), LanguageDialog.TAG);
            this.u.setOnDismissListener(new b());
        }
    }

    @Override // com.byd.mvp.mvp.DialogActivity, com.byd.mvp.mvp.XActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        if (bundle != null) {
            this.v = bundle.getInt("bundle_def_index");
        }
        super.onCreate(bundle);
    }

    @Override // com.byd.mvp.mvp.XActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        LanguageDialog languageDialog = this.u;
        if (languageDialog != null) {
            bundle.putInt("bundle_def_index", languageDialog.checkedIndex);
        }
    }
}
