package com.amazon.alexa.voice.core.internal.http;

import com.amazon.alexa.voice.core.internal.http.Multipart;
import com.amazon.alexa.voice.core.internal.util.ArrayIterator;
import com.amazon.alexa.voice.core.internal.util.Preconditions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public final class MultipartResponse implements Iterable<PartialResponse> {
    private PartialResponse[] responses;

    private MultipartResponse(PartialResponse[] partialResponseArr) {
        this.responses = partialResponseArr;
    }

    public int getCount() {
        return this.responses.length;
    }

    @Override // java.lang.Iterable
    public Iterator<PartialResponse> iterator() {
        return new ArrayIterator(this.responses);
    }

    public static MultipartResponse create(String str, byte[] bArr) throws IOException {
        Preconditions.notNull(str, "contentType == null");
        Preconditions.notNull(bArr, "body == null");
        String str2 = Header.parse(str).get("boundary");
        if (str2 == null) {
            throw new IOException("Invalid content type " + str);
        }
        Multipart multipart = new Multipart(bArr, str2);
        ArrayList arrayList = new ArrayList();
        while (true) {
            Multipart.Part partNextPart = multipart.nextPart();
            if (partNextPart != null) {
                arrayList.add(new PartialResponse(partNextPart.data(), partNextPart.offset(), partNextPart.size()));
            } else {
                return new MultipartResponse((PartialResponse[]) arrayList.toArray(new PartialResponse[arrayList.size()]));
            }
        }
    }

    public static class PartialResponse {
        private final byte[] body;
        private final String[] nameValues;
        private final int offset;
        private final int size;

        PartialResponse(byte[] bArr, int i, int i2) {
            ArrayList arrayList = new ArrayList();
            int i3 = i;
            while (true) {
                int iNextEndOfLine = nextEndOfLine(i3, bArr, i, i2);
                if (iNextEndOfLine == -1) {
                    break;
                }
                if (iNextEndOfLine == i3) {
                    i3 = iNextEndOfLine + 2;
                    break;
                }
                int i4 = iNextEndOfLine - i3;
                int iFindSeparator = findSeparator(bArr, i3, i4);
                if (iFindSeparator == -1) {
                    break;
                }
                String strTrim = new String(bArr, i3, iFindSeparator).trim();
                if (!isValidHeaderName(strTrim)) {
                    break;
                }
                String strTrim2 = new String(bArr, i3 + iFindSeparator + 1, i4 - iFindSeparator).trim();
                arrayList.add(strTrim);
                arrayList.add(strTrim2);
                i3 = iNextEndOfLine + 2;
            }
            this.body = bArr;
            this.size = (i2 + i) - i3;
            this.offset = i3;
            this.nameValues = (String[]) arrayList.toArray(new String[arrayList.size()]);
        }

        private boolean isValidHeaderName(String str) {
            int length = str.length();
            if (length == 0) {
                return false;
            }
            for (int i = 0; i < length; i++) {
                char cCharAt = str.charAt(i);
                if ((cCharAt < 'a' || cCharAt > 'z') && ((cCharAt < 'A' || cCharAt > 'Z') && ((cCharAt < '0' || cCharAt > '9') && cCharAt != '-'))) {
                    return false;
                }
            }
            return true;
        }

        private int findSeparator(byte[] bArr, int i, int i2) {
            for (int i3 = 0; i3 < i2; i3++) {
                if (bArr[i + i3] == 58) {
                    return i3;
                }
            }
            return -1;
        }

        private int nextEndOfLine(int i, byte[] bArr, int i2, int i3) {
            int i4;
            int i5 = i2 + i3;
            while (i < i5) {
                if (bArr[i] == 13 && ((i4 = i + 1) == i5 || bArr[i4] == 10)) {
                    return i;
                }
                i++;
                if (i == i5) {
                    return i5;
                }
            }
            return -1;
        }

        public byte[] body() {
            return this.body;
        }

        public int offset() {
            return this.offset;
        }

        public int size() {
            return this.size;
        }

        public String header(String str) {
            int length = this.nameValues.length - 1;
            for (int i = 0; i < length; i += 2) {
                if (str.equalsIgnoreCase(this.nameValues[i])) {
                    return this.nameValues[i + 1];
                }
            }
            return null;
        }
    }
}
