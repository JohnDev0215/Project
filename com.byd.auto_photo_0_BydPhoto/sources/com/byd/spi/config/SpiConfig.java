package com.byd.spi.config;

/* JADX INFO: loaded from: classes2.dex */
public class SpiConfig {
    public IpcConfig ipc;

    public static class Builder {
        private IpcConfig ipc;

        public SpiConfig build() {
            return new SpiConfig(this);
        }

        public Builder setIpcConfig(IpcConfig ipcConfig) {
            this.ipc = ipcConfig;
            return this;
        }
    }

    private SpiConfig(Builder builder) {
        this.ipc = builder.ipc;
    }
}
