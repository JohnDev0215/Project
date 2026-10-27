package org.chromium.components.browser_ui.contacts_picker;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import defpackage.fy;
import defpackage.i9;
import defpackage.xx;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.chromium.components.browser_ui.contacts_picker.PickerCategoryView;

/* JADX INFO: compiled from: CompressContactIconsWorkerTask.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a extends i9<Void> {
    public final ContentResolver h;
    public final HashSet i;
    public final HashMap<String, Bitmap> j = new HashMap<>();
    public final List<xx> k;
    public final InterfaceC0120a l;

    /* JADX INFO: renamed from: org.chromium.components.browser_ui.contacts_picker.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CompressContactIconsWorkerTask.java */
    public interface InterfaceC0120a {
    }

    public a(ContentResolver contentResolver, PickerCategoryView.a aVar, ArrayList arrayList, InterfaceC0120a interfaceC0120a) {
        this.h = contentResolver;
        this.i = aVar.b;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            xx xxVar = (xx) it.next();
            HashMap<String, Bitmap> map = this.j;
            String str = xxVar.e;
            map.put(str, aVar.a.a(str));
        }
        this.k = arrayList;
        this.l = interfaceC0120a;
    }

    @Override // defpackage.i9
    public final Void b() {
        for (xx xxVar : this.k) {
            if (!this.i.contains(xxVar.e)) {
                HashMap<String, Bitmap> map = this.j;
                String str = xxVar.e;
                Bitmap bitmapO = map.get(str);
                if (bitmapO == null) {
                    boolean z = xxVar.k;
                    Drawable drawable = z ? xxVar.l : null;
                    if (drawable != null && (drawable instanceof BitmapDrawable)) {
                        bitmapO = ((BitmapDrawable) drawable).getBitmap();
                    } else if (!z) {
                        bitmapO = new e(str, this.h, null).b();
                    }
                }
                if (bitmapO != null) {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmapO.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    fy fyVar = new fy();
                    fyVar.c = byteArrayOutputStream.toByteArray();
                    fyVar.b = "image/png";
                    xxVar.j.add(fyVar);
                }
            }
        }
        return null;
    }

    @Override // defpackage.i9
    public final void l(Void r1) {
        if (i()) {
            return;
        }
        ((PickerCategoryView) this.l).m(this.k);
    }
}
