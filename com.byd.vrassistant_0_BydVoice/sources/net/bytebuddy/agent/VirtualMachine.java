package net.bytebuddy.agent;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.apache.commons.compress.utils.CharsetNames;
import org.newsclub.net.unix.AFUNIXSocket;
import org.newsclub.net.unix.AFUNIXSocketAddress;

/* JADX INFO: loaded from: classes3.dex */
public interface VirtualMachine {
    void detach() throws IOException;

    void loadAgent(String str, String str2) throws IOException;

    public static abstract class ForHotSpot implements VirtualMachine {
        private static final String ARGUMENT_DELIMITER = "=";
        private static final String INSTRUMENT_COMMAND = "instrument";
        private static final String LOAD_COMMAND = "load";
        private static final String PROTOCOL_VERSION = "1";
        protected final String processId;
        private static final Charset UTF_8 = Charset.forName(CharsetNames.UTF_8);
        private static final byte[] BLANK = {0};

        protected abstract void connect() throws IOException;

        protected abstract int read(byte[] bArr) throws IOException;

        protected abstract void write(byte[] bArr) throws IOException;

        protected ForHotSpot(String str) {
            this.processId = str;
        }

        @Override // net.bytebuddy.agent.VirtualMachine
        public void loadAgent(String str, String str2) throws IOException {
            connect();
            Charset charset = UTF_8;
            write(PROTOCOL_VERSION.getBytes(charset));
            byte[] bArr = BLANK;
            write(bArr);
            write(LOAD_COMMAND.getBytes(charset));
            write(bArr);
            write(INSTRUMENT_COMMAND.getBytes(charset));
            write(bArr);
            write(Boolean.FALSE.toString().getBytes(charset));
            write(bArr);
            if (str2 != null) {
                str = str + ARGUMENT_DELIMITER + str2;
            }
            write(str.getBytes(charset));
            write(bArr);
            byte[] bArr2 = new byte[1];
            StringBuilder sb = new StringBuilder();
            while (true) {
                int i = read(bArr2);
                if (i == -1) {
                    break;
                }
                if (i > 0) {
                    byte b = bArr2[0];
                    if (b == 10) {
                        break;
                    } else {
                        sb.append((char) b);
                    }
                }
            }
            int i2 = Integer.parseInt(sb.toString());
            if (i2 == 0) {
                return;
            }
            if (i2 == 101) {
                throw new IOException("Protocol mismatch with target VM");
            }
            byte[] bArr3 = new byte[1024];
            StringBuilder sb2 = new StringBuilder();
            while (true) {
                int i3 = read(bArr3);
                if (i3 != -1) {
                    sb2.append(new String(bArr3, 0, i3, UTF_8));
                } else {
                    throw new IllegalStateException(sb2.toString());
                }
            }
        }

        public static class OnUnix extends ForHotSpot {
            private static final String ATTACH_FILE_PREFIX = ".attach_pid";
            private static final int DEFAULT_ATTEMPTS = 10;
            private static final long DEFAULT_PAUSE = 200;
            private static final long DEFAULT_TIMEOUT = 5000;
            private static final String SOCKET_FILE_PREFIX = ".java_pid";
            private static final String TEMPORARY_DIRECTORY = "/tmp";
            private final int attempts;
            private final long pause;
            private final Object socket;
            private final TimeUnit timeUnit;
            private final long timeout;

            public OnUnix(String str, Object obj, int i, long j, long j2, TimeUnit timeUnit) {
                super(str);
                this.socket = obj;
                this.attempts = i;
                this.pause = j;
                this.timeout = j2;
                this.timeUnit = timeUnit;
            }

            public static Class<?> assertAvailability() throws Throwable {
                if (!AFUNIXSocket.isSupported()) {
                    throw new IllegalStateException("POSIX sockets are not supported on the current system");
                }
                if (!System.getProperty("java.vm.name").toLowerCase(Locale.US).contains("hotspot")) {
                    throw new IllegalStateException("Cannot apply attachment on non-Hotspot compatible VM");
                }
                return OnUnix.class;
            }

            public static VirtualMachine attach(String str) throws IOException {
                return new OnUnix(str, AFUNIXSocket.newInstance(), 10, DEFAULT_PAUSE, DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
            }

            @Override // net.bytebuddy.agent.VirtualMachine.ForHotSpot
            protected void connect() throws IOException {
                boolean z;
                File file = new File(TEMPORARY_DIRECTORY, SOCKET_FILE_PREFIX + this.processId);
                if (!file.exists()) {
                    String str = ATTACH_FILE_PREFIX + this.processId;
                    File file2 = new File("/proc/" + this.processId + "/cwd/" + str);
                    try {
                        if (!file2.createNewFile() && !file2.isFile()) {
                            throw new IllegalStateException("Could not create attach file: " + file2);
                        }
                    } catch (IOException unused) {
                        file2 = new File(TEMPORARY_DIRECTORY, str);
                        if (!file2.createNewFile() && !file2.isFile()) {
                            throw new IllegalStateException("Could not create attach file: " + file2);
                        }
                    }
                    try {
                        try {
                            Process processExec = Runtime.getRuntime().exec("kill -3 " + this.processId);
                            int i = this.attempts;
                            while (true) {
                                try {
                                    if (processExec.exitValue() != 0) {
                                        throw new IllegalStateException("Error while sending signal to target VM: " + this.processId);
                                    }
                                    z = true;
                                    break;
                                } catch (IllegalThreadStateException unused2) {
                                    i--;
                                    Thread.sleep(this.timeUnit.toMillis(this.pause));
                                    if (i <= 0) {
                                        z = false;
                                        break;
                                    }
                                }
                            }
                            if (!z) {
                                throw new IllegalStateException("Target VM did not respond to signal: " + this.processId);
                            }
                            int i2 = this.attempts;
                            while (true) {
                                int i3 = i2 - 1;
                                if (i2 <= 0 || file.exists()) {
                                    break;
                                }
                                Thread.sleep(this.timeUnit.toMillis(this.pause));
                                i2 = i3;
                            }
                            if (!file.exists()) {
                                throw new IllegalStateException("Target VM did not respond: " + this.processId);
                            }
                        } catch (InterruptedException e) {
                            throw new IllegalStateException("Interrupted during wait for process", e);
                        }
                    } finally {
                        if (!file2.delete()) {
                            file2.deleteOnExit();
                        }
                    }
                }
                ((AFUNIXSocket) this.socket).setSoTimeout((int) this.timeUnit.toMillis(this.timeout));
                ((AFUNIXSocket) this.socket).connect(new AFUNIXSocketAddress(file));
            }

            @Override // net.bytebuddy.agent.VirtualMachine.ForHotSpot
            public int read(byte[] bArr) throws IOException {
                return ((AFUNIXSocket) this.socket).getInputStream().read(bArr);
            }

            @Override // net.bytebuddy.agent.VirtualMachine.ForHotSpot
            public void write(byte[] bArr) throws IOException {
                ((AFUNIXSocket) this.socket).getOutputStream().write(bArr);
            }

            @Override // net.bytebuddy.agent.VirtualMachine
            public void detach() throws IOException {
                ((AFUNIXSocket) this.socket).close();
            }
        }
    }
}
