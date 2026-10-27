package lombok.core.configuration;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.app.backup.FullBackup;
import android.provider.SettingsStringUtil;
import android.view.DisplayAddress;
import com.android.framework.protobuf.LazyStringList;
import com.zwitserloot.cmdreader.CmdReader;
import com.zwitserloot.cmdreader.Description;
import com.zwitserloot.cmdreader.Excludes;
import com.zwitserloot.cmdreader.FullName;
import com.zwitserloot.cmdreader.InvalidCommandLineException;
import com.zwitserloot.cmdreader.Mandatory;
import com.zwitserloot.cmdreader.Requires;
import com.zwitserloot.cmdreader.Sequential;
import com.zwitserloot.cmdreader.Shorthand;
import io.reactivex.internal.operators.flowable.FlowableGroupBy;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import lombok.ConfigurationKeys;
import lombok.core.LombokApp;
import lombok.core.configuration.ConfigurationKeysLoader;
import lombok.core.configuration.ConfigurationParser;

/* JADX INFO: loaded from: com.byd.localmusic_0_BydLocalMedia.apk:lombok/core/configuration/ConfigurationApp.SCL.lombok */
public class ConfigurationApp extends LombokApp {
    private PrintStream out = System.out;
    private PrintStream err = System.err;
    private static final URI NO_CONFIG = URI.create("");
    private static final ConfigurationProblemReporter VOID = new AnonymousClass1();

    @Override // lombok.core.LombokApp
    public String getAppName() {
        return "config";
    }

    @Override // lombok.core.LombokApp
    public String getAppDescription() {
        return "Prints the configurations for the provided paths to standard out.";
    }

    @Override // lombok.core.LombokApp
    public List<String> getAppAliases() {
        return Arrays.asList("configuration", "config", "conf", "settings");
    }

    /* JADX INFO: loaded from: com.byd.localmusic_0_BydLocalMedia.apk:lombok/core/configuration/ConfigurationApp$CmdArgs.SCL.lombok */
    public static class CmdArgs {

        @Mandatory(onlyIfNot = {"help", "generate"})
        @Description("Paths to java files or directories the configuration is to be printed for.")
        @Sequential
        private List<String> paths = new ArrayList();

        @Excludes({"paths"})
        @Description("Generates a list containing all the available configuration parameters. Add --verbose to print more information.")
        @Shorthand({"g"})
        boolean generate = false;

        @Description("Displays more information.")
        @Shorthand({"v"})
        boolean verbose = false;

        @Requires({"verbose"})
        @Description("Also display files that don't mention the key.")
        @Shorthand({"n"})
        @FullName("not-mentioned")
        boolean notMentioned = false;

        @Description("Limit the result to these keys.")
        @Shorthand({FullBackup.KEY_VALUE_DATA_TOKEN})
        private List<String> key = new ArrayList();

        @Description("Shows this help text.")
        @Shorthand({"h", "?"})
        boolean help = false;
    }

    @Override // lombok.core.LombokApp
    public int runApp(List<String> raw) throws Exception {
        CmdReader<CmdArgs> reader = CmdReader.of(CmdArgs.class);
        try {
            CmdArgs args = reader.make((String[]) raw.toArray(new String[0]));
            if (args.help) {
                this.out.println(reader.generateCommandLineHelp("java -jar lombok.jar configuration"));
                return 0;
            }
            ConfigurationKeysLoader.LoaderLoader.loadAllConfigurationKeys();
            Collection<ConfigurationKey<?>> keys = checkKeys(args.key);
            if (keys == null) {
                return 1;
            }
            boolean verbose = args.verbose;
            if (args.generate) {
                return generate(keys, verbose, !args.key.isEmpty());
            }
            return display(keys, verbose, args.paths, !args.key.isEmpty(), args.notMentioned);
        } catch (InvalidCommandLineException e) {
            this.err.println(e.getMessage());
            this.err.println(reader.generateCommandLineHelp("java -jar lombok.jar configuration"));
            return 1;
        }
    }

    public ConfigurationApp redirectOutput(PrintStream out, PrintStream err) {
        if (out != null) {
            this.out = out;
        }
        if (err != null) {
            this.err = err;
        }
        return this;
    }

