package defpackage;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamite.DynamiteModule$DynamiteLoaderClassLoader;
import dalvik.system.DelegateLastClassLoader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: renamed from: kg, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1061kg {
    public static Boolean b = null;
    public static InterfaceC1337pg c = null;
    public static InterfaceC1392qg d = null;
    public static String e = null;
    public static int f = -1;
    public static final ThreadLocal g = new ThreadLocal();
    public static final InterfaceC0782fg h = new C1117lg();
    public static final InterfaceC0894hg i = new C1172mg();
    public static final InterfaceC0894hg j = new C1227ng();
    public final Context a;

    public C1061kg(Context context) {
        Cif.f(context);
        this.a = context;
    }

    public static int a(Context context, String str) {
        try {
            ClassLoader classLoader = context.getApplicationContext().getClassLoader();
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 61);
            sb.append("com.google.android.gms.dynamite.descriptors.");
            sb.append(str);
            sb.append(".ModuleDescriptor");
            Class<?> clsLoadClass = classLoader.loadClass(sb.toString());
            Field declaredField = clsLoadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = clsLoadClass.getDeclaredField("MODULE_VERSION");
            if (declaredField.get(null).equals(str)) {
                return declaredField2.getInt(null);
            }
            String strValueOf = String.valueOf(declaredField.get(null));
            StringBuilder sb2 = new StringBuilder(strValueOf.length() + 51 + String.valueOf(str).length());
            sb2.append("Module descriptor id '");
            sb2.append(strValueOf);
            sb2.append("' didn't match expected id '");
            sb2.append(str);
            sb2.append("'");
            Log.e("DynamiteModule", sb2.toString());
            return 0;
        } catch (ClassNotFoundException unused) {
            StringBuilder sb3 = new StringBuilder(String.valueOf(str).length() + 45);
            sb3.append("Local module descriptor class for ");
            sb3.append(str);
            sb3.append(" not found.");
            Log.w("DynamiteModule", sb3.toString());
            return 0;
        } catch (Exception e2) {
            String strValueOf2 = String.valueOf(e2.getMessage());
            Log.e("DynamiteModule", strValueOf2.length() != 0 ? "Failed to load module descriptor class: ".concat(strValueOf2) : new String("Failed to load module descriptor class: "));
            return 0;
        }
    }

    public static C1061kg c(Context context, InterfaceC0894hg interfaceC0894hg, String str) {
        C0949ig c0949ig = (C0949ig) g.get();
        C0949ig c0949ig2 = new C0949ig(null);
        g.set(c0949ig2);
        try {
            C0838gg c0838ggA = interfaceC0894hg.a(context, str, h);
            int i2 = c0838ggA.a;
            int i3 = c0838ggA.b;
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 68 + String.valueOf(str).length());
            sb.append("Considering local module ");
            sb.append(str);
            sb.append(":");
            sb.append(i2);
            sb.append(" and remote module ");
            sb.append(str);
            sb.append(":");
            sb.append(i3);
            Log.i("DynamiteModule", sb.toString());
            if (c0838ggA.c == 0 || ((c0838ggA.c == -1 && c0838ggA.a == 0) || (c0838ggA.c == 1 && c0838ggA.b == 0))) {
                int i4 = c0838ggA.a;
                int i5 = c0838ggA.b;
                StringBuilder sb2 = new StringBuilder(91);
                sb2.append("No acceptable module found. Local version is ");
                sb2.append(i4);
                sb2.append(" and remote version is ");
                sb2.append(i5);
                sb2.append(".");
                throw new C0726eg(sb2.toString(), null);
            }
            if (c0838ggA.c == -1) {
                C1061kg c1061kgF = f(context, str);
                Cursor cursor = c0949ig2.a;
                if (cursor != null) {
                    cursor.close();
                }
                g.set(c0949ig);
                return c1061kgF;
            }
            if (c0838ggA.c != 1) {
                int i6 = c0838ggA.c;
                StringBuilder sb3 = new StringBuilder(47);
                sb3.append("VersionPolicy returned invalid code:");
                sb3.append(i6);
                throw new C0726eg(sb3.toString(), null);
            }
            try {
                C1061kg c1061kgD = d(context, str, c0838ggA.b);
                Cursor cursor2 = c0949ig2.a;
                if (cursor2 != null) {
                    cursor2.close();
                }
                g.set(c0949ig);
                return c1061kgD;
            } catch (C0726eg e2) {
                String strValueOf = String.valueOf(e2.getMessage());
                Log.w("DynamiteModule", strValueOf.length() != 0 ? "Failed to load remote module: ".concat(strValueOf) : new String("Failed to load remote module: "));
                if (c0838ggA.a == 0 || interfaceC0894hg.a(context, str, new C1005jg(c0838ggA.a)).c != -1) {
                    throw new C0726eg("Remote load failed. No local fallback found.", e2, null);
                }
                C1061kg c1061kgF2 = f(context, str);
                Cursor cursor3 = c0949ig2.a;
                if (cursor3 != null) {
                    cursor3.close();
                }
                g.set(c0949ig);
                return c1061kgF2;
            }
        } catch (Throwable th) {
            Cursor cursor4 = c0949ig2.a;
            if (cursor4 != null) {
                cursor4.close();
            }
            g.set(c0949ig);
            throw th;
        }
    }

    public static C1061kg d(Context context, String str, int i2) throws C0726eg {
        Boolean bool;
        InterfaceC0559bg interfaceC0559bgI;
        try {
            synchronized (C1061kg.class) {
                bool = b;
            }
            if (bool == null) {
                throw new C0726eg("Failed to determine which loading route to use.", null);
            }
            if (bool.booleanValue()) {
                return g(context, str, i2);
            }
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 51);
            sb.append("Selected remote version of ");
            sb.append(str);
            sb.append(", version >= ");
            sb.append(i2);
            Log.i("DynamiteModule", sb.toString());
            InterfaceC1337pg interfaceC1337pgK = k(context);
            if (interfaceC1337pgK == null) {
                throw new C0726eg("Failed to create IDynamiteLoader.", null);
            }
            C1446rg c1446rg = (C1446rg) interfaceC1337pgK;
            Parcel parcelC = c1446rg.c(6, c1446rg.f());
            int i3 = parcelC.readInt();
            parcelC.recycle();
            if (i3 >= 2) {
                interfaceC0559bgI = ((C1446rg) interfaceC1337pgK).u(new BinderC0615cg(context), str, i2);
            } else {
                Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to createModuleContext");
                interfaceC0559bgI = ((C1446rg) interfaceC1337pgK).i(new BinderC0615cg(context), str, i2);
            }
            if (BinderC0615cg.w(interfaceC0559bgI) != null) {
                return new C1061kg((Context) BinderC0615cg.w(interfaceC0559bgI));
            }
            throw new C0726eg("Failed to load remote module.", null);
        } catch (RemoteException e2) {
            throw new C0726eg("Failed to load remote module.", e2, null);
        } catch (C0726eg e3) {
            throw e3;
        } catch (Throwable th) {
            try {
                Cif.f(context);
                Cif.f(th);
            } catch (Exception e4) {
                Log.e("CrashUtils", "Error adding exception to DropBox!", e4);
            }
            throw new C0726eg("Failed to load remote module.", th, null);
        }
    }

    public static void e(ClassLoader classLoader) throws C0726eg {
        InterfaceC1392qg c1501sg;
        try {
            IBinder iBinder = (IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0]);
            if (iBinder == null) {
                c1501sg = null;
            } else {
                IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                c1501sg = iInterfaceQueryLocalInterface instanceof InterfaceC1392qg ? (InterfaceC1392qg) iInterfaceQueryLocalInterface : new C1501sg(iBinder);
            }
            d = c1501sg;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e2) {
            throw new C0726eg("Failed to instantiate dynamite loader", e2, null);
        }
    }

    public static C1061kg f(Context context, String str) {
        String strValueOf = String.valueOf(str);
        Log.i("DynamiteModule", strValueOf.length() != 0 ? "Selected local version of ".concat(strValueOf) : new String("Selected local version of "));
        return new C1061kg(context.getApplicationContext());
    }

    public static C1061kg g(Context context, String str, int i2) throws C0726eg {
        InterfaceC1392qg interfaceC1392qg;
        Boolean boolValueOf;
        InterfaceC0559bg interfaceC0559bgV;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 51);
        sb.append("Selected remote version of ");
        sb.append(str);
        sb.append(", version >= ");
        sb.append(i2);
        Log.i("DynamiteModule", sb.toString());
        synchronized (C1061kg.class) {
            interfaceC1392qg = d;
        }
        if (interfaceC1392qg == null) {
            throw new C0726eg("DynamiteLoaderV2 was not cached.", null);
        }
        C0949ig c0949ig = (C0949ig) g.get();
        if (c0949ig == null || c0949ig.a == null) {
            throw new C0726eg("No result cursor", null);
        }
        Context applicationContext = context.getApplicationContext();
        Cursor cursor = c0949ig.a;
        new BinderC0615cg(null);
        synchronized (C1061kg.class) {
            boolValueOf = Boolean.valueOf(f >= 2);
        }
        if (boolValueOf.booleanValue()) {
            BinderC0615cg binderC0615cg = new BinderC0615cg(applicationContext);
            BinderC0615cg binderC0615cg2 = new BinderC0615cg(cursor);
            C1501sg c1501sg = (C1501sg) interfaceC1392qg;
            Parcel parcelF = c1501sg.f();
            AbstractC0383Vg.c(parcelF, binderC0615cg);
            parcelF.writeString(str);
            parcelF.writeInt(i2);
            AbstractC0383Vg.c(parcelF, binderC0615cg2);
            Parcel parcelC = c1501sg.c(3, parcelF);
            interfaceC0559bgV = AbstractBinderC0503ag.v(parcelC.readStrongBinder());
            parcelC.recycle();
        } else {
            Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to loadModule2");
            BinderC0615cg binderC0615cg3 = new BinderC0615cg(applicationContext);
            BinderC0615cg binderC0615cg4 = new BinderC0615cg(cursor);
            C1501sg c1501sg2 = (C1501sg) interfaceC1392qg;
            Parcel parcelF2 = c1501sg2.f();
            AbstractC0383Vg.c(parcelF2, binderC0615cg3);
            parcelF2.writeString(str);
            parcelF2.writeInt(i2);
            AbstractC0383Vg.c(parcelF2, binderC0615cg4);
            Parcel parcelC2 = c1501sg2.c(2, parcelF2);
            interfaceC0559bgV = AbstractBinderC0503ag.v(parcelC2.readStrongBinder());
            parcelC2.recycle();
        }
        Context context2 = (Context) BinderC0615cg.w(interfaceC0559bgV);
        if (context2 != null) {
            return new C1061kg(context2);
        }
        throw new C0726eg("Failed to get module context", null);
    }

    public static int h(Context context, String str, boolean z) {
        Field declaredField;
        Boolean bool = Boolean.TRUE;
        Boolean bool2 = Boolean.FALSE;
        try {
            synchronized (C1061kg.class) {
                Boolean bool3 = b;
                if (bool3 == null) {
                    try {
                        declaredField = context.getApplicationContext().getClassLoader().loadClass(DynamiteModule$DynamiteLoaderClassLoader.class.getName()).getDeclaredField("sClassLoader");
                    } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e2) {
                        String strValueOf = String.valueOf(e2);
                        StringBuilder sb = new StringBuilder(strValueOf.length() + 30);
                        sb.append("Failed to load module via V2: ");
                        sb.append(strValueOf);
                        Log.w("DynamiteModule", sb.toString());
                    }
                    synchronized (declaredField.getDeclaringClass()) {
                        ClassLoader classLoader = (ClassLoader) declaredField.get(null);
                        if (classLoader != null) {
                            if (classLoader != ClassLoader.getSystemClassLoader()) {
                                try {
                                    e(classLoader);
                                } catch (C0726eg unused) {
                                }
                            }
                            bool2 = bool;
                            b = bool2;
                            bool3 = bool2;
                        } else if ("com.google.android.gms".equals(context.getApplicationContext().getPackageName())) {
                            declaredField.set(null, ClassLoader.getSystemClassLoader());
                        } else {
                            try {
                                int iJ = j(context, str, z);
                                if (e != null && !e.isEmpty()) {
                                    ClassLoader delegateLastClassLoader = Build.VERSION.SDK_INT >= 29 ? new DelegateLastClassLoader(e, ClassLoader.getSystemClassLoader()) : new C1282og(e, ClassLoader.getSystemClassLoader());
                                    e(delegateLastClassLoader);
                                    declaredField.set(null, delegateLastClassLoader);
                                    b = bool;
                                    return iJ;
                                }
                                return iJ;
                            } catch (C0726eg unused2) {
                                declaredField.set(null, ClassLoader.getSystemClassLoader());
                            }
                        }
                        bool = bool2;
                        bool2 = bool;
                        b = bool2;
                        bool3 = bool2;
                    }
                }
                if (!bool3.booleanValue()) {
                    return i(context, str, z);
                }
                try {
                    return j(context, str, z);
                } catch (C0726eg e3) {
                    String strValueOf2 = String.valueOf(e3.getMessage());
                    Log.w("DynamiteModule", strValueOf2.length() != 0 ? "Failed to retrieve remote module version: ".concat(strValueOf2) : new String("Failed to retrieve remote module version: "));
                    return 0;
                }
            }
        } catch (Throwable th) {
            try {
                Cif.f(context);
                Cif.f(th);
            } catch (Exception e4) {
                Log.e("CrashUtils", "Error adding exception to DropBox!", e4);
            }
            throw th;
        }
    }

    public static int i(Context context, String str, boolean z) {
        InterfaceC1337pg interfaceC1337pgK = k(context);
        if (interfaceC1337pgK == null) {
            return 0;
        }
        try {
            C1446rg c1446rg = (C1446rg) interfaceC1337pgK;
            Parcel parcelC = c1446rg.c(6, c1446rg.f());
            int i2 = parcelC.readInt();
            parcelC.recycle();
            if (i2 >= 2) {
                BinderC0615cg binderC0615cg = new BinderC0615cg(context);
                C1446rg c1446rg2 = (C1446rg) interfaceC1337pgK;
                Parcel parcelF = c1446rg2.f();
                AbstractC0383Vg.c(parcelF, binderC0615cg);
                parcelF.writeString(str);
                parcelF.writeInt(z ? 1 : 0);
                Parcel parcelC2 = c1446rg2.c(5, parcelF);
                int i3 = parcelC2.readInt();
                parcelC2.recycle();
                return i3;
            }
            Log.w("DynamiteModule", "IDynamite loader version < 2, falling back to getModuleVersion2");
            BinderC0615cg binderC0615cg2 = new BinderC0615cg(context);
            C1446rg c1446rg3 = (C1446rg) interfaceC1337pgK;
            Parcel parcelF2 = c1446rg3.f();
            AbstractC0383Vg.c(parcelF2, binderC0615cg2);
            parcelF2.writeString(str);
            parcelF2.writeInt(z ? 1 : 0);
            Parcel parcelC3 = c1446rg3.c(3, parcelF2);
            int i4 = parcelC3.readInt();
            parcelC3.recycle();
            return i4;
        } catch (RemoteException e2) {
            String strValueOf = String.valueOf(e2.getMessage());
            Log.w("DynamiteModule", strValueOf.length() != 0 ? "Failed to retrieve remote module version: ".concat(strValueOf) : new String("Failed to retrieve remote module version: "));
            return 0;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00b0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int j(android.content.Context r8, java.lang.String r9, boolean r10) throws java.lang.Throwable {
        /*
            r0 = 0
            android.content.ContentResolver r1 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            if (r10 == 0) goto La
            java.lang.String r8 = "api_force_staging"
            goto Lc
        La:
            java.lang.String r8 = "api"
        Lc:
            int r10 = r8.length()     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            int r10 = r10 + 42
            java.lang.String r2 = java.lang.String.valueOf(r9)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            int r2 = r2.length()     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            int r10 = r10 + r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            r2.<init>(r10)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            java.lang.String r10 = "content://com.google.android.gms.chimera/"
            r2.append(r10)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            r2.append(r8)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            java.lang.String r8 = "/"
            r2.append(r8)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            r2.append(r9)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            java.lang.String r8 = r2.toString()     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            android.net.Uri r2 = android.net.Uri.parse(r8)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            android.database.Cursor r8 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            if (r8 == 0) goto L83
            boolean r9 = r8.moveToFirst()     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L96
            if (r9 == 0) goto L83
            r9 = 0
            int r9 = r8.getInt(r9)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L96
            if (r9 <= 0) goto L7c
            java.lang.Class<kg> r10 = defpackage.C1061kg.class
            monitor-enter(r10)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L96
            r1 = 2
            java.lang.String r1 = r8.getString(r1)     // Catch: java.lang.Throwable -> L79
            defpackage.C1061kg.e = r1     // Catch: java.lang.Throwable -> L79
            java.lang.String r1 = "loaderVersion"
            int r1 = r8.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L79
            if (r1 < 0) goto L67
            int r1 = r8.getInt(r1)     // Catch: java.lang.Throwable -> L79
            defpackage.C1061kg.f = r1     // Catch: java.lang.Throwable -> L79
        L67:
            monitor-exit(r10)     // Catch: java.lang.Throwable -> L79
            java.lang.ThreadLocal r10 = defpackage.C1061kg.g     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L96
            java.lang.Object r10 = r10.get()     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L96
            ig r10 = (defpackage.C0949ig) r10     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L96
            if (r10 == 0) goto L7c
            android.database.Cursor r1 = r10.a     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L96
            if (r1 != 0) goto L7c
            r10.a = r8     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L96
            goto L7d
        L79:
            r9 = move-exception
            monitor-exit(r10)     // Catch: java.lang.Throwable -> L79
            throw r9     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L96
        L7c:
            r0 = r8
        L7d:
            if (r0 == 0) goto L82
            r0.close()
        L82:
            return r9
        L83:
            java.lang.String r9 = "DynamiteModule"
            java.lang.String r10 = "Failed to retrieve remote module version."
            android.util.Log.w(r9, r10)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L96
            eg r9 = new eg     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L96
            java.lang.String r10 = "Failed to connect to dynamite module ContentResolver."
            r9.<init>(r10, r0)     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L96
            throw r9     // Catch: java.lang.Throwable -> L92 java.lang.Exception -> L96
        L92:
            r9 = move-exception
            r0 = r8
            r8 = r9
            goto Lae
        L96:
            r9 = move-exception
            r7 = r9
            r9 = r8
            r8 = r7
            goto L9f
        L9b:
            r8 = move-exception
            goto Lae
        L9d:
            r8 = move-exception
            r9 = r0
        L9f:
            boolean r10 = r8 instanceof defpackage.C0726eg     // Catch: java.lang.Throwable -> Lac
            if (r10 == 0) goto La4
            throw r8     // Catch: java.lang.Throwable -> Lac
        La4:
            eg r10 = new eg     // Catch: java.lang.Throwable -> Lac
            java.lang.String r1 = "V2 version check failed"
            r10.<init>(r1, r8, r0)     // Catch: java.lang.Throwable -> Lac
            throw r10     // Catch: java.lang.Throwable -> Lac
        Lac:
            r8 = move-exception
            r0 = r9
        Lae:
            if (r0 == 0) goto Lb3
            r0.close()
        Lb3:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C1061kg.j(android.content.Context, java.lang.String, boolean):int");
    }

    public static InterfaceC1337pg k(Context context) {
        InterfaceC1337pg c1446rg;
        synchronized (C1061kg.class) {
            if (c != null) {
                return c;
            }
            try {
                IBinder iBinder = (IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance();
                if (iBinder == null) {
                    c1446rg = null;
                } else {
                    IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                    c1446rg = iInterfaceQueryLocalInterface instanceof InterfaceC1337pg ? (InterfaceC1337pg) iInterfaceQueryLocalInterface : new C1446rg(iBinder);
                }
                if (c1446rg != null) {
                    c = c1446rg;
                    return c1446rg;
                }
            } catch (Exception e2) {
                String strValueOf = String.valueOf(e2.getMessage());
                Log.e("DynamiteModule", strValueOf.length() != 0 ? "Failed to load IDynamiteLoader from GmsCore: ".concat(strValueOf) : new String("Failed to load IDynamiteLoader from GmsCore: "));
            }
            return null;
        }
    }

    public final IBinder b(String str) throws C0726eg {
        try {
            return (IBinder) this.a.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e2) {
            throw new C0726eg(str.length() != 0 ? "Failed to instantiate module class: ".concat(str) : new String("Failed to instantiate module class: "), e2, null);
        }
    }
}
