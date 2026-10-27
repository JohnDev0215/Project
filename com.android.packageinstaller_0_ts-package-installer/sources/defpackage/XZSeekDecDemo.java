package defpackage;

import org.tukaani.xz.SeekableFileInputStream;
import org.tukaani.xz.SeekableXZInputStream;

/* JADX INFO: loaded from: classes.dex */
class XZSeekDecDemo {
    XZSeekDecDemo() {
    }

    public static void main(String[] args) throws Exception {
        SeekableFileInputStream file = new SeekableFileInputStream(args[0]);
        SeekableXZInputStream in = new SeekableXZInputStream(file);
        System.err.println("Number of XZ Streams: " + in.getStreamCount());
        System.err.println("Number of XZ Blocks: " + in.getBlockCount());
        System.err.println("Uncompressed size: " + in.length() + " B");
        System.err.println("Largest XZ Block size: " + in.getLargestBlockSize() + " B");
        System.err.print("List of Check IDs:");
        int checkTypes = in.getCheckTypes();
        for (int i = 0; i < 16; i++) {
            if (((1 << i) & checkTypes) != 0) {
                System.err.print(" " + i);
            }
        }
        System.err.println();
        System.err.println("Index memory usage: " + in.getIndexMemoryUsage() + " KiB");
        byte[] buf = new byte[8192];
        if (args.length != 1) {
            for (int i2 = 1; i2 < args.length; i2 += 2) {
                int pos = Integer.parseInt(args[i2]);
                int len = Integer.parseInt(args[i2 + 1]);
                in.seek(pos);
                while (len > 0) {
                    int size = in.read(buf, 0, Math.min(len, buf.length));
                    if (size == -1) {
                        System.err.println("Error: End of file reached");
                        System.exit(1);
                    }
                    System.out.write(buf, 0, size);
                    len -= size;
                }
            }
            return;
        }
        while (true) {
            int size2 = in.read(buf);
            if (size2 != -1) {
                System.out.write(buf, 0, size2);
            } else {
                return;
            }
        }
    }
}
