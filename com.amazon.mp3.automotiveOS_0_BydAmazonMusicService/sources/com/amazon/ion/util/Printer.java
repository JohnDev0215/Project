package com.amazon.ion.util;

import com.amazon.ion.Decimal;
import com.amazon.ion.IonBlob;
import com.amazon.ion.IonBool;
import com.amazon.ion.IonClob;
import com.amazon.ion.IonDatagram;
import com.amazon.ion.IonDecimal;
import com.amazon.ion.IonFloat;
import com.amazon.ion.IonInt;
import com.amazon.ion.IonList;
import com.amazon.ion.IonNull;
import com.amazon.ion.IonSequence;
import com.amazon.ion.IonSexp;
import com.amazon.ion.IonString;
import com.amazon.ion.IonStruct;
import com.amazon.ion.IonSymbol;
import com.amazon.ion.IonTimestamp;
import com.amazon.ion.IonType;
import com.amazon.ion.IonValue;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.Timestamp;
import com.amazon.ion.impl._Private_IonSymbol;
import com.amazon.ion.impl._Private_IonValue;
import com.amazon.ion.util.IonTextUtils;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.amplifyframework.core.model.ModelIdentifier;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Iterator;
import kotlin.text.Typography;

/* JADX INFO: loaded from: classes3.dex */
public class Printer {
    protected Options myOptions;

    public class Options implements Cloneable {
        public boolean blobAsString;
        public boolean clobAsString;
        public boolean datagramAsList;
        public boolean decimalAsFloat;
        public boolean sexpAsList;
        public boolean simplifySystemValues;
        public boolean skipAnnotations;
        public boolean skipSystemValues;
        public boolean stringAsJson;
        public boolean symbolAsString;
        public boolean timestampAsMillis;
        public boolean timestampAsString;
        public boolean untypedNulls;

        public Options() {
        }

        /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
        public Options m104clone() {
            try {
                return (Options) super.clone();
            } catch (CloneNotSupportedException unused) {
                throw new InternalError();
            }
        }
    }

    public Printer() {
        this.myOptions = new Options();
        this.myOptions = new Options();
    }

    public void print(IonValue ionValue, Appendable appendable) throws IOException {
        Options optionsM104clone;
        synchronized (this) {
            optionsM104clone = this.myOptions.m104clone();
        }
        _print(ionValue, makeVisitor(optionsM104clone, appendable));
    }

    private void _print(IonValue ionValue, PrinterVisitor printerVisitor) throws IOException {
        try {
            if (!(ionValue instanceof IonDatagram)) {
                printerVisitor.setSymbolTableProvider(new BasicSymbolTableProvider(ionValue.getSymbolTable()));
            }
            ionValue.accept(printerVisitor);
        } catch (IOException e) {
            throw e;
        } catch (RuntimeException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new RuntimeException(e3);
        }
    }

    protected PrinterVisitor makeVisitor(Options options, Appendable appendable) {
        return new PrinterVisitor(options, appendable);
    }

    private static class BasicSymbolTableProvider implements _Private_IonValue.SymbolTableProvider {
        private final SymbolTable symbolTable;

        public BasicSymbolTableProvider(SymbolTable symbolTable) {
            this.symbolTable = symbolTable;
        }

        @Override // com.amazon.ion.impl._Private_IonValue.SymbolTableProvider
        public SymbolTable getSymbolTable() {
            return this.symbolTable;
        }
    }

    public static class PrinterVisitor extends AbstractValueVisitor {
        protected final Options myOptions;
        protected final Appendable myOut;
        private boolean myQuoteOperators = true;
        private _Private_IonValue.SymbolTableProvider mySymbolTableProvider = null;

        public PrinterVisitor(Options options, Appendable appendable) {
            this.myOptions = options;
            this.myOut = appendable;
        }

        void setSymbolTableProvider(_Private_IonValue.SymbolTableProvider symbolTableProvider) {
            this.mySymbolTableProvider = symbolTableProvider;
        }

        protected void writeChild(IonValue ionValue, boolean z) throws Exception {
            boolean z2 = this.myQuoteOperators;
            this.myQuoteOperators = z;
            ionValue.accept(this);
            this.myQuoteOperators = z2;
        }

        public void writeAnnotations(IonValue ionValue) throws IOException {
            SymbolToken[] typeAnnotationSymbols;
            if (this.myOptions.skipAnnotations || (typeAnnotationSymbols = ((_Private_IonValue) ionValue).getTypeAnnotationSymbols(this.mySymbolTableProvider)) == null) {
                return;
            }
            for (SymbolToken symbolToken : typeAnnotationSymbols) {
                String text = symbolToken.getText();
                if (text == null) {
                    this.myOut.append(Typography.dollar);
                    this.myOut.append(Integer.toString(symbolToken.getSid()));
                } else {
                    IonTextUtils.printSymbol(this.myOut, text);
                }
                this.myOut.append("::");
            }
        }

