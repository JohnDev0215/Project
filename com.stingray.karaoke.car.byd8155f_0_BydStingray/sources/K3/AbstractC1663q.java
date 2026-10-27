package k3;

import android.graphics.Bitmap;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import d5.C1078G;
import e2.C1195c;
import e2.C1212u;
import e2.C1214w;
import e2.C1216y;
import e2.C1217z;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import l3.C1740E;
import l3.C1741F;
import l3.C1746b;
import l3.C1755k;

/* JADX INFO: renamed from: k3.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1663q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final d5.M f18029a;

    static {
        int i7 = d5.M.f13389n;
        Object[] objArr = new Object[32];
        objArr[0] = "android.media.metadata.TITLE";
        objArr[1] = "android.media.metadata.ARTIST";
        objArr[2] = "android.media.metadata.DURATION";
        objArr[3] = "android.media.metadata.ALBUM";
        objArr[4] = "android.media.metadata.AUTHOR";
        objArr[5] = "android.media.metadata.WRITER";
        System.arraycopy(new String[]{"android.media.metadata.COMPOSER", "android.media.metadata.COMPILATION", "android.media.metadata.DATE", "android.media.metadata.YEAR", "android.media.metadata.GENRE", "android.media.metadata.TRACK_NUMBER", "android.media.metadata.NUM_TRACKS", "android.media.metadata.DISC_NUMBER", "android.media.metadata.ALBUM_ARTIST", "android.media.metadata.ART", "android.media.metadata.ART_URI", "android.media.metadata.ALBUM_ART", "android.media.metadata.ALBUM_ART_URI", "android.media.metadata.USER_RATING", "android.media.metadata.RATING", "android.media.metadata.DISPLAY_TITLE", "android.media.metadata.DISPLAY_SUBTITLE", "android.media.metadata.DISPLAY_DESCRIPTION", "android.media.metadata.DISPLAY_ICON", "android.media.metadata.DISPLAY_ICON_URI", "android.media.metadata.MEDIA_ID", "android.media.metadata.MEDIA_URI", "android.media.metadata.BT_FOLDER_TYPE", "android.media.metadata.ADVERTISEMENT", "android.media.metadata.DOWNLOAD_STATUS", "androidx.media3.session.EXTRAS_KEY_MEDIA_TYPE_COMPAT"}, 0, objArr, 6, 26);
        f18029a = d5.M.u(32, objArr);
    }

    public static long a(l3.X x8, C1741F c1741f, long j3) {
        long j10 = x8 == null ? 0L : x8.f18553n;
        long jC = c(x8, c1741f, j3);
        long jD = d(c1741f);
        return jD == -9223372036854775807L ? Math.max(jC, j10) : AbstractC1343A.j(j10, jC, jD);
    }

    public static byte[] b(Bitmap bitmap) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return byteArray;
        } catch (Throwable th) {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static long c(l3.X x8, C1741F c1741f, long j3) {
        if (x8 == null) {
            return 0L;
        }
        long jMax = x8.f18552m;
        if (x8.l == 3) {
            jMax = Math.max(0L, jMax + ((long) (x8.o * ((j3 == -9223372036854775807L ? null : Long.valueOf(j3)) != null ? r3.longValue() : SystemClock.elapsedRealtime() - x8.f18557s))));
        }
        long j10 = jMax;
        long jD = d(c1741f);
        return jD == -9223372036854775807L ? Math.max(0L, j10) : AbstractC1343A.j(j10, 0L, jD);
    }

    public static long d(C1741F c1741f) {
        if (c1741f == null || !c1741f.l.containsKey("android.media.metadata.DURATION")) {
            return -9223372036854775807L;
        }
        long jA = c1741f.a("android.media.metadata.DURATION");
        if (jA <= 0) {
            return -9223372036854775807L;
        }
        return jA;
    }

    public static long e(int i7) {
        switch (i7) {
            case 0:
                return 0L;
            case 1:
                return 1L;
            case 2:
                return 2L;
            case 3:
                return 3L;
            case 4:
                return 4L;
            case 5:
                return 5L;
            case 6:
                return 6L;
            default:
                throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.e(i7, "Unrecognized FolderType: "));
        }
    }

    public static int f(long j3) {
        if (j3 == 0) {
            return 0;
        }
        if (j3 == 1) {
            return 1;
        }
        if (j3 == 2) {
            return 2;
        }
        if (j3 == 3) {
            return 3;
        }
        if (j3 == 4) {
            return 4;
        }
        if (j3 == 5) {
            return 5;
        }
        return j3 == 6 ? 6 : 0;
    }

    public static C1740E g(e2.F f10, Bitmap bitmap) {
        CharSequence charSequence;
        CharSequence charSequence2;
        CharSequence charSequence3;
        Bundle bundle;
        CharSequence charSequence4;
        String str = f10.f13878a.equals("") ? null : f10.f13878a;
        Bitmap bitmap2 = bitmap != null ? bitmap : null;
        e2.I i7 = f10.d;
        Bundle bundle2 = i7.f13957I;
        if (bundle2 != null) {
            bundle2 = new Bundle(bundle2);
        }
        Integer num = i7.f13970p;
        boolean z6 = (num == null || num.intValue() == -1) ? false : true;
        Integer num2 = i7.f13956H;
        boolean z10 = num2 != null;
        if (z6 || z10) {
            if (bundle2 == null) {
                bundle2 = new Bundle();
            }
            if (z6) {
                num.getClass();
                bundle2.putLong("android.media.extra.BT_FOLDER_TYPE", e(num.intValue()));
            }
            if (z10) {
                num2.getClass();
                bundle2.putLong("androidx.media3.session.EXTRAS_KEY_MEDIA_TYPE_COMPAT", num2.intValue());
            }
        }
        d5.I i10 = i7.f13958J;
        if (!i10.isEmpty()) {
            if (bundle2 == null) {
                bundle2 = new Bundle();
            }
            bundle2.putStringArrayList("androidx.media.utils.extras.CUSTOM_BROWSER_ACTION_ID_LIST", new ArrayList<>(i10));
        }
        CharSequence charSequence5 = i7.f13959a;
        CharSequence charSequence6 = i7.f13961e;
        if (charSequence6 != null) {
            if (bundle2 == null) {
                bundle2 = new Bundle();
            }
            bundle2.putCharSequence("androidx.media3.mediadescriptioncompat.title", charSequence5);
            CharSequence charSequence7 = i7.f13962f;
            charSequence = i7.f13963g;
            bundle = bundle2;
            charSequence2 = charSequence6;
            charSequence3 = charSequence7;
        } else {
            CharSequence[] charSequenceArr = new CharSequence[3];
            int i11 = 0;
            int i12 = 0;
            for (int i13 = 3; i11 < i13; i13 = 3) {
                String[] strArr = C1741F.o;
                if (i12 < strArr.length) {
                    int i14 = i12 + 1;
                    String str2 = strArr[i12];
                    str2.getClass();
                    switch (str2) {
                        case "android.media.metadata.ARTIST":
                            charSequence4 = i7.f13960b;
                            break;
                        case "android.media.metadata.WRITER":
                            charSequence4 = i7.f13980z;
                            break;
                        case "android.media.metadata.COMPOSER":
                            charSequence4 = i7.f13950A;
                            break;
                        case "android.media.metadata.ALBUM":
                            charSequence4 = i7.c;
                            break;
                        case "android.media.metadata.TITLE":
                            charSequence4 = charSequence5;
                            break;
                        case "android.media.metadata.ALBUM_ARTIST":
                            charSequence4 = i7.d;
                            break;
                        default:
                            charSequence4 = null;
                            break;
                    }
                    if (!TextUtils.isEmpty(charSequence4)) {
                        charSequenceArr[i11] = charSequence4;
                        i11++;
                    }
                    i12 = i14;
                } else {
                    CharSequence charSequence8 = charSequenceArr[0];
                    CharSequence charSequence9 = charSequenceArr[1];
                    charSequence = charSequenceArr[2];
                    charSequence2 = charSequence8;
                    charSequence3 = charSequence9;
                    bundle = bundle2;
                }
            }
            CharSequence charSequence82 = charSequenceArr[0];
            CharSequence charSequence92 = charSequenceArr[1];
            charSequence = charSequenceArr[2];
            charSequence2 = charSequence82;
            charSequence3 = charSequence92;
            bundle = bundle2;
        }
        return new C1740E(str, charSequence2, charSequence3, charSequence, bitmap2, i7.f13968m, bundle, f10.f13881f.f13854a);
    }

    public static e2.F h(String str, C1741F c1741f, int i7) {
        e2.B b10;
        C1212u c1212u = new C1212u();
        C1078G c1078g = d5.I.f13384m;
        d5.d0 d0Var = d5.d0.f13415p;
        Collections.emptyList();
        d5.d0 d0Var2 = d5.d0.f13415p;
        C1216y c1216y = new C1216y();
        e2.B b11 = e2.B.d;
        if (str == null) {
            str = null;
        }
        CharSequence charSequence = c1741f.l.getCharSequence("android.media.metadata.MEDIA_URI");
        String string = charSequence != null ? charSequence.toString() : null;
        if (string != null) {
            l3.P p10 = new l3.P(22, false);
            p10.f18537m = Uri.parse(string);
            b10 = new e2.B(p10);
        } else {
            b10 = b11;
        }
        e2.I iK = k(c1741f, i7);
        if (str == null) {
            str = "";
        }
        return new e2.F(str, new C1214w(c1212u), null, new C1217z(c1216y), iK != null ? iK : e2.I.f13914K, b10);
    }

    public static e2.F i(C1740E c1740e) {
        boolean z6 = false;
        c1740e.getClass();
        C1212u c1212u = new C1212u();
        C1078G c1078g = d5.I.f13384m;
        d5.d0 d0Var = d5.d0.f13415p;
        Collections.emptyList();
        C1216y c1216y = new C1216y();
        e2.B b10 = e2.B.d;
        String str = c1740e.l;
        if (str == null) {
            str = "";
        }
        String str2 = str;
        l3.P p10 = new l3.P(22, z6);
        p10.f18537m = c1740e.f18518s;
        e2.B b11 = new e2.B(p10);
        e2.I iJ = j(c1740e, 0);
        C1214w c1214w = new C1214w(c1212u);
        C1217z c1217z = new C1217z(c1216y);
        if (iJ == null) {
            iJ = e2.I.f13914K;
        }
        return new e2.F(str2, c1214w, null, c1217z, iJ, b11);
    }

    public static e2.I j(C1740E c1740e, int i7) {
        l3.Y y10;
        byte[] bArrB;
        if (c1740e == null) {
            return e2.I.f13914K;
        }
        e2.H h10 = new e2.H();
        h10.f13895f = c1740e.f18514n;
        h10.f13896g = c1740e.o;
        h10.f13901m = c1740e.f18516q;
        switch (i7) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                y10 = new l3.Y(-1.0f, i7);
                break;
            default:
                y10 = null;
                break;
        }
        h10.f13898i = o(y10);
        Bitmap bitmap = c1740e.f18515p;
        if (bitmap != null) {
            try {
                bArrB = b(bitmap);
            } catch (IOException e10) {
                AbstractC1346c.A("LegacyConversions", "Failed to convert iconBitmap to artworkData", e10);
                bArrB = null;
            }
            h10.b(bArrB, 3);
        }
        Bundle bundle = c1740e.f18517r;
        Bundle bundle2 = bundle != null ? new Bundle(bundle) : null;
        if (bundle2 != null && bundle2.containsKey("android.media.extra.BT_FOLDER_TYPE")) {
            h10.f13903p = Integer.valueOf(f(bundle2.getLong("android.media.extra.BT_FOLDER_TYPE")));
            bundle2.remove("android.media.extra.BT_FOLDER_TYPE");
        }
        h10.f13904q = Boolean.FALSE;
        if (bundle2 != null && bundle2.containsKey("androidx.media3.session.EXTRAS_KEY_MEDIA_TYPE_COMPAT")) {
            h10.f13889G = Integer.valueOf((int) bundle2.getLong("androidx.media3.session.EXTRAS_KEY_MEDIA_TYPE_COMPAT"));
            bundle2.remove("androidx.media3.session.EXTRAS_KEY_MEDIA_TYPE_COMPAT");
        }
        if (bundle2 != null && bundle2.containsKey("androidx.media.utils.extras.CUSTOM_BROWSER_ACTION_ID_LIST")) {
            ArrayList<String> stringArrayList = bundle2.getStringArrayList("androidx.media.utils.extras.CUSTOM_BROWSER_ACTION_ID_LIST");
            stringArrayList.getClass();
            h10.f13891I = d5.I.w(d5.I.w(stringArrayList));
        }
        CharSequence charSequence = c1740e.f18513m;
        if (bundle2 == null || !bundle2.containsKey("androidx.media3.mediadescriptioncompat.title")) {
            h10.f13892a = charSequence;
        } else {
            h10.f13892a = bundle2.getCharSequence("androidx.media3.mediadescriptioncompat.title");
            h10.f13894e = charSequence;
            bundle2.remove("androidx.media3.mediadescriptioncompat.title");
        }
        if (bundle2 != null && !bundle2.isEmpty()) {
            h10.f13890H = bundle2;
        }
        h10.f13905r = Boolean.TRUE;
        return new e2.I(h10);
    }

    public static e2.I k(C1741F c1741f, int i7) {
        l3.Y yA;
        l3.Y yA2;
        l3.Y y10;
        String string;
        if (c1741f == null) {
            return e2.I.f13914K;
        }
        e2.H h10 = new e2.H();
        Bundle bundle = c1741f.l;
        CharSequence charSequence = bundle.getCharSequence("android.media.metadata.TITLE");
        CharSequence charSequence2 = bundle.getCharSequence("android.media.metadata.DISPLAY_TITLE");
        h10.f13892a = charSequence != null ? charSequence : charSequence2;
        Bitmap bitmap = null;
        if (charSequence == null) {
            charSequence2 = null;
        }
        h10.f13894e = charSequence2;
        h10.f13895f = bundle.getCharSequence("android.media.metadata.DISPLAY_SUBTITLE");
        h10.f13896g = bundle.getCharSequence("android.media.metadata.DISPLAY_DESCRIPTION");
        h10.f13893b = bundle.getCharSequence("android.media.metadata.ARTIST");
        h10.c = bundle.getCharSequence("android.media.metadata.ALBUM");
        h10.d = bundle.getCharSequence("android.media.metadata.ALBUM_ARTIST");
        try {
            yA = l3.Y.a(bundle.getParcelable("android.media.metadata.RATING"));
        } catch (Exception e10) {
            h6.n.u("MediaMetadata", "Failed to retrieve a key as Rating.", e10);
            yA = null;
        }
        h10.f13899j = o(yA);
        if (bundle.containsKey("android.media.metadata.DURATION")) {
            long jA = c1741f.a("android.media.metadata.DURATION");
            if (jA >= 0) {
                h10.c(Long.valueOf(jA));
            }
        }
        try {
            yA2 = l3.Y.a(bundle.getParcelable("android.media.metadata.USER_RATING"));
        } catch (Exception e11) {
            h6.n.u("MediaMetadata", "Failed to retrieve a key as Rating.", e11);
            yA2 = null;
        }
        e2.X xO = o(yA2);
        if (xO != null) {
            h10.f13898i = xO;
        } else {
            switch (i7) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    y10 = new l3.Y(-1.0f, i7);
                    break;
                default:
                    y10 = null;
                    break;
            }
            h10.f13898i = o(y10);
        }
        if (bundle.containsKey("android.media.metadata.YEAR")) {
            h10.f13906s = Integer.valueOf((int) c1741f.a("android.media.metadata.YEAR"));
        }
        String[] strArr = {"android.media.metadata.DISPLAY_ICON_URI", "android.media.metadata.ALBUM_ART_URI", "android.media.metadata.ART_URI"};
        int i10 = 0;
        int i11 = 0;
        while (true) {
            if (i11 < 3) {
                String str = strArr[i11];
                if (bundle.containsKey(str)) {
                    CharSequence charSequence3 = bundle.getCharSequence(str);
                    if (charSequence3 != null) {
                        string = charSequence3.toString();
                    }
                } else {
                    i11++;
                }
            }
        }
        string = null;
        if (string != null) {
            h10.f13901m = Uri.parse(string);
        }
        String[] strArr2 = {"android.media.metadata.DISPLAY_ICON", "android.media.metadata.ALBUM_ART", "android.media.metadata.ART"};
        while (true) {
            if (i10 < 3) {
                String str2 = strArr2[i10];
                if (bundle.containsKey(str2)) {
                    try {
                        bitmap = (Bitmap) bundle.getParcelable(str2);
                    } catch (Exception e12) {
                        h6.n.u("MediaMetadata", "Failed to retrieve a key as Bitmap.", e12);
                    }
                } else {
                    i10++;
                }
            }
        }
        if (bitmap != null) {
            try {
                h10.b(b(bitmap), 3);
            } catch (IOException e13) {
                AbstractC1346c.A("LegacyConversions", "Failed to convert artworkBitmap to artworkData", e13);
            }
        }
        boolean zContainsKey = bundle.containsKey("android.media.metadata.BT_FOLDER_TYPE");
        h10.f13904q = Boolean.valueOf(zContainsKey);
        if (zContainsKey) {
            h10.f13903p = Integer.valueOf(f(c1741f.a("android.media.metadata.BT_FOLDER_TYPE")));
        }
        if (bundle.containsKey("androidx.media3.session.EXTRAS_KEY_MEDIA_TYPE_COMPAT")) {
            h10.f13889G = Integer.valueOf((int) c1741f.a("androidx.media3.session.EXTRAS_KEY_MEDIA_TYPE_COMPAT"));
        }
        h10.f13905r = Boolean.TRUE;
        Bundle bundle2 = new Bundle(bundle);
        d5.s0 it = f18029a.iterator();
        while (it.hasNext()) {
            bundle2.remove((String) it.next());
        }
        if (!bundle2.isEmpty()) {
            h10.f13890H = bundle2;
        }
        return new e2.I(h10);
    }

    public static C1741F l(e2.I i7, String str, Uri uri, long j3, Bitmap bitmap) {
        Long l;
        C1755k c1755k = new C1755k();
        c1755k.d("android.media.metadata.MEDIA_ID", str);
        CharSequence charSequence = i7.f13959a;
        if (charSequence != null) {
            c1755k.e(charSequence, "android.media.metadata.TITLE");
        }
        CharSequence charSequence2 = i7.f13961e;
        if (charSequence2 != null) {
            c1755k.e(charSequence2, "android.media.metadata.DISPLAY_TITLE");
        }
        CharSequence charSequence3 = i7.f13962f;
        if (charSequence3 != null) {
            c1755k.e(charSequence3, "android.media.metadata.DISPLAY_SUBTITLE");
        }
        CharSequence charSequence4 = i7.f13963g;
        if (charSequence4 != null) {
            c1755k.e(charSequence4, "android.media.metadata.DISPLAY_DESCRIPTION");
        }
        CharSequence charSequence5 = i7.f13960b;
        if (charSequence5 != null) {
            c1755k.e(charSequence5, "android.media.metadata.ARTIST");
        }
        CharSequence charSequence6 = i7.c;
        if (charSequence6 != null) {
            c1755k.e(charSequence6, "android.media.metadata.ALBUM");
        }
        CharSequence charSequence7 = i7.d;
        if (charSequence7 != null) {
            c1755k.e(charSequence7, "android.media.metadata.ALBUM_ARTIST");
        }
        if (i7.f13974t != null) {
            c1755k.b("android.media.metadata.YEAR", r4.intValue());
        }
        if (uri != null) {
            c1755k.d("android.media.metadata.MEDIA_URI", uri.toString());
        }
        Uri uri2 = i7.f13968m;
        if (uri2 != null) {
            c1755k.d("android.media.metadata.DISPLAY_ICON_URI", uri2.toString());
            c1755k.d("android.media.metadata.ALBUM_ART_URI", uri2.toString());
            c1755k.d("android.media.metadata.ART_URI", uri2.toString());
        }
        if (bitmap != null) {
            c1755k.a("android.media.metadata.DISPLAY_ICON", bitmap);
            c1755k.a("android.media.metadata.ALBUM_ART", bitmap);
        }
        Integer num = i7.f13970p;
        if (num != null && num.intValue() != -1) {
            c1755k.b("android.media.metadata.BT_FOLDER_TYPE", e(num.intValue()));
        }
        if (j3 == -9223372036854775807L && (l = i7.f13964h) != null) {
            j3 = l.longValue();
        }
        if (j3 != -9223372036854775807L) {
            c1755k.b("android.media.metadata.DURATION", j3);
        }
        l3.Y yP = p(i7.f13965i);
        if (yP != null) {
            c1755k.c("android.media.metadata.USER_RATING", yP);
        }
        l3.Y yP2 = p(i7.f13966j);
        if (yP2 != null) {
            c1755k.c("android.media.metadata.RATING", yP2);
        }
        if (i7.f13956H != null) {
            c1755k.b("androidx.media3.session.EXTRAS_KEY_MEDIA_TYPE_COMPAT", r4.intValue());
        }
        Bundle bundle = i7.f13957I;
        if (bundle != null) {
            for (String str2 : bundle.keySet()) {
                Object obj = bundle.get(str2);
                if (obj == null || (obj instanceof CharSequence)) {
                    c1755k.e((CharSequence) obj, str2);
                } else if ((obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long)) {
                    c1755k.b(str2, ((Number) obj).longValue());
                }
            }
        }
        return new C1741F(c1755k.f18579a);
    }

    public static e2.O m(l3.X x8) {
        if (x8 == null || x8.l != 7) {
            return null;
        }
        CharSequence charSequence = x8.f18556r;
        String string = charSequence != null ? charSequence.toString() : null;
        int iR = r(x8.f18555q);
        if (iR == -5) {
            iR = 2000;
        } else if (iR == -1) {
            iR = 1000;
        }
        int i7 = iR;
        Bundle bundle = x8.f18560v;
        if (bundle == null) {
            bundle = Bundle.EMPTY;
        }
        return new e2.O(string, null, i7, bundle, SystemClock.elapsedRealtime());
    }

    public static int n(int i7) {
        if (i7 == 0) {
            return 0;
        }
        int i10 = 1;
        if (i7 != 1) {
            i10 = 2;
            if (i7 != 2) {
                AbstractC1346c.z("LegacyConversions", "Unrecognized RepeatMode: " + i7 + " was converted to `PlaybackStateCompat.REPEAT_MODE_NONE`");
                return 0;
            }
        }
        return i10;
    }

    public static e2.X o(l3.Y y10) {
        if (y10 == null) {
            return null;
        }
        float f10 = y10.f18562m;
        int i7 = y10.l;
        switch (i7) {
            case 1:
                if (!y10.f()) {
                }
                break;
            case 2:
                if (!y10.f()) {
                }
                break;
            case 3:
                if (!y10.f()) {
                }
                break;
            case 4:
                if (!y10.f()) {
                }
                break;
            case 5:
                if (!y10.f()) {
                }
                break;
            case 6:
                if (y10.f()) {
                    if (i7 != 6 || !y10.f()) {
                        f10 = -1.0f;
                    }
                }
                break;
        }
        return null;
    }

    public static l3.Y p(e2.X x8) {
        if (x8 == null) {
            return null;
        }
        int iV = v(x8);
        if (!x8.b()) {
            switch (iV) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    return new l3.Y(-1.0f, iV);
                default:
                    return null;
            }
        }
        switch (iV) {
            case 1:
                return new l3.Y(((e2.r) x8).c ? 1.0f : 0.0f, 1);
            case 2:
                return new l3.Y(((e2.a0) x8).c ? 1.0f : 0.0f, 2);
            case 3:
            case 4:
            case 5:
                return l3.Y.h(((e2.Y) x8).c, iV);
            case 6:
                return l3.Y.g(((e2.N) x8).f13985b);
            default:
                return null;
        }
    }

    public static int q(int i7) {
        if (i7 == -1 || i7 == 0) {
            return 0;
        }
        int i10 = 1;
        if (i7 != 1) {
            i10 = 2;
            if (i7 != 2 && i7 != 3) {
                AbstractC1346c.z("LegacyConversions", "Unrecognized PlaybackStateCompat.RepeatMode: " + i7 + " was converted to `Player.REPEAT_MODE_OFF`");
                return 0;
            }
        }
        return i10;
    }

    public static int r(int i7) {
        switch (i7) {
            case 1:
                return -2;
            case 2:
                return -6;
            case 3:
                return -102;
            case 4:
                return -103;
            case 5:
                return -104;
            case 6:
                return -105;
            case 7:
                return -106;
            case 8:
                return -110;
            case 9:
                return -107;
            case 10:
                return 1;
            case 11:
                return -109;
            default:
                return -1;
        }
    }

    public static boolean s(int i7) {
        if (i7 == -1 || i7 == 0) {
            return false;
        }
        if (i7 == 1 || i7 == 2) {
            return true;
        }
        throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.e(i7, "Unrecognized ShuffleMode: "));
    }

    public static void t(h5.y yVar) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        boolean z6 = false;
        long j3 = 3000;
        while (true) {
            try {
                try {
                    yVar.get(j3, TimeUnit.MILLISECONDS);
                    if (z6) {
                        return;
                    } else {
                        return;
                    }
                } catch (InterruptedException unused) {
                    z6 = true;
                    long jElapsedRealtime2 = SystemClock.elapsedRealtime() - jElapsedRealtime;
                    if (jElapsedRealtime2 >= 3000) {
                        throw new TimeoutException();
                    }
                    j3 = 3000 - jElapsedRealtime2;
                }
            } finally {
                if (z6) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static int u(C1195c c1195c) {
        int i7 = 1;
        int i10 = C1746b.f18567b;
        AudioAttributes.Builder builder = new AudioAttributes.Builder();
        builder.setContentType(c1195c.f14051a);
        builder.setFlags(c1195c.f14052b);
        builder.setUsage(c1195c.c);
        AudioAttributes audioAttributesBuild = builder.build();
        audioAttributesBuild.getClass();
        int flags = audioAttributesBuild.getFlags();
        int usage = audioAttributesBuild.getUsage();
        if ((flags & 1) != 1) {
            if ((flags & 4) != 4) {
                switch (usage) {
                    case 0:
                    case 1:
                    case 12:
                    case 14:
                    case 15:
                    case 16:
                    default:
                        i7 = 3;
                        break;
                    case 2:
                        i7 = 0;
                        break;
                    case 3:
                        i7 = 8;
                        break;
                    case 4:
                        i7 = 4;
                        break;
                    case 5:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                        i7 = 5;
                        break;
                    case 6:
                        i7 = 2;
                        break;
                    case 11:
                        i7 = 10;
                        break;
                    case 13:
                        break;
                }
            } else {
                i7 = 6;
            }
        } else {
            i7 = 7;
        }
        if (i7 == Integer.MIN_VALUE) {
            return 3;
        }
        return i7;
    }

    public static int v(e2.X x8) {
        if (x8 instanceof e2.r) {
            return 1;
        }
        if (x8 instanceof e2.a0) {
            return 2;
        }
        if (!(x8 instanceof e2.Y)) {
            return x8 instanceof e2.N ? 6 : 0;
        }
        int i7 = ((e2.Y) x8).f14016b;
        int i10 = 3;
        if (i7 != 3) {
            i10 = 4;
            if (i7 != 4) {
                i10 = 5;
                if (i7 != 5) {
                    return 0;
                }
            }
        }
        return i10;
    }

    public static boolean w(long j3, long j10) {
        return (j3 & j10) != 0;
    }
}
