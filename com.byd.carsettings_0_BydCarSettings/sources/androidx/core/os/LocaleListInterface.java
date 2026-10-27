package androidx.core.os;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.transformation.FabTransformationScrimBehavior;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public interface LocaleListInterface {
    Locale get(int i);

    @Nullable
    Locale getFirstMatch(@NonNull String[] strArr);

    Object getLocaleList();

    @IntRange(from = -1)
    int indexOf(Locale locale);

    boolean isEmpty();

    @IntRange(from = FabTransformationScrimBehavior.COLLAPSE_DELAY)
    int size();

    String toLanguageTags();
}
