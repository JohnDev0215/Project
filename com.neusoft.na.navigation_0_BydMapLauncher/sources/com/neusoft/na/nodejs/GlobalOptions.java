package com.neusoft.na.nodejs;

/* JADX INFO: loaded from: classes2.dex */
public class GlobalOptions {
    final String nodePath;

    public static class Builder {
        private String mNodePath;

        public Builder nodePath(String str) {
            this.mNodePath = str;
            return this;
        }

        public GlobalOptions build() {
            return new GlobalOptions(this.mNodePath);
        }
    }

    public GlobalOptions(String str) {
        this.nodePath = str;
    }
}
