package org.apache.commons.compress.harmony.pack200;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.compress.harmony.pack200.AttributeDefinitionBands;
import org.objectweb.asm.Label;

/* JADX INFO: loaded from: classes4.dex */
public class NewAttributeBands extends BandSet {
    protected List attributeLayoutElements;
    private int[] backwardsCallCounts;
    private final CpBands cpBands;
    private final AttributeDefinitionBands.AttributeDefinition def;
    private Integral lastPIntegral;
    private boolean usedAtLeastOnce;

    public interface AttributeLayoutElement {
        void addAttributeToBand(NewAttribute newAttribute, InputStream inputStream);

        void pack(OutputStream outputStream) throws Pack200Exception, IOException;

        void renumberBci(IntList intList, Map map);
    }

    public NewAttributeBands(int i, CpBands cpBands, SegmentHeader segmentHeader, AttributeDefinitionBands.AttributeDefinition attributeDefinition) throws IOException {
        super(i, segmentHeader);
        this.def = attributeDefinition;
        this.cpBands = cpBands;
        parseLayout();
    }

    public void addAttribute(NewAttribute newAttribute) {
        this.usedAtLeastOnce = true;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(newAttribute.getBytes());
        Iterator it = this.attributeLayoutElements.iterator();
        while (it.hasNext()) {
            ((AttributeLayoutElement) it.next()).addAttributeToBand(newAttribute, byteArrayInputStream);
        }
    }

    @Override // org.apache.commons.compress.harmony.pack200.BandSet
    public void pack(OutputStream outputStream) throws Pack200Exception, IOException {
        Iterator it = this.attributeLayoutElements.iterator();
        while (it.hasNext()) {
            ((AttributeLayoutElement) it.next()).pack(outputStream);
        }
    }

    public String getAttributeName() {
        return this.def.name.getUnderlyingString();
    }

    public int getFlagIndex() {
        return this.def.index;
    }

    public int[] numBackwardsCalls() {
        return this.backwardsCallCounts;
    }

    public boolean isUsedAtLeastOnce() {
        return this.usedAtLeastOnce;
    }

    private void parseLayout() throws IOException {
        String underlyingString = this.def.layout.getUnderlyingString();
        if (this.attributeLayoutElements != null) {
            return;
        }
        this.attributeLayoutElements = new ArrayList();
        StringReader stringReader = new StringReader(underlyingString);
        while (true) {
            AttributeLayoutElement nextAttributeElement = readNextAttributeElement(stringReader);
            if (nextAttributeElement != null) {
                this.attributeLayoutElements.add(nextAttributeElement);
            } else {
                resolveCalls();
                return;
            }
        }
    }

    private void resolveCalls() {
        for (int i = 0; i < this.attributeLayoutElements.size(); i++) {
            AttributeLayoutElement attributeLayoutElement = (AttributeLayoutElement) this.attributeLayoutElements.get(i);
            if (attributeLayoutElement instanceof Callable) {
                Callable callable = (Callable) attributeLayoutElement;
                List list = callable.body;
                for (int i2 = 0; i2 < list.size(); i2++) {
                    resolveCallsForElement(i, callable, (LayoutElement) list.get(i2));
                }
            }
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.attributeLayoutElements.size(); i4++) {
            AttributeLayoutElement attributeLayoutElement2 = (AttributeLayoutElement) this.attributeLayoutElements.get(i4);
            if (attributeLayoutElement2 instanceof Callable) {
                Callable callable2 = (Callable) attributeLayoutElement2;
                if (callable2.isBackwardsCallable) {
                    callable2.setBackwardsCallableIndex(i3);
                    i3++;
                }
            }
        }
        this.backwardsCallCounts = new int[i3];
    }

