package org.chromium.chrome.browser.download.settings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import defpackage.ma0;
import defpackage.md0;
import defpackage.oj2;
import defpackage.pu2;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.download.settings.a;

/* JADX INFO: compiled from: DownloadLocationPreferenceAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class b extends a implements View.OnClickListener {
    public b(Context context, a.InterfaceC0080a interfaceC0080a) {
        super(context, interfaceC0080a);
    }

    @Override // org.chromium.chrome.browser.download.settings.a, android.widget.ArrayAdapter, android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R$layout.download_location_preference_item, (ViewGroup) null);
        }
        view.setTag(Integer.valueOf(i));
        view.setOnClickListener(this);
        RadioButton radioButton = (RadioButton) view.findViewById(R$id.radio_button);
        radioButton.setChecked(this.e == i);
        radioButton.setTag(Integer.valueOf(i));
        radioButton.setOnClickListener(this);
        if (getCount() <= 1) {
            radioButton.setVisibility(8);
        }
        view.setEnabled(isEnabled(i));
        ma0 ma0Var = (ma0) getItem(i);
        if (ma0Var == null) {
            return view;
        }
        TextView textView = (TextView) view.findViewById(R$id.title);
        textView.setText(ma0Var.a);
        TextView textView2 = (TextView) view.findViewById(R$id.description);
        if (isEnabled(i)) {
            String strB = md0.b(getContext(), pu2.a, ma0Var.c);
            textView2.setText(strB);
            StringBuilder sb = new StringBuilder();
            sb.append(ma0Var.a);
            sb.append(" ");
            sb.append(strB);
            radioButton.setContentDescription(sb);
        } else {
            radioButton.setEnabled(false);
            textView.setEnabled(false);
            textView2.setEnabled(false);
            if (this.k.isEmpty()) {
                textView2.setText(getContext().getText(R$string.download_location_not_enough_space));
            } else {
                textView2.setVisibility(8);
            }
        }
        return view;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int iIntValue = ((Integer) view.getTag()).intValue();
        ma0 ma0Var = (ma0) getItem(iIntValue);
        if (ma0Var == null) {
            return;
        }
        this.e = iIntValue;
        a.InterfaceC0080a interfaceC0080a = this.h;
        if (interfaceC0080a != null) {
            interfaceC0080a.e();
        }
        oj2.U(ma0Var.e, 3, "MobileDownload.Location.Setting.DirectoryType");
        notifyDataSetChanged();
    }
}
