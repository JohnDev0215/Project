package defpackage;

import android.content.Context;
import android.util.Log;

/* JADX INFO: renamed from: eh, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0727eh {
    public static C1061kg a(Context context, String str, boolean z) {
        String str2 = String.format("%s.%s", "com.google.android.gms.vision", str);
        if (!z) {
            str2 = "com.google.android.gms.vision.dynamite";
        }
        try {
            Object[] objArr = {str2};
            if (Log.isLoggable("Vision", 3)) {
                String.format("Loading module %s", objArr);
            }
            return C1061kg.c(context, z ? C1061kg.i : C1061kg.j, str2);
        } catch (C0726eg e) {
            Object[] objArr2 = {str2, Boolean.valueOf(z)};
            if (!Log.isLoggable("Vision", 6)) {
                return null;
            }
            if (Log.isLoggable("Vision", 3)) {
                Log.e("Vision", String.format("Error loading module %s optional module %b", objArr2), e);
                return null;
            }
            String str3 = String.format("Error loading module %s optional module %b", objArr2);
            String strValueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(strValueOf.length() + String.valueOf(str3).length() + 2);
            sb.append(str3);
            sb.append(": ");
            sb.append(strValueOf);
            Log.e("Vision", sb.toString());
            return null;
        }
    }
}
