package org.chromium.components.page_info;

import J.N;
import android.R;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import defpackage.bz;
import defpackage.c6;
import defpackage.fa1;
import defpackage.tm;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.widget.ButtonCompat;

/* JADX INFO: loaded from: classes3.dex */
public class ConnectionInfoView implements View.OnClickListener {
    public final Context e;
    public final a f;
    public final LinearLayout g;
    public final WebContents h;
    public final int i;
    public final int j;
    public final long k;
    public final CertificateViewer l;
    public AppCompatTextView m;
    public AppCompatTextView n;
    public ViewGroup o;
    public ViewGroup p;
    public ButtonCompat q;
    public String r;

    public interface a {
    }

    public ConnectionInfoView(Context context, WebContents webContents, a aVar) {
        this.e = context;
        this.f = aVar;
        this.h = webContents;
        this.l = new CertificateViewer(context);
        LinearLayout linearLayout = new LinearLayout(context);
        this.g = linearLayout;
        linearLayout.setOrientation(1);
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R$dimen.page_info_popup_padding_sides);
        this.i = dimensionPixelSize;
        int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(R$dimen.page_info_popup_padding_vertical);
        this.j = dimensionPixelSize2;
        linearLayout.setPadding(dimensionPixelSize, dimensionPixelSize2, dimensionPixelSize, 0);
        this.k = N.MJUBMbqq(this, webContents);
    }

    public final View a(int i, int i2, String str) {
        Context context = this.e;
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.connection_info, (ViewGroup) null);
        ImageView imageView = (ImageView) viewInflate.findViewById(R$id.connection_info_icon);
        imageView.setImageResource(i);
        imageView.setImageTintList(bz.b(context, i2));
        TextView textView = (TextView) viewInflate.findViewById(R$id.connection_info_description);
        textView.setText(str);
        if (TextUtils.isEmpty(str)) {
            textView.setVisibility(8);
        }
        this.g.addView(viewInflate);
        return viewInflate;
    }

    @CalledByNative
    public final void addCertificateSection(int i, String str, String str2, String str3, int i2) {
        this.o = (ViewGroup) a(i, i2, str2).findViewById(R$id.connection_info_text_layout);
        if (str3 == null || str3.isEmpty()) {
            return;
        }
        AppCompatTextView appCompatTextView = new AppCompatTextView(this.e);
        this.m = appCompatTextView;
        appCompatTextView.setText(str3);
        c6.f(this.m, R$style.TextAppearance_TextSmall_Link);
        this.m.setOnClickListener(this);
        this.m.setPadding(0, this.j, 0, 0);
        this.o.addView(this.m);
    }

    @CalledByNative
    public final void addDescriptionSection(int i, String str, String str2, int i2) {
        this.p = (ViewGroup) a(i, i2, str2).findViewById(R$id.connection_info_text_layout);
    }

    @CalledByNative
    public final void addMoreInfoLink(String str) {
        AppCompatTextView appCompatTextView = new AppCompatTextView(this.e);
        this.n = appCompatTextView;
        this.r = "https://support.google.com/chrome?p=android_connection_info";
        appCompatTextView.setText(str);
        c6.f(this.n, R$style.TextAppearance_TextSmall_Link);
        this.n.setPadding(0, this.j, 0, 0);
        this.n.setOnClickListener(this);
        this.p.addView(this.n);
    }

    @CalledByNative
    public final void addResetCertDecisionsButton(String str) {
        int i = R$style.FilledButtonThemeOverlay;
        Context context = this.e;
        ButtonCompat buttonCompat = new ButtonCompat(context, null, i);
        this.q = buttonCompat;
        buttonCompat.setText(str);
        this.q.setOnClickListener(this);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        linearLayout.addView(this.q);
        linearLayout.setPadding(0, 0, 0, this.i);
        this.g.addView(linearLayout);
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        byte[] bArrDigest;
        ButtonCompat buttonCompat = this.q;
        a aVar = this.f;
        WebContents webContents = this.h;
        if (buttonCompat == view) {
            N.MYkS$dAY(this.k, this, webContents);
            ((PageInfoController) ((b) aVar).e).d();
            return;
        }
        if (this.m != view) {
            if (this.n == view) {
                Context context = this.e;
                ((PageInfoController) ((b) aVar).e).d();
                try {
                    Intent uri = Intent.parseUri(this.r, 1);
                    uri.putExtra("create_new_tab", true);
                    uri.putExtra("com.android.browser.application_id", context.getPackageName());
                    context.startActivity(uri);
                    return;
                } catch (Exception e) {
                    fa1.l("ConnectionInfoView", "Bad URI %s", this.r, e);
                    return;
                }
            }
            return;
        }
        byte[][] bArrMW74qHgy = N.MW74qHgy(webContents);
        if (bArrMW74qHgy == null) {
            return;
        }
        CertificateViewer certificateViewer = this.l;
        Dialog dialog = certificateViewer.j;
        if (dialog == null || !dialog.isShowing()) {
            certificateViewer.g = new ArrayList<>();
            certificateViewer.h = new ArrayList<>();
            for (byte[] bArr : bArrMW74qHgy) {
                try {
                    if (certificateViewer.i == null) {
                        certificateViewer.i = CertificateFactory.getInstance("X.509");
                    }
                    Certificate certificateGenerateCertificate = certificateViewer.i.generateCertificate(new ByteArrayInputStream(bArr));
                    byte[] bArrDigest2 = null;
                    try {
                        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                        messageDigest.update(bArr);
                        bArrDigest = messageDigest.digest();
                    } catch (NoSuchAlgorithmException unused) {
                        bArrDigest = null;
                    }
                    try {
                        MessageDigest messageDigest2 = MessageDigest.getInstance("SHA-1");
                        messageDigest2.update(bArr);
                        bArrDigest2 = messageDigest2.digest();
                    } catch (NoSuchAlgorithmException unused2) {
                    }
                    certificateViewer.a(certificateGenerateCertificate, bArrDigest, bArrDigest2);
                } catch (CertificateException e2) {
                    Log.e("cr_CertViewer", "Error parsing certificate" + e2.toString());
                }
            }
            ArrayList<String> arrayList = certificateViewer.g;
            Context context2 = certificateViewer.e;
            tm tmVar = new tm(certificateViewer, context2, arrayList);
            tmVar.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
            LinearLayout linearLayout = new LinearLayout(context2);
            linearLayout.setOrientation(1);
            AppCompatTextView appCompatTextView = new AppCompatTextView(context2);
            appCompatTextView.setText(R$string.certtitle);
            appCompatTextView.setTextAlignment(5);
            appCompatTextView.setTextAppearance(appCompatTextView.getContext(), R.style.TextAppearance.Large);
            appCompatTextView.setTypeface(appCompatTextView.getTypeface(), 1);
            int i = certificateViewer.f;
            appCompatTextView.setPadding(i, i, i, i / 2);
            linearLayout.addView(appCompatTextView);
            Spinner spinner = new Spinner(context2);
            spinner.setTextAlignment(5);
            spinner.setAdapter((SpinnerAdapter) tmVar);
            spinner.setOnItemSelectedListener(certificateViewer);
            spinner.setDropDownWidth(-1);
            spinner.setPadding(0, 0, 0, 0);
            linearLayout.addView(spinner);
            LinearLayout linearLayout2 = new LinearLayout(context2);
            linearLayout2.setOrientation(1);
            for (int i2 = 0; i2 < certificateViewer.h.size(); i2++) {
                LinearLayout linearLayout3 = certificateViewer.h.get(i2);
                if (i2 != 0) {
                    linearLayout3.setVisibility(8);
                }
                linearLayout2.addView(linearLayout3);
            }
            ScrollView scrollView = new ScrollView(context2);
            scrollView.addView(linearLayout2);
            linearLayout.addView(scrollView);
            Dialog dialog2 = new Dialog(context2);
            certificateViewer.j = dialog2;
            dialog2.requestWindowFeature(1);
            certificateViewer.j.addContentView(linearLayout, new LinearLayout.LayoutParams(-1, -1));
            certificateViewer.j.show();
        }
    }

    @CalledByNative
    public final void onReady() {
        FrameLayout frameLayout = ((b) this.f).m;
        if (frameLayout != null) {
            frameLayout.addView(this.g);
        }
    }
}
