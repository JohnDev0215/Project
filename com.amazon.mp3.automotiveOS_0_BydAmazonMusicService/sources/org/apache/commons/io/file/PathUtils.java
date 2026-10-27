package org.apache.commons.io.file;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.apache.commons.io.IOExceptionList;
import org.apache.commons.io.file.Counters;

/* JADX INFO: loaded from: classes7.dex */
public final class PathUtils {
    public static final CopyOption[] EMPTY_COPY_OPTIONS = new CopyOption[0];
    public static final DeleteOption[] EMPTY_DELETE_OPTION_ARRAY = new DeleteOption[0];
    public static final FileVisitOption[] EMPTY_FILE_VISIT_OPTION_ARRAY = new FileVisitOption[0];
    public static final LinkOption[] EMPTY_LINK_OPTION_ARRAY = new LinkOption[0];
    public static final LinkOption[] NOFOLLOW_LINK_OPTION_ARRAY = {LinkOption.NOFOLLOW_LINKS};
    public static final OpenOption[] EMPTY_OPEN_OPTION_ARRAY = new OpenOption[0];
    public static final Path[] EMPTY_PATH_ARRAY = new Path[0];

    public static Counters.PathCounters delete(Path path, LinkOption[] linkOptionArr, DeleteOption... deleteOptionArr) throws IOException {
        return Files.isDirectory(path, linkOptionArr) ? deleteDirectory(path, linkOptionArr, deleteOptionArr) : deleteFile(path, linkOptionArr, deleteOptionArr);
    }

    public static Counters.PathCounters deleteDirectory(Path path, LinkOption[] linkOptionArr, DeleteOption... deleteOptionArr) throws IOException {
        return ((DeletingPathVisitor) visitFileTree(new DeletingPathVisitor(Counters.longPathCounters(), linkOptionArr, deleteOptionArr, new String[0]), path)).getPathCounters();
    }

    public static Counters.PathCounters deleteFile(Path path, LinkOption[] linkOptionArr, DeleteOption... deleteOptionArr) throws IOException {
        if (Files.isDirectory(path, linkOptionArr)) {
            throw new NoSuchFileException(path.toString());
        }
        Counters.PathCounters pathCountersLongPathCounters = Counters.longPathCounters();
        boolean zExists = Files.exists(path, linkOptionArr);
        long size = (!zExists || Files.isSymbolicLink(path)) ? 0L : Files.size(path);
        if (overrideReadOnly(deleteOptionArr) && zExists) {
            setReadOnly(path, false, linkOptionArr);
        }
        if (Files.deleteIfExists(path)) {
            pathCountersLongPathCounters.getFileCounter().increment();
            pathCountersLongPathCounters.getByteCounter().add(size);
        }
        return pathCountersLongPathCounters;
    }

    public static boolean isEmptyDirectory(Path path) throws IOException {
        DirectoryStream<Path> directoryStreamNewDirectoryStream = Files.newDirectoryStream(path);
        try {
            boolean z = !directoryStreamNewDirectoryStream.iterator().hasNext();
            directoryStreamNewDirectoryStream.close();
            return z;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (directoryStreamNewDirectoryStream != null) {
                    try {
                        directoryStreamNewDirectoryStream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$overrideReadOnly$1(DeleteOption deleteOption) {
        return deleteOption == StandardDeleteOption.OVERRIDE_READ_ONLY;
    }

    private static boolean overrideReadOnly(DeleteOption... deleteOptionArr) {
        if (deleteOptionArr == null) {
            return false;
        }
        return Stream.of((Object[]) deleteOptionArr).anyMatch(new Predicate() { // from class: org.apache.commons.io.file.PathUtils$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return PathUtils.lambda$overrideReadOnly$1((DeleteOption) obj);
            }
        });
    }

    public static Path setReadOnly(Path path, boolean z, LinkOption... linkOptionArr) throws IOException {
        ArrayList arrayList = new ArrayList(2);
        DosFileAttributeView dosFileAttributeView = (DosFileAttributeView) Files.getFileAttributeView(path, DosFileAttributeView.class, linkOptionArr);
        if (dosFileAttributeView != null) {
            try {
                dosFileAttributeView.setReadOnly(z);
                return path;
            } catch (IOException e) {
                arrayList.add(e);
            }
        }
        PosixFileAttributeView posixFileAttributeView = (PosixFileAttributeView) Files.getFileAttributeView(path, PosixFileAttributeView.class, linkOptionArr);
        if (posixFileAttributeView != null) {
            Set<PosixFilePermission> setPermissions = posixFileAttributeView.readAttributes().permissions();
            setPermissions.remove(PosixFilePermission.OWNER_WRITE);
            setPermissions.remove(PosixFilePermission.GROUP_WRITE);
            setPermissions.remove(PosixFilePermission.OTHERS_WRITE);
            try {
                return Files.setPosixFilePermissions(path, setPermissions);
            } catch (IOException e2) {
                arrayList.add(e2);
            }
        }
        if (!arrayList.isEmpty()) {
            throw new IOExceptionList(path.toString(), arrayList);
        }
        throw new IOException(String.format("No DosFileAttributeView or PosixFileAttributeView for '%s' (linkOptions=%s)", path, Arrays.toString(linkOptionArr)));
    }

    public static <T extends FileVisitor<? super Path>> T visitFileTree(T t, Path path) throws IOException {
        Files.walkFileTree(path, t);
        return t;
    }

    private PathUtils() {
    }
}
