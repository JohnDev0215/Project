package com.spotify.localfiles.mediastore;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import androidx.annotation.Keep;
import com.spotify.localfiles.proto.QueryResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.EmptyList;
import kotlin.sequences.a;
import kotlin.text.b;
import p.av0;
import p.lq2;
import p.ou;
import p.ry0;
import p.sb;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
@Keep
public final class MediaStoreReader {
    private final ContentResolver contentResolver;
    private final Context context;
    private ObservedQueries observedQueries;
    private final OpenedAudioFiles openedAudioFiles;
    private final MediaStoreReaderOptions options;

    public static final class ObservedQueries extends ContentObserver {
        private final long handle;
        private final Handler handler;
        private final ry0 onChange;
        private final List<Cursor> queries;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public ObservedQueries(List<? extends Cursor> list, long j, ry0 ry0Var, Handler handler) {
            super(handler);
            v03.j(list, "queries");
            v03.j(ry0Var, "onChange");
            v03.j(handler, "handler");
            this.queries = list;
            this.handle = j;
            this.onChange = ry0Var;
            this.handler = handler;
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ((Cursor) it.next()).registerContentObserver(this);
            }
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            onChange(z, null);
        }

        public final void prepareForShutdown(ry0 ry0Var) {
            v03.j(ry0Var, "prepareForShutdown");
            ry0Var.invoke(Long.valueOf(this.handle));
        }

        public final void stop(final ry0 ry0Var) {
            v03.j(ry0Var, "freeHandle");
            Iterator<T> it = this.queries.iterator();
            while (it.hasNext()) {
                ((Cursor) it.next()).unregisterContentObserver(this);
            }
            this.handler.post(new Runnable() { // from class: com.spotify.localfiles.mediastore.MediaStoreReader$ObservedQueries$stop$2
                @Override // java.lang.Runnable
                public final void run() {
                    ry0Var.invoke(Long.valueOf(this.handle));
                }
            });
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            this.onChange.invoke(Long.valueOf(this.handle));
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public ObservedQueries(List<? extends Cursor> list, long j, ry0 ry0Var) {
            this(list, j, ry0Var, new Handler(Looper.getMainLooper()));
            v03.j(list, "queries");
            v03.j(ry0Var, "onChange");
        }
    }

    public MediaStoreReader(Context context, MediaStoreReaderOptions mediaStoreReaderOptions, OpenedAudioFiles openedAudioFiles) {
        v03.j(context, "context");
        v03.j(mediaStoreReaderOptions, "options");
        v03.j(openedAudioFiles, "openedAudioFiles");
        this.context = context;
        this.options = mediaStoreReaderOptions;
        this.openedAudioFiles = openedAudioFiles;
        this.contentResolver = context.getContentResolver();
    }

    private final String displayName(Cursor cursor, int i) {
        String string = cursor.getString(i);
        if (string != null) {
            return b.F0(string, '.');
        }
        return null;
    }

