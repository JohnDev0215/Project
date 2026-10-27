package defpackage;

import android.content.Context;
import android.os.Handler;
import android.view.textclassifier.TextClassificationManager;
import android.view.textclassifier.TextClassifier;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: renamed from: nD, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1206nD {
    public ZD a;
    public WindowAndroid b;
    public C1151mD c;
    public TextClassifier d;
    public Handler e = new Handler();
    public Runnable f = new RunnableC1095lD(this);

    public C1206nD(ZD zd, WindowAndroid windowAndroid) {
        this.a = zd;
        this.b = windowAndroid;
    }

    public TextClassifier a() {
        TextClassifier textClassifier = this.d;
        if (textClassifier != null) {
            return textClassifier;
        }
        Context context = (Context) this.b.S().get();
        if (context == null) {
            return null;
        }
        return ((TextClassificationManager) context.getSystemService("textclassification")).getTextClassifier();
    }

    public final void b(int i, CharSequence charSequence, int i2, int i3) {
        TextClassifier textClassifierA = a();
        if (textClassifierA == null || textClassifierA == TextClassifier.NO_OP) {
            this.e.post(this.f);
            return;
        }
        C1151mD c1151mD = this.c;
        if (c1151mD != null) {
            c1151mD.b(false);
            this.c = null;
        }
        C1151mD c1151mD2 = new C1151mD(this, textClassifierA, i, charSequence, i2, i3);
        this.c = c1151mD2;
        c1151mD2.d(Su.g);
    }
}
