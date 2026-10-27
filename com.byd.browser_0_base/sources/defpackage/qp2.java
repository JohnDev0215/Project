package defpackage;

import android.annotation.SuppressLint;
import android.app.RemoteAction;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.LocaleList;
import android.util.Log;
import android.view.textclassifier.TextClassification;
import android.view.textclassifier.TextClassificationManager;
import android.view.textclassifier.TextClassifier;
import android.view.textclassifier.TextSelection;
import defpackage.ci2;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: SmartSelectionProvider.java */
/* JADX INFO: loaded from: classes3.dex */
public final class qp2 {
    public final ci2.b a;
    public WindowAndroid b;
    public a c;
    public final Handler d;
    public final pp2 e;
    public final np2 f;

    /* JADX INFO: compiled from: SmartSelectionProvider.java */
    public class a extends i9<ci2.a> {
        public final TextClassifier h;
        public final int i;
        public final CharSequence j;
        public final int k;
        public final int l;
        public final Context m;

        public a(TextClassifier textClassifier, int i, CharSequence charSequence, int i2, int i3, Context context) {
            this.h = textClassifier;
            this.i = i;
            this.j = charSequence;
            this.k = i2;
            this.l = i3;
            this.m = context;
        }

        @Override // defpackage.i9
        public final ci2.a b() {
            TextSelection textSelection;
            try {
                int i = this.i;
                TextClassifier textClassifier = this.h;
                CharSequence charSequence = this.j;
                int i2 = this.k;
                int i3 = this.l;
                if (i == 1) {
                    int i4 = Build.VERSION.SDK_INT;
                    TextSelection textSelectionSuggestSelection = i4 >= 31 ? textClassifier.suggestSelection(new TextSelection.Request.Builder(charSequence, i2, i3).setDefaultLocales(LocaleList.getAdjustedDefault()).setIncludeTextClassification(true).build()) : textClassifier.suggestSelection(charSequence, i2, i3, LocaleList.getAdjustedDefault());
                    int iMax = Math.max(0, textSelectionSuggestSelection.getSelectionStartIndex());
                    int iMin = Math.min(charSequence.length(), textSelectionSuggestSelection.getSelectionEndIndex());
                    if (i()) {
                        return new ci2.a();
                    }
                    textClassificationClassifyText = i4 >= 31 ? textSelectionSuggestSelection.getTextClassification() : null;
                    textSelection = textSelectionSuggestSelection;
                    i2 = iMax;
                    i3 = iMin;
                } else {
                    textSelection = null;
                }
                if (textClassificationClassifyText == null) {
                    textClassificationClassifyText = textClassifier.classifyText(charSequence, i2, i3, LocaleList.getAdjustedDefault());
                }
                return o(i2, i3, textClassificationClassifyText, textSelection);
            } catch (IllegalStateException e) {
                Log.e("cr_SmartSelProvider", "Failed to use text classifier for smart selection", e);
                return new ci2.a();
            }
        }

        @Override // defpackage.i9
        public final void l(ci2.a aVar) {
            ((SelectionPopupControllerImpl.b) qp2.this.a).a(aVar);
        }

        public final ci2.a o(int i, int i2, TextClassification textClassification, TextSelection textSelection) {
            ArrayList arrayList;
            ci2.a aVar = new ci2.a();
            aVar.a = i - this.k;
            aVar.b = i2 - this.l;
            aVar.c = textClassification.getLabel();
            aVar.d = textClassification.getIcon();
            aVar.e = textClassification.getIntent();
            aVar.f = textClassification.getOnClickListener();
            aVar.h = textSelection;
            aVar.g = textClassification;
            Context context = this.m;
            if (context != null) {
                arrayList = new ArrayList();
                Iterator<RemoteAction> it = textClassification.getActions().iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next().getIcon().loadDrawable(context));
                }
            } else {
                arrayList = null;
            }
            aVar.i = arrayList;
            return aVar;
        }
    }

    public qp2(SelectionPopupControllerImpl.b bVar, WebContents webContents, np2 np2Var) {
        this.a = bVar;
        this.b = webContents.V();
        kt3 kt3VarE = kt3.e(webContents);
        if (kt3VarE != null) {
            kt3VarE.b(new op2(this));
        }
        this.d = new Handler();
        this.e = new pp2(this);
        this.f = np2Var;
    }

    @SuppressLint({"WrongConstant"})
    public final TextClassifier a() {
        Context context;
        WindowAndroid windowAndroid = this.b;
        if (windowAndroid == null || (context = windowAndroid.i.get()) == null) {
            return null;
        }
        return ((TextClassificationManager) context.getSystemService("textclassification")).getTextClassifier();
    }

    public final void b(int i, int i2, int i3, CharSequence charSequence) {
        TextClassifier textClassifier;
        TextClassifier textClassifierA;
        WindowAndroid windowAndroid = this.b;
        if (windowAndroid == null || windowAndroid.i.get() == null) {
            textClassifier = null;
        } else {
            np2 np2Var = this.f;
            if (np2Var == null || (textClassifierA = np2Var.b) == null || textClassifierA.isDestroyed()) {
                textClassifierA = a();
            }
            textClassifier = textClassifierA;
        }
        if (textClassifier == null || textClassifier == TextClassifier.NO_OP) {
            this.d.post(this.e);
            return;
        }
        a aVar = this.c;
        if (aVar != null) {
            aVar.a(false);
            this.c = null;
        }
        a aVar2 = new a(textClassifier, i, charSequence, i2, i3, this.b.i.get());
        this.c = aVar2;
        aVar2.c(i9.f);
    }
}
