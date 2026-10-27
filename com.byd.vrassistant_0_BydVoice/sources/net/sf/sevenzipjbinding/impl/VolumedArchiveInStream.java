package net.sf.sevenzipjbinding.impl;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import net.sf.sevenzipjbinding.IArchiveOpenVolumeCallback;
import net.sf.sevenzipjbinding.IInStream;
import net.sf.sevenzipjbinding.PropID;
import net.sf.sevenzipjbinding.SevenZipException;

/* JADX INFO: loaded from: classes3.dex */
public class VolumedArchiveInStream implements IInStream {
    private static final String SEVEN_ZIP_FIRST_VOLUME_POSTFIX = ".7z.001";
    private long absoluteLength;
    private long absoluteOffset;
    private final IArchiveOpenVolumeCallback archiveOpenVolumeCallback;
    private IInStream currentInStream;
    private int currentIndex;
    private long currentVolumeLength;
    private long currentVolumeOffset;
    private String cuttedVolumeFilename;
    private List<Long> volumePositions;

    public VolumedArchiveInStream(IArchiveOpenVolumeCallback iArchiveOpenVolumeCallback) throws SevenZipException {
        this((String) iArchiveOpenVolumeCallback.getProperty(PropID.NAME), iArchiveOpenVolumeCallback);
    }

    public VolumedArchiveInStream(String str, IArchiveOpenVolumeCallback iArchiveOpenVolumeCallback) throws SevenZipException {
        this.absoluteLength = -1L;
        this.currentIndex = -1;
        ArrayList arrayList = new ArrayList();
        this.volumePositions = arrayList;
        this.archiveOpenVolumeCallback = iArchiveOpenVolumeCallback;
        arrayList.add(0L);
        if (!str.endsWith(SEVEN_ZIP_FIRST_VOLUME_POSTFIX)) {
            throw new SevenZipException("The first 7z volume filename '" + str + "' don't ends with the postfix: '.7z.001'. Can't proceed");
        }
        this.cuttedVolumeFilename = str.substring(0, str.length() - 3);
        openVolume(1, true);
    }

    private void openVolume(int i, boolean z) throws SevenZipException {
        if (this.currentIndex == i) {
            return;
        }
        for (int size = this.volumePositions.size(); size < i && this.absoluteLength == -1; size++) {
            openVolume(size, false);
        }
        if (this.absoluteLength == -1 || this.volumePositions.size() > i) {
            IInStream stream = this.archiveOpenVolumeCallback.getStream(this.cuttedVolumeFilename + MessageFormat.format("{0,number,000}", Integer.valueOf(i)));
            if (stream == null) {
                List<Long> list = this.volumePositions;
                this.absoluteLength = list.get(list.size() - 1).longValue();
                return;
            }
            this.currentInStream = stream;
            if (this.volumePositions.size() == i) {
                long jSeek = this.currentInStream.seek(0L, 2);
                this.currentVolumeLength = jSeek;
                if (jSeek == 0) {
                    throw new RuntimeException("Volume " + i + " is empty");
                }
                List<Long> list2 = this.volumePositions;
                list2.add(Long.valueOf(list2.get(i - 1).longValue() + this.currentVolumeLength));
                if (z) {
                    this.currentInStream.seek(0L, 0);
                }
            } else {
                this.currentVolumeLength = this.volumePositions.get(i).longValue() - this.volumePositions.get(i - 1).longValue();
            }
            if (z) {
                this.currentVolumeOffset = 0L;
                this.absoluteOffset = this.volumePositions.get(i - 1).longValue();
            }
            this.currentIndex = i;
        }
    }

    private void openVolumeToAbsoluteOffset() throws SevenZipException {
        int size = this.volumePositions.size() - 1;
        long j = this.absoluteLength;
        if (j == -1 || this.absoluteOffset < j) {
            while (this.volumePositions.get(size).longValue() > this.absoluteOffset) {
                size--;
            }
            if (size < this.volumePositions.size() - 1) {
                openVolume(size + 1, false);
                return;
            }
            do {
                size++;
                openVolume(size, false);
                long j2 = this.absoluteLength;
                if (j2 != -1 && this.absoluteOffset >= j2) {
                    return;
                }
            } while (this.volumePositions.get(size).longValue() <= this.absoluteOffset);
        }
    }

    @Override // net.sf.sevenzipjbinding.ISeekableStream
    public synchronized long seek(long j, int i) throws SevenZipException {
        boolean z;
        try {
            if (i == 0) {
                z = false;
            } else if (i == 1) {
                j += this.absoluteOffset;
                z = false;
            } else if (i == 2) {
                if (this.absoluteLength == -1) {
                    openVolume(Integer.MAX_VALUE, false);
                    z = true;
                } else {
                    z = false;
                }
                j += this.absoluteLength;
            } else {
                throw new RuntimeException("Seek: unknown origin: " + i);
            }
            if (j == this.absoluteOffset && !z) {
                return j;
            }
            this.absoluteOffset = j;
            openVolumeToAbsoluteOffset();
            long j2 = this.absoluteLength;
            if (j2 != -1 && j2 <= this.absoluteOffset) {
                this.absoluteOffset = j2;
                return j2;
            }
            long jLongValue = this.absoluteOffset - this.volumePositions.get(this.currentIndex - 1).longValue();
            this.currentVolumeOffset = jLongValue;
            this.currentInStream.seek(jLongValue, 0);
            return j;
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // net.sf.sevenzipjbinding.ISequentialInStream
    public synchronized int read(byte[] bArr) throws SevenZipException {
        long j = this.absoluteLength;
        if (j != -1 && this.absoluteOffset >= j) {
            return 0;
        }
        int i = this.currentInStream.read(bArr);
        long j2 = i;
        this.absoluteOffset += j2;
        long j3 = this.currentVolumeOffset + j2;
        this.currentVolumeOffset = j3;
        if (j3 >= this.currentVolumeLength) {
            openVolume(this.currentIndex + 1, true);
        }
        return i;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        throw new RuntimeException("close() method not supported. The user should implement its own caching and closing stratagies within the IArchiveOpenVolumeCallback.getStream() implementation.");
    }
}
