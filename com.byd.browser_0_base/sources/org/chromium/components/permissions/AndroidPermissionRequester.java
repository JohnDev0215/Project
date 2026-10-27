package org.chromium.components.permissions;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import defpackage.C0175b;
import defpackage.bi1;
import defpackage.di1;
import defpackage.es3;
import defpackage.zi;
import defpackage.zj;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import org.chromium.base.BuildInfo;
import org.chromium.base.CollectionUtil;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.permissions.AndroidPermissionRequester;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.permissions.AndroidPermissionDelegate;
import org.chromium.ui.permissions.PermissionCallback;

/* JADX INFO: loaded from: classes3.dex */
public class AndroidPermissionRequester {

    public class a implements PermissionCallback {
        public final /* synthetic */ SparseArray e;
        public final /* synthetic */ WindowAndroid f;
        public final /* synthetic */ c g;
        public final /* synthetic */ int[] h;

        public a(SparseArray sparseArray, WindowAndroid windowAndroid, c cVar, int[] iArr) {
            this.e = sparseArray;
            this.f = windowAndroid;
            this.g = cVar;
            this.h = iArr;
        }

        @Override // org.chromium.ui.permissions.PermissionCallback
        public final void s(int[] iArr, String[] strArr) {
            int i;
            final WindowAndroid windowAndroid;
            int iKeyAt;
            HashSet hashSet = new HashSet();
            final int i2 = 1;
            boolean z = true;
            int i3 = 0;
            while (true) {
                int length = iArr.length;
                i = -1;
                windowAndroid = this.f;
                if (i3 >= length) {
                    break;
                }
                if (iArr[i3] == -1) {
                    String str = strArr[i3];
                    int i4 = 0;
                    while (true) {
                        SparseArray sparseArray = this.e;
                        if (i4 >= sparseArray.size()) {
                            iKeyAt = -1;
                            break;
                        } else {
                            if (((Set) sparseArray.valueAt(i4)).contains(str)) {
                                iKeyAt = sparseArray.keyAt(i4);
                                break;
                            }
                            i4++;
                        }
                    }
                    if (iKeyAt != -1) {
                        hashSet.add(Integer.valueOf(iKeyAt));
                        if (!windowAndroid.canRequestPermission(strArr[i3])) {
                            z = false;
                        }
                    }
                }
                i3++;
            }
            Context context = windowAndroid.i.get();
            final c cVar = this.g;
            if (!z || hashSet.isEmpty() || context == null) {
                if (hashSet.isEmpty()) {
                    cVar.g();
                    return;
                } else {
                    cVar.j();
                    return;
                }
            }
            if (hashSet.size() == 2 && hashSet.contains(8) && hashSet.contains(9)) {
                i = R$string.infobar_missing_microphone_camera_permissions_text;
            } else if (hashSet.size() == 1) {
                if (hashSet.contains(4)) {
                    i = R$string.infobar_missing_location_permission_text;
                } else if (hashSet.contains(8)) {
                    i = R$string.infobar_missing_microphone_permission_text;
                } else if (hashSet.contains(9)) {
                    i = R$string.infobar_missing_camera_permission_text;
                } else if (hashSet.contains(56)) {
                    i = R$string.infobar_missing_ar_camera_permission_text;
                } else if (hashSet.contains(5)) {
                    cVar.j();
                    return;
                }
            }
            String string = context.getString(i, BuildInfo.a.a.a);
            final int[] iArr2 = this.h;
            Consumer consumer = new Consumer() { // from class: yi
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    int i5 = i2;
                    Object obj2 = cVar;
                    Object obj3 = iArr2;
                    Object obj4 = windowAndroid;
                    switch (i5) {
                        case 0:
                            zi.a aVar = (zi.a) obj4;
                            xn1 xn1Var = (xn1) obj;
                            aVar.getClass();
                            xn1Var.a();
                            xn1Var.b();
                            aVar.getClass();
                            throw null;
                        default:
                            WindowAndroid windowAndroid2 = (WindowAndroid) obj4;
                            windowAndroid2.n().b(1, (PropertyModel) obj);
                            AndroidPermissionRequester.a(windowAndroid2, (int[]) obj3, (AndroidPermissionRequester.c) obj2);
                            return;
                    }
                }
            };
            Objects.requireNonNull(cVar);
            AndroidPermissionRequester.b(windowAndroid, string, consumer, new es3(22, cVar));
        }
    }

    public class b implements di1.a {
        public final /* synthetic */ Consumer e;
        public final /* synthetic */ Runnable f;

        public b(Consumer consumer, Runnable runnable) {
            this.e = consumer;
            this.f = runnable;
        }

        @Override // di1.a
        public final void b(int i) {
            if (i != 1) {
                this.f.run();
            }
        }

        @Override // di1.a
        public final void c(int i, PropertyModel propertyModel) {
            if (i == 0) {
                this.e.accept(propertyModel);
            }
        }
    }

    public interface c {
        void g();

        void j();
    }

    public static boolean a(WindowAndroid windowAndroid, int[] iArr, c cVar) {
        if (windowAndroid == null) {
            return false;
        }
        SparseArray sparseArray = new SparseArray();
        HashSet hashSet = new HashSet();
        for (int i : iArr) {
            if (!hasRequiredAndroidPermissionsForContentSetting(windowAndroid, i)) {
                HashSet hashSetE = CollectionUtil.e(PermissionUtil.getRequiredAndroidPermissionsForContentSetting(i));
                HashSet hashSetE2 = CollectionUtil.e(PermissionUtil.getOptionalAndroidPermissionsForContentSetting(i));
                sparseArray.append(i, hashSetE);
                hashSet.addAll(hashSetE);
                hashSet.addAll(hashSetE2);
            }
        }
        if (hashSet.isEmpty()) {
            return false;
        }
        windowAndroid.b((String[]) hashSet.toArray(new String[hashSet.size()]), new a(sparseArray, windowAndroid, cVar, iArr));
        return true;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, org.chromium.components.permissions.AndroidPermissionRequester$b] */
    /* JADX WARN: Type inference failed for: r3v4, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v2, types: [T, android.view.View] */
    public static void b(WindowAndroid windowAndroid, String str, Consumer<PropertyModel> consumer, Runnable runnable) {
        bi1 bi1VarN = windowAndroid.n();
        ?? bVar = new b(consumer, runnable);
        Context context = windowAndroid.i.get();
        ?? Inflate = LayoutInflater.from(context).inflate(R$layout.update_permissions_dialog, (ViewGroup) null);
        ((TextView) Inflate.findViewById(R$id.text)).setText(str);
        HashMap mapE = PropertyModel.e(di1.B);
        PropertyModel.r<View> rVar = di1.h;
        int i = zj.a;
        PropertyModel.g gVar = new PropertyModel.g();
        gVar.a = Inflate;
        mapE.put(rVar, gVar);
        PropertyModel.n nVar = di1.q;
        PropertyModel.a aVar = new PropertyModel.a();
        aVar.a = true;
        mapE.put(nVar, aVar);
        PropertyModel.r<String> rVar2 = di1.j;
        ?? string = context.getString(R$string.infobar_update_permissions_button_text);
        PropertyModel.g gVar2 = new PropertyModel.g();
        gVar2.a = string;
        mapE.put(rVar2, gVar2);
        PropertyModel.l<di1.a> lVar = di1.a;
        PropertyModel.g gVar3 = new PropertyModel.g();
        gVar3.a = bVar;
        bi1VarN.j(C0175b.p(mapE, lVar, gVar3, mapE), 1, false);
    }

    @CalledByNative
    public static boolean hasRequiredAndroidPermissionsForContentSetting(AndroidPermissionDelegate androidPermissionDelegate, int i) {
        String[] requiredAndroidPermissionsForContentSetting = PermissionUtil.getRequiredAndroidPermissionsForContentSetting(i);
        HashSet hashSet = new HashSet();
        for (String str : requiredAndroidPermissionsForContentSetting) {
            if (!androidPermissionDelegate.hasPermission(str)) {
                hashSet.add(str);
            }
        }
        for (String str2 : PermissionUtil.getOptionalAndroidPermissionsForContentSetting(i)) {
            androidPermissionDelegate.hasPermission(str2);
        }
        return hashSet.isEmpty();
    }
}
