package com.byd.bluetoothcall.d;

import android.widget.TextView;

/* JADX INFO: compiled from: TextViewTextChangeEvent.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private TextView f3260a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private CharSequence f3261b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f3262c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3263d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3264e;

    public c(TextView textView, CharSequence charSequence, int i, int i2, int i3) {
        this.f3260a = textView;
        this.f3261b = charSequence;
        this.f3262c = i;
        this.f3263d = i2;
        this.f3264e = i3;
    }

    public static c a(TextView textView, CharSequence charSequence, int i, int i2, int i3) {
        return new c(textView, charSequence, i, i2, i3);
    }

    public CharSequence b() {
        return this.f3261b;
    }
}
