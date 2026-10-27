package defpackage;

import J.N;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.Log;
import defpackage.lk;
import defpackage.p93;
import defpackage.q93;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.thumbnail.generator.ThumbnailGenerator;
import org.chromium.chrome.browser.thumbnail.generator.ThumbnailGeneratorCallback;

/* JADX INFO: compiled from: ThumbnailDiskStorage.java */
/* JADX INFO: loaded from: classes2.dex */
public final class r93 implements ThumbnailGeneratorCallback {
    public static final LinkedHashSet<qv1<String, Integer>> g = new LinkedHashSet<>();
    public static final HashMap<String, HashSet<Integer>> h = new HashMap<>();
    public final ThumbnailGenerator a;
    public File b;
    public final v93 c;
    public final int d;
    public long e;
    public boolean f;

    /* JADX INFO: compiled from: ThumbnailDiskStorage.java */
    public class a extends ac<Void> {
        public final String h;
        public final Bitmap i;
        public final int j;

        public a(String str, Bitmap bitmap, int i) {
            this.h = str;
            this.i = bitmap;
            this.j = i;
        }

        @Override // defpackage.i9
        public final Object b() {
            j9 j9Var;
            FileOutputStream fileOutputStreamF;
            Bitmap bitmap = this.i;
            r93 r93Var = r93.this;
            r93Var.getClass();
            Object obj = ThreadUtils.a;
            if (r93Var.b != null) {
                LinkedHashSet<qv1<String, Integer>> linkedHashSet = r93.g;
                int i = this.j;
                Integer numValueOf = Integer.valueOf(i);
                String str = this.h;
                if (linkedHashSet.contains(new qv1(str, numValueOf))) {
                    r93Var.c(new qv1<>(str, Integer.valueOf(i)));
                }
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    q93.a aVarZ = q93.Z();
                    p93.a aVarT = p93.T();
                    aVarT.y();
                    p93.O((p93) aVarT.f, str);
                    aVarZ.y();
                    q93.P((q93) aVarZ.f, aVarT.build());
                    aVarZ.y();
                    q93.Q((q93) aVarZ.f, i);
                    lk.f fVar = lk.f;
                    lk.f fVarD = lk.d(byteArray, 0, byteArray.length);
                    aVarZ.y();
                    q93.O((q93) aVarZ.f, fVarD);
                    q93 q93VarBuild = aVarZ.build();
                    File file = new File(r93Var.b(i, str));
                    j9Var = new j9(file);
                    try {
                        fileOutputStreamF = j9Var.f();
                    } catch (IOException e) {
                        e = e;
                        fileOutputStreamF = null;
                    }
                    try {
                        fileOutputStreamF.write(q93VarBuild.q());
                        j9Var.b(fileOutputStreamF);
                        linkedHashSet.add(new qv1<>(str, Integer.valueOf(i)));
                        HashMap<String, HashSet<Integer>> map = r93.h;
                        if (map.containsKey(str)) {
                            map.get(str).add(Integer.valueOf(i));
                        } else {
                            HashSet<Integer> hashSet = new HashSet<>();
                            hashSet.add(Integer.valueOf(i));
                            map.put(str, hashSet);
                        }
                        r93Var.e += file.length();
                        while (r93Var.e > r93Var.d) {
                            r93Var.c(linkedHashSet.iterator().next());
                        }
                    } catch (IOException e2) {
                        e = e2;
                        Log.e("cr_ThumbnailStorage", "Error while writing to disk.", e);
                        j9Var.a(fileOutputStreamF);
                    }
                } catch (IOException e3) {
                    e = e3;
                    j9Var = null;
                    fileOutputStreamF = null;
                }
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: ThumbnailDiskStorage.java */
    public class b extends i9<Bitmap> {
        public final s93 h;

        public b(s93 s93Var) {
            this.h = s93Var;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [boolean] */
        /* JADX WARN: Type inference failed for: r0v10 */
        /* JADX WARN: Type inference failed for: r0v2 */
        /* JADX WARN: Type inference failed for: r0v5, types: [java.io.Closeable] */
        /* JADX WARN: Type inference failed for: r0v8 */
        /* JADX WARN: Type inference failed for: r0v9 */
        /* JADX WARN: Type inference failed for: r3v10 */
        /* JADX WARN: Type inference failed for: r3v4 */
        /* JADX WARN: Type inference failed for: r3v5, types: [java.io.Closeable] */
        @Override // defpackage.i9
        public final Bitmap b() throws Throwable {
            FileInputStream fileInputStreamC;
            File file;
            LinkedHashSet<qv1<String, Integer>> linkedHashSet = r93.g;
            s93 s93Var = this.h;
            Bitmap bitmapDecodeByteArray = null;
            bitmapDecodeByteArray = null;
            bitmapDecodeByteArray = null;
            bitmapDecodeByteArray = null;
            ?? r3 = 0;
            bitmapDecodeByteArray = null;
            bitmapDecodeByteArray = null;
            if (linkedHashSet.contains(new qv1(s93Var.c(), Integer.valueOf(s93Var.b())))) {
                String strC = s93Var.c();
                int iB = s93Var.b();
                r93 r93Var = r93.this;
                r93Var.getClass();
                Object obj = ThreadUtils.a;
                if (r93Var.b != null) {
                    ?? Contains = linkedHashSet.contains(new qv1(strC, Integer.valueOf(iB)));
                    try {
                        if (Contains != 0) {
                            try {
                                file = new File(r93Var.b(iB, strC));
                            } catch (IOException e) {
                                e = e;
                                fileInputStreamC = null;
                            } catch (Throwable th) {
                                th = th;
                                a8.s(r3);
                                throw th;
                            }
                            if (file.exists()) {
                                j9 j9Var = new j9(file);
                                fileInputStreamC = j9Var.c();
                                try {
                                    q93 q93VarA0 = q93.a0(j9Var.d());
                                    if (q93VarA0.W()) {
                                        bitmapDecodeByteArray = BitmapFactory.decodeByteArray(q93VarA0.T().x(), 0, q93VarA0.T().size());
                                        Contains = fileInputStreamC;
                                    } else {
                                        Contains = fileInputStreamC;
                                    }
                                } catch (IOException e2) {
                                    e = e2;
                                    Log.e("cr_ThumbnailStorage", "Error while reading from disk.", e);
                                    Contains = fileInputStreamC;
                                }
                                a8.s(Contains);
                            }
                            Log.e("cr_ThumbnailStorage", "Error while reading from disk.", e);
                            Contains = fileInputStreamC;
                            a8.s(Contains);
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        r3 = Contains;
                    }
                }
            }
            return bitmapDecodeByteArray;
        }

        @Override // defpackage.i9
        public final void l(Bitmap bitmap) {
            Bitmap bitmap2 = bitmap;
            oj2.O("Android.ThumbnailDiskStorage.CachedBitmap.Found", bitmap2 != null);
            r93 r93Var = r93.this;
            s93 s93Var = this.h;
            if (bitmap2 != null) {
                r93Var.a(s93Var.b(), bitmap2, s93Var.c());
                return;
            }
            ThumbnailGenerator thumbnailGenerator = r93Var.a;
            thumbnailGenerator.getClass();
            Object obj = ThreadUtils.a;
            TextUtils.isEmpty(s93Var.e());
            if (thumbnailGenerator.a == 0) {
                thumbnailGenerator.a = N.McvDi7ks(thumbnailGenerator);
            }
            N.MIn_XlLY(thumbnailGenerator.a, thumbnailGenerator, s93Var.c(), s93Var.e(), s93Var.f(), s93Var.b(), r93Var);
        }
    }

    /* JADX INFO: compiled from: ThumbnailDiskStorage.java */
    public class c extends ac<Void> {
        public c() {
        }

        @Override // defpackage.i9
        public final Object b() {
            boolean zMkdir;
            r93 r93Var = r93.this;
            if (r93Var.b != null) {
                return null;
            }
            Object obj = ThreadUtils.a;
            File file = new File(C0175b.k(xz.a.getCacheDir().getPath(), File.separator, "thumbnails"));
            r93Var.b = file;
            if (!file.exists()) {
                try {
                    zMkdir = r93Var.b.mkdir();
                } catch (SecurityException e) {
                    Log.e("cr_ThumbnailStorage", "Error while creating thumbnails directory.", e);
                    zMkdir = false;
                }
                if (!zMkdir) {
                    return null;
                }
            }
            File[] fileArrListFiles = r93Var.b.listFiles();
            if (fileArrListFiles == null) {
                return null;
            }
            for (File file2 : fileArrListFiles) {
                try {
                    q93 q93VarA0 = q93.a0(new j9(file2).d());
                    if (q93VarA0.X()) {
                        String strS = q93VarA0.U().S();
                        if (q93VarA0.Y()) {
                            int iV = q93VarA0.V();
                            r93.g.add(new qv1<>(strS, Integer.valueOf(iV)));
                            HashMap<String, HashSet<Integer>> map = r93.h;
                            if (map.containsKey(strS)) {
                                map.get(strS).add(Integer.valueOf(iV));
                            } else {
                                HashSet<Integer> hashSet = new HashSet<>();
                                hashSet.add(Integer.valueOf(iV));
                                map.put(strS, hashSet);
                            }
                            r93Var.e += file2.length();
                        }
                    }
                } catch (IOException e2) {
                    Log.e("cr_ThumbnailStorage", "Error while reading from disk.", e2);
                }
            }
            zh3.a.c((int) (r93Var.e / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID), 1000, 500000, 50, "Android.ThumbnailDiskStorage.Size");
            return null;
        }
    }

    public r93(v93 v93Var, ThumbnailGenerator thumbnailGenerator) {
        Object obj = ThreadUtils.a;
        this.c = v93Var;
        this.a = thumbnailGenerator;
        this.d = 5242880;
        new c().c(i9.f);
    }

    @Override // org.chromium.chrome.browser.thumbnail.generator.ThumbnailGeneratorCallback
    public final void a(int i, Bitmap bitmap, String str) {
        if (this.f) {
            return;
        }
        Object obj = ThreadUtils.a;
        if (bitmap != null && !TextUtils.isEmpty(str)) {
            new a(str, bitmap, i).c(i9.f);
        }
        ((t93) this.c).b(bitmap, str);
    }

    public final String b(int i, String str) {
        return this.b.getPath() + File.separator + str + i + ".entry";
    }

    public final void c(qv1<String, Integer> qv1Var) {
        long length;
        Object obj = ThreadUtils.a;
        String str = qv1Var.a;
        int iIntValue = qv1Var.b.intValue();
        File file = new File(b(iIntValue, str));
        if (!file.exists()) {
            Log.e("cr_ThumbnailStorage", "Error while removing from disk. File does not exist.");
            return;
        }
        try {
            length = file.length();
        } catch (SecurityException e) {
            Log.e("cr_ThumbnailStorage", "Error while removing from disk. File denied read access.", e);
            length = 0;
        }
        File file2 = new File(file.getPath() + ".new");
        File file3 = new File(file.getPath() + ".bak");
        file.delete();
        file2.delete();
        file3.delete();
        g.remove(qv1Var);
        HashMap<String, HashSet<Integer>> map = h;
        map.get(str).remove(Integer.valueOf(iIntValue));
        if (map.get(str).size() == 0) {
            map.remove(str);
        }
        this.e -= length;
    }
}
