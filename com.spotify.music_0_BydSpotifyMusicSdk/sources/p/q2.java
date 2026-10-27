package p;

import android.media.session.MediaSessionManager;
import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.TextPaint;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class q2 {
    public static /* synthetic */ PrecomputedText.Params.Builder j(TextPaint textPaint) {
        return new PrecomputedText.Params.Builder(textPaint);
    }

    public static /* synthetic */ void w(String str, int i, int i2) {
        new MediaSessionManager.RemoteUserInfo(str, i, i2);
    }

    public static /* bridge */ /* synthetic */ boolean x(Spannable spannable) {
        return spannable instanceof PrecomputedText;
    }
}
