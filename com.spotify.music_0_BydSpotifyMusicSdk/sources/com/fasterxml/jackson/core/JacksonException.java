package com.fasterxml.jackson.core;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public abstract class JacksonException extends IOException {
    private static final long serialVersionUID = 123;

    public abstract JsonLocation a();

    public abstract String b();
}
