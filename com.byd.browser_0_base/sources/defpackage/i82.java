package defpackage;

import android.app.Activity;
import android.content.Context;
import android.os.Process;
import android.text.DynamicLayout;
import android.text.Layout;
import android.text.TextPaint;
import android.text.TextUtils;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: QrCodeShareMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class i82 {
    public final Context a;
    public final PropertyModel b;
    public WindowAndroid c;
    public int d;
    public boolean e;
    public final String f;
    public final Runnable g;

    /* JADX INFO: compiled from: QrCodeShareMediator.java */
    public class a extends DynamicLayout {
        public final int a;

        public a(String str, TextPaint textPaint, int i, Layout.Alignment alignment) {
            super(str, str, textPaint, i, alignment, 1.0f, 0.0f, true, TextUtils.TruncateAt.END, i);
            this.a = 2;
        }

        @Override // android.text.DynamicLayout, android.text.Layout
        public final int getEllipsisCount(int i) {
            return (i != this.a + (-1) || super.getLineCount() + (-2) <= i) ? 0 : 3;
        }

        @Override // android.text.DynamicLayout, android.text.Layout
        public final int getEllipsisStart(int i) {
            if (i != this.a - 1 || super.getLineCount() - 2 <= i) {
                return 0;
            }
            return (getLineEnd(i) - getLineStart(i)) - 1;
        }

        @Override // android.text.DynamicLayout, android.text.Layout
        public final int getLineCount() {
            int lineCount = super.getLineCount() - 1;
            int i = this.a;
            return lineCount > i ? i : super.getLineCount() - 1;
        }
    }

    public i82(Activity activity, PropertyModel propertyModel, eu1 eu1Var, String str, WindowAndroid windowAndroid) {
        this.a = activity;
        this.b = propertyModel;
        this.g = eu1Var;
        this.f = str;
        sp.a().f(new f52(5, this));
        this.c = windowAndroid;
        a();
    }

    public final void a() {
        WindowAndroid windowAndroid;
        Boolean boolValueOf;
        PropertyModel.n nVar = m82.d;
        Boolean boolValueOf2 = (Boolean.valueOf(yj.a() ^ true).booleanValue() && (windowAndroid = this.c) != null) ? Boolean.valueOf(windowAndroid.canRequestPermission("android.permission.WRITE_EXTERNAL_STORAGE")) : Boolean.FALSE;
        boolean zBooleanValue = boolValueOf2.booleanValue();
        PropertyModel propertyModel = this.b;
        propertyModel.l(nVar, zBooleanValue);
        PropertyModel.n nVar2 = m82.c;
        if (Boolean.valueOf(!yj.a()).booleanValue()) {
            boolValueOf = Boolean.valueOf(this.a.checkPermission("android.permission.WRITE_EXTERNAL_STORAGE", Process.myPid(), Process.myUid()) == 0);
        } else {
            boolValueOf = Boolean.TRUE;
        }
        propertyModel.l(nVar2, boolValueOf.booleanValue());
    }
}