    private void resolveCallsForElement(int i, Callable callable, LayoutElement layoutElement) {
        if (layoutElement instanceof Call) {
            Call call = (Call) layoutElement;
            int i2 = call.callableIndex;
            if (i2 == 0) {
                call.setCallable(callable);
                return;
            }
            if (i2 <= 0) {
                for (int i3 = i - 1; i3 >= 0; i3--) {
                    AttributeLayoutElement attributeLayoutElement = (AttributeLayoutElement) this.attributeLayoutElements.get(i3);
                    if ((attributeLayoutElement instanceof Callable) && (i2 = i2 + 1) == 0) {
                        call.setCallable((Callable) attributeLayoutElement);
                        return;
                    }
                }
                return;
            }
            while (true) {
                i++;
                if (i >= this.attributeLayoutElements.size()) {
                    return;
                }
                AttributeLayoutElement attributeLayoutElement2 = (AttributeLayoutElement) this.attributeLayoutElements.get(i);
                if ((attributeLayoutElement2 instanceof Callable) && i2 - 1 == 0) {
                    call.setCallable((Callable) attributeLayoutElement2);
                    return;
                }
            }
        } else if (layoutElement instanceof Replication) {
            Iterator it = ((Replication) layoutElement).layoutElements.iterator();
            while (it.hasNext()) {
                resolveCallsForElement(i, callable, (LayoutElement) it.next());
            }
        }
    }

