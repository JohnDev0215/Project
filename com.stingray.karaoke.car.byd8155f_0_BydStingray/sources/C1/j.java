package C1;

import B2.C0073m;
import a4.C0920a;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Shader;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.stingray.karaoke.car.player.PlayerService;
import g4.x;
import h2.AbstractC1343A;
import io.sentry.EnumC1495p1;
import io.sentry.K;
import java.io.ByteArrayOutputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.InetAddress;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Currency;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicIntegerArray;
import k3.A0;
import n4.z;
import n7.AbstractC1998m;
import s2.t;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class j implements G1.n, t, s4.a {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f944m;

    public /* synthetic */ j(int i7, Object obj) {
        this.l = i7;
        this.f944m = obj;
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x01f1, code lost:
    
        if (r5 == 1) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x01f3, code lost:
    
        if (r5 == 2) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x01f5, code lost:
    
        r1 = android.graphics.Shader.TileMode.CLAMP;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x01f7, code lost:
    
        r17 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x01fa, code lost:
    
        r1 = android.graphics.Shader.TileMode.MIRROR;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x01fd, code lost:
    
        r1 = android.graphics.Shader.TileMode.REPEAT;
     */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x0200, code lost:
    
        r1 = 0;
        r3 = new android.graphics.LinearGradient(r11, r27, r26, r25, (int[]) r0.l, (float[]) r0.f18945m, r17);
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x0217, code lost:
    
        r1 = 0;
        r3 = new android.graphics.SweepGradient(r22, r23, (int[]) r0.l, (float[]) r0.f18945m);
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x022a, code lost:
    
        r9 = r22;
        r15 = r23;
        r1 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x0232, code lost:
    
        if (r24 <= 0.0f) goto L121;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x0237, code lost:
    
        if (r5 == 1) goto L117;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x023a, code lost:
    
        if (r5 == 2) goto L116;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x023c, code lost:
    
        r2 = android.graphics.Shader.TileMode.CLAMP;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x0241, code lost:
    
        r2 = android.graphics.Shader.TileMode.MIRROR;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x0244, code lost:
    
        r2 = android.graphics.Shader.TileMode.REPEAT;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x0247, code lost:
    
        r3 = new android.graphics.RadialGradient(r9, r15, r24, (int[]) r0.l, (float[]) r0.f18945m, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x0264, code lost:
    
        return new C1.j(r3, null, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x026c, code lost:
    
        throw new org.xmlpull.v1.XmlPullParserException("<gradient> tag requires 'gradientRadius' attribute with radial type");
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x01cf, code lost:
    
        if (r14.size() <= 0) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x01d1, code lost:
    
        r0 = new m2.b(r14, r15);
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x01d7, code lost:
    
        r0 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x01d8, code lost:
    
        if (r0 == null) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x01dc, code lost:
    
        if (r19 == false) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x01de, code lost:
    
        r0 = new m2.b(r6, r9, r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x01e4, code lost:
    
        r0 = new m2.b(r6, r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x01ea, code lost:
    
        if (r8 == 1) goto L108;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x01ed, code lost:
    
        if (r8 == 2) goto L107;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static C1.j i(android.content.res.Resources r29, int r30, android.content.res.Resources.Theme r31) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 659
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: C1.j.i(android.content.res.Resources, int, android.content.res.Resources$Theme):C1.j");
    }

    @Override // G1.n
    public boolean a(View view) {
        ((BottomSheetBehavior) this.f944m).y(this.l);
        return true;
    }

    @Override // s2.t
    public MediaCodecInfo b(int i7) {
        if (((MediaCodecInfo[]) this.f944m) == null) {
            this.f944m = new MediaCodecList(this.l).getCodecInfos();
        }
        return ((MediaCodecInfo[]) this.f944m)[i7];
    }

    @Override // s2.t
    public boolean c(String str, MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return codecCapabilities.isFeatureRequired(str);
    }

    @Override // s2.t
    public int d() {
        if (((MediaCodecInfo[]) this.f944m) == null) {
            this.f944m = new MediaCodecList(this.l).getCodecInfos();
        }
        return ((MediaCodecInfo[]) this.f944m).length;
    }

    @Override // s2.t
    public boolean e(String str, String str2, MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return codecCapabilities.isFeatureSupported(str);
    }

    @Override // s4.a
    public x f(x xVar, e4.h hVar) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ((Bitmap) xVar.get()).compress((Bitmap.CompressFormat) this.f944m, this.l, byteArrayOutputStream);
        xVar.e();
        return new z(byteArrayOutputStream.toByteArray());
    }

    @Override // s2.t
    public boolean g() {
        return true;
    }

    public void h() {
        WeakReference weakReference;
        this.l = 0;
        Iterator it = ((LinkedHashMap) this.f944m).values().iterator();
        while (it.hasNext()) {
            ArrayList arrayList = (ArrayList) it.next();
            if (arrayList.size() <= 1) {
                I3.f fVar = (I3.f) AbstractC1998m.a0(arrayList);
                if (((fVar == null || (weakReference = fVar.f2940b) == null) ? null : (Bitmap) weakReference.get()) == null) {
                    it.remove();
                }
            } else {
                int size = arrayList.size();
                int i7 = 0;
                for (int i10 = 0; i10 < size; i10++) {
                    int i11 = i10 - i7;
                    if (((I3.f) arrayList.get(i11)).f2940b.get() == null) {
                        arrayList.remove(i11);
                        i7++;
                    }
                }
                if (arrayList.isEmpty()) {
                    it.remove();
                }
            }
        }
    }

    public PendingIntent j(A0 a02, long j3) {
        int i7 = (j3 == 8 || j3 == 9) ? 87 : (j3 == 6 || j3 == 7) ? 88 : j3 == 3 ? 86 : j3 == 12 ? 90 : j3 == 11 ? 89 : j3 == 1 ? 85 : 0;
        Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
        intent.setData(a02.f17714a.f17797b);
        PlayerService playerService = (PlayerService) this.f944m;
        intent.setComponent(new ComponentName(playerService, playerService.getClass()));
        intent.putExtra("android.intent.extra.KEY_EVENT", new KeyEvent(0, i7));
        int i10 = AbstractC1343A.f15195a;
        if (i10 < 26 || j3 != 1 || a02.b().C()) {
            return PendingIntent.getService(playerService, i7, intent, i10 >= 23 ? 67108864 : 0);
        }
        return PendingIntent.getForegroundService(playerService, i7, intent, 67108864);
    }

    public void k(String str, Object... objArr) {
        if (s(3)) {
            Log.d((String) this.f944m, String.format(str, objArr));
        }
    }

    public void l(Exception exc, String str, Object... objArr) {
        if (s(2)) {
            h6.n.i((String) this.f944m, String.format(str, objArr), exc);
        }
    }

    public boolean m() {
        return ((C0920a) this.f944m) != null;
    }

    public HashMap n(Map map, K k10) {
        HashMap map2 = new HashMap();
        for (Object obj : map.keySet()) {
            Object obj2 = map.get(obj);
            if (obj2 != null) {
                map2.put(obj.toString(), p(k10, obj2));
            } else {
                map2.put(obj.toString(), null);
            }
        }
        return map2;
    }

    public long o(C0073m c0073m) {
        h2.t tVar = (h2.t) this.f944m;
        int i7 = 0;
        c0073m.k(tVar.f15241a, 0, 1, false);
        int i10 = tVar.f15241a[0] & 255;
        if (i10 == 0) {
            return Long.MIN_VALUE;
        }
        int i11 = 128;
        int i12 = 0;
        while ((i10 & i11) == 0) {
            i11 >>= 1;
            i12++;
        }
        int i13 = i10 & (~i11);
        c0073m.k(tVar.f15241a, 1, i12, false);
        while (i7 < i12) {
            i7++;
            i13 = (tVar.f15241a[i7] & 255) + (i13 << 8);
        }
        this.l = i12 + 1 + this.l;
        return i13;
    }

    public Object p(K k10, Object obj) {
        Object string;
        Object obj2 = null;
        if (obj == null) {
            return null;
        }
        if (obj instanceof Character) {
            return obj.toString();
        }
        if ((obj instanceof Number) || (obj instanceof Boolean) || (obj instanceof String)) {
            return obj;
        }
        if (obj instanceof Locale) {
            return obj.toString();
        }
        int i7 = 0;
        if (obj instanceof AtomicIntegerArray) {
            AtomicIntegerArray atomicIntegerArray = (AtomicIntegerArray) obj;
            Charset charset = io.sentry.util.b.f16916a;
            int length = atomicIntegerArray.length();
            ArrayList arrayList = new ArrayList(length);
            while (i7 < length) {
                arrayList.add(Integer.valueOf(atomicIntegerArray.get(i7)));
                i7++;
            }
            return arrayList;
        }
        if (obj instanceof AtomicBoolean) {
            return Boolean.valueOf(((AtomicBoolean) obj).get());
        }
        if (!(obj instanceof URI) && !(obj instanceof InetAddress) && !(obj instanceof UUID) && !(obj instanceof Currency)) {
            if (obj instanceof Calendar) {
                return io.sentry.util.b.a((Calendar) obj);
            }
            if (obj.getClass().isEnum()) {
                return obj.toString();
            }
            HashSet hashSet = (HashSet) this.f944m;
            if (hashSet.contains(obj)) {
                k10.k(EnumC1495p1.INFO, "Cyclic reference detected. Calling toString() on object.", new Object[0]);
                return obj.toString();
            }
            hashSet.add(obj);
            try {
                if (hashSet.size() > this.l) {
                    hashSet.remove(obj);
                    k10.k(EnumC1495p1.INFO, "Max depth exceeded. Calling toString() on object.", new Object[0]);
                    return obj.toString();
                }
                try {
                    if (obj.getClass().isArray()) {
                        Object[] objArr = (Object[]) obj;
                        ArrayList arrayList2 = new ArrayList();
                        int length2 = objArr.length;
                        while (i7 < length2) {
                            arrayList2.add(p(k10, objArr[i7]));
                            i7++;
                        }
                        obj2 = arrayList2;
                    } else if (obj instanceof Collection) {
                        ArrayList arrayList3 = new ArrayList();
                        Iterator it = ((Collection) obj).iterator();
                        while (it.hasNext()) {
                            arrayList3.add(p(k10, it.next()));
                        }
                        obj2 = arrayList3;
                    } else {
                        if (obj instanceof Map) {
                            string = n((Map) obj, k10);
                        } else {
                            HashMap mapQ = q(k10, obj);
                            boolean zIsEmpty = mapQ.isEmpty();
                            string = mapQ;
                            if (zIsEmpty) {
                                string = obj.toString();
                            }
                        }
                        obj2 = string;
                    }
                } catch (Exception e10) {
                    k10.s(EnumC1495p1.INFO, "Not serializing object due to throwing sub-path.", e10);
                }
                return obj2;
            } finally {
                hashSet.remove(obj);
            }
        }
        return obj.toString();
    }

    public HashMap q(K k10, Object obj) {
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        HashMap map = new HashMap();
        for (Field field : declaredFields) {
            if (!Modifier.isTransient(field.getModifiers()) && !Modifier.isStatic(field.getModifiers())) {
                String name = field.getName();
                try {
                    field.setAccessible(true);
                    map.put(name, p(k10, field.get(obj)));
                    field.setAccessible(false);
                } catch (Exception unused) {
                    k10.k(EnumC1495p1.INFO, android.hardware.bydauto.ac.tsimpl.a.i("Cannot access field ", name, "."), new Object[0]);
                }
            }
        }
        return map;
    }

    public synchronized void r(I3.a aVar, Bitmap bitmap, Map map, int i7) {
        try {
            LinkedHashMap linkedHashMap = (LinkedHashMap) this.f944m;
            Object arrayList = linkedHashMap.get(aVar);
            if (arrayList == null) {
                arrayList = new ArrayList();
                linkedHashMap.put(aVar, arrayList);
            }
            ArrayList arrayList2 = (ArrayList) arrayList;
            int iIdentityHashCode = System.identityHashCode(bitmap);
            I3.f fVar = new I3.f(iIdentityHashCode, new WeakReference(bitmap), map, i7);
            int size = arrayList2.size();
            int i10 = 0;
            while (true) {
                if (i10 >= size) {
                    arrayList2.add(fVar);
                    break;
                }
                I3.f fVar2 = (I3.f) arrayList2.get(i10);
                if (i7 < fVar2.d) {
                    i10++;
                } else if (fVar2.f2939a == iIdentityHashCode && fVar2.f2940b.get() == bitmap) {
                    arrayList2.set(i10, fVar);
                } else {
                    arrayList2.add(i10, fVar);
                }
            }
            int i11 = this.l;
            this.l = i11 + 1;
            if (i11 >= 10) {
                h();
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public boolean s(int i7) {
        return AbstractC2641j.c(this.l) >= AbstractC2641j.c(i7);
    }

    public void t(String str, Object... objArr) {
        if (s(5)) {
            Log.v((String) this.f944m, String.format(str, objArr));
        }
    }

    public /* synthetic */ j(Object obj) {
        this.l = 0;
        this.f944m = obj;
    }

    public /* synthetic */ j(Object obj, int i7) {
        this.f944m = obj;
        this.l = i7;
    }

    public j(Shader shader, ColorStateList colorStateList, int i7) {
        this.f944m = shader;
        this.l = i7;
    }

    public j(int i7, boolean z6) {
        switch (i7) {
            case 3:
                this.f944m = new LinkedHashMap();
                break;
            case 5:
                this.f944m = new h2.t(8);
                break;
            case 7:
                this.l = 255;
                this.f944m = null;
                break;
            case 13:
                this.f944m = Bitmap.CompressFormat.JPEG;
                this.l = 100;
                break;
            case 14:
                this.l = 0;
                this.f944m = new StringBuilder();
                break;
            default:
                this.l = 1;
                this.f944m = Collections.singletonList(null);
                break;
        }
    }
}
