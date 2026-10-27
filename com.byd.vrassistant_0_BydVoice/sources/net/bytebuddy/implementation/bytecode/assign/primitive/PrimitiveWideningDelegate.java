package net.bytebuddy.implementation.bytecode.assign.primitive;

import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.jar.asm.MethodVisitor;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'BYTE' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: classes3.dex */
public final class PrimitiveWideningDelegate {
    private static final /* synthetic */ PrimitiveWideningDelegate[] $VALUES;
    public static final PrimitiveWideningDelegate BOOLEAN;
    public static final PrimitiveWideningDelegate BYTE;
    public static final PrimitiveWideningDelegate CHARACTER;
    public static final PrimitiveWideningDelegate DOUBLE;
    public static final PrimitiveWideningDelegate FLOAT;
    public static final PrimitiveWideningDelegate INTEGER;
    public static final PrimitiveWideningDelegate LONG;
    public static final PrimitiveWideningDelegate SHORT;
    private final StackManipulation toBooleanStackManipulation;
    private final StackManipulation toByteStackManipulation;
    private final StackManipulation toCharacterStackManipulation;
    private final StackManipulation toDoubleStackManipulation;
    private final StackManipulation toFloatStackManipulation;
    private final StackManipulation toIntegerStackManipulation;
    private final StackManipulation toLongStackManipulation;
    private final StackManipulation toShortStackManipulation;

    public static PrimitiveWideningDelegate valueOf(String str) {
        return (PrimitiveWideningDelegate) Enum.valueOf(PrimitiveWideningDelegate.class, str);
    }

    public static PrimitiveWideningDelegate[] values() {
        return (PrimitiveWideningDelegate[]) $VALUES.clone();
    }

