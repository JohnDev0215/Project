package p;

import android.content.ClipDescription;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour;
import coil.size.Precision;
import coil.size.Scale;
import com.google.android.datatransport.Priority;
import com.google.common.base.Optional;
import com.google.firebase.encoders.EncodingException;
import com.spotify.auto.parcel.PlaybackSpeedActionParcelable;
import com.spotify.base.java.logging.Logger;
import com.spotify.connectivity.httpwebgate.WebgateHelper;
import com.spotify.mediasession.imageprovider.MediaUriUtil$ImageDimension;
import com.spotify.mediasession.imageprovider.MediaUriUtil$Transformation;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.ReferenceQueue;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class v4 implements r41, k71, i13, mz1, xo1 {
    public static v4 m;
    public final /* synthetic */ int c;
    public Object e;
    public Object k;
    public Object l;

    public /* synthetic */ v4(Object obj, Object obj2, Object obj3) {
        this.c = 14;
        this.l = obj;
        this.e = obj2;
        this.k = obj3;
    }

    public static void C(gg1 gg1Var) {
        Bundle bundle = new Bundle();
        List list = tt1.e;
        List<tt1> list2 = tt1.e;
        ArrayList arrayList = new ArrayList(lu.W(list2));
        for (tt1 tt1Var : list2) {
            String str = tt1Var.a;
            int iIndexOf = list2.indexOf(tt1Var) - 1;
            arrayList.add(new PlaybackSpeedActionParcelable(str, ((tt1) (iIndexOf < 0 ? ou.i0(list2) : list2.get(iIndexOf))).c, tt1Var.d));
        }
        bundle.putParcelableArrayList("result", he1.D(arrayList));
        gg1Var.g(bundle);
    }

    public static v4 J(Context context, AttributeSet attributeSet, int[] iArr, int i) {
        return new v4(context, context.obtainStyledAttributes(attributeSet, iArr, i, 0));
    }

    public static String f(String str, Map map) {
        StringBuilder sb = new StringBuilder();
        Iterator it = map.entrySet().iterator();
        Map.Entry entry = (Map.Entry) it.next();
        sb.append((String) entry.getKey());
        sb.append("=");
        sb.append(entry.getValue() != null ? URLEncoder.encode((String) entry.getValue(), "UTF-8") : "");
        while (it.hasNext()) {
            Map.Entry entry2 = (Map.Entry) it.next();
            sb.append("&");
            sb.append((String) entry2.getKey());
            sb.append("=");
            sb.append(entry2.getValue() != null ? URLEncoder.encode((String) entry2.getValue(), "UTF-8") : "");
        }
        String string = sb.toString();
        if (string.isEmpty()) {
            return str;
        }
        if (str.contains("?")) {
            if (!str.endsWith("&")) {
                string = "&".concat(string);
            }
            return u4.h(str, string);
        }
        return str + "?" + string;
    }

    public final CharSequence A(int i) {
        return ((TypedArray) this.k).getText(i);
    }

    public final void B(o oVar, Bundle bundle, gg1 gg1Var) {
        Optional optionalA = (bundle == null || !bundle.containsKey("uri")) ? Optional.a() : Optional.b(vr2.g(bundle.getString("uri")));
        if (!optionalA.d()) {
            throw new IllegalArgumentException("Missing or invalid URI provided for custom action.");
        }
        gg1Var.a();
        ((Completable) oVar.apply((if2) optionalA.c())).subscribe(new q(0, gg1Var), new p(gg1Var, 2), (io.reactivex.rxjava3.disposables.a) this.l);
    }

    public final void D(com.spotify.automotive.auth.a aVar, Bundle bundle, gg1 gg1Var) {
        Optional optionalA = (bundle == null || !bundle.containsKey("uri")) ? Optional.a() : Optional.b(vr2.g(bundle.getString("uri")));
        if (!optionalA.d()) {
            throw new IllegalArgumentException("Missing or invalid URI provided for custom action.");
        }
        gg1Var.a();
        ((Single) aVar.apply((if2) optionalA.c())).subscribe(new p(gg1Var, 0), new p(gg1Var, 1), (io.reactivex.rxjava3.disposables.a) this.l);
    }

    public final void E(Bundle bundle, gg1 gg1Var) {
        if (bundle == null || !bundle.containsKey("androidx.media.utils.extras.KEY_CUSTOM_BROWSER_ACTION_MEDIA_ITEM_ID")) {
            lc.n("Exception while handling custom browse action play-pause.");
            gg1Var.f();
            return;
        }
        String string = bundle.getString("androidx.media.utils.extras.KEY_CUSTOM_BROWSER_ACTION_MEDIA_ITEM_ID", null);
        w40 w40Var = (w40) this.k;
        w40Var.getClass();
        v03.j(string, "mediaId");
        boolean zB = v03.b(string, w40Var.f);
        boolean z = false;
        st1 st1Var = w40Var.a;
        if (!zB) {
            if2 if2VarG = vr2.g(string);
            String strF = vr2.f(string);
            if (if2VarG != null) {
                String string2 = if2VarG.toString();
                v03.i(string2, "it.toString()");
                ((z1) st1Var).s(null, string2, null, strF);
            }
        } else if (w40Var.d) {
            z1 z1Var = (z1) st1Var;
            z1Var.l().subscribe(y1.c, w1.n, z1Var.g);
        } else {
            st1Var.h();
            z = true;
        }
        w40Var.d = z;
        w40Var.f = string;
        vg1 vg1Var = w40Var.g;
        if (vg1Var != null) {
            vg1Var.g(new Bundle());
        }
        w40Var.g = gg1Var;
        w40Var.b(string);
        gg1Var.a();
    }

    public final void F(Bundle bundle, gg1 gg1Var, Boolean bool) {
        if (bundle == null || !bundle.containsKey("androidx.media.utils.extras.KEY_CUSTOM_BROWSER_ACTION_MEDIA_ITEM_ID")) {
            lc.n("Exception while handling custom browse action shuffle.");
            gg1Var.f();
            return;
        }
        String string = bundle.getString("androidx.media.utils.extras.KEY_CUSTOM_BROWSER_ACTION_MEDIA_ITEM_ID", null);
        w40 w40Var = (w40) this.k;
        boolean zBooleanValue = bool.booleanValue();
        w40Var.getClass();
        v03.j(string, "mediaId");
        w40Var.a.c(zBooleanValue);
        w40Var.e = zBooleanValue;
        vg1 vg1Var = w40Var.g;
        if (vg1Var != null) {
            vg1Var.g(new Bundle());
        }
        w40Var.g = gg1Var;
        w40Var.b(string);
        gg1Var.a();
    }

    public final boolean G(int i) {
        return ((TypedArray) this.k).hasValue(i);
    }

    public final mp2 H() {
        switch (this.c) {
            case 22:
                lp2 lp2Var = new lp2();
                lp2Var.c((ip2) this.k);
                lp2Var.b = ((zi) this.l).a;
                return (mp2) lp2Var.a();
            default:
                lp2 lp2Var2 = new lp2();
                lp2Var2.c((ip2) this.k);
                lp2Var2.b = ((gj) this.l).a;
                return (mp2) lp2Var2.a();
        }
    }

    public final boolean I(int i, ty tyVar, ey eyVar) {
        ml mlVar = (ml) this.k;
        ConstraintWidget$DimensionBehaviour[] constraintWidget$DimensionBehaviourArr = tyVar.S;
        mlVar.a = constraintWidget$DimensionBehaviourArr[0];
        mlVar.b = constraintWidget$DimensionBehaviourArr[1];
        mlVar.c = tyVar.o();
        ((ml) this.k).d = tyVar.i();
        ml mlVar2 = (ml) this.k;
        mlVar2.i = false;
        mlVar2.j = i;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour = mlVar2.a;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2 = ConstraintWidget$DimensionBehaviour.k;
        boolean z = constraintWidget$DimensionBehaviour == constraintWidget$DimensionBehaviour2;
        boolean z2 = mlVar2.b == constraintWidget$DimensionBehaviour2;
        boolean z3 = z && tyVar.W > 0.0f;
        boolean z4 = z2 && tyVar.W > 0.0f;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour3 = ConstraintWidget$DimensionBehaviour.c;
        int[] iArr = tyVar.t;
        if (z3 && iArr[0] == 4) {
            mlVar2.a = constraintWidget$DimensionBehaviour3;
        }
        if (z4 && iArr[1] == 4) {
            mlVar2.b = constraintWidget$DimensionBehaviour3;
        }
        eyVar.b(tyVar, mlVar2);
        tyVar.K(((ml) this.k).e);
        tyVar.H(((ml) this.k).f);
        Object obj = this.k;
        ml mlVar3 = (ml) obj;
        tyVar.E = mlVar3.h;
        int i2 = mlVar3.g;
        tyVar.a0 = i2;
        tyVar.E = i2 > 0;
        ml mlVar4 = (ml) obj;
        mlVar4.j = 0;
        return mlVar4.i;
    }

    public final ParcelFileDescriptor K(Uri uri, File file) {
        Uri uriBuild;
        v03.j(uri, "uri");
        int i = com.google.common.hash.d.a;
        String string = ((k1) com.google.common.hash.c.a).b(uri.toString()).toString();
        v03.i(string, "sha256().hashUnencodedCh…ri.toString()).toString()");
        File file2 = new File(file, string);
        if (file2.exists()) {
            return ParcelFileDescriptor.open(file2, 268435456);
        }
        ((ki1) this.k).getClass();
        String path = uri.getPath();
        int i2 = 0;
        if (path == null || path.isEmpty()) {
            uriBuild = Uri.EMPTY;
        } else {
            if (!path.isEmpty() && '/' == path.charAt(0)) {
                path = path.substring(1);
            }
            if (path.startsWith("spotify:")) {
                uriBuild = Uri.parse(path);
            } else {
                String queryParameter = uri.getQueryParameter("cdn");
                if (v03.y(queryParameter)) {
                    queryParameter = "i.scdn.co";
                }
                uriBuild = new Uri.Builder().scheme(WebgateHelper.DEFAULT_WEBGATE_PROTOCOL).authority(queryParameter).path(path).build();
            }
        }
        v03.i(uriBuild, "uriUtil.toLoadableUri(uri)");
        ((ki1) this.k).getClass();
        String queryParameter2 = uri.getQueryParameter("transformation");
        MediaUriUtil$Transformation mediaUriUtil$TransformationValueOf = (queryParameter2 == null || queryParameter2.isEmpty()) ? MediaUriUtil$Transformation.k : MediaUriUtil$Transformation.valueOf(queryParameter2.toUpperCase(Locale.ENGLISH));
        v03.i(mediaUriUtil$TransformationValueOf, "uriUtil.getTransformationFromUri(uri)");
        fu fuVarA = ((m60) this.e).a(uriBuild);
        if (mediaUriUtil$TransformationValueOf == MediaUriUtil$Transformation.c) {
            fuVarA.a(new qo());
        } else if (mediaUriUtil$TransformationValueOf == MediaUriUtil$Transformation.e) {
            fuVarA.a(new po());
        }
        ((ki1) this.k).getClass();
        String queryParameter3 = uri.getQueryParameter("dimension");
        MediaUriUtil$ImageDimension mediaUriUtil$ImageDimensionValueOf = v03.y(queryParameter3) ? null : MediaUriUtil$ImageDimension.valueOf(queryParameter3.toUpperCase(Locale.ENGLISH));
        if (mediaUriUtil$ImageDimensionValueOf != null) {
            int iA = mediaUriUtil$ImageDimensionValueOf.a();
            int iA2 = mediaUriUtil$ImageDimensionValueOf.a();
            o31 o31Var = fuVarA.b;
            if (iA > 0 && iA2 > 0) {
                o31Var.getClass();
                o31Var.K = new d12(new qd2(new t90(iA), new t90(iA2)));
                o31Var.M = null;
                o31Var.N = null;
                o31Var.O = null;
                o31Var.j = Precision.c;
            }
            o31Var.L = Scale.c;
        }
        File parentFile = file2.getParentFile();
        if (parentFile != null) {
            parentFile.mkdirs();
            try {
                Single singleCreate = Single.create(new du(i2, fuVarA));
                v03.i(singleCreate, "override fun getBitmap()…eRequest)\n        }\n    }");
                Object objBlockingGet = singleCreate.blockingGet();
                v03.i(objBlockingGet, "request.getBitmap().blockingGet()");
                f60.V((Bitmap) objBlockingGet, file2);
            } catch (Exception e) {
                Logger.c(e, "Exception while trying to load the image.", new Object[0]);
            }
        } else {
            Logger.b("Couldn't create directories for \"%s\".", file2);
        }
        return ParcelFileDescriptor.open(file2, 268435456);
    }

    public final void L() {
        ((TypedArray) this.k).recycle();
    }

    public final void M(String str) {
        if (str == null) {
            throw new NullPointerException("Null backendName");
        }
        this.e = str;
    }

    public final void N(Priority priority) {
        if (priority == null) {
            throw new NullPointerException("Null priority");
        }
        this.l = priority;
    }

    public final void O(uy uyVar, int i, int i2, int i3) {
        uyVar.getClass();
        int i4 = uyVar.b0;
        int i5 = uyVar.c0;
        uyVar.b0 = 0;
        uyVar.c0 = 0;
        uyVar.K(i2);
        uyVar.H(i3);
        if (i4 < 0) {
            uyVar.b0 = 0;
        } else {
            uyVar.b0 = i4;
        }
        if (i5 < 0) {
            uyVar.c0 = 0;
        } else {
            uyVar.c0 = i5;
        }
        uy uyVar2 = (uy) this.l;
        uyVar2.s0 = i;
        uyVar2.N();
    }

    public final void P(uy uyVar) {
        ((ArrayList) this.e).clear();
        int size = uyVar.p0.size();
        for (int i = 0; i < size; i++) {
            ty tyVar = (ty) uyVar.p0.get(i);
            ConstraintWidget$DimensionBehaviour[] constraintWidget$DimensionBehaviourArr = tyVar.S;
            ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour = constraintWidget$DimensionBehaviourArr[0];
            ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2 = ConstraintWidget$DimensionBehaviour.k;
            if (constraintWidget$DimensionBehaviour == constraintWidget$DimensionBehaviour2 || constraintWidget$DimensionBehaviourArr[1] == constraintWidget$DimensionBehaviour2) {
                ((ArrayList) this.e).add(tyVar);
            }
        }
        uyVar.r0.b = true;
    }

    @Override // p.i13
    public final /* bridge */ /* synthetic */ Object a() {
        Object objA = ((i13) this.e).a();
        return new a23((com.google.android.play.core.assetpacks.c) objA, (b23) ((i13) this.k).a(), (pz2) ((i13) this.l).a());
    }

    @Override // p.mz1
    public final void b(int i, lz1 lz1Var) throws IOException {
        try {
            lz1Var.read((byte[]) this.e, ((int[]) this.k)[0], i);
            int[] iArr = (int[]) this.k;
            iArr[0] = iArr[0] + i;
        } finally {
            lz1Var.close();
        }
    }

    public final sh c() {
        String strH = ((String) this.e) == null ? " name" : "";
        if (((String) this.k) == null) {
            strH = strH.concat(" code");
        }
        if (((Long) this.l) == null) {
            strH = u4.h(strH, " address");
        }
        if (strH.isEmpty()) {
            return new sh((String) this.e, (String) this.k, ((Long) this.l).longValue());
        }
        throw new IllegalStateException("Missing required properties:".concat(strH));
    }

    public final oi d() {
        String strH = ((Long) this.e) == null ? " delta" : "";
        if (((Long) this.k) == null) {
            strH = strH.concat(" maxAllowedDelay");
        }
        if (((Set) this.l) == null) {
            strH = u4.h(strH, " flags");
        }
        if (strH.isEmpty()) {
            return new oi(((Long) this.e).longValue(), ((Long) this.k).longValue(), (Set) this.l);
        }
        throw new IllegalStateException("Missing required properties:".concat(strH));
    }

    public final wi e() {
        String strConcat = ((String) this.e) == null ? " backendName" : "";
        if (((Priority) this.l) == null) {
            strConcat = strConcat.concat(" priority");
        }
        if (strConcat.isEmpty()) {
            return new wi((String) this.e, (byte[]) this.k, (Priority) this.l);
        }
        throw new IllegalStateException("Missing required properties:".concat(strConcat));
    }

    public final void g(wt wtVar, ByteArrayOutputStream byteArrayOutputStream) {
        Map map = (Map) this.e;
        uy1 uy1Var = new uy1(byteArrayOutputStream, map, (Map) this.k, (yo1) this.l);
        yo1 yo1Var = (yo1) map.get(wt.class);
        if (yo1Var != null) {
            yo1Var.a(wtVar, uy1Var);
        } else {
            throw new EncodingException("No encoder for " + wt.class);
        }
    }

    @Override // p.r41
    public final ClipDescription getDescription() {
        return (ClipDescription) this.k;
    }

    @Override // p.r41
    public final Object h() {
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00ab  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final p.j6 i() throws java.lang.Throwable {
        /*
            r8 = this;
            r0 = 0
            java.lang.Object r1 = r8.e     // Catch: java.lang.Throwable -> La2
            java.lang.String r1 = (java.lang.String) r1     // Catch: java.lang.Throwable -> La2
            java.lang.Object r2 = r8.k     // Catch: java.lang.Throwable -> La2
            java.util.Map r2 = (java.util.Map) r2     // Catch: java.lang.Throwable -> La2
            java.lang.String r1 = f(r1, r2)     // Catch: java.lang.Throwable -> L9c
            java.lang.String r2 = "FirebaseCrashlytics"
            r3 = 2
            android.util.Log.isLoggable(r2, r3)     // Catch: java.lang.Throwable -> L9f
            java.net.URL r2 = new java.net.URL     // Catch: java.lang.Throwable -> L9c
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L9c
            java.net.URLConnection r1 = r2.openConnection()     // Catch: java.lang.Throwable -> L9c
            javax.net.ssl.HttpsURLConnection r1 = (javax.net.ssl.HttpsURLConnection) r1     // Catch: java.lang.Throwable -> L9c
            r2 = 10000(0x2710, float:1.4013E-41)
            r1.setReadTimeout(r2)     // Catch: java.lang.Throwable -> L53
            r1.setConnectTimeout(r2)     // Catch: java.lang.Throwable -> L53
            java.lang.String r2 = "GET"
            r1.setRequestMethod(r2)     // Catch: java.lang.Throwable -> L53
            java.lang.Object r2 = r8.l     // Catch: java.lang.Throwable -> L53
            java.util.Map r2 = (java.util.Map) r2     // Catch: java.lang.Throwable -> L53
            java.util.Set r2 = r2.entrySet()     // Catch: java.lang.Throwable -> L53
            java.util.Iterator r2 = r2.iterator()     // Catch: java.lang.Throwable -> L53
        L37:
            boolean r3 = r2.hasNext()     // Catch: java.lang.Throwable -> L53
            if (r3 == 0) goto L55
            java.lang.Object r3 = r2.next()     // Catch: java.lang.Throwable -> L53
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3     // Catch: java.lang.Throwable -> L53
            java.lang.Object r4 = r3.getKey()     // Catch: java.lang.Throwable -> L53
            java.lang.String r4 = (java.lang.String) r4     // Catch: java.lang.Throwable -> L53
            java.lang.Object r3 = r3.getValue()     // Catch: java.lang.Throwable -> L53
            java.lang.String r3 = (java.lang.String) r3     // Catch: java.lang.Throwable -> L53
            r1.addRequestProperty(r4, r3)     // Catch: java.lang.Throwable -> L53
            goto L37
        L53:
            r2 = move-exception
            goto La4
        L55:
            r1.connect()     // Catch: java.lang.Throwable -> L53
            int r2 = r1.getResponseCode()     // Catch: java.lang.Throwable -> L53
            java.io.InputStream r3 = r1.getInputStream()     // Catch: java.lang.Throwable -> L53
            if (r3 == 0) goto L8e
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L8a
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L8a
            java.lang.String r5 = "UTF-8"
            r4.<init>(r3, r5)     // Catch: java.lang.Throwable -> L8a
            r0.<init>(r4)     // Catch: java.lang.Throwable -> L8a
            r4 = 8192(0x2000, float:1.148E-41)
            char[] r4 = new char[r4]     // Catch: java.lang.Throwable -> L8a
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8a
            r5.<init>()     // Catch: java.lang.Throwable -> L8a
        L77:
            int r6 = r0.read(r4)     // Catch: java.lang.Throwable -> L8a
            r7 = -1
            if (r6 == r7) goto L83
            r7 = 0
            r5.append(r4, r7, r6)     // Catch: java.lang.Throwable -> L8a
            goto L77
        L83:
            java.lang.String r0 = r5.toString()     // Catch: java.lang.Throwable -> L8a
            goto L8e
        L88:
            r2 = r0
            goto L8c
        L8a:
            r0 = move-exception
            goto L88
        L8c:
            r0 = r3
            goto La4
        L8e:
            if (r3 == 0) goto L93
            r3.close()
        L93:
            r1.disconnect()
            p.j6 r1 = new p.j6
            r1.<init>(r2, r0)
            return r1
        L9c:
            r2 = move-exception
        L9d:
            r1 = r0
            goto La4
        L9f:
            r1 = move-exception
        La0:
            r2 = r1
            goto L9d
        La2:
            r1 = move-exception
            goto La0
        La4:
            if (r0 == 0) goto La9
            r0.close()
        La9:
            if (r1 == 0) goto Lae
            r1.disconnect()
        Lae:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p.v4.i():p.j6");
    }

    @Override // p.r41
    public final Uri j() {
        return (Uri) this.e;
    }

    @Override // p.xo1
    public final Object k() {
        u4.z(this.e);
        throw null;
    }

    public final String l() {
        String str = (String) this.e;
        if (str != null) {
            return str;
        }
        String strE = ((g90) this.l).e();
        String string = ((ww1) ((wg1) this.k).a).t().getString("es_sdk_last_android_id", null);
        String string2 = ((ww1) ((wg1) this.k).a).t().getString("installation_id", null);
        if (string2 == null || string == null || !string.equals(strE)) {
            string2 = UUID.randomUUID().toString();
            ((wg1) this.k).f("es_sdk_last_android_id", strE);
            ((wg1) this.k).f("installation_id", string2);
        }
        this.e = string2;
        return string2;
    }

    public final iu2 m(Class cls, String str) {
        iu2 iu2VarA;
        v03.j(str, "key");
        lu2 lu2Var = (lu2) this.e;
        lu2Var.getClass();
        iu2 iu2Var = (iu2) lu2Var.a.get(str);
        if (cls.isInstance(iu2Var)) {
            v03.g(iu2Var, "null cannot be cast to non-null type T of androidx.lifecycle.ViewModelProvider.get");
            return iu2Var;
        }
        yl1 yl1Var = new yl1((q40) this.l);
        yl1Var.a.put(qo.r, str);
        try {
            iu2VarA = ((ku2) this.k).b(cls, yl1Var);
        } catch (AbstractMethodError unused) {
            iu2VarA = ((ku2) this.k).a(cls);
        }
        lu2 lu2Var2 = (lu2) this.e;
        lu2Var2.getClass();
        v03.j(iu2VarA, "viewModel");
        iu2 iu2Var2 = (iu2) lu2Var2.a.put(str, iu2VarA);
        if (iu2Var2 != null) {
            iu2Var2.a();
        }
        return iu2VarA;
    }

    @Override // p.r41
    public final void n() {
    }

    @Override // p.r41
    public final Uri o() {
        return (Uri) this.l;
    }

    public final boolean p(int i, boolean z) {
        return ((TypedArray) this.k).getBoolean(i, z);
    }

    public final ColorStateList q(int i) {
        int resourceId;
        ColorStateList colorStateListD;
        return (!((TypedArray) this.k).hasValue(i) || (resourceId = ((TypedArray) this.k).getResourceId(i, 0)) == 0 || (colorStateListD = l13.d((Context) this.e, resourceId)) == null) ? ((TypedArray) this.k).getColorStateList(i) : colorStateListD;
    }

    public final int r(int i, int i2) {
        return ((TypedArray) this.k).getDimensionPixelOffset(i, i2);
    }

    public final int s(int i, int i2) {
        return ((TypedArray) this.k).getDimensionPixelSize(i, i2);
    }

    public final Drawable t(int i) {
        int resourceId;
        return (!((TypedArray) this.k).hasValue(i) || (resourceId = ((TypedArray) this.k).getResourceId(i, 0)) == 0) ? ((TypedArray) this.k).getDrawable(i) : l13.f((Context) this.e, resourceId);
    }

    public final Drawable u(int i) {
        int resourceId;
        Drawable drawableF;
        if (!((TypedArray) this.k).hasValue(i) || (resourceId = ((TypedArray) this.k).getResourceId(i, 0)) == 0) {
            return null;
        }
        u8 u8VarA = u8.a();
        Context context = (Context) this.e;
        synchronized (u8VarA) {
            drawableF = u8VarA.a.f(context, resourceId, true);
        }
        return drawableF;
    }

    public final Typeface v(int i, int i2, w9 w9Var) {
        int resourceId = ((TypedArray) this.k).getResourceId(i, 0);
        if (resourceId == 0) {
            return null;
        }
        if (((TypedValue) this.l) == null) {
            this.l = new TypedValue();
        }
        Context context = (Context) this.e;
        TypedValue typedValue = (TypedValue) this.l;
        ThreadLocal threadLocal = s42.a;
        if (context.isRestricted()) {
            return null;
        }
        return s42.b(context, resourceId, typedValue, i2, w9Var, true, false);
    }

    public final int w(int i, int i2) {
        return ((TypedArray) this.k).getInt(i, i2);
    }

    public final Location x(String str) {
        try {
            if (((LocationManager) this.k).isProviderEnabled(str)) {
                return ((LocationManager) this.k).getLastKnownLocation(str);
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public final int y(int i, int i2) {
        return ((TypedArray) this.k).getResourceId(i, i2);
    }

    public final String z(int i) {
        return ((TypedArray) this.k).getString(i);
    }

    public /* synthetic */ v4(Object obj, Object obj2, Object obj3, int i) {
        this.c = i;
        this.e = obj;
        this.k = obj2;
        this.l = obj3;
    }

    public v4(sv svVar, String str, q30 q30Var) {
        this.c = 0;
        this.l = svVar;
        this.e = str;
        this.k = q30Var;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public v4(lu2 lu2Var, ku2 ku2Var) {
        this(lu2Var, ku2Var, 0);
        this.c = 5;
        v03.j(lu2Var, "store");
        v03.j(ku2Var, "factory");
    }

    public v4(m60 m60Var, ki1 ki1Var) {
        this.c = 21;
        v03.j(m60Var, "imageLoader");
        this.e = m60Var;
        this.k = ki1Var;
        this.l = new String[]{"image/png"};
    }

    public v4(int i) {
        this.c = i;
        if (i != 10 && i != 11 && i != 15) {
            this.e = new Object();
            this.k = new ConcurrentHashMap();
            this.l = new ReferenceQueue();
        }
    }

    public v4(wg1 wg1Var, g90 g90Var) {
        this.c = 20;
        this.k = wg1Var;
        this.l = g90Var;
    }

    public v4(ju juVar, w40 w40Var) {
        this.c = 19;
        this.l = new io.reactivex.rxjava3.disposables.a();
        this.e = juVar;
        this.k = w40Var;
    }

    public v4(String str, HashMap map) {
        this.c = 16;
        this.e = str;
        this.k = map;
        this.l = new HashMap();
    }

    public v4(lu2 lu2Var, ku2 ku2Var, q40 q40Var) {
        this.c = 5;
        v03.j(lu2Var, "store");
        v03.j(ku2Var, "factory");
        v03.j(q40Var, "defaultCreationExtras");
        this.e = lu2Var;
        this.k = ku2Var;
        this.l = q40Var;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ v4(lu2 lu2Var, ku2 ku2Var, int i) {
        this(lu2Var, ku2Var, (q40) p40.b);
        this.c = 5;
    }

    public v4(Context context, TypedArray typedArray) {
        this.c = 2;
        this.e = context;
        this.k = typedArray;
    }

    public v4(Context context, LocationManager locationManager) {
        this.c = 1;
        this.l = new ko2();
        this.e = context;
        this.k = locationManager;
    }

    public v4(uy uyVar) {
        this.c = 3;
        this.e = new ArrayList();
        this.k = new ml();
        this.l = uyVar;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ v4(g90 g90Var, int i) {
        this(g90Var);
        this.c = 13;
    }

    public v4(g90 g90Var) {
        this.c = 13;
        this.l = g90Var;
        int iI0 = zu.i0((Context) g90Var.e, "com.google.firebase.crashlytics.unity_version", "string");
        if (iI0 != 0) {
            this.e = "Unity";
            this.k = ((Context) g90Var.e).getResources().getString(iI0);
            Log.isLoggable("FirebaseCrashlytics", 2);
            return;
        }
        if (((Context) g90Var.e).getAssets() != null) {
            try {
                InputStream inputStreamOpen = ((Context) g90Var.e).getAssets().open("flutter_assets/NOTICES.Z");
                if (inputStreamOpen != null) {
                    inputStreamOpen.close();
                }
                this.e = "Flutter";
                this.k = null;
                Log.isLoggable("FirebaseCrashlytics", 2);
                return;
            } catch (IOException unused) {
            }
        }
        this.e = null;
        this.k = null;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public v4(mu2 mu2Var, o41 o41Var) {
        q40 q40VarG;
        this.c = 5;
        lu2 lu2VarJ = mu2Var.j();
        if (mu2Var instanceof n11) {
            q40VarG = ((n11) mu2Var).g();
        } else {
            q40VarG = p40.b;
        }
        this(lu2VarJ, (ku2) o41Var, q40VarG);
    }

    public v4(zi ziVar, String str) {
        this.c = 22;
        this.l = ziVar;
        this.e = str;
        hp2 hp2VarB = ziVar.b.b();
        hp2VarB.i.add(new jp2("error_message", (String) this.e, null, null, null));
        hp2VarB.j = true;
        this.k = hp2VarB.a();
    }

    public v4(gj gjVar, String str) {
        this.c = 23;
        this.l = gjVar;
        this.e = str;
        hp2 hp2VarB = gjVar.b.b();
        hp2VarB.i.add(new jp2("error_message", (String) this.e, null, null, null));
        hp2VarB.j = true;
        this.k = hp2VarB.a();
    }
}