    private AttributeLayoutElement readNextAttributeElement(StringReader stringReader) throws IOException {
        stringReader.mark(1);
        int i = stringReader.read();
        if (i == -1) {
            return null;
        }
        if (i == 91) {
            return new Callable(readBody(getStreamUpToMatchingBracket(stringReader)));
        }
        stringReader.reset();
        return readNextLayoutElement(stringReader);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public LayoutElement readNextLayoutElement(StringReader stringReader) throws IOException {
        int i = stringReader.read();
        List body = null;
        if (i == -1) {
            return null;
        }
        if (i != 40) {
            if (i != 66) {
                if (i != 70) {
                    if (i != 75) {
                        if (i != 86 && i != 72 && i != 73) {
                            switch (i) {
                                case 78:
                                    char c = (char) stringReader.read();
                                    stringReader.read();
                                    break;
                                case 79:
                                    stringReader.mark(1);
                                    if (stringReader.read() != 83) {
                                        stringReader.reset();
                                    }
                                    break;
                                case 80:
                                    stringReader.mark(1);
                                    if (stringReader.read() != 79) {
                                        stringReader.reset();
                                        Integral integral = new Integral("P" + ((char) stringReader.read()));
                                        this.lastPIntegral = integral;
                                    } else {
                                        Integral integral2 = new Integral("PO" + ((char) stringReader.read()), this.lastPIntegral);
                                        this.lastPIntegral = integral2;
                                    }
                                    break;
                                default:
                                    switch (i) {
                                        case 84:
                                            String str = "" + ((char) stringReader.read());
                                            if (str.equals("S")) {
                                                str = str + ((char) stringReader.read());
                                            }
                                            ArrayList arrayList = new ArrayList();
                                            while (true) {
                                                UnionCase nextUnionCase = readNextUnionCase(stringReader);
                                                if (nextUnionCase != null) {
                                                    arrayList.add(nextUnionCase);
                                                } else {
                                                    stringReader.read();
                                                    stringReader.read();
                                                    stringReader.read();
                                                    stringReader.mark(1);
                                                    if (((char) stringReader.read()) != ']') {
                                                        stringReader.reset();
                                                        body = readBody(getStreamUpToMatchingBracket(stringReader));
                                                    }
                                                }
                                                break;
                                            }
                                            break;
                                    }
                                    break;
                            }
                            return null;
                        }
                    }
                    StringBuilder sbAppend = new StringBuilder("").append((char) i).append((char) stringReader.read());
                    char c2 = (char) stringReader.read();
                    sbAppend.append(c2);
                    if (c2 == 'N') {
                        sbAppend.append((char) stringReader.read());
                    }
                    return new Reference(sbAppend.toString());
                }
                return new Integral(new String(new char[]{(char) i, (char) stringReader.read()}));
            }
            return new Integral(new String(new char[]{(char) i}));
        }
        int iIntValue = readNumber(stringReader).intValue();
        stringReader.read();
        return new Call(iIntValue);
    }

    private UnionCase readNextUnionCase(StringReader stringReader) throws IOException {
        Integer number;
        stringReader.mark(2);
        stringReader.read();
        if (((char) stringReader.read()) == ')') {
            stringReader.reset();
            return null;
        }
        stringReader.reset();
        stringReader.read();
        ArrayList arrayList = new ArrayList();
        do {
            number = readNumber(stringReader);
            if (number != null) {
                arrayList.add(number);
                stringReader.read();
            }
        } while (number != null);
        stringReader.read();
        stringReader.mark(1);
        if (((char) stringReader.read()) == ']') {
            return new UnionCase(arrayList);
        }
        stringReader.reset();
        return new UnionCase(arrayList, readBody(getStreamUpToMatchingBracket(stringReader)));
    }

    public abstract class LayoutElement implements AttributeLayoutElement {
        protected int getLength(char c) {
            if (c == 'B') {
                return 1;
            }
            if (c == 'V') {
                return 0;
            }
            if (c != 'H') {
                return c != 'I' ? 0 : 4;
            }
            return 2;
        }

        public LayoutElement() {
        }
    }

    public class Integral extends LayoutElement {
        private final List band;
        private final BHSDCodec defaultCodec;
        private Integral previousIntegral;
        private int previousPValue;
        private final String tag;

        public Integral(String str) {
            super();
            this.band = new ArrayList();
            this.tag = str;
            this.defaultCodec = NewAttributeBands.this.getCodec(str);
        }

        public Integral(String str, Integral integral) {
            super();
            this.band = new ArrayList();
            this.tag = str;
            this.defaultCodec = NewAttributeBands.this.getCodec(str);
            this.previousIntegral = integral;
        }

        public String getTag() {
            return this.tag;
        }

        @Override // org.apache.commons.compress.harmony.pack200.NewAttributeBands.AttributeLayoutElement
        public void addAttributeToBand(NewAttribute newAttribute, InputStream inputStream) {
            int integer;
            int integer2;
            Label labelValueOf = null;
            if (this.tag.equals("B") || this.tag.equals("FB")) {
                integer = NewAttributeBands.this.readInteger(1, inputStream) & 255;
            } else if (this.tag.equals("SB")) {
                integer = NewAttributeBands.this.readInteger(1, inputStream);
            } else if (this.tag.equals("H") || this.tag.equals("FH")) {
                integer = NewAttributeBands.this.readInteger(2, inputStream) & 65535;
            } else if (this.tag.equals("SH")) {
                integer = NewAttributeBands.this.readInteger(2, inputStream);
            } else if (this.tag.equals("I") || this.tag.equals("FI") || this.tag.equals("SI")) {
                integer = NewAttributeBands.this.readInteger(4, inputStream);
            } else if (this.tag.equals("V") || this.tag.equals("FV") || this.tag.equals("SV")) {
                integer = 0;
            } else {
                if (this.tag.startsWith("PO") || this.tag.startsWith("OS")) {
                    integer2 = NewAttributeBands.this.readInteger(getLength(this.tag.substring(2).toCharArray()[0]), inputStream) + this.previousIntegral.previousPValue;
                    labelValueOf = newAttribute.getLabel(integer2);
                    this.previousPValue = integer2;
                } else if (this.tag.startsWith("P")) {
                    integer2 = NewAttributeBands.this.readInteger(getLength(this.tag.substring(1).toCharArray()[0]), inputStream);
                    labelValueOf = newAttribute.getLabel(integer2);
                    this.previousPValue = integer2;
                } else {
                    if (this.tag.startsWith("O")) {
                        integer2 = NewAttributeBands.this.readInteger(getLength(this.tag.substring(1).toCharArray()[0]), inputStream) + this.previousIntegral.previousPValue;
                        labelValueOf = newAttribute.getLabel(integer2);
                        this.previousPValue = integer2;
                    }
                    integer = 0;
                }
                integer = integer2;
            }
            if (labelValueOf == null) {
                labelValueOf = Integer.valueOf(integer);
            }
            this.band.add(labelValueOf);
        }

        @Override // org.apache.commons.compress.harmony.pack200.NewAttributeBands.AttributeLayoutElement
        public void pack(OutputStream outputStream) throws Pack200Exception, IOException {
            PackingUtils.log("Writing new attribute bands...");
            NewAttributeBands newAttributeBands = NewAttributeBands.this;
            byte[] bArrEncodeBandInt = newAttributeBands.encodeBandInt(this.tag, newAttributeBands.integerListToArray(this.band), this.defaultCodec);
            outputStream.write(bArrEncodeBandInt);
            PackingUtils.log("Wrote " + bArrEncodeBandInt.length + " bytes from " + this.tag + "[" + this.band.size() + "]");
        }

        public int latestValue() {
            return ((Integer) this.band.get(r0.size() - 1)).intValue();
        }

        @Override // org.apache.commons.compress.harmony.pack200.NewAttributeBands.AttributeLayoutElement
        public void renumberBci(IntList intList, Map map) {
            if (this.tag.startsWith("O") || this.tag.startsWith("PO")) {
                renumberOffsetBci(this.previousIntegral.band, intList, map);
                return;
            }
            if (this.tag.startsWith("P")) {
                for (int size = this.band.size() - 1; size >= 0; size--) {
                    Object obj = this.band.get(size);
                    if (obj instanceof Integer) {
                        return;
                    }
                    if (obj instanceof Label) {
                        this.band.remove(size);
                        this.band.add(size, Integer.valueOf(intList.get(((Integer) map.get(obj)).intValue())));
                    }
                }
            }
        }

        private void renumberOffsetBci(List list, IntList intList, Map map) {
            for (int size = this.band.size() - 1; size >= 0; size--) {
                Object obj = this.band.get(size);
                if (obj instanceof Integer) {
                    return;
                }
                if (obj instanceof Label) {
                    this.band.remove(size);
                    this.band.add(size, Integer.valueOf(intList.get(((Integer) map.get(obj)).intValue()) - ((Integer) list.get(size)).intValue()));
                }
            }
        }
    }

    public class Replication extends LayoutElement {
        private final Integral countElement;
        private final List layoutElements;

        public Integral getCountElement() {
            return this.countElement;
        }

        public List getLayoutElements() {
            return this.layoutElements;
        }

        public Replication(String str, String str2) throws IOException {
            super();
            this.layoutElements = new ArrayList();
            this.countElement = NewAttributeBands.this.new Integral(str);
            StringReader stringReader = new StringReader(str2);
            while (true) {
                LayoutElement nextLayoutElement = NewAttributeBands.this.readNextLayoutElement(stringReader);
                if (nextLayoutElement == null) {
                    return;
                } else {
                    this.layoutElements.add(nextLayoutElement);
                }
            }
        }

        @Override // org.apache.commons.compress.harmony.pack200.NewAttributeBands.AttributeLayoutElement
        public void addAttributeToBand(NewAttribute newAttribute, InputStream inputStream) {
            this.countElement.addAttributeToBand(newAttribute, inputStream);
            int iLatestValue = this.countElement.latestValue();
            for (int i = 0; i < iLatestValue; i++) {
                Iterator it = this.layoutElements.iterator();
                while (it.hasNext()) {
                    ((AttributeLayoutElement) it.next()).addAttributeToBand(newAttribute, inputStream);
                }
            }
        }

        @Override // org.apache.commons.compress.harmony.pack200.NewAttributeBands.AttributeLayoutElement
        public void pack(OutputStream outputStream) throws Pack200Exception, IOException {
            this.countElement.pack(outputStream);
            Iterator it = this.layoutElements.iterator();
            while (it.hasNext()) {
                ((AttributeLayoutElement) it.next()).pack(outputStream);
            }
        }

        @Override // org.apache.commons.compress.harmony.pack200.NewAttributeBands.AttributeLayoutElement
        public void renumberBci(IntList intList, Map map) {
            Iterator it = this.layoutElements.iterator();
            while (it.hasNext()) {
                ((AttributeLayoutElement) it.next()).renumberBci(intList, map);
            }
        }
    }

    public class Union extends LayoutElement {
        private final List defaultCaseBody;
        private final List unionCases;
        private final Integral unionTag;

        public Union(String str, List list, List list2) {
            super();
            this.unionTag = NewAttributeBands.this.new Integral(str);
            this.unionCases = list;
            this.defaultCaseBody = list2;
        }

        @Override // org.apache.commons.compress.harmony.pack200.NewAttributeBands.AttributeLayoutElement
        public void addAttributeToBand(NewAttribute newAttribute, InputStream inputStream) {
            this.unionTag.addAttributeToBand(newAttribute, inputStream);
            long jLatestValue = this.unionTag.latestValue();
            boolean z = true;
            for (int i = 0; i < this.unionCases.size(); i++) {
                UnionCase unionCase = (UnionCase) this.unionCases.get(i);
                if (unionCase.hasTag(jLatestValue)) {
                    unionCase.addAttributeToBand(newAttribute, inputStream);
                    z = false;
                }
            }
            if (z) {
                for (int i2 = 0; i2 < this.defaultCaseBody.size(); i2++) {
                    ((LayoutElement) this.defaultCaseBody.get(i2)).addAttributeToBand(newAttribute, inputStream);
                }
            }
        }

        @Override // org.apache.commons.compress.harmony.pack200.NewAttributeBands.AttributeLayoutElement
        public void pack(OutputStream outputStream) throws Pack200Exception, IOException {
            this.unionTag.pack(outputStream);
            Iterator it = this.unionCases.iterator();
            while (it.hasNext()) {
                ((UnionCase) it.next()).pack(outputStream);
            }
            Iterator it2 = this.defaultCaseBody.iterator();
            while (it2.hasNext()) {
                ((AttributeLayoutElement) it2.next()).pack(outputStream);
            }
        }

        @Override // org.apache.commons.compress.harmony.pack200.NewAttributeBands.AttributeLayoutElement
        public void renumberBci(IntList intList, Map map) {
            Iterator it = this.unionCases.iterator();
            while (it.hasNext()) {
                ((UnionCase) it.next()).renumberBci(intList, map);
            }
            Iterator it2 = this.defaultCaseBody.iterator();
            while (it2.hasNext()) {
                ((AttributeLayoutElement) it2.next()).renumberBci(intList, map);
            }
        }

        public Integral getUnionTag() {
            return this.unionTag;
        }

        public List getUnionCases() {
            return this.unionCases;
        }

        public List getDefaultCaseBody() {
            return this.defaultCaseBody;
        }
    }

    public class Call extends LayoutElement {
        private Callable callable;
        private final int callableIndex;

        @Override // org.apache.commons.compress.harmony.pack200.NewAttributeBands.AttributeLayoutElement
        public void pack(OutputStream outputStream) {
        }

        @Override // org.apache.commons.compress.harmony.pack200.NewAttributeBands.AttributeLayoutElement
        public void renumberBci(IntList intList, Map map) {
        }

        public Call(int i) {
            super();
            this.callableIndex = i;
        }

        public void setCallable(Callable callable) {
            this.callable = callable;
            if (this.callableIndex < 1) {
                callable.setBackwardsCallable();
            }
        }

        @Override // org.apache.commons.compress.harmony.pack200.NewAttributeBands.AttributeLayoutElement
        public void addAttributeToBand(NewAttribute newAttribute, InputStream inputStream) {
            this.callable.addAttributeToBand(newAttribute, inputStream);
            if (this.callableIndex < 1) {
                this.callable.addBackwardsCall();
            }
        }

        public int getCallableIndex() {
            return this.callableIndex;
        }

        public Callable getCallable() {
            return this.callable;
        }
    }

    public class Reference extends LayoutElement {
        private List band;
        private boolean nullsAllowed;
        private final String tag;

        @Override // org.apache.commons.compress.harmony.pack200.NewAttributeBands.AttributeLayoutElement
        public void renumberBci(IntList intList, Map map) {
        }

        public Reference(String str) {
            super();
            this.nullsAllowed = false;
            this.tag = str;
            this.nullsAllowed = str.indexOf(78) != -1;
        }

        @Override // org.apache.commons.compress.harmony.pack200.NewAttributeBands.AttributeLayoutElement
        public void addAttributeToBand(NewAttribute newAttribute, InputStream inputStream) {
            int integer = NewAttributeBands.this.readInteger(4, inputStream);
            if (this.tag.startsWith("RC")) {
                this.band.add(NewAttributeBands.this.cpBands.getCPClass(newAttribute.readClass(integer)));
                return;
            }
            if (this.tag.startsWith("RU")) {
                this.band.add(NewAttributeBands.this.cpBands.getCPUtf8(newAttribute.readUTF8(integer)));
            } else if (this.tag.startsWith("RS")) {
                this.band.add(NewAttributeBands.this.cpBands.getCPSignature(newAttribute.readUTF8(integer)));
            } else {
                this.band.add(NewAttributeBands.this.cpBands.getConstant(newAttribute.readConst(integer)));
            }
        }

        public String getTag() {
            return this.tag;
        }

        @Override // org.apache.commons.compress.harmony.pack200.NewAttributeBands.AttributeLayoutElement
        public void pack(OutputStream outputStream) throws Pack200Exception, IOException {
            int[] iArrCpEntryListToArray;
            if (this.nullsAllowed) {
                iArrCpEntryListToArray = NewAttributeBands.this.cpEntryOrNullListToArray(this.band);
            } else {
                iArrCpEntryListToArray = NewAttributeBands.this.cpEntryListToArray(this.band);
            }
            byte[] bArrEncodeBandInt = NewAttributeBands.this.encodeBandInt(this.tag, iArrCpEntryListToArray, Codec.UNSIGNED5);
            outputStream.write(bArrEncodeBandInt);
            PackingUtils.log("Wrote " + bArrEncodeBandInt.length + " bytes from " + this.tag + "[" + iArrCpEntryListToArray.length + "]");
        }
    }

    public class Callable implements AttributeLayoutElement {
        private int backwardsCallableIndex;
        private final List body;
        private boolean isBackwardsCallable;

        public Callable(List list) throws IOException {
            this.body = list;
        }

        public void setBackwardsCallableIndex(int i) {
            this.backwardsCallableIndex = i;
        }

        public void addBackwardsCall() {
            int[] iArr = NewAttributeBands.this.backwardsCallCounts;
            int i = this.backwardsCallableIndex;
            iArr[i] = iArr[i] + 1;
        }

        public boolean isBackwardsCallable() {
            return this.isBackwardsCallable;
        }

        public void setBackwardsCallable() {
            this.isBackwardsCallable = true;
        }

        @Override // org.apache.commons.compress.harmony.pack200.NewAttributeBands.AttributeLayoutElement
        public void addAttributeToBand(NewAttribute newAttribute, InputStream inputStream) {
            Iterator it = this.body.iterator();
            while (it.hasNext()) {
                ((AttributeLayoutElement) it.next()).addAttributeToBand(newAttribute, inputStream);
            }
        }

        @Override // org.apache.commons.compress.harmony.pack200.NewAttributeBands.AttributeLayoutElement
        public void pack(OutputStream outputStream) throws Pack200Exception, IOException {
            Iterator it = this.body.iterator();
            while (it.hasNext()) {
                ((AttributeLayoutElement) it.next()).pack(outputStream);
            }
        }

        @Override // org.apache.commons.compress.harmony.pack200.NewAttributeBands.AttributeLayoutElement
        public void renumberBci(IntList intList, Map map) {
            Iterator it = this.body.iterator();
            while (it.hasNext()) {
                ((AttributeLayoutElement) it.next()).renumberBci(intList, map);
            }
        }

        public List getBody() {
            return this.body;
        }
    }

    public class UnionCase extends LayoutElement {
        private final List body;
        private final List tags;

        public UnionCase(List list) {
            super();
            this.tags = list;
            this.body = Collections.EMPTY_LIST;
        }

        public boolean hasTag(long j) {
            return this.tags.contains(Integer.valueOf((int) j));
        }

        public UnionCase(List list, List list2) throws IOException {
            super();
            this.tags = list;
            this.body = list2;
        }

        @Override // org.apache.commons.compress.harmony.pack200.NewAttributeBands.AttributeLayoutElement
        public void addAttributeToBand(NewAttribute newAttribute, InputStream inputStream) {
            for (int i = 0; i < this.body.size(); i++) {
                ((LayoutElement) this.body.get(i)).addAttributeToBand(newAttribute, inputStream);
            }
        }

        @Override // org.apache.commons.compress.harmony.pack200.NewAttributeBands.AttributeLayoutElement
        public void pack(OutputStream outputStream) throws Pack200Exception, IOException {
            for (int i = 0; i < this.body.size(); i++) {
                ((LayoutElement) this.body.get(i)).pack(outputStream);
            }
        }

        @Override // org.apache.commons.compress.harmony.pack200.NewAttributeBands.AttributeLayoutElement
        public void renumberBci(IntList intList, Map map) {
            for (int i = 0; i < this.body.size(); i++) {
                ((LayoutElement) this.body.get(i)).renumberBci(intList, map);
            }
        }

        public List getBody() {
            return this.body;
        }
    }

    private StringReader getStreamUpToMatchingBracket(StringReader stringReader) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        int i = -1;
        while (i != 0) {
            char c = (char) stringReader.read();
            if (c == ']') {
                i++;
            }
            if (c == '[') {
                i--;
            }
            if (i != 0) {
                stringBuffer.append(c);
            }
        }
        return new StringReader(stringBuffer.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int readInteger(int i, InputStream inputStream) {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            try {
                i2 = (i2 << 8) | inputStream.read();
            } catch (IOException unused) {
                throw new RuntimeException("Error reading unknown attribute");
            }
        }
        if (i == 1) {
            i2 = (byte) i2;
        }
        return i == 2 ? (short) i2 : i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BHSDCodec getCodec(String str) {
        if (str.indexOf(79) >= 0) {
            return Codec.BRANCH5;
        }
        if (str.indexOf(80) >= 0) {
            return Codec.BCI5;
        }
        if (str.indexOf(83) >= 0 && str.indexOf("KS") < 0 && str.indexOf("RS") < 0) {
            return Codec.SIGNED5;
        }
        if (str.indexOf(66) >= 0) {
            return Codec.BYTE1;
        }
        return Codec.UNSIGNED5;
    }

    private String readUpToMatchingBracket(StringReader stringReader) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        int i = -1;
        while (i != 0) {
            char c = (char) stringReader.read();
            if (c == ']') {
                i++;
            }
            if (c == '[') {
                i--;
            }
            if (i != 0) {
                stringBuffer.append(c);
            }
        }
        return stringBuffer.toString();
    }

    private Integer readNumber(StringReader stringReader) throws IOException {
        stringReader.mark(1);
        int i = 0;
        boolean z = ((char) stringReader.read()) == '-';
        if (!z) {
            stringReader.reset();
        }
        stringReader.mark(100);
        while (true) {
            int i2 = stringReader.read();
            if (i2 == -1 || !Character.isDigit((char) i2)) {
                break;
            }
            i++;
        }
        stringReader.reset();
        if (i == 0) {
            return null;
        }
        char[] cArr = new char[i];
        if (stringReader.read(cArr) != i) {
            throw new IOException("Error reading from the input stream");
        }
        return Integer.valueOf(Integer.parseInt((z ? "-" : "").concat(new String(cArr))));
    }

    private List readBody(StringReader stringReader) throws IOException {
        ArrayList arrayList = new ArrayList();
        while (true) {
            LayoutElement nextLayoutElement = readNextLayoutElement(stringReader);
            if (nextLayoutElement == null) {
                return arrayList;
            }
            arrayList.add(nextLayoutElement);
        }
    }

    public void renumberBci(IntList intList, Map map) {
        Iterator it = this.attributeLayoutElements.iterator();
        while (it.hasNext()) {
            ((AttributeLayoutElement) it.next()).renumberBci(intList, map);
        }
    }
}
