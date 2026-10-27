package com.google.common.io;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.graph.SuccessorsFunction;
import com.google.common.graph.Traverser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
public final class Files {
    private static final SuccessorsFunction<File> FILE_TREE = new SuccessorsFunction<File>() { // from class: com.google.common.io.Files.2
        @Override // com.google.common.graph.SuccessorsFunction
        public Iterable<File> successors(File file) {
            File[] fileArrListFiles;
            if (file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
                return Collections.unmodifiableList(Arrays.asList(fileArrListFiles));
            }
            return ImmutableList.of();
        }
    };

    private Files() {
    }

    public static ByteSource asByteSource(File file) {
        return new FileByteSource(file, null);
    }

    private static final class FileByteSource extends ByteSource {
        private final File file;

        /* synthetic */ FileByteSource(File file, AnonymousClass1 anonymousClass1) {
            this(file);
        }

        private FileByteSource(File file) {
            this.file = (File) Preconditions.checkNotNull(file);
        }

        @Override // com.google.common.io.ByteSource
        public FileInputStream openStream() throws IOException {
            return new FileInputStream(this.file);
        }

        @Override // com.google.common.io.ByteSource
        public Optional<Long> sizeIfKnown() {
            if (this.file.isFile()) {
                return Optional.of(Long.valueOf(this.file.length()));
            }
            return Optional.absent();
        }

        @Override // com.google.common.io.ByteSource
        public byte[] read() throws Throwable {
            try {
                FileInputStream fileInputStream = (FileInputStream) Closer.create().register(openStream());
                return ByteStreams.toByteArray(fileInputStream, fileInputStream.getChannel().size());
            } finally {
            }
        }

        public String toString() {
            String strValueOf = String.valueOf(this.file);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 20);
            sb.append("Files.asByteSource(");
            sb.append(strValueOf);
            sb.append(")");
            return sb.toString();
        }
    }

    public static ByteSink asByteSink(File file, FileWriteMode... fileWriteModeArr) {
        return new FileByteSink(file, fileWriteModeArr, null);
    }

    private static final class FileByteSink extends ByteSink {
        private final File file;
        private final ImmutableSet<FileWriteMode> modes;

        /* synthetic */ FileByteSink(File file, FileWriteMode[] fileWriteModeArr, AnonymousClass1 anonymousClass1) {
            this(file, fileWriteModeArr);
        }

        private FileByteSink(File file, FileWriteMode... fileWriteModeArr) {
            this.file = (File) Preconditions.checkNotNull(file);
            this.modes = ImmutableSet.copyOf(fileWriteModeArr);
        }

        @Override // com.google.common.io.ByteSink
        public FileOutputStream openStream() throws IOException {
            return new FileOutputStream(this.file, this.modes.contains(FileWriteMode.APPEND));
        }

        public String toString() {
            String strValueOf = String.valueOf(this.file);
            String strValueOf2 = String.valueOf(this.modes);
            StringBuilder sb = new StringBuilder(strValueOf.length() + 20 + strValueOf2.length());
            sb.append("Files.asByteSink(");
            sb.append(strValueOf);
            sb.append(", ");
            sb.append(strValueOf2);
            sb.append(")");
            return sb.toString();
        }
    }

    public static CharSource asCharSource(File file, Charset charset) {
        return asByteSource(file).asCharSource(charset);
    }

    @Deprecated
    public static String toString(File file, Charset charset) throws IOException {
        return asCharSource(file, charset).read();
    }

    public static void write(byte[] bArr, File file) throws Throwable {
        asByteSink(file, new FileWriteMode[0]).write(bArr);
    }

    public static void copy(File file, File file2) throws IOException {
        Preconditions.checkArgument(!file.equals(file2), "Source %s and destination %s must be different", file, file2);
        asByteSource(file).copyTo(asByteSink(file2, new FileWriteMode[0]));
    }

    public static void createParentDirs(File file) throws IOException {
        Preconditions.checkNotNull(file);
        File parentFile = file.getCanonicalFile().getParentFile();
        if (parentFile == null) {
            return;
        }
        parentFile.mkdirs();
        if (parentFile.isDirectory()) {
            return;
        }
        String strValueOf = String.valueOf(file);
        StringBuilder sb = new StringBuilder(strValueOf.length() + 39);
        sb.append("Unable to create parent directories of ");
        sb.append(strValueOf);
        throw new IOException(sb.toString());
    }

    /* JADX INFO: renamed from: com.google.common.io.Files$1, reason: invalid class name */
    class AnonymousClass1 {
        final List<String> result = Lists.newArrayList();

        AnonymousClass1() {
        }
    }

    public static Traverser<File> fileTraverser() {
        return Traverser.forTree(FILE_TREE);
    }
}
