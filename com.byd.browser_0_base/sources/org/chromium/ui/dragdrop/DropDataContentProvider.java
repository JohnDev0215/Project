package org.chromium.ui.dragdrop;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.SystemClock;
import android.webkit.MimeTypeMap;
import defpackage.an2;
import defpackage.oj2;
import defpackage.xz;

/* JADX INFO: loaded from: classes3.dex */
public class DropDataContentProvider extends ContentProvider {
    public DropDataProviderImpl e;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.content.ContentProvider
    public final Bundle call(String str, String str2, Bundle bundle) {
        DropDataProviderImpl dropDataProviderImpl;
        dropDataProviderImpl = this.e;
        dropDataProviderImpl.getClass();
        str.getClass();
        switch (str) {
            case "onDragEnd":
                if (bundle.getBoolean("imageIsInUse")) {
                    synchronized (DropDataProviderImpl.p) {
                        if (dropDataProviderImpl.f == null) {
                            dropDataProviderImpl.f = new Handler(Looper.getMainLooper());
                        }
                        dropDataProviderImpl.f.postDelayed(new an2(12, dropDataProviderImpl), dropDataProviderImpl.a);
                        dropDataProviderImpl.g = SystemClock.elapsedRealtime();
                        break;
                    }
                } else {
                    dropDataProviderImpl.a();
                }
                return null;
            case "cache":
                Bundle bundle2 = new Bundle();
                byte[] bArr = (byte[]) bundle.getSerializable("bytes");
                String string = bundle.getString("imageContentExtension");
                String string2 = bundle.getString("imageFilename");
                long jElapsedRealtime = SystemClock.elapsedRealtime();
                long j = dropDataProviderImpl.k;
                String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(string);
                Uri uriBuild = new Uri.Builder().scheme("content").authority(xz.a.getPackageName() + ".DropDataProvider").path(String.valueOf(System.currentTimeMillis())).build();
                synchronized (DropDataProviderImpl.p) {
                    dropDataProviderImpl.b();
                    dropDataProviderImpl.k = jElapsedRealtime;
                    dropDataProviderImpl.b = bArr;
                    dropDataProviderImpl.c = string2;
                    dropDataProviderImpl.d = mimeTypeFromExtension;
                    dropDataProviderImpl.g = 0L;
                    dropDataProviderImpl.h = 0L;
                    dropDataProviderImpl.e = uriBuild;
                    break;
                }
                if (j > 0) {
                    oj2.Y(jElapsedRealtime - j, "Android.DragDrop.Image.UriCreatedInterval");
                }
                oj2.S(bArr.length / 1024, 1, 100000, 50, "Android.DragDrop.Image.Size");
                bundle2.putParcelable("uri", uriBuild);
                return bundle2;
            case "setClearCachedDataIntervalMs":
                int i = bundle.getInt("clearCacheDelayedMs", 60000);
                synchronized (DropDataProviderImpl.p) {
                    dropDataProviderImpl.a = i;
                    break;
                }
                return null;
            default:
                return null;
        }
    }

