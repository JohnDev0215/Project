package com.android.internal.content.om;

import android.content.pm.PackagePartitions;
import android.os.FileUtils;
import android.util.ArraySet;
import android.util.Log;
import android.util.Xml;
import com.android.internal.content.om.OverlayScanner;
import com.android.internal.util.XmlUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import libcore.io.IoUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes4.dex */
final class OverlayConfigParser {
    private static final String CONFIG_DEFAULT_FILENAME = "config/config.xml";
    private static final String CONFIG_DIRECTORY = "config";
    static final boolean DEFAULT_ENABLED_STATE = false;
    static final boolean DEFAULT_MUTABILITY = true;
    private static final int MAXIMUM_MERGE_DEPTH = 5;

    OverlayConfigParser() {
    }

    public static class ParsedConfiguration {
        public final boolean enabled;
        public final boolean mutable;
        public final String packageName;
        public final OverlayScanner.ParsedOverlayInfo parsedInfo;
        public final String policy;

        ParsedConfiguration(String str, boolean z, boolean z2, String str2, OverlayScanner.ParsedOverlayInfo parsedOverlayInfo) {
            this.packageName = str;
            this.enabled = z;
            this.mutable = z2;
            this.policy = str2;
            this.parsedInfo = parsedOverlayInfo;
        }

        public String toString() {
            return getClass().getSimpleName() + String.format("{packageName=%s, enabled=%s, mutable=%s, policy=%s, parsedInfo=%s}", this.packageName, Boolean.valueOf(this.enabled), Boolean.valueOf(this.mutable), this.policy, this.parsedInfo);
        }
    }

    static class OverlayPartition extends PackagePartitions.SystemPartition {
        static final String POLICY_ODM = "odm";
        static final String POLICY_OEM = "oem";
        static final String POLICY_PRODUCT = "product";
        static final String POLICY_PUBLIC = "public";
        static final String POLICY_SYSTEM = "system";
        static final String POLICY_VENDOR = "vendor";
        public final String policy;

        OverlayPartition(PackagePartitions.SystemPartition systemPartition) {
            super(systemPartition);
            this.policy = policyForPartition(systemPartition);
        }

        OverlayPartition(File file, PackagePartitions.SystemPartition systemPartition) {
            super(file, systemPartition);
            this.policy = policyForPartition(systemPartition);
        }

        private static String policyForPartition(PackagePartitions.SystemPartition systemPartition) {
            int i = systemPartition.type;
            if (i == 0) {
                return "system";
            }
            if (i == 1) {
                return "vendor";
            }
            if (i == 2) {
                return POLICY_ODM;
            }
            if (i == 3) {
                return POLICY_OEM;
            }
            if (i == 4) {
                return "product";
            }
            if (i == 5) {
                return "system";
            }
            throw new IllegalStateException("Unable to determine policy for " + systemPartition.getFolder());
        }
    }

    private static class ParsingContext {
        private final ArraySet<String> mConfiguredOverlays;
        private boolean mFoundMutableOverlay;
        private int mMergeDepth;
        private final ArrayList<ParsedConfiguration> mOrderedConfigurations;
        private final OverlayPartition mPartition;

        static /* synthetic */ int access$208(ParsingContext parsingContext) {
            int i = parsingContext.mMergeDepth;
            parsingContext.mMergeDepth = i + 1;
            return i;
        }

        static /* synthetic */ int access$210(ParsingContext parsingContext) {
            int i = parsingContext.mMergeDepth;
            parsingContext.mMergeDepth = i - 1;
            return i;
        }

        private ParsingContext(OverlayPartition overlayPartition) {
            this.mOrderedConfigurations = new ArrayList<>();
            this.mConfiguredOverlays = new ArraySet<>();
            this.mPartition = overlayPartition;
        }
    }

