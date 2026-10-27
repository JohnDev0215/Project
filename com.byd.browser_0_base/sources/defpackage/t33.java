package defpackage;

import android.os.SystemClock;
import android.util.Log;
import android.util.Pair;
import defpackage.ct;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.FileChannel;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;

/* JADX INFO: compiled from: TabStateFileManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class t33 {
    public static String a(int i, boolean z) {
        return (z ? "cryptonito" : "tab") + i;
    }

    public static Pair<Integer, Boolean> b(String str) {
        try {
            if (str.startsWith("cryptonito")) {
                return Pair.create(Integer.valueOf(Integer.parseInt(str.substring(10))), Boolean.TRUE);
            }
            if (str.startsWith("tab")) {
                return Pair.create(Integer.valueOf(Integer.parseInt(str.substring(3))), Boolean.FALSE);
            }
            return null;
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public static m33 c(FileInputStream fileInputStream, boolean z) throws IOException {
        Cipher cipherA;
        DataInputStream dataInputStream = (!z || (cipherA = ct.c.a.a(2)) == null) ? null : new DataInputStream(new CipherInputStream(fileInputStream, cipherA));
        if (dataInputStream == null) {
            dataInputStream = new DataInputStream(fileInputStream);
        }
        if (z) {
            try {
                if (dataInputStream.readLong() != 0) {
                    return null;
                }
            } finally {
                dataInputStream.close();
            }
        }
        m33 m33Var = new m33();
        m33Var.d = dataInputStream.readLong();
        int i = dataInputStream.readInt();
        if (z) {
            byte[] bArr = new byte[i];
            dataInputStream.readFully(bArr);
            ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(i);
            m33Var.a = new on2(byteBufferAllocateDirect);
            byteBufferAllocateDirect.put(bArr);
        } else {
            FileChannel channel = fileInputStream.getChannel();
            long j = i;
            m33Var.a = new on2(channel.map(FileChannel.MapMode.READ_ONLY, channel.position(), j));
            long jSkip = fileInputStream.skip(j);
            if (jSkip != j) {
                Log.e("cr_TabState", "Only skipped " + jSkip + " bytes when " + i + " should've been skipped. Tab restore may fail.");
            }
        }
        m33Var.b = dataInputStream.readInt();
        try {
            String utf = dataInputStream.readUTF();
            m33Var.e = utf;
            if ("".equals(utf)) {
                m33Var.e = null;
            }
        } catch (EOFException unused) {
            Log.w("cr_TabState", "Failed to read opener app id state from tab state");
        }
        try {
            m33Var.a.a = dataInputStream.readInt();
        } catch (EOFException unused2) {
            on2 on2Var = m33Var.a;
            "stable".equals(null);
            on2Var.a = 0;
            Log.w("cr_TabState", "Failed to read saved state version id from tab state. Assuming version " + m33Var.a.a);
        }
        try {
            dataInputStream.readLong();
        } catch (EOFException unused3) {
        }
        try {
            dataInputStream.readBoolean();
        } catch (EOFException unused4) {
            Log.w("cr_TabState", "Failed to read shouldPreserve flag from tab state. Assuming shouldPreserve is false");
        }
        m33Var.h = z;
        try {
            m33Var.f = dataInputStream.readInt();
        } catch (EOFException unused5) {
            m33Var.f = 0;
            Log.w("cr_TabState", "Failed to read theme color from tab state. Assuming theme color is TabState#UNSPECIFIED_THEME_COLOR");
        }
        try {
            Integer numValueOf = Integer.valueOf(dataInputStream.readInt());
            m33Var.g = numValueOf;
            if (numValueOf.intValue() < 0 || m33Var.g.intValue() >= 20) {
                m33Var.g = null;
            }
        } catch (EOFException unused6) {
            m33Var.g = null;
            Log.w("cr_TabState", "Failed to read tab launch type at creation from tab state. Assuming tab launch type is null");
        }
        try {
            m33Var.c = dataInputStream.readInt();
        } catch (EOFException unused7) {
            m33Var.c = -1;
            Log.w("cr_TabState", "Failed to read tab root id from tab state. Assuming root id is Tab.INVALID_TAB_ID");
        }
        try {
            m33Var.i = dataInputStream.readInt();
        } catch (EOFException unused8) {
            m33Var.i = 3;
            Log.w("cr_TabState", "Failed to read tab user agent from tab state. Assuming user agent is TabUserAgent.UNSET");
        }
        return m33Var;
    }

    public static m33 d(File file, int i) throws Throwable {
        boolean z;
        FileInputStream fileInputStream;
        File file2 = new File(file, a(i, false));
        if (file2.exists()) {
            z = false;
        } else {
            file2 = new File(file, a(i, true));
            z = true;
        }
        FileInputStream fileInputStream2 = null;
        m33VarC = null;
        m33VarC = null;
        m33 m33VarC = null;
        if (!file2.exists()) {
            return null;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        try {
            try {
                fileInputStream = new FileInputStream(file2);
                try {
                    m33VarC = c(fileInputStream, z);
                } catch (FileNotFoundException unused) {
                    Log.e("cr_TabState", "Failed to restore tab state for tab: " + file2);
                    oj2.U(0, 3, "Tabs.RestoreTabStateException");
                } catch (ClosedByInterruptException e) {
                    e = e;
                    Log.e("cr_TabState", "Failed to restore tab state.", e);
                    oj2.U(1, 3, "Tabs.RestoreTabStateException");
                } catch (IOException e2) {
                    e = e2;
                    Log.e("cr_TabState", "Failed to restore tab state.", e);
                    oj2.U(2, 3, "Tabs.RestoreTabStateException");
                }
            } catch (Throwable th) {
                th = th;
                fileInputStream2 = fileInputStream;
                a8.s(fileInputStream2);
                throw th;
            }
        } catch (FileNotFoundException unused2) {
            fileInputStream = null;
        } catch (ClosedByInterruptException e3) {
            e = e3;
            fileInputStream = null;
        } catch (IOException e4) {
            e = e4;
            fileInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            a8.s(fileInputStream2);
            throw th;
        }
        a8.s(fileInputStream);
        if (m33VarC != null) {
            oj2.b0(SystemClock.elapsedRealtime() - jElapsedRealtime, "Tabs.TabState.LoadTime");
        }
        return m33VarC;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v1, types: [java.nio.ByteBuffer] */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v5, types: [java.io.Closeable] */
    public static void e(File file, m33 m33Var, boolean z) throws Throwable {
        FileOutputStream fileOutputStream;
        if (m33Var.a == null) {
            return;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        ByteBuffer byteBufferAsReadOnlyBuffer = ((ByteBuffer) m33Var.a.b).asReadOnlyBuffer();
        int iLimit = byteBufferAsReadOnlyBuffer.limit();
        byte[] bArr = new byte[iLimit];
        ?? r6 = (ByteBuffer) byteBufferAsReadOnlyBuffer.rewind();
        byteBufferAsReadOnlyBuffer.get(bArr);
        DataOutputStream dataOutputStream = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(file);
                try {
                    if (z) {
                        Cipher cipherA = ct.c.a.a(1);
                        if (cipherA == null) {
                            a8.s(fileOutputStream);
                            return;
                        }
                        dataOutputStream = new DataOutputStream(new BufferedOutputStream(new CipherOutputStream(fileOutputStream, cipherA)));
                    } else {
                        dataOutputStream = new DataOutputStream(new BufferedOutputStream(fileOutputStream));
                    }
                    if (z) {
                        dataOutputStream.writeLong(0L);
                    }
                    dataOutputStream.writeLong(m33Var.d);
                    dataOutputStream.writeInt(iLimit);
                    dataOutputStream.write(bArr);
                    dataOutputStream.writeInt(m33Var.b);
                    String str = m33Var.e;
                    if (str == null) {
                        str = "";
                    }
                    dataOutputStream.writeUTF(str);
                    dataOutputStream.writeInt(m33Var.a.a);
                    dataOutputStream.writeLong(-1L);
                    dataOutputStream.writeBoolean(false);
                    dataOutputStream.writeInt(m33Var.f);
                    Integer num = m33Var.g;
                    dataOutputStream.writeInt(num != null ? num.intValue() : -1);
                    dataOutputStream.writeInt(m33Var.c);
                    dataOutputStream.writeInt(m33Var.i);
                    oj2.b0(SystemClock.elapsedRealtime() - jElapsedRealtime, "Tabs.TabState.SaveTime");
                } catch (FileNotFoundException unused) {
                    Log.w("cr_TabState", "FileNotFoundException while attempting to save TabState.");
                } catch (IOException unused2) {
                    Log.w("cr_TabState", "IOException while attempting to save TabState.");
                }
            } catch (Throwable th) {
                th = th;
                a8.s(null);
                a8.s(r6);
                throw th;
            }
        } catch (FileNotFoundException unused3) {
            fileOutputStream = null;
        } catch (IOException unused4) {
            fileOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
            r6 = 0;
            a8.s(null);
            a8.s(r6);
            throw th;
        }
        a8.s(dataOutputStream);
        a8.s(fileOutputStream);
    }
}
