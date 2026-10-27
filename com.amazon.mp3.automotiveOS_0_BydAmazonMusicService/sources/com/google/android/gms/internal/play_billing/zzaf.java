package com.google.android.gms.internal.play_billing;

import java.util.Arrays;
import javax.annotation.CheckForNull;

/* JADX INFO: compiled from: com.android.billingclient:billing@@5.2.1 */
/* JADX INFO: loaded from: classes6.dex */
final class zzaf extends zzx {
    static final zzx zza = new zzaf(null, new Object[0], 0);
    final transient Object[] zzb;

    @CheckForNull
    private final transient Object zzc;
    private final transient int zzd;

    private zzaf(@CheckForNull Object obj, Object[] objArr, int i) {
        this.zzc = obj;
        this.zzb = objArr;
        this.zzd = i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v0 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v19, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v24 */
    /* JADX WARN: Type inference failed for: r2v27 */
    /* JADX WARN: Type inference failed for: r2v34 */
    /* JADX WARN: Type inference failed for: r2v35 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r5v4, types: [int[]] */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v3, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r6v4 */
    static zzaf zzf(int i, Object[] objArr, zzw zzwVar) {
        short[] sArr;
        ?? r2;
        ?? r6;
        int i2 = i;
        Object[] objArrCopyOf = objArr;
        if (i2 == 0) {
            return (zzaf) zza;
        }
        zzv zzvVar = null;
        ?? r22 = 0;
        zzv zzvVar2 = null;
        zzv zzvVar3 = null;
        if (i2 == 1) {
            Object obj = objArrCopyOf[0];
            obj.getClass();
            Object obj2 = objArrCopyOf[1];
            obj2.getClass();
            zzp.zza(obj, obj2);
            return new zzaf(null, objArrCopyOf, 1);
        }
        zzm.zzb(i2, objArrCopyOf.length >> 1, "index");
        char c = 2;
        int iMax = Math.max(i2, 2);
        int i3 = 1073741824;
        if (iMax < 751619276) {
            int iHighestOneBit = Integer.highestOneBit(iMax - 1);
            i3 = iHighestOneBit + iHighestOneBit;
            while (((double) i3) * 0.7d < iMax) {
                i3 += i3;
            }
        } else if (iMax >= 1073741824) {
            throw new IllegalArgumentException("collection too large");
        }
        if (i2 == 1) {
            Object obj3 = objArrCopyOf[0];
            obj3.getClass();
            Object obj4 = objArrCopyOf[1];
            obj4.getClass();
            zzp.zza(obj3, obj4);
        } else {
            int i4 = i3 - 1;
            byte b = -1;
            if (i3 <= 128) {
                byte[] bArr = new byte[i3];
                Arrays.fill(bArr, (byte) -1);
                int i5 = 0;
                for (int i6 = 0; i6 < i2; i6++) {
                    int i7 = i5 + i5;
                    int i8 = i6 + i6;
                    Object obj5 = objArrCopyOf[i8];
                    obj5.getClass();
                    Object obj6 = objArrCopyOf[i8 ^ 1];
                    obj6.getClass();
                    zzp.zza(obj5, obj6);
                    int iZza = zzq.zza(obj5.hashCode());
                    while (true) {
                        int i9 = iZza & i4;
                        int i10 = bArr[i9] & 255;
                        if (i10 == 255) {
                            bArr[i9] = (byte) i7;
                            if (i5 < i6) {
                                objArrCopyOf[i7] = obj5;
                                objArrCopyOf[i7 ^ 1] = obj6;
                            }
                            i5++;
                        } else {
                            if (obj5.equals(objArrCopyOf[i10 == true ? 1 : 0])) {
                                int i11 = ~i10;
                                Object obj7 = objArrCopyOf[i11 == true ? 1 : 0];
                                obj7.getClass();
                                zzv zzvVar4 = new zzv(obj5, obj6, obj7);
                                objArrCopyOf[i11 == true ? 1 : 0] = obj6;
                                zzvVar2 = zzvVar4;
                                break;
                            }
                            iZza = i9 + 1;
                        }
                    }
                }
                if (i5 == i2) {
                    r2 = bArr;
                    c = 2;
                    r22 = r2;
                } else {
                    r22 = new Object[]{bArr, Integer.valueOf(i5), zzvVar2};
                    c = 2;
                }
            } else if (i3 <= 32768) {
                sArr = new short[i3];
                Arrays.fill(sArr, (short) -1);
                int i12 = 0;
                for (int i13 = 0; i13 < i2; i13++) {
                    int i14 = i12 + i12;
                    int i15 = i13 + i13;
                    Object obj8 = objArrCopyOf[i15];
                    obj8.getClass();
                    Object obj9 = objArrCopyOf[i15 ^ 1];
                    obj9.getClass();
                    zzp.zza(obj8, obj9);
                    int iZza2 = zzq.zza(obj8.hashCode());
                    while (true) {
                        int i16 = iZza2 & i4;
                        char c2 = (char) sArr[i16];
                        if (c2 == 65535) {
                            sArr[i16] = (short) i14;
                            if (i12 < i13) {
                                objArrCopyOf[i14] = obj8;
                                objArrCopyOf[i14 ^ 1] = obj9;
                            }
                            i12++;
                        } else {
                            if (obj8.equals(objArrCopyOf[c2])) {
                                int i17 = c2 ^ 1;
                                Object obj10 = objArrCopyOf[i17 == true ? 1 : 0];
                                obj10.getClass();
                                zzv zzvVar5 = new zzv(obj8, obj9, obj10);
                                objArrCopyOf[i17 == true ? 1 : 0] = obj9;
                                zzvVar3 = zzvVar5;
                                break;
                            }
                            iZza2 = i16 + 1;
                        }
                    }
                }
                if (i12 != i2) {
                    c = 2;
                    r6 = new Object[]{sArr, Integer.valueOf(i12), zzvVar3};
                    r22 = r6;
                }
                r2 = sArr;
                c = 2;
                r22 = r2;
            } else {
                sArr = new int[i3];
                Arrays.fill((int[]) sArr, -1);
                int i18 = 0;
                int i19 = 0;
                while (i18 < i2) {
                    int i20 = i19 + i19;
                    int i21 = i18 + i18;
                    Object obj11 = objArrCopyOf[i21];
                    obj11.getClass();
                    Object obj12 = objArrCopyOf[i21 ^ 1];
                    obj12.getClass();
                    zzp.zza(obj11, obj12);
                    int iZza3 = zzq.zza(obj11.hashCode());
                    while (true) {
                        int i22 = iZza3 & i4;
                        ?? r15 = sArr[i22];
                        if (r15 == b) {
                            sArr[i22] = i20;
                            if (i19 < i18) {
                                objArrCopyOf[i20] = obj11;
                                objArrCopyOf[i20 ^ 1] = obj12;
                            }
                            i19++;
                        } else {
                            if (obj11.equals(objArrCopyOf[r15])) {
                                int i23 = r15 ^ 1;
                                Object obj13 = objArrCopyOf[i23 == true ? 1 : 0];
                                obj13.getClass();
                                zzv zzvVar6 = new zzv(obj11, obj12, obj13);
                                objArrCopyOf[i23 == true ? 1 : 0] = obj12;
                                zzvVar = zzvVar6;
                                break;
                            }
                            iZza3 = i22 + 1;
                            b = -1;
                        }
                    }
                    i18++;
                    b = -1;
                }
                if (i19 != i2) {
                    c = 2;
                    r6 = new Object[]{sArr, Integer.valueOf(i19), zzvVar};
                    r22 = r6;
                }
                r2 = sArr;
                c = 2;
                r22 = r2;
            }
        }
        boolean z = r22 instanceof Object[];
        ?? r23 = r22;
        if (z) {
            Object[] objArr2 = (Object[]) r22;
            zzwVar.zzc = (zzv) objArr2[c];
            Object obj14 = objArr2[0];
            int iIntValue = ((Integer) objArr2[1]).intValue();
            objArrCopyOf = Arrays.copyOf(objArrCopyOf, iIntValue + iIntValue);
            r23 = obj14;
            i2 = iIntValue;
        }
        return new zzaf(r23, objArrCopyOf, i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x0009 A[EDGE_INSN: B:43:0x0009->B:4:0x0009 BREAK  A[LOOP:0: B:15:0x0038->B:21:0x004e], EDGE_INSN: B:45:0x0009->B:4:0x0009 BREAK  A[LOOP:1: B:25:0x0063->B:31:0x007a], EDGE_INSN: B:47:0x0009->B:4:0x0009 BREAK  A[LOOP:2: B:33:0x0089->B:42:0x00a0]] */
    @Override // com.google.android.gms.internal.play_billing.zzx, java.util.Map
    @javax.annotation.CheckForNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object get(@javax.annotation.CheckForNull java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = r9.zzc
            java.lang.Object[] r1 = r9.zzb
            int r2 = r9.zzd
            r3 = 0
            if (r10 != 0) goto Lc
        L9:
            r10 = r3
            goto L9c
        Lc:
            r4 = 1
            if (r2 != r4) goto L22
            r0 = 0
            r0 = r1[r0]
            r0.getClass()
            boolean r10 = r0.equals(r10)
            if (r10 == 0) goto L9
            r10 = r1[r4]
            r10.getClass()
            goto L9c
        L22:
            if (r0 != 0) goto L25
            goto L9
        L25:
            boolean r2 = r0 instanceof byte[]
            r5 = -1
            if (r2 == 0) goto L51
            r2 = r0
            byte[] r2 = (byte[]) r2
            int r0 = r2.length
            int r6 = r0 + (-1)
            int r0 = r10.hashCode()
            int r0 = com.google.android.gms.internal.play_billing.zzq.zza(r0)
        L38:
            r0 = r0 & r6
            r5 = r2[r0]
            r7 = 255(0xff, float:3.57E-43)
            r5 = r5 & r7
            if (r5 != r7) goto L41
            goto L9
        L41:
            r7 = r1[r5]
            boolean r7 = r10.equals(r7)
            if (r7 == 0) goto L4e
            r10 = r5 ^ 1
            r10 = r1[r10]
            goto L9c
        L4e:
            int r0 = r0 + 1
            goto L38
        L51:
            boolean r2 = r0 instanceof short[]
            if (r2 == 0) goto L7d
            r2 = r0
            short[] r2 = (short[]) r2
            int r0 = r2.length
            int r6 = r0 + (-1)
            int r0 = r10.hashCode()
            int r0 = com.google.android.gms.internal.play_billing.zzq.zza(r0)
        L63:
            r0 = r0 & r6
            short r5 = r2[r0]
            char r5 = (char) r5
            r7 = 65535(0xffff, float:9.1834E-41)
            if (r5 != r7) goto L6d
            goto L9
        L6d:
            r7 = r1[r5]
            boolean r7 = r10.equals(r7)
            if (r7 == 0) goto L7a
            r10 = r5 ^ 1
            r10 = r1[r10]
            goto L9c
        L7a:
            int r0 = r0 + 1
            goto L63
        L7d:
            int[] r0 = (int[]) r0
            int r2 = r0.length
            int r2 = r2 + r5
            int r6 = r10.hashCode()
            int r6 = com.google.android.gms.internal.play_billing.zzq.zza(r6)
        L89:
            r6 = r6 & r2
            r7 = r0[r6]
            if (r7 != r5) goto L90
            goto L9
        L90:
            r8 = r1[r7]
            boolean r8 = r10.equals(r8)
            if (r8 == 0) goto La0
            r10 = r7 ^ 1
            r10 = r1[r10]
        L9c:
            if (r10 != 0) goto L9f
            return r3
        L9f:
            return r10
        La0:
            int r6 = r6 + 1
            goto L89
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.play_billing.zzaf.get(java.lang.Object):java.lang.Object");
    }

    @Override // java.util.Map
    public final int size() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.play_billing.zzx
    final zzr zza() {
        return new zzae(this.zzb, 1, this.zzd);
    }

    @Override // com.google.android.gms.internal.play_billing.zzx
    final zzy zzc() {
        return new zzac(this, this.zzb, 0, this.zzd);
    }

    @Override // com.google.android.gms.internal.play_billing.zzx
    final zzy zzd() {
        return new zzad(this, new zzae(this.zzb, 0, this.zzd));
    }
}
