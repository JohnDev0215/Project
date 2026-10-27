package com.google.android.gms.internal.wearable;

import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzcz {
    static String zza(zzcx zzcxVar, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        zzc(zzcxVar, sb, 0);
        return sb.toString();
    }

    static final void zzb(StringBuilder sb, int i, String str, Object obj) {
        if (obj instanceof List) {
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                zzb(sb, i, str, it.next());
            }
            return;
        }
        if (obj instanceof Map) {
            Iterator it2 = ((Map) obj).entrySet().iterator();
            while (it2.hasNext()) {
                zzb(sb, i, str, (Map.Entry) it2.next());
            }
            return;
        }
        sb.append('\n');
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            sb.append(' ');
        }
        sb.append(str);
        if (obj instanceof String) {
            sb.append(": \"");
            sb.append(zzdu.zza(zzau.zzm((String) obj)));
            sb.append('\"');
            return;
        }
        if (obj instanceof zzau) {
            sb.append(": \"");
            sb.append(zzdu.zza((zzau) obj));
            sb.append('\"');
            return;
        }
        if (obj instanceof zzbs) {
            sb.append(" {");
            zzc((zzbs) obj, sb, i + 2);
            sb.append("\n");
            while (i2 < i) {
                sb.append(' ');
                i2++;
            }
            sb.append("}");
            return;
        }
        if (!(obj instanceof Map.Entry)) {
            sb.append(": ");
            sb.append(obj.toString());
            return;
        }
        sb.append(" {");
        Map.Entry entry = (Map.Entry) obj;
        int i4 = i + 2;
        zzb(sb, i4, "key", entry.getKey());
        zzb(sb, i4, "value", entry.getValue());
        sb.append("\n");
        while (i2 < i) {
            sb.append(' ');
            i2++;
        }
        sb.append("}");
    }

    private static void zzc(zzcx zzcxVar, StringBuilder sb, int i) {
        boolean zEquals;
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        TreeSet<String> treeSet = new TreeSet();
        for (Method method : zzcxVar.getClass().getDeclaredMethods()) {
            map2.put(method.getName(), method);
            if (method.getParameterTypes().length == 0) {
                map.put(method.getName(), method);
                if (method.getName().startsWith("get")) {
                    treeSet.add(method.getName());
                }
            }
        }
        for (String str : treeSet) {
            String strSubstring = str.startsWith("get") ? str.substring(3) : str;
            if (strSubstring.endsWith("List") && !strSubstring.endsWith("OrBuilderList") && !strSubstring.equals("List")) {
                String strValueOf = String.valueOf(strSubstring.substring(0, 1).toLowerCase());
                String strValueOf2 = String.valueOf(strSubstring.substring(1, strSubstring.length() - 4));
                String strConcat = strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf);
                Method method2 = (Method) map.get(str);
                if (method2 != null && method2.getReturnType().equals(List.class)) {
                    zzb(sb, i, zzd(strConcat), zzbs.zzT(method2, zzcxVar, new Object[0]));
                }
            }
            if (strSubstring.endsWith("Map") && !strSubstring.equals("Map")) {
                String strValueOf3 = String.valueOf(strSubstring.substring(0, 1).toLowerCase());
                String strValueOf4 = String.valueOf(strSubstring.substring(1, strSubstring.length() - 3));
                String strConcat2 = strValueOf4.length() != 0 ? strValueOf3.concat(strValueOf4) : new String(strValueOf3);
                Method method3 = (Method) map.get(str);
                if (method3 != null && method3.getReturnType().equals(Map.class) && !method3.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method3.getModifiers())) {
                    zzb(sb, i, zzd(strConcat2), zzbs.zzT(method3, zzcxVar, new Object[0]));
                }
            }
            if (((Method) map2.get(strSubstring.length() != 0 ? "set".concat(strSubstring) : new String("set"))) != null) {
                if (strSubstring.endsWith("Bytes")) {
                    String strValueOf5 = String.valueOf(strSubstring.substring(0, strSubstring.length() - 5));
                    if (!map.containsKey(strValueOf5.length() != 0 ? "get".concat(strValueOf5) : new String("get"))) {
                    }
                }
                String strValueOf6 = String.valueOf(strSubstring.substring(0, 1).toLowerCase());
                String strValueOf7 = String.valueOf(strSubstring.substring(1));
                String strConcat3 = strValueOf7.length() != 0 ? strValueOf6.concat(strValueOf7) : new String(strValueOf6);
                Method method4 = (Method) map.get(strSubstring.length() != 0 ? "get".concat(strSubstring) : new String("get"));
                Method method5 = (Method) map.get(strSubstring.length() != 0 ? "has".concat(strSubstring) : new String("has"));
                if (method4 != null) {
                    Object objZzT = zzbs.zzT(method4, zzcxVar, new Object[0]);
                    if (method5 == null) {
                        if (objZzT instanceof Boolean) {
                            if (((Boolean) objZzT).booleanValue()) {
                                zzb(sb, i, zzd(strConcat3), objZzT);
                            }
                        } else if (objZzT instanceof Integer) {
                            if (((Integer) objZzT).intValue() != 0) {
                                zzb(sb, i, zzd(strConcat3), objZzT);
                            }
                        } else if (objZzT instanceof Float) {
                            if (((Float) objZzT).floatValue() != 0.0f) {
                                zzb(sb, i, zzd(strConcat3), objZzT);
                            }
                        } else if (!(objZzT instanceof Double)) {
                            if (objZzT instanceof String) {
                                zEquals = objZzT.equals("");
                            } else if (objZzT instanceof zzau) {
                                zEquals = objZzT.equals(zzau.zzb);
                            } else if (objZzT instanceof zzcx) {
                                if (objZzT != ((zzcx) objZzT).zzac()) {
                                    zzb(sb, i, zzd(strConcat3), objZzT);
                                }
                            } else if (!(objZzT instanceof Enum) || ((Enum) objZzT).ordinal() != 0) {
                                zzb(sb, i, zzd(strConcat3), objZzT);
                            }
                            if (!zEquals) {
                                zzb(sb, i, zzd(strConcat3), objZzT);
                            }
                        } else if (((Double) objZzT).doubleValue() != 0.0d) {
                            zzb(sb, i, zzd(strConcat3), objZzT);
                        }
                    } else if (((Boolean) zzbs.zzT(method5, zzcxVar, new Object[0])).booleanValue()) {
                        zzb(sb, i, zzd(strConcat3), objZzT);
                    }
                }
            }
        }
        if (zzcxVar instanceof zzbq) {
            throw null;
        }
        zzdx zzdxVar = ((zzbs) zzcxVar).zzc;
        if (zzdxVar != null) {
            zzdxVar.zzg(sb, i);
        }
    }

    private static final String zzd(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (Character.isUpperCase(cCharAt)) {
                sb.append(Attributes.PREDEFINED_ATTRIBUTE_PREFIX);
            }
            sb.append(Character.toLowerCase(cCharAt));
        }
        return sb.toString();
    }
}
