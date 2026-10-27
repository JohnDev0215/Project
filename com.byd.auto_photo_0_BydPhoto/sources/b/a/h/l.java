package b.a.h;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.LruCache;
import android.webkit.URLUtil;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import b.a.h.k;
import com.byd.common.R$id;
import com.byd.util.m;
import com.byd.util.q;
import com.byd.util.w;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes2.dex */
public class l {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static volatile l f502b;
    private Context d;
    private String f;
    private ExecutorService g;
    private ExecutorService h;
    private List<String> j;
    private Map<String, Future> k;
    private volatile boolean l;
    private k m;
    private g o;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int f501a = R$id.imageview_tag_id;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static ConcurrentLinkedDeque<f> f503c = new ConcurrentLinkedDeque<>();
    private j n = new b();
    private Map<String, Set<ImageView>> i = new HashMap();
    private LruCache<String, Bitmap> e = new a((int) (Runtime.getRuntime().maxMemory() / 8));

    class a extends LruCache<String, Bitmap> {
        a(int i) {
            super(i);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.util.LruCache
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void entryRemoved(boolean z, String str, Bitmap bitmap, Bitmap bitmap2) {
            if (!l.this.l || bitmap == null || bitmap.isRecycled()) {
                return;
            }
            bitmap.recycle();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.util.LruCache
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public int sizeOf(String str, Bitmap bitmap) {
            return bitmap.getRowBytes() * bitmap.getHeight();
        }
    }

    class b implements j {
        b() {
        }

        @Override // b.a.h.j
        public String a(String str) {
            return l.this.n(str);
        }

        @Override // b.a.h.j
        public Bitmap b(Bitmap bitmap, float f) {
            return b.a.h.a.a(l.this.d, bitmap, f, true);
        }

        @Override // b.a.h.j
        public void c(ImageView imageView, String str) {
            if (l.this.o != null) {
                l.this.o.a(imageView, str);
            }
        }

        @Override // b.a.h.j
        public void d(String str, Bitmap bitmap) {
            l.this.e.put(str, bitmap);
        }

        @Override // b.a.h.j
        public void e(f fVar) {
            l.f503c.addLast(fVar);
        }

        @Override // b.a.h.j
        public Set<ImageView> f(f fVar) {
            if (l.this.i == null) {
                return null;
            }
            Set<ImageView> set = (Set) l.this.i.remove(fVar.j);
            l.this.k.remove(fVar.j);
            return set;
        }

        @Override // b.a.h.j
        public Bitmap g(f fVar) {
            Drawable drawable;
            Bitmap bitmapL;
            if (!fVar.g && (bitmapL = l.this.l(fVar.f486b)) != null) {
                return bitmapL;
            }
            if (fVar.e == 0 || (drawable = l.this.d.getResources().getDrawable(fVar.e)) == null || !(drawable instanceof BitmapDrawable)) {
                return null;
            }
            return ((BitmapDrawable) drawable).getBitmap();
        }
    }

    private class c extends Handler implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f506a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private f f507b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private ImageView f508c;

        c(String str, f fVar, ImageView imageView) {
            super(Looper.getMainLooper());
            this.f506a = str;
            this.f507b = fVar;
            this.f508c = imageView;
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            if (!this.f507b.j.equals(this.f508c.getTag(l.f501a))) {
                l.f503c.addLast(this.f507b);
                w.c("thumb show view content is change !");
                return;
            }
            Object obj = message.obj;
            if (obj != null) {
                Bitmap bitmap = (Bitmap) obj;
                l.this.e.put(this.f507b.j, bitmap);
                this.f508c.setImageBitmap(bitmap);
                w.c("show local bitmap : " + this.f507b.j);
            } else {
                this.f508c.setImageResource(this.f507b.e);
            }
            l.f503c.addLast(this.f507b);
        }

        @Override // java.lang.Runnable
        public void run() {
            String str = this.f506a;
            f fVar = this.f507b;
            Bitmap bitmapH = b.a.h.a.h(str, 0, 0, 1, fVar.i, fVar.l);
            if (bitmapH == null) {
                bitmapH = null;
            } else if (this.f507b.h > 0.0f) {
                bitmapH = b.a.h.a.a(l.this.d, bitmapH, this.f507b.h, true);
            }
            obtainMessage(1, bitmapH).sendToTarget();
        }
    }

    private l(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.d = applicationContext;
        File externalCacheDir = applicationContext.getExternalCacheDir();
        if (externalCacheDir == null) {
            externalCacheDir = new File("/sdcard/Android/data/" + this.d.getApplicationInfo().packageName + "/cache");
        }
        if (!externalCacheDir.exists()) {
            w.c("create cache folder : " + externalCacheDir.mkdirs());
        }
        this.f = externalCacheDir.getPath();
        this.m = new k(this.d);
        this.g = Executors.newCachedThreadPool();
        this.h = Executors.newFixedThreadPool(3);
        this.k = new HashMap();
        this.j = new LinkedList();
        this.l = false;
    }

