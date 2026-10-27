package com.byd.music.db;

import com.byd.music.beans.RadioDBBean;
import com.byd.music.beans.RadioDBBean_;
import com.byd.music.utils.LogUtil;
import io.objectbox.Box;
import io.objectbox.android.ObjectBoxLiveData;
import io.objectbox.query.QueryFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class RadioDB {
    private static final String TAG = "RadioDB";

    private static Box<RadioDBBean> getBox() {
        return BoxManager.get().getBoxStore().boxFor(RadioDBBean.class);
    }

    public static RadioDBBean query(long j) {
        return getBox().query().equal(RadioDBBean_.radioFrequency, j).build().findUnique();
    }

    public static List<RadioDBBean> queryAll() {
        return getBox().query().build().find();
    }

    public static void update(RadioDBBean radioDBBean) {
        if (!queryAll().contains(radioDBBean)) {
            throw new RuntimeException("update error! it is not database right now contain bean.");
        }
        getBox().put(radioDBBean);
    }

    public static synchronized void insert(RadioDBBean radioDBBean) {
        LogUtil.d(TAG, "insert FMRadioDBBean, bean = " + radioDBBean);
        List<RadioDBBean> arrayList = new ArrayList();
        int type = radioDBBean.getType();
        if (type != 1 && type != 2 && type != 3) {
            if (type == 4) {
                arrayList = queryDABBySID(radioDBBean.getDAB_SID());
            } else {
                switch (type) {
                    case 101:
                    case 102:
                        arrayList = queryMemoryRadio(radioDBBean.getType());
                        break;
                    case 103:
                        arrayList = queryMemoryDAB();
                        break;
                }
            }
        } else {
            arrayList = queryRadioByFrequency(radioDBBean.getRadioFrequency());
        }
        if (!arrayList.isEmpty()) {
            for (RadioDBBean radioDBBean2 : arrayList) {
                LogUtil.d(TAG, "has oldRadio , id = " + radioDBBean2.getId() + ", type = " + radioDBBean2.getType() + ", frequency = " + radioDBBean2.getUniqueFrequency());
                radioDBBean.setId(radioDBBean2.getId());
                long jPut = getBox().put(radioDBBean);
                StringBuilder sb = new StringBuilder();
                sb.append("insert finished, id = ");
                sb.append(jPut);
                LogUtil.d(TAG, sb.toString());
            }
        } else {
            radioDBBean.setId(0L);
            LogUtil.d(TAG, "insert finished, id = " + getBox().put(radioDBBean));
        }
    }

    public static List<RadioDBBean> queryRadioByFrequency(int i) {
        return getBox().query().equal(RadioDBBean_.radioFrequency, i).and().between(RadioDBBean_.type, 1L, 3L).build().find();
    }

    public static List<RadioDBBean> queryDABBySID(long j) {
        return getBox().query().equal(RadioDBBean_.DAB_SID, j).and().equal(RadioDBBean_.type, 4L).build().find();
    }

    public static List<RadioDBBean> queryMemoryDAB() {
        return getBox().query().equal(RadioDBBean_.type, 103L).build().find();
    }

    public static List<RadioDBBean> queryMemoryRadio(int i) {
        return getBox().query().equal(RadioDBBean_.type, i).build().find();
    }

    public static void insertAll(List<RadioDBBean> list) {
        LogUtil.d(TAG, "insert all! list = " + list.size());
        getBox().put(list);
    }

    public static void delete(long j) {
        LogUtil.d(TAG, "delete finished, success = " + getBox().remove(query(j)));
    }

    public static boolean delete(RadioDBBean radioDBBean) {
        return getBox().remove(radioDBBean);
    }

    public static void deleteAll() {
        getBox().removeAll();
    }

    public static ObjectBoxLiveData<RadioDBBean> queryRadioByType(int i) {
        return new ObjectBoxLiveData<>(getBox().query().equal(RadioDBBean_.type, i).order(RadioDBBean_.radioFrequency).build());
    }

    public static ObjectBoxLiveData<RadioDBBean> queryAllRadio() {
        return new ObjectBoxLiveData<>(getBox().query().filter(new QueryFilter() { // from class: com.byd.music.db.-$$Lambda$RadioDB$h9LIryxV34P5iD1O7cbE7iS6bX4
            @Override // io.objectbox.query.QueryFilter
            public final boolean keep(Object obj) {
                return RadioDB.lambda$queryAllRadio$0((RadioDBBean) obj);
            }
        }).orderDesc(RadioDBBean_.id).build());
    }

    static /* synthetic */ boolean lambda$queryAllRadio$0(RadioDBBean radioDBBean) {
        return radioDBBean.getType() < 101;
    }

    public static List<RadioDBBean> queryAllRadioNoLive() {
        return getBox().query().filter(new QueryFilter() { // from class: com.byd.music.db.-$$Lambda$RadioDB$yx3Wd3jgT5EDvavL1MU16QWsoNg
            @Override // io.objectbox.query.QueryFilter
            public final boolean keep(Object obj) {
                return RadioDB.lambda$queryAllRadioNoLive$1((RadioDBBean) obj);
            }
        }).orderDesc(RadioDBBean_.id).build().find();
    }

    static /* synthetic */ boolean lambda$queryAllRadioNoLive$1(RadioDBBean radioDBBean) {
        return radioDBBean.getType() < 101;
    }

    public static RadioDBBean queryUniqueRadioByType(int i) {
        return getBox().query().equal(RadioDBBean_.type, i).build().findFirst();
    }

    private static void insertScanMemoryList(List<RadioDBBean> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<RadioDBBean> it = list.iterator();
        while (it.hasNext()) {
            RadioDBBean radioDBBeanDeepCopy = RadioDBBean.deepCopy(it.next());
            radioDBBeanDeepCopy.setType(radioDBBeanDeepCopy.getType() + 990);
            arrayList.add(radioDBBeanDeepCopy);
        }
        insertAll(arrayList);
    }

    private static List<RadioDBBean> reduceTypeOffset(List<RadioDBBean> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<RadioDBBean> it = list.iterator();
        while (it.hasNext()) {
            RadioDBBean radioDBBeanDeepCopy = RadioDBBean.deepCopy(it.next());
            radioDBBeanDeepCopy.setType(radioDBBeanDeepCopy.getType() - 990);
            arrayList.add(radioDBBeanDeepCopy);
        }
        return arrayList;
    }

    public static List<RadioDBBean> queryScanMemoryFM() {
        return reduceTypeOffset(getBox().query().filter(new QueryFilter() { // from class: com.byd.music.db.-$$Lambda$RadioDB$tj4GueiTOvV58CeV7W6eW5XNapU
            @Override // io.objectbox.query.QueryFilter
            public final boolean keep(Object obj) {
                return RadioDB.lambda$queryScanMemoryFM$2((RadioDBBean) obj);
            }
        }).build().find());
    }

    static /* synthetic */ boolean lambda$queryScanMemoryFM$2(RadioDBBean radioDBBean) {
        return radioDBBean.getType() == 992 || radioDBBean.getType() == 993;
    }

    public static void updateScanMemoryFM(List<RadioDBBean> list) {
        Iterator<RadioDBBean> it = getBox().query().filter(new QueryFilter() { // from class: com.byd.music.db.-$$Lambda$RadioDB$RZGU60j1FrLR0PzY-AYFrGipABE
            @Override // io.objectbox.query.QueryFilter
            public final boolean keep(Object obj) {
                return RadioDB.lambda$updateScanMemoryFM$3((RadioDBBean) obj);
            }
        }).build().find().iterator();
        while (it.hasNext()) {
            delete(it.next());
        }
        insertScanMemoryList(list);
    }

    static /* synthetic */ boolean lambda$updateScanMemoryFM$3(RadioDBBean radioDBBean) {
        return radioDBBean.getType() == 992 || radioDBBean.getType() == 993;
    }

    public static List<RadioDBBean> queryScanMemoryAM() {
        return reduceTypeOffset(getBox().query().equal(RadioDBBean_.type, 991L).build().find());
    }

    public static void updateScanMemoryAM(List<RadioDBBean> list) {
        Iterator<RadioDBBean> it = getBox().query().equal(RadioDBBean_.type, 991L).build().find().iterator();
        while (it.hasNext()) {
            delete(it.next());
        }
        insertScanMemoryList(list);
    }

    public static List<RadioDBBean> queryScanMemoryDAB() {
        return reduceTypeOffset(getBox().query().equal(RadioDBBean_.type, 994L).build().find());
    }

    public static void updateScanMemoryDAB(List<RadioDBBean> list) {
        Iterator<RadioDBBean> it = getBox().query().equal(RadioDBBean_.type, 994L).build().find().iterator();
        while (it.hasNext()) {
            delete(it.next());
        }
        insertScanMemoryList(list);
    }
}
