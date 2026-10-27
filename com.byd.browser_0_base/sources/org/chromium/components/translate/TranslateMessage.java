package org.chromium.components.translate;

import J.N;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import defpackage.k81;
import defpackage.l81;
import defpackage.ng1;
import defpackage.nx0;
import defpackage.p4;
import defpackage.ur3;
import defpackage.vg1;
import defpackage.xg1;
import defpackage.yo;
import defpackage.z92;
import defpackage.zj;
import java.lang.ref.WeakReference;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.translate.a;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.widget.Toast;

/* JADX INFO: loaded from: classes3.dex */
public class TranslateMessage implements a.InterfaceC0144a {
    public final Context a;
    public final vg1 b;
    public final WebContents c;
    public long d;
    public final int e;
    public PropertyModel f;

    public static class MenuItem {
        public final String a;
        public final String b;
        public final boolean c;
        public final int d;
        public final String e;

        public MenuItem(int i, String str, String str2, String str3, boolean z) {
            this.a = str;
            this.b = str2;
            this.c = z;
            this.d = i;
            this.e = str3;
        }
    }

    public final class a extends DataSetObserver implements l81 {
        public WeakReference<z92> e;

        public a() {
        }

        @Override // defpackage.l81
        public final z92 a(View view) {
            z92 z92VarA = super.a(view);
            this.e = new WeakReference<>(z92VarA);
            return z92VarA;
        }

        @Override // defpackage.l81
        public final k81 b() {
            TranslateMessage translateMessage = TranslateMessage.this;
            Context context = translateMessage.a;
            long j = translateMessage.d;
            return new org.chromium.components.translate.a(context, translateMessage, this, j == 0 ? null : (MenuItem[]) N.MZGYXqwU(j));
        }

        @Override // android.database.DataSetObserver
        public final void onChanged() {
            z92 z92Var = this.e.get();
            if (z92Var != null) {
                Rect rect = z92Var.e;
                rect.set(rect);
                z92.a aVar = z92Var.f;
                if (aVar != null) {
                    ((p4) aVar).g();
                }
            }
        }
    }

    public TranslateMessage(Context context, vg1 vg1Var, WebContents webContents, long j, int i) {
        this.a = context;
        this.b = vg1Var;
        this.c = webContents;
        this.d = j;
        this.e = i;
    }

    @CalledByNative
    public static MenuItem[] constructMenuItemArray(String[] strArr, String[] strArr2, boolean[] zArr, int[] iArr, String[] strArr3) {
        MenuItem[] menuItemArr = new MenuItem[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            menuItemArr[i] = new MenuItem(iArr[i], strArr[i], strArr2[i], strArr3[i], zArr[i]);
        }
        return menuItemArr;
    }

    @CalledByNative
    public static TranslateMessage create(WebContents webContents, long j, int i) {
        nx0 nx0VarK;
        vg1 vg1VarA;
        WindowAndroid windowAndroidV = webContents.V();
        Context context = (windowAndroidV == null || (nx0VarK = windowAndroidV.k()) == null) ? null : (Context) nx0VarK.get();
        if (context == null || (vg1VarA = xg1.a(webContents.V())) == null) {
            return null;
        }
        return new TranslateMessage(context, vg1VarA, webContents, j, i);
    }

    @CalledByNative
    public static void showTranslateError(WebContents webContents) {
        nx0 nx0VarK;
        WindowAndroid windowAndroidV = webContents.V();
        Context context = null;
        if (windowAndroidV != null && (nx0VarK = windowAndroidV.k()) != null) {
            context = (Context) nx0VarK.get();
        }
        if (context == null) {
            return;
        }
        Toast.c(context, R$string.translate_infobar_error, 0).e();
    }

    @CalledByNative
    public void clearNativePointer() {
        this.d = 0L;
    }

    @CalledByNative
    public void dismiss() {
        this.b.a(9, this.f);
    }

    @CalledByNative
    public void showMessage(String str, String str2, String str3, boolean z) {
        boolean z2 = this.f == null;
        int i = 2;
        if (z2) {
            PropertyModel.b bVar = new PropertyModel.b(ng1.H);
            bVar.c(ng1.a, 29);
            bVar.c(ng1.m, R$drawable.infobar_translate_compact);
            bVar.c(ng1.p, 0);
            bVar.e(ng1.u, new a());
            bVar.c(ng1.v, 2);
            PropertyModel.q qVar = ng1.w;
            long j = this.e;
            int i2 = zj.a;
            PropertyModel.e eVar = new PropertyModel.e();
            eVar.a = j;
            bVar.a.put(qVar, eVar);
            bVar.e(ng1.d, new yo(20, this));
            bVar.e(ng1.x, new ur3(i, this));
            this.f = bVar.a();
        }
        this.f.p(ng1.f, str);
        this.f.p(ng1.h, str2);
        if (TextUtils.isEmpty(str3)) {
            this.f.n(ng1.b, 1);
        } else {
            this.f.p(ng1.c, str3);
            this.f.n(ng1.b, 0);
        }
        if (z) {
            this.f.n(ng1.r, R$drawable.settings_cog);
        }
        if (z2) {
            this.b.b(this.f, this.c, 2, false);
        }
    }
}
