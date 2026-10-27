package org.apache.commons.compress.archivers.sevenz;

import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
public class CLI {

    private enum Mode {
        LIST("Analysing") { // from class: org.apache.commons.compress.archivers.sevenz.CLI.Mode.1
            @Override // org.apache.commons.compress.archivers.sevenz.CLI.Mode
            public void takeAction(SevenZFile sevenZFile, SevenZArchiveEntry sevenZArchiveEntry) {
                System.out.print(sevenZArchiveEntry.getName());
                if (sevenZArchiveEntry.isDirectory()) {
                    System.out.print(" dir");
                } else {
                    System.out.print(" " + sevenZArchiveEntry.getCompressedSize() + "/" + sevenZArchiveEntry.getSize());
                }
                if (sevenZArchiveEntry.getHasLastModifiedDate()) {
                    System.out.print(" " + sevenZArchiveEntry.getLastModifiedDate());
                } else {
                    System.out.print(" no last modified date");
                }
                if (!sevenZArchiveEntry.isDirectory()) {
                    System.out.println(" " + getContentMethods(sevenZArchiveEntry));
                } else {
                    System.out.println();
                }
            }

            private String getContentMethods(SevenZArchiveEntry sevenZArchiveEntry) {
                StringBuilder sb = new StringBuilder();
                boolean z = true;
                for (SevenZMethodConfiguration sevenZMethodConfiguration : sevenZArchiveEntry.getContentMethods()) {
                    if (!z) {
                        sb.append(", ");
                    }
                    sb.append(sevenZMethodConfiguration.getMethod());
                    if (sevenZMethodConfiguration.getOptions() != null) {
                        sb.append("(").append(sevenZMethodConfiguration.getOptions()).append(")");
                    }
                    z = false;
                }
                return sb.toString();
            }
        };

        private final String message;

        public abstract void takeAction(SevenZFile sevenZFile, SevenZArchiveEntry sevenZArchiveEntry) throws IOException;

        Mode(String str) {
            this.message = str;
        }

        public String getMessage() {
            return this.message;
        }
    }

    public static void main(String[] strArr) throws Exception {
        if (strArr.length == 0) {
            usage();
            return;
        }
        Mode modeGrabMode = grabMode(strArr);
        System.out.println(modeGrabMode.getMessage() + " " + strArr[0]);
        File file = new File(strArr[0]);
        if (!file.isFile()) {
            System.err.println(file + " doesn't exist or is a directory");
        }
        SevenZFile sevenZFile = new SevenZFile(file);
        while (true) {
            try {
                SevenZArchiveEntry nextEntry = sevenZFile.getNextEntry();
                if (nextEntry != null) {
                    modeGrabMode.takeAction(sevenZFile, nextEntry);
                } else {
                    sevenZFile.close();
                    return;
                }
            } finally {
            }
        }
    }

    private static void usage() {
        System.out.println("Parameters: archive-name [list]");
    }

    private static Mode grabMode(String[] strArr) {
        if (strArr.length < 2) {
            return Mode.LIST;
        }
        return (Mode) Enum.valueOf(Mode.class, strArr[1].toUpperCase());
    }
}
