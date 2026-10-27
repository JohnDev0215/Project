package com.byd.data.collect.server;

import android.hardware.bydauto.pki.PkiManager;
import android.os.SystemProperties;
import android.text.TextUtils;
import com.byd.data.common.d.a;
import com.byd.data.common.sqlite.o;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.Consumer;

/* JADX INFO: compiled from: SocketThread.java */
/* JADX INFO: loaded from: classes.dex */
public class j extends Thread implements com.byd.data.common.server.e.c {
    private final byte[] LOCK;
    private final String TAG;
    private TreeMap<String, com.byd.data.common.e.a> encryptReqMap;
    private com.byd.data.collect.server.k.a iDataPick;
    private InputStream input;
    private String mSn;
    private Socket mSocket;
    private DataOutputStream outputStream;

    /* JADX INFO: compiled from: SocketThread.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f3261a;

        static {
            int[] iArr = new int[a.f.values().length];
            f3261a = iArr;
            try {
                iArr[a.f.SECURITY_CERTIFICATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3261a[a.f.SECURITY_VERITY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3261a[a.f.GB_FILE_LIST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f3261a[a.f.GB_EXTRA_FILE_LIST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f3261a[a.f.GB_ALL_FILE_DATA.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f3261a[a.f.GB_EXTRA_ALL_FILE_DATA.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f3261a[a.f.GB_SPECIAL_FILE_DATA.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f3261a[a.f.GB_EXTRA_SPECIAL_FILE_DATA.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public j(Socket socket, com.byd.data.collect.server.k.a aVar) {
        String str = "SocketThread" + com.byd.data.common.i.b.f3423a;
        this.TAG = str;
        this.encryptReqMap = new TreeMap<>();
        this.LOCK = new byte[0];
        this.mSocket = socket;
        this.iDataPick = aVar;
        String strB = com.byd.data.common.i.g.b("vehicle_sn");
        if (TextUtils.isEmpty(strB) || strB.length() != 21) {
            return;
        }
        this.mSn = strB;
        com.byd.data.common.i.f.j(str, "sp initial getSn =", strB);
    }

    private synchronized void a(byte[] bArr) {
        StringBuilder sb = new StringBuilder("ack is end ");
        if (bArr.length < 1024) {
            sb.append("hex =");
            sb.append(c.a.a.g.d.f(bArr));
        }
        try {
            if (this.outputStream == null) {
                this.outputStream = new DataOutputStream(new BufferedOutputStream(this.mSocket.getOutputStream()));
            }
            this.outputStream.write(bArr);
            this.outputStream.flush();
        } catch (IOException e2) {
            e2.printStackTrace();
            d.a.a.f.b.b.a.d(this.TAG, "IOException in ack execute");
        }
        d.a.a.f.b.b.a.d(this.TAG, sb.toString());
    }

    private void d(byte[] bArr) {
        com.byd.data.common.e.a value;
        com.byd.data.collect.server.k.a aVar = this.iDataPick;
        if (aVar == null || aVar.a() == null || !this.iDataPick.c()) {
            return;
        }
        a.f fVar = a.f.values()[bArr[2]];
        if (fVar.ordinal() <= 2 || !(this.encryptReqMap.isEmpty() || (value = this.encryptReqMap.entrySet().iterator().next().getValue()) == null || !value.f3357d)) {
            switch (a.f3261a[fVar.ordinal()]) {
                case 1:
                    q(bArr);
                    break;
                case 2:
                    r(bArr);
                    break;
                case 3:
                case 4:
                    k(fVar == a.f.GB_FILE_LIST);
                    break;
                case 5:
                case 6:
                    n(fVar == a.f.GB_ALL_FILE_DATA);
                    break;
                case 7:
                case 8:
                    p(bArr);
                    break;
            }
        }
    }

    private byte[] e(int i2) {
        return new byte[]{(byte) (65280 & i2), (byte) (i2 & 255)};
    }

    private String g() {
        if (!TextUtils.isEmpty(this.mSn)) {
            return this.mSn;
        }
        String str = SystemProperties.get("persist.vendor.bosch.cfg.diag.sn");
        if (TextUtils.isEmpty(str)) {
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        }
        this.mSn = str;
        if (str != null && str.startsWith("200") && str.length() == 21) {
            com.byd.data.common.i.g.e("vehicle_sn", str);
            com.byd.data.common.i.f.j(this.TAG, "first save sp sn =", str);
        }
        return this.mSn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void j(List list, int i2) {
        final ByteBuffer byteBufferAllocate = ByteBuffer.allocate(i2);
        if (!list.isEmpty()) {
            byteBufferAllocate.putChar((char) (list.size() & 65535));
            list.forEach(new Consumer() { // from class: com.byd.data.collect.server.f
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    com.byd.data.common.e.b bVar = (com.byd.data.common.e.b) obj;
                    byteBufferAllocate.putChar((char) (bVar.b().length() & 65535)).put(bVar.b().getBytes(StandardCharsets.UTF_8)).putInt((int) bVar.a());
                }
            });
        }
        a(byteBufferAllocate.array());
    }

    private void k(boolean z) {
        o.f().e().o(z, new com.byd.data.common.c.d.b() { // from class: com.byd.data.collect.server.e
            @Override // com.byd.data.common.c.d.b
            public final void a(List list, int i2) {
                this.f3229a.j(list, i2);
            }
        });
    }

    private String m(int i2) {
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(new byte[i2]);
        StringBuffer stringBuffer = new StringBuffer(i2);
        for (int i3 = 0; i3 < i2; i3++) {
            stringBuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".charAt(secureRandom.nextInt(36)));
        }
        return stringBuffer.toString();
    }

    private void n(boolean z) {
        com.byd.data.collect.server.k.a aVar = this.iDataPick;
        if (aVar != null && aVar.c()) {
            i.o(this.iDataPick.a()).n(z, this);
        }
        d.a.a.f.b.b.a.d(this.TAG, "readAllFile is end----");
    }

    private int o() {
        int iAvailable;
        Socket socket = this.mSocket;
        int i2 = -1;
        if (socket != null && !socket.isClosed()) {
            try {
                InputStream inputStream = this.input;
                if (inputStream == null) {
                    inputStream = this.mSocket.getInputStream();
                }
                this.input = inputStream;
                iAvailable = inputStream.available();
            } catch (SocketException e2) {
                if (this.iDataPick != null) {
                    String message = e2.getMessage();
                    Objects.requireNonNull(message);
                    if (message.endsWith("EPIPE (Broken pipe)")) {
                        this.iDataPick.b();
                    }
                }
                d.a.a.f.b.b.a.d(this.TAG, "SocketException found msg =" + e2.getMessage());
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            if (iAvailable < 20) {
                this.mSocket.sendUrgentData(255);
                return -1;
            }
            byte[] bArr = new byte[iAvailable];
            int i3 = this.input.read(bArr);
            int i4 = 255 & bArr[2];
            d.a.a.f.b.b.a.d(this.TAG, "receive reqType =" + i4 + ", hexString =" + c.a.a.g.d.f(bArr));
            int i5 = i3 - 1;
            byte bA = com.byd.data.common.i.d.a(bArr, i5);
            if (bArr[0] == 35 && bArr[0] == bArr[1] && bA == bArr[i5]) {
                d(bArr);
                i2 = 0;
                d.a.a.f.b.b.a.d(this.TAG, "read data is end " + i2);
            }
            return -1;
        }
        return i2;
    }

    private void p(byte[] bArr) {
        String str = new String(bArr, 22, (bArr[20] << 8) + bArr[21]);
        if (this.iDataPick == null || TextUtils.isEmpty(str) || str.contains("..")) {
            s(bArr, a.h.SECURITY_VERITY_FAULT, e(0));
        } else {
            i.o(this.iDataPick.a()).k(str, this);
        }
    }

    private void q(byte[] bArr) {
        String strM = m(8);
        String strG = g();
        byte bOrdinal = (byte) a.d.AES_128.ordinal();
        String strY = com.byd.data.common.g.a.i().j().y();
        String strB = "";
        if (!TextUtils.isEmpty(strG)) {
            String strEncryptStrHSM = PkiManager.getInstance(this.iDataPick.a()).encryptStrHSM(strM);
            if (!TextUtils.isEmpty(strEncryptStrHSM)) {
                strB = c.a.a.a.b.b(c.a.a.a.c.a(strEncryptStrHSM));
            }
        }
        boolean zIsEmpty = TextUtils.isEmpty(strB);
        int length = (zIsEmpty ? 16 : strB.getBytes(StandardCharsets.UTF_8).length) + 1;
        if (zIsEmpty) {
            strM = m(16);
        } else if (bOrdinal <= 1 && (bOrdinal = com.byd.data.common.server.b.h(this.iDataPick.a()).g().encryptType) == a.d.SN.ordinal() && !TextUtils.isEmpty(strG) && strG.length() != 21) {
            bOrdinal = (byte) (bOrdinal + 1);
            length++;
        }
        a.d dVar = a.d.SN;
        int length2 = length + (dVar.ordinal() <= bOrdinal ? strG.length() : 20);
        com.byd.data.common.e.a aVar = new com.byd.data.common.e.a(bOrdinal, zIsEmpty ? com.byd.data.collect.j.a.b(strM, strY.substring(strY.length() - 16)) : strB, strM);
        synchronized (this.LOCK) {
            this.encryptReqMap.put(strM, aVar);
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(length2 + 2);
        byteBufferAllocate.putShort((short) (length2 & 65535)).put(bOrdinal);
        if (bOrdinal == 5) {
            byteBufferAllocate.put((byte) strG.length());
        }
        if (dVar.ordinal() > bOrdinal) {
            strG = strY;
        }
        ByteBuffer byteBufferPut = byteBufferAllocate.put(strG.getBytes(StandardCharsets.UTF_8));
        if (zIsEmpty) {
            strB = strM;
        }
        byteBufferPut.put(strB.getBytes(StandardCharsets.UTF_8));
        s(bArr, a.h.SUCCESS, byteBufferAllocate.array());
    }

    private void r(byte[] bArr) {
        int i2 = (bArr[20] << 8) + bArr[21];
        boolean z = i2 == 8;
        String str = new String(bArr, 22, i2);
        String strA = z ? str : "";
        if (i2 > 8) {
            strA = com.byd.data.collect.j.a.a(com.byd.data.common.g.a.i().j().y().substring(r0.length() - 16), str);
        }
        d.a.a.f.b.b.a.d(this.TAG, "securityVerity decrypt =" + strA);
        if (TextUtils.isEmpty(strA) || !this.encryptReqMap.containsKey(strA)) {
            s(bArr, a.h.SECURITY_VERITY_FAULT, e(0));
            synchronized (this.LOCK) {
                this.encryptReqMap.remove(strA);
            }
        } else {
            s(bArr, a.h.SUCCESS, e(0));
            com.byd.data.common.e.a aVar = this.encryptReqMap.get(strA);
            if (aVar != null) {
                aVar.f3357d = true;
            }
        }
    }

    private void s(byte[] bArr, a.h hVar, byte[] bArr2) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bArr2.length + 20 + 1);
        byteBufferAllocate.put(bArr, 0, 20).put(3, hVar.a()).put(bArr2).put(com.byd.data.common.i.d.a(byteBufferAllocate.array(), byteBufferAllocate.capacity() - 1));
        a(byteBufferAllocate.array());
    }

    public void b(boolean z) {
        this.encryptReqMap.clear();
        try {
            d.a.a.f.b.b.a.d(this.TAG, "socket begin execute close");
            Socket socket = this.mSocket;
            if (socket != null && !socket.isClosed()) {
                InputStream inputStream = this.input;
                if (inputStream != null) {
                    inputStream.close();
                    this.input = null;
                }
                DataOutputStream dataOutputStream = this.outputStream;
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                    this.outputStream = null;
                }
                if (z) {
                    this.encryptReqMap = null;
                    this.iDataPick = null;
                    this.mSn = null;
                }
                this.mSocket = null;
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        d.a.a.f.b.b.a.d(this.TAG, "closeSocket is end");
    }

    @Override // com.byd.data.common.server.e.c
    public void c(byte[] bArr) {
        a(bArr);
    }

    @Override // com.byd.data.common.server.e.c
    public void f(a.g gVar) {
    }

    @Override // com.byd.data.common.server.e.c
    public Socket l() {
        return this.mSocket;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        d.a.a.f.b.b.a.d(this.TAG, "task is running begin");
        int iO = 0;
        while (true) {
            com.byd.data.collect.server.k.a aVar = this.iDataPick;
            if (aVar == null || !aVar.c()) {
                break;
            }
            try {
                Thread.sleep(iO == -1 ? 4500L : 1000L);
                iO = o();
            } catch (InterruptedException unused) {
                d.a.a.f.b.b.a.d(this.TAG, "InterruptedException is found");
            }
        }
        d.a.a.f.b.b.a.d(this.TAG, "task is running end");
    }

    public void t(Socket socket) {
        this.mSocket = socket;
    }
}