        public void writeNull(String str) throws IOException {
            if (this.myOptions.untypedNulls) {
                this.myOut.append("null");
            } else {
                this.myOut.append("null.");
                this.myOut.append(str);
            }
        }

        public void writeSequenceContent(IonSequence ionSequence, boolean z, char c, char c2, char c3) throws Exception {
            this.myOut.append(c);
            boolean z2 = false;
            for (IonValue ionValue : ionSequence) {
                if (z2) {
                    this.myOut.append(c2);
                }
                z2 = true;
                writeChild(ionValue, z);
            }
            this.myOut.append(c3);
        }

        public void writeSymbolToken(SymbolToken symbolToken) throws IOException {
            String text = symbolToken.getText();
            if (text != null) {
                writeSymbol(text);
                return;
            }
            int sid = symbolToken.getSid();
            if (sid < 0) {
                throw new IllegalArgumentException("Bad SID " + sid);
            }
            String str = "$" + symbolToken.getSid();
            if (this.myOptions.symbolAsString) {
                writeString(str);
            } else {
                this.myOut.append(str);
            }
        }

        public void writeSymbol(String str) throws IOException {
            if (this.myOptions.symbolAsString) {
                writeString(str);
                return;
            }
            int i = AnonymousClass1.$SwitchMap$com$amazon$ion$util$IonTextUtils$SymbolVariant[IonTextUtils.symbolVariant(str).ordinal()];
            if (i == 1) {
                this.myOut.append(str);
                return;
            }
            if (i != 2) {
                if (i != 3) {
                    return;
                }
            } else if (!this.myQuoteOperators) {
                this.myOut.append(str);
                return;
            }
            IonTextUtils.printQuotedSymbol(this.myOut, str);
        }

        public void writeString(String str) throws IOException {
            if (this.myOptions.stringAsJson) {
                IonTextUtils.printJsonString(this.myOut, str);
            } else {
                IonTextUtils.printString(this.myOut, str);
            }
        }

        @Override // com.amazon.ion.util.AbstractValueVisitor
        protected void defaultVisit(IonValue ionValue) {
            throw new UnsupportedOperationException("cannot print " + ionValue.getClass().getName());
        }

        @Override // com.amazon.ion.util.AbstractValueVisitor, com.amazon.ion.ValueVisitor
        public void visit(IonBlob ionBlob) throws IOException {
            writeAnnotations(ionBlob);
            if (ionBlob.isNullValue()) {
                writeNull("blob");
                return;
            }
            Appendable appendable = this.myOut;
            boolean z = this.myOptions.blobAsString;
            String str = ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR;
            appendable.append(z ? ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR : "{{");
            ionBlob.printBase64(this.myOut);
            Appendable appendable2 = this.myOut;
            if (!this.myOptions.blobAsString) {
                str = "}}";
            }
            appendable2.append(str);
        }

        @Override // com.amazon.ion.util.AbstractValueVisitor, com.amazon.ion.ValueVisitor
        public void visit(IonBool ionBool) throws IOException {
            writeAnnotations(ionBool);
            if (ionBool.isNullValue()) {
                writeNull("bool");
            } else {
                this.myOut.append(ionBool.booleanValue() ? "true" : "false");
            }
        }

        @Override // com.amazon.ion.util.AbstractValueVisitor, com.amazon.ion.ValueVisitor
        public void visit(IonClob ionClob) throws IOException {
            writeAnnotations(ionClob);
            if (ionClob.isNullValue()) {
                writeNull("clob");
                return;
            }
            if (!this.myOptions.clobAsString) {
                this.myOut.append("{{");
            }
            this.myOut.append('\"');
            InputStream inputStreamNewInputStream = ionClob.newInputStream();
            try {
                if (!this.myOptions.stringAsJson) {
                    while (true) {
                        int i = inputStreamNewInputStream.read();
                        if (i == -1) {
                            break;
                        } else {
                            IonTextUtils.printStringCodePoint(this.myOut, i);
                        }
                    }
                } else {
                    while (true) {
                        int i2 = inputStreamNewInputStream.read();
                        if (i2 == -1) {
                            break;
                        } else {
                            IonTextUtils.printJsonCodePoint(this.myOut, i2);
                        }
                    }
                }
                inputStreamNewInputStream.close();
                this.myOut.append('\"');
                if (this.myOptions.clobAsString) {
                    return;
                }
                this.myOut.append("}}");
            } catch (Throwable th) {
                inputStreamNewInputStream.close();
                throw th;
            }
        }

