package android.security.keystore;

import android.os.IBinder;
import android.security.KeyStore;
import android.security.KeyStoreException;
import android.security.keymaster.OperationResult;
import libcore.util.EmptyArray;

/* JADX INFO: loaded from: classes3.dex */
class KeyStoreCryptoOperationChunkedStreamer implements KeyStoreCryptoOperationStreamer {
    private static final int DEFAULT_CHUNK_SIZE_MAX = 65536;
    private static final int DEFAULT_CHUNK_SIZE_THRESHOLD = 2048;
    private final byte[] mChunk;
    private int mChunkLength;
    private final int mChunkSizeMax;
    private final int mChunkSizeThreshold;
    private long mConsumedInputSizeBytes;
    private final Stream mKeyStoreStream;
    private long mProducedOutputSizeBytes;

    interface Stream {
        OperationResult finish(byte[] bArr, byte[] bArr2, byte[] bArr3);

        OperationResult update(byte[] bArr);
    }

    KeyStoreCryptoOperationChunkedStreamer(Stream stream) {
        this(stream, 2048, 65536);
    }

    KeyStoreCryptoOperationChunkedStreamer(Stream stream, int i) {
        this(stream, i, 65536);
    }

    KeyStoreCryptoOperationChunkedStreamer(Stream stream, int i, int i2) {
        this.mChunkLength = 0;
        this.mKeyStoreStream = stream;
        this.mChunkSizeMax = i2;
        if (i <= 0) {
            this.mChunkSizeThreshold = 1;
        } else if (i > i2) {
            this.mChunkSizeThreshold = i2;
        } else {
            this.mChunkSizeThreshold = i;
        }
        this.mChunk = new byte[this.mChunkSizeMax];
    }

    @Override // android.security.keystore.KeyStoreCryptoOperationStreamer
    public byte[] update(byte[] bArr, int i, int i2) throws KeyStoreException {
        if (i2 == 0 || bArr == null) {
            return EmptyArray.BYTE;
        }
        if (i2 < 0 || i < 0 || i + i2 > bArr.length) {
            throw new KeyStoreException(-1000, "Input offset and length out of bounds of input array");
        }
        byte[] bArrConcat = EmptyArray.BYTE;
        while (true) {
            if (i2 <= 0 && this.mChunkLength < this.mChunkSizeThreshold) {
                return bArrConcat;
            }
            int iCopy = ArrayUtils.copy(bArr, i, this.mChunk, this.mChunkLength, i2);
            i2 -= iCopy;
            i += iCopy;
            int i3 = this.mChunkLength + iCopy;
            this.mChunkLength = i3;
            this.mConsumedInputSizeBytes += (long) iCopy;
            if (i3 > this.mChunkSizeMax) {
                throw new KeyStoreException(-21, "Chunk size exceeded max chunk size. Max: " + this.mChunkSizeMax + " Actual: " + this.mChunkLength);
            }
            if (i3 >= this.mChunkSizeThreshold) {
                OperationResult operationResultUpdate = this.mKeyStoreStream.update(ArrayUtils.subarray(this.mChunk, 0, i3));
                if (operationResultUpdate == null) {
                    throw new KeyStoreConnectException();
                }
                if (operationResultUpdate.resultCode != 1) {
                    throw KeyStore.getKeyStoreException(operationResultUpdate.resultCode);
                }
                if (operationResultUpdate.inputConsumed <= 0) {
                    throw new KeyStoreException(-21, "Keystore consumed 0 of " + this.mChunkLength + " bytes provided.");
                }
                int i4 = operationResultUpdate.inputConsumed;
                int i5 = this.mChunkLength;
                if (i4 > i5) {
                    throw new KeyStoreException(-1000, "Keystore consumed more input than provided. Provided: " + this.mChunkLength + ", consumed: " + operationResultUpdate.inputConsumed);
                }
                int i6 = i5 - operationResultUpdate.inputConsumed;
                this.mChunkLength = i6;
                if (i6 > 0) {
                    ArrayUtils.copy(this.mChunk, operationResultUpdate.inputConsumed, this.mChunk, 0, this.mChunkLength);
                }
                if (operationResultUpdate.output != null && operationResultUpdate.output.length > 0) {
                    this.mProducedOutputSizeBytes += (long) operationResultUpdate.output.length;
                    bArrConcat = ArrayUtils.concat(bArrConcat, operationResultUpdate.output);
                }
            }
        }
    }

    @Override // android.security.keystore.KeyStoreCryptoOperationStreamer
    public byte[] doFinal(byte[] bArr, int i, int i2, byte[] bArr2, byte[] bArr3) throws KeyStoreException {
        byte[] bArrUpdate = update(bArr, i, i2);
        byte[] bArrSubarray = ArrayUtils.subarray(this.mChunk, 0, this.mChunkLength);
        OperationResult operationResultFinish = this.mKeyStoreStream.finish(bArrSubarray, bArr2, bArr3);
        if (operationResultFinish == null) {
            throw new KeyStoreConnectException();
        }
        if (operationResultFinish.resultCode != 1) {
            throw KeyStore.getKeyStoreException(operationResultFinish.resultCode);
        }
        this.mConsumedInputSizeBytes += (long) bArrSubarray.length;
        if (operationResultFinish.output == null || operationResultFinish.output.length <= 0) {
            return bArrUpdate;
        }
        this.mProducedOutputSizeBytes += (long) operationResultFinish.output.length;
        return ArrayUtils.concat(bArrUpdate, operationResultFinish.output);
    }

    @Override // android.security.keystore.KeyStoreCryptoOperationStreamer
    public long getConsumedInputSizeBytes() {
        return this.mConsumedInputSizeBytes;
    }

    @Override // android.security.keystore.KeyStoreCryptoOperationStreamer
    public long getProducedOutputSizeBytes() {
        return this.mProducedOutputSizeBytes;
    }

    public static class MainDataStream implements Stream {
        private final KeyStore mKeyStore;
        private final IBinder mOperationToken;

        public MainDataStream(KeyStore keyStore, IBinder iBinder) {
            this.mKeyStore = keyStore;
            this.mOperationToken = iBinder;
        }

        @Override // android.security.keystore.KeyStoreCryptoOperationChunkedStreamer.Stream
        public OperationResult update(byte[] bArr) {
            return this.mKeyStore.update(this.mOperationToken, null, bArr);
        }

        @Override // android.security.keystore.KeyStoreCryptoOperationChunkedStreamer.Stream
        public OperationResult finish(byte[] bArr, byte[] bArr2, byte[] bArr3) {
            return this.mKeyStore.finish(this.mOperationToken, null, bArr, bArr2, bArr3);
        }
    }
}
