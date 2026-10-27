package SevenZip;

import SevenZip.Compression.LZMA.Decoder;
import SevenZip.Compression.LZMA.Encoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/* JADX INFO: loaded from: classes.dex */
public class LzmaAlone {

    public static class CommandLine {
        public static final int kBenchmak = 2;
        public static final int kDecode = 1;
        public static final int kEncode = 0;
        public String InFile;
        public String OutFile;
        public int Command = -1;
        public int NumBenchmarkPasses = 10;
        public int DictionarySize = 8388608;
        public boolean DictionarySizeIsDefined = false;
        public int Lc = 3;
        public int Lp = 0;
        public int Pb = 2;
        public int Fb = 128;
        public boolean FbIsDefined = false;
        public boolean Eos = false;
        public int Algorithm = 2;
        public int MatchFinder = 1;

        boolean ParseSwitch(String str) {
            if (str.startsWith("d")) {
                this.DictionarySize = 1 << Integer.parseInt(str.substring(1));
                this.DictionarySizeIsDefined = true;
            } else if (str.startsWith("fb")) {
                this.Fb = Integer.parseInt(str.substring(2));
                this.FbIsDefined = true;
            } else if (str.startsWith("a")) {
                this.Algorithm = Integer.parseInt(str.substring(1));
            } else if (str.startsWith("lc")) {
                this.Lc = Integer.parseInt(str.substring(2));
            } else if (str.startsWith("lp")) {
                this.Lp = Integer.parseInt(str.substring(2));
            } else if (str.startsWith("pb")) {
                this.Pb = Integer.parseInt(str.substring(2));
            } else if (str.startsWith("eos")) {
                this.Eos = true;
            } else {
                if (str.startsWith("mf")) {
                    String strSubstring = str.substring(2);
                    if (strSubstring.equals("bt2")) {
                        this.MatchFinder = 0;
                    } else if (strSubstring.equals("bt4")) {
                        this.MatchFinder = 1;
                    } else if (strSubstring.equals("bt4b")) {
                        this.MatchFinder = 2;
                    }
                }
                return false;
            }
            return true;
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x003b  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean Parse(java.lang.String[] r9) throws java.lang.Exception {
            /*
                r8 = this;
                r0 = 0
                r1 = 1
                r2 = r0
                r4 = r2
                r3 = r1
            L5:
                int r5 = r9.length
                if (r2 >= r5) goto L7c
                r5 = r9[r2]
                int r6 = r5.length()
                if (r6 != 0) goto L11
                return r0
            L11:
                if (r3 == 0) goto L3b
                java.lang.String r6 = "--"
                int r6 = r5.compareTo(r6)
                if (r6 != 0) goto L1d
                r3 = r0
                goto L78
            L1d:
                char r6 = r5.charAt(r0)
                r7 = 45
                if (r6 != r7) goto L3b
                java.lang.String r5 = r5.substring(r1)
                java.lang.String r5 = r5.toLowerCase()
                int r6 = r5.length()
                if (r6 != 0) goto L34
                return r0
            L34:
                boolean r5 = r8.ParseSwitch(r5)     // Catch: java.lang.NumberFormatException -> L3a
                if (r5 != 0) goto L78
            L3a:
                return r0
            L3b:
                r6 = 2
                if (r4 != 0) goto L60
                java.lang.String r7 = "e"
                boolean r7 = r5.equalsIgnoreCase(r7)
                if (r7 == 0) goto L49
                r8.Command = r0
                goto L76
            L49:
                java.lang.String r7 = "d"
                boolean r7 = r5.equalsIgnoreCase(r7)
                if (r7 == 0) goto L54
                r8.Command = r1
                goto L76
            L54:
                java.lang.String r7 = "b"
                boolean r5 = r5.equalsIgnoreCase(r7)
                if (r5 == 0) goto L5f
                r8.Command = r6
                goto L76
            L5f:
                return r0
            L60:
                if (r4 != r1) goto L72
                int r7 = r8.Command
                if (r7 != r6) goto L6f
                int r5 = java.lang.Integer.parseInt(r5)     // Catch: java.lang.NumberFormatException -> L6e
                r8.NumBenchmarkPasses = r5     // Catch: java.lang.NumberFormatException -> L6e
                if (r5 >= r1) goto L76
            L6e:
                return r0
            L6f:
                r8.InFile = r5
                goto L76
            L72:
                if (r4 != r6) goto L7b
                r8.OutFile = r5
            L76:
                int r4 = r4 + 1
            L78:
                int r2 = r2 + 1
                goto L5
            L7b:
                return r0
            L7c:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: SevenZip.LzmaAlone.CommandLine.Parse(java.lang.String[]):boolean");
        }
    }

    static void PrintHelp() {
        System.out.println("\nUsage:  LZMA <e|d> [<switches>...] inputFile outputFile\n  e: encode file\n  d: decode file\n  b: Benchmark\n<Switches>\n  -d{N}:  set dictionary - [0,28], default: 23 (8MB)\n  -fb{N}: set number of fast bytes - [5, 273], default: 128\n  -lc{N}: set number of literal context bits - [0, 8], default: 3\n  -lp{N}: set number of literal pos bits - [0, 4], default: 0\n  -pb{N}: set number of pos bits - [0, 4], default: 2\n  -mf{MF_ID}: set Match Finder: [bt2, bt4], default: bt4\n  -eos:   write End Of Stream marker\n");
    }

    public static void main(String[] strArr) throws Exception {
        System.out.println("\nLZMA (Java) 4.61  2008-11-23\n");
        if (strArr.length < 1) {
            PrintHelp();
            return;
        }
        CommandLine commandLine = new CommandLine();
        if (!commandLine.Parse(strArr)) {
            System.out.println("\nIncorrect command");
            return;
        }
        if (commandLine.Command == 2) {
            int i = commandLine.DictionarySizeIsDefined ? commandLine.DictionarySize : 2097152;
            if (commandLine.MatchFinder > 1) {
                throw new Exception("Unsupported match finder");
            }
            LzmaBench.LzmaBenchmark(commandLine.NumBenchmarkPasses, i);
            return;
        }
        if (commandLine.Command == 0 || commandLine.Command == 1) {
            File file = new File(commandLine.InFile);
            File file2 = new File(commandLine.OutFile);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
            boolean z = commandLine.Eos;
            int i2 = 0;
            if (commandLine.Command == 0) {
                Encoder encoder = new Encoder();
                if (!encoder.SetAlgorithm(commandLine.Algorithm)) {
                    throw new Exception("Incorrect compression mode");
                }
                if (!encoder.SetDictionarySize(commandLine.DictionarySize)) {
                    throw new Exception("Incorrect dictionary size");
                }
                if (!encoder.SetNumFastBytes(commandLine.Fb)) {
                    throw new Exception("Incorrect -fb value");
                }
                if (!encoder.SetMatchFinder(commandLine.MatchFinder)) {
                    throw new Exception("Incorrect -mf value");
                }
                if (!encoder.SetLcLpPb(commandLine.Lc, commandLine.Lp, commandLine.Pb)) {
                    throw new Exception("Incorrect -lc or -lp or -pb value");
                }
                encoder.SetEndMarkerMode(z);
                encoder.WriteCoderProperties(bufferedOutputStream);
                long length = z ? -1L : file.length();
                while (i2 < 8) {
                    bufferedOutputStream.write(((int) (length >>> (i2 * 8))) & 255);
                    i2++;
                }
                encoder.Code(bufferedInputStream, bufferedOutputStream, -1L, -1L, null);
            } else {
                byte[] bArr = new byte[5];
                if (bufferedInputStream.read(bArr, 0, 5) != 5) {
                    throw new Exception("input .lzma file is too short");
                }
                Decoder decoder = new Decoder();
                if (!decoder.SetDecoderProperties(bArr)) {
                    throw new Exception("Incorrect stream properties");
                }
                long j = 0;
                while (i2 < 8) {
                    int i3 = bufferedInputStream.read();
                    if (i3 < 0) {
                        throw new Exception("Can't read stream size");
                    }
                    j |= ((long) i3) << (i2 * 8);
                    i2++;
                }
                if (!decoder.Code(bufferedInputStream, bufferedOutputStream, j)) {
                    throw new Exception("Error in data stream");
                }
            }
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            bufferedInputStream.close();
            return;
        }
        throw new Exception("Incorrect command");
    }
}
