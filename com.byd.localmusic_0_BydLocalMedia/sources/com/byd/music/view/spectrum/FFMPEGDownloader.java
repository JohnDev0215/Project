package com.byd.music.view.spectrum;

import android.telecom.Logging.Session;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes5.dex */
public class FFMPEGDownloader {
    private static final Logger LOG = Logger.getLogger(FFMPEGDownloader.class.getName());
    private static String url = "https://0110.be/releases/TarsosDSP/TarsosDSP-static-ffmpeg/";
    private final String ffmpegBinary;

    public FFMPEGDownloader() {
        String str = operatingSystemName() + Session.SESSION_SEPARATION_CHAR_CHILD + processorArchitecture() + "_ffmpeg" + suffix();
        url += str;
        String absolutePath = new File(System.getProperty("java.io.tmpdir"), str).getAbsolutePath();
        if (new File(absolutePath).exists()) {
            LOG.info("Found an already download ffmpeg static binary: " + absolutePath);
            this.ffmpegBinary = absolutePath;
            return;
        }
        LOG.info("Started downloading an ffmpeg static binary from  " + url);
        downloadExecutable(absolutePath);
        if (new File(absolutePath).exists()) {
            LOG.info("Downloaded an ffmpeg static binary. Stored at: " + absolutePath);
            new File(absolutePath).setExecutable(true);
            this.ffmpegBinary = absolutePath;
            return;
        }
        LOG.warning("Unable to find or download an ffmpeg static binary.  " + str);
        this.ffmpegBinary = null;
    }

    public String ffmpegBinary() {
        String str = this.ffmpegBinary;
        if (str != null) {
            return str.replace(suffix(), "");
        }
        return null;
    }

    private void downloadExecutable(String str) {
        try {
            ReadableByteChannel readableByteChannelNewChannel = Channels.newChannel(new URL(url).openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            fileOutputStream.getChannel().transferFrom(readableByteChannelNewChannel, 0L, Long.MAX_VALUE);
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private String operatingSystemName() {
        String lowerCase = System.getProperty("os.name").toLowerCase();
        if (lowerCase.indexOf("indows") > 0) {
            return "windows";
        }
        if (lowerCase.indexOf("nux") >= 0) {
            return "linux";
        }
        if (lowerCase.indexOf("mac") >= 0) {
            return "mac_os_x";
        }
        return null;
    }

    private String processorArchitecture() {
        boolean z = true;
        if (!System.getProperty("os.name").contains("Windows") ? System.getProperty("os.arch").indexOf("64") == -1 : System.getenv("ProgramFiles(x86)") == null) {
            z = false;
        }
        return z ? "64_bits" : "32_bits";
    }

    private String suffix() {
        return System.getProperty("os.name").contains("Windows") ? ".exe" : "";
    }

    public static void main(String... strArr) {
        new FFMPEGDownloader();
    }
}
