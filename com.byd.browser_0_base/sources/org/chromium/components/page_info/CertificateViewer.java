package org.chromium.components.page_info;

import J.N;
import android.app.Dialog;
import android.content.Context;
import android.net.http.SslCertificate;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatTextView;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class CertificateViewer implements AdapterView.OnItemSelectedListener {
    public final Context e;
    public final int f;
    public ArrayList<String> g;
    public ArrayList<LinearLayout> h;
    public CertificateFactory i;
    public Dialog j = null;

    public CertificateViewer(Context context) {
        this.e = context;
        this.f = (int) context.getResources().getDimension(R$dimen.page_info_popup_padding_vertical);
    }

    public static String e(byte[] bArr, char c) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bArr.length; i++) {
            sb.append(String.format("%02X", Byte.valueOf(bArr[i])));
            if (i != bArr.length - 1) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public final void a(Certificate certificate, byte[] bArr, byte[] bArr2) {
        Collection<List<?>> subjectAlternativeNames;
        int iIntValue;
        LinearLayout linearLayout = new LinearLayout(this.e);
        this.h.add(linearLayout);
        linearLayout.setOrientation(1);
        X509Certificate x509Certificate = (X509Certificate) certificate;
        SslCertificate sslCertificate = new SslCertificate(x509Certificate);
        this.g.add(sslCertificate.getIssuedTo().getCName());
        c(linearLayout, N.MVBnBWgu()).setAllCaps(true);
        b(linearLayout, N.M61$9xnN(), sslCertificate.getIssuedTo().getCName());
        b(linearLayout, N.M3Q$lPl0(), sslCertificate.getIssuedTo().getOName());
        b(linearLayout, N.MtQn_hO9(), sslCertificate.getIssuedTo().getUName());
        b(linearLayout, N.M2rGgvRp(), e(x509Certificate.getSerialNumber().toByteArray(), ':'));
        c(linearLayout, N.MsQpEHij()).setAllCaps(true);
        b(linearLayout, N.M61$9xnN(), sslCertificate.getIssuedBy().getCName());
        b(linearLayout, N.M3Q$lPl0(), sslCertificate.getIssuedBy().getOName());
        b(linearLayout, N.MtQn_hO9(), sslCertificate.getIssuedBy().getUName());
        c(linearLayout, N.MdXOhLT3()).setAllCaps(true);
        DateFormat dateInstance = DateFormat.getDateInstance(2);
        b(linearLayout, N.MnsPVX7Z(), dateInstance.format(sslCertificate.getValidNotBeforeDate()));
        b(linearLayout, N.Mrqn7ZPZ(), dateInstance.format(sslCertificate.getValidNotAfterDate()));
        c(linearLayout, N.Mj6CXcHR()).setAllCaps(true);
        b(linearLayout, N.MGc5t$JX(), e(bArr, ' '));
        b(linearLayout, N.M3Qp55TF(), e(bArr2, ' '));
        ArrayList arrayList = new ArrayList();
        try {
            subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
        } catch (CertificateParsingException unused) {
            subjectAlternativeNames = null;
        }
        if (subjectAlternativeNames != null && !subjectAlternativeNames.isEmpty()) {
            for (List<?> list : subjectAlternativeNames) {
                if (list != null && list.size() == 2 && list.get(0) != null && list.get(0).getClass() == Integer.class && list.get(1) != null && list.get(1).getClass() == String.class && ((iIntValue = ((Integer) list.get(0)).intValue()) == 2 || iIntValue == 7)) {
                    arrayList.add(list.get(1).toString());
                }
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        c(linearLayout, N.MRBlj4Pf()).setAllCaps(true);
        c(linearLayout, N.M_b6sZk8());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            d(linearLayout, (String) it.next());
        }
    }

    public final void b(LinearLayout linearLayout, String str, String str2) {
        if (str2.isEmpty()) {
            return;
        }
        c(linearLayout, str);
        d(linearLayout, str2);
    }

    public final AppCompatTextView c(LinearLayout linearLayout, String str) {
        AppCompatTextView appCompatTextView = new AppCompatTextView(this.e);
        appCompatTextView.setTextAlignment(5);
        int i = this.f;
        appCompatTextView.setPadding(i, i / 2, i, 0);
        appCompatTextView.setText(str);
        appCompatTextView.setTextAppearance(appCompatTextView.getContext(), R$style.TextAppearance_TextMediumThick_Primary);
        linearLayout.addView(appCompatTextView);
        return appCompatTextView;
    }

    public final void d(LinearLayout linearLayout, String str) {
        AppCompatTextView appCompatTextView = new AppCompatTextView(this.e);
        appCompatTextView.setTextAlignment(5);
        appCompatTextView.setText(str);
        int i = this.f;
        appCompatTextView.setPadding(i, 0, i, i / 2);
        appCompatTextView.setTextAppearance(appCompatTextView.getContext(), R$style.TextAppearance_TextMedium_Primary);
        linearLayout.addView(appCompatTextView);
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        int i2 = 0;
        while (i2 < this.h.size()) {
            this.h.get(i2).setVisibility(i2 == i ? 0 : 8);
            i2++;
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onNothingSelected(AdapterView<?> adapterView) {
    }
}
