package com.byd.engineermode.ui.functiontest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.TextView;
import androidx.core.content.FileProvider;
import butterknife.R;
import com.byd.engineermode.ui.activity.BaseActivity;
import java.io.File;
import java.io.FileFilter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class USBTestActivity extends BaseActivity {
    private static String v = "/mnt/media_rw/63B6-453C/";
    private static String w = "";
    private static String x = "usbotg";
    private static String y = "sdcard";
    private Button A;
    private TextView B;
    private e C;
    private g D = new g(this, null);
    private final int E = 1;
    private final int F = 2;
    private List<String> G = new ArrayList();
    private List<String> H = new ArrayList();
    private MediaPlayer I;
    private AudioManager J;
    private int K;
    private int L;
    private Button z;

    class a implements FileFilter {
        a() {
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            if (file.isDirectory()) {
                return true;
            }
            String absolutePath = file.getAbsolutePath();
            if (absolutePath.endsWith("mp3")) {
                com.byd.engineermode.utils.a.a("getFilesAllName mp3List path = " + absolutePath);
                USBTestActivity.this.G.add(absolutePath);
                return false;
            }
            if (!file.getAbsolutePath().endsWith("mp4")) {
                return false;
            }
            com.byd.engineermode.utils.a.a("getFilesAllName mp4List path = " + absolutePath);
            USBTestActivity.this.H.add(absolutePath);
            return false;
        }
    }

    class b implements FileFilter {
        b() {
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            if (file.isDirectory()) {
                return true;
            }
            String absolutePath = file.getAbsolutePath();
            if (absolutePath.endsWith("mp3")) {
                com.byd.engineermode.utils.a.a("getFilesAllName mp3List path = " + absolutePath);
                USBTestActivity.this.G.add(absolutePath);
                return false;
            }
            if (!file.getAbsolutePath().endsWith("mp4")) {
                return false;
            }
            com.byd.engineermode.utils.a.a("getFilesAllName mp4List path = " + absolutePath);
            USBTestActivity.this.H.add(absolutePath);
            return false;
        }
    }

    class c implements FileFilter {
        c() {
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            if (file.isDirectory()) {
                return true;
            }
            String absolutePath = file.getAbsolutePath();
            if (absolutePath.endsWith("mp3")) {
                com.byd.engineermode.utils.a.a("getFilesAllName sdFiles mp3List path = " + absolutePath);
                USBTestActivity.this.G.add(absolutePath);
                return false;
            }
            if (!file.getAbsolutePath().endsWith("mp4")) {
                return false;
            }
            com.byd.engineermode.utils.a.a("getFilesAllName sdFiles mp4List path = " + absolutePath);
            USBTestActivity.this.H.add(absolutePath);
            return false;
        }
    }

    class d implements FileFilter {
        d() {
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            if (file.isDirectory()) {
                return true;
            }
            String absolutePath = file.getAbsolutePath();
            if (absolutePath.endsWith("mp3")) {
                com.byd.engineermode.utils.a.a("getFilesAllName sdFiles mp3List path = " + absolutePath);
                USBTestActivity.this.G.add(absolutePath);
                return false;
            }
            if (!file.getAbsolutePath().endsWith("mp4")) {
                return false;
            }
            com.byd.engineermode.utils.a.a("getFilesAllName sdFiles mp4List path = " + absolutePath);
            USBTestActivity.this.H.add(absolutePath);
            return false;
        }
    }

    private class e extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        WeakReference<USBTestActivity> f2427a;

        class a implements Runnable {
            a() {
            }

            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // java.lang.Runnable
            public void run() {
                USBTestActivity uSBTestActivity = USBTestActivity.this;
                int iY = uSBTestActivity.Y(uSBTestActivity);
                if ((iY & 1) == 1) {
                    USBTestActivity.this.z.setText(R.string.connected);
                } else {
                    USBTestActivity.this.z.setText(R.string.disconnect);
                }
                if ((iY & 2) == 2) {
                    USBTestActivity.this.A.setText(R.string.connected);
                } else {
                    USBTestActivity.this.A.setText(R.string.disconnect);
                }
            }
        }

        class b implements Runnable {
            b() {
            }

            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // java.lang.Runnable
            public void run() {
                USBTestActivity uSBTestActivity = USBTestActivity.this;
                int iY = uSBTestActivity.Y(uSBTestActivity);
                if ((iY & 1) == 1) {
                    USBTestActivity.this.z.setText(R.string.connected);
                } else {
                    USBTestActivity.this.z.setText(R.string.disconnect);
                }
                if ((iY & 2) == 2) {
                    USBTestActivity.this.A.setText(R.string.connected);
                } else {
                    USBTestActivity.this.A.setText(R.string.disconnect);
                }
            }
        }

        class c implements Runnable {
            c() {
            }

            @Override // java.lang.Runnable
            public void run() {
                USBTestActivity uSBTestActivity = USBTestActivity.this;
                uSBTestActivity.Z(0, (String) uSBTestActivity.G.get(0));
            }
        }

        e(USBTestActivity uSBTestActivity) {
            this.f2427a = new WeakReference<>(uSBTestActivity);
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (this.f2427a.get() == null) {
                com.byd.engineermode.utils.a.c("outer is null");
            }
            switch (message.what) {
                case 1001:
                    com.byd.engineermode.utils.a.a("MSG_1001，初始化获取U盘或SD卡的状态");
                    USBTestActivity uSBTestActivity = USBTestActivity.this;
                    int iY = uSBTestActivity.Y(uSBTestActivity);
                    if ((iY & 1) == 1) {
                        USBTestActivity.this.z.setText(R.string.connected);
                    } else {
                        USBTestActivity.this.z.setText(R.string.disconnect);
                    }
                    if ((iY & 2) != 2) {
                        USBTestActivity.this.A.setText(R.string.disconnect);
                    } else {
                        USBTestActivity.this.A.setText(R.string.connected);
                    }
                    break;
                case 1002:
                    com.byd.engineermode.utils.a.a("MSG_1002，判断U盘或SD卡插入的情况");
                    USBTestActivity.this.C.sendEmptyMessage(1005);
                    String str = (String) message.obj;
                    if ("android.intent.action.MEDIA_MOUNTED".equals(str)) {
                        com.byd.engineermode.utils.a.a("receive android.intent.action.MEDIA_MOUNTED, mount = USB/SDCard inserted");
                        USBTestActivity.this.runOnUiThread(new a());
                    } else if ("android.intent.action.MEDIA_EJECT".equals(str)) {
                        com.byd.engineermode.utils.a.a("receive android.intent.action.MEDIA_EJECT, unMount: USB/SDCard removed");
                        USBTestActivity.this.runOnUiThread(new b());
                    }
                    break;
                case 1003:
                    if (USBTestActivity.this.G != null && USBTestActivity.this.G.size() > 0) {
                        com.byd.engineermode.utils.a.c("path = " + ((String) USBTestActivity.this.G.get(0)));
                        USBTestActivity.this.B.setText((CharSequence) USBTestActivity.this.G.get(0));
                        new Thread(new c()).start();
                    } else {
                        com.byd.engineermode.ui.dialog.a.a(USBTestActivity.this).b(R.string.usb_test_toast_message);
                    }
                    break;
                case 1004:
                    if (USBTestActivity.this.H != null && USBTestActivity.this.H.size() > 0) {
                        USBTestActivity uSBTestActivity2 = USBTestActivity.this;
                        uSBTestActivity2.a0((String) uSBTestActivity2.H.get(0));
                    } else {
                        com.byd.engineermode.ui.dialog.a.a(USBTestActivity.this).b(R.string.usb_test_toast_message);
                    }
                    break;
                case 1005:
                    com.byd.engineermode.utils.a.a("MSG_1005，搜索文件路径");
                    USBTestActivity uSBTestActivity3 = USBTestActivity.this;
                    if (uSBTestActivity3.Y(uSBTestActivity3) > 0) {
                        USBTestActivity.this.b0(USBTestActivity.v, USBTestActivity.w);
                    }
                    break;
            }
        }
    }

    private final class f implements MediaPlayer.OnPreparedListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f2432a;

        public f(int i) {
            this.f2432a = i;
        }

        @Override // android.media.MediaPlayer.OnPreparedListener
        public void onPrepared(MediaPlayer mediaPlayer) {
            USBTestActivity.this.I.start();
            if (this.f2432a > 0) {
                USBTestActivity.this.I.seekTo(this.f2432a);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Z(int i, String str) {
        if (this.I == null) {
            this.I = new MediaPlayer();
        }
        try {
            this.I.reset();
            this.I.setDataSource(str);
            this.I.prepareAsync();
            this.I.setOnPreparedListener(new f(i));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void OnButtonClick(View view) {
        if (view.getId() == R.id.test_pass_btn) {
            J();
            return;
        }
        if (view.getId() == R.id.test_retry_btn) {
            K();
            return;
        }
        if (view.getId() == R.id.test_fail_btn) {
            I();
        } else if (view.getId() == R.id.btn_play_music) {
            this.C.sendEmptyMessage(1003);
        } else if (view.getId() == R.id.btn_play_video) {
            this.C.sendEmptyMessage(1004);
        }
    }

    public String W(File file) {
        String strX = X(file);
        if (strX == null) {
            return "file/*";
        }
        String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(strX);
        return !TextUtils.isEmpty(mimeTypeFromExtension) ? mimeTypeFromExtension : "file/*";
    }

    public String X(File file) {
        int iLastIndexOf;
        if (file == null) {
            return null;
        }
        String lowerCase = file.getName().toLowerCase();
        if (lowerCase.equals("") || lowerCase.endsWith(".") || (iLastIndexOf = lowerCase.lastIndexOf(".")) == -1) {
            return null;
        }
        return lowerCase.substring(iLastIndexOf + 1).toLowerCase(Locale.US);
    }

    public int Y(Context context) {
        int i = 0;
        try {
            List<VolumeInfo> volumes = ((StorageManager) context.getSystemService("storage")).getVolumes();
            boolean z = SystemProperties.getBoolean("sys.byd.isUSBExist", false);
            boolean z2 = SystemProperties.getBoolean(BYDAutoSysPropertiesDevice.SD_EXIST, false);
            com.byd.engineermode.utils.a.a("isUDiskMount isUSBExist = " + z);
            com.byd.engineermode.utils.a.a("isUDiskMount isSdcradExist = " + z2);
            if (volumes != null && (z || z2)) {
                com.byd.engineermode.utils.a.a("volumes size = " + volumes.size());
                for (VolumeInfo volumeInfo : volumes) {
                    try {
                        com.byd.engineermode.utils.a.a("addDisk volumeInfo = " + volumeInfo);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                    if (x.equals(volumeInfo.getDeviceType()) && z) {
                        v = volumeInfo.path;
                        i |= 1;
                        com.byd.engineermode.utils.a.a("USBOTG mUDiskFilePath = " + v + ", mType = " + i);
                    } else if (y.equals(volumeInfo.getDeviceType()) && z2) {
                        i |= 2;
                        w = volumeInfo.path;
                        com.byd.engineermode.utils.a.a("SDCARD sdCard Path  = " + w + ", mType = " + i);
                    }
                }
            }
        } catch (Throwable th2) {
            com.byd.engineermode.utils.a.d("initVolume failed", th2);
        }
        com.byd.engineermode.utils.a.a("mType = " + i);
        return i;
    }

    public void a0(String str) {
        com.byd.engineermode.utils.a.a(" playAudio , audioPath = " + str);
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addFlags(1);
        File file = new File(str);
        Uri uriE = Build.VERSION.SDK_INT >= 24 ? FileProvider.e(this, "com.byd.filemanager.fileprovider", file) : Uri.fromFile(file);
        intent.addFlags(268435456);
        intent.setDataAndType(uriE, W(file));
        startActivity(intent);
    }

    public void b0(String str, String str2) {
        com.byd.engineermode.utils.a.a("getFilesAllName path = " + str + ", sdPath = " + str2 + ", time = " + SystemClock.uptimeMillis());
        File file = new File(str);
        this.G.clear();
        this.H.clear();
        File[] fileArrListFiles = file.listFiles(new a());
        com.byd.engineermode.utils.a.a("start files = " + fileArrListFiles + ", time = " + SystemClock.uptimeMillis());
        if (fileArrListFiles == null) {
            com.byd.engineermode.utils.a.c("error  没有权限或没有文件");
        } else {
            for (File file2 : fileArrListFiles) {
                file2.listFiles(new b());
            }
        }
        File[] fileArrListFiles2 = new File(str2).listFiles(new c());
        com.byd.engineermode.utils.a.a("start sdFiles = " + fileArrListFiles2 + ", time = " + SystemClock.uptimeMillis());
        if (fileArrListFiles2 == null) {
            com.byd.engineermode.utils.a.c("error  没有权限或没有文件");
        } else {
            for (File file3 : fileArrListFiles2) {
                file3.listFiles(new d());
            }
        }
        com.byd.engineermode.utils.a.a(" end , time = " + SystemClock.uptimeMillis());
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        com.byd.engineermode.utils.a.a("onCreate()");
        setContentView(R.layout.activity_usb_test);
        this.z = (Button) findViewById(R.id.btn_uDisk);
        this.A = (Button) findViewById(R.id.btn_sdcard);
        e eVar = new e(this);
        this.C = eVar;
        eVar.sendEmptyMessage(1001);
        this.C.sendEmptyMessage(1005);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
        intentFilter.addAction("android.intent.action.MEDIA_EJECT");
        intentFilter.addDataScheme("file");
        registerReceiver(this.D, intentFilter);
        this.B = (TextView) findViewById(R.id.mp3_play);
        this.I = new MediaPlayer();
        AudioManager audioManager = (AudioManager) getSystemService("audio");
        this.J = audioManager;
        this.K = audioManager.getStreamMaxVolume(3);
        this.L = this.J.getStreamVolume(3);
        this.J.setStreamVolume(3, this.K / 2, 0);
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        g gVar = this.D;
        if (gVar != null) {
            unregisterReceiver(gVar);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.J.setStreamVolume(3, this.L, 0);
        if (this.I == null) {
            this.I = new MediaPlayer();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        com.byd.engineermode.utils.a.a("onStop()");
        this.J.setStreamVolume(3, this.L, 0);
        this.J.setMode(0);
        MediaPlayer mediaPlayer = this.I;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.I = null;
        }
        super.onStop();
    }

    private class g extends BroadcastReceiver {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        String f2434a;

        private g() {
            this.f2434a = "";
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            this.f2434a = intent.getAction();
            com.byd.engineermode.utils.a.a("UsbCheckReceiver usb action= " + this.f2434a);
            Message message = new Message();
            message.what = 1002;
            message.obj = this.f2434a;
            USBTestActivity.this.C.sendMessage(message);
        }

        /* synthetic */ g(USBTestActivity uSBTestActivity, a aVar) {
            this();
        }
    }
}
