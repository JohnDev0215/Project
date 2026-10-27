package com.google.protobuf;

import p.hf2;

/* JADX INFO: loaded from: classes.dex */
class Utf8$UnpairedSurrogateException extends IllegalArgumentException {
    public Utf8$UnpairedSurrogateException(int i, int i2) {
        super(hf2.c("Unpaired surrogate at index ", i, " of ", i2));
    }
}
