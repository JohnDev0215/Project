package com.android.providers.media.playlist;

import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class M3uPlaylistPersister implements PlaylistPersister {
    @Override // com.android.providers.media.playlist.PlaylistPersister
    public void read(InputStream inputStream, List<Path> list) throws IOException {
        FileSystem fileSystem = FileSystems.getDefault();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        while (true) {
            try {
                String line = bufferedReader.readLine();
                if (line != null) {
                    if (!TextUtils.isEmpty(line) && !line.startsWith("#")) {
                        list.add(fileSystem.getPath(line.replace('\\', '/'), new String[0]));
                    }
                } else {
                    bufferedReader.close();
                    return;
                }
            } catch (Throwable th) {
                try {
                    bufferedReader.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
    }

    @Override // com.android.providers.media.playlist.PlaylistPersister
    public void write(OutputStream outputStream, List<Path> list) throws IOException {
        PrintWriter printWriter = new PrintWriter(outputStream);
        try {
            printWriter.println("#EXTM3U");
            Iterator<Path> it = list.iterator();
            while (it.hasNext()) {
                printWriter.println(it.next());
            }
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
