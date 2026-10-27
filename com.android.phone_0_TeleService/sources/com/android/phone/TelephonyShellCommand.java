package com.android.phone;

import android.content.Context;
import android.os.Binder;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.provider.BlockedNumberContract;
import android.telephony.CarrierConfigManager;
import android.telephony.SubscriptionManager;
import android.util.Log;
import com.android.internal.telephony.BasicShellCommandHandler;
import com.android.internal.telephony.ITelephony;
import com.android.internal.telephony.Phone;
import com.android.internal.telephony.PhoneFactory;
import com.android.services.telephony.RadioOnStateListener;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes.dex */
public class TelephonyShellCommand extends BasicShellCommandHandler {
    private static final Map<String, CcType> CC_TYPE_MAP = new HashMap<String, CcType>() { // from class: com.android.phone.TelephonyShellCommand.1
        {
            put("gps.a_glonass_pos_protocol_select", CcType.STRING);
            put("gps.es_extension_sec", CcType.STRING);
            put("gps.gps_lock", CcType.STRING);
            put("gps.lpp_profile", CcType.STRING);
            put("gps.nfw_proxy_apps", CcType.STRING);
            put("gps.supl_es", CcType.STRING);
            put("gps.supl_host", CcType.STRING);
            put("gps.supl_mode", CcType.STRING);
            put("gps.supl_port", CcType.STRING);
            put("gps.supl_ver", CcType.STRING);
            put("gps.use_emergency_pdn_for_emergency_supl", CcType.STRING);
            put("carrier_app_no_wake_signal_config", CcType.STRING_ARRAY);
            put("carrier_app_wake_signal_config", CcType.STRING_ARRAY);
            put("call_screening_app", CcType.STRING);
            put("emailGatewayNumber", CcType.STRING);
            put("httpParams", CcType.STRING);
            put("naiSuffix", CcType.STRING);
            put("uaProfTagName", CcType.STRING);
            put("uaProfUrl", CcType.STRING);
            put("userAgent", CcType.STRING);
            put("ratchet_rat_families", CcType.STRING_ARRAY);
        }
    };
    private CarrierConfigManager mCarrierConfigManager;
    private Context mContext;
    private final ITelephony mInterface;

    private enum CcType {
        BOOLEAN,
        DOUBLE,
        DOUBLE_ARRAY,
        INT,
        INT_ARRAY,
        LONG,
        LONG_ARRAY,
        STRING,
        STRING_ARRAY,
        UNKNOWN
    }

    private class CcOptionParseResult {
        public boolean mPersistent;
        public int mSubId;

        private CcOptionParseResult(TelephonyShellCommand telephonyShellCommand) {
        }
    }

    public TelephonyShellCommand(ITelephony iTelephony, Context context) {
        this.mInterface = iTelephony;
        this.mCarrierConfigManager = (CarrierConfigManager) context.getSystemService("carrier_config");
        this.mContext = context;
    }

    @Override // com.android.internal.telephony.BasicShellCommandHandler
    public int onCommand(String str) {
        if (str == null) {
            return handleDefaultCommands(null);
        }
        switch (str) {
            case "ims":
                return handleImsCommand();
            case "numverify":
                return handleNumberVerificationCommand();
            case "emergency-callback-mode":
                return handleEmergencyCallbackModeCommand();
            case "emergency-number-test-mode":
                return handleEmergencyNumberTestModeCommand();
            case "cc":
                return handleCcCommand();
            case "data":
                return handleDataTestModeCommand();
            case "end-block-suppression":
                return handleEndBlockSuppressionCommand();
            default:
                return handleDefaultCommands(str);
        }
    }

    @Override // com.android.internal.telephony.BasicShellCommandHandler
    public void onHelp() {
        PrintWriter outPrintWriter = getOutPrintWriter();
        outPrintWriter.println("Telephony Commands:");
        outPrintWriter.println("  help");
        outPrintWriter.println("    Print this help text.");
        outPrintWriter.println("  ims");
        outPrintWriter.println("    IMS Commands.");
        outPrintWriter.println("  emergency-number-test-mode");
        outPrintWriter.println("    Emergency Number Test Mode Commands.");
        outPrintWriter.println("  end-block-suppression");
        outPrintWriter.println("    End Block Suppression command.");
        outPrintWriter.println("  data");
        outPrintWriter.println("    Data Test Mode Commands.");
        outPrintWriter.println("  cc");
        outPrintWriter.println("    Carrier Config Commands.");
        onHelpIms();
        onHelpEmergencyNumber();
        onHelpEndBlockSupperssion();
        onHelpDataTestMode();
        onHelpCc();
    }

