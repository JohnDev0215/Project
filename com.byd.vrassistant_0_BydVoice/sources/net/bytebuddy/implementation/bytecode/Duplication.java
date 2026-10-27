package net.bytebuddy.implementation.bytecode;

import net.bytebuddy.description.type.TypeDefinition;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.jar.asm.MethodVisitor;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'ZERO' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: classes3.dex */
public abstract class Duplication implements StackManipulation {
    private static final /* synthetic */ Duplication[] $VALUES;
    public static final Duplication DOUBLE;
    public static final Duplication SINGLE;
    public static final Duplication ZERO;
    private final int opcode;
    protected final StackManipulation.Size size;

    public abstract StackManipulation flipOver(TypeDefinition typeDefinition);

    @Override // net.bytebuddy.implementation.bytecode.StackManipulation
    public boolean isValid() {
        return true;
    }

    public static Duplication valueOf(String str) {
        return (Duplication) Enum.valueOf(Duplication.class, str);
    }

    public static Duplication[] values() {
        return (Duplication[]) $VALUES.clone();
    }

    static {
        int i = 0;
        Duplication duplication = new Duplication("ZERO", i, StackSize.ZERO, i) { // from class: net.bytebuddy.implementation.bytecode.Duplication.1
            @Override // net.bytebuddy.implementation.bytecode.Duplication, net.bytebuddy.implementation.bytecode.StackManipulation
            public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
                return this.size;
            }

            @Override // net.bytebuddy.implementation.bytecode.Duplication
            public StackManipulation flipOver(TypeDefinition typeDefinition) {
                throw new IllegalStateException("Cannot flip zero value");
            }
        };
        ZERO = duplication;
        Duplication duplication2 = new Duplication("SINGLE", 1, StackSize.SINGLE, 89) { // from class: net.bytebuddy.implementation.bytecode.Duplication.2
            @Override // net.bytebuddy.implementation.bytecode.Duplication
            public StackManipulation flipOver(TypeDefinition typeDefinition) {
                int i2 = AnonymousClass4.$SwitchMap$net$bytebuddy$implementation$bytecode$StackSize[typeDefinition.getStackSize().ordinal()];
                if (i2 == 1) {
                    return WithFlip.SINGLE_SINGLE;
                }
                if (i2 == 2) {
                    return WithFlip.SINGLE_DOUBLE;
                }
                throw new IllegalArgumentException("Cannot flip: " + typeDefinition);
            }
        };
        SINGLE = duplication2;
        Duplication duplication3 = new Duplication("DOUBLE", 2, StackSize.DOUBLE, 92) { // from class: net.bytebuddy.implementation.bytecode.Duplication.3
            @Override // net.bytebuddy.implementation.bytecode.Duplication
            public StackManipulation flipOver(TypeDefinition typeDefinition) {
                int i2 = AnonymousClass4.$SwitchMap$net$bytebuddy$implementation$bytecode$StackSize[typeDefinition.getStackSize().ordinal()];
                if (i2 == 1) {
                    return WithFlip.DOUBLE_SINGLE;
                }
                if (i2 == 2) {
                    return WithFlip.DOUBLE_DOUBLE;
                }
                throw new IllegalArgumentException("Cannot flip: " + typeDefinition);
            }
        };
        DOUBLE = duplication3;
        $VALUES = new Duplication[]{duplication, duplication2, duplication3};
    }

    /* JADX INFO: renamed from: net.bytebuddy.implementation.bytecode.Duplication$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$net$bytebuddy$implementation$bytecode$StackSize;

        static {
            int[] iArr = new int[StackSize.values().length];
            $SwitchMap$net$bytebuddy$implementation$bytecode$StackSize = iArr;
            try {
                iArr[StackSize.SINGLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$net$bytebuddy$implementation$bytecode$StackSize[StackSize.DOUBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$net$bytebuddy$implementation$bytecode$StackSize[StackSize.ZERO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private Duplication(String str, int i, StackSize stackSize, int i2) {
        this.size = stackSize.toIncreasingSize();
        this.opcode = i2;
    }

    public static Duplication of(TypeDefinition typeDefinition) {
        int i = AnonymousClass4.$SwitchMap$net$bytebuddy$implementation$bytecode$StackSize[typeDefinition.getStackSize().ordinal()];
        if (i == 1) {
            return SINGLE;
        }
        if (i == 2) {
            return DOUBLE;
        }
        if (i == 3) {
            return ZERO;
        }
        throw new AssertionError("Unexpected type: " + typeDefinition);
    }

    @Override // net.bytebuddy.implementation.bytecode.StackManipulation
    public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
        methodVisitor.visitInsn(this.opcode);
        return this.size;
    }

    protected enum WithFlip implements StackManipulation {
        SINGLE_SINGLE(90, StackSize.SINGLE),
        SINGLE_DOUBLE(91, StackSize.SINGLE),
        DOUBLE_SINGLE(93, StackSize.DOUBLE),
        DOUBLE_DOUBLE(94, StackSize.DOUBLE);

        private final int opcode;
        private final StackSize stackSize;

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public boolean isValid() {
            return true;
        }

        WithFlip(int i, StackSize stackSize) {
            this.opcode = i;
            this.stackSize = stackSize;
        }

        @Override // net.bytebuddy.implementation.bytecode.StackManipulation
        public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
            methodVisitor.visitInsn(this.opcode);
            return this.stackSize.toIncreasingSize();
        }
    }
}
