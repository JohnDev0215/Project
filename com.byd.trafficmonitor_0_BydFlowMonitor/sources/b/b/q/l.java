package b.b.q;

import android.view.textclassifier.TextClassificationManager;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;

/* JADX INFO: compiled from: AppCompatTextClassifierHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f1782a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TextClassifier f1783b;

    public l(TextView textView) {
        this.f1782a = (TextView) b.h.l.g.c(textView);
    }

    public TextClassifier a() {
        TextClassifier textClassifier = this.f1783b;
        if (textClassifier != null) {
            return textClassifier;
        }
        TextClassificationManager textClassificationManager = (TextClassificationManager) this.f1782a.getContext().getSystemService(TextClassificationManager.class);
        return textClassificationManager != null ? textClassificationManager.getTextClassifier() : TextClassifier.NO_OP;
    }

    public void b(TextClassifier textClassifier) {
        this.f1783b = textClassifier;
    }
}
