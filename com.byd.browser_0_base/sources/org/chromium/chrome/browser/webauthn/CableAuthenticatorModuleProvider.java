package org.chromium.chrome.browser.webauthn;

import J.N;
import android.app.KeyguardManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat$Builder;
import androidx.fragment.app.Fragment;
import defpackage.hi1;
import defpackage.ii1;
import defpackage.im2;
import defpackage.l5;
import defpackage.n3;
import defpackage.p5;
import defpackage.qq;
import defpackage.ro1;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.webauth.authenticator.CableAuthenticatorActivity;

/* JADX INFO: loaded from: classes2.dex */
public class CableAuthenticatorModuleProvider extends Fragment implements View.OnClickListener {
    public static final /* synthetic */ int a0 = 0;
    public View Z;

    public class a extends l5.a {
        public final /* synthetic */ p5 b;

        public a(p5 p5Var) {
            this.b = p5Var;
        }

        @Override // l5.a
        public final void b(Drawable drawable) {
            if (drawable != null) {
                this.b.start();
            }
        }
    }

    @CalledByNative
    public static boolean canDeviceSupportCable() {
        if (BluetoothAdapter.getDefaultAdapter() == null) {
            return false;
        }
        return ((KeyguardManager) xz.a.getSystemService("keyguard")).isDeviceSecure();
    }

    @CalledByNative
    public static void onCloudMessage(byte[] bArr, boolean z) {
        String string;
        String string2;
        Context context = xz.a;
        Resources resources = context.getResources();
        try {
            int i = CableAuthenticatorActivity.I;
            Intent intent = new Intent(context, (Class<?>) CableAuthenticatorActivity.class);
            intent.setFlags(268468224);
            Bundle bundle = new Bundle();
            bundle.putBoolean("org.chromium.chrome.modules.cablev2_authenticator.FCM", true);
            bundle.putByteArray("org.chromium.chrome.modules.cablev2_authenticator.EVENT", bArr);
            intent.putExtra("show_fragment_args", bundle);
            PendingIntent activity = PendingIntent.getActivity(context, 424386536, intent, 201326592);
            if (z) {
                string = resources.getString(R$string.cablev2_make_credential_notification_title);
                string2 = resources.getString(R$string.cablev2_make_credential_notification_body);
            } else {
                string = resources.getString(R$string.cablev2_get_assertion_notification_title);
                string2 = resources.getString(R$string.cablev2_get_assertion_notification_body);
            }
            qq qqVarD = n3.D("security_key", null);
            qqVarD.h(true);
            NotificationCompat$Builder notificationCompat$Builder = qqVarD.a;
            notificationCompat$Builder.s = "msg";
            notificationCompat$Builder.g = activity;
            qqVarD.j(string2);
            qqVarD.k(string);
            notificationCompat$Builder.i = 2;
            qqVarD.p(R$drawable.ic_chrome);
            notificationCompat$Builder.y = 60000L;
            notificationCompat$Builder.v = 1;
            new ro1(context).b(null, 16, qqVarD.e());
        } catch (ClassNotFoundException unused) {
            Log.e("cr_CableAuthModuleProv", "Failed to find class org.chromium.chrome.browser.webauth.authenticator.CableAuthenticatorActivity");
        }
    }

    public final void H0() {
        androidx.fragment.app.a aVar = new androidx.fragment.app.a(M());
        Fragment fragmentA = ((ii1) n3.i.a()).a();
        Bundle bundle = this.j;
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putLong("org.chromium.chrome.modules.cablev2_authenticator.NetworkContext", N.MxK6FZrl());
        bundle.putLong("org.chromium.chrome.modules.cablev2_authenticator.Registration", N.MX3WavKZ());
        bundle.putByteArray("org.chromium.chrome.modules.cablev2_authenticator.Secret", N.MKEGJA07());
        fragmentA.C0(bundle);
        aVar.e(this.z, fragmentA, null);
        aVar.g();
    }

    @Override // androidx.fragment.app.Fragment
    public final View e0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LinearLayout linearLayout = new LinearLayout(K());
        View viewInflate = layoutInflater.inflate(R$layout.cablev2_error, viewGroup, false);
        this.Z = viewInflate;
        viewInflate.findViewById(R$id.error_close).setOnClickListener(this);
        ((TextView) this.Z.findViewById(R$id.error_code)).setText(N().getString(R$string.cablev2_error_code, 99));
        ((TextView) this.Z.findViewById(R$id.error_description)).setText(N().getString(R$string.cablev2_error_generic));
        hi1 hi1Var = n3.i;
        if (hi1Var.d()) {
            H0();
        } else {
            hi1Var.c(new im2(this));
            linearLayout.addView(layoutInflater.inflate(R$layout.cablev2_spinner, viewGroup, false));
            ((TextView) linearLayout.findViewById(R$id.status_text)).setText(N().getString(R$string.cablev2_serverlink_status_dfm_install));
            p5 p5VarB = p5.b(K(), R$drawable.circle_loader_animation);
            p5VarB.a(new a(p5VarB));
            ((ImageView) linearLayout.findViewById(R$id.spinner)).setImageDrawable(p5VarB);
            p5VarB.start();
        }
        return linearLayout;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        D().finish();
    }
}
