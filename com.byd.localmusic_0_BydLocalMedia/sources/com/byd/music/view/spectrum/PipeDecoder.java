package com.byd.music.view.spectrum;

import android.media.MediaMetrics;
import android.util.TimeUtils;
import com.byd.music.view.spectrum.TarsosDSPAudioFormat;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteOrder;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.text.Typography;

/* JADX INFO: loaded from: classes5.dex */
public class PipeDecoder {
    private static final Logger LOG = Logger.getLogger(PipeDecoder.class.getName());
    private String decoderBinaryAbsolutePath;
    private final String pipeArgument;
    private final int pipeBuffer;
    private final String pipeCommand;
    private final String pipeEnvironment;
    private boolean printErrorstream;

    public PipeDecoder() {
        this.printErrorstream = false;
        this.pipeBuffer = 10000;
        if (System.getProperty("os.name").indexOf("indows") > 0) {
            this.pipeEnvironment = "cmd.exe";
            this.pipeArgument = "/C";
        } else if (new File("/bin/bash").exists()) {
            this.pipeEnvironment = "/bin/bash";
            this.pipeArgument = "-c";
        } else if (new File("/system/bin/sh").exists()) {
            this.pipeEnvironment = "/system/bin/sh";
            this.pipeArgument = "-c";
        } else {
            LOG.severe("Coud not find a command line environment (cmd.exe or /bin/bash)");
            throw new Error("Decoding via a pipe will not work: Coud not find a command line environment (cmd.exe or /bin/bash)");
        }
        String str = System.getenv("PATH");
        if (isAvailable("ffmpeg")) {
            LOG.info("found ffmpeg on the path (" + str + "). Will use ffmpeg for decoding media files.");
            StringBuilder sb = new StringBuilder();
            sb.append("ffmpeg");
            sb.append(" -ss %input_seeking%  %number_of_seconds% -i \"%resource%\" -vn -ar %sample_rate% -ac %channels% -sample_fmt s16 -f s16le pipe:1");
            this.pipeCommand = sb.toString();
            return;
        }
        if (isAvailable("avconv")) {
            LOG.info("found avconv on your path(" + str + "). Will use avconv for decoding media files.");
            StringBuilder sb2 = new StringBuilder();
            sb2.append("avconv");
            sb2.append(" -ss %input_seeking%  %number_of_seconds% -i \"%resource%\" -vn -ar %sample_rate% -ac %channels% -sample_fmt s16 -f s16le pipe:1");
            this.pipeCommand = sb2.toString();
            return;
        }
        if (isAndroid()) {
            String property = System.getProperty("java.io.tmpdir");
            this.printErrorstream = true;
            File file = new File(property, "ffmpeg");
            if (file.exists() && file.length() > TimeUtils.NANOS_PER_MS && file.canExecute()) {
                this.decoderBinaryAbsolutePath = file.getAbsolutePath();
            } else {
                LOG.severe("Could not find an ffmpeg binary for your Android system. Did you forget calling: 'new AndroidFFMPEGLocator(this);' ?");
                LOG.severe("Tried to unpack a statically compiled ffmpeg binary for your architecture to: " + file.getAbsolutePath());
            }
        } else {
            LOG.warning("Dit not find ffmpeg or avconv on your path(" + str + "), will try to download it automatically.");
            String strFfmpegBinary = new FFMPEGDownloader().ffmpegBinary();
            this.decoderBinaryAbsolutePath = strFfmpegBinary;
            if (strFfmpegBinary == null) {
                LOG.severe("Could not download an ffmpeg binary automatically for your system.");
            }
        }
        if (this.decoderBinaryAbsolutePath == null) {
            this.pipeCommand = "false";
            throw new Error("Decoding via a pipe will not work: Could not find an ffmpeg binary for your system");
        }
        this.pipeCommand = Typography.quote + this.decoderBinaryAbsolutePath + Typography.quote + " -ss %input_seeking%  %number_of_seconds% -i \"%resource%\" -vn -ar %sample_rate% -ac %channels% -sample_fmt s16 -f s16le pipe:1";
    }

