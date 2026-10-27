package org.chromium.chrome.browser.download;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import defpackage.i9;
import defpackage.ma0;
import defpackage.oj2;
import defpackage.rh3;
import defpackage.xz;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.base.ContentUriUtils;
import org.chromium.base.PathUtils;
import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: DownloadDirectoryProvider.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {
    public boolean a;
    public boolean b;
    public a c;
    public ArrayList<ma0> d;
    public String e;
    public final ArrayList<Callback<ArrayList<ma0>>> f = new ArrayList<>();

    /* JADX INFO: compiled from: DownloadDirectoryProvider.java */
    public class a extends i9<ArrayList<ma0>> {
        public final InterfaceC0078b h;

        public a(c cVar) {
            this.h = cVar;
        }

        @Override // defpackage.i9
        public final ArrayList<ma0> b() {
            ArrayList<ma0> arrayList = new ArrayList<>();
            c cVar = (c) this.h;
            cVar.getClass();
            File fileB = b.b();
            if (fileB == null) {
                arrayList.add(new ma0(0L, 2, null, 0L));
            } else {
                arrayList.add(new ma0(fileB.getUsableSpace(), 0, fileB.getAbsolutePath(), fileB.getTotalSpace()));
                b bVar = b.this;
                bVar.getClass();
                boolean z = false;
                oj2.U(0, 3, "MobileDownload.Location.DirectoryType");
                bVar.e = Environment.getExternalStorageDirectory().getAbsolutePath();
                f fVarA = cVar.a();
                List<File> list = Build.VERSION.SDK_INT > 29 ? fVarA.a : fVarA.b;
                if (!list.isEmpty()) {
                    for (File file : list) {
                        if (file != null) {
                            arrayList.add(new ma0(file.getUsableSpace(), 1, file.getAbsolutePath(), file.getTotalSpace()));
                            z = true;
                        }
                    }
                    if (z) {
                        oj2.U(1, 3, "MobileDownload.Location.DirectoryType");
                    }
                }
            }
            return arrayList;
        }

        @Override // defpackage.i9
        public final void l(ArrayList<ma0> arrayList) {
            b bVar = b.this;
            bVar.d = arrayList;
            bVar.a = true;
            bVar.b = false;
            ArrayList<Callback<ArrayList<ma0>>> arrayList2 = bVar.f;
            Iterator<Callback<ArrayList<ma0>>> it = arrayList2.iterator();
            while (it.hasNext()) {
                it.next().onResult(bVar.d);
            }
            arrayList2.clear();
            bVar.c = null;
        }
    }

    /* JADX INFO: renamed from: org.chromium.chrome.browser.download.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DownloadDirectoryProvider.java */
    public interface InterfaceC0078b {
    }

    /* JADX INFO: compiled from: DownloadDirectoryProvider.java */
    public static class c implements InterfaceC0078b {
        public final f a() {
            ArrayList arrayList = new ArrayList();
            String[] allPrivateDownloadsDirectories = PathUtils.getAllPrivateDownloadsDirectories();
            for (int i = 1; i < allPrivateDownloadsDirectories.length; i++) {
                arrayList.add(new File(allPrivateDownloadsDirectories[i]));
            }
            ArrayList arrayList2 = new ArrayList();
            if (Build.VERSION.SDK_INT <= 29) {
                return new f(null, arrayList);
            }
            for (String str : PathUtils.getExternalDownloadVolumesNames()) {
                arrayList2.add(new File(str));
            }
            return new f(arrayList2, arrayList);
        }
    }

    /* JADX INFO: compiled from: DownloadDirectoryProvider.java */
    public final class d extends BroadcastReceiver {
        public d() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.MEDIA_REMOVED") || intent.getAction().equals("android.intent.action.MEDIA_MOUNTED") || intent.getAction().equals("android.intent.action.MEDIA_EJECT")) {
                b.this.b = true;
            }
        }
    }

    /* JADX INFO: compiled from: DownloadDirectoryProvider.java */
    public static class e {
        public static final b a = new b();
    }

    /* JADX INFO: compiled from: DownloadDirectoryProvider.java */
    public static class f {
        public final List<File> a;
        public final List<File> b;

        public f(ArrayList arrayList, ArrayList arrayList2) {
            this.a = arrayList;
            this.b = arrayList2;
        }
    }

    public b() {
        IntentFilter intentFilter = new IntentFilter("android.intent.action.MEDIA_REMOVED");
        intentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
        intentFilter.addAction("android.intent.action.MEDIA_EJECT");
        intentFilter.addDataScheme("file");
        xz.g(xz.a, new d(), intentFilter);
    }

    public static File b() {
        String downloadsDirectory = PathUtils.getDownloadsDirectory();
        if (TextUtils.isEmpty(downloadsDirectory)) {
            return null;
        }
        File file = new File(downloadsDirectory);
        if (!file.exists()) {
            try {
                file.mkdirs();
            } catch (SecurityException e2) {
                Log.e("cr_DownloadDirectory", "Exception when creating download directory.", e2);
            }
        }
        return file;
    }

    public static boolean c(String str) {
        if (!ContentUriUtils.d(str) && str != null) {
            String[] allPrivateDownloadsDirectories = PathUtils.getAllPrivateDownloadsDirectories();
            for (int i = 1; i < allPrivateDownloadsDirectories.length; i++) {
                if (str.startsWith(allPrivateDownloadsDirectories[i])) {
                    return true;
                }
            }
            if (Build.VERSION.SDK_INT >= 30) {
                for (String str2 : PathUtils.getExternalDownloadVolumesNames()) {
                    if (str.startsWith(str2)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final void a(Callback<ArrayList<ma0>> callback) {
        if (!this.b && this.a) {
            PostTask.c(rh3.a, callback.p1(this.d));
            return;
        }
        this.f.add(callback);
        if (this.c != null) {
            return;
        }
        a aVar = new a(new c());
        this.c = aVar;
        aVar.c(i9.e);
    }
}
