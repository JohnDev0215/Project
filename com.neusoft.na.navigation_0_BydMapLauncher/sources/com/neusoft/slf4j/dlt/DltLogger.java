package com.neusoft.slf4j.dlt;

import android.os.SystemClock;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes.dex */
class DltLogger {
    private static final int HEADER_LENGTH = 22;
    private static final byte HEADER_TYPE = 53;
    private static final int PAYLOAD_HEADER_LENGTH = 6;
    private static final byte[] PAYLOAD_TYPE_STRING = {0, 2, 0, 0};
    private final byte[] ECU_ID;
    private final byte[] STORAGE_HEADER;
    private final String appId;
    private byte counter;
    private final String ctxId;

    DltLogger(String str, String str2, String str3) {
        byte[] bArr = {79, 67, 84, 65};
        this.ECU_ID = bArr;
        byte[] bArr2 = {68, 76, 84, 1, 0, 0, 0, 0, 0, 0, 0, 0, 79, 67, 84, 65};
        this.STORAGE_HEADER = bArr2;
        if (str.length() != 4) {
            throw new IllegalArgumentException("ECU Id must be exactly 4 characters long");
        }
        if (containsNonPrintableChars(str)) {
            throw new IllegalArgumentException("ECU Id contains non-ascii-printable chars");
        }
        if (str2.length() != 4) {
            throw new IllegalArgumentException("App Id must be exactly 4 characters long");
        }
        if (containsNonPrintableChars(str2)) {
            throw new IllegalArgumentException("App Id contains non-ascii-printable char");
        }
        if (str3.length() != 4) {
            throw new IllegalArgumentException("Context Id must be exactly 4 characters long");
        }
        if (containsNonPrintableChars(str3)) {
            throw new IllegalArgumentException("Context Id contains non-ascii-printable char");
        }
        System.arraycopy(str.getBytes(), 0, bArr2, 12, 4);
        System.arraycopy(str.getBytes(), 0, bArr, 0, 4);
        this.appId = str2;
        this.ctxId = str3;
    }

    private static byte[] toByteList(short s, ByteOrder byteOrder) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(2);
        byteBufferAllocate.order(byteOrder);
        byteBufferAllocate.putShort(s);
        return byteBufferAllocate.array();
    }

    private static byte[] androidUptime() {
        return ByteBuffer.allocate(4).putInt((int) (SystemClock.elapsedRealtime() * 10)).order(ByteOrder.BIG_ENDIAN).array();
    }

    byte[] makeMessage(String str, DltLogLevel dltLogLevel) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            short length = (short) (str.length() + 28);
            byteArrayOutputStream.write(this.STORAGE_HEADER);
            writeStandardHeader(byteArrayOutputStream, length);
            writeExtendedHeader(byteArrayOutputStream, dltLogLevel);
            writePayload(byteArrayOutputStream, str);
        } catch (IOException e) {
            Log.e("DltLogger", "Error while making message", e);
        }
        return byteArrayOutputStream.toByteArray();
    }

    private void writeStandardHeader(ByteArrayOutputStream byteArrayOutputStream, short s) throws IOException {
        byteArrayOutputStream.write(53);
        byte b = this.counter;
        this.counter = (byte) (b + 1);
        byteArrayOutputStream.write(b);
        byteArrayOutputStream.write(toByteList(s, ByteOrder.BIG_ENDIAN));
        byteArrayOutputStream.write(this.ECU_ID);
        byteArrayOutputStream.write(androidUptime());
    }

    private void writeExtendedHeader(ByteArrayOutputStream byteArrayOutputStream, DltLogLevel dltLogLevel) throws IOException {
        byteArrayOutputStream.write(dltLogLevel.asExtHeader());
        byteArrayOutputStream.write(1);
        byteArrayOutputStream.write(this.appId.getBytes());
        byteArrayOutputStream.write(this.ctxId.getBytes());
    }

    private void writePayload(ByteArrayOutputStream byteArrayOutputStream, String str) throws IOException {
        byteArrayOutputStream.write(PAYLOAD_TYPE_STRING);
        byteArrayOutputStream.write(toByteList((short) str.length(), ByteOrder.LITTLE_ENDIAN));
        byteArrayOutputStream.write(str.getBytes());
    }

    private static boolean containsNonPrintableChars(String str) {
        for (char c : str.toCharArray()) {
            if (c < ' ' || c >= 127) {
                return true;
            }
        }
        return false;
    }
}
