package net.bytebuddy.jar.asm;

import com.android.dx.cf.attrib.AttSynthetic;
import com.android.multidex.ClassPathElement;
import net.bytebuddy.implementation.auxiliary.TypeProxy;
import net.bytebuddy.pool.TypePool;

/* JADX INFO: loaded from: classes3.dex */
public class ClassWriter extends ClassVisitor {
    static final int ACC_SYNTHETIC_ATTRIBUTE = 262144;
    static final int ASM_LABELW_INSN = 19;
    static final int ASM_LABEL_INSN = 18;
    static final int BSM = 33;
    static final int CLASS = 7;
    public static final int COMPUTE_FRAMES = 2;
    public static final int COMPUTE_MAXS = 1;
    static final int DOUBLE = 6;
    static final int FIELD = 9;
    static final int FIELDORMETH_INSN = 6;
    static final int FLOAT = 4;
    static final int F_INSERT = 256;
    static final int HANDLE = 15;
    static final int HANDLE_BASE = 20;
    static final int IINC_INSN = 13;
    static final int IMETH = 11;
    static final int IMPLVAR_INSN = 4;
    static final int INDY = 18;
    static final int INDYMETH_INSN = 8;
    static final int INT = 3;
    static final int ITFMETH_INSN = 7;
    static final int LABELW_INSN = 10;
    static final int LABEL_INSN = 9;
    static final int LDCW_INSN = 12;
    static final int LDC_INSN = 11;
    static final int LONG = 5;
    static final int LOOK_INSN = 15;
    static final int MANA_INSN = 16;
    static final int METH = 10;
    static final int MODULE = 19;
    static final int MTYPE = 16;
    static final int NAME_TYPE = 12;
    static final int NOARG_INSN = 0;
    static final int PACKAGE = 20;
    static final int SBYTE_INSN = 1;
    static final int SHORT_INSN = 2;
    static final int STR = 8;
    static final int TABL_INSN = 14;
    static final int TO_ACC_SYNTHETIC = 64;
    static final byte[] TYPE;
    static final int TYPE_INSN = 5;
    static final int TYPE_MERGED = 32;
    static final int TYPE_NORMAL = 30;
    static final int TYPE_UNINIT = 31;
    static final int UTF8 = 1;
    static final int VAR_INSN = 3;
    static final int WIDE_INSN = 17;
    private int access;
    private AnnotationWriter anns;
    private Attribute attrs;
    ByteVector bootstrapMethods;
    int bootstrapMethodsCount;
    private int compute;
    ClassReader cr;
    private int enclosingMethod;
    private int enclosingMethodOwner;
    FieldWriter firstField;
    MethodWriter firstMethod;
    boolean hasAsmInsns;
    private AnnotationWriter ianns;
    int index;
    private ByteVector innerClasses;
    private int innerClassesCount;
    private int interfaceCount;
    private int[] interfaces;
    private AnnotationWriter itanns;
    Item[] items;
    final Item key;
    final Item key2;
    final Item key3;
    final Item key4;
    FieldWriter lastField;
    MethodWriter lastMethod;
    private ModuleWriter moduleWriter;
    private int name;
    final ByteVector pool;
    private int signature;
    private ByteVector sourceDebug;
    private int sourceFile;
    private int superName;
    private AnnotationWriter tanns;
    String thisName;
    int threshold;
    private short typeCount;
    Item[] typeTable;
    int version;

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public final void visitEnd() {
    }

    static {
        byte[] bArr = new byte[221];
        for (int i = 0; i < 221; i++) {
            bArr[i] = (byte) ("AAAAAAAAAAAAAAAABCLMMDDDDDEEEEEEEEEEEEEEEEEEEEAAAAAAAADDDDDEEEEEEEEEEEEEEEEEEEEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAANAAAAAAAAAAAAAAAAAAAAJJJJJJJJJJJJJJJJDOPAAAAAAGGGGGGGHIFBFAAFFAARQJJKKSSSSSSSSSSSSSSSSSST".charAt(i) - 'A');
        }
        TYPE = bArr;
    }