    static {
        PrimitiveWideningDelegate primitiveWideningDelegate = new PrimitiveWideningDelegate("BOOLEAN", 0, StackManipulation.Trivial.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE);
        BOOLEAN = primitiveWideningDelegate;
        int i = 133;
        int i2 = 134;
        PrimitiveWideningDelegate primitiveWideningDelegate2 = new PrimitiveWideningDelegate("BYTE", 1, StackManipulation.Illegal.INSTANCE, StackManipulation.Trivial.INSTANCE, StackManipulation.Trivial.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Trivial.INSTANCE, new StackManipulation(i, StackSize.SINGLE.toIncreasingSize()) { // from class: net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveWideningDelegate.WideningStackManipulation
            private final int conversionOpcode;
            private final StackManipulation.Size size;

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public boolean isValid() {
                return true;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof WideningStackManipulation;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof WideningStackManipulation)) {
                    return false;
                }
                WideningStackManipulation wideningStackManipulation = (WideningStackManipulation) obj;
                if (!wideningStackManipulation.canEqual(this) || this.conversionOpcode != wideningStackManipulation.conversionOpcode) {
                    return false;
                }
                StackManipulation.Size size = this.size;
                StackManipulation.Size size2 = wideningStackManipulation.size;
                return size != null ? size.equals(size2) : size2 == null;
            }

            public int hashCode() {
                int i3 = this.conversionOpcode + 59;
                StackManipulation.Size size = this.size;
                return (i3 * 59) + (size == null ? 43 : size.hashCode());
            }

            {
                this.conversionOpcode = i;
                this.size = size;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitInsn(this.conversionOpcode);
                return this.size;
            }
        }, new StackManipulation(i2, StackSize.ZERO.toIncreasingSize()) { // from class: net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveWideningDelegate.WideningStackManipulation
            private final int conversionOpcode;
            private final StackManipulation.Size size;

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public boolean isValid() {
                return true;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof WideningStackManipulation;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof WideningStackManipulation)) {
                    return false;
                }
                WideningStackManipulation wideningStackManipulation = (WideningStackManipulation) obj;
                if (!wideningStackManipulation.canEqual(this) || this.conversionOpcode != wideningStackManipulation.conversionOpcode) {
                    return false;
                }
                StackManipulation.Size size = this.size;
                StackManipulation.Size size2 = wideningStackManipulation.size;
                return size != null ? size.equals(size2) : size2 == null;
            }

            public int hashCode() {
                int i3 = this.conversionOpcode + 59;
                StackManipulation.Size size = this.size;
                return (i3 * 59) + (size == null ? 43 : size.hashCode());
            }

            {
                this.conversionOpcode = i2;
                this.size = size;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitInsn(this.conversionOpcode);
                return this.size;
            }
        }, new StackManipulation(i, StackSize.SINGLE.toIncreasingSize()) { // from class: net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveWideningDelegate.WideningStackManipulation
            private final int conversionOpcode;
            private final StackManipulation.Size size;

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public boolean isValid() {
                return true;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof WideningStackManipulation;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof WideningStackManipulation)) {
                    return false;
                }
                WideningStackManipulation wideningStackManipulation = (WideningStackManipulation) obj;
                if (!wideningStackManipulation.canEqual(this) || this.conversionOpcode != wideningStackManipulation.conversionOpcode) {
                    return false;
                }
                StackManipulation.Size size = this.size;
                StackManipulation.Size size2 = wideningStackManipulation.size;
                return size != null ? size.equals(size2) : size2 == null;
            }

            public int hashCode() {
                int i3 = this.conversionOpcode + 59;
                StackManipulation.Size size = this.size;
                return (i3 * 59) + (size == null ? 43 : size.hashCode());
            }

            {
                this.conversionOpcode = i;
                this.size = size;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitInsn(this.conversionOpcode);
                return this.size;
            }
        });
        BYTE = primitiveWideningDelegate2;
        int i3 = 135;
        PrimitiveWideningDelegate primitiveWideningDelegate3 = new PrimitiveWideningDelegate("SHORT", 2, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Trivial.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Trivial.INSTANCE, new StackManipulation(i, StackSize.SINGLE.toIncreasingSize()) { // from class: net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveWideningDelegate.WideningStackManipulation
            private final int conversionOpcode;
            private final StackManipulation.Size size;

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public boolean isValid() {
                return true;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof WideningStackManipulation;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof WideningStackManipulation)) {
                    return false;
                }
                WideningStackManipulation wideningStackManipulation = (WideningStackManipulation) obj;
                if (!wideningStackManipulation.canEqual(this) || this.conversionOpcode != wideningStackManipulation.conversionOpcode) {
                    return false;
                }
                StackManipulation.Size size = this.size;
                StackManipulation.Size size2 = wideningStackManipulation.size;
                return size != null ? size.equals(size2) : size2 == null;
            }

            public int hashCode() {
                int i32 = this.conversionOpcode + 59;
                StackManipulation.Size size = this.size;
                return (i32 * 59) + (size == null ? 43 : size.hashCode());
            }

            {
                this.conversionOpcode = i;
                this.size = size;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitInsn(this.conversionOpcode);
                return this.size;
            }
        }, new StackManipulation(i2, StackSize.ZERO.toIncreasingSize()) { // from class: net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveWideningDelegate.WideningStackManipulation
            private final int conversionOpcode;
            private final StackManipulation.Size size;

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public boolean isValid() {
                return true;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof WideningStackManipulation;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof WideningStackManipulation)) {
                    return false;
                }
                WideningStackManipulation wideningStackManipulation = (WideningStackManipulation) obj;
                if (!wideningStackManipulation.canEqual(this) || this.conversionOpcode != wideningStackManipulation.conversionOpcode) {
                    return false;
                }
                StackManipulation.Size size = this.size;
                StackManipulation.Size size2 = wideningStackManipulation.size;
                return size != null ? size.equals(size2) : size2 == null;
            }

            public int hashCode() {
                int i32 = this.conversionOpcode + 59;
                StackManipulation.Size size = this.size;
                return (i32 * 59) + (size == null ? 43 : size.hashCode());
            }

            {
                this.conversionOpcode = i2;
                this.size = size;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitInsn(this.conversionOpcode);
                return this.size;
            }
        }, new StackManipulation(i3, StackSize.SINGLE.toIncreasingSize()) { // from class: net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveWideningDelegate.WideningStackManipulation
            private final int conversionOpcode;
            private final StackManipulation.Size size;

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public boolean isValid() {
                return true;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof WideningStackManipulation;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof WideningStackManipulation)) {
                    return false;
                }
                WideningStackManipulation wideningStackManipulation = (WideningStackManipulation) obj;
                if (!wideningStackManipulation.canEqual(this) || this.conversionOpcode != wideningStackManipulation.conversionOpcode) {
                    return false;
                }
                StackManipulation.Size size = this.size;
                StackManipulation.Size size2 = wideningStackManipulation.size;
                return size != null ? size.equals(size2) : size2 == null;
            }

            public int hashCode() {
                int i32 = this.conversionOpcode + 59;
                StackManipulation.Size size = this.size;
                return (i32 * 59) + (size == null ? 43 : size.hashCode());
            }

            {
                this.conversionOpcode = i3;
                this.size = size;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitInsn(this.conversionOpcode);
                return this.size;
            }
        });
        SHORT = primitiveWideningDelegate3;
        PrimitiveWideningDelegate primitiveWideningDelegate4 = new PrimitiveWideningDelegate("CHARACTER", 3, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Trivial.INSTANCE, StackManipulation.Trivial.INSTANCE, new StackManipulation(i, StackSize.SINGLE.toIncreasingSize()) { // from class: net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveWideningDelegate.WideningStackManipulation
            private final int conversionOpcode;
            private final StackManipulation.Size size;

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public boolean isValid() {
                return true;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof WideningStackManipulation;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof WideningStackManipulation)) {
                    return false;
                }
                WideningStackManipulation wideningStackManipulation = (WideningStackManipulation) obj;
                if (!wideningStackManipulation.canEqual(this) || this.conversionOpcode != wideningStackManipulation.conversionOpcode) {
                    return false;
                }
                StackManipulation.Size size = this.size;
                StackManipulation.Size size2 = wideningStackManipulation.size;
                return size != null ? size.equals(size2) : size2 == null;
            }

            public int hashCode() {
                int i32 = this.conversionOpcode + 59;
                StackManipulation.Size size = this.size;
                return (i32 * 59) + (size == null ? 43 : size.hashCode());
            }

            {
                this.conversionOpcode = i;
                this.size = size;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitInsn(this.conversionOpcode);
                return this.size;
            }
        }, new StackManipulation(i2, StackSize.ZERO.toIncreasingSize()) { // from class: net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveWideningDelegate.WideningStackManipulation
            private final int conversionOpcode;
            private final StackManipulation.Size size;

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public boolean isValid() {
                return true;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof WideningStackManipulation;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof WideningStackManipulation)) {
                    return false;
                }
                WideningStackManipulation wideningStackManipulation = (WideningStackManipulation) obj;
                if (!wideningStackManipulation.canEqual(this) || this.conversionOpcode != wideningStackManipulation.conversionOpcode) {
                    return false;
                }
                StackManipulation.Size size = this.size;
                StackManipulation.Size size2 = wideningStackManipulation.size;
                return size != null ? size.equals(size2) : size2 == null;
            }

            public int hashCode() {
                int i32 = this.conversionOpcode + 59;
                StackManipulation.Size size = this.size;
                return (i32 * 59) + (size == null ? 43 : size.hashCode());
            }

            {
                this.conversionOpcode = i2;
                this.size = size;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitInsn(this.conversionOpcode);
                return this.size;
            }
        }, new StackManipulation(i3, StackSize.SINGLE.toIncreasingSize()) { // from class: net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveWideningDelegate.WideningStackManipulation
            private final int conversionOpcode;
            private final StackManipulation.Size size;

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public boolean isValid() {
                return true;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof WideningStackManipulation;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof WideningStackManipulation)) {
                    return false;
                }
                WideningStackManipulation wideningStackManipulation = (WideningStackManipulation) obj;
                if (!wideningStackManipulation.canEqual(this) || this.conversionOpcode != wideningStackManipulation.conversionOpcode) {
                    return false;
                }
                StackManipulation.Size size = this.size;
                StackManipulation.Size size2 = wideningStackManipulation.size;
                return size != null ? size.equals(size2) : size2 == null;
            }

            public int hashCode() {
                int i32 = this.conversionOpcode + 59;
                StackManipulation.Size size = this.size;
                return (i32 * 59) + (size == null ? 43 : size.hashCode());
            }

            {
                this.conversionOpcode = i3;
                this.size = size;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitInsn(this.conversionOpcode);
                return this.size;
            }
        });
        CHARACTER = primitiveWideningDelegate4;
        PrimitiveWideningDelegate primitiveWideningDelegate5 = new PrimitiveWideningDelegate("INTEGER", 4, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Trivial.INSTANCE, new StackManipulation(i, StackSize.SINGLE.toIncreasingSize()) { // from class: net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveWideningDelegate.WideningStackManipulation
            private final int conversionOpcode;
            private final StackManipulation.Size size;

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public boolean isValid() {
                return true;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof WideningStackManipulation;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof WideningStackManipulation)) {
                    return false;
                }
                WideningStackManipulation wideningStackManipulation = (WideningStackManipulation) obj;
                if (!wideningStackManipulation.canEqual(this) || this.conversionOpcode != wideningStackManipulation.conversionOpcode) {
                    return false;
                }
                StackManipulation.Size size = this.size;
                StackManipulation.Size size2 = wideningStackManipulation.size;
                return size != null ? size.equals(size2) : size2 == null;
            }

            public int hashCode() {
                int i32 = this.conversionOpcode + 59;
                StackManipulation.Size size = this.size;
                return (i32 * 59) + (size == null ? 43 : size.hashCode());
            }

            {
                this.conversionOpcode = i;
                this.size = size;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitInsn(this.conversionOpcode);
                return this.size;
            }
        }, new StackManipulation(i2, StackSize.ZERO.toIncreasingSize()) { // from class: net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveWideningDelegate.WideningStackManipulation
            private final int conversionOpcode;
            private final StackManipulation.Size size;

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public boolean isValid() {
                return true;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof WideningStackManipulation;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof WideningStackManipulation)) {
                    return false;
                }
                WideningStackManipulation wideningStackManipulation = (WideningStackManipulation) obj;
                if (!wideningStackManipulation.canEqual(this) || this.conversionOpcode != wideningStackManipulation.conversionOpcode) {
                    return false;
                }
                StackManipulation.Size size = this.size;
                StackManipulation.Size size2 = wideningStackManipulation.size;
                return size != null ? size.equals(size2) : size2 == null;
            }

            public int hashCode() {
                int i32 = this.conversionOpcode + 59;
                StackManipulation.Size size = this.size;
                return (i32 * 59) + (size == null ? 43 : size.hashCode());
            }

            {
                this.conversionOpcode = i2;
                this.size = size;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitInsn(this.conversionOpcode);
                return this.size;
            }
        }, new StackManipulation(i3, StackSize.SINGLE.toIncreasingSize()) { // from class: net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveWideningDelegate.WideningStackManipulation
            private final int conversionOpcode;
            private final StackManipulation.Size size;

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public boolean isValid() {
                return true;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof WideningStackManipulation;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof WideningStackManipulation)) {
                    return false;
                }
                WideningStackManipulation wideningStackManipulation = (WideningStackManipulation) obj;
                if (!wideningStackManipulation.canEqual(this) || this.conversionOpcode != wideningStackManipulation.conversionOpcode) {
                    return false;
                }
                StackManipulation.Size size = this.size;
                StackManipulation.Size size2 = wideningStackManipulation.size;
                return size != null ? size.equals(size2) : size2 == null;
            }

            public int hashCode() {
                int i32 = this.conversionOpcode + 59;
                StackManipulation.Size size = this.size;
                return (i32 * 59) + (size == null ? 43 : size.hashCode());
            }

            {
                this.conversionOpcode = i3;
                this.size = size;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitInsn(this.conversionOpcode);
                return this.size;
            }
        });
        INTEGER = primitiveWideningDelegate5;
        PrimitiveWideningDelegate primitiveWideningDelegate6 = new PrimitiveWideningDelegate("LONG", 5, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Trivial.INSTANCE, new StackManipulation(137, StackSize.SINGLE.toDecreasingSize()) { // from class: net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveWideningDelegate.WideningStackManipulation
            private final int conversionOpcode;
            private final StackManipulation.Size size;

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public boolean isValid() {
                return true;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof WideningStackManipulation;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof WideningStackManipulation)) {
                    return false;
                }
                WideningStackManipulation wideningStackManipulation = (WideningStackManipulation) obj;
                if (!wideningStackManipulation.canEqual(this) || this.conversionOpcode != wideningStackManipulation.conversionOpcode) {
                    return false;
                }
                StackManipulation.Size size = this.size;
                StackManipulation.Size size2 = wideningStackManipulation.size;
                return size != null ? size.equals(size2) : size2 == null;
            }

            public int hashCode() {
                int i32 = this.conversionOpcode + 59;
                StackManipulation.Size size = this.size;
                return (i32 * 59) + (size == null ? 43 : size.hashCode());
            }

            {
                this.conversionOpcode = i3;
                this.size = size;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitInsn(this.conversionOpcode);
                return this.size;
            }
        }, new StackManipulation(138, StackSize.ZERO.toIncreasingSize()) { // from class: net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveWideningDelegate.WideningStackManipulation
            private final int conversionOpcode;
            private final StackManipulation.Size size;

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public boolean isValid() {
                return true;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof WideningStackManipulation;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof WideningStackManipulation)) {
                    return false;
                }
                WideningStackManipulation wideningStackManipulation = (WideningStackManipulation) obj;
                if (!wideningStackManipulation.canEqual(this) || this.conversionOpcode != wideningStackManipulation.conversionOpcode) {
                    return false;
                }
                StackManipulation.Size size = this.size;
                StackManipulation.Size size2 = wideningStackManipulation.size;
                return size != null ? size.equals(size2) : size2 == null;
            }

            public int hashCode() {
                int i32 = this.conversionOpcode + 59;
                StackManipulation.Size size = this.size;
                return (i32 * 59) + (size == null ? 43 : size.hashCode());
            }

            {
                this.conversionOpcode = i3;
                this.size = size;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitInsn(this.conversionOpcode);
                return this.size;
            }
        });
        LONG = primitiveWideningDelegate6;
        PrimitiveWideningDelegate primitiveWideningDelegate7 = new PrimitiveWideningDelegate("FLOAT", 6, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Trivial.INSTANCE, new StackManipulation(141, StackSize.SINGLE.toIncreasingSize()) { // from class: net.bytebuddy.implementation.bytecode.assign.primitive.PrimitiveWideningDelegate.WideningStackManipulation
            private final int conversionOpcode;
            private final StackManipulation.Size size;

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public boolean isValid() {
                return true;
            }

            protected boolean canEqual(Object obj) {
                return obj instanceof WideningStackManipulation;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof WideningStackManipulation)) {
                    return false;
                }
                WideningStackManipulation wideningStackManipulation = (WideningStackManipulation) obj;
                if (!wideningStackManipulation.canEqual(this) || this.conversionOpcode != wideningStackManipulation.conversionOpcode) {
                    return false;
                }
                StackManipulation.Size size = this.size;
                StackManipulation.Size size2 = wideningStackManipulation.size;
                return size != null ? size.equals(size2) : size2 == null;
            }

            public int hashCode() {
                int i32 = this.conversionOpcode + 59;
                StackManipulation.Size size = this.size;
                return (i32 * 59) + (size == null ? 43 : size.hashCode());
            }

            {
                this.conversionOpcode = i3;
                this.size = size;
            }

            @Override // net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                methodVisitor.visitInsn(this.conversionOpcode);
                return this.size;
            }
        });
        FLOAT = primitiveWideningDelegate7;
        PrimitiveWideningDelegate primitiveWideningDelegate8 = new PrimitiveWideningDelegate("DOUBLE", 7, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Illegal.INSTANCE, StackManipulation.Trivial.INSTANCE);
        DOUBLE = primitiveWideningDelegate8;
        $VALUES = new PrimitiveWideningDelegate[]{primitiveWideningDelegate, primitiveWideningDelegate2, primitiveWideningDelegate3, primitiveWideningDelegate4, primitiveWideningDelegate5, primitiveWideningDelegate6, primitiveWideningDelegate7, primitiveWideningDelegate8};
    }

    private PrimitiveWideningDelegate(String str, int i, StackManipulation stackManipulation, StackManipulation stackManipulation2, StackManipulation stackManipulation3, StackManipulation stackManipulation4, StackManipulation stackManipulation5, StackManipulation stackManipulation6, StackManipulation stackManipulation7, StackManipulation stackManipulation8) {
        this.toBooleanStackManipulation = stackManipulation;
        this.toByteStackManipulation = stackManipulation2;
        this.toShortStackManipulation = stackManipulation3;
        this.toCharacterStackManipulation = stackManipulation4;
        this.toIntegerStackManipulation = stackManipulation5;
        this.toLongStackManipulation = stackManipulation6;
        this.toFloatStackManipulation = stackManipulation7;
        this.toDoubleStackManipulation = stackManipulation8;
    }

    public static PrimitiveWideningDelegate forPrimitive(TypeDefinition typeDefinition) {
        if (typeDefinition.represents(Boolean.TYPE)) {
            return BOOLEAN;
        }
        if (typeDefinition.represents(Byte.TYPE)) {
            return BYTE;
        }
        if (typeDefinition.represents(Short.TYPE)) {
            return SHORT;
        }
        if (typeDefinition.represents(Character.TYPE)) {
            return CHARACTER;
        }
        if (typeDefinition.represents(Integer.TYPE)) {
            return INTEGER;
        }
        if (typeDefinition.represents(Long.TYPE)) {
            return LONG;
        }
        if (typeDefinition.represents(Float.TYPE)) {
            return FLOAT;
        }
        if (typeDefinition.represents(Double.TYPE)) {
            return DOUBLE;
        }
        throw new IllegalArgumentException("Not a primitive, non-void type: " + typeDefinition);
    }

    public StackManipulation widenTo(TypeDefinition typeDefinition) {
        if (typeDefinition.represents(Boolean.TYPE)) {
            return this.toBooleanStackManipulation;
        }
        if (typeDefinition.represents(Byte.TYPE)) {
            return this.toByteStackManipulation;
        }
        if (typeDefinition.represents(Short.TYPE)) {
            return this.toShortStackManipulation;
        }
        if (typeDefinition.represents(Character.TYPE)) {
            return this.toCharacterStackManipulation;
        }
        if (typeDefinition.represents(Integer.TYPE)) {
            return this.toIntegerStackManipulation;
        }
        if (typeDefinition.represents(Long.TYPE)) {
            return this.toLongStackManipulation;
        }
        if (typeDefinition.represents(Float.TYPE)) {
            return this.toFloatStackManipulation;
        }
        if (typeDefinition.represents(Double.TYPE)) {
            return this.toDoubleStackManipulation;
        }
        throw new IllegalArgumentException("Not a primitive non-void type: " + typeDefinition);
    }
}