    private void onHelpIms() {
        PrintWriter outPrintWriter = getOutPrintWriter();
        outPrintWriter.println("IMS Commands:");
        outPrintWriter.println("  ims set-ims-service [-s SLOT_ID] (-c | -d | -f) PACKAGE_NAME");
        outPrintWriter.println("    Sets the ImsService defined in PACKAGE_NAME to to be the bound");
        outPrintWriter.println("    ImsService. Options are:");
        outPrintWriter.println("      -s: the slot ID that the ImsService should be bound for. If no option");
        outPrintWriter.println("          is specified, it will choose the default voice SIM slot.");
        outPrintWriter.println("      -c: Override the ImsService defined in the carrier configuration.");
        outPrintWriter.println("      -d: Override the ImsService defined in the device overlay.");
        outPrintWriter.println("      -f: Set the feature that this override if for, if no option is");
        outPrintWriter.println("          specified, the new package name will be used for all features.");
        outPrintWriter.println("  ims get-ims-service [-s SLOT_ID] [-c | -d]");
        outPrintWriter.println("    Gets the package name of the currently defined ImsService.");
        outPrintWriter.println("    Options are:");
        outPrintWriter.println("      -s: The SIM slot ID for the registered ImsService. If no option");
        outPrintWriter.println("          is specified, it will choose the default voice SIM slot.");
        outPrintWriter.println("      -c: The ImsService defined as the carrier configured ImsService.");
        outPrintWriter.println("      -c: The ImsService defined as the device default ImsService.");
        outPrintWriter.println("      -f: The feature type that the query will be requested for. If none is");
        outPrintWriter.println("          specified, the returned package name will correspond to MMTEL.");
        outPrintWriter.println("  ims enable [-s SLOT_ID]");
        outPrintWriter.println("    enables IMS for the SIM slot specified, or for the default voice SIM slot");
        outPrintWriter.println("    if none is specified.");
        outPrintWriter.println("  ims disable [-s SLOT_ID]");
        outPrintWriter.println("    disables IMS for the SIM slot specified, or for the default voice SIM");
        outPrintWriter.println("    slot if none is specified.");
        outPrintWriter.println("  ims conference-event-package [enable/disable]");
        outPrintWriter.println("    enables or disables handling or network conference event package data.");
    }

    private void onHelpNumberVerification() {
        PrintWriter outPrintWriter = getOutPrintWriter();
        outPrintWriter.println("Number verification commands");
        outPrintWriter.println("  numverify override-package PACKAGE_NAME;");
        outPrintWriter.println("    Set the authorized package for number verification.");
        outPrintWriter.println("    Leave the package name blank to reset.");
        outPrintWriter.println("  numverify fake-call NUMBER;");
        outPrintWriter.println("    Fake an incoming call from NUMBER. This is for testing. Output will be");
        outPrintWriter.println("    1 if the call would have been intercepted, 0 otherwise.");
    }

    private void onHelpDataTestMode() {
        PrintWriter outPrintWriter = getOutPrintWriter();
        outPrintWriter.println("Mobile Data Test Mode Commands:");
        outPrintWriter.println("  data enable: enable mobile data connectivity");
        outPrintWriter.println("  data disable: disable mobile data connectivity");
    }

    private void onHelpEmergencyNumber() {
        PrintWriter outPrintWriter = getOutPrintWriter();
        outPrintWriter.println("Emergency Number Test Mode Commands:");
        outPrintWriter.println("  emergency-number-test-mode ");
        outPrintWriter.println("    Add(-a), Clear(-c), Print (-p) or Remove(-r) the emergency number list in the test mode");
        outPrintWriter.println("      -a <emergency number address>: add an emergency number address for the test mode, only allows '0'-'9', '*', '#' or '+'.");
        outPrintWriter.println("      -c: clear the emergency number list in the test mode.");
        outPrintWriter.println("      -r <emergency number address>: remove an existing emergency number address added by the test mode, only allows '0'-'9', '*', '#' or '+'.");
        outPrintWriter.println("      -p: get the full emergency number list in the test mode.");
    }

    private void onHelpEndBlockSupperssion() {
        PrintWriter outPrintWriter = getOutPrintWriter();
        outPrintWriter.println("End Block Suppression command:");
        outPrintWriter.println("  end-block-suppression: disable suppressing blocking by contact");
        outPrintWriter.println("                         with emergency services.");
    }

