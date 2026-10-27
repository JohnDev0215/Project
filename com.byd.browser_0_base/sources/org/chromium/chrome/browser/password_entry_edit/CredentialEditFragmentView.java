package org.chromium.chrome.browser.password_entry_edit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import defpackage.ie0;
import defpackage.n3;
import defpackage.om3;
import java.util.WeakHashMap;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.password_entry_edit.CredentialEditFragmentView;

/* JADX INFO: loaded from: classes2.dex */
public class CredentialEditFragmentView extends CredentialEntryFragmentViewBase {
    public static final /* synthetic */ int o0 = 0;
    public TextInputLayout j0;
    public TextInputEditText k0;
    public TextInputLayout l0;
    public TextInputEditText m0;
    public Button n0;

    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        D().setTitle(R$string.password_entry_viewer_edit_stored_password_action_title);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0029  */
    @Override // androidx.preference.d, androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.View e0(android.view.LayoutInflater r3, android.view.ViewGroup r4, android.os.Bundle r5) {
        /*
            r2 = this;
            r2.D0()
            java.lang.String r2 = defpackage.kp2.a
            int r5 = r2.hashCode()
            r0 = 1307376552(0x4decfba8, float:4.9698944E8)
            r1 = 0
            if (r5 == r0) goto L1f
            r0 = 1418153572(0x54874e64, float:4.649086E12)
            if (r5 == r0) goto L15
            goto L29
        L15:
            java.lang.String r5 = "8155f_dynasty_def"
            boolean r2 = r2.equals(r5)
            if (r2 == 0) goto L29
            r2 = 1
            goto L2a
        L1f:
            java.lang.String r5 = "8155f_denza_def"
            boolean r2 = r2.equals(r5)
            if (r2 == 0) goto L29
            r2 = r1
            goto L2a
        L29:
            r2 = -1
        L2a:
            if (r2 == 0) goto L2f
            int r2 = org.chromium.chrome.R$layout.credential_edit_view
            goto L31
        L2f:
            int r2 = org.chromium.chrome.R$layout.credential_edit_view_8155f_denza_def
        L31:
            android.view.View r2 = r3.inflate(r2, r4, r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.password_entry_edit.CredentialEditFragmentView.e0(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle):android.view.View");
    }

    @Override // org.chromium.chrome.browser.password_entry_edit.CredentialEntryFragmentViewBase, androidx.preference.d, androidx.fragment.app.Fragment
    public final void o0() {
        this.j0 = (TextInputLayout) this.f12J.findViewById(R$id.username_text_input_layout);
        this.k0 = (TextInputEditText) this.f12J.findViewById(R$id.username);
        final View viewFindViewById = this.f12J.findViewById(R$id.copy_username_button);
        final TextInputEditText textInputEditText = this.k0;
        viewFindViewById.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: t10
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                int i9 = CredentialEditFragmentView.o0;
                WeakHashMap<View, in3> weakHashMap = om3.a;
                TextInputEditText textInputEditText2 = textInputEditText;
                om3.e.i(textInputEditText2, om3.e.e(textInputEditText2), textInputEditText2.getPaddingTop(), viewFindViewById.getWidth(), textInputEditText2.getPaddingBottom());
            }
        });
        this.l0 = (TextInputLayout) this.f12J.findViewById(R$id.password_text_input_layout);
        this.m0 = (TextInputEditText) this.f12J.findViewById(R$id.password);
        final View viewFindViewById2 = this.f12J.findViewById(R$id.password_icons);
        final TextInputEditText textInputEditText2 = this.m0;
        viewFindViewById2.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: t10
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                int i9 = CredentialEditFragmentView.o0;
                WeakHashMap<View, in3> weakHashMap = om3.a;
                TextInputEditText textInputEditText22 = textInputEditText2;
                om3.e.i(textInputEditText22, om3.e.e(textInputEditText22), textInputEditText22.getPaddingTop(), viewFindViewById2.getWidth(), textInputEditText22.getPaddingBottom());
            }
        });
        LinearLayout linearLayout = (LinearLayout) this.f12J.findViewById(R$id.ll_sure_cancel);
        if (n3.Q0()) {
            linearLayout.setLayoutDirection(1);
        } else {
            linearLayout.setLayoutDirection(0);
        }
        this.n0 = (Button) this.f12J.findViewById(R$id.btn_sure);
        this.f12J.findViewById(R$id.btn_cancel).setOnClickListener(new ie0(15, this));
        super.o0();
    }
}
