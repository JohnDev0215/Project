package com.sun.xml.bind.api.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.lang.model.SourceVersion;
import net.bytebuddy.pool.TypePool;

/* JADX INFO: loaded from: classes3.dex */
public interface NameConverter {
    public static final NameConverter standard = new Standard();
    public static final NameConverter jaxrpcCompatible = new Standard() { // from class: com.sun.xml.bind.api.impl.NameConverter.1
        @Override // com.sun.xml.bind.api.impl.NameUtil
        protected boolean isPunct(char c) {
            return c == '.' || c == '-' || c == ';' || c == 183 || c == 903 || c == 1757 || c == 1758;
        }

        @Override // com.sun.xml.bind.api.impl.NameUtil
        protected boolean isLetter(char c) {
            return super.isLetter(c) || c == '_';
        }

        @Override // com.sun.xml.bind.api.impl.NameUtil
        protected int classify(char c) {
            if (c == '_') {
                return 2;
            }
            return super.classify(c);
        }
    };
    public static final NameConverter smart = new Standard() { // from class: com.sun.xml.bind.api.impl.NameConverter.2
        @Override // com.sun.xml.bind.api.impl.NameConverter.Standard, com.sun.xml.bind.api.impl.NameUtil, com.sun.xml.bind.api.impl.NameConverter
        public String toConstantName(String str) {
            String constantName = super.toConstantName(str);
            return !SourceVersion.isKeyword(constantName) ? constantName : "_" + constantName;
        }
    };

    String toClassName(String str);

    String toConstantName(String str);

    String toInterfaceName(String str);

    String toPackageName(String str);

    String toPropertyName(String str);

    String toVariableName(String str);

    public static class Standard extends NameUtil implements NameConverter {
        @Override // com.sun.xml.bind.api.impl.NameUtil
        public /* bridge */ /* synthetic */ String capitalize(String str) {
            return super.capitalize(str);
        }

        @Override // com.sun.xml.bind.api.impl.NameUtil
        public /* bridge */ /* synthetic */ String toConstantName(List list) {
            return super.toConstantName((List<String>) list);
        }

        @Override // com.sun.xml.bind.api.impl.NameUtil
        public /* bridge */ /* synthetic */ List toWordList(String str) {
            return super.toWordList(str);
        }

        @Override // com.sun.xml.bind.api.impl.NameConverter
        public String toClassName(String str) {
            return toMixedCaseName(toWordList(str), true);
        }

        @Override // com.sun.xml.bind.api.impl.NameConverter
        public String toVariableName(String str) {
            return toMixedCaseName(toWordList(str), false);
        }

        @Override // com.sun.xml.bind.api.impl.NameConverter
        public String toInterfaceName(String str) {
            return toClassName(str);
        }

        @Override // com.sun.xml.bind.api.impl.NameConverter
        public String toPropertyName(String str) {
            String className = toClassName(str);
            return className.equals("Class") ? "Clazz" : className;
        }

        @Override // com.sun.xml.bind.api.impl.NameUtil, com.sun.xml.bind.api.impl.NameConverter
        public String toConstantName(String str) {
            return super.toConstantName(str);
        }

        @Override // com.sun.xml.bind.api.impl.NameConverter
        public String toPackageName(String str) {
            String strSubstring;
            String str2;
            int iLastIndexOf;
            int iIndexOf = str.indexOf(58);
            if (iIndexOf >= 0) {
                strSubstring = str.substring(0, iIndexOf);
                if (strSubstring.equalsIgnoreCase("http") || strSubstring.equalsIgnoreCase("urn")) {
                    str = str.substring(iIndexOf + 1);
                }
            } else {
                strSubstring = "";
            }
            ArrayList<String> arrayList = tokenize(str, "/: ");
            if (arrayList.size() == 0) {
                return null;
            }
            if (arrayList.size() > 1 && (iLastIndexOf = (str2 = arrayList.get(arrayList.size() - 1)).lastIndexOf(46)) > 0) {
                arrayList.set(arrayList.size() - 1, str2.substring(0, iLastIndexOf));
            }
            String strSubstring2 = arrayList.get(0);
            int iIndexOf2 = strSubstring2.indexOf(58);
            if (iIndexOf2 >= 0) {
                strSubstring2 = strSubstring2.substring(0, iIndexOf2);
            }
            ArrayList arrayListReverse = reverse(tokenize(strSubstring2, strSubstring.equals("urn") ? ".-" : "."));
            if (((String) arrayListReverse.get(arrayListReverse.size() - 1)).equalsIgnoreCase("www")) {
                arrayListReverse.remove(arrayListReverse.size() - 1);
            }
            arrayList.addAll(1, arrayListReverse);
            arrayList.remove(0);
            for (int i = 0; i < arrayList.size(); i++) {
                String strRemoveIllegalIdentifierChars = removeIllegalIdentifierChars(arrayList.get(i));
                if (SourceVersion.isKeyword(strRemoveIllegalIdentifierChars.toLowerCase())) {
                    strRemoveIllegalIdentifierChars = "_" + strRemoveIllegalIdentifierChars;
                }
                arrayList.set(i, strRemoveIllegalIdentifierChars.toLowerCase());
            }
            return combine(arrayList, TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH);
        }

        private static String removeIllegalIdentifierChars(String str) {
            StringBuilder sb = new StringBuilder(str.length() + 1);
            for (int i = 0; i < str.length(); i++) {
                char cCharAt = str.charAt(i);
                if (i == 0 && !Character.isJavaIdentifierStart(cCharAt)) {
                    sb.append('_');
                }
                if (!Character.isJavaIdentifierPart(cCharAt)) {
                    sb.append('_');
                } else {
                    sb.append(cCharAt);
                }
            }
            return sb.toString();
        }

        private static ArrayList<String> tokenize(String str, String str2) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, str2);
            ArrayList<String> arrayList = new ArrayList<>();
            while (stringTokenizer.hasMoreTokens()) {
                arrayList.add(stringTokenizer.nextToken());
            }
            return arrayList;
        }

        private static <T> ArrayList<T> reverse(List<T> list) {
            ArrayList<T> arrayList = new ArrayList<>();
            for (int size = list.size() - 1; size >= 0; size--) {
                arrayList.add(list.get(size));
            }
            return arrayList;
        }

        private static String combine(List list, char c) {
            StringBuilder sb = new StringBuilder(list.get(0).toString());
            for (int i = 1; i < list.size(); i++) {
                sb.append(c);
                sb.append(list.get(i));
            }
            return sb.toString();
        }
    }
}
