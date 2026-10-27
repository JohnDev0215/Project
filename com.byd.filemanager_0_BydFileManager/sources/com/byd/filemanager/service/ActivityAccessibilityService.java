package com.byd.filemanager.service;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import com.byd.music.f.g;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.greenrobot.eventbus.c;

/* JADX INFO: loaded from: classes.dex */
public class ActivityAccessibilityService extends AccessibilityService {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final List<a> f3643b;

    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        String f3644a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f3645b;

        public a(String str, String str2) {
            this.f3644a = str;
            this.f3645b = str2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || a.class != obj.getClass()) {
                return false;
            }
            a aVar = (a) obj;
            return Objects.equals(this.f3644a, aVar.f3644a) && Objects.equals(this.f3645b, aVar.f3645b);
        }

        public int hashCode() {
            return Objects.hash(this.f3644a, this.f3645b);
        }
    }

    static {
        ArrayList arrayList = new ArrayList();
        f3643b = arrayList;
        arrayList.add(new a("com.byd.auto_photo", "com.byd.auto_photo.MainActivity"));
        arrayList.add(new a("com.byd.localmusic", "com.byd.music.activity.main.MediaMainActivity"));
    }

    @Override // android.accessibilityservice.AccessibilityService
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() == 32) {
            CharSequence packageName = accessibilityEvent.getPackageName();
            CharSequence className = accessibilityEvent.getClassName();
            if (packageName == null || className == null) {
                return;
            }
            com.byd.filemanager.k.b.f("pkgName:%s className:%s", packageName.toString(), className.toString());
            a aVar = new a(packageName.toString(), className.toString());
            Iterator<a> it = f3643b.iterator();
            while (it.hasNext()) {
                if (it.next().equals(aVar)) {
                    c.c().k(new g());
                    return;
                }
            }
        }
    }

    @Override // android.accessibilityservice.AccessibilityService
    public void onInterrupt() {
    }

    @Override // android.accessibilityservice.AccessibilityService
    protected void onServiceConnected() {
        super.onServiceConnected();
        com.byd.filemanager.k.b.b("onServiceConnected");
    }
}
