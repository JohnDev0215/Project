package p;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Path;
import android.net.Uri;
import android.os.StrictMode;
import android.text.Spannable;
import android.util.Log;
import androidx.recyclerview.widget.RecyclerView;
import com.google.common.base.Optional;
import com.google.zxing.EncodeHintType;
import com.spotify.base.java.logging.Logger;
import com.spotify.mediasession.imageprovider.MediaUriUtil$Transformation;
import com.spotify.music.R;
import com.spotify.player.esperanto.proto.EsResponseWithReasons$ResponseWithReasons;
import com.spotify.player.model.ContextTrack;
import com.spotify.player.model.PlayerQueue;
import com.spotify.webapi.service.models.Pager;
import com.spotify.webapi.service.models.Playlist;
import com.spotify.webapi.service.models.PlaylistTrack;
import com.spotify.webapi.service.models.Search;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import kotlin.collections.EmptyList;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okhttp3.ResponseBody$Companion$asResponseBody$1;

/* JADX INFO: loaded from: classes.dex */
public class zb0 implements fy1, lg, h00, x10, io.reactivex.rxjava3.functions.o, io.reactivex.rxjava3.functions.p, io.reactivex.rxjava3.functions.g, io.reactivex.rxjava3.functions.c, l03, qc1, cn1, ph2, rc1, nc1, o51, ma0 {
    public static final zb0 c = new zb0();
    public static final zb0 e = new zb0();
    public static final /* synthetic */ zb0 k = new zb0();
    public static final zb0 l = new zb0();
    public static final zb0 m = new zb0();
    public static final zb0 n = new zb0();
    public static final zb0 o = new zb0();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final zb0 f216p = new zb0();
    public static final zb0 q = new zb0();
    public static final /* synthetic */ zb0 r = new zb0();

    public zb0(RecyclerView recyclerView) {
    }

