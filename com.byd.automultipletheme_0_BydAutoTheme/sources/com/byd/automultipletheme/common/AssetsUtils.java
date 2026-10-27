package com.byd.automultipletheme.common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.ResourceUtils;
import com.blankj.utilcode.util.Utils;
import com.byd.automultipletheme.bean.AutoTheme;
import com.byd.automultipletheme.bean.AutoWallpaper;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AssetsUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004J\u0016\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000fJ\u0016\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u00110\rj\b\u0012\u0004\u0012\u00020\u0011`\u000fJ\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0004J\u000e\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0004J\u000e\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0004J\u001e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u0004J\u0016\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0004J\u001b\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u001e2\u0006\u0010\u0019\u001a\u00020\u0004¢\u0006\u0002\u0010\u001fJ\u0010\u0010 \u001a\u00020\u00042\b\u0010\u0019\u001a\u0004\u0018\u00010\u0004J\u0012\u0010!\u001a\u00020\u00042\b\u0010\u0019\u001a\u0004\u0018\u00010\u0004H\u0003J\u000e\u0010\"\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0004J\u0018\u0010#\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0004H\u0007J\u0010\u0010$\u001a\u0004\u0018\u00010\u00112\u0006\u0010%\u001a\u00020&R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/byd/automultipletheme/common/AssetsUtils;", "", "()V", "ASSETS_THEME_LIST", "", "ASSETS_THEME_LIST_RIGHT", "ASSETS_WALLPAPER_LIST", "resourceName", "copyFilesFromAssets", "", "oldPath", "newPath", "getAssetsAutoTheme", "Ljava/util/ArrayList;", "Lcom/byd/automultipletheme/bean/AutoTheme;", "Lkotlin/collections/ArrayList;", "getAssetsAutoWallpaper", "Lcom/byd/automultipletheme/bean/AutoWallpaper;", "getAssetsBitmap", "Landroid/graphics/Bitmap;", "url", "getAssetsUrl", "getBitmap", "getBitmapFromAssets", "", "pathName", "orientation", "mode", "getCarouselBitmapFromAssets", "getDefaultCarouselWallpaper", "", "(Ljava/lang/String;)[Ljava/lang/String;", "getPathName", "getPathNameNoExtension", "getPresetAssetsBitmap", "getTimeBitmapFromAssets", "getWallpaperById", "resId", "", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AssetsUtils {
    private static final String ASSETS_THEME_LIST = "res/ThemeList.json";
    private static final String ASSETS_THEME_LIST_RIGHT = "res/ThemeListRight.json";
    private static final String ASSETS_WALLPAPER_LIST = "res/WallpaperList.json";
    public static final AssetsUtils INSTANCE = new AssetsUtils();
    private static String resourceName = "wallpaper";

    private AssetsUtils() {
    }

    public final ArrayList<AutoTheme> getAssetsAutoTheme() {
        String assets2String;
        Type type = new TypeToken<ArrayList<AutoTheme>>() { // from class: com.byd.automultipletheme.common.AssetsUtils$getAssetsAutoTheme$type$1
        }.getType();
        if (AutoUtils.INSTANCE.isRightRudder()) {
            assets2String = ResourceUtils.readAssets2String(ASSETS_THEME_LIST_RIGHT);
        } else {
            assets2String = ResourceUtils.readAssets2String(ASSETS_THEME_LIST);
        }
        Object objFromJson = GsonUtils.fromJson(assets2String, type);
        Intrinsics.checkNotNullExpressionValue(objFromJson, "fromJson(json, type)");
        return (ArrayList) objFromJson;
    }

    public final ArrayList<AutoWallpaper> getAssetsAutoWallpaper() {
        Object objFromJson = GsonUtils.fromJson(ResourceUtils.readAssets2String(ASSETS_WALLPAPER_LIST), new TypeToken<ArrayList<AutoWallpaper>>() { // from class: com.byd.automultipletheme.common.AssetsUtils$getAssetsAutoWallpaper$type$1
        }.getType());
        Intrinsics.checkNotNullExpressionValue(objFromJson, "fromJson(\n            Re…        ), type\n        )");
        return (ArrayList) objFromJson;
    }

    public final AutoWallpaper getWallpaperById(int resId) {
        for (AutoWallpaper autoWallpaper : getAssetsAutoWallpaper()) {
            if (resId == autoWallpaper.getRsId()) {
                return autoWallpaper;
            }
        }
        for (AutoTheme item : getAssetsAutoTheme()) {
            if (resId == item.getRsId()) {
                AutoUtils autoUtils = AutoUtils.INSTANCE;
                Intrinsics.checkNotNullExpressionValue(item, "item");
                return autoUtils.convertAutoThemeToAutoWallpaper(item);
            }
        }
        return null;
    }

    public final String getAssetsUrl(String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        return StringsKt.startsWith$default(url, "file:///android_asset/", false, 2, (Object) null) ? StringsKt.replace$default(url, "file:///android_asset/", "", false, 4, (Object) null) : url;
    }

    public final Bitmap getAssetsBitmap(String url) throws IOException {
        Intrinsics.checkNotNullParameter(url, "url");
        if (StringsKt.startsWith$default(url, "file:///android_asset/", false, 2, (Object) null)) {
            url = StringsKt.replace$default(url, "file:///android_asset/", "", false, 4, (Object) null);
        }
        InputStream inputStreamOpen = Utils.getApp().getAssets().open(url);
        Bitmap bitmap = BitmapFactory.decodeStream(inputStreamOpen);
        inputStreamOpen.close();
        Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
        return bitmap;
    }

    public final Bitmap getPresetAssetsBitmap(String url) throws IOException {
        Intrinsics.checkNotNullParameter(url, "url");
        if (StringsKt.startsWith$default(url, "file:///android_asset/", false, 2, (Object) null)) {
            url = StringsKt.replace$default(url, "file:///android_asset/", "", false, 4, (Object) null);
        } else if (StringsKt.startsWith$default(url, "/storage/emulated/0/Android/data/com.byd.automultipletheme/files/", false, 2, (Object) null)) {
            url = StringsKt.replace$default(url, "/storage/emulated/0/Android/data/com.byd.automultipletheme/files/", "", false, 4, (Object) null);
        }
        InputStream inputStreamOpen = Utils.getApp().getAssets().open(url);
        Bitmap bitmap = BitmapFactory.decodeStream(inputStreamOpen);
        inputStreamOpen.close();
        Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
        return bitmap;
    }

    public final Bitmap getBitmap(String url) throws IOException {
        FileInputStream fileInputStream;
        Intrinsics.checkNotNullParameter(url, "url");
        if (StringsKt.startsWith$default(url, "file:///android_asset/", false, 2, (Object) null)) {
            fileInputStream = Utils.getApp().getAssets().open(StringsKt.replace$default(url, "file:///android_asset/", "", false, 4, (Object) null));
            Intrinsics.checkNotNullExpressionValue(fileInputStream, "{\n            Utils.getA…d_asset/\", \"\"))\n        }");
        } else {
            fileInputStream = new FileInputStream(url);
        }
        Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream);
        fileInputStream.close();
        Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
        return bitmap;
    }

    public final boolean copyFilesFromAssets(String oldPath, String newPath) {
        Intrinsics.checkNotNullParameter(oldPath, "oldPath");
        Intrinsics.checkNotNullParameter(newPath, "newPath");
        try {
            String[] list = Utils.getApp().getAssets().list(oldPath);
            if (list != null) {
                if (!(list.length == 0)) {
                    LogUtils.INSTANCE.i("copyFilesFromAssets s: " + new File(newPath).mkdirs() + "   newPath : " + newPath);
                    for (String str : list) {
                        if (!StringsKt.contains$default((CharSequence) str, (CharSequence) "preview", false, 2, (Object) null)) {
                            INSTANCE.copyFilesFromAssets(oldPath + '/' + str, newPath + '/' + str);
                        }
                    }
                } else {
                    File file = new File(newPath);
                    if (!file.exists() && file.length() <= 0) {
                        InputStream inputStreamOpen = Utils.getApp().getAssets().open(INSTANCE.getAssetsUrl(oldPath));
                        Intrinsics.checkNotNullExpressionValue(inputStreamOpen, "getApp().assets.open(getAssetsUrl(oldPath))");
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int i = inputStreamOpen.read(bArr);
                            if (i == -1) {
                                break;
                            }
                            fileOutputStream.write(bArr, 0, i);
                        }
                        fileOutputStream.flush();
                        fileOutputStream.getFD().sync();
                        inputStreamOpen.close();
                        fileOutputStream.close();
                    }
                    return true;
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public final String[] getDefaultCarouselWallpaper(String pathName) {
        Intrinsics.checkNotNullParameter(pathName, "pathName");
        String[] strArr = new String[7];
        try {
            String[] list = Utils.getApp().getAssets().list(pathName);
            if (list != null) {
                if (!(list.length == 0)) {
                    int length = list.length;
                    for (int i = 0; i < length; i++) {
                        String str = list[i];
                        strArr[i] = pathName + '/' + str;
                        LogUtils.INSTANCE.i("fileName: ", str, "fileNameList: ", strArr);
                    }
                } else {
                    LogUtils.INSTANCE.i("this directory have not file");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:121:0x01cd A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x012f A[Catch: Exception -> 0x022d, TryCatch #0 {Exception -> 0x022d, blocks: (B:3:0x0017, B:5:0x002c, B:12:0x0041, B:14:0x0045, B:82:0x01cd, B:17:0x0057, B:20:0x0066, B:22:0x0073, B:25:0x008b, B:28:0x0094, B:30:0x009a, B:33:0x00a9, B:36:0x00b7, B:39:0x00c5, B:40:0x00de, B:42:0x00e4, B:44:0x00ea, B:47:0x00fa, B:50:0x0108, B:53:0x0116, B:54:0x012f, B:56:0x0135, B:58:0x013b, B:61:0x014b, B:64:0x0159, B:67:0x0166, B:68:0x017f, B:70:0x0185, B:72:0x018b, B:75:0x019a, B:78:0x01a7, B:81:0x01b4, B:24:0x0080, B:83:0x01d2, B:85:0x01e3, B:87:0x01e9, B:88:0x01f1, B:90:0x01f7, B:92:0x01fd, B:93:0x0205, B:95:0x020b, B:97:0x0211, B:98:0x0219, B:100:0x021f, B:102:0x0225), top: B:107:0x0017 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0135 A[Catch: Exception -> 0x022d, TryCatch #0 {Exception -> 0x022d, blocks: (B:3:0x0017, B:5:0x002c, B:12:0x0041, B:14:0x0045, B:82:0x01cd, B:17:0x0057, B:20:0x0066, B:22:0x0073, B:25:0x008b, B:28:0x0094, B:30:0x009a, B:33:0x00a9, B:36:0x00b7, B:39:0x00c5, B:40:0x00de, B:42:0x00e4, B:44:0x00ea, B:47:0x00fa, B:50:0x0108, B:53:0x0116, B:54:0x012f, B:56:0x0135, B:58:0x013b, B:61:0x014b, B:64:0x0159, B:67:0x0166, B:68:0x017f, B:70:0x0185, B:72:0x018b, B:75:0x019a, B:78:0x01a7, B:81:0x01b4, B:24:0x0080, B:83:0x01d2, B:85:0x01e3, B:87:0x01e9, B:88:0x01f1, B:90:0x01f7, B:92:0x01fd, B:93:0x0205, B:95:0x020b, B:97:0x0211, B:98:0x0219, B:100:0x021f, B:102:0x0225), top: B:107:0x0017 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x017f A[Catch: Exception -> 0x022d, TryCatch #0 {Exception -> 0x022d, blocks: (B:3:0x0017, B:5:0x002c, B:12:0x0041, B:14:0x0045, B:82:0x01cd, B:17:0x0057, B:20:0x0066, B:22:0x0073, B:25:0x008b, B:28:0x0094, B:30:0x009a, B:33:0x00a9, B:36:0x00b7, B:39:0x00c5, B:40:0x00de, B:42:0x00e4, B:44:0x00ea, B:47:0x00fa, B:50:0x0108, B:53:0x0116, B:54:0x012f, B:56:0x0135, B:58:0x013b, B:61:0x014b, B:64:0x0159, B:67:0x0166, B:68:0x017f, B:70:0x0185, B:72:0x018b, B:75:0x019a, B:78:0x01a7, B:81:0x01b4, B:24:0x0080, B:83:0x01d2, B:85:0x01e3, B:87:0x01e9, B:88:0x01f1, B:90:0x01f7, B:92:0x01fd, B:93:0x0205, B:95:0x020b, B:97:0x0211, B:98:0x0219, B:100:0x021f, B:102:0x0225), top: B:107:0x0017 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0185 A[Catch: Exception -> 0x022d, TryCatch #0 {Exception -> 0x022d, blocks: (B:3:0x0017, B:5:0x002c, B:12:0x0041, B:14:0x0045, B:82:0x01cd, B:17:0x0057, B:20:0x0066, B:22:0x0073, B:25:0x008b, B:28:0x0094, B:30:0x009a, B:33:0x00a9, B:36:0x00b7, B:39:0x00c5, B:40:0x00de, B:42:0x00e4, B:44:0x00ea, B:47:0x00fa, B:50:0x0108, B:53:0x0116, B:54:0x012f, B:56:0x0135, B:58:0x013b, B:61:0x014b, B:64:0x0159, B:67:0x0166, B:68:0x017f, B:70:0x0185, B:72:0x018b, B:75:0x019a, B:78:0x01a7, B:81:0x01b4, B:24:0x0080, B:83:0x01d2, B:85:0x01e3, B:87:0x01e9, B:88:0x01f1, B:90:0x01f7, B:92:0x01fd, B:93:0x0205, B:95:0x020b, B:97:0x0211, B:98:0x0219, B:100:0x021f, B:102:0x0225), top: B:107:0x0017 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void getBitmapFromAssets(java.lang.String r18, java.lang.String r19, java.lang.String r20) {
        /*
            Method dump skipped, instruction units count: 562
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.automultipletheme.common.AssetsUtils.getBitmapFromAssets(java.lang.String, java.lang.String, java.lang.String):void");
    }

    public final void getCarouselBitmapFromAssets(String pathName, String orientation) {
        Intrinsics.checkNotNullParameter(pathName, "pathName");
        Intrinsics.checkNotNullParameter(orientation, "orientation");
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            String[] list = Utils.getApp().getAssets().list(pathName);
            if (list != null) {
                if (!(!(list.length == 0))) {
                    AssetsUtils assetsUtils = INSTANCE;
                    String fileNameNoExtension = FileUtils.getFileNameNoExtension(pathName);
                    Intrinsics.checkNotNullExpressionValue(fileNameNoExtension, "getFileNameNoExtension(pathName)");
                    resourceName = fileNameNoExtension;
                    linkedHashMap.put(resourceName, assetsUtils.getAssetsBitmap(pathName));
                    if (Intrinsics.areEqual(orientation, Const.DIRECTION_LAND)) {
                        WallpaperManagerUtils.getInstance().addBitmaps(linkedHashMap, 0, 4, true);
                    }
                    if (Intrinsics.areEqual(orientation, Const.DIRECTION_PORT)) {
                        WallpaperManagerUtils.getInstance().addBitmaps(linkedHashMap, 1, 4, true);
                    }
                }
            }
            linkedHashMap.clear();
            LogUtils.INSTANCE.d("时间间隔", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void getTimeBitmapFromAssets(String pathName, String orientation) {
        Intrinsics.checkNotNullParameter(pathName, "pathName");
        Intrinsics.checkNotNullParameter(orientation, "orientation");
        try {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            String[] list = Utils.getApp().getAssets().list(pathName);
            if (list != null) {
                if (!(list.length == 0)) {
                    for (String str : list) {
                        AssetsUtils assetsUtils = INSTANCE;
                        String fileNameNoExtension = FileUtils.getFileNameNoExtension(str);
                        Intrinsics.checkNotNullExpressionValue(fileNameNoExtension, "getFileNameNoExtension(fileName)");
                        resourceName = fileNameNoExtension;
                        if (Intrinsics.areEqual(orientation, Const.DIRECTION_LAND)) {
                            if (!Intrinsics.areEqual(str, "wallpaper")) {
                                assetsUtils.getTimeBitmapFromAssets(pathName + '/' + str, Const.DIRECTION_LAND);
                                if (Intrinsics.areEqual(orientation, Const.DIRECTION_PORT)) {
                                    assetsUtils.getTimeBitmapFromAssets(pathName + '/' + str, Const.DIRECTION_PORT);
                                }
                            }
                        } else if (Intrinsics.areEqual(orientation, Const.DIRECTION_PORT) && !StringsKt.contains$default((CharSequence) str, (CharSequence) "wallpaper-land", false, 2, (Object) null)) {
                            assetsUtils.getTimeBitmapFromAssets(pathName + '/' + str, Const.DIRECTION_PORT);
                        }
                    }
                    return;
                }
                linkedHashMap.put(resourceName, INSTANCE.getAssetsBitmap(pathName));
                if (Intrinsics.areEqual(orientation, Const.DIRECTION_LAND)) {
                    WallpaperManagerUtils.getInstance().addBitmaps(linkedHashMap, 0, 3, true);
                }
                if (Intrinsics.areEqual(orientation, Const.DIRECTION_PORT)) {
                    WallpaperManagerUtils.getInstance().addBitmaps(linkedHashMap, 1, 3, true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final String getPathNameNoExtension(String pathName) {
        String fileNameWithExtension = FileUtils.getFileName(pathName);
        String fileNameNoExtension = FileUtils.getFileNameNoExtension(pathName);
        if (pathName != null) {
            List listSplit$default = StringsKt.split$default((CharSequence) pathName, new String[]{'/' + fileNameNoExtension}, false, 0, 6, (Object) null);
            return FileUtils.getFileNameNoExtension(((String) listSplit$default.get(0)) + ((String) listSplit$default.get(1))) + '/' + fileNameWithExtension;
        }
        Intrinsics.checkNotNullExpressionValue(fileNameWithExtension, "fileNameWithExtension");
        return fileNameWithExtension;
    }

    public final String getPathName(String pathName) {
        String pathNameNoExtension = getPathNameNoExtension(pathName);
        if (pathName != null) {
            try {
                List listSplit$default = StringsKt.split$default((CharSequence) pathName, new String[]{'/' + pathNameNoExtension}, false, 0, 6, (Object) null);
                return FileUtils.getFileNameNoExtension(((String) listSplit$default.get(0)) + ((String) listSplit$default.get(1))) + '/' + pathNameNoExtension;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return pathNameNoExtension;
    }
}
