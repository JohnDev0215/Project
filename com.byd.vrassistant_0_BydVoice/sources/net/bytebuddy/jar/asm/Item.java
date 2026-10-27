package net.bytebuddy.jar.asm;

/* JADX INFO: loaded from: classes3.dex */
final class Item {
    int hashCode;
    int index;
    int intVal;
    long longVal;
    Item next;
    String strVal1;
    String strVal2;
    String strVal3;
    int type;

    Item() {
    }

    Item(int i) {
        this.index = i;
    }

    Item(int i, Item item) {
        this.index = i;
        this.type = item.type;
        this.intVal = item.intVal;
        this.longVal = item.longVal;
        this.strVal1 = item.strVal1;
        this.strVal2 = item.strVal2;
        this.strVal3 = item.strVal3;
        this.hashCode = item.hashCode;
    }

    void set(int i) {
        this.type = 3;
        this.intVal = i;
        this.hashCode = (3 + i) & Integer.MAX_VALUE;
    }

    void set(long j) {
        this.type = 5;
        this.longVal = j;
        this.hashCode = Integer.MAX_VALUE & (5 + ((int) j));
    }

    void set(float f) {
        this.type = 4;
        this.intVal = Float.floatToRawIntBits(f);
        this.hashCode = Integer.MAX_VALUE & (this.type + ((int) f));
    }

    void set(double d) {
        this.type = 6;
        this.longVal = Double.doubleToRawLongBits(d);
        this.hashCode = Integer.MAX_VALUE & (this.type + ((int) d));
    }

    void set(int i, String str, String str2, String str3) {
        this.type = i;
        this.strVal1 = str;
        this.strVal2 = str2;
        this.strVal3 = str3;
        if (i != 1) {
            if (i == 12) {
                this.hashCode = (i + (str.hashCode() * str2.hashCode())) & Integer.MAX_VALUE;
                return;
            }
            if (i != 16 && i != 30) {
                if (i == 7) {
                    this.intVal = 0;
                } else if (i != 8 && i != 19 && i != 20) {
                    this.hashCode = (i + (str.hashCode() * str2.hashCode() * str3.hashCode())) & Integer.MAX_VALUE;
                    return;
                }
            }
        }
        this.hashCode = (i + str.hashCode()) & Integer.MAX_VALUE;
    }

    void set(String str, String str2, int i) {
        this.type = 18;
        this.longVal = i;
        this.strVal1 = str;
        this.strVal2 = str2;
        this.hashCode = Integer.MAX_VALUE & ((i * str.hashCode() * this.strVal2.hashCode()) + 18);
    }

    void set(int i, int i2) {
        this.type = 33;
        this.intVal = i;
        this.hashCode = i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x006a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    boolean isEqualTo(net.bytebuddy.jar.asm.Item r9) {
        /*
            r8 = this;
            int r0 = r8.type
            r1 = 1
            if (r0 == r1) goto L95
            r2 = 12
            r3 = 0
            if (r0 == r2) goto L7e
            r2 = 16
            if (r0 == r2) goto L95
            switch(r0) {
                case 3: goto L75;
                case 4: goto L75;
                case 5: goto L6a;
                case 6: goto L6a;
                case 7: goto L95;
                case 8: goto L95;
                default: goto L11;
            }
        L11:
            switch(r0) {
                case 18: goto L4b;
                case 19: goto L95;
                case 20: goto L95;
                default: goto L14;
            }
        L14:
            switch(r0) {
                case 30: goto L95;
                case 31: goto L38;
                case 32: goto L6a;
                default: goto L17;
            }
        L17:
            java.lang.String r0 = r9.strVal1
            java.lang.String r2 = r8.strVal1
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L36
            java.lang.String r0 = r9.strVal2
            java.lang.String r2 = r8.strVal2
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L36
            java.lang.String r9 = r9.strVal3
            java.lang.String r0 = r8.strVal3
            boolean r9 = r9.equals(r0)
            if (r9 == 0) goto L36
            goto L37
        L36:
            r1 = r3
        L37:
            return r1
        L38:
            int r0 = r9.intVal
            int r2 = r8.intVal
            if (r0 != r2) goto L49
            java.lang.String r9 = r9.strVal1
            java.lang.String r0 = r8.strVal1
            boolean r9 = r9.equals(r0)
            if (r9 == 0) goto L49
            goto L4a
        L49:
            r1 = r3
        L4a:
            return r1
        L4b:
            long r4 = r9.longVal
            long r6 = r8.longVal
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 != 0) goto L68
            java.lang.String r0 = r9.strVal1
            java.lang.String r2 = r8.strVal1
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L68
            java.lang.String r9 = r9.strVal2
            java.lang.String r0 = r8.strVal2
            boolean r9 = r9.equals(r0)
            if (r9 == 0) goto L68
            goto L69
        L68:
            r1 = r3
        L69:
            return r1
        L6a:
            long r4 = r9.longVal
            long r6 = r8.longVal
            int r9 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r9 != 0) goto L73
            goto L74
        L73:
            r1 = r3
        L74:
            return r1
        L75:
            int r9 = r9.intVal
            int r0 = r8.intVal
            if (r9 != r0) goto L7c
            goto L7d
        L7c:
            r1 = r3
        L7d:
            return r1
        L7e:
            java.lang.String r0 = r9.strVal1
            java.lang.String r2 = r8.strVal1
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L93
            java.lang.String r9 = r9.strVal2
            java.lang.String r0 = r8.strVal2
            boolean r9 = r9.equals(r0)
            if (r9 == 0) goto L93
            goto L94
        L93:
            r1 = r3
        L94:
            return r1
        L95:
            java.lang.String r9 = r9.strVal1
            java.lang.String r0 = r8.strVal1
            boolean r9 = r9.equals(r0)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.jar.asm.Item.isEqualTo(net.bytebuddy.jar.asm.Item):boolean");
    }
}
