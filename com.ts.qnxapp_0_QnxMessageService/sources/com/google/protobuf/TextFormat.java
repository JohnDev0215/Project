package com.google.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.Message;
import com.google.protobuf.MessageReflection;
import com.google.protobuf.TextFormatParseInfoTree;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.lang.Character;
import java.math.BigInteger;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public final class TextFormat {
    private static final String DEBUG_STRING_SILENT_MARKER = "\t ";
    private static final Logger logger = Logger.getLogger(TextFormat.class.getName());
    private static final Parser PARSER = Parser.newBuilder().build();

    private TextFormat() {
    }

    @Deprecated
    public static void print(MessageOrBuilder message, Appendable output) throws IOException {
        printer().print(message, output);
    }

    @Deprecated
    public static void print(UnknownFieldSet fields, Appendable output) throws IOException {
        printer().print(fields, output);
    }

    @Deprecated
    public static void printUnicode(MessageOrBuilder message, Appendable output) throws IOException {
        printer().escapingNonAscii(false).print(message, output);
    }

    @Deprecated
    public static void printUnicode(UnknownFieldSet fields, Appendable output) throws IOException {
        printer().escapingNonAscii(false).print(fields, output);
    }

    public static String shortDebugString(MessageOrBuilder message) {
        return printer().shortDebugString(message);
    }

    @Deprecated
    public static String shortDebugString(Descriptors.FieldDescriptor field, Object value) {
        return printer().shortDebugString(field, value);
    }

    @Deprecated
    public static String shortDebugString(UnknownFieldSet fields) {
        return printer().shortDebugString(fields);
    }

    @Deprecated
    public static String printToString(MessageOrBuilder message) {
        return printer().printToString(message);
    }

    @Deprecated
    public static String printToString(UnknownFieldSet fields) {
        return printer().printToString(fields);
    }

    @Deprecated
    public static String printToUnicodeString(MessageOrBuilder message) {
        return printer().escapingNonAscii(false).printToString(message);
    }

    @Deprecated
    public static String printToUnicodeString(UnknownFieldSet fields) {
        return printer().escapingNonAscii(false).printToString(fields);
    }

    @Deprecated
    public static void printField(Descriptors.FieldDescriptor field, Object value, Appendable output) throws IOException {
        printer().printField(field, value, output);
    }

    @Deprecated
    public static String printFieldToString(Descriptors.FieldDescriptor field, Object value) {
        return printer().printFieldToString(field, value);
    }

    @Deprecated
    public static void printUnicodeFieldValue(Descriptors.FieldDescriptor field, Object value, Appendable output) throws IOException {
        printer().escapingNonAscii(false).printFieldValue(field, value, output);
    }

    @Deprecated
    public static void printFieldValue(Descriptors.FieldDescriptor field, Object value, Appendable output) throws IOException {
        printer().printFieldValue(field, value, output);
    }

    public static void printUnknownFieldValue(int tag, Object value, Appendable output) throws IOException {
        printUnknownFieldValue(tag, value, multiLineOutput(output));
    }

    private static void printUnknownFieldValue(int tag, Object value, TextGenerator generator) throws IOException {
        int tagWireType = WireFormat.getTagWireType(tag);
        if (tagWireType == 0) {
            generator.print(unsignedToString(((Long) value).longValue()));
            return;
        }
        if (tagWireType == 1) {
            generator.print(String.format((Locale) null, "0x%016x", (Long) value));
            return;
        }
        if (tagWireType != 2) {
            if (tagWireType == 3) {
                Printer.printUnknownFields((UnknownFieldSet) value, generator);
                return;
            } else {
                if (tagWireType == 5) {
                    generator.print(String.format((Locale) null, "0x%08x", (Integer) value));
                    return;
                }
                throw new IllegalArgumentException("Bad tag: " + tag);
            }
        }
        try {
            UnknownFieldSet message = UnknownFieldSet.parseFrom((ByteString) value);
            generator.print("{");
            generator.eol();
            generator.indent();
            Printer.printUnknownFields(message, generator);
            generator.outdent();
            generator.print("}");
        } catch (InvalidProtocolBufferException e) {
            generator.print("\"");
            generator.print(escapeBytes((ByteString) value));
            generator.print("\"");
        }
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

        public Printer escapingNonAscii(boolean escapeNonAscii) {
            return new Printer(escapeNonAscii, this.typeRegistry);
        }

        public Printer usingTypeRegistry(TypeRegistry typeRegistry) {
            if (this.typeRegistry != TypeRegistry.getEmptyTypeRegistry()) {
                throw new IllegalArgumentException("Only one typeRegistry is allowed.");
            }
            return new Printer(this.escapeNonAscii, typeRegistry);
        }

        public void print(MessageOrBuilder message, Appendable output) throws IOException {
            print(message, TextFormat.multiLineOutput(output));
        }

        public void print(UnknownFieldSet fields, Appendable output) throws IOException {
            printUnknownFields(fields, TextFormat.multiLineOutput(output));
        }

        private void print(MessageOrBuilder message, TextGenerator generator) throws IOException {
            if (message.getDescriptorForType().getFullName().equals("google.protobuf.Any") && printAny(message, generator)) {
                return;
            }
            printMessage(message, generator);
        }

        private boolean printAny(MessageOrBuilder message, TextGenerator generator) throws IOException {
            Descriptors.Descriptor messageType = message.getDescriptorForType();
            Descriptors.FieldDescriptor typeUrlField = messageType.findFieldByNumber(1);
            Descriptors.FieldDescriptor valueField = messageType.findFieldByNumber(2);
            if (typeUrlField == null || typeUrlField.getType() != Descriptors.FieldDescriptor.Type.STRING || valueField == null || valueField.getType() != Descriptors.FieldDescriptor.Type.BYTES) {
                return false;
            }
            String typeUrl = (String) message.getField(typeUrlField);
            if (typeUrl.isEmpty()) {
                return false;
            }
            Object value = message.getField(valueField);
            try {
                Descriptors.Descriptor contentType = this.typeRegistry.getDescriptorForTypeUrl(typeUrl);
                if (contentType == null) {
                    return false;
                }
                Message.Builder contentBuilder = DynamicMessage.getDefaultInstance(contentType).newBuilderForType();
                contentBuilder.mergeFrom((ByteString) value);
                generator.print("[");
                generator.print(typeUrl);
                generator.print("] {");
                generator.eol();
                generator.indent();
                print(contentBuilder, generator);
                generator.outdent();
                generator.print("}");
                generator.eol();
                return true;
            } catch (InvalidProtocolBufferException e) {
                return false;
            }
        }

        public String printFieldToString(Descriptors.FieldDescriptor field, Object value) {
            try {
                StringBuilder text = new StringBuilder();
                printField(field, value, text);
                return text.toString();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        public void printField(Descriptors.FieldDescriptor field, Object value, Appendable output) throws IOException {
            printField(field, value, TextFormat.multiLineOutput(output));
        }

        private void printField(Descriptors.FieldDescriptor field, Object value, TextGenerator generator) throws IOException {
            if (!field.isMapField()) {
                if (field.isRepeated()) {
                    for (Object element : (List) value) {
                        printSingleField(field, element, generator);
                    }
                    return;
                }
                printSingleField(field, value, generator);
                return;
            }
            List<MapEntryAdapter> adapters = new ArrayList<>();
            for (Object entry : (List) value) {
                adapters.add(new MapEntryAdapter(entry, field));
            }
            Collections.sort(adapters);
            for (MapEntryAdapter adapter : adapters) {
                printSingleField(field, adapter.getEntry(), generator);
            }
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
                if (mapEntry != null) {
                    return mapEntry;
                }
                return this.entry;
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
                String aString = (String) getKey();
                String bString = (String) b.getKey();
                if (aString == null && bString == null) {
                    return 0;
                }
                if (aString == null && bString != null) {
                    return -1;
                }
                if (aString == null || bString != null) {
                    return aString.compareTo(bString);
                }
                return 1;
            }
        }

        public void printFieldValue(Descriptors.FieldDescriptor field, Object value, Appendable output) throws IOException {
            printFieldValue(field, value, TextFormat.multiLineOutput(output));
        }

        private void printFieldValue(Descriptors.FieldDescriptor field, Object value, TextGenerator generator) throws IOException {
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
                    generator.print("\"");
                    if (this.escapeNonAscii) {
                        strReplace = TextFormatEscaper.escapeText((String) value);
                    } else {
                        strReplace = TextFormat.escapeDoubleQuotesAndBackslashes((String) value).replace("\n", "\\n");
                    }
                    generator.print(strReplace);
                    generator.print("\"");
                    break;
                case 15:
                    generator.print("\"");
                    if (value instanceof ByteString) {
                        generator.print(TextFormat.escapeBytes((ByteString) value));
                    } else {
                        generator.print(TextFormat.escapeBytes((byte[]) value));
                    }
                    generator.print("\"");
                    break;
                case 16:
                    generator.print(((Descriptors.EnumValueDescriptor) value).getName());
                    break;
                case 17:
                case 18:
                    print((Message) value, generator);
                    break;
            }
        }

        public String printToString(MessageOrBuilder message) {
            try {
                StringBuilder text = new StringBuilder();
                print(message, text);
                return text.toString();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        public String printToString(UnknownFieldSet fields) {
            try {
                StringBuilder text = new StringBuilder();
                print(fields, text);
                return text.toString();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        public String shortDebugString(MessageOrBuilder message) {
            try {
                StringBuilder text = new StringBuilder();
                print(message, TextFormat.singleLineOutput(text));
                return text.toString();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        public String shortDebugString(Descriptors.FieldDescriptor field, Object value) {
            try {
                StringBuilder text = new StringBuilder();
                printField(field, value, TextFormat.singleLineOutput(text));
                return text.toString();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        public String shortDebugString(UnknownFieldSet fields) {
            try {
                StringBuilder text = new StringBuilder();
                printUnknownFields(fields, TextFormat.singleLineOutput(text));
                return text.toString();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        private static void printUnknownFieldValue(int tag, Object value, TextGenerator generator) throws IOException {
            int tagWireType = WireFormat.getTagWireType(tag);
            if (tagWireType == 0) {
                generator.print(TextFormat.unsignedToString(((Long) value).longValue()));
                return;
            }
            if (tagWireType == 1) {
                generator.print(String.format((Locale) null, "0x%016x", (Long) value));
                return;
            }
            if (tagWireType != 2) {
                if (tagWireType == 3) {
                    printUnknownFields((UnknownFieldSet) value, generator);
                    return;
                } else {
                    if (tagWireType == 5) {
                        generator.print(String.format((Locale) null, "0x%08x", (Integer) value));
                        return;
                    }
                    throw new IllegalArgumentException("Bad tag: " + tag);
                }
            }
            try {
                UnknownFieldSet message = UnknownFieldSet.parseFrom((ByteString) value);
                generator.print("{");
                generator.eol();
                generator.indent();
                printUnknownFields(message, generator);
                generator.outdent();
                generator.print("}");
            } catch (InvalidProtocolBufferException e) {
                generator.print("\"");
                generator.print(TextFormat.escapeBytes((ByteString) value));
                generator.print("\"");
            }
        }

        private void printMessage(MessageOrBuilder message, TextGenerator generator) throws IOException {
            for (Map.Entry<Descriptors.FieldDescriptor, Object> field : message.getAllFields().entrySet()) {
                printField(field.getKey(), field.getValue(), generator);
            }
            printUnknownFields(message.getUnknownFields(), generator);
        }

        private void printSingleField(Descriptors.FieldDescriptor field, Object value, TextGenerator generator) throws IOException {
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
            if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                generator.print(" {");
                generator.eol();
                generator.indent();
            } else {
                generator.print(": ");
            }
            printFieldValue(field, value, generator);
            if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                generator.outdent();
                generator.print("}");
            }
            generator.eol();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void printUnknownFields(UnknownFieldSet unknownFields, TextGenerator generator) throws IOException {
            for (Map.Entry<Integer, UnknownFieldSet.Field> entry : unknownFields.asMap().entrySet()) {
                int number = entry.getKey().intValue();
                UnknownFieldSet.Field field = entry.getValue();
                printUnknownField(number, 0, field.getVarintList(), generator);
                printUnknownField(number, 5, field.getFixed32List(), generator);
                printUnknownField(number, 1, field.getFixed64List(), generator);
                printUnknownField(number, 2, field.getLengthDelimitedList(), generator);
                for (UnknownFieldSet value : field.getGroupList()) {
                    generator.print(entry.getKey().toString());
                    generator.print(" {");
                    generator.eol();
                    generator.indent();
                    printUnknownFields(value, generator);
                    generator.outdent();
                    generator.print("}");
                    generator.eol();
                }
            }
        }

        private static void printUnknownField(int number, int wireType, List<?> values, TextGenerator generator) throws IOException {
            for (Object value : values) {
                generator.print(String.valueOf(number));
                generator.print(": ");
                printUnknownFieldValue(wireType, value, generator);
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
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.SINT32.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.SFIXED32.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.INT64.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.SINT64.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.SFIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.BOOL.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.FLOAT.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.DOUBLE.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.UINT32.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.FIXED32.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.UINT64.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.FIXED64.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.STRING.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.BYTES.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.ENUM.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.MESSAGE.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[Descriptors.FieldDescriptor.Type.GROUP.ordinal()] = 18;
            } catch (NoSuchFieldError e18) {
            }
            int[] iArr2 = new int[Descriptors.FieldDescriptor.JavaType.values().length];
            $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$JavaType = iArr2;
            try {
                iArr2[Descriptors.FieldDescriptor.JavaType.BOOLEAN.ordinal()] = 1;
            } catch (NoSuchFieldError e19) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$JavaType[Descriptors.FieldDescriptor.JavaType.LONG.ordinal()] = 2;
            } catch (NoSuchFieldError e20) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$JavaType[Descriptors.FieldDescriptor.JavaType.INT.ordinal()] = 3;
            } catch (NoSuchFieldError e21) {
            }
            try {
                $SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$JavaType[Descriptors.FieldDescriptor.JavaType.STRING.ordinal()] = 4;
            } catch (NoSuchFieldError e22) {
            }
        }
    }

    public static String unsignedToString(int value) {
        if (value >= 0) {
            return Integer.toString(value);
        }
        return Long.toString(((long) value) & 4294967295L);
    }

    public static String unsignedToString(long value) {
        if (value >= 0) {
            return Long.toString(value);
        }
        return BigInteger.valueOf(Long.MAX_VALUE & value).setBit(63).toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static TextGenerator multiLineOutput(Appendable output) {
        return new TextGenerator(output, false, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static TextGenerator singleLineOutput(Appendable output) {
        return new TextGenerator(output, true, null);
    }

    private static final class TextGenerator {
        private boolean atStartOfLine;
        private final StringBuilder indent;
        private final Appendable output;
        private final boolean singleLineMode;

        /* synthetic */ TextGenerator(Appendable x0, boolean x1, AnonymousClass1 x2) {
            this(x0, x1);
        }

        private TextGenerator(Appendable output, boolean singleLineMode) {
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

        public void print(CharSequence text) throws IOException {
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

    private static final class Tokenizer {
        private int column;
        private boolean containsSilentMarkerAfterCurrentToken;
        private boolean containsSilentMarkerAfterPrevToken;
        private String currentToken;
        private int line;
        private final Matcher matcher;
        private int pos;
        private int previousColumn;
        private int previousLine;
        private final CharSequence text;
        private static final Pattern WHITESPACE = Pattern.compile("(\\s|(#.*$))++", 8);
        private static final Pattern TOKEN = Pattern.compile("[a-zA-Z_][0-9a-zA-Z_+-]*+|[.]?[0-9+-][0-9a-zA-Z_.+-]*+|\"([^\"\n\\\\]|\\\\.)*+(\"|\\\\?$)|'([^'\n\\\\]|\\\\.)*+('|\\\\?$)", 8);
        private static final Pattern DOUBLE_INFINITY = Pattern.compile("-?inf(inity)?", 2);
        private static final Pattern FLOAT_INFINITY = Pattern.compile("-?inf(inity)?f?", 2);
        private static final Pattern FLOAT_NAN = Pattern.compile("nanf?", 2);

        /* synthetic */ Tokenizer(CharSequence x0, AnonymousClass1 x1) {
            this(x0);
        }

        private Tokenizer(CharSequence text) {
            this.pos = 0;
            this.line = 0;
            this.column = 0;
            this.previousLine = 0;
            this.previousColumn = 0;
            this.containsSilentMarkerAfterCurrentToken = false;
            this.containsSilentMarkerAfterPrevToken = false;
            this.text = text;
            this.matcher = WHITESPACE.matcher(text);
            skipWhitespace();
            nextToken();
        }

        int getPreviousLine() {
            return this.previousLine;
        }

        int getPreviousColumn() {
            return this.previousColumn;
        }

        int getLine() {
            return this.line;
        }

        int getColumn() {
            return this.column;
        }

        boolean getContainsSilentMarkerAfterCurrentToken() {
            return this.containsSilentMarkerAfterCurrentToken;
        }

        boolean getContainsSilentMarkerAfterPrevToken() {
            return this.containsSilentMarkerAfterPrevToken;
        }

        boolean atEnd() {
            return this.currentToken.length() == 0;
        }

        void nextToken() {
            this.previousLine = this.line;
            this.previousColumn = this.column;
            while (this.pos < this.matcher.regionStart()) {
                if (this.text.charAt(this.pos) == '\n') {
                    this.line++;
                    this.column = 0;
                } else {
                    this.column++;
                }
                this.pos++;
            }
            if (this.matcher.regionStart() == this.matcher.regionEnd()) {
                this.currentToken = "";
                return;
            }
            this.matcher.usePattern(TOKEN);
            if (this.matcher.lookingAt()) {
                this.currentToken = this.matcher.group();
                Matcher matcher = this.matcher;
                matcher.region(matcher.end(), this.matcher.regionEnd());
            } else {
                this.currentToken = String.valueOf(this.text.charAt(this.pos));
                Matcher matcher2 = this.matcher;
                matcher2.region(this.pos + 1, matcher2.regionEnd());
            }
            skipWhitespace();
        }

        private void skipWhitespace() {
            this.matcher.usePattern(WHITESPACE);
            if (this.matcher.lookingAt()) {
                Matcher matcher = this.matcher;
                matcher.region(matcher.end(), this.matcher.regionEnd());
            }
        }

        boolean tryConsume(String token) {
            if (this.currentToken.equals(token)) {
                nextToken();
                return true;
            }
            return false;
        }

        void consume(String token) throws ParseException {
            if (!tryConsume(token)) {
                throw parseException("Expected \"" + token + "\".");
            }
        }

        boolean lookingAtInteger() {
            if (this.currentToken.length() == 0) {
                return false;
            }
            char c = this.currentToken.charAt(0);
            return ('0' <= c && c <= '9') || c == '-' || c == '+';
        }

        boolean lookingAt(String text) {
            return this.currentToken.equals(text);
        }

        String consumeIdentifier() throws ParseException {
            for (int i = 0; i < this.currentToken.length(); i++) {
                char c = this.currentToken.charAt(i);
                if (('a' > c || c > 'z') && (('A' > c || c > 'Z') && (('0' > c || c > '9') && c != '_' && c != '.'))) {
                    throw parseException("Expected identifier. Found '" + this.currentToken + "'");
                }
            }
            String result = this.currentToken;
            nextToken();
            return result;
        }

        boolean tryConsumeIdentifier() {
            try {
                consumeIdentifier();
                return true;
            } catch (ParseException e) {
                return false;
            }
        }

        int consumeInt32() throws ParseException {
            try {
                int result = TextFormat.parseInt32(this.currentToken);
                nextToken();
                return result;
            } catch (NumberFormatException e) {
                throw integerParseException(e);
            }
        }

        int consumeUInt32() throws ParseException {
            try {
                int result = TextFormat.parseUInt32(this.currentToken);
                nextToken();
                return result;
            } catch (NumberFormatException e) {
                throw integerParseException(e);
            }
        }

        long consumeInt64() throws ParseException {
            try {
                long result = TextFormat.parseInt64(this.currentToken);
                nextToken();
                return result;
            } catch (NumberFormatException e) {
                throw integerParseException(e);
            }
        }

        boolean tryConsumeInt64() {
            try {
                consumeInt64();
                return true;
            } catch (ParseException e) {
                return false;
            }
        }

        long consumeUInt64() throws ParseException {
            try {
                long result = TextFormat.parseUInt64(this.currentToken);
                nextToken();
                return result;
            } catch (NumberFormatException e) {
                throw integerParseException(e);
            }
        }

        public boolean tryConsumeUInt64() {
            try {
                consumeUInt64();
                return true;
            } catch (ParseException e) {
                return false;
            }
        }

        public double consumeDouble() throws ParseException {
            if (DOUBLE_INFINITY.matcher(this.currentToken).matches()) {
                boolean negative = this.currentToken.startsWith("-");
                nextToken();
                return negative ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
            }
            if (this.currentToken.equalsIgnoreCase("nan")) {
                nextToken();
                return Double.NaN;
            }
            try {
                double result = Double.parseDouble(this.currentToken);
                nextToken();
                return result;
            } catch (NumberFormatException e) {
                throw floatParseException(e);
            }
        }

        public boolean tryConsumeDouble() {
            try {
                consumeDouble();
                return true;
            } catch (ParseException e) {
                return false;
            }
        }

        public float consumeFloat() throws ParseException {
            if (FLOAT_INFINITY.matcher(this.currentToken).matches()) {
                boolean negative = this.currentToken.startsWith("-");
                nextToken();
                return negative ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY;
            }
            if (FLOAT_NAN.matcher(this.currentToken).matches()) {
                nextToken();
                return Float.NaN;
            }
            try {
                float result = Float.parseFloat(this.currentToken);
                nextToken();
                return result;
            } catch (NumberFormatException e) {
                throw floatParseException(e);
            }
        }

        public boolean tryConsumeFloat() {
            try {
                consumeFloat();
                return true;
            } catch (ParseException e) {
                return false;
            }
        }

        public boolean consumeBoolean() throws ParseException {
            if (this.currentToken.equals("true") || this.currentToken.equals("True") || this.currentToken.equals("t") || this.currentToken.equals("1")) {
                nextToken();
                return true;
            }
            if (this.currentToken.equals("false") || this.currentToken.equals("False") || this.currentToken.equals("f") || this.currentToken.equals("0")) {
                nextToken();
                return false;
            }
            throw parseException("Expected \"true\" or \"false\". Found \"" + this.currentToken + "\".");
        }

        public String consumeString() throws ParseException {
            return consumeByteString().toStringUtf8();
        }

        boolean tryConsumeString() {
            try {
                consumeString();
                return true;
            } catch (ParseException e) {
                return false;
            }
        }

        ByteString consumeByteString() throws ParseException {
            List<ByteString> list = new ArrayList<>();
            consumeByteString(list);
            while (true) {
                if (this.currentToken.startsWith("'") || this.currentToken.startsWith("\"")) {
                    consumeByteString(list);
                } else {
                    return ByteString.copyFrom(list);
                }
            }
        }

        private void consumeByteString(List<ByteString> list) throws ParseException {
            char quote = this.currentToken.length() > 0 ? this.currentToken.charAt(0) : (char) 0;
            if (quote != '\"' && quote != '\'') {
                throw parseException("Expected string.");
            }
            if (this.currentToken.length() >= 2) {
                String str = this.currentToken;
                if (str.charAt(str.length() - 1) == quote) {
                    try {
                        String escaped = this.currentToken.substring(1, this.currentToken.length() - 1);
                        ByteString result = TextFormat.unescapeBytes(escaped);
                        nextToken();
                        list.add(result);
                        return;
                    } catch (InvalidEscapeSequenceException e) {
                        throw parseException(e.getMessage());
                    }
                }
            }
            throw parseException("String missing ending quote.");
        }

        ParseException parseException(String description) {
            return new ParseException(this.line + 1, this.column + 1, description);
        }

        ParseException parseExceptionPreviousToken(String description) {
            return new ParseException(this.previousLine + 1, this.previousColumn + 1, description);
        }

        private ParseException integerParseException(NumberFormatException e) {
            return parseException("Couldn't parse integer: " + e.getMessage());
        }

        private ParseException floatParseException(NumberFormatException e) {
            return parseException("Couldn't parse number: " + e.getMessage());
        }
    }

    public static class ParseException extends IOException {
        private static final long serialVersionUID = 3196188060225107702L;
        private final int column;
        private final int line;

        public ParseException(String message) {
            this(-1, -1, message);
        }

        public ParseException(int line, int column, String message) {
            super(Integer.toString(line) + ":" + column + ": " + message);
            this.line = line;
            this.column = column;
        }

        public int getLine() {
            return this.line;
        }

        public int getColumn() {
            return this.column;
        }
    }

    public static class UnknownFieldParseException extends ParseException {
        private final String unknownField;

        public UnknownFieldParseException(String message) {
            this(-1, -1, "", message);
        }

        public UnknownFieldParseException(int line, int column, String unknownField, String message) {
            super(line, column, message);
            this.unknownField = unknownField;
        }

        public String getUnknownField() {
            return this.unknownField;
        }
    }

    public static Parser getParser() {
        return PARSER;
    }

    public static void merge(Readable input, Message.Builder builder) throws IOException {
        PARSER.merge(input, builder);
    }

    public static void merge(CharSequence input, Message.Builder builder) throws ParseException {
        PARSER.merge(input, builder);
    }

    public static <T extends Message> T parse(CharSequence charSequence, Class<T> cls) throws ParseException {
        Message.Builder builderNewBuilderForType = ((Message) Internal.getDefaultInstance(cls)).newBuilderForType();
        merge(charSequence, builderNewBuilderForType);
        return (T) builderNewBuilderForType.build();
    }

    public static void merge(Readable input, ExtensionRegistry extensionRegistry, Message.Builder builder) throws IOException {
        PARSER.merge(input, extensionRegistry, builder);
    }

    public static void merge(CharSequence input, ExtensionRegistry extensionRegistry, Message.Builder builder) throws ParseException {
        PARSER.merge(input, extensionRegistry, builder);
    }

    public static <T extends Message> T parse(CharSequence charSequence, ExtensionRegistry extensionRegistry, Class<T> cls) throws ParseException {
        Message.Builder builderNewBuilderForType = ((Message) Internal.getDefaultInstance(cls)).newBuilderForType();
        merge(charSequence, extensionRegistry, builderNewBuilderForType);
        return (T) builderNewBuilderForType.build();
    }

    public static class Parser {
        private static final int BUFFER_SIZE = 4096;
        private final boolean allowUnknownEnumValues;
        private final boolean allowUnknownExtensions;
        private final boolean allowUnknownFields;
        private TextFormatParseInfoTree.Builder parseInfoTreeBuilder;
        private final SingularOverwritePolicy singularOverwritePolicy;
        private final TypeRegistry typeRegistry;

        public enum SingularOverwritePolicy {
            ALLOW_SINGULAR_OVERWRITES,
            FORBID_SINGULAR_OVERWRITES
        }

        /* synthetic */ Parser(TypeRegistry x0, boolean x1, boolean x2, boolean x3, SingularOverwritePolicy x4, TextFormatParseInfoTree.Builder x5, AnonymousClass1 x6) {
            this(x0, x1, x2, x3, x4, x5);
        }

        private void detectSilentMarker(Tokenizer tokenizer) {
        }

        private Parser(TypeRegistry typeRegistry, boolean allowUnknownFields, boolean allowUnknownEnumValues, boolean allowUnknownExtensions, SingularOverwritePolicy singularOverwritePolicy, TextFormatParseInfoTree.Builder parseInfoTreeBuilder) {
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
            private TextFormatParseInfoTree.Builder parseInfoTreeBuilder = null;
            private TypeRegistry typeRegistry = TypeRegistry.getEmptyTypeRegistry();

            public Builder setTypeRegistry(TypeRegistry typeRegistry) {
                this.typeRegistry = typeRegistry;
                return this;
            }

            public Builder setAllowUnknownFields(boolean allowUnknownFields) {
                this.allowUnknownFields = allowUnknownFields;
                return this;
            }

            public Builder setAllowUnknownExtensions(boolean allowUnknownExtensions) {
                this.allowUnknownExtensions = allowUnknownExtensions;
                return this;
            }

            public Builder setSingularOverwritePolicy(SingularOverwritePolicy p) {
                this.singularOverwritePolicy = p;
                return this;
            }

            public Builder setParseInfoTreeBuilder(TextFormatParseInfoTree.Builder parseInfoTreeBuilder) {
                this.parseInfoTreeBuilder = parseInfoTreeBuilder;
                return this;
            }

            public Parser build() {
                return new Parser(this.typeRegistry, this.allowUnknownFields, this.allowUnknownEnumValues, this.allowUnknownExtensions, this.singularOverwritePolicy, this.parseInfoTreeBuilder, null);
            }
        }

        public void merge(Readable input, Message.Builder builder) throws IOException {
            merge(input, ExtensionRegistry.getEmptyRegistry(), builder);
        }

        public void merge(CharSequence input, Message.Builder builder) throws ParseException {
            merge(input, ExtensionRegistry.getEmptyRegistry(), builder);
        }

        public void merge(Readable input, ExtensionRegistry extensionRegistry, Message.Builder builder) throws IOException {
            merge(toStringBuilder(input), extensionRegistry, builder);
        }

        private static StringBuilder toStringBuilder(Readable input) throws IOException {
            StringBuilder text = new StringBuilder();
            CharBuffer buffer = CharBuffer.allocate(4096);
            while (true) {
                int n = input.read(buffer);
                if (n != -1) {
                    buffer.flip();
                    text.append((CharSequence) buffer, 0, n);
                } else {
                    return text;
                }
            }
        }

        static final class UnknownField {
            final String message;
            final Type type;

            enum Type {
                FIELD,
                EXTENSION
            }

            UnknownField(String message, Type type) {
                this.message = message;
                this.type = type;
            }
        }

        private void checkUnknownFields(List<UnknownField> unknownFields) throws ParseException {
            if (unknownFields.isEmpty()) {
                return;
            }
            StringBuilder msg = new StringBuilder("Input contains unknown fields and/or extensions:");
            for (UnknownField field : unknownFields) {
                msg.append('\n');
                msg.append(field.message);
            }
            if (this.allowUnknownFields) {
                TextFormat.logger.warning(msg.toString());
                return;
            }
            int firstErrorIndex = 0;
            if (this.allowUnknownExtensions) {
                boolean allUnknownExtensions = true;
                Iterator<UnknownField> it = unknownFields.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    UnknownField field2 = it.next();
                    if (field2.type == UnknownField.Type.FIELD) {
                        allUnknownExtensions = false;
                        break;
                    }
                    firstErrorIndex++;
                }
                if (allUnknownExtensions) {
                    TextFormat.logger.warning(msg.toString());
                    return;
                }
            }
            String[] lineColumn = unknownFields.get(firstErrorIndex).message.split(":");
            throw new ParseException(Integer.parseInt(lineColumn[0]), Integer.parseInt(lineColumn[1]), msg.toString());
        }

        public void merge(CharSequence input, ExtensionRegistry extensionRegistry, Message.Builder builder) throws ParseException {
            Tokenizer tokenizer = new Tokenizer(input, null);
            MessageReflection.BuilderAdapter target = new MessageReflection.BuilderAdapter(builder);
            List<UnknownField> unknownFields = new ArrayList<>();
            while (!tokenizer.atEnd()) {
                mergeField(tokenizer, extensionRegistry, target, unknownFields);
            }
            checkUnknownFields(unknownFields);
        }

        private void mergeField(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget target, List<UnknownField> unknownFields) throws ParseException {
            mergeField(tokenizer, extensionRegistry, target, this.parseInfoTreeBuilder, unknownFields);
        }

        private void mergeField(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget target, TextFormatParseInfoTree.Builder parseTreeBuilder, List<UnknownField> unknownFields) throws ParseException {
            Descriptors.FieldDescriptor field;
            ExtensionRegistry.ExtensionInfo extension;
            Descriptors.FieldDescriptor field2;
            Descriptors.FieldDescriptor field3;
            Descriptors.FieldDescriptor field4 = null;
            int startLine = tokenizer.getLine();
            int startColumn = tokenizer.getColumn();
            Descriptors.Descriptor type = target.getDescriptorForType();
            if ("google.protobuf.Any".equals(type.getFullName()) && tokenizer.tryConsume("[")) {
                mergeAnyFieldValue(tokenizer, extensionRegistry, target, parseTreeBuilder, unknownFields, type);
                return;
            }
            if (tokenizer.tryConsume("[")) {
                StringBuilder name = new StringBuilder(tokenizer.consumeIdentifier());
                while (tokenizer.tryConsume(".")) {
                    name.append('.');
                    name.append(tokenizer.consumeIdentifier());
                }
                ExtensionRegistry.ExtensionInfo extension2 = target.findExtensionByName(extensionRegistry, name.toString());
                if (extension2 == null) {
                    String message = (tokenizer.getPreviousLine() + 1) + ":" + (tokenizer.getPreviousColumn() + 1) + ":\t" + type.getFullName() + ".[" + ((Object) name) + "]";
                    unknownFields.add(new UnknownField(message, UnknownField.Type.EXTENSION));
                } else {
                    if (extension2.descriptor.getContainingType() != type) {
                        throw tokenizer.parseExceptionPreviousToken("Extension \"" + ((Object) name) + "\" does not extend message type \"" + type.getFullName() + "\".");
                    }
                    field4 = extension2.descriptor;
                }
                tokenizer.consume("]");
                extension = extension2;
                field2 = field4;
            } else {
                String name2 = tokenizer.consumeIdentifier();
                Descriptors.FieldDescriptor field5 = type.findFieldByName(name2);
                if (field5 == null) {
                    String lowerName = name2.toLowerCase(Locale.US);
                    Descriptors.FieldDescriptor field6 = type.findFieldByName(lowerName);
                    if (field6 != null) {
                        field3 = field6;
                        if (field6.getType() != Descriptors.FieldDescriptor.Type.GROUP) {
                            field5 = null;
                        }
                    } else {
                        field3 = field6;
                    }
                    field5 = field3;
                }
                if (field5 != null && field5.getType() == Descriptors.FieldDescriptor.Type.GROUP && !field5.getMessageType().getName().equals(name2)) {
                    field = null;
                } else {
                    field = field5;
                }
                if (field == null) {
                    String message2 = (tokenizer.getPreviousLine() + 1) + ":" + (tokenizer.getPreviousColumn() + 1) + ":\t" + type.getFullName() + "." + name2;
                    unknownFields.add(new UnknownField(message2, UnknownField.Type.FIELD));
                }
                extension = null;
                field2 = field;
            }
            if (field2 == null) {
                detectSilentMarker(tokenizer);
                if (tokenizer.tryConsume(":") && !tokenizer.lookingAt("{") && !tokenizer.lookingAt("<")) {
                    skipFieldValue(tokenizer);
                    return;
                } else {
                    skipFieldMessage(tokenizer);
                    return;
                }
            }
            if (field2.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                detectSilentMarker(tokenizer);
                tokenizer.tryConsume(":");
                if (parseTreeBuilder != null) {
                    TextFormatParseInfoTree.Builder childParseTreeBuilder = parseTreeBuilder.getBuilderForSubMessageField(field2);
                    consumeFieldValues(tokenizer, extensionRegistry, target, field2, extension, childParseTreeBuilder, unknownFields);
                } else {
                    consumeFieldValues(tokenizer, extensionRegistry, target, field2, extension, parseTreeBuilder, unknownFields);
                }
            } else {
                detectSilentMarker(tokenizer);
                tokenizer.consume(":");
                consumeFieldValues(tokenizer, extensionRegistry, target, field2, extension, parseTreeBuilder, unknownFields);
            }
            if (parseTreeBuilder != null) {
                parseTreeBuilder.setLocation(field2, TextFormatParseLocation.create(startLine, startColumn));
            }
            if (!tokenizer.tryConsume(";")) {
                tokenizer.tryConsume(",");
            }
        }

        private void consumeFieldValues(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget target, Descriptors.FieldDescriptor field, ExtensionRegistry.ExtensionInfo extension, TextFormatParseInfoTree.Builder parseTreeBuilder, List<UnknownField> unknownFields) throws ParseException {
            if (field.isRepeated() && tokenizer.tryConsume("[")) {
                if (tokenizer.tryConsume("]")) {
                    return;
                }
                while (true) {
                    consumeFieldValue(tokenizer, extensionRegistry, target, field, extension, parseTreeBuilder, unknownFields);
                    if (!tokenizer.tryConsume("]")) {
                        tokenizer.consume(",");
                    } else {
                        return;
                    }
                }
            } else {
                consumeFieldValue(tokenizer, extensionRegistry, target, field, extension, parseTreeBuilder, unknownFields);
            }
        }

        private void consumeFieldValue(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget target, Descriptors.FieldDescriptor field, ExtensionRegistry.ExtensionInfo extension, TextFormatParseInfoTree.Builder parseTreeBuilder, List<UnknownField> unknownFields) throws ParseException {
            String endToken;
            if (this.singularOverwritePolicy == SingularOverwritePolicy.FORBID_SINGULAR_OVERWRITES && !field.isRepeated()) {
                if (target.hasField(field)) {
                    throw tokenizer.parseExceptionPreviousToken("Non-repeated field \"" + field.getFullName() + "\" cannot be overwritten.");
                }
                if (field.getContainingOneof() != null && target.hasOneof(field.getContainingOneof())) {
                    Descriptors.OneofDescriptor oneof = field.getContainingOneof();
                    throw tokenizer.parseExceptionPreviousToken("Field \"" + field.getFullName() + "\" is specified along with field \"" + target.getOneofFieldDescriptor(oneof).getFullName() + "\", another member of oneof \"" + oneof.getName() + "\".");
                }
            }
            Object value = null;
            if (field.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE) {
                if (tokenizer.tryConsume("<")) {
                    endToken = ">";
                } else {
                    tokenizer.consume("{");
                    endToken = "}";
                }
                if (field.getMessageType().getFullName().equals("google.protobuf.Any") && tokenizer.tryConsume("[")) {
                    Message anyBuilder = DynamicMessage.getDefaultInstance(field.getMessageType());
                    MessageReflection.MergeTarget anyField = target.newMergeTargetForField(field, anyBuilder);
                    mergeAnyFieldValue(tokenizer, extensionRegistry, anyField, parseTreeBuilder, unknownFields, field.getMessageType());
                    Object value2 = anyField.finish();
                    tokenizer.consume(endToken);
                    value = value2;
                } else {
                    Message defaultInstance = extension == null ? null : extension.defaultInstance;
                    MessageReflection.MergeTarget subField = target.newMergeTargetForField(field, defaultInstance);
                    while (!tokenizer.tryConsume(endToken)) {
                        if (tokenizer.atEnd()) {
                            throw tokenizer.parseException("Expected \"" + endToken + "\".");
                        }
                        mergeField(tokenizer, extensionRegistry, subField, parseTreeBuilder, unknownFields);
                    }
                    value = subField.finish();
                }
            } else {
                switch (AnonymousClass1.$SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[field.getType().ordinal()]) {
                    case 1:
                    case 2:
                    case 3:
                        value = Integer.valueOf(tokenizer.consumeInt32());
                        break;
                    case 4:
                    case 5:
                    case 6:
                        value = Long.valueOf(tokenizer.consumeInt64());
                        break;
                    case 7:
                        value = Boolean.valueOf(tokenizer.consumeBoolean());
                        break;
                    case 8:
                        value = Float.valueOf(tokenizer.consumeFloat());
                        break;
                    case 9:
                        value = Double.valueOf(tokenizer.consumeDouble());
                        break;
                    case 10:
                    case 11:
                        value = Integer.valueOf(tokenizer.consumeUInt32());
                        break;
                    case 12:
                    case 13:
                        value = Long.valueOf(tokenizer.consumeUInt64());
                        break;
                    case 14:
                        value = tokenizer.consumeString();
                        break;
                    case 15:
                        value = tokenizer.consumeByteString();
                        break;
                    case 16:
                        Descriptors.EnumDescriptor enumType = field.getEnumType();
                        if (tokenizer.lookingAtInteger()) {
                            int number = tokenizer.consumeInt32();
                            value = enumType.findValueByNumber(number);
                            if (value == null) {
                                String unknownValueMsg = "Enum type \"" + enumType.getFullName() + "\" has no value with number " + number + '.';
                                if (this.allowUnknownEnumValues) {
                                    TextFormat.logger.warning(unknownValueMsg);
                                    return;
                                }
                                throw tokenizer.parseExceptionPreviousToken("Enum type \"" + enumType.getFullName() + "\" has no value with number " + number + '.');
                            }
                        } else {
                            String id = tokenizer.consumeIdentifier();
                            value = enumType.findValueByName(id);
                            if (value == null) {
                                String unknownValueMsg2 = "Enum type \"" + enumType.getFullName() + "\" has no value named \"" + id + "\".";
                                if (this.allowUnknownEnumValues) {
                                    TextFormat.logger.warning(unknownValueMsg2);
                                    return;
                                }
                                throw tokenizer.parseExceptionPreviousToken(unknownValueMsg2);
                            }
                        }
                    case 17:
                    case 18:
                        throw new RuntimeException("Can't get here.");
                }
            }
            if (field.isRepeated()) {
                target.addRepeatedField(field, value);
            } else {
                target.setField(field, value);
            }
        }

        private void mergeAnyFieldValue(Tokenizer tokenizer, ExtensionRegistry extensionRegistry, MessageReflection.MergeTarget target, TextFormatParseInfoTree.Builder parseTreeBuilder, List<UnknownField> unknownFields, Descriptors.Descriptor anyDescriptor) throws ParseException {
            String anyEndToken;
            StringBuilder typeUrlBuilder = new StringBuilder();
            while (true) {
                typeUrlBuilder.append(tokenizer.consumeIdentifier());
                if (!tokenizer.tryConsume("]")) {
                    if (!tokenizer.tryConsume("/")) {
                        if (!tokenizer.tryConsume(".")) {
                            throw tokenizer.parseExceptionPreviousToken("Expected a valid type URL.");
                        }
                        typeUrlBuilder.append(".");
                    } else {
                        typeUrlBuilder.append("/");
                    }
                } else {
                    detectSilentMarker(tokenizer);
                    tokenizer.tryConsume(":");
                    if (!tokenizer.tryConsume("<")) {
                        tokenizer.consume("{");
                        anyEndToken = "}";
                    } else {
                        anyEndToken = ">";
                    }
                    String typeUrl = typeUrlBuilder.toString();
                    try {
                        Descriptors.Descriptor contentType = this.typeRegistry.getDescriptorForTypeUrl(typeUrl);
                        if (contentType == null) {
                            throw tokenizer.parseException("Unable to parse Any of type: " + typeUrl + ". Please make sure that the TypeRegistry contains the descriptors for the given types.");
                        }
                        Message.Builder contentBuilder = DynamicMessage.getDefaultInstance(contentType).newBuilderForType();
                        MessageReflection.BuilderAdapter contentTarget = new MessageReflection.BuilderAdapter(contentBuilder);
                        while (!tokenizer.tryConsume(anyEndToken)) {
                            mergeField(tokenizer, extensionRegistry, contentTarget, parseTreeBuilder, unknownFields);
                        }
                        target.setField(anyDescriptor.findFieldByName("type_url"), typeUrlBuilder.toString());
                        target.setField(anyDescriptor.findFieldByName("value"), contentBuilder.build().toByteString());
                        return;
                    } catch (InvalidProtocolBufferException e) {
                        throw tokenizer.parseException("Invalid valid type URL. Found: " + typeUrl);
                    }
                }
            }
        }

        private void skipField(Tokenizer tokenizer) throws ParseException {
            if (tokenizer.tryConsume("[")) {
                do {
                    tokenizer.consumeIdentifier();
                } while (tokenizer.tryConsume("."));
                tokenizer.consume("]");
            } else {
                tokenizer.consumeIdentifier();
            }
            detectSilentMarker(tokenizer);
            if (tokenizer.tryConsume(":") && !tokenizer.lookingAt("<") && !tokenizer.lookingAt("{")) {
                skipFieldValue(tokenizer);
            } else {
                skipFieldMessage(tokenizer);
            }
            if (!tokenizer.tryConsume(";")) {
                tokenizer.tryConsume(",");
            }
        }

        private void skipFieldMessage(Tokenizer tokenizer) throws ParseException {
            String delimiter;
            if (tokenizer.tryConsume("<")) {
                delimiter = ">";
            } else {
                tokenizer.consume("{");
                delimiter = "}";
            }
            while (!tokenizer.lookingAt(">") && !tokenizer.lookingAt("}")) {
                skipField(tokenizer);
            }
            tokenizer.consume(delimiter);
        }

        private void skipFieldValue(Tokenizer tokenizer) throws ParseException {
            if (tokenizer.tryConsumeString()) {
                while (tokenizer.tryConsumeString()) {
                }
                return;
            }
            if (!tokenizer.tryConsumeIdentifier() && !tokenizer.tryConsumeInt64() && !tokenizer.tryConsumeUInt64() && !tokenizer.tryConsumeDouble() && !tokenizer.tryConsumeFloat()) {
                throw tokenizer.parseException("Invalid field value: " + tokenizer.currentToken);
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
        ByteString input = ByteString.copyFromUtf8(charString.toString());
        byte[] result = new byte[input.size()];
        int pos = 0;
        int i = 0;
        while (i < input.size()) {
            byte c = input.byteAt(i);
            if (c == 92) {
                if (i + 1 < input.size()) {
                    i++;
                    byte c2 = input.byteAt(i);
                    if (isOctal(c2)) {
                        int code = digitValue(c2);
                        if (i + 1 < input.size() && isOctal(input.byteAt(i + 1))) {
                            i++;
                            code = (code * 8) + digitValue(input.byteAt(i));
                        }
                        if (i + 1 < input.size() && isOctal(input.byteAt(i + 1))) {
                            i++;
                            code = (code * 8) + digitValue(input.byteAt(i));
                        }
                        result[pos] = (byte) code;
                        pos++;
                    } else if (c2 == 34) {
                        result[pos] = 34;
                        pos++;
                    } else if (c2 == 39) {
                        result[pos] = 39;
                        pos++;
                    } else if (c2 == 63) {
                        result[pos] = 63;
                        pos++;
                    } else if (c2 != 85) {
                        if (c2 == 92) {
                            result[pos] = 92;
                            pos++;
                        } else if (c2 == 102) {
                            result[pos] = 12;
                            pos++;
                        } else if (c2 == 110) {
                            result[pos] = 10;
                            pos++;
                        } else if (c2 == 114) {
                            result[pos] = 13;
                            pos++;
                        } else if (c2 != 120) {
                            if (c2 == 97) {
                                result[pos] = 7;
                                pos++;
                            } else if (c2 == 98) {
                                result[pos] = 8;
                                pos++;
                            } else {
                                switch (c2) {
                                    case SUSD_SET_EX_AUDIO_STATE_VALUE:
                                        result[pos] = 9;
                                        pos++;
                                        break;
                                    case 117:
                                        int i2 = i + 1;
                                        if (i2 + 3 < input.size() && isHex(input.byteAt(i2)) && isHex(input.byteAt(i2 + 1)) && isHex(input.byteAt(i2 + 2)) && isHex(input.byteAt(i2 + 3))) {
                                            char ch = (char) ((digitValue(input.byteAt(i2)) << 12) | (digitValue(input.byteAt(i2 + 1)) << 8) | (digitValue(input.byteAt(i2 + 2)) << 4) | digitValue(input.byteAt(i2 + 3)));
                                            if (ch >= 55296 && ch <= 57343) {
                                                throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\u' refers to a surrogate");
                                            }
                                            byte[] chUtf8 = Character.toString(ch).getBytes(Internal.UTF_8);
                                            System.arraycopy(chUtf8, 0, result, pos, chUtf8.length);
                                            pos += chUtf8.length;
                                            i = i2 + 3;
                                        } else {
                                            throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\u' with too few hex chars");
                                        }
                                        break;
                                    case 118:
                                        result[pos] = 11;
                                        pos++;
                                        break;
                                    default:
                                        throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\" + ((char) c2) + '\'');
                                }
                            }
                        } else if (i + 1 < input.size() && isHex(input.byteAt(i + 1))) {
                            i++;
                            int code2 = digitValue(input.byteAt(i));
                            if (i + 1 < input.size() && isHex(input.byteAt(i + 1))) {
                                i++;
                                code2 = (code2 * 16) + digitValue(input.byteAt(i));
                            }
                            result[pos] = (byte) code2;
                            pos++;
                        } else {
                            throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\x' with no digits");
                        }
                    } else {
                        int i3 = i + 1;
                        if (i3 + 7 >= input.size()) {
                            throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\U' with too few hex chars");
                        }
                        int codepoint = 0;
                        for (int offset = i3; offset < i3 + 8; offset++) {
                            byte b = input.byteAt(offset);
                            if (!isHex(b)) {
                                throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\U' with too few hex chars");
                            }
                            codepoint = (codepoint << 4) | digitValue(b);
                        }
                        if (!Character.isValidCodePoint(codepoint)) {
                            throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\U" + input.substring(i3, i3 + 8).toStringUtf8() + "' is not a valid code point value");
                        }
                        Character.UnicodeBlock unicodeBlock = Character.UnicodeBlock.of(codepoint);
                        if (unicodeBlock != null && (unicodeBlock.equals(Character.UnicodeBlock.LOW_SURROGATES) || unicodeBlock.equals(Character.UnicodeBlock.HIGH_SURROGATES) || unicodeBlock.equals(Character.UnicodeBlock.HIGH_PRIVATE_USE_SURROGATES))) {
                            throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\U" + input.substring(i3, i3 + 8).toStringUtf8() + "' refers to a surrogate code unit");
                        }
                        int[] codepoints = {codepoint};
                        byte[] chUtf82 = new String(codepoints, 0, 1).getBytes(Internal.UTF_8);
                        System.arraycopy(chUtf82, 0, result, pos, chUtf82.length);
                        pos += chUtf82.length;
                        i = i3 + 7;
                    }
                } else {
                    throw new InvalidEscapeSequenceException("Invalid escape sequence: '\\' at end of string.");
                }
            } else {
                result[pos] = c;
                pos++;
            }
            i++;
        }
        int i4 = result.length;
        if (i4 == pos) {
            return ByteString.wrap(result);
        }
        return ByteString.copyFrom(result, 0, pos);
    }

    public static class InvalidEscapeSequenceException extends IOException {
        private static final long serialVersionUID = -8164033650142593304L;

        InvalidEscapeSequenceException(String description) {
            super(description);
        }
    }

    static String escapeText(String input) {
        return escapeBytes(ByteString.copyFromUtf8(input));
    }

    public static String escapeDoubleQuotesAndBackslashes(String input) {
        return TextFormatEscaper.escapeDoubleQuotesAndBackslashes(input);
    }

    static String unescapeText(String input) throws InvalidEscapeSequenceException {
        return unescapeBytes(input).toStringUtf8();
    }

    private static boolean isOctal(byte c) {
        return 48 <= c && c <= 55;
    }

    private static boolean isHex(byte c) {
        return (48 <= c && c <= 57) || (97 <= c && c <= 102) || (65 <= c && c <= 70);
    }

    private static int digitValue(byte c) {
        if (48 <= c && c <= 57) {
            return c - 48;
        }
        if (97 <= c && c <= 122) {
            return (c - 97) + 10;
        }
        return (c - 65) + 10;
    }

    static int parseInt32(String text) throws NumberFormatException {
        return (int) parseInteger(text, true, false);
    }

    static int parseUInt32(String text) throws NumberFormatException {
        return (int) parseInteger(text, false, false);
    }

    static long parseInt64(String text) throws NumberFormatException {
        return parseInteger(text, true, true);
    }

    static long parseUInt64(String text) throws NumberFormatException {
        return parseInteger(text, false, true);
    }

    private static long parseInteger(String text, boolean isSigned, boolean isLong) throws NumberFormatException {
        int pos = 0;
        boolean negative = false;
        if (text.startsWith("-", 0)) {
            if (!isSigned) {
                throw new NumberFormatException("Number must be positive: " + text);
            }
            pos = 0 + 1;
            negative = true;
        }
        int radix = 10;
        if (text.startsWith("0x", pos)) {
            pos += 2;
            radix = 16;
        } else if (text.startsWith("0", pos)) {
            radix = 8;
        }
        String numberText = text.substring(pos);
        if (numberText.length() < 16) {
            long result = Long.parseLong(numberText, radix);
            if (negative) {
                result = -result;
            }
            if (!isLong) {
                if (isSigned) {
                    if (result > 2147483647L || result < -2147483648L) {
                        throw new NumberFormatException("Number out of range for 32-bit signed integer: " + text);
                    }
                    return result;
                }
                if (result >= 4294967296L || result < 0) {
                    throw new NumberFormatException("Number out of range for 32-bit unsigned integer: " + text);
                }
                return result;
            }
            return result;
        }
        BigInteger bigValue = new BigInteger(numberText, radix);
        if (negative) {
            bigValue = bigValue.negate();
        }
        if (!isLong) {
            if (isSigned) {
                if (bigValue.bitLength() > 31) {
                    throw new NumberFormatException("Number out of range for 32-bit signed integer: " + text);
                }
            } else if (bigValue.bitLength() > 32) {
                throw new NumberFormatException("Number out of range for 32-bit unsigned integer: " + text);
            }
        } else if (isSigned) {
            if (bigValue.bitLength() > 63) {
                throw new NumberFormatException("Number out of range for 64-bit signed integer: " + text);
            }
        } else if (bigValue.bitLength() > 64) {
            throw new NumberFormatException("Number out of range for 64-bit unsigned integer: " + text);
        }
        return bigValue.longValue();
    }
}