    public static final File i(File file, String str) {
        if (file == null) {
            throw new IllegalStateException("No root directory for creating ".concat(str).toString());
        }
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        try {
            File absoluteFile = file.getAbsoluteFile();
            if (!absoluteFile.isDirectory() || !absoluteFile.canWrite()) {
                if (!absoluteFile.mkdirs() && (!absoluteFile.isDirectory() || !absoluteFile.canWrite())) {
                    throw new IllegalStateException(("Could not create " + absoluteFile).toString());
                }
            }
            File file2 = new File(absoluteFile, str);
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskWrites);
            return file2;
        } catch (Throwable th) {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskWrites);
            throw th;
        }
    }

    public static final String j(char c2, String str, String str2, Throwable th) {
        List list = Logger.a;
        StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + String.valueOf(str).length() + 5);
        sb.append(c2);
        sb.append(" [");
        sb.append(str);
        sb.append("] ");
        sb.append(str2);
        if (th != null) {
            sb.append(' ');
            sb.append(Log.getStackTraceString(th));
        }
        String string = sb.toString();
        v03.i(string, "stringBuilder.toString()");
        return string;
    }

    public static com.spotify.navigation.identifier.a p(String str) {
        v03.j(str, "viewUri");
        return new com.spotify.navigation.identifier.a(str);
    }

    public static ji1 q(PlayerQueue playerQueue, boolean z, String str, ki1 ki1Var, Resources resources) {
        v03.j(playerQueue, "playerQueue");
        int i = z ? 2 : 1;
        Optional optionalF = playerQueue.f();
        Object[] array = playerQueue.b().toArray(new ContextTrack[0]);
        v03.i(array, "playerQueue.nextTracks()…(arrayOf<ContextTrack>())");
        ArrayList arrayList = new ArrayList(50);
        int i2 = 0;
        for (ContextTrack contextTrack : (ContextTrack[]) array) {
            if (ContextTrack.a.contains(contextTrack.f())) {
                i2++;
                if (i2 >= i) {
                    break;
                }
            } else {
                pz1 pz1VarR = r(contextTrack, false, str, ki1Var, resources);
                if (pz1VarR.b.length() > 0 && pz1VarR.e.length() > 0 && !pz1VarR.i) {
                    arrayList.add(pz1VarR);
                }
            }
        }
        if (optionalF.d()) {
            ContextTrack contextTrack2 = (ContextTrack) optionalF.c();
            v03.i(contextTrack2, Search.Type.TRACK);
            arrayList.add(0, r(contextTrack2, true, str, ki1Var, resources));
        }
        if (str == null) {
            str = "";
        }
        return new ji1(str, arrayList);
    }

    public static pz1 r(ContextTrack contextTrack, boolean z, String str, ki1 ki1Var, Resources resources) {
        String string;
        Uri uriB;
        Long lZ;
        String strZ = f60.z(contextTrack, "title");
        String strZ2 = "";
        if (strZ == null) {
            strZ = "";
        }
        if (f60.b(contextTrack).length() > 0) {
            strZ2 = f60.b(contextTrack);
        } else {
            String strZ3 = f60.z(contextTrack, "album_title");
            if (strZ3 != null && strZ3.length() != 0) {
                strZ2 = f60.z(contextTrack, "album_title");
                v03.h(strZ2);
            }
        }
        if (resources == null || str == null) {
            string = null;
        } else if (z) {
            string = resources.getString(R.string.queue_now_playing);
        } else if (Boolean.parseBoolean((String) contextTrack.b().get("is_queued"))) {
            string = resources.getString(R.string.queue_next_queued);
        } else {
            String string2 = resources.getString(R.string.queue_next_from);
            v03.i(string2, "resources.getString(R.string.queue_next_from)");
            string = String.format(string2, Arrays.copyOf(new Object[]{str}, 1));
            v03.i(string, "format(format, *args)");
        }
        String str2 = string;
        String strF = contextTrack.f();
        v03.i(strF, "playerTrack.uri()");
        if (ki1Var == null) {
            uriB = Uri.EMPTY;
            v03.i(uriB, "EMPTY");
        } else {
            String strZ4 = f60.z(contextTrack, "image_small_url");
            if (strZ4 == null) {
                strZ4 = f60.z(contextTrack, "image_url");
            }
            uriB = (strZ4 == null || strZ4.isEmpty()) ? Uri.EMPTY : ki1Var.b(Uri.parse(strZ4), MediaUriUtil$Transformation.k);
            v03.i(uriB, "mediaUriUtil.toLocalCont…Util.Transformation.NONE)");
        }
        Uri uri = uriB;
        boolean z2 = Boolean.parseBoolean((String) contextTrack.b().get("is_explicit"));
        boolean z3 = Boolean.parseBoolean((String) contextTrack.b().get("is_19_plus"));
        boolean zContainsKey = contextTrack.b().containsKey("availability_restrictions");
        String str3 = (String) contextTrack.b().get("duration");
        return new pz1(contextTrack, strZ, strZ2, str2, strF, uri, z2, z3, zContainsKey, (str3 == null || (lZ = mg2.Z(str3)) == null) ? 0L : lZ.longValue(), f60.F(contextTrack).hashCode());
    }

    public static w62 s(x62 x62Var) {
        v03.j(x62Var, "owner");
        return new w62(x62Var);
    }

    public static y6 t(Context context, String str, String str2, String str3) {
        File file;
        v03.j(str, "fileName");
        v03.j(str2, "subfolder");
        File dir = context.getDir("remote-config", 0);
        v03.i(dir, "this.getDir(DEFAULT_DIRE…RY, Context.MODE_PRIVATE)");
        if (str3 != null) {
            File dir2 = context.getDir("remote-config", 0);
            v03.i(dir2, "this.getDir(DEFAULT_DIRE…RY, Context.MODE_PRIVATE)");
            file = new File(dir2, str3);
        } else {
            file = null;
        }
        if (str2.length() > 0) {
            File file2 = new File(dir, str2);
            if (!file2.exists()) {
                file2.mkdirs();
            }
            dir = file2;
        }
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return new y6(new File(dir, str), file);
    }

    public static m02 u(w01 w01Var) {
        m02 m02Var = m02.c;
        List list = w01Var.c;
        int iB = he1.B(lu.W(list));
        if (iB < 16) {
            iB = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iB);
        for (Object obj : list) {
            pc pcVar = (pc) obj;
            v03.j(pcVar, "property");
            m02 m02Var2 = m02.c;
            linkedHashMap.put(w(pcVar.b, pcVar.a), obj);
        }
        return new m02(w01Var, linkedHashMap);
    }

    public static Bitmap v(int i, int i2, int i3, String str) {
        try {
            yl ylVarM = yn.m(str, i, i, Collections.singletonMap(EncodeHintType.k, 0));
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
            for (int i4 = 0; i4 < ylVarM.c; i4++) {
                for (int i5 = 0; i5 < ylVarM.e; i5++) {
                    bitmapCreateBitmap.setPixel(i4, i5, ((ylVarM.l[(i4 / 32) + (ylVarM.k * i5)] >>> (i4 & 31)) & 1) != 0 ? i2 : i3);
                }
            }
            return bitmapCreateBitmap;
        } catch (Exception e2) {
            Logger.c(e2, "Exception while generating QR code.", new Object[0]);
            throw new IllegalArgumentException("Unable to render QR code with the parameters provided.", e2);
        }
    }

    public static String w(String str, String str2) {
        v03.j(str, "componentId");
        v03.j(str2, "name");
        return str + ':' + str2;
    }

    public static Path x(float f, float f2, float f3, float f4) {
        Path path = new Path();
        path.moveTo(f, f2);
        path.lineTo(f3, f4);
        return path;
    }

    public static String y(String str, String str2) {
        v03.j(str, "tableName");
        v03.j(str2, "triggerType");
        return "`room_table_modification_trigger_" + str + '_' + str2 + '`';
    }

    public static androidx.sqlite.db.framework.b z(hk0 hk0Var, SQLiteDatabase sQLiteDatabase) {
        v03.j(hk0Var, "refHolder");
        v03.j(sQLiteDatabase, "sqLiteDatabase");
        androidx.sqlite.db.framework.b bVar = (androidx.sqlite.db.framework.b) hk0Var.e;
        if (bVar != null && v03.b(bVar.c, sQLiteDatabase)) {
            return bVar;
        }
        androidx.sqlite.db.framework.b bVar2 = new androidx.sqlite.db.framework.b(sQLiteDatabase);
        hk0Var.e = bVar2;
        return bVar2;
    }

    public boolean A(Spannable spannable) {
        return false;
    }

    @Override // p.ph2
    public qh2 a(oh2 oh2Var) {
        return new androidx.sqlite.db.framework.d(oh2Var.a, oh2Var.b, oh2Var.c, oh2Var.d, oh2Var.e);
    }

    @Override // io.reactivex.rxjava3.functions.g
    public /* bridge */ /* synthetic */ void accept(Object obj) {
    }

    @Override // io.reactivex.rxjava3.functions.o
    public Object apply(Object obj) {
        EsResponseWithReasons$ResponseWithReasons esResponseWithReasons$ResponseWithReasons = (EsResponseWithReasons$ResponseWithReasons) obj;
        v03.j(esResponseWithReasons$ResponseWithReasons, "p0");
        return yu.a(esResponseWithReasons$ResponseWithReasons);
    }

    @Override // p.qc1
    public String b() {
        return Thread.currentThread().getName();
    }

    @Override // p.rc1
    public void c(String str, String str2) {
    }

    @Override // p.h00
    public Object convert(Object obj) {
        ResponseBody responseBody = (ResponseBody) obj;
        try {
            fo foVar = new fo();
            responseBody.i().d0(foVar);
            MediaType mediaTypeC = responseBody.c();
            long jB = responseBody.b();
            ResponseBody.e.getClass();
            return new ResponseBody$Companion$asResponseBody$1(mediaTypeC, jB, foVar);
        } finally {
            responseBody.close();
        }
    }

    @Override // p.nc1
    public String d(String str, Object... objArr) {
        return String.format(Locale.US, str, objArr);
    }

    @Override // p.l03
    public int e(int i) {
        return i;
    }

    @Override // p.rc1
    public void f(String str, String str2, Throwable th) {
    }

    @Override // p.nc1
    public String g(String str) {
        return str;
    }

    @Override // p.lg
    public void h(androidx.sqlite.db.framework.b bVar) {
        bVar.w("UPDATE workspec SET period_count = 1 WHERE last_enqueue_time <> 0 AND interval_duration <> 0");
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("last_enqueue_time", Long.valueOf(System.currentTimeMillis()));
        bVar.i("WorkSpec", 3, contentValues, "last_enqueue_time = 0 AND interval_duration <> 0 ", new Object[0]);
    }

    @Override // p.cn1
    public boolean isOnline() {
        return true;
    }

    @Override // io.reactivex.rxjava3.functions.c
    public Object k(Object obj, Object obj2) {
        List<PlaylistTrack> list;
        Collection<? extends PlaylistTrack> collection;
        Playlist playlist = (Playlist) obj;
        Playlist playlist2 = (Playlist) obj2;
        v03.j(playlist, "first");
        v03.j(playlist2, "next");
        Pager<PlaylistTrack> pager = playlist.tracks;
        if (pager != null && (list = pager.items) != null) {
            Pager<PlaylistTrack> pager2 = playlist2.tracks;
            if (pager2 == null || (collection = pager2.items) == null) {
                collection = EmptyList.c;
            }
            list.addAll(collection);
        }
        return playlist;
    }

    public boolean l(int i) {
        return 4 <= i || Log.isLoggable("FirebaseCrashlytics", i);
    }

    public void m(zd0 zd0Var) {
        jf jfVar = jf.a;
        g71 g71Var = (g71) zd0Var;
        g71Var.a(nl.class, jfVar);
        g71Var.a(zg.class, jfVar);
        mf mfVar = mf.a;
        g71Var.a(oc1.class, mfVar);
        g71Var.a(gi.class, mfVar);
        kf kfVar = kf.a;
        g71Var.a(vt.class, kfVar);
        g71Var.a(ah.class, kfVar);
        Cif cif = Cif.a;
        g71Var.a(t6.class, cif);
        g71Var.a(xg.class, cif);
        lf lfVar = lf.a;
        g71Var.a(kc1.class, lfVar);
        g71Var.a(fi.class, lfVar);
        nf nfVar = nf.a;
        g71Var.a(wm1.class, nfVar);
        g71Var.a(ii.class, nfVar);
    }

    @Override // p.fy1
    public void n() {
    }

    @Override // p.fy1
    public void o(int i, Object obj) {
    }

    @Override // p.cn1
    public void shutdown() {
    }

    @Override // io.reactivex.rxjava3.functions.p
    public boolean test(Object obj) {
        return !((Boolean) obj).booleanValue();
    }
}
