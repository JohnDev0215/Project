package c.a.b;

import android.bluetooth.BluetoothA2dpSink;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.ComponentName;
import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import com.byd.info.BluetoothDeviceInfo;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: A2dpBluetoothManage.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a */
    private static volatile a f2590a;
    private BluetoothDevice C;
    private BluetoothDevice D;
    private AudioManager E;
    private MediaBrowserCompat F;
    private MediaControllerCompat G;
    private d H;
    private e I;
    private f J;

    /* JADX INFO: renamed from: c */
    private Context f2592c;

    /* JADX INFO: renamed from: d */
    private BluetoothA2dpSink f2593d;

    /* JADX INFO: renamed from: e */
    private BluetoothAdapter f2594e;
    private String f;
    private String g;
    private String h;
    private String i;
    private boolean m;
    private AudioManager y;

    /* JADX INFO: renamed from: b */
    private String f2591b = "A2dpBluetoothManage";
    private long j = 0;
    private long k = -1;
    private int l = 0;
    private ArrayList<c.a.a.d> n = new ArrayList<>();
    private CopyOnWriteArrayList<BluetoothDeviceInfo> o = new CopyOnWriteArrayList<>();
    private final int p = 100001;
    private final int q = 100002;
    private final int r = 100003;
    private final int s = 100004;
    private final int t = 100005;
    private final int u = 100006;
    private final int v = 100007;
    private boolean w = false;
    private int x = 0;
    private final String z = "sinohb.bt.music";
    private boolean A = false;
    private int B = 0;
    private BluetoothProfile.ServiceListener K = new C0066a();
    private BluetoothProfile.ServiceListener L = new b();
    private Handler M = new c(Looper.getMainLooper());

    /* JADX INFO: renamed from: c.a.b.a$a */
    /* JADX INFO: compiled from: A2dpBluetoothManage.java */
    class C0066a implements BluetoothProfile.ServiceListener {
        C0066a() {
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            if (i == 11) {
                a.this.f2593d = (BluetoothA2dpSink) bluetoothProfile;
                if (a.this.f2593d == null) {
                    a.this.w = false;
                } else {
                    a.this.w = true;
                }
            }
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i) {
            if (i == 11) {
                a.this.f2593d = null;
            }
        }
    }

    /* JADX INFO: compiled from: A2dpBluetoothManage.java */
    class b implements BluetoothProfile.ServiceListener {
        b() {
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            if (i == 12) {
                BydLogHelper.logD(a.this.f2591b, "onServiceConnected === ");
            }
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i) {
        }
    }

    /* JADX INFO: compiled from: A2dpBluetoothManage.java */
    class c extends Handler {
        c(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            super.dispatchMessage(message);
            int i = 0;
            switch (message.what) {
                case 100001:
                    for (int i2 = 0; i2 < a.this.n.size(); i2++) {
                        ((c.a.a.d) a.this.n.get(i2)).updateTitle(a.this.f, a.this.g, a.this.h, a.this.i);
                    }
                    a.this.x = c.a.b.b.f0().r0().beginBroadcast();
                    if (a.this.x > 0) {
                        while (i < a.this.x) {
                            try {
                                ((com.byd.bluetoothservice.a) c.a.b.b.f0().r0().getBroadcastItem(i)).updateTitle(a.this.f, a.this.g, a.this.h, a.this.i);
                                i++;
                            } catch (RemoteException e2) {
                                e2.printStackTrace();
                            }
                        }
                    }
                    c.a.b.b.f0().r0().finishBroadcast();
                    return;
                case 100002:
                    for (int i3 = 0; i3 < a.this.n.size(); i3++) {
                        ((c.a.a.d) a.this.n.get(i3)).updatePlayState(a.this.l);
                    }
                    a.this.x = c.a.b.b.f0().r0().beginBroadcast();
                    if (a.this.x > 0) {
                        while (i < a.this.x) {
                            try {
                                ((com.byd.bluetoothservice.a) c.a.b.b.f0().r0().getBroadcastItem(i)).updatePlayState(a.this.l);
                                i++;
                            } catch (RemoteException e3) {
                                e3.printStackTrace();
                            }
                            break;
                        }
                    }
                    c.a.b.b.f0().r0().finishBroadcast();
                    return;
                case 100003:
                    for (int i4 = 0; i4 < a.this.n.size(); i4++) {
                        ((c.a.a.d) a.this.n.get(i4)).updateTotalTime(a.this.j);
                    }
                    a.this.x = c.a.b.b.f0().r0().beginBroadcast();
                    if (a.this.x > 0) {
                        while (i < a.this.x) {
                            try {
                                ((com.byd.bluetoothservice.a) c.a.b.b.f0().r0().getBroadcastItem(i)).updateTotalTime(a.this.j);
                                i++;
                            } catch (RemoteException e4) {
                                e4.printStackTrace();
                            }
                            break;
                        }
                    }
                    c.a.b.b.f0().r0().finishBroadcast();
                    return;
                case 100004:
                    for (int i5 = 0; i5 < a.this.n.size(); i5++) {
                        ((c.a.a.d) a.this.n.get(i5)).updateCurrentTime(a.this.k);
                    }
                    a.this.x = c.a.b.b.f0().r0().beginBroadcast();
                    if (a.this.x > 0) {
                        while (i < a.this.x) {
                            try {
                                ((com.byd.bluetoothservice.a) c.a.b.b.f0().r0().getBroadcastItem(i)).updateCurrentTime(a.this.k);
                                i++;
                            } catch (RemoteException e5) {
                                e5.printStackTrace();
                            }
                            break;
                        }
                    }
                    c.a.b.b.f0().r0().finishBroadcast();
                    return;
                case 100005:
                    for (int i6 = 0; i6 < a.this.n.size(); i6++) {
                        ((c.a.a.d) a.this.n.get(i6)).updateBattery(message.arg1);
                    }
                    a.this.x = c.a.b.b.f0().r0().beginBroadcast();
                    if (a.this.x > 0) {
                        while (i < a.this.x) {
                            try {
                                ((com.byd.bluetoothservice.a) c.a.b.b.f0().r0().getBroadcastItem(i)).updateBattery(message.arg1);
                                i++;
                            } catch (RemoteException e6) {
                                e6.printStackTrace();
                            }
                            break;
                        }
                    }
                    c.a.b.b.f0().r0().finishBroadcast();
                    return;
                case 100006:
                    a.this.x = c.a.b.b.f0().r0().beginBroadcast();
                    if (a.this.x > 0) {
                        for (int i7 = 0; i7 < a.this.x; i7++) {
                            try {
                                ((com.byd.bluetoothservice.a) c.a.b.b.f0().r0().getBroadcastItem(i7)).x(a.this.B);
                            } catch (RemoteException e7) {
                                e7.printStackTrace();
                            }
                        }
                    }
                    c.a.b.b.f0().r0().finishBroadcast();
                    while (i < a.this.n.size()) {
                        ((c.a.a.d) a.this.n.get(i)).updateA2dpConnectState(a.this.C, a.this.B);
                        i++;
                    }
                    return;
                case 100007:
                    break;
                default:
                    return;
            }
            while (i < a.this.n.size()) {
                ((c.a.a.d) a.this.n.get(i)).updateA2dpConnectStateShowToast((BluetoothDevice) message.obj, message.arg1);
                i++;
            }
        }
    }

    /* JADX INFO: compiled from: A2dpBluetoothManage.java */
    private class d extends MediaBrowserCompat.b {
        private d() {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b
        public void a() {
            try {
                BydLogHelper.logD(a.this.f2591b, "MediaBrowserConnection onConnected!");
                a aVar = a.this;
                aVar.G = new MediaControllerCompat(aVar.f2592c, a.this.F.d());
                a.this.G.e(a.this.J);
                a.this.J.d(a.this.G.b());
                a.this.J.e(a.this.G.c());
                a.this.F.e(a.this.F.c(), a.this.I);
            } catch (RemoteException e2) {
                BydLogHelper.logD(a.this.f2591b, String.format("onConnected: Problem: %s", e2.toString()));
                throw new RuntimeException("onConnected: Problem");
            }
        }

        @Override // android.support.v4.media.MediaBrowserCompat.b
        public void b() {
            super.b();
            BydLogHelper.logD(a.this.f2591b, "MediaBrowserConnection onConnectionFailed!");
        }

        /* synthetic */ d(a aVar, C0066a c0066a) {
            this();
        }
    }

    /* JADX INFO: compiled from: A2dpBluetoothManage.java */
    public class e extends MediaBrowserCompat.l {
        public e() {
        }

        @Override // android.support.v4.media.MediaBrowserCompat.l
        public void a(String str, List<MediaBrowserCompat.MediaItem> list) {
        }
    }

    /* JADX INFO: compiled from: A2dpBluetoothManage.java */
    private class f extends MediaControllerCompat.a {
        private f() {
        }

        private void o(long j) {
            a.this.k = j;
            a.this.M.sendEmptyMessage(100004);
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void d(MediaMetadataCompat mediaMetadataCompat) {
            if (mediaMetadataCompat != null) {
                a.this.j(mediaMetadataCompat.f("android.media.metadata.TITLE"), mediaMetadataCompat.f("android.media.metadata.ARTIST"), mediaMetadataCompat.f("android.media.metadata.ALBUM"), mediaMetadataCompat.f("android.media.metadata.DISPLAY_ICON_URI"));
                mediaMetadataCompat.f("android.media.metadata.GENRE");
                a.this.l(mediaMetadataCompat.c("android.media.metadata.DURATION"));
                mediaMetadataCompat.c("android.media.metadata.NUM_TRACKS");
                mediaMetadataCompat.c("android.media.metadata.TRACK_NUMBER");
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void e(PlaybackStateCompat playbackStateCompat) {
            if (playbackStateCompat != null) {
                o(playbackStateCompat.c());
                BydLogHelper.logD(a.this.f2591b, "onPlaybackStateChanged == " + playbackStateCompat.f());
                if (playbackStateCompat.f() == 3 || playbackStateCompat.f() == 4 || playbackStateCompat.f() == 5) {
                    a.this.k(3);
                } else {
                    a.this.k(2);
                }
            }
        }

        @Override // android.support.v4.media.session.MediaControllerCompat.a
        public void i() {
        }

        /* synthetic */ f(a aVar, C0066a c0066a) {
            this();
        }
    }

    public static a K() {
        if (f2590a == null) {
            synchronized (a.class) {
                if (f2590a == null) {
                    f2590a = new a();
                }
            }
        }
        return f2590a;
    }

    public BluetoothDevice L() {
        return this.C;
    }

    public int M() {
        for (int i = 0; i < this.o.size(); i++) {
            if (this.o.get(i).c() == 2) {
                return 1;
            }
        }
        return 0;
    }

    public String N() {
        String str = this.g;
        return (str == null || str.length() == 0) ? " " : this.g;
    }

    public String O() {
        String str = this.i;
        return (str == null || str.length() == 0) ? " " : this.i;
    }

    public String P() {
        String str = this.f;
        return (str == null || str.length() == 0) ? " " : this.f;
    }

    public int Q() {
        return this.l;
    }

    public long R() {
        return this.k;
    }

    public long S() {
        return this.j;
    }

    public void T(Context context) {
        this.f2592c = context;
        this.E = (AudioManager) context.getSystemService("audio");
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        this.f2594e = defaultAdapter;
        defaultAdapter.getProfileProxy(this.f2592c, this.K, 11);
        this.y = (AudioManager) this.f2592c.getSystemService("audio");
        this.H = new d(this, null);
        this.I = new e();
        this.J = new f(this, null);
        if (this.F == null) {
            MediaBrowserCompat mediaBrowserCompat = new MediaBrowserCompat(this.f2592c, new ComponentName("com.android.bluetooth", "com.android.bluetooth.avrcpcontroller.BluetoothMediaBrowserService"), this.H, null);
            this.F = mediaBrowserCompat;
            mediaBrowserCompat.a();
        }
    }

    public boolean U() {
        return this.w;
    }

    public void V(c.a.a.d dVar) {
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= this.n.size()) {
                break;
            }
            if (this.n.get(i) == dVar) {
                z = true;
                break;
            }
            i++;
        }
        if (z) {
            return;
        }
        this.n.add(dVar);
    }

    public void W(c.a.a.d dVar) {
        for (int i = 0; i < this.n.size(); i++) {
            if (this.n.get(i) == dVar) {
                this.n.remove(i);
                return;
            }
        }
    }

    public boolean a(BluetoothDevice bluetoothDevice) {
        BluetoothA2dpSink bluetoothA2dpSink = this.f2593d;
        if (bluetoothA2dpSink == null || bluetoothDevice == null || bluetoothA2dpSink.getConnectionState(bluetoothDevice) != 2) {
            return false;
        }
        this.C = bluetoothDevice;
        return true;
    }

    public void b() {
        this.B = 0;
        this.C = null;
        j(" ", " ", " ", " ");
        this.k = 0L;
        l(0L);
        k(2);
        this.o.clear();
        this.M.sendEmptyMessage(100006);
    }

    public void c() {
        BydLogHelper.logD(this.f2591b, "Next_Music==");
        MediaControllerCompat mediaControllerCompat = this.G;
        if (mediaControllerCompat != null) {
            mediaControllerCompat.d().c();
        }
    }

    public void d() {
        BydLogHelper.logD("DANDAN", "btMusicFocusLinister = Pause_Music");
        MediaControllerCompat mediaControllerCompat = this.G;
        if (mediaControllerCompat != null) {
            mediaControllerCompat.d().a();
        }
    }

    public void e() {
        MediaControllerCompat mediaControllerCompat = this.G;
        if (mediaControllerCompat != null) {
            mediaControllerCompat.d().b();
        }
    }

    public void f() {
        BydLogHelper.logD(this.f2591b, "Play_or_Pause==");
        if (this.m) {
            MediaControllerCompat mediaControllerCompat = this.G;
            if (mediaControllerCompat != null) {
                mediaControllerCompat.d().a();
                return;
            }
            return;
        }
        MediaControllerCompat mediaControllerCompat2 = this.G;
        if (mediaControllerCompat2 != null) {
            mediaControllerCompat2.d().b();
        }
    }

    public void g() {
        BydLogHelper.logD(this.f2591b, "Pre_Music==");
        MediaControllerCompat mediaControllerCompat = this.G;
        if (mediaControllerCompat != null) {
            mediaControllerCompat.d().d();
        }
    }

    public void h(BluetoothDevice bluetoothDevice, int i) {
        boolean z;
        boolean z2;
        BydLogHelper.logD(this.f2591b, "==connectState = " + i);
        if (bluetoothDevice == null) {
            return;
        }
        BydLogHelper.logD(this.f2591b, "UpdataA2dpState==connectState = " + bluetoothDevice.getName());
        Message messageObtainMessage = this.M.obtainMessage();
        messageObtainMessage.what = 100007;
        messageObtainMessage.obj = bluetoothDevice;
        messageObtainMessage.arg1 = i;
        this.M.sendMessage(messageObtainMessage);
        int i2 = 0;
        while (true) {
            z = true;
            if (i2 >= this.o.size()) {
                z2 = false;
                break;
            } else {
                if (this.o.get(i2).f().getAddress().equals(bluetoothDevice.getAddress())) {
                    this.o.get(i2).j(i);
                    z2 = true;
                    break;
                }
                i2++;
            }
        }
        if (!z2) {
            this.o.add(new BluetoothDeviceInfo(bluetoothDevice, i, -1));
        }
        int i3 = 0;
        while (true) {
            if (i3 >= this.o.size()) {
                z = false;
                break;
            } else {
                if (this.o.get(i3).c() == 2) {
                    this.C = this.o.get(i3).f();
                    this.B = 1;
                    break;
                }
                i3++;
            }
        }
        if (!z) {
            this.B = 0;
            this.D = this.C;
            this.C = null;
            j(" ", " ", " ", " ");
            this.k = 0L;
            l(0L);
            k(2);
            this.o.clear();
        }
        BydLogHelper.logD(this.f2591b, "mA2dpConnectState ==" + this.B);
        this.M.sendEmptyMessage(100006);
    }

    public void i(BluetoothDevice bluetoothDevice, int i) {
        Message messageObtainMessage = this.M.obtainMessage();
        messageObtainMessage.what = 100005;
        messageObtainMessage.arg1 = i;
        this.M.sendMessage(messageObtainMessage);
    }

    public void j(String str, String str2, String str3, String str4) {
        this.f = str;
        this.g = str2;
        this.h = str3;
        this.i = str4;
        if (TextUtils.isEmpty(str)) {
            this.f = " ";
        }
        if (TextUtils.isEmpty(this.h)) {
            this.h = " ";
        }
        if (TextUtils.isEmpty(this.g)) {
            this.g = " ";
        }
        if (TextUtils.isEmpty(this.i)) {
            this.i = " ";
        }
        this.M.sendEmptyMessage(100001);
    }

    public void k(int i) {
        if (this.l != i && i == 3) {
            j(this.f, this.g, this.h, this.i);
        }
        if (this.l != i) {
            this.l = i;
            BydLogHelper.logD("DANDAN", "btMusicFocusLinister = state = " + i);
            if (i == 3) {
                BydLogHelper.logD("DANDAN", "btMusicFocusLinister = STATE_PLAYING = ");
                this.m = true;
            } else if (i == 2) {
                this.m = false;
            }
            this.M.sendEmptyMessage(100002);
        }
    }

    public void l(long j) {
        this.j = j;
        this.M.sendEmptyMessage(100003);
    }
}
