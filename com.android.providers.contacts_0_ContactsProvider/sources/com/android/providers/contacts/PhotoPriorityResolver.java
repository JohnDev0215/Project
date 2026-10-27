package com.android.providers.contacts;

import android.accounts.AccountManager;
import android.accounts.AuthenticatorDescription;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.XmlResourceParser;
import android.util.ArrayMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PhotoPriorityResolver {
    private static final String[] METADATA_CONTACTS_NAMES = {"android.provider.ALTERNATE_CONTACTS_STRUCTURE", "android.provider.CONTACTS_STRUCTURE"};
    private Context mContext;
    private ArrayMap<String, Integer> mPhotoPriorities = new ArrayMap<>();

    public PhotoPriorityResolver(Context context) {
        this.mContext = context;
    }

    public synchronized int getPhotoPriority(String str) {
        if (str == null) {
            return 7;
        }
        Integer numValueOf = this.mPhotoPriorities.get(str);
        if (numValueOf == null) {
            numValueOf = Integer.valueOf(resolvePhotoPriority(str));
            this.mPhotoPriorities.put(str, numValueOf);
        }
        return numValueOf.intValue();
    }

    private int resolvePhotoPriority(String str) {
        for (AuthenticatorDescription authenticatorDescription : AccountManager.get(this.mContext).getAuthenticatorTypes()) {
            if (str.equals(authenticatorDescription.type)) {
                return resolvePhotoPriorityFromMetaData(authenticatorDescription.packageName);
            }
        }
        return 7;
    }

    int resolvePhotoPriorityFromMetaData(String str) {
        PackageManager packageManager = this.mContext.getPackageManager();
        List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(new Intent("android.content.SyncAdapter").setPackage(str), 132);
        if (listQueryIntentServices == null) {
            return 7;
        }
        Iterator<ResolveInfo> it = listQueryIntentServices.iterator();
        while (it.hasNext()) {
            ServiceInfo serviceInfo = it.next().serviceInfo;
            if (serviceInfo != null) {
                for (String str2 : METADATA_CONTACTS_NAMES) {
                    XmlResourceParser xmlResourceParserLoadXmlMetaData = serviceInfo.loadXmlMetaData(packageManager, str2);
                    if (xmlResourceParserLoadXmlMetaData != null) {
                        return loadPhotoPriorityFromXml(this.mContext, xmlResourceParserLoadXmlMetaData);
                    }
                }
            }
        }
        return 7;
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0067, code lost:
    
        return r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int loadPhotoPriorityFromXml(android.content.Context r8, org.xmlpull.v1.XmlPullParser r9) {
        /*
            r7 = this;
            java.lang.String r7 = "Problem reading XML"
        L2:
            int r8 = r9.next()     // Catch: java.io.IOException -> L70 org.xmlpull.v1.XmlPullParserException -> L77
            r0 = 1
            r1 = 2
            if (r8 == r1) goto Ld
            if (r8 == r0) goto Ld
            goto L2
        Ld:
            if (r8 != r1) goto L68
            int r8 = r9.getDepth()     // Catch: java.io.IOException -> L70 org.xmlpull.v1.XmlPullParserException -> L77
            r2 = 7
            r3 = r2
        L15:
            int r4 = r9.next()     // Catch: java.io.IOException -> L70 org.xmlpull.v1.XmlPullParserException -> L77
            r5 = 3
            if (r4 != r5) goto L22
            int r5 = r9.getDepth()     // Catch: java.io.IOException -> L70 org.xmlpull.v1.XmlPullParserException -> L77
            if (r5 <= r8) goto L67
        L22:
            if (r4 == r0) goto L67
            java.lang.String r5 = r9.getName()     // Catch: java.io.IOException -> L70 org.xmlpull.v1.XmlPullParserException -> L77
            if (r4 != r1) goto L15
            java.lang.String r4 = "Picture"
            boolean r4 = r4.equals(r5)     // Catch: java.io.IOException -> L70 org.xmlpull.v1.XmlPullParserException -> L77
            if (r4 == 0) goto L15
            int r4 = r9.getAttributeCount()     // Catch: java.io.IOException -> L70 org.xmlpull.v1.XmlPullParserException -> L77
            r5 = 0
        L37:
            if (r5 >= r4) goto L15
            java.lang.String r3 = r9.getAttributeName(r5)     // Catch: java.io.IOException -> L70 org.xmlpull.v1.XmlPullParserException -> L77
            java.lang.String r6 = "priority"
            boolean r6 = r6.equals(r3)     // Catch: java.io.IOException -> L70 org.xmlpull.v1.XmlPullParserException -> L77
            if (r6 == 0) goto L50
            java.lang.String r3 = r9.getAttributeValue(r5)     // Catch: java.io.IOException -> L70 org.xmlpull.v1.XmlPullParserException -> L77
            int r3 = com.android.internal.util.XmlUtils.convertValueToInt(r3, r2)     // Catch: java.io.IOException -> L70 org.xmlpull.v1.XmlPullParserException -> L77
            int r5 = r5 + 1
            goto L37
        L50:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException     // Catch: java.io.IOException -> L70 org.xmlpull.v1.XmlPullParserException -> L77
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L70 org.xmlpull.v1.XmlPullParserException -> L77
            r9.<init>()     // Catch: java.io.IOException -> L70 org.xmlpull.v1.XmlPullParserException -> L77
            java.lang.String r0 = "Unsupported attribute "
            r9.append(r0)     // Catch: java.io.IOException -> L70 org.xmlpull.v1.XmlPullParserException -> L77
            r9.append(r3)     // Catch: java.io.IOException -> L70 org.xmlpull.v1.XmlPullParserException -> L77
            java.lang.String r9 = r9.toString()     // Catch: java.io.IOException -> L70 org.xmlpull.v1.XmlPullParserException -> L77
            r8.<init>(r9)     // Catch: java.io.IOException -> L70 org.xmlpull.v1.XmlPullParserException -> L77
            throw r8     // Catch: java.io.IOException -> L70 org.xmlpull.v1.XmlPullParserException -> L77
        L67:
            return r3
        L68:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException     // Catch: java.io.IOException -> L70 org.xmlpull.v1.XmlPullParserException -> L77
            java.lang.String r9 = "No start tag found"
            r8.<init>(r9)     // Catch: java.io.IOException -> L70 org.xmlpull.v1.XmlPullParserException -> L77
            throw r8     // Catch: java.io.IOException -> L70 org.xmlpull.v1.XmlPullParserException -> L77
        L70:
            r8 = move-exception
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            r9.<init>(r7, r8)
            throw r9
        L77:
            r8 = move-exception
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            r9.<init>(r7, r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.contacts.PhotoPriorityResolver.loadPhotoPriorityFromXml(android.content.Context, org.xmlpull.v1.XmlPullParser):int");
    }
}
