package org.chromium.chrome.browser.share.send_tab_to_self;

import J.N;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import defpackage.bq;
import defpackage.iq2;
import defpackage.jb1;
import defpackage.un1;
import defpackage.z7;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$styleable;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.widget.RoundedCornerImageView;
import org.chromium.components.signin.base.AccountInfo;
import org.chromium.components.signin.identitymanager.IdentityManager;

/* JADX INFO: loaded from: classes2.dex */
public class ManageAccountDevicesLinkView extends LinearLayout {
    public static final /* synthetic */ int f = 0;
    public final boolean e;

    public ManageAccountDevicesLinkView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R$styleable.ManageAccountDevicesLinkView, 0, 0);
        try {
            this.e = typedArrayObtainStyledAttributes.getBoolean(R$styleable.ManageAccountDevicesLinkView_showLink, false);
            typedArrayObtainStyledAttributes.recycle();
            a();
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    private static AccountInfo getSharingAccountInfo() {
        z7 z7VarM = z7.m();
        Profile profileD = Profile.d();
        z7VarM.getClass();
        IdentityManager identityManagerV = z7.v(profileD);
        return (AccountInfo) N.MAwvRw4K(identityManagerV.a, identityManagerV.a(0).getEmail());
    }

    public final void a() {
        if (getVisibility() == 0 && getChildCount() <= 0) {
            LayoutInflater.from(getContext()).inflate(R$layout.send_tab_to_self_manage_devices_link, this);
            AccountInfo sharingAccountInfo = getSharingAccountInfo();
            if (sharingAccountInfo.f != null) {
                RoundedCornerImageView roundedCornerImageView = (RoundedCornerImageView) findViewById(R$id.account_avatar);
                int iRound = Math.round(getResources().getDisplayMetrics().density * 24.0f);
                roundedCornerImageView.setImageBitmap(Bitmap.createScaledBitmap(sharingAccountInfo.f, iRound, iRound, false));
                int i = iRound / 2;
                roundedCornerImageView.d(i, i, i, i);
            }
            TextView textView = (TextView) findViewById(R$id.manage_devices_link);
            String email = sharingAccountInfo.g.getCapabilityByName("accountcapabilities/haytqlldmfya") == 1 || !bq.C.a() ? sharingAccountInfo.getEmail() : sharingAccountInfo.d;
            if (!this.e) {
                textView.setText(email);
            } else {
                textView.setText(iq2.a(getResources().getString(R$string.send_tab_to_self_manage_devices_link, email), new iq2.a(new un1(getContext(), new jb1(0, this)), "<link>", "</link>")));
                textView.setMovementMethod(LinkMovementMethod.getInstance());
            }
        }
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        a();
    }
}
