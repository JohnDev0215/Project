package com.byd.vrassistant.conversation;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import com.byd.vrassistant.utils.RLog;
import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.i18n.MessageBundle;

/* JADX INFO: loaded from: classes.dex */
public class ScanMusicList {
    private final String TAG = "NEU_Audio ScanMusicList";
    private Context mcontext;

    public ScanMusicList(Context context) {
        this.mcontext = context;
    }

    public List<Song> scanMusicList(String str) {
        RLog.d("NEU_Audio ScanMusicList", "start scanMusicList");
        ArrayList arrayList = new ArrayList();
        ContentResolver contentResolver = this.mcontext.getContentResolver();
        Uri contentUri = MediaStore.Audio.Media.getContentUri(str);
        RLog.d("NEU_Audio ScanMusicList", "start uri ====" + contentUri.toString());
        Cursor cursorQuery = contentResolver.query(contentUri, null, null, null, null);
        if (cursorQuery == null) {
            RLog.d("NEU_Audio ScanMusicList", "start error");
        } else if (!cursorQuery.moveToFirst()) {
            RLog.d("NEU_Audio ScanMusicList", "no media");
            cursorQuery.close();
        } else {
            RLog.d("NEU_Audio ScanMusicList", "found  media");
            int columnIndex = cursorQuery.getColumnIndex("_id");
            int columnIndex2 = cursorQuery.getColumnIndex(MessageBundle.TITLE_ENTRY);
            int columnIndex3 = cursorQuery.getColumnIndex("_data");
            int columnIndex4 = cursorQuery.getColumnIndex("artist");
            int columnIndex5 = cursorQuery.getColumnIndex("duration");
            int columnIndex6 = cursorQuery.getColumnIndex("_size");
            do {
                Song song = new Song();
                cursorQuery.getLong(columnIndex);
                String string = cursorQuery.getString(columnIndex2);
                String string2 = cursorQuery.getString(columnIndex3);
                String string3 = cursorQuery.getString(columnIndex4);
                long j = cursorQuery.getLong(columnIndex6);
                song.setName(string);
                song.setData(string2);
                song.setSinger(string3);
                song.setDuration(columnIndex5);
                song.setSize(j);
                arrayList.add(song);
            } while (cursorQuery.moveToNext());
            cursorQuery.close();
        }
        return arrayList;
    }
}
