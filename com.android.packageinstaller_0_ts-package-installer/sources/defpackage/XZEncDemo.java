package defpackage;

import org.tukaani.xz.LZMA2Options;
import org.tukaani.xz.XZOutputStream;

/* JADX INFO: loaded from: classes.dex */
class XZEncDemo {
    XZEncDemo() {
    }

    public static void main(String[] args) throws Exception {
        LZMA2Options options = new LZMA2Options();
        if (args.length >= 1) {
            options.setPreset(Integer.parseInt(args[0]));
        }
        System.err.println("Encoder memory usage: " + options.getEncoderMemoryUsage() + " KiB");
        System.err.println("Decoder memory usage: " + options.getDecoderMemoryUsage() + " KiB");
        XZOutputStream out = new XZOutputStream(System.out, options);
        byte[] buf = new byte[8192];
        while (true) {
            int size = System.in.read(buf);
            if (size != -1) {
                out.write(buf, 0, size);
            } else {
                out.finish();
                return;
            }
        }
    }
}
