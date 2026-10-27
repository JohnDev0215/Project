package O3;

import Y3.r;
import a4.AbstractC0922c;
import a4.AbstractC0927h;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.util.Base64;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import r9.AbstractC2312b;
import r9.D;

/* JADX INFO: loaded from: classes.dex */
public abstract class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final HashMap f5084a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final HashSet f5085b = new HashSet();
    public static final byte[] c = {80, 75, 3, 4};
    public static final byte[] d = {31, -117, 8};

    public static o a(Z3.b bVar, String str, boolean z6) {
        a aVarA;
        try {
            if (str == null) {
                aVarA = null;
            } else {
                try {
                    aVarA = U3.e.f9012b.a(str);
                } catch (Exception e10) {
                    o oVar = new o(e10);
                    if (z6) {
                        AbstractC0927h.b(bVar);
                    }
                    return oVar;
                }
            }
            if (aVarA != null) {
                o oVar2 = new o(aVarA);
                if (z6) {
                    AbstractC0927h.b(bVar);
                }
                return oVar2;
            }
            a aVarA2 = r.a(bVar);
            if (str != null) {
                U3.e.f9012b.f9013a.k(str, aVarA2);
            }
            o oVar3 = new o(aVarA2);
            if (z6) {
                AbstractC0927h.b(bVar);
            }
            return oVar3;
        } catch (Throwable th) {
            if (z6) {
                AbstractC0927h.b(bVar);
            }
            throw th;
        }
    }

    public static o b(Context context, ZipInputStream zipInputStream, String str) {
        a aVarA;
        m mVar;
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        if (str == null) {
            aVarA = null;
        } else {
            try {
                aVarA = U3.e.f9012b.a(str);
            } catch (IOException e10) {
                return new o(e10);
            }
        }
        if (aVarA != null) {
            return new o(aVarA);
        }
        ZipEntry nextEntry = zipInputStream.getNextEntry();
        a aVar = null;
        while (nextEntry != null) {
            String name = nextEntry.getName();
            if (name.contains("__MACOSX")) {
                zipInputStream.closeEntry();
            } else if (nextEntry.getName().equalsIgnoreCase("manifest.json")) {
                zipInputStream.closeEntry();
            } else if (nextEntry.getName().contains(".json")) {
                D dC = AbstractC2312b.c(AbstractC2312b.j(zipInputStream));
                String[] strArr = Z3.a.f10885p;
                aVar = a(new Z3.b(dC), null, false).f5137a;
            } else if (name.contains(".png") || name.contains(".webp") || name.contains(".jpg") || name.contains(".jpeg")) {
                String[] strArrSplit = name.split("/");
                map.put(strArrSplit[strArrSplit.length - 1], BitmapFactory.decodeStream(zipInputStream));
            } else if (name.contains(".ttf") || name.contains(".otf")) {
                String[] strArrSplit2 = name.split("/");
                String str2 = strArrSplit2[strArrSplit2.length - 1];
                String str3 = str2.split("\\.")[0];
                if (context == null) {
                    return new o(new IllegalStateException("Unable to extract font " + str3 + " please pass a non-null Context parameter"));
                }
                File file = new File(context.getCacheDir(), str2);
                try {
                    io.sentry.instrumentation.file.g gVarM = h6.m.m(new FileOutputStream(file), file);
                    try {
                        io.sentry.instrumentation.file.g gVarM2 = h6.m.m(new FileOutputStream(file), file);
                        try {
                            byte[] bArr = new byte[4096];
                            while (true) {
                                int i7 = zipInputStream.read(bArr);
                                if (i7 == -1) {
                                    break;
                                }
                                gVarM2.write(bArr, 0, i7);
                            }
                            gVarM2.flush();
                            gVarM2.close();
                            gVarM.close();
                        } catch (Throwable th) {
                            try {
                                gVarM2.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                            throw th;
                        }
                    } finally {
                    }
                } catch (Throwable th3) {
                    AbstractC0922c.b("Unable to save font " + str3 + " to the temporary file: " + str2 + ". ", th3);
                }
                Typeface typefaceCreateFromFile = Typeface.createFromFile(file);
                if (!file.delete()) {
                    AbstractC0922c.a("Failed to delete temp font file " + file.getAbsolutePath() + ".");
                }
                map2.put(str3, typefaceCreateFromFile);
            } else {
                zipInputStream.closeEntry();
            }
            nextEntry = zipInputStream.getNextEntry();
        }
        if (aVar == null) {
            return new o(new IllegalArgumentException("Unable to parse composition"));
        }
        for (Map.Entry entry : map.entrySet()) {
            String str4 = (String) entry.getKey();
            Iterator it = ((HashMap) aVar.c()).values().iterator();
            while (true) {
                if (!it.hasNext()) {
                    mVar = null;
                    break;
                }
                mVar = (m) it.next();
                if (mVar.d.equals(str4)) {
                    break;
                }
            }
            if (mVar != null) {
                mVar.f5136f = AbstractC0927h.d((Bitmap) entry.getValue(), mVar.f5133a, mVar.f5134b);
            }
        }
        for (Map.Entry entry2 : map2.entrySet()) {
            boolean z6 = false;
            for (U3.c cVar : aVar.f5072f.values()) {
                if (cVar.f9007a.equals(entry2.getKey())) {
                    cVar.d = (Typeface) entry2.getValue();
                    z6 = true;
                }
            }
            if (!z6) {
                AbstractC0922c.a("Parsed font for " + ((String) entry2.getKey()) + " however it was not found in the animation.");
            }
        }
        if (map.isEmpty()) {
            Iterator it2 = ((HashMap) aVar.c()).entrySet().iterator();
            while (it2.hasNext()) {
                m mVar2 = (m) ((Map.Entry) it2.next()).getValue();
                if (mVar2 == null) {
                    return null;
                }
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inScaled = true;
                options.inDensity = 160;
                String str5 = mVar2.d;
                if (str5.startsWith("data:") && str5.indexOf("base64,") > 0) {
                    try {
                        byte[] bArrDecode = Base64.decode(str5.substring(str5.indexOf(44) + 1), 0);
                        Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length, options);
                        if (bitmapDecodeByteArray != null) {
                            mVar2.f5136f = AbstractC0927h.d(bitmapDecodeByteArray, mVar2.f5133a, mVar2.f5134b);
                        }
                    } catch (IllegalArgumentException e11) {
                        AbstractC0922c.b("data URL did not have correct base64 format.", e11);
                        return null;
                    }
                }
            }
        }
        if (str != null) {
            U3.e.f9012b.f9013a.k(str, aVar);
        }
        return new o(aVar);
    }

    public static Boolean c(D d10, byte[] bArr) {
        try {
            D dJ = d10.j();
            for (byte b10 : bArr) {
                if (dJ.k() != b10) {
                    return Boolean.FALSE;
                }
            }
            dJ.close();
            return Boolean.TRUE;
        } catch (Exception unused) {
            AbstractC0922c.f11094a.getClass();
            return Boolean.FALSE;
        } catch (NoSuchMethodError unused2) {
            return Boolean.FALSE;
        }
    }

    public static void d() {
        ArrayList arrayList = new ArrayList(f5085b);
        if (arrayList.size() <= 0) {
            return;
        }
        arrayList.get(0).getClass();
        throw new ClassCastException();
    }
}
