package defpackage;

import android.content.DialogInterface;
import org.chromium.components.browser_ui.settings.R$string;
import org.chromium.components.browser_ui.site_settings.ChosenObjectSettings;
import org.chromium.components.browser_ui.site_settings.SingleWebsiteSettings;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class oo implements DialogInterface.OnClickListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ oo(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        int i2 = this.e;
        Object obj = this.f;
        switch (i2) {
            case 0:
                ChosenObjectSettings chosenObjectSettings = (ChosenObjectSettings) obj;
                boolean z = false;
                for (no noVar : chosenObjectSettings.j0) {
                    if (noVar.i) {
                        z = true;
                    } else {
                        noVar.a(((kr) chosenObjectSettings.h0).b);
                    }
                }
                if (z) {
                    nb1.h(chosenObjectSettings.K(), R$string.managed_settings_cannot_be_reset);
                } else {
                    chosenObjectSettings.D().finish();
                }
                chosenObjectSettings.M0();
                break;
            case 1:
                ((SingleWebsiteSettings) obj).r0 = null;
                break;
            default:
                ((Runnable) obj).run();
                break;
        }
    }
}
