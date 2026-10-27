package net.bytebuddy.jar.asm;

import com.android.dx.cf.attrib.AttSynthetic;
import com.android.multidex.ClassPathElement;
import java.io.IOException;
import java.io.InputStream;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.pool.TypePool;
import okio.Utf8;

/* JADX INFO: loaded from: classes3.dex */
public class ClassReader {
    static final int EXPAND_ASM_INSNS = 256;
    public static final int EXPAND_FRAMES = 8;
    public static final int SKIP_CODE = 1;
    public static final int SKIP_DEBUG = 2;
    public static final int SKIP_FRAMES = 4;
    public final byte[] b;
    public final int header;
    private final int[] items;
    private final int maxStringLength;
    private final String[] strings;

    public ClassReader(byte[] bArr) {
        this(bArr, 0, bArr.length);
    }

    public ClassReader(byte[] bArr, int i, int i2) {
        this.b = bArr;
        if (readShort(i + 6) > 53) {
            throw new IllegalArgumentException();
        }
        int[] iArr = new int[readUnsignedShort(i + 8)];
        this.items = iArr;
        int length = iArr.length;
        this.strings = new String[length];
        int i3 = i + 10;
        int i4 = 0;
        int i5 = 1;
        while (i5 < length) {
            int i6 = i3 + 1;
            this.items[i5] = i6;
            byte b = bArr[i3];
            int unsignedShort = 3;
            if (b == 1) {
                unsignedShort = 3 + readUnsignedShort(i6);
                if (unsignedShort > i4) {
                    i4 = unsignedShort;
                }
            } else if (b == 15) {
                unsignedShort = 4;
            } else if (b != 18 && b != 3 && b != 4) {
                if (b != 5 && b != 6) {
                    switch (b) {
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                            unsignedShort = 5;
                            break;
                    }
                } else {
                    i5++;
                    unsignedShort = 9;
                }
            } else {
                unsignedShort = 5;
            }
            i3 += unsignedShort;
            i5++;
        }
        this.maxStringLength = i4;
        this.header = i3;
    }

    public int getAccess() {
        return readUnsignedShort(this.header);
    }

    public String getClassName() {
        return readClass(this.header + 2, new char[this.maxStringLength]);
    }

    public String getSuperName() {
        return readClass(this.header + 4, new char[this.maxStringLength]);
    }

    public String[] getInterfaces() {
        int i = this.header + 6;
        int unsignedShort = readUnsignedShort(i);
        String[] strArr = new String[unsignedShort];
        if (unsignedShort > 0) {
            char[] cArr = new char[this.maxStringLength];
            for (int i2 = 0; i2 < unsignedShort; i2++) {
                i += 2;
                strArr[i2] = readClass(i, cArr);
            }
        }
        return strArr;
    }

    void copyPool(ClassWriter classWriter) {
        char[] cArr = new char[this.maxStringLength];
        int length = this.items.length;
        Item[] itemArr = new Item[length];
        int i = 1;
        while (i < length) {
            int i2 = this.items[i];
            byte b = this.b[i2 - 1];
            Item item = new Item(i);
            if (b == 1) {
                String[] strArr = this.strings;
                String utf = strArr[i];
                if (utf == null) {
                    int i3 = this.items[i];
                    utf = readUTF(i3 + 2, readUnsignedShort(i3), cArr);
                    strArr[i] = utf;
                }
                item.set(b, utf, null, null);
            } else if (b == 15) {
                int i4 = this.items[readUnsignedShort(i2 + 1)];
                int i5 = this.items[readUnsignedShort(i4 + 2)];
                item.set(readByte(i2) + 20, readClass(i4, cArr), readUTF8(i5, cArr), readUTF8(i5 + 2, cArr));
            } else if (b == 18) {
                if (classWriter.bootstrapMethods == null) {
                    copyBootstrapMethods(classWriter, itemArr, cArr);
                }
                int i6 = this.items[readUnsignedShort(i2 + 2)];
                item.set(readUTF8(i6, cArr), readUTF8(i6 + 2, cArr), readUnsignedShort(i2));
            } else if (b == 3) {
                item.set(readInt(i2));
            } else if (b != 4) {
                if (b == 5) {
                    item.set(readLong(i2));
                } else if (b != 6) {
                    switch (b) {
                        case 9:
                        case 10:
                        case 11:
                            int i7 = this.items[readUnsignedShort(i2 + 2)];
                            item.set(b, readClass(i2, cArr), readUTF8(i7, cArr), readUTF8(i7 + 2, cArr));
                            break;
                        case 12:
                            item.set(b, readUTF8(i2, cArr), readUTF8(i2 + 2, cArr), null);
                            break;
                        default:
                            item.set(b, readUTF8(i2, cArr), null, null);
                            break;
                    }
                } else {
                    item.set(Double.longBitsToDouble(readLong(i2)));
                }
                i++;
            } else {
                item.set(Float.intBitsToFloat(readInt(i2)));
            }
            int i8 = item.hashCode % length;
            item.next = itemArr[i8];
            itemArr[i8] = item;
            i++;
        }
        int i9 = this.items[1] - 1;
        classWriter.pool.putByteArray(this.b, i9, this.header - i9);
        classWriter.items = itemArr;
        classWriter.threshold = (int) (((double) length) * 0.75d);
        classWriter.index = length;
    }

    private void copyBootstrapMethods(ClassWriter classWriter, Item[] itemArr, char[] cArr) {
        int i;
        boolean z;
        int attributes = getAttributes();
        int unsignedShort = readUnsignedShort(attributes);
        while (true) {
            if (unsignedShort <= 0) {
                z = false;
                break;
            } else if ("BootstrapMethods".equals(readUTF8(attributes + 2, cArr))) {
                z = true;
                break;
            } else {
                attributes += readInt(attributes + 4) + 6;
                unsignedShort--;
            }
        }
        if (z) {
            int unsignedShort2 = readUnsignedShort(attributes + 8);
            int i2 = attributes + 10;
            int i3 = i2;
            for (i = 0; i < unsignedShort2; i++) {
                int i4 = (i3 - attributes) - 10;
                int iHashCode = readConst(readUnsignedShort(i3), cArr).hashCode();
                for (int unsignedShort3 = readUnsignedShort(i3 + 2); unsignedShort3 > 0; unsignedShort3--) {
                    iHashCode ^= readConst(readUnsignedShort(i3 + 4), cArr).hashCode();
                    i3 += 2;
                }
                i3 += 4;
                Item item = new Item(i);
                item.set(i4, iHashCode & Integer.MAX_VALUE);
                int length = item.hashCode % itemArr.length;
                item.next = itemArr[length];
                itemArr[length] = item;
            }
            int i5 = readInt(attributes + 4);
            ByteVector byteVector = new ByteVector(i5 + 62);
            byteVector.putByteArray(this.b, i2, i5 - 2);
            classWriter.bootstrapMethodsCount = unsignedShort2;
            classWriter.bootstrapMethods = byteVector;
        }
    }

    public ClassReader(InputStream inputStream) throws IOException {
        this(readClass(inputStream, false));
    }

