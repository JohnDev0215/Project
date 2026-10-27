package com.android.providers.media.playlist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class PlsPlaylistPersister implements PlaylistPersister {
    private static final Pattern PATTERN_PLS = Pattern.compile("File(\\d+)=(.+)");

    @Override // com.android.providers.media.playlist.PlaylistPersister
    public void read(InputStream inputStream, List<Path> list) throws IOException {
        FileSystem fileSystem = FileSystems.getDefault();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            Path[] pathArr = new Path[0];
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                Matcher matcher = PATTERN_PLS.matcher(line);
                if (matcher.matches()) {
                    int i = Integer.parseInt(matcher.group(1));
                    Path path = fileSystem.getPath(matcher.group(2).replace('\\', '/'), new String[0]);
                    int i2 = i + 1;
                    if (i2 > pathArr.length) {
                        pathArr = (Path[]) Arrays.copyOf(pathArr, i2);
                    }
                    pathArr[i] = path;
                }
            }
            for (int i3 = 0; i3 < pathArr.length; i3++) {
                if (pathArr[i3] != null) {
                    list.add(pathArr[i3]);
                }
            }
            bufferedReader.close();
        } catch (Throwable th) {
            try {
                bufferedReader.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    @Override // com.android.providers.media.playlist.PlaylistPersister
    public void write(OutputStream outputStream, List<Path> list) throws IOException {
        PrintWriter printWriter = new PrintWriter(outputStream);
        try {
            printWriter.printf("[playlist]\n", new Object[0]);
            int i = 0;
            while (i < list.size()) {
                int i2 = i + 1;
                printWriter.printf("File%d=%s\n", Integer.valueOf(i2), list.get(i));
                i = i2;
            }
            printWriter.printf("NumberOfEntries=%d\n", Integer.valueOf(list.size()));
            printWriter.printf("Version=2\n", new Object[0]);
            printWriter.close();
        } catch (Throwable th) {
            try {
                printWriter.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }
}
