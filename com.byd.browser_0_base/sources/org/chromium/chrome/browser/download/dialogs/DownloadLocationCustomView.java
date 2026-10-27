package org.chromium.chrome.browser.download.dialogs;

import J.N;
import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import defpackage.bq;
import defpackage.bz;
import defpackage.ma0;
import defpackage.md0;
import defpackage.oj2;
import defpackage.pu2;
import defpackage.sk;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.download.settings.a;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.widget.text.AlertDialogEditText;

/* JADX INFO: loaded from: classes.dex */
public class DownloadLocationCustomView extends ScrollView implements CompoundButton.OnCheckedChangeListener, a.InterfaceC0080a {
    public final org.chromium.chrome.browser.download.settings.a e;
    public TextView f;
    public TextView g;
    public TextView h;
    public AlertDialogEditText i;
    public TextView j;
    public Spinner k;
    public TextView l;
    public CheckBox m;
    public int n;
    public long o;

    public DownloadLocationCustomView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = new org.chromium.chrome.browser.download.settings.a(context, this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLocationAvailableSpace(long j) {
        if (this.n != 6) {
            return;
        }
        String strB = md0.b(getContext(), pu2.a, j);
        ColorStateList colorStateListB = bz.b(getContext(), R$color.default_text_color_list);
        int color = getContext().getColor(R$color.explanation_text_color);
        if (j < this.o) {
            strB = getContext().getResources().getString(R$string.download_manager_list_item_description, strB, getContext().getText(R$string.download_location_not_enough_space));
            Context context = getContext();
            int i = R$color.input_underline_error_color;
            ColorStateList colorStateListValueOf = ColorStateList.valueOf(context.getColor(i));
            color = getContext().getColor(i);
            oj2.U(1, 2, "MobileDownload.Location.Dialog.Suggestion.Events");
            colorStateListB = colorStateListValueOf;
        }
        this.l.setText(strB);
        this.l.setTextColor(colorStateListB);
        this.k.getBackground().mutate().setTint(color);
    }

    @Override // org.chromium.chrome.browser.download.settings.a.InterfaceC0080a
    public final void g() {
        int i;
        org.chromium.chrome.browser.download.settings.a aVar = this.e;
        int iD = aVar.e;
        int i2 = org.chromium.chrome.browser.download.settings.a.l;
        if (iD == -1 || (i = this.n) == 2 || i == 3) {
            iD = aVar.d();
        }
        if (this.n == 6) {
            long j = this.o;
            aVar.getClass();
            String strM4fixBWD = N.M4fixBWD();
            double d = 0.0d;
            int i3 = -1;
            for (int i4 = 0; i4 < aVar.getCount(); i4++) {
                ma0 ma0Var = (ma0) aVar.getItem(i4);
                if (ma0Var != null && !strM4fixBWD.equals(ma0Var.b)) {
                    double d2 = (ma0Var.c - j) / ma0Var.d;
                    if (d2 > d) {
                        i3 = i4;
                        d = d2;
                    }
                }
            }
            if (i3 != -1) {
                aVar.e = i3;
                iD = i3;
            } else {
                aVar.a();
                iD = 0;
            }
        }
        this.k.setAdapter((SpinnerAdapter) aVar);
        this.k.setSelection(iD);
        sk skVar = bq.a;
        if (N.M09VlOh_("SmartSuggestionForLargeDownloads")) {
            this.k.setOnItemSelectedListener(new a());
        }
    }

    public ma0 getDirectoryOption() {
        Spinner spinner = this.k;
        if (spinner == null) {
            return null;
        }
        return (ma0) spinner.getSelectedItem();
    }

    public boolean getDontShowAgain() {
        CheckBox checkBox = this.m;
        return checkBox != null && checkBox.isChecked();
    }

    public String getFileName() {
        AlertDialogEditText alertDialogEditText = this.i;
        if (alertDialogEditText == null || alertDialogEditText.getText() == null) {
            return null;
        }
        return this.i.getText().toString();
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        N.MPBZLcVx(oj2.k(Profile.d()).a, "download.prompt_for_download_android", z ? 2 : 1);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.f = (TextView) findViewById(R$id.title);
        this.g = (TextView) findViewById(R$id.subtitle);
        this.h = (TextView) findViewById(R$id.incognito_warning);
        this.i = (AlertDialogEditText) findViewById(R$id.file_name);
        this.j = (TextView) findViewById(R$id.file_size);
        this.k = (Spinner) findViewById(R$id.file_location);
        this.l = (TextView) findViewById(R$id.location_available_space);
        this.m = (CheckBox) findViewById(R$id.show_again_checkbox);
    }

    public void setDontShowAgainCheckbox(boolean z) {
        this.m.setChecked(z);
        this.m.setOnCheckedChangeListener(this);
    }

    public void setFileName(CharSequence charSequence) {
        this.i.setText(charSequence);
    }

    public void setFileSize(CharSequence charSequence) {
        this.j.setVisibility(0);
        this.j.setText(charSequence);
    }

    public void setSubtitle(CharSequence charSequence) {
        this.g.setText(charSequence);
    }

    public void setTitle(CharSequence charSequence) {
        this.f.setText(charSequence);
    }

    @Override // org.chromium.chrome.browser.download.settings.a.InterfaceC0080a
    public final void e() {
    }

    public class a implements AdapterView.OnItemSelectedListener {
        public a() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public final void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            DownloadLocationCustomView downloadLocationCustomView = DownloadLocationCustomView.this;
            downloadLocationCustomView.setLocationAvailableSpace(((ma0) downloadLocationCustomView.e.getItem(i)).c);
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public final void onNothingSelected(AdapterView<?> adapterView) {
        }
    }
}
