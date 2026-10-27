package com.android.bluetooth.btservice.bluetoothkeystore;

import android.net.INetd;
import android.os.SystemProperties;
import android.security.keystore.AndroidKeyStoreProvider;
import android.security.keystore.KeyGenParameterSpec;
import android.util.Log;
import androidx.core.view.PointerIconCompat;
import com.android.bluetooth.BluetoothKeystoreProto;
import com.android.bluetooth.avrcpcontroller.AvrcpControllerStateMachine;
import com.google.common.primitives.UnsignedBytes;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.ProviderException;
import java.security.UnrecoverableEntryException;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothKeystoreService {
    private static final int BUFFER_SIZE = 4000;
    private static final String CIPHER_ALGORITHM = "AES/GCM/NoPadding";
    private static final String CONFIG_BACKUP_CHECKSUM_PATH = "/data/misc/bluedroid/bt_config.bak.encrypted-checksum";
    private static final int CONFIG_BACKUP_COMPARE_PASS = 2;
    private static final String CONFIG_BACKUP_ENCRYPTION_PATH = "/data/misc/bluedroid/bt_config.bak.encrypted";
    private static final String CONFIG_BACKUP_PATH = "/data/misc/bluedroid/bt_config.bak";
    private static final String CONFIG_BACKUP_PREFIX = "bt_config-backup";
    private static final String CONFIG_CHECKSUM_ENCRYPTION_PATH = "/data/misc/bluedroid/bt_config.checksum.encrypted";
    private static final int CONFIG_COMPARE_INIT = 0;
    private static final String CONFIG_FILE_CHECKSUM_PATH = "/data/misc/bluedroid/bt_config.conf.encrypted-checksum";
    private static final int CONFIG_FILE_COMPARE_PASS = 1;
    private static final String CONFIG_FILE_ENCRYPTION_PATH = "/data/misc/bluedroid/bt_config.conf.encrypted";
    private static final String CONFIG_FILE_HASH = "hash";
    private static final String CONFIG_FILE_PATH = "/data/misc/bluedroid/bt_config.conf";
    private static final String CONFIG_FILE_PREFIX = "bt_config-origin";
    private static final boolean DBG = false;
    private static final int GCM_TAG_LENGTH = 128;
    private static final String KEYALIAS = "bluetooth-key-encrypted";
    private static final int KEY_LENGTH = 256;
    private static final String KEY_STORE = "AndroidKeyStore";
    private static final String TAG = "BluetoothKeystoreService";
    private static final int TRY_MAX = 3;
    private static BluetoothKeystoreService sBluetoothKeystoreService;
    BluetoothKeystoreNativeInterface mBluetoothKeystoreNativeInterface;
    private boolean mCleaningUp;
    private int mCompareResult;
    private ComputeDataThread mDecryptDataThread;
    private ComputeDataThread mEncryptDataThread;
    private boolean mIsNiapMode;
    private Map<String, String> mNameEncryptKey = new HashMap();
    private Map<String, String> mNameDecryptKey = new HashMap();
    private BlockingQueue<String> mPendingDecryptKey = new LinkedBlockingQueue();
    private BlockingQueue<String> mPendingEncryptKey = new LinkedBlockingQueue();
    private final List<String> mEncryptKeyNameList = List.of("LinkKey", "LE_KEY_PENC", "LE_KEY_PID", "LE_KEY_LID", "LE_KEY_PCSRK", "LE_KEY_LENC", "LE_KEY_LCSRK");
    private Base64.Decoder mDecoder = Base64.getDecoder();
    private Base64.Encoder mEncoder = Base64.getEncoder();

    public BluetoothKeystoreService(boolean isNiapMode) {
        debugLog("new BluetoothKeystoreService isNiapMode: " + isNiapMode);
        this.mIsNiapMode = isNiapMode;
        this.mCompareResult = 0;
        startThread();
    }

    public void start() {
        debugLog(AvrcpControllerStateMachine.KEY_START);
        if (sBluetoothKeystoreService != null) {
            errorLog("start() called twice");
            return;
        }
        KeyStore keyStore = getKeyStore();
        if (keyStore == null) {
            debugLog("cannot find the keystore.");
            return;
        }
        BluetoothKeystoreNativeInterface bluetoothKeystoreNativeInterface = BluetoothKeystoreNativeInterface.getInstance();
        Objects.requireNonNull(bluetoothKeystoreNativeInterface, "BluetoothKeystoreNativeInterface cannot be null when BluetoothKeystore starts");
        this.mBluetoothKeystoreNativeInterface = bluetoothKeystoreNativeInterface;
        setBluetoothKeystoreService(this);
        try {
            if (!keyStore.containsAlias(KEYALIAS) && this.mIsNiapMode) {
                infoLog("Enable NIAP mode for the first time, pass hash check.");
                this.mCompareResult = 3;
            } else {
                loadConfigData();
            }
        } catch (KeyStoreException e) {
            reportKeystoreException(e, "cannot find the keystore");
        }
    }

    public void factoryReset() {
        try {
            cleanupAll();
        } catch (IOException e) {
            reportBluetoothKeystoreException(e, "IO error while file operating.");
        }
    }

    public void cleanup() {
        debugLog("cleanup");
        if (this.mCleaningUp) {
            debugLog("already doing cleanup");
        }
        this.mCleaningUp = true;
        if (sBluetoothKeystoreService == null) {
            debugLog("cleanup() called before start()");
            return;
        }
        setBluetoothKeystoreService(null);
        this.mBluetoothKeystoreNativeInterface.cleanup();
        this.mBluetoothKeystoreNativeInterface = null;
        if (this.mIsNiapMode) {
            cleanupForNiapModeEnable();
        } else {
            cleanupForNiapModeDisable();
        }
    }

    public void cleanupForNiapModeEnable() {
        try {
            setEncryptKeyOrRemoveKey(CONFIG_FILE_PREFIX, CONFIG_FILE_HASH);
        } catch (IOException e) {
            reportBluetoothKeystoreException(e, "IO error while file operating.");
        } catch (InterruptedException e2) {
            reportBluetoothKeystoreException(e2, "Interrupted while operating.");
        } catch (NoSuchAlgorithmException e3) {
            reportBluetoothKeystoreException(e3, "encrypt could not find the algorithm: SHA256");
        }
        cleanupMemory();
        stopThread();
    }

    public void cleanupForNiapModeDisable() {
        this.mNameDecryptKey.clear();
        this.mNameEncryptKey.clear();
    }

    public void loadConfigData() {
        try {
            debugLog("loadConfigData");
            if (isFactoryReset()) {
                cleanupAll();
            }
            if (Files.exists(Paths.get(CONFIG_CHECKSUM_ENCRYPTION_PATH, new String[0]), new LinkOption[0])) {
                debugLog("Load encryption file.");
                loadEncryptionFile(CONFIG_CHECKSUM_ENCRYPTION_PATH, false);
                if (compareFileHash(CONFIG_FILE_PATH)) {
                    debugLog("bt_config.conf checksum pass.");
                    this.mCompareResult |= 1;
                }
                if (compareFileHash(CONFIG_BACKUP_PATH)) {
                    debugLog("bt_config.bak checksum pass.");
                    this.mCompareResult |= 2;
                }
                if (doesComparePass(1)) {
                    loadEncryptionFile(CONFIG_FILE_ENCRYPTION_PATH, true);
                } else if (doesComparePass(2)) {
                    Files.deleteIfExists(Paths.get(CONFIG_FILE_ENCRYPTION_PATH, new String[0]));
                    this.mNameEncryptKey.remove(CONFIG_FILE_PREFIX);
                    loadEncryptionFile(CONFIG_BACKUP_ENCRYPTION_PATH, true);
                } else {
                    if (this.mIsNiapMode) {
                        debugLog("Config file conf and bak checksum check fail.");
                    }
                    cleanupAll();
                    return;
                }
            }
            if (!this.mIsNiapMode) {
                stopThread();
                cleanupFile();
            }
        } catch (IOException e) {
            reportBluetoothKeystoreException(e, "IO error while file operating.");
        } catch (InterruptedException e2) {
            reportBluetoothKeystoreException(e2, "Interrupted while operating.");
        } catch (NoSuchAlgorithmException e3) {
            reportBluetoothKeystoreException(e3, "could not find the algorithm: SHA256");
        }
    }

    private boolean isFactoryReset() {
        return SystemProperties.getBoolean("persist.bluetooth.factoryreset", false);
    }

    public void initJni() {
        debugLog("initJni()");
        this.mBluetoothKeystoreNativeInterface.init();
    }

    private boolean isAvailable() {
        return !this.mCleaningUp;
    }

    public static synchronized BluetoothKeystoreService getBluetoothKeystoreService() {
        if (sBluetoothKeystoreService == null) {
            debugLog("getBluetoothKeystoreService(): service is NULL");
            return null;
        }
        if (!sBluetoothKeystoreService.isAvailable()) {
            debugLog("getBluetoothKeystoreService(): service is not available");
            return null;
        }
        return sBluetoothKeystoreService;
    }

    private static synchronized void setBluetoothKeystoreService(BluetoothKeystoreService instance) {
        debugLog("setBluetoothKeystoreService(): set to: " + instance);
        sBluetoothKeystoreService = instance;
    }

    public int getCompareResult() {
        debugLog("getCompareResult: " + this.mCompareResult);
        return this.mCompareResult;
    }

    public void setEncryptKeyOrRemoveKey(String prefixString, String decryptedString) throws InterruptedException, NoSuchAlgorithmException, IOException {
        infoLog("setEncryptKeyOrRemoveKey: prefix: " + prefixString);
        if (prefixString == null || decryptedString == null) {
            return;
        }
        if (prefixString.equals(CONFIG_FILE_PREFIX)) {
            if (decryptedString.isEmpty()) {
                cleanupAll();
                return;
            }
            if (decryptedString.equals(CONFIG_FILE_HASH)) {
                backupConfigEncryptionFile();
                readHashFile(CONFIG_FILE_PATH, CONFIG_FILE_PREFIX);
                if (this.mNameDecryptKey.containsKey(CONFIG_FILE_PREFIX) && this.mNameDecryptKey.get(CONFIG_FILE_PREFIX).equals(this.mNameDecryptKey.get(CONFIG_BACKUP_PREFIX))) {
                    infoLog("Since the hash is same with previous, don't need encrypt again.");
                } else {
                    this.mPendingEncryptKey.put(prefixString);
                }
                saveEncryptedKey();
                return;
            }
            return;
        }
        if (decryptedString.isEmpty()) {
            this.mNameDecryptKey.remove(prefixString);
            this.mNameEncryptKey.remove(prefixString);
        } else {
            this.mNameDecryptKey.put(prefixString, decryptedString);
            this.mPendingEncryptKey.put(prefixString);
        }
    }

    public void cleanupAll() throws IOException {
        cleanupFile();
        cleanupMemory();
    }

    private void cleanupFile() throws IOException {
        Files.deleteIfExists(Paths.get(CONFIG_CHECKSUM_ENCRYPTION_PATH, new String[0]));
        Files.deleteIfExists(Paths.get(CONFIG_FILE_ENCRYPTION_PATH, new String[0]));
        Files.deleteIfExists(Paths.get(CONFIG_BACKUP_ENCRYPTION_PATH, new String[0]));
    }

    public void cleanupMemory() {
        stopThread();
        this.mNameEncryptKey.clear();
        this.mNameDecryptKey.clear();
        startThread();
    }

    public void stopThread() {
        try {
            if (this.mEncryptDataThread != null) {
                this.mEncryptDataThread.setWaitQueueEmptyForStop();
                this.mEncryptDataThread.join();
            }
            if (this.mDecryptDataThread != null) {
                this.mDecryptDataThread.setWaitQueueEmptyForStop();
                this.mDecryptDataThread.join();
            }
        } catch (InterruptedException e) {
            reportBluetoothKeystoreException(e, "Interrupted while operating.");
        }
    }

    private void startThread() {
        this.mEncryptDataThread = new ComputeDataThread(true);
        this.mDecryptDataThread = new ComputeDataThread(false);
        this.mEncryptDataThread.start();
        this.mDecryptDataThread.start();
    }

    public String getKey(String prefixString) {
        infoLog("getKey: prefix: " + prefixString);
        if (!this.mNameDecryptKey.containsKey(prefixString)) {
            return null;
        }
        return this.mNameDecryptKey.get(prefixString);
    }

    public void saveEncryptedKey() {
        stopThread();
        List<String> configEncryptedLines = new LinkedList<>();
        List<String> keyEncryptedLines = new LinkedList<>();
        for (String key : this.mNameEncryptKey.keySet()) {
            if (key.equals(CONFIG_FILE_PREFIX) || key.equals(CONFIG_BACKUP_PREFIX)) {
                configEncryptedLines.add(getEncryptedKeyData(key));
            } else {
                keyEncryptedLines.add(getEncryptedKeyData(key));
            }
        }
        startThread();
        try {
            if (!configEncryptedLines.isEmpty()) {
                Files.write(Paths.get(CONFIG_CHECKSUM_ENCRYPTION_PATH, new String[0]), configEncryptedLines, new OpenOption[0]);
            }
            if (!keyEncryptedLines.isEmpty()) {
                Files.write(Paths.get(CONFIG_FILE_ENCRYPTION_PATH, new String[0]), keyEncryptedLines, new OpenOption[0]);
            }
        } catch (IOException e) {
            throw new RuntimeException("write encryption file fail");
        }
    }

    private String getEncryptedKeyData(String prefixString) {
        if (prefixString == null) {
            return null;
        }
        return prefixString.concat("-").concat(this.mNameEncryptKey.get(prefixString));
    }

    public Map<String, String> getNameEncryptKey() {
        return this.mNameEncryptKey;
    }

    public Map<String, String> getNameDecryptKey() {
        return this.mNameDecryptKey;
    }

    private void backupConfigEncryptionFile() throws IOException {
        if (Files.exists(Paths.get(CONFIG_FILE_ENCRYPTION_PATH, new String[0]), new LinkOption[0])) {
            Files.move(Paths.get(CONFIG_FILE_ENCRYPTION_PATH, new String[0]), Paths.get(CONFIG_BACKUP_ENCRYPTION_PATH, new String[0]), StandardCopyOption.REPLACE_EXISTING);
        }
        if (this.mNameEncryptKey.containsKey(CONFIG_FILE_PREFIX)) {
            Map<String, String> map = this.mNameEncryptKey;
            map.put(CONFIG_BACKUP_PREFIX, map.get(CONFIG_FILE_PREFIX));
        }
        if (this.mNameDecryptKey.containsKey(CONFIG_FILE_PREFIX)) {
            Map<String, String> map2 = this.mNameDecryptKey;
            map2.put(CONFIG_BACKUP_PREFIX, map2.get(CONFIG_FILE_PREFIX));
        }
    }

    private boolean doesComparePass(int item) {
        return (this.mCompareResult & item) == item;
    }

    public boolean compareFileHash(String hashFilePathString) throws NoSuchAlgorithmException, IOException {
        if (!Files.exists(Paths.get(hashFilePathString, new String[0]), new LinkOption[0])) {
            infoLog("compareFileHash: File does not exist, path: " + hashFilePathString);
            return false;
        }
        String prefixString = null;
        if (CONFIG_FILE_PATH.equals(hashFilePathString)) {
            prefixString = CONFIG_FILE_PREFIX;
        } else if (CONFIG_BACKUP_PATH.equals(hashFilePathString)) {
            prefixString = CONFIG_BACKUP_PREFIX;
        }
        if (prefixString == null) {
            errorLog("compareFileHash: Unexpected hash file path: " + hashFilePathString);
            return false;
        }
        readHashFile(hashFilePathString, prefixString);
        if (!this.mNameEncryptKey.containsKey(prefixString)) {
            errorLog("compareFileHash: NameEncryptKey doesn't contain the key, prefix:" + prefixString);
            return false;
        }
        String encryptedData = this.mNameEncryptKey.get(prefixString);
        String decryptedData = tryCompute(encryptedData, false);
        if (decryptedData == null) {
            errorLog("compareFileHash: decrypt encrypted hash data fail, prefix: " + prefixString);
            return false;
        }
        return decryptedData.equals(this.mNameDecryptKey.get(prefixString));
    }

    private void readHashFile(String filePathString, String prefixString) throws NoSuchAlgorithmException, IOException {
        byte[] dataBuffer = new byte[BUFFER_SIZE];
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        InputStream fileStream = Files.newInputStream(Paths.get(filePathString, new String[0]), new OpenOption[0]);
        while (true) {
            int bytesRead = fileStream.read(dataBuffer);
            if (bytesRead == -1) {
                break;
            } else {
                messageDigest.update(dataBuffer, 0, bytesRead);
            }
        }
        byte[] messageDigestBytes = messageDigest.digest();
        StringBuffer hashString = new StringBuffer();
        for (byte b : messageDigestBytes) {
            hashString.append(Integer.toString((b & UnsignedBytes.MAX_VALUE) + 256, 16).substring(1));
        }
        this.mNameDecryptKey.put(prefixString, hashString.toString());
    }

    private void readChecksumFile(String filePathString, String prefixString) throws IOException {
        if (!Files.exists(Paths.get(filePathString, new String[0]), new LinkOption[0])) {
            return;
        }
        byte[] allBytes = Files.readAllBytes(Paths.get(filePathString, new String[0]));
        String checksumDataBase64 = this.mEncoder.encodeToString(allBytes);
        this.mNameEncryptKey.put(prefixString, checksumDataBase64);
    }

    public void parseConfigFile(String filePathString) throws InterruptedException, IOException {
        String name = null;
        if (!Files.exists(Paths.get(filePathString, new String[0]), new LinkOption[0])) {
            return;
        }
        List<String> allLinesString = Files.readAllLines(Paths.get(filePathString, new String[0]));
        for (String line : allLinesString) {
            if (line.startsWith("[")) {
                name = line.replace("[", INetd.NEXTHOP_NONE).replace("]", INetd.NEXTHOP_NONE);
            } else {
                int index = line.indexOf(" = ");
                if (index >= 0) {
                    String key = line.substring(0, index);
                    if (this.mEncryptKeyNameList.contains(key) && name != null) {
                        String prefixString = name + "-" + key;
                        String dataString = line.substring(index + 3);
                        if (dataString.length() != 0) {
                            this.mNameDecryptKey.put(prefixString, dataString);
                            this.mPendingEncryptKey.put(prefixString);
                        }
                    }
                }
            }
        }
    }

    public void loadEncryptionFile(String filePathString, boolean doDecrypt) throws InterruptedException {
        try {
            if (!Files.exists(Paths.get(filePathString, new String[0]), new LinkOption[0])) {
                return;
            }
            List<String> allLinesString = Files.readAllLines(Paths.get(filePathString, new String[0]));
            for (String line : allLinesString) {
                int index = line.lastIndexOf("-");
                if (index >= 0) {
                    String prefixString = line.substring(0, index);
                    String encryptedString = line.substring(index + 1);
                    this.mNameEncryptKey.put(prefixString, encryptedString);
                    if (doDecrypt) {
                        this.mPendingDecryptKey.put(prefixString);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("read encryption file all line fail");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String tryCompute(String sourceData, boolean doEncrypt) {
        String targetData = null;
        if (sourceData == null) {
            return null;
        }
        for (int counter = 0; targetData == null && counter < 3; counter++) {
            if (doEncrypt) {
                targetData = encrypt(sourceData);
            } else {
                targetData = decrypt(sourceData);
            }
        }
        return targetData;
    }

    private String encrypt(String data) {
        try {
            if (data == null) {
                errorLog("encrypt: data is null");
                return null;
            }
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            SecretKey secretKeyReference = getOrCreateSecretKey();
            if (secretKeyReference != null) {
                cipher.init(1, secretKeyReference);
                BluetoothKeystoreProto.EncryptedData protobuf = BluetoothKeystoreProto.EncryptedData.newBuilder().setEncryptedData(ByteString.copyFrom(cipher.doFinal(data.getBytes()))).setInitVector(ByteString.copyFrom(cipher.getIV())).build();
                byte[] outputBytes = protobuf.toByteArray();
                if (outputBytes == null) {
                    errorLog("encrypt: Failed to serialize EncryptedData protobuf.");
                    return null;
                }
                String outputBase64 = this.mEncoder.encodeToString(outputBytes);
                return outputBase64;
            }
            errorLog("encrypt: secretKeyReference is null.");
            return null;
        } catch (InvalidKeyException e) {
            reportKeystoreException(e, "encrypt received an invalid key");
            return null;
        } catch (NoSuchAlgorithmException e2) {
            reportKeystoreException(e2, "encrypt could not find the algorithm: AES/GCM/NoPadding");
            return null;
        } catch (BadPaddingException e3) {
            reportKeystoreException(e3, "encrypt had a padding problem");
            return null;
        } catch (IllegalBlockSizeException e4) {
            reportKeystoreException(e4, "encrypt had an illegal block size");
            return null;
        } catch (NoSuchPaddingException e5) {
            reportKeystoreException(e5, "encrypt had a padding exception");
            return null;
        }
    }

    private String decrypt(String encryptedDataBase64) {
        try {
            if (encryptedDataBase64 == null) {
                errorLog("decrypt: encryptedDataBase64 is null");
                return null;
            }
            byte[] encryptedDataBytes = this.mDecoder.decode(encryptedDataBase64);
            BluetoothKeystoreProto.EncryptedData protobuf = BluetoothKeystoreProto.EncryptedData.parser().parseFrom(encryptedDataBytes);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            GCMParameterSpec spec = new GCMParameterSpec(128, protobuf.getInitVector().toByteArray());
            SecretKey secretKeyReference = getOrCreateSecretKey();
            if (secretKeyReference == null) {
                errorLog("decrypt: secretKeyReference is null.");
                return null;
            }
            cipher.init(2, secretKeyReference, spec);
            byte[] decryptedDataBytes = cipher.doFinal(protobuf.getEncryptedData().toByteArray());
            String output = new String(decryptedDataBytes);
            return output;
        } catch (InvalidProtocolBufferException e) {
            reportBluetoothKeystoreException(e, "decrypt: Failed to parse EncryptedData protobuf.");
            return null;
        } catch (InvalidAlgorithmParameterException e2) {
            reportKeystoreException(e2, "decrypt had an invalid algorithm parameter");
            return null;
        } catch (InvalidKeyException e3) {
            reportKeystoreException(e3, "decrypt had an invalid key");
            return null;
        } catch (NoSuchAlgorithmException e4) {
            reportKeystoreException(e4, "decrypt could not find cipher algorithm AES/GCM/NoPadding");
            return null;
        } catch (BadPaddingException e5) {
            reportKeystoreException(e5, "decrypt had bad padding");
            return null;
        } catch (IllegalBlockSizeException e6) {
            reportKeystoreException(e6, "decrypt had a illegal block size");
            return null;
        } catch (NoSuchPaddingException e7) {
            reportKeystoreException(e7, "decrypt could not find padding algorithm");
            return null;
        }
    }

    private KeyStore getKeyStore() {
        KeyStore keyStore = null;
        for (int counter = 0; counter <= 3 && keyStore == null; counter++) {
            try {
                keyStore = AndroidKeyStoreProvider.getKeyStoreForUid(PointerIconCompat.TYPE_HAND);
            } catch (KeyStoreException e) {
                reportKeystoreException(e, "cannot find the keystore");
            } catch (NoSuchProviderException e2) {
                reportKeystoreException(e2, "cannot find crypto provider");
            }
        }
        return keyStore;
    }

    private SecretKey getOrCreateSecretKey() {
        SecretKey secretKey = null;
        try {
            KeyStore keyStore = getKeyStore();
            if (keyStore.containsAlias(KEYALIAS)) {
                KeyStore.SecretKeyEntry secretKeyEntry = (KeyStore.SecretKeyEntry) keyStore.getEntry(KEYALIAS, null);
                if (secretKeyEntry != null) {
                    secretKey = secretKeyEntry.getSecretKey();
                } else {
                    errorLog("decrypt: secretKeyEntry is null.");
                }
                return secretKey;
            }
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", KEY_STORE);
            KeyGenParameterSpec keyGenParameterSpec = new KeyGenParameterSpec.Builder(KEYALIAS, 3).setBlockModes("GCM").setEncryptionPaddings("NoPadding").setKeySize(256).setUid(PointerIconCompat.TYPE_HAND).build();
            keyGenerator.init(keyGenParameterSpec);
            SecretKey secretKey2 = keyGenerator.generateKey();
            return secretKey2;
        } catch (InvalidAlgorithmParameterException e) {
            reportKeystoreException(e, "getOrCreateSecretKey had an invalid algorithm parameter");
            return null;
        } catch (KeyStoreException e2) {
            reportKeystoreException(e2, "cannot find the keystore: AndroidKeyStore");
            return null;
        } catch (NoSuchAlgorithmException e3) {
            reportKeystoreException(e3, "getOrCreateSecretKey cannot find algorithm");
            return null;
        } catch (NoSuchProviderException e4) {
            reportKeystoreException(e4, "getOrCreateSecretKey cannot find crypto provider");
            return null;
        } catch (ProviderException e5) {
            reportKeystoreException(e5, "getOrCreateSecretKey had a provider exception.");
            return null;
        } catch (UnrecoverableEntryException e6) {
            reportKeystoreException(e6, "getOrCreateSecretKey had an unrecoverable entry exception.");
            return null;
        }
    }

    private static void reportKeystoreException(Exception exception, String error) {
        Log.wtf(TAG, "A keystore error was encountered: " + error, exception);
    }

    private static void reportBluetoothKeystoreException(Exception exception, String error) {
        Log.wtf(TAG, "A bluetoothkey store error was encountered: " + error, exception);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void infoLog(String msg) {
    }

    private static void debugLog(String msg) {
        Log.d(TAG, msg);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void errorLog(String msg) {
        Log.e(TAG, msg);
    }

    private class ComputeDataThread extends Thread {
        private boolean mDoEncrypt;
        private Map<String, String> mSourceDataMap;
        private BlockingQueue<String> mSourceQueue;
        private Map<String, String> mTargetDataMap;
        private boolean mWaitQueueEmptyForStop;

        ComputeDataThread(boolean doEncrypt) {
            BluetoothKeystoreService.infoLog("ComputeDataThread: create, doEncrypt: " + doEncrypt);
            this.mWaitQueueEmptyForStop = false;
            this.mDoEncrypt = doEncrypt;
            if (doEncrypt) {
                this.mSourceDataMap = BluetoothKeystoreService.this.mNameDecryptKey;
                this.mTargetDataMap = BluetoothKeystoreService.this.mNameEncryptKey;
                this.mSourceQueue = BluetoothKeystoreService.this.mPendingEncryptKey;
            } else {
                this.mSourceDataMap = BluetoothKeystoreService.this.mNameEncryptKey;
                this.mTargetDataMap = BluetoothKeystoreService.this.mNameDecryptKey;
                this.mSourceQueue = BluetoothKeystoreService.this.mPendingDecryptKey;
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            BluetoothKeystoreService.infoLog("ComputeDataThread: run, doEncrypt: " + this.mDoEncrypt);
            while (true) {
                if (!this.mSourceQueue.isEmpty() || !this.mWaitQueueEmptyForStop) {
                    try {
                        String prefixString = this.mSourceQueue.take();
                        if (this.mSourceDataMap.containsKey(prefixString)) {
                            String sourceData = this.mSourceDataMap.get(prefixString);
                            String targetData = BluetoothKeystoreService.this.tryCompute(sourceData, this.mDoEncrypt);
                            if (targetData != null) {
                                this.mTargetDataMap.put(prefixString, targetData);
                            } else {
                                BluetoothKeystoreService.errorLog("Computing of Data failed with prefixString: " + prefixString + ", doEncrypt: " + this.mDoEncrypt);
                            }
                        }
                    } catch (InterruptedException e) {
                        BluetoothKeystoreService.infoLog("Interrupted while operating.");
                    }
                } else {
                    BluetoothKeystoreService.infoLog("ComputeDataThread: Stop, doEncrypt: " + this.mDoEncrypt);
                    return;
                }
            }
        }

        public void setWaitQueueEmptyForStop() {
            this.mWaitQueueEmptyForStop = true;
            if (BluetoothKeystoreService.this.mPendingEncryptKey.isEmpty()) {
                interrupt();
            }
        }
    }
}
