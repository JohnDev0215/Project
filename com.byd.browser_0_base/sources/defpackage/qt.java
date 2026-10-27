package defpackage;

import J.N;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import defpackage.g50;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.browsing_data.ClearBrowsingDataFragmentBasic;
import org.chromium.chrome.browser.c;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class qt implements Callback {
    public final /* synthetic */ int e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ Object g;

    public /* synthetic */ qt(n62 n62Var) {
        this.e = 2;
        this.f = true;
        this.g = n62Var;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:15:0x005e. Please report as an issue. */
    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) throws Throwable {
        Uri uriB;
        OutputStream outputStream;
        OutputStream outputStream2;
        String str;
        FileInputStream fileInputStream = null;
        outputStreamOpenOutputStream = null;
        outputStreamOpenOutputStream = null;
        OutputStream outputStreamOpenOutputStream = null;
        FileInputStream fileInputStream2 = null;
        int i = this.e;
        boolean z = this.f;
        Object obj2 = this.g;
        switch (i) {
            case 0:
                ClearBrowsingDataFragmentBasic clearBrowsingDataFragmentBasic = (ClearBrowsingDataFragmentBasic) obj2;
                int i2 = ClearBrowsingDataFragmentBasic.q0;
                clearBrowsingDataFragmentBasic.getClass();
                g50.a aVar = new g50.a();
                aVar.d();
                g50 g50VarA = aVar.a();
                if (z) {
                    oj2.U(1, 2, "Settings.ClearBrowsingData.OpenMyActivity");
                    str = "https://myactivity.google.com/product/search?utm_source=chrome_cbd";
                } else {
                    oj2.U(0, 2, "Settings.ClearBrowsingData.OpenMyActivity");
                    str = "https://myactivity.google.com/myactivity?utm_source=chrome_cbd";
                }
                Uri uri = Uri.parse(str);
                Intent intent = g50VarA.a;
                intent.setData(uri);
                ClearBrowsingDataFragmentBasic.a aVar2 = clearBrowsingDataFragmentBasic.p0;
                Context contextK = clearBrowsingDataFragmentBasic.K();
                switch (((wd0) aVar2).e) {
                }
                Intent intentE = c.e(contextK, intent);
                intentE.setPackage(clearBrowsingDataFragmentBasic.K().getPackageName());
                intentE.putExtra("com.android.browser.application_id", clearBrowsingDataFragmentBasic.K().getPackageName());
                q11.a(intentE);
                try {
                    clearBrowsingDataFragmentBasic.K().startActivity(intentE, null);
                    return;
                } catch (ActivityNotFoundException unused) {
                    return;
                }
            case 1:
                Runnable runnable = (Runnable) obj2;
                if (((Integer) obj).equals(1)) {
                    runnable.run();
                    return;
                } else {
                    N.MxULk9PS(0, 38);
                    return;
                }
            default:
                Callback callback = (Callback) obj2;
                File file = (File) obj;
                if (z) {
                    uriB = pm0.b(file);
                } else {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("_display_name", file.getName());
                    String name = file.getName();
                    int iLastIndexOf = name.lastIndexOf(".");
                    String lowerCase = (iLastIndexOf == -1 ? "" : name.substring(iLastIndexOf)).toLowerCase(Locale.getDefault());
                    lowerCase.getClass();
                    contentValues.put("mime_type", !lowerCase.equals("png") ? "image/jpeg" : "image/png");
                    contentValues.put("relative_path", Environment.DIRECTORY_DOWNLOADS);
                    ContentResolver contentResolver = xz.a.getContentResolver();
                    uriB = contentResolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues);
                    try {
                        FileInputStream fileInputStream3 = new FileInputStream(file);
                        if (uriB != null) {
                            try {
                                outputStreamOpenOutputStream = contentResolver.openOutputStream(uriB);
                            } catch (IOException unused2) {
                                outputStream2 = outputStreamOpenOutputStream;
                                fileInputStream2 = fileInputStream3;
                                a8.s(fileInputStream2);
                                a8.s(outputStream2);
                            } catch (Throwable th) {
                                th = th;
                                outputStream = outputStreamOpenOutputStream;
                                fileInputStream = fileInputStream3;
                                a8.s(fileInputStream);
                                a8.s(outputStream);
                                throw th;
                            }
                        }
                        if (outputStreamOpenOutputStream != null) {
                            byte[] bArr = new byte[4096];
                            while (true) {
                                int i3 = fileInputStream3.read(bArr);
                                if (i3 != -1) {
                                    outputStreamOpenOutputStream.write(bArr, 0, i3);
                                }
                            }
                        }
                        file.delete();
                        a8.s(fileInputStream3);
                        a8.s(outputStreamOpenOutputStream);
                    } catch (IOException unused3) {
                        outputStream2 = null;
                    } catch (Throwable th2) {
                        th = th2;
                        outputStream = null;
                    }
                }
                callback.onResult(uriB);
                return;
        }
    }

    public /* synthetic */ qt(Object obj, int i, boolean z) {
        this.e = i;
        this.g = obj;
        this.f = z;
    }
}
