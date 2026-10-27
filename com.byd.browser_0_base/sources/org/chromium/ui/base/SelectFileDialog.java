package org.chromium.ui.base;

import J.N;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import defpackage.bz;
import defpackage.i9;
import defpackage.ke0;
import defpackage.ku2;
import defpackage.nx0;
import defpackage.oj2;
import defpackage.uh3;
import defpackage.vd1;
import defpackage.w02;
import defpackage.wt1;
import defpackage.xz;
import defpackage.y02;
import defpackage.yj;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.chromium.base.ContentUriUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.browser_ui.photo_picker.h;
import org.chromium.ui.R$string;
import org.chromium.ui.base.SelectFileDialog;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.permissions.PermissionCallback;
import org.chromium.ui.widget.Toast;

/* JADX INFO: loaded from: classes3.dex */
public class SelectFileDialog implements WindowAndroid.c, y02 {
    public static final long k = TimeUnit.HOURS.toMillis(1);
    public static final String[] l = {".apng", ".bmp", ".gif", ".jpeg", ".jpg", ".pdf", ".png", ".tif", ".tiff", ".xcf", ".webp"};
    public static final String[] m = {".asf", ".avhcd", ".avi", ".divx", ".flv", ".mov", ".mp4", ".mpeg", ".mpg", ".swf", ".wmv", ".webm", ".mkv"};
    public static vd1 n;
    public static w02 o;
    public final long a;
    public ArrayList b;
    public boolean c;
    public boolean d;
    public Uri e;
    public WindowAndroid f;
    public boolean g;
    public boolean h;
    public boolean i;
    public boolean j;

    public final class a extends i9<Boolean> {
        public final Context h;
        public final String i;

        public a(Context context, String str) {
            this.h = context;
            this.i = str;
        }

        @Override // defpackage.i9
        public final Boolean b() {
            Context context = this.h;
            String str = this.i;
            return Boolean.valueOf((SelectFileDialog.j(context, str) || N.MWck3aif(str).isEmpty()) ? false : true);
        }

        @Override // defpackage.i9
        public final void l(Boolean bool) {
            boolean zBooleanValue = bool.booleanValue();
            SelectFileDialog selectFileDialog = SelectFileDialog.this;
            if (!zBooleanValue) {
                String[] strArr = SelectFileDialog.l;
                selectFileDialog.m();
                return;
            }
            selectFileDialog.n(selectFileDialog.a, this.i, "");
            int i = R$string.opening_file_error;
            nx0<Activity> nx0Var = WindowAndroid.A;
            String string = xz.a.getString(i);
            if (string != null) {
                Toast.b(0, xz.a, string).e();
            }
        }
    }

    public class b extends i9<Uri> {
        public final Boolean h;
        public final WindowAndroid i;
        public final WindowAndroid.c j;

        public b(Boolean bool, WindowAndroid windowAndroid, WindowAndroid.c cVar) {
            this.h = bool;
            this.i = windowAndroid;
            this.j = cVar;
        }

        @Override // defpackage.i9
        public final Uri b() {
            try {
                Context context = xz.a;
                SelectFileDialog selectFileDialog = SelectFileDialog.this;
                String[] strArr = SelectFileDialog.l;
                selectFileDialog.getClass();
                return ContentUriUtils.b(File.createTempFile(String.valueOf(System.currentTimeMillis()), ".jpg", uh3.c(context)));
            } catch (IOException e) {
                Log.e("cr_SelectFileDialog", "Cannot retrieve content uri from file", e);
                return null;
            }
        }

