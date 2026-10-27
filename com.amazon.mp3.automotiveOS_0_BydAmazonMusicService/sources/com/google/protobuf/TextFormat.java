package com.google.protobuf;

import com.amazon.digitalmusicplayback.AudioFormat;
import com.amplifyframework.core.model.ModelIdentifier;
import com.google.protobuf.Descriptors;
import com.google.protobuf.DynamicMessage;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.lang.Character;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* JADX INFO: loaded from: classes7.dex */
public final class TextFormat {
    private static final Logger logger = Logger.getLogger(TextFormat.class.getName());
    private static final Parser PARSER = Parser.newBuilder().build();

    private static int digitValue(final byte c) {
        if (48 > c || c > 57) {
            return ((97 > c || c > 122) ? c - 65 : c - 97) + 10;
        }
        return c - 48;
    }

    private static boolean isHex(final byte c) {
        return (48 <= c && c <= 57) || (97 <= c && c <= 102) || (65 <= c && c <= 70);
    }

    private static boolean isOctal(final byte c) {
        return 48 <= c && c <= 55;
    }

    private TextFormat() {
    }

    public static Printer printer() {
        return Printer.DEFAULT;
    }

    public static final class Printer {
        private static final Printer DEFAULT = new Printer(true, TypeRegistry.getEmptyTypeRegistry());
        private final boolean escapeNonAscii;
        private final TypeRegistry typeRegistry;

        private Printer(boolean escapeNonAscii, TypeRegistry typeRegistry) {
            this.escapeNonAscii = escapeNonAscii;
            this.typeRegistry = typeRegistry;
        }

        public void print(final MessageOrBuilder message, final Appendable output) throws IOException {
            print(message, TextFormat.multiLineOutput(output));
        }

        public void print(final UnknownFieldSet fields, final Appendable output) throws IOException {
            printUnknownFields(fields, TextFormat.multiLineOutput(output));
        }

        private void print(final MessageOrBuilder message, final TextGenerator generator) throws IOException {
            if (message.getDescriptorForType().getFullName().equals("google.protobuf.Any") && printAny(message, generator)) {
                return;
            }
            printMessage(message, generator);
        }

        private boolean printAny(final MessageOrBuilder message, final TextGenerator generator) throws IOException {
            Descriptors.Descriptor descriptorForType = message.getDescriptorForType();
            Descriptors.FieldDescriptor fieldDescriptorFindFieldByNumber = descriptorForType.findFieldByNumber(1);
            Descriptors.FieldDescriptor fieldDescriptorFindFieldByNumber2 = descriptorForType.findFieldByNumber(2);
            if (fieldDescriptorFindFieldByNumber != null && fieldDescriptorFindFieldByNumber.getType() == Descriptors.FieldDescriptor.Type.STRING && fieldDescriptorFindFieldByNumber2 != null && fieldDescriptorFindFieldByNumber2.getType() == Descriptors.FieldDescriptor.Type.BYTES) {
                String str = (String) message.getField(fieldDescriptorFindFieldByNumber);
                if (str.isEmpty()) {
                    return false;
                }
                Object field = message.getField(fieldDescriptorFindFieldByNumber2);
                try {
                    Descriptors.Descriptor descriptorForTypeUrl = this.typeRegistry.getDescriptorForTypeUrl(str);
                    if (descriptorForTypeUrl == null) {
                        return false;
                    }
                    DynamicMessage.Builder builderNewBuilderForType = DynamicMessage.getDefaultInstance(descriptorForTypeUrl).newBuilderForType();
                    builderNewBuilderForType.mergeFrom((ByteString) field);
                    generator.print("[");
                    generator.print(str);
                    generator.print("] {");
                    generator.eol();
                    generator.indent();
                    print(builderNewBuilderForType, generator);
                    generator.outdent();
                    generator.print("}");
                    generator.eol();
                    return true;
                } catch (InvalidProtocolBufferException unused) {
                }
            }
            return false;
        }

