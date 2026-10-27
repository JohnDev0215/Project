package com.android.dx.command.dump;

import com.android.dex.util.FileUtils;
import com.android.dx.cf.iface.ParseException;
import com.android.dx.util.HexParser;
import java.io.UnsupportedEncodingException;
import net.bytebuddy.dynamic.ClassFileLocator;

/* JADX INFO: loaded from: classes.dex */
public class Main {
    static Args parsedArgs = new Args();

    private Main() {
    }

    public static void main(String[] strArr) {
        int i = 0;
        while (i < strArr.length) {
            String str = strArr[i];
            if (str.equals("--") || !str.startsWith("--")) {
                break;
            }
            if (str.equals("--bytes")) {
                parsedArgs.rawBytes = true;
            } else if (str.equals("--basic-blocks")) {
                parsedArgs.basicBlocks = true;
            } else if (str.equals("--rop-blocks")) {
                parsedArgs.ropBlocks = true;
            } else if (str.equals("--optimize")) {
                parsedArgs.optimize = true;
            } else if (str.equals("--ssa-blocks")) {
                parsedArgs.ssaBlocks = true;
            } else if (str.startsWith("--ssa-step=")) {
                parsedArgs.ssaStep = str.substring(str.indexOf(61) + 1);
            } else if (str.equals("--debug")) {
                parsedArgs.debug = true;
            } else if (str.equals("--dot")) {
                parsedArgs.dotDump = true;
            } else if (str.equals("--strict")) {
                parsedArgs.strictParse = true;
            } else if (str.startsWith("--width=")) {
                parsedArgs.width = Integer.parseInt(str.substring(str.indexOf(61) + 1));
            } else if (str.startsWith("--method=")) {
                parsedArgs.method = str.substring(str.indexOf(61) + 1);
            } else {
                System.err.println("unknown option: " + str);
                throw new RuntimeException("usage");
            }
            i++;
        }
        if (i == strArr.length) {
            System.err.println("no input files specified");
            throw new RuntimeException("usage");
        }
        while (i < strArr.length) {
            try {
                String str2 = strArr[i];
                System.out.println("reading " + str2 + "...");
                byte[] file = FileUtils.readFile(str2);
                if (!str2.endsWith(ClassFileLocator.CLASS_FILE_EXTENSION)) {
                    try {
                        file = HexParser.parse(new String(file, "utf-8"));
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException("shouldn't happen", e);
                    }
                }
                processOne(str2, file);
            } catch (ParseException e2) {
                System.err.println("\ntrouble parsing:");
                if (parsedArgs.debug) {
                    e2.printStackTrace();
                } else {
                    e2.printContext(System.err);
                }
            }
            i++;
        }
    }

    private static void processOne(String str, byte[] bArr) {
        if (parsedArgs.dotDump) {
            DotDumper.dump(bArr, str, parsedArgs);
            return;
        }
        if (parsedArgs.basicBlocks) {
            BlockDumper.dump(bArr, System.out, str, false, parsedArgs);
            return;
        }
        if (parsedArgs.ropBlocks) {
            BlockDumper.dump(bArr, System.out, str, true, parsedArgs);
        } else if (parsedArgs.ssaBlocks) {
            parsedArgs.optimize = false;
            SsaDumper.dump(bArr, System.out, str, parsedArgs);
        } else {
            ClassDumper.dump(bArr, System.out, str, parsedArgs);
        }
    }
}
