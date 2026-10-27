package com.android.providers.media.util;

import android.media.ExifInterface;
import android.text.TextUtils;
import android.util.Xml;
import com.android.providers.media.MediaProvider;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Set;
import java.util.UUID;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class XmpInterface {
    private static final String NAME_DESCRIPTION = "Description";
    private static final String NAME_DOCUMENT_ID = "DocumentID";
    private static final String NAME_FORMAT = "format";
    private static final String NAME_INSTANCE_ID = "InstanceID";
    private static final String NAME_ORIGINAL_DOCUMENT_ID = "OriginalDocumentID";
    private static final String NS_DC = "http://purl.org/dc/elements/1.1/";
    private static final String NS_EXIF = "http://ns.adobe.com/exif/1.0/";
    private static final String NS_RDF = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
    private static final String NS_XMP = "http://ns.adobe.com/xap/1.0/";
    private static final String NS_XMPMM = "http://ns.adobe.com/xap/1.0/mm/";
    private String mDocumentId;
    private String mFormat;
    private String mInstanceId;
    private String mOriginalDocumentId;
    private final LongArray mRedactedRanges = new LongArray();
    private byte[] mRedactedXmp;

    private XmpInterface(byte[] bArr, Set<String> set, long[] jArr) throws IOException {
        this.mRedactedXmp = bArr;
        ByteCountingInputStream byteCountingInputStream = new ByteCountingInputStream(new ByteArrayInputStream(bArr));
        long offset = 0;
        long j = jArr.length == 0 ? 0L : jArr[0];
        try {
            XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
            xmlPullParserNewPullParser.setInput(byteCountingInputStream, StandardCharsets.UTF_8.name());
            while (true) {
                int next = xmlPullParserNewPullParser.next();
                if (next == 1) {
                    return;
                }
                if (next != 2) {
                    offset = byteCountingInputStream.getOffset(xmlPullParserNewPullParser);
                } else {
                    String namespace = xmlPullParserNewPullParser.getNamespace();
                    String name = xmlPullParserNewPullParser.getName();
                    if (NS_RDF.equals(namespace) && NAME_DESCRIPTION.equals(name)) {
                        this.mFormat = maybeOverride(this.mFormat, xmlPullParserNewPullParser.getAttributeValue(NS_DC, NAME_FORMAT));
                        this.mDocumentId = maybeOverride(this.mDocumentId, xmlPullParserNewPullParser.getAttributeValue(NS_XMPMM, NAME_DOCUMENT_ID));
                        this.mInstanceId = maybeOverride(this.mInstanceId, xmlPullParserNewPullParser.getAttributeValue(NS_XMPMM, NAME_INSTANCE_ID));
                        this.mOriginalDocumentId = maybeOverride(this.mOriginalDocumentId, xmlPullParserNewPullParser.getAttributeValue(NS_XMPMM, NAME_ORIGINAL_DOCUMENT_ID));
                    } else if (NS_DC.equals(namespace) && NAME_FORMAT.equals(name)) {
                        this.mFormat = maybeOverride(this.mFormat, xmlPullParserNewPullParser.nextText());
                    } else if (NS_XMPMM.equals(namespace) && NAME_DOCUMENT_ID.equals(name)) {
                        this.mDocumentId = maybeOverride(this.mDocumentId, xmlPullParserNewPullParser.nextText());
                    } else if (NS_XMPMM.equals(namespace) && NAME_INSTANCE_ID.equals(name)) {
                        this.mInstanceId = maybeOverride(this.mInstanceId, xmlPullParserNewPullParser.nextText());
                    } else if (NS_XMPMM.equals(namespace) && NAME_ORIGINAL_DOCUMENT_ID.equals(name)) {
                        this.mOriginalDocumentId = maybeOverride(this.mOriginalDocumentId, xmlPullParserNewPullParser.nextText());
                    } else if (NS_EXIF.equals(namespace)) {
                        if (set.contains(name)) {
                            while (true) {
                                if (xmlPullParserNewPullParser.next() == 3 && xmlPullParserNewPullParser.getName().equals(name)) {
                                    break;
                                }
                            }
                            long offset2 = byteCountingInputStream.getOffset(xmlPullParserNewPullParser);
                            this.mRedactedRanges.add(j + offset);
                            this.mRedactedRanges.add(j + offset2);
                            Arrays.fill(this.mRedactedXmp, (int) offset, (int) offset2, (byte) 32);
                            offset = offset2;
                        }
                    }
                }
            }
        } catch (XmlPullParserException e) {
            throw new IOException(e);
        }
    }

    public static XmpInterface fromContainer(InputStream inputStream) throws IOException {
        return fromContainer(new ExifInterface(inputStream));
    }

    public static XmpInterface fromContainer(InputStream inputStream, Set<String> set) throws IOException {
        return fromContainer(new ExifInterface(inputStream), set);
    }

    public static XmpInterface fromContainer(ExifInterface exifInterface) throws IOException {
        return fromContainer(exifInterface, MediaProvider.sRedactedExifTags);
    }

    public static XmpInterface fromContainer(ExifInterface exifInterface, Set<String> set) throws IOException {
        byte[] attributeBytes;
        long[] attributeRange;
        if (exifInterface.hasAttribute("Xmp")) {
            attributeBytes = exifInterface.getAttributeBytes("Xmp");
            attributeRange = exifInterface.getAttributeRange("Xmp");
        } else {
            attributeBytes = new byte[0];
            attributeRange = new long[0];
        }
        return new XmpInterface(attributeBytes, set, attributeRange);
    }

    public static XmpInterface fromContainer(IsoInterface isoInterface) throws IOException {
        return fromContainer(isoInterface, MediaProvider.sRedactedExifTags);
    }

    public static XmpInterface fromContainer(IsoInterface isoInterface, Set<String> set) throws IOException {
        UUID uuidFromString = UUID.fromString("be7acfcb-97a9-42e8-9c71-999491e3afac");
        byte[] boxBytes = isoInterface.getBoxBytes(uuidFromString);
        long[] boxRanges = isoInterface.getBoxRanges(uuidFromString);
        if (boxBytes == null) {
            boxBytes = isoInterface.getBoxBytes(IsoInterface.BOX_XMP);
            boxRanges = isoInterface.getBoxRanges(IsoInterface.BOX_XMP);
        }
        if (boxBytes == null) {
            boxBytes = new byte[0];
            boxRanges = new long[0];
        }
        return new XmpInterface(boxBytes, set, boxRanges);
    }

    public static XmpInterface fromSidecar(File file) throws IOException {
        return new XmpInterface(Files.readAllBytes(file.toPath()), MediaProvider.sRedactedExifTags, new long[0]);
    }

    private static String maybeOverride(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        return str2;
    }

    public String getFormat() {
        return this.mFormat;
    }

    public String getDocumentId() {
        return this.mDocumentId;
    }

    public String getInstanceId() {
        return this.mInstanceId;
    }

    public String getOriginalDocumentId() {
        return this.mOriginalDocumentId;
    }

    public byte[] getRedactedXmp() {
        return this.mRedactedXmp;
    }

    public LongArray getRedactionRanges() {
        return this.mRedactedRanges;
    }

    public static class ByteCountingInputStream extends InputStream {
        private final InputStream mWrapped;
        private final LongArray mOffsets = new LongArray();
        private int mLine = 1;
        private int mOffset = 0;

        public ByteCountingInputStream(InputStream inputStream) {
            this.mWrapped = inputStream;
        }

        public long getOffset(XmlPullParser xmlPullParser) {
            int lineNumber = xmlPullParser.getLineNumber() - 1;
            return (lineNumber == 0 ? 0L : this.mOffsets.get(lineNumber - 1)) + ((long) (xmlPullParser.getColumnNumber() - 1));
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr) throws IOException {
            return read(bArr, 0, bArr.length);
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            int i3 = this.mWrapped.read(bArr, i, i2);
            if (i3 == -1) {
                return -1;
            }
            for (int i4 = 0; i4 < i3; i4++) {
                if (bArr[i + i4] == 10) {
                    this.mOffsets.add(this.mLine - 1, this.mOffset + i4 + 1);
                    this.mLine++;
                }
            }
            this.mOffset += i3;
            return i3;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            int i = this.mWrapped.read();
            if (i == -1) {
                return -1;
            }
            int i2 = this.mOffset + 1;
            this.mOffset = i2;
            if (i == 10) {
                this.mOffsets.add(this.mLine - 1, i2);
                this.mLine++;
            }
            return i;
        }

        @Override // java.io.InputStream
        public long skip(long j) throws IOException {
            return super.skip(j);
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            return this.mWrapped.available();
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.mWrapped.close();
        }

        public String toString() {
            return Arrays.toString(this.mOffsets.toArray());
        }
    }
}
