package defpackage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;
import org.tukaani.xz.ArrayCache;
import org.tukaani.xz.BasicArrayCache;
import org.tukaani.xz.LZMA2Options;
import org.tukaani.xz.XZInputStream;
import org.tukaani.xz.XZOutputStream;

/* JADX INFO: loaded from: classes.dex */
class TestAllocSpeed implements Runnable {
    private static boolean compressing;
    private static int repeats;
    private static int testdataSize;
    private final Random rng;
    private static final byte[] testdata = new byte[1048576];
    private static volatile IOException exception = null;

    public TestAllocSpeed(long seed) {
        this.rng = new Random(seed);
    }

    private void compress() throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(testdataSize + 1024);
        LZMA2Options options = new LZMA2Options();
        options.setDictSize(1 << (this.rng.nextInt(6) + 16));
        for (int i = 0; i < repeats; i++) {
            XZOutputStream out = new XZOutputStream(byteStream, options);
            out.write(testdata, 0, testdataSize);
            out.finish();
        }
    }

    private void decompress() throws IOException {
        ByteArrayInputStream byteStream = new ByteArrayInputStream(testdata, 0, testdataSize);
        byte[] outbuf = new byte[8192];
        for (int i = 0; i < repeats; i++) {
            byteStream.reset();
            XZInputStream in = new XZInputStream(byteStream);
            while (in.read(outbuf) > 0) {
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (compressing) {
                compress();
            } else {
                decompress();
            }
        } catch (IOException e) {
            exception = e;
        }
    }

    public static void main(String[] args) throws Exception {
        compressing = Boolean.parseBoolean(args[0]);
        repeats = Integer.parseInt(args[1]);
        int threadCount = Integer.parseInt(args[2]);
        if (threadCount < 1 || threadCount > 64) {
            throw new Exception("Thread count must be 1-64");
        }
        testdataSize = System.in.read(testdata);
        ArrayCache.setDefaultCache(BasicArrayCache.getInstance());
        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(new TestAllocSpeed(i));
            threads[i].start();
        }
        for (int i2 = 0; i2 < threadCount; i2++) {
            threads[i2].join();
        }
        if (exception != null) {
            throw exception;
        }
    }
}