    public static f i(Context context) {
        return f503c.size() > 0 ? f503c.removeFirst().d() : new f(m(context));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap l(String str) {
        String strE = this.m.e(str);
        if (strE == null) {
            return null;
        }
        String str2 = this.f + File.separator + strE;
        if (new File(str2).exists()) {
            return b.a.h.a.h(str2, 0, 0, 1, Bitmap.Config.RGB_565, false);
        }
        return null;
    }

    public static l m(Context context) {
        if (f502b == null) {
            synchronized (l.class) {
                if (f502b == null) {
                    f502b = new l(context);
                }
            }
        }
        return f502b;
    }

    private String p(String str) {
        if (URLUtil.isNetworkUrl(str)) {
            return q.b(str.replaceAll("^(?=^.{3,255}$)(http(s)?://)?(www.)?[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(.[a-zA-Z]{2,6})+(:\\d+)*(/)", ""));
        }
        File file = new File(str);
        return this.f.equals(file.getParent()) ? file.getName() : q.b(str);
    }

    private File[] q(String str) {
        List<String> listG = this.m.g(str);
        if (listG == null || listG.size() <= 0) {
            return null;
        }
        int size = listG.size();
        File[] fileArr = new File[size];
        for (int i = 0; i < size; i++) {
            fileArr[i] = new File(this.f + File.separator + listG.get(i));
        }
        return fileArr;
    }

    private void s(String str, ImageView imageView) {
        for (Map.Entry<String, Set<ImageView>> entry : this.i.entrySet()) {
            String key = entry.getKey();
            for (ImageView imageView2 : entry.getValue()) {
                if (!str.equals(key) && imageView.equals(imageView2)) {
                    entry.getValue().remove(imageView);
                    return;
                }
            }
        }
    }

    public synchronized void j(String str) {
        Map<String, Bitmap> mapSnapshot = this.e.snapshot();
        if (mapSnapshot.size() > 0) {
            for (String str2 : mapSnapshot.keySet()) {
                if (str2.startsWith(str)) {
                    this.e.remove(str2);
                }
            }
        }
    }

    public synchronized void k(String str) {
        try {
            for (String str2 : this.k.keySet()) {
                if (str2.startsWith(str)) {
                    this.k.get(str2).cancel(true);
                    this.k.remove(str2);
                    this.i.remove(str2);
                    w.c("deleteCacheThumb : " + str2);
                }
            }
            File[] fileArrQ = q(str);
            if (fileArrQ != null && fileArrQ.length > 0) {
                for (int i = 0; i < fileArrQ.length; i++) {
                    String path = fileArrQ[i].getPath();
                    w.c("delete thumb : " + path);
                    if (this.e.get(path) != null) {
                        this.e.remove(path);
                    }
                    fileArrQ[i].delete();
                }
            }
            this.m.d(str);
            j(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String n(String str) {
        return this.f + File.separator + p(str);
    }

    public Bitmap o(String str, int i, int i2, boolean z, boolean z2, boolean z3) throws IOException {
        String str2;
        String strN;
        Bitmap bitmapK;
        int i3;
        int i4;
        String str3;
        if (z2) {
            if (z) {
                str3 = str + i + i2;
            } else {
                str3 = str;
            }
            if (z3) {
                str3 = str3 + "cr";
            }
            str2 = str3;
            if (this.e.get(str2) != null && !this.e.get(str2).isRecycled()) {
                return this.e.get(str2);
            }
            strN = n(str2);
            if (new File(strN).exists()) {
                Bitmap bitmapH = b.a.h.a.h(strN, i, i2, 1, Bitmap.Config.RGB_565, z3);
                if (bitmapH != null) {
                    this.e.put(str2, bitmapH);
                }
                return bitmapH;
            }
        } else {
            str2 = str;
            strN = null;
        }
        if (m.d(m.a(str))) {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            try {
                try {
                    mediaMetadataRetriever.setDataSource(str);
                    bitmapK = mediaMetadataRetriever.getFrameAtTime();
                    try {
                        int width = bitmapK.getWidth();
                        int height = bitmapK.getHeight();
                        if (width > i || height > i2) {
                            float f = width;
                            float f2 = i / f;
                            float f3 = height;
                            float f4 = i2 / f3;
                            if (f2 < f4) {
                                i4 = (int) (f3 * f2);
                                i3 = i;
                            } else {
                                i3 = (int) (f * f4);
                                i4 = i2;
                            }
                            Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmapK, i3, i4, true);
                            bitmapK.recycle();
                            bitmapK = bitmapCreateScaledBitmap;
                        }
                    } catch (Exception unused) {
                    }
                } catch (Exception unused2) {
                    bitmapK = null;
                }
            } finally {
                mediaMetadataRetriever.release();
            }
        } else {
            bitmapK = b.a.h.a.k(str, i, i2, Bitmap.Config.RGB_565, z3);
        }
        if (bitmapK == null) {
            return null;
        }
        if (z2) {
            this.e.put(str2, bitmapK);
            boolean zR = b.a.h.a.r(bitmapK, strN, Bitmap.Config.RGB_565);
            w.c("getThumbBitmap -> save local : " + zR);
            File file = new File(strN);
            if (zR) {
                this.m.b(str, file.getName(), i, i2, z3, z);
            } else if (file.exists()) {
                file.delete();
            }
        }
        return bitmapK;
    }

    public void r() {
        this.l = true;
        this.g.shutdown();
        this.h.shutdownNow();
        this.e.evictAll();
        this.i.clear();
        f503c.clear();
        this.i = null;
        f502b = null;
        System.gc();
    }

    public void t(String str) {
        this.g.execute(new h(str, this.f, this.m));
    }

    public void u() {
        this.h.shutdownNow();
        Iterator<String> it = this.j.iterator();
        while (it.hasNext()) {
            this.i.remove(it.next());
            it.remove();
        }
        this.h = Executors.newFixedThreadPool(3);
    }

    void v(ImageView imageView, f fVar) {
        ExecutorService executorService;
        Runnable cVar;
        if (fVar.f486b == null || this.l) {
            return;
        }
        fVar.a();
        if (fVar.l) {
            fVar.j += "cr";
        }
        Bitmap bitmap = this.e.get(fVar.j);
        if (bitmap == null && !fVar.g) {
            bitmap = this.e.get(fVar.f486b);
        }
        imageView.setTag(f501a, fVar.j);
        if (bitmap != null && !bitmap.isRecycled()) {
            imageView.setImageBitmap(bitmap);
            f503c.addLast(fVar);
            return;
        }
        String strN = n(fVar.j);
        File file = new File(strN);
        boolean zC = false;
        boolean zContainsKey = this.i.containsKey(fVar.j);
        if (file.exists() && !(zC = this.m.c(fVar.f486b, fVar.f487c, fVar.d)) && !zContainsKey) {
            w.c("local thumb has not record , delete");
            file.delete();
        }
        if (zC && !zContainsKey) {
            this.g.execute(new c(strN, fVar, imageView));
            return;
        }
        if (!this.i.isEmpty()) {
            s(fVar.j, imageView);
            if (zContainsKey) {
                int i = fVar.f;
                if (i != 0) {
                    imageView.setImageResource(i);
                }
                Set<ImageView> set = this.i.get(fVar.j);
                if (set != null) {
                    w.c("create thumb exist loading : " + fVar.j);
                    set.add(imageView);
                    return;
                }
            }
        }
        w.c("create thumb start : " + fVar.j);
        int i2 = fVar.f;
        if (i2 != 0) {
            imageView.setImageResource(i2);
        }
        HashSet hashSet = new HashSet(2);
        hashSet.add(imageView);
        this.i.put(fVar.j, hashSet);
        if (URLUtil.isNetworkUrl(fVar.f486b)) {
            executorService = this.g;
            cVar = new d(fVar, this.m, this.n);
        } else if (m.d(m.a(fVar.f486b)) || fVar.k == 1) {
            this.j.add(fVar.j);
            executorService = this.h;
            cVar = new b.a.h.c(fVar, this.m, this.n, this.j);
        } else {
            executorService = this.g;
            cVar = new b.a.h.b(fVar, this.m, this.n);
        }
        this.k.put(fVar.j, executorService.submit(cVar));
    }

    public void w(String str, String str2) {
        List<k.c> listF = this.m.f(str);
        if (listF != null) {
            int size = listF.size();
            for (int i = 0; i < size; i++) {
                k.c cVar = listF.get(i);
                String str3 = cVar.f() ? str2 + cVar.d() + cVar.a() : str2;
                if (cVar.e()) {
                    str3 = str3 + "cr";
                }
                String strP = p(str3);
                w.c("update key : " + str3 + " newName : " + strP);
                StringBuilder sb = new StringBuilder();
                sb.append(this.f);
                String str4 = File.separator;
                sb.append(str4);
                sb.append(strP);
                if (new File(this.f + str4 + cVar.b()).renameTo(new File(sb.toString()))) {
                    this.m.j(cVar, str2, strP);
                }
            }
        }
    }
}
