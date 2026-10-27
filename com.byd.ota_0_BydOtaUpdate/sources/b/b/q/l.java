package b.b.q;

import android.view.textclassifier.TextClassificationManager;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;

/* JADX INFO: compiled from: AppCompatTextClassifierHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f1067a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TextClassifier f1068b;

    public l(TextView textView) {
        this.f1067a = (TextView) b.f.l.f.b(textView);
    }

    public TextClassifier a() {
        TextClassifier textClassifier = this.f1068b;
        if (textClassifier != null) {
            return textClassifier;
        }
        TextClassificationManager textClassificationManager = (TextClassificationManager) this.f1067a.getContext().getSystemService(TextClassificationManager.class);
        return textClassificationManager != null ? textClassificationManager.getTextClassifier() : TextClassifier.NO_OP;
    }

    public void b(TextClassifier textClassifier) {
        this.f1068b = textClassifier;
    }
}
