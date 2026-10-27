package org.apache.tools.zip;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.NoSuchElementException;
import java.util.Vector;
import java.util.zip.ZipException;

/* JADX INFO: loaded from: classes.dex */
public class ZipEntry extends java.util.zip.ZipEntry implements Cloneable {
    private static final int PLATFORM_FAT = 0;
    private static final int PLATFORM_UNIX = 3;
    static /* synthetic */ Class class$java$util$zip$ZipEntry;
    private Long compressedSize;
    private long externalAttributes;
    private Vector extraFields;
    private int internalAttributes;
    private String name;
    private int platform;
    private static Method setCompressedSizeMethod = null;
    private static Object lockReflection = new Object();
    private static boolean triedToGetMethod = false;

    public ZipEntry(String name) {
        super(name);
        this.internalAttributes = 0;
        this.platform = 0;
        this.externalAttributes = 0L;
        this.extraFields = new Vector();
        this.name = null;
        this.compressedSize = null;
    }

    public ZipEntry(java.util.zip.ZipEntry entry) throws ZipException {
        super(entry.getName());
        this.internalAttributes = 0;
        this.platform = 0;
        this.externalAttributes = 0L;
        this.extraFields = new Vector();
        this.name = null;
        this.compressedSize = null;
        setComment(entry.getComment());
        setMethod(entry.getMethod());
        setTime(entry.getTime());
        long size = entry.getSize();
        if (size > 0) {
            setSize(size);
        }
        long cSize = entry.getCompressedSize();
        if (cSize > 0) {
            setComprSize(cSize);
        }
        long crc = entry.getCrc();
        if (crc > 0) {
            setCrc(crc);
        }
        byte[] extra = entry.getExtra();
        if (extra != null) {
            setExtraFields(ExtraFieldUtils.parse(extra));
        } else {
            setExtra();
        }
    }

    public ZipEntry(ZipEntry entry) throws ZipException {
        this((java.util.zip.ZipEntry) entry);
        setInternalAttributes(entry.getInternalAttributes());
        setExternalAttributes(entry.getExternalAttributes());
        setExtraFields(entry.getExtraFields());
    }

    protected ZipEntry() {
        super("");
        this.internalAttributes = 0;
        this.platform = 0;
        this.externalAttributes = 0L;
        this.extraFields = new Vector();
        this.name = null;
        this.compressedSize = null;
    }

    @Override // java.util.zip.ZipEntry
    public Object clone() {
        try {
            ZipEntry e = (ZipEntry) super.clone();
            e.setName(getName());
            e.setComment(getComment());
            e.setMethod(getMethod());
            e.setTime(getTime());
            long size = getSize();
            if (size > 0) {
                e.setSize(size);
            }
            long cSize = getCompressedSize();
            if (cSize > 0) {
                e.setComprSize(cSize);
            }
            long crc = getCrc();
            if (crc > 0) {
                e.setCrc(crc);
            }
            e.extraFields = (Vector) this.extraFields.clone();
            e.setInternalAttributes(getInternalAttributes());
            e.setExternalAttributes(getExternalAttributes());
            e.setExtraFields(getExtraFields());
            return e;
        } catch (Throwable th) {
            return null;
        }
    }

    public int getInternalAttributes() {
        return this.internalAttributes;
    }

    public void setInternalAttributes(int value) {
        this.internalAttributes = value;
    }

    public long getExternalAttributes() {
        return this.externalAttributes;
    }

    public void setExternalAttributes(long value) {
        this.externalAttributes = value;
    }

    public void setUnixMode(int mode) {
        setExternalAttributes((mode << 16) | ((mode & 128) == 0 ? 1 : 0) | (isDirectory() ? 16 : 0));
        this.platform = 3;
    }

    public int getUnixMode() {
        return (int) ((getExternalAttributes() >> 16) & 65535);
    }

    public int getPlatform() {
        return this.platform;
    }

    protected void setPlatform(int platform) {
        this.platform = platform;
    }