        @Override // com.amazon.ion.util.AbstractValueVisitor, com.amazon.ion.ValueVisitor
        public void visit(IonDatagram ionDatagram) throws Exception {
            Iterator<IonValue> itSystemIterator;
            if (this.myOptions.skipSystemValues) {
                itSystemIterator = ionDatagram.iterator();
            } else {
                itSystemIterator = ionDatagram.systemIterator();
            }
            boolean z = this.myOptions.datagramAsList;
            if (z) {
                this.myOut.append('[');
            }
            Options options = this.myOptions;
            boolean z2 = false;
            boolean z3 = options.simplifySystemValues && !options.skipSystemValues;
            SymbolTable symbolTable = null;
            while (itSystemIterator.hasNext()) {
                IonValue next = itSystemIterator.next();
                SymbolTable symbolTable2 = next.getSymbolTable();
                this.mySymbolTableProvider = new BasicSymbolTableProvider(symbolTable2);
                if (z3) {
                    next = simplify(next, symbolTable);
                    symbolTable = symbolTable2;
                }
                if (next != null) {
                    if (z2) {
                        this.myOut.append(z ? ',' : ' ');
                    }
                    writeChild(next, true);
                    z2 = true;
                }
            }
            if (z) {
                this.myOut.append(']');
            }
        }

        private final IonValue simplify(IonValue ionValue, SymbolTable symbolTable) {
            int i = AnonymousClass1.$SwitchMap$com$amazon$ion$IonType[ionValue.getType().ordinal()];
            if (i == 1) {
                if (ionValue.hasTypeAnnotation("$ion_symbol_table")) {
                    if (symbol_table_struct_has_imports(ionValue)) {
                        return ((IonStruct) ionValue).cloneAndRemove("symbols");
                    }
                    return null;
                }
            } else if (i == 2 && ((IonSymbol) ionValue).getSymbolId() == 2 && symbolTable != null && symbolTable.isSystemTable()) {
                return null;
            }
            return ionValue;
        }

        private static final boolean symbol_table_struct_has_imports(IonValue ionValue) {
            IonValue ionValue2 = ((IonStruct) ionValue).get("imports");
            return (ionValue2 instanceof IonList) && ((IonList) ionValue2).size() != 0;
        }

        @Override // com.amazon.ion.util.AbstractValueVisitor, com.amazon.ion.ValueVisitor
        public void visit(IonDecimal ionDecimal) throws IOException {
            int i;
            writeAnnotations(ionDecimal);
            if (ionDecimal.isNullValue()) {
                writeNull("decimal");
                return;
            }
            Decimal decimalDecimalValue = ionDecimal.decimalValue();
            BigInteger bigIntegerUnscaledValue = decimalDecimalValue.unscaledValue();
            int iSignum = decimalDecimalValue.signum();
            if (iSignum < 0) {
                this.myOut.append('-');
                bigIntegerUnscaledValue = bigIntegerUnscaledValue.negate();
            } else if (iSignum == 0 && decimalDecimalValue.isNegativeZero()) {
                this.myOut.append('-');
            }
            String string = bigIntegerUnscaledValue.toString();
            int length = string.length();
            int iScale = decimalDecimalValue.scale();
            int i2 = -iScale;
            if (this.myOptions.decimalAsFloat) {
                this.myOut.append(string);
                this.myOut.append('e');
                this.myOut.append(Integer.toString(i2));
                return;
            }
            if (i2 == 0) {
                this.myOut.append(string);
                this.myOut.append('.');
                return;
            }
            if (iScale > 0) {
                int i3 = 1;
                if (length > iScale) {
                    i3 = length - iScale;
                    i = 0;
                } else {
                    i = (iScale - length) + 1;
                }
                this.myOut.append(string, 0, i3);
                if (i3 < length) {
                    this.myOut.append('.');
                    this.myOut.append(string, i3, length);
                }
                if (i != 0) {
                    this.myOut.append("d-");
                    this.myOut.append(Integer.toString(i));
                    return;
                }
                return;
            }
            this.myOut.append(string);
            this.myOut.append('d');
            this.myOut.append(Integer.toString(i2));
        }

        @Override // com.amazon.ion.util.AbstractValueVisitor, com.amazon.ion.ValueVisitor
        public void visit(IonFloat ionFloat) throws IOException {
            writeAnnotations(ionFloat);
            if (ionFloat.isNullValue()) {
                writeNull("float");
            } else {
                IonTextUtils.printFloat(this.myOut, ionFloat.doubleValue());
            }
        }

