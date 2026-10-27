package androidx.work;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class InputMerger {
    private static final String TAG = Logger.tagWithPrefix("InputMerger");

    @NonNull
    public abstract Data merge(@NonNull List<Data> inputs);

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static InputMerger fromClassName(String className) {
        try {
            return (InputMerger) Class.forName(className).newInstance();
        } catch (Exception e) {
            Logger.get().error(TAG, "Trouble instantiating + " + className, e);
            return null;
        }
    }
}
