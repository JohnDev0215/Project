package androidx.work;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

/* JADX INFO: loaded from: classes2.dex */
public abstract class InputMergerFactory {
    @Nullable
    public abstract InputMerger createInputMerger(@NonNull String className);

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final InputMerger createInputMergerWithDefaultFallback(@NonNull String className) {
        InputMerger inputMergerCreateInputMerger = createInputMerger(className);
        return inputMergerCreateInputMerger == null ? InputMerger.fromClassName(className) : inputMergerCreateInputMerger;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static InputMergerFactory getDefaultInputMergerFactory() {
        return new InputMergerFactory() { // from class: androidx.work.InputMergerFactory.1
            @Override // androidx.work.InputMergerFactory
            @Nullable
            public InputMerger createInputMerger(@NonNull String className) {
                return null;
            }
        };
    }
}
