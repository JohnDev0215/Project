package com.amazon.music.media.auto.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import com.amazon.music.media.auto.util.AutoLog;
import com.google.android.gms.actions.SearchIntents;
import java.io.File;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: MediaItemImageContentProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0005¢\u0006\u0002\u0010\u0002J1\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0010\u0010\t\u001a\f\u0012\u0006\b\u0001\u0012\u00020\b\u0018\u00010\nH\u0016¢\u0006\u0002\u0010\u000bJ\u0012\u0010\f\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001c\u0010\r\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u001a\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\bH\u0016JO\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0005\u001a\u00020\u00062\u0010\u0010\u0017\u001a\f\u0012\u0006\b\u0001\u0012\u00020\b\u0018\u00010\n2\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0010\u0010\t\u001a\f\u0012\u0006\b\u0001\u0012\u00020\b\u0018\u00010\n2\b\u0010\u0018\u001a\u0004\u0018\u00010\bH\u0016¢\u0006\u0002\u0010\u0019J;\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0010\u0010\t\u001a\f\u0012\u0006\b\u0001\u0012\u00020\b\u0018\u00010\nH\u0016¢\u0006\u0002\u0010\u001b¨\u0006\u001d"}, d2 = {"Lcom/amazon/music/media/auto/provider/MediaItemImageContentProvider;", "Landroid/content/ContentProvider;", "()V", "delete", "", "uri", "Landroid/net/Uri;", "selection", "", "selectionArgs", "", "(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I", "getType", "insert", "contentValues", "Landroid/content/ContentValues;", "onCreate", "", "openFile", "Landroid/os/ParcelFileDescriptor;", "mode", SearchIntents.EXTRA_QUERY, "Landroid/database/Cursor;", "projection", "sortOrder", "(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;", "update", "(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I", "Companion", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MediaItemImageContentProvider extends ContentProvider {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = MediaItemImageContentProvider.class.getSimpleName();

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return false;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        return 0;
    }

    /* JADX INFO: compiled from: MediaItemImageContentProvider.kt */
    @Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\f\u0010\rJ\u0016\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004R\u001c\u0010\n\u001a\n \t*\u0004\u0018\u00010\b0\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/media/auto/provider/MediaItemImageContentProvider$Companion;", "", "Ljava/io/File;", "file", "Landroid/content/Context;", "context", "", "queryFile", "", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "<init>", "()V", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean queryFile(File file, Context context) throws IOException {
            Intrinsics.checkNotNullParameter(file, "file");
            Intrinsics.checkNotNullParameter(context, "context");
            String cachePath = context.getCacheDir().getCanonicalPath();
            if (!file.exists()) {
                return false;
            }
            String canonicalPath = file.getCanonicalPath();
            Intrinsics.checkNotNullExpressionValue(canonicalPath, "file.canonicalPath");
            Intrinsics.checkNotNullExpressionValue(cachePath, "cachePath");
            return StringsKt__StringsJVMKt.startsWith$default(canonicalPath, cachePath, false, 2, null);
        }
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri uri, String mode) throws IOException {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Context context = getContext();
        if (context == null) {
            return null;
        }
        if (!Intrinsics.areEqual(uri.getAuthority(), "com.amazon.mp3.media.automotive.provider")) {
            AutoLog autoLog = AutoLog.INSTANCE;
            String TAG2 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
            autoLog.error(TAG2, "Cannot open file not created by media browser authority");
            return null;
        }
        File file = new File(uri.getPath()).getCanonicalFile();
        AutoLog autoLog2 = AutoLog.INSTANCE;
        String TAG3 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
        autoLog2.debug(TAG3, "openFile uri " + uri + " mode " + mode);
        Companion companion = INSTANCE;
        Intrinsics.checkNotNullExpressionValue(file, "file");
        if (companion.queryFile(file, context)) {
            return ParcelFileDescriptor.open(file, 268435456);
        }
        Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
        autoLog2.error(TAG3, Intrinsics.stringPlus("Could find image file corresponding to local URI ", uri));
        return null;
    }
}
