package org.chromium.components.browser_ui.contacts_picker;

import android.content.ContentResolver;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.xx;
import org.chromium.components.browser_ui.contacts_picker.e;

/* JADX INFO: compiled from: ContactViewHolder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b extends RecyclerView.a0 implements e.a {
    public final ContactView A;
    public xx B;
    public e C;
    public final int D;
    public final PickerCategoryView y;
    public final ContentResolver z;

    public b(ContactView contactView, PickerCategoryView pickerCategoryView, ContentResolver contentResolver, int i) {
        super(contactView);
        this.y = pickerCategoryView;
        this.z = contentResolver;
        this.A = contactView;
        this.D = i;
    }
}
