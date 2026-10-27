package com.byd.bluetoothcall.d;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import d.a.i;

/* JADX INFO: compiled from: TextViewTextChangeEventObservable.java */
/* JADX INFO: loaded from: classes.dex */
final class d extends com.byd.bluetoothcall.d.a<c> {

    /* JADX INFO: renamed from: b */
    private final TextView f3265b;

    /* JADX INFO: compiled from: TextViewTextChangeEventObservable.java */
    static final class a extends d.a.l.a implements TextWatcher {

        /* JADX INFO: renamed from: c */
        private final TextView f3266c;

        /* JADX INFO: renamed from: d */
        private final i<? super c> f3267d;

        a(TextView textView, i<? super c> iVar) {
            this.f3266c = textView;
            this.f3267d = iVar;
        }

        @Override // d.a.l.a
        protected void a() {
            this.f3266c.removeTextChangedListener(this);
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (isDisposed()) {
                return;
            }
            this.f3267d.onNext(c.a(this.f3266c, charSequence, i, i2, i3));
        }
    }

    d(TextView textView) {
        this.f3265b = textView;
    }

    @Override // com.byd.bluetoothcall.d.a
    protected void s(i<? super c> iVar) {
        a aVar = new a(this.f3265b, iVar);
        iVar.onSubscribe(aVar);
        this.f3265b.addTextChangedListener(aVar);
    }

    @Override // com.byd.bluetoothcall.d.a
    /* JADX INFO: renamed from: t */
    public c r() {
        TextView textView = this.f3265b;
        return c.a(textView, textView.getText(), 0, 0, 0);
    }
}
