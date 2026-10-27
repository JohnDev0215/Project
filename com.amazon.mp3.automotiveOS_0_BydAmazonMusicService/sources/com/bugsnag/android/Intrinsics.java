package com.bugsnag.android;

/* JADX INFO: loaded from: classes5.dex */
class Intrinsics {
    Intrinsics() {
    }

    static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }
}
