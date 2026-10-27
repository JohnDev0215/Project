package i5;

import android.content.Context;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

/* JADX INFO: compiled from: CustomClickSpan.java */
/* JADX INFO: loaded from: classes2.dex */
public class b extends ClickableSpan {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Context f5504d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f5505e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f5506f;

    public b(Context context, int i8, boolean z7) {
        this.f5504d = context;
        this.f5505e = i8;
        this.f5506f = z7;
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(View view) {
    }

    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        textPaint.setColor(this.f5505e);
        textPaint.setUnderlineText(this.f5506f);
    }
}
