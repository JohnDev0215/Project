package com.byd.card.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.VectorDrawable;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.text.TextUtils;
import android.util.Log;
import android.view.SurfaceControl;
import android.view.WindowManager;
import androidx.appcompat.content.res.AppCompatResources;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.statusbar.policy.CarController;
import com.android.systemui.util.StyleUtil;
import com.byd.card.bean.Constant;
import com.byd.card.drag.LruCacheBitmap;
import com.bydux.api.common.views.skin.loader.SkinManager;
import com.google.common.base.Ascii;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class BitmapUtils {
    public static final String CARPLAY_CONNECTED = "carplay_connected";
    public static final String CARPLAY_DISCONNECTED = "carplay_disconnected";
    private static final String TAG = "BitmapUtils";
    private static Bitmap mBlurAddCardActivityBg;
    private static Bitmap mBlurMoreActivityBg;

    public static Drawable getIconDrawable(Drawable drawable) {
        try {
            if (!(drawable instanceof AdaptiveIconDrawable)) {
                return drawable;
            }
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return new BitmapDrawable(bitmapCreateBitmap);
        } catch (Exception unused) {
            return null;
        }
    }

    public static byte[] img2byte(Drawable drawable) {
        Bitmap bitmapCreateBitmap;
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof VectorDrawable) {
            bitmapCreateBitmap = decodeBitmapFromResource(drawable);
        } else if (drawable instanceof BitmapDrawable) {
            bitmapCreateBitmap = ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable.getIntrinsicWidth() > 0 && drawable.getIntrinsicHeight() > 0) {
            Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap2);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            bitmapCreateBitmap = bitmapCreateBitmap2;
        } else {
            bitmapCreateBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        }
        if (bitmapCreateBitmap == null) {
            return new byte[0];
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapCreateBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        try {
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArray;
    }

    public static byte[] img2byte(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        try {
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArray;
    }

    public static Drawable bytes2Drawable(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        return new BitmapDrawable(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
    }

    public static Bitmap decodeBitmapFromResource(Drawable drawable) {
        if (drawable.getIntrinsicWidth() > 0 && drawable.getIntrinsicHeight() > 0) {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmapCreateBitmap;
        }
        return Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
    }

    public static Bitmap changedBitmap(Context context, Drawable drawable, int dimenRes) {
        if (drawable == null) {
            return null;
        }
        if (drawable.getIntrinsicWidth() > 0 && drawable.getIntrinsicHeight() > 0) {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            int width = bitmapCreateBitmap.getWidth();
            int height = bitmapCreateBitmap.getHeight();
            float dimension = context.getResources().getDimension(dimenRes);
            Matrix matrix = new Matrix();
            matrix.postScale(dimension / width, dimension / height);
            Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(bitmapCreateBitmap, 0, 0, width, height, matrix, true);
            Canvas canvas = new Canvas(bitmapCreateBitmap2);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            bitmapCreateBitmap.recycle();
            return bitmapCreateBitmap2;
        }
        return Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
    }

    public static Bitmap changedBitmap(Context context, Drawable drawable) {
        return changedBitmap(context, drawable, R.dimen.dp_72);
    }

    public static Bitmap changedBitmap(Context context, Drawable drawable, String pkg) {
        if (drawable == null || pkg == null) {
            return null;
        }
        Bitmap dataFromCache = LruCacheBitmap.getInstance().getDataFromCache(pkg);
        if (dataFromCache != null) {
            return dataFromCache;
        }
        if (drawable.getIntrinsicWidth() > 0 && drawable.getIntrinsicHeight() > 0) {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            int width = bitmapCreateBitmap.getWidth();
            int height = bitmapCreateBitmap.getHeight();
            float dimension = context.getResources().getDimension(R.dimen.dp_72);
            Matrix matrix = new Matrix();
            matrix.postScale(dimension / width, dimension / height);
            Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(bitmapCreateBitmap, 0, 0, width, height, matrix, true);
            Canvas canvas = new Canvas(bitmapCreateBitmap2);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            bitmapCreateBitmap.recycle();
            LruCacheBitmap.getInstance().putDataToCache(pkg, bitmapCreateBitmap2);
            return bitmapCreateBitmap2;
        }
        return Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
    }

    public static Bitmap getScreenshot(Context context, int left, int top, int marginBottom, int width, int height) {
        int rotation = ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRotation();
        int i = context.getResources().getDisplayMetrics().widthPixels;
        int i2 = context.getResources().getDisplayMetrics().heightPixels;
        DensityUtil.getStatusBarHeight(context);
        return getBlurImage(context, SurfaceControl.screenshot(new Rect(left, top, left + width, top + height), width, height, rotation), 0.9f, 25.0f);
    }

    public static Bitmap getScreenshot1(Context context, Rect rect, int width, int height) {
        return getBlurImage(context, SurfaceControl.screenshot(rect, width, height, ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRotation()), 0.9f, 25.0f);
    }

    public static Bitmap getBlurImage(Context context, Bitmap bitmap, float ambiguity, float radius) {
        if (bitmap == null) {
            return null;
        }
        Bitmap bitmapCopy = bitmap.copy(Bitmap.Config.ARGB_8888, false);
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmapCopy, Math.round(bitmapCopy.getWidth() * ambiguity), Math.round(bitmapCopy.getHeight() * ambiguity), false);
        bitmapCopy.recycle();
        RenderScript renderScriptCreate = RenderScript.create(context);
        Allocation allocationCreateFromBitmap = Allocation.createFromBitmap(renderScriptCreate, bitmapCreateScaledBitmap);
        Allocation allocationCreateTyped = Allocation.createTyped(renderScriptCreate, allocationCreateFromBitmap.getType());
        ScriptIntrinsicBlur scriptIntrinsicBlurCreate = ScriptIntrinsicBlur.create(renderScriptCreate, Element.U8_4(renderScriptCreate));
        scriptIntrinsicBlurCreate.setInput(allocationCreateFromBitmap);
        scriptIntrinsicBlurCreate.setRadius(radius);
        scriptIntrinsicBlurCreate.forEach(allocationCreateTyped);
        allocationCreateTyped.copyTo(bitmapCreateScaledBitmap);
        allocationCreateTyped.destroy();
        scriptIntrinsicBlurCreate.destroy();
        allocationCreateFromBitmap.destroy();
        renderScriptCreate.destroy();
        return bitmapCreateScaledBitmap;
    }

    public static Bitmap drawableToBitmap(Context mContext, int drawableInt) {
        Bitmap bitmapCreateBitmap;
        Drawable drawable = AppCompatResources.getDrawable(mContext, drawableInt);
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        } else if (drawable instanceof GradientDrawable) {
            GradientDrawable gradientDrawable = (GradientDrawable) drawable;
            Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(300, 450, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap2);
            gradientDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            gradientDrawable.draw(canvas);
            return bitmapCreateBitmap2;
        }
        if (drawable == null) {
            return null;
        }
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmapCreateBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        } else {
            bitmapCreateBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }
        Canvas canvas2 = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, canvas2.getWidth(), canvas2.getHeight());
        drawable.draw(canvas2);
        return bitmapCreateBitmap;
    }

    public static Bitmap compressBitmapForSampleSize(Context context, int resId) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = 2;
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        return BitmapFactory.decodeResource(context.getResources(), resId, options);
    }

    public static Bitmap compressBitmapForAppIcon(Context context, Drawable drawable) {
        return compressBitmapForQuality(drawable, context.getResources().getDimensionPixelOffset(R.dimen.dp_72), context.getResources().getDimensionPixelOffset(R.dimen.dp_72));
    }

    public static Bitmap compressBitmapForQuality(Drawable drawable, int width, int height) {
        Bitmap bitmapCreateBitmap;
        if (drawable instanceof BitmapDrawable) {
            bitmapCreateBitmap = ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof GradientDrawable) {
            GradientDrawable gradientDrawable = (GradientDrawable) drawable;
            Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap2);
            gradientDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            gradientDrawable.draw(canvas);
            bitmapCreateBitmap = bitmapCreateBitmap2;
        } else if (drawable == null) {
            bitmapCreateBitmap = null;
        } else if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmapCreateBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        } else {
            bitmapCreateBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }
        if (bitmapCreateBitmap == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapCreateBitmap.compress(Bitmap.CompressFormat.WEBP, 10, byteArrayOutputStream);
        return BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), null, null);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static Drawable adapterMultiTheme(String packageName, byte[] icons) {
        if (TextUtils.isEmpty(packageName)) {
            Log.w(TAG, "adapterMultiTheme packageName is null");
            return null;
        }
        packageName.hashCode();
        byte b = -1;
        switch (packageName.hashCode()) {
            case -2050414413:
                if (packageName.equals(Constant.PackageName.MY_CAR_PKG_TS)) {
                    b = 0;
                }
                break;
            case -1854654062:
                if (packageName.equals("com.byd.appstartmanagement")) {
                    b = 1;
                }
                break;
            case -1722241341:
                if (packageName.equals("com.byd.filemanager")) {
                    b = 2;
                }
                break;
            case -1701008145:
                if (packageName.equals("com.ts.androidauto.app")) {
                    b = 3;
                }
                break;
            case -1563384354:
                if (packageName.equals("com.byd.hvac")) {
                    b = 4;
                }
                break;
            case -1467358375:
                if (packageName.equals("com.ts.carplay.app")) {
                    b = 5;
                }
                break;
            case -1374912664:
                if (packageName.equals("com.byd.vrassistant.xf")) {
                    b = 6;
                }
                break;
            case -1215565766:
                if (packageName.equals("com.byd.mycar")) {
                    b = 7;
                }
                break;
            case -639852535:
                if (packageName.equals("com.byd.carsettings")) {
                    b = 8;
                }
                break;
            case -211654713:
                if (packageName.equals("com.byd.auto_camera")) {
                    b = 9;
                }
                break;
            case -158582775:
                if (packageName.equals("com.byd.auto_emanual")) {
                    b = 10;
                }
                break;
            case 27393645:
                if (packageName.equals(Constant.PackageName.OVER_SEA_APP_STORE)) {
                    b = Ascii.VT;
                }
                break;
            case 432180365:
                if (packageName.equals("com.byd.vrsettings")) {
                    b = Ascii.FF;
                }
                break;
            case 444238699:
                if (packageName.equals("com.byd.trafficmonitor")) {
                    b = Ascii.CR;
                }
                break;
            case 559578448:
                if (packageName.equals("com.byd.auto_photo")) {
                    b = Ascii.SO;
                }
                break;
            case 724988608:
                if (packageName.equals(Constant.PackageName.ENGINEERMODE_PKG)) {
                    b = Ascii.SI;
                }
                break;
            case 1179636476:
                if (packageName.equals("com.byd.automultipletheme")) {
                    b = Ascii.DLE;
                }
                break;
            case 1351217566:
                if (packageName.equals("com.byd.bluetoothcall")) {
                    b = 17;
                }
                break;
            case 1435822868:
                if (packageName.equals("com.amazon.mp3.automotiveOS")) {
                    b = Ascii.DC2;
                }
                break;
            case 1612129514:
                if (packageName.equals("com.byd.avm")) {
                    b = 19;
                }
                break;
            case 1612132402:
                if (packageName.equals("com.byd.dvr")) {
                    b = Ascii.DC4;
                }
                break;
            case 1919366984:
                if (packageName.equals("com.byd.localmusic")) {
                    b = Ascii.NAK;
                }
                break;
            case 1955222069:
                if (packageName.equals("com.byd.smarttravel")) {
                    b = Ascii.SYN;
                }
                break;
        }
        switch (b) {
            case 0:
            case 7:
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_mycar);
            case 1:
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_appstartmanagement);
            case 2:
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_filemanager);
            case 3:
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_android_auto);
            case 4:
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_airconditioning);
            case 5:
                if (((CarController) Dependency.get(CarController.class)).getCarPlayConnectionStatus()) {
                    return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_carpaly);
                }
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_carpaly_unable);
            case 6:
            case 12:
                if (StyleUtil.isDenza()) {
                    return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_autovoice_deza);
                }
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_autovoice);
            case 8:
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_carsettings);
            case 9:
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_auto_camera);
            case 10:
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_auto_emanual);
            case 11:
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_appstore);
            case 13:
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_trafficmonitor);
            case 14:
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_auto_photo);
            case 15:
                return null;
            case 16:
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_multipletheme);
            case 17:
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_bluetoothcall);
            case 18:
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_amazon_music);
            case 19:
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_avc);
            case 20:
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_dvr);
            case 21:
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_mediacenter);
            case 22:
                return SkinManager.getInstance().getMipmapDrawable(R.mipmap.com_byd_smarttravel);
            default:
                return bytes2Drawable(icons);
        }
    }

    public static Bitmap scaleBitmap(Bitmap bm, int newWidth, int newHeight) {
        if (bm == null) {
            return null;
        }
        int width = bm.getWidth();
        int height = bm.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(newWidth / width, newHeight / height);
        return Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
    }

    public static void writeBitmapToFile(String filePath, Bitmap b, int quality) {
        Log.d(TAG, "writeBitmapToFile filePath = " + filePath);
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
            b.compress(Bitmap.CompressFormat.JPEG, quality, bufferedOutputStream);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setMoreAppBlurBitmap(Bitmap bitmap) {
        mBlurMoreActivityBg = bitmap;
    }

    public static Bitmap getMoreAppBlurBitmap() {
        return mBlurMoreActivityBg;
    }

    public static void setAddAppBlurBitmap(Bitmap bitmap) {
        mBlurAddCardActivityBg = bitmap;
    }

    public static Bitmap getAddAppBlurBitmap() {
        return mBlurAddCardActivityBg;
    }
}