    public ClassWriter(int i) {
        super(Opcodes.ASM6);
        this.index = 1;
        this.pool = new ByteVector();
        Item[] itemArr = new Item[256];
        this.items = itemArr;
        this.threshold = (int) (((double) itemArr.length) * 0.75d);
        this.key = new Item();
        this.key2 = new Item();
        this.key3 = new Item();
        this.key4 = new Item();
        this.compute = (i & 2) != 0 ? 0 : (i & 1) != 0 ? 2 : 3;
    }

    public ClassWriter(ClassReader classReader, int i) {
        this(i);
        classReader.copyPool(this);
        this.cr = classReader;
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public final void visit(int i, int i2, String str, String str2, String str3, String[] strArr) {
        this.version = i;
        this.access = i2;
        this.name = newClass(str);
        this.thisName = str;
        if (str2 != null) {
            this.signature = newUTF8(str2);
        }
        this.superName = str3 == null ? 0 : newClass(str3);
        if (strArr == null || strArr.length <= 0) {
            return;
        }
        int length = strArr.length;
        this.interfaceCount = length;
        this.interfaces = new int[length];
        for (int i3 = 0; i3 < this.interfaceCount; i3++) {
            this.interfaces[i3] = newClass(strArr[i3]);
        }
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public final void visitSource(String str, String str2) {
        if (str != null) {
            this.sourceFile = newUTF8(str);
        }
        if (str2 != null) {
            this.sourceDebug = new ByteVector().encodeUTF8(str2, 0, Integer.MAX_VALUE);
        }
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public final ModuleVisitor visitModule(String str, int i, String str2) {
        ModuleWriter moduleWriter = new ModuleWriter(this, newModule(str), i, str2 == null ? 0 : newUTF8(str2));
        this.moduleWriter = moduleWriter;
        return moduleWriter;
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public final void visitOuterClass(String str, String str2, String str3) {
        this.enclosingMethodOwner = newClass(str);
        if (str2 == null || str3 == null) {
            return;
        }
        this.enclosingMethod = newNameType(str2, str3);
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public final AnnotationVisitor visitAnnotation(String str, boolean z) {
        ByteVector byteVector = new ByteVector();
        byteVector.putShort(newUTF8(str)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this, true, byteVector, byteVector, 2);
        if (z) {
            annotationWriter.next = this.anns;
            this.anns = annotationWriter;
        } else {
            annotationWriter.next = this.ianns;
            this.ianns = annotationWriter;
        }
        return annotationWriter;
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public final AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z) {
        ByteVector byteVector = new ByteVector();
        AnnotationWriter.putTarget(i, typePath, byteVector);
        byteVector.putShort(newUTF8(str)).putShort(0);
        AnnotationWriter annotationWriter = new AnnotationWriter(this, true, byteVector, byteVector, byteVector.length - 2);
        if (z) {
            annotationWriter.next = this.tanns;
            this.tanns = annotationWriter;
        } else {
            annotationWriter.next = this.itanns;
            this.itanns = annotationWriter;
        }
        return annotationWriter;
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public final void visitAttribute(Attribute attribute) {
        attribute.next = this.attrs;
        this.attrs = attribute;
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public final void visitInnerClass(String str, String str2, String str3, int i) {
        if (this.innerClasses == null) {
            this.innerClasses = new ByteVector();
        }
        Item itemNewStringishItem = newStringishItem(7, str);
        if (itemNewStringishItem.intVal == 0) {
            this.innerClassesCount++;
            this.innerClasses.putShort(itemNewStringishItem.index);
            this.innerClasses.putShort(str2 == null ? 0 : newClass(str2));
            this.innerClasses.putShort(str3 != null ? newUTF8(str3) : 0);
            this.innerClasses.putShort(i);
            itemNewStringishItem.intVal = this.innerClassesCount;
        }
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public final FieldVisitor visitField(int i, String str, String str2, String str3, Object obj) {
        return new FieldWriter(this, i, str, str2, str3, obj);
    }

    @Override // net.bytebuddy.jar.asm.ClassVisitor
    public final MethodVisitor visitMethod(int i, String str, String str2, String str3, String[] strArr) {
        return new MethodWriter(this, i, str, str2, str3, strArr, this.compute);
    }

    public byte[] toByteArray() {
        int i;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        int i2;
        ByteVector byteVector;
        if (this.index > 65535) {
            throw new RuntimeException("Class file too large!");
        }
        int size = (this.interfaceCount * 2) + 24;
        int i3 = 0;
        for (FieldWriter fieldWriter = this.firstField; fieldWriter != null; fieldWriter = (FieldWriter) fieldWriter.fv) {
            i3++;
            size += fieldWriter.getSize();
        }
        int i4 = 0;
        for (MethodWriter methodWriter = this.firstMethod; methodWriter != null; methodWriter = (MethodWriter) methodWriter.mv) {
            i4++;
            size += methodWriter.getSize();
        }
        ByteVector byteVector2 = this.bootstrapMethods;
        if (byteVector2 != null) {
            size += byteVector2.length + 8;
            newUTF8("BootstrapMethods");
            i = 1;
        } else {
            i = 0;
        }
        if (this.signature != 0) {
            i++;
            size += 8;
            newUTF8("Signature");
        }
        if (this.sourceFile != 0) {
            i++;
            size += 8;
            newUTF8("SourceFile");
        }
        ByteVector byteVector3 = this.sourceDebug;
        if (byteVector3 != null) {
            i++;
            size += byteVector3.length + 6;
            newUTF8("SourceDebugExtension");
        }
        if (this.enclosingMethodOwner != 0) {
            i++;
            size += 10;
            newUTF8("EnclosingMethod");
        }
        if ((this.access & 131072) != 0) {
            i++;
            size += 6;
            newUTF8("Deprecated");
        }
        int i5 = this.access;
        if ((i5 & 4096) != 0 && ((this.version & 65535) < 49 || (i5 & 262144) != 0)) {
            i++;
            size += 6;
            newUTF8(AttSynthetic.ATTRIBUTE_NAME);
        }
        ByteVector byteVector4 = this.innerClasses;
        if (byteVector4 != null) {
            i++;
            size += byteVector4.length + 8;
            newUTF8("InnerClasses");
        }
        AnnotationWriter annotationWriter = this.anns;
        if (annotationWriter != null) {
            i++;
            size += annotationWriter.getSize() + 8;
            newUTF8("RuntimeVisibleAnnotations");
        }
        AnnotationWriter annotationWriter2 = this.ianns;
        if (annotationWriter2 != null) {
            i++;
            size += annotationWriter2.getSize() + 8;
            newUTF8("RuntimeInvisibleAnnotations");
        }
        AnnotationWriter annotationWriter3 = this.tanns;
        if (annotationWriter3 != null) {
            i++;
            size += annotationWriter3.getSize() + 8;
            newUTF8("RuntimeVisibleTypeAnnotations");
        }
        AnnotationWriter annotationWriter4 = this.itanns;
        if (annotationWriter4 != null) {
            i++;
            size += annotationWriter4.getSize() + 8;
            newUTF8("RuntimeInvisibleTypeAnnotations");
        }
        ModuleWriter moduleWriter = this.moduleWriter;
        if (moduleWriter != null) {
            i += moduleWriter.attributeCount + 1;
            size += this.moduleWriter.size + 6 + this.moduleWriter.attributesSize;
            newUTF8("Module");
        }
        int size2 = size;
        Attribute attribute = this.attrs;
        if (attribute != null) {
            int count = i + attribute.getCount();
            Attribute attribute2 = this.attrs;
            str = "EnclosingMethod";
            str3 = "SourceDebugExtension";
            str2 = "RuntimeVisibleTypeAnnotations";
            str5 = "InnerClasses";
            str6 = "SourceFile";
            str4 = "RuntimeInvisibleAnnotations";
            str7 = "RuntimeVisibleAnnotations";
            str8 = AttSynthetic.ATTRIBUTE_NAME;
            size2 += attribute2.getSize(this, null, 0, -1, -1);
            i = count;
        } else {
            str = "EnclosingMethod";
            str2 = "RuntimeVisibleTypeAnnotations";
            str3 = "SourceDebugExtension";
            str4 = "RuntimeInvisibleAnnotations";
            str5 = "InnerClasses";
            str6 = "SourceFile";
            str7 = "RuntimeVisibleAnnotations";
            str8 = AttSynthetic.ATTRIBUTE_NAME;
        }
        ByteVector byteVector5 = new ByteVector(size2 + this.pool.length);
        byteVector5.putInt(-889275714).putInt(this.version);
        byteVector5.putShort(this.index).putByteArray(this.pool.data, 0, this.pool.length);
        int i6 = this.access;
        byteVector5.putShort(i6 & (~(((i6 & 262144) / 64) | Opcodes.ASM6))).putShort(this.name).putShort(this.superName);
        byteVector5.putShort(this.interfaceCount);
        for (int i7 = 0; i7 < this.interfaceCount; i7++) {
            byteVector5.putShort(this.interfaces[i7]);
        }
        byteVector5.putShort(i3);
        for (FieldWriter fieldWriter2 = this.firstField; fieldWriter2 != null; fieldWriter2 = (FieldWriter) fieldWriter2.fv) {
            fieldWriter2.put(byteVector5);
        }
        byteVector5.putShort(i4);
        for (MethodWriter methodWriter2 = this.firstMethod; methodWriter2 != null; methodWriter2 = (MethodWriter) methodWriter2.mv) {
            methodWriter2.put(byteVector5);
        }
        byteVector5.putShort(i);
        if (this.bootstrapMethods != null) {
            byteVector5.putShort(newUTF8("BootstrapMethods"));
            byteVector5.putInt(this.bootstrapMethods.length + 2).putShort(this.bootstrapMethodsCount);
            byteVector5.putByteArray(this.bootstrapMethods.data, 0, this.bootstrapMethods.length);
        }
        if (this.signature != 0) {
            i2 = 2;
            byteVector5.putShort(newUTF8("Signature")).putInt(2).putShort(this.signature);
        } else {
            i2 = 2;
        }
        if (this.sourceFile != 0) {
            byteVector5.putShort(newUTF8(str6)).putInt(i2).putShort(this.sourceFile);
        }
        ByteVector byteVector6 = this.sourceDebug;
        if (byteVector6 != null) {
            int i8 = byteVector6.length;
            byteVector5.putShort(newUTF8(str3)).putInt(i8);
            byteVector5.putByteArray(this.sourceDebug.data, 0, i8);
        }
        if (this.moduleWriter != null) {
            byteVector5.putShort(newUTF8("Module"));
            this.moduleWriter.put(byteVector5);
            this.moduleWriter.putAttributes(byteVector5);
        }
        if (this.enclosingMethodOwner != 0) {
            byteVector5.putShort(newUTF8(str)).putInt(4);
            byteVector5.putShort(this.enclosingMethodOwner).putShort(this.enclosingMethod);
        }
        if ((this.access & 131072) != 0) {
            byteVector5.putShort(newUTF8("Deprecated")).putInt(0);
        }
        int i9 = this.access;
        if ((i9 & 4096) != 0 && ((this.version & 65535) < 49 || (i9 & 262144) != 0)) {
            byteVector5.putShort(newUTF8(str8)).putInt(0);
        }
        if (this.innerClasses != null) {
            byteVector5.putShort(newUTF8(str5));
            byteVector5.putInt(this.innerClasses.length + 2).putShort(this.innerClassesCount);
            byteVector5.putByteArray(this.innerClasses.data, 0, this.innerClasses.length);
        }
        if (this.anns != null) {
            byteVector5.putShort(newUTF8(str7));
            this.anns.put(byteVector5);
        }
        if (this.ianns != null) {
            byteVector5.putShort(newUTF8(str4));
            this.ianns.put(byteVector5);
        }
        if (this.tanns != null) {
            byteVector5.putShort(newUTF8(str2));
            this.tanns.put(byteVector5);
        }
        if (this.itanns != null) {
            byteVector5.putShort(newUTF8("RuntimeInvisibleTypeAnnotations"));
            this.itanns.put(byteVector5);
        }
        Attribute attribute3 = this.attrs;
        if (attribute3 != null) {
            byteVector = byteVector5;
            attribute3.put(this, null, 0, -1, -1, byteVector);
        } else {
            byteVector = byteVector5;
        }
        if (this.hasAsmInsns) {
            boolean z = false;
            for (MethodWriter methodWriter3 = this.firstMethod; methodWriter3 != null; methodWriter3 = (MethodWriter) methodWriter3.mv) {
                z |= methodWriter3.frameCount > 0;
            }
            this.anns = null;
            this.ianns = null;
            this.attrs = null;
            this.moduleWriter = null;
            this.firstField = null;
            this.lastField = null;
            this.firstMethod = null;
            this.lastMethod = null;
            this.compute = z ? 1 : 3;
            this.hasAsmInsns = false;
            new ClassReader(byteVector.data).accept(this, (z ? 8 : 0) | 256);
            return toByteArray();
        }
        return byteVector.data;
    }

    Item newConstItem(Object obj) {
        if (obj instanceof Integer) {
            return newInteger(((Integer) obj).intValue());
        }
        if (obj instanceof Byte) {
            return newInteger(((Byte) obj).intValue());
        }
        if (obj instanceof Character) {
            return newInteger(((Character) obj).charValue());
        }
        if (obj instanceof Short) {
            return newInteger(((Short) obj).intValue());
        }
        if (obj instanceof Boolean) {
            return newInteger(((Boolean) obj).booleanValue() ? 1 : 0);
        }
        if (obj instanceof Float) {
            return newFloat(((Float) obj).floatValue());
        }
        if (obj instanceof Long) {
            return newLong(((Long) obj).longValue());
        }
        if (obj instanceof Double) {
            return newDouble(((Double) obj).doubleValue());
        }
        if (obj instanceof String) {
            return newStringishItem(8, (String) obj);
        }
        if (obj instanceof Type) {
            Type type = (Type) obj;
            int sort = type.getSort();
            if (sort == 10) {
                return newStringishItem(7, type.getInternalName());
            }
            if (sort == 11) {
                return newStringishItem(16, type.getDescriptor());
            }
            return newStringishItem(7, type.getDescriptor());
        }
        if (obj instanceof Handle) {
            Handle handle = (Handle) obj;
            return newHandleItem(handle.tag, handle.owner, handle.name, handle.desc, handle.itf);
        }
        throw new IllegalArgumentException("value " + obj);
    }

    public int newConst(Object obj) {
        return newConstItem(obj).index;
    }

    public int newUTF8(String str) {
        this.key.set(1, str, null, null);
        Item item = get(this.key);
        if (item == null) {
            this.pool.putByte(1).putUTF8(str);
            int i = this.index;
            this.index = i + 1;
            item = new Item(i, this.key);
            put(item);
        }
        return item.index;
    }

    Item newStringishItem(int i, String str) {
        this.key2.set(i, str, null, null);
        Item item = get(this.key2);
        if (item != null) {
            return item;
        }
        this.pool.put12(i, newUTF8(str));
        int i2 = this.index;
        this.index = i2 + 1;
        Item item2 = new Item(i2, this.key2);
        put(item2);
        return item2;
    }

    public int newClass(String str) {
        return newStringishItem(7, str).index;
    }

    public int newMethodType(String str) {
        return newStringishItem(16, str).index;
    }

    public int newModule(String str) {
        return newStringishItem(19, str).index;
    }

    public int newPackage(String str) {
        return newStringishItem(20, str).index;
    }

    Item newHandleItem(int i, String str, String str2, String str3, boolean z) {
        this.key4.set(i + 20, str, str2, str3);
        Item item = get(this.key4);
        if (item != null) {
            return item;
        }
        if (i <= 4) {
            put112(15, i, newField(str, str2, str3));
        } else {
            put112(15, i, newMethod(str, str2, str3, z));
        }
        int i2 = this.index;
        this.index = i2 + 1;
        Item item2 = new Item(i2, this.key4);
        put(item2);
        return item2;
    }

    @Deprecated
    public int newHandle(int i, String str, String str2, String str3) {
        return newHandle(i, str, str2, str3, i == 9);
    }

    public int newHandle(int i, String str, String str2, String str3, boolean z) {
        return newHandleItem(i, str, str2, str3, z).index;
    }

    Item newInvokeDynamicItem(String str, String str2, Handle handle, Object... objArr) {
        int i;
        ByteVector byteVector = this.bootstrapMethods;
        if (byteVector == null) {
            byteVector = new ByteVector();
            this.bootstrapMethods = byteVector;
        }
        int i2 = byteVector.length;
        int iHashCode = handle.hashCode();
        byteVector.putShort(newHandle(handle.tag, handle.owner, handle.name, handle.desc, handle.isInterface()));
        int length = objArr.length;
        byteVector.putShort(length);
        for (Object obj : objArr) {
            iHashCode ^= obj.hashCode();
            byteVector.putShort(newConst(obj));
        }
        byte[] bArr = byteVector.data;
        int i3 = (length + 2) << 1;
        int i4 = iHashCode & Integer.MAX_VALUE;
        Item[] itemArr = this.items;
        Item item = itemArr[i4 % itemArr.length];
        loop1: while (item != null) {
            if (item.type != 33 || item.hashCode != i4) {
                item = item.next;
            } else {
                int i5 = item.intVal;
                for (int i6 = 0; i6 < i3; i6++) {
                    if (bArr[i2 + i6] != bArr[i5 + i6]) {
                        item = item.next;
                    }
                }
                break loop1;
            }
        }
        if (item != null) {
            i = item.index;
            byteVector.length = i2;
        } else {
            i = this.bootstrapMethodsCount;
            this.bootstrapMethodsCount = i + 1;
            Item item2 = new Item(i);
            item2.set(i2, i4);
            put(item2);
        }
        this.key3.set(str, str2, i);
        Item item3 = get(this.key3);
        if (item3 != null) {
            return item3;
        }
        put122(18, i, newNameType(str, str2));
        int i7 = this.index;
        this.index = i7 + 1;
        Item item4 = new Item(i7, this.key3);
        put(item4);
        return item4;
    }

    public int newInvokeDynamic(String str, String str2, Handle handle, Object... objArr) {
        return newInvokeDynamicItem(str, str2, handle, objArr).index;
    }

    Item newFieldItem(String str, String str2, String str3) {
        this.key3.set(9, str, str2, str3);
        Item item = get(this.key3);
        if (item != null) {
            return item;
        }
        put122(9, newClass(str), newNameType(str2, str3));
        int i = this.index;
        this.index = i + 1;
        Item item2 = new Item(i, this.key3);
        put(item2);
        return item2;
    }

    public int newField(String str, String str2, String str3) {
        return newFieldItem(str, str2, str3).index;
    }

    Item newMethodItem(String str, String str2, String str3, boolean z) {
        int i = z ? 11 : 10;
        this.key3.set(i, str, str2, str3);
        Item item = get(this.key3);
        if (item != null) {
            return item;
        }
        put122(i, newClass(str), newNameType(str2, str3));
        int i2 = this.index;
        this.index = i2 + 1;
        Item item2 = new Item(i2, this.key3);
        put(item2);
        return item2;
    }

    public int newMethod(String str, String str2, String str3, boolean z) {
        return newMethodItem(str, str2, str3, z).index;
    }

    Item newInteger(int i) {
        this.key.set(i);
        Item item = get(this.key);
        if (item != null) {
            return item;
        }
        this.pool.putByte(3).putInt(i);
        int i2 = this.index;
        this.index = i2 + 1;
        Item item2 = new Item(i2, this.key);
        put(item2);
        return item2;
    }

    Item newFloat(float f) {
        this.key.set(f);
        Item item = get(this.key);
        if (item != null) {
            return item;
        }
        this.pool.putByte(4).putInt(this.key.intVal);
        int i = this.index;
        this.index = i + 1;
        Item item2 = new Item(i, this.key);
        put(item2);
        return item2;
    }

    Item newLong(long j) {
        this.key.set(j);
        Item item = get(this.key);
        if (item != null) {
            return item;
        }
        this.pool.putByte(5).putLong(j);
        Item item2 = new Item(this.index, this.key);
        this.index += 2;
        put(item2);
        return item2;
    }

    Item newDouble(double d) {
        this.key.set(d);
        Item item = get(this.key);
        if (item != null) {
            return item;
        }
        this.pool.putByte(6).putLong(this.key.longVal);
        Item item2 = new Item(this.index, this.key);
        this.index += 2;
        put(item2);
        return item2;
    }

    public int newNameType(String str, String str2) {
        return newNameTypeItem(str, str2).index;
    }

    Item newNameTypeItem(String str, String str2) {
        this.key2.set(12, str, str2, null);
        Item item = get(this.key2);
        if (item != null) {
            return item;
        }
        put122(12, newUTF8(str), newUTF8(str2));
        int i = this.index;
        this.index = i + 1;
        Item item2 = new Item(i, this.key2);
        put(item2);
        return item2;
    }

    int addType(String str) {
        this.key.set(30, str, null, null);
        Item itemAddType = get(this.key);
        if (itemAddType == null) {
            itemAddType = addType(this.key);
        }
        return itemAddType.index;
    }

    int addUninitializedType(String str, int i) {
        this.key.type = 31;
        this.key.intVal = i;
        this.key.strVal1 = str;
        this.key.hashCode = (str.hashCode() + 31 + i) & Integer.MAX_VALUE;
        Item itemAddType = get(this.key);
        if (itemAddType == null) {
            itemAddType = addType(this.key);
        }
        return itemAddType.index;
    }

    private Item addType(Item item) {
        this.typeCount = (short) (this.typeCount + 1);
        Item item2 = new Item(this.typeCount, this.key);
        put(item2);
        if (this.typeTable == null) {
            this.typeTable = new Item[16];
        }
        short s = this.typeCount;
        Item[] itemArr = this.typeTable;
        if (s == itemArr.length) {
            Item[] itemArr2 = new Item[itemArr.length * 2];
            System.arraycopy(itemArr, 0, itemArr2, 0, itemArr.length);
            this.typeTable = itemArr2;
        }
        this.typeTable[this.typeCount] = item2;
        return item2;
    }

    int getMergedType(int i, int i2) {
        this.key2.type = 32;
        this.key2.longVal = ((long) i) | (((long) i2) << 32);
        this.key2.hashCode = (i + 32 + i2) & Integer.MAX_VALUE;
        Item item = get(this.key2);
        if (item == null) {
            String str = this.typeTable[i].strVal1;
            String str2 = this.typeTable[i2].strVal1;
            this.key2.intVal = addType(getCommonSuperClass(str, str2));
            item = new Item(0, this.key2);
            put(item);
        }
        return item.intVal;
    }

    protected String getCommonSuperClass(String str, String str2) {
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            Class<?> cls = Class.forName(str.replace(ClassPathElement.SEPARATOR_CHAR, TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH), false, classLoader);
            Class<?> cls2 = Class.forName(str2.replace(ClassPathElement.SEPARATOR_CHAR, TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH), false, classLoader);
            if (cls.isAssignableFrom(cls2)) {
                return str;
            }
            if (cls2.isAssignableFrom(cls)) {
                return str2;
            }
            if (cls.isInterface() || cls2.isInterface()) {
                return TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_INTERNAL_NAME;
            }
            do {
                cls = cls.getSuperclass();
            } while (!cls.isAssignableFrom(cls2));
            return cls.getName().replace(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH, ClassPathElement.SEPARATOR_CHAR);
        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        }
    }

    private Item get(Item item) {
        Item item2 = this.items[item.hashCode % this.items.length];
        while (item2 != null && (item2.type != item.type || !item.isEqualTo(item2))) {
            item2 = item2.next;
        }
        return item2;
    }

    private void put(Item item) {
        if (this.index + this.typeCount > this.threshold) {
            int length = this.items.length;
            int i = (length * 2) + 1;
            Item[] itemArr = new Item[i];
            for (int i2 = length - 1; i2 >= 0; i2--) {
                Item item2 = this.items[i2];
                while (item2 != null) {
                    int i3 = item2.hashCode % i;
                    Item item3 = item2.next;
                    item2.next = itemArr[i3];
                    itemArr[i3] = item2;
                    item2 = item3;
                }
            }
            this.items = itemArr;
            this.threshold = (int) (((double) i) * 0.75d);
        }
        int i4 = item.hashCode;
        Item[] itemArr2 = this.items;
        int length2 = i4 % itemArr2.length;
        item.next = itemArr2[length2];
        this.items[length2] = item;
    }

    private void put122(int i, int i2, int i3) {
        this.pool.put12(i, i2).putShort(i3);
    }

    private void put112(int i, int i2, int i3) {
        this.pool.put11(i, i2).putShort(i3);
    }
}
