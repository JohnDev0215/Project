package com.byd.music;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import java.io.IOException;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;

/* JADX INFO: loaded from: classes.dex */
public class AudioPreview extends Activity implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private e f3894b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private TextView f3895c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private TextView f3896d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private TextView f3897e;
    private SeekBar f;
    private Handler g;
    private int j;
    private Uri k;
    private AudioManager m;
    private boolean n;
    private boolean o;
    private String q;
    private boolean h = false;
    private boolean i = true;
    private long l = -1;
    private Handler p = new Handler(Looper.getMainLooper());
    private final AudioManager.OnAudioFocusChangeListener r = new b();
    private SeekBar.OnSeekBarChangeListener s = new c();

    class a extends AsyncQueryHandler {
        a(ContentResolver contentResolver) {
            super(contentResolver);
        }

        @Override // android.content.AsyncQueryHandler
        protected void onQueryComplete(int i, Object obj, Cursor cursor) {
            if (cursor == null || !cursor.moveToFirst()) {
                Log.w("AudioPreview", "empty cursor");
            } else {
                int columnIndex = cursor.getColumnIndex("title");
                int columnIndex2 = cursor.getColumnIndex("artist");
                int columnIndex3 = cursor.getColumnIndex("_id");
                int columnIndex4 = cursor.getColumnIndex("_display_name");
                if (columnIndex3 >= 0) {
                    AudioPreview.this.l = cursor.getLong(columnIndex3);
                }
                if (columnIndex >= 0) {
                    AudioPreview.this.f3895c.setText(cursor.getString(columnIndex));
                    if (columnIndex2 >= 0) {
                        AudioPreview.this.f3896d.setText(cursor.getString(columnIndex2));
                    }
                } else if (columnIndex4 >= 0) {
                    AudioPreview.this.f3895c.setText(cursor.getString(columnIndex4));
                } else {
                    Log.w("AudioPreview", "Cursor had no names for us");
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            AudioPreview.this.s();
        }
    }

    class b implements AudioManager.OnAudioFocusChangeListener {
        b() {
        }

        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i) {
            if (AudioPreview.this.f3894b == null) {
                AudioPreview.this.m.abandonAudioFocus(this);
                return;
            }
            Log.d("AudioPreview", "focusChange = " + i);
            if (i == -3 || i == -2) {
                AudioPreview.this.o = false;
                if (AudioPreview.this.f3894b.isPlaying()) {
                    AudioPreview.this.n = true;
                    AudioPreview.this.f3894b.pause();
                }
            } else if (i == -1) {
                AudioPreview.this.o = false;
                AudioPreview.this.n = false;
                AudioPreview.this.f3894b.pause();
            } else if (i == 1) {
                AudioPreview.this.o = true;
                if (AudioPreview.this.n) {
                    AudioPreview.this.n = false;
                    AudioPreview.this.u();
                }
            }
            AudioPreview.this.w();
        }
    }

    class c implements SeekBar.OnSeekBarChangeListener {
        c() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (z && AudioPreview.this.f3894b != null) {
                AudioPreview.this.f3894b.seekTo(i);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            AudioPreview.this.h = true;
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            AudioPreview.this.h = false;
        }
    }

    class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (AudioPreview.this.f3894b == null || !AudioPreview.this.f3894b.isPlaying()) {
                org.greenrobot.eventbus.c.c().k(new com.byd.music.a(1));
                AudioPreview.this.finish();
            }
        }
    }

    class f implements Runnable {
        f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (AudioPreview.this.f3894b != null && !AudioPreview.this.h && AudioPreview.this.j != 0) {
                AudioPreview.this.f.setProgress(AudioPreview.this.f3894b.getCurrentPosition());
            }
            AudioPreview.this.g.removeCallbacksAndMessages(null);
            if (AudioPreview.this.i) {
                return;
            }
            AudioPreview.this.g.postDelayed(AudioPreview.this.new f(), 200L);
        }
    }

    private void q() {
        Handler handler = this.g;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        e eVar = this.f3894b;
        if (eVar != null) {
            eVar.reset();
            this.f3894b.release();
            this.f3894b = null;
            this.m.abandonAudioFocus(this.r);
        }
    }

    @SuppressLint({"NewApi"})
    private void r() {
        Log.d("AudioPreview", "requestAudioFocus");
        int iRequestAudioFocus = this.m.requestAudioFocus(new AudioFocusRequest.Builder(1).setAudioAttributes(new AudioAttributes.Builder().setUsage(1).build()).setAcceptsDelayedFocusGain(true).setOnAudioFocusChangeListener(this.r).build());
        Log.d("AudioPreview", "state = " + iRequestAudioFocus);
        this.o = iRequestAudioFocus == 1;
        if (iRequestAudioFocus == 0) {
            this.n = true;
            this.f3894b.pause();
        } else if (iRequestAudioFocus == 1) {
            this.n = false;
            u();
        }
    }

    private void t() {
        ((ProgressBar) findViewById(com.byd.music.c.f)).setVisibility(8);
        int duration = this.f3894b.getDuration();
        this.j = duration;
        if (duration != 0) {
            this.f.setMax(duration);
            this.f.setVisibility(0);
            if (!this.h) {
                this.f.setProgress(this.f3894b.getCurrentPosition());
            }
        }
        this.f.setOnSeekBarChangeListener(this.s);
        this.f3897e.setVisibility(8);
        findViewById(com.byd.music.c.g).setVisibility(0);
        r();
        Handler handler = this.g;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.g.postDelayed(new f(), 200L);
        }
        w();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        Log.d("AudioPreview", "start hasAudioFocus = " + this.o);
        if (!this.o) {
            r();
        } else {
            this.f3894b.start();
            this.g.postDelayed(new f(), 200L);
        }
    }

    private void v() {
        Handler handler = this.g;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        e eVar = this.f3894b;
        if (eVar != null) {
            eVar.release();
            this.f3894b = null;
            this.m.abandonAudioFocus(this.r);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        e eVar;
        ImageButton imageButton = (ImageButton) findViewById(com.byd.music.c.f3913d);
        if (imageButton == null || (eVar = this.f3894b) == null) {
            return;
        }
        if (eVar.isPlaying()) {
            imageButton.setImageResource(com.byd.music.b.f3908a);
        } else {
            imageButton.setImageResource(com.byd.music.b.f3909b);
            this.g.removeCallbacksAndMessages(null);
        }
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        this.f.setProgress(this.j);
        w();
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        org.greenrobot.eventbus.c.c().o(this);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        Uri data = intent.getData();
        this.k = data;
        if (data == null) {
            finish();
            return;
        }
        this.q = intent.getStringExtra("filePath");
        Log.i("AudioPreview", "filePath:" + this.q);
        String scheme = this.k.getScheme();
        setVolumeControlStream(3);
        requestWindowFeature(1);
        setContentView(com.byd.music.d.f3915a);
        this.f3895c = (TextView) findViewById(com.byd.music.c.f3910a);
        this.f3896d = (TextView) findViewById(com.byd.music.c.f3911b);
        this.f3897e = (TextView) findViewById(com.byd.music.c.f3912c);
        if (scheme.equals("http")) {
            this.f3897e.setText(getString(com.byd.music.e.f3916a, new Object[]{this.k.getHost()}));
        } else {
            this.f3897e.setVisibility(8);
        }
        this.f = (SeekBar) findViewById(com.byd.music.c.f3914e);
        this.g = new Handler();
        this.m = (AudioManager) getSystemService("audio");
        e eVar = (e) getLastNonConfigurationInstance();
        if (eVar == null) {
            e eVar2 = new e(null);
            this.f3894b = eVar2;
            eVar2.b(this);
            try {
                this.f3894b.c(this.k);
            } catch (Exception e2) {
                Log.d("AudioPreview", "Failed to open file: " + e2);
                org.greenrobot.eventbus.c.c().k(new com.byd.music.a(1));
                finish();
                return;
            }
        } else {
            this.f3894b = eVar;
            eVar.b(this);
        }
        a aVar = new a(getContentResolver());
        if (scheme.equals("content")) {
            if (this.k.getAuthority() == "media") {
                aVar.startQuery(0, null, this.k, new String[]{"title", "artist"}, null, null, null);
                return;
            } else {
                aVar.startQuery(0, null, this.k, null, null, null, null);
                return;
            }
        }
        if (scheme.equals("file")) {
            aVar.startQuery(0, null, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "title", "artist"}, "_data=?", new String[]{this.k.getPath()}, null);
        } else if (this.f3894b.a()) {
            s();
        }
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0, "open in music");
        return true;
    }

    @Override // android.app.Activity
    public void onDestroy() {
        Log.d("AudioPreview", "onDestroy");
        org.greenrobot.eventbus.c.c().q(this);
        Handler handler = this.p;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        q();
        super.onDestroy();
    }

    @l(threadMode = ThreadMode.MAIN)
    public void onDiskEvent(com.byd.music.f.b bVar) {
        boolean zD = bVar.d();
        String strC = bVar.c();
        Log.i("AudioPreview", "isEject:" + zD + ",storagePath:" + strC + ",mFilePath:" + this.q);
        if (!zD || TextUtils.isEmpty(strC) || TextUtils.isEmpty(this.q) || !this.q.startsWith(strC)) {
            return;
        }
        v();
        finish();
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        Log.d("AudioPreview", "onError what = " + i);
        if (i == -1010) {
            org.greenrobot.eventbus.c.c().k(new com.byd.music.a(1));
            finish();
        } else {
            this.p.postDelayed(new d(), 1000L);
        }
        return true;
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            if (i != 79) {
                if (i == 126) {
                    u();
                    w();
                    return true;
                }
                if (i == 127) {
                    if (this.f3894b.isPlaying()) {
                        this.f3894b.pause();
                    }
                    w();
                    return true;
                }
                switch (i) {
                    case 85:
                        break;
                    case 86:
                        break;
                    case 87:
                    case 88:
                    case 89:
                    case 90:
                        return true;
                    default:
                        return super.onKeyDown(i, keyEvent);
                }
            }
            if (this.f3894b.isPlaying()) {
                this.f3894b.pause();
            } else {
                u();
            }
            w();
            return true;
        }
        v();
        finish();
        return true;
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        this.i = true;
        Handler handler = this.g;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    @Override // android.app.Activity
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItemFindItem = menu.findItem(1);
        if (this.l >= 0) {
            menuItemFindItem.setVisible(true);
            return true;
        }
        menuItemFindItem.setVisible(false);
        return false;
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        Log.d("AudioPreview", "onPrepared");
        if (isFinishing()) {
            return;
        }
        this.f3894b = (e) mediaPlayer;
        s();
        this.f3894b.start();
        t();
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        this.i = false;
        if (this.f3894b.a()) {
            t();
        }
    }

    @Override // android.app.Activity
    public Object onRetainNonConfigurationInstance() {
        e eVar = this.f3894b;
        this.f3894b = null;
        return eVar;
    }

    @Override // android.app.Activity
    public void onUserLeaveHint() {
        v();
        finish();
        super.onUserLeaveHint();
    }

    public void playPauseClicked(View view) {
        e eVar = this.f3894b;
        if (eVar == null) {
            return;
        }
        if (eVar.isPlaying()) {
            this.f3894b.pause();
        } else {
            u();
        }
        w();
    }

    public void s() {
        if (TextUtils.isEmpty(this.f3895c.getText())) {
            this.f3895c.setText(this.k.getLastPathSegment());
        }
        if (TextUtils.isEmpty(this.f3896d.getText())) {
            this.f3896d.setVisibility(8);
        } else {
            this.f3896d.setVisibility(0);
        }
    }

    private static class e extends MediaPlayer implements MediaPlayer.OnPreparedListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        AudioPreview f3902b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f3903c;

        private e() {
            this.f3903c = false;
        }

        boolean a() {
            return this.f3903c;
        }

        public void b(AudioPreview audioPreview) {
            this.f3902b = audioPreview;
            setOnPreparedListener(this);
            setOnErrorListener(this.f3902b);
            setOnCompletionListener(this.f3902b);
        }

        public void c(Uri uri) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
            setDataSource(this.f3902b, uri);
            prepareAsync();
        }

        @Override // android.media.MediaPlayer.OnPreparedListener
        public void onPrepared(MediaPlayer mediaPlayer) {
            this.f3903c = true;
            this.f3902b.onPrepared(mediaPlayer);
        }

        @Override // android.media.MediaPlayer
        public void release() {
            super.release();
            setOnPreparedListener(null);
            this.f3902b = null;
        }

        /* synthetic */ e(a aVar) {
            this();
        }
    }
}
