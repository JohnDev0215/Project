package org.chromium.chrome.browser.infobar;

import J.N;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.google.android.material.tabs.TabLayout;
import defpackage.hf3;
import defpackage.if3;
import defpackage.ii2;
import defpackage.ku2;
import defpackage.oj2;
import defpackage.tp2;
import defpackage.wp2;
import defpackage.xp2;
import java.util.ArrayList;
import java.util.HashMap;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.preferences.PrefChangeRegistrar;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.browser_ui.styles.R$attr;
import org.chromium.components.infobars.InfoBar;
import org.chromium.components.infobars.InfoBarCompactLayout;
import org.chromium.components.translate.TranslateTabContent;
import org.chromium.components.translate.TranslateTabLayout;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.widget.Toast;

/* JADX INFO: loaded from: classes.dex */
public class TranslateCompactInfoBar extends InfoBar implements TabLayout.d, hf3.b, PrefChangeRegistrar.a {
    public boolean A;
    public boolean B;
    public final PrefChangeRegistrar C;
    public final int o;
    public final int p;
    public final if3 q;
    public long r;
    public TranslateTabLayout s;
    public hf3 t;
    public hf3 u;
    public ImageButton v;
    public InfoBarCompactLayout w;
    public final WindowAndroid x;
    public d y;
    public boolean z;

