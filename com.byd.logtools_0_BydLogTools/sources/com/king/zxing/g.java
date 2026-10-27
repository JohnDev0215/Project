package com.king.zxing;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.Log;
import java.io.Closeable;
import java.io.IOException;

/* JADX INFO: compiled from: BeepManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class g implements MediaPlayer.OnErrorListener, Closeable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f4598b = g.class.getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Activity f4599c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private MediaPlayer f4600d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f4601e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f4602f;

    g(Activity activity) {
        this.f4599c = activity;
        f();
    }

    @TargetApi(19)
    private MediaPlayer a(Context context) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            AssetFileDescriptor assetFileDescriptorOpenRawResourceFd = context.getResources().openRawResourceFd(r.f4654a);
            try {
                mediaPlayer.setDataSource(assetFileDescriptorOpenRawResourceFd.getFileDescriptor(), assetFileDescriptorOpenRawResourceFd.getStartOffset(), assetFileDescriptorOpenRawResourceFd.getLength());
                mediaPlayer.setOnErrorListener(this);
                mediaPlayer.setAudioStreamType(3);
                mediaPlayer.setLooping(false);
                mediaPlayer.setVolume(0.1f, 0.1f);
                mediaPlayer.prepare();
                assetFileDescriptorOpenRawResourceFd.close();
                return mediaPlayer;
            } finally {
            }
        } catch (IOException e2) {
            Log.w(f4598b, e2);
            mediaPlayer.release();
            return null;
        }
    }

    private static boolean e(SharedPreferences sharedPreferences, Context context) {
        boolean z = sharedPreferences.getBoolean("preferences_play_beep", false);
        if (!z || ((AudioManager) context.getSystemService("audio")).getRingerMode() == 2) {
            return z;
        }
        return false;
    }

    synchronized void b() {
        MediaPlayer mediaPlayer;
        if (this.f4601e && (mediaPlayer = this.f4600d) != null) {
            mediaPlayer.start();
        }
        if (this.f4602f) {
            ((Vibrator) this.f4599c.getSystemService("vibrator")).vibrate(200L);
        }
    }

    public void c(boolean z) {
        this.f4601e = z;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        MediaPlayer mediaPlayer = this.f4600d;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.f4600d = null;
        }
    }

    public void d(boolean z) {
        this.f4602f = z;
    }

    synchronized void f() {
        e(PreferenceManager.getDefaultSharedPreferences(this.f4599c), this.f4599c);
        if (this.f4601e && this.f4600d == null) {
            this.f4599c.setVolumeControlStream(3);
            this.f4600d = a(this.f4599c);
        }
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public synchronized boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        if (i == 100) {
            this.f4599c.finish();
        } else {
            close();
            f();
        }
        return true;
    }
}
