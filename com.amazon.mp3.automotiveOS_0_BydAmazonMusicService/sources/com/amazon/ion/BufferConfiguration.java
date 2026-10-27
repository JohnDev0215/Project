package com.amazon.ion;

import com.amazon.ion.BufferConfiguration;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BufferConfiguration<Configuration extends BufferConfiguration<Configuration>> {
    private final DataHandler dataHandler;
    private final int initialBufferSize;
    private final int maximumBufferSize;
    private final OversizedValueHandler oversizedValueHandler;

    public interface DataHandler {
        void onData(int i) throws Exception;
    }

    public interface OversizedValueHandler {
        void onOversizedValue() throws Exception;
    }

    public static abstract class Builder<Configuration extends BufferConfiguration<Configuration>, BuilderType extends Builder<Configuration, BuilderType>> {
        private int initialBufferSize = 32768;
        private int maximumBufferSize = Integer.MAX_VALUE;
        private OversizedValueHandler oversizedValueHandler = null;
        private DataHandler dataHandler = null;

        public abstract int getMinimumMaximumBufferSize();

        public abstract DataHandler getNoOpDataHandler();

        public abstract OversizedValueHandler getNoOpOversizedValueHandler();

        public final int getInitialBufferSize() {
            return this.initialBufferSize;
        }

        public final OversizedValueHandler getOversizedValueHandler() {
            return this.oversizedValueHandler;
        }

        public final DataHandler getDataHandler() {
            return this.dataHandler;
        }

        public int getMaximumBufferSize() {
            return this.maximumBufferSize;
        }
    }

    protected BufferConfiguration(Builder<Configuration, ?> builder) {
        int initialBufferSize = builder.getInitialBufferSize();
        this.initialBufferSize = initialBufferSize;
        int maximumBufferSize = builder.getMaximumBufferSize();
        this.maximumBufferSize = maximumBufferSize;
        if (initialBufferSize > maximumBufferSize) {
            throw new IllegalArgumentException("Initial buffer size may not exceed the maximum buffer size.");
        }
        if (maximumBufferSize < builder.getMinimumMaximumBufferSize()) {
            throw new IllegalArgumentException(String.format("Maximum buffer size must be at least %d bytes.", Integer.valueOf(builder.getMinimumMaximumBufferSize())));
        }
        if (builder.getOversizedValueHandler() == null) {
            requireUnlimitedBufferSize();
            this.oversizedValueHandler = builder.getNoOpOversizedValueHandler();
        } else {
            this.oversizedValueHandler = builder.getOversizedValueHandler();
        }
        if (builder.getDataHandler() == null) {
            this.dataHandler = builder.getNoOpDataHandler();
        } else {
            this.dataHandler = builder.getDataHandler();
        }
    }

    protected void requireUnlimitedBufferSize() {
        if (this.maximumBufferSize < Integer.MAX_VALUE) {
            throw new IllegalArgumentException("Must specify an OversizedValueHandler when a maximum buffer size is specified.");
        }
    }

    public final int getInitialBufferSize() {
        return this.initialBufferSize;
    }

    public final int getMaximumBufferSize() {
        return this.maximumBufferSize;
    }

    public final OversizedValueHandler getOversizedValueHandler() {
        return this.oversizedValueHandler;
    }

    public final DataHandler getDataHandler() {
        return this.dataHandler;
    }
}
