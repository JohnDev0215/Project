package p;

import android.text.StaticLayout;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final class ka extends ja {
    @Override // p.ja, p.la
    public void a(StaticLayout.Builder builder, TextView textView) {
        builder.setTextDirection(textView.getTextDirectionHeuristic());
    }

    @Override // p.la
    public boolean b(TextView textView) {
        return textView.isHorizontallyScrollable();
    }
}
