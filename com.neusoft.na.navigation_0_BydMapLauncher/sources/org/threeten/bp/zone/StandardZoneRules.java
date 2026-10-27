package org.threeten.bp.zone;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import kotlin.jvm.internal.LongCompanionObject;
import org.threeten.bp.Duration;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.zone.ZoneRules;

/* JADX INFO: loaded from: classes4.dex */
final class StandardZoneRules extends ZoneRules implements Serializable {
    private static final int LAST_CACHED_YEAR = 2100;
    private static final long serialVersionUID = 3044319355680032515L;
    private final ZoneOffsetTransitionRule[] lastRules;
    private final ConcurrentMap<Integer, ZoneOffsetTransition[]> lastRulesCache = new ConcurrentHashMap();
    private final long[] savingsInstantTransitions;
    private final LocalDateTime[] savingsLocalTransitions;
    private final ZoneOffset[] standardOffsets;
    private final long[] standardTransitions;
    private final ZoneOffset[] wallOffsets;

    StandardZoneRules(ZoneOffset zoneOffset, ZoneOffset zoneOffset2, List<ZoneOffsetTransition> list, List<ZoneOffsetTransition> list2, List<ZoneOffsetTransitionRule> list3) {
        this.standardTransitions = new long[list.size()];
        ZoneOffset[] zoneOffsetArr = new ZoneOffset[list.size() + 1];
        this.standardOffsets = zoneOffsetArr;
        zoneOffsetArr[0] = zoneOffset;
        int i = 0;
        while (i < list.size()) {
            this.standardTransitions[i] = list.get(i).toEpochSecond();
            int i2 = i + 1;
            this.standardOffsets[i2] = list.get(i).getOffsetAfter();
            i = i2;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(zoneOffset2);
        for (ZoneOffsetTransition zoneOffsetTransition : list2) {
            if (zoneOffsetTransition.isGap()) {
                arrayList.add(zoneOffsetTransition.getDateTimeBefore());
                arrayList.add(zoneOffsetTransition.getDateTimeAfter());
            } else {
                arrayList.add(zoneOffsetTransition.getDateTimeAfter());
                arrayList.add(zoneOffsetTransition.getDateTimeBefore());
            }
            arrayList2.add(zoneOffsetTransition.getOffsetAfter());
        }
        this.savingsLocalTransitions = (LocalDateTime[]) arrayList.toArray(new LocalDateTime[arrayList.size()]);
        this.wallOffsets = (ZoneOffset[]) arrayList2.toArray(new ZoneOffset[arrayList2.size()]);
        this.savingsInstantTransitions = new long[list2.size()];
        for (int i3 = 0; i3 < list2.size(); i3++) {
            this.savingsInstantTransitions[i3] = list2.get(i3).getInstant().getEpochSecond();
        }
        if (list3.size() > 15) {
            throw new IllegalArgumentException("Too many transition rules");
        }
        this.lastRules = (ZoneOffsetTransitionRule[]) list3.toArray(new ZoneOffsetTransitionRule[list3.size()]);
    }

    private StandardZoneRules(long[] jArr, ZoneOffset[] zoneOffsetArr, long[] jArr2, ZoneOffset[] zoneOffsetArr2, ZoneOffsetTransitionRule[] zoneOffsetTransitionRuleArr) {
        this.standardTransitions = jArr;
        this.standardOffsets = zoneOffsetArr;
        this.savingsInstantTransitions = jArr2;
        this.wallOffsets = zoneOffsetArr2;
        this.lastRules = zoneOffsetTransitionRuleArr;
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < jArr2.length) {
            int i2 = i + 1;
            ZoneOffsetTransition zoneOffsetTransition = new ZoneOffsetTransition(jArr2[i], zoneOffsetArr2[i], zoneOffsetArr2[i2]);
            if (zoneOffsetTransition.isGap()) {
                arrayList.add(zoneOffsetTransition.getDateTimeBefore());
                arrayList.add(zoneOffsetTransition.getDateTimeAfter());
            } else {
                arrayList.add(zoneOffsetTransition.getDateTimeAfter());
                arrayList.add(zoneOffsetTransition.getDateTimeBefore());
            }
            i = i2;
        }
        this.savingsLocalTransitions = (LocalDateTime[]) arrayList.toArray(new LocalDateTime[arrayList.size()]);
    }

