package com.byd.voicelibrary;

import android.content.ComponentName;
import android.content.ContentProviderClient;
import android.content.Context;
import android.database.ContentObserver;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemProperties;
import android.util.Log;
import com.android.dx.Version;
import com.byd.common.bean.WuwInfoBean;
import com.byd.mediacenter.api.media.MediaControl;
import java.lang.reflect.Method;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class VoiceControllerForCamera implements ICameraFunction, IAlbumFunction {
    private static final String a = "m_tag_opt_lib";
    private static volatile VoiceControllerForCamera b;
    private static final Uri c = Uri.parse("content://com.byd.auto_camera.fun");
    private static final Uri d = Uri.parse("content://com.byd.auto_photo");
    private static final Uri e = Uri.parse("content://com.byd.auto_photo.fse");
    private Context f;
    private FuncObserver g;
    private boolean h;
    private boolean i;
    private boolean j;
    private VoiceControllerOld k;
    private Uri l;
    private OnFunctionResultListener m;

    private class FuncObserver extends ContentObserver {
        public FuncObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            String path;
            String queryParameter;
            int i;
            if (VoiceControllerForCamera.this.m == null || (path = uri.getPath()) == null || (queryParameter = uri.getQueryParameter(WuwInfoBean.ReturnKey)) == null) {
                return;
            }
            try {
                i = Integer.parseInt(queryParameter);
                path.hashCode();
                path.hashCode();
                switch (path) {
                    case "/search":
                        VoiceControllerForCamera.this.m.onSearchResult(i);
                        break;
                    case "/switch_camera":
                        VoiceControllerForCamera.this.m.onSwitch(i);
                        break;
                    case "/capture":
                        VoiceControllerForCamera.this.m.onCaptureResult(i);
                        break;
                    case "/open_camera":
                        VoiceControllerForCamera.this.m.onCameraOpen(i);
                        break;
                    case "/open_album":
                        VoiceControllerForCamera.this.m.onAlbumOpenResult(i);
                        break;
                    case "/record":
                        VoiceControllerForCamera.this.m.onRecordResult(i);
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private VoiceControllerForCamera(Context context) {
        this.f = context.getApplicationContext();
        this.k = new VoiceControllerOld(context);
    }

    private int a(Uri uri, String str, Bundle bundle) throws Throwable {
        ContentProviderClient contentProviderClient = null;
        try {
            try {
                ContentProviderClient contentProviderClientAcquireUnstableContentProviderClient = this.f.getContentResolver().acquireUnstableContentProviderClient(uri);
                try {
                    Bundle bundleCall = Build.VERSION.SDK_INT >= 29 ? contentProviderClientAcquireUnstableContentProviderClient.call(uri.getAuthority(), str, null, bundle) : this.f.getContentResolver().call(uri, str, (String) null, bundle);
                    if (contentProviderClientAcquireUnstableContentProviderClient != null) {
                        contentProviderClientAcquireUnstableContentProviderClient.close();
                    }
                    if (bundleCall == null) {
                        return -1;
                    }
                    return bundleCall.getBoolean(MediaControl.PARAM_SUCCESS) ? 1 : 0;
                } catch (Exception e2) {
                    e = e2;
                    contentProviderClient = contentProviderClientAcquireUnstableContentProviderClient;
                    e.printStackTrace();
                    Log.d(a, "executeMethod " + str + " exception : " + e.getMessage());
                    if (contentProviderClient != null) {
                        contentProviderClient.close();
                    }
                    return -1;
                } catch (Throwable th) {
                    th = th;
                    contentProviderClient = contentProviderClientAcquireUnstableContentProviderClient;
                    if (contentProviderClient != null) {
                        contentProviderClient.close();
                    }
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private Bundle a() {
        Bundle bundle = new Bundle();
        bundle.putString("pkgName", this.f.getApplicationInfo().packageName);
        return bundle;
    }

    private boolean a(ComponentName componentName) {
        try {
            int componentEnabledSetting = this.f.getPackageManager().getComponentEnabledSetting(componentName);
            Log.d(a, componentName + " : " + componentEnabledSetting);
            return componentEnabledSetting != 2;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private boolean a(Uri uri) {
        try {
            this.f.getContentResolver().registerContentObserver(uri, true, this.g);
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            Log.d(a, "registerObserver exception : " + e2.getMessage());
            return false;
        }
    }

    private boolean a(Uri uri, String str) throws Throwable {
        ContentProviderClient contentProviderClientAcquireUnstableContentProviderClient;
        ContentProviderClient contentProviderClient = null;
        try {
            try {
                contentProviderClientAcquireUnstableContentProviderClient = this.f.getContentResolver().acquireUnstableContentProviderClient(uri);
                try {
                    Bundle bundleCall = Build.VERSION.SDK_INT >= 29 ? contentProviderClientAcquireUnstableContentProviderClient.call(uri.getAuthority(), str, null, a()) : this.f.getContentResolver().call(uri, str, (String) null, a());
                    if (bundleCall != null) {
                        boolean z = bundleCall.getBoolean("state");
                        if (contentProviderClientAcquireUnstableContentProviderClient != null) {
                            contentProviderClientAcquireUnstableContentProviderClient.close();
                        }
                        return z;
                    }
                    if (contentProviderClientAcquireUnstableContentProviderClient == null) {
                        return false;
                    }
                } catch (Exception e2) {
                    e = e2;
                    contentProviderClient = contentProviderClientAcquireUnstableContentProviderClient;
                    e.printStackTrace();
                    Log.d(a, "getState : " + str + " exception : " + e.getMessage());
                    if (contentProviderClient == null) {
                        return false;
                    }
                    contentProviderClientAcquireUnstableContentProviderClient = contentProviderClient;
                } catch (Throwable th) {
                    th = th;
                    contentProviderClient = contentProviderClientAcquireUnstableContentProviderClient;
                    if (contentProviderClient != null) {
                        contentProviderClient.close();
                    }
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
            }
            contentProviderClientAcquireUnstableContentProviderClient.close();
            return false;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private Uri b() {
        if (this.l == null) {
            this.l = this.j ? e : d;
        }
        return this.l;
    }

    private boolean c() {
        try {
            boolean zEquals = "ROW".equals(SystemProperties.get(BYDAutoSysPropertiesDevice.BUILD_REGION));
            String str = SystemProperties.get("vehicle.config.cam_sort");
            if (str != null && !str.isEmpty()) {
                return zEquals ? str.contains("ims") || str.contains("front") : str.contains("rear") || str.contains("ims") || str.contains("front");
            }
            try {
                Class<?> cls = Class.forName("android.hardware.BmmCameraInfo");
                Method declaredMethod = cls.getDeclaredMethod("getAvailableCameraTypeList", new Class[0]);
                declaredMethod.setAccessible(true);
                List<String> list = (List) declaredMethod.invoke(cls, new Object[0]);
                Log.d(a, "getAvailableCameraTypeList check!");
                if (list.size() > 0) {
                    for (String str2 : list) {
                        if (zEquals && str2.equals("ims")) {
                            return true;
                        }
                        if (!zEquals && (str2.equals("rear") || str2.equals("ims"))) {
                            return true;
                        }
                    }
                }
                return false;
            } catch (Exception e2) {
                e2.printStackTrace();
                Log.d(a, "getAvailableCameraTypeList exception : " + e2.getMessage());
                return true;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            return true;
        }
    }

    public static VoiceControllerForCamera getInstance(Context context) {
        synchronized (VoiceControllerForCamera.class) {
            if (b == null) {
                b = new VoiceControllerForCamera(context);
            }
        }
        return b;
    }

    @Override // com.byd.voicelibrary.IAlbumFunction
    public void closeAlbum() {
        Log.d(a, "closeAlbum in jar");
        if (!this.i || a(b(), "close_album", a()) == -1) {
            this.k.closeAlbum();
        }
    }

    @Override // com.byd.voicelibrary.ICameraFunction
    public void closeCameraApp() {
        Log.d(a, "closeCameraApp in jar");
        if (!this.h || a(c, "close_camera", a()) == -1) {
            this.k.closeCameraApp();
        }
    }

    public void init() {
        init(false);
    }

    public void init(boolean z) {
        this.j = z;
        Log.d(a, "VoiceControllerForCamera init");
        if (this.g == null) {
            this.g = new FuncObserver(new Handler(Looper.getMainLooper()));
        }
        this.h = a(c);
        boolean zA = a(b());
        this.i = zA;
        if (this.h || zA) {
            return;
        }
        this.g = null;
    }

    public boolean isAlbumEnable() {
        return a(new ComponentName("com.byd.auto_photo", "com.byd.auto_photo.MainActivity"));
    }

    public boolean isCameraEnable() {
        return a(new ComponentName("com.byd.auto_camera", "com.byd.auto_camera.MainActivity")) && c();
    }

    public boolean isRecording() {
        return !this.h ? this.k.isRecording() : a(c, "record_state");
    }

    @Override // com.byd.voicelibrary.IAlbumFunction
    public void openAlbum(int i) {
        Log.d(a, "openAlbum in jar");
        if (!this.i) {
            this.k.openAlbum(i);
            return;
        }
        Bundle bundleA = a();
        bundleA.putInt("index", i);
        if (a(b(), "open_album", bundleA) == -1) {
            this.k.openAlbum(i);
        }
    }

    @Override // com.byd.voicelibrary.ICameraFunction
    public void openCamera(int i) {
        Log.d(a, "openCamera in jar");
        if (!this.h) {
            this.k.openCamera(i);
            return;
        }
        Bundle bundleA = a();
        bundleA.putInt("opt", 0);
        bundleA.putInt("index", i);
        if (a(c, "open_camera", bundleA) == -1) {
            this.k.openCamera(i);
        }
    }

    public void release() {
        Log.d(a, "voicelibraryforvcamera release in jar");
        if (this.g != null) {
            this.f.getContentResolver().unregisterContentObserver(this.g);
            this.g = null;
        }
        b = null;
    }

    @Override // com.byd.voicelibrary.IAlbumFunction
    public void resizeVideoPlayer(boolean z) {
        Log.d(a, "resizeVideoPlayer in jar");
        if (!this.i) {
            this.k.resizeVideoPlayer(z);
            return;
        }
        Bundle bundleA = a();
        bundleA.putInt("opt", 1);
        bundleA.putBoolean("fullstate", z);
        if (a(b(), "resize_player", bundleA) == -1) {
            this.k.resizeVideoPlayer(z);
        }
    }

    @Override // com.byd.voicelibrary.IAlbumFunction
    public void search(String str, int i, int i2) {
        Log.d(a, "search in jar");
        if (!this.i) {
            this.k.search(str, i, i2);
            return;
        }
        Bundle bundleA = a();
        bundleA.putString("search_word", str);
        bundleA.putInt("search_source", i);
        bundleA.putInt("search_type", i2);
        if (a(b(), "search", bundleA) == -1) {
            this.k.search(str, i, i2);
        }
    }

    @Override // com.byd.voicelibrary.IAlbumFunction
    public void seekToPosition(int i, boolean z) {
        if (!this.i) {
            this.k.seekToPosition(i, z);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("opt", 2);
        bundle.putInt("time", i);
        bundle.putBoolean("baseCurrent", z);
        if (a(b(), "seek_to", bundle) == -1) {
            this.k.seekToPosition(i, z);
        }
    }

    public void setOnFunctionResultListener(OnFunctionResultListener onFunctionResultListener) {
        this.m = onFunctionResultListener;
    }

    @Override // com.byd.voicelibrary.ICameraFunction
    public void startRecord(int i, int i2, boolean z) {
        Log.d(a, "startRecord in jar");
        if (!this.h) {
            this.k.startRecord(i, i2, z);
            return;
        }
        if (z) {
            OnFunctionResultListener onFunctionResultListener = this.m;
            if (onFunctionResultListener != null) {
                onFunctionResultListener.onRecordResult(1);
                return;
            }
            return;
        }
        Bundle bundleA = a();
        bundleA.putInt("opt", 2);
        bundleA.putInt("facing", i);
        bundleA.putInt("duration", i2);
        if (a(c, "record", bundleA) == -1) {
            this.k.startRecord(i, i2, false);
        }
    }

    @Override // com.byd.voicelibrary.ICameraFunction
    public void stopRecord(boolean z) {
        Log.d(a, "stopRecord in jar");
        if (!this.h) {
            this.k.stopRecord(z);
            return;
        }
        Bundle bundleA = a();
        bundleA.putInt("opt", 7);
        if (a(c, "stop_record", bundleA) == -1) {
            this.k.stopRecord(z);
        }
    }

    @Override // com.byd.voicelibrary.ICameraFunction
    public void switchCamera(int i) {
        Log.d(a, "switchCamera in jar");
        if (!this.h) {
            this.k.switchCamera(i);
            return;
        }
        Bundle bundleA = a();
        bundleA.putInt("opt", 3);
        bundleA.putInt("facing", i);
        if (a(c, "switch_camera", bundleA) == -1) {
            this.k.switchCamera(i);
        }
    }

    @Override // com.byd.voicelibrary.ICameraFunction
    public void takePicture(int i, int i2, int i3, boolean z) {
        Log.d(a, "takePicture in jar");
        if (!this.h) {
            this.k.takePicture(i, i2, i3, z);
            return;
        }
        Bundle bundleA = a();
        bundleA.putInt("opt", 1);
        bundleA.putInt("facing", i);
        bundleA.putInt("delay", i2);
        bundleA.putInt("counts", i3);
        if (a(c, "capture", bundleA) == -1) {
            this.k.takePicture(i, i2, i3, z);
        }
    }

    public String version() {
        return Version.VERSION;
    }
}
