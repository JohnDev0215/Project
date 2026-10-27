package androidx.appcompat.widget;

import android.view.textclassifier.TextClassificationManager;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;

/* JADX INFO: compiled from: AppCompatTextClassifierHelper.java */
/* JADX INFO: loaded from: classes.dex */
final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private TextView f628a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private TextClassifier f629b;

    l(TextView textView) {
        this.f628a = (TextView) b.g.j.h.c(textView);
    }

    public TextClassifier a() {
        TextClassifier textClassifier = this.f629b;
        if (textClassifier != null) {
            return textClassifier;
        }
        TextClassificationManager textClassificationManager = (TextClassificationManager) this.f628a.getContext().getSystemService(TextClassificationManager.class);
        return textClassificationManager != null ? textClassificationManager.getTextClassifier() : TextClassifier.NO_OP;
    }

    public void b(TextClassifier textClassifier) {
        this.f629b = textClassifier;
    }
}
