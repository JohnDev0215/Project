package org.objectweb.asm;

import kotlin.jvm.internal.ByteCompanionObject;

/* JADX INFO: loaded from: classes6.dex */
public class ClassReader {

    @Deprecated
    public final byte[] b;
    private final int[] bootstrapMethodOffsets;
    final byte[] classFileBuffer;
    private final ConstantDynamic[] constantDynamicValues;
    private final String[] constantUtf8Values;
    private final int[] cpInfoOffsets;
    public final int header;
    private final int maxStringLength;

    public ClassReader(byte[] bArr) {
        this(bArr, 0, bArr.length);
    }

    public ClassReader(byte[] bArr, int i, int i2) {
        this(bArr, i, true);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    ClassReader(byte[] bArr, int i, boolean z) {
        this.classFileBuffer = bArr;
        this.b = bArr;
        if (z) {
            int i2 = i + 6;
            if (readShort(i2) > 60) {
                throw new IllegalArgumentException("Unsupported class file major version " + ((int) readShort(i2)));
            }
        }
        int unsignedShort = readUnsignedShort(i + 8);
        this.cpInfoOffsets = new int[unsignedShort];
        this.constantUtf8Values = new String[unsignedShort];
        int i3 = i + 10;
        int i4 = 0;
        boolean z2 = false;
        boolean z3 = false;
        int i5 = 1;
        while (i5 < unsignedShort) {
            int i6 = i5 + 1;
            int i7 = i3 + 1;
            this.cpInfoOffsets[i5] = i7;
            int unsignedShort2 = 3;
            switch (bArr[i3]) {
                case 1:
                    unsignedShort2 = 3 + readUnsignedShort(i7);
                    if (unsignedShort2 > i4) {
                        i4 = unsignedShort2;
                    }
                    i5 = i6;
                    i3 += unsignedShort2;
                    break;
                case 2:
                case 13:
                case 14:
                default:
                    throw new IllegalArgumentException();
                case 3:
                case 4:
                case 9:
                case 10:
                case 11:
                case 12:
                    i5 = i6;
                    unsignedShort2 = 5;
                    i3 += unsignedShort2;
                    break;
                case 5:
                case 6:
                    unsignedShort2 = 9;
                    i6++;
                    i5 = i6;
                    i3 += unsignedShort2;
                    break;
                case 7:
                case 8:
                case 16:
                case 19:
                case 20:
                    i5 = i6;
                    i3 += unsignedShort2;
                    break;
                case 15:
                    unsignedShort2 = 4;
                    i5 = i6;
                    i3 += unsignedShort2;
                    break;
                case 17:
                    z2 = true;
                    z3 = true;
                    i5 = i6;
                    unsignedShort2 = 5;
                    i3 += unsignedShort2;
                    break;
                case 18:
                    z3 = true;
                    i5 = i6;
                    unsignedShort2 = 5;
                    i3 += unsignedShort2;
                    break;
            }
        }
        this.maxStringLength = i4;
        this.header = i3;
        this.constantDynamicValues = z2 ? new ConstantDynamic[unsignedShort] : null;
        this.bootstrapMethodOffsets = z3 ? readBootstrapMethodsAttribute(i4) : null;
    }

    public int getAccess() {
        return readUnsignedShort(this.header);
    }

    public void accept(ClassVisitor classVisitor, Attribute[] attributeArr, int i) {
        int i2;
        int i3;
        int i4;
        String[] strArr;
        Context context = new Context();
        context.attributePrototypes = attributeArr;
        context.parsingOptions = i;
        char[] cArr = new char[this.maxStringLength];
        context.charBuffer = cArr;
        int i5 = this.header;
        int unsignedShort = readUnsignedShort(i5);
        String str = readClass(i5 + 2, cArr);
        String str2 = readClass(i5 + 4, cArr);
        int unsignedShort2 = readUnsignedShort(i5 + 6);
        String[] strArr2 = new String[unsignedShort2];
        int i6 = i5 + 8;
        for (int i7 = 0; i7 < unsignedShort2; i7++) {
            strArr2[i7] = readClass(i6, cArr);
            i6 += 2;
        }
        int firstAttributeOffset = getFirstAttributeOffset();
        int i8 = unsignedShort;
        int unsignedShort3 = readUnsignedShort(firstAttributeOffset - 2);
        String utf = null;
        String utf8 = null;
        int i9 = 0;
        String utf82 = null;
        int i10 = 0;
        String str3 = null;
        String str4 = null;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        Attribute attribute = null;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        while (unsignedShort3 > 0) {
            String utf83 = readUTF8(firstAttributeOffset, cArr);
            int i20 = readInt(firstAttributeOffset + 2);
            int i21 = firstAttributeOffset + 6;
            String str5 = utf;
            if ("SourceFile".equals(utf83)) {
                i2 = i21;
                utf8 = readUTF8(i21, cArr);
            } else if ("InnerClasses".equals(utf83)) {
                i18 = i21;
                i2 = i18;
            } else if ("EnclosingMethod".equals(utf83)) {
                i11 = i21;
                i2 = i11;
            } else if ("NestHost".equals(utf83)) {
                i2 = i21;
                str4 = readClass(i21, cArr);
            } else if ("NestMembers".equals(utf83)) {
                i16 = i21;
                i2 = i16;
            } else if ("PermittedSubclasses".equals(utf83)) {
                i17 = i21;
                i2 = i17;
            } else {
                if ("Signature".equals(utf83)) {
                    utf82 = readUTF8(i21, cArr);
                } else if ("RuntimeVisibleAnnotations".equals(utf83)) {
                    i12 = i21;
                    i2 = i12;
                } else if ("RuntimeVisibleTypeAnnotations".equals(utf83)) {
                    i14 = i21;
                    i2 = i14;
                } else if ("Deprecated".equals(utf83)) {
                    i8 |= 131072;
                } else if ("Synthetic".equals(utf83)) {
                    i8 |= 4096;
                } else if ("SourceDebugExtension".equals(utf83)) {
                    utf = readUtf(i21, i20, new char[i20]);
                    i2 = i21;
                    i3 = i6;
                    i4 = i20;
                    strArr = strArr2;
                    firstAttributeOffset = i2 + i4;
                    unsignedShort3--;
                    strArr2 = strArr;
                    i6 = i3;
                } else if ("RuntimeInvisibleAnnotations".equals(utf83)) {
                    i13 = i21;
                    i2 = i13;
                } else if ("RuntimeInvisibleTypeAnnotations".equals(utf83)) {
                    i15 = i21;
                    i2 = i15;
                } else if ("Record".equals(utf83)) {
                    i8 |= 65536;
                    i19 = i21;
                    i2 = i19;
                } else if ("Module".equals(utf83)) {
                    i9 = i21;
                    i2 = i9;
                } else if ("ModuleMainClass".equals(utf83)) {
                    str3 = readClass(i21, cArr);
                } else if ("ModulePackages".equals(utf83)) {
                    i10 = i21;
                    i2 = i10;
                } else {
                    if ("BootstrapMethods".equals(utf83)) {
                        i2 = i21;
                        i3 = i6;
                        i4 = i20;
                        strArr = strArr2;
                        utf = str5;
                    } else {
                        i2 = i21;
                        i3 = i6;
                        i4 = i20;
                        strArr = strArr2;
                        Attribute attribute2 = readAttribute(attributeArr, utf83, i2, i20, cArr, -1, null);
                        attribute2.nextAttribute = attribute;
                        attribute = attribute2;
                        i8 = i8;
                        utf = str5;
                        utf8 = utf8;
                    }
                    firstAttributeOffset = i2 + i4;
                    unsignedShort3--;
                    strArr2 = strArr;
                    i6 = i3;
                }
                i2 = i21;
            }
            i3 = i6;
            i4 = i20;
            strArr = strArr2;
            utf = str5;
            firstAttributeOffset = i2 + i4;
            unsignedShort3--;
            strArr2 = strArr;
            i6 = i3;
        }
        String str6 = utf;
        String str7 = utf8;
        int i22 = i6;
        String[] strArr3 = strArr2;
        Attribute attribute3 = attribute;
        classVisitor.visit(readInt(this.cpInfoOffsets[1] - 7), i8, str, utf82, str2, strArr3);
        if ((i & 2) == 0 && (str7 != null || str6 != null)) {
            classVisitor.visitSource(str7, str6);
        }
        if (i9 != 0) {
            readModuleAttributes(classVisitor, context, i9, i10, str3);
        }
        String str8 = str4;
        if (str8 != null) {
            classVisitor.visitNestHost(str8);
        }
        int i23 = i11;
        if (i23 != 0) {
            String str9 = readClass(i23, cArr);
            int unsignedShort4 = readUnsignedShort(i23 + 2);
            classVisitor.visitOuterClass(str9, unsignedShort4 == 0 ? null : readUTF8(this.cpInfoOffsets[unsignedShort4], cArr), unsignedShort4 == 0 ? null : readUTF8(this.cpInfoOffsets[unsignedShort4] + 2, cArr));
        }
        int i24 = i12;
        if (i24 != 0) {
            int unsignedShort5 = readUnsignedShort(i24);
            int elementValues = i24 + 2;
            while (true) {
                int i25 = unsignedShort5 - 1;
                if (unsignedShort5 <= 0) {
                    break;
                }
                elementValues = readElementValues(classVisitor.visitAnnotation(readUTF8(elementValues, cArr), true), elementValues + 2, true, cArr);
                unsignedShort5 = i25;
            }
        }
        int i26 = i13;
        if (i26 != 0) {
            int unsignedShort6 = readUnsignedShort(i26);
            int elementValues2 = i26 + 2;
            while (true) {
                int i27 = unsignedShort6 - 1;
                if (unsignedShort6 <= 0) {
                    break;
                }
                elementValues2 = readElementValues(classVisitor.visitAnnotation(readUTF8(elementValues2, cArr), false), elementValues2 + 2, true, cArr);
                unsignedShort6 = i27;
            }
        }
        int i28 = i14;
        if (i28 != 0) {
            int unsignedShort7 = readUnsignedShort(i28);
            int elementValues3 = i28 + 2;
            while (true) {
                int i29 = unsignedShort7 - 1;
                if (unsignedShort7 <= 0) {
                    break;
                }
                int typeAnnotationTarget = readTypeAnnotationTarget(context, elementValues3);
                elementValues3 = readElementValues(classVisitor.visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, readUTF8(typeAnnotationTarget, cArr), true), typeAnnotationTarget + 2, true, cArr);
                unsignedShort7 = i29;
            }
        }
        int i30 = i15;
        if (i30 != 0) {
            int unsignedShort8 = readUnsignedShort(i30);
            int elementValues4 = i30 + 2;
            while (true) {
                int i31 = unsignedShort8 - 1;
                if (unsignedShort8 <= 0) {
                    break;
                }
                int typeAnnotationTarget2 = readTypeAnnotationTarget(context, elementValues4);
                elementValues4 = readElementValues(classVisitor.visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, readUTF8(typeAnnotationTarget2, cArr), false), typeAnnotationTarget2 + 2, true, cArr);
                unsignedShort8 = i31;
            }
        }
        while (attribute3 != null) {
            Attribute attribute4 = attribute3.nextAttribute;
            attribute3.nextAttribute = null;
            classVisitor.visitAttribute(attribute3);
            attribute3 = attribute4;
        }
        int i32 = i16;
        if (i32 != 0) {
            int unsignedShort9 = readUnsignedShort(i32);
            int i33 = i32 + 2;
            while (true) {
                int i34 = unsignedShort9 - 1;
                if (unsignedShort9 <= 0) {
                    break;
                }
                classVisitor.visitNestMember(readClass(i33, cArr));
                i33 += 2;
                unsignedShort9 = i34;
            }
        }
        int i35 = i17;
        if (i35 != 0) {
            int unsignedShort10 = readUnsignedShort(i35);
            int i36 = i35 + 2;
            while (true) {
                int i37 = unsignedShort10 - 1;
                if (unsignedShort10 <= 0) {
                    break;
                }
                classVisitor.visitPermittedSubclass(readClass(i36, cArr));
                i36 += 2;
                unsignedShort10 = i37;
            }
        }
        int i38 = i18;
        if (i38 != 0) {
            int unsignedShort11 = readUnsignedShort(i38);
            int i39 = i38 + 2;
            while (true) {
                int i40 = unsignedShort11 - 1;
                if (unsignedShort11 <= 0) {
                    break;
                }
                classVisitor.visitInnerClass(readClass(i39, cArr), readClass(i39 + 2, cArr), readUTF8(i39 + 4, cArr), readUnsignedShort(i39 + 6));
                i39 += 8;
                unsignedShort11 = i40;
            }
        }
        int i41 = i19;
        if (i41 != 0) {
            int unsignedShort12 = readUnsignedShort(i41);
            int recordComponent = i41 + 2;
            while (true) {
                int i42 = unsignedShort12 - 1;
                if (unsignedShort12 <= 0) {
                    break;
                }
                recordComponent = readRecordComponent(classVisitor, context, recordComponent);
                unsignedShort12 = i42;
            }
        }
        int unsignedShort13 = readUnsignedShort(i22);
        int field = i22 + 2;
        while (true) {
            int i43 = unsignedShort13 - 1;
            if (unsignedShort13 <= 0) {
                break;
            }
            field = readField(classVisitor, context, field);
            unsignedShort13 = i43;
        }
        int unsignedShort14 = readUnsignedShort(field);
        int method = field + 2;
        while (true) {
            int i44 = unsignedShort14 - 1;
            if (unsignedShort14 > 0) {
                method = readMethod(classVisitor, context, method);
                unsignedShort14 = i44;
            } else {
                classVisitor.visitEnd();
                return;
            }
        }
    }

    private void readModuleAttributes(ClassVisitor classVisitor, Context context, int i, int i2, String str) {
        String[] strArr;
        char[] cArr = context.charBuffer;
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
            int unsignedShort2 = readUnsignedShort(i2);
            int i4 = i2 + 2;
            while (true) {
                int i5 = unsignedShort2 - 1;
                if (unsignedShort2 <= 0) {
                    break;
                }
                moduleVisitorVisitModule.visitPackage(readPackage(i4, cArr));
                i4 += 2;
                unsignedShort2 = i5;
            }
        }
        int unsignedShort3 = readUnsignedShort(i3);
        int i6 = i3 + 2;
        while (true) {
            int i7 = unsignedShort3 - 1;
            if (unsignedShort3 <= 0) {
                break;
            }
            String module2 = readModule(i6, cArr);
            int unsignedShort4 = readUnsignedShort(i6 + 2);
            String utf82 = readUTF8(i6 + 4, cArr);
            i6 += 6;
            moduleVisitorVisitModule.visitRequire(module2, unsignedShort4, utf82);
            unsignedShort3 = i7;
        }
        int unsignedShort5 = readUnsignedShort(i6);
        int i8 = i6 + 2;
        while (true) {
            int i9 = unsignedShort5 - 1;
            String[] strArr2 = null;
            if (unsignedShort5 <= 0) {
                break;
            }
            String str2 = readPackage(i8, cArr);
            int unsignedShort6 = readUnsignedShort(i8 + 2);
            int unsignedShort7 = readUnsignedShort(i8 + 4);
            i8 += 6;
            if (unsignedShort7 != 0) {
                strArr2 = new String[unsignedShort7];
                for (int i10 = 0; i10 < unsignedShort7; i10++) {
                    strArr2[i10] = readModule(i8, cArr);
                    i8 += 2;
                }
            }
            moduleVisitorVisitModule.visitExport(str2, unsignedShort6, strArr2);
            unsignedShort5 = i9;
        }
        int unsignedShort8 = readUnsignedShort(i8);
        int i11 = i8 + 2;
        while (true) {
            int i12 = unsignedShort8 - 1;
            if (unsignedShort8 <= 0) {
                break;
            }
            String str3 = readPackage(i11, cArr);
            int unsignedShort9 = readUnsignedShort(i11 + 2);
            int unsignedShort10 = readUnsignedShort(i11 + 4);
            i11 += 6;
            if (unsignedShort10 != 0) {
                strArr = new String[unsignedShort10];
                for (int i13 = 0; i13 < unsignedShort10; i13++) {
                    strArr[i13] = readModule(i11, cArr);
                    i11 += 2;
                }
            } else {
                strArr = null;
            }
            moduleVisitorVisitModule.visitOpen(str3, unsignedShort9, strArr);
            unsignedShort8 = i12;
        }
        int unsignedShort11 = readUnsignedShort(i11);
        int i14 = i11 + 2;
        while (true) {
            int i15 = unsignedShort11 - 1;
            if (unsignedShort11 <= 0) {
                break;
            }
            moduleVisitorVisitModule.visitUse(readClass(i14, cArr));
            i14 += 2;
            unsignedShort11 = i15;
        }
        int unsignedShort12 = readUnsignedShort(i14);
        int i16 = i14 + 2;
        while (true) {
            int i17 = unsignedShort12 - 1;
            if (unsignedShort12 > 0) {
                String str4 = readClass(i16, cArr);
                int unsignedShort13 = readUnsignedShort(i16 + 2);
                i16 += 4;
                String[] strArr3 = new String[unsignedShort13];
                for (int i18 = 0; i18 < unsignedShort13; i18++) {
                    strArr3[i18] = readClass(i16, cArr);
                    i16 += 2;
                }
                moduleVisitorVisitModule.visitProvide(str4, strArr3);
                unsignedShort12 = i17;
            } else {
                moduleVisitorVisitModule.visitEnd();
                return;
            }
        }
    }

    private int readRecordComponent(ClassVisitor classVisitor, Context context, int i) {
        int i2;
        Context context2 = context;
        char[] cArr = context2.charBuffer;
        String utf8 = readUTF8(i, cArr);
        String utf82 = readUTF8(i + 2, cArr);
        int i3 = i + 4;
        int unsignedShort = readUnsignedShort(i3);
        int i4 = i3 + 2;
        int i5 = 0;
        Attribute attribute = null;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        String utf83 = null;
        while (true) {
            int i9 = unsignedShort - 1;
            if (unsignedShort <= 0) {
                break;
            }
            String utf84 = readUTF8(i4, cArr);
            int i10 = readInt(i4 + 2);
            int i11 = i4 + 6;
            if ("Signature".equals(utf84)) {
                utf83 = readUTF8(i11, cArr);
                i2 = i11;
            } else if ("RuntimeVisibleAnnotations".equals(utf84)) {
                i8 = i11;
                i2 = i8;
            } else if ("RuntimeVisibleTypeAnnotations".equals(utf84)) {
                i6 = i11;
                i2 = i6;
            } else if ("RuntimeInvisibleAnnotations".equals(utf84)) {
                i7 = i11;
                i2 = i7;
            } else if ("RuntimeInvisibleTypeAnnotations".equals(utf84)) {
                i5 = i11;
                i2 = i5;
            } else {
                i2 = i11;
                Attribute attribute2 = attribute;
                attribute = readAttribute(context2.attributePrototypes, utf84, i2, i10, cArr, -1, null);
                attribute.nextAttribute = attribute2;
                i8 = i8;
                i7 = i7;
                i6 = i6;
                i5 = i5;
            }
            i4 = i2 + i10;
            context2 = context;
            unsignedShort = i9;
        }
        int i12 = i5;
        Attribute attribute3 = attribute;
        int i13 = i6;
        int i14 = i7;
        int i15 = i8;
        RecordComponentVisitor recordComponentVisitorVisitRecordComponent = classVisitor.visitRecordComponent(utf8, utf82, utf83);
        if (recordComponentVisitorVisitRecordComponent == null) {
            return i4;
        }
        if (i15 != 0) {
            int unsignedShort2 = readUnsignedShort(i15);
            int elementValues = i15 + 2;
            while (true) {
                int i16 = unsignedShort2 - 1;
                if (unsignedShort2 <= 0) {
                    break;
                }
                elementValues = readElementValues(recordComponentVisitorVisitRecordComponent.visitAnnotation(readUTF8(elementValues, cArr), true), elementValues + 2, true, cArr);
                unsignedShort2 = i16;
            }
        }
        if (i14 != 0) {
            int unsignedShort3 = readUnsignedShort(i14);
            int elementValues2 = i14 + 2;
            while (true) {
                int i17 = unsignedShort3 - 1;
                if (unsignedShort3 <= 0) {
                    break;
                }
                elementValues2 = readElementValues(recordComponentVisitorVisitRecordComponent.visitAnnotation(readUTF8(elementValues2, cArr), false), elementValues2 + 2, true, cArr);
                unsignedShort3 = i17;
            }
        }
        if (i13 != 0) {
            int unsignedShort4 = readUnsignedShort(i13);
            int elementValues3 = i13 + 2;
            while (true) {
                int i18 = unsignedShort4 - 1;
                if (unsignedShort4 <= 0) {
                    break;
                }
                int typeAnnotationTarget = readTypeAnnotationTarget(context, elementValues3);
                elementValues3 = readElementValues(recordComponentVisitorVisitRecordComponent.visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, readUTF8(typeAnnotationTarget, cArr), true), typeAnnotationTarget + 2, true, cArr);
                unsignedShort4 = i18;
            }
        }
        if (i12 != 0) {
            int unsignedShort5 = readUnsignedShort(i12);
            int elementValues4 = i12 + 2;
            while (true) {
                int i19 = unsignedShort5 - 1;
                if (unsignedShort5 <= 0) {
                    break;
                }
                int typeAnnotationTarget2 = readTypeAnnotationTarget(context, elementValues4);
                elementValues4 = readElementValues(recordComponentVisitorVisitRecordComponent.visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, readUTF8(typeAnnotationTarget2, cArr), false), typeAnnotationTarget2 + 2, true, cArr);
                unsignedShort5 = i19;
            }
        }
        Attribute attribute4 = attribute3;
        while (attribute4 != null) {
            Attribute attribute5 = attribute4.nextAttribute;
            attribute4.nextAttribute = null;
            recordComponentVisitorVisitRecordComponent.visitAttribute(attribute4);
            attribute4 = attribute5;
        }
        recordComponentVisitorVisitRecordComponent.visitEnd();
        return i4;
    }

    private int readField(ClassVisitor classVisitor, Context context, int i) {
        int i2;
        int i3;
        Context context2 = context;
        char[] cArr = context2.charBuffer;
        int unsignedShort = readUnsignedShort(i);
        String utf8 = readUTF8(i + 2, cArr);
        String utf82 = readUTF8(i + 4, cArr);
        int i4 = i + 6;
        int unsignedShort2 = readUnsignedShort(i4);
        int i5 = i4 + 2;
        int i6 = unsignedShort;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        Attribute attribute = null;
        String utf83 = null;
        Object obj = null;
        while (true) {
            int i11 = unsignedShort2 - 1;
            if (unsignedShort2 <= 0) {
                break;
            }
            String utf84 = readUTF8(i5, cArr);
            int i12 = readInt(i5 + 2);
            int i13 = i5 + 6;
            if ("ConstantValue".equals(utf84)) {
                int unsignedShort3 = readUnsignedShort(i13);
                obj = unsignedShort3 == 0 ? null : readConst(unsignedShort3, cArr);
            } else if ("Signature".equals(utf84)) {
                utf83 = readUTF8(i13, cArr);
            } else {
                if ("Deprecated".equals(utf84)) {
                    i3 = 131072 | i6;
                } else if ("Synthetic".equals(utf84)) {
                    i3 = i6 | 4096;
                } else {
                    if ("RuntimeVisibleAnnotations".equals(utf84)) {
                        i10 = i13;
                        i2 = i10;
                    } else if ("RuntimeVisibleTypeAnnotations".equals(utf84)) {
                        i8 = i13;
                        i2 = i8;
                    } else if ("RuntimeInvisibleAnnotations".equals(utf84)) {
                        i9 = i13;
                        i2 = i9;
                    } else if ("RuntimeInvisibleTypeAnnotations".equals(utf84)) {
                        i7 = i13;
                        i2 = i7;
                    } else {
                        i2 = i13;
                        Attribute attribute2 = attribute;
                        attribute = readAttribute(context2.attributePrototypes, utf84, i2, i12, cArr, -1, null);
                        attribute.nextAttribute = attribute2;
                        i9 = i9;
                        i10 = i10;
                        i7 = i7;
                        i8 = i8;
                    }
                    i5 = i2 + i12;
                    context2 = context;
                    unsignedShort2 = i11;
                }
                i6 = i3;
            }
            i2 = i13;
            i5 = i2 + i12;
            context2 = context;
            unsignedShort2 = i11;
        }
        Attribute attribute3 = attribute;
        int i14 = i7;
        int i15 = i8;
        int i16 = i9;
        int i17 = i10;
        FieldVisitor fieldVisitorVisitField = classVisitor.visitField(i6, utf8, utf82, utf83, obj);
        if (fieldVisitorVisitField == null) {
            return i5;
        }
        if (i17 != 0) {
            int unsignedShort4 = readUnsignedShort(i17);
            int elementValues = i17 + 2;
            while (true) {
                int i18 = unsignedShort4 - 1;
                if (unsignedShort4 <= 0) {
                    break;
                }
                elementValues = readElementValues(fieldVisitorVisitField.visitAnnotation(readUTF8(elementValues, cArr), true), elementValues + 2, true, cArr);
                unsignedShort4 = i18;
            }
        }
        if (i16 != 0) {
            int unsignedShort5 = readUnsignedShort(i16);
            int elementValues2 = i16 + 2;
            while (true) {
                int i19 = unsignedShort5 - 1;
                if (unsignedShort5 <= 0) {
                    break;
                }
                elementValues2 = readElementValues(fieldVisitorVisitField.visitAnnotation(readUTF8(elementValues2, cArr), false), elementValues2 + 2, true, cArr);
                unsignedShort5 = i19;
            }
        }
        if (i15 != 0) {
            int unsignedShort6 = readUnsignedShort(i15);
            int elementValues3 = i15 + 2;
            while (true) {
                int i20 = unsignedShort6 - 1;
                if (unsignedShort6 <= 0) {
                    break;
                }
                int typeAnnotationTarget = readTypeAnnotationTarget(context, elementValues3);
                elementValues3 = readElementValues(fieldVisitorVisitField.visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, readUTF8(typeAnnotationTarget, cArr), true), typeAnnotationTarget + 2, true, cArr);
                unsignedShort6 = i20;
            }
        }
        if (i14 != 0) {
            int unsignedShort7 = readUnsignedShort(i14);
            int elementValues4 = i14 + 2;
            while (true) {
                int i21 = unsignedShort7 - 1;
                if (unsignedShort7 <= 0) {
                    break;
                }
                int typeAnnotationTarget2 = readTypeAnnotationTarget(context, elementValues4);
                elementValues4 = readElementValues(fieldVisitorVisitField.visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, readUTF8(typeAnnotationTarget2, cArr), false), typeAnnotationTarget2 + 2, true, cArr);
                unsignedShort7 = i21;
            }
        }
        while (true) {
            Attribute attribute4 = attribute3;
            if (attribute4 != null) {
                attribute3 = attribute4.nextAttribute;
                attribute4.nextAttribute = null;
                fieldVisitorVisitField.visitAttribute(attribute4);
            } else {
                fieldVisitorVisitField.visitEnd();
                return i5;
            }
        }
    }

    private int readMethod(ClassVisitor classVisitor, Context context, int i) {
        int i2;
        int i3;
        int i4;
        char[] cArr = context.charBuffer;
        context.currentMethodAccessFlags = readUnsignedShort(i);
        context.currentMethodName = readUTF8(i + 2, cArr);
        int i5 = i + 4;
        context.currentMethodDescriptor = readUTF8(i5, cArr);
        int i6 = i + 6;
        int unsignedShort = readUnsignedShort(i6);
        int i7 = i6 + 2;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        int unsignedShort2 = 0;
        Attribute attribute = null;
        boolean z = false;
        int i13 = 0;
        String[] strArr = null;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        int i17 = 0;
        while (true) {
            int i18 = unsignedShort - 1;
            if (unsignedShort <= 0) {
                break;
            }
            String utf8 = readUTF8(i7, cArr);
            int i19 = readInt(i7 + 2);
            int i20 = i7 + 6;
            int i21 = i8;
            if ("Code".equals(utf8)) {
                if ((context.parsingOptions & 1) == 0) {
                    i17 = i20;
                    i8 = i21;
                    i4 = i17;
                    i7 = i4 + i19;
                    unsignedShort = i18;
                } else {
                    i2 = i9;
                    i3 = i10;
                    i8 = i21;
                    i9 = i2;
                    i10 = i3;
                }
            } else {
                if ("Exceptions".equals(utf8)) {
                    int unsignedShort3 = readUnsignedShort(i20);
                    String[] strArr2 = new String[unsignedShort3];
                    int i22 = i9;
                    int i23 = i10;
                    int i24 = i20 + 2;
                    for (int i25 = 0; i25 < unsignedShort3; i25++) {
                        strArr2[i25] = readClass(i24, cArr);
                        i24 += 2;
                    }
                    strArr = strArr2;
                    i13 = i20;
                    i8 = i21;
                    i9 = i22;
                    i10 = i23;
                    i4 = i13;
                } else {
                    i2 = i9;
                    i3 = i10;
                    if ("Signature".equals(utf8)) {
                        unsignedShort2 = readUnsignedShort(i20);
                    } else if ("Deprecated".equals(utf8)) {
                        context.currentMethodAccessFlags |= 131072;
                    } else if ("RuntimeVisibleAnnotations".equals(utf8)) {
                        i10 = i20;
                        i8 = i21;
                        i9 = i2;
                        i4 = i10;
                    } else if ("RuntimeVisibleTypeAnnotations".equals(utf8)) {
                        i8 = i20;
                        i4 = i8;
                        i9 = i2;
                        i10 = i3;
                    } else if ("AnnotationDefault".equals(utf8)) {
                        i11 = i20;
                        i8 = i21;
                        i9 = i2;
                        i10 = i3;
                        i4 = i11;
                    } else if ("Synthetic".equals(utf8)) {
                        context.currentMethodAccessFlags |= 4096;
                        i8 = i21;
                        i9 = i2;
                        i10 = i3;
                        z = true;
                    } else if ("RuntimeInvisibleAnnotations".equals(utf8)) {
                        i9 = i20;
                        i8 = i21;
                        i10 = i3;
                        i4 = i9;
                    } else if ("RuntimeInvisibleTypeAnnotations".equals(utf8)) {
                        i14 = i20;
                        i8 = i21;
                        i9 = i2;
                        i10 = i3;
                        i4 = i14;
                    } else if ("RuntimeVisibleParameterAnnotations".equals(utf8)) {
                        i15 = i20;
                        i8 = i21;
                        i9 = i2;
                        i10 = i3;
                        i4 = i15;
                    } else if ("RuntimeInvisibleParameterAnnotations".equals(utf8)) {
                        i16 = i20;
                        i8 = i21;
                        i9 = i2;
                        i10 = i3;
                        i4 = i16;
                    } else if ("MethodParameters".equals(utf8)) {
                        i12 = i20;
                        i8 = i21;
                        i9 = i2;
                        i10 = i3;
                        i4 = i12;
                    } else {
                        i4 = i20;
                        Attribute attribute2 = readAttribute(context.attributePrototypes, utf8, i20, i19, cArr, -1, null);
                        attribute2.nextAttribute = attribute;
                        attribute = attribute2;
                        unsignedShort2 = unsignedShort2;
                        i9 = i2;
                        i10 = i3;
                        i8 = i21;
                        i11 = i11;
                        i12 = i12;
                    }
                    i8 = i21;
                    i9 = i2;
                    i10 = i3;
                }
                i7 = i4 + i19;
                unsignedShort = i18;
            }
            i4 = i20;
            i7 = i4 + i19;
            unsignedShort = i18;
        }
        int i26 = i8;
        int i27 = i9;
        int i28 = i10;
        int i29 = i11;
        int i30 = i12;
        int i31 = unsignedShort2;
        MethodVisitor methodVisitorVisitMethod = classVisitor.visitMethod(context.currentMethodAccessFlags, context.currentMethodName, context.currentMethodDescriptor, i31 == 0 ? null : readUtf(i31, cArr), strArr);
        if (methodVisitorVisitMethod == null) {
            return i7;
        }
        if (methodVisitorVisitMethod instanceof MethodWriter) {
            MethodWriter methodWriter = (MethodWriter) methodVisitorVisitMethod;
            if (methodWriter.canCopyMethodAttributes(this, z, (context.currentMethodAccessFlags & 131072) != 0, readUnsignedShort(i5), i31, i13)) {
                methodWriter.setMethodAttributesSource(i, i7 - i);
                return i7;
            }
        }
        if (i30 != 0 && (context.parsingOptions & 2) == 0) {
            int i32 = readByte(i30);
            int i33 = i30 + 1;
            while (true) {
                int i34 = i32 - 1;
                if (i32 <= 0) {
                    break;
                }
                methodVisitorVisitMethod.visitParameter(readUTF8(i33, cArr), readUnsignedShort(i33 + 2));
                i33 += 4;
                i32 = i34;
            }
        }
        if (i29 != 0) {
            AnnotationVisitor annotationVisitorVisitAnnotationDefault = methodVisitorVisitMethod.visitAnnotationDefault();
            readElementValue(annotationVisitorVisitAnnotationDefault, i29, null, cArr);
            if (annotationVisitorVisitAnnotationDefault != null) {
                annotationVisitorVisitAnnotationDefault.visitEnd();
            }
        }
        if (i28 != 0) {
            int unsignedShort4 = readUnsignedShort(i28);
            int elementValues = i28 + 2;
            while (true) {
                int i35 = unsignedShort4 - 1;
                if (unsignedShort4 <= 0) {
                    break;
                }
                elementValues = readElementValues(methodVisitorVisitMethod.visitAnnotation(readUTF8(elementValues, cArr), true), elementValues + 2, true, cArr);
                unsignedShort4 = i35;
            }
        }
        if (i27 != 0) {
            int unsignedShort5 = readUnsignedShort(i27);
            int elementValues2 = i27 + 2;
            while (true) {
                int i36 = unsignedShort5 - 1;
                if (unsignedShort5 <= 0) {
                    break;
                }
                elementValues2 = readElementValues(methodVisitorVisitMethod.visitAnnotation(readUTF8(elementValues2, cArr), false), elementValues2 + 2, true, cArr);
                unsignedShort5 = i36;
            }
        }
        if (i26 != 0) {
            int unsignedShort6 = readUnsignedShort(i26);
            int elementValues3 = i26 + 2;
            while (true) {
                int i37 = unsignedShort6 - 1;
                if (unsignedShort6 <= 0) {
                    break;
                }
                int typeAnnotationTarget = readTypeAnnotationTarget(context, elementValues3);
                elementValues3 = readElementValues(methodVisitorVisitMethod.visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, readUTF8(typeAnnotationTarget, cArr), true), typeAnnotationTarget + 2, true, cArr);
                unsignedShort6 = i37;
            }
        }
        int i38 = i14;
        if (i38 != 0) {
            int unsignedShort7 = readUnsignedShort(i38);
            int elementValues4 = i38 + 2;
            while (true) {
                int i39 = unsignedShort7 - 1;
                if (unsignedShort7 <= 0) {
                    break;
                }
                int typeAnnotationTarget2 = readTypeAnnotationTarget(context, elementValues4);
                elementValues4 = readElementValues(methodVisitorVisitMethod.visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, readUTF8(typeAnnotationTarget2, cArr), false), typeAnnotationTarget2 + 2, true, cArr);
                unsignedShort7 = i39;
            }
        }
        int i40 = i15;
        if (i40 != 0) {
            readParameterAnnotations(methodVisitorVisitMethod, context, i40, true);
        }
        int i41 = i16;
        if (i41 != 0) {
            readParameterAnnotations(methodVisitorVisitMethod, context, i41, false);
        }
        while (attribute != null) {
            Attribute attribute3 = attribute.nextAttribute;
            attribute.nextAttribute = null;
            methodVisitorVisitMethod.visitAttribute(attribute);
            attribute = attribute3;
        }
        int i42 = i17;
        if (i42 != 0) {
            methodVisitorVisitMethod.visitCode();
            readCode(methodVisitorVisitMethod, context, i42);
        }
        methodVisitorVisitMethod.visitEnd();
        return i7;
    }

    /* JADX WARN: Removed duplicated region for block: B:229:0x0793  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x07c3  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x07ca  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x07ea  */
    /* JADX WARN: Removed duplicated region for block: B:346:0x07bb A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Type update failed for variable: r40v0 org.objectweb.asm.MethodVisitor, new type: org.objectweb.asm.MethodVisitor
    jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 33541. Try increasing type updates limit count.
    	at jadx.core.dex.visitors.typeinference.TypeUpdateInfo.requestUpdate(TypeUpdateInfo.java:37)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:224)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.suggestAllSameListener(TypeUpdate.java:513)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.arithListener(TypeUpdate.java:497)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.suggestAllSameListener(TypeUpdate.java:513)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.arithListener(TypeUpdate.java:497)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.suggestAllSameListener(TypeUpdate.java:507)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.arithListener(TypeUpdate.java:497)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.ifListener(TypeUpdate.java:640)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.suggestAllSameListener(TypeUpdate.java:513)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.arithListener(TypeUpdate.java:497)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.suggestAllSameListener(TypeUpdate.java:507)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.arithListener(TypeUpdate.java:497)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.suggestAllSameListener(TypeUpdate.java:513)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.arithListener(TypeUpdate.java:497)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.suggestAllSameListener(TypeUpdate.java:513)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.arithListener(TypeUpdate.java:497)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.suggestAllSameListener(TypeUpdate.java:513)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.arithListener(TypeUpdate.java:497)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.suggestAllSameListener(TypeUpdate.java:513)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.arithListener(TypeUpdate.java:497)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.suggestAllSameListener(TypeUpdate.java:513)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.arithListener(TypeUpdate.java:497)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.arrayPutListener(TypeUpdate.java:616)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.arrayGetListener(TypeUpdate.java:572)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.ifListener(TypeUpdate.java:640)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.applyInvokeTypes(TypeUpdate.java:399)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.invokeListener(TypeUpdate.java:364)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:480)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:197)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:241)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:225)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:202)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:119)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:86)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.applyWithWiderIgnSame(TypeUpdate.java:72)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setImmutableType(TypeInferenceVisitor.java:111)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$1(TypeInferenceVisitor.java:102)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:102)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void readCode(org.objectweb.asm.MethodVisitor r40, org.objectweb.asm.Context r41, int r42) {
        /*
            Method dump skipped, instruction units count: 3354
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.objectweb.asm.ClassReader.readCode(org.objectweb.asm.MethodVisitor, org.objectweb.asm.Context, int):void");
    }

    protected Label readLabel(int i, Label[] labelArr) {
        if (labelArr[i] == null) {
            labelArr[i] = new Label();
        }
        return labelArr[i];
    }

    private Label createLabel(int i, Label[] labelArr) {
        Label label = readLabel(i, labelArr);
        label.flags = (short) (label.flags & (-2));
        return label;
    }

    private void createDebugLabel(int i, Label[] labelArr) {
        if (labelArr[i] == null) {
            Label label = readLabel(i, labelArr);
            label.flags = (short) (label.flags | 1);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x004d A[FALL_THROUGH] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int[] readTypeAnnotations(org.objectweb.asm.MethodVisitor r11, org.objectweb.asm.Context r12, int r13, boolean r14) {
        /*
            r10 = this;
            char[] r0 = r12.charBuffer
            int r1 = r10.readUnsignedShort(r13)
            int[] r2 = new int[r1]
            int r13 = r13 + 2
            r3 = 0
        Lb:
            if (r3 >= r1) goto L84
            r2[r3] = r13
            int r4 = r10.readInt(r13)
            int r5 = r4 >>> 24
            r6 = 23
            if (r5 == r6) goto L4d
            switch(r5) {
                case 16: goto L4d;
                case 17: goto L4d;
                case 18: goto L4d;
                default: goto L1c;
            }
        L1c:
            switch(r5) {
                case 64: goto L28;
                case 65: goto L28;
                case 66: goto L4d;
                case 67: goto L4d;
                case 68: goto L4d;
                case 69: goto L4d;
                case 70: goto L4d;
                case 71: goto L25;
                case 72: goto L25;
                case 73: goto L25;
                case 74: goto L25;
                case 75: goto L25;
                default: goto L1f;
            }
        L1f:
            java.lang.IllegalArgumentException r11 = new java.lang.IllegalArgumentException
            r11.<init>()
            throw r11
        L25:
            int r13 = r13 + 4
            goto L4f
        L28:
            int r6 = r13 + 1
            int r6 = r10.readUnsignedShort(r6)
            int r13 = r13 + 3
        L30:
            int r7 = r6 + (-1)
            if (r6 <= 0) goto L4f
            int r6 = r10.readUnsignedShort(r13)
            int r8 = r13 + 2
            int r8 = r10.readUnsignedShort(r8)
            int r13 = r13 + 6
            org.objectweb.asm.Label[] r9 = r12.currentMethodLabels
            r10.createLabel(r6, r9)
            int r6 = r6 + r8
            org.objectweb.asm.Label[] r8 = r12.currentMethodLabels
            r10.createLabel(r6, r8)
            r6 = r7
            goto L30
        L4d:
            int r13 = r13 + 3
        L4f:
            int r6 = r10.readByte(r13)
            r7 = 66
            r8 = 0
            r9 = 1
            if (r5 != r7) goto L78
            if (r6 != 0) goto L5c
            goto L63
        L5c:
            org.objectweb.asm.TypePath r8 = new org.objectweb.asm.TypePath
            byte[] r5 = r10.classFileBuffer
            r8.<init>(r5, r13)
        L63:
            int r6 = r6 * 2
            int r6 = r6 + r9
            int r13 = r13 + r6
            java.lang.String r5 = r10.readUTF8(r13, r0)
            int r13 = r13 + 2
            r4 = r4 & (-256(0xffffffffffffff00, float:NaN))
            org.objectweb.asm.AnnotationVisitor r4 = r11.visitTryCatchAnnotation(r4, r8, r5, r14)
            int r13 = r10.readElementValues(r4, r13, r9, r0)
            goto L81
        L78:
            int r6 = r6 * 2
            int r6 = r6 + 3
            int r13 = r13 + r6
            int r13 = r10.readElementValues(r8, r13, r9, r0)
        L81:
            int r3 = r3 + 1
            goto Lb
        L84:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.objectweb.asm.ClassReader.readTypeAnnotations(org.objectweb.asm.MethodVisitor, org.objectweb.asm.Context, int, boolean):int[]");
    }

    private int getTypeAnnotationBytecodeOffset(int[] iArr, int i) {
        if (iArr == null || i >= iArr.length || readByte(iArr[i]) < 67) {
            return -1;
        }
        return readUnsignedShort(iArr[i] + 1);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:16:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0070  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int readTypeAnnotationTarget(org.objectweb.asm.Context r10, int r11) {
        /*
            r9 = this;
            int r0 = r9.readInt(r11)
            int r1 = r0 >>> 24
            r2 = 1
            if (r1 == 0) goto L70
            if (r1 == r2) goto L70
            r3 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            switch(r1) {
                case 16: goto L6b;
                case 17: goto L6b;
                case 18: goto L6b;
                case 19: goto L68;
                case 20: goto L68;
                case 21: goto L68;
                case 22: goto L70;
                case 23: goto L6b;
                default: goto L10;
            }
        L10:
            switch(r1) {
                case 64: goto L22;
                case 65: goto L22;
                case 66: goto L6b;
                case 67: goto L20;
                case 68: goto L20;
                case 69: goto L20;
                case 70: goto L20;
                case 71: goto L19;
                case 72: goto L19;
                case 73: goto L19;
                case 74: goto L19;
                case 75: goto L19;
                default: goto L13;
            }
        L13:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            r10.<init>()
            throw r10
        L19:
            r1 = -16776961(0xffffffffff0000ff, float:-1.7014636E38)
            r0 = r0 & r1
            int r11 = r11 + 4
            goto L75
        L20:
            r0 = r0 & r3
            goto L6d
        L22:
            r0 = r0 & r3
            int r1 = r11 + 1
            int r1 = r9.readUnsignedShort(r1)
            int r11 = r11 + 3
            org.objectweb.asm.Label[] r3 = new org.objectweb.asm.Label[r1]
            r10.currentLocalVariableAnnotationRangeStarts = r3
            org.objectweb.asm.Label[] r3 = new org.objectweb.asm.Label[r1]
            r10.currentLocalVariableAnnotationRangeEnds = r3
            int[] r3 = new int[r1]
            r10.currentLocalVariableAnnotationRangeIndices = r3
            r3 = 0
        L38:
            if (r3 >= r1) goto L75
            int r4 = r9.readUnsignedShort(r11)
            int r5 = r11 + 2
            int r5 = r9.readUnsignedShort(r5)
            int r6 = r11 + 4
            int r6 = r9.readUnsignedShort(r6)
            int r11 = r11 + 6
            org.objectweb.asm.Label[] r7 = r10.currentLocalVariableAnnotationRangeStarts
            org.objectweb.asm.Label[] r8 = r10.currentMethodLabels
            org.objectweb.asm.Label r8 = r9.createLabel(r4, r8)
            r7[r3] = r8
            org.objectweb.asm.Label[] r7 = r10.currentLocalVariableAnnotationRangeEnds
            int r4 = r4 + r5
            org.objectweb.asm.Label[] r5 = r10.currentMethodLabels
            org.objectweb.asm.Label r4 = r9.createLabel(r4, r5)
            r7[r3] = r4
            int[] r4 = r10.currentLocalVariableAnnotationRangeIndices
            r4[r3] = r6
            int r3 = r3 + 1
            goto L38
        L68:
            r0 = r0 & r3
            int r11 = r11 + r2
            goto L75
        L6b:
            r0 = r0 & (-256(0xffffffffffffff00, float:NaN))
        L6d:
            int r11 = r11 + 3
            goto L75
        L70:
            r1 = -65536(0xffffffffffff0000, float:NaN)
            r0 = r0 & r1
            int r11 = r11 + 2
        L75:
            r10.currentTypeAnnotationTarget = r0
            int r0 = r9.readByte(r11)
            if (r0 != 0) goto L7f
            r1 = 0
            goto L86
        L7f:
            org.objectweb.asm.TypePath r1 = new org.objectweb.asm.TypePath
            byte[] r3 = r9.classFileBuffer
            r1.<init>(r3, r11)
        L86:
            r10.currentTypeAnnotationTargetPath = r1
            int r11 = r11 + r2
            int r0 = r0 * 2
            int r11 = r11 + r0
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.objectweb.asm.ClassReader.readTypeAnnotationTarget(org.objectweb.asm.Context, int):int");
    }

    private void readParameterAnnotations(MethodVisitor methodVisitor, Context context, int i, boolean z) {
        int elementValues = i + 1;
        int i2 = this.classFileBuffer[i] & 255;
        methodVisitor.visitAnnotableParameterCount(i2, z);
        char[] cArr = context.charBuffer;
        for (int i3 = 0; i3 < i2; i3++) {
            int unsignedShort = readUnsignedShort(elementValues);
            elementValues += 2;
            while (true) {
                int i4 = unsignedShort - 1;
                if (unsignedShort > 0) {
                    elementValues = readElementValues(methodVisitor.visitParameterAnnotation(i3, readUTF8(elementValues, cArr), z), elementValues + 2, true, cArr);
                    unsignedShort = i4;
                }
            }
        }
    }

    private int readElementValues(AnnotationVisitor annotationVisitor, int i, boolean z, char[] cArr) {
        int unsignedShort = readUnsignedShort(i);
        int elementValue = i + 2;
        if (!z) {
            while (true) {
                int i2 = unsignedShort - 1;
                if (unsignedShort <= 0) {
                    break;
                }
                elementValue = readElementValue(annotationVisitor, elementValue, null, cArr);
                unsignedShort = i2;
            }
        } else {
            while (true) {
                int i3 = unsignedShort - 1;
                if (unsignedShort <= 0) {
                    break;
                }
                elementValue = readElementValue(annotationVisitor, elementValue + 2, readUTF8(elementValue, cArr), cArr);
                unsignedShort = i3;
            }
        }
        if (annotationVisitor != null) {
            annotationVisitor.visitEnd();
        }
        return elementValue;
    }

    /* JADX WARN: Removed duplicated region for block: B:97:0x0210  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int readElementValue(org.objectweb.asm.AnnotationVisitor r10, int r11, java.lang.String r12, char[] r13) {
        /*
            Method dump skipped, instruction units count: 576
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.objectweb.asm.ClassReader.readElementValue(org.objectweb.asm.AnnotationVisitor, int, java.lang.String, char[]):int");
    }

    private void computeImplicitFrame(Context context) {
        int i;
        String str = context.currentMethodDescriptor;
        Object[] objArr = context.currentFrameLocalTypes;
        int i2 = 0;
        if ((context.currentMethodAccessFlags & 8) == 0) {
            if ("<init>".equals(context.currentMethodName)) {
                objArr[0] = Opcodes.UNINITIALIZED_THIS;
            } else {
                objArr[0] = readClass(this.header + 2, context.charBuffer);
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
                                    context.currentFrameLocalCount = i2;
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

    private int readStackMapFrame(int i, boolean z, boolean z2, Context context) {
        int verificationTypeInfo;
        int i2;
        char[] cArr = context.charBuffer;
        Label[] labelArr = context.currentMethodLabels;
        if (z) {
            verificationTypeInfo = i + 1;
            i2 = this.classFileBuffer[i] & 255;
        } else {
            context.currentFrameOffset = -1;
            verificationTypeInfo = i;
            i2 = 255;
        }
        context.currentFrameLocalCountDelta = 0;
        if (i2 < 64) {
            context.currentFrameType = 3;
            context.currentFrameStackCount = 0;
        } else if (i2 < 128) {
            i2 -= 64;
            verificationTypeInfo = readVerificationTypeInfo(verificationTypeInfo, context.currentFrameStackTypes, 0, cArr, labelArr);
            context.currentFrameType = 4;
            context.currentFrameStackCount = 1;
        } else if (i2 >= 247) {
            int unsignedShort = readUnsignedShort(verificationTypeInfo);
            verificationTypeInfo += 2;
            if (i2 == 247) {
                verificationTypeInfo = readVerificationTypeInfo(verificationTypeInfo, context.currentFrameStackTypes, 0, cArr, labelArr);
                context.currentFrameType = 4;
                context.currentFrameStackCount = 1;
            } else if (i2 >= 248 && i2 < 251) {
                context.currentFrameType = 2;
                int i3 = 251 - i2;
                context.currentFrameLocalCountDelta = i3;
                context.currentFrameLocalCount -= i3;
                context.currentFrameStackCount = 0;
            } else if (i2 == 251) {
                context.currentFrameType = 3;
                context.currentFrameStackCount = 0;
            } else if (i2 < 255) {
                int i4 = i2 - 251;
                int i5 = z2 ? context.currentFrameLocalCount : 0;
                int i6 = i4;
                while (i6 > 0) {
                    verificationTypeInfo = readVerificationTypeInfo(verificationTypeInfo, context.currentFrameLocalTypes, i5, cArr, labelArr);
                    i6--;
                    i5++;
                }
                context.currentFrameType = 1;
                context.currentFrameLocalCountDelta = i4;
                context.currentFrameLocalCount += i4;
                context.currentFrameStackCount = 0;
            } else {
                int unsignedShort2 = readUnsignedShort(verificationTypeInfo);
                int verificationTypeInfo2 = verificationTypeInfo + 2;
                context.currentFrameType = 0;
                context.currentFrameLocalCountDelta = unsignedShort2;
                context.currentFrameLocalCount = unsignedShort2;
                for (int i7 = 0; i7 < unsignedShort2; i7++) {
                    verificationTypeInfo2 = readVerificationTypeInfo(verificationTypeInfo2, context.currentFrameLocalTypes, i7, cArr, labelArr);
                }
                int unsignedShort3 = readUnsignedShort(verificationTypeInfo2);
                verificationTypeInfo = verificationTypeInfo2 + 2;
                context.currentFrameStackCount = unsignedShort3;
                for (int i8 = 0; i8 < unsignedShort3; i8++) {
                    verificationTypeInfo = readVerificationTypeInfo(verificationTypeInfo, context.currentFrameStackTypes, i8, cArr, labelArr);
                }
            }
            i2 = unsignedShort;
        } else {
            throw new IllegalArgumentException();
        }
        int i9 = context.currentFrameOffset + i2 + 1;
        context.currentFrameOffset = i9;
        createLabel(i9, labelArr);
        return verificationTypeInfo;
    }

    private int readVerificationTypeInfo(int i, Object[] objArr, int i2, char[] cArr, Label[] labelArr) {
        int i3 = i + 1;
        switch (this.classFileBuffer[i] & 255) {
            case 0:
                objArr[i2] = Opcodes.TOP;
                return i3;
            case 1:
                objArr[i2] = Opcodes.INTEGER;
                return i3;
            case 2:
                objArr[i2] = Opcodes.FLOAT;
                return i3;
            case 3:
                objArr[i2] = Opcodes.DOUBLE;
                return i3;
            case 4:
                objArr[i2] = Opcodes.LONG;
                return i3;
            case 5:
                objArr[i2] = Opcodes.NULL;
                return i3;
            case 6:
                objArr[i2] = Opcodes.UNINITIALIZED_THIS;
                return i3;
            case 7:
                objArr[i2] = readClass(i3, cArr);
                break;
            case 8:
                objArr[i2] = createLabel(readUnsignedShort(i3), labelArr);
                break;
            default:
                throw new IllegalArgumentException();
        }
        return i3 + 2;
    }

    final int getFirstAttributeOffset() {
        int i = this.header;
        int unsignedShort = i + 8 + (readUnsignedShort(i + 6) * 2);
        int unsignedShort2 = readUnsignedShort(unsignedShort);
        int i2 = unsignedShort + 2;
        while (true) {
            int i3 = unsignedShort2 - 1;
            if (unsignedShort2 <= 0) {
                break;
            }
            int unsignedShort3 = readUnsignedShort(i2 + 6);
            i2 += 8;
            while (true) {
                int i4 = unsignedShort3 - 1;
                if (unsignedShort3 > 0) {
                    i2 += readInt(i2 + 2) + 6;
                    unsignedShort3 = i4;
                }
            }
            unsignedShort2 = i3;
        }
        int unsignedShort4 = readUnsignedShort(i2);
        int i5 = i2 + 2;
        while (true) {
            int i6 = unsignedShort4 - 1;
            if (unsignedShort4 <= 0) {
                return i5 + 2;
            }
            int unsignedShort5 = readUnsignedShort(i5 + 6);
            i5 += 8;
            while (true) {
                int i7 = unsignedShort5 - 1;
                if (unsignedShort5 > 0) {
                    i5 += readInt(i5 + 2) + 6;
                    unsignedShort5 = i7;
                }
            }
            unsignedShort4 = i6;
        }
    }

    private int[] readBootstrapMethodsAttribute(int i) {
        char[] cArr = new char[i];
        int firstAttributeOffset = getFirstAttributeOffset();
        for (int unsignedShort = readUnsignedShort(firstAttributeOffset - 2); unsignedShort > 0; unsignedShort--) {
            String utf8 = readUTF8(firstAttributeOffset, cArr);
            int i2 = readInt(firstAttributeOffset + 2);
            int i3 = firstAttributeOffset + 6;
            if ("BootstrapMethods".equals(utf8)) {
                int unsignedShort2 = readUnsignedShort(i3);
                int[] iArr = new int[unsignedShort2];
                int unsignedShort3 = i3 + 2;
                for (int i4 = 0; i4 < unsignedShort2; i4++) {
                    iArr[i4] = unsignedShort3;
                    unsignedShort3 += (readUnsignedShort(unsignedShort3 + 2) * 2) + 4;
                }
                return iArr;
            }
            firstAttributeOffset = i3 + i2;
        }
        throw new IllegalArgumentException();
    }

    private Attribute readAttribute(Attribute[] attributeArr, String str, int i, int i2, char[] cArr, int i3, Label[] labelArr) {
        for (Attribute attribute : attributeArr) {
            if (attribute.type.equals(str)) {
                return attribute.read(this, i, i2, cArr, i3, labelArr);
            }
        }
        return new Attribute(str).read(this, i, i2, null, -1, null);
    }

    public int getItemCount() {
        return this.cpInfoOffsets.length;
    }

    public int getItem(int i) {
        return this.cpInfoOffsets[i];
    }

    public int getMaxStringLength() {
        return this.maxStringLength;
    }

    public int readByte(int i) {
        return this.classFileBuffer[i] & 255;
    }

    public int readUnsignedShort(int i) {
        byte[] bArr = this.classFileBuffer;
        return (bArr[i + 1] & 255) | ((bArr[i] & 255) << 8);
    }

    public short readShort(int i) {
        byte[] bArr = this.classFileBuffer;
        return (short) ((bArr[i + 1] & 255) | ((bArr[i] & 255) << 8));
    }

    public int readInt(int i) {
        byte[] bArr = this.classFileBuffer;
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
        return readUtf(unsignedShort, cArr);
    }

    final String readUtf(int i, char[] cArr) {
        String[] strArr = this.constantUtf8Values;
        String str = strArr[i];
        if (str != null) {
            return str;
        }
        int i2 = this.cpInfoOffsets[i];
        String utf = readUtf(i2 + 2, readUnsignedShort(i2), cArr);
        strArr[i] = utf;
        return utf;
    }

    private String readUtf(int i, int i2, char[] cArr) {
        int i3;
        int i4 = i2 + i;
        byte[] bArr = this.classFileBuffer;
        int i5 = 0;
        while (i < i4) {
            int i6 = i + 1;
            byte b = bArr[i];
            if ((b & ByteCompanionObject.MIN_VALUE) == 0) {
                i3 = i5 + 1;
                cArr[i5] = (char) (b & 127);
            } else if ((b & 224) == 192) {
                cArr[i5] = (char) (((b & 31) << 6) + (bArr[i6] & 63));
                i5++;
                i = i6 + 1;
            } else {
                i3 = i5 + 1;
                int i7 = i6 + 1;
                int i8 = ((b & 15) << 12) + ((bArr[i6] & 63) << 6);
                i6 = i7 + 1;
                cArr[i5] = (char) (i8 + (bArr[i7] & 63));
            }
            i = i6;
            i5 = i3;
        }
        return new String(cArr, 0, i5);
    }

    private String readStringish(int i, char[] cArr) {
        return readUTF8(this.cpInfoOffsets[readUnsignedShort(i)], cArr);
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

    private ConstantDynamic readConstantDynamic(int i, char[] cArr) {
        ConstantDynamic constantDynamic = this.constantDynamicValues[i];
        if (constantDynamic != null) {
            return constantDynamic;
        }
        int[] iArr = this.cpInfoOffsets;
        int i2 = iArr[i];
        int i3 = iArr[readUnsignedShort(i2 + 2)];
        String utf8 = readUTF8(i3, cArr);
        String utf82 = readUTF8(i3 + 2, cArr);
        int i4 = this.bootstrapMethodOffsets[readUnsignedShort(i2)];
        Handle handle = (Handle) readConst(readUnsignedShort(i4), cArr);
        int unsignedShort = readUnsignedShort(i4 + 2);
        Object[] objArr = new Object[unsignedShort];
        int i5 = i4 + 4;
        for (int i6 = 0; i6 < unsignedShort; i6++) {
            objArr[i6] = readConst(readUnsignedShort(i5), cArr);
            i5 += 2;
        }
        ConstantDynamic[] constantDynamicArr = this.constantDynamicValues;
        ConstantDynamic constantDynamic2 = new ConstantDynamic(utf8, utf82, handle, objArr);
        constantDynamicArr[i] = constantDynamic2;
        return constantDynamic2;
    }

    public Object readConst(int i, char[] cArr) {
        int i2 = this.cpInfoOffsets[i];
        byte b = this.classFileBuffer[i2 - 1];
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
                switch (b) {
                    case 15:
                        int i3 = readByte(i2);
                        int i4 = this.cpInfoOffsets[readUnsignedShort(i2 + 1)];
                        int i5 = this.cpInfoOffsets[readUnsignedShort(i4 + 2)];
                        return new Handle(i3, readClass(i4, cArr), readUTF8(i5, cArr), readUTF8(i5 + 2, cArr), this.classFileBuffer[i4 - 1] == 11);
                    case 16:
                        return Type.getMethodType(readUTF8(i2, cArr));
                    case 17:
                        return readConstantDynamic(i, cArr);
                    default:
                        throw new IllegalArgumentException();
                }
        }
    }
}
