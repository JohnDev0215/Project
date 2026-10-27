package cerence.ark.assistant.core;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.CancellationSignal;
import android.provider.MediaStore;
import cerence.ark.assistant.api.dynamic.Music;
import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.library.Utils;
import com.neusoft.na.common.FileUtils;
import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.i18n.MessageBundle;

/* JADX INFO: loaded from: classes2.dex */
public final class LocalMusicQuery {
    private static final String MUSIC_SELECTION = "is_music <> ? AND _display_name NOT LIKE ?";
    private static final String[] MUSIC_SELECTION_ARGS = {FileUtils.DEFAULT_VERSION, "%.wav"};
    private static final String TAG = "LocalMusicQuery";

    private LocalMusicQuery() {
    }

    public static List<Music> getMusic(Context context, CancellationSignal cancellationSignal) {
        String[] strArr;
        Cursor cursorQuery;
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT >= 33) {
            if (!Utils.checkSelfPermission(context, "android.permission.READ_MEDIA_AUDIO")) {
                Logger.error(TAG, "getMusic: the android.permission.READ_MEDIA_AUDIO was denied in Android 13 or later.");
                return arrayList;
            }
        } else if (!Utils.checkSelfPermission(context, "android.permission.READ_EXTERNAL_STORAGE")) {
            Logger.error(TAG, "getMusic: the android.permission.READ_EXTERNAL_STORAGE was denied.");
            return arrayList;
        }
        if (Build.VERSION.SDK_INT >= 30) {
            strArr = new String[]{"_id", MessageBundle.TITLE_ENTRY, "artist", "artist_id", "album", "album_id", "genre", "genre_id"};
        } else {
            strArr = new String[]{"_id", MessageBundle.TITLE_ENTRY, "artist", "artist_id", "album", "album_id"};
        }
        try {
            cursorQuery = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, strArr, MUSIC_SELECTION, MUSIC_SELECTION_ARGS, null, cancellationSignal);
        } catch (Exception e) {
            Logger.error(TAG, "getMusic: load music exception", e);
        }
        if (cursorQuery != null) {
            try {
                if (cursorQuery.getCount() != 0) {
                    Logger.info(TAG, Utils.format("getMusic: load music data begin, count = %s", Integer.valueOf(cursorQuery.getCount())));
                    int columnIndex = cursorQuery.getColumnIndex("_id");
                    int columnIndex2 = cursorQuery.getColumnIndex(MessageBundle.TITLE_ENTRY);
                    int columnIndex3 = cursorQuery.getColumnIndex("artist");
                    int columnIndex4 = cursorQuery.getColumnIndex("artist_id");
                    int columnIndex5 = cursorQuery.getColumnIndex("album");
                    int columnIndex6 = cursorQuery.getColumnIndex("album_id");
                    while (true) {
                        if (!cursorQuery.moveToNext()) {
                            break;
                        }
                        if (cancellationSignal != null && cancellationSignal.isCanceled()) {
                            Logger.info(TAG, "music query was cancelled.");
                            break;
                        }
                        Music music = new Music();
                        music.setId(cursorQuery.getLong(columnIndex));
                        music.setTitle(cursorQuery.getString(columnIndex2));
                        music.setArtist(cursorQuery.getString(columnIndex3));
                        music.setArtistId(cursorQuery.getLong(columnIndex4));
                        music.setAlbum(cursorQuery.getString(columnIndex5));
                        music.setAlbumId(cursorQuery.getLong(columnIndex6));
                        if (Build.VERSION.SDK_INT >= 30) {
                            int columnIndex7 = cursorQuery.getColumnIndex("genre");
                            if (columnIndex7 >= 0) {
                                music.setGenre(cursorQuery.getString(columnIndex7));
                            } else {
                                Logger.warn(TAG, "getMusic: the GENRE column name does not exist");
                            }
                            int columnIndex8 = cursorQuery.getColumnIndex("genre_id");
                            if (columnIndex8 >= 0) {
                                music.setGenreId(cursorQuery.getLong(columnIndex8));
                            } else {
                                Logger.warn(TAG, "getMusic: the GENRE_ID column name does not exist");
                            }
                        }
                        arrayList.add(music);
                    }
                    Logger.info(TAG, Utils.format("getMusic: load music finish, count = %s", Integer.valueOf(arrayList.size())));
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return arrayList;
                }
            } finally {
            }
        }
        Logger.error(TAG, "getMusic: No music found.");
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return arrayList;
    }
}
