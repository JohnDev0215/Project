package com.byd.ota.view.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import c.a.b.d.d;
import c.a.b.d.e;
import c.a.b.d.l;
import c.a.g.h.c;
import c.a.g.l.g;
import com.byd.ota.R;
import com.byd.ota.view.widget.TextProgressBar;
import com.byd.repository.bean.EcuVersionInfo;

/* JADX INFO: loaded from: classes.dex */
public class OtaDownloadTestFragment extends Fragment implements View.OnClickListener {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String f3113c = OtaDownloadTestFragment.class.getSimpleName();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static volatile boolean f3114d = false;
    public TextView E3;
    public TextView F3;
    public TextProgressBar G3;
    public Context H3;
    public c I3;
    public b J3;
    public int s;
    public Button x;
    public Button y;

    public class a implements c.a.g.h.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ EcuVersionInfo f3115a;

        public a(EcuVersionInfo ecuVersionInfo) {
            this.f3115a = ecuVersionInfo;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void f(EcuVersionInfo ecuVersionInfo, int i2, String str) {
            OtaDownloadTestFragment.this.F3.setText(OtaDownloadTestFragment.this.H3.getResources().getString(R.string.ota_tool_download_test_count) + ": " + OtaDownloadTestFragment.this.s + ", downloadFile->onDownloadError:ecuId = " + ecuVersionInfo.ecuId + ", code = " + i2 + ", msg = " + str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void h(long j, long j2) {
            OtaDownloadTestFragment.this.G3.c(j, j2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void j(long j) {
            OtaDownloadTestFragment.this.G3.setMax((int) j);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void l() {
            OtaDownloadTestFragment.this.k();
        }

        @Override // c.a.g.h.b
        public void a() {
            e.c(OtaDownloadTestFragment.f3113c, "downloadFile->onDownloadSuccess:ecuId = " + this.f3115a.ecuId);
            d.i(this.f3115a.fileDir + this.f3115a.fileName);
            if (OtaDownloadTestFragment.f3114d) {
                l.d().b(new Runnable() { // from class: c.a.d.i.d1.d
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f2547c.l();
                    }
                });
            }
        }

        @Override // c.a.g.h.b
        public void b(long j, final long j2) {
            e.c(OtaDownloadTestFragment.f3113c, "downloadFile->onDownloadStart:ecuId = " + this.f3115a.ecuId + ", progress = " + j + ", contentLength = " + j2);
            l.d().b(new Runnable() { // from class: c.a.d.i.d1.c
                @Override // java.lang.Runnable
                public final void run() {
                    this.f2545c.j(j2);
                }
            });
        }

        @Override // c.a.g.h.b
        public void c(final long j, final long j2) {
            l.d().b(new Runnable() { // from class: c.a.d.i.d1.e
                @Override // java.lang.Runnable
                public final void run() {
                    this.f2548c.h(j, j2);
                }
            });
        }

        @Override // c.a.g.h.b
        public void d(final int i2, final String str) {
            e.b(OtaDownloadTestFragment.f3113c, "downloadFile->onDownloadError:ecuId = " + this.f3115a.ecuId + ", code = " + i2 + ", msg = " + str);
            if (OtaDownloadTestFragment.f3114d) {
                boolean unused = OtaDownloadTestFragment.f3114d = false;
                d.i(this.f3115a.fileDir + this.f3115a.fileName);
                l lVarD = l.d();
                final EcuVersionInfo ecuVersionInfo = this.f3115a;
                lVarD.b(new Runnable() { // from class: c.a.d.i.d1.f
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f2550c.f(ecuVersionInfo, i2, str);
                    }
                });
            }
        }
    }

    public interface b {
    }

    public void i(Context context) {
        this.H3 = context;
    }

    public final void j() {
        c cVar = this.I3;
        if (cVar != null) {
            cVar.h();
            this.I3 = null;
        }
    }

    public final void k() {
        EcuVersionInfo ecuVersionInfo = new EcuVersionInfo();
        ecuVersionInfo.ecuId = "STEBU_Media_Autolink_00100001";
        ecuVersionInfo.encryptMd5 = "35e1527bb49b617774bf05df9077ec49";
        ecuVersionInfo.encryptSHA256 = "d685d513ccdd14b46af1459d5bd68619682eb6d96af60b55dd8f5c8fa6d1d394";
        ecuVersionInfo.fileDir = "/data/vendor/nfs/nfs_ota/";
        ecuVersionInfo.fileName = "download_test.zip";
        ecuVersionInfo.fileSize = "187261153";
        ecuVersionInfo.versionPath = "https://firmware-cdn-eu.byd.auto/obsota/obsota/2023/7/2c90863c895c79eb01896182cf4a43bc.zip.zip";
        this.s++;
        this.E3.setText(this.H3.getResources().getString(R.string.ota_tool_download_test_address) + ": " + ecuVersionInfo.versionPath);
        this.F3.setText(this.H3.getResources().getString(R.string.ota_tool_download_test_count) + ": " + this.s);
        j();
        this.I3 = new c(ecuVersionInfo, new a(ecuVersionInfo));
        c.a.g.g.a.l().k(this.I3, ecuVersionInfo.versionPath, g.a(ecuVersionInfo.fileDir, ecuVersionInfo.fileName));
    }

    @Override // android.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.J3 = (b) getActivity();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ota_download_test_start_btn /* 2131230996 */:
                if (!f3114d) {
                    f3114d = true;
                    this.s = 0;
                    k();
                }
                break;
            case R.id.ota_download_test_stop_btn /* 2131230997 */:
                f3114d = false;
                j();
                break;
        }
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R.layout.fragment_ota_download_test, (ViewGroup) null);
        this.x = (Button) viewInflate.findViewById(R.id.ota_download_test_start_btn);
        this.y = (Button) viewInflate.findViewById(R.id.ota_download_test_stop_btn);
        this.E3 = (TextView) viewInflate.findViewById(R.id.ota_download_test_address_tv);
        this.F3 = (TextView) viewInflate.findViewById(R.id.ota_download_test_result_tv);
        this.G3 = (TextProgressBar) viewInflate.findViewById(R.id.ota_download_test_progress_bar);
        this.x.setOnClickListener(this);
        this.y.setOnClickListener(this);
        return viewInflate;
    }

    @Override // android.app.Fragment
    public void onDetach() {
        super.onDetach();
        j();
        this.J3 = null;
    }
}
