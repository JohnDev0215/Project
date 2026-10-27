package com.spotify.localfiles.mediastore;

import android.content.Context;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import com.spotify.localfiles.proto.LocalFile;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.a;
import kotlin.text.b;
import p.jb2;
import p.ou;
import p.py0;
import p.ux2;
import p.v03;
import p.v81;

/* JADX INFO: loaded from: classes.dex */
public final class OpenedAudioFiles {
    private final Context context;
    private final v81 permanentFiles$delegate;
    private final OpenedAudioFilesStorage storage;
    private final Set<Uri> temporaryFiles;

    public OpenedAudioFiles(Context context, OpenedAudioFilesStorage openedAudioFilesStorage) {
        v03.j(context, "context");
        v03.j(openedAudioFilesStorage, "storage");
        this.context = context;
        this.storage = openedAudioFilesStorage;
        this.temporaryFiles = new LinkedHashSet();
        this.permanentFiles$delegate = a.c(new py0() { // from class: com.spotify.localfiles.mediastore.OpenedAudioFiles$permanentFiles$2
            {
                super(0);
            }

            @Override // p.py0
            public final Set<Uri> invoke() {
                return ou.p0(this.this$0.storage.load());
            }
        });
    }

    private final void decorate(MediaMetadataRetriever mediaMetadataRetriever, Uri uri, LocalFile.Builder builder, LocalFile.Metadata.Builder builder2) {
        builder.setPath(uri.toString());
        mediaMetadataRetriever.setDataSource(this.context, uri);
        String strExtractMetadata = mediaMetadataRetriever.extractMetadata(7);
        String strExtractMetadata2 = mediaMetadataRetriever.extractMetadata(1);
        String strExtractMetadata3 = mediaMetadataRetriever.extractMetadata(2);
        String strExtractMetadata4 = mediaMetadataRetriever.extractMetadata(9);
        boolean z = mediaMetadataRetriever.getEmbeddedPicture() != null;
        if (strExtractMetadata == null && (strExtractMetadata = displayName(uri)) == null) {
            strExtractMetadata = uri.getLastPathSegment();
        }
        builder2.setTitle(strExtractMetadata);
        if (strExtractMetadata2 == null) {
            strExtractMetadata2 = "";
        }
        builder2.setAlbum(strExtractMetadata2);
        if (strExtractMetadata3 == null) {
            strExtractMetadata3 = "";
        }
        builder2.setArtist(strExtractMetadata3);
        builder2.setDuration((strExtractMetadata4 != null ? Integer.parseInt(strExtractMetadata4) : 0) / 1000);
        builder2.setImageState(z ? LocalFile.Metadata.ImageState.AVAILABLE : LocalFile.Metadata.ImageState.UNAVAILABLE);
        builder.setMetadata((LocalFile.Metadata) builder2.build());
    }

    private final String displayName(Uri uri) {
        Cursor cursorQuery = this.context.getContentResolver().query(uri, (String[]) v03.B("_display_name").toArray(new String[0]), null, null, null);
        if (cursorQuery != null) {
            try {
                int columnIndex = cursorQuery.getColumnIndex("_display_name");
                if (columnIndex >= 0 && cursorQuery.moveToFirst()) {
                    String string = cursorQuery.getString(columnIndex);
                    String strF0 = string != null ? b.F0(string, '.') : null;
                    ux2.e(cursorQuery, null);
                    return strF0;
                }
                ux2.e(cursorQuery, null);
            } finally {
            }
        }
        return null;
    }

    private final Set<Uri> getPermanentFiles() {
        return (Set) this.permanentFiles$delegate.getValue();
    }

    private final boolean isChildDocument(Uri uri, Uri uri2) {
        try {
            return DocumentsContract.isChildDocument(this.context.getContentResolver(), DocumentsContract.buildChildDocumentsUriUsingTree(uri, DocumentsContract.getTreeDocumentId(uri)), uri2);
        } catch (Exception unused) {
            return false;
        }
    }

    private final void save() {
        this.storage.save(getPermanentFiles());
    }

    public final void addPermanent(jb2 jb2Var) {
        v03.j(jb2Var, "uris");
        Iterator it = jb2Var.iterator();
        while (it.hasNext()) {
            this.context.getContentResolver().takePersistableUriPermission((Uri) it.next(), 1);
        }
        Collection permanentFiles = getPermanentFiles();
        v03.j(permanentFiles, "<this>");
        Iterator it2 = jb2Var.iterator();
        while (it2.hasNext()) {
            permanentFiles.add(it2.next());
        }
        save();
    }

    public final boolean addTemporary(Uri uri) {
        v03.j(uri, "uri");
        return this.temporaryFiles.add(uri);
    }

    public final jb2 getFiles() {
        return kotlin.sequences.a.I0(ou.Y(this.temporaryFiles), ou.Y(getPermanentFiles()));
    }

    public final boolean isPermanent(Uri uri) {
        v03.j(uri, "uri");
        boolean zContains = getPermanentFiles().contains(uri);
        Set<Uri> permanentFiles = getPermanentFiles();
        boolean z = false;
        if (!(permanentFiles instanceof Collection) || !permanentFiles.isEmpty()) {
            Iterator<T> it = permanentFiles.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (isChildDocument((Uri) it.next(), uri)) {
                    z = true;
                    break;
                }
            }
        }
        return zContains | z;
    }

    public final void removePermanent(Uri uri) {
        Object next;
        v03.j(uri, "contentUri");
        if (!getPermanentFiles().contains(uri)) {
            Iterator<T> it = getPermanentFiles().iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                } else {
                    next = it.next();
                    if (isChildDocument((Uri) next, uri)) {
                        break;
                    }
                }
            }
            uri = (Uri) next;
            if (uri == null) {
                return;
            }
        }
        getPermanentFiles().remove(uri);
        this.context.getContentResolver().releasePersistableUriPermission(uri, 1);
        save();
    }

    public final LocalFile toFile(Uri uri) throws IOException {
        LocalFile localFile;
        v03.j(uri, "uri");
        LocalFile.Builder builderNewBuilder = LocalFile.newBuilder();
        LocalFile.Metadata.Builder builderNewBuilder2 = LocalFile.Metadata.newBuilder();
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            v03.i(builderNewBuilder, "file");
            v03.i(builderNewBuilder2, "metadata");
            decorate(mediaMetadataRetriever, uri, builderNewBuilder, builderNewBuilder2);
            localFile = (LocalFile) builderNewBuilder.build();
        } catch (Throwable unused) {
            localFile = null;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            mediaMetadataRetriever.release();
        } else {
            mediaMetadataRetriever.release();
        }
        return localFile;
    }
}
