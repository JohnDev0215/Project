package com.amazon.mp3.download.redownload;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.mp3.automotiveOS.R;

/* JADX INFO: loaded from: classes3.dex */
public class RedownloadMediaTypeCheckUtil {
    public static boolean isPlaylist(@NonNull String name, Context context) {
        return context.getResources().getString(R.string.dmusic_redownload_group_name_prime_playlists).trim().toLowerCase().equals(name.trim().toLowerCase());
    }
}
