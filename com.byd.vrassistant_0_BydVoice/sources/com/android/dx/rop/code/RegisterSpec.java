package com.android.dx.rop.code;

import com.android.dx.rop.type.Type;
import com.android.dx.rop.type.TypeBearer;
import com.android.dx.util.ToHuman;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class RegisterSpec implements TypeBearer, ToHuman, Comparable<RegisterSpec> {
    public static final String PREFIX = "v";
    private final LocalItem local;
    private final int reg;
    private final TypeBearer type;
    private static final HashMap<Object, RegisterSpec> theInterns = new HashMap<>(1000);
    private static final ForComparison theInterningItem = new ForComparison();

    @Override // com.android.dx.rop.type.TypeBearer
    public final boolean isConstant() {
        return false;
    }

    private static RegisterSpec intern(int i, TypeBearer typeBearer, LocalItem localItem) {
        HashMap<Object, RegisterSpec> map = theInterns;
        synchronized (map) {
            ForComparison forComparison = theInterningItem;
            forComparison.set(i, typeBearer, localItem);
            RegisterSpec registerSpec = map.get(forComparison);
            if (registerSpec != null) {
                return registerSpec;
            }
            RegisterSpec registerSpec2 = forComparison.toRegisterSpec();
            map.put(registerSpec2, registerSpec2);
            return registerSpec2;
        }
    }

    public static RegisterSpec make(int i, TypeBearer typeBearer) {
        return intern(i, typeBearer, null);
    }

    public static RegisterSpec make(int i, TypeBearer typeBearer, LocalItem localItem) {
        if (localItem == null) {
            throw new NullPointerException("local  == null");
        }
        return intern(i, typeBearer, localItem);
    }

    public static RegisterSpec makeLocalOptional(int i, TypeBearer typeBearer, LocalItem localItem) {
        return intern(i, typeBearer, localItem);
    }

    public static String regString(int i) {
        return PREFIX + i;
    }

    private RegisterSpec(int i, TypeBearer typeBearer, LocalItem localItem) {
        if (i < 0) {
            throw new IllegalArgumentException("reg < 0");
        }
        if (typeBearer == null) {
            throw new NullPointerException("type == null");
        }
        this.reg = i;
        this.type = typeBearer;
        this.local = localItem;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof RegisterSpec)) {
            if (!(obj instanceof ForComparison)) {
                return false;
            }
            ForComparison forComparison = (ForComparison) obj;
            return equals(forComparison.reg, forComparison.type, forComparison.local);
        }
        RegisterSpec registerSpec = (RegisterSpec) obj;
        return equals(registerSpec.reg, registerSpec.type, registerSpec.local);
    }

    public boolean equalsUsingSimpleType(RegisterSpec registerSpec) {
        return matchesVariable(registerSpec) && this.reg == registerSpec.reg;
    }

    public boolean matchesVariable(RegisterSpec registerSpec) {
        if (registerSpec == null || !this.type.getType().equals(registerSpec.type.getType())) {
            return false;
        }
        LocalItem localItem = this.local;
        LocalItem localItem2 = registerSpec.local;
        return localItem == localItem2 || (localItem != null && localItem.equals(localItem2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean equals(int i, TypeBearer typeBearer, LocalItem localItem) {
        LocalItem localItem2;
        return this.reg == i && this.type.equals(typeBearer) && ((localItem2 = this.local) == localItem || (localItem2 != null && localItem2.equals(localItem)));
    }

    @Override // java.lang.Comparable
    public int compareTo(RegisterSpec registerSpec) {
        int i = this.reg;
        int i2 = registerSpec.reg;
        if (i < i2) {
            return -1;
        }
        if (i > i2) {
            return 1;
        }
        int iCompareTo = this.type.getType().compareTo(registerSpec.type.getType());
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        LocalItem localItem = this.local;
        if (localItem == null) {
            return registerSpec.local == null ? 0 : -1;
        }
        LocalItem localItem2 = registerSpec.local;
        if (localItem2 == null) {
            return 1;
        }
        return localItem.compareTo(localItem2);
    }

    public int hashCode() {
        return hashCodeOf(this.reg, this.type, this.local);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int hashCodeOf(int i, TypeBearer typeBearer, LocalItem localItem) {
        return ((((localItem != null ? localItem.hashCode() : 0) * 31) + typeBearer.hashCode()) * 31) + i;
    }

    public String toString() {
        return toString0(false);
    }

    @Override // com.android.dx.util.ToHuman
    public String toHuman() {
        return toString0(true);
    }

    @Override // com.android.dx.rop.type.TypeBearer
    public Type getType() {
        return this.type.getType();
    }

    @Override // com.android.dx.rop.type.TypeBearer
    public TypeBearer getFrameType() {
        return this.type.getFrameType();
    }

    @Override // com.android.dx.rop.type.TypeBearer
    public final int getBasicType() {
        return this.type.getBasicType();
    }

    @Override // com.android.dx.rop.type.TypeBearer
    public final int getBasicFrameType() {
        return this.type.getBasicFrameType();
    }

    public int getReg() {
        return this.reg;
    }

    public TypeBearer getTypeBearer() {
        return this.type;
    }

    public LocalItem getLocalItem() {
        return this.local;
    }

    public int getNextReg() {
        return this.reg + getCategory();
    }

    public int getCategory() {
        return this.type.getType().getCategory();
    }

    public boolean isCategory1() {
        return this.type.getType().isCategory1();
    }

    public boolean isCategory2() {
        return this.type.getType().isCategory2();
    }

    public String regString() {
        return regString(this.reg);
    }

    public RegisterSpec intersect(RegisterSpec registerSpec, boolean z) {
        TypeBearer type;
        if (this == registerSpec) {
            return this;
        }
        if (registerSpec == null || this.reg != registerSpec.getReg()) {
            return null;
        }
        LocalItem localItem = this.local;
        LocalItem localItem2 = (localItem == null || !localItem.equals(registerSpec.getLocalItem())) ? null : this.local;
        boolean z2 = localItem2 == this.local;
        if ((z && !z2) || (type = getType()) != registerSpec.getType()) {
            return null;
        }
        if (this.type.equals(registerSpec.getTypeBearer())) {
            type = this.type;
        }
        if (type == this.type && z2) {
            return this;
        }
        int i = this.reg;
        return localItem2 == null ? make(i, type) : make(i, type, localItem2);
    }

    public RegisterSpec withReg(int i) {
        return this.reg == i ? this : makeLocalOptional(i, this.type, this.local);
    }

    public RegisterSpec withType(TypeBearer typeBearer) {
        return makeLocalOptional(this.reg, typeBearer, this.local);
    }

    public RegisterSpec withOffset(int i) {
        return i == 0 ? this : withReg(this.reg + i);
    }

    public RegisterSpec withSimpleType() {
        Type type;
        TypeBearer typeBearer = this.type;
        if (typeBearer instanceof Type) {
            type = (Type) typeBearer;
        } else {
            type = typeBearer.getType();
        }
        if (type.isUninitialized()) {
            type = type.getInitializedType();
        }
        return type == typeBearer ? this : makeLocalOptional(this.reg, type, this.local);
    }

    public RegisterSpec withLocalItem(LocalItem localItem) {
        LocalItem localItem2 = this.local;
        return (localItem2 == localItem || (localItem2 != null && localItem2.equals(localItem))) ? this : makeLocalOptional(this.reg, this.type, localItem);
    }

    public boolean isEvenRegister() {
        return (getReg() & 1) == 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0052  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String toString0(boolean r4) {
        /*
            r3 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r1 = 40
            r0.<init>(r1)
            java.lang.String r1 = r3.regString()
            r0.append(r1)
            java.lang.String r1 = ":"
            r0.append(r1)
            com.android.dx.rop.code.LocalItem r1 = r3.local
            if (r1 == 0) goto L1e
            java.lang.String r1 = r1.toString()
            r0.append(r1)
        L1e:
            com.android.dx.rop.type.TypeBearer r1 = r3.type
            com.android.dx.rop.type.Type r1 = r1.getType()
            r0.append(r1)
            com.android.dx.rop.type.TypeBearer r2 = r3.type
            if (r1 == r2) goto L57
            java.lang.String r1 = "="
            r0.append(r1)
            if (r4 == 0) goto L42
            com.android.dx.rop.type.TypeBearer r1 = r3.type
            boolean r2 = r1 instanceof com.android.dx.rop.cst.CstString
            if (r2 == 0) goto L42
            com.android.dx.rop.cst.CstString r1 = (com.android.dx.rop.cst.CstString) r1
            java.lang.String r4 = r1.toQuoted()
            r0.append(r4)
            goto L57
        L42:
            if (r4 == 0) goto L52
            com.android.dx.rop.type.TypeBearer r4 = r3.type
            boolean r1 = r4 instanceof com.android.dx.rop.cst.Constant
            if (r1 == 0) goto L52
            java.lang.String r4 = r4.toHuman()
            r0.append(r4)
            goto L57
        L52:
            com.android.dx.rop.type.TypeBearer r4 = r3.type
            r0.append(r4)
        L57:
            java.lang.String r4 = r0.toString()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dx.rop.code.RegisterSpec.toString0(boolean):java.lang.String");
    }

    private static class ForComparison {
        private LocalItem local;
        private int reg;
        private TypeBearer type;

        private ForComparison() {
        }

        public void set(int i, TypeBearer typeBearer, LocalItem localItem) {
            this.reg = i;
            this.type = typeBearer;
            this.local = localItem;
        }

        public RegisterSpec toRegisterSpec() {
            return new RegisterSpec(this.reg, this.type, this.local);
        }

        public boolean equals(Object obj) {
            if (obj instanceof RegisterSpec) {
                return ((RegisterSpec) obj).equals(this.reg, this.type, this.local);
            }
            return false;
        }

        public int hashCode() {
            return RegisterSpec.hashCodeOf(this.reg, this.type, this.local);
        }
    }
}
