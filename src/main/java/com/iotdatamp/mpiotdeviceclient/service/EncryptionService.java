package com.iotdatamp.mpiotdeviceclient.service;

import com.iotdatamp.mpiotdeviceclient.config.PropertiesBean;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.Security;

@Slf4j
@Service
@RequiredArgsConstructor
public class EncryptionService {

    private static final String ALGO = "AES/ECB/PKCS7Padding";
    private final PropertiesBean properties;

    public String encrypt(String inputTextToEncrypt) throws Exception {

        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        byte[] input = inputTextToEncrypt.getBytes();
        byte[] keyBytes = properties.getEncryptionKey().getBytes();

        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = Cipher.getInstance(ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        ByteArrayInputStream bIn = new ByteArrayInputStream(input);
        CipherInputStream cIn = new CipherInputStream(bIn, cipher);
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();

        int ch;
        while ((ch = cIn.read()) >= 0) {
            bOut.write(ch);
        }

        byte[] cipherText = bOut.toByteArray();

        cipher.init(Cipher.DECRYPT_MODE, key);
        bOut = new ByteArrayOutputStream();
        CipherOutputStream cOut = new CipherOutputStream(bOut, cipher);
        cOut.write(cipherText);
        cOut.close();
        return Hex.toHexString(cipherText);
    }
}
