package org.chromium.components.browser_ui.contacts_picker;

import J.N;
import android.content.Context;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import defpackage.iq2;
import defpackage.xx;
import java.util.HashSet;
import org.chromium.components.browser_ui.widget.chips.ChipView;
import org.chromium.content.browser.ContactsDialogHost;

/* JADX INFO: loaded from: classes2.dex */
public class TopView extends RelativeLayout implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    public final Context e;
    public View f;
    public CheckBox g;
    public TextView h;
    public b i;
    public ChipView j;
    public ChipView k;
    public ChipView l;
    public ChipView m;
    public ChipView n;
    public a o;
    public boolean p;

    public interface a {
    }

    public interface b {
    }

    public TopView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = context;
    }

    public final void a(int i) {
        ChipView chipView;
        int i2;
        if (i == 0) {
            chipView = this.j;
            i2 = R$drawable.names;
        } else if (i == 1) {
            chipView = this.l;
            i2 = R$drawable.email;
        } else if (i == 2) {
            chipView = this.m;
            i2 = R$drawable.telephone;
        } else if (i == 3) {
            chipView = this.k;
            i2 = R$drawable.address;
        } else {
            if (i != 4) {
                return;
            }
            chipView = this.n;
            i2 = R$drawable.face;
        }
        chipView.setSelected(!chipView.isSelected());
        if (chipView.isSelected()) {
            i2 = R$drawable.ic_check_googblue_24dp;
        }
        chipView.a(i2, true);
        f fVar = (f) this.o;
        fVar.getClass();
        if (i == 0) {
            f.r = !f.r;
        } else if (i == 1) {
            f.s = !f.s;
        } else if (i == 2) {
            f.t = !f.t;
        } else if (i == 3) {
            f.q = !f.q;
        } else if (i == 4) {
            f.u = !f.u;
        }
        fVar.o();
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (this.p) {
            return;
        }
        b bVar = this.i;
        boolean zIsChecked = this.g.isChecked();
        PickerCategoryView pickerCategoryView = (PickerCategoryView) bVar;
        org.chromium.components.browser_ui.widget.selectable_list.a<xx> aVar = pickerCategoryView.m;
        if (zIsChecked) {
            pickerCategoryView.p = aVar.c;
            aVar.h(new HashSet(pickerCategoryView.k.m));
            ((ContactsDialogHost) pickerCategoryView.h).a(2, null, 0, 0);
        } else {
            aVar.h(new HashSet());
            pickerCategoryView.p = null;
            ((ContactsDialogHost) pickerCategoryView.h).a(3, null, 0, 0);
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int id = view.getId();
        if (id == R$id.names_filter) {
            a(0);
            return;
        }
        if (id == R$id.address_filter) {
            a(3);
            return;
        }
        if (id == R$id.email_filter) {
            a(1);
        } else if (id == R$id.tel_filter) {
            a(2);
        } else if (id == R$id.icon_filter) {
            a(4);
        }
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.f = findViewById(R$id.content);
        if (N.Mk6X8tWe("ContactsPickerSelectAll")) {
            this.f.setVisibility(0);
        }
        this.g = (CheckBox) findViewById(R$id.checkbox);
        this.h = (TextView) findViewById(R$id.checkbox_details);
        ((TextView) findViewById(R$id.checkbox_title)).setText(R$string.contacts_picker_all_contacts);
        ChipView chipView = (ChipView) findViewById(R$id.names_filter);
        this.j = chipView;
        chipView.getPrimaryTextView().setText(R$string.top_view_names_filter_label);
        this.j.setSelected(true);
        this.j.setOnClickListener(this);
        ChipView chipView2 = this.j;
        int i = R$drawable.ic_check_googblue_24dp;
        chipView2.a(i, false);
        ChipView chipView3 = (ChipView) findViewById(R$id.address_filter);
        this.k = chipView3;
        chipView3.getPrimaryTextView().setText(R$string.top_view_address_filter_label);
        this.k.setSelected(true);
        this.k.setOnClickListener(this);
        this.k.a(i, false);
        ChipView chipView4 = (ChipView) findViewById(R$id.email_filter);
        this.l = chipView4;
        chipView4.getPrimaryTextView().setText(R$string.top_view_email_filter_label);
        this.l.setSelected(true);
        this.l.setOnClickListener(this);
        this.l.a(i, false);
        ChipView chipView5 = (ChipView) findViewById(R$id.tel_filter);
        this.m = chipView5;
        chipView5.getPrimaryTextView().setText(R$string.top_view_telephone_filter_label);
        this.m.setSelected(true);
        this.m.setOnClickListener(this);
        this.m.a(i, false);
        ChipView chipView6 = (ChipView) findViewById(R$id.icon_filter);
        this.n = chipView6;
        chipView6.getPrimaryTextView().setText(R$string.top_view_icon_filter_label);
        this.n.setSelected(true);
        this.n.setOnClickListener(this);
        this.n.a(i, false);
    }

    public void setSiteString(String str) {
        ((TextView) findViewById(R$id.explanation)).setText(iq2.a(this.e.getString(R$string.disclaimer_sharing_contact_details, str), new iq2.a(new StyleSpan(1), "<b>", "</b>")));
    }
}
