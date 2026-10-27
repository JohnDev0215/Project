package androidx.autofill.inline;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.autofill.inline.UiVersions;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(api = 30)
@RestrictTo({RestrictTo.Scope.LIBRARY})
public final class VersionUtils {
    private static final String KEY_INLINE_UI_VERSIONS = "androidx.autofill.inline.ui.version:key";

    public static boolean isVersionSupported(@Nullable String str) {
        return UiVersions.getUiVersions().contains(str);
    }

    @NonNull
    public static List<String> getSupportedVersions(@NonNull Bundle bundle) {
        ArrayList arrayList = new ArrayList();
        ArrayList<String> stringArrayList = bundle.getStringArrayList(KEY_INLINE_UI_VERSIONS);
        if (stringArrayList != null) {
            for (String str : stringArrayList) {
                if (isVersionSupported(str)) {
                    arrayList.add(str);
                }
            }
        }
        return arrayList;
    }

    public static void writeSupportedVersions(@NonNull Bundle bundle) {
        bundle.putStringArrayList(KEY_INLINE_UI_VERSIONS, new ArrayList<>(UiVersions.getUiVersions()));
    }

    public static void writeStylesToBundle(@NonNull List<UiVersions.Style> list, @NonNull Bundle bundle) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (UiVersions.Style style : list) {
            String version = style.getVersion();
            arrayList.add(style.getVersion());
            bundle.putBundle(version, style.getBundle());
        }
        bundle.putStringArrayList(KEY_INLINE_UI_VERSIONS, arrayList);
    }

    @Nullable
    public static Bundle readStyleByVersion(@NonNull Bundle bundle, @NonNull String str) {
        return bundle.getBundle(str);
    }

    private VersionUtils() {
    }
}
