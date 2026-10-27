package org.apache.shiro.crypto.hash;

import org.apache.shiro.util.ByteSource;

/* JADX INFO: loaded from: classes.dex */
public interface HashRequest {
    String getAlgorithmName();

    int getIterations();

    ByteSource getSalt();

    ByteSource getSource();

    public static class Builder {
        private String algorithmName;
        private int iterations = 0;
        private ByteSource salt;
        private ByteSource source;

        public Builder setSource(ByteSource byteSource) {
            this.source = byteSource;
            return this;
        }

        public Builder setSource(Object obj) throws IllegalArgumentException {
            this.source = ByteSource.Util.bytes(obj);
            return this;
        }

        public Builder setSalt(ByteSource byteSource) {
            this.salt = byteSource;
            return this;
        }

        public Builder setSalt(Object obj) throws IllegalArgumentException {
            this.salt = ByteSource.Util.bytes(obj);
            return this;
        }

        public Builder setIterations(int i) {
            this.iterations = i;
            return this;
        }

        public Builder setAlgorithmName(String str) {
            this.algorithmName = str;
            return this;
        }

        public HashRequest build() {
            return new SimpleHashRequest(this.algorithmName, this.source, this.salt, this.iterations);
        }
    }
}