        @Override // defpackage.i9
        public final void l(Uri uri) {
            Uri uri2 = uri;
            SelectFileDialog selectFileDialog = SelectFileDialog.this;
            selectFileDialog.e = uri2;
            Intent intentD = null;
            Boolean bool = this.h;
            if (uri2 == null) {
                if ((selectFileDialog.c && selectFileDialog.e("image")) || bool.booleanValue()) {
                    selectFileDialog.m();
                    return;
                } else {
                    selectFileDialog.l(null);
                    return;
                }
            }
            if (!bool.booleanValue()) {
                selectFileDialog.l(SelectFileDialog.d(selectFileDialog));
                return;
            }
            if (selectFileDialog.e("video")) {
                boolean zHasPermission = selectFileDialog.f.hasPermission("android.permission.CAMERA");
                if (selectFileDialog.h && zHasPermission) {
                    intentD = new Intent("android.media.action.VIDEO_CAPTURE");
                }
            } else {
                intentD = SelectFileDialog.d(selectFileDialog);
            }
            this.i.u(intentD, this.j, Integer.valueOf(R$string.low_memory_error));
        }
    }

    public class c extends i9<String[]> {
        public String[] h;
        public final Context i;
        public final boolean j;
        public final Uri[] k;

        public c(Context context, boolean z, Uri[] uriArr) {
            this.i = context;
            this.j = z;
            this.k = uriArr;
        }

        @Override // defpackage.i9
        @SuppressLint({"NewApi"})
        public final String[] b() {
            boolean zEquals;
            Context context;
            Uri[] uriArr = this.k;
            this.h = new String[uriArr.length];
            String[] strArr = new String[uriArr.length];
            for (int i = 0; i < uriArr.length; i++) {
                try {
                    zEquals = "file".equals(uriArr[i].getScheme());
                    context = this.i;
                } catch (SecurityException unused) {
                    Log.w("cr_SelectFileDialog", "Unable to extract results from the content provider");
                }
                if (zEquals) {
                    if (SelectFileDialog.j(context, uriArr[i].getSchemeSpecificPart())) {
                        return null;
                    }
                    this.h[i] = uriArr[i].getSchemeSpecificPart();
                    strArr[i] = ContentUriUtils.c(uriArr[i], context);
                } else {
                    if ("content".equals(uriArr[i].getScheme()) && SelectFileDialog.i(context, uriArr[i])) {
                        return null;
                    }
                    this.h[i] = uriArr[i].toString();
                    strArr[i] = ContentUriUtils.c(uriArr[i], context);
                }
            }
            return strArr;
        }

        @Override // defpackage.i9
        public final void l(String[] strArr) {
            String[] strArr2 = strArr;
            SelectFileDialog selectFileDialog = SelectFileDialog.this;
            if (strArr2 == null) {
                String[] strArr3 = SelectFileDialog.l;
                selectFileDialog.m();
            } else {
                if (!this.j) {
                    selectFileDialog.n(selectFileDialog.a, this.h[0], strArr2[0]);
                    return;
                }
                long j = selectFileDialog.a;
                String[] strArr4 = this.h;
                selectFileDialog.p(strArr4);
                N.Mx1807vz(j, selectFileDialog, strArr4, strArr2);
            }
        }
    }

    public final class d extends i9<Boolean> {
        public final String[] h;
        public final boolean i;

        public d(String[] strArr, boolean z) {
            this.h = strArr;
            this.i = z;
        }

        /* JADX WARN: Removed duplicated region for block: B:38:0x0085  */
        /* JADX WARN: Removed duplicated region for block: B:64:0x00dd  */
        /* JADX WARN: Removed duplicated region for block: B:65:0x00df  */
        @Override // defpackage.i9
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Boolean b() {
            /*
                Method dump skipped, instruction units count: 239
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: org.chromium.ui.base.SelectFileDialog.d.b():java.lang.Object");
        }

        @Override // defpackage.i9
        public final /* bridge */ /* synthetic */ void l(Boolean bool) {
        }
    }

    public SelectFileDialog(long j) {
        this.a = j;
    }

    @CalledByNative
    public static SelectFileDialog create(long j) {
        return new SelectFileDialog(j);
    }