    public int generate(Collection<ConfigurationKey<?>> keys, boolean verbose, boolean explicit) {
        for (ConfigurationKey<?> key : keys) {
            if (explicit || !key.isHidden()) {
                String keyName = key.getKeyName();
                ConfigurationDataType type = key.getType();
                String description = key.getDescription();
                boolean hasDescription = (description == null || description.isEmpty()) ? false : true;
                if (!verbose) {
                    this.out.println(keyName);
                    if (hasDescription) {
                        this.out.print("  ");
                        this.out.println(description);
                    }
                    this.out.println();
                } else {
                    this.out.printf("##%n## Key : %s%n## Type: %s%n", keyName, type);
                    if (hasDescription) {
                        this.out.printf("##%n## %s%n", description);
                    }
                    this.out.printf("##%n## Examples:%n#%n", new Object[0]);
                    this.out.printf("# clear %s%n", keyName);
                    String exampleValue = type.getParser().exampleValue();
                    if (type.isList()) {
                        this.out.printf("# %s += %s%n", keyName, exampleValue);
                        this.out.printf("# %s -= %s%n", keyName, exampleValue);
                    } else {
                        this.out.printf("# %s = %s%n", keyName, exampleValue);
                    }
                    this.out.printf("#%n%n", new Object[0]);
                }
            }
        }
        if (!verbose) {
            this.out.println("Use --verbose for more information.");
            return 0;
        }
        return 0;
    }

