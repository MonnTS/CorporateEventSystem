package com.eventsystem.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.image.BufferedImage;

public class QrCodeGeneratorService {

    private QrCodeGeneratorService() {

    }

    public static BufferedImage generateQRCodeImage(String barcodeText) throws Exception {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix =
                barcodeWriter.encode("Your access key is: [ " + barcodeText + " ]", BarcodeFormat.QR_CODE, 450, 450);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
}
