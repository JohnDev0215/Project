package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import defpackage.kp1;
import defpackage.to1;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.notifications.R$id;
import org.chromium.chrome.browser.notifications.R$layout;
import org.chromium.chrome.browser.notifications.R$string;
import org.chromium.components.browser_ui.bottomsheet.j;

/* JADX INFO: compiled from: NotificationPermissionRationaleBottomSheet.java */
/* JADX INFO: loaded from: classes2.dex */
public final class vo1 implements to1.a, ii {
    public final ji e;
    public final Context f;
    public Callback<Integer> g;
    public final uo1 h = new uo1(this);
    public View i;
    public boolean j;

    public vo1(AppCompatActivity appCompatActivity, j jVar) {
        this.e = jVar;
        this.f = appCompatActivity;
    }

    @Override // to1.a
    public final void b(ko0 ko0Var) {
        if (this.i == null) {
            View viewInflate = LayoutInflater.from(this.f).inflate(R$layout.notification_permission_rationale_bottom_sheet, (ViewGroup) null);
            this.i = viewInflate;
            viewInflate.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            Button button = (Button) this.i.findViewById(R$id.notification_permission_rationale_positive_button);
            Button button2 = (Button) this.i.findViewById(R$id.notification_permission_rationale_negative_button);
            button.setOnClickListener(new gs(3, this));
            button2.setOnClickListener(new ie0(13, this));
        }
        this.g = ko0Var;
        ji jiVar = this.e;
        jiVar.g(this.h);
        jiVar.d(this, true);
    }

    public final void c(int i, int i2) {
        if (this.g == null) {
            return;
        }
        kp1.a.a.getClass();
        oj2.U(i2, 12, "Mobile.SystemNotification.Permission.RationaleResult");
        this.g.onResult(Integer.valueOf(i));
        this.g = null;
    }

    @Override // defpackage.ii
    public final View d() {
        return this.i;
    }

    @Override // defpackage.ii
    public final void destroy() {
        if (this.j) {
            c(1, 9);
        } else {
            c(2, 11);
        }
        this.e.e(this.h);
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
        return 0;
    }

    @Override // defpackage.ii
    public final int i() {
        return R$string.notification_permission_rationale_closed_description;
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
    public final float n() {
        return -2.0f;
    }

    @Override // defpackage.ii
    public final int p() {
        return R$string.notification_permission_rationale_content_description;
    }

    @Override // defpackage.ii
    public final int r() {
        return R$string.notification_permission_rationale_opened_full;
    }

    @Override // defpackage.ii
    public final float s() {
        return -1.0f;
    }

    @Override // defpackage.ii
    public final boolean u() {
        return false;
    }
}
