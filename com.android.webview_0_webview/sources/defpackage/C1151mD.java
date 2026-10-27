package defpackage;

import android.os.LocaleList;
import android.view.textclassifier.TextClassification;
import android.view.textclassifier.TextClassifier;
import android.view.textclassifier.TextSelection;

/* JADX INFO: renamed from: mD, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1151mD extends Su {
    public final TextClassifier h;
    public final int i;
    public final CharSequence j;
    public final int k;
    public final int l;
    public final /* synthetic */ C1206nD m;

    public C1151mD(C1206nD c1206nD, TextClassifier textClassifier, int i, CharSequence charSequence, int i2, int i3) {
        this.m = c1206nD;
        this.h = textClassifier;
        this.i = i;
        this.j = charSequence;
        this.k = i2;
        this.l = i3;
    }

    @Override // defpackage.Su
    public Object c() {
        TextSelection textSelection;
        int i = this.k;
        int i2 = this.l;
        if (this.i == 1) {
            TextSelection textSelectionSuggestSelection = this.h.suggestSelection(this.j, i, i2, LocaleList.getAdjustedDefault());
            int iMax = Math.max(0, textSelectionSuggestSelection.getSelectionStartIndex());
            int iMin = Math.min(this.j.length(), textSelectionSuggestSelection.getSelectionEndIndex());
            if (j()) {
                return new YD();
            }
            textSelection = textSelectionSuggestSelection;
            i = iMax;
            i2 = iMin;
        } else {
            textSelection = null;
        }
        TextClassification textClassificationClassifyText = this.h.classifyText(this.j, i, i2, LocaleList.getAdjustedDefault());
        YD yd = new YD();
        yd.a = i - this.k;
        yd.b = i2 - this.l;
        yd.c = textClassificationClassifyText.getLabel();
        yd.d = textClassificationClassifyText.getIcon();
        yd.e = textClassificationClassifyText.getIntent();
        yd.f = textClassificationClassifyText.getOnClickListener();
        yd.h = textSelection;
        yd.g = textClassificationClassifyText;
        return yd;
    }

    @Override // defpackage.Su
    public void n(Object obj) {
        ((C0872hD) this.m.a).a((YD) obj);
    }
}
