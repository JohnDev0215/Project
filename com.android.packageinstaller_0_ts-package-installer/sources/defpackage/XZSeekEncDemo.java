package defpackage;

import org.tukaani.xz.LZMA2Options;
import org.tukaani.xz.XZOutputStream;

/* JADX INFO: loaded from: classes.dex */
class XZSeekEncDemo {
    XZSeekEncDemo() {
    }

    public static void main(String[] args) throws Exception {
        LZMA2Options options = new LZMA2Options();
        if (args.length >= 1) {
            options.setPreset(Integer.parseInt(args[0]));
        }
        int blockSize = 1048576;
        if (args.length >= 2) {
            blockSize = Integer.parseInt(args[1]);
        }
        options.setDictSize(Math.min(options.getDictSize(), Math.max(4096, blockSize)));
        System.err.println("Encoder memory usage: " + options.getEncoderMemoryUsage() + " KiB");
        System.err.println("Decoder memory usage: " + options.getDecoderMemoryUsage() + " KiB");
        System.err.println("Block size:           " + blockSize + " B");
        XZOutputStream out = new XZOutputStream(System.out, options);
        byte[] buf = new byte[8192];
        int left = blockSize;
        while (true) {
            int size = System.in.read(buf, 0, Math.min(buf.length, left));
            if (size != -1) {
                out.write(buf, 0, size);
                left -= size;
                if (left == 0) {
                    out.endBlock();
                    left = blockSize;
                }
            } else {
                out.finish();
                return;
            }
        }
    }
}
