package k4;

import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Base64;
import android.util.Log;
import e4.InterfaceC1226b;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class z implements r, InterfaceC1226b {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final z f18224m = new z(0);
    public final /* synthetic */ int l;

    public /* synthetic */ z(int i7) {
        this.l = i7;
    }

    public static ByteArrayInputStream a(String str) {
        if (!str.startsWith("data:image")) {
            throw new IllegalArgumentException("Not a valid image data URL.");
        }
        int iIndexOf = str.indexOf(44);
        if (iIndexOf == -1) {
            throw new IllegalArgumentException("Missing comma in data URL.");
        }
        if (str.substring(0, iIndexOf).endsWith(";base64")) {
            return new ByteArrayInputStream(Base64.decode(str.substring(iIndexOf + 1), 0));
        }
        throw new IllegalArgumentException("Not a base64 image data URL.");
    }

    @Override // k4.r
    public q A(w wVar) {
        switch (this.l) {
            case 0:
                return C1682A.f18174b;
            case 2:
                return new C1685c(new z(1), 0);
            case 4:
                return new C1685c(new z(3), 0);
            case 6:
                return new C1682A(1);
            case 11:
                return new y(wVar.b(Uri.class, AssetFileDescriptor.class), 0);
            case 12:
                return new y(wVar.b(Uri.class, ParcelFileDescriptor.class), 0);
            case 13:
                return new y(wVar.b(Uri.class, InputStream.class), 0);
            default:
                return new D(wVar.b(g.class, InputStream.class));
        }
    }

    public Class b() {
        switch (this.l) {
            case 1:
                return ByteBuffer.class;
            case 3:
                return InputStream.class;
            case 8:
                return ParcelFileDescriptor.class;
            default:
                return InputStream.class;
        }
    }

    @Override // e4.InterfaceC1226b
    public boolean q(Object obj, File file, e4.h hVar) throws Throwable {
        try {
            A4.c.d((ByteBuffer) obj, file);
            return true;
        } catch (IOException e10) {
            if (Log.isLoggable("ByteBufferEncoder", 3)) {
                Log.d("ByteBufferEncoder", "Failed to write data", e10);
            }
            return false;
        }
    }
}
