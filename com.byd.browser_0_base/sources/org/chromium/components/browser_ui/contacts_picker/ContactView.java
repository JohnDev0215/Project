package org.chromium.components.browser_ui.contacts_picker;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import defpackage.bi1;
import defpackage.di1;
import defpackage.qc2;
import defpackage.xx;
import defpackage.zj;
import java.util.ArrayList;
import java.util.HashMap;
import org.chromium.components.browser_ui.widget.selectable_list.SelectableItemView;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: loaded from: classes2.dex */
public class ContactView extends SelectableItemView<xx> {
    public xx A;
    public TextView B;
    public TextView C;
    public TextView D;
    public TextView E;
    public TextView F;
    public TextView G;
    public TextView H;
    public ImageView I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public bi1 f87J;
    public PropertyModel K;
    public final Context y;
    public PickerCategoryView z;

    public ContactView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.y = context;
        setSelectionOnLongClick(false);
    }

    public static void s(TextView textView, String str) {
        textView.setVisibility(TextUtils.isEmpty(str) ? 8 : 0);
        textView.setText(str);
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase, org.chromium.components.browser_ui.widget.selectable_list.a.InterfaceC0128a
    public final void f(ArrayList arrayList) {
        xx xxVar = this.A;
        if (xxVar == null || arrayList.contains(xxVar) == super.isChecked()) {
            return;
        }
        super.toggle();
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase, android.view.View.OnClickListener
    public final void onClick(View view) {
        int id = view.getId();
        if (id == R$id.address_overflow_count || id == R$id.email_overflow_count || id == R$id.telephone_number_overflow_count) {
            onLongClick(this);
        } else {
            super.onClick(view);
        }
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableItemView, org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase, android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.B = (TextView) findViewById(R$id.title);
        this.C = (TextView) findViewById(R$id.address);
        this.D = (TextView) findViewById(R$id.address_overflow_count);
        this.E = (TextView) findViewById(R$id.email);
        this.F = (TextView) findViewById(R$id.email_overflow_count);
        this.G = (TextView) findViewById(R$id.telephone_number);
        this.H = (TextView) findViewById(R$id.telephone_number_overflow_count);
        this.I = (ImageView) findViewById(R$id.star);
        this.D.setOnClickListener(this);
        this.F.setOnClickListener(this);
        this.H.setOnClickListener(this);
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v4, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v9, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v3, types: [T, org.chromium.components.browser_ui.contacts_picker.ContactView$a] */
    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase, android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        this.f87J = this.z.getModalDialogManager();
        ?? aVar = new a();
        HashMap mapE = PropertyModel.e(di1.B);
        PropertyModel.l<di1.a> lVar = di1.a;
        int i = zj.a;
        PropertyModel.g gVar = new PropertyModel.g();
        gVar.a = aVar;
        mapE.put(lVar, gVar);
        PropertyModel.r<String> rVar = di1.c;
        ?? r1 = this.A.f;
        int i2 = zj.a;
        PropertyModel.g gVar2 = new PropertyModel.g();
        gVar2.a = r1;
        mapE.put(rVar, gVar2);
        PropertyModel.r<CharSequence> rVar2 = di1.f;
        ?? E = this.A.e(f.q, f.s, f.t);
        int i3 = zj.a;
        PropertyModel.g gVar3 = new PropertyModel.g();
        gVar3.a = E;
        mapE.put(rVar2, gVar3);
        PropertyModel.r<String> rVar3 = di1.j;
        Resources resources = this.y.getResources();
        int i4 = R$string.close;
        if (i4 != 0) {
            ?? string = resources.getString(i4);
            int i5 = zj.a;
            PropertyModel.g gVar4 = new PropertyModel.g();
            gVar4.a = string;
            mapE.put(rVar3, gVar4);
        }
        PropertyModel propertyModel = new PropertyModel(mapE);
        this.K = propertyModel;
        propertyModel.p(di1.e, getStartIconDrawable());
        this.f87J.j(this.K, 1, false);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00d7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void q(defpackage.xx r18, android.graphics.Bitmap r19) {
        /*
            Method dump skipped, instruction units count: 340
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.browser_ui.contacts_picker.ContactView.q(xx, android.graphics.Bitmap):void");
    }

    public void setCategoryView(PickerCategoryView pickerCategoryView) {
        this.z = pickerCategoryView;
        setSelectionDelegate(pickerCategoryView.getSelectionDelegate());
    }

    public void setIconBitmap(Bitmap bitmap) {
        qc2 qc2Var = new qc2(this.y.getResources(), bitmap);
        qc2Var.k = true;
        qc2Var.j = true;
        qc2Var.g = Math.min(qc2Var.m, qc2Var.l) / 2;
        qc2Var.d.setShader(qc2Var.e);
        qc2Var.invalidateSelf();
        setStartIconDrawable(qc2Var);
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase
    public final void m() {
    }

    public class a implements di1.a {
        public a() {
        }

        @Override // di1.a
        public final void c(int i, PropertyModel propertyModel) {
            ContactView contactView = ContactView.this;
            contactView.f87J.b(i, propertyModel);
            contactView.K = null;
            contactView.f87J = null;
        }

        @Override // di1.a
        public final void b(int i) {
        }
    }
}
