package defpackage;

import android.net.Uri;
import android.webkit.PermissionRequest;
import java.util.ArrayList;
import org.chromium.android_webview.permission.AwPermissionRequest;

/* JADX INFO: renamed from: Nb, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0234Nb extends PermissionRequest {
    public AwPermissionRequest a;
    public final String[] b;

    public C0234Nb(AwPermissionRequest awPermissionRequest) {
        this.a = awPermissionRequest;
        long j = awPermissionRequest.b;
        ArrayList arrayList = new ArrayList();
        if ((2 & j) != 0) {
            arrayList.add("android.webkit.resource.VIDEO_CAPTURE");
        }
        if ((4 & j) != 0) {
            arrayList.add("android.webkit.resource.AUDIO_CAPTURE");
        }
        if ((8 & j) != 0) {
            arrayList.add("android.webkit.resource.PROTECTED_MEDIA_ID");
        }
        if ((j & 16) != 0) {
            arrayList.add("android.webkit.resource.MIDI_SYSEX");
        }
        this.b = (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    @Override // android.webkit.PermissionRequest
    public void deny() {
        this.a.a();
    }

    @Override // android.webkit.PermissionRequest
    public Uri getOrigin() {
        return this.a.a;
    }

    @Override // android.webkit.PermissionRequest
    public String[] getResources() {
        return (String[]) this.b.clone();
    }

    @Override // android.webkit.PermissionRequest
    public void grant(String[] strArr) {
        long j;
        long j2 = this.a.b;
        long j3 = 0;
        for (String str : strArr) {
            if (str.equals("android.webkit.resource.VIDEO_CAPTURE")) {
                j = 2;
            } else if (str.equals("android.webkit.resource.AUDIO_CAPTURE")) {
                j = 4;
            } else if (str.equals("android.webkit.resource.PROTECTED_MEDIA_ID")) {
                j = 8;
            } else if (str.equals("android.webkit.resource.MIDI_SYSEX")) {
                j = 16;
            }
            j3 |= j;
        }
        if ((j2 & j3) == j2) {
            this.a.b();
        } else {
            this.a.a();
        }
    }
}
