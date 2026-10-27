package com.amazon.ion.impl.bin;

/* JADX INFO: loaded from: classes3.dex */
final class BlockAllocatorProviders {
    private static final BlockAllocatorProvider BASIC_PROVIDER = new BasicBlockAllocatorProvider();

    private BlockAllocatorProviders() {
    }

    private static final class BasicBlockAllocatorProvider extends BlockAllocatorProvider {
        private BasicBlockAllocatorProvider() {
        }

        @Override // com.amazon.ion.impl.bin.BlockAllocatorProvider
        public BlockAllocator vendAllocator(final int i) {
            return new BlockAllocator() { // from class: com.amazon.ion.impl.bin.BlockAllocatorProviders.BasicBlockAllocatorProvider.1
                @Override // com.amazon.ion.impl.bin.BlockAllocator, java.io.Closeable, java.lang.AutoCloseable
                public void close() {
                }

                @Override // com.amazon.ion.impl.bin.BlockAllocator
                public Block allocateBlock() {
                    return new Block(new byte[i]) { // from class: com.amazon.ion.impl.bin.BlockAllocatorProviders.BasicBlockAllocatorProvider.1.1
                        @Override // com.amazon.ion.impl.bin.Block, java.io.Closeable, java.lang.AutoCloseable
                        public void close() {
                        }
                    };
                }

                @Override // com.amazon.ion.impl.bin.BlockAllocator
                public int getBlockSize() {
                    return i;
                }
            };
        }
    }

    public static BlockAllocatorProvider basicProvider() {
        return BASIC_PROVIDER;
    }
}
