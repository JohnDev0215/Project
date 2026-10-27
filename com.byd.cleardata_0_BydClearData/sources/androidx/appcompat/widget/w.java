package androidx.appcompat.widget;

import android.view.textclassifier.TextClassificationManager;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
final class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private TextView f1046a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private TextClassifier f1047b;

    w(TextView textView) {
        this.f1046a = (TextView) a.g.j.h.c(textView);
    }

    public TextClassifier a() {
        TextClassifier textClassifier = this.f1047b;
        if (textClassifier != null) {
            return textClassifier;
        }
        TextClassificationManager textClassificationManager = (TextClassificationManager) this.f1046a.getContext().getSystemService(TextClassificationManager.class);
        return textClassificationManager != null ? textClassificationManager.getTextClassifier() : TextClassifier.NO_OP;
    }

    public void b(TextClassifier textClassifier) {
        this.f1047b = textClassifier;
    }
}