    private boolean isAvailable(String str) {
        try {
            Runtime.getRuntime().exec(str + " -version");
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public PipeDecoder(String str, String str2, String str3, String str4, int i) {
        this.printErrorstream = false;
        this.pipeEnvironment = str;
        this.pipeArgument = str2;
        this.pipeCommand = str3;
        this.pipeBuffer = i;
    }

    public InputStream getDecodedStream(String str, int i, double d, double d2) {
        String strReplace;
        try {
            String strReplace2 = this.pipeCommand.replace("%input_seeking%", String.valueOf(d));
            if (d2 > 0.0d) {
                strReplace = strReplace2.replace("%number_of_seconds%", "-t " + String.valueOf(d2));
            } else {
                strReplace = strReplace2.replace("%number_of_seconds%", "");
            }
            String strReplace3 = strReplace.replace("%resource%", str).replace("%sample_rate%", String.valueOf(i)).replace("%channels%", "1");
            ProcessBuilder processBuilder = new ProcessBuilder(this.pipeEnvironment, this.pipeArgument, strReplace3);
            LOG.info("Starting piped decoding process for " + str);
            LOG.info(" with command: " + strReplace3);
            final Process processStart = processBuilder.start();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(processStart.getInputStream(), this.pipeBuffer) { // from class: com.byd.music.view.spectrum.PipeDecoder.1
                @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
                public void close() throws IOException {
                    super.close();
                    processStart.destroy();
                }
            };
            if (this.printErrorstream) {
                new ErrorStreamGobbler(processStart.getErrorStream(), LOG).start();
            }
            new Thread(new Runnable() { // from class: com.byd.music.view.spectrum.PipeDecoder.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        processStart.waitFor();
                        PipeDecoder.LOG.info("Finished piped decoding process");
                    } catch (InterruptedException e) {
                        PipeDecoder.LOG.severe("Interrupted while waiting for decoding sub process exit.");
                        e.printStackTrace();
                    }
                }
            }, "Decoding Pipe").start();
            return bufferedInputStream;
        } catch (IOException e) {
            LOG.warning("IO exception while decoding audio via sub process." + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public double getDuration(String str) {
        try {
            String strReplace = "ffmpeg -i \"%resource%\"".replace("%resource%", str);
            ProcessBuilder processBuilder = new ProcessBuilder(this.pipeEnvironment, this.pipeArgument, strReplace);
            LOG.info("Starting duration command for " + str);
            LOG.fine(" with command: " + strReplace);
            final Process processStart = processBuilder.start();
            new BufferedInputStream(processStart.getInputStream(), this.pipeBuffer) { // from class: com.byd.music.view.spectrum.PipeDecoder.3
                @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
                public void close() throws IOException {
                    super.close();
                    processStart.destroy();
                }
            };
            ErrorStreamStringGlobber errorStreamStringGlobber = new ErrorStreamStringGlobber(processStart.getErrorStream());
            errorStreamStringGlobber.start();
            new Thread(new Runnable() { // from class: com.byd.music.view.spectrum.PipeDecoder.4
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        processStart.waitFor();
                        PipeDecoder.LOG.info("Finished piped decoding process");
                    } catch (InterruptedException e) {
                        PipeDecoder.LOG.severe("Interrupted while waiting for decoding sub process exit.");
                        e.printStackTrace();
                    }
                }
            }, "Decoding Pipe").run();
            Matcher matcher = Pattern.compile(".*\\s.*Duration:\\s+(\\d\\d):(\\d\\d):(\\d\\d)\\.(\\d\\d), .*", 40).matcher(errorStreamStringGlobber.getErrorStreamAsString());
            if (!matcher.find()) {
                return -1.0d;
            }
            return (Integer.valueOf(matcher.group(1)).intValue() * 3600) + (Integer.valueOf(matcher.group(2)).intValue() * 60) + (Integer.valueOf(matcher.group(3)).intValue() * 1) + Double.valueOf(MediaMetrics.SEPARATOR + matcher.group(4)).doubleValue();
        } catch (IOException e) {
            LOG.warning("IO exception while decoding audio via sub process." + e.getMessage());
            e.printStackTrace();
            return -1.0d;
        }
    }

    public void printBinaryInfo() {
        try {
            Process processExec = Runtime.getRuntime().exec(this.decoderBinaryAbsolutePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(processExec.getErrorStream()));
            while (true) {
                String line = bufferedReader.readLine();
                if (line != null) {
                    System.out.println(line);
                } else {
                    bufferedReader.close();
                    processExec.waitFor();
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
    }

    public static TarsosDSPAudioFormat getTargetAudioFormat(int i) {
        float f = i;
        return new TarsosDSPAudioFormat(TarsosDSPAudioFormat.Encoding.PCM_SIGNED, f, 16, 1, 2, f, ByteOrder.BIG_ENDIAN.equals(ByteOrder.nativeOrder()));
    }

    private boolean isAndroid() {
        try {
            Class.forName("android.app.Activity");
            System.out.println("Running on Android!");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    private class ErrorStreamGobbler extends Thread {
        private final InputStream is;
        private final Logger logger;

        private ErrorStreamGobbler(InputStream inputStream, Logger logger) {
            this.is = inputStream;
            this.logger = logger;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.is));
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        return;
                    } else {
                        this.logger.info(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class ErrorStreamStringGlobber extends Thread {
        private final InputStream is;
        private final StringBuilder sb;

        private ErrorStreamStringGlobber(InputStream inputStream) {
            this.is = inputStream;
            this.sb = new StringBuilder();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.is));
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        return;
                    } else {
                        this.sb.append(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public String getErrorStreamAsString() {
            return this.sb.toString();
        }
    }
}
