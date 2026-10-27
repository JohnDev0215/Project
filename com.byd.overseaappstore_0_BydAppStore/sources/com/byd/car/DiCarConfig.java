package com.byd.car;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class DiCarConfig {
    public Context context;
    public boolean debuggable;

    public static class Builder {
        private Context context;
        private boolean debuggable;

        public Builder(Context context) {
            this.context = context;
        }

        public DiCarConfig build() {
            if (this.context != null) {
                return new DiCarConfig(this);
            }
            throw new RuntimeException("context should not be null.");
        }

        public Builder debuggable(boolean z) {
            this.debuggable = z;
            return this;
        }
    }

    private DiCarConfig(Builder builder) {
        this.context = builder.context;
        this.debuggable = builder.debuggable;
    }
}
