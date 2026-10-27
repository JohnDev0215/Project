package com.byd.car;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class DiCarConfig {
    public Context context;
    public boolean debuggable;

    private DiCarConfig(Builder builder) {
        this.context = builder.context;
        this.debuggable = builder.debuggable;
    }

    public static class Builder {
        private Context context;
        private boolean debuggable;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder debuggable(boolean z) {
            this.debuggable = z;
            return this;
        }

        public DiCarConfig build() {
            if (this.context == null) {
                throw new RuntimeException("context should not be null.");
            }
            return new DiCarConfig(this);
        }
    }
}
