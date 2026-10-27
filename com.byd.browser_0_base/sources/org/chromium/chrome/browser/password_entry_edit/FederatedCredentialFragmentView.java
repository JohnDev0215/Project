package org.chromium.chrome.browser.password_entry_edit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.material.tabs.TabLayout;
import java.util.concurrent.atomic.AtomicReference;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.password_entry_edit.FederatedCredentialFragmentView;
import org.chromium.ui.widget.ChromeImageButton;

/* JADX INFO: loaded from: classes2.dex */
public class FederatedCredentialFragmentView extends CredentialEntryFragmentViewBase {
    public static final /* synthetic */ int l0 = 0;
    public ChromeImageButton j0;
    public TextView k0;

    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        D().setTitle(R$string.password_entry_viewer_title);
    }

    @Override // androidx.preference.d, androidx.fragment.app.Fragment
    public final View e0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        D0();
        return layoutInflater.inflate(R$layout.federated_credential_view, viewGroup, false);
    }

    @Override // androidx.preference.d, androidx.fragment.app.Fragment
    public final void q0(View view, Bundle bundle) {
        this.k0 = (TextView) this.f12J.findViewById(R$id.username);
        this.j0 = (ChromeImageButton) this.f12J.findViewById(R$id.copy_username_button);
        final View viewFindViewById = this.f12J.findViewById(R$id.username_layout);
        final TextView textView = (TextView) this.f12J.findViewById(R$id.username_label);
        final int i = 1;
        viewFindViewById.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: fv0
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view2, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                int i10 = i;
                View view3 = textView;
                Object obj = viewFindViewById;
                Object obj2 = this;
                switch (i10) {
                    case 0:
                        ((gv0) obj2).getClass();
                        gv0.O((cw) obj, view2, (TabLayout) view3, new AtomicReference());
                        break;
                    default:
                        FederatedCredentialFragmentView federatedCredentialFragmentView = (FederatedCredentialFragmentView) obj2;
                        View view4 = (View) obj;
                        TextView textView2 = (TextView) view3;
                        int i11 = FederatedCredentialFragmentView.l0;
                        federatedCredentialFragmentView.getClass();
                        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view4.getLayoutParams();
                        int dimensionPixelSize = federatedCredentialFragmentView.N().getDimensionPixelSize(R$dimen.federated_view_username_margin_bottom);
                        if (federatedCredentialFragmentView.j0.getHeight() < view4.getHeight()) {
                            marginLayoutParams.bottomMargin = dimensionPixelSize;
                        } else {
                            marginLayoutParams.bottomMargin = dimensionPixelSize - ((federatedCredentialFragmentView.j0.getHeight() - federatedCredentialFragmentView.k0.getHeight()) - textView2.getHeight());
                        }
                        view4.setLayoutParams(marginLayoutParams);
                        break;
                }
            }
        });
    }
}
