package org.apache.commons.compress.harmony.pack200;

import cerence.ark.assistant.controller.enumeration.TemperatureUnitEnum;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class MetadataBandGroup extends BandSet {
    public static final int CONTEXT_CLASS = 0;
    public static final int CONTEXT_FIELD = 1;
    public static final int CONTEXT_METHOD = 2;
    public List T;
    public IntList anno_N;
    public List caseD_KD;
    public List caseF_KF;
    public List caseI_KI;
    public List caseJ_KJ;
    public IntList casearray_N;
    public List casec_RS;
    public List caseec_RU;
    public List caseet_RS;
    public List cases_RU;
    private final int context;
    private final CpBands cpBands;
    public List name_RU;
    public List nestname_RU;
    public IntList nestpair_N;
    public List nesttype_RS;
    private int numBackwardsCalls;
    public IntList pair_N;
    public IntList param_NB;
    private final String type;
    public List type_RS;

    public MetadataBandGroup(String str, int i, CpBands cpBands, SegmentHeader segmentHeader, int i2) {
        super(i2, segmentHeader);
        this.numBackwardsCalls = 0;
        this.param_NB = new IntList();
        this.anno_N = new IntList();
        this.type_RS = new ArrayList();
        this.pair_N = new IntList();
        this.name_RU = new ArrayList();
        this.T = new ArrayList();
        this.caseI_KI = new ArrayList();
        this.caseD_KD = new ArrayList();
        this.caseF_KF = new ArrayList();
        this.caseJ_KJ = new ArrayList();
        this.casec_RS = new ArrayList();
        this.caseet_RS = new ArrayList();
        this.caseec_RU = new ArrayList();
        this.cases_RU = new ArrayList();
        this.casearray_N = new IntList();
        this.nesttype_RS = new ArrayList();
        this.nestpair_N = new IntList();
        this.nestname_RU = new ArrayList();
        this.type = str;
        this.cpBands = cpBands;
        this.context = i;
    }

    @Override // org.apache.commons.compress.harmony.pack200.BandSet
    public void pack(OutputStream outputStream) throws Pack200Exception, IOException {
        PackingUtils.log("Writing metadata band group...");
        if (hasContent()) {
            int i = this.context;
            String str = i == 0 ? "Class" : i == 1 ? "Field" : "Method";
            if (!this.type.equals("AD")) {
                if (this.type.indexOf(80) != -1) {
                    byte[] bArrEncodeBandInt = encodeBandInt(str + "_" + this.type + " param_NB", this.param_NB.toArray(), Codec.BYTE1);
                    outputStream.write(bArrEncodeBandInt);
                    PackingUtils.log("Wrote " + bArrEncodeBandInt.length + " bytes from " + str + "_" + this.type + " anno_N[" + this.param_NB.size() + "]");
                }
                byte[] bArrEncodeBandInt2 = encodeBandInt(str + "_" + this.type + " anno_N", this.anno_N.toArray(), Codec.UNSIGNED5);
                outputStream.write(bArrEncodeBandInt2);
                PackingUtils.log("Wrote " + bArrEncodeBandInt2.length + " bytes from " + str + "_" + this.type + " anno_N[" + this.anno_N.size() + "]");
                byte[] bArrEncodeBandInt3 = encodeBandInt(str + "_" + this.type + " type_RS", cpEntryListToArray(this.type_RS), Codec.UNSIGNED5);
                outputStream.write(bArrEncodeBandInt3);
                PackingUtils.log("Wrote " + bArrEncodeBandInt3.length + " bytes from " + str + "_" + this.type + " type_RS[" + this.type_RS.size() + "]");
                byte[] bArrEncodeBandInt4 = encodeBandInt(str + "_" + this.type + " pair_N", this.pair_N.toArray(), Codec.UNSIGNED5);
                outputStream.write(bArrEncodeBandInt4);
                PackingUtils.log("Wrote " + bArrEncodeBandInt4.length + " bytes from " + str + "_" + this.type + " pair_N[" + this.pair_N.size() + "]");
                byte[] bArrEncodeBandInt5 = encodeBandInt(str + "_" + this.type + " name_RU", cpEntryListToArray(this.name_RU), Codec.UNSIGNED5);
                outputStream.write(bArrEncodeBandInt5);
                PackingUtils.log("Wrote " + bArrEncodeBandInt5.length + " bytes from " + str + "_" + this.type + " name_RU[" + this.name_RU.size() + "]");
            }
            byte[] bArrEncodeBandInt6 = encodeBandInt(str + "_" + this.type + " T", tagListToArray(this.T), Codec.BYTE1);
            outputStream.write(bArrEncodeBandInt6);
            PackingUtils.log("Wrote " + bArrEncodeBandInt6.length + " bytes from " + str + "_" + this.type + " T[" + this.T.size() + "]");
            byte[] bArrEncodeBandInt7 = encodeBandInt(str + "_" + this.type + " caseI_KI", cpEntryListToArray(this.caseI_KI), Codec.UNSIGNED5);
            outputStream.write(bArrEncodeBandInt7);
            PackingUtils.log("Wrote " + bArrEncodeBandInt7.length + " bytes from " + str + "_" + this.type + " caseI_KI[" + this.caseI_KI.size() + "]");
            byte[] bArrEncodeBandInt8 = encodeBandInt(str + "_" + this.type + " caseD_KD", cpEntryListToArray(this.caseD_KD), Codec.UNSIGNED5);
            outputStream.write(bArrEncodeBandInt8);
            PackingUtils.log("Wrote " + bArrEncodeBandInt8.length + " bytes from " + str + "_" + this.type + " caseD_KD[" + this.caseD_KD.size() + "]");
            byte[] bArrEncodeBandInt9 = encodeBandInt(str + "_" + this.type + " caseF_KF", cpEntryListToArray(this.caseF_KF), Codec.UNSIGNED5);
            outputStream.write(bArrEncodeBandInt9);
            PackingUtils.log("Wrote " + bArrEncodeBandInt9.length + " bytes from " + str + "_" + this.type + " caseF_KF[" + this.caseF_KF.size() + "]");
            byte[] bArrEncodeBandInt10 = encodeBandInt(str + "_" + this.type + " caseJ_KJ", cpEntryListToArray(this.caseJ_KJ), Codec.UNSIGNED5);
            outputStream.write(bArrEncodeBandInt10);
            PackingUtils.log("Wrote " + bArrEncodeBandInt10.length + " bytes from " + str + "_" + this.type + " caseJ_KJ[" + this.caseJ_KJ.size() + "]");
            byte[] bArrEncodeBandInt11 = encodeBandInt(str + "_" + this.type + " casec_RS", cpEntryListToArray(this.casec_RS), Codec.UNSIGNED5);
            outputStream.write(bArrEncodeBandInt11);
            PackingUtils.log("Wrote " + bArrEncodeBandInt11.length + " bytes from " + str + "_" + this.type + " casec_RS[" + this.casec_RS.size() + "]");
            byte[] bArrEncodeBandInt12 = encodeBandInt(str + "_" + this.type + " caseet_RS", cpEntryListToArray(this.caseet_RS), Codec.UNSIGNED5);
            outputStream.write(bArrEncodeBandInt12);
            PackingUtils.log("Wrote " + bArrEncodeBandInt12.length + " bytes from " + str + "_" + this.type + " caseet_RS[" + this.caseet_RS.size() + "]");
            byte[] bArrEncodeBandInt13 = encodeBandInt(str + "_" + this.type + " caseec_RU", cpEntryListToArray(this.caseec_RU), Codec.UNSIGNED5);
            outputStream.write(bArrEncodeBandInt13);
            PackingUtils.log("Wrote " + bArrEncodeBandInt13.length + " bytes from " + str + "_" + this.type + " caseec_RU[" + this.caseec_RU.size() + "]");
            byte[] bArrEncodeBandInt14 = encodeBandInt(str + "_" + this.type + " cases_RU", cpEntryListToArray(this.cases_RU), Codec.UNSIGNED5);
            outputStream.write(bArrEncodeBandInt14);
            PackingUtils.log("Wrote " + bArrEncodeBandInt14.length + " bytes from " + str + "_" + this.type + " cases_RU[" + this.cases_RU.size() + "]");
            byte[] bArrEncodeBandInt15 = encodeBandInt(str + "_" + this.type + " casearray_N", this.casearray_N.toArray(), Codec.UNSIGNED5);
            outputStream.write(bArrEncodeBandInt15);
            PackingUtils.log("Wrote " + bArrEncodeBandInt15.length + " bytes from " + str + "_" + this.type + " casearray_N[" + this.casearray_N.size() + "]");
            byte[] bArrEncodeBandInt16 = encodeBandInt(str + "_" + this.type + " nesttype_RS", cpEntryListToArray(this.nesttype_RS), Codec.UNSIGNED5);
            outputStream.write(bArrEncodeBandInt16);
            PackingUtils.log("Wrote " + bArrEncodeBandInt16.length + " bytes from " + str + "_" + this.type + " nesttype_RS[" + this.nesttype_RS.size() + "]");
            byte[] bArrEncodeBandInt17 = encodeBandInt(str + "_" + this.type + " nestpair_N", this.nestpair_N.toArray(), Codec.UNSIGNED5);
            outputStream.write(bArrEncodeBandInt17);
            PackingUtils.log("Wrote " + bArrEncodeBandInt17.length + " bytes from " + str + "_" + this.type + " nestpair_N[" + this.nestpair_N.size() + "]");
            byte[] bArrEncodeBandInt18 = encodeBandInt(str + "_" + this.type + " nestname_RU", cpEntryListToArray(this.nestname_RU), Codec.UNSIGNED5);
            outputStream.write(bArrEncodeBandInt18);
            PackingUtils.log("Wrote " + bArrEncodeBandInt18.length + " bytes from " + str + "_" + this.type + " nestname_RU[" + this.nestname_RU.size() + "]");
        }
    }

    private int[] tagListToArray(List list) {
        int size = list.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = ((String) list.get(i)).charAt(0);
        }
        return iArr;
    }

    public void addParameterAnnotation(int i, int[] iArr, IntList intList, List list, List list2, List list3, List list4, List list5, List list6, List list7, List list8) {
        this.param_NB.add(i);
        for (int i2 : iArr) {
            this.anno_N.add(i2);
        }
        this.pair_N.addAll(intList);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            this.type_RS.add(this.cpBands.getCPSignature((String) it.next()));
        }
        Iterator it2 = list2.iterator();
        while (it2.hasNext()) {
            this.name_RU.add(this.cpBands.getCPUtf8((String) it2.next()));
        }
        Iterator it3 = list4.iterator();
        Iterator it4 = list3.iterator();
        while (it4.hasNext()) {
            String str = (String) it4.next();
            this.T.add(str);
            if (str.equals("B") || str.equals(TemperatureUnitEnum.C) || str.equals("I") || str.equals("S") || str.equals("Z")) {
                this.caseI_KI.add(this.cpBands.getConstant((Integer) it3.next()));
            } else if (str.equals("D")) {
                this.caseD_KD.add(this.cpBands.getConstant((Double) it3.next()));
            } else if (str.equals(TemperatureUnitEnum.F)) {
                this.caseF_KF.add(this.cpBands.getConstant((Float) it3.next()));
            } else if (str.equals("J")) {
                this.caseJ_KJ.add(this.cpBands.getConstant((Long) it3.next()));
            } else if (str.equals("c")) {
                this.casec_RS.add(this.cpBands.getCPSignature((String) it3.next()));
            } else if (str.equals("e")) {
                String str2 = (String) it3.next();
                String str3 = (String) it3.next();
                this.caseet_RS.add(this.cpBands.getCPSignature(str2));
                this.caseec_RU.add(this.cpBands.getCPUtf8(str3));
            } else if (str.equals("s")) {
                this.cases_RU.add(this.cpBands.getCPUtf8((String) it3.next()));
            }
        }
        Iterator it5 = list5.iterator();
        while (it5.hasNext()) {
            int iIntValue = ((Integer) it5.next()).intValue();
            this.casearray_N.add(iIntValue);
            this.numBackwardsCalls += iIntValue;
        }
        Iterator it6 = list6.iterator();
        while (it6.hasNext()) {
            this.nesttype_RS.add(this.cpBands.getCPSignature((String) it6.next()));
        }
        Iterator it7 = list7.iterator();
        while (it7.hasNext()) {
            this.nestname_RU.add(this.cpBands.getCPUtf8((String) it7.next()));
        }
        Iterator it8 = list8.iterator();
        while (it8.hasNext()) {
            Integer num = (Integer) it8.next();
            this.nestpair_N.add(num.intValue());
            this.numBackwardsCalls += num.intValue();
        }
    }

    public void addAnnotation(String str, List list, List list2, List list3, List list4, List list5, List list6, List list7) {
        this.type_RS.add(this.cpBands.getCPSignature(str));
        this.pair_N.add(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            this.name_RU.add(this.cpBands.getCPUtf8((String) it.next()));
        }
        Iterator it2 = list3.iterator();
        Iterator it3 = list2.iterator();
        while (it3.hasNext()) {
            String str2 = (String) it3.next();
            this.T.add(str2);
            if (str2.equals("B") || str2.equals(TemperatureUnitEnum.C) || str2.equals("I") || str2.equals("S") || str2.equals("Z")) {
                this.caseI_KI.add(this.cpBands.getConstant((Integer) it2.next()));
            } else if (str2.equals("D")) {
                this.caseD_KD.add(this.cpBands.getConstant((Double) it2.next()));
            } else if (str2.equals(TemperatureUnitEnum.F)) {
                this.caseF_KF.add(this.cpBands.getConstant((Float) it2.next()));
            } else if (str2.equals("J")) {
                this.caseJ_KJ.add(this.cpBands.getConstant((Long) it2.next()));
            } else if (str2.equals("c")) {
                this.casec_RS.add(this.cpBands.getCPSignature((String) it2.next()));
            } else if (str2.equals("e")) {
                String str3 = (String) it2.next();
                String str4 = (String) it2.next();
                this.caseet_RS.add(this.cpBands.getCPSignature(str3));
                this.caseec_RU.add(this.cpBands.getCPUtf8(str4));
            } else if (str2.equals("s")) {
                this.cases_RU.add(this.cpBands.getCPUtf8((String) it2.next()));
            }
        }
        Iterator it4 = list4.iterator();
        while (it4.hasNext()) {
            int iIntValue = ((Integer) it4.next()).intValue();
            this.casearray_N.add(iIntValue);
            this.numBackwardsCalls += iIntValue;
        }
        Iterator it5 = list5.iterator();
        while (it5.hasNext()) {
            this.nesttype_RS.add(this.cpBands.getCPSignature((String) it5.next()));
        }
        Iterator it6 = list6.iterator();
        while (it6.hasNext()) {
            this.nestname_RU.add(this.cpBands.getCPUtf8((String) it6.next()));
        }
        Iterator it7 = list7.iterator();
        while (it7.hasNext()) {
            Integer num = (Integer) it7.next();
            this.nestpair_N.add(num.intValue());
            this.numBackwardsCalls += num.intValue();
        }
    }

    public boolean hasContent() {
        return this.type_RS.size() > 0;
    }

    public int numBackwardsCalls() {
        return this.numBackwardsCalls;
    }

    public void incrementAnnoN() {
        this.anno_N.increment(r0.size() - 1);
    }

    public void newEntryInAnnoN() {
        this.anno_N.add(1);
    }

    public void removeLatest() {
        int iRemove = this.anno_N.remove(r0.size() - 1);
        for (int i = 0; i < iRemove; i++) {
            this.type_RS.remove(r3.size() - 1);
            int iRemove2 = this.pair_N.remove(r3.size() - 1);
            for (int i2 = 0; i2 < iRemove2; i2++) {
                removeOnePair();
            }
        }
    }

    private void removeOnePair() {
        String str = (String) this.T.remove(r0.size() - 1);
        if (str.equals("B") || str.equals(TemperatureUnitEnum.C) || str.equals("I") || str.equals("S") || str.equals("Z")) {
            this.caseI_KI.remove(r0.size() - 1);
            return;
        }
        if (str.equals("D")) {
            this.caseD_KD.remove(r0.size() - 1);
            return;
        }
        if (str.equals(TemperatureUnitEnum.F)) {
            this.caseF_KF.remove(r0.size() - 1);
            return;
        }
        if (str.equals("J")) {
            this.caseJ_KJ.remove(r0.size() - 1);
            return;
        }
        if (str.equals(TemperatureUnitEnum.C)) {
            this.casec_RS.remove(r0.size() - 1);
            return;
        }
        if (str.equals("e")) {
            this.caseet_RS.remove(r0.size() - 1);
            this.caseec_RU.remove(this.caseet_RS.size() - 1);
            return;
        }
        if (str.equals("s")) {
            this.cases_RU.remove(r0.size() - 1);
            return;
        }
        int i = 0;
        if (str.equals("[")) {
            int iRemove = this.casearray_N.remove(r0.size() - 1);
            this.numBackwardsCalls -= iRemove;
            while (i < iRemove) {
                removeOnePair();
                i++;
            }
            return;
        }
        if (str.equals("@")) {
            this.nesttype_RS.remove(r0.size() - 1);
            int iRemove2 = this.nestpair_N.remove(r0.size() - 1);
            this.numBackwardsCalls -= iRemove2;
            while (i < iRemove2) {
                removeOnePair();
                i++;
            }
        }
    }
}
