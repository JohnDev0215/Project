package com.android.providers.media.playlist;

import android.util.Log;
import com.android.providers.media.util.Logging;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class Playlist {
    private final ArrayList<Path> mItems = new ArrayList<>();

    private static int constrain(int i, int i2, int i3) {
        return i < i2 ? i2 : i > i3 ? i3 : i;
    }

    public List<Path> asList() {
        return Collections.unmodifiableList(this.mItems);
    }

    public void clear() {
        this.mItems.clear();
    }

    public void read(File file) throws IOException {
        clear();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                PlaylistPersister.resolvePersister(file).read(fileInputStream, this.mItems);
                fileInputStream.close();
            } finally {
            }
        } catch (FileNotFoundException unused) {
            Log.w(Logging.TAG, "Treating missing file as empty playlist");
        }
    }

    public void write(File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            PlaylistPersister.resolvePersister(file).write(fileOutputStream, this.mItems);
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public int add(int i, Path path) {
        int iConstrain = constrain(i, 0, this.mItems.size());
        this.mItems.add(iConstrain, path);
        return iConstrain;
    }

    public int move(int i, int i2) {
        int size = this.mItems.size() - 1;
        int iConstrain = constrain(i, 0, size);
        int iConstrain2 = constrain(i2, 0, size);
        this.mItems.add(iConstrain2, this.mItems.remove(iConstrain));
        return iConstrain2;
    }

    public int remove(int i) {
        int iConstrain = constrain(i, 0, this.mItems.size() - 1);
        this.mItems.remove(iConstrain);
        return iConstrain;
    }
}
