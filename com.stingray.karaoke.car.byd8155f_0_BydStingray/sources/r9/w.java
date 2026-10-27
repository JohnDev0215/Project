package r9;

import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import n0.C1886o;
import p1.C2078f;

/* JADX INFO: loaded from: classes.dex */
public final class w extends v {
    public static Long m(FileTime fileTime) {
        long millis = fileTime.toMillis();
        Long lValueOf = Long.valueOf(millis);
        if (millis != 0) {
            return lValueOf;
        }
        return null;
    }

    @Override // r9.v, r9.o
    public final C2078f h(z zVar) {
        z zVarI;
        A7.m.f(zVar, "path");
        Path pathG = zVar.g();
        try {
            BasicFileAttributes attributes = Files.readAttributes(pathG, (Class<BasicFileAttributes>) BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
            Path symbolicLink = attributes.isSymbolicLink() ? Files.readSymbolicLink(pathG) : null;
            boolean zIsRegularFile = attributes.isRegularFile();
            boolean zIsDirectory = attributes.isDirectory();
            if (symbolicLink != null) {
                String str = z.f21951m;
                zVarI = C1886o.i(symbolicLink.toString(), false);
            } else {
                zVarI = null;
            }
            Long lValueOf = Long.valueOf(attributes.size());
            FileTime fileTimeCreationTime = attributes.creationTime();
            Long lM = fileTimeCreationTime != null ? m(fileTimeCreationTime) : null;
            FileTime fileTimeLastModifiedTime = attributes.lastModifiedTime();
            Long lM2 = fileTimeLastModifiedTime != null ? m(fileTimeLastModifiedTime) : null;
            FileTime fileTimeLastAccessTime = attributes.lastAccessTime();
            return new C2078f(zIsRegularFile, zIsDirectory, zVarI, lValueOf, lM, lM2, fileTimeLastAccessTime != null ? m(fileTimeLastAccessTime) : null);
        } catch (NoSuchFileException | FileSystemException unused) {
            return null;
        }
    }

    @Override // r9.v
    public final void l(z zVar, z zVar2) throws IOException {
        A7.m.f(zVar, BYDAutoMultimediaDevice.PARAM_SOURCE);
        A7.m.f(zVar2, "target");
        try {
            Files.move(zVar.g(), zVar2.g(), StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
        } catch (UnsupportedOperationException unused) {
            throw new IOException("atomic move not supported");
        } catch (NoSuchFileException e10) {
            throw new FileNotFoundException(e10.getMessage());
        }
    }

    @Override // r9.v
    public final String toString() {
        return "NioSystemFileSystem";
    }
}
