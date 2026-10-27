package com.ecarx.xui.adaptapi.theme;

import android.content.Context;
import android.graphics.Bitmap;
import com.ecarx.xui.adaptapi.AdaptAPI;
import java.io.InputStream;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Theme extends AdaptAPI {
    public abstract void applyDefaultTheme() throws Exception;

    public abstract void applyInstalledTheme() throws Exception;

    public abstract void applyInstalledThemeReboot() throws Exception;

    public abstract void applyTheme(String str, List<String> list, boolean z, boolean z2, boolean z3, boolean z4) throws Exception;

    public abstract void applyThemeBootanimation(String str, boolean z) throws Exception;

    public abstract void applyThemeContacts(String str) throws Exception;

    public abstract void applyThemeDialer(String str) throws Exception;

    public abstract void applyThemeFont(String str) throws Exception;

    public abstract void applyThemeFontReboot(String str) throws Exception;

    public abstract void applyThemeFramework(String str) throws Exception;

    public abstract void applyThemeIcons(String str) throws Exception;

    public abstract void applyThemeLockscreenWallpaper(String str) throws Exception;

    public abstract void applyThemeMms(String str) throws Exception;

    public abstract void applyThemeRingtone(String str) throws Exception;

    public abstract void applyThemeScreensaver(String str) throws Exception;

    public abstract void applyThemeSystemUI(String str) throws Exception;

    public abstract void applyThemeWallpaper(String str) throws Exception;

    public abstract void removeTheme(boolean z, boolean z2) throws Exception;

    public abstract void removeThemeAndApply(boolean z, boolean z2) throws Exception;

    public abstract void resetThemeBootanimation() throws Exception;

    public abstract void resetThemeContacts() throws Exception;

    public abstract void resetThemeDialer() throws Exception;

    public abstract void resetThemeFont() throws Exception;

    public abstract void resetThemeFontReboot() throws Exception;

    public abstract void resetThemeFramework() throws Exception;

    public abstract void resetThemeIcons() throws Exception;

    public abstract void resetThemeLockscreenWallpaper() throws Exception;

    public abstract void resetThemeMms() throws Exception;

    public abstract void resetThemeRingtone() throws Exception;

    public abstract void resetThemeScreensaver() throws Exception;

    public abstract void resetThemeScreensaverWallpaper() throws Exception;

    public abstract void resetThemeSystemUI() throws Exception;

    public abstract void resetThemeWallpaper() throws Exception;

    public abstract void setScreensaverWallpaperBitmap(Bitmap... bitmapArr) throws Exception;

    public abstract void setScreensaverWallpaperStream(InputStream... inputStreamArr) throws Exception;

    public abstract void setWallpaperBitmap(Bitmap... bitmapArr) throws Exception;

    public abstract void setWallpaperStream(InputStream... inputStreamArr) throws Exception;

    public abstract void updateSystemUI() throws Exception;

    public static Theme create(Context context) {
        try {
            return (Theme) Class.forName("com.ecarx.xui.adaptapi.theme.impl.ThemeImpl").getConstructor(Context.class).newInstance(context);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