    public ClassReader(String str) throws IOException {
        this(readClass(ClassLoader.getSystemResourceAsStream(str.replace(TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH, ClassPathElement.SEPARATOR_CHAR) + ClassFileLocator.CLASS_FILE_EXTENSION), true));
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0021, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0014, code lost:
    
        if (r2 >= r0.length) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0016, code lost:
    
        r3 = new byte[r2];
        java.lang.System.arraycopy(r0, 0, r3, 0, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001b, code lost:
    
        r0 = r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static byte[] readClass(java.io.InputStream r5, boolean r6) throws java.io.IOException {
        /*
            if (r5 == 0) goto L49
            int r0 = r5.available()     // Catch: java.lang.Throwable -> L42
            byte[] r0 = new byte[r0]     // Catch: java.lang.Throwable -> L42
            r1 = 0
            r2 = r1
        La:
            int r3 = r0.length     // Catch: java.lang.Throwable -> L42
            int r3 = r3 - r2
            int r3 = r5.read(r0, r2, r3)     // Catch: java.lang.Throwable -> L42
            r4 = -1
            if (r3 != r4) goto L22
            int r3 = r0.length     // Catch: java.lang.Throwable -> L42
            if (r2 >= r3) goto L1c
            byte[] r3 = new byte[r2]     // Catch: java.lang.Throwable -> L42
            java.lang.System.arraycopy(r0, r1, r3, r1, r2)     // Catch: java.lang.Throwable -> L42
            r0 = r3
        L1c:
            if (r6 == 0) goto L21
            r5.close()
        L21:
            return r0
        L22:
            int r2 = r2 + r3
            int r3 = r0.length     // Catch: java.lang.Throwable -> L42
            if (r2 != r3) goto La
            int r3 = r5.read()     // Catch: java.lang.Throwable -> L42
            if (r3 >= 0) goto L32
            if (r6 == 0) goto L31
            r5.close()
        L31:
            return r0
        L32:
            int r4 = r0.length     // Catch: java.lang.Throwable -> L42
            int r4 = r4 + 1000
            byte[] r4 = new byte[r4]     // Catch: java.lang.Throwable -> L42
            java.lang.System.arraycopy(r0, r1, r4, r1, r2)     // Catch: java.lang.Throwable -> L42
            int r0 = r2 + 1
            byte r3 = (byte) r3     // Catch: java.lang.Throwable -> L42
            r4[r2] = r3     // Catch: java.lang.Throwable -> L42
            r2 = r0
            r0 = r4
            goto La
        L42:
            r0 = move-exception
            if (r6 == 0) goto L48
            r5.close()
        L48:
            throw r0
        L49:
            java.io.IOException r5 = new java.io.IOException
            java.lang.String r6 = "Class not found"
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.jar.asm.ClassReader.readClass(java.io.InputStream, boolean):byte[]");
    }

    public void accept(ClassVisitor classVisitor, int i) {
        accept(classVisitor, new Attribute[0], i);
    }

    public void accept(ClassVisitor classVisitor, Attribute[] attributeArr, int i) {
        int i2;
        String str;
        String str2;
        String[] strArr;
        String str3;
        Attribute attribute;
        int i3;
        int i4 = this.header;
        char[] cArr = new char[this.maxStringLength];
        Context context = new Context();
        context.attrs = attributeArr;
        context.flags = i;
        context.buffer = cArr;
        int unsignedShort = readUnsignedShort(i4);
        String str4 = readClass(i4 + 2, cArr);
        String str5 = readClass(i4 + 4, cArr);
        int unsignedShort2 = readUnsignedShort(i4 + 6);
        String[] strArr2 = new String[unsignedShort2];
        int i5 = i4 + 8;
        for (int i6 = 0; i6 < unsignedShort2; i6++) {
            strArr2[i6] = readClass(i5, cArr);
            i5 += 2;
        }
        int attributes = getAttributes();
        int i7 = attributes;
        int i8 = unsignedShort;
        int unsignedShort3 = readUnsignedShort(attributes);
        int i9 = 0;
        String utf8 = null;
        String utf = null;
        String utf82 = null;
        int i10 = 0;
        String utf83 = null;
        String str6 = null;
        int i11 = 0;
        String str7 = null;
        String utf84 = null;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        Attribute attribute2 = null;
        while (unsignedShort3 > 0) {
            String utf85 = readUTF8(i7 + 2, cArr);
            if ("SourceFile".equals(utf85)) {
                utf82 = readUTF8(i7 + 8, cArr);
            } else if ("InnerClasses".equals(utf85)) {
                i15 = i7 + 8;
            } else if ("EnclosingMethod".equals(utf85)) {
                String str8 = readClass(i7 + 8, cArr);
                int unsignedShort4 = readUnsignedShort(i7 + 10);
                if (unsignedShort4 != 0) {
                    utf84 = readUTF8(this.items[unsignedShort4], cArr);
                    utf8 = readUTF8(this.items[unsignedShort4] + 2, cArr);
                }
                str7 = str8;
            } else if ("Signature".equals(utf85)) {
                utf83 = readUTF8(i7 + 8, cArr);
            } else if ("RuntimeVisibleAnnotations".equals(utf85)) {
                i9 = i7 + 8;
            } else if ("RuntimeVisibleTypeAnnotations".equals(utf85)) {
                i13 = i7 + 8;
            } else {
                if ("Deprecated".equals(utf85)) {
                    i3 = 131072;
                } else if (AttSynthetic.ATTRIBUTE_NAME.equals(utf85)) {
                    i3 = 266240;
                } else if ("SourceDebugExtension".equals(utf85)) {
                    int i16 = readInt(i7 + 4);
                    utf = readUTF(i7 + 8, i16, new char[i16]);
                } else if ("RuntimeInvisibleAnnotations".equals(utf85)) {
                    i12 = i7 + 8;
                } else if ("RuntimeInvisibleTypeAnnotations".equals(utf85)) {
                    i14 = i7 + 8;
                } else if ("Module".equals(utf85)) {
                    i10 = i7 + 8;
                } else if ("ModuleMainClass".equals(utf85)) {
                    str6 = readClass(i7 + 8, cArr);
                } else if ("ModulePackages".equals(utf85)) {
                    i11 = i7 + 10;
                } else {
                    if ("BootstrapMethods".equals(utf85)) {
                        int unsignedShort5 = readUnsignedShort(i7 + 8);
                        int[] iArr = new int[unsignedShort5];
                        int unsignedShort6 = i7 + 10;
                        int i17 = 0;
                        while (i17 < unsignedShort5) {
                            iArr[i17] = unsignedShort6;
                            unsignedShort6 += (readUnsignedShort(unsignedShort6 + 2) + 2) << 1;
                            i17++;
                            i9 = i9;
                        }
                        context.bootstrapMethods = iArr;
                        str = utf8;
                        str2 = utf;
                        str3 = utf82;
                        strArr = strArr2;
                        attribute = attribute2;
                        i2 = i9;
                    } else {
                        i2 = i9;
                        str = utf8;
                        str2 = utf;
                        strArr = strArr2;
                        str3 = utf82;
                        Attribute attribute3 = readAttribute(attributeArr, utf85, i7 + 8, readInt(i7 + 4), cArr, -1, null);
                        attribute = attribute2;
                        if (attribute3 != null) {
                            attribute3.next = attribute;
                            attribute2 = attribute3;
                        }
                        utf82 = str3;
                        i9 = i2;
                        utf8 = str;
                        utf = str2;
                        i7 += readInt(i7 + 4) + 6;
                        unsignedShort3--;
                        strArr2 = strArr;
                    }
                    attribute2 = attribute;
                    utf82 = str3;
                    i9 = i2;
                    utf8 = str;
                    utf = str2;
                    i7 += readInt(i7 + 4) + 6;
                    unsignedShort3--;
                    strArr2 = strArr;
                }
                i8 |= i3;
            }
            strArr = strArr2;
            i7 += readInt(i7 + 4) + 6;
            unsignedShort3--;
            strArr2 = strArr;
        }
        int i18 = i9;
        String str9 = utf8;
        String str10 = utf;
        String str11 = utf82;
        Attribute attribute4 = attribute2;
        classVisitor.visit(readInt(this.items[1] - 7), i8, str4, utf83, str5, strArr2);
        if ((i & 2) == 0 && (str11 != null || str10 != null)) {
            classVisitor.visitSource(str11, str10);
        }
        if (i10 != 0) {
            readModule(classVisitor, context, i10, str6, i11);
        }
        String str12 = str7;
        if (str12 != null) {
            classVisitor.visitOuterClass(str12, utf84, str9);
        }
        if (i18 != 0) {
            int annotationValues = i18 + 2;
            for (int unsignedShort7 = readUnsignedShort(i18); unsignedShort7 > 0; unsignedShort7--) {
                annotationValues = readAnnotationValues(annotationValues + 2, cArr, true, classVisitor.visitAnnotation(readUTF8(annotationValues, cArr), true));
            }
        }
        int i19 = i12;
        if (i19 != 0) {
            int annotationValues2 = i19 + 2;
            for (int unsignedShort8 = readUnsignedShort(i19); unsignedShort8 > 0; unsignedShort8--) {
                annotationValues2 = readAnnotationValues(annotationValues2 + 2, cArr, true, classVisitor.visitAnnotation(readUTF8(annotationValues2, cArr), false));
            }
        }
        int i20 = i13;
        if (i20 != 0) {
            int annotationValues3 = i20 + 2;
            for (int unsignedShort9 = readUnsignedShort(i20); unsignedShort9 > 0; unsignedShort9--) {
                int annotationTarget = readAnnotationTarget(context, annotationValues3);
                annotationValues3 = readAnnotationValues(annotationTarget + 2, cArr, true, classVisitor.visitTypeAnnotation(context.typeRef, context.typePath, readUTF8(annotationTarget, cArr), true));
            }
        }
        int i21 = i14;
        if (i21 != 0) {
            int annotationValues4 = i21 + 2;
            for (int unsignedShort10 = readUnsignedShort(i21); unsignedShort10 > 0; unsignedShort10--) {
                int annotationTarget2 = readAnnotationTarget(context, annotationValues4);
                annotationValues4 = readAnnotationValues(annotationTarget2 + 2, cArr, true, classVisitor.visitTypeAnnotation(context.typeRef, context.typePath, readUTF8(annotationTarget2, cArr), false));
            }
        }
        while (attribute4 != null) {
            Attribute attribute5 = attribute4.next;
            attribute4.next = null;
            classVisitor.visitAttribute(attribute4);
            attribute4 = attribute5;
        }
        int i22 = i15;
        if (i22 != 0) {
            int i23 = i22 + 2;
            for (int unsignedShort11 = readUnsignedShort(i22); unsignedShort11 > 0; unsignedShort11--) {
                classVisitor.visitInnerClass(readClass(i23, cArr), readClass(i23 + 2, cArr), readUTF8(i23 + 4, cArr), readUnsignedShort(i23 + 6));
                i23 += 8;
            }
        }
        int field = this.header + 10 + (unsignedShort2 * 2);
        for (int unsignedShort12 = readUnsignedShort(field - 2); unsignedShort12 > 0; unsignedShort12--) {
            field = readField(classVisitor, context, field);
        }
        int method = field + 2;
        for (int unsignedShort13 = readUnsignedShort(method - 2); unsignedShort13 > 0; unsignedShort13--) {
            method = readMethod(classVisitor, context, method);
        }
        classVisitor.visitEnd();
    }

    private void readModule(ClassVisitor classVisitor, Context context, int i, String str, int i2) {
        String[] strArr;
        char[] cArr = context.buffer;
        String module = readModule(i, cArr);
        int unsignedShort = readUnsignedShort(i + 2);
        String utf8 = readUTF8(i + 4, cArr);
        int i3 = i + 6;
        ModuleVisitor moduleVisitorVisitModule = classVisitor.visitModule(module, unsignedShort, utf8);
        if (moduleVisitorVisitModule == null) {
            return;
        }
        if (str != null) {
            moduleVisitorVisitModule.visitMainClass(str);
        }
        if (i2 != 0) {
            for (int unsignedShort2 = readUnsignedShort(i2 - 2); unsignedShort2 > 0; unsignedShort2--) {
                moduleVisitorVisitModule.visitPackage(readPackage(i2, cArr));
                i2 += 2;
            }
        }
        int i4 = i3 + 2;
        for (int unsignedShort3 = readUnsignedShort(i4 - 2); unsignedShort3 > 0; unsignedShort3--) {
            moduleVisitorVisitModule.visitRequire(readModule(i4, cArr), readUnsignedShort(i4 + 2), readUTF8(i4 + 4, cArr));
            i4 += 6;
        }
        int i5 = i4 + 2;
        int unsignedShort4 = readUnsignedShort(i5 - 2);
        while (true) {
            String[] strArr2 = null;
            if (unsignedShort4 <= 0) {
                break;
            }
            String str2 = readPackage(i5, cArr);
            int unsignedShort5 = readUnsignedShort(i5 + 2);
            int unsignedShort6 = readUnsignedShort(i5 + 4);
            i5 += 6;
            if (unsignedShort6 != 0) {
                strArr2 = new String[unsignedShort6];
                for (int i6 = 0; i6 < unsignedShort6; i6++) {
                    strArr2[i6] = readModule(i5, cArr);
                    i5 += 2;
                }
            }
            moduleVisitorVisitModule.visitExport(str2, unsignedShort5, strArr2);
            unsignedShort4--;
        }
        int i7 = i5 + 2;
        for (int unsignedShort7 = readUnsignedShort(i7 - 2); unsignedShort7 > 0; unsignedShort7--) {
            String str3 = readPackage(i7, cArr);
            int unsignedShort8 = readUnsignedShort(i7 + 2);
            int unsignedShort9 = readUnsignedShort(i7 + 4);
            i7 += 6;
            if (unsignedShort9 != 0) {
                strArr = new String[unsignedShort9];
                for (int i8 = 0; i8 < unsignedShort9; i8++) {
                    strArr[i8] = readModule(i7, cArr);
                    i7 += 2;
                }
            } else {
                strArr = null;
            }
            moduleVisitorVisitModule.visitOpen(str3, unsignedShort8, strArr);
        }
        int i9 = i7 + 2;
        for (int unsignedShort10 = readUnsignedShort(i9 - 2); unsignedShort10 > 0; unsignedShort10--) {
            moduleVisitorVisitModule.visitUse(readClass(i9, cArr));
            i9 += 2;
        }
        int i10 = i9 + 2;
        for (int unsignedShort11 = readUnsignedShort(i10 - 2); unsignedShort11 > 0; unsignedShort11--) {
            String str4 = readClass(i10, cArr);
            int unsignedShort12 = readUnsignedShort(i10 + 2);
            i10 += 4;
            String[] strArr3 = new String[unsignedShort12];
            for (int i11 = 0; i11 < unsignedShort12; i11++) {
                strArr3[i11] = readClass(i10, cArr);
                i10 += 2;
            }
            moduleVisitorVisitModule.visitProvide(str4, strArr3);
        }
        moduleVisitorVisitModule.visitEnd();
    }

    private int readField(ClassVisitor classVisitor, Context context, int i) {
        int i2;
        Context context2 = context;
        char[] cArr = context2.buffer;
        int unsignedShort = readUnsignedShort(i);
        String utf8 = readUTF8(i + 2, cArr);
        String utf82 = readUTF8(i + 4, cArr);
        int i3 = i + 6;
        int i4 = i3;
        int i5 = unsignedShort;
        int unsignedShort2 = readUnsignedShort(i3);
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        Attribute attribute = null;
        String utf83 = null;
        Object obj = null;
        while (unsignedShort2 > 0) {
            String utf84 = readUTF8(i4 + 2, cArr);
            if ("ConstantValue".equals(utf84)) {
                int unsignedShort3 = readUnsignedShort(i4 + 8);
                obj = unsignedShort3 == 0 ? null : readConst(unsignedShort3, cArr);
            } else if ("Signature".equals(utf84)) {
                utf83 = readUTF8(i4 + 8, cArr);
            } else {
                if ("Deprecated".equals(utf84)) {
                    i2 = 131072;
                } else if (AttSynthetic.ATTRIBUTE_NAME.equals(utf84)) {
                    i2 = 266240;
                } else if ("RuntimeVisibleAnnotations".equals(utf84)) {
                    i9 = i4 + 8;
                } else if ("RuntimeVisibleTypeAnnotations".equals(utf84)) {
                    i7 = i4 + 8;
                } else if ("RuntimeInvisibleAnnotations".equals(utf84)) {
                    i8 = i4 + 8;
                } else if ("RuntimeInvisibleTypeAnnotations".equals(utf84)) {
                    i6 = i4 + 8;
                } else {
                    Attribute attribute2 = attribute;
                    int i10 = i6;
                    int i11 = i7;
                    int i12 = i8;
                    int i13 = i9;
                    attribute = readAttribute(context2.attrs, utf84, i4 + 8, readInt(i4 + 4), cArr, -1, null);
                    if (attribute != null) {
                        attribute.next = attribute2;
                        i8 = i12;
                    } else {
                        i8 = i12;
                        attribute = attribute2;
                    }
                    i9 = i13;
                    i6 = i10;
                    i7 = i11;
                }
                i5 |= i2;
            }
            i4 += readInt(i4 + 4) + 6;
            unsignedShort2--;
            context2 = context;
        }
        Attribute attribute3 = attribute;
        int i14 = i6;
        int i15 = i7;
        int i16 = i8;
        int i17 = i9;
        int i18 = i4 + 2;
        FieldVisitor fieldVisitorVisitField = classVisitor.visitField(i5, utf8, utf82, utf83, obj);
        if (fieldVisitorVisitField == null) {
            return i18;
        }
        if (i17 != 0) {
            int annotationValues = i17 + 2;
            for (int unsignedShort4 = readUnsignedShort(i17); unsignedShort4 > 0; unsignedShort4--) {
                annotationValues = readAnnotationValues(annotationValues + 2, cArr, true, fieldVisitorVisitField.visitAnnotation(readUTF8(annotationValues, cArr), true));
            }
        }
        if (i16 != 0) {
            int annotationValues2 = i16 + 2;
            for (int unsignedShort5 = readUnsignedShort(i16); unsignedShort5 > 0; unsignedShort5--) {
                annotationValues2 = readAnnotationValues(annotationValues2 + 2, cArr, true, fieldVisitorVisitField.visitAnnotation(readUTF8(annotationValues2, cArr), false));
            }
        }
        if (i15 != 0) {
            int annotationValues3 = i15 + 2;
            for (int unsignedShort6 = readUnsignedShort(i15); unsignedShort6 > 0; unsignedShort6--) {
                int annotationTarget = readAnnotationTarget(context, annotationValues3);
                annotationValues3 = readAnnotationValues(annotationTarget + 2, cArr, true, fieldVisitorVisitField.visitTypeAnnotation(context.typeRef, context.typePath, readUTF8(annotationTarget, cArr), true));
            }
        }
        if (i14 != 0) {
            int annotationValues4 = i14 + 2;
            for (int unsignedShort7 = readUnsignedShort(i14); unsignedShort7 > 0; unsignedShort7--) {
                int annotationTarget2 = readAnnotationTarget(context, annotationValues4);
                annotationValues4 = readAnnotationValues(annotationTarget2 + 2, cArr, true, fieldVisitorVisitField.visitTypeAnnotation(context.typeRef, context.typePath, readUTF8(annotationTarget2, cArr), false));
            }
        }
        while (attribute3 != null) {
            Attribute attribute4 = attribute3.next;
            attribute3.next = null;
            fieldVisitorVisitField.visitAttribute(attribute3);
            attribute3 = attribute4;
        }
        fieldVisitorVisitField.visitEnd();
        return i18;
    }

    private int readMethod(ClassVisitor classVisitor, Context context, int i) {
        boolean z;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        String[] strArr;
        String str;
        char[] cArr = context.buffer;
        context.access = readUnsignedShort(i);
        context.name = readUTF8(i + 2, cArr);
        context.desc = readUTF8(i + 4, cArr);
        int i9 = i + 6;
        int i10 = i9;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        String[] strArr2 = null;
        String utf8 = null;
        Attribute attribute = null;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        int i20 = 0;
        for (int unsignedShort = readUnsignedShort(i9); unsignedShort > 0; unsignedShort--) {
            String utf82 = readUTF8(i10 + 2, cArr);
            int i21 = i11;
            if ("Code".equals(utf82)) {
                if ((context.flags & 1) == 0) {
                    i20 = i10 + 8;
                    i11 = i21;
                } else {
                    i4 = i12;
                    i5 = i13;
                    i6 = i14;
                    i7 = i15;
                    i8 = i16;
                    str = utf8;
                    i3 = i21;
                    strArr = strArr2;
                    i14 = i6;
                    strArr2 = strArr;
                    utf8 = str;
                    i11 = i3;
                    i12 = i4;
                    i13 = i5;
                    i15 = i7;
                    i16 = i8;
                }
            } else {
                if ("Exceptions".equals(utf82)) {
                    int unsignedShort2 = readUnsignedShort(i10 + 8);
                    strArr2 = new String[unsignedShort2];
                    i2 = i12;
                    int i22 = i10 + 10;
                    for (int i23 = 0; i23 < unsignedShort2; i23++) {
                        strArr2[i23] = readClass(i22, cArr);
                        i22 += 2;
                    }
                    i18 = i22;
                } else {
                    i2 = i12;
                    if ("Signature".equals(utf82)) {
                        utf8 = readUTF8(i10 + 8, cArr);
                    } else {
                        if ("Deprecated".equals(utf82)) {
                            context.access |= 131072;
                        } else if ("RuntimeVisibleAnnotations".equals(utf82)) {
                            i16 = i10 + 8;
                        } else if ("RuntimeVisibleTypeAnnotations".equals(utf82)) {
                            i13 = i10 + 8;
                        } else if ("AnnotationDefault".equals(utf82)) {
                            i14 = i10 + 8;
                        } else if (AttSynthetic.ATTRIBUTE_NAME.equals(utf82)) {
                            context.access |= 266240;
                        } else if ("RuntimeInvisibleAnnotations".equals(utf82)) {
                            i15 = i10 + 8;
                        } else if ("RuntimeInvisibleTypeAnnotations".equals(utf82)) {
                            i12 = i10 + 8;
                            i11 = i21;
                        } else if ("RuntimeVisibleParameterAnnotations".equals(utf82)) {
                            i19 = i10 + 8;
                        } else if ("RuntimeInvisibleParameterAnnotations".equals(utf82)) {
                            i11 = i10 + 8;
                            i12 = i2;
                        } else if ("MethodParameters".equals(utf82)) {
                            i17 = i10 + 8;
                        } else {
                            i3 = i21;
                            i4 = i2;
                            i5 = i13;
                            i6 = i14;
                            i7 = i15;
                            i8 = i16;
                            strArr = strArr2;
                            str = utf8;
                            Attribute attribute2 = readAttribute(context.attrs, utf82, i10 + 8, readInt(i10 + 4), cArr, -1, null);
                            if (attribute2 != null) {
                                attribute2.next = attribute;
                                attribute = attribute2;
                            }
                            i14 = i6;
                            strArr2 = strArr;
                            utf8 = str;
                            i11 = i3;
                            i12 = i4;
                            i13 = i5;
                            i15 = i7;
                            i16 = i8;
                        }
                        i5 = i13;
                        i6 = i14;
                        i7 = i15;
                        i8 = i16;
                        str = utf8;
                        i4 = i2;
                        i3 = i21;
                        strArr = strArr2;
                        i14 = i6;
                        strArr2 = strArr;
                        utf8 = str;
                        i11 = i3;
                        i12 = i4;
                        i13 = i5;
                        i15 = i7;
                        i16 = i8;
                    }
                }
                i12 = i2;
                i11 = i21;
            }
            i10 += readInt(i10 + 4) + 6;
        }
        int i24 = i11;
        int i25 = i12;
        int i26 = i13;
        int i27 = i14;
        int i28 = i15;
        int i29 = i16;
        String[] strArr3 = strArr2;
        String str2 = utf8;
        int i30 = i10 + 2;
        MethodVisitor methodVisitorVisitMethod = classVisitor.visitMethod(context.access, context.name, context.desc, str2, strArr3);
        if (methodVisitorVisitMethod == null) {
            return i30;
        }
        if (methodVisitorVisitMethod instanceof MethodWriter) {
            MethodWriter methodWriter = (MethodWriter) methodVisitorVisitMethod;
            if (methodWriter.cw.cr == this && str2 == methodWriter.signature) {
                if (strArr3 == null) {
                    if (methodWriter.exceptionCount != 0) {
                        z = false;
                        break;
                    }
                    z = true;
                } else {
                    if (strArr3.length == methodWriter.exceptionCount) {
                        int length = strArr3.length - 1;
                        while (length >= 0) {
                            int i31 = i18 - 2;
                            if (methodWriter.exceptions[length] == readUnsignedShort(i31)) {
                                length--;
                                i18 = i31;
                            }
                        }
                        z = true;
                    }
                    z = false;
                    break;
                }
                if (z) {
                    methodWriter.classReaderOffset = i9;
                    methodWriter.classReaderLength = i30 - i9;
                    return i30;
                }
            }
        }
        if (i17 != 0) {
            int i32 = this.b[i17] & 255;
            int i33 = i17 + 1;
            while (i32 > 0) {
                methodVisitorVisitMethod.visitParameter(readUTF8(i33, cArr), readUnsignedShort(i33 + 2));
                i32--;
                i33 += 4;
            }
        }
        if (i27 != 0) {
            AnnotationVisitor annotationVisitorVisitAnnotationDefault = methodVisitorVisitMethod.visitAnnotationDefault();
            readAnnotationValue(i27, cArr, null, annotationVisitorVisitAnnotationDefault);
            if (annotationVisitorVisitAnnotationDefault != null) {
                annotationVisitorVisitAnnotationDefault.visitEnd();
            }
        }
        if (i29 != 0) {
            int annotationValues = i29 + 2;
            for (int unsignedShort3 = readUnsignedShort(i29); unsignedShort3 > 0; unsignedShort3--) {
                annotationValues = readAnnotationValues(annotationValues + 2, cArr, true, methodVisitorVisitMethod.visitAnnotation(readUTF8(annotationValues, cArr), true));
            }
        }
        if (i28 != 0) {
            int unsignedShort4 = readUnsignedShort(i28);
            int annotationValues2 = i28 + 2;
            while (unsignedShort4 > 0) {
                unsignedShort4--;
                annotationValues2 = readAnnotationValues(annotationValues2 + 2, cArr, true, methodVisitorVisitMethod.visitAnnotation(readUTF8(annotationValues2, cArr), false));
            }
        }
        if (i26 != 0) {
            int annotationValues3 = i26 + 2;
            for (int unsignedShort5 = readUnsignedShort(i26); unsignedShort5 > 0; unsignedShort5--) {
                int annotationTarget = readAnnotationTarget(context, annotationValues3);
                annotationValues3 = readAnnotationValues(annotationTarget + 2, cArr, true, methodVisitorVisitMethod.visitTypeAnnotation(context.typeRef, context.typePath, readUTF8(annotationTarget, cArr), true));
            }
        }
        if (i25 != 0) {
            int annotationValues4 = i25 + 2;
            for (int unsignedShort6 = readUnsignedShort(i25); unsignedShort6 > 0; unsignedShort6--) {
                int annotationTarget2 = readAnnotationTarget(context, annotationValues4);
                annotationValues4 = readAnnotationValues(annotationTarget2 + 2, cArr, true, methodVisitorVisitMethod.visitTypeAnnotation(context.typeRef, context.typePath, readUTF8(annotationTarget2, cArr), false));
            }
        }
        int i34 = i19;
        if (i34 != 0) {
            readParameterAnnotations(methodVisitorVisitMethod, context, i34, true);
        }
        if (i24 != 0) {
            readParameterAnnotations(methodVisitorVisitMethod, context, i24, false);
        }
        while (attribute != null) {
            Attribute attribute3 = attribute.next;
            attribute.next = null;
            methodVisitorVisitMethod.visitAttribute(attribute);
            attribute = attribute3;
        }
        int i35 = i20;
        if (i35 != 0) {
            methodVisitorVisitMethod.visitCode();
            readCode(methodVisitorVisitMethod, context, i35);
        }
        methodVisitorVisitMethod.visitEnd();
        return i30;
    }

    /* JADX WARN: Failed to analyze thrown exceptions
    java.util.ConcurrentModificationException
    	at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1095)
    	at java.base/java.util.ArrayList$Itr.next(ArrayList.java:1049)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:117)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.checkInsn(MethodThrowsVisitor.java:178)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:131)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.checkInsn(MethodThrowsVisitor.java:178)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:131)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.checkInsn(MethodThrowsVisitor.java:178)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:131)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.checkInsn(MethodThrowsVisitor.java:178)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:131)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
     */
    private void readCode(MethodVisitor methodVisitor, Context context, int i) {
        Label[] labelArr;
        boolean z;
        Context context2;
        int[] iArr;
        char[] cArr;
        int[] iArr2;
        Context context3;
        MethodVisitor methodVisitor2;
        int i2;
        int i3;
        char[] cArr2;
        int i4;
        int[] iArr3;
        String utf8;
        MethodVisitor methodVisitor3;
        boolean z2;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        boolean z3;
        int i10;
        boolean z4;
        boolean z5;
        Context context4;
        int i11;
        int i12;
        int i13;
        int i14;
        int unsignedShort;
        boolean z6;
        int[] iArr4;
        boolean z7;
        boolean z8;
        int i15;
        int i16;
        int i17;
        boolean z9;
        boolean z10;
        int i18;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        int i24;
        boolean z11;
        int i25;
        int i26;
        Label[] labelArr2;
        int unsignedShort2;
        int[] iArr5;
        Label[] labelArr3;
        int i27;
        boolean z12;
        int[] iArr6;
        String str;
        int i28;
        Label[] labelArr4;
        int i29;
        boolean z13;
        int[] iArr7;
        MethodVisitor methodVisitor4 = methodVisitor;
        Context context5 = context;
        byte[] bArr = this.b;
        char[] cArr3 = context5.buffer;
        int unsignedShort3 = readUnsignedShort(i);
        int unsignedShort4 = readUnsignedShort(i + 2);
        int i30 = readInt(i + 4);
        int i31 = i + 8;
        int i32 = i31 + i30;
        Label[] labelArr5 = new Label[i30 + 2];
        context5.labels = labelArr5;
        createLabel(i30 + 1, labelArr5);
        int i33 = i31;
        while (i33 < i32) {
            int i34 = i33 - i31;
            switch (ClassWriter.TYPE[bArr[i33] & 255]) {
                case 0:
                case 4:
                    i33++;
                    break;
                case 1:
                case 3:
                case 11:
                    i33 += 2;
                    break;
                case 2:
                case 5:
                case 6:
                case 12:
                case 13:
                    i33 += 3;
                    break;
                case 7:
                case 8:
                    i33 += 5;
                    break;
                case 9:
                    createLabel(i34 + readShort(i33 + 1), labelArr5);
                    i33 += 3;
                    break;
                case 10:
                case 19:
                    createLabel(i34 + readInt(i33 + 1), labelArr5);
                    i33 += 5;
                    break;
                case 14:
                    int i35 = (i33 + 4) - (i34 & 3);
                    createLabel(readInt(i35) + i34, labelArr5);
                    for (int i36 = (readInt(i35 + 8) - readInt(i35 + 4)) + 1; i36 > 0; i36--) {
                        createLabel(readInt(i35 + 12) + i34, labelArr5);
                        i35 += 4;
                    }
                    i33 = i35 + 12;
                    break;
                case 15:
                    int i37 = (i33 + 4) - (i34 & 3);
                    createLabel(readInt(i37) + i34, labelArr5);
                    for (int i38 = readInt(i37 + 4); i38 > 0; i38--) {
                        createLabel(readInt(i37 + 12) + i34, labelArr5);
                        i37 += 8;
                    }
                    i33 = i37 + 8;
                    break;
                case 16:
                default:
                    break;
                case 17:
                    i33 = (bArr[i33 + 1] & 255) == 132 ? i33 + 6 : i33 + 4;
                    break;
                case 18:
                    createLabel(i34 + readUnsignedShort(i33 + 1), labelArr5);
                    i33 += 3;
                    break;
            }
        }
        for (int unsignedShort5 = readUnsignedShort(i33); unsignedShort5 > 0; unsignedShort5--) {
            Label labelCreateLabel = createLabel(readUnsignedShort(i33 + 2), labelArr5);
            Label labelCreateLabel2 = createLabel(readUnsignedShort(i33 + 4), labelArr5);
            Label labelCreateLabel3 = createLabel(readUnsignedShort(i33 + 6), labelArr5);
            i33 += 8;
            methodVisitor4.visitTryCatchBlock(labelCreateLabel, labelCreateLabel2, labelCreateLabel3, readUTF8(this.items[readUnsignedShort(i33)], cArr3));
        }
        int i39 = i33 + 2;
        boolean z14 = (context5.flags & 8) != 0;
        int i40 = i39;
        int unsignedShort6 = readUnsignedShort(i39);
        boolean z15 = z14;
        int[] typeAnnotations = null;
        int[] iArr8 = null;
        int i41 = 0;
        int i42 = 0;
        Attribute attribute = null;
        int i43 = 0;
        int i44 = 0;
        boolean z16 = true;
        int unsignedShort7 = 0;
        int unsignedShort8 = -1;
        int unsignedShort9 = -1;
        while (unsignedShort6 > 0) {
            String utf82 = readUTF8(i40 + 2, cArr3);
            if ("LocalVariableTable".equals(utf82)) {
                if ((context5.flags & 2) == 0) {
                    int i45 = i40 + 8;
                    int unsignedShort10 = readUnsignedShort(i45);
                    int i46 = i40;
                    while (unsignedShort10 > 0) {
                        int i47 = i46 + 10;
                        int[] iArr9 = typeAnnotations;
                        int unsignedShort11 = readUnsignedShort(i47);
                        createDebugLabel(unsignedShort11, labelArr5);
                        createDebugLabel(unsignedShort11 + readUnsignedShort(i46 + 12), labelArr5);
                        unsignedShort10--;
                        i46 = i47;
                        typeAnnotations = iArr9;
                    }
                    i43 = i45;
                    labelArr3 = labelArr5;
                    i27 = i32;
                    z12 = z15;
                } else {
                    iArr5 = typeAnnotations;
                    labelArr3 = labelArr5;
                    i27 = i32;
                    z12 = z15;
                    typeAnnotations = iArr5;
                }
            } else {
                iArr5 = typeAnnotations;
                if ("LocalVariableTypeTable".equals(utf82)) {
                    i44 = i40 + 8;
                } else if ("LineNumberTable".equals(utf82)) {
                    if ((context5.flags & 2) == 0) {
                        int i48 = i40;
                        for (int unsignedShort12 = readUnsignedShort(i40 + 8); unsignedShort12 > 0; unsignedShort12--) {
                            int unsignedShort13 = readUnsignedShort(i48 + 10);
                            createDebugLabel(unsignedShort13, labelArr5);
                            Label label = labelArr5[unsignedShort13];
                            while (label.line > 0) {
                                if (label.next == null) {
                                    label.next = new Label();
                                }
                                label = label.next;
                            }
                            label.line = readUnsignedShort(i48 + 12);
                            i48 += 4;
                        }
                    }
                } else if ("RuntimeVisibleTypeAnnotations".equals(utf82)) {
                    int[] typeAnnotations2 = readTypeAnnotations(methodVisitor4, context5, i40 + 8, true);
                    unsignedShort8 = (typeAnnotations2.length == 0 || readByte(typeAnnotations2[0]) < 67) ? -1 : readUnsignedShort(typeAnnotations2[0] + 1);
                    labelArr3 = labelArr5;
                    i27 = i32;
                    z12 = z15;
                    iArr8 = typeAnnotations2;
                    typeAnnotations = iArr5;
                } else if ("RuntimeInvisibleTypeAnnotations".equals(utf82)) {
                    typeAnnotations = readTypeAnnotations(methodVisitor4, context5, i40 + 8, false);
                    unsignedShort9 = (typeAnnotations.length == 0 || readByte(typeAnnotations[0]) < 67) ? -1 : readUnsignedShort(typeAnnotations[0] + 1);
                    labelArr3 = labelArr5;
                    i27 = i32;
                    z12 = z15;
                } else if ("StackMapTable".equals(utf82)) {
                    if ((context5.flags & 4) == 0) {
                        i41 = i40 + 10;
                        i42 = readInt(i40 + 4);
                        unsignedShort7 = readUnsignedShort(i40 + 8);
                    }
                } else if ("StackMap".equals(utf82)) {
                    if ((context5.flags & 4) == 0) {
                        i41 = i40 + 10;
                        i42 = readInt(i40 + 4);
                        unsignedShort7 = readUnsignedShort(i40 + 8);
                        labelArr3 = labelArr5;
                        i27 = i32;
                        z12 = z15;
                        typeAnnotations = iArr5;
                        z16 = false;
                    }
                } else {
                    Attribute attribute2 = attribute;
                    int i49 = 0;
                    while (i49 < context5.attrs.length) {
                        if (context5.attrs[i49].type.equals(utf82)) {
                            iArr7 = iArr5;
                            iArr6 = iArr8;
                            str = utf82;
                            i28 = i49;
                            z13 = z15;
                            labelArr4 = labelArr5;
                            i29 = i32;
                            Attribute attribute3 = context5.attrs[i49].read(this, i40 + 8, readInt(i40 + 4), cArr3, i31 - 8, labelArr4);
                            if (attribute3 != null) {
                                attribute3.next = attribute2;
                                attribute2 = attribute3;
                            }
                        } else {
                            iArr6 = iArr8;
                            str = utf82;
                            i28 = i49;
                            labelArr4 = labelArr5;
                            i29 = i32;
                            z13 = z15;
                            iArr7 = iArr5;
                        }
                        i49 = i28 + 1;
                        labelArr5 = labelArr4;
                        utf82 = str;
                        iArr5 = iArr7;
                        iArr8 = iArr6;
                        z15 = z13;
                        i32 = i29;
                    }
                    labelArr3 = labelArr5;
                    i27 = i32;
                    z12 = z15;
                    attribute = attribute2;
                    typeAnnotations = iArr5;
                }
                labelArr3 = labelArr5;
                i27 = i32;
                z12 = z15;
                typeAnnotations = iArr5;
            }
            i40 += readInt(i40 + 4) + 6;
            unsignedShort6--;
            methodVisitor4 = methodVisitor;
            labelArr5 = labelArr3;
            z15 = z12;
            i32 = i27;
        }
        int[] iArr10 = typeAnnotations;
        int[] iArr11 = iArr8;
        Label[] labelArr6 = labelArr5;
        int i50 = i32;
        boolean z17 = z15;
        if (i41 != 0) {
            context5.offset = -1;
            context5.mode = 0;
            context5.localCount = 0;
            context5.localDiff = 0;
            context5.stackCount = 0;
            context5.local = new Object[unsignedShort4];
            context5.stack = new Object[unsignedShort3];
            z = z17;
            if (z) {
                getImplicitFrame(context5);
            }
            int i51 = i41;
            while (i51 < (i41 + i42) - 2) {
                if (bArr[i51] != 8 || (unsignedShort2 = readUnsignedShort(i51 + 1)) < 0 || unsignedShort2 >= i30 || (bArr[i31 + unsignedShort2] & 255) != 187) {
                    labelArr2 = labelArr6;
                } else {
                    labelArr2 = labelArr6;
                    createLabel(unsignedShort2, labelArr2);
                }
                i51++;
                labelArr6 = labelArr2;
            }
            labelArr = labelArr6;
            context2 = context5;
        } else {
            labelArr = labelArr6;
            z = z17;
            context2 = null;
        }
        if ((context5.flags & 256) != 0 && (context5.flags & 8) != 0) {
            methodVisitor.visitFrame(-1, unsignedShort4, null, 0, null);
        }
        int i52 = (context5.flags & 256) == 0 ? -33 : 0;
        int i53 = i31;
        Context context6 = context2;
        boolean z18 = false;
        int i54 = 0;
        int i55 = 0;
        while (true) {
            int i56 = i50;
            if (i53 < i56) {
                int i57 = i53 - i31;
                Label label2 = labelArr[i57];
                if (label2 != null) {
                    Label label3 = label2.next;
                    label2.next = null;
                    methodVisitor3 = methodVisitor;
                    i5 = unsignedShort4;
                    methodVisitor3.visitLabel(label2);
                    if ((context5.flags & 2) == 0 && label2.line > 0) {
                        methodVisitor3.visitLineNumber(label2.line, label2);
                        Label label4 = label3;
                        while (label4 != null) {
                            methodVisitor3.visitLineNumber(label4.line, label2);
                            label4 = label4.next;
                            z18 = z18;
                        }
                    }
                    z2 = z18;
                } else {
                    methodVisitor3 = methodVisitor;
                    z2 = z18;
                    i5 = unsignedShort4;
                }
                boolean z19 = z2;
                Context context7 = context6;
                int frame = i41;
                while (true) {
                    if (context7 != null) {
                        if (context7.offset != i57) {
                            i19 = i57;
                            i20 = -1;
                            if (context7.offset != -1) {
                                i6 = frame;
                                i7 = i56;
                                i8 = i30;
                                i9 = unsignedShort3;
                                z3 = z16;
                                i10 = i19;
                            }
                        } else {
                            i19 = i57;
                            i20 = -1;
                        }
                        if (context7.offset != i20) {
                            boolean z20 = z16;
                            if (!z20 || z) {
                                i21 = frame;
                                i22 = i56;
                                i26 = i53;
                                i23 = i30;
                                i24 = unsignedShort3;
                                i25 = i19;
                                z11 = z20;
                                methodVisitor.visitFrame(-1, context7.localCount, context7.local, context7.stackCount, context7.stack);
                            } else {
                                i26 = i53;
                                i23 = i30;
                                i24 = unsignedShort3;
                                i25 = i19;
                                z11 = z20;
                                i21 = frame;
                                i22 = i56;
                                methodVisitor.visitFrame(context7.mode, context7.localDiff, context7.local, context7.stackCount, context7.stack);
                            }
                            z19 = false;
                        } else {
                            i21 = frame;
                            i22 = i56;
                            i23 = i30;
                            i24 = unsignedShort3;
                            z11 = z16;
                            i25 = i19;
                            i26 = i53;
                        }
                        if (unsignedShort7 > 0) {
                            frame = readFrame(i21, z11, z, context7);
                            unsignedShort7--;
                            i57 = i25;
                            i56 = i22;
                            i53 = i26;
                            unsignedShort3 = i24;
                        } else {
                            i57 = i25;
                            frame = i21;
                            i56 = i22;
                            i53 = i26;
                            unsignedShort3 = i24;
                            context7 = null;
                        }
                        z16 = z11;
                        i30 = i23;
                    } else {
                        i6 = frame;
                        i7 = i56;
                        i8 = i30;
                        i9 = unsignedShort3;
                        z3 = z16;
                        i10 = i57;
                    }
                }
                int i58 = i53;
                if (z19) {
                    methodVisitor.visitFrame(256, 0, null, 0, null);
                    z4 = false;
                } else {
                    z4 = z19;
                }
                int i59 = bArr[i58] & 255;
                switch (ClassWriter.TYPE[i59]) {
                    case 0:
                        z5 = z3;
                        context4 = context7;
                        i11 = i6;
                        context5 = context;
                        methodVisitor3.visitInsn(i59);
                        i12 = i58 + 1;
                        i14 = i54;
                        unsignedShort = unsignedShort8;
                        z6 = z4;
                        iArr4 = iArr11;
                        break;
                    case 1:
                        z5 = z3;
                        context4 = context7;
                        i11 = i6;
                        context5 = context;
                        methodVisitor3.visitIntInsn(i59, bArr[i58 + 1]);
                        i12 = i58 + 2;
                        i14 = i54;
                        unsignedShort = unsignedShort8;
                        z6 = z4;
                        iArr4 = iArr11;
                        break;
                    case 2:
                        z5 = z3;
                        context4 = context7;
                        i11 = i6;
                        context5 = context;
                        methodVisitor3.visitIntInsn(i59, readShort(i58 + 1));
                        i14 = i54;
                        unsignedShort = unsignedShort8;
                        z6 = z4;
                        iArr4 = iArr11;
                        break;
                    case 3:
                        z5 = z3;
                        context4 = context7;
                        i11 = i6;
                        context5 = context;
                        methodVisitor3.visitVarInsn(i59, bArr[i58 + 1] & 255);
                        i12 = i58 + 2;
                        i14 = i54;
                        unsignedShort = unsignedShort8;
                        z6 = z4;
                        iArr4 = iArr11;
                        break;
                    case 4:
                        z5 = z3;
                        context4 = context7;
                        i11 = i6;
                        context5 = context;
                        if (i59 > 54) {
                            int i60 = i59 - 59;
                            methodVisitor3.visitVarInsn((i60 >> 2) + 54, i60 & 3);
                        } else {
                            int i61 = i59 - 26;
                            methodVisitor3.visitVarInsn((i61 >> 2) + 21, i61 & 3);
                        }
                        i12 = i58 + 1;
                        i14 = i54;
                        unsignedShort = unsignedShort8;
                        z6 = z4;
                        iArr4 = iArr11;
                        break;
                    case 5:
                        z5 = z3;
                        context4 = context7;
                        i11 = i6;
                        context5 = context;
                        methodVisitor3.visitTypeInsn(i59, readClass(i58 + 1, cArr3));
                        i14 = i54;
                        unsignedShort = unsignedShort8;
                        z6 = z4;
                        iArr4 = iArr11;
                        break;
                    case 6:
                    case 7:
                        z5 = z3;
                        i11 = i6;
                        context5 = context;
                        int i62 = this.items[readUnsignedShort(i58 + 1)];
                        boolean z21 = bArr[i62 + (-1)] == 11;
                        String str2 = readClass(i62, cArr3);
                        int i63 = this.items[readUnsignedShort(i62 + 2)];
                        String utf83 = readUTF8(i63, cArr3);
                        String utf84 = readUTF8(i63 + 2, cArr3);
                        if (i59 < 182) {
                            methodVisitor3.visitFieldInsn(i59, str2, utf83, utf84);
                            context4 = context7;
                            i13 = i59;
                        } else {
                            context4 = context7;
                            i13 = i59;
                            methodVisitor.visitMethodInsn(i59, str2, utf83, utf84, z21);
                        }
                        i12 = i13 == 185 ? i58 + 5 : i58 + 3;
                        i14 = i54;
                        unsignedShort = unsignedShort8;
                        z6 = z4;
                        iArr4 = iArr11;
                        break;
                    case 8:
                        z5 = z3;
                        int i64 = this.items[readUnsignedShort(i58 + 1)];
                        i11 = i6;
                        context5 = context;
                        int i65 = context5.bootstrapMethods[readUnsignedShort(i64)];
                        Handle handle = (Handle) readConst(readUnsignedShort(i65), cArr3);
                        int unsignedShort14 = readUnsignedShort(i65 + 2);
                        Object[] objArr = new Object[unsignedShort14];
                        int i66 = i65 + 4;
                        int i67 = 0;
                        while (i67 < unsignedShort14) {
                            objArr[i67] = readConst(readUnsignedShort(i66), cArr3);
                            i66 += 2;
                            i67++;
                            unsignedShort14 = unsignedShort14;
                        }
                        int i68 = this.items[readUnsignedShort(i64 + 2)];
                        methodVisitor3.visitInvokeDynamicInsn(readUTF8(i68, cArr3), readUTF8(i68 + 2, cArr3), handle, objArr);
                        i12 = i58 + 5;
                        context4 = context7;
                        i14 = i54;
                        unsignedShort = unsignedShort8;
                        z6 = z4;
                        iArr4 = iArr11;
                        break;
                    case 9:
                        z5 = z3;
                        methodVisitor3.visitJumpInsn(i59, labelArr[i10 + readShort(i58 + 1)]);
                        i12 = i58 + 3;
                        context4 = context7;
                        i11 = i6;
                        i14 = i54;
                        unsignedShort = unsignedShort8;
                        z6 = z4;
                        iArr4 = iArr11;
                        context5 = context;
                        break;
                    case 10:
                        z5 = z3;
                        methodVisitor3.visitJumpInsn(i59 + i52, labelArr[i10 + readInt(i58 + 1)]);
                        i12 = i58 + 5;
                        context4 = context7;
                        i11 = i6;
                        i14 = i54;
                        unsignedShort = unsignedShort8;
                        z6 = z4;
                        iArr4 = iArr11;
                        context5 = context;
                        break;
                    case 11:
                        z5 = z3;
                        methodVisitor3.visitLdcInsn(readConst(bArr[i58 + 1] & 255, cArr3));
                        i12 = i58 + 2;
                        context4 = context7;
                        i11 = i6;
                        i14 = i54;
                        unsignedShort = unsignedShort8;
                        z6 = z4;
                        iArr4 = iArr11;
                        context5 = context;
                        break;
                    case 12:
                        z5 = z3;
                        methodVisitor3.visitLdcInsn(readConst(readUnsignedShort(i58 + 1), cArr3));
                        i12 = i58 + 3;
                        context4 = context7;
                        i11 = i6;
                        i14 = i54;
                        unsignedShort = unsignedShort8;
                        z6 = z4;
                        iArr4 = iArr11;
                        context5 = context;
                        break;
                    case 13:
                        z5 = z3;
                        methodVisitor3.visitIincInsn(bArr[i58 + 1] & 255, bArr[i58 + 2]);
                        i12 = i58 + 3;
                        context4 = context7;
                        i11 = i6;
                        i14 = i54;
                        unsignedShort = unsignedShort8;
                        z6 = z4;
                        iArr4 = iArr11;
                        context5 = context;
                        break;
                    case 14:
                        int i69 = (i58 + 4) - (i10 & 3);
                        int i70 = i10 + readInt(i69);
                        int i71 = readInt(i69 + 4);
                        int i72 = readInt(i69 + 8);
                        int i73 = (i72 - i71) + 1;
                        Label[] labelArr7 = new Label[i73];
                        z5 = z3;
                        int i74 = i69 + 12;
                        for (int i75 = 0; i75 < i73; i75++) {
                            labelArr7[i75] = labelArr[i10 + readInt(i74)];
                            i74 += 4;
                        }
                        methodVisitor3.visitTableSwitchInsn(i71, i72, labelArr[i70], labelArr7);
                        i12 = i74;
                        context4 = context7;
                        i11 = i6;
                        i14 = i54;
                        unsignedShort = unsignedShort8;
                        z6 = z4;
                        iArr4 = iArr11;
                        context5 = context;
                        break;
                    case 15:
                        int i76 = (i58 + 4) - (i10 & 3);
                        int i77 = i10 + readInt(i76);
                        int i78 = readInt(i76 + 4);
                        int[] iArr12 = new int[i78];
                        Label[] labelArr8 = new Label[i78];
                        int i79 = i76 + 8;
                        int i80 = 0;
                        while (i80 < i78) {
                            iArr12[i80] = readInt(i79);
                            labelArr8[i80] = labelArr[readInt(i79 + 4) + i10];
                            i79 += 8;
                            i80++;
                            i78 = i78;
                        }
                        methodVisitor3.visitLookupSwitchInsn(labelArr[i77], iArr12, labelArr8);
                        i12 = i79;
                        z5 = z3;
                        context4 = context7;
                        i11 = i6;
                        i14 = i54;
                        unsignedShort = unsignedShort8;
                        z6 = z4;
                        iArr4 = iArr11;
                        context5 = context;
                        break;
                    case 16:
                    default:
                        z5 = z3;
                        context4 = context7;
                        i11 = i6;
                        context5 = context;
                        methodVisitor3.visitMultiANewArrayInsn(readClass(i58 + 1, cArr3), bArr[i58 + 3] & 255);
                        i12 = i58 + 4;
                        i14 = i54;
                        unsignedShort = unsignedShort8;
                        z6 = z4;
                        iArr4 = iArr11;
                        break;
                    case 17:
                        int i81 = bArr[i58 + 1] & 255;
                        if (i81 == 132) {
                            methodVisitor3.visitIincInsn(readUnsignedShort(i58 + 2), readShort(i58 + 4));
                            i12 = i58 + 6;
                        } else {
                            methodVisitor3.visitVarInsn(i81, readUnsignedShort(i58 + 2));
                            i12 = i58 + 4;
                        }
                        z5 = z3;
                        context4 = context7;
                        i11 = i6;
                        i14 = i54;
                        unsignedShort = unsignedShort8;
                        z6 = z4;
                        iArr4 = iArr11;
                        context5 = context;
                        break;
                    case 18:
                        int i82 = i59 < 218 ? i59 - 49 : i59 - 20;
                        Label label5 = labelArr[readUnsignedShort(i58 + 1) + i10];
                        if (i82 == 167 || i82 == 168) {
                            methodVisitor3.visitJumpInsn(i82 + 33, label5);
                            z7 = z4;
                        } else {
                            if (i82 <= 166) {
                                z8 = true;
                                i15 = ((i82 + 1) ^ 1) - 1;
                            } else {
                                z8 = true;
                                i15 = i82 ^ 1;
                            }
                            methodVisitor3.visitJumpInsn(i15, createLabel(i10 + 3, labelArr));
                            methodVisitor3.visitJumpInsn(200, label5);
                            z7 = z8;
                        }
                        i12 = i58 + 3;
                        z6 = z7;
                        z5 = z3;
                        context4 = context7;
                        i11 = i6;
                        i14 = i54;
                        unsignedShort = unsignedShort8;
                        iArr4 = iArr11;
                        context5 = context;
                        break;
                    case 19:
                        methodVisitor3.visitJumpInsn(200, labelArr[readInt(i58 + 1) + i10]);
                        i12 = i58 + 5;
                        z5 = z3;
                        context4 = context7;
                        i11 = i6;
                        i14 = i54;
                        unsignedShort = unsignedShort8;
                        iArr4 = iArr11;
                        z6 = true;
                        context5 = context;
                        break;
                }
                while (iArr4 != null && i14 < iArr4.length && unsignedShort <= i10) {
                    if (unsignedShort == i10) {
                        int annotationTarget = readAnnotationTarget(context5, iArr4[i14]);
                        z10 = z6;
                        i18 = i12;
                        readAnnotationValues(annotationTarget + 2, cArr3, true, methodVisitor3.visitInsnAnnotation(context5.typeRef, context5.typePath, readUTF8(annotationTarget, cArr3), true));
                    } else {
                        z10 = z6;
                        i18 = i12;
                    }
                    i14++;
                    unsignedShort = (i14 >= iArr4.length || readByte(iArr4[i14]) < 67) ? -1 : readUnsignedShort(iArr4[i14] + 1);
                    z6 = z10;
                    i12 = i18;
                }
                boolean z22 = z6;
                int i83 = i12;
                int i84 = i55;
                int unsignedShort15 = unsignedShort9;
                int[] iArr13 = iArr10;
                while (iArr13 != null && i84 < iArr13.length && unsignedShort15 <= i10) {
                    if (unsignedShort15 == i10) {
                        int annotationTarget2 = readAnnotationTarget(context5, iArr13[i84]);
                        i16 = i14;
                        i17 = unsignedShort;
                        z9 = z;
                        readAnnotationValues(annotationTarget2 + 2, cArr3, true, methodVisitor3.visitInsnAnnotation(context5.typeRef, context5.typePath, readUTF8(annotationTarget2, cArr3), false));
                    } else {
                        i16 = i14;
                        i17 = unsignedShort;
                        z9 = z;
                    }
                    i84++;
                    unsignedShort15 = (i84 >= iArr13.length || readByte(iArr13[i84]) < 67) ? -1 : readUnsignedShort(iArr13[i84] + 1);
                    i14 = i16;
                    unsignedShort = i17;
                    z = z9;
                }
                unsignedShort8 = unsignedShort;
                unsignedShort9 = unsignedShort15;
                iArr10 = iArr13;
                iArr11 = iArr4;
                i50 = i7;
                i53 = i83;
                z = z;
                unsignedShort4 = i5;
                unsignedShort3 = i9;
                z16 = z5;
                context6 = context4;
                i41 = i11;
                i30 = i8;
                i55 = i84;
                z18 = z22;
                i54 = i14;
            } else {
                MethodVisitor methodVisitor5 = methodVisitor;
                int i85 = unsignedShort4;
                int i86 = unsignedShort3;
                int[] iArr14 = iArr10;
                int[] iArr15 = iArr11;
                int i87 = 0;
                Label label6 = labelArr[i30];
                if (label6 != null) {
                    methodVisitor5.visitLabel(label6);
                }
                if ((context5.flags & 2) == 0 && (i4 = i43) != 0) {
                    int i88 = i44;
                    if (i88 != 0) {
                        int i89 = i88 + 2;
                        int unsignedShort16 = readUnsignedShort(i88) * 3;
                        int[] iArr16 = new int[unsignedShort16];
                        int i90 = i89;
                        while (unsignedShort16 > 0) {
                            int i91 = unsignedShort16 - 1;
                            iArr16[i91] = i90 + 6;
                            int i92 = i91 - 1;
                            iArr16[i92] = readUnsignedShort(i90 + 8);
                            unsignedShort16 = i92 - 1;
                            iArr16[unsignedShort16] = readUnsignedShort(i90);
                            i90 += 10;
                        }
                        iArr3 = iArr16;
                    } else {
                        iArr3 = null;
                    }
                    int unsignedShort17 = readUnsignedShort(i4);
                    int i93 = i4 + 2;
                    while (unsignedShort17 > 0) {
                        int unsignedShort18 = readUnsignedShort(i93);
                        int unsignedShort19 = readUnsignedShort(i93 + 2);
                        int unsignedShort20 = readUnsignedShort(i93 + 8);
                        if (iArr3 != null) {
                            for (int i94 = i87; i94 < iArr3.length; i94 += 3) {
                                if (iArr3[i94] == unsignedShort18 && iArr3[i94 + 1] == unsignedShort20) {
                                    utf8 = readUTF8(iArr3[i94 + 2], cArr3);
                                }
                            }
                            utf8 = null;
                        } else {
                            utf8 = null;
                        }
                        methodVisitor.visitLocalVariable(readUTF8(i93 + 4, cArr3), readUTF8(i93 + 6, cArr3), utf8, labelArr[unsignedShort18], labelArr[unsignedShort18 + unsignedShort19], unsignedShort20);
                        i93 += 10;
                        unsignedShort17--;
                        labelArr = labelArr;
                        iArr14 = iArr14;
                        i87 = 0;
                    }
                }
                int[] iArr17 = iArr14;
                int i95 = 32;
                if (iArr15 != null) {
                    int i96 = 0;
                    while (i96 < iArr15.length) {
                        if ((readByte(iArr15[i96]) >> 1) == i95) {
                            int annotationTarget3 = readAnnotationTarget(context5, iArr15[i96]);
                            int i97 = annotationTarget3 + 2;
                            int i98 = context5.typeRef;
                            TypePath typePath = context5.typePath;
                            Label[] labelArr9 = context5.start;
                            Label[] labelArr10 = context5.end;
                            int[] iArr18 = context5.index;
                            String utf85 = readUTF8(annotationTarget3, cArr3);
                            iArr2 = iArr15;
                            i2 = i85;
                            i3 = i86;
                            context3 = context5;
                            methodVisitor2 = methodVisitor5;
                            cArr2 = cArr3;
                            readAnnotationValues(i97, cArr2, true, methodVisitor.visitLocalVariableAnnotation(i98, typePath, labelArr9, labelArr10, iArr18, utf85, true));
                        } else {
                            iArr2 = iArr15;
                            context3 = context5;
                            methodVisitor2 = methodVisitor5;
                            i2 = i85;
                            i3 = i86;
                            cArr2 = cArr3;
                        }
                        i96++;
                        i85 = i2;
                        i86 = i3;
                        context5 = context3;
                        cArr3 = cArr2;
                        iArr15 = iArr2;
                        i95 = 32;
                        methodVisitor5 = methodVisitor2;
                    }
                }
                Context context8 = context5;
                MethodVisitor methodVisitor6 = methodVisitor5;
                int i99 = i85;
                int i100 = i86;
                char[] cArr4 = cArr3;
                int[] iArr19 = iArr17;
                if (iArr19 != null) {
                    int i101 = 0;
                    while (i101 < iArr19.length) {
                        if ((readByte(iArr19[i101]) >> 1) == 32) {
                            int annotationTarget4 = readAnnotationTarget(context8, iArr19[i101]);
                            iArr = iArr19;
                            cArr = cArr4;
                            readAnnotationValues(annotationTarget4 + 2, cArr, true, methodVisitor.visitLocalVariableAnnotation(context8.typeRef, context8.typePath, context8.start, context8.end, context8.index, readUTF8(annotationTarget4, cArr4), false));
                        } else {
                            iArr = iArr19;
                            cArr = cArr4;
                        }
                        i101++;
                        cArr4 = cArr;
                        iArr19 = iArr;
                    }
                }
                Attribute attribute4 = attribute;
                while (attribute4 != null) {
                    Attribute attribute5 = attribute4.next;
                    attribute4.next = null;
                    methodVisitor6.visitAttribute(attribute4);
                    attribute4 = attribute5;
                }
                methodVisitor6.visitMaxs(i100, i99);
                return;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0055  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int[] readTypeAnnotations(net.bytebuddy.jar.asm.MethodVisitor r12, net.bytebuddy.jar.asm.Context r13, int r14, boolean r15) {
        /*
            r11 = this;
            char[] r0 = r13.buffer
            int r1 = r11.readUnsignedShort(r14)
            int[] r2 = new int[r1]
            int r14 = r14 + 2
            r3 = 0
        Lb:
            if (r3 >= r1) goto L89
            r2[r3] = r14
            int r4 = r11.readInt(r14)
            int r5 = r4 >>> 24
            r6 = 1
            if (r5 == 0) goto L55
            if (r5 == r6) goto L55
            r7 = 64
            if (r5 == r7) goto L31
            r7 = 65
            if (r5 == r7) goto L31
            switch(r5) {
                case 19: goto L2e;
                case 20: goto L2e;
                case 21: goto L2e;
                case 22: goto L55;
                default: goto L25;
            }
        L25:
            switch(r5) {
                case 71: goto L2b;
                case 72: goto L2b;
                case 73: goto L2b;
                case 74: goto L2b;
                case 75: goto L2b;
                default: goto L28;
            }
        L28:
            int r14 = r14 + 3
            goto L57
        L2b:
            int r14 = r14 + 4
            goto L57
        L2e:
            int r14 = r14 + 1
            goto L57
        L31:
            int r7 = r14 + 1
            int r7 = r11.readUnsignedShort(r7)
        L37:
            if (r7 <= 0) goto L28
            int r8 = r14 + 3
            int r8 = r11.readUnsignedShort(r8)
            int r9 = r14 + 5
            int r9 = r11.readUnsignedShort(r9)
            net.bytebuddy.jar.asm.Label[] r10 = r13.labels
            r11.createLabel(r8, r10)
            int r8 = r8 + r9
            net.bytebuddy.jar.asm.Label[] r9 = r13.labels
            r11.createLabel(r8, r9)
            int r14 = r14 + 6
            int r7 = r7 + (-1)
            goto L37
        L55:
            int r14 = r14 + 2
        L57:
            int r7 = r11.readByte(r14)
            r8 = 66
            r9 = 0
            if (r5 != r8) goto L7d
            if (r7 != 0) goto L63
            goto L6a
        L63:
            net.bytebuddy.jar.asm.TypePath r9 = new net.bytebuddy.jar.asm.TypePath
            byte[] r5 = r11.b
            r9.<init>(r5, r14)
        L6a:
            int r7 = r7 * 2
            int r7 = r7 + r6
            int r14 = r14 + r7
            int r5 = r14 + 2
            java.lang.String r14 = r11.readUTF8(r14, r0)
            net.bytebuddy.jar.asm.AnnotationVisitor r14 = r12.visitTryCatchAnnotation(r4, r9, r14, r15)
            int r14 = r11.readAnnotationValues(r5, r0, r6, r14)
            goto L86
        L7d:
            int r14 = r14 + 3
            int r7 = r7 * 2
            int r14 = r14 + r7
            int r14 = r11.readAnnotationValues(r14, r0, r6, r9)
        L86:
            int r3 = r3 + 1
            goto Lb
        L89:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.jar.asm.ClassReader.readTypeAnnotations(net.bytebuddy.jar.asm.MethodVisitor, net.bytebuddy.jar.asm.Context, int, boolean):int[]");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0075  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int readAnnotationTarget(net.bytebuddy.jar.asm.Context r9, int r10) {
        /*
            r8 = this;
            int r0 = r8.readInt(r10)
            int r1 = r0 >>> 24
            r2 = 1
            if (r1 == 0) goto L75
            if (r1 == r2) goto L75
            r3 = 64
            r4 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            if (r1 == r3) goto L2f
            r3 = 65
            if (r1 == r3) goto L2f
            switch(r1) {
                case 19: goto L2c;
                case 20: goto L2c;
                case 21: goto L2c;
                case 22: goto L75;
                default: goto L18;
            }
        L18:
            switch(r1) {
                case 71: goto L25;
                case 72: goto L25;
                case 73: goto L25;
                case 74: goto L25;
                case 75: goto L25;
                default: goto L1b;
            }
        L1b:
            r3 = 67
            if (r1 >= r3) goto L21
            r4 = -256(0xffffffffffffff00, float:NaN)
        L21:
            r0 = r0 & r4
            int r10 = r10 + 3
            goto L7a
        L25:
            r1 = -16776961(0xffffffffff0000ff, float:-1.7014636E38)
            r0 = r0 & r1
            int r10 = r10 + 4
            goto L7a
        L2c:
            r0 = r0 & r4
            int r10 = r10 + r2
            goto L7a
        L2f:
            r0 = r0 & r4
            int r1 = r10 + 1
            int r1 = r8.readUnsignedShort(r1)
            net.bytebuddy.jar.asm.Label[] r3 = new net.bytebuddy.jar.asm.Label[r1]
            r9.start = r3
            net.bytebuddy.jar.asm.Label[] r3 = new net.bytebuddy.jar.asm.Label[r1]
            r9.end = r3
            int[] r3 = new int[r1]
            r9.index = r3
            int r10 = r10 + 3
            r3 = 0
        L45:
            if (r3 >= r1) goto L7a
            int r4 = r8.readUnsignedShort(r10)
            int r5 = r10 + 2
            int r5 = r8.readUnsignedShort(r5)
            net.bytebuddy.jar.asm.Label[] r6 = r9.start
            net.bytebuddy.jar.asm.Label[] r7 = r9.labels
            net.bytebuddy.jar.asm.Label r7 = r8.createLabel(r4, r7)
            r6[r3] = r7
            net.bytebuddy.jar.asm.Label[] r6 = r9.end
            int r4 = r4 + r5
            net.bytebuddy.jar.asm.Label[] r5 = r9.labels
            net.bytebuddy.jar.asm.Label r4 = r8.createLabel(r4, r5)
            r6[r3] = r4
            int[] r4 = r9.index
            int r5 = r10 + 4
            int r5 = r8.readUnsignedShort(r5)
            r4[r3] = r5
            int r10 = r10 + 6
            int r3 = r3 + 1
            goto L45
        L75:
            r1 = -65536(0xffffffffffff0000, float:NaN)
            r0 = r0 & r1
            int r10 = r10 + 2
        L7a:
            int r1 = r8.readByte(r10)
            r9.typeRef = r0
            if (r1 != 0) goto L84
            r0 = 0
            goto L8b
        L84:
            net.bytebuddy.jar.asm.TypePath r0 = new net.bytebuddy.jar.asm.TypePath
            byte[] r3 = r8.b
            r0.<init>(r3, r10)
        L8b:
            r9.typePath = r0
            int r10 = r10 + r2
            int r1 = r1 * 2
            int r10 = r10 + r1
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.jar.asm.ClassReader.readAnnotationTarget(net.bytebuddy.jar.asm.Context, int):int");
    }

    private void readParameterAnnotations(MethodVisitor methodVisitor, Context context, int i, boolean z) {
        int annotationValues = i + 1;
        int i2 = this.b[i] & 255;
        int length = Type.getArgumentTypes(context.desc).length - i2;
        int i3 = 0;
        while (i3 < length) {
            AnnotationVisitor annotationVisitorVisitParameterAnnotation = methodVisitor.visitParameterAnnotation(i3, "Ljava/lang/Synthetic;", false);
            if (annotationVisitorVisitParameterAnnotation != null) {
                annotationVisitorVisitParameterAnnotation.visitEnd();
            }
            i3++;
        }
        char[] cArr = context.buffer;
        while (i3 < i2 + length) {
            annotationValues += 2;
            for (int unsignedShort = readUnsignedShort(annotationValues); unsignedShort > 0; unsignedShort--) {
                annotationValues = readAnnotationValues(annotationValues + 2, cArr, true, methodVisitor.visitParameterAnnotation(i3, readUTF8(annotationValues, cArr), z));
            }
            i3++;
        }
    }

    private int readAnnotationValues(int i, char[] cArr, boolean z, AnnotationVisitor annotationVisitor) {
        int unsignedShort = readUnsignedShort(i);
        int annotationValue = i + 2;
        if (z) {
            while (unsignedShort > 0) {
                annotationValue = readAnnotationValue(annotationValue + 2, cArr, readUTF8(annotationValue, cArr), annotationVisitor);
                unsignedShort--;
            }
        } else {
            while (unsignedShort > 0) {
                annotationValue = readAnnotationValue(annotationValue, cArr, null, annotationVisitor);
                unsignedShort--;
            }
        }
        if (annotationVisitor != null) {
            annotationVisitor.visitEnd();
        }
        return annotationValue;
    }

    /* JADX WARN: Failed to analyze thrown exceptions
    java.util.ConcurrentModificationException
    	at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1095)
    	at java.base/java.util.ArrayList$Itr.next(ArrayList.java:1049)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:117)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.checkInsn(MethodThrowsVisitor.java:178)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:131)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.checkInsn(MethodThrowsVisitor.java:178)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:131)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.checkInsn(MethodThrowsVisitor.java:178)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:131)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.checkInsn(MethodThrowsVisitor.java:178)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:131)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
     */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01fb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int readAnnotationValue(int r11, char[] r12, java.lang.String r13, net.bytebuddy.jar.asm.AnnotationVisitor r14) {
        /*
            Method dump skipped, instruction units count: 556
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.jar.asm.ClassReader.readAnnotationValue(int, char[], java.lang.String, net.bytebuddy.jar.asm.AnnotationVisitor):int");
    }

    private void getImplicitFrame(Context context) {
        int i;
        String str = context.desc;
        Object[] objArr = context.local;
        int i2 = 0;
        if ((context.access & 8) == 0) {
            if (MethodDescription.CONSTRUCTOR_INTERNAL_NAME.equals(context.name)) {
                objArr[0] = Opcodes.UNINITIALIZED_THIS;
            } else {
                objArr[0] = readClass(this.header + 2, context.buffer);
            }
            i2 = 1;
        }
        int i3 = 1;
        while (true) {
            int i4 = i3 + 1;
            char cCharAt = str.charAt(i3);
            if (cCharAt == 'F') {
                i = i2 + 1;
                objArr[i2] = Opcodes.FLOAT;
            } else if (cCharAt != 'L') {
                if (cCharAt != 'S' && cCharAt != 'I') {
                    if (cCharAt == 'J') {
                        i = i2 + 1;
                        objArr[i2] = Opcodes.LONG;
                    } else if (cCharAt != 'Z') {
                        if (cCharAt != '[') {
                            switch (cCharAt) {
                                case 'B':
                                case 'C':
                                    break;
                                case 'D':
                                    i = i2 + 1;
                                    objArr[i2] = Opcodes.DOUBLE;
                                    break;
                                default:
                                    context.localCount = i2;
                                    return;
                            }
                        } else {
                            while (str.charAt(i4) == '[') {
                                i4++;
                            }
                            if (str.charAt(i4) == 'L') {
                                do {
                                    i4++;
                                } while (str.charAt(i4) != ';');
                            }
                            int i5 = i4 + 1;
                            objArr[i2] = str.substring(i3, i5);
                            i3 = i5;
                            i2++;
                        }
                    }
                }
                i = i2 + 1;
                objArr[i2] = Opcodes.INTEGER;
            } else {
                int i6 = i4;
                while (str.charAt(i6) != ';') {
                    i6++;
                }
                objArr[i2] = str.substring(i4, i6);
                i2++;
                i3 = i6 + 1;
            }
            i2 = i;
            i3 = i4;
        }
    }

    private int readFrame(int i, boolean z, boolean z2, Context context) {
        int frameType;
        int i2;
        char[] cArr = context.buffer;
        Label[] labelArr = context.labels;
        if (z) {
            int i3 = i + 1;
            i2 = this.b[i] & 255;
            frameType = i3;
        } else {
            context.offset = -1;
            frameType = i;
            i2 = 255;
        }
        int i4 = 0;
        context.localDiff = 0;
        if (i2 < 64) {
            context.mode = 3;
            context.stackCount = 0;
        } else if (i2 < 128) {
            i2 -= 64;
            frameType = readFrameType(context.stack, 0, frameType, cArr, labelArr);
            context.mode = 4;
            context.stackCount = 1;
        } else {
            int unsignedShort = readUnsignedShort(frameType);
            frameType += 2;
            if (i2 == 247) {
                frameType = readFrameType(context.stack, 0, frameType, cArr, labelArr);
                context.mode = 4;
                context.stackCount = 1;
            } else if (i2 >= 248 && i2 < 251) {
                context.mode = 2;
                context.localDiff = 251 - i2;
                context.localCount -= context.localDiff;
                context.stackCount = 0;
            } else if (i2 != 251) {
                if (i2 < 255) {
                    int i5 = i2 - 251;
                    int i6 = z2 ? context.localCount : 0;
                    int i7 = i5;
                    while (i7 > 0) {
                        frameType = readFrameType(context.local, i6, frameType, cArr, labelArr);
                        i7--;
                        i6++;
                    }
                    context.mode = 1;
                    context.localDiff = i5;
                    context.localCount += context.localDiff;
                    context.stackCount = 0;
                } else {
                    context.mode = 0;
                    int unsignedShort2 = readUnsignedShort(frameType);
                    int frameType2 = frameType + 2;
                    context.localDiff = unsignedShort2;
                    context.localCount = unsignedShort2;
                    int i8 = 0;
                    while (unsignedShort2 > 0) {
                        frameType2 = readFrameType(context.local, i8, frameType2, cArr, labelArr);
                        unsignedShort2--;
                        i8++;
                    }
                    int unsignedShort3 = readUnsignedShort(frameType2);
                    frameType = frameType2 + 2;
                    context.stackCount = unsignedShort3;
                    while (true) {
                        int i9 = i4;
                        if (unsignedShort3 <= 0) {
                            break;
                        }
                        i4 = i9 + 1;
                        frameType = readFrameType(context.stack, i9, frameType, cArr, labelArr);
                        unsignedShort3--;
                    }
                }
            } else {
                context.mode = 3;
                context.stackCount = 0;
            }
            i2 = unsignedShort;
        }
        context.offset += i2 + 1;
        createLabel(context.offset, labelArr);
        return frameType;
    }

    private int readFrameType(Object[] objArr, int i, int i2, char[] cArr, Label[] labelArr) {
        int i3 = i2 + 1;
        switch (this.b[i2] & 255) {
            case 0:
                objArr[i] = Opcodes.TOP;
                return i3;
            case 1:
                objArr[i] = Opcodes.INTEGER;
                return i3;
            case 2:
                objArr[i] = Opcodes.FLOAT;
                return i3;
            case 3:
                objArr[i] = Opcodes.DOUBLE;
                return i3;
            case 4:
                objArr[i] = Opcodes.LONG;
                return i3;
            case 5:
                objArr[i] = Opcodes.NULL;
                return i3;
            case 6:
                objArr[i] = Opcodes.UNINITIALIZED_THIS;
                return i3;
            case 7:
                objArr[i] = readClass(i3, cArr);
                break;
            default:
                objArr[i] = createLabel(readUnsignedShort(i3), labelArr);
                break;
        }
        return i3 + 2;
    }

    protected Label readLabel(int i, Label[] labelArr) {
        if (labelArr[i] == null) {
            labelArr[i] = new Label();
        }
        return labelArr[i];
    }

    private Label createLabel(int i, Label[] labelArr) {
        Label label = readLabel(i, labelArr);
        label.status &= -2;
        return label;
    }

    private void createDebugLabel(int i, Label[] labelArr) {
        if (labelArr[i] == null) {
            readLabel(i, labelArr).status |= 1;
        }
    }

    private int getAttributes() {
        int i = this.header;
        int unsignedShort = i + 8 + (readUnsignedShort(i + 6) * 2);
        for (int unsignedShort2 = readUnsignedShort(unsignedShort); unsignedShort2 > 0; unsignedShort2--) {
            for (int unsignedShort3 = readUnsignedShort(unsignedShort + 8); unsignedShort3 > 0; unsignedShort3--) {
                unsignedShort += readInt(unsignedShort + 12) + 6;
            }
            unsignedShort += 8;
        }
        int i2 = unsignedShort + 2;
        for (int unsignedShort4 = readUnsignedShort(i2); unsignedShort4 > 0; unsignedShort4--) {
            for (int unsignedShort5 = readUnsignedShort(i2 + 8); unsignedShort5 > 0; unsignedShort5--) {
                i2 += readInt(i2 + 12) + 6;
            }
            i2 += 8;
        }
        return i2 + 2;
    }

    private Attribute readAttribute(Attribute[] attributeArr, String str, int i, int i2, char[] cArr, int i3, Label[] labelArr) {
        for (int i4 = 0; i4 < attributeArr.length; i4++) {
            if (attributeArr[i4].type.equals(str)) {
                return attributeArr[i4].read(this, i, i2, cArr, i3, labelArr);
            }
        }
        return new Attribute(str).read(this, i, i2, null, -1, null);
    }

    public int getItemCount() {
        return this.items.length;
    }

    public int getItem(int i) {
        return this.items[i];
    }

    public int getMaxStringLength() {
        return this.maxStringLength;
    }

    public int readByte(int i) {
        return this.b[i] & 255;
    }

    public int readUnsignedShort(int i) {
        byte[] bArr = this.b;
        return (bArr[i + 1] & 255) | ((bArr[i] & 255) << 8);
    }

    public short readShort(int i) {
        byte[] bArr = this.b;
        return (short) ((bArr[i + 1] & 255) | ((bArr[i] & 255) << 8));
    }

    public int readInt(int i) {
        byte[] bArr = this.b;
        return (bArr[i + 3] & 255) | ((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8);
    }

    public long readLong(int i) {
        return (((long) readInt(i)) << 32) | (((long) readInt(i + 4)) & 4294967295L);
    }

    public String readUTF8(int i, char[] cArr) {
        int unsignedShort = readUnsignedShort(i);
        if (i == 0 || unsignedShort == 0) {
            return null;
        }
        String[] strArr = this.strings;
        String str = strArr[unsignedShort];
        if (str != null) {
            return str;
        }
        int i2 = this.items[unsignedShort];
        String utf = readUTF(i2 + 2, readUnsignedShort(i2), cArr);
        strArr[unsignedShort] = utf;
        return utf;
    }

    private String readUTF(int i, int i2, char[] cArr) {
        int i3;
        int i4 = i2 + i;
        byte[] bArr = this.b;
        int i5 = 0;
        char c = 0;
        char c2 = 0;
        while (i < i4) {
            int i6 = i + 1;
            byte b = bArr[i];
            if (c == 0) {
                int i7 = b & 255;
                if (i7 < 128) {
                    cArr[i5] = (char) i7;
                    i5++;
                } else if (i7 >= 224 || i7 <= 191) {
                    c2 = (char) (i7 & 15);
                    c = 2;
                } else {
                    i3 = i7 & 31;
                    c2 = (char) i3;
                    c = 1;
                }
            } else if (c == 1) {
                cArr[i5] = (char) ((b & Utf8.REPLACEMENT_BYTE) | (c2 << 6));
                i5++;
                c = 0;
            } else if (c == 2) {
                i3 = (b & Utf8.REPLACEMENT_BYTE) | (c2 << 6);
                c2 = (char) i3;
                c = 1;
            }
            i = i6;
        }
        return new String(cArr, 0, i5);
    }

    private String readStringish(int i, char[] cArr) {
        return readUTF8(this.items[readUnsignedShort(i)], cArr);
    }

    public String readClass(int i, char[] cArr) {
        return readStringish(i, cArr);
    }

    public String readModule(int i, char[] cArr) {
        return readStringish(i, cArr);
    }

    public String readPackage(int i, char[] cArr) {
        return readStringish(i, cArr);
    }

    public Object readConst(int i, char[] cArr) {
        int i2 = this.items[i];
        byte b = this.b[i2 - 1];
        if (b != 16) {
            switch (b) {
                case 3:
                    return Integer.valueOf(readInt(i2));
                case 4:
                    return Float.valueOf(Float.intBitsToFloat(readInt(i2)));
                case 5:
                    return Long.valueOf(readLong(i2));
                case 6:
                    return Double.valueOf(Double.longBitsToDouble(readLong(i2)));
                case 7:
                    return Type.getObjectType(readUTF8(i2, cArr));
                case 8:
                    return readUTF8(i2, cArr);
                default:
                    int i3 = readByte(i2);
                    int[] iArr = this.items;
                    int i4 = iArr[readUnsignedShort(i2 + 1)];
                    boolean z = this.b[i4 + (-1)] == 11;
                    String str = readClass(i4, cArr);
                    int i5 = iArr[readUnsignedShort(i4 + 2)];
                    return new Handle(i3, str, readUTF8(i5, cArr), readUTF8(i5 + 2, cArr), z);
            }
        }
        return Type.getMethodType(readUTF8(i2, cArr));
    }
}