    public class b implements View.OnLayoutChangeListener {
        public b() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            if (i == i5 && i2 == i6 && i3 == i7 && i4 == i8) {
                return;
            }
            TranslateCompactInfoBar translateCompactInfoBar = TranslateCompactInfoBar.this;
            hf3 hf3Var = translateCompactInfoBar.t;
            if (hf3Var != null) {
                hf3Var.b();
            }
            hf3 hf3Var2 = translateCompactInfoBar.u;
            if (hf3Var2 != null) {
                hf3Var2.b();
            }
            if (translateCompactInfoBar.A) {
                translateCompactInfoBar.s.w();
                translateCompactInfoBar.A = false;
            } else {
                ObjectAnimator objectAnimator = translateCompactInfoBar.s.b0;
                if (objectAnimator != null) {
                    objectAnimator.end();
                }
            }
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            TranslateCompactInfoBar translateCompactInfoBar = TranslateCompactInfoBar.this;
            ObjectAnimator objectAnimator = translateCompactInfoBar.s.b0;
            if (objectAnimator != null) {
                objectAnimator.end();
            }
            TranslateCompactInfoBar.z(3);
            translateCompactInfoBar.y(0);
            hf3 hf3Var = translateCompactInfoBar.t;
            InfoBarCompactLayout infoBarCompactLayout = translateCompactInfoBar.w;
            hf3Var.d(0, infoBarCompactLayout != null ? infoBarCompactLayout.getWidth() : 0);
            translateCompactInfoBar.z = true;
        }
    }

    public class d implements wp2.b {
        public final int e;

        public d(int i) {
            this.e = i;
        }

        @Override // wp2.b
        public final void d(Object obj) {
            TranslateCompactInfoBar translateCompactInfoBar = TranslateCompactInfoBar.this;
            translateCompactInfoBar.y = null;
            translateCompactInfoBar.x(this.e);
        }

        @Override // wp2.b
        public final void j(Object obj) {
            TranslateCompactInfoBar translateCompactInfoBar = TranslateCompactInfoBar.this;
            translateCompactInfoBar.y = null;
            int i = this.e;
            if (i == 0) {
                TranslateCompactInfoBar.z(16);
                return;
            }
            if (i == 1) {
                TranslateCompactInfoBar.z(17);
                return;
            }
            if (i == 2) {
                TranslateCompactInfoBar.z(18);
                return;
            }
            if (i == 3) {
                TranslateCompactInfoBar.z(23);
            } else {
                if (i != 4) {
                    return;
                }
                TranslateCompactInfoBar.z(24);
                translateCompactInfoBar.v(false);
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TranslateCompactInfoBar(WindowAndroid windowAndroid, int i, String str, String str2, boolean z, boolean z2, boolean z3, boolean z4, String[] strArr, String[] strArr2, int[] iArr, String[] strArr3, int i2) {
        super(R$drawable.infobar_translate_compact, 0, null, null);
        this.A = true;
        this.x = windowAndroid;
        if (!N.MI_WWqtz("ContentLanguagesInLanguagePicker") || N.MV96drCk("ContentLanguagesInLanguagePicker", "disable_observers", false)) {
            this.C = null;
        } else {
            PrefChangeRegistrar prefChangeRegistrar = new PrefChangeRegistrar();
            this.C = prefChangeRegistrar;
            prefChangeRegistrar.a("intl.accept_languages", this);
        }
        this.o = i;
        this.p = i2;
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < strArr.length; i3++) {
            arrayList.add(new if3.a(strArr2[i3], strArr[i3], iArr != null ? Integer.valueOf(iArr[i3]) : null));
        }
        this.q = new if3(str, str2, arrayList, strArr3, z, z2, z3, z4);
    }

    @CalledByNative
    public static InfoBar create(Tab tab, int i, String str, String str2, boolean z, boolean z2, boolean z3, boolean z4, String[] strArr, String[] strArr2, int[] iArr, String[] strArr3, int i2) {
        z(0);
        return new TranslateCompactInfoBar(tab.D(), i, str, str2, z, z2, z3, z4, strArr, strArr2, iArr, strArr3, i2);
    }

    public static void z(int i) {
        oj2.U(i, 28, "Translate.CompactInfobar.Event");
    }

    public final void A(String str, String str2) {
        if3 if3Var = this.q;
        if3Var.getClass();
        boolean zIsEmpty = TextUtils.isEmpty(str2);
        HashMap map = if3Var.f;
        Integer num = !zIsEmpty && map.containsKey(str2) ? (Integer) map.get(str2) : null;
        if (num != null) {
            oj2.a0(num.intValue(), str);
        }
    }

    @Override // org.chromium.chrome.browser.preferences.PrefChangeRegistrar.a
    public final void f() {
        long j = this.r;
        if (j != 0) {
            String[] strArrM4cX9AMK = N.M4cX9AMK(j, this);
            this.q.d = strArrM4cX9AMK;
            hf3 hf3Var = this.u;
            if (hf3Var != null) {
                hf3Var.f.d = strArrM4cX9AMK;
                hf3.a aVar = hf3Var.h;
                int i = hf3.a.h;
                aVar.clear();
                aVar.f = 1;
                aVar.addAll(hf3.a(hf3.this, 1));
                aVar.notifyDataSetChanged();
            }
        }
    }

    @Override // com.google.android.material.tabs.TabLayout.c
    public final void h(TabLayout.g gVar) {
        int i = gVar.e;
        if (i == 0) {
            z(12);
            this.B = true;
            s(4);
        } else {
            if (i != 1) {
                return;
            }
            z(1);
            A("Translate.CompactInfobar.Language.Translate", this.q.b);
            this.B = true;
            s(3);
        }
    }

    @Override // org.chromium.components.infobars.InfoBar, defpackage.wz0
    public final void k() {
        PrefChangeRegistrar prefChangeRegistrar = this.C;
        if (prefChangeRegistrar != null) {
            prefChangeRegistrar.b();
        }
        ObjectAnimator objectAnimator = this.s.b0;
        if (objectAnimator != null) {
            objectAnimator.end();
        }
        v(true);
    }

    @Override // org.chromium.components.infobars.InfoBar
    public final void o(InfoBarCompactLayout infoBarCompactLayout) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this.k).inflate(R$layout.infobar_translate_compact_content, (ViewGroup) infoBarCompactLayout, false);
        linearLayout.addOnAttachStateChangeListener(new a());
        TranslateTabLayout translateTabLayout = (TranslateTabLayout) linearLayout.findViewById(R$id.translate_infobar_tabs);
        this.s = translateTabLayout;
        if (this.p > 0) {
            translateTabLayout.s(ii2.g(this.k, R$attr.colorOnSurface), ii2.g(this.k, R$attr.colorPrimary));
        }
        TranslateTabLayout translateTabLayout2 = this.s;
        if3 if3Var = this.q;
        CharSequence[] charSequenceArr = {if3Var.c(), if3Var.a(if3Var.b)};
        translateTabLayout2.getClass();
        for (int i = 0; i < 2; i++) {
            CharSequence charSequence = charSequenceArr[i];
            ku2 ku2VarT = ku2.t();
            try {
                TranslateTabContent translateTabContent = (TranslateTabContent) LayoutInflater.from(translateTabLayout2.getContext()).inflate(org.chromium.components.translate.R$layout.infobar_translate_tab_content, (ViewGroup) translateTabLayout2, false);
                ku2VarT.close();
                translateTabContent.setTextColor(translateTabLayout2.getTabTextColors());
                translateTabContent.setText(charSequence);
                TabLayout.g gVarL = translateTabLayout2.l();
                gVarL.f = translateTabContent;
                gVarL.e();
                gVarL.d = charSequence;
                gVarL.e();
                translateTabLayout2.a(gVarL);
            } catch (Throwable th) {
                try {
                    ku2VarT.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
        }
        int i2 = this.o;
        if (i2 == 1) {
            this.s.k(1).b();
            this.s.v();
            if (if3Var.g) {
                this.B = true;
            }
        } else if (i2 == 2) {
            this.s.k(1).b();
        }
        this.s.addOnTabSelectedListener((TabLayout.d) this);
        this.s.addOnLayoutChangeListener(new b());
        ImageButton imageButton = (ImageButton) linearLayout.findViewById(R$id.translate_infobar_menu_button);
        this.v = imageButton;
        imageButton.setOnClickListener(new c());
        infoBarCompactLayout.a(linearLayout);
        this.w = infoBarCompactLayout;
    }

    @CalledByNative
    public final boolean onPageTranslated(int i) {
        TranslateTabLayout translateTabLayout = this.s;
        if (translateTabLayout == null) {
            return false;
        }
        TabLayout.g gVar = translateTabLayout.a0;
        if (gVar != null) {
            View view = gVar.f;
            if (view instanceof TranslateTabContent) {
                TranslateTabContent translateTabContent = (TranslateTabContent) view;
                translateTabContent.f.setVisibility(4);
                translateTabContent.e.setVisibility(0);
            }
            translateTabLayout.a0 = null;
        }
        if (i == 0) {
            return false;
        }
        Toast toastC = Toast.c(this.k, R$string.translate_infobar_error, 0);
        int[] iArr = new int[2];
        this.s.getLocationOnScreen(iArr);
        toastC.d(49, 0, (iArr[1] - this.s.getHeight()) - this.k.getResources().getDimensionPixelSize(R$dimen.translate_toast_y_offset));
        toastC.e();
        TranslateTabLayout translateTabLayout2 = this.s;
        if (translateTabLayout2 != null) {
            translateTabLayout2.removeOnTabSelectedListener((TabLayout.d) this);
            this.s.k(0).b();
            this.s.addOnTabSelectedListener((TabLayout.d) this);
        }
        return true;
    }

    @CalledByNative
    public void onTargetLanguageChanged(String str) {
        if3 if3Var = this.q;
        boolean z = if3Var.b(if3Var.a) && if3Var.b(str);
        if (z) {
            if3Var.b = str;
        }
        if (z) {
            TranslateTabLayout translateTabLayout = this.s;
            String strA = if3Var.a(str);
            if (1 >= translateTabLayout.getTabCount()) {
                return;
            }
            TabLayout.g gVarK = translateTabLayout.k(1);
            ((TranslateTabContent) gVarK.f).setText(strA);
            gVarK.d = strA;
            gVarK.e();
        }
    }

    @CalledByNative
    public final void onTranslating() {
        TranslateTabLayout translateTabLayout = this.s;
        if (translateTabLayout != null) {
            if (translateTabLayout != null) {
                translateTabLayout.removeOnTabSelectedListener((TabLayout.d) this);
                this.s.k(1).b();
                this.s.addOnTabSelectedListener((TabLayout.d) this);
            }
            this.s.v();
        }
    }

    @Override // org.chromium.components.infobars.InfoBar
    public final CharSequence r(CharSequence charSequence) {
        return this.k.getString(R$string.translate_button);
    }

    @Override // org.chromium.components.infobars.InfoBar
    public final void resetNativeInfoBar() {
        this.r = 0L;
        super.resetNativeInfoBar();
    }

    @CalledByNative
    public final void setAutoAlwaysTranslate() {
        Context context = this.k;
        int i = R$string.translate_snackbar_always_translate;
        if3 if3Var = this.q;
        w(18, 3, context.getString(i, if3Var.c(), if3Var.a(if3Var.b)));
    }

    @CalledByNative
    public final void setNativePtr(long j) {
        this.r = j;
    }

    @Override // org.chromium.components.infobars.InfoBar
    public final void t() {
        hf3 hf3Var = this.t;
        if (hf3Var != null) {
            hf3Var.b();
        }
        hf3 hf3Var2 = this.u;
        if (hf3Var2 != null) {
            hf3Var2.b();
        }
        WindowAndroid windowAndroid = this.x;
        if (xp2.a(windowAndroid) == null || this.y == null) {
            return;
        }
        xp2.a(windowAndroid).a(this.y);
    }

    @Override // org.chromium.components.infobars.InfoBar
    public final boolean u() {
        return true;
    }

    public final void v(boolean z) {
        if (this.l) {
            return;
        }
        if (!this.B) {
            z(2);
        }
        if (z) {
            long j = this.r;
            if (j != 0 && N.MX8X$p3M(j, this, this.z)) {
                w(19, 4, this.k.getString(R$string.translate_snackbar_language_never, this.q.c()));
                return;
            }
        }
        super.k();
    }

    public final void w(int i, int i2, String str) {
        WindowAndroid windowAndroid = this.x;
        if (xp2.a(windowAndroid) == null) {
            x(i2);
            return;
        }
        if (i2 == 0) {
            z(13);
        } else if (i2 == 1) {
            z(15);
        } else if (i2 == 2) {
            z(14);
        } else if (i2 == 3) {
            z(21);
        } else if (i2 == 4) {
            z(22);
        }
        this.y = new d(i2);
        wp2 wp2VarA = xp2.a(windowAndroid);
        tp2 tp2VarA = tp2.a(str, this.y, 1, i);
        tp2VarA.i = false;
        tp2VarA.d = this.k.getString(R$string.translate_snackbar_cancel);
        tp2VarA.e = null;
        wp2VarA.c(tp2VarA);
    }

    public final void x(int i) {
        long j = this.r;
        if (j == 0) {
            return;
        }
        if3 if3Var = this.q;
        if (i == 0) {
            this.B = true;
            if3Var.d(!if3Var.h[2]);
            N.MIY$H5s3(this.r, this, 2, if3Var.h[2]);
            if (if3Var.h[2] && this.s.getSelectedTabPosition() == 0) {
                this.B = true;
                s(3);
                return;
            }
            return;
        }
        if (i == 1) {
            boolean[] zArr = if3Var.h;
            boolean z = !zArr[1];
            zArr[1] = z;
            this.B = true;
            N.MIY$H5s3(j, this, 4, z);
            return;
        }
        if (i == 2) {
            this.B = true;
        } else if (i == 3) {
            if3Var.d(!if3Var.h[2]);
            N.MIY$H5s3(this.r, this, 2, if3Var.h[2]);
            return;
        } else if (i != 4) {
            return;
        }
        boolean[] zArr2 = if3Var.h;
        boolean z2 = !zArr2[0];
        if (zArr2[2] && z2) {
            if3Var.d(false);
        }
        zArr2[0] = z2;
        N.MIY$H5s3(this.r, this, 3, if3Var.h[0]);
    }

    public final void y(int i) {
        boolean zMM0pw8sM = N.MM0pw8sM(this.r, this);
        boolean zEquals = this.q.a.equals("und");
        if (i == 0) {
            this.t = new hf3(this.k, this.v, this.q, this, zMM0pw8sM, zEquals);
        } else if ((i == 1 || i == 2) && this.u == null) {
            this.u = new hf3(this.k, this.v, this.q, this, zMM0pw8sM, zEquals);
        }
    }

    public class a implements View.OnAttachStateChangeListener {
        public a() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewDetachedFromWindow(View view) {
            TranslateCompactInfoBar translateCompactInfoBar = TranslateCompactInfoBar.this;
            hf3 hf3Var = translateCompactInfoBar.t;
            if (hf3Var != null) {
                hf3Var.b();
            }
            hf3 hf3Var2 = translateCompactInfoBar.u;
            if (hf3Var2 != null) {
                hf3Var2.b();
            }
            WindowAndroid windowAndroid = translateCompactInfoBar.x;
            if (xp2.a(windowAndroid) == null || translateCompactInfoBar.y == null) {
                return;
            }
            xp2.a(windowAndroid).a(translateCompactInfoBar.y);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewAttachedToWindow(View view) {
        }
    }

    @Override // com.google.android.material.tabs.TabLayout.c
    public final void a(TabLayout.g gVar) {
    }

    @Override // com.google.android.material.tabs.TabLayout.c
    public final void l(TabLayout.g gVar) {
    }
}
