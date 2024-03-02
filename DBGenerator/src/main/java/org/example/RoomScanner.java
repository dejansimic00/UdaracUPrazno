/*
package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

public class RoomScanner {

    public static void main(String[] args) throws IOException {
        // Read the QR code image file as byte array
        byte[] qrImageBytes = readQRCodeImage("path/to/qr_code.png");

        // Send QR code to endpoint
        sendQRCodeToEndpoint(qrImageBytes, "https://example.com/api/qrcode");
    }

    public static byte[] readQRCodeImage(String filePath) throws IOException {
        File qrCodeFile = new File(filePath);
        byte[] qrImageBytes = new byte[(int) qrCodeFile.length()];

        try (FileInputStream fis = new FileInputStream(qrCodeFile)) {
            fis.read(qrImageBytes);
        }

        return qrImageBytes;
    }

    public static <HttpURLConnection> void sendQRCodeToEndpoint(byte[] qrImageBytes, String endpointUrl) throws IOException {
        URL url = new URL(endpointUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "image/png");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            os.write(qrImageBytes);
        }

        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        connection.disconnect();
    }
}
*/