        private void printField(final Descriptors.FieldDescriptor field, final Object value, final TextGenerator generator) throws IOException {
            if (field.isMapField()) {
                ArrayList arrayList = new ArrayList();
                Iterator it = ((List) value).iterator();
                while (it.hasNext()) {
                    arrayList.add(new MapEntryAdapter(it.next(), field));
                }
                Collections.sort(arrayList);
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    printSingleField(field, ((MapEntryAdapter) it2.next()).getEntry(), generator);
                }
                return;
            }
            if (field.isRepeated()) {
                Iterator it3 = ((List) value).iterator();
                while (it3.hasNext()) {
                    printSingleField(field, it3.next(), generator);
                }
                return;
            }
            printSingleField(field, value, generator);
        }

        private static class MapEntryAdapter implements Comparable<MapEntryAdapter> {
            private Object entry;
            private final Descriptors.FieldDescriptor.JavaType fieldType;
            private MapEntry mapEntry;

            MapEntryAdapter(Object entry, Descriptors.FieldDescriptor fieldDescriptor) {
                if (entry instanceof MapEntry) {
                    this.mapEntry = (MapEntry) entry;
                } else {
                    this.entry = entry;
                }
                this.fieldType = extractFieldType(fieldDescriptor);
            }

            private static Descriptors.FieldDescriptor.JavaType extractFieldType(Descriptors.FieldDescriptor fieldDescriptor) {
                return fieldDescriptor.getMessageType().getFields().get(0).getJavaType();
            }

            Object getKey() {
                MapEntry mapEntry = this.mapEntry;
                if (mapEntry != null) {
                    return mapEntry.getKey();
                }
                return null;
            }

            Object getEntry() {
                MapEntry mapEntry = this.mapEntry;
                return mapEntry != null ? mapEntry : this.entry;
            }

            @Override // java.lang.Comparable
            public int compareTo(MapEntryAdapter b) {
                if (getKey() == null || b.getKey() == null) {
                    TextFormat.logger.info("Invalid key for map field.");
                    return -1;
                }
                int i = AnonymousClass1.$SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$JavaType[this.fieldType.ordinal()];
                if (i == 1) {
                    return Boolean.valueOf(((Boolean) getKey()).booleanValue()).compareTo(Boolean.valueOf(((Boolean) b.getKey()).booleanValue()));
                }
                if (i == 2) {
                    return Long.valueOf(((Long) getKey()).longValue()).compareTo(Long.valueOf(((Long) b.getKey()).longValue()));
                }
                if (i == 3) {
                    return Integer.valueOf(((Integer) getKey()).intValue()).compareTo(Integer.valueOf(((Integer) b.getKey()).intValue()));
                }
                if (i != 4) {
                    return 0;
                }
                String str = (String) getKey();
                String str2 = (String) b.getKey();
                if (str == null && str2 == null) {
                    return 0;
                }
                if (str == null && str2 != null) {
                    return -1;
                }
                if (str == null || str2 != null) {
                    return str.compareTo(str2);
                }
                return 1;
            }
        }

