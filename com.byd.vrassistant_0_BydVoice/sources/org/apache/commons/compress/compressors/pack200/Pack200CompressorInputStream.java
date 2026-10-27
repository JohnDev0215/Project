package org.apache.commons.compress.compressors.pack200;

import com.google.common.base.Ascii;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.jar.JarOutputStream;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.java.util.jar.Pack200;
import org.apache.commons.compress.utils.CloseShieldFilterInputStream;
import org.apache.commons.compress.utils.IOUtils;

/* JADX INFO: loaded from: classes4.dex */
public class Pack200CompressorInputStream extends CompressorInputStream {
    private static final byte[] CAFE_DOOD;
    private static final int SIG_LENGTH;
    private final InputStream originalInput;
    private final StreamBridge streamBridge;

    public Pack200CompressorInputStream(InputStream inputStream) throws IOException {
        this(inputStream, Pack200Strategy.IN_MEMORY);
    }

    public Pack200CompressorInputStream(InputStream inputStream, Pack200Strategy pack200Strategy) throws IOException {
        this(inputStream, null, pack200Strategy, null);
    }

    public Pack200CompressorInputStream(InputStream inputStream, Map<String, String> map) throws IOException {
        this(inputStream, Pack200Strategy.IN_MEMORY, map);
    }

    public Pack200CompressorInputStream(InputStream inputStream, Pack200Strategy pack200Strategy, Map<String, String> map) throws IOException {
        this(inputStream, null, pack200Strategy, map);
    }

    public Pack200CompressorInputStream(File file) throws IOException {
        this(file, Pack200Strategy.IN_MEMORY);
    }

    public Pack200CompressorInputStream(File file, Pack200Strategy pack200Strategy) throws IOException {
        this(null, file, pack200Strategy, null);
    }

    public Pack200CompressorInputStream(File file, Map<String, String> map) throws IOException {
        this(file, Pack200Strategy.IN_MEMORY, map);
    }

    public Pack200CompressorInputStream(File file, Pack200Strategy pack200Strategy, Map<String, String> map) throws IOException {
        this(null, file, pack200Strategy, map);
    }

    private Pack200CompressorInputStream(InputStream inputStream, File file, Pack200Strategy pack200Strategy, Map<String, String> map) throws IOException {
        this.originalInput = inputStream;
        StreamBridge streamBridgeNewStreamBridge = pack200Strategy.newStreamBridge();
        this.streamBridge = streamBridgeNewStreamBridge;
        JarOutputStream jarOutputStream = new JarOutputStream(streamBridgeNewStreamBridge);
        try {
            Pack200.Unpacker unpackerNewUnpacker = Pack200.newUnpacker();
            if (map != null) {
                unpackerNewUnpacker.properties().putAll(map);
            }
            if (file == null) {
                unpackerNewUnpacker.unpack(new CloseShieldFilterInputStream(inputStream), jarOutputStream);
            } else {
                unpackerNewUnpacker.unpack(file, jarOutputStream);
            }
            jarOutputStream.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    jarOutputStream.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        return this.streamBridge.getInput().read();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return this.streamBridge.getInput().read(bArr);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        return this.streamBridge.getInput().read(bArr, i, i2);
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.streamBridge.getInput().available();
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        try {
            return this.streamBridge.getInput().markSupported();
        } catch (IOException unused) {
            return false;
        }
    }

    @Override // java.io.InputStream
    public synchronized void mark(int i) {
        try {
            this.streamBridge.getInput().mark(i);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // java.io.InputStream
    public synchronized void reset() throws IOException {
        this.streamBridge.getInput().reset();
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        return IOUtils.skip(this.streamBridge.getInput(), j);
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            this.streamBridge.stop();
        } finally {
            InputStream inputStream = this.originalInput;
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    static {
        byte[] bArr = {-54, -2, -48, Ascii.CR};
        CAFE_DOOD = bArr;
        SIG_LENGTH = bArr.length;
    }

    public static boolean matches(byte[] bArr, int i) {
        if (i < SIG_LENGTH) {
            return false;
        }
        for (int i2 = 0; i2 < SIG_LENGTH; i2++) {
            if (bArr[i2] != CAFE_DOOD[i2]) {
                return false;
            }
        }
        return true;
    }
}
