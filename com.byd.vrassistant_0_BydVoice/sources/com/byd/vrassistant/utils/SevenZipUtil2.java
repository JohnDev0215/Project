package com.byd.vrassistant.utils;

import com.byd.vrassistant.utils.DataUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.util.HashMap;
import net.sf.sevenzipjbinding.ArchiveFormat;
import net.sf.sevenzipjbinding.ExtractAskMode;
import net.sf.sevenzipjbinding.ExtractOperationResult;
import net.sf.sevenzipjbinding.IArchiveExtractCallback;
import net.sf.sevenzipjbinding.IInArchive;
import net.sf.sevenzipjbinding.ISequentialOutStream;
import net.sf.sevenzipjbinding.PropID;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.SevenZipException;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;

/* JADX INFO: loaded from: classes2.dex */
public class SevenZipUtil2 {
    private final String TAG = "NEU_SevenZipUtil";

    public static synchronized void unCompress(File file, File file2) throws IOException {
        if (file != null) {
            if (file.exists()) {
                if (file2 == null || !file2.exists() || !file2.isDirectory()) {
                    file2.mkdirs();
                }
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
                IInArchive iInArchiveOpenInArchive = SevenZip.openInArchive(ArchiveFormat.SEVEN_ZIP, new RandomAccessFileInStream(randomAccessFile));
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                int numberOfItems = iInArchiveOpenInArchive.getNumberOfItems();
                int[] iArr = new int[numberOfItems];
                for (int i = 0; i < numberOfItems; i++) {
                    iArr[i] = i;
                }
                iInArchiveOpenInArchive.extract(iArr, false, new ExtractCallback(iInArchiveOpenInArchive, file2.getAbsolutePath(), null));
                iInArchiveOpenInArchive.close();
                randomAccessFile.close();
            }
        }
        throw new RuntimeException("Invalid outputDir:" + file2);
    }

    public static synchronized void unCompressEx(File file, File file2, DataUtils.USBCopyDataProgress uSBCopyDataProgress) throws IOException {
        if (file != null) {
            if (file.exists()) {
                if (file2 == null || !file2.exists() || !file2.isDirectory()) {
                    file2.mkdirs();
                }
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
                IInArchive iInArchiveOpenInArchive = SevenZip.openInArchive(ArchiveFormat.SEVEN_ZIP, new RandomAccessFileInStream(randomAccessFile));
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                int numberOfItems = iInArchiveOpenInArchive.getNumberOfItems();
                int[] iArr = new int[numberOfItems];
                for (int i = 0; i < numberOfItems; i++) {
                    iArr[i] = i;
                }
                iInArchiveOpenInArchive.extract(iArr, false, new ExtractCallback(iInArchiveOpenInArchive, file2.getAbsolutePath(), uSBCopyDataProgress));
                iInArchiveOpenInArchive.close();
                randomAccessFile.close();
            }
        }
        throw new RuntimeException("Invalid outputDir:" + file2);
    }

    public static int getCompressFileCount(File file) throws IOException {
        if (file == null || !file.exists()) {
            throw new RuntimeException("Invalid inCompressFile:" + file.getName());
        }
        return SevenZip.openInArchive(ArchiveFormat.SEVEN_ZIP, new RandomAccessFileInStream(new RandomAccessFile(file, "r"))).getNumberOfItems();
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class ExtractCallback implements IArchiveExtractCallback {
        private final String extractPath;
        private final IInArchive inArchive;
        private HashMap<String, Integer> map;
        private DataUtils.USBCopyDataProgress progress;

        @Override // net.sf.sevenzipjbinding.IArchiveExtractCallback
        public void prepareOperation(ExtractAskMode extractAskMode) {
        }

        @Override // net.sf.sevenzipjbinding.IProgress
        public void setCompleted(long j) {
        }

        @Override // net.sf.sevenzipjbinding.IArchiveExtractCallback
        public void setOperationResult(ExtractOperationResult extractOperationResult) {
        }

        @Override // net.sf.sevenzipjbinding.IProgress
        public void setTotal(long j) {
        }

        public ExtractCallback(IInArchive iInArchive, String str, DataUtils.USBCopyDataProgress uSBCopyDataProgress) {
            this.inArchive = iInArchive;
            if (!str.endsWith("/") && !str.endsWith("\\")) {
                str = str + File.separator;
            }
            this.extractPath = str;
            this.progress = uSBCopyDataProgress;
            this.map = new HashMap<>();
        }

        @Override // net.sf.sevenzipjbinding.IArchiveExtractCallback
        public ISequentialOutStream getStream(final int i, ExtractAskMode extractAskMode) throws SevenZipException {
            return new ISequentialOutStream() { // from class: com.byd.vrassistant.utils.SevenZipUtil2$ExtractCallback$$ExternalSyntheticLambda0
                @Override // net.sf.sevenzipjbinding.ISequentialOutStream
                public final int write(byte[] bArr) {
                    return this.f$0.m187xa311a8c(i, bArr);
                }
            };
        }

        /* JADX INFO: renamed from: lambda$getStream$0$com-byd-vrassistant-utils-SevenZipUtil2$ExtractCallback, reason: not valid java name */
        /* synthetic */ int m187xa311a8c(int i, byte[] bArr) throws Throwable {
            PrintStream printStream;
            StringBuilder sb;
            String stringProperty = this.inArchive.getStringProperty(i, PropID.PATH);
            FileOutputStream fileOutputStream = null;
            try {
                try {
                    File file = new File(this.extractPath + stringProperty);
                    if (!file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    }
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    String strSubstring = i == -1 ? "" : file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(File.separator) + 1);
                    String str = i + "-" + strSubstring;
                    int iIntValue = this.map.get(str) == null ? 0 : this.map.get(str).intValue();
                    FileOutputStream fileOutputStream2 = new FileOutputStream(file, this.map.containsKey(str));
                    try {
                        fileOutputStream2.write(bArr, 0, bArr.length);
                        this.map.put(str, Integer.valueOf(iIntValue + bArr.length));
                        DataUtils.USBCopyDataProgress uSBCopyDataProgress = this.progress;
                        if (uSBCopyDataProgress != null) {
                            uSBCopyDataProgress.onFileCopyIncreased(strSubstring, "");
                        }
                        try {
                            fileOutputStream2.flush();
                            fileOutputStream2.close();
                        } catch (IOException e) {
                            e = e;
                            printStream = System.out;
                            sb = new StringBuilder("Could not close FileOutputStream");
                            printStream.println(sb.append(e).toString());
                        }
                    } catch (IOException unused) {
                        fileOutputStream = fileOutputStream2;
                        System.out.println("IOException while extracting " + stringProperty);
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.flush();
                                fileOutputStream.close();
                            } catch (IOException e2) {
                                e = e2;
                                printStream = System.out;
                                sb = new StringBuilder("Could not close FileOutputStream");
                                printStream.println(sb.append(e).toString());
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.flush();
                                fileOutputStream.close();
                            } catch (IOException e3) {
                                System.out.println("Could not close FileOutputStream" + e3);
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (IOException unused2) {
            }
            return bArr.length;
        }
    }
}
