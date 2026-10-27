package defpackage;

import android.R;
import android.app.AlertDialog;
import android.app.job.JobInfo;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/* JADX INFO: renamed from: mr, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1183mr extends BaseExpandableListAdapter {
    public List a = new ArrayList();
    public final /* synthetic */ C1238nr b;

    public C1183mr(C1238nr c1238nr, TextView textView) {
        this.b = c1238nr;
        registerDataSetObserver(new C1072kr(this, c1238nr, textView));
    }

    public final void a(String str) {
        Context context = this.b.r0;
        File fileF = new Px(AbstractC0267Oq.b()).f(str);
        if (fileF == null) {
            Ts.a("CrashUploadUtil", Z7.c("Could not find a crash dump with local ID ", str), new Object[0]);
        } else {
            File file = null;
            if (fileF.getName().contains(".up")) {
                StringBuilder sbF = Z7.f("Refusing to reset upload attempt state for a file that has already been successfully uploaded: ");
                sbF.append(fileF.getName());
                Ts.f("CrashFileManager", sbF.toString(), new Object[0]);
            } else {
                String path = fileF.getPath();
                int iL = Px.l(path);
                if (iL > 0) {
                    path = path.replace(".try" + iL, ".try0");
                }
                File file2 = new File(path.replace(".skipped", ".forced").replace(".dmp", ".forced"));
                if (fileF.renameTo(file2)) {
                    file = file2;
                }
            }
            if (file == null) {
                StringBuilder sbF2 = Z7.f("Could not rename the file ");
                sbF2.append(fileF.getName());
                sbF2.append(" for re-upload");
                Ts.a("CrashUploadUtil", sbF2.toString(), new Object[0]);
            } else {
                Xx.a(new JobInfo.Builder(42, new ComponentName(context, "org.chromium.android_webview.services.AwMinidumpUploadJobService")));
            }
        }
        b();
    }

    public void b() {
        new C1128lr(this).d(Su.f);
    }

    @Override // android.widget.ExpandableListAdapter
    public Object getChild(int i, int i2) {
        return this.a.get(i);
    }

    @Override // android.widget.ExpandableListAdapter
    public long getChildId(int i, int i2) {
        return i2;
    }

    @Override // android.widget.ExpandableListAdapter
    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        EnumC0231Mq enumC0231Mq = EnumC0231Mq.UPLOADED;
        if (view == null) {
            view = this.b.m().inflate(AbstractC0425Xm.L, (ViewGroup) null);
        }
        final C0249Nq c0249Nq = (C0249Nq) this.a.get(i);
        int iOrdinal = c0249Nq.a.ordinal();
        String str = iOrdinal != 0 ? (iOrdinal == 1 || iOrdinal == 2) ? "Pending upload" : iOrdinal != 3 ? null : "Uploaded" : "Skipped upload";
        View viewFindViewById = view.findViewById(AbstractC0371Um.j5);
        if (c0249Nq.a == enumC0231Mq) {
            final String str2 = new Date(c0249Nq.g).toString() + "\nID: " + c0249Nq.f;
            viewFindViewById.setOnLongClickListener(new View.OnLongClickListener(this, str2) { // from class: fr
                public final C1183mr s;
                public final String t;

                {
                    this.s = this;
                    this.t = str2;
                }

                @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(View view2) {
                    C1183mr c1183mr = this.s;
                    ((ClipboardManager) c1183mr.b.r0.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("upload info", this.t));
                    C1602uP.a(c1183mr.b.r0, "Copied upload info", 0).a.show();
                    return true;
                }
            });
            C1238nr.g0(viewFindViewById, str, str2);
        } else {
            C1238nr.g0(viewFindViewById, str, null);
        }
        Button button = (Button) view.findViewById(AbstractC0371Um.v0);
        if (c0249Nq.a == enumC0231Mq) {
            button.setEnabled(true);
            button.setOnClickListener(new View.OnClickListener(this, c0249Nq) { // from class: gr
                public final C1183mr s;
                public final C0249Nq t;

                {
                    this.s = this;
                    this.t = c0249Nq;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    C1183mr c1183mr = this.s;
                    C0249Nq c0249Nq2 = this.t;
                    C1238nr c1238nr = c1183mr.b;
                    Objects.requireNonNull(c1238nr);
                    AlertDialog.Builder builder = new AlertDialog.Builder(c1238nr.r0);
                    builder.setMessage("This crash has already been reported to our crash system. Do you want to share more information, such as steps to reproduce the crash?");
                    builder.setPositiveButton("Provide more info", new DialogInterface.OnClickListener(c1238nr, c0249Nq2) { // from class: dr
                        public final C1238nr s;
                        public final C0249Nq t;

                        {
                            this.s = c1238nr;
                            this.t = c0249Nq2;
                        }

                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i3) {
                            String str3;
                            String str4;
                            C1238nr c1238nr2 = this.s;
                            C0249Nq c0249Nq3 = this.t;
                            Objects.requireNonNull(c1238nr2);
                            String str5 = "";
                            if (c0249Nq3.d != null) {
                                try {
                                    PackageInfo packageInfo = c1238nr2.r0.getPackageManager().getPackageInfo(c0249Nq3.d, 0);
                                    str3 = c0249Nq3.d;
                                    try {
                                        str5 = String.format(Locale.US, "%s/%s", packageInfo.versionName, Integer.valueOf(packageInfo.versionCode));
                                    } catch (PackageManager.NameNotFoundException unused) {
                                    }
                                } catch (PackageManager.NameNotFoundException unused2) {
                                    str3 = "";
                                }
                                str4 = str5;
                                str5 = str3;
                            } else {
                                str4 = "";
                            }
                            PackageInfo packageInfoA = AbstractC0232Mr.a(c1238nr2.r0);
                            c1238nr2.f0(new Intent("android.intent.action.VIEW", new Uri.Builder().scheme("https").authority("bugs.chromium.org").path("/p/chromium/issues/entry").appendQueryParameter("template", "Webview+Bugs").appendQueryParameter("description", String.format(Locale.US, "Build fingerprint: %s\nAndroid API level: %s\nWebView package: %s (%s/%s)\nApplication: %s\nApplication version: %s\nIf this app is available on Google Play, please include a URL:\n\n\nSteps to reproduce:\n(1)\n(2)\n(3)\n\n\nExpected result:\n(What should have happened?)\n\n\n<Any additional comments, you want to share>\n\n****DO NOT CHANGE BELOW THIS LINE****\nCrash ID: http://crash/%s\nInstructions for triaging this report (Chromium members only): https://bit.ly/2SM1Y9t\n", Build.FINGERPRINT, Integer.valueOf(Build.VERSION.SDK_INT), packageInfoA.packageName, packageInfoA.versionName, Integer.valueOf(packageInfoA.versionCode), str5, str4, c0249Nq3.f)).appendQueryParameter("labels", "User-Submitted,Via-WebView-DevTools,Pri-3,Type-Bug,OS-Android").build()));
                        }
                    });
                    builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() { // from class: er
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i3) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.create().show();
                }
            });
        } else {
            button.setEnabled(false);
        }
        Button button2 = (Button) view.findViewById(AbstractC0371Um.w0);
        EnumC0231Mq enumC0231Mq2 = c0249Nq.a;
        if (enumC0231Mq2 == EnumC0231Mq.SKIPPED || enumC0231Mq2 == EnumC0231Mq.PENDING) {
            button2.setVisibility(0);
            button2.setOnClickListener(new View.OnClickListener(this, c0249Nq) { // from class: hr
                public final C1183mr s;
                public final C0249Nq t;

                {
                    this.s = this;
                    this.t = c0249Nq;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    final C1183mr c1183mr = this.s;
                    final C0249Nq c0249Nq2 = this.t;
                    if (AbstractC0744ey.a((ConnectivityManager) c1183mr.b.r0.getApplicationContext().getSystemService("connectivity"))) {
                        c1183mr.a(c0249Nq2.b);
                    } else {
                        new AlertDialog.Builder(c1183mr.b.r0).setTitle("Network Warning").setMessage("You are connected to a metered network or cellular data. Do you want to proceed?").setPositiveButton("Upload", new DialogInterface.OnClickListener(c1183mr, c0249Nq2) { // from class: ir
                            public final C1183mr s;
                            public final C0249Nq t;

                            {
                                this.s = c1183mr;
                                this.t = c0249Nq2;
                            }

                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i3) {
                                C1183mr c1183mr2 = this.s;
                                C0249Nq c0249Nq3 = this.t;
                                Objects.requireNonNull(c1183mr2);
                                c1183mr2.a(c0249Nq3.b);
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() { // from class: jr
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i3) {
                                dialogInterface.dismiss();
                            }
                        }).create().show();
                    }
                }
            });
        } else {
            button2.setVisibility(8);
        }
        return view;
    }

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override // android.widget.ExpandableListAdapter
    public Object getGroup(int i) {
        return this.a.get(i);
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        return this.a.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public long getGroupId(int i) {
        return ((C0249Nq) this.a.get(i)).b.hashCode();
    }

    @Override // android.widget.ExpandableListAdapter
    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.b.m().inflate(AbstractC0425Xm.M, (ViewGroup) null);
        }
        C0249Nq c0249Nq = (C0249Nq) this.a.get(i);
        ImageView imageView = (ImageView) view.findViewById(AbstractC0371Um.u0);
        String str = c0249Nq.d;
        if (str == null) {
            imageView.setImageResource(R.drawable.sym_def_app_icon);
            str = "unknown app";
        } else {
            try {
                imageView.setImageDrawable(this.b.r0.getPackageManager().getApplicationIcon(str));
            } catch (PackageManager.NameNotFoundException unused) {
                imageView.setImageResource(R.drawable.sym_def_app_icon);
            }
        }
        C1238nr.g0(view.findViewById(AbstractC0371Um.t0), str, new Date(c0249Nq.c).toString());
        return view;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean hasStableIds() {
        return true;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean isChildSelectable(int i, int i2) {
        return true;
    }
}
