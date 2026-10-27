package com.byd.data.common.entiry.cloud.config;

import com.byd.data.common.entiry.cloud.event.CanBean;
import com.byd.data.common.entiry.cloud.event.CanCollectEventConfig;
import com.byd.data.common.entiry.cloud.event.EventCanId;
import com.byd.data.common.entiry.cloud.event.EventRule;
import com.byd.data.common.entiry.cloud.period.CanCollectPeriodConfig;
import com.byd.data.common.entiry.cloud.period.CycleCanId;
import com.byd.data.common.i.f;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
public class CanCollectConfig extends BaseConfigResult<com.byd.data.common.c.c.c> {
    private com.byd.data.common.c.c.c callBack;
    public CanCollectEventConfig canCollectEventConfig;
    public CanCollectPeriodConfig canCollectPeriodConfig;
    private TreeMap<Short, EventRule[]> canMapRules;
    public boolean enableSwitch;

    @Deprecated
    public boolean eventLocalStorageSwitch;
    public List<? extends CanBean> list;
    public boolean mccMncSwitch;
    private TreeMap<Integer, Byte[]> uuidIndexMap;
    private final String TAG = "CanCollectConfig";
    public final byte MIN_SIZE = 6;
    private short periodTotalSize = 8;
    private final byte TLV = 5;

    private void A() {
        byte b2 = this.flagType;
        if (b2 != 3) {
            this.list = (List) (b2 == 1 ? this.canCollectPeriodConfig.cycleCanConfigInfoList : this.canCollectEventConfig.eventCanIdInfoList).stream().distinct().sorted().collect(Collectors.toList());
        } else {
            TreeSet<CanBean> treeSet = new TreeSet<>((List) this.canCollectEventConfig.eventCanIdInfoList.stream().distinct().collect(Collectors.toList()));
            h((List) this.canCollectPeriodConfig.cycleCanConfigInfoList.stream().distinct().collect(Collectors.toList()), treeSet);
            this.list = (List) treeSet.stream().sorted().collect(Collectors.toList());
        }
        if (!d.a.a.f.b.c.a.a(this.canCollectEventConfig.eventTriggerRulesList)) {
            w(this.canCollectEventConfig.eventTriggerRulesList);
            this.canCollectEventConfig.eventTriggerRulesList.sort(null);
        }
        g();
        List<EventCanId> list = this.canCollectEventConfig.eventCanIdInfoList;
        List<CycleCanId> list2 = this.canCollectPeriodConfig.cycleCanConfigInfoList;
        StringBuilder sb = new StringBuilder();
        sb.append("initial sortList data is end, list size = ");
        List<? extends CanBean> list3 = this.list;
        sb.append(list3 == null ? 0 : list3.size());
        f.g("CanCollectConfig", sb.toString());
    }

