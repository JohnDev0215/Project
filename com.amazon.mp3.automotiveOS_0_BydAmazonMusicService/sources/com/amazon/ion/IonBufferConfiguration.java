package com.amazon.ion;

import com.amazon.ion.BufferConfiguration;

/* JADX INFO: loaded from: classes3.dex */
public final class IonBufferConfiguration extends BufferConfiguration<IonBufferConfiguration> {
    private final OversizedSymbolTableHandler oversizedSymbolTableHandler;

    public interface OversizedSymbolTableHandler {
        void onOversizedSymbolTable() throws Exception;
    }

    public static final class Builder extends BufferConfiguration.Builder<IonBufferConfiguration, Builder> {
        private OversizedSymbolTableHandler oversizedSymbolTableHandler = null;
        private static final BufferConfiguration.OversizedValueHandler NO_OP_OVERSIZED_VALUE_HANDLER = new BufferConfiguration.OversizedValueHandler() { // from class: com.amazon.ion.IonBufferConfiguration.Builder.1
            @Override // com.amazon.ion.BufferConfiguration.OversizedValueHandler
            public void onOversizedValue() {
            }
        };
        private static final BufferConfiguration.DataHandler NO_OP_DATA_HANDLER = new BufferConfiguration.DataHandler() { // from class: com.amazon.ion.IonBufferConfiguration.Builder.2
            @Override // com.amazon.ion.BufferConfiguration.DataHandler
            public void onData(int i) {
            }
        };
        private static final OversizedSymbolTableHandler NO_OP_OVERSIZED_SYMBOL_TABLE_HANDLER = new OversizedSymbolTableHandler() { // from class: com.amazon.ion.IonBufferConfiguration.Builder.3
            @Override // com.amazon.ion.IonBufferConfiguration.OversizedSymbolTableHandler
            public void onOversizedSymbolTable() {
            }
        };

        @Override // com.amazon.ion.BufferConfiguration.Builder
        public int getMinimumMaximumBufferSize() {
            return 5;
        }

        private Builder() {
        }

        public static Builder standard() {
            return new Builder();
        }

        public OversizedSymbolTableHandler getOversizedSymbolTableHandler() {
            return this.oversizedSymbolTableHandler;
        }

        @Override // com.amazon.ion.BufferConfiguration.Builder
        public BufferConfiguration.OversizedValueHandler getNoOpOversizedValueHandler() {
            return NO_OP_OVERSIZED_VALUE_HANDLER;
        }

        @Override // com.amazon.ion.BufferConfiguration.Builder
        public BufferConfiguration.DataHandler getNoOpDataHandler() {
            return NO_OP_DATA_HANDLER;
        }

        public OversizedSymbolTableHandler getNoOpOversizedSymbolTableHandler() {
            return NO_OP_OVERSIZED_SYMBOL_TABLE_HANDLER;
        }

        public IonBufferConfiguration build() {
            return new IonBufferConfiguration(this);
        }
    }

    private IonBufferConfiguration(Builder builder) {
        super(builder);
        if (builder.getOversizedSymbolTableHandler() == null) {
            requireUnlimitedBufferSize();
            this.oversizedSymbolTableHandler = builder.getNoOpOversizedSymbolTableHandler();
        } else {
            this.oversizedSymbolTableHandler = builder.getOversizedSymbolTableHandler();
        }
    }

    public OversizedSymbolTableHandler getOversizedSymbolTableHandler() {
        return this.oversizedSymbolTableHandler;
    }
}
