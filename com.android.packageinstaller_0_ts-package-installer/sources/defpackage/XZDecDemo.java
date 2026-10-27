package defpackage;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.tukaani.xz.XZInputStream;

/* JADX INFO: loaded from: classes.dex */
class XZDecDemo {
    XZDecDemo() {
    }

    public static void main(String[] args) {
        byte[] buf = new byte[8192];
        try {
            if (args.length == 0) {
                InputStream in = new XZInputStream(System.in);
                while (true) {
                    int size = in.read(buf);
                    if (size != -1) {
                        System.out.write(buf, 0, size);
                    } else {
                        return;
                    }
                }
            } else {
                for (String name : args) {
                    InputStream in2 = new FileInputStream(name);
                    try {
                        in2 = new XZInputStream(in2);
                        while (true) {
                            int size2 = in2.read(buf);
                            if (size2 != -1) {
                                System.out.write(buf, 0, size2);
                            }
                        }
                        in2.close();
                    } catch (Throwable th) {
                        in2.close();
                        throw th;
                    }
                }
            }
        } catch (EOFException e) {
            System.err.println("XZDecDemo: Unexpected end of input on " + ((String) null));
            System.exit(1);
        } catch (FileNotFoundException e2) {
            System.err.println("XZDecDemo: Cannot open " + ((String) null) + ": " + e2.getMessage());
            System.exit(1);
        } catch (IOException e3) {
            System.err.println("XZDecDemo: Error decompressing from " + ((String) null) + ": " + e3.getMessage());
            System.exit(1);
        }
    }
}