    private void g() {
        if (d.a.a.f.b.c.a.a(this.list)) {
            return;
        }
        this.list.forEach(new Consumer() { // from class: com.byd.data.common.entiry.cloud.config.b
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CanCollectConfig.t((CanBean) obj);
            }
        });
    }

    private void h(List<CycleCanId> list, TreeSet<CanBean> treeSet) {
        if (treeSet == null) {
            return;
        }
        for (final CycleCanId cycleCanId : list) {
            if (!treeSet.add(cycleCanId)) {
                if (b()) {
                    f.g("CanCollectConfig", "period and event all contain canId =0x" + Integer.toHexString(cycleCanId.canId));
                }
                treeSet.stream().filter(new Predicate() { // from class: com.byd.data.common.entiry.cloud.config.a
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return CanCollectConfig.u(cycleCanId, (CanBean) obj);
                    }
                }).forEach(new Consumer() { // from class: com.byd.data.common.entiry.cloud.config.c
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((CanBean) obj).e((byte) 3);
                    }
                });
            }
        }
    }

    private void i(short s, byte b2, byte[] bArr) {
        com.byd.data.common.server.e.a aVarF;
        EventRule[] eventRuleArr;
        Byte[] bArr2;
        if (this.callBack == null || this.uuidIndexMap == null || this.canMapRules == null || d.a.a.f.b.c.a.a(this.list) || s() || (aVarF = com.byd.data.common.g.a.i().f()) == null || this.uuidIndexMap.isEmpty() || this.canMapRules.isEmpty() || (eventRuleArr = this.canMapRules.get(Short.valueOf(s))) == null || eventRuleArr.length == 0) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < eventRuleArr.length && !s(); i2++) {
            EventRule eventRule = eventRuleArr[i2];
            if (eventRule != null && eventRule.c(s, b2)) {
                int i3 = eventRule.eventUuid;
                if (eventRule.e(bArr, s) && !arrayList.contains(Integer.valueOf(i3)) && (bArr2 = this.uuidIndexMap.get(Integer.valueOf(i3))) != null && bArr2.length >= 2) {
                    arrayList.add(Integer.valueOf(i3));
                    this.callBack.p(aVarF, i3, bArr2, jCurrentTimeMillis);
                    z(eventRuleArr, bArr, i2 + 1);
                }
            }
        }
        if (d.a.a.f.b.c.a.a(arrayList)) {
            return;
        }
        arrayList.clear();
    }

    private CanBean j(short s, byte b2) {
        byte bK = k(s, b2);
        if (bK < 0 || bK >= this.list.size()) {
            return null;
        }
        return this.list.get(bK);
    }

    private byte k(short s, byte b2) {
        if (d.a.a.f.b.c.a.a(this.list)) {
            return (byte) -1;
        }
        int i2 = 0;
        int size = this.list.size() - 1;
        while (i2 <= size) {
            int i3 = (i2 + size) >>> 1;
            CanBean canBean = this.list.get(i3);
            short s2 = canBean.canId;
            if (s2 >= s) {
                if (s2 <= s) {
                    if (!canBean.subTag) {
                        if (r()) {
                            f.g("CanCollectConfig", "findCanIndex not subId index =" + i3);
                        }
                        return (byte) i3;
                    }
                    byte b3 = canBean.subVal;
                    if (b3 >= b2) {
                        if (b3 <= b2) {
                            if (r()) {
                                f.g("CanCollectConfig", "findCanIndex has subId index =" + i3);
                            }
                            return (byte) i3;
                        }
                    }
                }
                size = i3 - 1;
            }
            i2 = i3 + 1;
        }
        return (byte) -1;
    }

    private boolean s() {
        com.byd.data.common.c.c.c cVar = this.callBack;
        return cVar == null || cVar.j() < 6;
    }

    static /* synthetic */ void t(CanBean canBean) {
        if (canBean.d()) {
            canBean.queue = new LinkedList<>();
            canBean.timeQueue = new LinkedList<>();
        }
    }

    static /* synthetic */ boolean u(CycleCanId cycleCanId, CanBean canBean) {
        return (canBean instanceof EventCanId) && canBean.equals(cycleCanId);
    }

    private void w(List<EventRule> list) {
        TreeMap<Short, ArrayList<EventRule>> treeMap = new TreeMap<>();
        TreeMap<Integer, ArrayList<Byte>> treeMap2 = new TreeMap<>();
        for (byte b2 = 0; b2 < list.size(); b2 = (byte) (b2 + 1)) {
            EventRule eventRule = list.get(b2);
            ArrayList<EventRule> arrayList = treeMap.get(Short.valueOf(eventRule.triggerId));
            ArrayList<Byte> arrayList2 = treeMap2.get(Integer.valueOf(eventRule.eventUuid));
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                treeMap.put(Short.valueOf(eventRule.triggerId), arrayList);
            }
            arrayList.add(eventRule);
            byte bK = k(eventRule.triggerId, eventRule.triggerSubId);
            if (bK >= 0) {
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList<>();
                    treeMap2.put(Integer.valueOf(eventRule.eventUuid), arrayList2);
                }
                if (!arrayList2.contains(Byte.valueOf(bK))) {
                    arrayList2.add(Byte.valueOf(bK));
                }
            }
        }
        y(treeMap, treeMap2);
        f.g("CanCollectConfig", "initial config parseRuleData is end");
    }

    private void x(EventCanId eventCanId, byte[] bArr) {
        LinkedList<byte[]> linkedList = eventCanId.queue;
        LinkedList<Integer> linkedList2 = eventCanId.timeQueue;
        int size = linkedList.size();
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (size == this.canCollectEventConfig.a()) {
            linkedList.removeFirst();
            linkedList2.removeFirst();
        } else if (size >= 2 && Math.abs(jCurrentTimeMillis - eventCanId.tailTimeDivide) < 1000) {
            linkedList.removeLast();
            linkedList2.removeLast();
        }
        eventCanId.tailTimeDivide = eventCanId.tempTimestamps;
        eventCanId.tempTimestamps = jCurrentTimeMillis;
        linkedList.add(bArr);
        linkedList2.add(Integer.valueOf((int) (jCurrentTimeMillis / 1000)));
    }

    private void y(TreeMap<Short, ArrayList<EventRule>> treeMap, TreeMap<Integer, ArrayList<Byte>> treeMap2) {
        this.canMapRules = new TreeMap<>();
        for (Map.Entry<Short, ArrayList<EventRule>> entry : treeMap.entrySet()) {
            ArrayList<EventRule> value = entry.getValue();
            this.canMapRules.put(entry.getKey(), (EventRule[]) value.toArray(new EventRule[value.size()]));
        }
        this.uuidIndexMap = new TreeMap<>();
        for (Map.Entry<Integer, ArrayList<Byte>> entry2 : treeMap2.entrySet()) {
            ArrayList<Byte> value2 = entry2.getValue();
            byte b2 = 0;
            for (int i2 = 0; i2 < value2.size(); i2++) {
                b2 = (byte) (b2 + this.list.get(value2.get(i2).byteValue()).size + 5);
            }
            value2.add(Byte.valueOf((byte) (b2 + 12)));
            this.uuidIndexMap.put(entry2.getKey(), (Byte[]) value2.toArray(new Byte[value2.size()]));
        }
    }

    private void z(EventRule[] eventRuleArr, byte[] bArr, int i2) {
        while (i2 < eventRuleArr.length) {
            EventRule eventRule = eventRuleArr[i2];
            if (eventRule != null) {
                eventRule.preValue = eventRule.b(bArr);
            }
            i2++;
        }
    }

    public void B(short s, byte b2, byte[] bArr) {
        CanBean canBeanJ = j(s, b2);
        if (canBeanJ == null) {
            return;
        }
        if (canBeanJ.d()) {
            try {
                EventCanId eventCanId = (EventCanId) canBeanJ;
                if (eventCanId.g()) {
                    this.periodTotalSize = (short) (this.periodTotalSize + canBeanJ.size + 5);
                }
                synchronized (eventCanId.Lock) {
                    x(eventCanId, bArr);
                }
            } catch (InterruptedException | NoSuchElementException e2) {
                e2.printStackTrace();
            }
        } else if (canBeanJ instanceof CycleCanId) {
            CycleCanId cycleCanId = (CycleCanId) canBeanJ;
            if (cycleCanId.data == null) {
                int i2 = canBeanJ.size;
                cycleCanId.data = new byte[i2];
                this.periodTotalSize = (short) (this.periodTotalSize + i2 + 5);
            }
            System.arraycopy(bArr, 0, cycleCanId.data, 0, Math.min(bArr.length, (int) canBeanJ.size));
        }
        i(s, b2, bArr);
    }

    @Override // com.byd.data.common.entiry.cloud.config.BaseConfigResult
    public void e() {
        CanCollectEventConfig canCollectEventConfig = this.canCollectEventConfig;
        if (canCollectEventConfig != null) {
            if (!d.a.a.f.b.c.a.a(canCollectEventConfig.eventTriggerRulesList)) {
                this.canCollectEventConfig.eventTriggerRulesList.clear();
                this.canCollectEventConfig.eventTriggerRulesList = null;
            }
            this.canCollectEventConfig = null;
        }
        if (!d.a.a.f.b.c.a.a(this.list)) {
            for (int i2 = 0; i2 < this.list.size(); i2++) {
                CanBean canBean = this.list.get(i2);
                LinkedList<Integer> linkedList = canBean.timeQueue;
                if (linkedList != null) {
                    linkedList.clear();
                    canBean.timeQueue = null;
                }
                LinkedList<byte[]> linkedList2 = canBean.queue;
                if (linkedList2 != null) {
                    linkedList2.clear();
                    canBean.queue = null;
                } else if (canBean instanceof CycleCanId) {
                    ((CycleCanId) canBean).data = null;
                }
            }
            this.list.clear();
            this.list = null;
        }
        TreeMap<Short, EventRule[]> treeMap = this.canMapRules;
        if (treeMap != null) {
            treeMap.clear();
            this.canMapRules = null;
        }
        TreeMap<Integer, Byte[]> treeMap2 = this.uuidIndexMap;
        if (treeMap2 != null) {
            treeMap2.clear();
            this.uuidIndexMap = null;
        }
        this.callBack = null;
    }

    public Byte[] m(int i2) {
        if (this.uuidIndexMap.isEmpty()) {
            return null;
        }
        return this.uuidIndexMap.get(Integer.valueOf(i2));
    }

    public List<? extends CanBean> n() {
        return this.list;
    }

    public short o() {
        return this.periodTotalSize;
    }

    @Override // com.byd.data.common.entiry.cloud.config.BaseConfigResult
    /* JADX INFO: renamed from: p */
    public void a(com.byd.data.common.c.c.c cVar) {
        this.callBack = cVar;
        if (!q()) {
            f.g("CanCollectConfig", "initial cloud data is end not satisfy enableSwitch =" + this.enableSwitch);
            return;
        }
        CanCollectEventConfig canCollectEventConfig = this.canCollectEventConfig;
        if (canCollectEventConfig != null) {
            this.flagType = (byte) (((d.a.a.f.b.c.a.a(canCollectEventConfig.eventCanIdInfoList) || d.a.a.f.b.c.a.a(this.canCollectEventConfig.eventTriggerRulesList)) ? (byte) 0 : (byte) 2) | this.flagType);
            f.g("CanCollectConfig", "initial eventCanIdInfoList =" + d.a.a.f.b.c.a.a(this.canCollectEventConfig.eventCanIdInfoList) + ",eventTriggerRulesList =" + d.a.a.f.b.c.a.a(this.canCollectEventConfig.eventTriggerRulesList) + ",flagType =" + ((int) this.flagType));
        }
        CanCollectPeriodConfig canCollectPeriodConfig = this.canCollectPeriodConfig;
        if (canCollectPeriodConfig != null) {
            this.flagType = (byte) ((!d.a.a.f.b.c.a.a(canCollectPeriodConfig.cycleCanConfigInfoList) ? 1 : 0) | this.flagType);
            f.g("CanCollectConfig", "initial cycleCanConfigInfoList =" + d.a.a.f.b.c.a.a(this.canCollectPeriodConfig.cycleCanConfigInfoList) + ",flagType =" + ((int) this.flagType));
        }
        if (this.flagType == 0) {
            f.g("CanCollectConfig", "initial cloud data is end collect list is empty");
            return;
        }
        f.g("CanCollectConfig", "initData: flagType = " + ((int) this.flagType));
        CanCollectEventConfig canCollectEventConfig2 = this.canCollectEventConfig;
        if (canCollectEventConfig2 != null && canCollectEventConfig2.a() < 6) {
            this.canCollectEventConfig.b((byte) 6);
        }
        try {
            A();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        f.g("CanCollectConfig", "initial cloud data is finish");
    }

    public boolean q() {
        f.g("CanCollectConfig", "isCollect enableSwitch =" + this.enableSwitch + ",mccMncSwitch =" + this.mccMncSwitch);
        return this.enableSwitch && this.mccMncSwitch;
    }

    public boolean r() {
        return com.byd.data.common.server.b.e().q();
    }
}
