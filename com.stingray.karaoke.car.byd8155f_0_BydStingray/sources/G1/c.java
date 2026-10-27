package G1;

import android.graphics.text.LineBreakConfig;
import android.text.BoringLayout;
import android.text.Layout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.inputmethod.EditorBoundsInfo;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class c {
    public static /* synthetic */ LineBreakConfig.Builder f() {
        return new LineBreakConfig.Builder();
    }

    public static /* synthetic */ BoringLayout j(CharSequence charSequence, TextPaint textPaint, int i7, Layout.Alignment alignment, float f10, float f11, BoringLayout.Metrics metrics, boolean z6, boolean z10, TextUtils.TruncateAt truncateAt, int i10) {
        return new BoringLayout(charSequence, textPaint, i7, alignment, f10, f11, metrics, z6, truncateAt, i10, z10);
    }

    public static /* synthetic */ EditorBoundsInfo.Builder m() {
        return new EditorBoundsInfo.Builder();
    }
}
