package org.chromium.ui.base;

import J.N;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import defpackage.AbstractC0566bn;
import defpackage.BN;
import defpackage.C1602uP;
import defpackage.InterfaceC0492aO;
import defpackage.Is;
import defpackage.Lt;
import defpackage.RN;
import defpackage.Su;
import defpackage.UN;
import defpackage.VN;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.chromium.base.PathUtils;
import org.chromium.ui.base.SelectFileDialog;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class SelectFileDialog implements InterfaceC0492aO {
    public static final String[] j;
    public static final String[] k;
    public final long a;
    public List b;
    public boolean c;
    public boolean d;
    public Uri e;
    public WindowAndroid f;
    public boolean g;
    public boolean h;
    public boolean i;

    static {
        TimeUnit.HOURS.toMillis(1L);
        j = new String[]{".apng", ".bmp", ".gif", ".jpeg", ".jpg", ".pdf", ".png", ".tif", ".tiff", ".xcf", ".webp"};
        k = new String[]{".asf", ".avhcd", ".avi", ".divx", ".flv", ".mov", ".mp4", ".mpeg", ".mpg", ".swf", ".wmv", ".webm", ".mkv"};
    }

    public SelectFileDialog(long j2) {
        this.a = j2;
    }

    public static File c(SelectFileDialog selectFileDialog, Context context) {
        Objects.requireNonNull(selectFileDialog);
        return File.createTempFile(String.valueOf(System.currentTimeMillis()), ".jpg", BN.b(context));
    }

    public static SelectFileDialog create(long j2) {
        return new SelectFileDialog(j2);
    }

    public static List f(List list) {
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
                BN.c();
                return null;
            }
            if (!arrayList.contains(mimeTypeFromExtension)) {
                arrayList.add(mimeTypeFromExtension);
            }
        }
        return arrayList;
    }

    private void selectFile(String[] strArr, boolean z, boolean z2, WindowAndroid windowAndroid) {
        this.b = new ArrayList(Arrays.asList(strArr));
        this.c = z;
        this.d = z2;
        this.f = windowAndroid;
        this.g = windowAndroid.O(new Intent("android.media.action.IMAGE_CAPTURE"));
        this.h = this.f.O(new Intent("android.media.action.VIDEO_CAPTURE"));
        this.i = this.f.O(new Intent("android.provider.MediaStore.RECORD_SOUND"));
        ArrayList arrayList = new ArrayList();
        List listF = f(this.b);
        if (!e() && listF != null) {
            BN.e();
        }
        final boolean z3 = false;
        if (((this.g && o("image/*", "image/")) || (this.h && o("video/*", "video/"))) && !windowAndroid.hasPermission("android.permission.CAMERA")) {
            arrayList.add("android.permission.CAMERA");
        }
        if (this.i && o("audio/*", "audio/") && !windowAndroid.hasPermission("android.permission.RECORD_AUDIO")) {
            arrayList.add("android.permission.RECORD_AUDIO");
        }
        if (arrayList.isEmpty()) {
            i();
            return;
        }
        final String[] strArr2 = (String[]) arrayList.toArray(new String[arrayList.size()]);
        final String str = "android.permission.READ_EXTERNAL_STORAGE";
        windowAndroid.E(strArr2, new RN(this, z3, strArr2, str) { // from class: TN
            public final SelectFileDialog a;
            public final boolean b;
            public final String[] c;
            public final String d;

            {
                this.a = this;
                this.b = z3;
                this.c = strArr2;
                this.d = str;
            }

            @Override // defpackage.RN
            public void a(String[] strArr3, int[] iArr) {
                SelectFileDialog selectFileDialog = this.a;
                Objects.requireNonNull(selectFileDialog);
                for (int i : iArr) {
                    if (i == -1 && selectFileDialog.c) {
                        selectFileDialog.l();
                        return;
                    }
                }
                selectFileDialog.i();
            }
        });
    }

    @Override // defpackage.InterfaceC0492aO
    public void a(WindowAndroid windowAndroid, int i, Intent intent) {
        if (i != -1) {
            l();
            return;
        }
        if (intent == null || (intent.getData() == null && intent.getClipData() == null)) {
            m(this.a, "file".equals(this.e.getScheme()) ? this.e.getPath() : this.e.toString(), this.e.getLastPathSegment());
            Is.a.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", this.e));
            return;
        }
        if (intent.getData() == null && intent.getClipData() != null) {
            ClipData clipData = intent.getClipData();
            int itemCount = clipData.getItemCount();
            if (itemCount == 0) {
                l();
                return;
            }
            Uri[] uriArr = new Uri[itemCount];
            for (int i2 = 0; i2 < itemCount; i2++) {
                uriArr[i2] = clipData.getItemAt(i2).getUri();
            }
            new VN(this, Is.a, true, uriArr).d(Su.f);
            return;
        }
        if ("file".equals(intent.getData().getScheme())) {
            String path = intent.getData().getPath();
            if (!TextUtils.isEmpty(path) && !path.startsWith(PathUtils.getDataDirectory())) {
                m(this.a, path, "");
                return;
            }
        }
        if ("content".equals(intent.getScheme())) {
            new VN(this, Is.a, false, new Uri[]{intent.getData()}).d(Su.f);
            return;
        }
        l();
        String string = Is.a.getString(AbstractC0566bn.c2);
        if (string != null) {
            C1602uP.a(Is.a, string, 0).a.show();
        }
    }

    public final boolean b(String str) {
        return this.b.size() == 1 && TextUtils.equals((CharSequence) this.b.get(0), str);
    }

    public final boolean e() {
        return this.c && b("image/*");
    }

    public final int g(String str) {
        Iterator it = this.b.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (((String) it.next()).startsWith(str)) {
                i++;
            }
        }
        return i;
    }

    public final boolean h() {
        return f(this.b) != null;
    }

    public final void i() {
        boolean zHasPermission = this.f.hasPermission("android.permission.CAMERA");
        if (this.g && zHasPermission) {
            new UN(this, Boolean.FALSE, this.f, this).d(Su.f);
        } else {
            j(null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x006a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void j(android.content.Intent r13) {
        /*
            Method dump skipped, instruction units count: 466
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.ui.base.SelectFileDialog.j(android.content.Intent):void");
    }

    public final boolean k() {
        return this.b.size() != 1 || this.b.contains("*/*");
    }

    public final void l() {
        long j2 = this.a;
        if (h()) {
            n(0);
        }
        N.MGVJOCWv(j2, this);
    }

    public final void m(long j2, String str, String str2) {
        if (h()) {
            n(1);
        }
        N.MBeWYy2V(j2, this, str, str2);
    }

    public final void n(int i) {
        Lt.a.e("Android.SelectFileDialogImgCount", i, 1, 100, 50);
    }

    public final boolean o(String str, String str2) {
        return k() || this.b.contains(str) || g(str2) > 0;
    }
}
