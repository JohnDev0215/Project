package com.byd.carsettings.ui.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import b.b.g.m.d.b;
import b.b.g.o.i.c;
import b.b.g.p.d;
import b.b.g.q.f.b.h;
import b.b.g.q.f.b.u;
import b.b.m.e.a;
import com.byd.base.view.dialog.BaseBydDialog;
import com.byd.carsettings.R;
import com.byd.carsettings.sound.model.Manufactor;
import com.byd.systemsettings.security.CredentialListPager;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class CustomViewDialog extends BaseBydDialog {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2973e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Serializable f2974f;
    public String g;
    public String h;
    public a i;

    public static CustomViewDialog y(int i) {
        CustomViewDialog customViewDialog = new CustomViewDialog();
        Bundle bundle = new Bundle();
        bundle.putInt("custom_type", i);
        customViewDialog.setArguments(bundle);
        return customViewDialog;
    }

    public static CustomViewDialog z(int i, String str, String str2) {
        CustomViewDialog customViewDialog = new CustomViewDialog();
        Bundle bundle = new Bundle();
        bundle.putInt("custom_type", i);
        bundle.putString("bundle_arg_1", str);
        bundle.putString("bundle_arg_2", str2);
        customViewDialog.setArguments(bundle);
        return customViewDialog;
    }

    @Override // com.byd.base.view.dialog.BaseBydDialog
    public int getBaseDialogCommonAnim() {
        if (this.f2973e == 2) {
            return -1;
        }
        return super.getBaseDialogCommonAnim();
    }

    @Override // com.byd.base.view.dialog.BaseBydDialog, com.byd.base.view.dialog.LeakDialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        this.f2973e = arguments.getInt("custom_type");
        this.f2974f = arguments.getSerializable("bundle_data");
        this.g = arguments.getString("bundle_arg_1");
        this.h = arguments.getString("bundle_arg_2");
    }

    @Override // com.byd.base.view.dialog.BaseBydDialog, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        a aVar = this.i;
        if (aVar != null) {
            aVar.onDestroy();
        }
    }

    @Override // com.byd.base.view.dialog.BaseBydDialog
    public View t(Bundle bundle) {
        View settingItemsRecoveryView;
        Manufactor manufactor;
        a aVar;
        int i = this.f2973e;
        a aVar2 = null;
        aVar2 = null;
        aVar2 = null;
        if (i == 2) {
            settingItemsRecoveryView = new SettingItemsRecoveryView(getContext(), this);
        } else if (i == 3) {
            AppDetailStorageView appDetailStorageView = new AppDetailStorageView(getContext(), this);
            appDetailStorageView.setData(this.g, this.h);
            settingItemsRecoveryView = appDetailStorageView;
        } else {
            if (i == 4) {
                h hVar = new h(getContext(), this);
                int dimension = (int) getResources().getDimension(R.dimen.dp_560);
                int dimension2 = (int) getResources().getDimension(R.dimen.dp_312);
                List<b> listC = d.g.a().C(getContext());
                if (listC != null && listC.size() >= 2) {
                    dimension2 = (int) getResources().getDimension(R.dimen.dp_380);
                }
                hVar.getRootView().setLayoutParams(new LinearLayout.LayoutParams(dimension, dimension2));
                aVar = hVar;
            } else if (i == 5) {
                x(false);
                a aVarI = c.i(getContext(), this);
                aVarI.loadData();
                aVarI.getRootView().setLayoutParams(new LinearLayout.LayoutParams((int) getResources().getDimension(R.dimen.dp_560), (int) getResources().getDimension(R.dimen.dp_544)));
                aVar = aVarI;
            } else if (i == 6) {
                x(false);
                a aVarD = c.d(getContext(), this);
                int dimension3 = (int) getResources().getDimension(R.dimen.dp_560);
                b.b.g.o.h.a aVarA = b.b.g.o.d.a();
                if (aVarA != null && ((manufactor = aVarA.f1342a) == Manufactor.BS || manufactor == Manufactor.NON_BRAND)) {
                    dimension3 = (int) getResources().getDimension(R.dimen.dp_640);
                }
                aVarD.getRootView().setLayoutParams(new LinearLayout.LayoutParams(dimension3, (int) getResources().getDimension(R.dimen.dp_544)));
                aVar = aVarD;
            } else if (i == 7) {
                CredentialListPager credentialListPager = new CredentialListPager(getContext(), this);
                credentialListPager.getRootView().setLayoutParams(new LinearLayout.LayoutParams((int) getResources().getDimension(R.dimen.dp_560), (int) getResources().getDimension(R.dimen.dp_544)));
                aVar = credentialListPager;
            } else if (i == 8) {
                b.b.g.q.f.b.d dVar = new b.b.g.q.f.b.d(getContext(), this);
                dVar.p(this.g);
                dVar.getRootView().setLayoutParams(new LinearLayout.LayoutParams((int) getResources().getDimension(R.dimen.dp_560), (int) getResources().getDimension(R.dimen.dp_544)));
                aVar = dVar;
            } else if (i == 9) {
                u uVar = new u(getContext(), this);
                uVar.getRootView().setLayoutParams(new LinearLayout.LayoutParams((int) getResources().getDimension(R.dimen.dp_560), -2));
                aVar = uVar;
            } else {
                settingItemsRecoveryView = null;
            }
            aVar2 = aVar;
            settingItemsRecoveryView = null;
        }
        if (aVar2 == null) {
            return settingItemsRecoveryView;
        }
        this.i = aVar2;
        View rootView = aVar2.getRootView();
        setWindowTitle(getContext(), aVar2.getClass().getName());
        return rootView;
    }
}
