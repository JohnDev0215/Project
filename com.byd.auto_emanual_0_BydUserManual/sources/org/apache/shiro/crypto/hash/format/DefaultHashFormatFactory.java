package org.apache.shiro.crypto.hash.format;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.shiro.util.ClassUtils;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.util.UnknownClassException;

/* JADX INFO: loaded from: classes.dex */
public class DefaultHashFormatFactory implements HashFormatFactory {
    private Set<String> searchPackages = new HashSet();
    private Map<String, String> formatClassNames = new HashMap();

    public Map<String, String> getFormatClassNames() {
        return this.formatClassNames;
    }

    public void setFormatClassNames(Map<String, String> map) {
        this.formatClassNames = map;
    }

    public Set<String> getSearchPackages() {
        return this.searchPackages;
    }

    public void setSearchPackages(Set<String> set) {
        this.searchPackages = set;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0028  */
    @Override // org.apache.shiro.crypto.hash.format.HashFormatFactory
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.apache.shiro.crypto.hash.format.HashFormat getInstance(java.lang.String r4) {
        /*
            r3 = this;
            r0 = 0
            if (r4 != 0) goto L4
            return r0
        L4:
            java.lang.String r1 = "$"
            boolean r1 = r4.startsWith(r1)
            if (r1 == 0) goto L28
            r1 = 1
            java.lang.String r1 = r4.substring(r1)
            java.lang.String r2 = "\\$"
            java.lang.String[] r1 = r1.split(r2)
            if (r1 == 0) goto L20
            int r2 = r1.length
            if (r2 <= 0) goto L20
            r2 = 0
            r1 = r1[r2]
            goto L21
        L20:
            r1 = r0
        L21:
            if (r1 == 0) goto L28
            java.lang.Class r1 = r3.getHashFormatClass(r1)
            goto L29
        L28:
            r1 = r0
        L29:
            if (r1 != 0) goto L2f
            java.lang.Class r1 = r3.getHashFormatClass(r4)
        L2f:
            if (r1 == 0) goto L35
            org.apache.shiro.crypto.hash.format.HashFormat r0 = r3.newHashFormatInstance(r1)
        L35:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.shiro.crypto.hash.format.DefaultHashFormatFactory.getInstance(java.lang.String):org.apache.shiro.crypto.hash.format.HashFormat");
    }

    protected Class getHashFormatClass(String str) {
        Set<String> set;
        ProvidedHashFormat providedHashFormatById;
        String str2;
        Map<String, String> map = this.formatClassNames;
        Class<? extends HashFormat> clsLookupHashFormatClass = (map == null || (str2 = map.get(str)) == null) ? null : lookupHashFormatClass(str2);
        if (clsLookupHashFormatClass == null && (providedHashFormatById = ProvidedHashFormat.byId(str)) != null) {
            clsLookupHashFormatClass = providedHashFormatById.getHashFormatClass();
        }
        if (clsLookupHashFormatClass == null) {
            clsLookupHashFormatClass = lookupHashFormatClass(str);
        }
        if (clsLookupHashFormatClass == null && (set = this.searchPackages) != null) {
            Iterator<String> it = set.iterator();
            while (it.hasNext() && (clsLookupHashFormatClass = getHashFormatClass(it.next(), str)) == null) {
            }
        }
        if (clsLookupHashFormatClass != null) {
            assertHashFormatImpl(clsLookupHashFormatClass);
        }
        return clsLookupHashFormatClass;
    }

    protected Class getHashFormatClass(String str, String str2) {
        if (str == null) {
            str = "";
        }
        Class clsLookupHashFormatClass = lookupHashFormatClass(str2);
        if (clsLookupHashFormatClass == null) {
            clsLookupHashFormatClass = lookupHashFormatClass(str + "." + str2);
        }
        if (clsLookupHashFormatClass == null) {
            clsLookupHashFormatClass = lookupHashFormatClass(str + "." + StringUtils.uppercaseFirstChar(str2) + "Format");
        }
        if (clsLookupHashFormatClass == null) {
            clsLookupHashFormatClass = lookupHashFormatClass(str + "." + str2 + "Format");
        }
        if (clsLookupHashFormatClass == null) {
            clsLookupHashFormatClass = lookupHashFormatClass(str + "." + StringUtils.uppercaseFirstChar(str2) + "HashFormat");
        }
        if (clsLookupHashFormatClass == null) {
            clsLookupHashFormatClass = lookupHashFormatClass(str + "." + str2 + "HashFormat");
        }
        if (clsLookupHashFormatClass == null) {
            clsLookupHashFormatClass = lookupHashFormatClass(str + "." + StringUtils.uppercaseFirstChar(str2) + "CryptFormat");
        }
        if (clsLookupHashFormatClass == null) {
            clsLookupHashFormatClass = lookupHashFormatClass(str + "." + str2 + "CryptFormat");
        }
        if (clsLookupHashFormatClass == null) {
            return null;
        }
        assertHashFormatImpl(clsLookupHashFormatClass);
        return clsLookupHashFormatClass;
    }

    protected Class lookupHashFormatClass(String str) {
        try {
            return ClassUtils.forName(str);
        } catch (UnknownClassException unused) {
            return null;
        }
    }

    protected final void assertHashFormatImpl(Class cls) {
        if (!HashFormat.class.isAssignableFrom(cls) || cls.isInterface()) {
            throw new IllegalArgumentException("Discovered class [" + cls.getName() + "] is not a " + HashFormat.class.getName() + " implementation.");
        }
    }

    protected final HashFormat newHashFormatInstance(Class cls) {
        assertHashFormatImpl(cls);
        return (HashFormat) ClassUtils.newInstance(cls);
    }
}
