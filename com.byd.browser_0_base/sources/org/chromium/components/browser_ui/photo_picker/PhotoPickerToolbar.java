package org.chromium.components.browser_ui.photo_picker;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import defpackage.b12;
import java.util.ArrayList;
import org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar;

/* JADX INFO: loaded from: classes2.dex */
public class PhotoPickerToolbar extends SelectableListToolbar<b12> {
    public a A0;

    public interface a {
    }

    public PhotoPickerToolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar
    public final void B() {
        super.B();
        ((h) this.A0).cancel();
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar, org.chromium.components.browser_ui.widget.selectable_list.a.InterfaceC0128a
    public final void f(ArrayList arrayList) {
        super.f(arrayList);
        int size = arrayList.size();
        Button button = (Button) findViewById(R$id.done);
        button.setEnabled(arrayList.size() > 0);
        if (size > 0) {
            button.setTextAppearance(button.getContext(), R$style.TextAppearance_TextMedium_Secondary);
        } else {
            button.setTextAppearance(button.getContext(), R$style.TextAppearance_TextMedium_Disabled);
            setNavigationButton(1);
        }
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar, android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        setNavigationContentDescription(R$string.close);
    }

    public void setDelegate(a aVar) {
        this.A0 = aVar;
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableListToolbar
    public final void y(org.chromium.components.browser_ui.widget.selectable_list.a<b12> aVar, int i, int i2, int i3, boolean z) {
        super.y(aVar, i, i2, i3, z);
        setNavigationButton(1);
    }
}
