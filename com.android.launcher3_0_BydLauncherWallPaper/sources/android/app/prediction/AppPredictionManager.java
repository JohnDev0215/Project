package android.app.prediction;

import android.annotation.SystemApi;
import android.content.Context;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
@SystemApi
public final class AppPredictionManager {
    private final Context mContext;

    public AppPredictionManager(Context context) {
        this.mContext = (Context) Objects.requireNonNull(context);
    }

    public AppPredictor createAppPredictionSession(AppPredictionContext appPredictionContext) {
        return new AppPredictor(this.mContext, appPredictionContext);
    }
}
