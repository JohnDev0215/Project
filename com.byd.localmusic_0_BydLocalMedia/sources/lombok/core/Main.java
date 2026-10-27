package lombok.core;

import com.android.internal.content.NativeLibraryHelper;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: com.byd.localmusic_0_BydLocalMedia.apk:lombok/core/Main.SCL.lombok */
public class Main {
    private static final Collection<?> HELP_SWITCHES = Collections.unmodifiableList(Arrays.asList("/?", "/h", "/help", "-h", "-help", "--help", "help", "h"));
    private final List<LombokApp> apps;
    private final List<String> args;

    public static void main(String[] args) throws IOException {
        Thread.currentThread().setContextClassLoader(Main.class.getClassLoader());
        int err = new Main(SpiLoadUtil.readAllFromIterator(SpiLoadUtil.findServices(LombokApp.class)), Arrays.asList(args)).go();
        if (err != 0) {
            System.exit(err);
        }
    }

    /* JADX INFO: loaded from: com.byd.localmusic_0_BydLocalMedia.apk:lombok/core/Main$VersionApp.SCL.lombok */
    public static class VersionApp extends LombokApp {
        @Override // lombok.core.LombokApp
        public String getAppName() {
            return "version";
        }

        @Override // lombok.core.LombokApp
        public String getAppDescription() {
            return "prints lombok's version.";
        }

        @Override // lombok.core.LombokApp
        public List<String> getAppAliases() {
            return Arrays.asList("-version", "--version");
        }

        @Override // lombok.core.LombokApp
        public int runApp(List<String> args) {
            System.out.println(Version.getFullVersion());
            return 0;
        }
    }

    /* JADX INFO: loaded from: com.byd.localmusic_0_BydLocalMedia.apk:lombok/core/Main$LicenseApp.SCL.lombok */
    public static class LicenseApp extends LombokApp {
        /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
            java.lang.ArrayIndexOutOfBoundsException
            */
        public LicenseApp(io.reactivex.internal.operators.flowable.FlowableGroupBy.GroupedUnicast r3) {
            /*
                r2 = this;
                r0 = r2
                super/*android.animation.Animator*/.removeAllListeners()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: lombok.core.Main.LicenseApp.accept(io.reactivex.internal.operators.flowable.FlowableGroupBy$GroupedUnicast):void");
        }

