package com.amazon.ion.impl.bin;

import com.visualon.OSMPUtils.voOSType;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes3.dex */
final class PooledBlockAllocatorProvider extends BlockAllocatorProvider {
    private static final PooledBlockAllocatorProvider INSTANCE = new PooledBlockAllocatorProvider();
    private final ConcurrentMap<Integer, BlockAllocator> allocators = new ConcurrentHashMap();

    private static final class PooledBlockAllocator extends BlockAllocator {
        private final int blockLimit;
        private final int blockSize;
        private final AtomicInteger size = new AtomicInteger(0);
        private final ConcurrentLinkedQueue<Block> freeBlocks = new ConcurrentLinkedQueue<>();

        @Override // com.amazon.ion.impl.bin.BlockAllocator, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        public PooledBlockAllocator(int i) {
            this.blockSize = i;
            this.blockLimit = voOSType.VOOSMP_SRC_FFAUDIO_MIDI / i;
        }

        @Override // com.amazon.ion.impl.bin.BlockAllocator
        public Block allocateBlock() {
            Block blockPoll = this.freeBlocks.poll();
            if (blockPoll == null) {
                return new Block(new byte[this.blockSize]) { // from class: com.amazon.ion.impl.bin.PooledBlockAllocatorProvider.PooledBlockAllocator.1
                    @Override // com.amazon.ion.impl.bin.Block, java.io.Closeable, java.lang.AutoCloseable
                    public void close() {
                        if (PooledBlockAllocator.this.size.getAndIncrement() >= PooledBlockAllocator.this.blockLimit) {
                            PooledBlockAllocator.this.size.decrementAndGet();
                        } else {
                            reset();
                            PooledBlockAllocator.this.freeBlocks.add(this);
                        }
                    }
                };
            }
            this.size.decrementAndGet();
            return blockPoll;
        }

        @Override // com.amazon.ion.impl.bin.BlockAllocator
        public int getBlockSize() {
            return this.blockSize;
        }
    }

    private PooledBlockAllocatorProvider() {
    }

    public static PooledBlockAllocatorProvider getInstance() {
        return INSTANCE;
    }

    @Override // com.amazon.ion.impl.bin.BlockAllocatorProvider
    public BlockAllocator vendAllocator(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Invalid block size: " + i);
        }
        BlockAllocator blockAllocator = this.allocators.get(Integer.valueOf(i));
        if (blockAllocator != null) {
            return blockAllocator;
        }
        PooledBlockAllocator pooledBlockAllocator = new PooledBlockAllocator(i);
        BlockAllocator blockAllocatorPutIfAbsent = this.allocators.putIfAbsent(Integer.valueOf(i), pooledBlockAllocator);
        return blockAllocatorPutIfAbsent != null ? blockAllocatorPutIfAbsent : pooledBlockAllocator;
    }
}