        private void printFieldValue(final Descriptors.FieldDescriptor field, final Object value, final TextGenerator generator) throws IOException {
            String strReplace;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[field.getType().ordinal()]) {
                case 1:
                case 2:
                case 3:
                    generator.print(((Integer) value).toString());
                    break;
                case 4:
                case 5:
                case 6:
                    generator.print(((Long) value).toString());
                    break;
                case 7:
                    generator.print(((Boolean) value).toString());
                    break;
                case 8:
                    generator.print(((Float) value).toString());
                    break;
                case 9:
                    generator.print(((Double) value).toString());
                    break;
                case 10:
                case 11:
                    generator.print(TextFormat.unsignedToString(((Integer) value).intValue()));
                    break;
                case 12:
                case 13:
                    generator.print(TextFormat.unsignedToString(((Long) value).longValue()));
                    break;
                case 14:
                    generator.print(ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR);
                    if (this.escapeNonAscii) {
                        strReplace = TextFormatEscaper.escapeText((String) value);
                    } else {
                        strReplace = TextFormat.escapeDoubleQuotesAndBackslashes((String) value).replace("\n", "\\n");
                    }
                    generator.print(strReplace);
                    generator.print(ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR);
                    break;
                case 15:
                    generator.print(ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR);
                    if (value instanceof ByteString) {
                        generator.print(TextFormat.escapeBytes((ByteString) value));
                    } else {
                        generator.print(TextFormat.escapeBytes((byte[]) value));
                    }
                    generator.print(ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR);
                    break;
                case 16:
                    generator.print(((Descriptors.EnumValueDescriptor) value).getName());
                    break;
                case 17:
                case 18:
                    print((MessageOrBuilder) value, generator);
                    break;
            }
        }

        public String printToString(final MessageOrBuilder message) {
            try {
                StringBuilder sb = new StringBuilder();
                print(message, sb);
                return sb.toString();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        public String printToString(final UnknownFieldSet fields) {
            try {
                StringBuilder sb = new StringBuilder();
                print(fields, sb);
                return sb.toString();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        private static void printUnknownFieldValue(final int tag, final Object value, final TextGenerator generator) throws IOException {
            int tagWireType = WireFormat.getTagWireType(tag);
            if (tagWireType == 0) {
                generator.print(TextFormat.unsignedToString(((Long) value).longValue()));
                return;
            }
            if (tagWireType == 1) {
                generator.print(String.format(null, "0x%016x", (Long) value));
                return;
            }
            if (tagWireType != 2) {
                if (tagWireType == 3) {
                    printUnknownFields((UnknownFieldSet) value, generator);
                    return;
                } else {
                    if (tagWireType == 5) {
                        generator.print(String.format(null, "0x%08x", (Integer) value));
                        return;
                    }
                    throw new IllegalArgumentException("Bad tag: " + tag);
                }
            }
            try {
                UnknownFieldSet from = UnknownFieldSet.parseFrom((ByteString) value);
                generator.print("{");
                generator.eol();
                generator.indent();
                printUnknownFields(from, generator);
                generator.outdent();
                generator.print("}");
            } catch (InvalidProtocolBufferException unused) {
                generator.print(ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR);
                generator.print(TextFormat.escapeBytes((ByteString) value));
                generator.print(ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR);
            }
        }

        private void printMessage(final MessageOrBuilder message, final TextGenerator generator) throws IOException {
            for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : message.getAllFields().entrySet()) {
                printField(entry.getKey(), entry.getValue(), generator);
            }
            printUnknownFields(message.getUnknownFields(), generator);
        }

        private void printSingleField(final Descriptors.FieldDescriptor field, final Object value, final TextGenerator generator) throws IOException {
            if (field.isExtension()) {
                generator.print("[");
                if (field.getContainingType().getOptions().getMessageSetWireFormat() && field.getType() == Descriptors.FieldDescriptor.Type.MESSAGE && field.isOptional() && field.getExtensionScope() == field.getMessageType()) {
                    generator.print(field.getMessageType().getFullName());
                } else {
                    generator.print(field.getFullName());
                }
                generator.print("]");
            } else if (field.getType() == Descriptors.FieldDescriptor.Type.GROUP) {
                generator.print(field.getMessageType().getName());
            } else {
                generator.print(field.getName());
            }
            Descriptors.FieldDescriptor.JavaType javaType = field.getJavaType();
            Descriptors.FieldDescriptor.JavaType javaType2 = Descriptors.FieldDescriptor.JavaType.MESSAGE;
            if (javaType == javaType2) {
                generator.print(" {");
                generator.eol();
                generator.indent();
            } else {
                generator.print(": ");
            }
            printFieldValue(field, value, generator);
            if (field.getJavaType() == javaType2) {
                generator.outdent();
                generator.print("}");
            }
            generator.eol();
        }

        private static void printUnknownFields(final UnknownFieldSet unknownFields, final TextGenerator generator) throws IOException {
            for (Map.Entry<Integer, UnknownFieldSet.Field> entry : unknownFields.asMap().entrySet()) {
                int iIntValue = entry.getKey().intValue();
                UnknownFieldSet.Field value = entry.getValue();
                printUnknownField(iIntValue, 0, value.getVarintList(), generator);
                printUnknownField(iIntValue, 5, value.getFixed32List(), generator);
                printUnknownField(iIntValue, 1, value.getFixed64List(), generator);
                printUnknownField(iIntValue, 2, value.getLengthDelimitedList(), generator);
                for (UnknownFieldSet unknownFieldSet : value.getGroupList()) {
                    generator.print(entry.getKey().toString());
                    generator.print(" {");
                    generator.eol();
                    generator.indent();
                    printUnknownFields(unknownFieldSet, generator);
                    generator.outdent();
                    generator.print("}");
                    generator.eol();
                }
            }
        }

        private static void printUnknownField(final int number, final int wireType, final List<?> values, final TextGenerator generator) throws IOException {
            for (Object obj : values) {
                generator.print(String.valueOf(number));
                generator.print(": ");
                printUnknownFieldValue(wireType, obj, generator);
                generator.eol();
            }
        }
    }

    /* JADX INFO: renamed from: com.google.protobuf.TextFormat$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$JavaType;
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type;

        static {
            int[] iArr = new int[Descriptors.FieldDescriptor.Type.values().length];
            $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type = iArr;
            try {
                iArr[Descriptors.FieldDescriptor.Type.INT32.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.SINT32.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.SFIXED32.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.INT64.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.SINT64.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.SFIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.BOOL.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.FLOAT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.DOUBLE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.UINT32.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.FIXED32.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.UINT64.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.FIXED64.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.STRING.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.BYTES.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.ENUM.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.MESSAGE.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.GROUP.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            int[] iArr2 = new int[Descriptors.FieldDescriptor.JavaType.values().length];
            $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$JavaType = iArr2;
            try {
                iArr2[Descriptors.FieldDescriptor.JavaType.BOOLEAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$JavaType[Descriptors.FieldDescriptor.JavaType.LONG.ordinal()] = 2;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$JavaType[Descriptors.FieldDescriptor.JavaType.INT.ordinal()] = 3;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$JavaType[Descriptors.FieldDescriptor.JavaType.STRING.ordinal()] = 4;
            } catch (NoSuchFieldError unused22) {
            }
        }
    }

    public static String unsignedToString(final int value) {
        if (value >= 0) {
            return Integer.toString(value);
        }
        return Long.toString(((long) value) & 4294967295L);
    }

    public static String unsignedToString(final long value) {
        if (value >= 0) {
            return Long.toString(value);
        }
        return BigInteger.valueOf(value & Long.MAX_VALUE).setBit(63).toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static TextGenerator multiLineOutput(Appendable output) {
        return new TextGenerator(output, false, null);
    }

    private static final class TextGenerator {
        private boolean atStartOfLine;
        private final StringBuilder indent;
        private final Appendable output;
        private final boolean singleLineMode;

        /* synthetic */ TextGenerator(Appendable appendable, boolean z, AnonymousClass1 anonymousClass1) {
            this(appendable, z);
        }

        private TextGenerator(final Appendable output, boolean singleLineMode) {
            this.indent = new StringBuilder();
            this.atStartOfLine = false;
            this.output = output;
            this.singleLineMode = singleLineMode;
        }

        public void indent() {
            this.indent.append("  ");
        }

        public void outdent() {
            int length = this.indent.length();
            if (length == 0) {
                throw new IllegalArgumentException(" Outdent() without matching Indent().");
            }
            this.indent.setLength(length - 2);
        }

        public void print(final CharSequence text) throws IOException {
            if (this.atStartOfLine) {
                this.atStartOfLine = false;
                this.output.append(this.singleLineMode ? " " : this.indent);
            }
            this.output.append(text);
        }

        public void eol() throws IOException {
            if (!this.singleLineMode) {
                this.output.append("\n");
            }
            this.atStartOfLine = true;
        }
    }

    public static class Parser {
        private final boolean allowUnknownEnumValues;
        private final boolean allowUnknownExtensions;
        private final boolean allowUnknownFields;
        private TextFormatParseInfoTree$Builder parseInfoTreeBuilder;
        private final SingularOverwritePolicy singularOverwritePolicy;
        private final TypeRegistry typeRegistry;

        public enum SingularOverwritePolicy {
            ALLOW_SINGULAR_OVERWRITES,
            FORBID_SINGULAR_OVERWRITES
        }

        /* synthetic */ Parser(TypeRegistry typeRegistry, boolean z, boolean z2, boolean z3, SingularOverwritePolicy singularOverwritePolicy, TextFormatParseInfoTree$Builder textFormatParseInfoTree$Builder, AnonymousClass1 anonymousClass1) {
            this(typeRegistry, z, z2, z3, singularOverwritePolicy, textFormatParseInfoTree$Builder);
        }

        private Parser(TypeRegistry typeRegistry, boolean allowUnknownFields, boolean allowUnknownEnumValues, boolean allowUnknownExtensions, SingularOverwritePolicy singularOverwritePolicy, TextFormatParseInfoTree$Builder parseInfoTreeBuilder) {
            this.typeRegistry = typeRegistry;
            this.allowUnknownFields = allowUnknownFields;
            this.allowUnknownEnumValues = allowUnknownEnumValues;
            this.allowUnknownExtensions = allowUnknownExtensions;
            this.singularOverwritePolicy = singularOverwritePolicy;
            this.parseInfoTreeBuilder = parseInfoTreeBuilder;
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public static class Builder {
            private boolean allowUnknownFields = false;
            private boolean allowUnknownEnumValues = false;
            private boolean allowUnknownExtensions = false;
            private SingularOverwritePolicy singularOverwritePolicy = SingularOverwritePolicy.ALLOW_SINGULAR_OVERWRITES;
            private TextFormatParseInfoTree$Builder parseInfoTreeBuilder = null;
            private TypeRegistry typeRegistry = TypeRegistry.getEmptyTypeRegistry();

            public Parser build() {
                return new Parser(this.typeRegistry, this.allowUnknownFields, this.allowUnknownEnumValues, this.allowUnknownExtensions, this.singularOverwritePolicy, this.parseInfoTreeBuilder, null);
            }
        }
    }

    public static String escapeBytes(ByteString input) {
        return TextFormatEscaper.escapeBytes(input);
    }

    public static String escapeBytes(byte[] input) {
        return TextFormatEscaper.escapeBytes(input);
    }

    public static ByteString unescapeBytes(CharSequence charString) throws InvalidEscapeSequenceException {
        int i;
        int i2;
        int i3;
        int length;
        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8(charString.toString());
        int size = byteStringCopyFromUtf8.size();
        byte[] bArr = new byte[size];
        int i4 = 0;
        int i5 = 0;
        while (i4 < byteStringCopyFromUtf8.size()) {
            byte bByteAt = byteStringCopyFromUtf8.byteAt(i4);
            if (bByteAt == 92) {
                i4++;
                if (i4 < byteStringCopyFromUtf8.size()) {
                    byte bByteAt2 = byteStringCopyFromUtf8.byteAt(i4);
                    if (isOctal(bByteAt2)) {
                        int iDigitValue = digitValue(bByteAt2);
                        int i6 = i4 + 1;
                        if (i6 < byteStringCopyFromUtf8.size() && isOctal(byteStringCopyFromUtf8.byteAt(i6))) {
                            iDigitValue = (iDigitValue * 8) + digitValue(byteStringCopyFromUtf8.byteAt(i6));
                            i4 = i6;
                        }
                        int i7 = i4 + 1;
                        if (i7 < byteStringCopyFromUtf8.size() && isOctal(byteStringCopyFromUtf8.byteAt(i7))) {
                            iDigitValue = (iDigitValue * 8) + digitValue(byteStringCopyFromUtf8.byteAt(i7));
                            i4 = i7;
                        }
                        i = i5 + 1;
                        bArr[i5] = (byte) iDigitValue;
                    } else {
                        if (bByteAt2 == 34) {
                            i2 = i5 + 1;
                            bArr[i5] = 34;
                        } else if (bByteAt2 == 39) {
                            i2 = i5 + 1;
                            bArr[i5] = 39;
                        } else if (bByteAt2 != 63) {
                            if (bByteAt2 == 85) {
                                int i8 = i4 + 1;
                                i3 = i8 + 7;
                                if (i3 >= byteStringCopyFromUtf8.size()) {
                                    throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\U' with too few hex chars");
                                }
                                int iDigitValue2 = 0;
                                int i9 = i8;
                                while (true) {
                                    int i10 = i8 + 8;
                                    if (i9 < i10) {
                                        byte bByteAt3 = byteStringCopyFromUtf8.byteAt(i9);
                                        if (!isHex(bByteAt3)) {
                                            throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\U' with too few hex chars");
                                        }
                                        iDigitValue2 = (iDigitValue2 << 4) | digitValue(bByteAt3);
                                        i9++;
                                    } else {
                                        if (!Character.isValidCodePoint(iDigitValue2)) {
                                            throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\U" + byteStringCopyFromUtf8.substring(i8, i10).toStringUtf8() + "' is not a valid code point value");
                                        }
                                        Character.UnicodeBlock unicodeBlockOf = Character.UnicodeBlock.of(iDigitValue2);
                                        if (unicodeBlockOf != null && (unicodeBlockOf.equals(Character.UnicodeBlock.LOW_SURROGATES) || unicodeBlockOf.equals(Character.UnicodeBlock.HIGH_SURROGATES) || unicodeBlockOf.equals(Character.UnicodeBlock.HIGH_PRIVATE_USE_SURROGATES))) {
                                            throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\U" + byteStringCopyFromUtf8.substring(i8, i10).toStringUtf8() + "' refers to a surrogate code unit");
                                        }
                                        byte[] bytes = new String(new int[]{iDigitValue2}, 0, 1).getBytes(Internal.UTF_8);
                                        System.arraycopy(bytes, 0, bArr, i5, bytes.length);
                                        length = bytes.length;
                                    }
                                }
                            } else if (bByteAt2 == 92) {
                                i2 = i5 + 1;
                                bArr[i5] = 92;
                            } else if (bByteAt2 == 102) {
                                i2 = i5 + 1;
                                bArr[i5] = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                            } else if (bByteAt2 == 110) {
                                i2 = i5 + 1;
                                bArr[i5] = 10;
                            } else if (bByteAt2 == 114) {
                                i2 = i5 + 1;
                                bArr[i5] = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                            } else if (bByteAt2 == 120) {
                                i4++;
                                if (i4 < byteStringCopyFromUtf8.size() && isHex(byteStringCopyFromUtf8.byteAt(i4))) {
                                    int iDigitValue3 = digitValue(byteStringCopyFromUtf8.byteAt(i4));
                                    int i11 = i4 + 1;
                                    if (i11 < byteStringCopyFromUtf8.size() && isHex(byteStringCopyFromUtf8.byteAt(i11))) {
                                        iDigitValue3 = (iDigitValue3 * 16) + digitValue(byteStringCopyFromUtf8.byteAt(i11));
                                        i4 = i11;
                                    }
                                    i = i5 + 1;
                                    bArr[i5] = (byte) iDigitValue3;
                                } else {
                                    throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\x' with no digits");
                                }
                            } else if (bByteAt2 == 97) {
                                i2 = i5 + 1;
                                bArr[i5] = 7;
                            } else if (bByteAt2 == 98) {
                                i2 = i5 + 1;
                                bArr[i5] = 8;
                            } else {
                                switch (bByteAt2) {
                                    case 116:
                                        i2 = i5 + 1;
                                        bArr[i5] = 9;
                                        break;
                                    case 117:
                                        int i12 = i4 + 1;
                                        i3 = i12 + 3;
                                        if (i3 < byteStringCopyFromUtf8.size() && isHex(byteStringCopyFromUtf8.byteAt(i12))) {
                                            int i13 = i12 + 1;
                                            if (isHex(byteStringCopyFromUtf8.byteAt(i13))) {
                                                int i14 = i12 + 2;
                                                if (isHex(byteStringCopyFromUtf8.byteAt(i14)) && isHex(byteStringCopyFromUtf8.byteAt(i3))) {
                                                    char cDigitValue = (char) ((digitValue(byteStringCopyFromUtf8.byteAt(i12)) << 12) | (digitValue(byteStringCopyFromUtf8.byteAt(i13)) << 8) | (digitValue(byteStringCopyFromUtf8.byteAt(i14)) << 4) | digitValue(byteStringCopyFromUtf8.byteAt(i3)));
                                                    if (cDigitValue >= 55296 && cDigitValue <= 57343) {
                                                        throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\u' refers to a surrogate");
                                                    }
                                                    byte[] bytes2 = Character.toString(cDigitValue).getBytes(Internal.UTF_8);
                                                    System.arraycopy(bytes2, 0, bArr, i5, bytes2.length);
                                                    length = bytes2.length;
                                                    break;
                                                }
                                            }
                                        }
                                        throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\u' with too few hex chars");
                                    case 118:
                                        i2 = i5 + 1;
                                        bArr[i5] = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                                        break;
                                    default:
                                        throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\" + ((char) bByteAt2) + '\'');
                                }
                            }
                            i5 += length;
                            i4 = i3;
                            i4++;
                        } else {
                            i2 = i5 + 1;
                            bArr[i5] = 63;
                        }
                        i5 = i2;
                        i4++;
                    }
                } else {
                    throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\' at end of string.");
                }
            } else {
                i = i5 + 1;
                bArr[i5] = bByteAt;
            }
            i5 = i;
            i4++;
        }
        if (size == i5) {
            return ByteString.wrap(bArr);
        }
        return ByteString.copyFrom(bArr, 0, i5);
    }

    public static class InvalidEscapeSequenceException extends IOException {
        InvalidEscapeSequenceException(final String description) {
            super(description);
        }
    }

    public static String escapeDoubleQuotesAndBackslashes(final String input) {
        return TextFormatEscaper.escapeDoubleQuotesAndBackslashes(input);
    }

    static int parseInt32(final String text) throws NumberFormatException {
        return (int) parseInteger(text, true, false);
    }

    static int parseUInt32(final String text) throws NumberFormatException {
        return (int) parseInteger(text, false, false);
    }

    static long parseInt64(final String text) throws NumberFormatException {
        return parseInteger(text, true, true);
    }

    static long parseUInt64(final String text) throws NumberFormatException {
        return parseInteger(text, false, true);
    }

    private static long parseInteger(final String text, final boolean isSigned, final boolean isLong) throws NumberFormatException {
        int i = 0;
        boolean z = true;
        if (!text.startsWith("-", 0)) {
            z = false;
        } else {
            if (!isSigned) {
                throw new NumberFormatException("Number must be positive: " + text);
            }
            i = 1;
        }
        int i2 = 10;
        if (text.startsWith("0x", i)) {
            i += 2;
            i2 = 16;
        } else if (text.startsWith("0", i)) {
            i2 = 8;
        }
        String strSubstring = text.substring(i);
        if (strSubstring.length() < 16) {
            long j = Long.parseLong(strSubstring, i2);
            if (z) {
                j = -j;
            }
            if (isLong) {
                return j;
            }
            if (isSigned) {
                if (j <= 2147483647L && j >= -2147483648L) {
                    return j;
                }
                throw new NumberFormatException("Number out of range for 32-bit signed integer: " + text);
            }
            if (j < AudioFormat.WIDE_RIGHT && j >= 0) {
                return j;
            }
            throw new NumberFormatException("Number out of range for 32-bit unsigned integer: " + text);
        }
        BigInteger bigInteger = new BigInteger(strSubstring, i2);
        if (z) {
            bigInteger = bigInteger.negate();
        }
        if (isLong) {
            if (isSigned) {
                if (bigInteger.bitLength() > 63) {
                    throw new NumberFormatException("Number out of range for 64-bit signed integer: " + text);
                }
            } else if (bigInteger.bitLength() > 64) {
                throw new NumberFormatException("Number out of range for 64-bit unsigned integer: " + text);
            }
        } else if (isSigned) {
            if (bigInteger.bitLength() > 31) {
                throw new NumberFormatException("Number out of range for 32-bit signed integer: " + text);
            }
        } else if (bigInteger.bitLength() > 32) {
            throw new NumberFormatException("Number out of range for 32-bit unsigned integer: " + text);
        }
        return bigInteger.longValue();
    }
}
