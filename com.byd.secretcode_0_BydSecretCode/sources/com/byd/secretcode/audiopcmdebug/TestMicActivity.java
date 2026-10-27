package com.byd.secretcode.audiopcmdebug;

import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.byd.secretcode.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.window.BydActivity;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public class TestMicActivity extends BydActivity implements View.OnClickListener {
    public Button b;
    public Button c;
    public Button d;
    public Button e;
    public Button f;
    public AudioRecord g;
    public int h;
    public File i;
    public AudioFocusRequest l;
    public AudioAttributes m;
    public String n;
    public AudioTrack q;
    public int r;
    public byte[] s;
    public File t;
    public FileInputStream u;
    public boolean j = false;
    public AudioManager k = null;
    public Comparator<File> o = new b();
    public final Object p = new Object();
    public AudioManager.OnAudioFocusChangeListener v = new e();

    public class a implements FileFilter {
        public a() {
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            return file.isFile();
        }
    }

    public class b implements Comparator<File> {
        public b() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(File file, File file2) {
            if (file.lastModified() < file2.lastModified()) {
                return 1;
            }
            return file.lastModified() == file2.lastModified() ? 0 : -1;
        }
    }

    public class c implements Runnable {
        public final /* synthetic */ byte[] b;

        public c(byte[] bArr) {
            this.b = bArr;
        }

        /* JADX WARN: Removed duplicated region for block: B:31:0x0063 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() throws java.lang.Throwable {
            /*
                r6 = this;
                r0 = 0
                java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L49 java.io.IOException -> L4e
                com.byd.secretcode.audiopcmdebug.TestMicActivity r2 = com.byd.secretcode.audiopcmdebug.TestMicActivity.this     // Catch: java.lang.Throwable -> L49 java.io.IOException -> L4e
                java.io.File r2 = com.byd.secretcode.audiopcmdebug.TestMicActivity.g(r2)     // Catch: java.lang.Throwable -> L49 java.io.IOException -> L4e
                r1.<init>(r2)     // Catch: java.lang.Throwable -> L49 java.io.IOException -> L4e
            Lc:
                com.byd.secretcode.audiopcmdebug.TestMicActivity r0 = com.byd.secretcode.audiopcmdebug.TestMicActivity.this     // Catch: java.io.IOException -> L47 java.lang.Throwable -> L60
                boolean r0 = com.byd.secretcode.audiopcmdebug.TestMicActivity.h(r0)     // Catch: java.io.IOException -> L47 java.lang.Throwable -> L60
                if (r0 == 0) goto L43
                com.byd.secretcode.audiopcmdebug.TestMicActivity r0 = com.byd.secretcode.audiopcmdebug.TestMicActivity.this     // Catch: java.io.IOException -> L47 java.lang.Throwable -> L60
                android.media.AudioRecord r0 = com.byd.secretcode.audiopcmdebug.TestMicActivity.j(r0)     // Catch: java.io.IOException -> L47 java.lang.Throwable -> L60
                byte[] r2 = r6.b     // Catch: java.io.IOException -> L47 java.lang.Throwable -> L60
                r3 = 0
                com.byd.secretcode.audiopcmdebug.TestMicActivity r4 = com.byd.secretcode.audiopcmdebug.TestMicActivity.this     // Catch: java.io.IOException -> L47 java.lang.Throwable -> L60
                int r4 = com.byd.secretcode.audiopcmdebug.TestMicActivity.i(r4)     // Catch: java.io.IOException -> L47 java.lang.Throwable -> L60
                int r0 = r0.read(r2, r3, r4)     // Catch: java.io.IOException -> L47 java.lang.Throwable -> L60
                java.lang.String r2 = "MicTestActivity"
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L47 java.lang.Throwable -> L60
                r3.<init>()     // Catch: java.io.IOException -> L47 java.lang.Throwable -> L60
                java.lang.String r4 = "run: readStatus="
                r3.append(r4)     // Catch: java.io.IOException -> L47 java.lang.Throwable -> L60
                r3.append(r0)     // Catch: java.io.IOException -> L47 java.lang.Throwable -> L60
                java.lang.String r0 = r3.toString()     // Catch: java.io.IOException -> L47 java.lang.Throwable -> L60
                com.bydux.api.common.tools.log.BydLogHelper.logD(r2, r0)     // Catch: java.io.IOException -> L47 java.lang.Throwable -> L60
                byte[] r0 = r6.b     // Catch: java.io.IOException -> L47 java.lang.Throwable -> L60
                r1.write(r0)     // Catch: java.io.IOException -> L47 java.lang.Throwable -> L60
                goto Lc
            L43:
                r1.close()     // Catch: java.io.IOException -> L5b
                goto L5f
            L47:
                r0 = move-exception
                goto L52
            L49:
                r1 = move-exception
                r5 = r1
                r1 = r0
                r0 = r5
                goto L61
            L4e:
                r1 = move-exception
                r5 = r1
                r1 = r0
                r0 = r5
            L52:
                r0.printStackTrace()     // Catch: java.lang.Throwable -> L60
                if (r1 == 0) goto L5f
                r1.close()     // Catch: java.io.IOException -> L5b
                goto L5f
            L5b:
                r0 = move-exception
                r0.printStackTrace()
            L5f:
                return
            L60:
                r0 = move-exception
            L61:
                if (r1 == 0) goto L6b
                r1.close()     // Catch: java.io.IOException -> L67
                goto L6b
            L67:
                r1 = move-exception
                r1.printStackTrace()
            L6b:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.byd.secretcode.audiopcmdebug.TestMicActivity.c.run():void");
        }
    }

    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            TestMicActivity.this.q.play();
            while (true) {
                try {
                    TestMicActivity testMicActivity = TestMicActivity.this;
                    int i = testMicActivity.u.read(testMicActivity.s);
                    if (i == -1) {
                        break;
                    }
                    try {
                        TestMicActivity testMicActivity2 = TestMicActivity.this;
                        testMicActivity2.q.write(testMicActivity2.s, 0, i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            TestMicActivity.this.q.stop();
            TestMicActivity testMicActivity3 = TestMicActivity.this;
            testMicActivity3.q = null;
            try {
                testMicActivity3.u.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
    }

    public class e implements AudioManager.OnAudioFocusChangeListener {
        public e() {
        }

        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i) {
            BydLogHelper.logD("MicTestActivity", "requestAudioFocus = " + i);
            if (i != -2 && i == -1) {
            }
        }
    }

    public final void f() {
        BydLogHelper.logD("MicTestActivity", "Release HFP AudioFocus +++");
        if (this.k == null || this.l == null) {
            return;
        }
        BydLogHelper.logD("MicTestActivity", "Release HFP AudioFocus abandonAudioFocusRequest");
        this.k.abandonAudioFocusRequest(this.l);
    }

    public final void k() {
        int minBufferSize = AudioRecord.getMinBufferSize(44100, 16, 2);
        this.h = minBufferSize;
        AudioRecord audioRecord = new AudioRecord(1, 44100, 16, 2, minBufferSize);
        this.g = audioRecord;
        int state = audioRecord.getState();
        BydLogHelper.logD("MicTestActivity", "createAudioRecord: state=" + state + " bufferSize=" + this.h);
        if (1 != state) {
            new Exception("AudioRecord无法初始化，请检查录制权限或者是否其他app没有释放录音器");
        }
    }

    public void l(File file) {
        if (file != null) {
            try {
                if (!file.isDirectory()) {
                    file.delete();
                    return;
                }
                File[] fileArrListFiles = file.listFiles();
                if (fileArrListFiles != null) {
                    for (File file2 : fileArrListFiles) {
                        file2.delete();
                    }
                }
                file.delete();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public final ArrayList<File> m(File file) {
        ArrayList<File> arrayList = new ArrayList<>();
        File[] fileArrListFiles = file.listFiles(new a());
        if (fileArrListFiles == null) {
            Log.e("MicTestActivity", "getComparatorFileList: There is no log at " + file.getAbsolutePath());
            return arrayList;
        }
        for (File file2 : fileArrListFiles) {
            if (file2.getName().contains(".pcm")) {
                Log.e("MicTestActivity", "getComparatorFileList: add file to list =  " + file2.getName());
                arrayList.add(file2);
            }
        }
        if (arrayList.size() > 1) {
            Collections.sort(arrayList, this.o);
        }
        return arrayList;
    }

    public void n(String str, String str2) {
        try {
            this.t = new File(str, str2);
            this.u = new FileInputStream(this.t);
            int minBufferSize = AudioTrack.getMinBufferSize(16000, 4, 2);
            this.r = minBufferSize;
            this.q = new AudioTrack(3, 16000, 4, 2, minBufferSize, 1);
            this.s = new byte[this.r];
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public final void o() {
        File file = new File(this.n);
        if (!file.exists()) {
            file.mkdir();
        }
        ArrayList<File> arrayListM = m(new File(this.n));
        if (arrayListM.size() >= 1) {
            File file2 = arrayListM.get(0);
            BydLogHelper.logD("MicTestActivity", "initPCMFile: tempFile=" + file2.getAbsolutePath());
            if (file2.getName().length() == 8) {
                int i = Integer.parseInt(file2.getName().substring(3, 4)) + 1;
                int i2 = i <= 5 ? i : 1;
                BydLogHelper.logD("MicTestActivity", "initPCMFile: newFileNum=" + i2);
                this.i = new File(this.n + "/raw" + i2 + ".pcm");
            } else {
                this.i = new File(this.n + "/raw1.pcm");
            }
        } else {
            this.i = new File(this.n + "/raw1.pcm");
        }
        BydLogHelper.logD("MicTestActivity", "initPCMFile: pcmFile=" + this.i);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_del_file /* 2131296357 */:
                l(new File(this.n));
                break;
            case R.id.bt_pause /* 2131296375 */:
                s();
                break;
            case R.id.bt_play /* 2131296376 */:
                p();
                break;
            case R.id.bt_start_record /* 2131296378 */:
                r();
                break;
            case R.id.bt_stop_record /* 2131296379 */:
                t();
                break;
        }
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_mic_test);
        Button button = (Button) findViewById(R.id.bt_start_record);
        this.b = button;
        button.setOnClickListener(this);
        Button button2 = (Button) findViewById(R.id.bt_stop_record);
        this.c = button2;
        button2.setOnClickListener(this);
        Button button3 = (Button) findViewById(R.id.bt_play);
        this.d = button3;
        button3.setOnClickListener(this);
        Button button4 = (Button) findViewById(R.id.bt_pause);
        this.e = button4;
        button4.setOnClickListener(this);
        Button button5 = (Button) findViewById(R.id.bt_del_file);
        this.f = button5;
        button5.setOnClickListener(this);
        this.n = Environment.getExternalStorageDirectory().getAbsolutePath() + "/audioPcm";
        o();
        n(getExternalFilesDir(Environment.DIRECTORY_MUSIC).getPath(), "raw.pcm");
        k();
        this.k = (AudioManager) getSystemService("audio");
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
    }

    public void p() {
        q();
        new Thread(new d());
    }

    public final void q() {
        BydLogHelper.logD("MicTestActivity", "request HFP AudioFocus +++");
        if (this.l == null) {
            if (this.m == null) {
                this.m = new AudioAttributes.Builder().setLegacyStreamType(0).setUsage(2).build();
            }
            this.l = new AudioFocusRequest.Builder(2).setAudioAttributes(this.m).setWillPauseWhenDucked(true).setOnAudioFocusChangeListener(this.v).build();
        }
        BydLogHelper.logD("MicTestActivity", "request HFP AudioFocus requestAudioFocus");
        this.k.requestAudioFocus(this.l);
    }

    public final void r() {
        t();
        o();
        File file = this.i;
        if (file != null || file.exists()) {
            this.i.delete();
        }
        this.j = true;
        byte[] bArr = new byte[this.h];
        if (this.g == null) {
            k();
        }
        this.g.startRecording();
        new Thread(new c(bArr)).start();
    }

    public final void s() {
        f();
    }

    public final void t() {
        this.j = false;
        AudioRecord audioRecord = this.g;
        if (audioRecord != null) {
            audioRecord.stop();
        }
        AudioRecord audioRecord2 = this.g;
        if (audioRecord2 != null) {
            audioRecord2.release();
        }
        this.g = null;
    }
}