    private void onHelpCc() {
        PrintWriter outPrintWriter = getOutPrintWriter();
        outPrintWriter.println("Carrier Config Commands:");
        outPrintWriter.println("  cc get-value [-s SLOT_ID] [KEY]");
        outPrintWriter.println("    Print carrier config values.");
        outPrintWriter.println("    Options are:");
        outPrintWriter.println("      -s: The SIM slot ID to read carrier config value for. If no option");
        outPrintWriter.println("          is specified, it will choose the default voice SIM slot.");
        outPrintWriter.println("    KEY: The key to the carrier config value to print. All values are printed");
        outPrintWriter.println("         if KEY is not specified.");
        outPrintWriter.println("  cc set-value [-s SLOT_ID] [-p] KEY [NEW_VALUE]");
        outPrintWriter.println("    Set carrier config KEY to NEW_VALUE.");
        outPrintWriter.println("    Options are:");
        outPrintWriter.println("      -s: The SIM slot ID to set carrier config value for. If no option");
        outPrintWriter.println("          is specified, it will choose the default voice SIM slot.");
        outPrintWriter.println("      -p: Value will be stored persistent");
        outPrintWriter.println("    NEW_VALUE specifies the new value for carrier config KEY. Null will be");
        outPrintWriter.println("      used if NEW_VALUE is not set. Strings should be encapsulated with");
        outPrintWriter.println("      quotation marks. Spaces needs to be escaped. Example: \"Hello\\ World\"");
        outPrintWriter.println("      Separate items in arrays with space . Example: \"item1\" \"item2\"");
        outPrintWriter.println("  cc clear-values [-s SLOT_ID]");
        outPrintWriter.println("    Clear all carrier override values that has previously been set");
        outPrintWriter.println("    with set-value");
        outPrintWriter.println("    Options are:");
        outPrintWriter.println("      -s: The SIM slot ID to clear carrier config values for. If no option");
        outPrintWriter.println("          is specified, it will choose the default voice SIM slot.");
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private int handleImsCommand() {
        String nextArg = getNextArg();
        byte b = 0;
        if (nextArg == null) {
            onHelpIms();
            return 0;
        }
        switch (nextArg.hashCode()) {
            case -1583880926:
                b = !nextArg.equals("conference-event-package") ? (byte) -1 : (byte) 4;
                break;
            case -1298848381:
                b = !nextArg.equals("enable") ? (byte) -1 : (byte) 2;
                break;
            case 436884160:
                b = !nextArg.equals("get-ims-service") ? (byte) -1 : (byte) 1;
                break;
            case 1347636684:
                if (!nextArg.equals("set-ims-service")) {
                    b = -1;
                }
                break;
            case 1671308008:
                b = !nextArg.equals("disable") ? (byte) -1 : (byte) 3;
                break;
            default:
                b = -1;
                break;
        }
        if (b == 0) {
            return handleImsSetServiceCommand();
        }
        if (b == 1) {
            return handleImsGetServiceCommand();
        }
        if (b == 2) {
            return handleEnableIms();
        }
        if (b == 3) {
            return handleDisableIms();
        }
        if (b != 4) {
            return -1;
        }
        return handleCepChange();
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int handleDataTestModeCommand() {
        /*
            r7 = this;
            java.io.PrintWriter r0 = r7.getErrPrintWriter()
            java.lang.String r1 = r7.getNextArgRequired()
            r2 = 0
            if (r1 != 0) goto Lf
            r7.onHelpDataTestMode()
            return r2
        Lf:
            int r3 = r1.hashCode()
            r4 = -1298848381(0xffffffffb2952583, float:-1.7362941E-8)
            r5 = 1
            r6 = -1
            if (r3 == r4) goto L2a
            r4 = 1671308008(0x639e22e8, float:5.8342016E21)
            if (r3 == r4) goto L20
            goto L34
        L20:
            java.lang.String r3 = "disable"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L34
            r1 = r5
            goto L35
        L2a:
            java.lang.String r3 = "enable"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L34
            r1 = r2
            goto L35
        L34:
            r1 = r6
        L35:
            java.lang.String r3 = "Exception: "
            java.lang.String r4 = "TelephonyShellCommand"
            if (r1 == 0) goto L77
            if (r1 == r5) goto L41
            r7.onHelpDataTestMode()
            goto L7c
        L41:
            com.android.internal.telephony.ITelephony r7 = r7.mInterface     // Catch: android.os.RemoteException -> L47
            r7.disableDataConnectivity()     // Catch: android.os.RemoteException -> L47
            goto L7c
        L47:
            r7 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "data disable, error "
            r1.append(r2)
            java.lang.String r2 = r7.getMessage()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.util.Log.w(r4, r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r3)
            java.lang.String r7 = r7.getMessage()
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            r0.println(r7)
            return r6
        L77:
            com.android.internal.telephony.ITelephony r7 = r7.mInterface     // Catch: android.os.RemoteException -> L7d
            r7.enableDataConnectivity()     // Catch: android.os.RemoteException -> L7d
        L7c:
            return r2
        L7d:
            r7 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "data enable, error "
            r1.append(r2)
            java.lang.String r2 = r7.getMessage()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.util.Log.w(r4, r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r3)
            java.lang.String r7 = r7.getMessage()
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            r0.println(r7)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.phone.TelephonyShellCommand.handleDataTestModeCommand():int");
    }

    private int handleEmergencyCallbackModeCommand() {
        PrintWriter errPrintWriter = getErrPrintWriter();
        try {
            this.mInterface.startEmergencyCallbackMode();
            Log.d("TelephonyShellCommand", "handleEmergencyCallbackModeCommand: triggered");
            return 0;
        } catch (RemoteException e) {
            Log.w("TelephonyShellCommand", "emergency-callback-mode error: " + e.getMessage());
            errPrintWriter.println("Exception: " + e.getMessage());
            return -1;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0052  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int handleEmergencyNumberTestModeCommand() {
        /*
            Method dump skipped, instruction units count: 421
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.phone.TelephonyShellCommand.handleEmergencyNumberTestModeCommand():int");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int handleNumberVerificationCommand() {
        /*
            r6 = this;
            java.lang.String r0 = r6.getNextArg()
            r1 = 0
            if (r0 != 0) goto Lb
            r6.onHelpNumberVerification()
            return r1
        Lb:
            boolean r2 = r6.checkShellUid()
            r3 = -1
            if (r2 != 0) goto L13
            return r3
        L13:
            int r2 = r0.hashCode()
            r4 = 156149573(0x94ea745, float:2.4874998E-33)
            r5 = 1
            if (r2 == r4) goto L2d
            r4 = 986911190(0x3ad311d6, float:0.0016103338)
            if (r2 == r4) goto L23
            goto L37
        L23:
            java.lang.String r2 = "fake-call"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L37
            r0 = r5
            goto L38
        L2d:
            java.lang.String r2 = "override-package"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L37
            r0 = r1
            goto L38
        L37:
            r0 = r3
        L38:
            if (r0 == 0) goto L58
            if (r0 == r5) goto L3d
            return r3
        L3d:
            com.android.phone.NumberVerificationManager r0 = com.android.phone.NumberVerificationManager.getInstance()
            java.lang.String r2 = r6.getNextArg()
            boolean r0 = r0.checkIncomingCall(r2)
            java.io.PrintWriter r6 = r6.getOutPrintWriter()
            if (r0 == 0) goto L52
            java.lang.String r0 = "1"
            goto L54
        L52:
            java.lang.String r0 = "0"
        L54:
            r6.println(r0)
            return r1
        L58:
            java.lang.String r6 = r6.getNextArg()
            com.android.phone.NumberVerificationManager.overrideAuthorizedPackage(r6)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.phone.TelephonyShellCommand.handleNumberVerificationCommand():int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x00ca, code lost:
    
        r0 = java.lang.Integer.parseInt(getNextArgRequired());
     */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0058  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int handleImsSetServiceCommand() {
        /*
            Method dump skipped, instruction units count: 430
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.phone.TelephonyShellCommand.handleImsSetServiceCommand():int");
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0054  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int handleImsGetServiceCommand() {
        /*
            Method dump skipped, instruction units count: 257
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.phone.TelephonyShellCommand.handleImsGetServiceCommand():int");
    }

    private int handleEnableIms() {
        int defaultSlot = getDefaultSlot();
        while (true) {
            String nextOption = getNextOption();
            if (nextOption != null) {
                if (((nextOption.hashCode() == 1510 && nextOption.equals("-s")) ? (byte) 0 : (byte) -1) == 0) {
                    try {
                        defaultSlot = Integer.parseInt(getNextArgRequired());
                    } catch (NumberFormatException unused) {
                        getErrPrintWriter().println("ims enable requires an integer as a SLOT_ID.");
                        return -1;
                    }
                }
            } else {
                try {
                    this.mInterface.enableIms(defaultSlot);
                    Log.v("TelephonyShellCommand", "ims enable -s " + defaultSlot);
                    return 0;
                } catch (RemoteException unused2) {
                    return -1;
                }
            }
        }
    }

    private int handleDisableIms() {
        int defaultSlot = getDefaultSlot();
        while (true) {
            String nextOption = getNextOption();
            if (nextOption != null) {
                if (((nextOption.hashCode() == 1510 && nextOption.equals("-s")) ? (byte) 0 : (byte) -1) == 0) {
                    try {
                        defaultSlot = Integer.parseInt(getNextArgRequired());
                    } catch (NumberFormatException unused) {
                        getErrPrintWriter().println("ims disable requires an integer as a SLOT_ID.");
                        return -1;
                    }
                }
            } else {
                try {
                    this.mInterface.disableIms(defaultSlot);
                    Log.v("TelephonyShellCommand", "ims disable -s " + defaultSlot);
                    return 0;
                } catch (RemoteException unused2) {
                    return -1;
                }
            }
        }
    }

    private int handleCepChange() {
        Log.i("TelephonyShellCommand", "handleCepChange");
        String nextArg = getNextArg();
        if (nextArg == null) {
            return -1;
        }
        try {
            this.mInterface.setCepEnabled(nextArg.equals("enable"));
            return 0;
        } catch (RemoteException unused) {
            return -1;
        }
    }

    private int getDefaultSlot() {
        int defaultVoicePhoneId = SubscriptionManager.getDefaultVoicePhoneId();
        if (defaultVoicePhoneId <= -1 || defaultVoicePhoneId == Integer.MAX_VALUE) {
            return 0;
        }
        return defaultVoicePhoneId;
    }

    private CcOptionParseResult parseCcOptions(String str, boolean z) {
        PrintWriter errPrintWriter = getErrPrintWriter();
        CcOptionParseResult ccOptionParseResult = new CcOptionParseResult();
        ccOptionParseResult.mSubId = SubscriptionManager.getDefaultSubscriptionId();
        ccOptionParseResult.mPersistent = false;
        while (true) {
            String nextOption = getNextOption();
            if (nextOption == null) {
                return ccOptionParseResult;
            }
            byte b = -1;
            int iHashCode = nextOption.hashCode();
            if (iHashCode != 1507) {
                if (iHashCode == 1510 && nextOption.equals("-s")) {
                    b = 0;
                }
            } else if (nextOption.equals("-p")) {
                b = 1;
            }
            if (b == 0) {
                try {
                    int iSlotStringToSubId = slotStringToSubId(str, getNextArgRequired());
                    ccOptionParseResult.mSubId = iSlotStringToSubId;
                    if (!SubscriptionManager.isValidSubscriptionId(iSlotStringToSubId)) {
                        errPrintWriter.println(str + "No valid subscription found.");
                        return null;
                    }
                } catch (IllegalArgumentException unused) {
                    errPrintWriter.println(str + "SLOT_ID expected after -s.");
                    return null;
                }
            } else {
                if (b != 1) {
                    errPrintWriter.println(str + "Unknown option " + nextOption);
                    return null;
                }
                if (z) {
                    ccOptionParseResult.mPersistent = true;
                } else {
                    errPrintWriter.println(str + "Unexpected option " + nextOption);
                    return null;
                }
            }
        }
    }

    private int slotStringToSubId(String str, String str2) {
        try {
            int i = Integer.parseInt(str2);
            if (!SubscriptionManager.isValidPhoneId(i)) {
                getErrPrintWriter().println(str + str2 + " is not a valid SLOT_ID.");
                return -1;
            }
            Phone phone = PhoneFactory.getPhone(i);
            if (phone == null) {
                getErrPrintWriter().println(str + "No subscription found in slot " + i + ".");
                return -1;
            }
            return phone.getSubId();
        } catch (NumberFormatException unused) {
            getErrPrintWriter().println(str + str2 + " is not a valid number for SLOT_ID.");
            return -1;
        }
    }

    private boolean checkShellUid() {
        return Binder.getCallingUid() == 2000 || Binder.getCallingUid() == 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x004b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int handleCcCommand() {
        /*
            r7 = this;
            int r0 = android.os.Binder.getCallingUid()
            r1 = -1
            if (r0 != 0) goto L7a
            boolean r0 = com.android.internal.telephony.util.TelephonyUtils.IS_USER
            if (r0 == 0) goto Ld
            goto L7a
        Ld:
            java.lang.String r0 = r7.getNextArg()
            r2 = 0
            if (r0 != 0) goto L18
            r7.onHelpCc()
            return r2
        L18:
            int r3 = r0.hashCode()
            r4 = -1229320030(0xffffffffb6ba10a2, float:-5.5451683E-6)
            r5 = 2
            r6 = 1
            if (r3 == r4) goto L41
            r4 = -497151930(0xffffffffe25e1046, float:-1.02408745E21)
            if (r3 == r4) goto L37
            r4 = -282534854(0xffffffffef28dc3a, float:-5.225972E28)
            if (r3 == r4) goto L2e
            goto L4b
        L2e:
            java.lang.String r3 = "get-value"
            boolean r3 = r0.equals(r3)
            if (r3 == 0) goto L4b
            goto L4c
        L37:
            java.lang.String r2 = "set-value"
            boolean r2 = r0.equals(r2)
            if (r2 == 0) goto L4b
            r2 = r6
            goto L4c
        L41:
            java.lang.String r2 = "clear-values"
            boolean r2 = r0.equals(r2)
            if (r2 == 0) goto L4b
            r2 = r5
            goto L4c
        L4b:
            r2 = r1
        L4c:
            if (r2 == 0) goto L75
            if (r2 == r6) goto L70
            if (r2 == r5) goto L6b
            java.io.PrintWriter r7 = r7.getErrPrintWriter()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "cc: Unknown argument: "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r7.println(r0)
            return r1
        L6b:
            int r7 = r7.handleCcClearValues()
            return r7
        L70:
            int r7 = r7.handleCcSetValue()
            return r7
        L75:
            int r7 = r7.handleCcGetValue()
            return r7
        L7a:
            java.io.PrintWriter r7 = r7.getErrPrintWriter()
            java.lang.String r0 = "cc: Permission denied."
            r7.println(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.phone.TelephonyShellCommand.handleCcCommand():int");
    }

    private int handleCcGetValue() {
        PrintWriter errPrintWriter = getErrPrintWriter();
        CcOptionParseResult ccOptions = parseCcOptions("cc get-value: ", false);
        if (ccOptions == null) {
            return -1;
        }
        PersistableBundle configForSubId = this.mCarrierConfigManager.getConfigForSubId(ccOptions.mSubId);
        if (configForSubId == null) {
            errPrintWriter.println("cc get-value: No carrier config values found for subId " + ccOptions.mSubId + ".");
            return -1;
        }
        String nextArg = getNextArg();
        if (nextArg != null) {
            if (!configForSubId.containsKey(nextArg)) {
                errPrintWriter.println("cc get-value: " + nextArg + " is not a valid key.");
                return -1;
            }
            getOutPrintWriter().println(ccValueToString(nextArg, getType("cc get-value: ", nextArg, configForSubId), configForSubId));
        } else {
            for (String str : new TreeSet(configForSubId.keySet())) {
                getOutPrintWriter().println(ccValueToString(str, getType("cc get-value: ", str, configForSubId), configForSubId));
            }
        }
        return 0;
    }

    private int handleCcSetValue() {
        PrintWriter errPrintWriter = getErrPrintWriter();
        CcOptionParseResult ccOptions = parseCcOptions("cc set-value: ", true);
        if (ccOptions == null) {
            return -1;
        }
        PersistableBundle configForSubId = this.mCarrierConfigManager.getConfigForSubId(ccOptions.mSubId);
        if (configForSubId == null) {
            errPrintWriter.println("cc set-value: No carrier config values found for subId " + ccOptions.mSubId + ".");
            return -1;
        }
        String nextArg = getNextArg();
        if (nextArg == null || nextArg.equals("")) {
            errPrintWriter.println("cc set-value: KEY is missing");
            return -1;
        }
        if (!configForSubId.containsKey(nextArg)) {
            errPrintWriter.println("cc set-value: " + nextArg + " is not a valid key.");
            return -1;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        while (peekNextArg() != null) {
            arrayList.add(getNextArg());
        }
        CcType type = getType("cc set-value: ", nextArg, configForSubId);
        if (type == CcType.UNKNOWN) {
            errPrintWriter.println("cc set-value: ERROR: Not possible to override key with unknown type.");
            return -1;
        }
        PersistableBundle overrideBundle = getOverrideBundle("cc set-value: ", type, nextArg, arrayList);
        if (overrideBundle == null) {
            return -1;
        }
        this.mCarrierConfigManager.overrideConfig(ccOptions.mSubId, overrideBundle, ccOptions.mPersistent);
        PersistableBundle configForSubId2 = this.mCarrierConfigManager.getConfigForSubId(ccOptions.mSubId);
        if (configForSubId2 == null) {
            errPrintWriter.println("cc set-value: No carrier config values found for subId " + ccOptions.mSubId + ".");
            return -1;
        }
        String strCcValueToString = ccValueToString(nextArg, type, configForSubId);
        String strCcValueToString2 = ccValueToString(nextArg, type, configForSubId2);
        getOutPrintWriter().println("Previous value: \n" + strCcValueToString);
        getOutPrintWriter().println("New value: \n" + strCcValueToString2);
        return 0;
    }

    private int handleCcClearValues() {
        getErrPrintWriter();
        CcOptionParseResult ccOptions = parseCcOptions("cc clear-values: ", false);
        if (ccOptions == null) {
            return -1;
        }
        this.mCarrierConfigManager.overrideConfig(ccOptions.mSubId, null, true);
        getOutPrintWriter().println("All previously set carrier config override values has been cleared");
        return 0;
    }

    private CcType getType(String str, String str2, PersistableBundle persistableBundle) {
        Object obj = persistableBundle.get(str2);
        if (CC_TYPE_MAP.containsKey(str2)) {
            return CC_TYPE_MAP.get(str2);
        }
        if (obj != null) {
            if (obj instanceof Boolean) {
                return CcType.BOOLEAN;
            }
            if (obj instanceof Double) {
                return CcType.DOUBLE;
            }
            if (obj instanceof double[]) {
                return CcType.DOUBLE_ARRAY;
            }
            if (obj instanceof Integer) {
                return CcType.INT;
            }
            if (obj instanceof int[]) {
                return CcType.INT_ARRAY;
            }
            if (obj instanceof Long) {
                return CcType.LONG;
            }
            if (obj instanceof long[]) {
                return CcType.LONG_ARRAY;
            }
            if (obj instanceof String) {
                return CcType.STRING;
            }
            if (obj instanceof String[]) {
                return CcType.STRING_ARRAY;
            }
        } else {
            if (str2.endsWith("double_array")) {
                return CcType.DOUBLE_ARRAY;
            }
            if (str2.endsWith("int_array")) {
                return CcType.INT_ARRAY;
            }
            if (str2.endsWith("long_array")) {
                return CcType.LONG_ARRAY;
            }
            if (str2.endsWith("string")) {
                return CcType.STRING;
            }
            if (str2.endsWith("string_array") || str2.endsWith("strings")) {
                return CcType.STRING_ARRAY;
            }
        }
        getErrPrintWriter().println(str + "ERROR: " + str2 + " has unknown type.");
        return CcType.UNKNOWN;
    }

    private String ccValueToString(String str, CcType ccType, PersistableBundle persistableBundle) {
        StringBuilder sb = new StringBuilder();
        String string = ccType.toString();
        Object obj = persistableBundle.get(str);
        if (obj == null) {
            sb.append("null");
        } else {
            int i = AnonymousClass2.$SwitchMap$com$android$phone$TelephonyShellCommand$CcType[ccType.ordinal()];
            if (i == 1) {
                double[] dArr = (double[]) obj;
                for (int i2 = 0; i2 < dArr.length; i2++) {
                    if (i2 != 0) {
                        sb.append(" ");
                    }
                    sb.append(dArr[i2]);
                }
            } else if (i == 2) {
                int[] iArr = (int[]) obj;
                for (int i3 = 0; i3 < iArr.length; i3++) {
                    if (i3 != 0) {
                        sb.append(" ");
                    }
                    sb.append(iArr[i3]);
                }
            } else if (i == 3) {
                long[] jArr = (long[]) obj;
                for (int i4 = 0; i4 < jArr.length; i4++) {
                    if (i4 != 0) {
                        sb.append(" ");
                    }
                    sb.append(jArr[i4]);
                }
            } else if (i == 4) {
                sb.append("\"" + obj.toString() + "\"");
            } else if (i == 5) {
                String[] strArr = (String[]) obj;
                for (int i5 = 0; i5 < strArr.length; i5++) {
                    if (i5 != 0) {
                        sb.append(" ");
                    }
                    if (strArr[i5] != null) {
                        sb.append("\"" + strArr[i5] + "\"");
                    } else {
                        sb.append("null");
                    }
                }
            } else {
                sb.append(obj.toString());
            }
        }
        return String.format("%-70s %-15s %s", str, string, sb);
    }

    /* JADX INFO: renamed from: com.android.phone.TelephonyShellCommand$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$android$phone$TelephonyShellCommand$CcType;

        static {
            int[] iArr = new int[CcType.values().length];
            $SwitchMap$com$android$phone$TelephonyShellCommand$CcType = iArr;
            try {
                iArr[CcType.DOUBLE_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$android$phone$TelephonyShellCommand$CcType[CcType.INT_ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$android$phone$TelephonyShellCommand$CcType[CcType.LONG_ARRAY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$android$phone$TelephonyShellCommand$CcType[CcType.STRING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$android$phone$TelephonyShellCommand$CcType[CcType.STRING_ARRAY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$android$phone$TelephonyShellCommand$CcType[CcType.BOOLEAN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$android$phone$TelephonyShellCommand$CcType[CcType.DOUBLE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$android$phone$TelephonyShellCommand$CcType[CcType.INT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$android$phone$TelephonyShellCommand$CcType[CcType.LONG.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private PersistableBundle getOverrideBundle(String str, CcType ccType, String str2, ArrayList<String> arrayList) {
        PrintWriter errPrintWriter = getErrPrintWriter();
        PersistableBundle persistableBundle = new PersistableBundle();
        double[] dArr = null;
        String[] strArr = null;
        long[] jArr = null;
        int[] iArr = null;
        switch (AnonymousClass2.$SwitchMap$com$android$phone$TelephonyShellCommand$CcType[ccType.ordinal()]) {
            case RadioOnStateListener.MSG_RADIO_ON /* 4 */:
                if (arrayList.size() > 1) {
                    errPrintWriter.println(str + "Expected 0 or 1 values for type " + ccType + ". Found: " + arrayList.size());
                    return null;
                }
                break;
            case 6:
            case 7:
            case 8:
            case 9:
                if (arrayList.size() != 1) {
                    errPrintWriter.println(str + "Expected 1 value for type " + ccType + ". Found: " + arrayList.size());
                    return null;
                }
                break;
        }
        int i = 0;
        switch (AnonymousClass2.$SwitchMap$com$android$phone$TelephonyShellCommand$CcType[ccType.ordinal()]) {
            case 1:
                if (arrayList.size() > 0) {
                    double[] dArr2 = new double[arrayList.size()];
                    while (i < arrayList.size()) {
                        try {
                            dArr2[i] = Double.parseDouble(arrayList.get(i));
                            i++;
                        } catch (NumberFormatException unused) {
                            errPrintWriter.println(str + "Unable to parse " + arrayList.get(i) + " as a double.");
                            return null;
                        }
                    }
                    dArr = dArr2;
                }
                persistableBundle.putDoubleArray(str2, dArr);
                return persistableBundle;
            case RadioOnStateListener.MSG_SERVICE_STATE_CHANGED /* 2 */:
                if (arrayList.size() > 0) {
                    int[] iArr2 = new int[arrayList.size()];
                    while (i < arrayList.size()) {
                        try {
                            iArr2[i] = Integer.parseInt(arrayList.get(i));
                            i++;
                        } catch (NumberFormatException unused2) {
                            errPrintWriter.println(str + "Unable to parse " + arrayList.get(i) + " as an integer.");
                            return null;
                        }
                    }
                    iArr = iArr2;
                }
                persistableBundle.putIntArray(str2, iArr);
                return persistableBundle;
            case 3:
                if (arrayList.size() > 0) {
                    long[] jArr2 = new long[arrayList.size()];
                    while (i < arrayList.size()) {
                        try {
                            jArr2[i] = Long.parseLong(arrayList.get(i));
                            i++;
                        } catch (NumberFormatException unused3) {
                            errPrintWriter.println(str + "Unable to parse " + arrayList.get(i) + " as a long");
                            return null;
                        }
                    }
                    jArr = jArr2;
                }
                persistableBundle.putLongArray(str2, jArr);
                return persistableBundle;
            case RadioOnStateListener.MSG_RADIO_ON /* 4 */:
                persistableBundle.putString(str2, arrayList.size() > 0 ? arrayList.get(0) : null);
                return persistableBundle;
            case 5:
                if (arrayList.size() > 0) {
                    strArr = new String[arrayList.size()];
                    arrayList.toArray(strArr);
                }
                persistableBundle.putStringArray(str2, strArr);
                return persistableBundle;
            case 6:
                if ("true".equalsIgnoreCase(arrayList.get(0))) {
                    persistableBundle.putBoolean(str2, true);
                } else if ("false".equalsIgnoreCase(arrayList.get(0))) {
                    persistableBundle.putBoolean(str2, false);
                } else {
                    errPrintWriter.println(str + "Unable to parse " + arrayList.get(0) + " as a " + ccType);
                    return null;
                }
                return persistableBundle;
            case 7:
                try {
                    persistableBundle.putDouble(str2, Double.parseDouble(arrayList.get(0)));
                    return persistableBundle;
                } catch (NumberFormatException unused4) {
                    errPrintWriter.println(str + "Unable to parse " + arrayList.get(0) + " as a " + ccType);
                    return null;
                }
            case 8:
                try {
                    persistableBundle.putInt(str2, Integer.parseInt(arrayList.get(0)));
                    return persistableBundle;
                } catch (NumberFormatException unused5) {
                    errPrintWriter.println(str + "Unable to parse " + arrayList.get(0) + " as an " + ccType);
                    return null;
                }
            case 9:
                try {
                    persistableBundle.putLong(str2, Long.parseLong(arrayList.get(0)));
                    return persistableBundle;
                } catch (NumberFormatException unused6) {
                    errPrintWriter.println(str + "Unable to parse " + arrayList.get(0) + " as a " + ccType);
                    return null;
                }
            default:
                return persistableBundle;
        }
    }

    private int handleEndBlockSuppressionCommand() {
        if (!checkShellUid()) {
            return -1;
        }
        if (!BlockedNumberContract.SystemContract.getBlockSuppressionStatus(this.mContext).isSuppressed) {
            return 0;
        }
        BlockedNumberContract.SystemContract.endBlockSuppression(this.mContext);
        return 0;
    }
}
