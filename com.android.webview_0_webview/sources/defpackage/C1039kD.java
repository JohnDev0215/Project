package defpackage;

import android.content.Context;
import android.view.textclassifier.SelectionEvent;
import android.view.textclassifier.TextClassification;
import android.view.textclassifier.TextClassificationContext;
import android.view.textclassifier.TextClassificationManager;
import android.view.textclassifier.TextClassifier;
import android.view.textclassifier.TextSelection;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: renamed from: kD, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1039kD {
    public WindowAndroid a;
    public TextClassifier b;
    public C0481aD c;

    public C1039kD(WindowAndroid windowAndroid) {
        this.a = windowAndroid;
    }

    public static C1039kD a(WindowAndroid windowAndroid) {
        if (windowAndroid.S().get() == null) {
            return null;
        }
        return new C1039kD(windowAndroid);
    }

    private TextClassifier b(Context context, boolean z) {
        return ((TextClassificationManager) context.getSystemService("textclassification")).createTextClassificationSession(new TextClassificationContext.Builder(context.getPackageName(), z ? "edit-webview" : "webview").build());
    }

    private void c() {
        TextClassifier textClassifier = this.b;
        if (textClassifier == null || textClassifier.isDestroyed()) {
            return;
        }
        this.b.destroy();
        this.b = null;
    }

    public void d(SelectionEvent selectionEvent) {
        this.b.onSelectionEvent(selectionEvent);
    }

    public void e(String str, int i, int i2, YD yd) {
        TextClassification textClassification;
        if (this.b == null) {
            return;
        }
        if (!this.c.e(str, i)) {
            c();
            return;
        }
        int[] iArr = new int[2];
        if (!this.c.c(i, str.length() + i, iArr)) {
            c();
            return;
        }
        if (yd == null || (textClassification = yd.g) == null) {
            d(SelectionEvent.createSelectionActionEvent(iArr[0], iArr[1], i2));
        } else {
            d(SelectionEvent.createSelectionActionEvent(iArr[0], iArr[1], i2, textClassification));
        }
        if (SelectionEvent.isTerminal(i2)) {
            c();
        }
    }

    public void f(String str, int i, YD yd) {
        TextClassification textClassification;
        TextSelection textSelection;
        if (this.b == null) {
            return;
        }
        if (!this.c.e(str, i)) {
            c();
            return;
        }
        int[] iArr = new int[2];
        if (!this.c.c(i, str.length() + i, iArr)) {
            c();
            return;
        }
        if (yd != null && (textSelection = yd.h) != null) {
            d(SelectionEvent.createSelectionModifiedEvent(iArr[0], iArr[1], textSelection));
        } else if (yd == null || (textClassification = yd.g) == null) {
            d(SelectionEvent.createSelectionModifiedEvent(iArr[0], iArr[1]));
        } else {
            d(SelectionEvent.createSelectionModifiedEvent(iArr[0], iArr[1], textClassification));
        }
    }

    public void g(String str, int i, boolean z) {
        Context context = (Context) this.a.S().get();
        if (context == null) {
            return;
        }
        this.b = b(context, z);
        C0481aD c0481aD = new C0481aD();
        this.c = c0481aD;
        c0481aD.e(str, i);
        this.c.e = i;
        d(SelectionEvent.createSelectionStartedEvent(1, 0));
    }
}
