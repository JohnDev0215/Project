package org.apache.commons.io.file;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;
import org.apache.commons.io.file.Counters;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

/* JADX INFO: loaded from: classes7.dex */
public class CountingPathVisitor extends SimplePathVisitor {
    static final String[] EMPTY_STRING_ARRAY = new String[0];
    private final PathFilter dirFilter;
    private final PathFilter fileFilter;
    private final Counters.PathCounters pathCounters;

    /* JADX WARN: Illegal instructions before constructor call */
    public CountingPathVisitor(Counters.PathCounters pathCounters) {
        IOFileFilter iOFileFilter = TrueFileFilter.INSTANCE;
        this(pathCounters, iOFileFilter, iOFileFilter);
    }

    public CountingPathVisitor(Counters.PathCounters pathCounters, PathFilter pathFilter, PathFilter pathFilter2) {
        Objects.requireNonNull(pathCounters, "pathCounter");
        this.pathCounters = pathCounters;
        Objects.requireNonNull(pathFilter, "fileFilter");
        this.fileFilter = pathFilter;
        Objects.requireNonNull(pathFilter2, "dirFilter");
        this.dirFilter = pathFilter2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CountingPathVisitor) {
            return Objects.equals(this.pathCounters, ((CountingPathVisitor) obj).pathCounters);
        }
        return false;
    }

    public Counters.PathCounters getPathCounters() {
        return this.pathCounters;
    }

    public int hashCode() {
        return Objects.hash(this.pathCounters);
    }

    @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
    public FileVisitResult postVisitDirectory(Path path, IOException iOException) throws IOException {
        updateDirCounter(path, iOException);
        return FileVisitResult.CONTINUE;
    }

    @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
    public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
        FileVisitResult fileVisitResultAccept = this.dirFilter.accept(path, basicFileAttributes);
        FileVisitResult fileVisitResult = FileVisitResult.CONTINUE;
        return fileVisitResultAccept != fileVisitResult ? FileVisitResult.SKIP_SUBTREE : fileVisitResult;
    }

    public String toString() {
        return this.pathCounters.toString();
    }

    protected void updateDirCounter(Path path, IOException iOException) {
        this.pathCounters.getDirectoryCounter().increment();
    }

    protected void updateFileCounters(Path path, BasicFileAttributes basicFileAttributes) {
        this.pathCounters.getFileCounter().increment();
        this.pathCounters.getByteCounter().add(basicFileAttributes.size());
    }

    @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
    public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
        if (Files.exists(path, new LinkOption[0]) && this.fileFilter.accept(path, basicFileAttributes) == FileVisitResult.CONTINUE) {
            updateFileCounters(path, basicFileAttributes);
        }
        return FileVisitResult.CONTINUE;
    }
}
