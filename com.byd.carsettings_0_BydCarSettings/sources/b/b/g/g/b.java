package b.b.g.g;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.text.TextUtils;
import b.b.g.g.c;
import b.b.g.g.e;
import b.b.g.g.f;
import b.b.g.p.d;
import b.b.g.r.b0;
import b.b.m.g.g;
import com.byd.carsettings.R;
import com.byd.carsettings.db.model.FunctionItem;
import com.byd.carsettings.model.XmlItem;
import com.bydux.api.common.tools.util.ListUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: compiled from: CarSettingDbManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static StringBuilder f785b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String[] f784a = {"module0", "module1"};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static volatile boolean f786c = false;

    /* JADX INFO: compiled from: CarSettingDbManager.java */
    public static class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f787a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Context f788b;

        public a(Context context, Context context2) {
            this.f787a = context;
            this.f788b = context2;
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x00cb  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 209
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: b.b.g.g.b.a.run():void");
        }
    }

    /* JADX INFO: renamed from: b.b.g.g.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CarSettingDbManager.java */
    public static class RunnableC0042b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f789a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ b.b.g.g.c f790b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ f f791c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ c f792d;

        public RunnableC0042b(Context context, b.b.g.g.c cVar, f fVar, c cVar2) {
            this.f789a = context;
            this.f790b = cVar;
            this.f791c = fVar;
            this.f792d = cVar2;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            f fVar;
            Resources resources = this.f789a.getResources();
            Locale localeV = d.g.a().v(this.f789a);
            boolean zC0 = d.g.a().c0(this.f789a);
            String string = localeV.toString();
            int iO = this.f790b.o(string);
            int iQ = this.f791c.q(string);
            b.b.m.c.a.g("CarSettingDbManager", "--checkLanguageChanged--langTag: " + string + ", dataSize: " + iO + ", keywordDataSize: " + iQ);
            if (iQ <= 0 && (fVar = this.f791c) != null) {
                fVar.c(null);
            }
            if (iO <= 0) {
                c cVar = this.f792d;
                if (cVar != null) {
                    cVar.a();
                }
                f fVar2 = this.f791c;
                if (fVar2 != null) {
                    fVar2.c(null);
                }
                b.r(this.f789a, resources, zC0, localeV.toString(), this.f789a.getPackageName(), this.f790b);
            }
            c cVar2 = this.f792d;
            if (cVar2 != null) {
                cVar2.b();
            }
        }
    }

    /* JADX INFO: compiled from: CarSettingDbManager.java */
    public interface c {
        void a();

        void b();
    }

    public static void c(SharedPreferences.Editor editor, String str, String str2, int i) {
        if (editor == null) {
            return;
        }
        String str3 = str + ListUtils.DEFAULT_JOIN_SEPARATOR + j(str2, i);
        b.b.m.c.a.d("CarSettingDbManager", "--appendDbState--newDbStateList: " + str3);
        editor.putString("function_items_db_state_list", str3);
        editor.commit();
    }

    public static void d(Context context) {
        if (context == null) {
            return;
        }
        b.b.m.c.a.d("CarSettingDbManager", "--checkDbStateCompleted--");
        b.b.m.g.b.a(new a(context.getApplicationContext(), context));
    }

    public static boolean e(SharedPreferences.Editor editor, String str, String str2, int i) {
        b.b.m.c.a.d("CarSettingDbManager", "--checkDbStateList--dbStateList: " + str);
        if (TextUtils.isEmpty(str)) {
            t(editor, str2, i);
            return false;
        }
        if (!str.contains(ListUtils.DEFAULT_JOIN_SEPARATOR)) {
            if (str.contains(str2)) {
                s(editor, str, str2, i);
            } else {
                c(editor, str, str2, i);
            }
            return false;
        }
        String[] strArrSplit = str.split(ListUtils.DEFAULT_JOIN_SEPARATOR);
        if (strArrSplit != null && strArrSplit.length > 0) {
            return true;
        }
        t(editor, str2, i);
        return false;
    }

    public static void f(Context context, c cVar) {
        if (context == null) {
            return;
        }
        b.b.g.g.c cVarA = c.b.a();
        cVarA.m();
        f fVarA = f.b.a();
        fVarA.m();
        b.b.m.g.b.a(new RunnableC0042b(context, cVarA, fVarA, cVar));
    }

    public static boolean g(String str) {
        String[] strArrSplit;
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        if (str.contains("#") && (strArrSplit = str.split("#")) != null && strArrSplit.length >= 2) {
            try {
                return Integer.parseInt(strArrSplit[1]) == 1;
            } catch (RuntimeException unused) {
            }
        }
        return false;
    }

    public static String h(String str) {
        if (TextUtils.isEmpty(str) || !str.contains(">")) {
            return "";
        }
        String[] strArrSplit = str.split(">");
        return str.length() > 1 ? strArrSplit[strArrSplit.length - 1] : "";
    }

    public static String i(String str, String str2) {
        return h(str) + " " + str2;
    }

    public static String j(String str, int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("#");
        sb.append(i + "");
        return sb.toString();
    }

    public static String k(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        String strH = h(str);
        if (!TextUtils.isEmpty(strH)) {
            sb.append(strH);
            String[] strArrL = l(strH);
            if (strArrL != null && strArrL.length > 1) {
                sb.append(" ");
                for (String str3 : strArrL) {
                    if (str3.startsWith("(")) {
                        if (str3.length() > 1) {
                            sb.append(str3.charAt(1));
                        }
                    } else if (str3.length() > 0) {
                        sb.append(str3.charAt(0));
                    }
                }
            }
        }
        String[] strArrL2 = l(str2);
        if (strArrL2 == null || strArrL2.length <= 1) {
            return sb.toString();
        }
        if (sb.toString().length() > 0) {
            sb.append(" ");
        }
        for (String str4 : strArrL2) {
            if (str4.startsWith("(")) {
                if (str4.length() > 1) {
                    sb.append(str4.charAt(1));
                }
            } else if (str4.length() > 0) {
                sb.append(str4.charAt(0));
            }
        }
        return sb.toString();
    }

    public static String[] l(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String strTrim = str.trim();
        if (strTrim.contains("-")) {
            strTrim = strTrim.replace("-", " ");
        }
        if (strTrim.contains("/")) {
            strTrim = strTrim.replace("/", " ");
        }
        return strTrim.split(" ");
    }

    public static int m(Resources resources) {
        InputStream inputStreamOpenRawResource = null;
        try {
            try {
                inputStreamOpenRawResource = resources.openRawResource(R.raw.function_items);
                String strE = b0.e(inputStreamOpenRawResource, "version");
                if (strE != null && !strE.trim().isEmpty()) {
                    int iO = o(strE.trim());
                    if (inputStreamOpenRawResource != null) {
                        try {
                            inputStreamOpenRawResource.close();
                        } catch (IOException e2) {
                            b.b.m.c.a.c("CarSettingDbManager", "--readRawFileAndInitDB--e: " + e2.toString());
                        }
                    }
                    return iO;
                }
                if (inputStreamOpenRawResource != null) {
                    try {
                        inputStreamOpenRawResource.close();
                    } catch (IOException e3) {
                        b.b.m.c.a.c("CarSettingDbManager", "--readRawFileAndInitDB--e: " + e3.toString());
                    }
                }
                return -1;
            } catch (Resources.NotFoundException e4) {
                b.b.m.c.a.c("CarSettingDbManager", "--readRawFileAndInitDB--e: " + e4.toString());
                if (inputStreamOpenRawResource != null) {
                    try {
                        inputStreamOpenRawResource.close();
                    } catch (IOException e5) {
                        b.b.m.c.a.c("CarSettingDbManager", "--readRawFileAndInitDB--e: " + e5.toString());
                    }
                }
                return -1;
            }
        } catch (Throwable th) {
            if (inputStreamOpenRawResource != null) {
                try {
                    inputStreamOpenRawResource.close();
                } catch (IOException e6) {
                    b.b.m.c.a.c("CarSettingDbManager", "--readRawFileAndInitDB--e: " + e6.toString());
                }
            }
            throw th;
        }
    }

    public static void n(Application application) {
        String str;
        if (application == null) {
            return;
        }
        b.b.h.a.g(application);
        b.b.g.g.c cVarA = c.b.a();
        cVarA.m();
        e.b.a().m();
        Context applicationContext = application.getApplicationContext();
        Resources resources = applicationContext.getResources();
        SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("carsettings_spf", 0);
        int i = sharedPreferences.getInt("version_function_items", -1);
        int iM = m(resources);
        b.b.m.c.a.g("CarSettingDbManager", "--init--version: " + i + ", fileVersion: " + iM);
        String string = sharedPreferences.getString("version_car_settings_app", "");
        String strC = g.c(applicationContext);
        b.b.m.c.a.d("CarSettingDbManager", "--init--appVer: " + strC + ", appVerSaved: " + string);
        boolean zEquals = string.equals(strC) ^ true;
        Locale localeV = d.g.a().v(applicationContext);
        String string2 = localeV.toString();
        int iO = cVarA.o(string2);
        b.b.m.c.a.g("CarSettingDbManager", "--init--langTag: " + string2 + ", dataSize: " + iO);
        if ((i > 0 && i < iM && iO > 0) || zEquals) {
            b.b.m.c.a.g("CarSettingDbManager", "--init--delete db data.");
            cVarA.c(null);
        }
        if (i < iM || iO <= 0 || zEquals) {
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putInt("version_function_items", iM);
            editorEdit.commit();
            if (!TextUtils.isEmpty(strC)) {
                editorEdit.putString("version_car_settings_app", strC);
                editorEdit.commit();
            }
            str = "--init--langTag: ";
            r(applicationContext, resources, d.g.a().c0(applicationContext), localeV.toString(), applicationContext.getPackageName(), cVarA);
        } else {
            str = "--init--langTag: ";
        }
        f fVarA = f.b.a();
        fVarA.m();
        int iQ = fVarA.q(string2);
        b.b.m.c.a.g("CarSettingDbManager", str + string2 + ", keywordDataSize: " + iQ);
        if (iQ > 0 && !zEquals) {
            return;
        }
        fVarA.c(null);
    }

    public static int o(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e2) {
            b.b.m.c.a.c("CarSettingDbManager", "--parseInt--e = " + e2.toString());
            e2.printStackTrace();
            return -1;
        }
    }

    public static String p(Resources resources, String str, String str2, List<String> list) {
        if (!str.contains(";")) {
            int identifier = resources.getIdentifier(str, "string", str2);
            if (identifier != 0) {
                return resources.getString(identifier);
            }
            b.b.m.c.a.c("CarSettingDbManager", "--parseXmlItemName--name id = " + identifier + ", name = " + str + ", index = -1");
            if (list == null) {
                return null;
            }
            list.add(str);
            return null;
        }
        b.b.m.c.a.g("CarSettingDbManager", "--parseXmlItemName--name id = 0, name = " + str + ", index = -1");
        StringBuilder sb = new StringBuilder();
        String[] strArrSplit = str.split(";");
        int length = strArrSplit.length;
        int identifier2 = 0;
        for (int i = 0; i < length; i++) {
            identifier2 = resources.getIdentifier(strArrSplit[i], "string", str2);
            if (identifier2 != 0) {
                sb.append(resources.getString(identifier2));
                sb.append(";");
            }
        }
        b.b.m.c.a.g("CarSettingDbManager", "--parseXmlItemName--name id = " + identifier2 + ", name = " + str + ", sb.toString() = " + sb.toString());
        return sb.toString();
    }

    public static synchronized void q(Resources resources, boolean z, String str, String str2, List<FunctionItem> list, List<XmlItem> list2, List<String> list3, List<Integer> list4, List<String> list5) {
        int i;
        boolean z2;
        Resources resources2 = resources;
        synchronized (b.class) {
            int i2 = 0;
            b.b.m.c.a.a("CarSettingDbManager", "--parseXmlModule--items.size = " + list2.size());
            for (XmlItem xmlItem : list2) {
                String str3 = xmlItem.get("name");
                int iO = o(xmlItem.get("index"));
                if (xmlItem.getList() != null) {
                    ArrayList arrayList = new ArrayList();
                    if (list3 != null) {
                        arrayList.addAll(list3);
                    }
                    arrayList.add(str3);
                    ArrayList arrayList2 = new ArrayList();
                    if (list4 != null) {
                        arrayList2.addAll(list4);
                    }
                    arrayList2.add(Integer.valueOf(iO));
                    q(resources, z, str, str2, list, xmlItem.getList(), arrayList, arrayList2, list5);
                    arrayList.clear();
                    arrayList2.clear();
                    i = i2;
                    z2 = true;
                } else {
                    int i3 = i2;
                    if (f785b == null) {
                        f785b = new StringBuilder();
                    }
                    f785b.setLength(i3);
                    if (list3 != null && !list3.isEmpty()) {
                        Iterator<String> it = list3.iterator();
                        while (it.hasNext()) {
                            int identifier = resources2.getIdentifier(it.next(), "string", str2);
                            if (identifier == 0) {
                                String[] strArr = new String[1];
                                strArr[i3] = "--parseXmlModule--address id = " + identifier + ", name = " + str3 + ", index = " + iO;
                                b.b.m.c.a.c("CarSettingDbManager", strArr);
                            } else {
                                String string = resources2.getString(identifier);
                                StringBuilder sb = f785b;
                                sb.append(string);
                                sb.append(">");
                            }
                        }
                        if (f785b.length() > 0) {
                            StringBuilder sb2 = f785b;
                            sb2.deleteCharAt(sb2.length() - 1);
                        }
                    }
                    String strP = p(resources2, str3, str2, list5);
                    if (strP == null) {
                        i2 = i3;
                    } else {
                        String string2 = f785b.toString();
                        FunctionItem functionItem = new FunctionItem(strP);
                        functionItem.setLangTag(str);
                        String strI = z ? i(string2, xmlItem.get("keywords")) : k(string2, strP);
                        functionItem.setKeywords(strI);
                        functionItem.setAddress(string2);
                        b.b.m.c.a.a("CarSettingDbManager", "--parseXmlModule--keywords---keywords = " + strI);
                        i = 0;
                        b.b.m.c.a.a("CarSettingDbManager", "--parseXmlModule---address: " + functionItem.getAddress() + ", itemName: " + strP);
                        z2 = true;
                        if (list4.size() > 1) {
                            functionItem.setIndex0(list4.get(0).intValue());
                            functionItem.setIndex1(list4.get(1).intValue());
                        }
                        functionItem.setIndex2(iO);
                        String str4 = xmlItem.get("subindex");
                        if (str4 != null && !str4.isEmpty()) {
                            functionItem.setIndex3(o(str4));
                        }
                        if (list != null) {
                            list.add(functionItem);
                        }
                    }
                }
                resources2 = resources;
                i2 = i;
            }
        }
    }

    public static void r(Context context, Resources resources, boolean z, String str, String str2, b.b.g.g.c cVar) throws Throwable {
        Throwable th;
        boolean z2;
        InputStream inputStreamOpenRawResource;
        List<XmlItem> listD;
        b.b.m.c.a.g("CarSettingDbManager", "--readRawFileAndInitDB---Doing DB init: " + f786c);
        if (f786c) {
            return;
        }
        f786c = true;
        InputStream inputStream = null;
        try {
            try {
                inputStreamOpenRawResource = resources.openRawResource(R.raw.function_items);
                try {
                    b.b.m.c.a.g("CarSettingDbManager", "--readRawFileAndInitDB---START---");
                    listD = b0.d(inputStreamOpenRawResource, "content", "module0", "module1", "item");
                    try {
                    } catch (Resources.NotFoundException e2) {
                        e = e2;
                        inputStream = inputStreamOpenRawResource;
                        b.b.m.c.a.c("CarSettingDbManager", "--readRawFileAndInitDB--e: " + e.toString());
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e3) {
                                z2 = false;
                                b.b.m.c.a.c("CarSettingDbManager", "--readRawFileAndInitDB--e = " + e3.toString());
                            }
                        }
                        f786c = z2;
                        return;
                    } catch (Throwable th2) {
                        th = th2;
                        th = th;
                        inputStream = inputStreamOpenRawResource;
                        if (inputStream == null) {
                            throw th;
                        }
                        try {
                            inputStream.close();
                            throw th;
                        } catch (IOException e4) {
                            b.b.m.c.a.c("CarSettingDbManager", "--readRawFileAndInitDB--e = " + e4.toString());
                            throw th;
                        }
                    }
                } catch (Resources.NotFoundException e5) {
                    e = e5;
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (Resources.NotFoundException e6) {
                e = e6;
            }
            if (listD != null && !listD.isEmpty()) {
                b.b.m.c.a.g("CarSettingDbManager", "--readRawFileAndInitDB--modules.size: " + listD.size());
                ArrayList arrayList = new ArrayList();
                u(context, str, 0);
                ArrayList arrayList2 = new ArrayList();
                q(resources, z, str, str2, arrayList2, listD, null, null, arrayList);
                if (cVar != null) {
                    cVar.k(arrayList2);
                }
                u(context, str, 1);
                b.b.m.c.a.g("CarSettingDbManager", "--readRawFileAndInitDB---END---resStrNotFoundList.size: " + arrayList.size());
                if (!arrayList.isEmpty()) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        b.b.m.c.a.c("CarSettingDbManager", "--readRawFileAndInitDB---resStrNotFound: " + ((String) it.next()));
                    }
                }
                if (inputStreamOpenRawResource != null) {
                    try {
                        inputStreamOpenRawResource.close();
                    } catch (IOException e7) {
                        b.b.m.c.a.c("CarSettingDbManager", "--readRawFileAndInitDB--e = " + e7.toString());
                    }
                }
                z2 = false;
                f786c = z2;
                return;
            }
            b.b.m.c.a.c("CarSettingDbManager", "--readRawFileAndInitDB--modules is " + listD);
            f786c = false;
            if (inputStreamOpenRawResource != null) {
                try {
                    inputStreamOpenRawResource.close();
                } catch (IOException e8) {
                    b.b.m.c.a.c("CarSettingDbManager", "--readRawFileAndInitDB--e = " + e8.toString());
                }
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    public static void s(SharedPreferences.Editor editor, String str, String str2, int i) {
        if (editor == null) {
            return;
        }
        String[] strArrSplit = str.split(ListUtils.DEFAULT_JOIN_SEPARATOR);
        String str3 = null;
        if (strArrSplit != null && strArrSplit.length > 0) {
            for (String str4 : strArrSplit) {
                if (str4.contains(str2)) {
                    str3 = str4;
                }
            }
        }
        String strJ = j(str2, i);
        b.b.m.c.a.d("CarSettingDbManager", "--replaceDbState--before replace--oldState: " + str3 + ", newState: " + strJ);
        String strReplace = str.replace(str3, strJ);
        StringBuilder sb = new StringBuilder();
        sb.append("--replaceDbState--after replace--newDbStateList: ");
        sb.append(strReplace);
        b.b.m.c.a.d("CarSettingDbManager", sb.toString());
        editor.putString("function_items_db_state_list", strReplace);
        editor.commit();
    }

    public static void t(SharedPreferences.Editor editor, String str, int i) {
        if (editor == null) {
            return;
        }
        String strJ = j(str, i);
        b.b.m.c.a.d("CarSettingDbManager", "--saveFirstDbState--firstState: " + strJ);
        editor.putString("function_items_db_state_list", strJ);
        editor.commit();
    }

    public static void u(Context context, String str, int i) {
        if (context == null) {
            return;
        }
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("carsettings_spf", 0);
        String string = sharedPreferences.getString("function_items_db_state_list", null);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        b.b.m.c.a.d("CarSettingDbManager", "--updateDbState--dbStateList: " + string);
        if (e(editorEdit, string, str, i)) {
            if (string.contains(str)) {
                s(editorEdit, string, str, i);
            } else {
                c(editorEdit, string, str, i);
            }
        }
    }
}
