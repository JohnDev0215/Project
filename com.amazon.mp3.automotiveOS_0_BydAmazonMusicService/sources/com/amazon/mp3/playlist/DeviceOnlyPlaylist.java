package com.amazon.mp3.playlist;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.library.provider.MediaProvider;

/* JADX INFO: loaded from: classes3.dex */
public class DeviceOnlyPlaylist {
    public static Uri create(final Context context, final String playlistName) {
        ContentResolver contentResolver = context.getContentResolver();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", playlistName);
        return contentResolver.insert(MediaProvider.UdoPlaylists.getContentUri("cirrus-local"), contentValues);
    }
}
