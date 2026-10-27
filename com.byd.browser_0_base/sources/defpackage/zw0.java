package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;
import defpackage.di1;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescription;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescriptionLayout;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.widget.Toast;

/* JADX INFO: compiled from: ImageDescriptionsDialog.java */
/* JADX INFO: loaded from: classes.dex */
public final class zw0 implements di1.a, RadioGroup.OnCheckedChangeListener {
    public final ww0 e;
    public final bi1 f;
    public final PropertyModel g;
    public final yw0 h;
    public final RadioButtonWithDescription i;
    public final RadioButtonWithDescription j;
    public final CheckBox k;
    public final boolean l;
    public final WebContents p;
    public final Context r;
    public final Profile q = Profile.d();
    public boolean m = true;
    public boolean n = false;
    public int o = 0;

    public zw0(AppCompatActivity appCompatActivity, bi1 bi1Var, uw0 uw0Var, boolean z, WebContents webContents) {
        this.f = bi1Var;
        this.e = uw0Var;
        this.p = webContents;
        this.r = appCompatActivity;
        this.l = z;
        View viewInflate = LayoutInflater.from(appCompatActivity).inflate(R$layout.image_descriptions_dialog, (ViewGroup) null);
        ((RadioButtonWithDescriptionLayout) viewInflate.findViewById(R$id.image_descriptions_dialog_radio_button_group)).setOnCheckedChangeListener(this);
        RadioButtonWithDescription radioButtonWithDescription = (RadioButtonWithDescription) viewInflate.findViewById(R$id.image_descriptions_dialog_radio_button_just_once);
        this.i = radioButtonWithDescription;
        this.j = (RadioButtonWithDescription) viewInflate.findViewById(R$id.image_descriptions_dialog_radio_button_always);
        CheckBox checkBox = (CheckBox) viewInflate.findViewById(R$id.image_descriptions_dialog_check_box);
        this.k = checkBox;
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: xw0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                zw0 zw0Var = this.e;
                if (zw0Var.i.b()) {
                    zw0Var.n = z2;
                } else {
                    zw0Var.m = z2;
                }
            }
        });
        radioButtonWithDescription.setChecked(true);
        if (z) {
            int i = R$string.dont_ask_again;
            boolean z2 = this.n;
            checkBox.setVisibility(0);
            checkBox.setText(i);
            checkBox.setChecked(z2);
        }
        this.h = new yw0(this, webContents);
        PropertyModel.b bVar = new PropertyModel.b(di1.B);
        bVar.e(di1.a, this);
        bVar.d(di1.c, appCompatActivity.getResources(), R$string.image_descriptions_dialog_header);
        bVar.e(di1.h, viewInflate);
        bVar.d(di1.m, appCompatActivity.getResources(), R$string.no_thanks);
        bVar.d(di1.j, appCompatActivity.getResources(), R$string.image_descriptions_dialog_get_descriptions_button);
        bVar.c(di1.u, 1);
        this.g = bVar.a();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [int] */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v7 */
    @Override // di1.a
    public final void c(int i, PropertyModel propertyModel) {
        ?? r3;
        int i2;
        ?? r32;
        Context context = this.r;
        if (i == 0) {
            boolean zB = this.j.b();
            ww0 ww0Var = this.e;
            if (zB) {
                i2 = R$string.image_descriptions_toast_on;
                uw0 uw0Var = (uw0) ww0Var;
                uw0Var.a.getClass();
                Profile profile = this.q;
                oj2.k(profile).e("settings.a11y.enable_accessibility_image_labels_android", true);
                boolean z = this.m;
                uw0Var.a.getClass();
                oj2.k(profile).e("settings.a11y.enable_accessibility_image_labels_only_on_wifi", z);
                boolean z2 = this.m;
                r32 = z2;
                if (z2) {
                    r32 = z2;
                    if (h90.b(context) != 2) {
                        i2 = R$string.image_descriptions_toast_on_no_wifi;
                        r32 = z2;
                    }
                }
            } else if (this.i.b()) {
                ((uw0) ww0Var).a(this.p, this.n);
                i2 = R$string.image_descriptions_toast_just_once;
                r32 = this.n ? (char) 3 : (char) 2;
            } else {
                i2 = -1;
                r32 = -1;
            }
            this.o = 1;
            r3 = r32;
        } else {
            this.o = 2;
            r3 = 4;
            i2 = -1;
        }
        if (i2 != -1) {
            Toast.c(context, i2, 1).e();
        }
        oj2.U(r3, 5, "Accessibility.ImageLabels.Android.DialogOption");
        this.h.destroy();
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public final void onCheckedChanged(RadioGroup radioGroup, int i) {
        int id = this.j.getId();
        CheckBox checkBox = this.k;
        if (i == id) {
            int i2 = R$string.image_descriptions_dialog_option_only_on_wifi;
            boolean z = this.m;
            checkBox.setVisibility(0);
            checkBox.setText(i2);
            checkBox.setChecked(z);
            return;
        }
        if (i == this.i.getId()) {
            if (!this.l) {
                checkBox.setVisibility(8);
                return;
            }
            int i3 = R$string.dont_ask_again;
            boolean z2 = this.n;
            checkBox.setVisibility(0);
            checkBox.setText(i3);
            checkBox.setChecked(z2);
        }
    }

    @Override // di1.a
    public final void b(int i) {
    }
}
