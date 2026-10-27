package defpackage;

import android.widget.NumberPicker;
import java.util.Locale;

/* JADX INFO: renamed from: xC, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1754xC implements NumberPicker.Formatter {
    public final String a;

    public C1754xC(String str) {
        this.a = str;
    }

    @Override // android.widget.NumberPicker.Formatter
    public String format(int i) {
        return String.format(Locale.getDefault(), this.a, Integer.valueOf(i));
    }
}