    @Override // android.content.ContentProvider
    public final int delete(Uri uri, String str, String[] strArr) {
        throw new UnsupportedOperationException();
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x005a  */
    @Override // android.content.ContentProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String[] getStreamTypes(android.net.Uri r8, java.lang.String r9) {
        /*
            r7 = this;
            org.chromium.ui.dragdrop.DropDataProviderImpl r7 = r7.e
            r7.getClass()
            java.lang.Object r0 = org.chromium.ui.dragdrop.DropDataProviderImpl.p
            monitor-enter(r0)
            r1 = 0
            if (r8 == 0) goto L5f
            android.net.Uri r2 = r7.e     // Catch: java.lang.Throwable -> L61
            boolean r8 = r8.equals(r2)     // Catch: java.lang.Throwable -> L61
            if (r8 != 0) goto L14
            goto L5f
        L14:
            java.lang.String r7 = r7.d     // Catch: java.lang.Throwable -> L61
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L61
            r8 = 1
            r0 = 0
            if (r7 == 0) goto L57
            if (r9 != 0) goto L1e
            goto L57
        L1e:
            r2 = 47
            int r3 = r7.indexOf(r2)
            java.lang.String r4 = r7.substring(r0, r3)
            int r3 = r3 + r8
            java.lang.String r3 = r7.substring(r3)
            int r2 = r9.indexOf(r2)
            java.lang.String r5 = r9.substring(r0, r2)
            int r2 = r2 + r8
            java.lang.String r9 = r9.substring(r2)
            java.lang.String r2 = "*"
            boolean r6 = r5.equals(r2)
            if (r6 != 0) goto L49
            boolean r4 = r5.equals(r4)
            if (r4 != 0) goto L49
            goto L57
        L49:
            boolean r2 = r9.equals(r2)
            if (r2 != 0) goto L55
            boolean r9 = r9.equals(r3)
            if (r9 == 0) goto L57
        L55:
            r9 = r8
            goto L58
        L57:
            r9 = r0
        L58:
            if (r9 == 0) goto L60
            java.lang.String[] r1 = new java.lang.String[r8]
            r1[r0] = r7
            goto L60
        L5f:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L61
        L60:
            return r1
        L61:
            r7 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L61
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.ui.dragdrop.DropDataContentProvider.getStreamTypes(android.net.Uri, java.lang.String):java.lang.String[]");
    }

    @Override // android.content.ContentProvider
    public final String getType(Uri uri) {
        String str;
        DropDataProviderImpl dropDataProviderImpl = this.e;
        dropDataProviderImpl.getClass();
        synchronized (DropDataProviderImpl.p) {
            if (uri != null) {
                str = !uri.equals(dropDataProviderImpl.e) ? null : dropDataProviderImpl.d;
            }
        }
        return str;
    }

    @Override // android.content.ContentProvider
    public final Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContentProvider
    public final boolean onCreate() {
        this.e = new DropDataProviderImpl();
        return true;
    }

    @Override // android.content.ContentProvider
    public final ParcelFileDescriptor openFile(Uri uri, String str) {
        DropDataProviderImpl dropDataProviderImpl = this.e;
        dropDataProviderImpl.getClass();
        String str2 = null;
        if (uri == null) {
            return null;
        }
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        Object obj = DropDataProviderImpl.p;
        synchronized (obj) {
            if (!uri.equals(dropDataProviderImpl.e)) {
                if (uri.equals(dropDataProviderImpl.i)) {
                    long j = jElapsedRealtime - dropDataProviderImpl.j;
                    oj2.Y(j, "Android.DragDrop.Image.OpenFileTime.AllExpired");
                    if (!dropDataProviderImpl.l) {
                        oj2.Y(j, "Android.DragDrop.Image.OpenFileTime.FirstExpired");
                        dropDataProviderImpl.l = true;
                    }
                }
                return null;
            }
            long j2 = 0;
            if (dropDataProviderImpl.h == 0) {
                long j3 = dropDataProviderImpl.g;
                if (j3 != 0) {
                    j2 = jElapsedRealtime - j3;
                }
                oj2.Y(j2, "Android.DragDrop.Image.OpenFileTime.FirstAttempt");
            }
            dropDataProviderImpl.h = jElapsedRealtime;
            byte[] bArr = dropDataProviderImpl.b;
            synchronized (obj) {
                try {
                    if (uri.equals(dropDataProviderImpl.e)) {
                        str2 = dropDataProviderImpl.d;
                    }
                } finally {
                }
            }
            return openPipeHelper(uri, str2, null, bArr, dropDataProviderImpl.m);
        }
    }

    @Override // android.content.ContentProvider
    public final Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        MatrixCursor matrixCursor;
        DropDataProviderImpl dropDataProviderImpl = this.e;
        dropDataProviderImpl.getClass();
        synchronized (DropDataProviderImpl.p) {
            char c = 0;
            if (uri == null) {
                matrixCursor = new MatrixCursor(DropDataProviderImpl.o, 0);
            } else if (uri.equals(dropDataProviderImpl.e)) {
                byte[] bArr = dropDataProviderImpl.b;
                String str3 = dropDataProviderImpl.c;
                if (strArr == null) {
                    strArr = DropDataProviderImpl.o;
                }
                int i = 0;
                boolean z = false;
                boolean z2 = false;
                for (String str4 : strArr) {
                    if ("_display_name".equals(str4)) {
                        i++;
                        z = true;
                    } else if ("_size".equals(str4)) {
                        i++;
                        z2 = true;
                    }
                }
                String[] strArr3 = new String[i];
                Object[] objArr = new Object[i];
                if (z) {
                    strArr3[0] = "_display_name";
                    objArr[0] = str3;
                    c = 1;
                }
                if (z2) {
                    strArr3[c] = "_size";
                    objArr[c] = Integer.valueOf(bArr.length);
                }
                matrixCursor = new MatrixCursor(strArr3, 1);
                matrixCursor.addRow(objArr);
            } else {
                matrixCursor = new MatrixCursor(DropDataProviderImpl.o, 0);
            }
        }
        return matrixCursor;
    }

    @Override // android.content.ContentProvider
    public final int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException();
    }
}
