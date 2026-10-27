package com.google.android.gms.common.util;

import androidx.annotation.NonNull;
import com.amplifyframework.core.model.ModelIdentifier;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.HashMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@KeepForSdk
public class MapUtils {
    @KeepForSdk
    public static void writeStringMapToJson(@NonNull StringBuilder sb, @NonNull HashMap<String, String> map) {
        sb.append("{");
        boolean z = true;
        for (String str : map.keySet()) {
            if (!z) {
                sb.append(",");
            }
            String str2 = map.get(str);
            sb.append(ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR);
            sb.append(str);
            sb.append("\":");
            if (str2 == null) {
                sb.append("null");
            } else {
                sb.append(ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR);
                sb.append(str2);
                sb.append(ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR);
            }
            z = false;
        }
        sb.append("}");
    }
}
