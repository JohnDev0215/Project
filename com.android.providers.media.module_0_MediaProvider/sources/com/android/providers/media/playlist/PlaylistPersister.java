package com.android.providers.media.playlist;

import android.content.ContentResolver;
import android.net.Uri;
import com.android.providers.media.util.MimeUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface PlaylistPersister {
    void read(InputStream inputStream, List<Path> list) throws IOException;

    void write(OutputStream outputStream, List<Path> list) throws IOException;

    static PlaylistPersister resolvePersister(File file) throws IOException {
        return resolvePersister(MimeUtils.resolveMimeType(file));
    }

    static PlaylistPersister resolvePersister(ContentResolver contentResolver, Uri uri) throws IOException {
        return resolvePersister(contentResolver.getType(uri));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:29:0x005e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static com.android.providers.media.playlist.PlaylistPersister resolvePersister(java.lang.String r3) throws java.io.IOException {
        /*
            java.util.Locale r0 = java.util.Locale.ROOT
            java.lang.String r0 = r3.toLowerCase(r0)
            int r1 = r0.hashCode()
            switch(r1) {
                case -1838132266: goto L54;
                case -1165508903: goto L4a;
                case -979095690: goto L40;
                case -731874404: goto L36;
                case -622808459: goto L2c;
                case -432766831: goto L22;
                case 264230524: goto L18;
                case 1872259501: goto Le;
                default: goto Ld;
            }
        Ld:
            goto L5e
        Le:
            java.lang.String r1 = "application/vnd.ms-wpl"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L5e
            r0 = 5
            goto L5f
        L18:
            java.lang.String r1 = "audio/x-mpegurl"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L5e
            r0 = 1
            goto L5f
        L22:
            java.lang.String r1 = "audio/mpegurl"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L5e
            r0 = 0
            goto L5f
        L2c:
            java.lang.String r1 = "application/vnd.apple.mpegurl"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L5e
            r0 = 2
            goto L5f
        L36:
            java.lang.String r1 = "application/xspf+xml"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L5e
            r0 = 7
            goto L5f
        L40:
            java.lang.String r1 = "application/x-mpegurl"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L5e
            r0 = 3
            goto L5f
        L4a:
            java.lang.String r1 = "audio/x-scpls"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L5e
            r0 = 4
            goto L5f
        L54:
            java.lang.String r1 = "video/x-ms-asf"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L5e
            r0 = 6
            goto L5f
        L5e:
            r0 = -1
        L5f:
            switch(r0) {
                case 0: goto L8b;
                case 1: goto L8b;
                case 2: goto L8b;
                case 3: goto L8b;
                case 4: goto L85;
                case 5: goto L7f;
                case 6: goto L7f;
                case 7: goto L79;
                default: goto L62;
            }
        L62:
            java.io.IOException r0 = new java.io.IOException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unsupported playlist format "
            r1.append(r2)
            r1.append(r3)
            java.lang.String r3 = r1.toString()
            r0.<init>(r3)
            throw r0
        L79:
            com.android.providers.media.playlist.XspfPlaylistPersister r3 = new com.android.providers.media.playlist.XspfPlaylistPersister
            r3.<init>()
            return r3
        L7f:
            com.android.providers.media.playlist.WplPlaylistPersister r3 = new com.android.providers.media.playlist.WplPlaylistPersister
            r3.<init>()
            return r3
        L85:
            com.android.providers.media.playlist.PlsPlaylistPersister r3 = new com.android.providers.media.playlist.PlsPlaylistPersister
            r3.<init>()
            return r3
        L8b:
            com.android.providers.media.playlist.M3uPlaylistPersister r3 = new com.android.providers.media.playlist.M3uPlaylistPersister
            r3.<init>()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.media.playlist.PlaylistPersister.resolvePersister(java.lang.String):com.android.providers.media.playlist.PlaylistPersister");
    }
}
