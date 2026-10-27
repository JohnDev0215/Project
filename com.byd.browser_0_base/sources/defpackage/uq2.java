package defpackage;

import android.graphics.Bitmap;
import android.util.ArrayMap;
import androidx.browser.customtabs.CustomTabsSessionToken;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: compiled from: SplashImageHolder.java */
/* JADX INFO: loaded from: classes.dex */
public final class uq2 {
    public final Map<CustomTabsSessionToken, Bitmap> a = Collections.synchronizedMap(new ArrayMap());
}
