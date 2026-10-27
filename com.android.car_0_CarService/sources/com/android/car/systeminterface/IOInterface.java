package com.android.car.systeminterface;

import android.content.Context;
import java.io.File;

/* JADX INFO: loaded from: classes3.dex */
public interface IOInterface {
    File getSystemCarDir();

    public static class DefaultImpl implements IOInterface {
        private final File mSystemCarDir = new File("/data/system/car");

        DefaultImpl(Context context) {
        }

        @Override // com.android.car.systeminterface.IOInterface
        public File getSystemCarDir() {
            return this.mSystemCarDir;
        }
    }
}