    private Object writeReplace() {
        return new Ser((byte) 1, this);
    }

    void writeExternal(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(this.standardTransitions.length);
        for (long j : this.standardTransitions) {
            Ser.writeEpochSec(j, dataOutput);
        }
        for (ZoneOffset zoneOffset : this.standardOffsets) {
            Ser.writeOffset(zoneOffset, dataOutput);
        }
        dataOutput.writeInt(this.savingsInstantTransitions.length);
        for (long j2 : this.savingsInstantTransitions) {
            Ser.writeEpochSec(j2, dataOutput);
        }
        for (ZoneOffset zoneOffset2 : this.wallOffsets) {
            Ser.writeOffset(zoneOffset2, dataOutput);
        }
        dataOutput.writeByte(this.lastRules.length);
        for (ZoneOffsetTransitionRule zoneOffsetTransitionRule : this.lastRules) {
            zoneOffsetTransitionRule.writeExternal(dataOutput);
        }
    }

    static StandardZoneRules readExternal(DataInput dataInput) throws IOException, ClassNotFoundException {
        int i = dataInput.readInt();
        long[] jArr = new long[i];
        for (int i2 = 0; i2 < i; i2++) {
            jArr[i2] = Ser.readEpochSec(dataInput);
        }
        int i3 = i + 1;
        ZoneOffset[] zoneOffsetArr = new ZoneOffset[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            zoneOffsetArr[i4] = Ser.readOffset(dataInput);
        }
        int i5 = dataInput.readInt();
        long[] jArr2 = new long[i5];
        for (int i6 = 0; i6 < i5; i6++) {
            jArr2[i6] = Ser.readEpochSec(dataInput);
        }
        int i7 = i5 + 1;
        ZoneOffset[] zoneOffsetArr2 = new ZoneOffset[i7];
        for (int i8 = 0; i8 < i7; i8++) {
            zoneOffsetArr2[i8] = Ser.readOffset(dataInput);
        }
        int i9 = dataInput.readByte();
        ZoneOffsetTransitionRule[] zoneOffsetTransitionRuleArr = new ZoneOffsetTransitionRule[i9];
        for (int i10 = 0; i10 < i9; i10++) {
            zoneOffsetTransitionRuleArr[i10] = ZoneOffsetTransitionRule.readExternal(dataInput);
        }
        return new StandardZoneRules(jArr, zoneOffsetArr, jArr2, zoneOffsetArr2, zoneOffsetTransitionRuleArr);
    }

    @Override // org.threeten.bp.zone.ZoneRules
    public boolean isFixedOffset() {
        return this.savingsInstantTransitions.length == 0;
    }

    @Override // org.threeten.bp.zone.ZoneRules
    public ZoneOffset getOffset(Instant instant) {
        long epochSecond = instant.getEpochSecond();
        if (this.lastRules.length > 0) {
            if (epochSecond > this.savingsInstantTransitions[r7.length - 1]) {
                ZoneOffsetTransition[] zoneOffsetTransitionArrFindTransitionArray = findTransitionArray(findYear(epochSecond, this.wallOffsets[r7.length - 1]));
                ZoneOffsetTransition zoneOffsetTransition = null;
                for (int i = 0; i < zoneOffsetTransitionArrFindTransitionArray.length; i++) {
                    zoneOffsetTransition = zoneOffsetTransitionArrFindTransitionArray[i];
                    if (epochSecond < zoneOffsetTransition.toEpochSecond()) {
                        return zoneOffsetTransition.getOffsetBefore();
                    }
                }
                return zoneOffsetTransition.getOffsetAfter();
            }
        }
        int iBinarySearch = Arrays.binarySearch(this.savingsInstantTransitions, epochSecond);
        if (iBinarySearch < 0) {
            iBinarySearch = (-iBinarySearch) - 2;
        }
        return this.wallOffsets[iBinarySearch + 1];
    }

