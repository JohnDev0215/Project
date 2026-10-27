package org.apache.commons.compress.compressors.lz4;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.compressors.lz77support.LZ77Compressor;
import org.apache.commons.compress.compressors.lz77support.Parameters;
import org.apache.commons.compress.utils.ByteUtils;

/* JADX INFO: loaded from: classes4.dex */
public class BlockLZ4CompressorOutputStream extends CompressorOutputStream {
    private static final int MIN_BACK_REFERENCE_LENGTH = 4;
    private static final int MIN_OFFSET_OF_LAST_BACK_REFERENCE = 12;
    private final LZ77Compressor compressor;
    private final Deque<byte[]> expandedBlocks;
    private boolean finished;
    private final byte[] oneByte;
    private final OutputStream os;
    private final Deque<Pair> pairs;

    public BlockLZ4CompressorOutputStream(OutputStream outputStream) throws IOException {
        this(outputStream, createParameterBuilder().build());
    }

    public BlockLZ4CompressorOutputStream(OutputStream outputStream, Parameters parameters) throws IOException {
        this.oneByte = new byte[1];
        this.pairs = new LinkedList();
        this.expandedBlocks = new LinkedList();
        this.os = outputStream;
        this.compressor = new LZ77Compressor(parameters, new LZ77Compressor.Callback() { // from class: org.apache.commons.compress.compressors.lz4.BlockLZ4CompressorOutputStream$$ExternalSyntheticLambda0
            @Override // org.apache.commons.compress.compressors.lz77support.LZ77Compressor.Callback
            public final void accept(LZ77Compressor.Block block) throws IOException {
                this.f$0.m1914x9ebf33ca(block);
            }
        });
    }

