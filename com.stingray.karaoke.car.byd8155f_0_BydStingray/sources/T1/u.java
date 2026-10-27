package T1;

import android.text.Editable;
import android.text.SpanWatcher;
import android.text.Spannable;
import android.text.TextWatcher;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class u implements TextWatcher, SpanWatcher {
    public final Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final AtomicInteger f8522m = new AtomicInteger(0);

    public u(Object obj) {
        this.l = obj;
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        ((TextWatcher) this.l).afterTextChanged(editable);
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i7, int i10, int i11) {
        ((TextWatcher) this.l).beforeTextChanged(charSequence, i7, i10, i11);
    }

    @Override // android.text.SpanWatcher
    public final void onSpanAdded(Spannable spannable, Object obj, int i7, int i10) {
        if (this.f8522m.get() <= 0 || !(obj instanceof x)) {
            ((SpanWatcher) this.l).onSpanAdded(spannable, obj, i7, i10);
        }
    }

    @Override // android.text.SpanWatcher
    public final void onSpanChanged(Spannable spannable, Object obj, int i7, int i10, int i11, int i12) {
        if (this.f8522m.get() <= 0 || !(obj instanceof x)) {
            ((SpanWatcher) this.l).onSpanChanged(spannable, obj, i7, i10, i11, i12);
        }
    }

    @Override // android.text.SpanWatcher
    public final void onSpanRemoved(Spannable spannable, Object obj, int i7, int i10) {
        if (this.f8522m.get() <= 0 || !(obj instanceof x)) {
            ((SpanWatcher) this.l).onSpanRemoved(spannable, obj, i7, i10);
        }
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i7, int i10, int i11) {
        ((TextWatcher) this.l).onTextChanged(charSequence, i7, i10, i11);
    }
}