    public static Intent d(SelectFileDialog selectFileDialog) {
        selectFileDialog.getClass();
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.setFlags(3);
        intent.putExtra("output", selectFileDialog.e);
        ku2 ku2VarT = ku2.t();
        try {
            intent.setClipData(ClipData.newUri(xz.a.getContentResolver(), "images", selectFileDialog.e));
            ku2VarT.close();
            return intent;
        } catch (Throwable th) {
            try {
                ku2VarT.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    public static ArrayList g(List list) {
        if (list.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String mimeTypeFromExtension = (String) it.next();
            if (mimeTypeFromExtension.length() == 0) {
                mimeTypeFromExtension = "";
            } else {
                String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(mimeTypeFromExtension);
                if (fileExtensionFromUrl.length() > 0 && (mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtensionFromUrl)) == null) {
                    mimeTypeFromExtension = "application/octet-stream";
                }
            }
            if (!mimeTypeFromExtension.startsWith("image/")) {
                if (!(n != null) || !mimeTypeFromExtension.startsWith("video/")) {
                    return null;
                }
            }
            if (!arrayList.contains(mimeTypeFromExtension)) {
                arrayList.add(mimeTypeFromExtension);
            }
        }
        return arrayList;
    }

    public static boolean i(Context context, Uri uri) {
        try {
            return j(context, Files.readSymbolicLink(Paths.get("/proc/self/fd/" + context.getContentResolver().openFileDescriptor(uri, "r").getFd(), new String[0])).toString());
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean j(Context context, String str) {
        File file = new File(str);
        Object obj = bz.a;
        try {
            return file.getCanonicalPath().startsWith(context.getDataDir().getCanonicalPath());
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean o() {
        if (!yj.a()) {
            return false;
        }
        vd1 vd1Var = n;
        if (!(vd1Var != null)) {
            return false;
        }
        vd1Var.getClass();
        return N.MRiRQ_Ey(N.MFo$BeWw(0));
    }

    @Override // defpackage.y02
    public final void a(int i, Uri[] uriArr) {
        if (i == 0) {
            m();
            return;
        }
        if (i == 1) {
            if (uriArr.length == 0) {
                m();
                return;
            } else {
                new c(xz.a, uriArr.length > 1, uriArr).c(i9.e);
                return;
            }
        }
        if (i != 2) {
            if (i != 3) {
                return;
            }
            this.j = false;
            r(null, null, null);
            return;
        }
        if (this.f.hasPermission("android.permission.CAMERA")) {
            new b(Boolean.TRUE, this.f, this).c(i9.e);
        } else {
            this.f.b(new String[]{"android.permission.CAMERA"}, new ke0(6, this));
        }
    }

    @Override // org.chromium.ui.base.WindowAndroid.c
    public final void b(Intent intent, int i) {
        w02 w02Var = o;
        if (w02Var != null) {
            h hVar = (h) w02Var;
            hVar.m = true;
            hVar.dismiss();
        }
        if (i != -1) {
            m();
            return;
        }
        if (intent == null || (intent.getData() == null && intent.getClipData() == null)) {
            String path = "file".equals(this.e.getScheme()) ? this.e.getPath() : this.e.toString();
            String schemeSpecificPart = this.e.getSchemeSpecificPart();
            this.f.getClass();
            if (j(xz.a, schemeSpecificPart)) {
                m();
                return;
            }
            n(this.a, path, this.e.getLastPathSegment());
            WindowAndroid windowAndroid = this.f;
            Intent intent2 = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", this.e);
            windowAndroid.getClass();
            xz.a.sendBroadcast(intent2);
            return;
        }
        if (intent.getData() == null && intent.getClipData() != null) {
            ClipData clipData = intent.getClipData();
            int itemCount = clipData.getItemCount();
            if (itemCount == 0) {
                m();
                return;
            }
            Uri[] uriArr = new Uri[itemCount];
            for (int i2 = 0; i2 < itemCount; i2++) {
                uriArr[i2] = clipData.getItemAt(i2).getUri();
            }
            new c(xz.a, true, uriArr).c(i9.e);
            return;
        }
        if ("file".equals(intent.getData().getScheme())) {
            String path2 = intent.getData().getPath();
            if (!TextUtils.isEmpty(path2)) {
                new a(xz.a, path2).c(i9.e);
                return;
            }
        }
        if ("content".equals(intent.getScheme())) {
            new c(xz.a, false, new Uri[]{intent.getData()}).c(i9.e);
            return;
        }
        m();
        int i3 = R$string.opening_file_error;
        nx0<Activity> nx0Var = WindowAndroid.A;
        String string = xz.a.getString(i3);
        if (string != null) {
            Toast.b(0, xz.a, string).e();
        }
    }

    @Override // defpackage.y02
    public final void c() {
        o = null;
    }

    public final boolean e(String str) {
        return h(str) == this.b.size();
    }

    public final boolean f(String str) {
        return this.b.isEmpty() || this.b.contains("*/*") || h(str) > 0;
    }

    public final int h(String str) {
        Iterator it = this.b.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (((String) it.next()).startsWith(str)) {
                i++;
            }
        }
        return i;
    }

    public final void k() {
        boolean zHasPermission = this.f.hasPermission("android.permission.CAMERA");
        if (this.g && zHasPermission) {
            new b(Boolean.FALSE, this.f, this).c(i9.e);
        } else {
            l(null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x006f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void l(android.content.Intent r17) {
        /*
            Method dump skipped, instruction units count: 471
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.ui.base.SelectFileDialog.l(android.content.Intent):void");
    }

    public final void m() {
        p(new String[0]);
        N.MGVJOCWv(this.a, this);
    }

    public final void n(long j, String str, String str2) {
        p(new String[]{str});
        N.MBeWYy2V(j, this, str, str2);
    }

    public final void p(String[] strArr) {
        if (g(this.b) != null) {
            oj2.Q(strArr.length, "Android.SelectFileDialogImgCount");
        }
        new d(strArr, this.j).c(i9.e);
    }

    public final boolean q() {
        ArrayList arrayListG = g(this.b);
        if (!(this.c && e("image")) && arrayListG != null) {
            if ((n != null) && this.f.k().get() != null) {
                return true;
            }
        }
        return false;
    }

    public final void r(Intent intent, Intent intent2, Intent intent3) {
        boolean zContains;
        Intent intent4 = new Intent("android.intent.action.GET_CONTENT");
        if (this.d) {
            intent4.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
        }
        ArrayList arrayList = new ArrayList();
        if (this.b.size() == 1) {
            zContains = true ^ this.b.contains("*/*");
        } else {
            String str = null;
            boolean z = false;
            for (String str2 : this.b) {
                int iIndexOf = str2.indexOf(47);
                if (iIndexOf != -1) {
                    String strSubstring = str2.substring(0, iIndexOf);
                    boolean zEquals = str2.substring(iIndexOf + 1).equals("*");
                    if (str == null) {
                        str = strSubstring;
                    } else if (!str.equals(strSubstring)) {
                    }
                    if (zEquals) {
                        z = true;
                    }
                }
                zContains = false;
            }
            zContains = z;
        }
        if (zContains) {
            if (f("image")) {
                if (intent != null) {
                    arrayList.add(intent);
                }
                intent4.setType("image/*");
            } else if (f("video")) {
                if (intent2 != null) {
                    arrayList.add(intent2);
                }
                intent4.setType("video/*");
            } else if (f("audio")) {
                if (intent3 != null) {
                    arrayList.add(intent3);
                }
                intent4.setType("audio/*");
            }
            intent4.addCategory("android.intent.category.OPENABLE");
        }
        if (intent4.getType() == null) {
            intent4.setType("*/*");
            if (intent != null) {
                arrayList.add(intent);
            }
            if (intent2 != null) {
                arrayList.add(intent2);
            }
            if (intent3 != null) {
                arrayList.add(intent3);
            }
        }
        Intent intent5 = new Intent("android.intent.action.CHOOSER");
        if (!arrayList.isEmpty()) {
            intent5.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new Intent[0]));
        }
        intent5.putExtra("android.intent.extra.INTENT", intent4);
        if (this.f.u(intent5, this, Integer.valueOf(R$string.low_memory_error))) {
            return;
        }
        m();
    }

    @CalledByNative
    public final void selectFile(String[] strArr, boolean z, boolean z2, WindowAndroid windowAndroid) {
        this.b = new ArrayList(Arrays.asList(strArr));
        this.c = z;
        this.d = z2;
        this.f = windowAndroid;
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        windowAndroid.getClass();
        this.g = wt1.a(intent, 0);
        WindowAndroid windowAndroid2 = this.f;
        Intent intent2 = new Intent("android.media.action.VIDEO_CAPTURE");
        windowAndroid2.getClass();
        this.h = wt1.a(intent2, 0);
        WindowAndroid windowAndroid3 = this.f;
        Intent intent3 = new Intent("android.provider.MediaStore.RECORD_SOUND");
        windowAndroid3.getClass();
        this.i = wt1.a(intent3, 0);
        ArrayList arrayList = new ArrayList();
        final boolean zQ = q();
        if (!zQ) {
            if (((this.g && f("image")) || (this.h && f("video"))) && !windowAndroid.hasPermission("android.permission.CAMERA")) {
                arrayList.add("android.permission.CAMERA");
            }
            if (this.i && f("audio") && !windowAndroid.hasPermission("android.permission.RECORD_AUDIO")) {
                arrayList.add("android.permission.RECORD_AUDIO");
            }
        } else if (yj.a()) {
            if (!o()) {
                if (!windowAndroid.hasPermission("android.permission.READ_MEDIA_IMAGES") && f("image")) {
                    arrayList.add("android.permission.READ_MEDIA_IMAGES");
                }
                if (!windowAndroid.hasPermission("android.permission.READ_MEDIA_VIDEO") && f("video")) {
                    arrayList.add("android.permission.READ_MEDIA_VIDEO");
                }
            }
        } else if (!windowAndroid.hasPermission("android.permission.READ_EXTERNAL_STORAGE")) {
            arrayList.add("android.permission.READ_EXTERNAL_STORAGE");
        }
        if (arrayList.isEmpty()) {
            k();
        } else {
            final String[] strArr2 = (String[]) arrayList.toArray(new String[arrayList.size()]);
            windowAndroid.b(strArr2, new PermissionCallback() { // from class: qh2
                @Override // org.chromium.ui.permissions.PermissionCallback
                public final void s(int[] iArr, String[] strArr3) {
                    String[] strArr4 = SelectFileDialog.l;
                    SelectFileDialog selectFileDialog = this.e;
                    selectFileDialog.getClass();
                    for (int i = 0; i < iArr.length; i++) {
                        if (iArr[i] == -1) {
                            if (selectFileDialog.c) {
                                selectFileDialog.m();
                                return;
                            }
                            boolean z3 = zQ;
                            if (z3) {
                                int length = strArr3.length;
                                String[] strArr5 = strArr2;
                                if (length != strArr5.length) {
                                    throw new RuntimeException(String.format("Permissions arrays misaligned: %d != %d", Integer.valueOf(strArr3.length), Integer.valueOf(strArr5.length)));
                                }
                                if (!strArr3[i].equals(strArr5[i])) {
                                    throw new RuntimeException(String.format("Permissions arrays don't match: %s != %s", strArr3[i], strArr5[i]));
                                }
                            }
                            if (z3 && (strArr3[i].equals("android.permission.READ_EXTERNAL_STORAGE") || strArr3[i].equals("android.permission.READ_MEDIA_IMAGES") || strArr3[i].equals("android.permission.READ_MEDIA_VIDEO"))) {
                                int i2 = R$string.permission_denied_error;
                                nx0<Activity> nx0Var = WindowAndroid.A;
                                String string = xz.a.getString(i2);
                                if (string != null) {
                                    Toast.b(0, xz.a, string).e();
                                }
                                selectFileDialog.m();
                                return;
                            }
                        }
                    }
                    selectFileDialog.k();
                }
            });
        }
    }
}
