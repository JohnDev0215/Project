package org.apache.tools.zip;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import java.util.zip.ZipException;

/* JADX INFO: loaded from: classes.dex */
public class ZipFile {
    private static final int CFD_LOCATOR_OFFSET = 16;
    private static final int CFH_LEN = 42;
    private static final long LFH_OFFSET_FOR_FILENAME_LENGTH = 26;
    private static final int MIN_EOCD_SIZE = 22;
    private RandomAccessFile archive;
    private Hashtable dataOffsets;
    private String encoding;
    private Hashtable entries;
    private Hashtable nameMap;

    public ZipFile(File f) throws IOException {
        this(f, (String) null);
    }

    public ZipFile(String name) throws IOException {
        this(new File(name), (String) null);
    }

    public ZipFile(String name, String encoding) throws IOException {
        this(new File(name), encoding);
    }

    public ZipFile(File f, String encoding) throws IOException {
        this.entries = new Hashtable();
        this.nameMap = new Hashtable();
        this.dataOffsets = new Hashtable();
        this.encoding = null;
        this.encoding = encoding;
        this.archive = new RandomAccessFile(f, "r");
        try {
            populateFromCentralDirectory();
            resolveLocalFileHeaderData();
        } catch (IOException e) {
            try {
                this.archive.close();
            } catch (IOException e2) {
            }
            throw e;
        }
    }

    public String getEncoding() {
        return this.encoding;
    }

    public void close() throws IOException {
        this.archive.close();
    }

    public Enumeration getEntries() {
        return this.entries.keys();
    }

    public ZipEntry getEntry(String name) {
        return (ZipEntry) this.nameMap.get(name);
    }

