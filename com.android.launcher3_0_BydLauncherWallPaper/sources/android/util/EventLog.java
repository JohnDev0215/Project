package android.util;

import android.annotation.SystemApi;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes3.dex */
public class EventLog {
    private static final String COMMENT_PATTERN = "^\\s*(#.*)?$";
    private static final String TAG = "EventLog";
    private static final String TAGS_FILE = "/system/etc/event-log-tags";
    private static final String TAG_PATTERN = "^\\s*(\\d+)\\s+(\\w+)\\s*(\\(.*\\))?\\s*$";
    private static HashMap<String, Integer> sTagCodes;
    private static HashMap<Integer, String> sTagNames;

    public static native void readEvents(int[] iArr, Collection<Event> collection) throws IOException;

    @SystemApi
    public static native void readEventsOnWrapping(int[] iArr, long j, Collection<Event> collection) throws IOException;

    public static native int writeEvent(int i, float f);

    public static native int writeEvent(int i, int i2);

    public static native int writeEvent(int i, long j);

    public static native int writeEvent(int i, String str);

    public static native int writeEvent(int i, Object... objArr);

    public static final class Event {
        private static final byte FLOAT_TYPE = 4;
        private static final int HEADER_SIZE_OFFSET = 2;
        private static final byte INT_TYPE = 0;
        private static final int LENGTH_OFFSET = 0;
        private static final byte LIST_TYPE = 3;
        private static final byte LONG_TYPE = 1;
        private static final int NANOSECONDS_OFFSET = 16;
        private static final int PROCESS_OFFSET = 4;
        private static final int SECONDS_OFFSET = 12;
        private static final byte STRING_TYPE = 2;
        private static final int TAG_LENGTH = 4;
        private static final int THREAD_OFFSET = 8;
        private static final int UID_OFFSET = 24;
        private static final int V1_PAYLOAD_START = 20;
        private final ByteBuffer mBuffer;
        private Exception mLastWtf;

        Event(byte[] bArr) {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
            this.mBuffer = byteBufferWrap;
            byteBufferWrap.order(ByteOrder.nativeOrder());
        }

        public int getProcessId() {
            return this.mBuffer.getInt(4);
        }

        @SystemApi
        public int getUid() {
            try {
                return this.mBuffer.getInt(24);
            } catch (IndexOutOfBoundsException unused) {
                return -1;
            }
        }

        public int getThreadId() {
            return this.mBuffer.getInt(8);
        }

        public long getTimeNanos() {
            return (((long) this.mBuffer.getInt(12)) * 1000000000) + ((long) this.mBuffer.getInt(16));
        }

        public int getTag() {
            return this.mBuffer.getInt(getHeaderSize());
        }

        private int getHeaderSize() {
            short s = this.mBuffer.getShort(2);
            if (s != 0) {
                return s;
            }
            return 20;
        }

        public synchronized Object getData() {
            try {
                try {
                    int headerSize = getHeaderSize();
                    ByteBuffer byteBuffer = this.mBuffer;
                    byteBuffer.limit(byteBuffer.getShort(0) + headerSize);
                    int i = headerSize + 4;
                    if (i >= this.mBuffer.limit()) {
                        return null;
                    }
                    this.mBuffer.position(i);
                    return decodeObject();
                } catch (BufferUnderflowException e) {
                    Log.wtf(EventLog.TAG, "Truncated entry payload: tag=" + getTag(), e);
                    this.mLastWtf = e;
                    return null;
                }
            } catch (IllegalArgumentException e2) {
                Log.wtf(EventLog.TAG, "Illegal entry payload: tag=" + getTag(), e2);
                this.mLastWtf = e2;
                return null;
            }
        }

        public Event withNewData(Object obj) {
            byte[] bArrEncodeObject = encodeObject(obj);
            if (bArrEncodeObject.length > 65531) {
                throw new IllegalArgumentException("Payload too long");
            }
            int headerSize = getHeaderSize() + 4;
            byte[] bArr = new byte[bArrEncodeObject.length + headerSize];
            System.arraycopy(this.mBuffer.array(), 0, bArr, 0, headerSize);
            System.arraycopy(bArrEncodeObject, 0, bArr, headerSize, bArrEncodeObject.length);
            Event event = new Event(bArr);
            event.mBuffer.putShort(0, (short) (bArrEncodeObject.length + 4));
            return event;
        }

