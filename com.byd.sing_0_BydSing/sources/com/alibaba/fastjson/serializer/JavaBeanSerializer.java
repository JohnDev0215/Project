package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class JavaBeanSerializer extends SerializeFilterable implements ObjectSerializer {
    protected SerializeBeanInfo beanInfo;
    protected final FieldSerializer[] getters;
    private volatile transient long[] hashArray;
    private volatile transient short[] hashArrayMapping;
    protected final FieldSerializer[] sortedGetters;

    public JavaBeanSerializer(Class<?> cls) {
        this(cls, (Map<String, String>) null);
    }

    static Map<String, String> createAliasMap(String... strArr) {
        HashMap map = new HashMap();
        for (String str : strArr) {
            map.put(str, str);
        }
        return map;
    }

    protected boolean applyLabel(JSONSerializer jSONSerializer, String str) {
        List<LabelFilter> list = jSONSerializer.labelFilters;
        if (list != null) {
            Iterator<LabelFilter> it = list.iterator();
            while (it.hasNext()) {
                if (!it.next().apply(str)) {
                    return false;
                }
            }
        }
        List<LabelFilter> list2 = this.labelFilters;
        if (list2 == null) {
            return true;
        }
        Iterator<LabelFilter> it2 = list2.iterator();
        while (it2.hasNext()) {
            if (!it2.next().apply(str)) {
                return false;
            }
        }
        return true;
    }

    protected BeanContext getBeanContext(int i) {
        return this.sortedGetters[i].fieldContext;
    }

    public Set<String> getFieldNames(Object obj) throws Exception {
        HashSet hashSet = new HashSet();
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            if (fieldSerializer.getPropertyValueDirect(obj) != null) {
                hashSet.add(fieldSerializer.fieldInfo.name);
            }
        }
        return hashSet;
    }

    public FieldSerializer getFieldSerializer(String str) {
        if (str == null) {
            return null;
        }
        int i = 0;
        int length = this.sortedGetters.length - 1;
        while (i <= length) {
            int i2 = (i + length) >>> 1;
            int iCompareTo = this.sortedGetters[i2].fieldInfo.name.compareTo(str);
            if (iCompareTo < 0) {
                i = i2 + 1;
            } else {
                if (iCompareTo <= 0) {
                    return this.sortedGetters[i2];
                }
                length = i2 - 1;
            }
        }
        return null;
    }

    protected Type getFieldType(int i) {
        return this.sortedGetters[i].fieldInfo.fieldType;
    }

    public Object getFieldValue(Object obj, String str) {
        FieldSerializer fieldSerializer = getFieldSerializer(str);
        if (fieldSerializer == null) {
            throw new JSONException("field not found. " + str);
        }
        try {
            return fieldSerializer.getPropertyValue(obj);
        } catch (IllegalAccessException e) {
            throw new JSONException("getFieldValue error." + str, e);
        } catch (InvocationTargetException e2) {
            throw new JSONException("getFieldValue error." + str, e2);
        }
    }

    public List<Object> getFieldValues(Object obj) throws Exception {
        ArrayList arrayList = new ArrayList(this.sortedGetters.length);
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            arrayList.add(fieldSerializer.getPropertyValue(obj));
        }
        return arrayList;
    }

    public Map<String, Object> getFieldValuesMap(Object obj) throws Exception {
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.sortedGetters.length);
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            boolean zIsEnabled = SerializerFeature.isEnabled(fieldSerializer.features, SerializerFeature.SkipTransientField);
            FieldInfo fieldInfo = fieldSerializer.fieldInfo;
            if (!zIsEnabled || fieldInfo == null || !fieldInfo.fieldTransient) {
                if (fieldInfo.unwrapped) {
                    Object json = JSON.toJSON(fieldSerializer.getPropertyValue(obj));
                    if (json instanceof Map) {
                        linkedHashMap.putAll((Map) json);
                    } else {
                        linkedHashMap.put(fieldSerializer.fieldInfo.name, fieldSerializer.getPropertyValue(obj));
                    }
                } else {
                    linkedHashMap.put(fieldInfo.name, fieldSerializer.getPropertyValue(obj));
                }
            }
        }
        return linkedHashMap;
    }

    public List<Object> getObjectFieldValues(Object obj) throws Exception {
        ArrayList arrayList = new ArrayList(this.sortedGetters.length);
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            Class<?> cls = fieldSerializer.fieldInfo.fieldClass;
            if (!cls.isPrimitive() && !cls.getName().startsWith("java.lang.")) {
                arrayList.add(fieldSerializer.getPropertyValue(obj));
            }
        }
        return arrayList;
    }

    public int getSize(Object obj) throws Exception {
        int i = 0;
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            if (fieldSerializer.getPropertyValueDirect(obj) != null) {
                i++;
            }
        }
        return i;
    }

    public Class<?> getType() {
        return this.beanInfo.beanType;
    }

    protected boolean isWriteAsArray(JSONSerializer jSONSerializer) {
        return isWriteAsArray(jSONSerializer, 0);
    }

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws Throwable {
        write(jSONSerializer, obj, obj2, type, i, false);
    }

    protected char writeAfter(JSONSerializer jSONSerializer, Object obj, char c2) {
        List<AfterFilter> list = jSONSerializer.afterFilters;
        if (list != null) {
            Iterator<AfterFilter> it = list.iterator();
            while (it.hasNext()) {
                c2 = it.next().writeAfter(jSONSerializer, obj, c2);
            }
        }
        List<AfterFilter> list2 = this.afterFilters;
        if (list2 != null) {
            Iterator<AfterFilter> it2 = list2.iterator();
            while (it2.hasNext()) {
                c2 = it2.next().writeAfter(jSONSerializer, obj, c2);
            }
        }
        return c2;
    }

    public void writeAsArray(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws Throwable {
        write(jSONSerializer, obj, obj2, type, i);
    }

    public void writeAsArrayNonContext(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws Throwable {
        write(jSONSerializer, obj, obj2, type, i);
    }

    protected char writeBefore(JSONSerializer jSONSerializer, Object obj, char c2) {
        List<BeforeFilter> list = jSONSerializer.beforeFilters;
        if (list != null) {
            Iterator<BeforeFilter> it = list.iterator();
            while (it.hasNext()) {
                c2 = it.next().writeBefore(jSONSerializer, obj, c2);
            }
        }
        List<BeforeFilter> list2 = this.beforeFilters;
        if (list2 != null) {
            Iterator<BeforeFilter> it2 = list2.iterator();
            while (it2.hasNext()) {
                c2 = it2.next().writeBefore(jSONSerializer, obj, c2);
            }
        }
        return c2;
    }

    protected void writeClassName(JSONSerializer jSONSerializer, String str, Object obj) {
        if (str == null) {
            str = jSONSerializer.config.typeKey;
        }
        jSONSerializer.out.writeFieldName(str, false);
        String name = this.beanInfo.typeName;
        if (name == null) {
            Class<?> superclass = obj.getClass();
            if (TypeUtils.isProxy(superclass)) {
                superclass = superclass.getSuperclass();
            }
            name = superclass.getName();
        }
        jSONSerializer.write(name);
    }

    public void writeDirectNonContext(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws Throwable {
        write(jSONSerializer, obj, obj2, type, i);
    }

    public void writeNoneASM(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws Throwable {
        write(jSONSerializer, obj, obj2, type, i, false);
    }

    public boolean writeReference(JSONSerializer jSONSerializer, Object obj, int i) {
        IdentityHashMap<Object, SerialContext> identityHashMap;
        SerialContext serialContext = jSONSerializer.context;
        int i2 = SerializerFeature.DisableCircularReferenceDetect.mask;
        if (serialContext == null || (serialContext.features & i2) != 0 || (i & i2) != 0 || (identityHashMap = jSONSerializer.references) == null || !identityHashMap.containsKey(obj)) {
            return false;
        }
        jSONSerializer.writeReference(obj);
        return true;
    }

    public JavaBeanSerializer(Class<?> cls, String... strArr) {
        this(cls, createAliasMap(strArr));
    }

    protected boolean isWriteAsArray(JSONSerializer jSONSerializer, int i) {
        int i2 = SerializerFeature.BeanToArray.mask;
        return ((this.beanInfo.features & i2) == 0 && !jSONSerializer.out.beanToArray && (i & i2) == 0) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x02a0 A[Catch: Exception -> 0x0475, all -> 0x0494, TryCatch #3 {Exception -> 0x0475, blocks: (B:100:0x014c, B:111:0x0177, B:114:0x019e, B:116:0x01aa, B:118:0x01b5, B:120:0x01bf, B:123:0x01c9, B:125:0x01d5, B:127:0x01d9, B:130:0x01e0, B:132:0x01e4, B:133:0x01e8, B:135:0x01ed, B:137:0x01f0, B:139:0x01f6, B:141:0x0202, B:143:0x0206, B:146:0x020d, B:149:0x0214, B:151:0x0219, B:154:0x021d, B:156:0x0225, B:158:0x0231, B:160:0x0235, B:163:0x023c, B:165:0x0240, B:166:0x0245, B:168:0x024a, B:170:0x024d, B:171:0x0252, B:173:0x025a, B:175:0x0266, B:177:0x026a, B:180:0x0271, B:182:0x0275, B:183:0x027a, B:185:0x027f, B:187:0x0282, B:189:0x0289, B:191:0x028d, B:193:0x0297, B:197:0x02a0, B:199:0x02a4, B:201:0x02ad, B:203:0x02b4, B:205:0x02ba, B:207:0x02be, B:210:0x02c9, B:212:0x02cd, B:214:0x02d1, B:217:0x02dc, B:219:0x02e0, B:221:0x02e4, B:224:0x02ef, B:226:0x02f3, B:228:0x02f7, B:231:0x0305, B:233:0x0309, B:235:0x030d, B:238:0x031a, B:240:0x031e, B:242:0x0322, B:245:0x0330, B:247:0x0334, B:249:0x0338, B:253:0x0344, B:255:0x0348, B:257:0x034c, B:260:0x035c, B:262:0x0369, B:267:0x0375, B:269:0x037b, B:326:0x0439, B:328:0x043d, B:330:0x0441, B:333:0x044b, B:335:0x0453, B:336:0x045b, B:338:0x0461, B:274:0x0387, B:275:0x038a, B:277:0x0390, B:279:0x039c, B:286:0x03b2, B:291:0x03bc, B:295:0x03cf, B:298:0x03d7, B:301:0x03e1, B:303:0x03e9, B:304:0x03f2, B:306:0x03fb, B:308:0x0402, B:309:0x0406, B:311:0x0409, B:312:0x040d, B:313:0x0411, B:315:0x0416, B:316:0x041a, B:317:0x041e, B:319:0x0422, B:321:0x0426, B:324:0x0432, B:325:0x0436, B:292:0x03c5), top: B:418:0x014c }] */
    /* JADX WARN: Removed duplicated region for block: B:203:0x02b4 A[Catch: Exception -> 0x0475, all -> 0x0494, TryCatch #3 {Exception -> 0x0475, blocks: (B:100:0x014c, B:111:0x0177, B:114:0x019e, B:116:0x01aa, B:118:0x01b5, B:120:0x01bf, B:123:0x01c9, B:125:0x01d5, B:127:0x01d9, B:130:0x01e0, B:132:0x01e4, B:133:0x01e8, B:135:0x01ed, B:137:0x01f0, B:139:0x01f6, B:141:0x0202, B:143:0x0206, B:146:0x020d, B:149:0x0214, B:151:0x0219, B:154:0x021d, B:156:0x0225, B:158:0x0231, B:160:0x0235, B:163:0x023c, B:165:0x0240, B:166:0x0245, B:168:0x024a, B:170:0x024d, B:171:0x0252, B:173:0x025a, B:175:0x0266, B:177:0x026a, B:180:0x0271, B:182:0x0275, B:183:0x027a, B:185:0x027f, B:187:0x0282, B:189:0x0289, B:191:0x028d, B:193:0x0297, B:197:0x02a0, B:199:0x02a4, B:201:0x02ad, B:203:0x02b4, B:205:0x02ba, B:207:0x02be, B:210:0x02c9, B:212:0x02cd, B:214:0x02d1, B:217:0x02dc, B:219:0x02e0, B:221:0x02e4, B:224:0x02ef, B:226:0x02f3, B:228:0x02f7, B:231:0x0305, B:233:0x0309, B:235:0x030d, B:238:0x031a, B:240:0x031e, B:242:0x0322, B:245:0x0330, B:247:0x0334, B:249:0x0338, B:253:0x0344, B:255:0x0348, B:257:0x034c, B:260:0x035c, B:262:0x0369, B:267:0x0375, B:269:0x037b, B:326:0x0439, B:328:0x043d, B:330:0x0441, B:333:0x044b, B:335:0x0453, B:336:0x045b, B:338:0x0461, B:274:0x0387, B:275:0x038a, B:277:0x0390, B:279:0x039c, B:286:0x03b2, B:291:0x03bc, B:295:0x03cf, B:298:0x03d7, B:301:0x03e1, B:303:0x03e9, B:304:0x03f2, B:306:0x03fb, B:308:0x0402, B:309:0x0406, B:311:0x0409, B:312:0x040d, B:313:0x0411, B:315:0x0416, B:316:0x041a, B:317:0x041e, B:319:0x0422, B:321:0x0426, B:324:0x0432, B:325:0x0436, B:292:0x03c5), top: B:418:0x014c }] */
    /* JADX WARN: Removed duplicated region for block: B:252:0x0342  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x03cb  */
    /* JADX WARN: Removed duplicated region for block: B:344:0x046d  */
    /* JADX WARN: Removed duplicated region for block: B:346:0x0470  */
    /* JADX WARN: Removed duplicated region for block: B:387:0x0507 A[Catch: all -> 0x051a, TRY_ENTER, TryCatch #13 {all -> 0x051a, blocks: (B:387:0x0507, B:397:0x0557, B:399:0x055d, B:400:0x0575, B:402:0x0579, B:406:0x0582, B:407:0x0587, B:391:0x051e, B:393:0x0522, B:395:0x0526, B:396:0x0541), top: B:434:0x0505 }] */
    /* JADX WARN: Removed duplicated region for block: B:390:0x051c  */
    /* JADX WARN: Removed duplicated region for block: B:399:0x055d A[Catch: all -> 0x051a, TryCatch #13 {all -> 0x051a, blocks: (B:387:0x0507, B:397:0x0557, B:399:0x055d, B:400:0x0575, B:402:0x0579, B:406:0x0582, B:407:0x0587, B:391:0x051e, B:393:0x0522, B:395:0x0526, B:396:0x0541), top: B:434:0x0505 }] */
    /* JADX WARN: Removed duplicated region for block: B:402:0x0579 A[Catch: all -> 0x051a, TryCatch #13 {all -> 0x051a, blocks: (B:387:0x0507, B:397:0x0557, B:399:0x055d, B:400:0x0575, B:402:0x0579, B:406:0x0582, B:407:0x0587, B:391:0x051e, B:393:0x0522, B:395:0x0526, B:396:0x0541), top: B:434:0x0505 }] */
    /* JADX WARN: Removed duplicated region for block: B:404:0x057f  */
    /* JADX WARN: Removed duplicated region for block: B:405:0x0580  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00fe  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void write(com.alibaba.fastjson.serializer.JSONSerializer r34, java.lang.Object r35, java.lang.Object r36, java.lang.reflect.Type r37, int r38, boolean r39) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1421
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.serializer.JavaBeanSerializer.write(com.alibaba.fastjson.serializer.JSONSerializer, java.lang.Object, java.lang.Object, java.lang.reflect.Type, int, boolean):void");
    }

    public JavaBeanSerializer(Class<?> cls, Map<String, String> map) {
        this(TypeUtils.buildBeanInfo(cls, map, null));
    }

    public JavaBeanSerializer(SerializeBeanInfo serializeBeanInfo) {
        FieldSerializer[] fieldSerializerArr;
        boolean z;
        this.beanInfo = serializeBeanInfo;
        this.sortedGetters = new FieldSerializer[serializeBeanInfo.sortedFields.length];
        int i = 0;
        while (true) {
            fieldSerializerArr = this.sortedGetters;
            if (i >= fieldSerializerArr.length) {
                break;
            }
            fieldSerializerArr[i] = new FieldSerializer(serializeBeanInfo.beanType, serializeBeanInfo.sortedFields[i]);
            i++;
        }
        FieldInfo[] fieldInfoArr = serializeBeanInfo.fields;
        if (fieldInfoArr == serializeBeanInfo.sortedFields) {
            this.getters = fieldSerializerArr;
        } else {
            this.getters = new FieldSerializer[fieldInfoArr.length];
            int i2 = 0;
            while (true) {
                if (i2 >= this.getters.length) {
                    z = false;
                    break;
                }
                FieldSerializer fieldSerializer = getFieldSerializer(serializeBeanInfo.fields[i2].name);
                if (fieldSerializer == null) {
                    z = true;
                    break;
                } else {
                    this.getters[i2] = fieldSerializer;
                    i2++;
                }
            }
            if (z) {
                FieldSerializer[] fieldSerializerArr2 = this.sortedGetters;
                System.arraycopy(fieldSerializerArr2, 0, this.getters, 0, fieldSerializerArr2.length);
            }
        }
        JSONType jSONType = serializeBeanInfo.jsonType;
        if (jSONType != null) {
            for (Class<? extends SerializeFilter> cls : jSONType.serialzeFilters()) {
                try {
                    addFilter(cls.getConstructor(new Class[0]).newInstance(new Object[0]));
                } catch (Exception unused) {
                }
            }
        }
    }

    public FieldSerializer getFieldSerializer(long j) {
        PropertyNamingStrategy[] propertyNamingStrategyArrValues;
        int iBinarySearch;
        if (this.hashArray == null) {
            propertyNamingStrategyArrValues = PropertyNamingStrategy.values();
            long[] jArr = new long[this.sortedGetters.length * propertyNamingStrategyArrValues.length];
            int i = 0;
            int i2 = 0;
            while (true) {
                FieldSerializer[] fieldSerializerArr = this.sortedGetters;
                if (i >= fieldSerializerArr.length) {
                    break;
                }
                String str = fieldSerializerArr[i].fieldInfo.name;
                jArr[i2] = TypeUtils.fnv1a_64(str);
                i2++;
                for (PropertyNamingStrategy propertyNamingStrategy : propertyNamingStrategyArrValues) {
                    String strTranslate = propertyNamingStrategy.translate(str);
                    if (!str.equals(strTranslate)) {
                        jArr[i2] = TypeUtils.fnv1a_64(strTranslate);
                        i2++;
                    }
                }
                i++;
            }
            Arrays.sort(jArr, 0, i2);
            this.hashArray = new long[i2];
            System.arraycopy(jArr, 0, this.hashArray, 0, i2);
        } else {
            propertyNamingStrategyArrValues = null;
        }
        int iBinarySearch2 = Arrays.binarySearch(this.hashArray, j);
        if (iBinarySearch2 < 0) {
            return null;
        }
        if (this.hashArrayMapping == null) {
            if (propertyNamingStrategyArrValues == null) {
                propertyNamingStrategyArrValues = PropertyNamingStrategy.values();
            }
            short[] sArr = new short[this.hashArray.length];
            Arrays.fill(sArr, (short) -1);
            int i3 = 0;
            while (true) {
                FieldSerializer[] fieldSerializerArr2 = this.sortedGetters;
                if (i3 >= fieldSerializerArr2.length) {
                    break;
                }
                String str2 = fieldSerializerArr2[i3].fieldInfo.name;
                int iBinarySearch3 = Arrays.binarySearch(this.hashArray, TypeUtils.fnv1a_64(str2));
                if (iBinarySearch3 >= 0) {
                    sArr[iBinarySearch3] = (short) i3;
                }
                for (PropertyNamingStrategy propertyNamingStrategy2 : propertyNamingStrategyArrValues) {
                    String strTranslate2 = propertyNamingStrategy2.translate(str2);
                    if (!str2.equals(strTranslate2) && (iBinarySearch = Arrays.binarySearch(this.hashArray, TypeUtils.fnv1a_64(strTranslate2))) >= 0) {
                        sArr[iBinarySearch] = (short) i3;
                    }
                }
                i3++;
            }
            this.hashArrayMapping = sArr;
        }
        short s = this.hashArrayMapping[iBinarySearch2];
        if (s != -1) {
            return this.sortedGetters[s];
        }
        return null;
    }

    public Object getFieldValue(Object obj, String str, long j, boolean z) {
        FieldSerializer fieldSerializer = getFieldSerializer(j);
        if (fieldSerializer == null) {
            if (!z) {
                return null;
            }
            throw new JSONException("field not found. " + str);
        }
        try {
            return fieldSerializer.getPropertyValue(obj);
        } catch (IllegalAccessException e) {
            throw new JSONException("getFieldValue error." + str, e);
        } catch (InvocationTargetException e2) {
            throw new JSONException("getFieldValue error." + str, e2);
        }
    }
}
