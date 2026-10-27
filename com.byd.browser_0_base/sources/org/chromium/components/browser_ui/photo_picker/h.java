package org.chromium.components.browser_ui.photo_picker;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.SystemClock;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AlertDialog;
import defpackage.d12;
import defpackage.i9;
import defpackage.sh1;
import defpackage.w02;
import defpackage.x02;
import defpackage.y02;
import java.util.ArrayList;
import org.chromium.components.browser_ui.photo_picker.PhotoPickerToolbar;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: PhotoPickerDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public final class h extends AlertDialog implements PhotoPickerToolbar.a, w02 {
    public final PickerCategoryView k;
    public final a l;
    public boolean m;

    /* JADX INFO: compiled from: PhotoPickerDialog.java */
    public static class a implements y02 {
        public final y02 a;
        public boolean b;

        public a(y02 y02Var) {
            this.a = y02Var;
        }

        @Override // defpackage.y02
        public final void a(int i, Uri[] uriArr) {
            this.b = false;
            if (i == 3 || i == 2) {
                this.b = true;
            }
            this.a.a(i, uriArr);
        }

        @Override // defpackage.y02
        public final void c() {
            this.a.c();
        }
    }

    public h(WindowAndroid windowAndroid, ContentResolver contentResolver, y02 y02Var, boolean z, ArrayList arrayList) {
        super(windowAndroid.i.get(), R$style.ThemeOverlay_BrowserUI_Fullscreen);
        a aVar = new a(y02Var);
        this.l = aVar;
        PickerCategoryView pickerCategoryView = new PickerCategoryView(windowAndroid, contentResolver, z, this);
        this.k = pickerCategoryView;
        pickerCategoryView.e = this;
        pickerCategoryView.j = aVar;
        pickerCategoryView.F = new ArrayList(arrayList);
        ArrayList arrayList2 = PickerCategoryView.I;
        if (arrayList2 != null) {
            pickerCategoryView.e(arrayList2);
        } else {
            d dVar = pickerCategoryView.C;
            if (dVar != null) {
                dVar.a(true);
            }
            pickerCategoryView.D = SystemClock.elapsedRealtime();
            d dVar2 = new d(pickerCategoryView.f, pickerCategoryView, new sh1(pickerCategoryView.F), pickerCategoryView.F, pickerCategoryView.g);
            pickerCategoryView.C = dVar2;
            dVar2.c(i9.e);
        }
        pickerCategoryView.e.setOnCancelListener(new d12(pickerCategoryView));
        h(pickerCategoryView);
        OnBackPressedDispatcher onBackPressedDispatcher = this.g;
        x02 x02Var = new x02(this);
        onBackPressedDispatcher.getClass();
        onBackPressedDispatcher.b(x02Var);
    }

    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog, android.content.DialogInterface
    public final void dismiss() {
        a aVar = this.l;
        if (!aVar.b || this.m) {
            super.dismiss();
            PickerCategoryView pickerCategoryView = this.k;
            d dVar = pickerCategoryView.C;
            if (dVar != null) {
                dVar.a(true);
                pickerCategoryView.C = null;
            }
            c cVar = pickerCategoryView.k;
            if (cVar != null) {
                if (cVar.q) {
                    cVar.e.unbindService(cVar.s);
                }
                cVar.q = false;
                pickerCategoryView.k = null;
            }
            pickerCategoryView.e = null;
            aVar.c();
        }
    }
}