    @Override // org.threeten.bp.zone.ZoneRules
    public ZoneOffset getOffset(LocalDateTime localDateTime) {
        Object offsetInfo = getOffsetInfo(localDateTime);
        if (offsetInfo instanceof ZoneOffsetTransition) {
            return ((ZoneOffsetTransition) offsetInfo).getOffsetBefore();
        }
        return (ZoneOffset) offsetInfo;
    }

    @Override // org.threeten.bp.zone.ZoneRules
    public List<ZoneOffset> getValidOffsets(LocalDateTime localDateTime) {
        Object offsetInfo = getOffsetInfo(localDateTime);
        if (offsetInfo instanceof ZoneOffsetTransition) {
            return ((ZoneOffsetTransition) offsetInfo).getValidOffsets();
        }
        return Collections.singletonList((ZoneOffset) offsetInfo);
    }

    @Override // org.threeten.bp.zone.ZoneRules
    public ZoneOffsetTransition getTransition(LocalDateTime localDateTime) {
        Object offsetInfo = getOffsetInfo(localDateTime);
        if (offsetInfo instanceof ZoneOffsetTransition) {
            return (ZoneOffsetTransition) offsetInfo;
        }
        return null;
    }

    private Object getOffsetInfo(LocalDateTime localDateTime) {
        int i = 0;
        if (this.lastRules.length > 0) {
            if (localDateTime.isAfter(this.savingsLocalTransitions[r0.length - 1])) {
                ZoneOffsetTransition[] zoneOffsetTransitionArrFindTransitionArray = findTransitionArray(localDateTime.getYear());
                int length = zoneOffsetTransitionArrFindTransitionArray.length;
                Object obj = null;
                while (i < length) {
                    ZoneOffsetTransition zoneOffsetTransition = zoneOffsetTransitionArrFindTransitionArray[i];
                    Object objFindOffsetInfo = findOffsetInfo(localDateTime, zoneOffsetTransition);
                    if ((objFindOffsetInfo instanceof ZoneOffsetTransition) || objFindOffsetInfo.equals(zoneOffsetTransition.getOffsetBefore())) {
                        return objFindOffsetInfo;
                    }
                    i++;
                    obj = objFindOffsetInfo;
                }
                return obj;
            }
        }
        int iBinarySearch = Arrays.binarySearch(this.savingsLocalTransitions, localDateTime);
        if (iBinarySearch == -1) {
            return this.wallOffsets[0];
        }
        if (iBinarySearch < 0) {
            iBinarySearch = (-iBinarySearch) - 2;
        } else {
            Object[] objArr = this.savingsLocalTransitions;
            if (iBinarySearch < objArr.length - 1) {
                int i2 = iBinarySearch + 1;
                if (objArr[iBinarySearch].equals(objArr[i2])) {
                    iBinarySearch = i2;
                }
            }
        }
        if ((iBinarySearch & 1) == 0) {
            LocalDateTime[] localDateTimeArr = this.savingsLocalTransitions;
            LocalDateTime localDateTime2 = localDateTimeArr[iBinarySearch];
            LocalDateTime localDateTime3 = localDateTimeArr[iBinarySearch + 1];
            ZoneOffset[] zoneOffsetArr = this.wallOffsets;
            int i3 = iBinarySearch / 2;
            ZoneOffset zoneOffset = zoneOffsetArr[i3];
            ZoneOffset zoneOffset2 = zoneOffsetArr[i3 + 1];
            if (zoneOffset2.getTotalSeconds() > zoneOffset.getTotalSeconds()) {
                return new ZoneOffsetTransition(localDateTime2, zoneOffset, zoneOffset2);
            }
            return new ZoneOffsetTransition(localDateTime3, zoneOffset, zoneOffset2);
        }
        return this.wallOffsets[(iBinarySearch / 2) + 1];
    }

