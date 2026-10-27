package org.chromium.components.browser_ui.site_settings;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import androidx.preference.Preference;
import defpackage.bt3;
import defpackage.kr;
import defpackage.p20;
import defpackage.ve3;
import defpackage.x10;
import java.util.Iterator;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: loaded from: classes2.dex */
public class GroupedWebsitesSettings extends SiteSettingsPreferenceFragment implements Preference.d, p20 {
    public static final /* synthetic */ int k0 = 0;
    public bt3 i0;
    public final ve3 j0 = new ve3(11, this);

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            GroupedWebsitesSettings groupedWebsitesSettings = GroupedWebsitesSettings.this;
            if (groupedWebsitesSettings.D() == null) {
                return;
            }
            Profile profile = ((kr) groupedWebsitesSettings.h0).b;
            Iterator<f> it = groupedWebsitesSettings.i0.f.iterator();
            while (it.hasNext()) {
                d.c(profile, it.next());
            }
            d.a(((kr) groupedWebsitesSettings.h0).b, groupedWebsitesSettings.i0, groupedWebsitesSettings.j0);
        }
    }

    public class b implements View.OnClickListener {
        public final /* synthetic */ Dialog e;

        public b(Dialog dialog) {
            this.e = dialog;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.e.dismiss();
        }
    }

    @Override // androidx.preference.d, androidx.preference.PreferenceManager.c
    public final boolean C(Preference preference) {
        if (preference instanceof WebsiteRowPreference) {
            ((WebsiteRowPreference) preference).b0(this.j);
        }
        return super.C(preference);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0130  */
    @Override // androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void Y() {
        /*
            Method dump skipped, instruction units count: 414
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.browser_ui.site_settings.GroupedWebsitesSettings.Y():void");
    }

    @Override // androidx.preference.Preference.d
    public final boolean r(Preference preference) {
        Dialog dialog = new Dialog(K(), R$style.Dialog_Style);
        View viewInflate = D().getLayoutInflater().inflate(R$layout.clear_reset_dialog, (ViewGroup) null);
        ((TextView) viewInflate.findViewById(R$id.main_message)).setText(R$string.website_reset_confirmation);
        ((TextView) viewInflate.findViewById(R$id.signed_out_text)).setText(R$string.webstorage_clear_data_dialog_sign_out_message);
        ((TextView) viewInflate.findViewById(R$id.offline_text)).setText(R$string.webstorage_clear_data_dialog_offline_message);
        if (((kr) this.h0).c()) {
            ((TextView) viewInflate.findViewById(R$id.ad_personalization_text)).setVisibility(0);
        }
        Button button = (Button) viewInflate.findViewById(R$id.btn_sure);
        Button button2 = (Button) viewInflate.findViewById(R$id.btn_cancel);
        dialog.setContentView(viewInflate);
        Window window = dialog.getWindow();
        window.setGravity(17);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = K().getResources().getDimensionPixelSize(R$dimen.alert_dialog_max_width);
        attributes.height = -2;
        window.setAttributes(attributes);
        dialog.show();
        button.setOnClickListener(new a());
        button2.setOnClickListener(new b(dialog));
        return true;
    }

    @Override // androidx.preference.d, androidx.preference.PreferenceManager.a
    public final void y(Preference preference) {
        if (!(preference instanceof ClearWebsiteStorage)) {
            super.y(preference);
        } else {
            if (this.v.L()) {
                return;
            }
            ClearWebsiteStorageDialog clearWebsiteStorageDialogS0 = ClearWebsiteStorageDialog.S0(preference, new x10(27, this), ((kr) this.h0).c(), true);
            clearWebsiteStorageDialogS0.E0(0, this);
            clearWebsiteStorageDialogS0.M0(this.v, "ClearWebsiteStorageDialog");
        }
    }

    @Override // defpackage.p20
    public final void t() {
    }

    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
    }
}
