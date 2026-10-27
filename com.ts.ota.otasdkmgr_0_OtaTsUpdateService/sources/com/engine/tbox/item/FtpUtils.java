package com.engine.tbox.item;

import com.engine.tbox.item.DownloadProcess;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import org.apache.commons.net.ProtocolCommandEvent;
import org.apache.commons.net.ProtocolCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/* JADX INFO: loaded from: classes.dex */
public class FtpUtils {
    private static final int DATA_TIMEOUT = 10000;
    private static final int FILE_NOT_EXSIST_CODE = 550;
    private static final int HUNDRED = 100;
    private static final int ONE_K_SIZE = 1024;
    private static final String PASS_COMMAND = "PASS";
    private static final String USER_COMMAND = "USER";

    public static boolean uploadFile(String remoteFile, String localFile, String userName, String password, int port) {
        FTPClient ftpClient = new FTPClient();
        ftpClient.setDataTimeout(DATA_TIMEOUT);
        File file = new File(localFile);
        if (!file.exists()) {
            System.err.println("FtpClient uploadFile local file:" + localFile + " is no exists");
            return false;
        }
        InputStream is = null;
        try {
            try {
                ftpClient.connect(TBoxConnection.REMOTE_IP, port);
                int reply = ftpClient.getReplyCode();
                if (!FTPReply.isPositiveCompletion(reply)) {
                    System.out.println("ftpClient upload disconnect");
                    ftpClient.disconnect();
                    System.out.println("ftpClient upload finally logout, close, disconnect");
                    try {
                        if (ftpClient.isConnected()) {
                            ftpClient.logout();
                            ftpClient.disconnect();
                        }
                        if (0 != 0) {
                            is.close();
                        }
                    } catch (IOException exp) {
                        exp.printStackTrace();
                    }
                    return false;
                }
                ftpClient.login(userName, password);
                ftpClient.setFileType(2);
                ftpClient.enterLocalPassiveMode();
                ftpClient.changeWorkingDirectory("/");
                InputStream is2 = new FileInputStream(file);
                boolean zStoreFile = ftpClient.storeFile(file.getName(), is2);
                System.out.println("ftpClient upload finally logout, close, disconnect");
                try {
                    if (ftpClient.isConnected()) {
                        ftpClient.logout();
                        ftpClient.disconnect();
                    }
                    is2.close();
                } catch (IOException exp2) {
                    exp2.printStackTrace();
                }
                return zStoreFile;
            } catch (IOException exp3) {
                exp3.printStackTrace();
                System.out.println("ftpClient upload finally logout, close, disconnect");
                try {
                    if (ftpClient.isConnected()) {
                        ftpClient.logout();
                        ftpClient.disconnect();
                    }
                    if (0 != 0) {
                        is.close();
                    }
                } catch (IOException exp4) {
                    exp4.printStackTrace();
                }
                return false;
            }
        } catch (Throwable th) {
            System.out.println("ftpClient upload finally logout, close, disconnect");
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
                if (0 != 0) {
                    is.close();
                }
            } catch (IOException exp5) {
                exp5.printStackTrace();
            }
            throw th;
        }
    }

    public static String formatFileSize(long fileSize) {
        String[] suffix = {"Bytes", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB", "BB", "NB", "DB"};
        if (fileSize == 0) {
            return "0Bytes";
        }
        DecimalFormat df = new DecimalFormat("#.00");
        int floor = (int) Math.floor(Math.log(fileSize) / Math.log(1024.0d));
        double value = fileSize / Math.pow(1024.0d, floor);
        String format = df.format(value);
        return format + suffix[floor];
    }

    public static long countDirectoryFileSize(FTPClient ftpClient, String path, long currentSize) {
        try {
            FTPFile[] ftpFiles = ftpClient.listFiles(path);
            if (ftpFiles != null) {
                System.out.println(":----------------------: current path:" + path + " currentSize:" + formatFileSize(currentSize));
                for (FTPFile ftpFile : ftpFiles) {
                    if (ftpFile.isDirectory()) {
                        if (!ftpFile.getName().equals(".") && !ftpFile.getName().equals("..")) {
                            System.out.println("ftpFile:" + ftpFile.getName());
                            currentSize = countDirectoryFileSize(ftpClient, path + ftpFile.getName() + "/", currentSize);
                        }
                    } else {
                        currentSize += ftpFile.getSize();
                        System.out.println("ftpFile:" + ftpFile.getName() + " size:" + formatFileSize(ftpFile.getSize()));
                    }
                }
            }
            return currentSize;
        } catch (IOException exp) {
            exp.printStackTrace();
            return currentSize;
        }
    }

    public static boolean downloadEachFile(FTPClient ftpClient, String localPath, String remoteFile) throws IOException {
        System.out.println("download file from " + remoteFile + " to " + localPath);
        File file = new File(localPath);
        FileOutputStream os = null;
        boolean downloadResult = false;
        try {
            try {
                os = new FileOutputStream(file);
                downloadResult = ftpClient.retrieveFile(remoteFile, os);
                if (!downloadResult) {
                    int code = ftpClient.getReplyCode();
                    System.out.println("download file:" + remoteFile + " result:" + downloadResult + " errorCode:" + code);
                    if (code == 550) {
                        System.out.println("downloadResult:true os:" + os);
                        try {
                            os.close();
                        } catch (IOException exp) {
                            exp.printStackTrace();
                        }
                        return true;
                    }
                }
                System.out.print("download file:" + remoteFile + " result:" + downloadResult);
                os.getFD().sync();
                System.out.println("downloadResult:" + downloadResult + " os:" + os);
                try {
                    os.close();
                } catch (IOException exp2) {
                    exp2.printStackTrace();
                }
                return downloadResult;
            } catch (Throwable th) {
                System.out.println("downloadResult:" + downloadResult + " os:" + os);
                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException exp3) {
                        exp3.printStackTrace();
                    }
                }
                return downloadResult;
            }
        } catch (IOException exp4) {
            exp4.printStackTrace();
            System.out.println("downloadResult:false os:" + os);
            if (os != null) {
                try {
                    os.close();
                } catch (IOException exp5) {
                    exp5.printStackTrace();
                }
            }
            return false;
        }
    }

    public static boolean downloadAllFile(FTPClient ftpClient, String localPath, String path, DownloadProcess process) {
        if (process.isStopCallback()) {
            return false;
        }
        try {
            FTPFile[] ftpFiles = ftpClient.listFiles(path);
            if (ftpFiles != null) {
                System.out.println(":*******************:download current path:" + path + " current process:" + process);
                int length = ftpFiles.length;
                for (int i = 0; i < length; i++) {
                    System.out.println("ftpFile:" + ftpFiles[i]);
                }
                for (FTPFile ftpFile : ftpFiles) {
                    if (ftpFile.isDirectory()) {
                        if (!ftpFile.getName().equals(".") && !ftpFile.getName().equals("..")) {
                            File file = new File(localPath + "/" + ftpFile.getName());
                            if (!file.exists() && !file.mkdirs()) {
                                System.out.println("downloadAllfile create failed:" + localPath + "/" + ftpFile.getName());
                            } else {
                                System.out.println("create the file dir:" + file.getPath());
                                if (!downloadAllFile(ftpClient, localPath + "/" + ftpFile.getName(), path + ftpFile.getName() + "/", process)) {
                                    System.out.println("download " + localPath + "/" + ftpFile.getName() + " failed");
                                    return false;
                                }
                            }
                        }
                    } else {
                        if (downloadEachFile(ftpClient, localPath + "/" + ftpFile.getName(), path + ftpFile.getName())) {
                            process.downloadSize(ftpFile.getSize());
                            System.out.println(" download file:" + ftpFile.getName() + " download process:" + process);
                            process.notifyProcess(process.getProcess());
                        } else {
                            System.out.println("download file:" + path + ftpFile.getName() + " failed");
                            return false;
                        }
                    }
                    if (process.isStopCallback()) {
                        return false;
                    }
                }
                return true;
            }
            return true;
        } catch (IOException exp) {
            exp.printStackTrace();
            return false;
        }
    }

    public static boolean downloadFile(String remoteFile, String savePath, String userName, String pass, int port, DownloadProcess.FtpProcessCallback callback) throws Throwable {
        FTPClient ftpClient = new FTPClient();
        ftpClient.setDataTimeout(DATA_TIMEOUT);
        OutputStream os = null;
        if (callback.isStopCallback()) {
            return false;
        }
        try {
            try {
                ftpClient.addProtocolCommandListener(new ProtocolCommandListener() { // from class: com.engine.tbox.item.FtpUtils.1
                    @Override // org.apache.commons.net.ProtocolCommandListener
                    public void protocolCommandSent(ProtocolCommandEvent protocolCommandEvent) {
                        if (!FtpUtils.PASS_COMMAND.equals(protocolCommandEvent.getCommand()) && !FtpUtils.USER_COMMAND.equals(protocolCommandEvent.getCommand())) {
                            System.out.println("protocolCommandSent command:" + protocolCommandEvent.getCommand() + " replyCode:" + protocolCommandEvent.getReplyCode() + " message:" + protocolCommandEvent.getMessage());
                            return;
                        }
                        System.out.println("protocolCommandSent command:" + protocolCommandEvent.getCommand() + " replyCode:" + protocolCommandEvent.getReplyCode());
                    }

                    @Override // org.apache.commons.net.ProtocolCommandListener
                    public void protocolReplyReceived(ProtocolCommandEvent protocolCommandEvent) {
                        System.out.println("protocolReplyReceived command:" + protocolCommandEvent.getCommand() + " replyCode:" + protocolCommandEvent.getReplyCode() + " message:" + protocolCommandEvent.getMessage());
                    }
                });
                try {
                    ftpClient.connect(TBoxConnection.REMOTE_IP, port);
                    int reply = ftpClient.getReplyCode();
                    if (!FTPReply.isPositiveCompletion(reply)) {
                        System.out.println("ftpClient download disconnect");
                        callback.result(2);
                        ftpClient.disconnect();
                        System.out.println("ftpClient download finally logout, close, disconnect");
                        try {
                            if (ftpClient.isConnected()) {
                                ftpClient.logout();
                                ftpClient.disconnect();
                            }
                            if (0 != 0) {
                                os.close();
                            }
                        } catch (IOException exp) {
                            exp.printStackTrace();
                        }
                        return false;
                    }
                    try {
                        ftpClient.login(userName, pass);
                        ftpClient.setFileType(2);
                        ftpClient.enterLocalPassiveMode();
                        long totalFileSize = countDirectoryFileSize(ftpClient, "/", 0L);
                        System.out.println("total size:" + formatFileSize(totalFileSize));
                        DownloadProcess downloadProcess = new DownloadProcess(totalFileSize, callback);
                        try {
                            boolean res = downloadAllFile(ftpClient, savePath, "/", downloadProcess);
                            if (callback.isStopCallback()) {
                                System.out.println("ftpClient download finally logout, close, disconnect");
                                try {
                                    if (ftpClient.isConnected()) {
                                        ftpClient.logout();
                                        ftpClient.disconnect();
                                    }
                                    if (0 != 0) {
                                        os.close();
                                    }
                                } catch (IOException exp2) {
                                    exp2.printStackTrace();
                                }
                                return false;
                            }
                            System.out.println("download result:" + res);
                            if (res) {
                                System.out.println("download success");
                                downloadProcess.notifyProcess(100);
                                downloadProcess.notifyResult(0);
                            } else {
                                downloadProcess.notifyResult(1);
                            }
                            System.out.println("ftpClient download finally logout, close, disconnect");
                            try {
                                if (ftpClient.isConnected()) {
                                    ftpClient.logout();
                                    ftpClient.disconnect();
                                }
                                if (0 != 0) {
                                    os.close();
                                }
                            } catch (IOException exp3) {
                                exp3.printStackTrace();
                            }
                            return true;
                        } catch (IOException e) {
                            e = e;
                        }
                    } catch (IOException e2) {
                        e = e2;
                    } catch (Throwable th) {
                        th = th;
                        Throwable th2 = th;
                        System.out.println("ftpClient download finally logout, close, disconnect");
                        try {
                            if (ftpClient.isConnected()) {
                                ftpClient.logout();
                                ftpClient.disconnect();
                            }
                            if (0 == 0) {
                                throw th2;
                            }
                            os.close();
                            throw th2;
                        } catch (IOException exp4) {
                            exp4.printStackTrace();
                            throw th2;
                        }
                    }
                } catch (IOException e3) {
                    e = e3;
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (IOException e4) {
                e = e4;
            } catch (Throwable th4) {
                th = th4;
            }
            IOException exp5 = e;
            exp5.printStackTrace();
            callback.result(2);
            System.out.println("ftpClient download finally logout, close, disconnect");
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
                if (0 == 0) {
                    return false;
                }
                os.close();
                return false;
            } catch (IOException exp6) {
                exp6.printStackTrace();
                return false;
            }
        } catch (Throwable th5) {
            th = th5;
        }
    }
}
