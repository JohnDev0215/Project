package defpackage;

import android.view.textclassifier.SelectionEvent;
import android.view.textclassifier.TextClassification;
import android.view.textclassifier.TextClassifier;
import android.view.textclassifier.TextSelection;
import defpackage.ci2;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: SmartSelectionEventProcessor.java */
/* JADX INFO: loaded from: classes3.dex */
public final class np2 {
    public WindowAndroid a;
    public TextClassifier b;
    public di2 c;

    /* JADX INFO: compiled from: SmartSelectionEventProcessor.java */
    public class a implements jt3 {
        public a() {
        }

        @Override // defpackage.jt3
        public final void a(WindowAndroid windowAndroid) {
            np2.this.a = windowAndroid;
        }
    }

    public np2(WebContents webContents) {
        this.a = webContents.V();
        kt3 kt3VarE = kt3.e(webContents);
        if (kt3VarE != null) {
            kt3VarE.b(new a());
        }
    }

    public final void a() {
        TextClassifier textClassifier = this.b;
        if (textClassifier == null || textClassifier.isDestroyed()) {
            return;
        }
        this.b.destroy();
        this.b = null;
    }

    public final void b(SelectionEvent selectionEvent) {
        this.b.onSelectionEvent(selectionEvent);
    }

    public final void c(String str, int i, int i2, ci2.a aVar) {
        TextClassification textClassification;
        if (this.b == null) {
            return;
        }
        if (!this.c.c(i, str)) {
            a();
            return;
        }
        int[] iArr = new int[2];
        if (!this.c.a(i, str.length() + i, iArr)) {
            a();
            return;
        }
        if (aVar == null || (textClassification = aVar.g) == null) {
            b(SelectionEvent.createSelectionActionEvent(iArr[0], iArr[1], i2));
        } else {
            b(SelectionEvent.createSelectionActionEvent(iArr[0], iArr[1], i2, textClassification));
        }
        if (SelectionEvent.isTerminal(i2)) {
            a();
        }
    }

    public final void d(String str, int i, ci2.a aVar) {
        TextClassification textClassification;
        TextSelection textSelection;
        if (this.b == null) {
            return;
        }
        if (!this.c.c(i, str)) {
            a();
            return;
        }
        int[] iArr = new int[2];
        if (!this.c.a(i, str.length() + i, iArr)) {
            a();
            return;
        }
        if (aVar != null && (textSelection = aVar.h) != null) {
            b(SelectionEvent.createSelectionModifiedEvent(iArr[0], iArr[1], textSelection));
        } else if (aVar == null || (textClassification = aVar.g) == null) {
            b(SelectionEvent.createSelectionModifiedEvent(iArr[0], iArr[1]));
        } else {
            b(SelectionEvent.createSelectionModifiedEvent(iArr[0], iArr[1], textClassification));
        }
    }
}