    private Object findOffsetInfo(LocalDateTime localDateTime, ZoneOffsetTransition zoneOffsetTransition) {
        LocalDateTime dateTimeBefore = zoneOffsetTransition.getDateTimeBefore();
        if (zoneOffsetTransition.isGap()) {
            if (localDateTime.isBefore(dateTimeBefore)) {
                return zoneOffsetTransition.getOffsetBefore();
            }
            return localDateTime.isBefore(zoneOffsetTransition.getDateTimeAfter()) ? zoneOffsetTransition : zoneOffsetTransition.getOffsetAfter();
        }
        if (localDateTime.isBefore(dateTimeBefore)) {
            return localDateTime.isBefore(zoneOffsetTransition.getDateTimeAfter()) ? zoneOffsetTransition.getOffsetBefore() : zoneOffsetTransition;
        }
        return zoneOffsetTransition.getOffsetAfter();
    }

    @Override // org.threeten.bp.zone.ZoneRules
    public boolean isValidOffset(LocalDateTime localDateTime, ZoneOffset zoneOffset) {
        return getValidOffsets(localDateTime).contains(zoneOffset);
    }

    private ZoneOffsetTransition[] findTransitionArray(int i) {
        Integer numValueOf = Integer.valueOf(i);
        ZoneOffsetTransition[] zoneOffsetTransitionArr = this.lastRulesCache.get(numValueOf);
        if (zoneOffsetTransitionArr != null) {
            return zoneOffsetTransitionArr;
        }
        ZoneOffsetTransitionRule[] zoneOffsetTransitionRuleArr = this.lastRules;
        ZoneOffsetTransition[] zoneOffsetTransitionArr2 = new ZoneOffsetTransition[zoneOffsetTransitionRuleArr.length];
        for (int i2 = 0; i2 < zoneOffsetTransitionRuleArr.length; i2++) {
            zoneOffsetTransitionArr2[i2] = zoneOffsetTransitionRuleArr[i2].createTransition(i);
        }
        if (i < 2100) {
            this.lastRulesCache.putIfAbsent(numValueOf, zoneOffsetTransitionArr2);
        }
        return zoneOffsetTransitionArr2;
    }

    @Override // org.threeten.bp.zone.ZoneRules
    public ZoneOffset getStandardOffset(Instant instant) {
        int iBinarySearch = Arrays.binarySearch(this.standardTransitions, instant.getEpochSecond());
        if (iBinarySearch < 0) {
            iBinarySearch = (-iBinarySearch) - 2;
        }
        return this.standardOffsets[iBinarySearch + 1];
    }

    @Override // org.threeten.bp.zone.ZoneRules
    public Duration getDaylightSavings(Instant instant) {
        return Duration.ofSeconds(getOffset(instant).getTotalSeconds() - getStandardOffset(instant).getTotalSeconds());
    }

    @Override // org.threeten.bp.zone.ZoneRules
    public boolean isDaylightSavings(Instant instant) {
        return !getStandardOffset(instant).equals(getOffset(instant));
    }

    @Override // org.threeten.bp.zone.ZoneRules
    public ZoneOffsetTransition nextTransition(Instant instant) {
        if (this.savingsInstantTransitions.length == 0) {
            return null;
        }
        long epochSecond = instant.getEpochSecond();
        long[] jArr = this.savingsInstantTransitions;
        if (epochSecond >= jArr[jArr.length - 1]) {
            if (this.lastRules.length == 0) {
                return null;
            }
            int iFindYear = findYear(epochSecond, this.wallOffsets[r11.length - 1]);
            for (ZoneOffsetTransition zoneOffsetTransition : findTransitionArray(iFindYear)) {
                if (epochSecond < zoneOffsetTransition.toEpochSecond()) {
                    return zoneOffsetTransition;
                }
            }
            if (iFindYear < 999999999) {
                return findTransitionArray(iFindYear + 1)[0];
            }
            return null;
        }
        int iBinarySearch = Arrays.binarySearch(jArr, epochSecond);
        int i = iBinarySearch < 0 ? (-iBinarySearch) - 1 : iBinarySearch + 1;
        long j = this.savingsInstantTransitions[i];
        ZoneOffset[] zoneOffsetArr = this.wallOffsets;
        return new ZoneOffsetTransition(j, zoneOffsetArr[i], zoneOffsetArr[i + 1]);
    }

