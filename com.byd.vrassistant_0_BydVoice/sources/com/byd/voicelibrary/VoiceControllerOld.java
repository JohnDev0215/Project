package com.byd.voicelibrary;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.ts.avm.sdk.impl.AvmManagerImpl;

/* JADX INFO: loaded from: classes.dex */
class VoiceControllerOld implements ICameraFunction, IAlbumFunction {
    private static final String a = "m_tag_lib_0";
    private static final Uri b = Uri.parse("content://com.byd.auto_camera.fun/state");
    private Context c;

    VoiceControllerOld(Context context) {
        this.c = context.getApplicationContext();
    }

    private void a(Bundle bundle) {
        Intent intent = new Intent("com.byd.ACTION_VIDEO_CONTROL");
        bundle.putString("pkgName", this.c.getPackageName());
        intent.putExtras(bundle);
        this.c.sendBroadcast(intent);
    }

    private void b(Bundle bundle) {
        Intent intent = new Intent();
        intent.setClassName("com.byd.auto_photo", "com.byd.auto_photo.MainActivity");
        intent.setFlags(AvmManagerImpl.AVM_CALIBRATION_CODE);
        intent.addFlags(268435456);
        bundle.putString("pkgName", this.c.getPackageName());
        intent.putExtras(bundle);
        Log.e(a, "openAlbumActivity in jar");
        this.c.startActivity(intent);
    }

    private void c(Bundle bundle) {
        Intent intent = new Intent();
        intent.setClassName("com.byd.auto_camera", "com.byd.auto_camera.MainActivity");
        intent.setFlags(AvmManagerImpl.AVM_CALIBRATION_CODE);
        intent.addFlags(268435456);
        bundle.putString("pkgName", this.c.getPackageName());
        intent.putExtras(bundle);
        Log.e(a, "toCameraMainActivity in jar");
        this.c.startActivity(intent);
    }

    @Override // com.byd.voicelibrary.IAlbumFunction
    public void closeAlbum() {
        Log.e(a, "closeAlbum old fun");
        Intent intent = new Intent();
        intent.putExtra("close_album", true);
        intent.putExtra("pkgName", this.c.getPackageName());
        try {
            intent.setClassName("com.byd.auto_photo", "com.byd.auto_photo.StartServiceActivity");
            intent.addFlags(268435456);
            this.c.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(a, "closeAlbum exception : " + e.getMessage());
        }
    }

    @Override // com.byd.voicelibrary.ICameraFunction
    public void closeCameraApp() {
        Intent intent = new Intent();
        intent.putExtra("close_camera", true);
        intent.putExtra("pkgName", this.c.getPackageName());
        try {
            intent.setClassName("com.byd.auto_camera", "com.byd.auto_camera.SDKLaunchActivity");
            intent.addFlags(268435456);
            this.c.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isRecording() {
        try {
            Cursor cursorQuery = this.c.getContentResolver().query(b, null, "name=?", new String[]{"record"}, null);
            if (cursorQuery != null) {
                z = cursorQuery.getCount() > 0;
                Log.e(a, "==>" + cursorQuery.getCount());
                cursorQuery.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(a, "please update camera to 20220208 or the latest version");
        }
        return z;
    }

    @Override // com.byd.voicelibrary.IAlbumFunction
    public void openAlbum(int i) {
        Log.e(a, "openAlbum in jar old func");
        Bundle bundle = new Bundle();
        bundle.putInt("index", i);
        b(bundle);
    }

    @Override // com.byd.voicelibrary.ICameraFunction
    public void openCamera(int i) {
        Log.e(a, "start camera activity");
        Bundle bundle = new Bundle();
        bundle.putInt("opt", 0);
        bundle.putInt("index", i);
        c(bundle);
    }

    @Override // com.byd.voicelibrary.IAlbumFunction
    public void resizeVideoPlayer(boolean z) {
        Log.e(a, "resizeVideoPlayer old fun");
        Bundle bundle = new Bundle();
        bundle.putInt("opt", 1);
        bundle.putBoolean("fullstate", z);
        a(bundle);
    }

    @Override // com.byd.voicelibrary.IAlbumFunction
    public void search(String str, int i, int i2) {
        Log.e(a, "search in jar old func");
        Bundle bundle = new Bundle();
        bundle.putString("search_word", str);
        bundle.putInt("search_source", i);
        bundle.putInt("search_type", i2);
        b(bundle);
    }

    @Override // com.byd.voicelibrary.IAlbumFunction
    public void seekToPosition(int i, boolean z) {
        Log.e(a, "seekToPosition old fun");
        Bundle bundle = new Bundle();
        bundle.putInt("opt", 2);
        bundle.putInt("time", i);
        bundle.putBoolean("baseCurrent", z);
        a(bundle);
    }

    @Override // com.byd.voicelibrary.ICameraFunction
    public void startRecord(int i, int i2, boolean z) {
        Log.e(a, "startRecord -> camera Activity");
        if (z) {
            Log.e(a, "startRecord in jar , not support backgroundRecord");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("opt", 2);
        bundle.putInt("facing", i);
        bundle.putInt("duration", i2);
        c(bundle);
    }

    @Override // com.byd.voicelibrary.ICameraFunction
    public void stopRecord(boolean z) {
        Log.e(a, "stopRecord -> camera Activity");
        Bundle bundle = new Bundle();
        bundle.putInt("opt", 7);
        c(bundle);
    }

    @Override // com.byd.voicelibrary.ICameraFunction
    public void switchCamera(int i) {
        Log.e(a, "switchCamera old func");
        Bundle bundle = new Bundle();
        bundle.putInt("opt", 3);
        bundle.putInt("facing", i);
        c(bundle);
    }

    @Override // com.byd.voicelibrary.ICameraFunction
    public void takePicture(int i, int i2, int i3, boolean z) {
        Log.e(a, "takePicture -> start camera activity");
        Bundle bundle = new Bundle();
        bundle.putInt("opt", 1);
        bundle.putInt("facing", i);
        bundle.putInt("delay", i2);
        bundle.putInt("counts", i3);
        c(bundle);
    }
}
