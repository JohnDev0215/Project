package defpackage;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Trace;
import android.util.SparseArray;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.view.autofill.AutofillValue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Objects;
import org.chromium.components.autofill.AutofillProvider;
import org.chromium.components.autofill.FormData;
import org.chromium.components.autofill.FormFieldData;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Aw extends AutofillProvider {
    public C1572tw a;
    public ViewGroup b;
    public WebContents c;
    public C1847yw d;
    public long e;
    public Dw f;
    public InterfaceC1517sw g;
    public long h;

    public Aw(Context context, ViewGroup viewGroup) {
        this(viewGroup, new C1572tw(context), context);
    }

    private void k() {
        if (this.d == null) {
            return;
        }
        for (int i = 0; i < this.d.b.c.size(); i++) {
            m(i);
        }
    }

    private void l() {
        C1902zw c1902zw;
        C1847yw c1847yw = this.d;
        if (c1847yw == null || (c1902zw = c1847yw.c) == null) {
            return;
        }
        this.a.c(this.b, c1847yw.b(c1902zw.a));
        this.d.c = null;
    }

    private void m(int i) {
        int iA;
        FormFieldData formFieldData = (FormFieldData) this.d.b.c.get(i);
        AutofillValue autofillValueForText = null;
        if (formFieldData != null) {
            int i2 = formFieldData.i;
            if (i2 == 0) {
                autofillValueForText = AutofillValue.forText(formFieldData.getValue());
            } else if (i2 == 1) {
                autofillValueForText = AutofillValue.forToggle(formFieldData.isChecked());
            } else if (i2 == 2 && (iA = C1847yw.a(formFieldData.g, formFieldData.getValue())) != -1) {
                autofillValueForText = AutofillValue.forList(iA);
            }
        }
        if (autofillValueForText == null) {
            return;
        }
        C1572tw c1572tw = this.a;
        ViewGroup viewGroup = this.b;
        int iB = this.d.b((short) i);
        if (c1572tw.e || c1572tw.a()) {
            return;
        }
        c1572tw.a.notifyValueChanged(viewGroup, iB, autofillValueForText);
    }

    private void n(boolean z, int i, float f, float f2, float f3, float f4, boolean z2) {
        C1847yw c1847yw = this.d;
        if (c1847yw == null) {
            return;
        }
        C1902zw c1902zw = c1847yw.c;
        if (!z) {
            if (c1902zw == null) {
                return;
            }
            this.a.c(this.b, c1847yw.b(c1902zw.a));
            this.d.c = null;
            return;
        }
        Rect rectP = p(new RectF(f, f2, f3 + f, f4 + f2));
        if (c1902zw != null && c1902zw.a == i && rectP.equals(c1902zw.b)) {
            return;
        }
        if (c1902zw != null) {
            this.a.c(this.b, this.d.b(c1902zw.a));
        }
        short s = (short) i;
        this.a.b(this.b, this.d.b(s), rectP);
        if (!z2) {
            m(i);
            this.h = System.currentTimeMillis();
        }
        this.d.c = new C1902zw(s, rectP);
    }

    private void o(FormData formData) {
        float f = this.c.H().t.d;
        Matrix matrix = new Matrix();
        matrix.setScale(f, f);
        matrix.postTranslate(this.b.getScrollX(), this.b.getScrollY());
        for (FormFieldData formFieldData : formData.c) {
            RectF rectF = new RectF();
            matrix.mapRect(rectF, formFieldData.l);
            formFieldData.m = rectF;
        }
    }

    private Rect p(RectF rectF) {
        float f = this.c.H().t.d;
        RectF rectF2 = new RectF(rectF);
        Matrix matrix = new Matrix();
        matrix.setScale(f, f);
        this.b.getLocationOnScreen(new int[2]);
        matrix.postTranslate(r0[0], r0[1]);
        matrix.mapRect(rectF2);
        return new Rect((int) rectF2.left, (int) rectF2.top, (int) rectF2.right, (int) rectF2.bottom);
    }

    @Override // org.chromium.components.autofill.AutofillProvider
    public void b(SparseArray sparseArray) {
        C1847yw c1847yw;
        FormFieldData formFieldData;
        int listValue;
        if (this.e == 0 || (c1847yw = this.d) == null) {
            return;
        }
        Objects.requireNonNull(c1847yw);
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= sparseArray.size()) {
                z = true;
                break;
            }
            int iKeyAt = sparseArray.keyAt(i);
            if ((((-65536) & iKeyAt) >> 16) != c1847yw.a) {
                break;
            }
            AutofillValue autofillValue = (AutofillValue) sparseArray.get(iKeyAt);
            if (autofillValue != null) {
                short s = (short) (iKeyAt & 65535);
                if (s < 0 || s >= c1847yw.b.c.size() || (formFieldData = (FormFieldData) c1847yw.b.c.get(s)) == null) {
                    break;
                }
                int i2 = formFieldData.i;
                if (i2 == 0) {
                    formFieldData.o = (String) autofillValue.getTextValue();
                    formFieldData.a(true);
                } else if (i2 == 1) {
                    formFieldData.n = autofillValue.getToggleValue();
                    formFieldData.a(true);
                } else if (i2 == 2 && ((listValue = autofillValue.getListValue()) >= 0 || listValue < formFieldData.g.length)) {
                    formFieldData.o = formFieldData.g[listValue];
                    formFieldData.a(true);
                }
            }
            i++;
        }
        if (z) {
            a(this.e, this.d.b);
            Cw cw = this.f.a;
            if (cw != null) {
                cw.a(4);
            }
        }
    }

    @Override // org.chromium.components.autofill.AutofillProvider
    public void c(ViewGroup viewGroup) {
        this.b = viewGroup;
    }

    @Override // org.chromium.components.autofill.AutofillProvider
    public void d(ViewStructure viewStructure, int i) {
        C1847yw c1847yw = this.d;
        if (c1847yw == null) {
            return;
        }
        viewStructure.setWebDomain(c1847yw.b.b);
        viewStructure.setHtmlInfo(viewStructure.newHtmlInfoBuilder("form").addAttribute("name", c1847yw.b.a).build());
        int iAddChildCount = viewStructure.addChildCount(c1847yw.b.c.size());
        short s = 0;
        for (FormFieldData formFieldData : c1847yw.b.c) {
            int i2 = iAddChildCount + 1;
            ViewStructure viewStructureNewChild = viewStructure.newChild(iAddChildCount);
            short s2 = (short) (s + 1);
            viewStructureNewChild.setAutofillId(viewStructure.getAutofillId(), s | (c1847yw.a << 16));
            String str = formFieldData.c;
            if (str != null && !str.isEmpty()) {
                viewStructureNewChild.setAutofillHints(formFieldData.c.split(" +"));
            }
            viewStructureNewChild.setHint(formFieldData.d);
            RectF rectF = formFieldData.m;
            viewStructureNewChild.setDimens((int) rectF.left, (int) rectF.top, 0, 0, (int) rectF.width(), (int) rectF.height());
            ViewStructure.HtmlInfo.Builder builderAddAttribute = viewStructureNewChild.newHtmlInfoBuilder("input").addAttribute("name", formFieldData.b).addAttribute("type", formFieldData.e).addAttribute("label", formFieldData.a).addAttribute("ua-autofill-hints", formFieldData.k).addAttribute("id", formFieldData.f);
            int i3 = formFieldData.i;
            if (i3 == 0) {
                viewStructureNewChild.setAutofillType(1);
                viewStructureNewChild.setAutofillValue(AutofillValue.forText(formFieldData.getValue()));
                int i4 = formFieldData.j;
                if (i4 != 0) {
                    builderAddAttribute.addAttribute("maxlength", String.valueOf(i4));
                }
            } else if (i3 == 1) {
                viewStructureNewChild.setAutofillType(2);
                viewStructureNewChild.setAutofillValue(AutofillValue.forToggle(formFieldData.isChecked()));
            } else if (i3 == 2) {
                viewStructureNewChild.setAutofillType(3);
                viewStructureNewChild.setAutofillOptions(formFieldData.h);
                int iA = C1847yw.a(formFieldData.g, formFieldData.getValue());
                if (iA != -1) {
                    viewStructureNewChild.setAutofillValue(AutofillValue.forList(iA));
                }
            }
            viewStructureNewChild.setHtmlInfo(builderAddAttribute.build());
            iAddChildCount = i2;
            s = s2;
        }
        Cw cw = this.f.a;
        if (cw != null) {
            cw.a(1);
        }
    }

    @Override // org.chromium.components.autofill.AutofillProvider
    public void e() {
        if (g()) {
            C1847yw c1847yw = this.d;
            C1902zw c1902zw = c1847yw.c;
            C1572tw c1572tw = this.a;
            ViewGroup viewGroup = this.b;
            int iB = c1847yw.b(c1902zw.a);
            Rect rect = c1902zw.b;
            if (c1572tw.e || c1572tw.a()) {
                return;
            }
            c1572tw.a.requestAutofill(viewGroup, iB, rect);
        }
    }

    @Override // org.chromium.components.autofill.AutofillProvider
    public void f(WebContents webContents) {
        WebContents webContents2 = this.c;
        if (webContents == webContents2) {
            return;
        }
        if (webContents2 != null) {
            this.d = null;
        }
        this.c = webContents;
    }

    @Override // org.chromium.components.autofill.AutofillProvider
    public boolean g() {
        C1847yw c1847yw = this.d;
        if (c1847yw == null || c1847yw.c == null) {
            return false;
        }
        C1572tw c1572tw = this.a;
        return !((c1572tw.e || c1572tw.a()) ? false : c1572tw.b);
    }

    @Override // org.chromium.components.autofill.AutofillProvider
    public void onDidFillAutofillFormData() {
        k();
    }

    @Override // org.chromium.components.autofill.AutofillProvider
    public void onFocusChanged(boolean z, int i, float f, float f2, float f3, float f4) {
        n(z, i, f, f2, f3, f4, false);
    }

    @Override // org.chromium.components.autofill.AutofillProvider
    public void onFormFieldDidChange(int i, float f, float f2, float f3, float f4) {
        C1847yw c1847yw = this.d;
        if (c1847yw == null) {
            return;
        }
        short s = (short) i;
        C1902zw c1902zw = c1847yw.c;
        if (c1902zw == null || s != c1902zw.a) {
            n(true, i, f, f2, f3, f4, true);
        } else {
            int iB = c1847yw.b(s);
            Rect rectP = p(new RectF(f, f2, f3 + f, f4 + f2));
            if (!c1902zw.b.equals(rectP)) {
                this.a.c(this.b, iB);
                this.a.b(this.b, iB, rectP);
                this.d.c = new C1902zw(c1902zw.a, rectP);
            }
        }
        m(i);
        Dw dw = this.f;
        boolean z = ((FormFieldData) this.d.b.c.get(s)).q;
        Cw cw = dw.a;
        if (cw == null) {
            return;
        }
        if (z) {
            cw.a(32);
        } else {
            cw.a(8);
        }
    }

    @Override // org.chromium.components.autofill.AutofillProvider
    public void onFormSubmitted(int i) {
        int i2;
        k();
        C1572tw c1572tw = this.a;
        if (!c1572tw.e && !c1572tw.a()) {
            c1572tw.a.commit();
        }
        this.d = null;
        Dw dw = this.f;
        Cw cw = dw.a;
        if (cw != null) {
            cw.a(16);
        }
        dw.a();
        switch (i) {
            case 1:
                i2 = 0;
                break;
            case 2:
                i2 = 1;
                break;
            case 3:
                i2 = 2;
                break;
            case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                i2 = 3;
                break;
            case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                i2 = 4;
                break;
            case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                i2 = 5;
                break;
            default:
                i2 = 6;
                break;
        }
        It.d("Autofill.WebView.SubmissionSource", i2, 6);
    }

    @Override // org.chromium.components.autofill.AutofillProvider
    public void onTextFieldDidScroll(int i, float f, float f2, float f3, float f4) {
        C1847yw c1847yw;
        if (Build.VERSION.SDK_INT < 28 && (c1847yw = this.d) != null) {
            short s = (short) i;
            C1902zw c1902zw = c1847yw.c;
            if (c1902zw == null || s != c1902zw.a) {
                return;
            }
            int iB = c1847yw.b(s);
            Rect rectP = p(new RectF(f, f2, f3 + f, f4 + f2));
            this.a.b(this.b, iB, rectP);
            this.d.c = new C1902zw(c1902zw.a, rectP);
        }
    }

    @Override // org.chromium.components.autofill.AutofillProvider
    public void reset() {
    }

    @Override // org.chromium.components.autofill.AutofillProvider
    public void setNativeAutofillProvider(long j) {
        long j2 = this.e;
        if (j == j2) {
            return;
        }
        if (j2 != 0) {
            try {
                this.d = null;
            } catch (IllegalStateException unused) {
                return;
            }
        }
        this.e = j;
        if (j == 0) {
            C1572tw c1572tw = this.a;
            if (!c1572tw.e && !c1572tw.a()) {
                c1572tw.a.unregisterCallback(c1572tw.c);
                c1572tw.a = null;
                c1572tw.d = true;
            }
        }
    }

    @Override // org.chromium.components.autofill.AutofillProvider
    public void startAutofillSession(FormData formData, int i, float f, float f2, float f3, float f4) {
        if (Build.VERSION.SDK_INT < 28) {
            C1572tw c1572tw = this.a;
            if (!c1572tw.e && !c1572tw.a()) {
                c1572tw.a.cancel();
            }
        }
        Objects.requireNonNull(this.a);
        C1572tw.g = false;
        Rect rectP = p(new RectF(f, f2, f3 + f, f4 + f2));
        if (this.d != null) {
            l();
        }
        o(formData);
        short s = (short) i;
        C1847yw c1847yw = new C1847yw(formData, new C1902zw(s, rectP));
        this.d = c1847yw;
        this.a.b(this.b, c1847yw.b(s), rectP);
        Dw dw = this.f;
        boolean z = this.a.e;
        Boolean bool = dw.b;
        if (bool == null || bool.booleanValue() != z) {
            Lt.a.b("Autofill.WebView.Enabled", !z);
            dw.b = Boolean.valueOf(z);
        }
        if (dw.a != null) {
            dw.a();
        }
        dw.a = new Cw(null);
        this.h = System.currentTimeMillis();
    }

    public Aw(ViewGroup viewGroup, C1572tw c1572tw, Context context) {
        Trace.beginSection("AutofillProviderImpl.constructor");
        try {
            this.a = c1572tw;
            this.b = viewGroup;
            this.f = new Dw(context);
            C1792xw c1792xw = new C1792xw(this);
            this.g = c1792xw;
            C1572tw c1572tw2 = this.a;
            Objects.requireNonNull(c1572tw2);
            if (c1572tw2.f == null) {
                c1572tw2.f = new ArrayList();
            }
            c1572tw2.f.add(new WeakReference(c1792xw));
            Trace.endSection();
        } catch (Throwable th) {
            try {
                Trace.endSection();
            } catch (Throwable th2) {
                AbstractC0279Pk.a.a(th, th2);
            }
            throw th;
        }
    }
}