    @Override // org.threeten.bp.zone.ZoneRules
    public ZoneOffsetTransition previousTransition(Instant instant) {
        if (this.savingsInstantTransitions.length == 0) {
            return null;
        }
        long epochSecond = instant.getEpochSecond();
        if (instant.getNano() > 0 && epochSecond < LongCompanionObject.MAX_VALUE) {
            epochSecond++;
        }
        long j = this.savingsInstantTransitions[r11.length - 1];
        if (this.lastRules.length > 0 && epochSecond > j) {
            ZoneOffset zoneOffset = this.wallOffsets[r11.length - 1];
            int iFindYear = findYear(epochSecond, zoneOffset);
            ZoneOffsetTransition[] zoneOffsetTransitionArrFindTransitionArray = findTransitionArray(iFindYear);
            for (int length = zoneOffsetTransitionArrFindTransitionArray.length - 1; length >= 0; length--) {
                if (epochSecond > zoneOffsetTransitionArrFindTransitionArray[length].toEpochSecond()) {
                    return zoneOffsetTransitionArrFindTransitionArray[length];
                }
            }
            int i = iFindYear - 1;
            if (i > findYear(j, zoneOffset)) {
                return findTransitionArray(i)[r11.length - 1];
            }
        }
        int iBinarySearch = Arrays.binarySearch(this.savingsInstantTransitions, epochSecond);
        if (iBinarySearch < 0) {
            iBinarySearch = (-iBinarySearch) - 1;
        }
        if (iBinarySearch <= 0) {
            return null;
        }
        int i2 = iBinarySearch - 1;
        long j2 = this.savingsInstantTransitions[i2];
        ZoneOffset[] zoneOffsetArr = this.wallOffsets;
        return new ZoneOffsetTransition(j2, zoneOffsetArr[i2], zoneOffsetArr[iBinarySearch]);
    }

    private int findYear(long j, ZoneOffset zoneOffset) {
        return LocalDate.ofEpochDay(Jdk8Methods.floorDiv(j + ((long) zoneOffset.getTotalSeconds()), 86400L)).getYear();
    }

    @Override // org.threeten.bp.zone.ZoneRules
    public List<ZoneOffsetTransition> getTransitions() {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < this.savingsInstantTransitions.length) {
            long j = this.savingsInstantTransitions[i];
            ZoneOffset[] zoneOffsetArr = this.wallOffsets;
            ZoneOffset zoneOffset = zoneOffsetArr[i];
            i++;
            arrayList.add(new ZoneOffsetTransition(j, zoneOffset, zoneOffsetArr[i]));
        }
        return Collections.unmodifiableList(arrayList);
    }

    @Override // org.threeten.bp.zone.ZoneRules
    public List<ZoneOffsetTransitionRule> getTransitionRules() {
        return Collections.unmodifiableList(Arrays.asList(this.lastRules));
    }

    @Override // org.threeten.bp.zone.ZoneRules
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof StandardZoneRules) {
            StandardZoneRules standardZoneRules = (StandardZoneRules) obj;
            return Arrays.equals(this.standardTransitions, standardZoneRules.standardTransitions) && Arrays.equals(this.standardOffsets, standardZoneRules.standardOffsets) && Arrays.equals(this.savingsInstantTransitions, standardZoneRules.savingsInstantTransitions) && Arrays.equals(this.wallOffsets, standardZoneRules.wallOffsets) && Arrays.equals(this.lastRules, standardZoneRules.lastRules);
        }
        if (obj instanceof ZoneRules.Fixed) {
            return isFixedOffset() && getOffset(Instant.EPOCH).equals(((ZoneRules.Fixed) obj).getOffset(Instant.EPOCH));
        }
        return false;
    }

    @Override // org.threeten.bp.zone.ZoneRules
    public int hashCode() {
        return (((Arrays.hashCode(this.standardTransitions) ^ Arrays.hashCode(this.standardOffsets)) ^ Arrays.hashCode(this.savingsInstantTransitions)) ^ Arrays.hashCode(this.wallOffsets)) ^ Arrays.hashCode(this.lastRules);
    }

    public String toString() {
        return new StringBuilder("StandardZoneRules[currentStandardOffset=").append(this.standardOffsets[r1.length - 1]).append("]").toString();
    }
}
