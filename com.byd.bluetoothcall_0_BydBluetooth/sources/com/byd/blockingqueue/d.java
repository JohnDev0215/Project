package com.byd.blockingqueue;

import android.bluetooth.BluetoothDevice;

/* JADX INFO: compiled from: PrintTask.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends com.byd.blockingqueue.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Bt_function f3218d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private BluetoothDevice f3219e;
    private int f;
    private String g;
    private long h;

    /* JADX INFO: compiled from: PrintTask.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f3220a;

        static {
            int[] iArr = new int[Bt_function.values().length];
            f3220a = iArr;
            try {
                iArr[Bt_function.MUSIC_MEDIA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3220a[Bt_function.MUSIC_TOTAL_TIME.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3220a[Bt_function.PAIRD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3220a[Bt_function.MUSIC_PLAY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3220a[Bt_function.A2DPCONNECT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f3220a[Bt_function.CONNECT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f3220a[Bt_function.CALL.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f3220a[Bt_function.CALL_CHANNEL.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f3220a[Bt_function.BATLEVEL.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f3220a[Bt_function.SETTING.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    public d(Bt_function bt_function) {
        this.f3218d = bt_function;
    }

    private void e(Bt_function bt_function) {
        switch (a.f3220a[bt_function.ordinal()]) {
            case 2:
                c.a.b.a.K().l(this.h);
                break;
            case 3:
                c.a.b.b.f0().i(this.f3219e, this.f);
                break;
            case 5:
                c.a.b.a.K().h(this.f3219e, this.f);
                break;
            case 6:
                c.a.b.b.f0().b(this.f3219e, this.f);
                break;
            case 7:
                c.a.b.b.f0().o(this.f, this.g, false);
                break;
            case 8:
                c.a.b.b.f0().p(this.f);
                break;
            case 9:
                c.a.b.b.f0().m(this.f, this.f3219e);
                break;
            case 10:
                c.a.b.b.f0().n(this.f);
                break;
        }
    }

    public void g(int i) {
        this.f = i;
    }

    public void h(BluetoothDevice bluetoothDevice) {
        this.f3219e = bluetoothDevice;
    }

    public void i(String str) {
        this.g = str;
    }

    @Override // com.byd.blockingqueue.b
    public void run() {
        e(this.f3218d);
    }
}
