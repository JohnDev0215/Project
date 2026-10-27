package com.facebook.soloader;

import android.os.StrictMode;
import java.io.File;
import java.io.IOException;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes6.dex */
public abstract class SoSource {
    public abstract int loadLibrary(String str, int i, StrictMode.ThreadPolicy threadPolicy) throws IOException;

    protected void prepare(int i) throws IOException {
    }

    @Nullable
    public abstract File unpackLibrary(String str) throws IOException;

    public String toString() {
        return getClass().getName();
    }
}