        private Object decodeObject() {
            byte b = this.mBuffer.get();
            if (b == 0) {
                return Integer.valueOf(this.mBuffer.getInt());
            }
            if (b == 1) {
                return Long.valueOf(this.mBuffer.getLong());
            }
            if (b == 2) {
                try {
                    int i = this.mBuffer.getInt();
                    int iPosition = this.mBuffer.position();
                    this.mBuffer.position(iPosition + i);
                    return new String(this.mBuffer.array(), iPosition, i, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    Log.wtf(EventLog.TAG, "UTF-8 is not supported", e);
                    this.mLastWtf = e;
                    return null;
                }
            }
            if (b != 3) {
                if (b == 4) {
                    return Float.valueOf(this.mBuffer.getFloat());
                }
                throw new IllegalArgumentException("Unknown entry type: " + ((int) b));
            }
            int i2 = this.mBuffer.get();
            if (i2 < 0) {
                i2 += 256;
            }
            Object[] objArr = new Object[i2];
            for (int i3 = 0; i3 < i2; i3++) {
                objArr[i3] = decodeObject();
            }
            return objArr;
        }

        private static byte[] encodeObject(Object obj) {
            byte[] bytes;
            if (obj == null) {
                return new byte[0];
            }
            if (obj instanceof Integer) {
                return ByteBuffer.allocate(5).order(ByteOrder.nativeOrder()).put((byte) 0).putInt(((Integer) obj).intValue()).array();
            }
            if (obj instanceof Long) {
                return ByteBuffer.allocate(9).order(ByteOrder.nativeOrder()).put((byte) 1).putLong(((Long) obj).longValue()).array();
            }
            if (obj instanceof Float) {
                return ByteBuffer.allocate(5).order(ByteOrder.nativeOrder()).put((byte) 4).putFloat(((Float) obj).floatValue()).array();
            }
            if (obj instanceof String) {
                try {
                    bytes = ((String) obj).getBytes("UTF-8");
                } catch (UnsupportedEncodingException unused) {
                    bytes = new byte[0];
                }
                return ByteBuffer.allocate(bytes.length + 5).order(ByteOrder.nativeOrder()).put((byte) 2).putInt(bytes.length).put(bytes).array();
            }
            if (obj instanceof Object[]) {
                Object[] objArr = (Object[]) obj;
                if (objArr.length > 255) {
                    throw new IllegalArgumentException("Object array too long");
                }
                byte[][] bArr = new byte[objArr.length][];
                int length = 0;
                for (int i = 0; i < objArr.length; i++) {
                    bArr[i] = encodeObject(objArr[i]);
                    length += bArr[i].length;
                }
                ByteBuffer byteBufferPut = ByteBuffer.allocate(length + 2).order(ByteOrder.nativeOrder()).put((byte) 3).put((byte) objArr.length);
                for (int i2 = 0; i2 < objArr.length; i2++) {
                    byteBufferPut.put(bArr[i2]);
                }
                return byteBufferPut.array();
            }
            throw new IllegalArgumentException("Unknown object type " + obj);
        }

        public static Event fromBytes(byte[] bArr) {
            return new Event(bArr);
        }

        public byte[] getBytes() {
            byte[] bArrArray = this.mBuffer.array();
            return Arrays.copyOf(bArrArray, bArrArray.length);
        }

        public Exception getLastError() {
            return this.mLastWtf;
        }

        public void clearError() {
            this.mLastWtf = null;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return Arrays.equals(this.mBuffer.array(), ((Event) obj).mBuffer.array());
        }

        public int hashCode() {
            return Arrays.hashCode(this.mBuffer.array());
        }
    }

    public static String getTagName(int i) {
        readTagsFile();
        return sTagNames.get(Integer.valueOf(i));
    }

    public static int getTagCode(String str) {
        readTagsFile();
        Integer num = sTagCodes.get(str);
        if (num != null) {
            return num.intValue();
        }
        return -1;
    }

    private static synchronized void readTagsFile() {
        if (sTagCodes == null || sTagNames == null) {
            sTagCodes = new HashMap<>();
            sTagNames = new HashMap<>();
            Pattern patternCompile = Pattern.compile(COMMENT_PATTERN);
            Pattern patternCompile2 = Pattern.compile(TAG_PATTERN);
            BufferedReader bufferedReader = null;
            try {
                try {
                    try {
                        BufferedReader bufferedReader2 = new BufferedReader(new FileReader(TAGS_FILE), 256);
                        while (true) {
                            try {
                                String line = bufferedReader2.readLine();
                                if (line == null) {
                                    break;
                                }
                                if (!patternCompile.matcher(line).matches()) {
                                    Matcher matcher = patternCompile2.matcher(line);
                                    if (!matcher.matches()) {
                                        Log.wtf(TAG, "Bad entry in /system/etc/event-log-tags: " + line);
                                    } else {
                                        try {
                                            int i = Integer.parseInt(matcher.group(1));
                                            String strGroup = matcher.group(2);
                                            sTagCodes.put(strGroup, Integer.valueOf(i));
                                            sTagNames.put(Integer.valueOf(i), strGroup);
                                        } catch (NumberFormatException e) {
                                            Log.wtf(TAG, "Error in /system/etc/event-log-tags: " + line, e);
                                        }
                                    }
                                }
                            } catch (IOException e2) {
                                e = e2;
                                bufferedReader = bufferedReader2;
                                Log.wtf(TAG, "Error reading /system/etc/event-log-tags", e);
                                if (bufferedReader != null) {
                                    bufferedReader.close();
                                }
                            } catch (Throwable th) {
                                th = th;
                                bufferedReader = bufferedReader2;
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (IOException unused) {
                                    }
                                }
                                throw th;
                            }
                        }
                        bufferedReader2.close();
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (IOException e3) {
                    e = e3;
                }
            } catch (IOException unused2) {
            }
        }
    }
}
