package com.android.providers.media.util;

import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import java.io.EOFException;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class IsoInterface {
    public static final int BOX_FTYP = 1718909296;
    public static final int BOX_GPS = 1735422752;
    public static final int BOX_GPS0 = 1735422768;
    public static final int BOX_HDLR = 1751411826;
    public static final int BOX_LOCI = 1819239273;
    public static final int BOX_META = 1835365473;
    public static final int BOX_UUID = 1970628964;
    public static final int BOX_XMP = 1481461855;
    public static final int BOX_XYZ = -1451722374;
    private static final String TAG = "IsoInterface";
    private static final boolean LOGV = Log.isLoggable(TAG, 2);
    private List<Box> mRoots = new ArrayList();
    private List<Box> mFlattened = new ArrayList();

    private static boolean isBoxParent(int i) {
        switch (i) {
            case 1684631142:
            case 1701082227:
            case 1751740006:
            case 1752069225:
            case 1768715124:
            case 1768977007:
            case 1785737832:
            case 1835297121:
            case BOX_META /* 1835365473 */:
            case 1835430497:
            case 1835626086:
            case 1836019558:
            case 1836019574:
            case 1936289382:
            case 1937007212:
            case 1953653094:
            case 1953653099:
            case 1953654118:
            case 1969517665:
                return true;
            default:
                return false;
        }
    }

    private static class Box {
        public List<Box> children;
        public byte[] data;
        public int headerSize;
        public final long[] range;
        public final int type;
        public UUID uuid;

        public Box(int i, long[] jArr) {
            this.type = i;
            this.range = jArr;
        }
    }

    public static String typeToString(int i) {
        byte[] bArr = new byte[4];
        Memory.pokeInt(bArr, 0, i, ByteOrder.BIG_ENDIAN);
        return new String(bArr);
    }

    private static int readInt(FileDescriptor fileDescriptor) throws IOException, ErrnoException {
        byte[] bArr = new byte[4];
        if (Os.read(fileDescriptor, bArr, 0, 4) == 4) {
            return Memory.peekInt(bArr, 0, ByteOrder.BIG_ENDIAN);
        }
        throw new EOFException();
    }

    private static UUID readUuid(FileDescriptor fileDescriptor) throws IOException, ErrnoException {
        return new UUID((((long) readInt(fileDescriptor)) << 32) | (((long) readInt(fileDescriptor)) & 4294967295L), (((long) readInt(fileDescriptor)) << 32) | (((long) readInt(fileDescriptor)) & 4294967295L));
    }

    private static Box parseNextBox(FileDescriptor fileDescriptor, long j, String str) throws IOException, ErrnoException {
        long jLseek = Os.lseek(fileDescriptor, 0L, OsConstants.SEEK_CUR);
        long j2 = j - jLseek;
        int i = 8;
        if (j2 < 8) {
            return null;
        }
        long unsignedLong = Integer.toUnsignedLong(readInt(fileDescriptor));
        int i2 = readInt(fileDescriptor);
        if (unsignedLong != 0) {
            if (unsignedLong == 1) {
                j2 = (((long) readInt(fileDescriptor)) & 4294967295L) | (((long) readInt(fileDescriptor)) << 32);
                i = 16;
            } else {
                j2 = unsignedLong;
            }
        }
        long j3 = i;
        if (j2 >= j3) {
            long j4 = jLseek + j2;
            if (j4 <= j) {
                Box box = new Box(i2, new long[]{jLseek, j2});
                box.headerSize = i;
                if (i2 == 1970628964) {
                    box.headerSize = i + 16;
                    box.uuid = readUuid(fileDescriptor);
                    if (LOGV) {
                        Log.v(TAG, str + "  UUID " + box.uuid);
                    }
                    if (j2 > 2147483647L) {
                        Log.w(TAG, "Skipping abnormally large uuid box");
                        return null;
                    }
                    byte[] bArr = new byte[(int) (j2 - ((long) box.headerSize))];
                    box.data = bArr;
                    Os.read(fileDescriptor, bArr, 0, bArr.length);
                } else if (i2 == 1481461855) {
                    if (j2 > 2147483647L) {
                        Log.w(TAG, "Skipping abnormally large xmp box");
                        return null;
                    }
                    byte[] bArr2 = new byte[(int) (j2 - ((long) i))];
                    box.data = bArr2;
                    Os.read(fileDescriptor, bArr2, 0, bArr2.length);
                } else if (i2 == 1835365473 && j2 != j3) {
                    readInt(fileDescriptor);
                    if (readInt(fileDescriptor) != 1751411826) {
                        box.headerSize += 4;
                    }
                    Os.lseek(fileDescriptor, ((long) box.headerSize) + jLseek, OsConstants.SEEK_SET);
                }
                if (LOGV) {
                    Log.v(TAG, str + "Found box " + typeToString(i2) + " at " + jLseek + " hdr " + box.headerSize + " length " + j2);
                }
                if (isBoxParent(i2)) {
                    box.children = new ArrayList();
                    while (true) {
                        Box nextBox = parseNextBox(fileDescriptor, j4, str + "  ");
                        if (nextBox == null) {
                            break;
                        }
                        box.children.add(nextBox);
                    }
                }
                Os.lseek(fileDescriptor, j4, OsConstants.SEEK_SET);
                return box;
            }
        }
        Log.w(TAG, "Invalid box at " + jLseek + " of length " + j2 + ". End of parent " + j);
        return null;
    }

    private IsoInterface(FileDescriptor fileDescriptor) throws IOException {
        try {
            Os.lseek(fileDescriptor, 4L, OsConstants.SEEK_SET);
            boolean z = false;
            try {
                if (readInt(fileDescriptor) == 1718909296) {
                    z = true;
                }
            } catch (EOFException unused) {
            }
            if (!z) {
                if (LOGV) {
                    Log.w(TAG, "Missing 'ftyp' header");
                    return;
                }
                return;
            }
            long jLseek = Os.lseek(fileDescriptor, 0L, OsConstants.SEEK_END);
            Os.lseek(fileDescriptor, 0L, OsConstants.SEEK_SET);
            while (true) {
                Box nextBox = parseNextBox(fileDescriptor, jLseek, "");
                if (nextBox == null) {
                    break;
                } else {
                    this.mRoots.add(nextBox);
                }
            }
            ArrayDeque arrayDeque = new ArrayDeque(this.mRoots);
            while (!arrayDeque.isEmpty()) {
                Box box = (Box) arrayDeque.poll();
                this.mFlattened.add(box);
                List<Box> list = box.children;
                if (list != null) {
                    arrayDeque.addAll(list);
                }
            }
        } catch (ErrnoException e) {
            throw e.rethrowAsIOException();
        }
    }

    public static IsoInterface fromFile(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            IsoInterface isoInterfaceFromFileDescriptor = fromFileDescriptor(fileInputStream.getFD());
            fileInputStream.close();
            return isoInterfaceFromFileDescriptor;
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static IsoInterface fromFileDescriptor(FileDescriptor fileDescriptor) throws IOException {
        return new IsoInterface(fileDescriptor);
    }

    public long[] getBoxRanges(int i) {
        LongArray longArray = new LongArray();
        for (Box box : this.mFlattened) {
            if (box.type == i) {
                longArray.add(box.range[0] + ((long) box.headerSize));
                long[] jArr = box.range;
                longArray.add(jArr[0] + jArr[1]);
            }
        }
        return longArray.toArray();
    }

    public long[] getBoxRanges(UUID uuid) {
        LongArray longArray = new LongArray();
        for (Box box : this.mFlattened) {
            if (box.type == 1970628964 && Objects.equals(box.uuid, uuid)) {
                longArray.add(box.range[0] + ((long) box.headerSize));
                long[] jArr = box.range;
                longArray.add(jArr[0] + jArr[1]);
            }
        }
        return longArray.toArray();
    }

    public byte[] getBoxBytes(int i) {
        for (Box box : this.mFlattened) {
            if (box.type == i) {
                return box.data;
            }
        }
        return null;
    }

    public byte[] getBoxBytes(UUID uuid) {
        for (Box box : this.mFlattened) {
            if (box.type == 1970628964 && Objects.equals(box.uuid, uuid)) {
                return box.data;
            }
        }
        return null;
    }

    public static boolean isSupportedMimeType(String str) {
        if (str == null) {
            throw new NullPointerException("mimeType shouldn't be null");
        }
        switch (str.toLowerCase(Locale.ROOT)) {
            case "audio/3gp2":
            case "audio/3gpp":
            case "audio/3gpp2":
            case "audio/aac":
            case "audio/mp4":
            case "audio/mpeg":
            case "video/3gp2":
            case "video/3gpp":
            case "video/3gpp2":
            case "video/mj2":
            case "video/mp4":
            case "video/mpeg":
            case "video/x-flv":
                return true;
            default:
                return false;
        }
    }
}
