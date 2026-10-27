package com.amazon.mp3.lyrics;

import android.content.Context;
import com.amazon.mp3.lyrics.exception.LyricsParseExeption;
import com.amazon.mp3.lyrics.item.Lyrics;
import com.amazon.mp3.lyrics.item.LyricsTrackInfo;
import com.amazon.mp3.util.Log;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
class LyricsFile {
    private static final String TAG = "LyricsFile";
    private final Context mContext;
    private final LyricsParser mParser = new LyricsParser();

    public LyricsFile(Context context) {
        this.mContext = context;
    }

    public Lyrics load(LyricsTrackInfo info) throws LyricsParseExeption, FileNotFoundException {
        return this.mParser.parse(loadRawFile(info));
    }

    public boolean save(LyricsTrackInfo info, String rawLyrics) throws Throwable {
        Closeable closeable = null;
        try {
            File file = getFile(info);
            file.getParentFile().mkdirs();
            FileWriter fileWriter = new FileWriter(file, false);
            try {
                fileWriter.write(rawLyrics);
                fileWriter.flush();
                close(fileWriter);
                return true;
            } catch (IOException unused) {
                closeable = fileWriter;
                if (closeable != null) {
                    close(closeable);
                }
                return false;
            } catch (Throwable th) {
                th = th;
                closeable = fileWriter;
                if (closeable != null) {
                    close(closeable);
                }
                throw th;
            }
        } catch (IOException unused2) {
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public void delete(LyricsTrackInfo info) {
        getFile(info).delete();
    }

    public void removeLyricsNotInList(Set<LyricsTrackInfo> infoSet) {
        if (infoSet == null) {
            infoSet = Collections.emptySet();
        }
        File[] fileArrListFiles = getLyricsDirectory().listFiles();
        if (fileArrListFiles != null) {
            for (File file : fileArrListFiles) {
                LyricsTrackInfo trackInfoFromFile = getTrackInfoFromFile(file);
                if (!infoSet.contains(trackInfoFromFile)) {
                    delete(trackInfoFromFile);
                }
            }
            Log.info(TAG, "deleted %d files", Integer.valueOf(fileArrListFiles.length));
        }
    }

    private File getLyricsDirectory() {
        return new File(this.mContext.getFilesDir(), ".lyrics");
    }

    private File getFile(LyricsTrackInfo info) {
        return new File(getLyricsDirectory(), getFileName(info.getAsin(), info.getMarketplaceId()));
    }

    private static String getFileName(String asin, String marketplaceId) {
        return marketplaceId + "-" + asin;
    }

    private LyricsTrackInfo getTrackInfoFromFile(File lyricsFile) {
        final String[] strArrSplit = lyricsFile.getName().split("-");
        if (strArrSplit.length == 2) {
            return new LyricsTrackInfo() { // from class: com.amazon.mp3.lyrics.LyricsFile.1
                @Override // com.amazon.mp3.lyrics.item.LyricsTrackInfo
                public String getAsin() {
                    return strArrSplit[1];
                }

                @Override // com.amazon.mp3.lyrics.item.LyricsTrackInfo
                public String getMarketplaceId() {
                    return strArrSplit[0];
                }
            };
        }
        return null;
    }

    private String loadRawFile(LyricsTrackInfo info) throws FileNotFoundException {
        StringBuilder sb;
        FileReader fileReader = new FileReader(getFile(info));
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        try {
            try {
                sb = new StringBuilder();
            } catch (IOException unused) {
                throw new FileNotFoundException("Could not read the file");
            }
        } finally {
            close(bufferedReader);
            close(fileReader);
        }
        while (true) {
            String line = bufferedReader.readLine();
            if (line != null) {
                sb.append(line);
                sb.append('\n');
            } else {
                return sb.toString();
            }
            close(bufferedReader);
            close(fileReader);
        }
    }

    private boolean close(Closeable closeable) {
        try {
            closeable.close();
            return true;
        } catch (IOException unused) {
            return false;
        }
    }
}