    public void setExtraFields(ZipExtraField[] fields) {
        this.extraFields.removeAllElements();
        for (ZipExtraField zipExtraField : fields) {
            this.extraFields.addElement(zipExtraField);
        }
        setExtra();
    }

    public ZipExtraField[] getExtraFields() {
        ZipExtraField[] result = new ZipExtraField[this.extraFields.size()];
        this.extraFields.copyInto(result);
        return result;
    }

    public void addExtraField(ZipExtraField ze) {
        ZipShort type = ze.getHeaderId();
        boolean done = false;
        for (int i = 0; !done && i < this.extraFields.size(); i++) {
            if (((ZipExtraField) this.extraFields.elementAt(i)).getHeaderId().equals(type)) {
                this.extraFields.setElementAt(ze, i);
                done = true;
            }
        }
        if (!done) {
            this.extraFields.addElement(ze);
        }
        setExtra();
    }

    public void removeExtraField(ZipShort type) {
        boolean done = false;
        for (int i = 0; !done && i < this.extraFields.size(); i++) {
            if (((ZipExtraField) this.extraFields.elementAt(i)).getHeaderId().equals(type)) {
                this.extraFields.removeElementAt(i);
                done = true;
            }
        }
        if (!done) {
            throw new NoSuchElementException();
        }
        setExtra();
    }

    @Override // java.util.zip.ZipEntry
    public void setExtra(byte[] extra) throws RuntimeException {
        try {
            setExtraFields(ExtraFieldUtils.parse(extra));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    protected void setExtra() {
        super.setExtra(ExtraFieldUtils.mergeLocalFileDataData(getExtraFields()));
    }

    public byte[] getLocalFileDataExtra() {
        byte[] extra = getExtra();
        return extra != null ? extra : new byte[0];
    }

    public byte[] getCentralDirectoryExtra() {
        return ExtraFieldUtils.mergeCentralDirectoryData(getExtraFields());
    }

    public void setComprSize(long size) {
        if (haveSetCompressedSize()) {
            performSetCompressedSize(this, size);
        } else {
            this.compressedSize = new Long(size);
        }
    }

    @Override // java.util.zip.ZipEntry
    public long getCompressedSize() {
        Long l = this.compressedSize;
        if (l != null) {
            return l.longValue();
        }
        return super.getCompressedSize();
    }

    @Override // java.util.zip.ZipEntry
    public String getName() {
        String str = this.name;
        return str == null ? super.getName() : str;
    }

    @Override // java.util.zip.ZipEntry
    public boolean isDirectory() {
        return getName().endsWith("/");
    }

    protected void setName(String name) {
        this.name = name;
    }

    private static boolean haveSetCompressedSize() {
        checkSCS();
        return setCompressedSizeMethod != null;
    }

    private static void performSetCompressedSize(ZipEntry ze, long size) {
        Long[] s = {new Long(size)};
        try {
            setCompressedSizeMethod.invoke(ze, s);
        } catch (InvocationTargetException ite) {
            Throwable nested = ite.getTargetException();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Exception setting the compressed size of ");
            stringBuffer.append(ze);
            stringBuffer.append(": ");
            stringBuffer.append(nested.getMessage());
            throw new RuntimeException(stringBuffer.toString());
        } catch (Throwable other) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Exception setting the compressed size of ");
            stringBuffer2.append(ze);
            stringBuffer2.append(": ");
            stringBuffer2.append(other.getMessage());
            throw new RuntimeException(stringBuffer2.toString());
        }
    }

    private static void checkSCS() {
        Class clsClass$;
        if (!triedToGetMethod) {
            synchronized (lockReflection) {
                triedToGetMethod = true;
                try {
                    if (class$java$util$zip$ZipEntry == null) {
                        clsClass$ = class$("java.util.zip.ZipEntry");
                        class$java$util$zip$ZipEntry = clsClass$;
                    } else {
                        clsClass$ = class$java$util$zip$ZipEntry;
                    }
                    setCompressedSizeMethod = clsClass$.getMethod("setCompressedSize", Long.TYPE);
                } catch (NoSuchMethodException e) {
                }
            }
        }
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }
}
