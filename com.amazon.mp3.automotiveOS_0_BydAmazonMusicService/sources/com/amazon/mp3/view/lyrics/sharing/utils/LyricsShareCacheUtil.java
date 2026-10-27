package com.amazon.mp3.view.lyrics.sharing.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.io.FilesKt__UtilsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: LyricsShareCacheUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u001e\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\bJ\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\r2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0002R\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/view/lyrics/sharing/utils/LyricsShareCacheUtil;", "", "", "name", "removeExtension", "Landroid/content/Context;", "context", "fileName", "Landroid/graphics/Bitmap;", "bitmap", "", "saveBitmapToFile", "dir", "", "loadImagesFromFile", "backgroundGradientsSequence", "Ljava/util/List;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LyricsShareCacheUtil {
    public static final LyricsShareCacheUtil INSTANCE = new LyricsShareCacheUtil();
    private static final List<String> backgroundGradientsSequence = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"Ripple_Cyan", "Ripple_Orange", "Ripple_Purple", "Ripple_Red", "Ripple_Violet", "Ripple_Yellow"});

    private LyricsShareCacheUtil() {
    }

    public final void saveBitmapToFile(Context context, String fileName, Bitmap bitmap) throws Throwable {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        File file = new File(context.getFilesDir(), "lyrics_share");
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(file, fileName);
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
                    try {
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream2);
                        fileOutputStream2.flush();
                        fileOutputStream2.close();
                    } catch (IOException e) {
                        e = e;
                        fileOutputStream = fileOutputStream2;
                        e.printStackTrace();
                        if (fileOutputStream == null) {
                        } else {
                            fileOutputStream.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (IOException e3) {
                e = e3;
            }
        } catch (IOException e4) {
            e4.printStackTrace();
        }
    }

    public final List<Bitmap> loadImagesFromFile(Context context, String dir) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(dir, "dir");
        File file = new File(context.getFilesDir(), dir);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        File[] fileArrListFiles = file.listFiles(new FileFilter() { // from class: com.amazon.mp3.view.lyrics.sharing.utils.LyricsShareCacheUtil$$ExternalSyntheticLambda0
            @Override // java.io.FileFilter
            public final boolean accept(File file2) {
                return LyricsShareCacheUtil.m1710loadImagesFromFile$lambda0(file2);
            }
        });
        ArrayList arrayList = new ArrayList();
        if (fileArrListFiles != null) {
            int i = 0;
            int length = fileArrListFiles.length;
            while (i < length) {
                File file2 = fileArrListFiles[i];
                i++;
                Bitmap bitmap = BitmapFactory.decodeFile(file2.getAbsolutePath());
                LyricsShareCacheUtil lyricsShareCacheUtil = INSTANCE;
                String name = file2.getName();
                Intrinsics.checkNotNullExpressionValue(name, "imageFile.name");
                String strRemoveExtension = lyricsShareCacheUtil.removeExtension(name);
                Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
                linkedHashMap.put(strRemoveExtension, bitmap);
            }
        }
        Iterator<T> it = backgroundGradientsSequence.iterator();
        while (it.hasNext()) {
            Bitmap bitmap2 = (Bitmap) linkedHashMap.get((String) it.next());
            if (bitmap2 != null) {
                arrayList.add(bitmap2);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: loadImagesFromFile$lambda-0, reason: not valid java name */
    public static final boolean m1710loadImagesFromFile$lambda0(File file) {
        if (file.isFile()) {
            Intrinsics.checkNotNullExpressionValue(file, "file");
            if (StringsKt__StringsJVMKt.equals(FilesKt__UtilsKt.getExtension(file), "png", true) || StringsKt__StringsJVMKt.equals(FilesKt__UtilsKt.getExtension(file), "jpg", true) || StringsKt__StringsJVMKt.equals(FilesKt__UtilsKt.getExtension(file), "jpeg", true) || StringsKt__StringsJVMKt.equals(FilesKt__UtilsKt.getExtension(file), "svg", true)) {
                return true;
            }
        }
        return false;
    }

    private final String removeExtension(String name) {
        String strSubstring = name.substring(0, StringsKt__StringsKt.lastIndexOf$default((CharSequence) name, ".", 0, false, 6, (Object) null));
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }
}
