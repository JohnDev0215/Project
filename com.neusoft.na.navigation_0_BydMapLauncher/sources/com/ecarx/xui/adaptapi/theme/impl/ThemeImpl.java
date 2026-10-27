package com.ecarx.xui.adaptapi.theme.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.ServiceManager;
import com.ecarx.xui.adaptapi.theme.Theme;
import com.neusoft.app.IThemeManagerService;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ThemeImpl extends Theme {
    public static final String SCREENSAVER_WALLPAPER_DARK_IMAGE_NAME = "default_screensaver_wallpaper_dark.jpg";
    public static final String SCREENSAVER_WALLPAPER_DARK_IMAGE_PATH = "/data/system/theme/wallpaper/default_screensaver_wallpaper_dark.jpg";
    public static final String SCREENSAVER_WALLPAPER_IMAGE_NAME = "default_screensaver_wallpaper.jpg";
    public static final String SCREENSAVER_WALLPAPER_IMAGE_PATH = "/data/system/theme/wallpaper/default_screensaver_wallpaper.jpg";
    public static final String WALLPAPER_DARK_IMAGE_NAME = "default_wallpaper_dark.jpg";
    public static final String WALLPAPER_DARK_IMAGE_PATH = "/data/system/theme/wallpaper/default_wallpaper_dark.jpg";
    public static final String WALLPAPER_IMAGE_NAME = "default_wallpaper.jpg";
    public static final String WALLPAPER_IMAGE_PATH = "/data/system/theme/wallpaper/default_wallpaper.jpg";

    public ThemeImpl(Context context) {
    }

    @Override // com.ecarx.xui.adaptapi.theme.Theme
    public void applyDefaultTheme() throws Exception {
        IThemeManagerService.Stub.asInterface(ServiceManager.getService("ThemeService")).applyDefaultTheme();
    }

    @Override // com.ecarx.xui.adaptapi.theme.Theme
    public void applyTheme(String str, List<String> list, boolean z, boolean z2, boolean z3, boolean z4) throws Exception {
        IThemeManagerService.Stub.asInterface(ServiceManager.getService("ThemeService")).applyTheme(str, list, z, z2, z3, z4);
    }

    @Override // com.ecarx.xui.adaptapi.theme.Theme
    public void removeTheme(boolean z, boolean z2) throws Exception {
        IThemeManagerService.Stub.asInterface(ServiceManager.getService("ThemeService")).removeTheme(z, z2);
    }

    @Override // com.ecarx.xui.adaptapi.theme.Theme
    public void removeThemeAndApply(boolean z, boolean z2) throws Exception {
        IThemeManagerService.Stub.asInterface(ServiceManager.getService("ThemeService")).removeThemeAndApply(z, z2);
    }

    @Override // com.ecarx.xui.adaptapi.theme.Theme
    public void applyInstalledTheme() throws Exception {
        IThemeManagerService.Stub.asInterface(ServiceManager.getService("ThemeService")).applyInstalledTheme();
    }

    @Override // com.ecarx.xui.adaptapi.theme.Theme
    public void applyInstalledThemeReboot() throws Exception {
        IThemeManagerService.Stub.asInterface(ServiceManager.getService("ThemeService")).applyInstalledThemeReboot();
    }

    private static void applyInstalledWallpaper() throws RemoteException {
        IThemeManagerService.Stub.asInterface(ServiceManager.getService("ThemeService")).applyInstalledWallpaper();
    }

    @Override // com.ecarx.xui.adaptapi.theme.Theme
    public void applyThemeIcons(String str) throws Exception {
        IThemeManagerService.Stub.asInterface(ServiceManager.getService("ThemeService")).applyThemeIcons(str);
    }

    @Override // com.ecarx.xui.adaptapi.theme.Theme
    public void applyThemeWallpaper(String str) throws Exception {
        IThemeManagerService.Stub.asInterface(ServiceManager.getService("ThemeService")).applyThemeWallpaper(str);
    }

    @Override // com.ecarx.xui.adaptapi.theme.Theme
    public void applyThemeSystemUI(String str) throws Exception {
        IThemeManagerService.Stub.asInterface(ServiceManager.getService("ThemeService")).applyThemeSystemUI(str);
    }

    @Override // com.ecarx.xui.adaptapi.theme.Theme
    public void applyThemeFramework(String str) throws Exception {
        IThemeManagerService.Stub.asInterface(ServiceManager.getService("ThemeService")).applyThemeFramework(str);
    }

    @Override // com.ecarx.xui.adaptapi.theme.Theme
    public void applyThemeRingtone(String str) throws Exception {
        IThemeManagerService.Stub.asInterface(ServiceManager.getService("ThemeService")).applyThemeRingtone(str);
    }

    @Override // com.ecarx.xui.adaptapi.theme.Theme
    public void applyThemeLockscreenWallpaper(String str) throws Exception {
        IThemeManagerService.Stub.asInterface(ServiceManager.getService("ThemeService")).applyThemeLockscreenWallpaper(str);
    }

    @Override // com.ecarx.xui.adaptapi.theme.Theme
    public void applyThemeBootanimation(String str, boolean z) throws Exception {
        IThemeManagerService.Stub.asInterface(ServiceManager.getService("ThemeService")).applyThemeBootanimation(str, z);
    }

    @Override // com.ecarx.xui.adaptapi.theme.Theme
    public void applyThemeMms(String str) throws Exception {
        IThemeManagerService.Stub.asInterface(ServiceManager.getService("ThemeService")).applyThemeMms(str);
    }

    @Override // com.ecarx.xui.adaptapi.theme.Theme
    public void applyThemeFont(String str) throws Exception {
        IThemeManagerService.Stub.asInterface(ServiceManager.getService("ThemeService")).applyThemeFont(str);
    }

    @Override // com.ecarx.xui.adaptapi.theme.Theme
    public void applyThemeFontReboot(String str) throws Exception {
        IThemeManagerService.Stub.asInterface(ServiceManager.getService("ThemeService")).applyThemeFontReboot(str);
    }

    @Override // com.ecarx.xui.adaptapi.theme.Theme
    public void applyThemeContacts(String str) throws Exception {
        IThemeManagerService.Stub.asInterface(ServiceManager.getService("ThemeService")).applyThemeContacts(str);
    }

    @Override // com.ecarx.xui.adaptapi.theme.Theme
    public void applyThemeDialer(String str) throws Exception {
        IThemeManagerService.Stub.asInterface(ServiceManager.getService("ThemeService")).applyThemeDialer(str);
    }

    @Override // com.ecarx.xui.adaptapi.theme.Theme
    public void applyThemeScreensaver(String str) throws Exception {
        IThemeManagerService.Stub.asInterface(ServiceManager.getService("ThemeService")).applyThemeScreensaver(str);
    }

    @Override // com.ecarx.xui.adaptapi.theme.Theme
    public void updateSystemUI() throws Exception {
        IThemeManagerService.Stub.asInterface(ServiceManager.getService("ThemeService")).updateSystemUI();
    }

    @Override // com.ecarx.xui.adaptapi.theme.Theme
    public void resetThemeIcons() throws Exception {
        IThemeManagerService.Stub.asInterface(ServiceManager.getService("ThemeService")).resetThemeIcons();
    }

    @Override // com.ecarx.xui.adaptapi.theme.Theme
    public void resetThemeWallpaper() throws Exception {
        IThemeManagerService.Stub.asInterface(ServiceManager.getService("ThemeService")).resetThemeWallpaper();
    }

    @Override // com.ecarx.xui.adaptapi.theme.Theme
    public void resetThemeSystemUI() throws Exception {
        IThemeManagerService.Stub.asInterface(ServiceManager.getService("ThemeService")).resetThemeSystemUI();
    }

    @Override // com.ecarx.xui.adaptapi.theme.Theme
    public void resetThemeFramework() throws Exception {
        IThemeManagerService.Stub.asInterface(ServiceManager.getService("ThemeService")).resetThemeFramework();
    }

    @Override // com.ecarx.xui.adaptapi.theme.Theme
    public void resetThemeRingtone() throws Exception {
        IThemeManagerService.Stub.asInterface(ServiceManager.getService("ThemeService")).resetThemeRingtone();
    }

    @Override // com.ecarx.xui.adaptapi.theme.Theme
    public void resetThemeLockscreenWallpaper() throws Exception {
        IThemeManagerService.Stub.asInterface(ServiceManager.getService("ThemeService")).resetThemeLockscreenWallpaper();
    }

    @Override // com.ecarx.xui.adaptapi.theme.Theme
    public void resetThemeScreensaverWallpaper() throws Exception {
        IThemeManagerService.Stub.asInterface(ServiceManager.getService("ThemeService")).resetThemeScreensaverWallpaper();
    }

    @Override // com.ecarx.xui.adaptapi.theme.Theme
    public void resetThemeBootanimation() throws Exception {
        IThemeManagerService.Stub.asInterface(ServiceManager.getService("ThemeService")).resetThemeBootanimation();
    }

    @Override // com.ecarx.xui.adaptapi.theme.Theme
    public void resetThemeMms() throws Exception {
        IThemeManagerService.Stub.asInterface(ServiceManager.getService("ThemeService")).resetThemeMms();
    }

    @Override // com.ecarx.xui.adaptapi.theme.Theme
    public void resetThemeFont() throws Exception {
        IThemeManagerService.Stub.asInterface(ServiceManager.getService("ThemeService")).resetThemeFont();
    }

    @Override // com.ecarx.xui.adaptapi.theme.Theme
    public void resetThemeFontReboot() throws Exception {
        IThemeManagerService.Stub.asInterface(ServiceManager.getService("ThemeService")).resetThemeFontReboot();
    }

    @Override // com.ecarx.xui.adaptapi.theme.Theme
    public void resetThemeContacts() throws Exception {
        IThemeManagerService.Stub.asInterface(ServiceManager.getService("ThemeService")).resetThemeContacts();
    }

    @Override // com.ecarx.xui.adaptapi.theme.Theme
    public void resetThemeDialer() throws Exception {
        IThemeManagerService.Stub.asInterface(ServiceManager.getService("ThemeService")).resetThemeDialer();
    }

    @Override // com.ecarx.xui.adaptapi.theme.Theme
    public void resetThemeScreensaver() throws Exception {
        IThemeManagerService.Stub.asInterface(ServiceManager.getService("ThemeService")).resetThemeScreensaver();
    }

    @Override // com.ecarx.xui.adaptapi.theme.Theme
    public void setWallpaperStream(InputStream... inputStreamArr) throws Exception {
        if (inputStreamArr.length > 0) {
            setStream(inputStreamArr[0], "default_wallpaper.jpg.tmp");
        }
        if (inputStreamArr.length > 1) {
            setStream(inputStreamArr[1], "default_wallpaper_dark.jpg.tmp");
        }
        try {
            Thread.sleep(inputStreamArr.length * 1000);
            applyInstalledWallpaper();
        } catch (RemoteException | InterruptedException unused) {
        }
    }

    @Override // com.ecarx.xui.adaptapi.theme.Theme
    public void setWallpaperBitmap(Bitmap... bitmapArr) throws Exception {
        if (bitmapArr.length > 0) {
            setBitmap(bitmapArr[0], "default_wallpaper.jpg.tmp");
        }
        if (bitmapArr.length > 1) {
            setBitmap(bitmapArr[1], "default_wallpaper_dark.jpg.tmp");
        }
        try {
            Thread.sleep(bitmapArr.length * 1000);
            applyInstalledWallpaper();
        } catch (RemoteException | InterruptedException unused) {
        }
    }

    @Override // com.ecarx.xui.adaptapi.theme.Theme
    public void setScreensaverWallpaperStream(InputStream... inputStreamArr) throws Exception {
        if (inputStreamArr.length > 0) {
            setStream(inputStreamArr[0], SCREENSAVER_WALLPAPER_IMAGE_NAME);
        }
        if (inputStreamArr.length > 1) {
            setStream(inputStreamArr[1], SCREENSAVER_WALLPAPER_DARK_IMAGE_NAME);
        }
        try {
            Thread.sleep(inputStreamArr.length * 1000);
        } catch (InterruptedException unused) {
        }
    }

    @Override // com.ecarx.xui.adaptapi.theme.Theme
    public void setScreensaverWallpaperBitmap(Bitmap... bitmapArr) throws Exception {
        if (bitmapArr.length > 0) {
            setBitmap(bitmapArr[0], SCREENSAVER_WALLPAPER_IMAGE_NAME);
        }
        if (bitmapArr.length > 1) {
            setBitmap(bitmapArr[1], SCREENSAVER_WALLPAPER_DARK_IMAGE_NAME);
        }
        try {
            Thread.sleep(bitmapArr.length * 1000);
        } catch (InterruptedException unused) {
        }
    }

    private static void setStream(InputStream inputStream, String str) throws Throwable {
        try {
            ParcelFileDescriptor wallpaper = IThemeManagerService.Stub.asInterface(ServiceManager.getService("ThemeService")).setWallpaper(str);
            if (wallpaper == null) {
                return;
            }
            ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream = null;
            try {
                ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream2 = new ParcelFileDescriptor.AutoCloseOutputStream(wallpaper);
                try {
                    setWallpaper(inputStream, autoCloseOutputStream2);
                    autoCloseOutputStream2.close();
                } catch (Throwable th) {
                    th = th;
                    autoCloseOutputStream = autoCloseOutputStream2;
                    if (autoCloseOutputStream != null) {
                        autoCloseOutputStream.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (RemoteException unused) {
        }
    }

    private static void setWallpaper(InputStream inputStream, FileOutputStream fileOutputStream) throws IOException {
        byte[] bArr = new byte[32768];
        while (true) {
            int i = inputStream.read(bArr);
            if (i <= 0) {
                return;
            } else {
                fileOutputStream.write(bArr, 0, i);
            }
        }
    }

    private static void setBitmap(Bitmap bitmap, String str) throws Throwable {
        ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream;
        try {
            ParcelFileDescriptor wallpaper = IThemeManagerService.Stub.asInterface(ServiceManager.getService("ThemeService")).setWallpaper(str);
            if (wallpaper == null) {
                return;
            }
            ParcelFileDescriptor.AutoCloseOutputStream autoCloseOutputStream2 = null;
            try {
                autoCloseOutputStream = new ParcelFileDescriptor.AutoCloseOutputStream(wallpaper);
            } catch (Throwable th) {
                th = th;
            }
            try {
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, autoCloseOutputStream);
                autoCloseOutputStream.close();
            } catch (Throwable th2) {
                th = th2;
                autoCloseOutputStream2 = autoCloseOutputStream;
                if (autoCloseOutputStream2 != null) {
                    autoCloseOutputStream2.close();
                }
                throw th;
            }
        } catch (RemoteException unused) {
        }
    }
}
