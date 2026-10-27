package kotlinx.metadata;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.metadata.internal.metadata.ProtoBuf$Class;
import kotlinx.metadata.internal.metadata.ProtoBuf$MemberKind;
import kotlinx.metadata.internal.metadata.ProtoBuf$Modality;
import kotlinx.metadata.internal.metadata.ProtoBuf$Visibility;
import kotlinx.metadata.internal.metadata.deserialization.Flags;

/* JADX INFO: compiled from: Flag.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u00122\u00020\u0001:\u0004\u0013\u0012\u0014\u0015B\u001d\b\u0010\u0012\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u000b\u0012\u0006\u0010\n\u001a\u00020\u0002¢\u0006\u0004\b\r\u0010\u000eB\u0011\b\u0010\u0012\u0006\u0010\f\u001a\u00020\u000f¢\u0006\u0004\b\r\u0010\u0010B\u001f\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\u0002¢\u0006\u0004\b\r\u0010\u0011J\u0015\u0010\u0006\u001a\u00020\u00052\n\u0010\u0004\u001a\u00060\u0002j\u0002`\u0003H\u0086\u0002R\u0014\u0010\u0007\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\bR\u0014\u0010\n\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\b¨\u0006\u0016"}, d2 = {"Lkotlinx/metadata/Flag;", "", "", "Lkotlinx/metadata/Flags;", "flags", "", "invoke", "offset", "I", "bitWidth", "value", "Lkotlinx/metadata/internal/metadata/deserialization/Flags$FlagField;", "field", "<init>", "(Lorg/jetbrains/kotlin/metadata/deserialization/Flags$FlagField;I)V", "Lkotlinx/metadata/internal/metadata/deserialization/Flags$BooleanFlagField;", "(Lorg/jetbrains/kotlin/metadata/deserialization/Flags$BooleanFlagField;)V", "(III)V", "Common", "Class", "Function", "Type", "kotlinx-metadata"}, k = 1, mv = {1, 7, 1})
public final class Flag {

    @JvmField
    public static final Flag HAS_ANNOTATIONS;

    @JvmField
    public static final Flag IS_ABSTRACT;

    @JvmField
    public static final Flag IS_FINAL;

    @JvmField
    public static final Flag IS_INTERNAL;

    @JvmField
    public static final Flag IS_LOCAL;

    @JvmField
    public static final Flag IS_OPEN;

    @JvmField
    public static final Flag IS_PRIVATE;

    @JvmField
    public static final Flag IS_PRIVATE_TO_THIS;

    @JvmField
    public static final Flag IS_PROTECTED;

    @JvmField
    public static final Flag IS_PUBLIC;

    @JvmField
    public static final Flag IS_SEALED;
    private final int bitWidth;
    private final int offset;
    private final int value;

    public Flag(int i, int i2, int i3) {
        this.offset = i;
        this.bitWidth = i2;
        this.value = i3;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Flag(Flags.FlagField<?> field, int i) {
        this(field.offset, field.bitWidth, i);
        Intrinsics.checkNotNullParameter(field, "field");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Flag(Flags.BooleanFlagField field) {
        this(field, 1);
        Intrinsics.checkNotNullParameter(field, "field");
    }

    public final boolean invoke(int flags) {
        return ((flags >>> this.offset) & ((1 << this.bitWidth) - 1)) == this.value;
    }

    static {
        Flags.BooleanFlagField HAS_ANNOTATIONS2 = Flags.HAS_ANNOTATIONS;
        Intrinsics.checkNotNullExpressionValue(HAS_ANNOTATIONS2, "HAS_ANNOTATIONS");
        HAS_ANNOTATIONS = new Flag(HAS_ANNOTATIONS2);
        Flags.FlagField<ProtoBuf$Visibility> VISIBILITY = Flags.VISIBILITY;
        Intrinsics.checkNotNullExpressionValue(VISIBILITY, "VISIBILITY");
        IS_INTERNAL = new Flag(VISIBILITY, 0);
        Intrinsics.checkNotNullExpressionValue(VISIBILITY, "VISIBILITY");
        IS_PRIVATE = new Flag(VISIBILITY, 1);
        Intrinsics.checkNotNullExpressionValue(VISIBILITY, "VISIBILITY");
        IS_PROTECTED = new Flag(VISIBILITY, 2);
        Intrinsics.checkNotNullExpressionValue(VISIBILITY, "VISIBILITY");
        IS_PUBLIC = new Flag(VISIBILITY, 3);
        Intrinsics.checkNotNullExpressionValue(VISIBILITY, "VISIBILITY");
        IS_PRIVATE_TO_THIS = new Flag(VISIBILITY, 4);
        Intrinsics.checkNotNullExpressionValue(VISIBILITY, "VISIBILITY");
        IS_LOCAL = new Flag(VISIBILITY, 5);
        Flags.FlagField<ProtoBuf$Modality> MODALITY = Flags.MODALITY;
        Intrinsics.checkNotNullExpressionValue(MODALITY, "MODALITY");
        IS_FINAL = new Flag(MODALITY, 0);
        Intrinsics.checkNotNullExpressionValue(MODALITY, "MODALITY");
        IS_OPEN = new Flag(MODALITY, 1);
        Intrinsics.checkNotNullExpressionValue(MODALITY, "MODALITY");
        IS_ABSTRACT = new Flag(MODALITY, 2);
        Intrinsics.checkNotNullExpressionValue(MODALITY, "MODALITY");
        IS_SEALED = new Flag(MODALITY, 3);
    }

    /* JADX INFO: compiled from: Flag.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u000e\u0010\u0002R\u0010\u0010\u000f\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lkotlinx/metadata/Flag$Class;", "", "()V", "IS_ANNOTATION_CLASS", "Lkotlinx/metadata/Flag;", "IS_CLASS", "IS_COMPANION_OBJECT", "IS_DATA", "IS_ENUM_CLASS", "IS_ENUM_ENTRY", "IS_EXPECT", "IS_EXTERNAL", "IS_FUN", "IS_INLINE", "getIS_INLINE$annotations", "IS_INNER", "IS_INTERFACE", "IS_OBJECT", "IS_VALUE", "kotlinx-metadata"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Class {
        public static final Class INSTANCE = new Class();

        @JvmField
        public static final Flag IS_ANNOTATION_CLASS;

        @JvmField
        public static final Flag IS_CLASS;

        @JvmField
        public static final Flag IS_COMPANION_OBJECT;

        @JvmField
        public static final Flag IS_DATA;

        @JvmField
        public static final Flag IS_ENUM_CLASS;

        @JvmField
        public static final Flag IS_ENUM_ENTRY;

        @JvmField
        public static final Flag IS_EXPECT;

        @JvmField
        public static final Flag IS_EXTERNAL;

        @JvmField
        public static final Flag IS_FUN;

        @JvmField
        public static final Flag IS_INLINE;

        @JvmField
        public static final Flag IS_INNER;

        @JvmField
        public static final Flag IS_INTERFACE;

        @JvmField
        public static final Flag IS_OBJECT;

        @JvmField
        public static final Flag IS_VALUE;

        private Class() {
        }

        static {
            Flags.FlagField<ProtoBuf$Class.Kind> CLASS_KIND = Flags.CLASS_KIND;
            Intrinsics.checkNotNullExpressionValue(CLASS_KIND, "CLASS_KIND");
            IS_CLASS = new Flag(CLASS_KIND, 0);
            Intrinsics.checkNotNullExpressionValue(CLASS_KIND, "CLASS_KIND");
            IS_INTERFACE = new Flag(CLASS_KIND, 1);
            Intrinsics.checkNotNullExpressionValue(CLASS_KIND, "CLASS_KIND");
            IS_ENUM_CLASS = new Flag(CLASS_KIND, 2);
            Intrinsics.checkNotNullExpressionValue(CLASS_KIND, "CLASS_KIND");
            IS_ENUM_ENTRY = new Flag(CLASS_KIND, 3);
            Intrinsics.checkNotNullExpressionValue(CLASS_KIND, "CLASS_KIND");
            IS_ANNOTATION_CLASS = new Flag(CLASS_KIND, 4);
            Intrinsics.checkNotNullExpressionValue(CLASS_KIND, "CLASS_KIND");
            IS_OBJECT = new Flag(CLASS_KIND, 5);
            Intrinsics.checkNotNullExpressionValue(CLASS_KIND, "CLASS_KIND");
            IS_COMPANION_OBJECT = new Flag(CLASS_KIND, 6);
            Flags.BooleanFlagField IS_INNER2 = Flags.IS_INNER;
            Intrinsics.checkNotNullExpressionValue(IS_INNER2, "IS_INNER");
            IS_INNER = new Flag(IS_INNER2);
            Flags.BooleanFlagField IS_DATA2 = Flags.IS_DATA;
            Intrinsics.checkNotNullExpressionValue(IS_DATA2, "IS_DATA");
            IS_DATA = new Flag(IS_DATA2);
            Flags.BooleanFlagField IS_EXTERNAL_CLASS = Flags.IS_EXTERNAL_CLASS;
            Intrinsics.checkNotNullExpressionValue(IS_EXTERNAL_CLASS, "IS_EXTERNAL_CLASS");
            IS_EXTERNAL = new Flag(IS_EXTERNAL_CLASS);
            Flags.BooleanFlagField IS_EXPECT_CLASS = Flags.IS_EXPECT_CLASS;
            Intrinsics.checkNotNullExpressionValue(IS_EXPECT_CLASS, "IS_EXPECT_CLASS");
            IS_EXPECT = new Flag(IS_EXPECT_CLASS);
            Flags.BooleanFlagField IS_VALUE_CLASS = Flags.IS_VALUE_CLASS;
            Intrinsics.checkNotNullExpressionValue(IS_VALUE_CLASS, "IS_VALUE_CLASS");
            IS_INLINE = new Flag(IS_VALUE_CLASS);
            Intrinsics.checkNotNullExpressionValue(IS_VALUE_CLASS, "IS_VALUE_CLASS");
            IS_VALUE = new Flag(IS_VALUE_CLASS);
            Flags.BooleanFlagField IS_FUN_INTERFACE = Flags.IS_FUN_INTERFACE;
            Intrinsics.checkNotNullExpressionValue(IS_FUN_INTERFACE, "IS_FUN_INTERFACE");
            IS_FUN = new Flag(IS_FUN_INTERFACE);
        }
    }

    /* JADX INFO: compiled from: Flag.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lkotlinx/metadata/Flag$Function;", "", "()V", "HAS_NON_STABLE_PARAMETER_NAMES", "Lkotlinx/metadata/Flag;", "IS_DECLARATION", "IS_DELEGATION", "IS_EXPECT", "IS_EXTERNAL", "IS_FAKE_OVERRIDE", "IS_INFIX", "IS_INLINE", "IS_OPERATOR", "IS_SUSPEND", "IS_SYNTHESIZED", "IS_TAILREC", "kotlinx-metadata"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Function {

        @JvmField
        public static final Flag HAS_NON_STABLE_PARAMETER_NAMES;
        public static final Function INSTANCE = new Function();

        @JvmField
        public static final Flag IS_DECLARATION;

        @JvmField
        public static final Flag IS_DELEGATION;

        @JvmField
        public static final Flag IS_EXPECT;

        @JvmField
        public static final Flag IS_EXTERNAL;

        @JvmField
        public static final Flag IS_FAKE_OVERRIDE;

        @JvmField
        public static final Flag IS_INFIX;

        @JvmField
        public static final Flag IS_INLINE;

        @JvmField
        public static final Flag IS_OPERATOR;

        @JvmField
        public static final Flag IS_SUSPEND;

        @JvmField
        public static final Flag IS_SYNTHESIZED;

        @JvmField
        public static final Flag IS_TAILREC;

        private Function() {
        }

        static {
            Flags.FlagField<ProtoBuf$MemberKind> MEMBER_KIND = Flags.MEMBER_KIND;
            Intrinsics.checkNotNullExpressionValue(MEMBER_KIND, "MEMBER_KIND");
            IS_DECLARATION = new Flag(MEMBER_KIND, 0);
            Intrinsics.checkNotNullExpressionValue(MEMBER_KIND, "MEMBER_KIND");
            IS_FAKE_OVERRIDE = new Flag(MEMBER_KIND, 1);
            Intrinsics.checkNotNullExpressionValue(MEMBER_KIND, "MEMBER_KIND");
            IS_DELEGATION = new Flag(MEMBER_KIND, 2);
            Intrinsics.checkNotNullExpressionValue(MEMBER_KIND, "MEMBER_KIND");
            IS_SYNTHESIZED = new Flag(MEMBER_KIND, 3);
            Flags.BooleanFlagField IS_OPERATOR2 = Flags.IS_OPERATOR;
            Intrinsics.checkNotNullExpressionValue(IS_OPERATOR2, "IS_OPERATOR");
            IS_OPERATOR = new Flag(IS_OPERATOR2);
            Flags.BooleanFlagField IS_INFIX2 = Flags.IS_INFIX;
            Intrinsics.checkNotNullExpressionValue(IS_INFIX2, "IS_INFIX");
            IS_INFIX = new Flag(IS_INFIX2);
            Flags.BooleanFlagField IS_INLINE2 = Flags.IS_INLINE;
            Intrinsics.checkNotNullExpressionValue(IS_INLINE2, "IS_INLINE");
            IS_INLINE = new Flag(IS_INLINE2);
            Flags.BooleanFlagField IS_TAILREC2 = Flags.IS_TAILREC;
            Intrinsics.checkNotNullExpressionValue(IS_TAILREC2, "IS_TAILREC");
            IS_TAILREC = new Flag(IS_TAILREC2);
            Flags.BooleanFlagField IS_EXTERNAL_FUNCTION = Flags.IS_EXTERNAL_FUNCTION;
            Intrinsics.checkNotNullExpressionValue(IS_EXTERNAL_FUNCTION, "IS_EXTERNAL_FUNCTION");
            IS_EXTERNAL = new Flag(IS_EXTERNAL_FUNCTION);
            Flags.BooleanFlagField IS_SUSPEND2 = Flags.IS_SUSPEND;
            Intrinsics.checkNotNullExpressionValue(IS_SUSPEND2, "IS_SUSPEND");
            IS_SUSPEND = new Flag(IS_SUSPEND2);
            Flags.BooleanFlagField IS_EXPECT_FUNCTION = Flags.IS_EXPECT_FUNCTION;
            Intrinsics.checkNotNullExpressionValue(IS_EXPECT_FUNCTION, "IS_EXPECT_FUNCTION");
            IS_EXPECT = new Flag(IS_EXPECT_FUNCTION);
            Flags.BooleanFlagField IS_FUNCTION_WITH_NON_STABLE_PARAMETER_NAMES = Flags.IS_FUNCTION_WITH_NON_STABLE_PARAMETER_NAMES;
            Intrinsics.checkNotNullExpressionValue(IS_FUNCTION_WITH_NON_STABLE_PARAMETER_NAMES, "IS_FUNCTION_WITH_NON_STABLE_PARAMETER_NAMES");
            HAS_NON_STABLE_PARAMETER_NAMES = new Flag(IS_FUNCTION_WITH_NON_STABLE_PARAMETER_NAMES);
        }
    }

    /* JADX INFO: compiled from: Flag.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lkotlinx/metadata/Flag$Type;", "", "()V", "IS_DEFINITELY_NON_NULL", "Lkotlinx/metadata/Flag;", "IS_NULLABLE", "IS_SUSPEND", "kotlinx-metadata"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Type {

        @JvmField
        public static final Flag IS_DEFINITELY_NON_NULL;

        @JvmField
        public static final Flag IS_SUSPEND;
        public static final Type INSTANCE = new Type();

        @JvmField
        public static final Flag IS_NULLABLE = new Flag(0, 1, 1);

        private Type() {
        }

        static {
            Flags.BooleanFlagField booleanFlagField = Flags.SUSPEND_TYPE;
            IS_SUSPEND = new Flag(booleanFlagField.offset + 1, booleanFlagField.bitWidth, 1);
            Flags.BooleanFlagField booleanFlagField2 = Flags.DEFINITELY_NOT_NULL_TYPE;
            IS_DEFINITELY_NON_NULL = new Flag(booleanFlagField2.offset + 1, booleanFlagField2.bitWidth, 1);
        }
    }
}