    static ArrayList<ParsedConfiguration> getConfigurations(OverlayPartition overlayPartition, OverlayScanner overlayScanner) {
        if (overlayPartition.getOverlayFolder() == null) {
            return null;
        }
        if (overlayScanner != null) {
            overlayScanner.scanDir(overlayPartition.getOverlayFolder());
        }
        File file = new File(overlayPartition.getOverlayFolder(), CONFIG_DEFAULT_FILENAME);
        if (!file.exists()) {
            return null;
        }
        ParsingContext parsingContext = new ParsingContext(overlayPartition);
        readConfigFile(file, overlayScanner, parsingContext);
        return parsingContext.mOrderedConfigurations;
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x007a: INVOKE (r1 I:java.lang.AutoCloseable) STATIC call: libcore.io.IoUtils.closeQuietly(java.lang.AutoCloseable):void, block:B:32:0x007a */
    private static void readConfigFile(File file, OverlayScanner overlayScanner, ParsingContext parsingContext) {
        AutoCloseable autoCloseableCloseQuietly;
        try {
            try {
                FileReader fileReader = new FileReader(file);
                try {
                    XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
                    xmlPullParserNewPullParser.setInput(fileReader);
                    XmlUtils.beginDocument(xmlPullParserNewPullParser, CONFIG_DIRECTORY);
                    int depth = xmlPullParserNewPullParser.getDepth();
                    while (XmlUtils.nextElementWithin(xmlPullParserNewPullParser, depth)) {
                        String name = xmlPullParserNewPullParser.getName();
                        byte b = -1;
                        int iHashCode = name.hashCode();
                        if (iHashCode != -1091287984) {
                            if (iHashCode == 103785528 && name.equals("merge")) {
                                b = 0;
                            }
                        } else if (name.equals("overlay")) {
                            b = 1;
                        }
                        if (b == 0) {
                            parseMerge(file, xmlPullParserNewPullParser, overlayScanner, parsingContext);
                        } else if (b == 1) {
                            parseOverlay(file, xmlPullParserNewPullParser, overlayScanner, parsingContext);
                        } else {
                            Log.w("OverlayConfig", String.format("Tag %s is unknown in %s at %s", name, file, xmlPullParserNewPullParser.getPositionDescription()));
                        }
                    }
                } catch (IOException | XmlPullParserException e) {
                    Log.w("OverlayConfig", "Got exception parsing overlay configuration.", e);
                }
                IoUtils.closeQuietly(fileReader);
            } catch (Throwable th) {
                IoUtils.closeQuietly(autoCloseableCloseQuietly);
                throw th;
            }
        } catch (FileNotFoundException unused) {
            Log.w("OverlayConfig", "Couldn't find or open overlay configuration file " + file);
        }
    }

    private static void parseMerge(File file, XmlPullParser xmlPullParser, OverlayScanner overlayScanner, ParsingContext parsingContext) {
        String attributeValue = xmlPullParser.getAttributeValue(null, "path");
        if (attributeValue == null) {
            throw new IllegalStateException(String.format("<merge> without path in %s at %s" + file, xmlPullParser.getPositionDescription()));
        }
        if (attributeValue.startsWith("/")) {
            throw new IllegalStateException(String.format("Path %s must be relative to the directory containing overlay configurations  files in %s at %s ", attributeValue, file, xmlPullParser.getPositionDescription()));
        }
        if (ParsingContext.access$208(parsingContext) == 5) {
            throw new IllegalStateException(String.format("Maximum <merge> depth exceeded in %s at %s", file, xmlPullParser.getPositionDescription()));
        }
        try {
            File canonicalFile = new File(parsingContext.mPartition.getOverlayFolder(), CONFIG_DIRECTORY).getCanonicalFile();
            File canonicalFile2 = new File(canonicalFile, attributeValue).getCanonicalFile();
            if (!canonicalFile2.exists()) {
                throw new IllegalStateException(String.format("Merged configuration file %s does not exist in %s at %s", attributeValue, file, xmlPullParser.getPositionDescription()));
            }
            if (!FileUtils.contains(canonicalFile, canonicalFile2)) {
                throw new IllegalStateException(String.format("Merged file %s outside of configuration directory in %s at %s", canonicalFile2.getAbsolutePath(), canonicalFile2, xmlPullParser.getPositionDescription()));
            }
            readConfigFile(canonicalFile2, overlayScanner, parsingContext);
            ParsingContext.access$210(parsingContext);
        } catch (IOException e) {
            throw new IllegalStateException(String.format("Couldn't find or open merged configuration file %s in %s at %s", attributeValue, file, xmlPullParser.getPositionDescription()), e);
        }
    }

    private static void parseOverlay(File file, XmlPullParser xmlPullParser, OverlayScanner overlayScanner, ParsingContext parsingContext) {
        OverlayScanner.ParsedOverlayInfo parsedOverlayInfo;
        boolean z;
        String attributeValue = xmlPullParser.getAttributeValue(null, "package");
        if (attributeValue != null) {
            if (overlayScanner != null) {
                OverlayScanner.ParsedOverlayInfo parsedInfo = overlayScanner.getParsedInfo(attributeValue);
                if (parsedInfo == null || !parsingContext.mPartition.containsOverlay(parsedInfo.path)) {
                    throw new IllegalStateException(String.format("overlay %s not present in partition %s in %s at %s", attributeValue, parsingContext.mPartition.getOverlayFolder(), file, xmlPullParser.getPositionDescription()));
                }
                parsedOverlayInfo = parsedInfo;
            } else {
                parsedOverlayInfo = null;
            }
            if (parsingContext.mConfiguredOverlays.contains(attributeValue)) {
                throw new IllegalStateException(String.format("overlay %s configured multiple times in a single partition in %s at %s", attributeValue, file, xmlPullParser.getPositionDescription()));
            }
            boolean z2 = xmlPullParser.getAttributeValue(null, "enabled") != null ? !"false".equals(r10) : false;
            String attributeValue2 = xmlPullParser.getAttributeValue(null, "mutable");
            if (attributeValue2 != null) {
                boolean z3 = !"false".equals(attributeValue2);
                if (!z3 && parsingContext.mFoundMutableOverlay) {
                    throw new IllegalStateException(String.format("immutable overlays must precede mutable overlays: found in %s at %s", file, xmlPullParser.getPositionDescription()));
                }
                z = z3;
            } else {
                z = true;
            }
            if (z) {
                parsingContext.mFoundMutableOverlay = true;
            } else if (!z2) {
                Log.w("OverlayConfig", "found default-disabled immutable overlay " + attributeValue);
            }
            ParsedConfiguration parsedConfiguration = new ParsedConfiguration(attributeValue, z2, z, parsingContext.mPartition.policy, parsedOverlayInfo);
            parsingContext.mConfiguredOverlays.add(attributeValue);
            parsingContext.mOrderedConfigurations.add(parsedConfiguration);
            return;
        }
        throw new IllegalStateException(String.format("\"<overlay> without package in %s at %s", file, xmlPullParser.getPositionDescription()));
    }
}
