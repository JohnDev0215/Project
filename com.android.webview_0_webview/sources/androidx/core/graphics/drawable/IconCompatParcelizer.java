package androidx.core.graphics.drawable;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class IconCompatParcelizer {
    /* JADX WARN: Removed duplicated region for block: B:22:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0094  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static androidx.core.graphics.drawable.IconCompat read(defpackage.W7 r8) {
        /*
            androidx.core.graphics.drawable.IconCompat r0 = new androidx.core.graphics.drawable.IconCompat
            r0.<init>()
            int r1 = r0.a
            r2 = 1
            int r1 = r8.e(r1, r2)
            r0.a = r1
            byte[] r1 = r0.c
            r3 = 2
            boolean r4 = r8.d(r3)
            if (r4 != 0) goto L18
            goto L2d
        L18:
            r1 = r8
            X7 r1 = (defpackage.X7) r1
            android.os.Parcel r4 = r1.b
            int r4 = r4.readInt()
            if (r4 >= 0) goto L25
            r1 = 0
            goto L2d
        L25:
            byte[] r4 = new byte[r4]
            android.os.Parcel r1 = r1.b
            r1.readByteArray(r4)
            r1 = r4
        L2d:
            r0.c = r1
            android.os.Parcelable r1 = r0.d
            r4 = 3
            android.os.Parcelable r1 = r8.f(r1, r4)
            r0.d = r1
            int r1 = r0.e
            r5 = 4
            int r1 = r8.e(r1, r5)
            r0.e = r1
            int r1 = r0.f
            r6 = 5
            int r1 = r8.e(r1, r6)
            r0.f = r1
            android.content.res.ColorStateList r1 = r0.g
            r7 = 6
            android.os.Parcelable r1 = r8.f(r1, r7)
            android.content.res.ColorStateList r1 = (android.content.res.ColorStateList) r1
            r0.g = r1
            java.lang.String r1 = r0.i
            r7 = 7
            boolean r7 = r8.d(r7)
            if (r7 != 0) goto L5f
            goto L67
        L5f:
            X7 r8 = (defpackage.X7) r8
            android.os.Parcel r8 = r8.b
            java.lang.String r1 = r8.readString()
        L67:
            r0.i = r1
            android.graphics.PorterDuff$Mode r8 = android.graphics.PorterDuff.Mode.valueOf(r1)
            r0.h = r8
            int r8 = r0.a
            r1 = -1
            if (r8 == r1) goto La8
            if (r8 == r2) goto L94
            if (r8 == r3) goto L84
            if (r8 == r4) goto L7f
            if (r8 == r5) goto L84
            if (r8 == r6) goto L94
            goto Lae
        L7f:
            byte[] r8 = r0.c
            r0.b = r8
            goto Lae
        L84:
            java.lang.String r8 = new java.lang.String
            byte[] r1 = r0.c
            java.lang.String r2 = "UTF-16"
            java.nio.charset.Charset r2 = java.nio.charset.Charset.forName(r2)
            r8.<init>(r1, r2)
            r0.b = r8
            goto Lae
        L94:
            android.os.Parcelable r8 = r0.d
            if (r8 == 0) goto L9b
            r0.b = r8
            goto Lae
        L9b:
            byte[] r8 = r0.c
            r0.b = r8
            r0.a = r4
            r1 = 0
            r0.e = r1
            int r8 = r8.length
            r0.f = r8
            goto Lae
        La8:
            android.os.Parcelable r8 = r0.d
            if (r8 == 0) goto Laf
            r0.b = r8
        Lae:
            return r0
        Laf:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Invalid icon"
            r8.<init>(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.drawable.IconCompatParcelizer.read(W7):androidx.core.graphics.drawable.IconCompat");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0049  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void write(androidx.core.graphics.drawable.IconCompat r8, defpackage.W7 r9) {
        /*
            java.util.Objects.requireNonNull(r9)
            android.graphics.PorterDuff$Mode r0 = r8.h
            java.lang.String r0 = r0.name()
            r8.i = r0
            int r0 = r8.a
            r1 = 5
            r2 = 4
            r3 = 3
            r4 = 2
            r5 = 1
            r6 = -1
            if (r0 == r6) goto L50
            if (r0 == r5) goto L49
            java.lang.String r7 = "UTF-16"
            if (r0 == r4) goto L3a
            if (r0 == r3) goto L33
            if (r0 == r2) goto L22
            if (r0 == r1) goto L49
            goto L56
        L22:
            java.lang.Object r0 = r8.b
            java.lang.String r0 = r0.toString()
            java.nio.charset.Charset r7 = java.nio.charset.Charset.forName(r7)
            byte[] r0 = r0.getBytes(r7)
            r8.c = r0
            goto L56
        L33:
            java.lang.Object r0 = r8.b
            byte[] r0 = (byte[]) r0
            r8.c = r0
            goto L56
        L3a:
            java.lang.Object r0 = r8.b
            java.lang.String r0 = (java.lang.String) r0
            java.nio.charset.Charset r7 = java.nio.charset.Charset.forName(r7)
            byte[] r0 = r0.getBytes(r7)
            r8.c = r0
            goto L56
        L49:
            java.lang.Object r0 = r8.b
            android.os.Parcelable r0 = (android.os.Parcelable) r0
            r8.d = r0
            goto L56
        L50:
            java.lang.Object r0 = r8.b
            android.os.Parcelable r0 = (android.os.Parcelable) r0
            r8.d = r0
        L56:
            int r0 = r8.a
            r9.i(r0, r5)
            byte[] r0 = r8.c
            r9.h(r4)
            r4 = r9
            X7 r4 = (defpackage.X7) r4
            if (r0 == 0) goto L71
            android.os.Parcel r5 = r4.b
            int r6 = r0.length
            r5.writeInt(r6)
            android.os.Parcel r5 = r4.b
            r5.writeByteArray(r0)
            goto L76
        L71:
            android.os.Parcel r0 = r4.b
            r0.writeInt(r6)
        L76:
            android.os.Parcelable r0 = r8.d
            r9.j(r0, r3)
            int r0 = r8.e
            r9.i(r0, r2)
            int r0 = r8.f
            r9.i(r0, r1)
            android.content.res.ColorStateList r0 = r8.g
            r1 = 6
            r9.j(r0, r1)
            java.lang.String r8 = r8.i
            r0 = 7
            r9.h(r0)
            android.os.Parcel r9 = r4.b
            r9.writeString(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.drawable.IconCompatParcelizer.write(androidx.core.graphics.drawable.IconCompat, W7):void");
    }
}
