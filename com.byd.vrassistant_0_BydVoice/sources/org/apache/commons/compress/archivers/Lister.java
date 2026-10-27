package org.apache.commons.compress.archivers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.Enumeration;
import java.util.Iterator;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarFile;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;

/* JADX INFO: loaded from: classes4.dex */
public final class Lister {
    private static final ArchiveStreamFactory FACTORY = ArchiveStreamFactory.DEFAULT;

    public static void main(String[] strArr) throws Exception {
        if (strArr.length == 0) {
            usage();
            return;
        }
        System.out.println("Analysing " + strArr[0]);
        File file = new File(strArr[0]);
        if (!file.isFile()) {
            System.err.println(file + " doesn't exist or is a directory");
        }
        String strDetectFormat = strArr.length > 1 ? strArr[1] : detectFormat(file);
        if (ArchiveStreamFactory.SEVEN_Z.equalsIgnoreCase(strDetectFormat)) {
            list7z(file);
            return;
        }
        if ("zipfile".equals(strDetectFormat)) {
            listZipUsingZipFile(file);
        } else if ("tarfile".equals(strDetectFormat)) {
            listZipUsingTarFile(file);
        } else {
            listStream(file, strArr);
        }
    }

    private static void listStream(File file, String[] strArr) throws ArchiveException, IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(Files.newInputStream(file.toPath(), new OpenOption[0]));
        try {
            ArchiveInputStream archiveInputStreamCreateArchiveInputStream = createArchiveInputStream(strArr, bufferedInputStream);
            try {
                System.out.println("Created " + archiveInputStreamCreateArchiveInputStream.toString());
                while (true) {
                    ArchiveEntry nextEntry = archiveInputStreamCreateArchiveInputStream.getNextEntry();
                    if (nextEntry == null) {
                        break;
                    } else {
                        System.out.println(nextEntry.getName());
                    }
                }
                if (archiveInputStreamCreateArchiveInputStream != null) {
                    archiveInputStreamCreateArchiveInputStream.close();
                }
                bufferedInputStream.close();
            } finally {
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    bufferedInputStream.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    private static ArchiveInputStream createArchiveInputStream(String[] strArr, InputStream inputStream) throws ArchiveException {
        if (strArr.length > 1) {
            return FACTORY.createArchiveInputStream(strArr[1], inputStream);
        }
        return FACTORY.createArchiveInputStream(inputStream);
    }

    private static String detectFormat(File file) throws ArchiveException, IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(Files.newInputStream(file.toPath(), new OpenOption[0]));
        try {
            String strDetect = ArchiveStreamFactory.detect(bufferedInputStream);
            bufferedInputStream.close();
            return strDetect;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    bufferedInputStream.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    private static void list7z(File file) throws ArchiveException, IOException {
        SevenZFile sevenZFile = new SevenZFile(file);
        try {
            System.out.println("Created " + sevenZFile.toString());
            while (true) {
                SevenZArchiveEntry nextEntry = sevenZFile.getNextEntry();
                if (nextEntry != null) {
                    System.out.println(nextEntry.getName() == null ? sevenZFile.getDefaultName() + " (entry name was null)" : nextEntry.getName());
                } else {
                    sevenZFile.close();
                    return;
                }
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    sevenZFile.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    private static void listZipUsingZipFile(File file) throws ArchiveException, IOException {
        ZipFile zipFile = new ZipFile(file);
        try {
            System.out.println("Created " + zipFile.toString());
            Enumeration<ZipArchiveEntry> entries = zipFile.getEntries();
            while (entries.hasMoreElements()) {
                System.out.println(entries.nextElement().getName());
            }
            zipFile.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    zipFile.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    private static void listZipUsingTarFile(File file) throws ArchiveException, IOException {
        TarFile tarFile = new TarFile(file);
        try {
            System.out.println("Created " + tarFile.toString());
            Iterator<TarArchiveEntry> it = tarFile.getEntries().iterator();
            while (it.hasNext()) {
                System.out.println(it.next().getName());
            }
            tarFile.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    tarFile.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    private static void usage() {
        System.out.println("Parameters: archive-name [archive-type]\n");
        System.out.println("the magic archive-type 'zipfile' prefers ZipFile over ZipArchiveInputStream");
        System.out.println("the magic archive-type 'tarfile' prefers TarFile over TarArchiveInputStream");
    }
}
