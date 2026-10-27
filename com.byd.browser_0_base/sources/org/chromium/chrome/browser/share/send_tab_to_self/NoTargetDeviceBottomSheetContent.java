package org.chromium.chrome.browser.share.send_tab_to_self;

import J.N;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import defpackage.bq;
import defpackage.ii;
import defpackage.n3;
import defpackage.sk;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;

/* JADX INFO: loaded from: classes2.dex */
public class NoTargetDeviceBottomSheetContent implements ii {
    public final ViewGroup e;

    public NoTargetDeviceBottomSheetContent(Context context) {
        sk skVar = bq.a;
        boolean zM09VlOh_ = N.M09VlOh_("SendTabToSelfSigninPromo");
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R$layout.send_tab_to_self_feature_unavailable_prompt, (ViewGroup) null);
        this.e = viewGroup;
        if (zM09VlOh_) {
            ((TextView) viewGroup.findViewById(R$id.empty_state_label)).setText(R$string.send_tab_to_self_android_no_target_device_label);
            viewGroup.findViewById(R$id.manage_account_devices_link).setVisibility(0);
        }
        n3.X0("SharingHubAndroid.SendTabToSelf.NoTargetDevices");
    }

    @Override // defpackage.ii
    public final View d() {
        return this.e;
    }

    @Override // defpackage.ii
    public final int e() {
        return 0;
    }

    @Override // defpackage.ii
    public final View g() {
        return null;
    }

    @Override // defpackage.ii
    public final int h() {
        return R$string.send_tab_to_self_sheet_half_height;
    }

    @Override // defpackage.ii
    public final int i() {
        return R$string.send_tab_to_self_sheet_closed;
    }

    @Override // defpackage.ii
    public final int j() {
        return -2;
    }

    @Override // defpackage.ii
    public final int m() {
        return 0;
    }

    @Override // defpackage.ii
    public final int p() {
        return R$string.send_tab_to_self_content_description;
    }

    @Override // defpackage.ii
    public final int r() {
        return R$string.send_tab_to_self_sheet_full_height;
    }

    @Override // defpackage.ii
    public final float s() {
        return -1.0f;
    }

    @Override // defpackage.ii
    public final boolean u() {
        return true;
    }

    @Override // defpackage.ii
    public final void destroy() {
    }
}