    /* JADX INFO: renamed from: org.apache.commons.compress.compressors.lz4.BlockLZ4CompressorOutputStream$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$compress$compressors$lz77support$LZ77Compressor$Block$BlockType;

        static {
            int[] iArr = new int[LZ77Compressor.Block.BlockType.values().length];
            $SwitchMap$org$apache$commons$compress$compressors$lz77support$LZ77Compressor$Block$BlockType = iArr;
            try {
                iArr[LZ77Compressor.Block.BlockType.LITERAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$apache$commons$compress$compressors$lz77support$LZ77Compressor$Block$BlockType[LZ77Compressor.Block.BlockType.BACK_REFERENCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$apache$commons$compress$compressors$lz77support$LZ77Compressor$Block$BlockType[LZ77Compressor.Block.BlockType.EOD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: renamed from: lambda$new$0$org-apache-commons-compress-compressors-lz4-BlockLZ4CompressorOutputStream, reason: not valid java name */
    /* synthetic */ void m1914x9ebf33ca(LZ77Compressor.Block block) throws IOException {
        int i = AnonymousClass1.$SwitchMap$org$apache$commons$compress$compressors$lz77support$LZ77Compressor$Block$BlockType[block.getType().ordinal()];
        if (i == 1) {
            addLiteralBlock((LZ77Compressor.LiteralBlock) block);
        } else if (i == 2) {
            addBackReference((LZ77Compressor.BackReference) block);
        } else {
            if (i != 3) {
                return;
            }
            writeFinalLiteralBlock();
        }
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        byte[] bArr = this.oneByte;
        bArr[0] = (byte) (i & 255);
        write(bArr);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.compressor.compress(bArr, i, i2);
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            finish();
        } finally {
            this.os.close();
        }
    }

    public void finish() throws IOException {
        if (this.finished) {
            return;
        }
        this.compressor.finish();
        this.finished = true;
    }

    public void prefill(byte[] bArr, int i, int i2) {
        if (i2 > 0) {
            byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, i, i2 + i);
            this.compressor.prefill(bArrCopyOfRange);
            recordLiteral(bArrCopyOfRange);
        }
    }

    private void addLiteralBlock(LZ77Compressor.LiteralBlock literalBlock) throws IOException {
        recordLiteral(writeBlocksAndReturnUnfinishedPair(literalBlock.getLength()).addLiteral(literalBlock));
        clearUnusedBlocksAndPairs();
    }

    private void addBackReference(LZ77Compressor.BackReference backReference) throws IOException {
        writeBlocksAndReturnUnfinishedPair(backReference.getLength()).setBackReference(backReference);
        recordBackReference(backReference);
        clearUnusedBlocksAndPairs();
    }

    private Pair writeBlocksAndReturnUnfinishedPair(int i) throws IOException {
        writeWritablePairs(i);
        Pair pairPeekLast = this.pairs.peekLast();
        if (pairPeekLast != null && !pairPeekLast.hasBackReference()) {
            return pairPeekLast;
        }
        Pair pair = new Pair();
        this.pairs.addLast(pair);
        return pair;
    }

    private void recordLiteral(byte[] bArr) {
        this.expandedBlocks.addFirst(bArr);
    }

    private void clearUnusedBlocksAndPairs() {
        clearUnusedBlocks();
        clearUnusedPairs();
    }

    private void clearUnusedBlocks() {
        Iterator<byte[]> it = this.expandedBlocks.iterator();
        int i = 0;
        int length = 0;
        while (it.hasNext()) {
            i++;
            length += it.next().length;
            if (length >= 65536) {
                break;
            }
        }
        int size = this.expandedBlocks.size();
        while (i < size) {
            this.expandedBlocks.removeLast();
            i++;
        }
    }

    private void recordBackReference(LZ77Compressor.BackReference backReference) {
        this.expandedBlocks.addFirst(expand(backReference.getOffset(), backReference.getLength()));
    }

    private byte[] expand(int i, int i2) {
        byte[] bArr = new byte[i2];
        if (i == 1) {
            byte[] bArrPeekFirst = this.expandedBlocks.peekFirst();
            byte b = bArrPeekFirst[bArrPeekFirst.length - 1];
            if (b != 0) {
                Arrays.fill(bArr, b);
            }
        } else {
            expandFromList(bArr, i, i2);
        }
        return bArr;
    }

    private void expandFromList(byte[] bArr, int i, int i2) {
        int length;
        int iMin;
        byte[] next;
        int i3 = i;
        int i4 = 0;
        while (i2 > 0) {
            if (i3 > 0) {
                Iterator<byte[]> it = this.expandedBlocks.iterator();
                int length2 = 0;
                while (true) {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                    if (next.length + length2 >= i3) {
                        break;
                    } else {
                        length2 += next.length;
                    }
                }
                if (next == null) {
                    throw new IllegalStateException("Failed to find a block containing offset " + i);
                }
                length = (length2 + next.length) - i3;
                iMin = Math.min(i2, next.length - length);
            } else {
                length = -i3;
                iMin = Math.min(i2, i4 + i3);
                next = bArr;
            }
            System.arraycopy(next, length, bArr, i4, iMin);
            i3 -= iMin;
            i2 -= iMin;
            i4 += iMin;
        }
    }

    private void clearUnusedPairs() {
        Iterator<Pair> itDescendingIterator = this.pairs.descendingIterator();
        int i = 0;
        int length = 0;
        while (itDescendingIterator.hasNext()) {
            i++;
            length += itDescendingIterator.next().length();
            if (length >= 65536) {
                break;
            }
        }
        int size = this.pairs.size();
        while (i < size && this.pairs.peekFirst().hasBeenWritten()) {
            this.pairs.removeFirst();
            i++;
        }
    }

    private void writeFinalLiteralBlock() throws IOException {
        rewriteLastPairs();
        for (Pair pair : this.pairs) {
            if (!pair.hasBeenWritten()) {
                pair.writeTo(this.os);
            }
        }
        this.pairs.clear();
    }

    private void writeWritablePairs(int i) throws IOException {
        Iterator<Pair> itDescendingIterator = this.pairs.descendingIterator();
        while (itDescendingIterator.hasNext()) {
            Pair next = itDescendingIterator.next();
            if (next.hasBeenWritten()) {
                break;
            } else {
                i += next.length();
            }
        }
        for (Pair pair : this.pairs) {
            if (!pair.hasBeenWritten()) {
                i -= pair.length();
                if (!pair.canBeWritten(i)) {
                    return;
                } else {
                    pair.writeTo(this.os);
                }
            }
        }
    }

    private void rewriteLastPairs() {
        LinkedList linkedList = new LinkedList();
        LinkedList linkedList2 = new LinkedList();
        Iterator<Pair> itDescendingIterator = this.pairs.descendingIterator();
        int i = 0;
        while (itDescendingIterator.hasNext()) {
            Pair next = itDescendingIterator.next();
            if (next.hasBeenWritten()) {
                break;
            }
            int length = next.length();
            linkedList2.addFirst(Integer.valueOf(length));
            linkedList.addFirst(next);
            i += length;
            if (i >= 12) {
                break;
            }
        }
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            this.pairs.remove((Pair) it.next());
        }
        int size = linkedList.size();
        int iIntValue = 0;
        for (int i2 = 1; i2 < size; i2++) {
            iIntValue += ((Integer) linkedList2.get(i2)).intValue();
        }
        Pair pair = new Pair();
        if (iIntValue > 0) {
            pair.prependLiteral(expand(iIntValue, iIntValue));
        }
        Pair pair2 = (Pair) linkedList.get(0);
        int i3 = 12 - iIntValue;
        int iBackReferenceLength = pair2.hasBackReference() ? pair2.backReferenceLength() : 0;
        if (!pair2.hasBackReference() || iBackReferenceLength < i3 + 4) {
            if (pair2.hasBackReference()) {
                pair.prependLiteral(expand(iIntValue + iBackReferenceLength, iBackReferenceLength));
            }
            pair2.prependTo(pair);
        } else {
            pair.prependLiteral(expand(iIntValue + i3, i3));
            this.pairs.add(pair2.splitWithNewBackReferenceLengthOf(iBackReferenceLength - i3));
        }
        this.pairs.add(pair);
    }

    public static Parameters.Builder createParameterBuilder() {
        return Parameters.builder(65536).withMinBackReferenceLength(4).withMaxBackReferenceLength(65535).withMaxOffset(65535).withMaxLiteralLength(65535);
    }

    static final class Pair {
        private int brLength;
        private int brOffset;
        private final Deque<byte[]> literals = new LinkedList();
        private boolean written;

        private static int lengths(int i, int i2) {
            int i3 = 15;
            if (i >= 15) {
                i = 15;
            }
            if (i2 < 4) {
                i3 = 0;
            } else if (i2 < 19) {
                i3 = i2 - 4;
            }
            return (i << 4) | i3;
        }

        Pair() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void prependLiteral(byte[] bArr) {
            this.literals.addFirst(bArr);
        }

        byte[] addLiteral(LZ77Compressor.LiteralBlock literalBlock) {
            byte[] bArrCopyOfRange = Arrays.copyOfRange(literalBlock.getData(), literalBlock.getOffset(), literalBlock.getOffset() + literalBlock.getLength());
            this.literals.add(bArrCopyOfRange);
            return bArrCopyOfRange;
        }

        void setBackReference(LZ77Compressor.BackReference backReference) {
            if (hasBackReference()) {
                throw new IllegalStateException();
            }
            this.brOffset = backReference.getOffset();
            this.brLength = backReference.getLength();
        }

        boolean hasBackReference() {
            return this.brOffset > 0;
        }

        boolean canBeWritten(int i) {
            return hasBackReference() && i >= 16;
        }

        int length() {
            return literalLength() + this.brLength;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean hasBeenWritten() {
            return this.written;
        }

        void writeTo(OutputStream outputStream) throws IOException {
            int iLiteralLength = literalLength();
            outputStream.write(lengths(iLiteralLength, this.brLength));
            if (iLiteralLength >= 15) {
                writeLength(iLiteralLength - 15, outputStream);
            }
            Iterator<byte[]> it = this.literals.iterator();
            while (it.hasNext()) {
                outputStream.write(it.next());
            }
            if (hasBackReference()) {
                ByteUtils.toLittleEndian(outputStream, this.brOffset, 2);
                int i = this.brLength;
                if (i - 4 >= 15) {
                    writeLength((i - 4) - 15, outputStream);
                }
            }
            this.written = true;
        }

        private int literalLength() {
            Iterator<byte[]> it = this.literals.iterator();
            int length = 0;
            while (it.hasNext()) {
                length += it.next().length;
            }
            return length;
        }

        private static void writeLength(int i, OutputStream outputStream) throws IOException {
            while (i >= 255) {
                outputStream.write(255);
                i -= 255;
            }
            outputStream.write(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int backReferenceLength() {
            return this.brLength;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void prependTo(Pair pair) {
            Iterator<byte[]> itDescendingIterator = this.literals.descendingIterator();
            while (itDescendingIterator.hasNext()) {
                pair.prependLiteral(itDescendingIterator.next());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Pair splitWithNewBackReferenceLengthOf(int i) {
            Pair pair = new Pair();
            pair.literals.addAll(this.literals);
            pair.brOffset = this.brOffset;
            pair.brLength = i;
            return pair;
        }
    }
}