        /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
            java.lang.ArrayIndexOutOfBoundsException
            */
        public void accept(java.lang.Object r3) {
            /*
                r2 = this;
                java.lang.String r0 = "license"
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: lombok.core.Main.LicenseApp.accept(java.lang.Object):void");
        }

        static {
        }

        /* JADX WARN: Incorrect return type in method signature: ()Ljava/util/List<Ljava/lang/String;>; */
        /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
            java.lang.ArrayIndexOutOfBoundsException
            */
        /* JADX INFO: renamed from: <init>, reason: not valid java name */
        public void m1086init(org.reactivestreams.Subscriber r6, io.reactivex.functions.Function r7, io.reactivex.functions.Function r8, int r9, boolean r10, java.util.Map r11, java.util.Queue r12) {
            /*
                r5 = this;
                r0 = 4
                java.lang.String[] r0 = new java.lang.String[r0]
                r1 = r0
                r2 = 0
                java.lang.String r3 = "licence"
                r1[r2] = r3
                r1 = r0
                r2 = 1
                java.lang.String r3 = "copyright"
                r1[r2] = r3
                r1 = r0
                r2 = 2
                java.lang.String r3 = "copyleft"
                r1[r2] = r3
                r1 = r0
                r2 = 3
                java.lang.String r3 = "gpl"
                r1[r2] = r3
                void r0 = android.animation.AnimatorSet.setInterpolator(r0)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: lombok.core.Main.LicenseApp.m1086init(org.reactivestreams.Subscriber, io.reactivex.functions.Function, io.reactivex.functions.Function, int, boolean, java.util.Map, java.util.Queue):void");
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [android.animation.ValueAnimator, java.io.PrintStream] */
        /* JADX WARN: Type inference failed for: r0v10, types: [void] */
        /* JADX WARN: Type inference failed for: r0v12, types: [android.animation.ValueAnimator, java.io.PrintStream] */
        /* JADX WARN: Type inference failed for: r0v3, types: [android.animation.ObjectAnimator, android.animation.ValueAnimator, long] */
        /* JADX WARN: Type inference failed for: r0v6, types: [android.animation.ObjectAnimator, android.animation.StateListAnimator, android.animation.TimeInterpolator, android.animation.ValueAnimator] */
        /* JADX WARN: Type inference failed for: r0v8, types: [byte[], int] */
        /* JADX WARN: Type inference failed for: r1v5, types: [android.animation.ValueAnimator, java.lang.String] */
        /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r1v5 ??, still in use, count: 1, list:
              (r1v5 ?? I:android.animation.ValueAnimator) from 0x003e: INVOKE (r1v5 ?? I:android.animation.ValueAnimator) SUPER call: android.animation.ValueAnimator.getRepeatMode():int A[Catch: all -> 0x004a, Exception -> 0x0053, MD:():int (m)]
            	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
            	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
            	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
            	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:57)
            	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:463)
            	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:97)
            */
        @Override // lombok.core.LombokApp
        public int runApp(java.util.List<java.lang.String> r6) {
            /*
                r5 = this;
                java.lang.Class<lombok.core.Main> r0 = lombok.core.Main.class
                java.lang.String r1 = "/LICENSE"
                android.animation.ObjectAnimator r0 = r0.ofFloat(r1, r0, r0)     // Catch: java.lang.Exception -> L53
                r7 = r0
                java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L53
                r1 = r0
                super/*android.animation.ObjectAnimator*/.setInterpolator(r0)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L53
                r8 = r0
                r0 = 65536(0x10000, float:9.1835E-41)
                byte[] r0 = new byte[r0]     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L53
                r9 = r0
            L16:
                r0 = r7
                r1 = r9
                void r0 = r0.setRepeatCount(r1)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L53
                r10 = r0
                r0 = r10
                r1 = -1
                if (r0 != r1) goto L27
                goto L33
            L27:
                r0 = r8
                r1 = r9
                r2 = 0
                r3 = r10
                r0.<init>()     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L53
                goto L16
            L33:
                java.io.PrintStream r0 = java.lang.System.out     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L53
                java.lang.String r1 = new java.lang.String     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L53
                r2 = r1
                r3 = r8
                java.lang.Object r3 = r3.getAnimatedValue()     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L53
                super/*android.animation.ValueAnimator*/.getRepeatMode()     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L53
                r0.isRunning()     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L53
                r0 = r7
                r0.setDuration(r7)     // Catch: java.lang.Exception -> L53
                r0 = 0
                return r0
            L4a:
                r11 = move-exception
                r0 = r7
                r0.setDuration(r7)     // Catch: java.lang.Exception -> L53
                r0 = r11
                throw r0     // Catch: java.lang.Exception -> L53
            L53:
                java.io.PrintStream r0 = java.lang.System.err
                java.lang.String r1 = "License file not found. Check https://projectlombok.org/LICENSE"
                r0.isRunning()
                r0 = 1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: lombok.core.Main.LicenseApp.runApp(java.util.List):int");
        }
    }

    public Main(List<LombokApp> apps, List<String> args) {
        this.apps = apps;
        this.args = args;
    }

    public int go() {
        if (!this.args.isEmpty() && HELP_SWITCHES.contains(this.args.get(0))) {
            printHelp(null, System.out);
            return 0;
        }
        String command = this.args.isEmpty() ? "" : this.args.get(0).trim();
        if (command.startsWith("--")) {
            command = command.substring(2);
        } else if (command.startsWith(NativeLibraryHelper.CLEAR_ABI_OVERRIDE)) {
            command = command.substring(1);
        }
        List<String> subArgs = this.args.isEmpty() ? Collections.emptyList() : Collections.unmodifiableList(this.args.subList(1, this.args.size()));
        for (LombokApp app : this.apps) {
            if (app.getAppName().equals(command) || app.getAppAliases().contains(command)) {
                try {
                    return app.runApp(subArgs);
                } catch (Exception e) {
                    e.printStackTrace();
                    return 5;
                }
            }
        }
        printHelp("Unknown command: " + command, System.err);
        return 1;
    }

    public void printHelp(String message, PrintStream out) {
        if (message != null) {
            out.println(message);
            out.println("------------------------------");
        }
        out.println("projectlombok.org " + Version.getFullVersion());
        out.println("Copyright (C) 2009-2021 The Project Lombok Authors.");
        out.println("Run 'lombok license' to see the lombok license agreement.");
        out.println();
        out.println("Run lombok without any parameters to start the graphical installer.");
        out.println("Other available commands:");
        for (LombokApp app : this.apps) {
            if (!app.isDebugTool()) {
                String[] desc = app.getAppDescription().split(BydAdbHelper.COMMAND_LINE_END);
                int i = 0;
                while (i < desc.length) {
                    Object[] objArr = new Object[2];
                    objArr[0] = i == 0 ? app.getAppName() : "";
                    objArr[1] = desc[i];
                    out.printf("  %15s    %s\n", objArr);
                    i++;
                }
            }
        }
        out.println();
        out.println("Run lombok commandName --help for more info on each command.");
    }
}
