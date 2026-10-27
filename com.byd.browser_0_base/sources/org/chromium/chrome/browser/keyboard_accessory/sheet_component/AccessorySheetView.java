package org.chromium.chrome.browser.keyboard_accessory.sheet_component;

import J.N;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.viewpager.widget.ViewPager;
import defpackage.bq;
import defpackage.sk;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.ui.base.LocalizationUtils;

/* JADX INFO: loaded from: classes.dex */
class AccessorySheetView extends LinearLayout {
    public ViewPager e;

    public AccessorySheetView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void addOnPageChangeListener(ViewPager.h hVar) {
        this.e.addOnPageChangeListener(hVar);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.e = (ViewPager) findViewById(R$id.keyboard_accessory_sheet);
        sk skVar = bq.a;
        if (N.M09VlOh_("AutofillKeyboardAccessory")) {
            ((ImageView) findViewById(R$id.show_keyboard)).setImageDrawable(AppCompatResources.a(getContext(), R$drawable.ic_arrow_back_24dp));
        }
        this.e.setLayoutDirection(LocalizationUtils.isLayoutRtl() ? 1 : 0);
    }
}
