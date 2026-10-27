package r;

import android.view.textclassifier.TextClassificationManager;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;

/* JADX INFO: compiled from: AppCompatTextClassifierHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public TextView f7583a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public TextClassifier f7584b;

    public l(TextView textView) {
        this.f7583a = (TextView) n0.h.e(textView);
    }

    public TextClassifier a() {
        TextClassifier textClassifier = this.f7584b;
        if (textClassifier != null) {
            return textClassifier;
        }
        TextClassificationManager textClassificationManager = (TextClassificationManager) this.f7583a.getContext().getSystemService(TextClassificationManager.class);
        return textClassificationManager != null ? textClassificationManager.getTextClassifier() : TextClassifier.NO_OP;
    }

    public void b(TextClassifier textClassifier) {
        this.f7584b = textClassifier;
    }
}
