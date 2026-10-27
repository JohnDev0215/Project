package defpackage;

import android.provider.Settings;
import android.text.TextUtils;
import android.util.Pair;
import java.util.Map;
import org.chromium.base.CollectionUtil;

/* JADX INFO: compiled from: IMEFeedbackSource.java */
/* JADX INFO: loaded from: classes.dex */
public final class dw0 implements gm0 {
    @Override // defpackage.gm0
    public final Map<String, String> c() {
        String string = Settings.Secure.getString(xz.a.getContentResolver(), "default_input_method");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return CollectionUtil.d(Pair.create("Default IME", string));
    }
}