    private final Integer duration(Cursor cursor, int i) {
        if (cursor.getType(i) == 1) {
            return Integer.valueOf(cursor.getInt(i) / 1000);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final native void freeHandle(long j);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void onChange(long j);

    /* JADX INFO: Access modifiers changed from: private */
    public final native void prepareForShutdown(long j);

    private final Cursor query(Uri uri, boolean z) {
        if (z) {
            uri = DocumentsContract.buildChildDocumentsUriUsingTree(uri, DocumentsContract.isDocumentUri(this.context, uri) ? DocumentsContract.getDocumentId(uri) : DocumentsContract.getTreeDocumentId(uri));
        }
        Uri uri2 = uri;
        ArrayList arrayListD = v03.D("_id", "_display_name", "is_alarm", "is_music", "is_notification", "is_podcast", "is_ringtone", "title", MediaStoreReaderKt.DURATION);
        if (z) {
            arrayListD.addAll(v03.C("document_id", "mime_type"));
        }
        int i = Build.VERSION.SDK_INT;
        if (i >= 29) {
            arrayListD.addAll(v03.C("is_audiobook", "is_pending"));
        }
        if (i >= 30) {
            arrayListD.addAll(v03.B("is_trashed"));
        }
        ArrayList arrayList = new ArrayList();
        if (!this.options.getIncludeAlarms()) {
            arrayList.add("is_alarm == 0");
        }
        if (!this.options.getIncludeMusic()) {
            arrayList.add("is_music == 0");
        }
        if (!this.options.getIncludeNotifications()) {
            arrayList.add("is_notification == 0");
        }
        if (!this.options.getIncludePodcasts()) {
            arrayList.add("is_podcast == 0");
        }
        if (!this.options.getIncludeRingtones()) {
            arrayList.add("is_ringtone == 0");
        }
        if (i >= 29) {
            arrayList.add("is_pending == 0");
            if (!this.options.getIncludeAudiobooks()) {
                arrayList.add("is_audiobook == 0");
            }
        }
        if (i >= 30) {
            arrayList.add("is_trashed == 0");
        }
        if (this.options.getDurationMin() > 0) {
            arrayList.add(MediaStoreReaderKt.DURATION + " >= " + this.options.getDurationMin());
        }
        return this.contentResolver.query(uri2, (String[]) arrayListD.toArray(new String[0]), ou.h0(arrayList, " AND ", null, null, null, 62), null, null);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(21:(3:9|(2:11|72)|12)(2:15|(1:20)(1:19))|21|69|22|(1:24)(1:25)|(1:31)|32|33|(1:35)(1:36)|37|(1:39)|40|(1:42)|43|(1:45)(1:46)|47|57|58|73|12|6) */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00fb, code lost:
    
        r13.clear();
        r0 = title(r5, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0102, code lost:
    
        if (r0 == null) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x010a, code lost:
    
        r0 = r14.getLastPathSegment();
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x010e, code lost:
    
        r13.setTitle(r0);
        r0 = duration(r5, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0115, code lost:
    
        if (r0 != null) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0117, code lost:
    
        r13.setDuration(r0.intValue());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void queryUri(android.media.MediaMetadataRetriever r21, com.spotify.localfiles.proto.QueryResult.Builder r22, android.net.Uri r23) {
        /*
            Method dump skipped, instruction units count: 316
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.spotify.localfiles.mediastore.MediaStoreReader.queryUri(android.media.MediaMetadataRetriever, com.spotify.localfiles.proto.QueryResult$Builder, android.net.Uri):void");
    }

    private final String title(Cursor cursor, int i) {
        if (cursor.getType(i) != 3) {
            return null;
        }
        String string = cursor.getString(i);
        v03.i(string, "cursor.getString(titleColumn)");
        return transformUnknown(string);
    }

    private final String transformUnknown(String str) {
        return v03.b(str, "<unknown>") ? "" : str;
    }

    public final byte[] runQuery() throws IOException {
        QueryResult.Builder builderNewBuilder = QueryResult.newBuilder();
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        av0 av0Var = new av0(a.I0(new sb(0, new Uri[]{MediaStore.Audio.Media.EXTERNAL_CONTENT_URI}), this.openedAudioFiles.getFiles()));
        while (av0Var.a()) {
            Uri uri = (Uri) av0Var.next();
            try {
                v03.i(builderNewBuilder, "result");
                v03.i(uri, "uri");
                queryUri(mediaMetadataRetriever, builderNewBuilder, uri);
            } catch (Throwable unused) {
            }
        }
        if (Build.VERSION.SDK_INT >= 29) {
            mediaMetadataRetriever.release();
        } else {
            mediaMetadataRetriever.release();
        }
        byte[] byteArray = ((QueryResult) builderNewBuilder.build()).toByteArray();
        v03.i(byteArray, "result.build().toByteArray()");
        return byteArray;
    }

    public final void startListening(long j) {
        try {
            Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            v03.i(uri, "EXTERNAL_CONTENT_URI");
            Cursor cursorQuery = query(uri, false);
            this.observedQueries = new ObservedQueries(cursorQuery != null ? v03.B(cursorQuery) : EmptyList.c, j, new ry0() { // from class: com.spotify.localfiles.mediastore.MediaStoreReader.startListening.1
                {
                    super(1);
                }

                @Override // p.ry0
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke(((Number) obj).longValue());
                    return lq2.a;
                }

                public final void invoke(long j2) {
                    MediaStoreReader.this.onChange(j2);
                }
            });
        } catch (Exception unused) {
        }
    }

    public final void stopListening() {
        ObservedQueries observedQueries = this.observedQueries;
        if (observedQueries != null) {
            observedQueries.prepareForShutdown(new ry0() { // from class: com.spotify.localfiles.mediastore.MediaStoreReader.stopListening.1
                {
                    super(1);
                }

                @Override // p.ry0
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke(((Number) obj).longValue());
                    return lq2.a;
                }

                public final void invoke(long j) {
                    MediaStoreReader.this.prepareForShutdown(j);
                }
            });
        }
        ObservedQueries observedQueries2 = this.observedQueries;
        if (observedQueries2 != null) {
            observedQueries2.stop(new ry0() { // from class: com.spotify.localfiles.mediastore.MediaStoreReader.stopListening.2
                {
                    super(1);
                }

                @Override // p.ry0
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke(((Number) obj).longValue());
                    return lq2.a;
                }

                public final void invoke(long j) {
                    MediaStoreReader.this.freeHandle(j);
                }
            });
        }
        this.observedQueries = null;
    }

    public final void triggerScan(String str) {
        v03.j(str, "path");
        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        intent.setData(Uri.fromFile(new File(str)));
        this.context.sendBroadcast(intent, null);
    }
}
