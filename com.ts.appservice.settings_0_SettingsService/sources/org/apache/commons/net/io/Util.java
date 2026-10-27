package org.apache.commons.net.io;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;

/* JADX INFO: loaded from: classes.dex */
public final class Util {
    public static final int DEFAULT_COPY_BUFFER_SIZE = 1024;

    private Util() {
    }

    public static long copyStream(InputStream source, OutputStream dest, int bufferSize, long streamSize, CopyStreamListener listener, boolean flush) throws CopyStreamException {
        long total;
        long total2 = 0;
        byte[] buffer = new byte[bufferSize > 0 ? bufferSize : 1024];
        while (true) {
            try {
                int numBytes = source.read(buffer);
                if (numBytes == -1) {
                    break;
                }
                if (numBytes == 0) {
                    int singleByte = source.read();
                    if (singleByte < 0) {
                        break;
                    }
                    dest.write(singleByte);
                    if (flush) {
                        dest.flush();
                    }
                    total = total2 + 1;
                    if (listener != null) {
                        try {
                            listener.bytesTransferred(total, 1, streamSize);
                        } catch (IOException e) {
                            e = e;
                            total2 = total;
                            throw new CopyStreamException("IOException caught while copying.", total2, e);
                        }
                    } else {
                        continue;
                    }
                } else {
                    dest.write(buffer, 0, numBytes);
                    if (flush) {
                        dest.flush();
                    }
                    total = total2 + ((long) numBytes);
                    if (listener != null) {
                        listener.bytesTransferred(total, numBytes, streamSize);
                    }
                }
                total2 = total;
            } catch (IOException e2) {
                e = e2;
            }
        }
        return total2;
    }

    public static long copyStream(InputStream source, OutputStream dest, int bufferSize, long streamSize, CopyStreamListener listener) throws CopyStreamException {
        return copyStream(source, dest, bufferSize, streamSize, listener, true);
    }

    public static long copyStream(InputStream source, OutputStream dest, int bufferSize) throws CopyStreamException {
        return copyStream(source, dest, bufferSize, -1L, null);
    }

    public static long copyStream(InputStream source, OutputStream dest) throws CopyStreamException {
        return copyStream(source, dest, 1024);
    }

    public static long copyReader(Reader source, Writer dest, int bufferSize, long streamSize, CopyStreamListener listener) throws CopyStreamException {
        long total;
        long total2 = 0;
        char[] buffer = new char[bufferSize > 0 ? bufferSize : 1024];
        while (true) {
            try {
                int numChars = source.read(buffer);
                if (numChars == -1) {
                    break;
                }
                if (numChars == 0) {
                    int singleChar = source.read();
                    if (singleChar < 0) {
                        break;
                    }
                    dest.write(singleChar);
                    dest.flush();
                    total = total2 + 1;
                    if (listener != null) {
                        try {
                            listener.bytesTransferred(total, 1, streamSize);
                        } catch (IOException e) {
                            e = e;
                            total2 = total;
                            throw new CopyStreamException("IOException caught while copying.", total2, e);
                        }
                    } else {
                        continue;
                    }
                } else {
                    dest.write(buffer, 0, numChars);
                    dest.flush();
                    total = total2 + ((long) numChars);
                    if (listener != null) {
                        listener.bytesTransferred(total, numChars, streamSize);
                    }
                }
                total2 = total;
            } catch (IOException e2) {
                e = e2;
            }
        }
        return total2;
    }

    public static long copyReader(Reader source, Writer dest, int bufferSize) throws CopyStreamException {
        return copyReader(source, dest, bufferSize, -1L, null);
    }

    public static long copyReader(Reader source, Writer dest) throws CopyStreamException {
        return copyReader(source, dest, 1024);
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    public static void closeQuietly(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }
}
