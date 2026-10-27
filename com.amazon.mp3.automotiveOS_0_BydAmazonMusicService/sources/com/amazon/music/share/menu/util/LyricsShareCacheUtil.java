package com.amazon.music.share.menu.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.io.FilesKt__UtilsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: LyricsShareCacheUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lcom/amazon/music/share/menu/util/LyricsShareCacheUtil;", "", "()V", "loadImagesFromCache", "", "Landroid/graphics/Bitmap;", "context", "Landroid/content/Context;", "dir", "", "DMMShareProvider_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LyricsShareCacheUtil {
    public static final LyricsShareCacheUtil INSTANCE = new LyricsShareCacheUtil();

    private LyricsShareCacheUtil() {
    }

    public final List<Bitmap> loadImagesFromCache(Context context, String dir) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(dir, "dir");
        File[] fileArrListFiles = new File(context.getFilesDir(), dir).listFiles(new FileFilter() { // from class: com.amazon.music.share.menu.util.LyricsShareCacheUtil$$ExternalSyntheticLambda0
            @Override // java.io.FileFilter
            public final boolean accept(File file) {
                return LyricsShareCacheUtil.m2193loadImagesFromCache$lambda0(file);
            }
        });
        ArrayList arrayList = new ArrayList();
        if (fileArrListFiles != null) {
            int i = 0;
            int length = fileArrListFiles.length;
            while (i < length) {
                File file = fileArrListFiles[i];
                i++;
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                if (bitmap != null) {
                    Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
                    arrayList.add(bitmap);
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: loadImagesFromCache$lambda-0, reason: not valid java name */
    public static final boolean m2193loadImagesFromCache$lambda0(File file) {
        if (file.isFile()) {
            Intrinsics.checkNotNullExpressionValue(file, "file");
            if (StringsKt__StringsJVMKt.equals(FilesKt__UtilsKt.getExtension(file), "png", true) || StringsKt__StringsJVMKt.equals(FilesKt__UtilsKt.getExtension(file), "jpg", true) || StringsKt__StringsJVMKt.equals(FilesKt__UtilsKt.getExtension(file), "jpeg", true) || StringsKt__StringsJVMKt.equals(FilesKt__UtilsKt.getExtension(file), "svg", true)) {
                return true;
            }
        }
        return false;
    }
}