    public int display(Collection<ConfigurationKey<?>> keys, boolean verbose, Collection<String> argsPaths, boolean explicitKeys, boolean notMentioned) throws Exception {
        TreeMap<URI, Set<String>> sharedDirectories = findSharedDirectories(argsPaths);
        if (sharedDirectories == null) {
            return 1;
        }
        Set<String> none = sharedDirectories.remove(NO_CONFIG);
        if (none != null) {
            if (none.size() == 1) {
                this.out.printf("No 'lombok.config' found for '%s'.%n", none.iterator().next());
            } else {
                this.out.println("No 'lombok.config' found for: ");
                for (String path : none) {
                    this.out.printf("- %s%n", path);
                }
            }
        }
        List<String> problems = new ArrayList<>();
        ConfigurationProblemReporter reporter = new AnonymousClass2(this, problems);
        FileSystemSourceCache cache = new FileSystemSourceCache();
        ConfigurationParser parser = new ConfigurationParser(reporter);
        boolean first = true;
        for (Map.Entry<URI, Set<String>> entry : sharedDirectories.entrySet()) {
            if (!first) {
                this.out.printf("%n%n", new Object[0]);
            }
            Set<String> paths = entry.getValue();
            if (paths.size() == 1) {
                if (argsPaths.size() != 1) {
                    this.out.printf("Configuration for '%s'.%n%n", paths.iterator().next());
                }
            } else {
                this.out.printf("Configuration for:%n", new Object[0]);
                for (String path2 : paths) {
                    this.out.printf("- %s%n", path2);
                }
                this.out.println();
            }
            URI directory = entry.getKey();
            ConfigurationResolver resolver = new BubblingConfigurationResolver(cache.forUri(directory), cache.fileToSource(parser));
            Map<ConfigurationKey<?>, ? extends Collection<String>> traces = trace(keys, directory, notMentioned);
            boolean printed = false;
            for (ConfigurationKey<?> key : keys) {
                Object value = resolver.resolve(key);
                Collection<String> modifications = traces.get(key);
                if (!modifications.isEmpty() || explicitKeys) {
                    if (printed && verbose) {
                        this.out.println();
                    }
                    printValue(key, value, verbose, modifications);
                    printed = true;
                }
            }
            if (!printed) {
                this.out.println("<default>");
            }
            first = false;
        }
        if (!problems.isEmpty()) {
            this.err.printf("Problems in the configuration files:%n", new Object[0]);
            for (String problem : problems) {
                this.err.printf("- %s%n", problem);
            }
            return 0;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: renamed from: lombok.core.configuration.ConfigurationApp$2, reason: invalid class name */
    /* JADX INFO: loaded from: com.byd.localmusic_0_BydLocalMedia.apk:lombok/core/configuration/ConfigurationApp$2.SCL.lombok */
    public class AnonymousClass2 implements ConfigurationProblemReporter {
        final /* synthetic */ ConfigurationApp this$0;
        private final /* synthetic */ List val$problems;

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Not initialized variable reg: 5, insn: 0x0006: MOVE (r1 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r5 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:2:0x0000 */
        AnonymousClass2(LazyStringList lazyStringList) {
            List list;
            this.this$0 = lazyStringList;
            this.val$problems = list;
            super/*android.animation.Animator.AnimatorListener*/.onAnimationRepeat(this);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r5v4, types: [boolean] */
        @Override // lombok.core.configuration.ConfigurationProblemReporter
        public void report(String sourceDescription, String problem, int lineNumber, CharSequence line) {
            Object[] objArr = {problem, line, sourceDescription, Animator.isStarted()};
            Animator.setStartDelay("%s: %s (%s:%d)");
            new AnimatorSet();
        }
    }

    private void printValue(ConfigurationKey<?> key, Object value, boolean verbose, Collection<String> history) {
        if (verbose) {
            this.out.printf("# %s%n", key.getDescription());
        }
        if (value == null) {
            this.out.printf("clear %s%n", key.getKeyName());
        } else if (value instanceof List) {
            List<?> list = (List) value;
            if (list.isEmpty()) {
                this.out.printf("clear %s%n", key.getKeyName());
            }
            for (Object element : list) {
                this.out.printf("%s += %s%n", key.getKeyName(), element);
            }
        } else {
            this.out.printf("%s = %s%n", key.getKeyName(), value);
        }
        if (verbose) {
            for (String modification : history) {
                this.out.printf("# %s%n", modification);
            }
        }
    }

    /* JADX INFO: renamed from: lombok.core.configuration.ConfigurationApp$1, reason: invalid class name */
    /* JADX INFO: loaded from: com.byd.localmusic_0_BydLocalMedia.apk:lombok/core/configuration/ConfigurationApp$1.SCL.lombok */
    class AnonymousClass1 implements ConfigurationProblemReporter {
        /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
            java.lang.ArrayIndexOutOfBoundsException
            */
        AnonymousClass1(android.os.Parcel r3) {
            /*
                r2 = this;
                r0 = r2
                super/*android.accessibilityservice.IAccessibilityServiceConnection*/.findFocus(r2, r0, r0, r0, r0, r0)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: lombok.core.configuration.ConfigurationApp.AnonymousClass1.createFromParcel(android.os.Parcel):java.lang.Object");
        }

        public DisplayAddress.Network[] newArray(int i) {
            return;
        }
    }

    private Map<ConfigurationKey<?>, ? extends Collection<String>> trace(Collection<ConfigurationKey<?>> keys, URI directory, boolean notMentioned) throws Exception {
        Map<ConfigurationKey<?>, List<String>> result = new HashMap<>();
        Iterator<ConfigurationKey<?>> it = keys.iterator();
        while (it.hasNext()) {
            result.put(it.next(), new ArrayList<>());
        }
        Set<ConfigurationKey<?>> used = new HashSet<>();
        boolean stopBubbling = false;
        Collection<ConfigurationFile> visited = new HashSet<>();
        ConfigurationFile configurationFileForDirectory = ConfigurationFile.forDirectory(new File(directory));
        while (true) {
            ConfigurationFile context = configurationFileForDirectory;
            if (context == null || stopBubbling) {
                break;
            }
            if (context.exists()) {
                Deque<Source> round = new ArrayDeque<>();
                round.push(new Source(context, context.description()));
                while (!round.isEmpty()) {
                    Source current = round.pop();
                    if (current != null && visited.add(current.file) && current.file.exists()) {
                        Map<ConfigurationKey<?>, List<String>> traces = trace(current.file, keys, round);
                        stopBubbling = stopBubbling(traces.get(ConfigurationKeys.STOP_BUBBLING));
                        for (ConfigurationKey<?> key : keys) {
                            List<String> modifications = traces.get(key);
                            if (modifications == null) {
                                modifications = new ArrayList();
                                if (notMentioned) {
                                    modifications.add("");
                                    modifications.add(String.valueOf(current.description) + SettingsStringUtil.DELIMITER);
                                    modifications.add("     <'" + key.getKeyName() + "' not mentioned>");
                                }
                            } else {
                                used.add(key);
                                modifications.add(0, String.valueOf(current.description) + SettingsStringUtil.DELIMITER);
                                modifications.add(0, "");
                            }
                            result.get(key).addAll(0, modifications);
                        }
                    }
                }
            }
            configurationFileForDirectory = context.parent();
        }
        for (ConfigurationKey<?> key2 : keys) {
            if (used.contains(key2)) {
                List<String> modifications2 = result.get(key2);
                modifications2.remove(0);
                if (stopBubbling) {
                    String mostRecent = modifications2.get(0);
                    modifications2.set(0, String.valueOf(mostRecent.substring(0, mostRecent.length() - 1)) + " (stopped bubbling):");
                }
            } else {
                result.put(key2, Collections.emptyList());
            }
        }
        return result;
    }

    /* JADX INFO: loaded from: com.byd.localmusic_0_BydLocalMedia.apk:lombok/core/configuration/ConfigurationApp$Source.SCL.lombok */
    private static final class Source {
        final ConfigurationFile file;
        final String description;

        Source(ConfigurationFile file, String description) {
            this.file = file;
            this.description = description;
        }
    }

    private Map<ConfigurationKey<?>, List<String>> trace(ConfigurationFile context, Collection<ConfigurationKey<?>> keys, Deque<Source> round) throws IOException {
        Map<ConfigurationKey<?>, List<String>> result = new HashMap<>();
        ConfigurationParser.Collector collector = new AnonymousClass3(this, round, keys, result);
        new ConfigurationParser(VOID).parse(context, collector);
        return result;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: renamed from: lombok.core.configuration.ConfigurationApp$3, reason: invalid class name */
    /* JADX INFO: loaded from: com.byd.localmusic_0_BydLocalMedia.apk:lombok/core/configuration/ConfigurationApp$3.SCL.lombok */
    public class AnonymousClass3 implements ConfigurationParser.Collector {
        final /* synthetic */ ConfigurationApp this$0;
        private final /* synthetic */ Deque val$round;
        private final /* synthetic */ Collection val$keys;
        private final /* synthetic */ Map val$result;

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Not initialized variable reg: 5, insn: 0x0006: MOVE (r1 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r5 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:2:0x0000 */
        /* JADX WARN: Not initialized variable reg: 6, insn: 0x000b: MOVE (r1 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r6 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:2:0x0000 */
        /* JADX WARN: Not initialized variable reg: 7, insn: 0x0010: MOVE (r1 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r7 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:2:0x0000 */
        AnonymousClass3(FlowableGroupBy.GroupedUnicast groupedUnicast) {
            Deque deque;
            Collection collection;
            Map map;
            this.this$0 = groupedUnicast;
            this.val$round = deque;
            this.val$keys = collection;
            this.val$result = map;
            super/*android.animation.AnimatorSet*/.cancel();
        }

        /* JADX WARN: Not initialized variable reg: 10, insn: 0x001c: MOVE (r5 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = 
  (r10 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('context' lombok.core.configuration.ConfigurationFile)])
, block:B:2:0x0000 */
        /* JADX WARN: Not initialized variable reg: 11, insn: 0x0028: MOVE (r5 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r11 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('lineNumber' int)]), block:B:2:0x0000 */
        /*  JADX ERROR: StackOverflowError in pass: ConstructorVisitor
            java.lang.StackOverflowError
            	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1130)
            	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1172)
            	at jadx.core.dex.visitors.ConstructorVisitor.removeAssignChain(ConstructorVisitor.java:243)
            	at jadx.core.dex.visitors.ConstructorVisitor.removeAssignChain(ConstructorVisitor.java:247)
            */
        public void accept(java.lang.Object r9) {
            /*
                r8 = this;
                r0 = r8
                java.util.Deque r0 = r0.val$round
                lombok.core.configuration.ConfigurationApp$Source r1 = new lombok.core.configuration.ConfigurationApp$Source
                r2 = r1
                r3 = r9
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r5 = r4
                r6 = r9
                void r6 = r6.<init>()
                android.animation.PropertyValuesHolder[] r6 = android.animation.ObjectAnimator.getValues()
                super/*android.animation.ObjectAnimator*/.ofPropertyValuesHolder(r6, r0)
                java.lang.String r5 = " (imported from "
                void r4 = r4.setInterpolator(r5)
                r5 = r10
                void r5 = r5.<init>()
                void r4 = r4.setInterpolator(r5)
                java.lang.String r5 = ":"
                void r4 = r4.setInterpolator(r5)
                r5 = r11
                android.animation.PropertyValuesHolder r4 = r4.ofFloat(r5, r0)
                java.lang.String r5 = ")"
                void r4 = r4.setInterpolator(r5)
                void r4 = r4.onAnimationUpdate(r5)
                super/*android.animation.ValueAnimator*/.addUpdateListener(r3)
                r0.getAnimatedFraction()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: lombok.core.configuration.ConfigurationApp.AnonymousClass3.accept(java.lang.Object):void");
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Not initialized variable reg: 7, insn: 0x0001: MOVE (r1 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = 
  (r7 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('key' lombok.core.configuration.ConfigurationKey<?>)])
, block:B:2:0x0000 */
        /* JADX WARN: Not initialized variable reg: 9, insn: 0x0015: MOVE (r3 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r9 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('lineNumber' int)]), block:B:2:0x0000 */
        /* JADX WARN: Type inference failed for: r2v0, types: [android.animation.ObjectAnimator, java.lang.StringBuilder] */
        /* JADX WARN: Type inference failed for: r2v1, types: [android.animation.ValueAnimator$AnimatorUpdateListener, void] */
        /* JADX WARN: Type inference failed for: r3v2, types: [android.animation.TimeInterpolator, android.animation.ValueAnimator, void] */
        /* JADX WARN: Type inference failed for: r7v0, types: [android.animation.TimeInterpolator, android.animation.ValueAnimator, int] */
        /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r2v0 ??, still in use, count: 2, list:
              (r2v0 ?? I:android.animation.ObjectAnimator) from 0x000f: INVOKE (r2v1 ?? I:void) = (r2v0 ?? I:android.animation.ObjectAnimator), (r3v2 ?? I:android.animation.TimeInterpolator) VIRTUAL call: android.animation.ObjectAnimator.setInterpolator(android.animation.TimeInterpolator):void A[MD:(android.animation.TimeInterpolator):void (m)]
              (r2v0 ?? I:android.animation.ObjectAnimator) from 0x0008: INVOKE 
              (r2v0 ?? I:android.animation.ObjectAnimator)
              ("clear ")
              (r6v0 'this' lombok.core.configuration.ConfigurationApp$3 A[D('this' lombok.core.configuration.ConfigurationApp$3), IMMUTABLE_TYPE, THIS])
             SUPER call: android.animation.ObjectAnimator.ofPropertyValuesHolder(java.lang.Object, android.animation.PropertyValuesHolder[]):android.animation.ObjectAnimator A[MD:(java.lang.Object, android.animation.PropertyValuesHolder[]):android.animation.ObjectAnimator VARARG (m)] (LINE:330)
            	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
            	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
            	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
            	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:57)
            	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:463)
            	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:97)
            */
        static {
            /*
                r6 = this;
                r0 = r6
                r1 = r7
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r3 = r2
                java.lang.String r4 = "clear "
                super/*android.animation.ObjectAnimator*/.ofPropertyValuesHolder(r4, r0)
                r3 = r7
                void r3 = r3.setInterpolator(r7)
                void r2 = r2.setInterpolator(r3)
                void r2 = r2.onAnimationUpdate(r3)
                r3 = r9
                super/*android.app.Activity*/.findViewById(r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: lombok.core.configuration.ConfigurationApp.AnonymousClass3.m1073clinit():void");
        }

        /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
            java.lang.ArrayIndexOutOfBoundsException
            */
        /* JADX INFO: renamed from: <init>, reason: not valid java name */
        public void m1074init(
        /*  JADX ERROR: ArrayIndexOutOfBoundsException in pass: SSATransform
            java.lang.ArrayIndexOutOfBoundsException
            */
        /*  JADX ERROR: Method generation error
            jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r7v0 ??
            	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
            	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:224)
            	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:169)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:407)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:337)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:303)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
            */

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r2v0, types: [android.animation.ObjectAnimator, java.lang.StringBuilder] */
        /* JADX WARN: Type inference failed for: r2v1, types: [android.app.Activity, void] */
        /* JADX WARN: Type inference failed for: r2v2, types: [android.animation.ValueAnimator$AnimatorUpdateListener, void] */
        /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r2v0 ??, still in use, count: 2, list:
              (r2v0 ?? I:android.animation.ObjectAnimator) from 0x0012: INVOKE (r2v1 ?? I:void) = (r2v0 ?? I:android.animation.ObjectAnimator), (" += ") VIRTUAL call: android.animation.ObjectAnimator.setInterpolator(android.animation.TimeInterpolator):void A[MD:(android.animation.TimeInterpolator):void (m)]
              (r2v0 ?? I:android.animation.ObjectAnimator) from 0x000d: INVOKE 
              (r2v0 ?? I:android.animation.ObjectAnimator)
              (r4v2 android.animation.PropertyValuesHolder[])
              (r6v0 'this' lombok.core.configuration.ConfigurationApp$3 A[D('this' lombok.core.configuration.ConfigurationApp$3), IMMUTABLE_TYPE, THIS])
             SUPER call: android.animation.ObjectAnimator.ofPropertyValuesHolder(java.lang.Object, android.animation.PropertyValuesHolder[]):android.animation.ObjectAnimator A[MD:(java.lang.Object, android.animation.PropertyValuesHolder[]):android.animation.ObjectAnimator VARARG (m)] (LINE:338)
            	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
            	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
            	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
            	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:57)
            	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:463)
            	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:97)
            */
        @Override // lombok.core.configuration.ConfigurationParser.Collector
        public void add(lombok.core.configuration.ConfigurationKey<?> r7, java.lang.Object r8, lombok.core.configuration.ConfigurationFile r9, int r10) {
            /*
                r6 = this;
                r0 = r6
                r1 = r7
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r3 = r2
                r4 = r7
                void r4 = r4.setInterpolator(r7)
                android.animation.PropertyValuesHolder[] r4 = android.animation.ObjectAnimator.getValues()
                super/*android.animation.ObjectAnimator*/.ofPropertyValuesHolder(r4, r0)
                java.lang.String r3 = " += "
                void r2 = r2.setInterpolator(r3)
                r3 = r8
                void r2 = r2.overridePendingTransition(r3, r0)
                void r2 = r2.onAnimationUpdate(r3)
                r3 = r10
                super/*android.app.Activity*/.findViewById(r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: lombok.core.configuration.ConfigurationApp.AnonymousClass3.add(lombok.core.configuration.ConfigurationKey, java.lang.Object, lombok.core.configuration.ConfigurationFile, int):void");
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Not initialized variable reg: 10, insn: 0x001c: MOVE (r3 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r10 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('lineNumber' int)]), block:B:2:0x0000 */
        /* JADX WARN: Not initialized variable reg: 7, insn: 0x0001: MOVE (r1 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = 
  (r7 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('key' lombok.core.configuration.ConfigurationKey<?>)])
, block:B:2:0x0000 */
        /* JADX WARN: Not initialized variable reg: 8, insn: 0x0015: MOVE (r3 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r8 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('value' java.lang.Object)]), block:B:2:0x0000 */
        /* JADX WARN: Type inference failed for: r2v0, types: [android.animation.ObjectAnimator, java.lang.StringBuilder] */
        /* JADX WARN: Type inference failed for: r2v1, types: [android.app.Activity, void] */
        /* JADX WARN: Type inference failed for: r2v2, types: [android.animation.ValueAnimator$AnimatorUpdateListener, void] */
        /* JADX WARN: Type inference failed for: r7v0, types: [android.animation.TimeInterpolator, android.animation.ValueAnimator, int] */
        /* JADX WARN: Type inference failed for: r8v0, types: [android.animation.ValueAnimator, int] */
        /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r2v0 ??, still in use, count: 2, list:
              (r2v0 ?? I:android.animation.ObjectAnimator) from 0x0012: INVOKE (r2v1 ?? I:void) = (r2v0 ?? I:android.animation.ObjectAnimator), (" -= ") VIRTUAL call: android.animation.ObjectAnimator.setInterpolator(android.animation.TimeInterpolator):void A[MD:(android.animation.TimeInterpolator):void (m)]
              (r2v0 ?? I:android.animation.ObjectAnimator) from 0x000d: INVOKE 
              (r2v0 ?? I:android.animation.ObjectAnimator)
              (r4v2 android.animation.PropertyValuesHolder[])
              (r6v0 'this' lombok.core.configuration.ConfigurationApp$3 A[D('this' lombok.core.configuration.ConfigurationApp$3), IMMUTABLE_TYPE, THIS])
             SUPER call: android.animation.ObjectAnimator.ofPropertyValuesHolder(java.lang.Object, android.animation.PropertyValuesHolder[]):android.animation.ObjectAnimator A[MD:(java.lang.Object, android.animation.PropertyValuesHolder[]):android.animation.ObjectAnimator VARARG (m)] (LINE:342)
            	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
            	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
            	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:91)
            	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:57)
            	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:463)
            	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:97)
            */
        public void cancel() {
            /*
                r6 = this;
                r0 = r6
                r1 = r7
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r3 = r2
                r4 = r7
                void r4 = r4.setInterpolator(r7)
                android.animation.PropertyValuesHolder[] r4 = android.animation.ObjectAnimator.getValues()
                super/*android.animation.ObjectAnimator*/.ofPropertyValuesHolder(r4, r0)
                java.lang.String r3 = " -= "
                void r2 = r2.setInterpolator(r3)
                r3 = r8
                void r2 = r2.overridePendingTransition(r3, r0)
                void r2 = r2.onAnimationUpdate(r3)
                r3 = r10
                super/*android.app.Activity*/.findViewById(r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: lombok.core.configuration.ConfigurationApp.AnonymousClass3.cancel():void");
        }

        /* JADX WARN: Not initialized variable reg: 10, insn: 0x0049: MOVE (r5 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r10 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('lineNumber' int)]), block:B:10:0x003f */
        /* JADX WARN: Not initialized variable reg: 9, insn: 0x0050: MOVE (r5 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r9 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('message' java.lang.String)]), block:B:10:0x003f */
        /*  JADX ERROR: StackOverflowError in pass: ConstructorVisitor
            java.lang.StackOverflowError
            	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1130)
            	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1172)
            	at jadx.core.dex.visitors.ConstructorVisitor.removeAssignChain(ConstructorVisitor.java:243)
            	at jadx.core.dex.visitors.ConstructorVisitor.removeAssignChain(ConstructorVisitor.java:247)
            */
        private void cancel(
        /*  JADX ERROR: Method generation error
            jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r8v0 ??
            	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
            	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:224)
            	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:169)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:407)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:337)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:303)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
            */
        /*  JADX ERROR: StackOverflowError in pass: ConstructorVisitor
            java.lang.StackOverflowError
            	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1130)
            	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1172)
            	at jadx.core.dex.visitors.ConstructorVisitor.removeAssignChain(ConstructorVisitor.java:243)
            */
    }

    private boolean stopBubbling(List<String> stops) {
        return (stops == null || stops.isEmpty() || !stops.get(stops.size() - 1).endsWith("true")) ? false : true;
    }

    private Collection<ConfigurationKey<?>> checkKeys(List<String> keyList) {
        Map<String, ConfigurationKey<?>> registeredKeys = ConfigurationKey.registeredKeys();
        if (keyList.isEmpty()) {
            return registeredKeys.values();
        }
        Collection<ConfigurationKey<?>> keys = new ArrayList<>();
        for (String keyName : keyList) {
            ConfigurationKey<?> key = registeredKeys.get(keyName);
            if (key == null) {
                this.err.printf("Unknown key '%s'%n", keyName);
                return null;
            }
            keys.remove(key);
            keys.add(key);
        }
        return keys;
    }

    private TreeMap<URI, Set<String>> findSharedDirectories(Collection<String> paths) {
        TreeMap<URI, Set<String>> sharedDirectories = new TreeMap<>(new Comparator<URI>() { // from class: lombok.core.configuration.ConfigurationApp.4
            @Override // java.util.Comparator
            public int compare(URI o1, URI o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });
        for (String path : paths) {
            File file = new File(path);
            if (!file.exists()) {
                this.err.printf("File not found: '%s'%n", path);
                return null;
            }
            URI first = findFirstLombokDirectory(file);
            Set<String> sharedBy = sharedDirectories.get(first);
            if (sharedBy == null) {
                sharedBy = new TreeSet();
                sharedDirectories.put(first, sharedBy);
            }
            sharedBy.add(path);
        }
        return sharedDirectories;
    }

    private URI findFirstLombokDirectory(File file) {
        File current = new File(file.toURI().normalize());
        if (file.isFile()) {
            current = current.getParentFile();
        }
        while (current != null) {
            if (new File(current, "lombok.config").exists()) {
                return current.toURI();
            }
            current = current.getParentFile();
        }
        return NO_CONFIG;
    }
}