    public InputStream getInputStream(ZipEntry ze) throws IOException {
        Long start = (Long) this.dataOffsets.get(ze);
        if (start == null) {
            return null;
        }
        BoundedInputStream bis = new BoundedInputStream(start.longValue(), ze.getCompressedSize());
        int method = ze.getMethod();
        if (method == 0) {
            return bis;
        }
        if (method == 8) {
            bis.addDummy();
            return new InflaterInputStream(bis, new Inflater(true));
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Found unsupported compression method ");
        stringBuffer.append(ze.getMethod());
        throw new ZipException(stringBuffer.toString());
    }

    private void populateFromCentralDirectory() throws IOException {
        positionAtCentralDirectory();
        byte[] cfh = new byte[42];
        byte[] signatureBytes = new byte[4];
        this.archive.readFully(signatureBytes);
        for (ZipLong sig = new ZipLong(signatureBytes); sig.equals(ZipOutputStream.CFH_SIG); sig = new ZipLong(signatureBytes)) {
            this.archive.readFully(cfh);
            ZipEntry ze = new ZipEntry();
            ZipShort versionMadeBy = new ZipShort(cfh, 0);
            ze.setPlatform((versionMadeBy.getValue() >> 8) & 15);
            int off = 0 + 2 + 4;
            ze.setMethod(new ZipShort(cfh, off).getValue());
            int off2 = off + 2;
            ze.setTime(fromDosTime(new ZipLong(cfh, off2)).getTime());
            int off3 = off2 + 4;
            ze.setCrc(new ZipLong(cfh, off3).getValue());
            int off4 = off3 + 4;
            ze.setCompressedSize(new ZipLong(cfh, off4).getValue());
            int off5 = off4 + 4;
            ze.setSize(new ZipLong(cfh, off5).getValue());
            int off6 = off5 + 4;
            int fileNameLen = new ZipShort(cfh, off6).getValue();
            int off7 = off6 + 2;
            int extraLen = new ZipShort(cfh, off7).getValue();
            int off8 = off7 + 2;
            int commentLen = new ZipShort(cfh, off8).getValue();
            int off9 = off8 + 2 + 2;
            ze.setInternalAttributes(new ZipShort(cfh, off9).getValue());
            int off10 = off9 + 2;
            ze.setExternalAttributes(new ZipLong(cfh, off10).getValue());
            this.entries.put(ze, new Long(new ZipLong(cfh, off10 + 4).getValue()));
            byte[] fileName = new byte[fileNameLen];
            this.archive.readFully(fileName);
            ze.setName(getString(fileName));
            this.nameMap.put(ze.getName(), ze);
            this.archive.skipBytes(extraLen);
            byte[] comment = new byte[commentLen];
            this.archive.readFully(comment);
            ze.setComment(getString(comment));
            this.archive.readFully(signatureBytes);
        }
    }

    private void positionAtCentralDirectory() throws IOException {
        long off = this.archive.length() - 22;
        this.archive.seek(off);
        byte[] sig = ZipOutputStream.EOCD_SIG.getBytes();
        int curr = this.archive.read();
        boolean found = false;
        while (true) {
            if (curr == -1) {
                break;
            }
            if (curr == sig[0]) {
                int curr2 = this.archive.read();
                if (curr2 == sig[1]) {
                    int curr3 = this.archive.read();
                    if (curr3 == sig[2]) {
                        int curr4 = this.archive.read();
                        if (curr4 == sig[3]) {
                            found = true;
                            break;
                        }
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }
            }
            long j = off - 1;
            off = j;
            this.archive.seek(j);
            curr = this.archive.read();
        }
        if (!found) {
            throw new ZipException("archive is not a ZIP archive");
        }
        this.archive.seek(16 + off);
        byte[] cfdOffset = new byte[4];
        this.archive.readFully(cfdOffset);
        this.archive.seek(new ZipLong(cfdOffset).getValue());
    }

    private void resolveLocalFileHeaderData() throws IOException {
        Enumeration e = getEntries();
        while (e.hasMoreElements()) {
            ZipEntry ze = (ZipEntry) e.nextElement();
            long offset = ((Long) this.entries.get(ze)).longValue();
            this.archive.seek(offset + LFH_OFFSET_FOR_FILENAME_LENGTH);
            byte[] b = new byte[2];
            this.archive.readFully(b);
            int fileNameLen = new ZipShort(b).getValue();
            this.archive.readFully(b);
            int extraFieldLen = new ZipShort(b).getValue();
            this.archive.skipBytes(fileNameLen);
            byte[] localExtraData = new byte[extraFieldLen];
            this.archive.readFully(localExtraData);
            ze.setExtra(localExtraData);
            this.dataOffsets.put(ze, new Long(LFH_OFFSET_FOR_FILENAME_LENGTH + offset + 2 + 2 + ((long) fileNameLen) + ((long) extraFieldLen)));
        }
    }

    protected static Date fromDosTime(ZipLong l) {
        long dosTime = l.getValue();
        Calendar cal = Calendar.getInstance();
        cal.set(1, ((int) ((dosTime >> 25) & 127)) + 1980);
        cal.set(2, ((int) ((dosTime >> 21) & 15)) - 1);
        cal.set(5, ((int) (dosTime >> 16)) & 31);
        cal.set(11, ((int) (dosTime >> 11)) & 31);
        cal.set(12, ((int) (dosTime >> 5)) & 63);
        cal.set(13, ((int) (dosTime << 1)) & 62);
        return cal.getTime();
    }

    protected String getString(byte[] bytes) throws ZipException {
        if (this.encoding == null) {
            return new String(bytes);
        }
        try {
            return new String(bytes, this.encoding);
        } catch (UnsupportedEncodingException uee) {
            throw new ZipException(uee.getMessage());
        }
    }

    private class BoundedInputStream extends InputStream {
        private boolean addDummyByte = false;
        private long loc;
        private long remaining;

        BoundedInputStream(long start, long remaining) {
            this.remaining = remaining;
            this.loc = start;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            int i;
            long j = this.remaining;
            this.remaining = j - 1;
            if (j > 0) {
                synchronized (ZipFile.this.archive) {
                    RandomAccessFile randomAccessFile = ZipFile.this.archive;
                    long j2 = this.loc;
                    this.loc = 1 + j2;
                    randomAccessFile.seek(j2);
                    i = ZipFile.this.archive.read();
                }
                return i;
            }
            if (this.addDummyByte) {
                this.addDummyByte = false;
                return 0;
            }
            return -1;
        }

        @Override // java.io.InputStream
        public int read(byte[] b, int off, int len) throws IOException {
            int ret;
            long j = this.remaining;
            if (j <= 0) {
                if (this.addDummyByte) {
                    this.addDummyByte = false;
                    b[off] = 0;
                    return 1;
                }
                return -1;
            }
            if (len <= 0) {
                return 0;
            }
            if (len > j) {
                len = (int) j;
            }
            synchronized (ZipFile.this.archive) {
                ZipFile.this.archive.seek(this.loc);
                ret = ZipFile.this.archive.read(b, off, len);
            }
            if (ret > 0) {
                this.loc += (long) ret;
                this.remaining -= (long) ret;
            }
            return ret;
        }

        void addDummy() {
            this.addDummyByte = true;
        }
    }
}
