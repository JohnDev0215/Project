package org.apache.commons.compress.archivers.zip;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.ZipException;

/* JADX INFO: loaded from: classes4.dex */
public class ExtraFieldUtils {
    static final ZipExtraField[] EMPTY_ZIP_EXTRA_FIELD_ARRAY;
    private static final int WORD = 4;
    private static final Map<ZipShort, Class<?>> implementations = new ConcurrentHashMap();

    static {
        register(AsiExtraField.class);
        register(X5455_ExtendedTimestamp.class);
        register(X7875_NewUnix.class);
        register(JarMarker.class);
        register(UnicodePathExtraField.class);
        register(UnicodeCommentExtraField.class);
        register(Zip64ExtendedInformationExtraField.class);
        register(X000A_NTFS.class);
        register(X0014_X509Certificates.class);
        register(X0015_CertificateIdForFile.class);
        register(X0016_CertificateIdForCentralDirectory.class);
        register(X0017_StrongEncryptionHeader.class);
        register(X0019_EncryptionRecipientCertificateList.class);
        register(ResourceAlignmentExtraField.class);
        EMPTY_ZIP_EXTRA_FIELD_ARRAY = new ZipExtraField[0];
    }

    public static void register(Class<?> cls) {
        try {
            implementations.put(((ZipExtraField) cls.newInstance()).getHeaderId(), cls);
        } catch (ClassCastException unused) {
            throw new RuntimeException(cls + " doesn't implement ZipExtraField");
        } catch (IllegalAccessException unused2) {
            throw new RuntimeException(cls + "'s no-arg constructor is not public");
        } catch (InstantiationException unused3) {
            throw new RuntimeException(cls + " is not a concrete class");
        }
    }

    public static ZipExtraField createExtraField(ZipShort zipShort) throws IllegalAccessException, InstantiationException {
        ZipExtraField zipExtraFieldCreateExtraFieldNoDefault = createExtraFieldNoDefault(zipShort);
        if (zipExtraFieldCreateExtraFieldNoDefault != null) {
            return zipExtraFieldCreateExtraFieldNoDefault;
        }
        UnrecognizedExtraField unrecognizedExtraField = new UnrecognizedExtraField();
        unrecognizedExtraField.setHeaderId(zipShort);
        return unrecognizedExtraField;
    }

    public static ZipExtraField createExtraFieldNoDefault(ZipShort zipShort) throws IllegalAccessException, InstantiationException {
        Class<?> cls = implementations.get(zipShort);
        if (cls != null) {
            return (ZipExtraField) cls.newInstance();
        }
        return null;
    }

    public static ZipExtraField[] parse(byte[] bArr) throws ZipException {
        return parse(bArr, true, UnparseableExtraField.THROW);
    }

    public static ZipExtraField[] parse(byte[] bArr, boolean z) throws ZipException {
        return parse(bArr, z, UnparseableExtraField.THROW);
    }

    public static ZipExtraField[] parse(byte[] bArr, boolean z, final UnparseableExtraField unparseableExtraField) throws ZipException {
        return parse(bArr, z, new ExtraFieldParsingBehavior() { // from class: org.apache.commons.compress.archivers.zip.ExtraFieldUtils.1
            @Override // org.apache.commons.compress.archivers.zip.UnparseableExtraFieldBehavior
            public ZipExtraField onUnparseableExtraField(byte[] bArr2, int i, int i2, boolean z2, int i3) throws ZipException {
                return unparseableExtraField.onUnparseableExtraField(bArr2, i, i2, z2, i3);
            }

            @Override // org.apache.commons.compress.archivers.zip.ExtraFieldParsingBehavior
            public ZipExtraField createExtraField(ZipShort zipShort) throws IllegalAccessException, ZipException, InstantiationException {
                return ExtraFieldUtils.createExtraField(zipShort);
            }

            @Override // org.apache.commons.compress.archivers.zip.ExtraFieldParsingBehavior
            public ZipExtraField fill(ZipExtraField zipExtraField, byte[] bArr2, int i, int i2, boolean z2) throws ZipException {
                return ExtraFieldUtils.fillExtraField(zipExtraField, bArr2, i, i2, z2);
            }
        });
    }

    public static ZipExtraField[] parse(byte[] bArr, boolean z, ExtraFieldParsingBehavior extraFieldParsingBehavior) throws ZipException {
        ArrayList arrayList = new ArrayList();
        int length = bArr.length;
        int i = 0;
        while (true) {
            if (i > length - 4) {
                break;
            }
            ZipShort zipShort = new ZipShort(bArr, i);
            int value = new ZipShort(bArr, i + 2).getValue();
            int i2 = i + 4;
            if (i2 + value > length) {
                ZipExtraField zipExtraFieldOnUnparseableExtraField = extraFieldParsingBehavior.onUnparseableExtraField(bArr, i, length - i, z, value);
                if (zipExtraFieldOnUnparseableExtraField != null) {
                    arrayList.add(zipExtraFieldOnUnparseableExtraField);
                }
            } else {
                try {
                    arrayList.add(Objects.requireNonNull(extraFieldParsingBehavior.fill((ZipExtraField) Objects.requireNonNull(extraFieldParsingBehavior.createExtraField(zipShort), "createExtraField must not return null"), bArr, i2, value, z), "fill must not return null"));
                    i += value + 4;
                } catch (IllegalAccessException | InstantiationException e) {
                    throw ((ZipException) new ZipException(e.getMessage()).initCause(e));
                }
            }
        }
        return (ZipExtraField[]) arrayList.toArray(EMPTY_ZIP_EXTRA_FIELD_ARRAY);
    }

