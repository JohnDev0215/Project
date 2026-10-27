package defpackage;

import J.N;
import android.content.Context;
import android.graphics.drawable.Animatable;
import android.os.SystemClock;
import android.text.TextUtils;
import defpackage.nj2;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.chrome.browser.autofill.settings.AutofillProfilesFragment;
import org.chromium.chrome.browser.crash.ChromePureJavaExceptionReporter;
import org.chromium.chrome.browser.omnibox.geo.f;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.settings.SettingsActivity;
import org.chromium.chrome.browser.video_tutorials.Tutorial;
import org.chromium.components.segmentation_platform.SegmentSelectionResult;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class fm3 implements Callback {
    public final /* synthetic */ int e;

    public /* synthetic */ fm3(int i) {
        this.e = i;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        switch (this.e) {
            case 0:
                HashSet hashSet = new HashSet();
                for (Tutorial tutorial : (List) obj) {
                    if (!TextUtils.isEmpty(tutorial.h)) {
                        hashSet.add(tutorial.h);
                    }
                }
                SharedPreferencesManager.getInstance().p("Chrome.VideoTutorials.ShareUrls", hashSet);
                break;
            case 1:
                qa qaVar = (qa) obj;
                int i = AutofillProfilesFragment.i0;
                if (qaVar != null) {
                    PersonalDataManager personalDataManagerC = PersonalDataManager.c();
                    PersonalDataManager.AutofillProfile autofillProfile = qaVar.l;
                    personalDataManagerC.getClass();
                    Object obj2 = ThreadUtils.a;
                    N.MgzFcfQz(personalDataManagerC.a, personalDataManagerC, autofillProfile);
                    nj2.a().getClass();
                    Iterator it = nj2.a.iterator();
                    while (it.hasNext()) {
                        PostTask.c(rh3.a, new lj2((nj2.a) it.next(), qaVar));
                    }
                }
                break;
            case 2:
                new ChromePureJavaExceptionReporter().a((Throwable) obj);
                break;
            case 3:
                ChromePureJavaExceptionReporter.d((Throwable) obj);
                break;
            case 4:
                Object obj3 = ThreadUtils.a;
                oo3.c = false;
                oo3.a = (f) obj;
                oo3.b = SystemClock.elapsedRealtime();
                break;
            case 5:
                SettingsActivity settingsActivity = SettingsActivity.P;
                n3.m1((Context) obj, 26);
                break;
            case 6:
                SegmentSelectionResult segmentSelectionResult = (SegmentSelectionResult) obj;
                long jLongValue = ((long) 28800) * 1000;
                if (segmentSelectionResult.a) {
                    jLongValue = segmentSelectionResult.b != 12 ? -1L : segmentSelectionResult.c.longValue() * 1000;
                }
                SharedPreferencesManager.getInstance().n(jLongValue, "Chrome.StartSurface.StartReturnTimeSegmentationResultMs");
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            default:
                int i2 = v9.g;
                ((Animatable) obj).stop();
                break;
        }
    }
}
