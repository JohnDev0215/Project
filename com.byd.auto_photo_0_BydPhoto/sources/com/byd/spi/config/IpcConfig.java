package com.byd.spi.config;

import android.net.Uri;

/* JADX INFO: loaded from: classes2.dex */
public class IpcConfig {
    public Uri contentUri;

    public static class Builder {
        private Uri contentUri;

        public IpcConfig build() {
            return new IpcConfig(this);
        }

        public Builder setContentUri(Uri uri) {
            this.contentUri = uri;
            return this;
        }

        public Builder setContentUri(String str) {
            this.contentUri = Uri.parse(str);
            return this;
        }
    }

    private IpcConfig(Builder builder) {
        this.contentUri = builder.contentUri;
    }
}
