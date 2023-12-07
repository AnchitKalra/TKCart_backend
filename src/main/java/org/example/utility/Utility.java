package org.example.utility;

import java.util.Base64;

public class Utility {

    public static String convertUploadedFileToBase64(byte bytes[])  {
        return Base64.getEncoder().encodeToString(bytes);
    }
}
