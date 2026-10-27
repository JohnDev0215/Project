package org.chromium.components.browser_ui.contacts_picker;

import android.content.Context;
import android.util.AttributeSet;
import defpackage.xx;
import java.util.ArrayList;
import org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar;
import org.chromium.ui.widget.ButtonCompat;

/* JADX INFO: loaded from: classes2.dex */
public class ContactsPickerToolbar extends SelectableListToolbar<xx> {
    public a A0;
    public boolean B0;

    public interface a {
    }

    public ContactsPickerToolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.B0 = true;
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar
    public final void B() {
        if (A()) {
            super.B();
        } else {
            ((d) this.A0).cancel();
        }
    }

    public final void H() {
        boolean z = !this.c0.c.isEmpty();
        boolean z2 = z && this.B0;
        ButtonCompat buttonCompat = (ButtonCompat) findViewById(R$id.done);
        buttonCompat.setEnabled(z2);
        if (z2) {
            buttonCompat.setTextAppearance(buttonCompat.getContext(), R$style.TextAppearance_TextMedium_Secondary);
            return;
        }
        buttonCompat.setTextAppearance(buttonCompat.getContext(), R$style.TextAppearance_TextMedium_Disabled);
        if (z) {
            setNavigationButton(2);
        } else {
            setNavigationButton(1);
        }
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar, org.chromium.components.browser_ui.widget.selectable_list.a.InterfaceC0128a
    public final void f(ArrayList arrayList) {
        super.f(arrayList);
        H();
    }

    public void setDelegate(a aVar) {
        this.A0 = aVar;
    }

    public void setFilterChipsSelected(boolean z) {
        this.B0 = z;
        H();
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar
    public final void y(org.chromium.components.browser_ui.widget.selectable_list.a<xx> aVar, int i, int i2, int i3, boolean z) {
        super.y(aVar, i, i2, i3, z);
        setNavigationButton(1);
    }
}
