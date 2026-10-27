package defpackage;

import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import defpackage.iq2;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.components.browser_ui.bottomsheet.f;
import org.chromium.components.browser_ui.settings.SettingsLauncher;

/* JADX INFO: compiled from: PrivacySandboxBottomSheetNotice.java */
/* JADX INFO: loaded from: classes2.dex */
public final class x42 implements ii {
    public final ji e;
    public final w42 f;
    public final Context g;
    public final SettingsLauncher h;
    public boolean i = false;
    public final View j;

    public x42(AppCompatActivity appCompatActivity, f fVar, n3 n3Var) {
        this.e = fVar;
        this.g = appCompatActivity;
        this.h = n3Var;
        View viewInflate = LayoutInflater.from(appCompatActivity).inflate(R$layout.privacy_sandbox_notice_bottom_sheet, (ViewGroup) null);
        this.j = viewInflate;
        this.f = new w42(this);
        TextView textView = (TextView) viewInflate.findViewById(R$id.privacy_sandbox_notice_sheet_description);
        textView.setText(iq2.a(appCompatActivity.getString(R$string.privacy_sandbox_notice_sheet_description), new iq2.a(new un1(appCompatActivity, new n62(this, 11, appCompatActivity)), "<link>", "</link>")));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        viewInflate.findViewById(R$id.ack_button).setOnClickListener(new by1(18, this));
        viewInflate.findViewById(R$id.settings_button).setOnClickListener(new vt(19, this));
    }

    @Override // defpackage.ii
    public final View d() {
        return this.j;
    }

    @Override // defpackage.ii
    public final void destroy() {
        this.e.e(this.f);
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
        return R$string.privacy_sandbox_notice_sheet_opened_half;
    }

    @Override // defpackage.ii
    public final int i() {
        return R$string.privacy_sandbox_notice_sheet_closed_description;
    }

    @Override // defpackage.ii
    public final int j() {
        return -2;
    }

    @Override // defpackage.ii
    public final boolean k() {
        return true;
    }

    @Override // defpackage.ii
    public final int m() {
        return 0;
    }

    @Override // defpackage.ii
    public final int p() {
        return R$string.privacy_sandbox_notice_sheet_title;
    }

    @Override // defpackage.ii
    public final int r() {
        return R$string.privacy_sandbox_notice_sheet_opened_full;
    }

    @Override // defpackage.ii
    public final float s() {
        return -1.0f;
    }

    @Override // defpackage.ii
    public final boolean u() {
        return true;
    }
}
