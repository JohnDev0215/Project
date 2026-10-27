package android.view.textclassifier;

import android.content.Context;
import android.os.ServiceManager;
import com.android.internal.util.IndentingPrintWriter;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class TextClassificationManager {
    private static final String LOG_TAG = "androidtc";
    private static final TextClassificationConstants sDefaultSettings = new TextClassificationConstants();
    private final Context mContext;
    private TextClassifier mCustomTextClassifier;
    private TextClassificationConstants mSettings;
    private final Object mLock = new Object();
    private final TextClassificationSessionFactory mDefaultSessionFactory = new TextClassificationSessionFactory() { // from class: android.view.textclassifier.-$$Lambda$TextClassificationManager$JIaezIJbMig_-kVzN6oArzkTsJE
        @Override // android.view.textclassifier.TextClassificationSessionFactory
        public final TextClassifier createTextClassificationSession(TextClassificationContext textClassificationContext) {
            return this.f$0.lambda$new$0$TextClassificationManager(textClassificationContext);
        }
    };
    private TextClassificationSessionFactory mSessionFactory = this.mDefaultSessionFactory;

    public /* synthetic */ TextClassifier lambda$new$0$TextClassificationManager(TextClassificationContext textClassificationContext) {
        return new TextClassificationSession(textClassificationContext, getTextClassifier());
    }

    public TextClassificationManager(Context context) {
        this.mContext = (Context) Objects.requireNonNull(context);
    }

    public TextClassifier getTextClassifier() {
        synchronized (this.mLock) {
            if (this.mCustomTextClassifier != null) {
                return this.mCustomTextClassifier;
            }
            if (getSettings().isSystemTextClassifierEnabled()) {
                return getSystemTextClassifier(1);
            }
            return getLocalTextClassifier();
        }
    }

    public void setTextClassifier(TextClassifier textClassifier) {
        synchronized (this.mLock) {
            this.mCustomTextClassifier = textClassifier;
        }
    }

    public TextClassifier getTextClassifier(int i) {
        if (i == 0) {
            return getLocalTextClassifier();
        }
        return getSystemTextClassifier(i);
    }

    private TextClassificationConstants getSettings() {
        TextClassificationConstants textClassificationConstants;
        synchronized (this.mLock) {
            if (this.mSettings == null) {
                this.mSettings = new TextClassificationConstants();
            }
            textClassificationConstants = this.mSettings;
        }
        return textClassificationConstants;
    }

    public TextClassifier createTextClassificationSession(TextClassificationContext textClassificationContext) {
        Objects.requireNonNull(textClassificationContext);
        TextClassifier textClassifierCreateTextClassificationSession = this.mSessionFactory.createTextClassificationSession(textClassificationContext);
        Objects.requireNonNull(textClassifierCreateTextClassificationSession, "Session Factory should never return null");
        return textClassifierCreateTextClassificationSession;
    }

    public TextClassifier createTextClassificationSession(TextClassificationContext textClassificationContext, TextClassifier textClassifier) {
        Objects.requireNonNull(textClassificationContext);
        Objects.requireNonNull(textClassifier);
        return new TextClassificationSession(textClassificationContext, textClassifier);
    }

    public void setTextClassificationSessionFactory(TextClassificationSessionFactory textClassificationSessionFactory) {
        synchronized (this.mLock) {
            if (textClassificationSessionFactory != null) {
                this.mSessionFactory = textClassificationSessionFactory;
            } else {
                this.mSessionFactory = this.mDefaultSessionFactory;
            }
        }
    }

    private TextClassifier getSystemTextClassifier(int i) {
        synchronized (this.mLock) {
            if (getSettings().isSystemTextClassifierEnabled()) {
                try {
                    Log.d("androidtc", "Initializing SystemTextClassifier, type = " + TextClassifier.typeToString(i));
                    return new SystemTextClassifier(this.mContext, getSettings(), i == 2);
                } catch (ServiceManager.ServiceNotFoundException e) {
                    Log.e("androidtc", "Could not initialize SystemTextClassifier", e);
                }
            }
            return TextClassifier.NO_OP;
        }
    }

    private TextClassifier getLocalTextClassifier() {
        Log.d("androidtc", "Local text-classifier not supported. Returning a no-op text-classifier.");
        return TextClassifier.NO_OP;
    }

    public void dump(IndentingPrintWriter indentingPrintWriter) {
        getSystemTextClassifier(2).dump(indentingPrintWriter);
        getSystemTextClassifier(1).dump(indentingPrintWriter);
        getSettings().dump(indentingPrintWriter);
    }

    public static TextClassificationConstants getSettings(Context context) {
        Objects.requireNonNull(context);
        TextClassificationManager textClassificationManager = (TextClassificationManager) context.getSystemService(TextClassificationManager.class);
        if (textClassificationManager != null) {
            return textClassificationManager.getSettings();
        }
        return sDefaultSettings;
    }
}