        @Override // com.amazon.ion.util.AbstractValueVisitor, com.amazon.ion.ValueVisitor
        public void visit(IonInt ionInt) throws IOException {
            writeAnnotations(ionInt);
            if (ionInt.isNullValue()) {
                writeNull("int");
            } else {
                this.myOut.append(ionInt.bigIntegerValue().toString(10));
            }
        }

        @Override // com.amazon.ion.util.AbstractValueVisitor, com.amazon.ion.ValueVisitor
        public void visit(IonList ionList) throws Exception {
            writeAnnotations(ionList);
            if (ionList.isNullValue()) {
                writeNull("list");
            } else {
                writeSequenceContent(ionList, true, '[', ',', ']');
            }
        }

        @Override // com.amazon.ion.util.AbstractValueVisitor, com.amazon.ion.ValueVisitor
        public void visit(IonNull ionNull) throws IOException {
            writeAnnotations(ionNull);
            this.myOut.append("null");
        }

        @Override // com.amazon.ion.util.AbstractValueVisitor, com.amazon.ion.ValueVisitor
        public void visit(IonSexp ionSexp) throws Exception {
            writeAnnotations(ionSexp);
            if (ionSexp.isNullValue()) {
                writeNull("sexp");
            } else if (this.myOptions.sexpAsList) {
                writeSequenceContent(ionSexp, true, '[', ',', ']');
            } else {
                writeSequenceContent(ionSexp, false, '(', ' ', ')');
            }
        }

        @Override // com.amazon.ion.util.AbstractValueVisitor, com.amazon.ion.ValueVisitor
        public void visit(IonString ionString) throws IOException {
            writeAnnotations(ionString);
            if (ionString.isNullValue()) {
                writeNull("string");
            } else {
                writeString(ionString.stringValue());
            }
        }

        @Override // com.amazon.ion.util.AbstractValueVisitor, com.amazon.ion.ValueVisitor
        public void visit(IonStruct ionStruct) throws Exception {
            writeAnnotations(ionStruct);
            if (ionStruct.isNullValue()) {
                writeNull("struct");
                return;
            }
            this.myOut.append('{');
            boolean z = false;
            for (IonValue ionValue : ionStruct) {
                if (z) {
                    this.myOut.append(',');
                }
                writeSymbolToken(((_Private_IonValue) ionValue).getFieldNameSymbol(this.mySymbolTableProvider));
                this.myOut.append(':');
                writeChild(ionValue, true);
                z = true;
            }
            this.myOut.append('}');
        }

        @Override // com.amazon.ion.util.AbstractValueVisitor, com.amazon.ion.ValueVisitor
        public void visit(IonSymbol ionSymbol) throws IOException {
            writeAnnotations(ionSymbol);
            SymbolToken symbolTokenSymbolValue = ((_Private_IonSymbol) ionSymbol).symbolValue(this.mySymbolTableProvider);
            if (symbolTokenSymbolValue == null) {
                writeNull("symbol");
            } else {
                writeSymbolToken(symbolTokenSymbolValue);
            }
        }

        @Override // com.amazon.ion.util.AbstractValueVisitor, com.amazon.ion.ValueVisitor
        public void visit(IonTimestamp ionTimestamp) throws IOException {
            writeAnnotations(ionTimestamp);
            if (ionTimestamp.isNullValue()) {
                writeNull(TetheredMessageKey.timestamp);
                return;
            }
            if (this.myOptions.timestampAsMillis) {
                this.myOut.append(Long.toString(ionTimestamp.getMillis()));
                return;
            }
            Timestamp timestampTimestampValue = ionTimestamp.timestampValue();
            if (this.myOptions.timestampAsString) {
                this.myOut.append('\"');
                timestampTimestampValue.print(this.myOut);
                this.myOut.append('\"');
                return;
            }
            timestampTimestampValue.print(this.myOut);
        }
    }

    /* JADX INFO: renamed from: com.amazon.ion.util.Printer$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$IonType;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$ion$util$IonTextUtils$SymbolVariant;

        static {
            int[] iArr = new int[IonType.values().length];
            $SwitchMap$com$amazon$ion$IonType = iArr;
            try {
                iArr[IonType.STRUCT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$ion$IonType[IonType.SYMBOL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[IonTextUtils.SymbolVariant.values().length];
            $SwitchMap$com$amazon$ion$util$IonTextUtils$SymbolVariant = iArr2;
            try {
                iArr2[IonTextUtils.SymbolVariant.IDENTIFIER.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$ion$util$IonTextUtils$SymbolVariant[IonTextUtils.SymbolVariant.OPERATOR.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$ion$util$IonTextUtils$SymbolVariant[IonTextUtils.SymbolVariant.QUOTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }
}