    public static byte[] mergeLocalFileDataData(ZipExtraField[] zipExtraFieldArr) {
        byte[] localFileDataData;
        int length = zipExtraFieldArr.length;
        boolean z = length > 0 && (zipExtraFieldArr[length + (-1)] instanceof UnparseableExtraFieldData);
        int i = z ? length - 1 : length;
        int value = i * 4;
        for (ZipExtraField zipExtraField : zipExtraFieldArr) {
            value += zipExtraField.getLocalFileDataLength().getValue();
        }
        byte[] bArr = new byte[value];
        int length2 = 0;
        for (int i2 = 0; i2 < i; i2++) {
            System.arraycopy(zipExtraFieldArr[i2].getHeaderId().getBytes(), 0, bArr, length2, 2);
            System.arraycopy(zipExtraFieldArr[i2].getLocalFileDataLength().getBytes(), 0, bArr, length2 + 2, 2);
            length2 += 4;
            byte[] localFileDataData2 = zipExtraFieldArr[i2].getLocalFileDataData();
            if (localFileDataData2 != null) {
                System.arraycopy(localFileDataData2, 0, bArr, length2, localFileDataData2.length);
                length2 += localFileDataData2.length;
            }
        }
        if (z && (localFileDataData = zipExtraFieldArr[length - 1].getLocalFileDataData()) != null) {
            System.arraycopy(localFileDataData, 0, bArr, length2, localFileDataData.length);
        }
        return bArr;
    }

    public static byte[] mergeCentralDirectoryData(ZipExtraField[] zipExtraFieldArr) {
        byte[] centralDirectoryData;
        int length = zipExtraFieldArr.length;
        boolean z = length > 0 && (zipExtraFieldArr[length + (-1)] instanceof UnparseableExtraFieldData);
        int i = z ? length - 1 : length;
        int value = i * 4;
        for (ZipExtraField zipExtraField : zipExtraFieldArr) {
            value += zipExtraField.getCentralDirectoryLength().getValue();
        }
        byte[] bArr = new byte[value];
        int length2 = 0;
        for (int i2 = 0; i2 < i; i2++) {
            System.arraycopy(zipExtraFieldArr[i2].getHeaderId().getBytes(), 0, bArr, length2, 2);
            System.arraycopy(zipExtraFieldArr[i2].getCentralDirectoryLength().getBytes(), 0, bArr, length2 + 2, 2);
            length2 += 4;
            byte[] centralDirectoryData2 = zipExtraFieldArr[i2].getCentralDirectoryData();
            if (centralDirectoryData2 != null) {
                System.arraycopy(centralDirectoryData2, 0, bArr, length2, centralDirectoryData2.length);
                length2 += centralDirectoryData2.length;
            }
        }
        if (z && (centralDirectoryData = zipExtraFieldArr[length - 1].getCentralDirectoryData()) != null) {
            System.arraycopy(centralDirectoryData, 0, bArr, length2, centralDirectoryData.length);
        }
        return bArr;
    }

    public static ZipExtraField fillExtraField(ZipExtraField zipExtraField, byte[] bArr, int i, int i2, boolean z) throws ZipException {
        try {
            if (z) {
                zipExtraField.parseFromLocalFileData(bArr, i, i2);
            } else {
                zipExtraField.parseFromCentralDirectoryData(bArr, i, i2);
            }
            return zipExtraField;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw ((ZipException) new ZipException("Failed to parse corrupt ZIP extra field of type " + Integer.toHexString(zipExtraField.getHeaderId().getValue())).initCause(e));
        }
    }

    public static final class UnparseableExtraField implements UnparseableExtraFieldBehavior {
        public static final int READ_KEY = 2;
        public static final int SKIP_KEY = 1;
        public static final int THROW_KEY = 0;
        private final int key;
        public static final UnparseableExtraField THROW = new UnparseableExtraField(0);
        public static final UnparseableExtraField SKIP = new UnparseableExtraField(1);
        public static final UnparseableExtraField READ = new UnparseableExtraField(2);

        private UnparseableExtraField(int i) {
            this.key = i;
        }

        public int getKey() {
            return this.key;
        }

        @Override // org.apache.commons.compress.archivers.zip.UnparseableExtraFieldBehavior
        public ZipExtraField onUnparseableExtraField(byte[] bArr, int i, int i2, boolean z, int i3) throws ZipException {
            int i4 = this.key;
            if (i4 == 0) {
                throw new ZipException("Bad extra field starting at " + i + ".  Block length of " + i3 + " bytes exceeds remaining data of " + (i2 - 4) + " bytes.");
            }
            if (i4 == 1) {
                return null;
            }
            if (i4 == 2) {
                UnparseableExtraFieldData unparseableExtraFieldData = new UnparseableExtraFieldData();
                if (z) {
                    unparseableExtraFieldData.parseFromLocalFileData(bArr, i, i2);
                } else {
                    unparseableExtraFieldData.parseFromCentralDirectoryData(bArr, i, i2);
                }
                return unparseableExtraFieldData;
            }
            throw new ZipException("Unknown UnparseableExtraField key: " + this.key);
        }
    }
}
